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

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.LayoutOptionData.Type;
import de.cau.cs.kieler.kiml.LayoutProviderData;
import de.cau.cs.kieler.kiml.LayoutServices;
import de.cau.cs.kieler.kiml.evol.genetic.Distribution;
import de.cau.cs.kieler.kiml.evol.genetic.EnumGene;
import de.cau.cs.kieler.kiml.evol.genetic.FloatTypeInfo;
import de.cau.cs.kieler.kiml.evol.genetic.Genome;
import de.cau.cs.kieler.kiml.evol.genetic.IGene;
import de.cau.cs.kieler.kiml.evol.genetic.IGeneFactory;
import de.cau.cs.kieler.kiml.evol.genetic.IValueFormatter;
import de.cau.cs.kieler.kiml.evol.genetic.MutationInfo;
import de.cau.cs.kieler.kiml.evol.genetic.RadioGene;
import de.cau.cs.kieler.kiml.evol.genetic.RadioTypeInfo;
import de.cau.cs.kieler.kiml.evol.genetic.TypeInfo;
import de.cau.cs.kieler.kiml.evol.genetic.UniversalGene;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.ui.layout.EclipseLayoutServices;
import de.cau.cs.kieler.kiml.ui.layout.ILayoutInspector;
import de.cau.cs.kieler.kiml.ui.views.LayoutPropertySource;

/**
 * A factory for genomes.
 *
 * @author bdu
 *
 */
final class GenomeFactory {

    /**
     * A factory for genes.
     *
     * @author bdu
     *
     */
    private static class LayoutOptionGeneFactory implements IGeneFactory {
        /**
         * The absolute value is scaled by this factor to get the default
         * variance.
         */
        private static final double VARIANCE_SCALING_FACTOR = .20;

        private static final String ATTRIBUTE_LOWER_BOUND = "lowerBound";
        private static final String ATTRIBUTE_UPPER_BOUND = "upperBound";
        private static final String ATTRIBUTE_DISTRIBUTION = "distribution";
        private static final String ATTRIBUTE_VARIANCE = "variance";

        /**
         * Creates a new {@link LayoutOptionGeneFactory}.
         */
        LayoutOptionGeneFactory() {
            // nothing to do here
        }

        /**
         * Creates a gene with the specified value.
         *
         * @param theId
         *            a valid layout option ID that is registered as evolution
         *            data
         * @param theValue
         *            a value
         * @param theMutationProbability
         *            the mutation probability
         * @return a gene
         */
        public IGene<?> newGene(
                final Object theId, final Object theValue, final double theMutationProbability) {

            IGene<?> result;

            // Get the evolution data for the layout option.
            final IConfigurationElement evolutionData =
                    EvolutionServices.getInstance().getEvolutionData((String) theId);
            if (evolutionData == null) {
                throw new IllegalArgumentException("No evolution data registered for " + theId);
            }

            final String lowerBoundAttr = evolutionData.getAttribute(ATTRIBUTE_LOWER_BOUND);
            final String upperBoundAttr = evolutionData.getAttribute(ATTRIBUTE_UPPER_BOUND);
            final String distrNameAttr = evolutionData.getAttribute(ATTRIBUTE_DISTRIBUTION);
            final String varianceAttr = evolutionData.getAttribute(ATTRIBUTE_VARIANCE);

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
            final LayoutOptionData<?> layoutOptionData =
                    LayoutServices.getInstance().getLayoutOptionData((String) theId);
            if (layoutOptionData == null) {
                throw new IllegalArgumentException("No layout option data for " + theId);
            }

            final Type layoutOptionDataType = layoutOptionData.getType();

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
         * Returns always {@code null}.
         */
        public IGene<?> newGene(
                final Object theId, final Object theValue, final TypeInfo<?> theTypeInfo,
                final MutationInfo theMutationInfo) {
            return null;
        }

        /**
         * Creates a gene for a boolean value.
         *
         * @param theId
         * @param theValue
         *            an object containing "1" or "0"
         * @param theMutationProbability
         * @param distr
         * @return
         */
        private IGene<?> newBooleanGene(
                final Object theId, final Object theValue, final double theMutationProbability,
                final Distribution distr) {
            IGene<?> result;
            final boolean booleanValue = (Integer.parseInt(theValue.toString()) == 1);
            final Float floatValue = Float.valueOf((booleanValue ? 1.0f : 0.0f));
            final MutationInfo mutationInfo = new MutationInfo(theMutationProbability, distr);
            result =
                    new UniversalGene(theId, floatValue, UniversalGene.BOOLEAN_TYPE_INFO,
                            mutationInfo);
            EvolPlugin.logStatus(theId + ": " + result);
            return result;
        }

        /**
         * Creates a gene for an enum value.
         *
         * @param theId
         * @param theRawValue
         * @param theMutationProbability
         * @param layoutOptionData
         * @return
         */
        private IGene<?> newEnumGene(
                final Object theId, final Object theRawValue,
                final double theMutationProbability, final LayoutOptionData<?> layoutOptionData) {
            IGene<?> result;
            final int choicesCount = layoutOptionData.getChoices().length;
            final Class<? extends Enum<?>> enumClass =
                    (Class<? extends Enum<?>>) layoutOptionData.getOptionClass();
            Assert.isNotNull(enumClass);
            final Enum<?>[] constants = enumClass.getEnumConstants();
            Assert.isTrue(constants.length == choicesCount);
            Integer value = null;
            if (theRawValue instanceof Integer) {
                value = Integer.valueOf(theRawValue.toString());
            } else if (theRawValue instanceof String) {
                final List<Enum<?>> constantsList = Arrays.asList(constants);
                for (final Enum<?> enum1 : constantsList) {
                    if (enum1.toString().equals(theRawValue)) {
                        value = Integer.valueOf(enum1.ordinal());
                        break;
                    }
                }
                if (value == null) {
                    value = 0;
                }
            } else if (theRawValue instanceof Enum) {
                value = Integer.valueOf(((Enum<Type>) theRawValue).ordinal());
            } else {
                value = Integer.valueOf(0);
            }
            result = new EnumGene(theId, value.intValue(), enumClass, theMutationProbability);
            EvolPlugin.logStatus("Enum " + enumClass.getSimpleName() + "(" + choicesCount + "): "
                    + enumClass.getEnumConstants()[value.intValue()] + " (" + value + ")");
            return result;
        }

        /**
         * Creates a gene for a float value.
         *
         * @param theId
         * @param theRawValue
         *            an object containing a string that represents a float
         * @param theMutationProbability
         * @param lowerBoundAttr
         * @param upperBoundAttr
         * @param varianceAttr
         * @param distr
         * @return
         */
        private IGene<?> newFloatGene(
                final Object theId, final Object theRawValue,
                final double theMutationProbability, final String lowerBoundAttr,
                final String upperBoundAttr, final String varianceAttr, final Distribution distr) {
            IGene<?> result;
            Float value = Float.valueOf((String) theRawValue);
            final Float lowerBound =
                    Float.valueOf((lowerBoundAttr == null ? Float.NEGATIVE_INFINITY : Float
                            .parseFloat(lowerBoundAttr)));
            final Float upperBound =
                    Float.valueOf(((upperBoundAttr == null) ? Float.POSITIVE_INFINITY : Float
                            .parseFloat(upperBoundAttr)));

            final double variance;
            if (varianceAttr != null) {
                // get variance from evolution data
                variance = Double.parseDouble(varianceAttr);

            } else {
                // threshold to prevent very small variances
                final float verySmall = 1e-3f;

                // estimate desired variance from the absolute value
                if ((Math.abs(value.floatValue()) < verySmall)) {
                    variance = MutationInfo.DEFAULT_VARIANCE;
                } else {
                    variance = Math.abs(value.floatValue()) * VARIANCE_SCALING_FACTOR;
                }
            }

            final IValueFormatter formatter;
            if (lowerBound.floatValue() >= 0.0f) {
                // we use a strictly positive float gene
                formatter = UniversalGene.STRICTLY_POSITIVE_FLOAT_FORMATTER;

            } else {
                // we use a general float gene
                formatter = UniversalGene.FLOAT_FORMATTER;
            }

            value = sanitize(value, lowerBound, upperBound, (String) theId);

            final TypeInfo<Float> typeInfo =
                    new FloatTypeInfo(value, lowerBound, upperBound, formatter, Float.class);

            final MutationInfo mutationInfo =
                    new MutationInfo(theMutationProbability, variance, distr);
            result = new UniversalGene(theId, value, typeInfo, mutationInfo);
            EvolPlugin.logStatus(theId + ": " + result);
            return result;
        }

        /**
         * Creates a gene for an integer value.
         *
         * @param theId
         * @param theRawValue
         * @param theMutationProbability
         * @param lowerBoundAttr
         * @param upperBoundAttr
         * @param varianceAttr
         * @param distr
         * @return
         */
        private IGene<?> newIntegerGene(
                final Object theId, final Object theRawValue,
                final double theMutationProbability, final String lowerBoundAttr,
                final String upperBoundAttr, final String varianceAttr, final Distribution distr) {
            IGene<?> result;
            Integer value = Integer.valueOf((String) theRawValue);

            final double variance;
            if (varianceAttr != null) {
                // get variance from evolution data
                variance = Double.parseDouble(varianceAttr);
            } else {
                // estimate desired variance from the absolute value
                variance = Math.abs(value.intValue()) * VARIANCE_SCALING_FACTOR;
            }

            final Integer lowerBound =
                    Integer.valueOf(((lowerBoundAttr == null) ? Integer.MIN_VALUE : (Integer
                            .parseInt(lowerBoundAttr))));

            final Integer upperBound =
                    Integer.valueOf(((upperBoundAttr == null) ? Integer.MAX_VALUE : (Integer
                            .parseInt(upperBoundAttr))));

            final IValueFormatter formatter = UniversalGene.INTEGER_FORMATTER;

            // enforce that the value is within the legal bounds
            value =
                    Integer.valueOf((sanitize(value.floatValue(), lowerBound.floatValue(),
                            upperBound.floatValue(), (String) theId).intValue()));

            final TypeInfo<Float> typeInfo =
                    new FloatTypeInfo(Float.valueOf(value.floatValue()), Float.valueOf(lowerBound
                            .floatValue()), Float.valueOf(upperBound.floatValue()), formatter,
                            Integer.class);

            final MutationInfo mutationInfo =
                    new MutationInfo(theMutationProbability, variance, distr);
            result =
                    new UniversalGene(theId, Float.valueOf(value.floatValue()), typeInfo,
                            mutationInfo);
            EvolPlugin.logStatus(theId + ": " + result);
            return result;
        }

        /**
         * Enforces that the value is within the specified legal bounds.
         *
         * @param theValue
         * @param theLowerBound
         * @param theUpperBound
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
        Assert.isLegal(metricIds != null);
        if (metricIds == null) {
            return null;
        }

        final IGeneFactory factory = new IGeneFactory() {
            public IGene<?> newGene(
                    final Object theId, final Object theValue, final double theMutationProb) {

                final TypeInfo<Float> typeInfo =
                        new FloatTypeInfo(Float.valueOf(1.0f), Float.valueOf(0.0f),
                                Float.valueOf(10.0f),
                                UniversalGene.STRICTLY_POSITIVE_FLOAT_FORMATTER, Float.class);
                final MutationInfo mutationInfo =
                        new MutationInfo(theMutationProb, .2, Distribution.GAUSSIAN);

                return this.newGene(theId, theValue, typeInfo, mutationInfo);
            }

            public IGene<?> newGene(
                    final Object theId, final Object theValue, final TypeInfo<?> theTypeInfo,
                    final MutationInfo theMutationInfo) {
                Assert.isLegal(theTypeInfo instanceof FloatTypeInfo);

                if (theTypeInfo instanceof FloatTypeInfo) {
                    return new UniversalGene(theId, (Float) theValue,
                            (FloatTypeInfo) theTypeInfo, theMutationInfo);
                }

                return null;
            }
        };

        final Genome result = new Genome();
        final Float value = Float.valueOf(1.0f);
        for (final String id : metricIds) {
            final IGene<?> gene = factory.newGene(id, value, 0.02);
            Assert.isNotNull(gene, "Failed to create gene for " + id);
            result.add(gene);
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

        final Set<String> accepted;
        if (acceptedProperties == null) {
            // Get the set of all registered learnable properties.
            accepted = EvolutionServices.getInstance().getEvolutionDataIds();
        } else {
            accepted = acceptedProperties;
        }

        final LayoutServices layoutServices = LayoutServices.getInstance();

        final Set<IPropertyDescriptor> result = new HashSet<IPropertyDescriptor>();

        // Iterate the given property descriptors.
        for (final IPropertyDescriptor p : descriptors) {
            final String id = (String) p.getId();
            // check property descriptor id
            if (!LayoutOptions.LAYOUTER_HINT_ID.equals(id)) {
                final LayoutOptionData<?> data = layoutServices.getLayoutOptionData(id);
                Assert.isNotNull(data, "Layout option not registered: " + id);

                final Type type = data.getType();
                switch (type) {
                case BOOLEAN:
                case ENUM:
                case INT:
                case FLOAT:
                    if (accepted.contains(id)) {
                        // learnable --> collect it
                        result.add(p);
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
     * Collects the property values from the given layout property sources. If a
     * property is present in more than one of the sources, the value defined in
     * its first occurrence prevails. Property values that contain layout hints
     * are ignored.
     *
     * @param inspectors
     *            a list of layout inspectors
     *
     * @return a map storing the property values
     */
    private static Map<String, Object> collectPropertyValues(
            final List<ILayoutInspector> inspectors) {

        final int expectedNumberOfPropsPerInspector = 10;
        final HashMap<String, Object> propertyId2ValueMap =
                new HashMap<String, Object>(inspectors.size() * expectedNumberOfPropsPerInspector);

        final EclipseLayoutServices layoutServices = EclipseLayoutServices.getInstance();

        // Iterate the layout inspectors.
        for (final ILayoutInspector inspector : inspectors) {
            // Iterate the available layout options.
            inspector.initOptions();

            final List<LayoutOptionData<?>> optionDescriptors = inspector.getOptionData();

            for (final LayoutOptionData<?> data : optionDescriptors) {

                final String id = data.getId();

                if (LayoutOptions.LAYOUTER_HINT_ID.equals(id)) {
                    // Property is a layout hint --> skip
                    final Object value = inspector.getContainerLayouterData().getId();
                    Assert.isNotNull(value, "layout hint value is null");
                    continue;

                } else if (!propertyId2ValueMap.containsKey(id)) {
                    Object value = inspector.getOption(data);
                    if (value == null) {

                        if (data.hasTarget(LayoutOptionData.Target.PARENTS)) {
                            value =
                                    layoutServices.getDefault(data,
                                            inspector.getFocusLayouterData(),
                                            inspector.getFocusPart(), inspector.getFocusPart(),
                                            inspector.hasChildren());
                        } else {
                            value =
                                    layoutServices
                                            .getDefault(data,
                                                    inspector.getContainerLayouterData(),
                                                    inspector.getFocusPart(),
                                                    inspector.getContainerPart(),
                                                    inspector.hasChildren());
                        }

                    }
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
     * @param providerIds
     *            list of layout provider IDs; must not be {@code null}
     * @param defaultEntry
     *            index of the default provider ID; must be a valid index of
     *            {@code providerIds}
     * @return a gene that mutates over given the providers
     */
    private static RadioGene createLayoutHintGene(
            final List<String> providerIds, final int defaultEntry) {

        Assert.isLegal(providerIds != null);
        if (providerIds == null) {
            return null;
        }
        Assert.isLegal((defaultEntry >= 0) && (defaultEntry < providerIds.size()),
                "Index out of range.");

        final RadioTypeInfo typeInfo =
                new RadioTypeInfo(Integer.valueOf(defaultEntry), providerIds);
        final double prob = 0.05;
        final MutationInfo mutationInfo = new MutationInfo(prob);

        final RadioGene hintGene =
                new RadioGene(LayoutOptions.LAYOUTER_HINT_ID, Integer.valueOf(defaultEntry),
                        typeInfo, mutationInfo);
        return hintGene;
    }

    /**
     * Creates a layout hint gene.
     *
     * @param providerIds
     * @param defaultProviderId
     * @return a gene that mutates over the given providers
     */
    private static RadioGene createLayoutHintGene(
            final List<String> providerIds, final String defaultProviderId) {
        Assert.isLegal((providerIds != null) && !providerIds.isEmpty());
        Assert.isLegal(defaultProviderId != null);

        if (providerIds == null) {
            return null;
        }

        final int indexOfProviderId = providerIds.indexOf(defaultProviderId);
        Assert.isTrue(indexOfProviderId >= 0);
        return createLayoutHintGene(providerIds, indexOfProviderId);
    }

    /**
     * Collects the property descriptors from the given layout inspectors.
     *
     * @param theInspectors
     *            a list of {@link ILayoutInspector} instances
     * @return a map containing property descriptor IDs and the respective
     *         property descriptors.
     */
    private static Map<String, IPropertyDescriptor> getPropertyDescriptors(
            final List<ILayoutInspector> theInspectors) {
        final Map<String, IPropertyDescriptor> allPropertyDescriptors =
                new HashMap<String, IPropertyDescriptor>();

        for (final ILayoutInspector inspector : theInspectors) {
            final LayoutPropertySource source = new LayoutPropertySource(inspector);
            final IPropertyDescriptor[] propertyDescriptorsArray =
                    source.getPropertyDescriptors();

            final List<IPropertyDescriptor> propertyDescriptorsList =
                    Arrays.asList(propertyDescriptorsArray);

            for (final IPropertyDescriptor pd : propertyDescriptorsList) {
                allPropertyDescriptors.put((String) pd.getId(), pd);
            }
        }
        return allPropertyDescriptors;
    }

    /**
     * @param choicesCount
     * @return the multiplicative inverse of the given number, or 0.0 if it is
     *         not > 0.
     */
    private static double uniformProbability(final int choicesCount) {
        final double uniformProb;
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
     */
    public GenomeFactory(final Set<String> theLearnableOptions) {
        if (theLearnableOptions != null) {
            this.learnableOptions = theLearnableOptions;
        } else {
            // Get the set of all registered learnable elements.
            this.learnableOptions = EvolutionServices.getInstance().getEvolutionDataIds();
        }
    }

    // The set of all learnable elements that are accepted.
    private final Set<String> learnableOptions;

    private final IGeneFactory geneFactory = new LayoutOptionGeneFactory();

    /**
     * Create a {@link Genome} from the given layout inspectors.
     *
     * @param inspectors
     *            a list of {@link ILayoutInspector} instances
     * @param layoutHintIds
     *            a set of layout hint IDs; must not be {@code null}
     * @return a genome, or {@code null}.
     * @throws KielerException
     */
    public Genome createGenome(
            final List<ILayoutInspector> inspectors, final Set<Object> layoutHintIds)
            throws KielerException {

        if ((inspectors == null) || (layoutHintIds == null) || layoutHintIds.isEmpty()) {
            return null;
        }

        /*
         * TODO: Discuss: If more than one ILayoutInspector is contained in the given list,
         * they may stem from different editors containing different layout providers.
         * Should the genes from different layout providers
         *   - be pooled without hierarchy?
         *   - be mapped to their layout provider id?
         *   - or be grouped together as "chromosomes" in the individual?
         * What about duplicate properties?
         * */

        final Genome result = new Genome();

        // Get property descriptors for the layout inspectors.
        final Map<String, IPropertyDescriptor> allPropertyDescriptors =
                getPropertyDescriptors(inspectors);

        // Collect the learnable properties from the property descriptors.
        final Set<IPropertyDescriptor> presentLearnables =
                collectLearnableProperties(allPropertyDescriptors.values(), this.learnableOptions);

        // Determine uniformly distributed mutation probability.
        final double uniformProb = uniformProbability(presentLearnables.size());

        EvolPlugin.logStatus("Creating genome of " + presentLearnables.size()
                + " layout property genes ...");

        // Collect the property values from the layout inspectors.
        final Map<String, Object> propertyId2ValueMap = collectPropertyValues(inspectors);

        // Create genes for the property values.
        for (final Entry<String, Object> entry : propertyId2ValueMap.entrySet()) {

            final String id = entry.getKey();
            final Object value = entry.getValue();

            // Check the property descriptor id.
            Assert.isTrue(!LayoutOptions.LAYOUTER_HINT_ID.equals(id),
                    "There should be no layout hint in the collected options.");

            // There should not be a gene for this option yet.
            Assert.isTrue(result.find(id) == null, "Duplicate property: " + id);

            // learnable option?
            if (this.learnableOptions.contains(id)) {
                IGene<?> gene = null;
                try {
                    Assert.isNotNull(value, "Value is null: " + id);
                    gene = this.geneFactory.newGene(id, value, uniformProb);
                    Assert.isNotNull(gene, "Failed to create gene for " + id);
                    result.add(gene);
                } catch (final IllegalArgumentException exception) {
                    throw new KielerException("Failed to create gene for " + id, exception);
                }

            } else {
                EvolPlugin.logStatus("Option not registered as evolutionData: " + id);
            }
        }

        Assert.isTrue(
                presentLearnables.size() == result.size(),
                "The number of genes does not have the predicted count of "
                        + presentLearnables.size());

        Assert.isTrue(!layoutHintIds.isEmpty(), "No layout hint specified.");

        // Add a gene for the layout hint (a gene that can mutate over a list of
        // layout hint IDs).

        // TODO: If we have more than one layout hint, we use the first as
        // default, but what about the others?
        final String hintId = (String) layoutHintIds.iterator().next();

        final LayoutServices layoutServices = LayoutServices.getInstance();
        final LayoutProviderData providerData =
                layoutServices.getLayoutProviderData(hintId, null);

        if (providerData == null) {
            // no provider for the given layout hint
            return null;
        }

        // Get the type of the provider.
        final String typeId = providerData.getType();

        // Get the IDs of all suitable providers for this type.
        final List<String> providerIds = EvolUtil.getLayoutProviderIds(typeId);

        final String providerId = providerData.getId();

        // Create the layout hint gene.
        final RadioGene hintGene = createLayoutHintGene(providerIds, providerId);
        Assert.isNotNull(hintGene, "Failed to create layout hint gene for " + typeId);
        result.add(hintGene);

        // Collect all learnable layout options that are known by the
        // providers.
        final Set<String> knownOptionIds = getLearnableKnownOptions(providerIds);

        // Add extra genes for the suitable options that have not been
        // added yet.
        final List<String> presentIds = result.getIds();

        try {
            final Genome extraGenes = createGenes(knownOptionIds, presentIds, uniformProb, null);
            result.addAll(extraGenes);
        } catch (final Exception exception) {
            throw new KielerException("Genome could not be created.", exception);
        }

        EvolPlugin.logStatus("Created genome: " + result.size() + " genes.");

        return result;
    }

    /**
     * @param knownOptionIds
     * @param presentIds
     * @param prob
     * @param theGeneFactory
     * @return
     * @throws KielerException
     */
    private Genome createGenes(
            final Set<String> knownOptionIds, final List<String> presentIds, final double prob,
            final IGeneFactory theGeneFactory) throws KielerException {
        final Genome extraGenes = new Genome();

        final IGeneFactory gf;
        if (theGeneFactory == null) {
            gf = this.geneFactory;
        } else {
            gf = theGeneFactory;
        }

        for (final String optionId : knownOptionIds) {
            if (!presentIds.contains(optionId)) {
                EvolPlugin.logStatus("Creating gene for " + optionId);
                final LayoutOptionData<?> optionData =
                        LayoutServices.getInstance().getLayoutOptionData(optionId);

                if (optionData == null) {
                    throw new KielerException("Could not get layout option data: " + optionId);
                }

                final Object value = optionData.getDefault();

                final IGene<?> gene = gf.newGene(optionId, value.toString(), prob);
                Assert.isNotNull(gene, "Failed to create gene for " + optionId);
                extraGenes.add(gene);
            }
        }
        return extraGenes;
    }

    /**
     * Determines which of the registered layout options are known by the
     * specified providers.
     *
     * @param providerIds
     *            a list of layout provider IDs
     * @return a set containing the IDs of the layout options that are known by
     *         the specified providers and that are registered as evolutionData
     */
    private Set<String> getLearnableKnownOptions(final List<String> providerIds) {
        final Set<String> knownOptionIds = new HashSet<String>();
        for (final String id : providerIds) {
            final LayoutProviderData provider =
                    LayoutServices.getInstance().getLayoutProviderData(id);
            for (final String optionId : this.learnableOptions) {
                if (provider.knowsOption(optionId)) {
                    knownOptionIds.add(optionId);
                }
            }
        }
        return knownOptionIds;
    }
}
