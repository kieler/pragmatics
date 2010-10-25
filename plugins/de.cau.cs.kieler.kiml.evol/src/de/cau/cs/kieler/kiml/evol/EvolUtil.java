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
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.core.alg.BasicProgressMonitor;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.ui.util.MonitoredOperation;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.LayoutProviderData;
import de.cau.cs.kieler.kiml.LayoutServices;
import de.cau.cs.kieler.kiml.evol.genetic.Genome;
import de.cau.cs.kieler.kiml.evol.genetic.IGene;
import de.cau.cs.kieler.kiml.evol.genetic.Population;
import de.cau.cs.kieler.kiml.grana.AbstractInfoAnalysis;
import de.cau.cs.kieler.kiml.grana.ui.DiagramAnalyzer;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.ui.layout.DiagramLayoutManager;
import de.cau.cs.kieler.kiml.ui.layout.EclipseLayoutServices;
import de.cau.cs.kieler.kiml.ui.layout.ILayoutInspector;
import de.cau.cs.kieler.kiml.ui.views.LayoutViewPart;

/**
 * Utility methods for Evolutionary Meta Layout.
 *
 * @author bdu
 *
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
        private static DiagramLayoutManager adoptAndApplyLayout(
                final Genome individual, final IEditorPart editor) {
            // Adopt the layout options that are encoded in the individual.
            // try {
            // // EvolUtil.adoptIndividual(individual, editor);
            // } catch (KielerException exception) {
            // exception.printStackTrace();
            // EvolPlugin.showError("Could not adopt the individual.",
            // exception);
            // return null;
            // }

            AdoptingRecursiveLayouterEngine engine = new AdoptingRecursiveLayouterEngine();

            IKielerProgressMonitor monitor = new BasicProgressMonitor();

            // TODO: layout might fail
            KNode layoutResult =
                    engine.calculateLayout(individual, (DiagramEditor) editor, monitor);

            DiagramLayoutManager manager = engine.getManager();

            // We don't specify the edit part because we want a manager for
            // the whole diagram.
            // DiagramLayoutManager manager =
            // EclipseLayoutServices.getInstance().getManager(editor, null);
            assert manager != null : "Could not get a layout manager for " + editor.getTitle();

            // IKielerProgressMonitor monitor =
            // EvolUtil.calculateLayout(manager, editor);

            // if (monitor != null) {
                // Apply the layout to the diagram in the editor.
            manager.applyAnimatedLayout(true /* animate */, false /* cacheLayout */, 0);
            // }

            return manager;
        }

        /**
         * Adopt the given individual and calculate layout for it in the
         * appropriate editor(s). The obtained layout is applied to the
         * diagrams.
         *
         * @param individual
         *            a {@link Genome}; must not be {@code null}
         * @param expectedLayoutProviderId
         *            the ID of the expected layout provider; must not be
         *            {@code null}
         */
        private static void applyIndividual(
                final Genome individual, final String expectedLayoutProviderId) {
            if ((individual == null) || (expectedLayoutProviderId == null)) {
                throw new IllegalArgumentException();
            }

            final LayoutServices layoutServices = LayoutServices.getInstance();
            // presuming layoutServices != null

            // Get the expected layout type id.
            LayoutProviderData expectedProviderData =
                    layoutServices.getLayoutProviderData(expectedLayoutProviderId);
            assert expectedProviderData != null;
            String expectedLayoutTypeId = expectedProviderData.getType();
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

                LayoutProviderData data = getLayoutProviderData(editor);

                // Check if we can handle its type.
                String layoutTypeId = data.getType();
                if (!expectedLayoutTypeId.equalsIgnoreCase(layoutTypeId) && !useDifferentType) {
                    // The type in the editor is not compatible to the current
                    // population. We skip this editor.

                    System.err.println("The editor " + editor.getTitle() + " is set to "
                            + layoutTypeId + ". Expecting an editor for " + expectedLayoutTypeId
                            + ".");
                    continue;
                }

                DiagramLayoutManager manager = adoptAndApplyLayout(individual, editor);
            }
        }

        /**
         * Tries to find a layout provider for the given editor.
         *
         * @param editor
         *            an editor
         * @return a layout provider or {@code null} if none can be found
         */
        private static LayoutProviderData getLayoutProviderData(final IEditorPart editor) {
            EditPart editPart = getCurrentEditPart(editor);

            // See which layout provider suits for the editor.
            LayoutProviderData data = EvolUtil.getLayoutProviderData(editor, editPart);

            String layoutProviderId;
            if (data == null) {
                // no layout provider found --> skip this editor
                System.err.println("Could not find a layout provider for the editor '"
                        + editor.getTitle() + "'.");
                return null;
            }
            layoutProviderId = data.getId();
            System.out.println("(" + layoutProviderId + ")");

            return data;
        }

        /**
         * Creates a new {@link IndividualApplierRunnable} instance.
         *
         * @param theIndividual
         * @param theLayoutProviderId
         */
        IndividualApplierRunnable(final Genome theIndividual, final String theLayoutProviderId) {
            this.layoutProviderId = theLayoutProviderId;
            this.individual = theIndividual;
        }

        /**
         * The expected layout provider ID.
         */
        private final String layoutProviderId;

        /**
         * The individual.
         */
        private final Genome individual;

        public void run() {
            // Adopt the given individual and apply its layout in the
            // appropriate editor(s).
            applyIndividual(this.individual, this.layoutProviderId);
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
                for (final Genome weightGenome : weightsGenomes) {
                    assert weightGenome != null;

                    Map<String, Double> weightsMap = extractMetricWeights(weightGenome);
                    assert weightsMap != null;
                    normalize(weightsMap);

                    // Get measurements, then get rating proposal from each
                    // weight genome.

                    // TODO Discuss: For more than one editor: rate average
                    // measurements, or use average ratings instead?

                    if (measurements == null) {
                        try {
                            measurements = measure(ind, editor, weightsMap);
                        } catch (Exception exception) {
                            measurements = null;
                            totalRating = 0.0;
                        }

                        ind.setFeatures(measurements);
                        // TODO: don't set features here. This works only for
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

            for (final IGene<?> gene : genome) {
                // presuming gene != null
                String id = (String) gene.getId();

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

            AdoptingRecursiveLayouterEngine engine = new AdoptingRecursiveLayouterEngine();

            IKielerProgressMonitor monitor = new BasicProgressMonitor();

            // calculate layout (might throw an exception)
            KNode layoutResult = engine.calculateLayout(ind, (DiagramEditor) editor, monitor);

            Map<String, Object> measurements = measure(layoutResult, weightsMap);
            // Attention: The measurements may contain additional intermediate
            // results we did not ask for. See #1152.

            // Add the execution speed value.
            double time = monitor.getExecutionTime();

            double speed = normalizedSpeed(time);

            measurements.put(EvolPlugin.EXECUTION_SPEED_VALUE_ID, speed);

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

            // Get the metric IDs.
            Set<String> metricIds = EvolutionServices.getInstance().getLayoutMetricsIds();

            // Get the metrics.
            Set<AbstractInfoAnalysis> metrics =
                    EvolutionServices.getInstance().getLayoutMetrics();

            Map<String, Double> weightsMap = new HashMap<String, Double>(theWeightsMap);

            List<AbstractInfoAnalysis> wantedMetricsList =
                    new ArrayList<AbstractInfoAnalysis>(metricIds.size());
            for (final AbstractInfoAnalysis metric : metrics) {

                String metricId = metric.getID();
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
                    DiagramAnalyzer.analyse(parentNode, wantedMetricsList, analyserOptionsMap,
                            false /* progressBar */);

            for (final String metricId : metricIds) {
                Object analysisResult = analysisResults.get(metricId);
                if (analysisResult instanceof Float) {
                    Float value = (Float) analysisResult;
                    System.out.println("Result: " + metricId + ": " + value);

                    assert ((value >= 0.0) && (value <= 1.0)) : "Analysis result out of range for "
                            + metricId + ": " + value;
                } else {
                    System.err.println("Result: " + metricId + ": " + analysisResult.toString());
                    EvolPlugin.showError("Cannot handle analysis result for " + metricId + ": "
                            + analysisResult.toString(), null);
                }
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
         * @param theWeightsGenomes
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

        public void run() {
            // Must be run in the UI thread.

            Collection<IEditorPart> editors = EvolUtil.getWantedEditors();

            Set<IEditorPart> editorsSet = new HashSet<IEditorPart>(editors);

            autoRateIndividual(editorsSet);
        }

        /**
         * Layouts the given individual in the given editors and calculates
         * automatic ratings for it.
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

        public void run() {
            LayoutViewPart layoutView = LayoutViewPart.findView();
            if (layoutView != null) {
                layoutView.refresh(); // async!
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

        // Get the expected layout provider id.
        String expectedLayoutProviderId = model.getLayoutProviderId();
        assert expectedLayoutProviderId != null;

        // Adopt and layout the current individual.
        syncApplyIndividual(individual, expectedLayoutProviderId);

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
     * @param thePopulation
     *            the {@link Population} to be rated
     * @param theMonitor
     *            a progress monitor; may be {@code null}
     * @param theWeightsGenomes
     *            a population of genomes encoding the metric weights
     */
    public static void autoRate(
            final Population thePopulation, final IProgressMonitor theMonitor,
            final Population theWeightsGenomes) {
        // TODO: move code into a class RatingPopulation extends Population.
        if (thePopulation == null) {
            throw new IllegalArgumentException();
        }

        // Ensure there is a monitor of some sort.
        IProgressMonitor monitor = (theMonitor != null) ? theMonitor : new NullProgressMonitor();

        int size = thePopulation.size();
        int total = size;
        final int scale = 100;

        try {
            monitor.beginTask("Auto-rating individuals.", total * scale);

            // Calculate auto-rating for each individual.
            for (final Genome ind : thePopulation) {
                if (monitor.isCanceled()) {
                    throw new OperationCanceledException();
                }

                Runnable runnable =
                        new IndividualAutoRaterRunnable(ind, theWeightsGenomes);

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
     * @throws KielerException
     *             when the population cannot be created.
     */
    public static Population createPopulation(final Set<IEditorPart> editors)
            throws KielerException {
        if ((editors == null) || editors.isEmpty()) {
            return new Population();
        }

        // Get the layout inspectors of the editors.
        List<ILayoutInspector> inspectors = getLayoutInspectors(editors);
        assert inspectors != null;

        if (inspectors.isEmpty()) {
            return new Population();
        }

        Population result = createPopulation(inspectors);
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
            IEditorPart editor = layoutViewPart.getCurrentEditor();
            if (editor != null) {
                return editor;
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
            ISelection selection =
                    editor.getEditorSite().getSelectionProvider().getSelection();
            Object element = null;
            if (selection != null) {
                if (selection instanceof StructuredSelection) {
                    element = ((StructuredSelection) selection).getFirstElement();
                    if (element instanceof IGraphicalEditPart) {
                        result = (IGraphicalEditPart) element;
                    }
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
     * Returns a layout inspector for the given editor and edit part.
     *
     * @param theEditor
     *            an {@link IEditorPart}
     * @param theEditPart
     *            an {@link EditPart}. If this is {@code null}, the rootPart is
     *            used instead.
     * @return a {@link ILayoutInspector} for the given editor and edit part, or
     *         {@code null} if none can be found.
     */
    public static ILayoutInspector getLayoutInspector(
            final IEditorPart theEditor, final EditPart theEditPart) {

        if (!(theEditor instanceof DiagramEditor)) {
            // We can only handle diagram editors.
            return null;
        }

        EditPart rootPart;
        if (theEditPart == null) {
            rootPart = ((DiagramEditor) theEditor).getDiagramEditPart();
        } else {
            // find root edit part
            rootPart = theEditPart;

            while (rootPart.getParent() != null) {
                rootPart = rootPart.getParent();
            }
        }

        DiagramLayoutManager manager =
                EclipseLayoutServices.getInstance().getManager(theEditor, rootPart);
        if (manager == null) {
            // No layout manager found. We cannot get the layout inspector.
            return null;
        }

        ILayoutInspector inspector = manager.getInspector(rootPart);
        assert inspector != null;

        return inspector;
    }

    /**
     * Finds a layout provider for the given {@link IEditorPart} and
     * {@link EditPart}.
     *
     * @param editor
     *            a {@link IEditorPart}
     * @param editPart
     *            an {@link EditPart}
     * @return the {@link LayoutProviderData} of the layout provider, or
     *         {@code null} if none can be found.
     */
    public static LayoutProviderData getLayoutProviderData(
            final IEditorPart editor, final EditPart editPart) {
        final DiagramLayoutManager manager =
                EclipseLayoutServices.getInstance().getManager(editor, editPart);
        if (manager == null) {
            return null;
        }
        LayoutProviderData result = getLayoutProviderData(manager, editPart);
        return result;
    }

    /**
     * Return a list of the IDs of all layout providers that can handle the
     * specified layout type.
     *
     * @param layoutType
     *            the type id to look for; must not be {@code null}
     * @return a list of layout provider IDs of all layout providers of the
     *         given type; may be empty.
     */
    public static List<String> getLayoutProviderIds(final String layoutType) {
        if (layoutType == null) {
            throw new IllegalArgumentException();
        }

        List<String> result = new LinkedList<String>();

        LayoutServices layoutServices = LayoutServices.getInstance();

        for (final LayoutProviderData data : layoutServices.getLayoutProviderData()) {
            if (data.getType().equalsIgnoreCase(layoutType)) {
                result.add(data.getId());
            }
        }

        return result;
    }


    /**
     * Checks if the given layout provider is of the given type.
     *
     * @param providerId
     *            layout provider ID; must not be {@code null}
     * @param typeId
     *            layout type ID
     * @return {@code true} iff the given layout provider is of the given type
     */
    public static boolean isCompatibleLayoutProvider(final String providerId, final String typeId) {
        if (providerId == null) {
            throw new IllegalArgumentException();
        }

        LayoutServices layoutServices = LayoutServices.getInstance();
        // presuming layoutServices != null
        LayoutProviderData providerData = layoutServices.getLayoutProviderData(providerId);
        // presuming providerData != null
        String newTypeId = providerData.getType();

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
     * @param providerId
     *            the expected layout provider id
     */
    public static void syncApplyIndividual(final Genome individual, final String providerId) {
        MonitoredOperation
                .runInUI(new IndividualApplierRunnable(individual, providerId), true /* synch */);
    }

    /**
     * Creates a population of default size, taking initial values from the
     * given list of {@link ILayoutInspector} instances.
     *
     * @param inspectors
     * @return the new population
     * @throws KielerException
     */
    private static Population createPopulation(final List<ILayoutInspector> inspectors)
            throws KielerException {
        if (inspectors == null) {
            throw new IllegalArgumentException();
        }

        int size =
                EvolPlugin.getDefault().getPreferenceStore()
                        .getInt(EvolPlugin.PREF_POPULATION_SIZE);

        return createPopulation(inspectors, size);
    }

    /**
     * Creates a population of the given size, taking initial values from the
     * given list of {@link ILayoutInspector} instances.
     *
     * @param inspectors
     *            the layout inspectors
     * @param size
     *            desired population size
     * @return the new population
     * @throws KielerException
     */
    private static Population createPopulation(
            final List<ILayoutInspector> inspectors, final int size) throws KielerException {
        if ((inspectors == null) || (size < 0)) {
            throw new IllegalArgumentException();
        }

        Population result = new Population();

        Set<Object> presentLayoutHintIds =
                getPropertyValues(inspectors, LayoutOptions.LAYOUTER_HINT_ID);

        assert !presentLayoutHintIds.isEmpty() : "Layout hint missing.";

        // Create the individuals one by one.
        GenomeFactory genomeFactory = new GenomeFactory(null);

        for (int i = 0; i < size; i++) {
            Genome genome = genomeFactory.createGenome(inspectors, presentLayoutHintIds);
            assert genome != null : "Failed to create genome.";
            result.add(genome);
        }
        return result;
    }

    /**
     * Obtains the layout hint identifier from the given layout inspector.
     *
     * @param inspector
     *            an {@link ILayoutInspector}
     *
     * @return the layout hint id
     */
    private static String getLayoutHintId(final ILayoutInspector inspector) {

        LayoutProviderData hintData = inspector.getFocusLayouterData();

        assert hintData != null : "Could not find layout provider id.";

        String hintId = hintData.getId();
        return hintId;
    }

    /**
     * Collect the layout inspectors of the given editors. The current editor is
     * treated first, if there is any.
     *
     * @param editors
     * @return a list of layout inspectors
     */
    private static List<ILayoutInspector> getLayoutInspectors(final Set<IEditorPart> editors) {
        List<ILayoutInspector> inspectors = new LinkedList<ILayoutInspector>();

        // Handle current editor.
        IEditorPart currentEditor = getCurrentEditor();

        if (currentEditor != null) {
            ILayoutInspector currentInspector = getLayoutInspector(currentEditor, null);
            if (currentInspector != null) {
                currentInspector.initOptions();
                inspectors.add(currentInspector);
            }
        }

        // Collect the layout property sources of the other editors.
        for (final IEditorPart editor : editors) {
            if (editor == currentEditor) {
                // Already handled this one.
                continue;
            }

            if (!(editor instanceof DiagramEditor)) {
                // Editor is not a DiagramEditor. Skip it.
                continue;
            }

            ILayoutInspector inspector = getLayoutInspector(editor, null);

            if (inspector != null) {
                inspectors.add(inspector);
            }
        }

        return inspectors;
    }

    /**
     * Finds a layout provider for the given manager and the given edit part.
     *
     * @param manager
     *            a {@link DiagramLayoutManager}; must not be {@code null}
     * @param editPart
     *            an {@link EditPart}
     * @return the {@link LayoutProviderData} of the layout provider, or
     *         {@code null} if none can be found
     */
    private static LayoutProviderData getLayoutProviderData(
            final DiagramLayoutManager manager, final EditPart editPart) {
        if (manager == null) {
            throw new IllegalArgumentException();
        }

        ILayoutInspector inspector = manager.getInspector(editPart);
        if (inspector == null) {
            return null;
        }
        inspector.initOptions();
        LayoutProviderData data = inspector.getContainerLayouterData();

        return data;
    }

    /**
     * Retrieve the values of the given IDs in from the given layout inspectors.
     * For layout hint, the (non-null) layout hint identifiers are returned.
     *
     * @param inspectors
     *            layout inspectors
     * @param id
     *            an identifier
     * @return the set of values having the specified ID
     */
    private static Set<Object> getPropertyValues(
            final List<ILayoutInspector> inspectors, final String id) {

        Set<Object> result = new LinkedHashSet<Object>();
        LayoutServices layoutServices = LayoutServices.getInstance();
        LayoutOptionData<?> optionData = layoutServices.getLayoutOptionData(id);

        for (final ILayoutInspector inspector : inspectors) {
            Object
                value = inspector.getOption(optionData);
                // value = source.getPropertyValue(id);

            if (LayoutOptions.LAYOUTER_HINT_ID.equals(id)) {
                if (value == null) {
                    // layout hint not found
                    continue;
                }

                if (value instanceof String) {
                    // we can use this
                    result.add(value);
                    continue;
                }

                // Legacy "Layout hint" options have an index as value.
                // But we want the layout hint identifier instead of its
                // index.

                String item = getLayoutHintId(inspector);

                if (item != null) {
                    result.add(item);
                }

            } else {
                // normal option
                result.add(value);
            }
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


    /** Hidden constructor to avoid instantiation. **/
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
     */
    public void saveOptionsToFile(final EvolModel model, final boolean canOverWrite) {
        File file = new File("evol");
        if (!file.exists() || canOverWrite) {
            try {
                FileWriter writer = new FileWriter(file);

                Genome genome = model.getCurrentIndividual();

                String newLine = System.getProperty("line.separator");

                for (final IGene<?> gene : genome) {
                    writer.append(gene.getId() + ";" + gene.getValue() + newLine);
                }

            } catch (final IOException exception) {
                // TODO Auto-generated catch block
                exception.printStackTrace();
            }
        }
    }
}
