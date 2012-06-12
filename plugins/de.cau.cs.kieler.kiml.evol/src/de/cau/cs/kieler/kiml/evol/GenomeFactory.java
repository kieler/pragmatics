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
import java.util.List;
import java.util.Random;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.ui.IWorkbenchPart;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.ILayoutData;
import de.cau.cs.kieler.kiml.LayoutAlgorithmData;
import de.cau.cs.kieler.kiml.LayoutContext;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.LayoutDataService;
import de.cau.cs.kieler.kiml.LayoutTypeData;
import de.cau.cs.kieler.kiml.config.CompoundLayoutConfig;
import de.cau.cs.kieler.kiml.config.DefaultLayoutConfig;
import de.cau.cs.kieler.kiml.config.ILayoutConfig;
import de.cau.cs.kieler.kiml.evol.genetic.Gene;
import de.cau.cs.kieler.kiml.evol.genetic.Genome;
import de.cau.cs.kieler.kiml.evol.genetic.TypeInfo;
import de.cau.cs.kieler.kiml.evol.genetic.TypeInfo.GeneType;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.service.LayoutInfoService;
import de.cau.cs.kieler.kiml.ui.diagram.LayoutMapping;
import de.cau.cs.kieler.kiml.ui.service.EclipseLayoutConfig;
import de.cau.cs.kieler.kiml.ui.service.EclipseLayoutInfoService;

/**
 * A factory for genes and genomes.
 *
 * @author msp
 */
public final class GenomeFactory {

    /**
     * Hidden constructor to prevent instantiation.
     */
    private GenomeFactory() {
    }

    /** identifier for the layout type gene. */
    public static final String LAYOUT_TYPE_ID = "de.cau.cs.kieler.kiml.evol.layoutType";

    /** probability for mutation of layout type genes. */
    private static final double P_LAYOUT_TYPE_MUTATION = 0.08;
    /** probability for mutation of layout algorithm genes. */
    private static final double P_LAYOUT_ALGO_MUTATION = 0.15;
    /** probability for mutation of boolean type genes. */
    private static final double P_BOOLEAN_MUTATION = 0.20;
    /** probability for mutation of enumeration type genes. */
    private static final double P_ENUM_MUTATION = 0.25;
    /** probability for mutation of integer type genes. */
    private static final double P_INT_MUTATION = 0.35;
    /** probability for mutation of floating point type genes. */
    private static final double P_FLOAT_MUTATION = 0.40;
    
    /**
     * Create a genome with default values from the given layout mapping.
     * 
     * @param layoutMapping a layout mapping
     * @param config a layout configurator for obtaining default values
     * @param context a layout context for obtaining default value
     * @return a genome filled with genes
     */
    public static Genome createInitialGenome(final LayoutMapping<?> layoutMapping,
            final ILayoutConfig config, final LayoutContext context) {
        LayoutDataService dataService = LayoutDataService.getInstance();
        LayoutOptionData<?> algoOptionData = dataService.getOptionData(
                LayoutOptions.ALGORITHM.getId());
        String algorithmId = (String) config.getValue(algoOptionData, context);
        LayoutOptionData<?> diagTypeData = dataService.getOptionData(
                LayoutOptions.DIAGRAM_TYPE.getId());
        String diagramType = (String) config.getValue(diagTypeData, context);
        LayoutAlgorithmData algorithmData = DefaultLayoutConfig.getLayouterData(
                algorithmId, diagramType);
        
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
                TypeInfo<?> typeInfo = createTypeInfo(optionData);
                if (typeInfo != null) {
                    Gene<?> gene;
                    if (algorithmData.knowsOption(optionData)) {
                        gene = createDefaultGene(algorithmData, optionData, typeInfo, config, context);
                    } else {
                        gene = Gene.create(null, typeInfo);
                        gene.setActive(false);
                    }
                    genome.getGenes().add(gene);
                }
            }
        }
        return genome;
    }
    
    /**
     * Create a gene for the layout type.
     * 
     * @param typeData the layout type data
     * @return a gene initialized to the given type
     */
    public static Gene<Integer> createLayoutTypeGene(final LayoutTypeData typeData) {
        List<LayoutTypeData> typeList = new ArrayList<LayoutTypeData>(LayoutDataService.getInstance()
                .getTypeData());
        int index = typeList.indexOf(typeData);
        TypeInfo<Integer> typeInfo = new TypeInfo<Integer>(LAYOUT_TYPE_ID, GeneType.LAYOUT_TYPE, 0,
                typeList.size() - 1, typeList, P_LAYOUT_TYPE_MUTATION, 1);
        return Gene.create(index, typeInfo);
    }
    
    /**
     * Create a gene for the layout algorithm. The gene will offer only algorithms of the given type.
     * 
     * @param layoutType the type of layout algorithm
     * @param algoData the layout algorithm data
     * @return a gene initialized to the given algorithm
     */
    public static Gene<Integer> createAlgorithmGene(final LayoutTypeData layoutType,
            final LayoutAlgorithmData algoData) {
        List<LayoutAlgorithmData> algoList = layoutType.getLayouters();
        int index = algoList.indexOf(algoData);
        TypeInfo<Integer> typeInfo = new TypeInfo<Integer>(LayoutOptions.ALGORITHM.getId(),
                GeneType.LAYOUT_ALGO, 0, algoList.size() - 1, algoList, P_LAYOUT_ALGO_MUTATION, 1);
        return Gene.create(index, typeInfo);
    }
    
    /**
     * Create a gene for the layout algorithm and randomly select one.
     * 
     * @param layoutType the type of layout algorithm
     * @param random a random number generator
     * @return a gene initialized to a random algorithm of the given type
     */
    public static Gene<Integer> createAlgorithmGene(final LayoutTypeData layoutType,
            final Random random) {
        List<LayoutAlgorithmData> algoList = layoutType.getLayouters(); 
        TypeInfo<Integer> typeInfo = new TypeInfo<Integer>(LayoutOptions.ALGORITHM.getId(),
                GeneType.LAYOUT_ALGO, 0, algoList.size() - 1, algoList, P_LAYOUT_ALGO_MUTATION, 1);
        int randomAlgoIndex = random.nextInt(algoList.size());
        return Gene.create(randomAlgoIndex, typeInfo);
    }
    
    /**
     * Create gene type information for the given layout option.
     * 
     * @param optionData a layout option data
     * @return gene type information for the given option, or  {@code null} if the type is not supported
     */
    @SuppressWarnings("unchecked")
    public static TypeInfo<?> createTypeInfo(final LayoutOptionData<?> optionData) {
        switch (optionData.getType()) {
        case BOOLEAN:
            return new TypeInfo<Integer>(optionData.getId(), GeneType.BOOLEAN, 0,
                    1, optionData, P_BOOLEAN_MUTATION, 1);
        case ENUM:
            return new TypeInfo<Integer>(optionData.getId(), GeneType.ENUM, 0,
                    optionData.getOptionClass().getEnumConstants().length - 1,
                    optionData, P_ENUM_MUTATION, 1);
        case INT:
            return new TypeInfo<Integer>(optionData.getId(), GeneType.INTEGER,
                    (Comparable<Integer>) optionData.getLowerBound(),
                    (Comparable<Integer>) optionData.getUpperBound(),
                    optionData, P_INT_MUTATION, optionData.getVariance());
        case FLOAT:
            return new TypeInfo<Float>(optionData.getId(), GeneType.FLOAT,
                    (Comparable<Float>) optionData.getLowerBound(),
                    (Comparable<Float>) optionData.getUpperBound(),
                    optionData, P_FLOAT_MUTATION, optionData.getVariance());
        default:
            return null;
        }
    }
    
    /**
     * Create a layout configurator and context for obtaining default values.
     * 
     * @param layoutMapping a layout mapping created by a diagram layout manager
     * @return a pair consisting of a layout configurator and a context that can be used
     *          by that configurator
     */
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
        context.setProperty(DefaultLayoutConfig.OPT_MAKE_OPTIONS, true);
        
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
    
    /**
     * Create a gene with default value using an existing type info. The gene is initially active.
     * 
     * @param algoData the active layout algorithm
     * @param optionData the layout option for which to create the gene
     * @param typeInfo type information for the layout option
     * @param config a layout configurator for obtaining default values
     * @param context a layout context for obtaining default value
     * @param <T> value type of the gene to create
     * @return a new gene with the obtained default value
     */
    @SuppressWarnings("unchecked")
    public static <T extends Comparable<? super T>> Gene<T> createDefaultGene(
            final LayoutAlgorithmData algoData, final LayoutOptionData<?> optionData,
            final TypeInfo<T> typeInfo, final ILayoutConfig config, final LayoutContext context) {
        context.setProperty(DefaultLayoutConfig.CONTENT_ALGO, algoData);
        T value = translateToGene(config.getValue(optionData, context), typeInfo);
        
        Comparable<T> lowerBound = typeInfo.getLowerBound();
        if (lowerBound.compareTo(value) > 0) {
            value = (T) lowerBound;
        } else {
            Comparable<T> upperBound = typeInfo.getUpperBound();
            if (upperBound.compareTo(value) < 0) {
                value = (T) upperBound;
            }
        }
        return Gene.create(value, typeInfo);
    }
    
    /**
     * Translate a layout option value to a gene value.
     * 
     * @param value a layout option value
     * @param typeInfo the type information of the gene
     * @param <T> type of result value
     * @return a gene value
     */
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
    
    /**
     * Configure a node using a given genome.
     * 
     * @param parentNode the node to configure
     * @param genome the genome holding layout option values
     * @param config a layout configurator for obtaining default values
     * @param context a layout context for obtaining default value
     */
    public static void configureGraph(final KNode parentNode, final Genome genome,
            final ILayoutConfig config, final LayoutContext context) {
        LayoutDataService dataService = LayoutDataService.getInstance();
        KShapeLayout parentLayout = parentNode.getData(KShapeLayout.class);
        // first transfer values from the layout configurator
        config.transferValues(parentLayout, context);
        
        // then transfer values from the given genome, overriding values of the configurator
        for (Gene<?> gene : genome.getGenes()) {
            if (gene.getValue() != null) {
                LayoutOptionData<?> optionData = dataService.getOptionData(gene.getTypeInfo().getId());
                if (optionData != null) {
                    parentLayout.setProperty(optionData, translateFromGene(gene));
                }
            }
        }
        
        // do the same for non-empty child nodes
        for (KNode child : parentNode.getChildren()) {
            if (!child.getChildren().isEmpty()) {
                configureGraph(child, genome, config, context);
            }
        }
    }

}
