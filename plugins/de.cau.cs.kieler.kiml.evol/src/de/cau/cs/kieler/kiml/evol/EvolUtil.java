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
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import de.cau.cs.kieler.core.alg.BasicProgressMonitor;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.LayoutOptionData.Type;
import de.cau.cs.kieler.kiml.LayoutProviderData;
import de.cau.cs.kieler.kiml.LayoutServices;
import de.cau.cs.kieler.kiml.evol.genetic.BooleanGene;
import de.cau.cs.kieler.kiml.evol.genetic.Distribution;
import de.cau.cs.kieler.kiml.evol.genetic.EnumGene;
import de.cau.cs.kieler.kiml.evol.genetic.FloatGene;
import de.cau.cs.kieler.kiml.evol.genetic.Genome;
import de.cau.cs.kieler.kiml.evol.genetic.IGene;
import de.cau.cs.kieler.kiml.evol.genetic.IGeneFactory;
import de.cau.cs.kieler.kiml.evol.genetic.IntegerGene;
import de.cau.cs.kieler.kiml.evol.genetic.MutationInfo;
import de.cau.cs.kieler.kiml.evol.genetic.Population;
import de.cau.cs.kieler.kiml.evol.genetic.TypeInfo;
import de.cau.cs.kieler.kiml.evol.genetic.UniversalGene;
import de.cau.cs.kieler.kiml.grana.AbstractInfoAnalysis;
import de.cau.cs.kieler.kiml.grana.AnalysisServices;
import de.cau.cs.kieler.kiml.grana.ui.DiagramAnalyser;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.ui.layout.DiagramLayoutManager;
import de.cau.cs.kieler.kiml.ui.layout.ILayoutInspector;
import de.cau.cs.kieler.kiml.ui.views.LayoutPropertySource;

/**
 * Utility methods for Evolutionary Meta Layout.
 *
 * @author bdu
 *
 */
public final class EvolUtil {
    /**
     * Create a population of the given size, taking initial values from the
     * given {@code LayoutPropertySource}.
     *
     * @param source
     *            where the initial data is taken from.
     * @param size
     *            determines the initial size of the population.
     * @return population
     */
    public static Population createPopulation(final LayoutPropertySource source, final int size) {
        final Population result = new Population();
        for (int i = 0; i < size; i++) {
            final Genome genome = createGenome(source);
            final Genome ind = new Genome(genome, 0);
            result.add(ind);
        }
        return result;
    }

    /**
     * Layout the diagram and measure it.
     *
     * @param manager
     *            a {@code DiagramLayoutManager}
     * @param editor
     *            the editor
     * @return measurement result (rating proposal)
     */
    public static int layoutAndMeasure(final DiagramLayoutManager manager, final IEditorPart editor) {
        if ((editor == null) || (manager == null)) {
            // we cannot perform the layout.
            return 0;
        }
        // first phase: build the layout graph
        final KNode layoutGraph = manager.buildLayoutGraph(editor, null, true);
        // second phase: execute layout algorithms
        // We need a new monitor each time because the old one
        // gets closed.
        final IKielerProgressMonitor monitor =
                new BasicProgressMonitor(DiagramLayoutManager.MAX_PROGRESS_LEVELS);
        final IStatus status = manager.layout(monitor, true, false);
        final int rating;
        if (status.isOK()) {
            final KNode layoutGraphAfterLayout = manager.getLayoutGraph();
            Assert.isTrue(layoutGraph == layoutGraphAfterLayout);
            // get the metric ids
            final Set<String> metricIds = EvolutionService.getInstance().getLayoutMetricsIds();
            // XXX: arbitrarily chosen coefficients
            final Deque<Double> coeffsQueue = new LinkedList<Double>();
            for (final double d : new double[] { .08, .02, .1, .5, .3 }) {
                coeffsQueue.add(d);
            }
            final Map<String, Double> coeffsMap = new HashMap<String, Double>(metricIds.size());
            for (final String metricId : metricIds) {
                coeffsMap.put(metricId, coeffsQueue.remove());
            }
            // do the measurement
            rating = measureDiagram(false, layoutGraphAfterLayout, coeffsMap);
        } else {
            // TODO: what to do about the layouting failure? Log it? Abort?
            rating = 0;
        }
        return rating;
    }

    /**
     * @param manager
     *            a {@link DiagramLayoutManager}
     * @param editPart
     *            an {@link EditPart}
     * @return the id of the layouter, or {@code null} if none can be found.
     */
    public static String getLayoutProviderId(
            final DiagramLayoutManager manager, final EditPart editPart) {
        final ILayoutInspector insp = manager.getInspector(editPart);
        insp.initOptions();
        final LayoutProviderData data = insp.getContainerLayouterData();
        if (data == null) {
            return null;
        }
        final String result = data.getId();
        return result;
    }

    /**
     * Analyzes the given KGraph.
     *
     * @param showProgressBar
     *            indicates whether a progress bar shall be shown
     * @param parentNode
     *            the KGraph to be analyzed.
     * @param coefficientsMap
     *            a map containing a coefficient for each metric id.
     * @return a rating
     */
    private static int measureDiagram(
            final boolean showProgressBar, final KNode parentNode,
            final Map<String, Double> coefficientsMap) {
        if (parentNode == null) {
            return 0;
        }
        // get the metric ids
        final Set<String> metricIds = EvolutionService.getInstance().getLayoutMetricsIds();
        final AnalysisServices as = AnalysisServices.getInstance();
        final List<AbstractInfoAnalysis> metricsList =
                new ArrayList<AbstractInfoAnalysis>(metricIds.size());
        // we have the metric ids, now get the metrics
        for (final String metricId : metricIds) {
            final AbstractInfoAnalysis metric = as.getAnalysisById(metricId);
            Assert.isNotNull(metric, "Could not find analysis: " + metricId);
            metricsList.add(metric);
        }
        // TODO: cache the metrics
        final Map<String, Object> results =
                DiagramAnalyser.analyse(parentNode, metricsList, showProgressBar);
        // final double[] scaledResults = new double[metrics.length];
        double sum = 0.0;
        for (final AbstractInfoAnalysis metric : metricsList) {
            final String metricResult = results.get(metric.getID()).toString();
            final double coeff = coefficientsMap.get(metric.getID());
            final double scaled = Double.parseDouble(metricResult) * coeff;
            // scaledResults[i] = scaled;
            sum += scaled;
        }
        final int newRating = (int) Math.round((sum * 1000));
        return newRating;
    }

    /**
     * Adopts layout options from the given {@link Individual} into the given
     * {@link LayoutPropertySource}. The individual must not be {@code null}.
     *
     * @param theIndividual
     *            the individual
     * @param source
     *            the layout property source
     */
    public static void adoptIndividual(final Genome theIndividual, final LayoutPropertySource source) {
        Assert.isLegal(theIndividual != null);

        if (theIndividual == null) {
            return;
        }

        System.out.println("adopt " + theIndividual.toString());
        final LayoutServices layoutServices = LayoutServices.getInstance();
        // set layout options according to genome
        for (final IGene<?> gene : theIndividual) {
            Assert.isNotNull(gene);
            final Object value = gene.getValue();
            final Object id = gene.getId();
            final LayoutOptionData data = layoutServices.getLayoutOptionData((String) id);
            Assert.isNotNull(data);
            switch (data.getType()) {
            case BOOLEAN:
                if (value instanceof Boolean) {
                    source.setPropertyValue(id, ((Boolean) value ? 1 : 0));
                } else {
                    source.setPropertyValue(id, Math.round((Float) value));
                }
                break;
            case ENUM:
                try {
                    source.setPropertyValue(id, value);
                } catch (final NullPointerException e) {
                    System.out.println("WARNING: enum property could not be set.");
                    Assert.isTrue(false);
                }
                break;
            default:
                source.setPropertyValue(id, value.toString());
                break;
            }
        }
    }

    /**
     * Count the learnable properties of the given list of IPropertyDescriptor
     * objects.
     *
     * @return number of learnable properties
     */
    private static int countLearnableProperties(final List<IPropertyDescriptor> propertyDescriptors) {
        int result = 0;
        final Set<String> learnables = EvolutionService.getInstance().getEvolutionDataIds();
        for (final IPropertyDescriptor p : propertyDescriptors) {
            final String id = (String) p.getId();
            // check property descriptor id
            if (!LayoutOptions.LAYOUT_HINT.equals(id)) {
                final LayoutOptionData layoutOptionData =
                        LayoutServices.getInstance().getLayoutOptionData(id);
                final Type type = layoutOptionData.getType();
                switch (type) {
                case BOOLEAN:
                case ENUM:
                case INT:
                case FLOAT:
                    if (learnables.contains(id)) {
                        // learnable --> count it
                        result++;
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
     * Create a genome from the given source.
     *
     * @param source
     * @return a genome.
     */
    private static Genome createGenome(final LayoutPropertySource source) {
        if (source == null) {
            return null;
        }
        /**
         * Get the set of learnable elements that are registered.
         */
        final Set<String> learnables = EvolutionService.getInstance().getEvolutionDataIds();
        final Genome result = new Genome();

        // get data from property descriptors
        final IPropertyDescriptor[] propertyDescriptors = source.getPropertyDescriptors();

        // determine uniformly distributed mutation probability
        double uniformProb = 0.0;
        final int learnableCount = countLearnableProperties(Arrays.asList(propertyDescriptors));
        if (learnableCount > 0) {
            uniformProb = 1.0 / learnableCount;
        }
        for (final IPropertyDescriptor p : propertyDescriptors) {

            final String id = (String) p.getId();
            final Object value = source.getPropertyValue(id);
            // check property descriptor id
            if (LayoutOptions.LAYOUT_HINT.equals(id)) {
                // layout hint --> not learnable
                final ILabelProvider labelProvider = p.getLabelProvider();
                String text;
                if ((value != null) && (labelProvider != null)) {
                    try {
                        text = labelProvider.getText(value);
                    } catch (final ArrayIndexOutOfBoundsException e) {
                        text = "*** EXCEPTION";
                    }
                } else {
                    text = "???";
                }
                System.out.println("--- LAYOUT_HINT: " + value + "=" + text);
            } else {
                // learnable option?
                if (learnables.contains(id)) {
                    final GeneFactory gf = new GeneFactory();
                    final IGene<?> gene = gf.newGene(id, value, uniformProb);
                    Assert.isNotNull(gene, "Failed to create gene for " + id);
                    result.add(gene);
                } else {
                    System.out.println("Not registered: " + id);
                }
            }
        }
        Assert.isTrue(learnableCount == result.size());
        return result;
    }

    /** Hidden constructor to avoid instantiation. **/
    private EvolUtil() {
        // nothing
    }

    /**
     * A factory for genes.
     *
     * @author bdu
     *
     */
    private static class GeneFactory implements IGeneFactory {
        public IGene<?> newGene(
                final Object theId, final Object theValue, final TypeInfo<?> theTypeInfo,
                final MutationInfo theMutationInfo) {
            // TODO: implement
            return null;
        }

        /**
         * Creates a gene with the specified value.
         *
         * @param theId
         *            an identifier
         * @param theValue
         *            a value
         * @param theMutationProbability
         *            the mutation probability
         * @return a gene
         */
        public IGene<?> newGene(
                final Object theId, final Object theValue, final double theMutationProbability) {
            // TODO: regard bounds from the evolution data extensions
            IGene<?> result = null;
            final LayoutOptionData layoutOptionData =
                    LayoutServices.getInstance().getLayoutOptionData((String) theId);
            final IConfigurationElement evolutionData =
                    EvolutionService.getInstance().getEvolutionData((String) theId);
            final String lowerBoundAttr = evolutionData.getAttribute("lowerBound");
            final String upperBoundAttr = evolutionData.getAttribute("upperBound");
            final String distrName = evolutionData.getAttribute("distribution");
            final String variance = evolutionData.getAttribute("variance");
            final Distribution distr = Distribution.valueOf(distrName);
            final Type type = layoutOptionData.getType();

            switch (type) {
            case BOOLEAN: {
                final boolean booleanValue = (Integer.parseInt(theValue.toString()) == 1);
                // result = new BooleanGene(theId, booleanValue,
                // theMutationProbability);
                final Float floatValue = (booleanValue ? 1.0f : 0.0f);
                result =
                        new UniversalGene(theId, floatValue, BooleanGene.UNIVERSAL_TYPE_INFO,
                                new MutationInfo(theMutationProbability, distr));
                System.out.println(result);
            }
                break;
            case ENUM: {
                final int choicesCount = layoutOptionData.getChoices().length;
                final Class<? extends Enum<?>> enumClass =
                        LayoutOptions.getEnumClass((String) theId);
                Assert.isNotNull(enumClass);
                Assert.isTrue(enumClass.getEnumConstants().length == choicesCount);
                final int intValue = Integer.valueOf(theValue.toString());
                result = new EnumGene(theId, intValue, enumClass, theMutationProbability);
                System.out.println("Enum " + enumClass.getSimpleName() + "(" + choicesCount + "): "
                        + intValue);
            }
                break;
            case INT: {
                final int intValue = Integer.parseInt((String) theValue);
                final double var = MutationInfo.DEFAULT_VARIANCE;
                result = new IntegerGene(theId, intValue, theMutationProbability, var);
                System.out.println(result);
            }
                break;
            case FLOAT: {
                final float floatValue = (Float.parseFloat((String) theValue));
                final float lowerBound = (Float.parseFloat(lowerBoundAttr + ""));
                final float upperBound =
                        ((upperBoundAttr == null) ? Float.POSITIVE_INFINITY : (Float
                                .parseFloat(upperBoundAttr + "")));

                // threshold to prevent very small variances
                final float verySmall = 1e-3f;

                // the absolute value is scaled by this factor to get the
                // variance
                final double scalingFactor = .20;

                // estimate desired variance from the absolute value
                final double var =
                        ((Math.abs(floatValue) < verySmall) ? MutationInfo.DEFAULT_VARIANCE : Math
                                .abs(floatValue) * scalingFactor);
                if (lowerBound >= 0.0f) {
                    // we presume we need a strictly positive float gene
                    // result =
                    // new StrictlyPositiveFloatGene(theId, floatValue,
                    // theMutationProbability, var);
                } else {
                    // we use a general float gene
                    // result = new FloatGene(theId, floatValue,
                    // theMutationProbability, var);
                }
                result =
                        new UniversalGene(theId, floatValue, FloatGene.UNIVERSAL_TYPE_INFO,
                                new MutationInfo(theMutationProbability, distr));
                System.out.println(result);
            }
                break;
            default:
                break;
            }
            return result;
        }
    }
}
