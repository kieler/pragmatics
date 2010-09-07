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
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.LayoutOptionData.Type;
import de.cau.cs.kieler.kiml.LayoutProviderData;
import de.cau.cs.kieler.kiml.LayoutServices;
import de.cau.cs.kieler.kiml.evol.EvolUtil.GeneFactory;
import de.cau.cs.kieler.kiml.evol.genetic.Genome;
import de.cau.cs.kieler.kiml.evol.genetic.IGene;
import de.cau.cs.kieler.kiml.evol.genetic.MutationInfo;
import de.cau.cs.kieler.kiml.evol.genetic.RadioGene;
import de.cau.cs.kieler.kiml.evol.genetic.RadioTypeInfo;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.ui.views.LayoutPropertySource;

/**
 * A factory for genomes.
 *
 * @author bdu
 *
 */
final class GenomeFactory {

    // Get the set of all learnable elements that are registered.
    private final Set<String> learnableOptions;

    private final GeneFactory geneFactory = new GeneFactory();

    /**
     * Creates a new {@link GenomeFactory} instance.
     *
     */
    public GenomeFactory(final Set<String> theLearnableOptions) {
        if (theLearnableOptions != null) {
            this.learnableOptions = theLearnableOptions;
        } else {
            // all registered learnables
            this.learnableOptions = EvolutionServices.getInstance().getEvolutionDataIds();
        }

        // nothing to do.

    }

    /**
     * Create a {@link Genome} from the given layout property sources.
     *
     * @param propertySources
     *            a list of {@link LayoutPropertySource} instances
     * @param layoutHintIds
     *            a set of layout hint IDs; must not be {@code null}
     * @return a genome, or {@code null}.
     */
    public Genome createGenome(
            final List<LayoutPropertySource> propertySources,
            final Set<Object> layoutHintIds) {

        if ((propertySources == null) || (layoutHintIds == null) || layoutHintIds.isEmpty()) {
            return null;
        }

        /*
         * TODO: Discuss: If more than one LayoutPropertySource is contained in the given list,
         * they may stem from different editors containing different layout providers.
         * Should the genes from different layout providers
         *   - be pooled without hierarchy?
         *   - be mapped to their layout provider id?
         *   - or be grouped together as "chromosomes" in the individual?
         * What about duplicate properties?
         * */

        final Genome result = new Genome();

        // Get property descriptors for the layout property sources.
        final Map<String, IPropertyDescriptor> allPropertyDescriptors =
                getPropertyDescriptors(propertySources);

        // Get the registered layout option data.
        // final Collection<LayoutOptionData<?>> registeredLayoutOptions =
        // LayoutServices.getInstance().getLayoutOptionData();

        // Collect the learnable properties from the property descriptors.
        final Set<IPropertyDescriptor> presentLearnables =
                collectLearnableProperties(allPropertyDescriptors.values(),
                        this.learnableOptions);

        // Determine uniformly distributed mutation probability.
        final double uniformProb = uniformProbability(presentLearnables.size());

        EvolPlugin.logStatus("Creating genome of " + presentLearnables.size()
                + " layout property genes ...");

        // Collect the property values from the layout property sources.
        final Map<String, Object> propertyId2ValueMap =
                collectPropertyValues(propertySources);

        // Create genes for the property values.
        for (final Entry<String, Object> entry : propertyId2ValueMap.entrySet()) {

            final String id = entry.getKey();
            final Object value = entry.getValue();

            // Check the property descriptor id.
            Assert.isTrue(!LayoutOptions.LAYOUT_HINT_ID.equals(id),
                    "There should be no layout hint in the collected options.");

            // There should not be a gene for this option yet.
            Assert.isTrue(result.find(id) == null, "Duplicate property: " + id);

            // learnable option?
            if (this.learnableOptions.contains(id)) {
                IGene<?> gene = null;
                try {
                    gene = this.geneFactory.newGene(id, value, uniformProb);
                    Assert.isNotNull(gene, "Failed to create gene for " + id);
                    result.add(gene);
                } catch (final IllegalArgumentException exception) {
                    EvolPlugin.showError("Failed to create gene for " + id, exception);
                }

            } else {
                EvolPlugin.logStatus("Option not registered as evolutionData: " + id);
            }
        }

        Assert.isTrue(presentLearnables.size() == result.size(),
                "The number of genes does not have the predicted count of "
                        + presentLearnables.size());

        Assert.isTrue(!layoutHintIds.isEmpty(), "No layout hint specified.");

        // Add a gene for the layout hint.

        // Create a gene that can mutate over a list of layout hint IDs.

        // TODO: If we have more than one layout hint, we use the first,
        // but what about the others?
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
        final Genome extraGenes = createGenes(knownOptionIds, presentIds, uniformProb, null);

        result.addAll(extraGenes);

        EvolPlugin.logStatus("Created genome: " + result.size() + " genes.");

        return result;
    }

    /**
     * @param providerIds
     * @param defaultProviderId
     * @return a gene that mutates over the given providers
     */
    private RadioGene createLayoutHintGene(
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
     * Collects the property values from the given layout property sources. If a
     * property is present in more than one of the sources, the value defined in
     * its first occurrence prevails. Property values that contain layout hints
     * are ignored.
     *
     * @param propertySources
     *            a list of layout property sources
     *
     * @return a map storing the property values
     */
    private Map<String, Object> collectPropertyValues(
            final List<LayoutPropertySource> propertySources) {

        final HashMap<String, Object> propertyId2ValueMap =
                new HashMap<String, Object>(propertySources.size() * 10);

        // Iterate the layout property sources.
        for (final LayoutPropertySource source : propertySources) {
            // Iterate the property descriptors of the layout property
            // source.
            final IPropertyDescriptor[] descriptors = source.getPropertyDescriptors();
            for (final IPropertyDescriptor desc : descriptors) {

                final String id = (String) desc.getId();
                final Object value = source.getPropertyValue(id);

                if (LayoutOptions.LAYOUT_HINT_ID.equals(id)) {
                    // Property is a layout hint --> skip
                    Assert.isNotNull(value, "layout hint value is null");
                    continue;

                } else if (!propertyId2ValueMap.containsKey(id)) {
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
     * Collect the learnable properties from the given list of
     * IPropertyDescriptor objects.
     *
     * @param descriptors
     *            a collection of property descriptors
     * @param acceptedProperties
     *            a set of accepted properties. If this is {@code null},
     *            then all registered properties are used.
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
            if (!LayoutOptions.LAYOUT_HINT_ID.equals(id)) {
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
     * @param knownOptionIds
     * @param presentIds
     * @param prob
     * @param theGeneFactory
     * @return
     */
    private Genome createGenes(
            final Set<String> knownOptionIds, final List<String> presentIds,
            final double prob, final GeneFactory theGeneFactory) {
        final Genome extraGenes = new Genome();

        final GeneFactory gf;
        if (theGeneFactory == null) {
            gf = this.geneFactory;
        } else {
            gf = theGeneFactory;
        }

        for (final String optionId : knownOptionIds) {
            if (!presentIds.contains(optionId)) {
                final LayoutOptionData<?> optionData =
                        LayoutServices.getInstance().getLayoutOptionData(optionId);
                final Object value = optionData.getDefault();
                EvolPlugin.logStatus("Creating gene for " + optionId);
                final IGene<?> gene = gf.newGene(optionId, value.toString(), prob);
                Assert.isNotNull(gene, "Failed to create gene for " + optionId);
                extraGenes.add(gene);
            }
        }
        return extraGenes;
    }

    /**
     * @param providerIds
     * @return
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
    private RadioGene createLayoutHintGene(
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
                new RadioGene(LayoutOptions.LAYOUT_HINT_ID, Integer.valueOf(defaultEntry),
                        typeInfo,
                        mutationInfo);
        return hintGene;
    }

    /**
     * Collects the property descriptors from the given layout property
     * sources.
     *
     * @param propertySources
     *            a list of LayoutPropertySource instances
     * @return a map containing property descriptor IDs and the respective
     *         property descriptors.
     */
    private static Map<String, IPropertyDescriptor> getPropertyDescriptors(
            final List<LayoutPropertySource> propertySources) {
        final Map<String, IPropertyDescriptor> allPropertyDescriptors =
                new HashMap<String, IPropertyDescriptor>();

        for (final LayoutPropertySource source : propertySources) {
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
     * @return the multiplicative inverse of the given number, or 0.0 if it
     *         is not > 0.
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
}
