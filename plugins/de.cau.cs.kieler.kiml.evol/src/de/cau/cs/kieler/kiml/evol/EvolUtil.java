/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2010 by
 *   + Christian-Albrechts-University of Kiel
 *     + Department of Computer Science
 *       + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.evol;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import de.cau.cs.kieler.core.alg.BasicProgressMonitor;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.model.GraphicalFrameworkService;
import de.cau.cs.kieler.core.model.IGraphicalFrameworkBridge;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.core.ui.KielerProgressMonitor;
import de.cau.cs.kieler.core.ui.util.MonitoredOperation;
import de.cau.cs.kieler.kiml.LayoutAlgorithmData;
import de.cau.cs.kieler.kiml.LayoutContext;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.LayoutDataService;
import de.cau.cs.kieler.kiml.config.DefaultLayoutConfig;
import de.cau.cs.kieler.kiml.config.ILayoutConfig;
import de.cau.cs.kieler.kiml.config.IMutableLayoutConfig;
import de.cau.cs.kieler.kiml.evol.genetic.Genome;
import de.cau.cs.kieler.kiml.evol.genetic.IGene;
import de.cau.cs.kieler.kiml.evol.genetic.Population;
import de.cau.cs.kieler.kiml.grana.AbstractInfoAnalysis;
import de.cau.cs.kieler.kiml.grana.util.DiagramAnalyzer;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.ui.diagram.DiagramLayoutEngine;
import de.cau.cs.kieler.kiml.ui.diagram.IDiagramLayoutManager;
import de.cau.cs.kieler.kiml.ui.diagram.LayoutMapping;
import de.cau.cs.kieler.kiml.ui.service.EclipseLayoutConfig;
import de.cau.cs.kieler.kiml.ui.service.EclipseLayoutInfoService;
import de.cau.cs.kieler.kiml.ui.service.LayoutOptionManager;
import de.cau.cs.kieler.kiml.ui.views.LayoutViewPart;

/**
 * Utility methods for Evolutionary Meta Layout.
 *
 * @author bdu
 */
public final class EvolUtil {
    /**
     * Applier for an individual. Can adopt, layout and measure an individual in
     * the appropriate editor(s).
     *
     * @author bdu
     *
     */
    private static final class IndividualApplierRunnable implements Runnable {
        /**
         * Adopts the given individual, calculates layout for it and applies it
         * to the diagram in the given editor.
         *
         * @param individual
         *            the genome encoding the layout options to use
         * @param editor
         *            the editor in which the layout shall be applied
         * @return the layout manager used to apply the layout
         */
        private static <T> IDiagramLayoutManager<T> adoptAndApplyLayout(
                final Genome individual, final IEditorPart editor) {

            AdoptingRecursiveGraphLayoutEngine engine = new AdoptingRecursiveGraphLayoutEngine();

            IKielerProgressMonitor monitor = new BasicProgressMonitor();

            // TODO: layout might fail
            LayoutMapping<T> layoutResult = (LayoutMapping<T>)
                    engine.calculateLayout(individual, (DiagramEditor) editor, monitor, false /* shouldCopyGraph */);
            // we could do something with the layout result ...

            IDiagramLayoutManager<T> manager = (IDiagramLayoutManager<T>) engine.getManager();

            assert manager != null : "Could not get a layout manager for " + editor.getTitle();

            int nodeCount = 0;
            manager.applyLayout(layoutResult, false, 0);

            return manager;
        }

        /**
         * Adopt the given individual and calculate layout for it in the
         * appropriate editor(s). The obtained layout is applied to the
         * diagrams.
         *
         * @param individual
         *            a {@link Genome}; must not be {@code null}
         * @param expectedLayoutAlgorithmId
         *            the ID of the expected layout algorithm; must not be
         *            {@code null}
         */
        private static void applyIndividual(
                final Genome individual, final String expectedLayoutAlgorithmId) {
            if ((individual == null) || (expectedLayoutAlgorithmId == null)) {
                throw new IllegalArgumentException();
            }

            final LayoutDataService layoutServices = LayoutDataService.getInstance();
            // presuming layoutServices != null

            // Get the expected layout type id.
            LayoutAlgorithmData expectedAlgorithmData =
                    layoutServices.getAlgorithmData(expectedLayoutAlgorithmId);
            assert expectedAlgorithmData != null;
            String expectedLayoutTypeId = expectedAlgorithmData.getType();
            assert expectedLayoutTypeId != null;

            // Get the appropriate editors.
            Collection<IEditorPart> editors = EvolUtil.getWantedEditors();
            assert editors != null;

            IPreferenceStore store = EvolPlugin.getDefault().getPreferenceStore();

            boolean useDifferentType =
                    store.getBoolean(EvolPlugin.PREF_USE_DIFFERENT_TYPE_LAYOUT_HINT);

            // Iterate the editors and perform layout in each appropriate
            // editor.
            for (final IEditorPart editor : editors) {
                System.out.println();
                System.out.print("--- Editor: " + editor.getTitle() + " ");

                // Find a layout algorithm for the editor.
                LayoutAlgorithmData data = getLayoutAlgorithmData(editor);
                if (data == null) {
                    // no layout algorithm found --> skip editor
                    continue;
                }

                // Check if we can handle its type.
                String layoutTypeId = data.getType();

                if (!useDifferentType
                        && !isCompatibleLayoutAlgorithm(data.getId(), expectedLayoutTypeId)) {
                    // The type in the editor is not compatible to the current
                    // population. We skip this editor.

                    EvolPlugin.logStatus("The editor " + editor.getTitle() + " is set to "
                            + layoutTypeId + ". Expecting an editor for " + expectedLayoutTypeId
                            + ".");
                    continue;
                }
                assert expectedLayoutTypeId.equalsIgnoreCase(layoutTypeId) || useDifferentType;

                IDiagramLayoutManager manager = adoptAndApplyLayout(individual, editor);
                // so now we have a manager ...
            }
        }

        /**
         * Tries to find a generic layout algorithm for the given editor.
         *
         * @param editor
         *            an editor
         * @return a layout algorithm or {@code null} if none can be found
         */
        private static LayoutAlgorithmData getLayoutAlgorithmData(final IEditorPart editor) {
            EditPart editPart = getCurrentEditPart(editor);
            // TODO use root edit part

            // See which layout algorithm suits for the editor.
            LayoutAlgorithmData data = EvolUtil.getLayoutAlgorithmData(editor, editPart);

            if (data == null) {
                // no layout algorithm found --> skip this editor
                EvolPlugin.showError("Could not find a layout algorithm for the editor '"
                        + editor.getTitle() + "'.", null);
                return null;
            }

            String layoutAlgorithmId = data.getId();
            System.out.println("(" + layoutAlgorithmId + ")");

            return data;
        }

        /**
         * Creates a new {@link IndividualApplierRunnable} instance.
         *
         * @param theIndividual
         *            the individual to be applied
         * @param theLayoutAlgorithmId
         *            ID of expected algorithm
         */
        IndividualApplierRunnable(final Genome theIndividual, final String theLayoutAlgorithmId) {
            this.layoutAlgorithmId = theLayoutAlgorithmId;
            this.individual = theIndividual;
        }

        /**
         * The expected layout algorithm ID.
         */
        private final String layoutAlgorithmId;

        /**
         * The individual.
         */
        private final Genome individual;

        /**
         * {@inheritDoc}
         */
        public void run() {
            // Adopt the given individual and apply its layout in the
            // appropriate editor(s).
            applyIndividual(this.individual, this.layoutAlgorithmId);
        }

    }

    /**
     * Auto-rater for an individual.
     *
     * @author bdu
     *
     */
    private static final class IndividualAutoRaterRunnable implements Runnable {

        /**
         * Lays out the given individual in the given editors and calculates
         * automatic ratings for it. <strong>Note</strong>: The rating is not
         * stored in the individual.
         *
         * @param ind
         *            a {@link Genome}; must not be {@code null}
         * @param editors
         *            set of editors; must not be {@code null}
         * @param weightsGenomes
         *            the weights genomes; must not be {@code null}
         *
         * @return the rating proposal
         */
        private static double calculateAutoRating(
                final Genome ind, final Set<IEditorPart> editors, final Population weightsGenomes) {
            if ((ind == null) || (editors == null) || (weightsGenomes == null)) {
                throw new IllegalArgumentException("Arguments must not be null.");
            }

            double totalRating = 0.0;
            int editorCount = editors.size();

            for (final IEditorPart editor : editors) {

                // TODO: what if weights genomes is empty?
                assert !weightsGenomes.isEmpty();

                double editorRating = 0.0;
                Map<String, Object> measurements = null;

                int weightsGenomesCount = weightsGenomes.size();
                for (final Genome weightGenome : weightsGenomes.getGenomes()) {
                    assert weightGenome != null;

                    Map<String, Double> weightsMap = extractMetricWeights(weightGenome);
                    assert weightsMap != null;
                    normalize(weightsMap);

                    // Get measurements, then get rating proposal from each
                    // weight genome.

                    // Discuss: For more than one editor: rate average
                    // measurements, or use average ratings instead?

                    if (measurements == null) {
                        try {
                            measurements = measure(ind, editor, weightsMap);
                        } catch (Exception exception) {
                            measurements = null;
                            totalRating = 0.0;
                        }

                        ind.setFeatures(measurements);
                        // Nice-to-have: don't set features here. This works
                        // only for
                        // one editor. For more editors, average measurements
                        // must be calculated.
                    }

                    double scaledSum = 0.0;
                    if (measurements != null) {
                        scaledSum = weight(measurements, weightsMap);
                    }

                    double rating = scaledSum;
                    System.out.println("Rated " + rating + " by " + weightGenome.toString());
                    weightGenome.addFeature("proposedRating:" + ind.getId(), rating);

                    editorRating += rating;
                }

                if (weightsGenomesCount > 1) {
                    double averageEditorRating = editorRating / weightsGenomesCount;
                    totalRating += averageEditorRating;
                } else {
                    totalRating += editorRating;
                }
            }

            if (editorCount > 1) {
                // return average rating of all editors
                double averageTotalRating = totalRating / editorCount;
                return averageTotalRating;
            }

            return totalRating;
        }

        /**
         * Extracts the metric weights from the given genome.
         *
         * @param genome
         *            a {@link Genome}
         * @return a map associating metric weights to metric IDs.
         */
        private static Map<String, Double> extractMetricWeights(final Genome genome) {

            EvolutionServices evolService = EvolutionServices.getInstance();

            // presuming evolService != null
            Set<String> metricIds = evolService.getLayoutMetricsIds();

            // presuming metricIds != null
            Map<String, Double> result = new HashMap<String, Double>(metricIds.size());

            for (final IGene<?> gene : genome.getGenes()) {
                // presuming gene != null
                String id = gene.getId();

                if (!metricIds.contains(id)) {
                    // not a metric id --> skip
                    continue;
                }

                Float value = (Float) gene.getValue();
                result.put(id, value.doubleValue());
            }

            return result;
        }

        /**
         * Adopts the given individual, calculates a layout for it in the given
         * editor and measures its features.
         *
         * @param ind
         *            a {@link Genome}; may not be {@code null}
         * @param editor
         *            an {@link IEditorPart}
         * @param weightsMap
         *            a map that associates weights to metric IDs; must not be
         *            {@code null}. This map indicates which features shall be
         *            measured.
         * @return the measured features
         */
        private static Map<String, Object> measure(
                final Genome ind, final IEditorPart editor, final Map<String, Double> weightsMap) {
            if ((ind == null) || (weightsMap == null)) {
                throw new IllegalArgumentException();
            }

            AdoptingRecursiveGraphLayoutEngine engine = new AdoptingRecursiveGraphLayoutEngine();

            IKielerProgressMonitor monitor = new BasicProgressMonitor();

            // calculate layout (might throw an exception)
            LayoutMapping<?> layoutResult =
                    engine.calculateLayout(ind, (DiagramEditor) editor, monitor, true /* shouldCopyGraph */);

            Map<String, Object> measurements = measure(layoutResult.getLayoutGraph(), weightsMap);
            // Attention: The measurements may contain additional intermediate
            // results we did not ask for. See #1152.

            // Add the execution speed value.
            double time = monitor.getExecutionTime();
            double speed = normalizedSpeed(time);
            measurements.put(EvolPlugin.EXECUTION_SPEED_VALUE_ID, speed);
            System.out.println("Result: " + EvolPlugin.EXECUTION_SPEED_VALUE_ID + ": " + speed);

            return measurements;
        }

        /**
         * Measures the given layout graph, according to the given metric
         * weights. It is recommended but not necessary to normalize the weights
         * map before so that its values yield a sum of one. Use
         * {@link #normalize(Map)} for that purpose.
         *
         * @param parentNode
         *            the KGraph to be analyzed.
         * @param theWeightsMap
         *            a map that associates weights to metric IDs; must not be
         *            {@code null}
         * @return a rating proposal
         */
        private static Map<String, Object> measure(
                final KNode parentNode, final Map<String, Double> theWeightsMap) {
            if (theWeightsMap == null) {
                throw new IllegalArgumentException();
            }

            if (parentNode == null) {
                return Collections.emptyMap();
            }

            // Get the layout metric IDs.
            Set<String> metricIds = EvolutionServices.getInstance().getLayoutMetricsIds();

            // Get the metrics.
            Set<AbstractInfoAnalysis> metrics =
                    EvolutionServices.getInstance().getLayoutMetrics();

            Map<String, Double> weightsMap = new HashMap<String, Double>(theWeightsMap);

            List<AbstractInfoAnalysis> wantedMetricsList =
                    new ArrayList<AbstractInfoAnalysis>(metricIds.size());
            for (final AbstractInfoAnalysis metric : metrics) {

                String metricId = metric.getId();
                if (!weightsMap.containsKey(metricId)) {
                    // Skip this analysis.
                    continue;
                }
                double coeff = weightsMap.get(metricId).doubleValue();

                if (coeff == 0.0) {
                    // Skip this analysis.
                    continue;
                }

                wantedMetricsList.add(metric);
            }

            // Do we have anything to measure?
            if (wantedMetricsList.isEmpty()) {
                return Collections.emptyMap();
            }

            Map<String, Object> analyserOptionsMap = new HashMap<String, Object>();
            analyserOptionsMap.put(EvolPlugin.WEIGHTS_ID, weightsMap);

            // Perform the measurement.
            Map<String, Object> analysisResults =
                    DiagramAnalyzer.analyze(parentNode, wantedMetricsList, analyserOptionsMap,
                            false /* progressBar */);

            // Check the results.
            for (final String metricId : metricIds) {
                Object analysisResult = analysisResults.get(metricId);

                if (!(analysisResult instanceof Float)) {
                    System.err.println("Result: " + metricId + ": " + analysisResult.toString());
                    EvolPlugin.showError("Cannot handle analysis result for " + metricId + ": "
                            + analysisResult.toString(), null);
                }

                Float value = (Float) analysisResult;
                System.out.println("Result: " + metricId + ": " + value);

                assert (value >= 0.0) && (value <= 1.0) : "Analysis result out of range for "
                        + metricId + ": " + value;

            }

            return analysisResults;
        }

        /**
         * Scales the values in the given map so that their sum equals one. This
         * operation modifies the map entries of the given map.
         *
         * @param map
         *            a map containing {@link Double} values; must not be
         *            {@code null}
         */
        private static void normalize(final Map<String, Double> map) {
            if (map == null) {
                throw new IllegalArgumentException();
            }

            // Calculate sum.
            double sum = 0.0;
            for (final Double value : map.values()) {
                sum += value.doubleValue();
            }

            if (sum != 1.0) {
                // Need to scale values.
                double factor = 1.0 / sum;

                for (final Entry<String, Double> entry : map.entrySet()) {
                    double value = entry.getValue();
                    entry.setValue(value * factor);
                }
            }
        }

        /**
         * Returns a normalized speed value for the given time. The result is
         * between {@code +0.0} and {@code +1.0}. It is {@code +0.0} if
         * {@code time} is positive infinity and {@code 1.0} if {@code time} is
         * zero, and monotonically decreasing for increasing time.
         *
         * @param time
         *            time; must be positive
         * @return normed speed value
         */
        private static double normalizedSpeed(final double time) {
            if (time < 0.0) {
                throw new IllegalArgumentException("The value of 'time' must be positive:" + time);
            }
            return Math.exp(-time);
        }

        /**
         * Returns the weighted sum of the given measurements, using the given
         * weights.
         *
         * @param measurements
         *            a map of IDs and measurements (double values)
         * @param weightsMap
         *            a map of weight IDs and weights
         * @return the weighted sum of the given measurements
         */
        private static double weight(
                final Map<String, Object> measurements, final Map<String, Double> weightsMap) {
            // Attention: The measurements can contain additional intermediate
            // results we did not ask for.

            // This is the default weight for metrics. If this is not zero, then
            // the weights map should be renormalized after the additional
            // metrics are added.
            final Double defaultScale = 0.0;
            for (final String metricId : measurements.keySet()) {
                if (!weightsMap.containsKey(metricId)) {
                    // add additional metrics
                    weightsMap.put(metricId, defaultScale);
                }
            }

            double scaledSum = 0.0;

            // scale results
            for (final Entry<String, Object> measurement : measurements.entrySet()) {
                String metricId = measurement.getKey();
                String metricResult = measurement.getValue().toString();

                // Get the weight.
                assert weightsMap.containsKey(metricId);
                double coeff = weightsMap.get(metricId);

                double val;
                try {
                    double parsedResult = Double.parseDouble(metricResult);
                    val = parsedResult;

                } catch (final NumberFormatException exception) {
                    val = 0.0;
                }

                final double scaled = val * coeff;
                scaledSum += scaled;
            }

            return scaledSum;
        }

        /**
         * Creates a new {@link IndividualAutoRaterRunnable} instance.
         *
         * @param theIndividual
         *            the individual that shall be rated
         * @param theWeightsGenomes
         *            the weights genomes
         */
        IndividualAutoRaterRunnable(final Genome theIndividual, final Population theWeightsGenomes) {
            this.individual = theIndividual;
            this.weightsGenomes = theWeightsGenomes;
        }

        // private fields
        /** The individual. */
        private final Genome individual;

        /** The population of weights genomes. */
        private final Population weightsGenomes;

        /**
         * {@inheritDoc}
         */
        public void run() {
            // Must be run in the UI thread.

            Collection<IEditorPart> editors = EvolUtil.getWantedEditors();

            Set<IEditorPart> editorsSet = new HashSet<IEditorPart>(editors);

            autoRateIndividual(editorsSet);
        }

        /**
         * Calculates layouts and automatic ratings for the given individual in
         * the given editors.
         *
         * @param editors
         *            Specifies the editors in which the individual shall be
         *            laid out; must not be {@code null}.
         */
        private void autoRateIndividual(final Set<IEditorPart> editors) {
            if (editors == null) {
                throw new IllegalArgumentException("Argument must not be null: editors");
            }

            Genome ind = this.individual;
            Population genomes = this.weightsGenomes;

            if ((ind == null) || (genomes == null)) {
                return;
            }

            double rating = 0.0;
            try {
                rating = calculateAutoRating(ind, editors, genomes);
            } catch (Exception exception) {
                rating = 0.0;
                exception.printStackTrace();
            } finally {
                ind.setUserRating(rating);
            }
        }

    }

    /**
     * Refresher for the layout view. Asynchronously refreshes the layout view,
     * if it can be found.
     *
     * @author bdu
     *
     */
    private static class LayoutViewRefreshRunnable implements Runnable {
        /**
         * Constructs a new {@link LayoutViewRefreshRunnable} instance.
         */
        LayoutViewRefreshRunnable() {
            // nothing to do here.
        }

        /**
         * {@inheritDoc}
         */
        public void run() {
            LayoutViewPart layoutView = LayoutViewPart.findView();
            if (layoutView != null) {
                // Refresh the layout view asynchronously.
                layoutView.refresh();
            }
        }
    }

    /**
     * Synchronously refreshes the layout according to the current individual of
     * the given model.
     *
     * @param model
     *            the evolution model; must be valid
     */
    public static void applyCurrentIndividual(final EvolModel model) {
        // presuming model != null
        assert model.isValid() : "Attempt to apply an individual when the model was not valid.";

        // Get the current individual from the model.
        Genome individual = model.getCurrentIndividual();
        assert individual != null;

        // Get the expected layout algorithm id.
        String expectedLayoutAlgorithmId = model.getLayoutAlgorithmId();
        assert expectedLayoutAlgorithmId != null;

        // Adopt and layout the current individual.
        syncApplyIndividual(individual, expectedLayoutAlgorithmId);

        // Refresh the layout view.
        asyncRefreshLayoutView();
    }

    /**
     * Asynchronously refreshes the layout view, if it can be found.
     */
    public static void asyncRefreshLayoutView() {
        MonitoredOperation.runInUI(new LayoutViewRefreshRunnable(), false);
    }

    /**
     * Auto-rate the given population in the appropriate editors.
     *
     * @param thePopulationIterator
     *            an iterator over the {@link Population} to be rated
     * @param theMonitor
     *            a progress monitor; may be {@code null}
     * @param theWeightsGenomes
     *            a population of genomes encoding the metric weights
     */
    public static void autoRate(
            final ListIterator<Genome> thePopulationIterator,
            final IKielerProgressMonitor theMonitor, final Population theWeightsGenomes) {
        // TODO: move code into a class RatingPopulation extends Population.
        if (thePopulationIterator == null) {
            throw new IllegalArgumentException();
        }

        // Ensure there is a monitor of some sort.
        IKielerProgressMonitor monitor =
                (theMonitor != null) ? theMonitor : new KielerProgressMonitor(
                        new NullProgressMonitor());

        // size of the iterator is unknown
        // int total = size;
        final int scale = 100;

        try {
            monitor.begin("Auto-rating individuals.", IKielerProgressMonitor.UNKNOWN_WORK);

            // Calculate auto-rating for each individual.
            while (thePopulationIterator.hasNext()) {
                if (monitor.isCanceled()) {
                    throw new OperationCanceledException();
                }

                Genome ind = thePopulationIterator.next();

                Runnable runnable = new IndividualAutoRaterRunnable(ind, theWeightsGenomes);

                // Synchronously auto-rate the individual.
                MonitoredOperation.runInUI(runnable, true /* synch */);

                monitor.worked(1 * scale);
            }

        } finally {
            monitor.done();
        }
    }

    /**
     * Creates a population of the default size, taking initial values from the
     * given diagram editors.
     *
     * @param editors
     *            a set of {@link IEditorPart} instances. Must be
     *            DiagramEditors.
     * @return a new {@link Population} of default size, or an empty
     *         {@link Population} in case of an error
     */
    public static Population createPopulation(final Set<IEditorPart> editors) {
        if ((editors == null) || editors.isEmpty()) {
            return new Population();
        }

        // Get the layout configurations of the editors.
        List<LayoutContext> configs = getLayoutConfigs(editors);
        if (configs.isEmpty()) {
            return new Population();
        }

        Population result = createPopulation(configs);
        return result;
    }

    /**
     * Returns the current editor (if any). Returns {@code null} if called from
     * a non-UI thread.
     *
     * @return the current editor or {@code null} if none exists or if called
     *         from a non-UI thread.
     */
    public static IEditorPart getCurrentEditor() {
        // Try to get the editor that is tracked by the layout view (must be in
        // UI thread).
        LayoutViewPart layoutViewPart = LayoutViewPart.findView();
        if (layoutViewPart != null) {
            IWorkbenchPart editor = layoutViewPart.getCurrentPart();
            if (editor instanceof IEditorPart) {
                return (IEditorPart) editor;
            }
        }

        // Try to get the active editor of the workbench (must be in UI thread).
        IWorkbench workbench = PlatformUI.getWorkbench();
        IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
        if (window == null) {
            return null;
        }
        IWorkbenchPage page = window.getActivePage();
        if (page == null) {
            return null;
        }
        IEditorPart editor = page.getActiveEditor();
        if (editor != null) {
            return editor;
        }

        // No active editor could be found.
        return null;
    }

    /**
     * Returns the current edit part of the given editor.
     *
     * @param editor
     *            an editor
     * @return the current edit part or {@code null} if none exists.
     */
    public static EditPart getCurrentEditPart(final IEditorPart editor) {
        EditPart result = null;
        if (editor != null) {
            ISelection selection = editor.getEditorSite().getSelectionProvider().getSelection();
            if (selection instanceof IStructuredSelection) {
                Object element = ((IStructuredSelection) selection).getFirstElement();
                if (element instanceof IGraphicalEditPart) {
                    result = (IGraphicalEditPart) element;
                }
            }
        }
        return result;
    }

    /**
     * Find all the visible graphical editors in the workbench.
     *
     * @return the set of the visible graphical editors; may be empty.
     */
    public static Set<IEditorPart> getEditors() {

        Set<IEditorPart> result = new HashSet<IEditorPart>();

        // Try to get the editors of the workbench.
        IWorkbench workbench = PlatformUI.getWorkbench();
        IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
        if (window == null) {
            return result;
        }

        IWorkbenchPage[] pages = window.getPages();
        for (final IWorkbenchPage page : pages) {
            if (page.isEditorAreaVisible()) {
                IEditorReference[] references = page.getEditorReferences();
                assert references != null;

                for (final IEditorReference ref : references) {
                    IEditorPart editor = ref.getEditor(false);
                    if (editor instanceof DiagramEditor) {
                        result.add(editor);
                    }
                }
            }
        }

        return result;
    }

    /**
     * Tries to find a layout algorithm for the given {@link IEditorPart} and
     * {@link EditPart}.
     *
     * @param editor
     *            a {@link IEditorPart}
     * @param editPart
     *            an {@link EditPart}
     * @return the {@link LayoutAlgorithmData} of the layout algorithm, or
     *         {@code null} if none can be found.
     */
    public static LayoutAlgorithmData getLayoutAlgorithmData(
            final IEditorPart editor, final EditPart editPart) {
        IDiagramLayoutManager<?> manager =
                EclipseLayoutInfoService.getInstance().getManager(editor, editPart);
        IGraphicalFrameworkBridge bridge = GraphicalFrameworkService.getInstance().getBridge(editor);
        if (manager != null && bridge != null) {
            LayoutContext context = new LayoutContext();
            context.setProperty(LayoutContext.DIAGRAM_PART, editPart);
            context.setProperty(EclipseLayoutConfig.WORKBENCH_PART, editor);
            context.setProperty(DefaultLayoutConfig.OPT_MAKE_OPTIONS, true);
            LayoutOptionManager optionManager = DiagramLayoutEngine.INSTANCE.getOptionManager();
            IMutableLayoutConfig layoutConfig = optionManager.createConfig(bridge.getElement(editPart),
                    manager.getLayoutConfig());
            layoutConfig.enrich(context);
            return context.getProperty(DefaultLayoutConfig.CONTENT_ALGO);
        }
        return null;
    }

    /**
     * Return a list of the IDs of all layout algorithms that can handle the
     * specified layout type.
     *
     * @param layoutType
     *            the type id to look for; must not be {@code null}
     * @return a list of layout algorithm IDs of all layout algorithms of the
     *         given type; may be empty.
     */
    public static List<String> getLayoutAlgorithmIds(final String layoutType) {
        if (layoutType == null) {
            throw new IllegalArgumentException();
        }

        List<String> result = new LinkedList<String>();

        LayoutDataService layoutServices = LayoutDataService.getInstance();

        for (final LayoutAlgorithmData data : layoutServices.getAlgorithmData()) {
            if (data.getType().equalsIgnoreCase(layoutType)) {
                result.add(data.getId());
            }
        }

        return result;
    }

    /**
     * Checks if the given layout algorithm is of the given type.
     *
     * @param algorithmId
     *            layout algorithm ID; must not be {@code null}
     * @param typeId
     *            layout type ID
     * @return {@code true} iff the given layout algorithm is of the given type
     */
    public static boolean isCompatibleLayoutAlgorithm(
            final String algorithmId, final String typeId) {
        if (algorithmId == null) {
            throw new IllegalArgumentException();
        }

        LayoutDataService layoutServices = LayoutDataService.getInstance();
        // presuming layoutServices != null
        LayoutAlgorithmData algorithmData = layoutServices.getAlgorithmData(algorithmId);
        // presuming algorithmData != null
        String newTypeId = algorithmData.getType();

        boolean result = typeId.equalsIgnoreCase(newTypeId);

        if (!result) {
            System.out.println("expected type: " + typeId);
            System.out.println("present type: " + newTypeId);
        }

        return result;
    }

    /**
     * Synchronously applies the given individual.
     *
     * @param individual
     *            the {@link Genome}
     * @param algorithmId
     *            the expected layout algorithm id
     */
    public static void syncApplyIndividual(final Genome individual, final String algorithmId) {
        MonitoredOperation
                .runInUI(new IndividualApplierRunnable(individual, algorithmId), true /* synch */);
    }

    /**
     * Creates a population of default size, taking initial values from the
     * given list of {@link ILayoutConfig} instances.
     *
     * @param configs
     *            list of layout configurations
     * @return the new population
     */
    private static Population createPopulation(final List<LayoutContext> configs) {
        int size = EvolPlugin.getDefault().getPreferenceStore()
                        .getInt(EvolPlugin.PREF_POPULATION_SIZE);

        return createPopulation(configs, size);
    }

    /**
     * Creates a population of the given size, taking initial values from the
     * given list of {@link ILayoutConfig} instances.
     *
     * @param configs
     *            list of layout configurations
     * @param size
     *            desired population size
     * @return the new population
     */
    private static Population createPopulation(final List<LayoutContext> configs, final int size) {
        if ((configs == null) || (size < 0)) {
            throw new IllegalArgumentException();
        }

        Population result = new Population();

        Set<Object> presentLayoutHintIds =
                getPropertyValues(configs, LayoutOptions.ALGORITHM_ID);

        assert !presentLayoutHintIds.isEmpty() : "Layout hint missing.";

        // Create the individuals one by one.
        GenomeFactory genomeFactory = new GenomeFactory(null);

        for (int i = 0; i < size; i++) {
            Genome genome = genomeFactory.createGenome(configs, presentLayoutHintIds);
            assert genome != null : "Failed to create genome.";
            result.add(genome);
        }
        return result;
    }

    /**
     * Collect the layout configuration handlers of the given editors. The
     * current editor is treated first, if there is any.
     *
     * @param editors
     *            set of diagram editors
     * @return a list of layout configurations
     */
    private static List<LayoutContext> getLayoutConfigs(final Set<IEditorPart> editors) {
        List<LayoutContext> configs = new LinkedList<LayoutContext>();

        // Handle current editor.
        IEditorPart currentEditor = getCurrentEditor();
        if (currentEditor != null) {
            LayoutContext context = getContext(currentEditor);
            if (context != null) {
                configs.add(context);
            }
        }

        // Collect the layout configurations of the other editors.
        for (final IEditorPart editor : editors) {
            if (editor == currentEditor) {
                // Already handled this one.
                continue;
            }

            LayoutContext context = getContext(editor);
            if (context != null) {
                configs.add(context);
            }
        }

        return configs;
    }

    /**
     * Layout configuration.
     */
    public static final Property<IMutableLayoutConfig> LAYOUT_CONFIG
            = new Property<IMutableLayoutConfig>("context.layoutConfig");

    /**
     * Returns the layout context for the given editor.
     *
     * @param editor
     *            an editor
     * @return layout context for the given editor
     */
    private static LayoutContext getContext(final IWorkbenchPart editor) {
        IDiagramLayoutManager<?> manager = EclipseLayoutInfoService.getInstance()
                .getManager(editor, null);
        IGraphicalFrameworkBridge bridge = GraphicalFrameworkService.getInstance().getBridge(editor);
        if (manager != null && bridge != null) {
            EditPart diagramPart = bridge.getEditPart(editor);
            EObject diagramElement = bridge.getElement(diagramPart);
            LayoutContext context = new LayoutContext();
            context.setProperty(LayoutContext.DOMAIN_MODEL, diagramElement);
            context.setProperty(LayoutContext.DIAGRAM_PART, diagramPart);
            context.setProperty(EclipseLayoutConfig.WORKBENCH_PART, editor);

            LayoutOptionManager optionManager = DiagramLayoutEngine.INSTANCE.getOptionManager();
            IMutableLayoutConfig layoutConfig = optionManager.createConfig(diagramElement,
                    manager.getLayoutConfig());
            context.setProperty(LAYOUT_CONFIG, layoutConfig);

            context.setProperty(DefaultLayoutConfig.OPT_MAKE_OPTIONS, true);
            layoutConfig.enrich(context);
            return context;
        }
        return null;
    }

    /**
     * Retrieve the values of the given IDs from the given layout
     * configurations. For layout hint, the (non-null) layout hint identifiers
     * are returned.
     *
     * @param configs
     *            list of layout configurations; must not be {@code null}
     * @param id
     *            an identifier
     * @return the set of values having the specified ID
     */
    private static Set<Object> getPropertyValues(
            final List<LayoutContext> configs, final String id) {
        Set<Object> result = new LinkedHashSet<Object>();
        LayoutDataService layoutServices = LayoutDataService.getInstance();
        LayoutOptionData<?> optionData = layoutServices.getOptionData(id);

        for (final LayoutContext context : configs) {
            ILayoutConfig config = context.getProperty(LAYOUT_CONFIG);
            Object value = config.getValue(optionData, context);

            if (LayoutOptions.ALGORITHM_ID.equals(id)) {
                if (value == null) {
                    // layout hint not found
                    continue;
                }

                // we can use this
                assert value instanceof String;
                result.add(value);
                continue;
            }
            // normal option
            result.add(value);
        }

        return result;
    }

    /**
     * Gets the set of wanted editors, which may be the current editor or all
     * visible editors, depending on the user setting.
     *
     * @return the set of wanted editors, may be empty.
     */
    private static Set<IEditorPart> getWantedEditors() {
        // Get the current editor (may be null).
        IEditorPart currentEditor = getCurrentEditor();

        String prefEditors =
                EvolPlugin.getDefault().getPreferenceStore().getString(EvolPlugin.PREF_EDITORS);

        boolean wantAllEditors = EvolPlugin.ALL_EDITORS.equalsIgnoreCase(prefEditors);

        // Collect the editor(s).
        Set<IEditorPart> editors;
        if (wantAllEditors) {
            editors = getEditors();
        } else if (currentEditor != null) {
            editors = Collections.singleton(currentEditor);
        } else {
            editors = Collections.emptySet();
        }

        return editors;
    }

    /** Hidden constructor to avoid instantiation. */
    private EvolUtil() {
        // nothing
    }

    /**
     * Saves options of the given model to a file.
     *
     * @param model
     *            the model to save
     * @param canOverWrite
     *            indicates whether the output file may be overwritten if it
     *            exists
     * @throws IOException
     *             If an I/O error occurs
     */
    public void saveOptionsToFile(final EvolModel model, final boolean canOverWrite)
            throws IOException {
        File file = new File("evol");
        if (!file.exists() || canOverWrite) {

            FileWriter writer = null;
            try {
                writer = new FileWriter(file);

                // Save options from current individual.
                Genome genome = model.getCurrentIndividual();

                String newLine = EvolPlugin.LINE_DELIMITER;

                for (final IGene<?> gene : genome.getGenes()) {
                    writer.append(gene.getId() + ";" + gene.getValue() + newLine);
                }

            } catch (final IOException exception) {
                exception.printStackTrace();
            } finally {
                if (writer != null) {
                    writer.close();
                }
            }
        }
    }
}
