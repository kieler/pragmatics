/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.evol;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ui.IWorkbenchPart;

import de.cau.cs.kieler.core.WrappedException;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KGraphData;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.ILayoutData;
import de.cau.cs.kieler.kiml.LayoutAlgorithmData;
import de.cau.cs.kieler.kiml.LayoutContext;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.LayoutOptionData.Type;
import de.cau.cs.kieler.kiml.LayoutDataService;
import de.cau.cs.kieler.kiml.LayoutTypeData;
import de.cau.cs.kieler.kiml.config.CompoundLayoutConfig;
import de.cau.cs.kieler.kiml.config.DefaultLayoutConfig;
import de.cau.cs.kieler.kiml.config.ILayoutConfig;
import de.cau.cs.kieler.kiml.evol.genetic.Gene;
import de.cau.cs.kieler.kiml.evol.genetic.Genome;
import de.cau.cs.kieler.kiml.evol.genetic.TypeInfo;
import de.cau.cs.kieler.kiml.evol.genetic.TypeInfo.GeneType;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.service.LayoutInfoService;
import de.cau.cs.kieler.kiml.ui.diagram.LayoutMapping;
import de.cau.cs.kieler.kiml.ui.service.EclipseLayoutConfig;
import de.cau.cs.kieler.kiml.ui.service.EclipseLayoutInfoService;
import de.cau.cs.kieler.kiml.ui.views.LayoutPropertySource;

/**
 * A factory for genes and genomes.
 *
 * @author bdu
 * @author msp
 */
public final class GenomeFactory {

    public static final String LAYOUT_TYPE_ID = "de.cau.cs.kieler.kiml.evol.layoutType";

    public static Genome createInitialGenome(final LayoutMapping<?> layoutMapping,
            final Pair<ILayoutConfig, LayoutContext> configPair) {
        LayoutDataService dataService = LayoutDataService.getInstance();
        ILayoutConfig config = configPair.getFirst();
        LayoutContext context = configPair.getSecond();
        LayoutOptionData<?> algoOptionData = dataService.getOptionData(LayoutOptions.ALGORITHM.getId());
        String algorithmId = (String) config.getValue(algoOptionData, context);
        LayoutAlgorithmData algorithmData = dataService.getAlgorithmData(algorithmId);
        
        Genome genome = new Genome(dataService.getOptionData().size());
        
        // create gene for the layout type
        LayoutTypeData typeData = dataService.getTypeData(algorithmData.getType());
        genome.getGenes().add(createLayoutTypeGene(typeData));
        
        // create gene for the layout algorithm
        genome.getGenes().add(createAlgorithmGene(typeData, algorithmData));
        
        // create genes for the other layout options
        for (LayoutOptionData<?> optionData : dataService.getOptionData()) {
            if (optionData.getTargets().contains(LayoutOptionData.Target.PARENTS)
                    && optionData.getVariance() > 0) {
                Object value = config.getValue(optionData, context);
                Gene<?> gene = createGene(optionData, value);
                if (gene != null) {
                    genome.getGenes().add(gene);
                }
            }
        }
        return genome;
    }
    
    private static final double P_LAYOUT_TYPE_MUTATION = 0.04;
    
    public static Gene<Integer> createLayoutTypeGene(final LayoutTypeData typeData) {
        List<LayoutTypeData> typeList = new ArrayList<LayoutTypeData>(LayoutDataService.getInstance()
                .getTypeData());
        int index = typeList.indexOf(typeData);
        TypeInfo<Integer> typeInfo = new TypeInfo<Integer>(GeneType.LAYOUT_TYPE, 0,
                typeList.size() - 1, typeList, P_LAYOUT_TYPE_MUTATION, 1);
        return Gene.create(LAYOUT_TYPE_ID, index, typeInfo);
    }
    
    private static final double P_LAYOUT_ALGO_MUTATION = 0.08;
    
    public static final Gene<Integer> createAlgorithmGene(final LayoutTypeData layoutType,
            final LayoutAlgorithmData algoData) {
        List<LayoutAlgorithmData> algoList = layoutType.getLayouters();
        int index = algoList.indexOf(algoData);
        TypeInfo<Integer> typeInfo = new TypeInfo<Integer>(GeneType.LAYOUT_ALGO, 0,
                algoList.size() - 1, algoList, P_LAYOUT_ALGO_MUTATION, 1);
        return Gene.create(LayoutOptions.ALGORITHM.getId(), index, typeInfo);
    }
    
    public static final Gene<Integer> createAlgorithmGene(final LayoutTypeData layoutType,
            final Random random) {
        List<LayoutAlgorithmData> algoList = layoutType.getLayouters(); 
        TypeInfo<Integer> typeInfo = new TypeInfo<Integer>(GeneType.LAYOUT_ALGO, 0,
                algoList.size() - 1, algoList, P_LAYOUT_ALGO_MUTATION, 1);
        int randomAlgoIndex = random.nextInt(algoList.size());
        return Gene.create(LayoutOptions.ALGORITHM.getId(), randomAlgoIndex, typeInfo);
    }
    
    private static final double P_BOOLEAN_MUTATION = 0.1;
    private static final double P_ENUM_MUTATION = 0.12;
    private static final double P_INT_MUTATION = 0.15;
    private static final double P_FLOAT_MUTATION = 0.2;
    
    @SuppressWarnings("unchecked")
    public static final Gene<?> createGene(final LayoutOptionData<?> optionData, final Object value) {
        TypeInfo<?> typeInfo;
        switch (optionData.getType()) {
        case BOOLEAN:
            typeInfo = new TypeInfo<Integer>(GeneType.BOOLEAN, 0, 1, optionData, P_BOOLEAN_MUTATION, 1);
            break;
        case ENUM:
            typeInfo = new TypeInfo<Integer>(GeneType.ENUM, 0,
                    optionData.getOptionClass().getEnumConstants().length - 1,
                    optionData, P_ENUM_MUTATION, 1);
            break;
        case INT:
            typeInfo = new TypeInfo<Integer>(GeneType.INTEGER,
                    (Comparable<Integer>) optionData.getLowerBound(),
                    (Comparable<Integer>) optionData.getUpperBound(),
                    optionData, P_INT_MUTATION, optionData.getVariance());
            break;
        case FLOAT:
            typeInfo = new TypeInfo<Float>(GeneType.FLOAT,
                    (Comparable<Float>) optionData.getLowerBound(),
                    (Comparable<Float>) optionData.getUpperBound(),
                    optionData, P_FLOAT_MUTATION, optionData.getVariance());
            break;
        default:
            return null;
        }
        return Gene.create(optionData.getId(), translateToGene(value, typeInfo), typeInfo);
    }
    
    public static Pair<ILayoutConfig, LayoutContext> createConfig(
            final LayoutMapping<?> layoutMapping) {
        // create basic layout configuration
        CompoundLayoutConfig clc = new CompoundLayoutConfig();
        clc.add(new DefaultLayoutConfig());
        for (ILayoutConfig config : LayoutInfoService.getInstance().getActiveConfigs()) {
            if (!(config instanceof EvolLayoutConfig)) {
                clc.add(config);
            }
        }
        clc.addAll(layoutMapping.getLayoutConfigs());
        
        // create a layout context for the given layout graph
        LayoutContext context = new LayoutContext();
        context.setProperty(LayoutContext.GRAPH_ELEM, layoutMapping.getLayoutGraph());
        Object diagramPart = layoutMapping.getGraphMap().get(layoutMapping.getLayoutGraph());
        context.setProperty(LayoutContext.DIAGRAM_PART, diagramPart);
        EObject modelElement = (EObject) layoutMapping.getAdapterFactory().getAdapter(
                diagramPart, EObject.class);
        context.setProperty(LayoutContext.DOMAIN_MODEL, modelElement);
        IWorkbenchPart workbenchPart = layoutMapping.getProperty(IWorkbenchPart.class);
        context.setProperty(EclipseLayoutConfig.WORKBENCH_PART, workbenchPart);
        
        // add semantic configurations from the extension point
        if (modelElement != null) {
            List<ILayoutConfig> semanticConfigs = EclipseLayoutInfoService.getInstance()
                    .getSemanticConfigs(modelElement.eClass());
            clc.addAll(semanticConfigs);
        }

        // enrich the layout context using the basic configuration
        clc.enrich(context);

        return new Pair<ILayoutConfig, LayoutContext>(clc, context);
    }
    
    @SuppressWarnings("unchecked")
    public static <T extends Comparable<? super T>> Gene<T> createDefaultGene(
            final LayoutAlgorithmData algoData, final LayoutOptionData<?> optionData,
            final TypeInfo<T> typeInfo) {
        Pair<ILayoutConfig, LayoutContext> configPair = EvolutionModel.getInstance().getConfigPair();
        if (configPair == null) {
            throw new IllegalStateException("The evolution model has not been initialized yet.");
        }
        Object value = configPair.getFirst().getValue(optionData, configPair.getSecond());
        
        Comparable<Object> lowerBound = (Comparable<Object>) optionData.getLowerBound();
        if (lowerBound.compareTo(value) > 0) {
            value = lowerBound;
        }
        Comparable<Object> upperBound = (Comparable<Object>) optionData.getUpperBound();
        if (upperBound.compareTo(value) < 0) {
            value = upperBound;
        }
        return Gene.create(optionData.getId(), translateToGene(value, typeInfo), typeInfo);
    }
    
    @SuppressWarnings("unchecked")
    public static <T extends Comparable<? super T>> T translateToGene(final Object value,
            final TypeInfo<T> typeInfo) {
        switch (typeInfo.getGeneType()) {
        case LAYOUT_ALGO:
            LayoutAlgorithmData algoData = LayoutDataService.getInstance().getAlgorithmData(
                    (String) value);
            int algoIndex = ((List<?>) typeInfo.getTypeParam()).indexOf(algoData);
            return (T) Integer.valueOf(algoIndex);
        case LAYOUT_TYPE:
            LayoutTypeData typeData = LayoutDataService.getInstance().getTypeData((String) value);
            int typeIndex = ((List<?>) typeInfo.getTypeParam()).indexOf(typeData);
            return (T) Integer.valueOf(typeIndex);
        case BOOLEAN:
            return (T) Integer.valueOf((((Boolean) value) ? 1 : 0));
        case ENUM:
            return (T) Integer.valueOf(((Enum<?>) value).ordinal());
        default:
            return (T) value;
        }
    }
    
    /**
     * Translate a gene value into a type understood by meta layout.
     * 
     * @param gene a gene
     * @return the translated gene value
     */
    public static Object translateFromGene(final Gene<?> gene) {
        GeneType geneType = gene.getTypeInfo().getGeneType();
        switch (geneType) {
        case LAYOUT_ALGO:
        case LAYOUT_TYPE:
            return ((ILayoutData) gene.listValue()).getId();
        case BOOLEAN:
            return (Integer) gene.getValue() != 0;
        case ENUM:
            return gene.enumValue();
        default:
            return gene.getValue();
        }
    }
    
    public static void configureGraph(final KNode parentNode, final Genome genome) {
        LayoutDataService dataService = LayoutDataService.getInstance();
        KShapeLayout parentLayout = parentNode.getData(KShapeLayout.class);
        for (Gene<?> gene : genome.getGenes()) {
            if (gene.getValue() != null) {
                LayoutOptionData<?> optionData = dataService.getOptionData(gene.getId());
                if (optionData != null) {
                    parentLayout.setProperty(optionData, translateFromGene(gene));
                }
            }
        }
        for (KNode child : parentNode.getChildren()) {
            if (!child.getChildren().isEmpty()) {
                configureGraph(child, genome);
            }
        }
    }

    
    /**
     * A factory for genes, used to create genes that encode layout option.
     *
     * @author bdu
     *
     */
    private static class LayoutOptionGeneFactory implements IGeneFactory {
        /**
         * The absolute value is scaled by this factor to get the default
         * variance.
         */
        private static final double VARIANCE_SCALING_FACTOR = 0.20;
        /**
         * Identifier of the attribute "lower bound".
         */
        private static final String ATTRIBUTE_LOWER_BOUND = "lowerBound";
        /**
         * Identifier of the attribute "upper bound".
         */
        private static final String ATTRIBUTE_UPPER_BOUND = "upperBound";
        /**
         * Identifier of the attribute "distribution".
         */
        private static final String ATTRIBUTE_DISTRIBUTION = "distribution";
        /**
         * Identifier of the attribute "variance".
         */
        private static final String ATTRIBUTE_VARIANCE = "variance";

        /**
         * Creates a new {@link LayoutOptionGeneFactory}.
         */
        LayoutOptionGeneFactory() {
            // nothing to do here
        }

        /**
         * Creates a gene with the specified value for the given layout option.
         * The layout option must be registered as evolution data.
         *
         * @param theId
         *            a valid ID of a layout option that is registered as
         *            evolution data
         * @param theValue
         *            a value
         * @param theMutationProbability
         *            the mutation probability
         * @return a gene
         */
        public IGene<?> newGene(
                final String theId, final Object theValue, final double theMutationProbability) {

            IGene<?> result;

            // Get the evolution data for the layout option.
            IConfigurationElement evolutionData =
                    EvolutionServices.getInstance().getEvolutionData((String) theId);
            if (evolutionData == null) {
                throw new IllegalArgumentException("No evolution data registered for " + theId);
            }

            String lowerBoundAttr = evolutionData.getAttribute(ATTRIBUTE_LOWER_BOUND);
            String upperBoundAttr = evolutionData.getAttribute(ATTRIBUTE_UPPER_BOUND);
            String distrNameAttr = evolutionData.getAttribute(ATTRIBUTE_DISTRIBUTION);
            String varianceAttr = evolutionData.getAttribute(ATTRIBUTE_VARIANCE);

            Distribution distr = null;
            try {
                distr = Distribution.valueOf(distrNameAttr);
            } catch (final NullPointerException npe) {
                throw new IllegalArgumentException("Distribution attribute missing or empty for "
                        + theId, npe);
            } catch (final IllegalArgumentException iae) {
                throw new IllegalArgumentException("Invalid evolution data for " + theId + ": "
                        + distrNameAttr, iae);
            }

            // Get the layout option data, so we know what kind of gene to
            // produce.
            LayoutOptionData<?> layoutOptionData =
                    LayoutDataService.getInstance().getOptionData((String) theId);
            if (layoutOptionData == null) {
                throw new IllegalArgumentException("No layout option data for " + theId);
            }
            Type layoutOptionDataType = layoutOptionData.getType();

            switch (layoutOptionDataType) {
            case BOOLEAN:
                result = newBooleanGene(theId, theValue, theMutationProbability, distr);
                break;

            case ENUM:
                result = newEnumGene(theId, theValue, theMutationProbability, layoutOptionData);
                break;

            case INT:
                result =
                        newIntegerGene(theId, theValue, theMutationProbability, lowerBoundAttr,
                                upperBoundAttr, varianceAttr, distr);
                break;

            case FLOAT:
                result =
                        newFloatGene(theId, theValue, theMutationProbability, lowerBoundAttr,
                                upperBoundAttr, varianceAttr, distr);
                break;

            default:
                result = null;
                break;
            }
            return result;
        }

        /**
         * <strong>Not implemented here.</strong> Throws an
         * {@link UnsupportedOperationException}.
         *
         * @param theId
         *            the id
         * @param theValue
         *            the value
         * @param theTypeInfo
         *            the type info
         * @param theMutationInfo
         *            the mutation info
         * @return nothing
         */
        public IGene<?> newGene(
                final String theId, final Object theValue, final TypeInfo<?> theTypeInfo,
                final MutationInfo theMutationInfo) {
            throw new UnsupportedOperationException();
        }

        /**
         * Creates a gene for a boolean value.
         *
         * @param theId
         *            the id
         * @param theValue
         *            a string that can be parsed into a Boolean
         * @param theMutationProbability
         *            the mutation probability
         * @param distr
         *            the distribution
         * @return the new gene
         */
        private IGene<?> newBooleanGene(
                final String theId, final Object theValue, final double theMutationProbability,
                final Distribution distr) {
            IGene<?> result;
            boolean booleanValue = Boolean.parseBoolean(theValue.toString());

            Float floatValue = booleanValue ? 1.0f : 0.0f;
            MutationInfo mutationInfo = new MutationInfo(theMutationProbability, distr);
            result =
                    new UniversalNumberGene(theId, floatValue, UniversalNumberGene.BOOLEAN_TYPE_INFO,
                            mutationInfo);
            EvolPlugin.logStatus(theId + ": " + result);
            return result;
        }

        /**
         * Creates a gene for an enum value, using the given value and the given
         * layout option data.
         *
         * @param theId
         *            the id
         * @param theRawValue
         *            the raw value; may be an integer, a string or a enum
         *            constant
         * @param theMutationProbability
         *            the mutation probability
         * @param layoutOptionData
         *            the layout option data
         * @return the new gene
         */
        private IGene<?> newEnumGene(
                final String theId, final Object theRawValue,
                final double theMutationProbability, final LayoutOptionData<?> layoutOptionData) {
            IGene<?> result;
            int choicesCount = layoutOptionData.getChoices().length;
            Class<? extends Enum<?>> enumClass =
                    (Class<? extends Enum<?>>) layoutOptionData.getOptionClass();
            assert enumClass != null;
            final Enum<?>[] constants = enumClass.getEnumConstants();
            assert constants.length == choicesCount;
            Integer value = null;
            if (theRawValue instanceof Integer) {
                value = Integer.valueOf(theRawValue.toString());
            } else if (theRawValue instanceof String) {
                List<Enum<?>> constantsList = Arrays.asList(constants);
                for (final Enum<?> enumConstant : constantsList) {
                    if (enumConstant.toString().equals(theRawValue)) {
                        value = enumConstant.ordinal();
                        break;
                    }
                }
                if (value == null) {
                    value = 0;
                }
            } else if (theRawValue instanceof Enum) {
                value = ((Enum<Type>) theRawValue).ordinal();
            } else {
                value = 0;
            }
            result = new Gene(theId, value, new TypeInfo<Integer>(0, Integer.valueOf(0),
                    Integer.valueOf(enumClass.getEnumConstants().length - 1),
                    enumClass, theMutationProbability, Distribution.UNIFORM);
            EvolPlugin.logStatus("Enum " + enumClass.getSimpleName() + "(" + choicesCount + "): "
                    + enumClass.getEnumConstants()[value.intValue()] + " (" + value + ")");
            return result;
        }

        /**
         * Creates a gene for a float value.
         *
         * @param theId
         *            the id
         * @param theRawValue
         *            an object containing a string that represents a float
         * @param theMutationProbability
         *            the mutation probability
         * @param lowerBoundAttr
         *            the lower bound attribute as defined in the evolution data
         * @param upperBoundAttr
         *            the upper bound attribute as defined in the evolution data
         * @param varianceAttr
         *            the variance attribute as defined in the evolution data
         * @param distr
         *            the distribution
         * @return the new gene
         */
        private IGene<?> newFloatGene(
                final String theId, final Object theRawValue,
                final double theMutationProbability, final String lowerBoundAttr,
                final String upperBoundAttr, final String varianceAttr, final Distribution distr) {
            // TODO: Too many parameters --> refactor. Remove variance stuff.
            IGene<?> result;
            Float value = Float.valueOf((String) theRawValue);
            Float lowerBound =
                    Float.valueOf(lowerBoundAttr == null ? Float.NEGATIVE_INFINITY : Float
                            .parseFloat(lowerBoundAttr));
            Float upperBound =
                    Float.valueOf((upperBoundAttr == null) ? Float.POSITIVE_INFINITY : Float
                            .parseFloat(upperBoundAttr));

            double variance;
            if (varianceAttr != null) {
                // get variance from evolution data
                variance = Double.parseDouble(varianceAttr);

            } else {
                // threshold to prevent very small variances
                final float verySmall = 1e-3f;

                // estimate desired variance from the absolute value
                if (Math.abs(value.floatValue()) < verySmall) {
                    variance = MutationInfo.DEFAULT_VARIANCE;
                } else {
                    variance = Math.abs(value.floatValue()) * VARIANCE_SCALING_FACTOR;
                }
            }

            IValueFormatter formatter;
            if (lowerBound.floatValue() >= 0.0f) {
                // we use a strictly positive float gene
                formatter = UniversalNumberGene.STRICTLY_POSITIVE_FLOAT_FORMATTER;

            } else {
                // we use a general float gene
                formatter = UniversalNumberGene.FLOAT_FORMATTER;
            }

            value = sanitize(value, lowerBound, upperBound, (String) theId);

            TypeInfo<Float> typeInfo =
                    new TypeInfo(value, lowerBound, upperBound, formatter, Float.class);

            MutationInfo mutationInfo =
                    new MutationInfo(theMutationProbability, variance, distr);
            result = new UniversalNumberGene(theId, value, typeInfo, mutationInfo);
            EvolPlugin.logStatus(theId + ": " + result);
            return result;
        }

        /**
         * Creates a gene for an integer value.
         *
         * @param theId
         *            the ID
         * @param theRawValue
         *            the raw value
         * @param theMutationProbability
         *            the mutation probability
         * @param lowerBoundAttr
         *            the lower bound attribute as defined in the evolution data
         * @param upperBoundAttr
         *            the upper bound attribute as defined in the evolution data
         * @param varianceAttr
         *            the variance attribute as defined in the evolution data
         * @param distr
         *            the distribution
         * @return the new gene
         */
        private IGene<?> newIntegerGene(
                final String theId, final Object theRawValue,
                final double theMutationProbability, final String lowerBoundAttr,
                final String upperBoundAttr, final String varianceAttr, final Distribution distr) {
            // TODO: Too many parameters --> refactor. Remove variance stuff.
            IGene<?> result;
            Integer value = Integer.valueOf((String) theRawValue);

            double variance;
            if (varianceAttr != null) {
                // get variance from evolution data
                variance = Double.parseDouble(varianceAttr);
            } else {
                // estimate desired variance from the absolute value
                variance = Math.abs(value.intValue()) * VARIANCE_SCALING_FACTOR;
            }

            Integer lowerBound =
                    Integer.valueOf((lowerBoundAttr == null) ? Integer.MIN_VALUE : (Integer
                            .parseInt(lowerBoundAttr)));

            Integer upperBound =
                    Integer.valueOf((upperBoundAttr == null) ? Integer.MAX_VALUE : (Integer
                            .parseInt(upperBoundAttr)));

            IValueFormatter formatter = UniversalNumberGene.INTEGER_FORMATTER;

            // enforce that the value is within the legal bounds
            value =
                    sanitize(value.floatValue(), lowerBound.floatValue(),
                            upperBound.floatValue(), (String) theId).intValue();

            TypeInfo<Float> typeInfo =
                    new TypeInfo(value.floatValue(), lowerBound.floatValue(),
                            upperBound.floatValue(), formatter, Integer.class);

            MutationInfo mutationInfo =
                    new MutationInfo(theMutationProbability, variance, distr);
            result = new UniversalNumberGene(theId, value.floatValue(), typeInfo, mutationInfo);
            EvolPlugin.logStatus(theId + ": " + result);
            return result;
        }

        /**
         * Enforces that the value is within the specified legal bounds.
         *
         * @param value
         *            the value
         * @param lowerBound
         *            the lower bound
         * @param upperBound
         *            the upper bound
         * @param id
         *            identifier of the value
         * @return the specified value, if it is within the specified bounds, or
         *         else a value that is within the specified bounds.
         */
        private Float sanitize(
                final Float value, final Float lowerBound, final Float upperBound, final String id) {

            Float result = value;

            // check bounds
            if (value.floatValue() < lowerBound.floatValue()) {
                System.err.println("WARNING: value: " + value + " < lower bound: " + lowerBound
                        + ") for " + id);
                result = lowerBound;
            }

            if (value.floatValue() > upperBound.floatValue()) {
                System.err.println("WARNING: value: " + value + " > upper bound: " + upperBound
                        + ") for " + id);
                result = upperBound;
            }

            return result;
        }
    }

    /**
     * Create a weight genome for the given metric IDs.
     *
     * @param metricIds
     *            a set of metric IDs; may not be {@code null}
     * @return a genome
     */
    public static Genome createWeightGenes(final Set<String> metricIds) {
        if (metricIds == null) {
            throw new IllegalArgumentException();
        }

        IGeneFactory factory = new IGeneFactory() {
            public IGene<?> newGene(
                    final String theId, final Object theValue, final double theMutationProb) {

                final float defaultValue = 1.0f;
                final float lowerBound = 0.0f;
                final float upperBound = 10.0f;
                final double variance = 0.2;

                TypeInfo<Float> typeInfo =
                        new TypeInfo(defaultValue, lowerBound, upperBound, Float.class);
                MutationInfo mutationInfo =
                        new MutationInfo(theMutationProb, variance, Distribution.GAUSSIAN);

                return this.newGene(theId, theValue, typeInfo, mutationInfo);
            }


            public IGene<?> newGene(
                    final String theId, final Object theValue, final TypeInfo<?> theTypeInfo,
                    final MutationInfo theMutationInfo) {
                return new UniversalNumberGene(theId, (Float) theValue, theTypeInfo,
                        theMutationInfo);
            }
        };

        Genome result = new Genome();
        float value = 1.0f;
        final double mutationProb = 0.02;
        for (final String id : metricIds) {
            IGene<?> gene = factory.newGene(id, value, mutationProb);
            assert gene != null : "Failed to create gene for " + id;
            result.getGenes().add(gene);
        }
        return result;
    }

    /**
     * Collect the learnable properties from the given list of
     * IPropertyDescriptor objects.
     *
     * @param descriptors
     *            a collection of property descriptors
     * @param acceptedProperties
     *            a set of accepted properties. If this is {@code null}, then
     *            all registered properties are used.
     * @return learnable properties
     */
    private static Set<IPropertyDescriptor> collectLearnableProperties(
            final Collection<IPropertyDescriptor> descriptors,
            final Set<String> acceptedProperties) {

        if ((descriptors == null) || descriptors.isEmpty()) {
            return Collections.emptySet();
        }

        Set<String> accepted;
        if (acceptedProperties == null) {
            // Get the set of all registered learnable properties.
            accepted = EvolutionServices.getInstance().getEvolutionDataIds();
        } else {
            accepted = acceptedProperties;
        }

        LayoutDataService layoutServices = LayoutDataService.getInstance();

        Set<IPropertyDescriptor> result = new HashSet<IPropertyDescriptor>();

        // Iterate the given property descriptors.
        for (final IPropertyDescriptor descriptor : descriptors) {
            String id = (String) descriptor.getId();
            // check property descriptor id
            if (!LayoutOptions.ALGORITHM.getId().equals(id)) {
                LayoutOptionData<?> data = layoutServices.getOptionData(id);
                assert data != null : "Layout option not registered: " + id;

                Type type = data.getType();
                switch (type) {
                case BOOLEAN:
                case ENUM:
                case INT:
                case FLOAT:
                    if (accepted.contains(id)) {
                        // learnable --> collect it
                        result.add(descriptor);
                    }
                    break;
                default:
                    // technically not learnable --> don't count
                    break;
                }
            }
        }
        return result;
    }

    /**
     * Collects the property values from the given layout configurations. If a
     * property is present in more than one of the sources, the value defined in
     * its first occurrence prevails. Property values that contain layout hints
     * are ignored.
     *
     * @param configs list of layout configurations
     * @return a map storing the property values
     */
    private static Map<String, Object> collectPropertyValues(
            final List<LayoutContext> configs) {
        final int expectedNumberOfPropsPerConfig = 10;
        HashMap<String, Object> propertyId2ValueMap =
                new HashMap<String, Object>(configs.size() * expectedNumberOfPropsPerConfig);

        // Iterate the layout inspectors.
        for (final LayoutContext context : configs) {
            // Iterate the available layout options.
            List<LayoutOptionData<?>> optionDescriptors = context.getProperty(DefaultLayoutConfig.OPTIONS);
            for (final LayoutOptionData<?> data : optionDescriptors) {

                String id = data.getId();

                if (LayoutOptions.ALGORITHM.getId().equals(id)) {
                    // Property is a layout hint --> skip
                    continue;

                } else if (!propertyId2ValueMap.containsKey(id)) {
                    ILayoutConfig config = context.getProperty(EvolUtil.LAYOUT_CONFIG);
                    Object value = config.getValue(data, context);
                    propertyId2ValueMap.put(id, value);
                } else {
                    // already added this option
                    EvolPlugin.logStatus("Duplicate property: " + id);
                }
            }
        }
        return propertyId2ValueMap;
    }

    /**
     * Creates a layout hint gene.
     *
     * @param algorithmIds
     *            list of layout algorithm IDs; must not be {@code null}
     * @param defaultEntry
     *            index of the default algorithm ID; must be a valid index of
     *            {@code algorithmIds}
     *
     * @return a gene that can mutate over the given algorithms
     */
    private static ListItemGene createLayoutHintGene(
            final List<String> algorithmIds, final int defaultEntry) {
        if (algorithmIds == null) {
            throw new IllegalArgumentException();
        }

        if ((defaultEntry < 0) || (defaultEntry >= algorithmIds.size())) {
            throw new IllegalArgumentException("Index out of range: " + defaultEntry);
        }

        TypeInfo typeInfo = new TypeInfo(defaultEntry, Integer.valueOf(0),
                Integer.valueOf(algorithmIds.size() - 1), algorithmIds.getClass());
        double prob = DEFAULT_LAYOUT_HINT_GENE_MUTATION_PROBABILITY;
        MutationInfo mutationInfo = new MutationInfo(prob);

        ListItemGene hintGene =
                new ListItemGene(LayoutOptions.ALGORITHM.getId(), defaultEntry, algorithmIds, typeInfo,
                        mutationInfo);
        return hintGene;
    }

    /**
     * Creates a layout hint gene.
     *
     * @param algorithmIds
     *            a list of layout algorithm identifiers
     * @param defaultAlgorithmId
     *            the identifier of the default layout algorithm
     * @return a gene that can mutate over the given layout algorithms
     */
    private static ListItemGene createLayoutHintGene(
            final List<String> algorithmIds, final String defaultAlgorithmId) {
        // presuming algorithmIds != null
        if (algorithmIds.isEmpty() || (defaultAlgorithmId == null)) {
            throw new IllegalArgumentException();
        }

        int indexOfAlgorithmId = algorithmIds.indexOf(defaultAlgorithmId);
        assert indexOfAlgorithmId >= 0;

        ListItemGene result = createLayoutHintGene(algorithmIds, indexOfAlgorithmId);
        return result;
    }

    /**
     * Collects the property descriptors from the given layout configurations.
     *
     * @param configs
     *            list of layout configurations; must not be {@code null}
     * @return a map containing property descriptor IDs and the respective
     *         property descriptors.
     */
    private static Map<String, IPropertyDescriptor> getPropertyDescriptors(
            final List<LayoutContext> configs) {
        Map<String, IPropertyDescriptor> allPropertyDescriptors =
                new HashMap<String, IPropertyDescriptor>();

        for (final LayoutContext context : configs) {
            Object editPart = context.getProperty(LayoutContext.DIAGRAM_PART);
            EditingDomain editingDomain = GraphicalFrameworkService.getInstance().getBridge(editPart)
                    .getEditingDomain(editPart);
            LayoutPropertySource source = new LayoutPropertySource(
                    context.getProperty(EvolUtil.LAYOUT_CONFIG), context,
                    (TransactionalEditingDomain) editingDomain);
            IPropertyDescriptor[] propertyDescriptors = source.getPropertyDescriptors();

            for (final IPropertyDescriptor pd : propertyDescriptors) {
                allPropertyDescriptors.put((String) pd.getId(), pd);
            }
        }
        return allPropertyDescriptors;
    }

    /**
     * Returns uniform probability for the given number of choices.
     *
     * @param choicesCount
     *            number of choices
     * @return the multiplicative inverse of the given number, or 0.0 if it is
     *         not > 0.
     */
    private static double uniformProbability(final int choicesCount) {
        double uniformProb;
        if (choicesCount > 0) {
            uniformProb = 1.0 / choicesCount;
        } else {
            EvolPlugin.showError("No learnable properties found.", null);
            uniformProb = 0.0;
        }
        return uniformProb;
    }

    /**
     * Creates a new {@link GenomeFactory} instance.
     *
     * @param theLearnableOptions
     *            the set of learnable options. If this is {@code null}, all
     *            options registered as evolution data are used instead.
     *
     */
    public GenomeFactory(final Set<String> theLearnableOptions) {
        if (theLearnableOptions != null) {
            this.learnableOptions = theLearnableOptions;
        } else {
            // Get the set of all registered learnable elements.
            this.learnableOptions = EvolutionServices.getInstance().getEvolutionDataIds();
        }
    }

    // Private fields.
    /** The set of all learnable elements that are accepted. */
    private final Set<String> learnableOptions;

    /** The gene factory used to produce layout option genes. */
    private final IGeneFactory layoutOptionGeneFactory = new LayoutOptionGeneFactory();

    /**
     * Default mutation probability for layout hint genes.
     */
    private static final double DEFAULT_LAYOUT_HINT_GENE_MUTATION_PROBABILITY = 0.05;

    /**
     * Create a {@link Genome} from the given layout configurations.
     *
     * @param configs
     *            list of layout configurations
     * @param layoutHintIds
     *            set of layout hint IDs
     * @return a genome, or {@code null}.
     */
    public Genome createGenome(final List<LayoutContext> configs, final Set<Object> layoutHintIds) {
        // TODO: split this method
        if ((configs == null) || (layoutHintIds == null) || layoutHintIds.isEmpty()) {
            return null;
        }

        /*
         * Discuss: If more than one ILayoutConfig is contained in the given list,
         * they may be for different layout algorithms.
         * Should the genes from different layout algorithms
         *   - be pooled without hierarchy?
         *   - be mapped to their layout algorithm id?
         *   - or be grouped together as hierarchical "chromosomes" in the individual?
         * What about duplicate properties?
         * */

        Genome result = new Genome();

        // Get property descriptors for the layout inspectors.
        Map<String, IPropertyDescriptor> allPropertyDescriptors = getPropertyDescriptors(configs);

        // Collect the learnable properties from the property descriptors.
        Set<IPropertyDescriptor> presentLearnables =
                collectLearnableProperties(allPropertyDescriptors.values(), this.learnableOptions);

        // Determine uniformly distributed mutation probability.
        double uniformProb = uniformProbability(presentLearnables.size());

        EvolPlugin.logStatus("Creating genome of " + presentLearnables.size()
                + " layout property genes ...");

        // Collect the property values from the layout inspectors.
        // XXX This should be done once by the GenomeFactory constructor.
        Map<String, Object> propertyId2ValueMap = collectPropertyValues(configs);

        // Create genes for the property values.
        for (final Entry<String, Object> entry : propertyId2ValueMap.entrySet()) {

            String id = entry.getKey();
            Object value = entry.getValue();

            // Check the property descriptor id.
            assert !LayoutOptions.ALGORITHM.getId().equals(id) : "There should be no algorithm in the collected options.";

            // There should not be a gene for this option yet.
            assert result.find(id) == null : "Duplicate property: " + id;

            // learnable option?
            if (this.learnableOptions.contains(id)) {
                IGene<?> gene = null;
                assert value != null : "Value is null: " + id;
                // XXX convert value to string for legacy reasons
                gene =
                        this.layoutOptionGeneFactory.newGene(id, value.toString(),
                                uniformProb);
                assert gene != null : "Failed to create gene for " + id;
                result.getGenes().add(gene);

            } else {
                EvolPlugin.logStatus("Option not registered as evolutionData: " + id);
            }
        }

        assert presentLearnables.size() == result.getSize() :
                "The number of genes does not have the predicted count of "
                + presentLearnables.size();

        assert !layoutHintIds.isEmpty() : "No layout hint specified.";

        // Add a gene for the layout hint (a gene that can mutate over a list of
        // layout hint IDs).

        // TODO: If we have more than one layout hint, we use the first as
        // default, but what about the others?
        String hintId = (String) layoutHintIds.iterator().next();

        LayoutAlgorithmData algorithmData =
                new DefaultLayoutConfig().getLayouterData(hintId, null);

        if (algorithmData == null) {
            // no algorithm for the given layout hint
            return null;
        }

        // Get the type of the layout algorithm.
        String typeId = algorithmData.getType();

        // Get the IDs of all suitable algorithms for this type.
        List<String> algorithmIds = EvolUtil.getLayoutAlgorithmIds(typeId);

        String algorithmId = algorithmData.getId();

        // Create the layout hint gene.
        ListItemGene hintGene = createLayoutHintGene(algorithmIds, algorithmId);
        result.getGenes().add(hintGene);

        // Collect all learnable layout options that are known by the
        // algorithms.
        Set<String> knownOptionIds = getLearnableKnownOptions(algorithmIds);

        // Add extra genes for the suitable options that have not been
        // added yet.
        List<String> presentIds = result.getIds();

        try {
            Genome extraGenes = createGenes(knownOptionIds, presentIds, uniformProb, null);
            result.getGenes().addAll(extraGenes.getGenes());
        } catch (final Exception exception) {
            throw new WrappedException(exception, "Genome could not be created.");
        }

        EvolPlugin.logStatus("Created genome: " + result.getSize() + " genes.");

        return result;
    }

    /**
     * Creates a genome of extra genes (for known options that are not present).
     *
     * @param knownOptionIds
     *            set of identifiers of the known options; must not be
     *            {@code null}
     * @param presentIds
     *            list of identifiers that are present; must not be {@code null}
     * @param prob
     *            mutation probability
     * @param theGeneFactory
     *            an {@link IGeneFactory}; may be {@code null}
     * @return the created genome
     */
    private Genome createGenes(
            final Set<String> knownOptionIds, final List<String> presentIds, final double prob,
            final IGeneFactory theGeneFactory) {

        if ((knownOptionIds == null) || (presentIds == null)) {
            throw new IllegalArgumentException("'knownOptionIds' or 'presentIds' was null.");
        }

        Genome extraGenes = new Genome();

        IGeneFactory geneFactory =
                (theGeneFactory == null) ? this.layoutOptionGeneFactory : theGeneFactory;

        LayoutDataService layoutServices = LayoutDataService.getInstance();

        for (final String optionId : knownOptionIds) {
            if (!presentIds.contains(optionId)) {
                EvolPlugin.logStatus("Creating extra gene for " + optionId);
                LayoutOptionData<?> optionData = layoutServices.getOptionData(optionId);

                if (optionData == null) {
                    throw new IllegalStateException("Could not get layout option data: " + optionId);
                }

                Object value = optionData.getDefault();

                IGene<?> gene = geneFactory.newGene(optionId, value.toString(), prob);
                assert gene != null : "Failed to create gene for " + optionId;
                extraGenes.getGenes().add(gene);
            }
        }
        return extraGenes;
    }

    /**
     * Determines which of the registered layout options are known by the
     * specified algorithms.
     *
     * @param algorithmIds
     *            a list of layout algorithm IDs; must not be {@code null}
     * @return a set containing the IDs of the layout options that are known by
     *         the specified algorithms and that are registered as evolutionData
     */
    private Set<String> getLearnableKnownOptions(final List<String> algorithmIds) {
        if (algorithmIds == null) {
            throw new IllegalArgumentException();
        }

        LayoutDataService layoutServices = LayoutDataService.getInstance();

        Set<String> knownOptionIds = new HashSet<String>();
        for (final String id : algorithmIds) {
            LayoutAlgorithmData algorithm = layoutServices.getAlgorithmData(id);
            for (final String optionId : this.learnableOptions) {
                LayoutOptionData<?> optionData = layoutServices.getOptionData(optionId);
                if (algorithm.knowsOption(optionData)) {
                    knownOptionIds.add(optionId);
                }
            }
        }
        return knownOptionIds;
    }
}
