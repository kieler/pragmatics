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

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
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
import org.eclipse.ui.statushandlers.StatusManager;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.core.alg.BasicProgressMonitor;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.ui.util.MonitoredOperation;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.LayoutOptionData.Type;
import de.cau.cs.kieler.kiml.LayoutProviderData;
import de.cau.cs.kieler.kiml.LayoutServices;
import de.cau.cs.kieler.kiml.evol.genetic.Genome;
import de.cau.cs.kieler.kiml.evol.genetic.IGene;
import de.cau.cs.kieler.kiml.evol.genetic.Population;
import de.cau.cs.kieler.kiml.grana.AbstractInfoAnalysis;
import de.cau.cs.kieler.kiml.grana.AnalysisServices;
import de.cau.cs.kieler.kiml.grana.ui.DiagramAnalyser;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.ui.layout.DiagramLayoutManager;
import de.cau.cs.kieler.kiml.ui.layout.EclipseLayoutServices;
import de.cau.cs.kieler.kiml.ui.layout.ILayoutInspector;
import de.cau.cs.kieler.kiml.ui.views.LayoutPropertySource;
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
         * Adopt the given individual and calculate layout for it in the
         * appropriate editor(s). The obtained layout is applied to the
         * diagrams.
         *
         * @param individual
         *            a {@link Genome}; may not be {@code null}
         * @param expectedLayoutProviderId
         *            the expected layout provider id
         */
        private static void applyIndividual(
                final Genome individual, final String expectedLayoutProviderId) {
            Assert.isLegal((individual != null) && (expectedLayoutProviderId != null));
            if ((individual == null) || (expectedLayoutProviderId == null)) {
                return;
            }

            final LayoutServices layoutServices = LayoutServices.getInstance();
            Assert.isNotNull(layoutServices);

            // Get the expected layout type id.
            final LayoutProviderData expectedProviderData =
                    layoutServices.getLayoutProviderData(expectedLayoutProviderId);
            Assert.isNotNull(expectedProviderData);
            final String expectedLayoutTypeId = expectedProviderData.getType();
            Assert.isNotNull(expectedLayoutTypeId);

            // Get the appropriate editors.
            final Collection<IEditorPart> editors = EvolUtil.getWantedEditors();
            Assert.isNotNull(editors);

            final IPreferenceStore store = EvolPlugin.getDefault().getPreferenceStore();

            final boolean useDifferentType =
                    store.getBoolean(EvolPlugin.PREF_USE_DIFFERENT_TYPE_LAYOUT_HINT);

            // Iterate the editors and perform layout in each appropriate
            // editor.
            for (final IEditorPart editor : editors) {
                System.out.println();
                System.out.print("--- Editor: " + editor.getTitle() + " ");

                final LayoutProviderData data = getLayoutProviderData(editor);

                // Check if we can handle its type.
                final String layoutTypeId = data.getType();
                if (!expectedLayoutTypeId.equalsIgnoreCase(layoutTypeId) && !useDifferentType) {
                    // The type in the editor is not compatible to the current
                    // population.

                    // We skip this editor.
                    System.err.println("The editor " + editor.getTitle() + " is set to "
                            + layoutTypeId + ". Expecting an editor for " + expectedLayoutTypeId
                            + ".");
                    continue;

                }

                final DiagramLayoutManager manager = adoptAndApplyLayout(individual, editor);
            }
        }

        /**
         * @param editor
         * @return
         */
        private static LayoutProviderData getLayoutProviderData(final IEditorPart editor) {
            final EditPart editPart = getCurrentEditPart(editor);

            // See which layout provider suits for the editor.
            final LayoutProviderData data = EvolUtil.getLayoutProviderData(editor, editPart);

            final String layoutProviderId;
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
         * @param individual
         * @param editor
         * @return
         */
        private static DiagramLayoutManager adoptAndApplyLayout(
                final Genome individual, final IEditorPart editor) {
            // Adopt the layout options that are encoded in the individual.
            EvolUtil.adoptIndividual(individual, editor);

            // We don't specify the edit part because we want a manager for
            // the whole diagram.
            final DiagramLayoutManager manager =
                    EclipseLayoutServices.getInstance().getManager(editor, null);
            Assert.isNotNull(manager);

            final IKielerProgressMonitor monitor = EvolUtil.calculateLayout(manager, editor);

            // Apply the layout to the diagram in the editor.
            manager.applyAnimatedLayout(false /* animate */, false /* cacheLayout */, 0);
            return manager;
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
         * Layouts the given individual in the given editors and calculates
         * automatic ratings for it.
         *
         * @param ind
         *            the {@link Genome} to be rated; must not be {@code null}
         * @param editors
         *            Specifies the editors in which the individual shall be
         *            laid out; must not be {@code null}.
         * @param weightsGenomes
         */
        private static void autoRateIndividual(
                final Genome ind, final Set<IEditorPart> editors, final Population weightsGenomes) {
            Assert.isLegal((ind != null) && (editors != null) && (weightsGenomes != null));
            if ((ind == null) || (editors == null) || (weightsGenomes == null)) {
                return;
            }

            final int rating = calculateAutoRating(ind, editors, weightsGenomes);
            ind.setUserRating(Integer.valueOf(rating));
        }

        /**
         * Lays out the given individual in the given editors and calculates
         * automatic ratings for it. <strong>Note</strong>: The rating is not
         * stored in the individual.
         *
         * @param ind
         *            a {@link Genome}
         * @param editors
         *            set of editors
         * @param weightsGenomes
         *            the weights genomes
         *
         * @return the rating proposal
         */
        private static int calculateAutoRating(
                final Genome ind, final Set<IEditorPart> editors, final Population weightsGenomes) {
            Assert.isLegal((ind != null) && (editors != null) && (weightsGenomes != null));
            if ((ind == null) || (editors == null) || (weightsGenomes == null)) {
                return 0;
            }

            int totalRating = 0;
            final int editorCount = editors.size();

            for (final IEditorPart editor : editors) {
                // We don't specify the edit part because we want a manager for
                // the whole diagram.
                final DiagramLayoutManager manager =
                        EclipseLayoutServices.getInstance().getManager(editor, null);
                Assert.isNotNull(manager);

                final ILayoutInspector inspector = EvolUtil.getLayoutInspector(editor, null);
                Assert.isNotNull(inspector);

                final LayoutPropertySource source = new LayoutPropertySource(inspector);
                Assert.isNotNull(source);

                // TODO: what if weights genomes is empty?
                Assert.isTrue(!weightsGenomes.isEmpty());

                int editorRating = 0;
                Map<String, Object> measurements = null;

                final int weightsGenomesCount = weightsGenomes.size();
                for (final Genome wg : weightsGenomes) {
                    Assert.isNotNull(wg);

                    final Map<String, Double> weightsMap = extractMetricWeights(wg);
                    Assert.isNotNull(weightsMap);
                    normalize(weightsMap);

                    // Get measurements, then get rating proposal from each
                    // weight
                    // genome.

                    // TODO Discuss: For more than one editor: rate average
                    // measurements, or use average ratings instead?

                    if (measurements == null) {
                        measurements = measure(ind, editor, manager, inspector, weightsMap);

                        ind.setFeatures(measurements);
                        // TODO: don't set features here. This works only for
                        // one
                        // editor. For more editors, average measurements must
                        // be
                        // calculated.
                    }

                    final double scaledSum = weight(measurements, weightsMap);
                    final int maxNum = 5000;
                    final int rating = (int) Math.round((scaledSum * maxNum));

                    System.out.println("Rated " + rating + " by " + wg.toString());
                    wg.addFeature("proposedRating:" + ind.getId(), Integer.valueOf(rating));

                    editorRating += rating;
                }

                if (weightsGenomesCount > 1) {
                    final int averageEditorRating =
                            Math.round(editorRating
                                    / Integer.valueOf(weightsGenomesCount).floatValue());
                    totalRating += averageEditorRating;
                } else {
                    totalRating += editorRating;
                }
            }

            if (editorCount > 1) {
                // return average rating of all editors
                final int averageTotalRating =
                        Math.round(totalRating / Integer.valueOf(editorCount).floatValue());
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
            // TODO: Discuss whether this method should be moved into Genome.
            final EvolutionServices evolService = EvolutionServices.getInstance();
            Assert.isNotNull(evolService);

            final Set<String> metricIds = evolService.getLayoutMetricsIds();
            Assert.isNotNull(metricIds);

            final Map<String, Double> result = new HashMap<String, Double>(metricIds.size());

            for (final IGene<?> gene : genome) {
                Assert.isNotNull(gene);
                final String id = (String) gene.getId();

                if (!metricIds.contains(id)) {
                    // not a metric id --> skip
                    continue;
                }

                final Float value = (Float) gene.getValue();
                result.put(id, Double.valueOf(value.doubleValue()));
            }

            return result;
        }

        /**
         * Adopts the given individual, calculates a layout for it in the given
         * editor and measures its features, using the given manager and layout
         * inspector.
         *
         * @param ind
         *            a {@link Genome}; may not be {@code null}
         * @param editor
         *            an {@link IEditorPart}
         * @param manager
         *            a {@link DiagramLayoutManager}
         * @param inspector
         *            a {@link ILayoutInspector}; may not be {@code null}
         * @param weightsMap
         *            a map that associates weights to metric IDs; must not be
         *            {@code null}. This map indicates which features shall be
         *            measured.
         * @return the measured features
         */
        private static Map<String, Object> measure(
                final Genome ind, final IEditorPart editor, final DiagramLayoutManager manager,
                final ILayoutInspector inspector, final Map<String, Double> weightsMap) {
            Assert.isLegal((ind != null) && (inspector != null) && (weightsMap != null));
            if ((ind == null) || (inspector == null) || (weightsMap == null)) {
                return null;
            }

            // Transfer layout options from the individual to the layout
            // inspector.
            EvolUtil.adoptIndividual(ind, inspector);

            final IKielerProgressMonitor monitor = EvolUtil.calculateLayout(manager, editor);

            final KNode layoutGraph = manager.getLayoutGraph();

            final Map<String, Object> measurements = measure(layoutGraph, weightsMap);
            // Attention: The measurements may contain additional intermediate
            // results we did not ask for. See #1152.

            // add the execution speed
            final double time = monitor.getExecutionTime();

            final double speed = normalizedSpeed(time);

            measurements.put("executionSpeed", Double.valueOf(speed));

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
            Assert.isLegal(theWeightsMap != null);

            if ((parentNode == null) || (theWeightsMap == null)) {
                return Collections.emptyMap();
            }

            // Get the metric IDs.
            final Set<String> metricIds = EvolutionServices.getInstance().getLayoutMetricsIds();

            final Map<String, Double> weightsMap = new HashMap<String, Double>(theWeightsMap);

            // We have the metric IDs, now get the metrics.
            final AnalysisServices as = AnalysisServices.getInstance();
            final List<AbstractInfoAnalysis> metricsList =
                    new ArrayList<AbstractInfoAnalysis>(metricIds.size());
            for (final String metricId : metricIds) {
                final AbstractInfoAnalysis metric = as.getAnalysisById(metricId);
                Assert.isNotNull(metric, "Could not find analysis: " + metricId);

                if (!weightsMap.containsKey(metricId)) {
                    // Skip this analysis.
                    continue;
                }
                final double coeff = weightsMap.get(metricId).doubleValue();

                if (coeff == 0.0) {
                    // Skip this analysis.
                    continue;
                }

                metricsList.add(metric);
            }

            // Do we have anything to measure?
            if (metricsList.isEmpty()) {
                return Collections.emptyMap();
            }

            // Perform the measurement.
            final boolean showProgressBar = false;
            final Map<String, Object> analysisResults =
                    DiagramAnalyser.analyse(parentNode, metricsList, showProgressBar);

            for (final String metricId : metricIds) {
                final Object analysisResult = analysisResults.get(metricId);
                if (analysisResult instanceof Float) {
                    final Float value = (Float) analysisResult;
                    System.out.println("Result: " + metricId + ": " + value);

                    Assert.isTrue((value.doubleValue() >= 0.0) && (value.doubleValue() <= 1.0));
                } else {
                    System.err.println("Result: " + metricId + ": " + analysisResult.toString());
                }
            }

            return analysisResults;
        }

        /**
         * Returns a normalized speed value for the given time. The result is
         * between {@code +0.0} and {@code +1.0}. It is {@code +0.0} if
         * {@code time} is positive infinity and {@code 1.0} if {@code time} is
         * zero.
         *
         * @param time
         *            time; must be positive
         * @return normed speed value
         */
        private static double normalizedSpeed(final double time) {
            Assert.isLegal(time >= 0.0, "The value of 'time' must be positive:" + time);
            return (Math.exp(-time));
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
            Assert.isLegal(map != null);

            if (map == null) {
                return;
            }

            // Calculate sum.
            double sum = 0.0;
            for (final Double value : map.values()) {
                sum += value.doubleValue();
            }

            if (sum != 1.0) {
                // Need to scale values.
                final double factor = 1.0 / sum;

                for (final Entry<String, Double> entry : map.entrySet()) {
                    final double value = entry.getValue().doubleValue();
                    entry.setValue(Double.valueOf(value * factor));
                }
            }
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

            // TODO: Discuss: How should metrics be weighted per default?
            // TODO: What if weightsMap is empty?
            // Attention: The measurements can contain additional intermediate
            // results we did not ask for.
            final Double defaultScale = Double.valueOf(0.0);

            for (final String metricId : measurements.keySet()) {
                if (!weightsMap.containsKey(metricId)) {
                    // add additional metrics
                    weightsMap.put(metricId, defaultScale);
                }
            }

            double scaledSum = 0;

            // scale results
            for (final Entry<String, Object> measurement : measurements.entrySet()) {
                final String metricId = measurement.getKey();
                final String metricResult = measurement.getValue().toString();

                // Get the weight.
                Assert.isTrue(weightsMap.containsKey(metricId));
                final double coeff = weightsMap.get(metricId).doubleValue();

                double val;
                try {
                    final double parsedResult = Double.parseDouble(metricResult);
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

        /**
        *
        */
        private final Genome individual;

        private final Population weightsGenomes;

        public void run() {
            // Must be run in the UI thread.

            final Collection<IEditorPart> editors = EvolUtil.getWantedEditors();

            final Set<IEditorPart> editorsSet = new HashSet<IEditorPart>(editors);

            // TODO: remove superfluous parameters
            autoRateIndividual(this.individual, editorsSet, this.weightsGenomes);
        }

    }

    /**
     * Refresher for the layout view.
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
            final LayoutViewPart layoutView = LayoutViewPart.findView();
            if (layoutView != null) {
                layoutView.refresh(); // async!
            }
        }
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
        Assert.isLegal((thePopulation != null));
        if (thePopulation == null) {
            return;
        }

        // Ensure there is a monitor of some sort.
        final IProgressMonitor monitor;
        monitor = ((theMonitor != null) ? theMonitor : new NullProgressMonitor());

        final int size = thePopulation.size();
        final int total = size;
        final int scale = 100;

        try {
            monitor.beginTask("Auto-rating individuals.", total * scale);

            // Calculate auto-rating for each individual.
            for (final Genome ind : thePopulation) {
                if (monitor.isCanceled()) {
                    throw new OperationCanceledException();
                }

                // Synchronously auto-rate the individual.
                MonitoredOperation.runInUI(
                        new IndividualAutoRaterRunnable(ind, theWeightsGenomes), true);

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
        final List<ILayoutInspector> inspectors = getLayoutInspectors(editors);
        Assert.isNotNull(inspectors);

        if (inspectors.isEmpty()) {
            return new Population();
        }

        final Population result = createPopulation(inspectors);
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
        final LayoutViewPart layoutViewPart = LayoutViewPart.findView();
        if (layoutViewPart != null) {
            final IEditorPart editor = layoutViewPart.getCurrentEditor();
            if (editor != null) {
                return editor;
            }
        }

        // Try to get the active editor of the workbench (must be in UI thread).
        final IWorkbench workbench = PlatformUI.getWorkbench();
        final IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
        if (window == null) {
            return null;
        }
        final IWorkbenchPage page = window.getActivePage();
        if (page == null) {
            return null;
        }
        final IEditorPart editor = page.getActiveEditor();
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
            final ISelection selection =
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

        final Set<IEditorPart> result = new HashSet<IEditorPart>();

        // Try to get the editors of the workbench.
        final IWorkbench workbench = PlatformUI.getWorkbench();
        final IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
        if (window == null) {
            return result;
        }

        final IWorkbenchPage[] pages = window.getPages();
        for (final IWorkbenchPage page : pages) {
            if (page.isEditorAreaVisible()) {
                final IEditorReference[] references = page.getEditorReferences();
                Assert.isNotNull(references);

                for (final IEditorReference ref : references) {
                    final IEditorPart editor = ref.getEditor(false);
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
            // TODO: Discuss: Is it OK to call getRoot() in this case?
            rootPart = ((DiagramEditor) theEditor).getDiagramEditPart();
        } else {
            // find root edit part
            rootPart = theEditPart;

            while (rootPart.getParent() != null) {
                rootPart = rootPart.getParent();
            }
        }

        final DiagramLayoutManager manager =
                EclipseLayoutServices.getInstance().getManager(theEditor, rootPart);
        if (manager == null) {
            // No layout manager found. We cannot get the layout inspector.
            return null;
        }

        final ILayoutInspector inspector = manager.getInspector(rootPart);
        Assert.isNotNull(inspector);

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
        final LayoutProviderData result = getLayoutProviderData(manager, editPart);
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
        Assert.isLegal(layoutType != null);

        final List<String> result = new LinkedList<String>();

        final LayoutServices layoutServices = LayoutServices.getInstance();

        for (final LayoutProviderData data : layoutServices.getLayoutProviderData()) {
            if (data.getType().equalsIgnoreCase(layoutType)) {
                result.add(data.getId());
            }
        }

        return result;
    }

    /**
     * @param providerId
     *            layout provider ID
     * @param typeId
     *            layout type ID
     * @return {@code true} iff the given layout provider is of the given type
     */
    public static boolean isCompatibleLayoutProvider(final String providerId, final String typeId) {

        final LayoutServices layoutServices = LayoutServices.getInstance();

        final String newTypeId = layoutServices.getLayoutProviderData(providerId).getType();

        final boolean result = typeId.equalsIgnoreCase(newTypeId);

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
        MonitoredOperation.runInUI(new IndividualApplierRunnable(individual, providerId), true);
    }

    /**
     * Adopts layout options from the given {@link Genome} into the given
     * {@link IEditorPart}.
     *
     * @param individual
     *            the {@link Genome}; must not be {@code null}
     * @param editor
     *            the {@link IEditorPart}
     */
    private static void adoptIndividual(final Genome individual, final IEditorPart editor) {
        Assert.isLegal(individual != null);

        final ILayoutInspector inspector = getLayoutInspector(editor, null);
        Assert.isNotNull(inspector);

        // final LayoutPropertySource propertySource = new
        // LayoutPropertySource(inspector);
        // Assert.isNotNull(propertySource);
        adoptIndividual(individual, inspector);
    }

    /**
     * Adopts layout options from the given {@link Genome} into the given
     * {@link ILayoutInspector}.
     *
     * @param individual
     *            the {@link Genome}; must not be {@code null}
     * @param inspector
     *            the {@link ILayoutInspector}; must not be {@code null}
     */
    private static void adoptIndividual(final Genome individual, final ILayoutInspector inspector) {
        Assert.isLegal((individual != null) && (inspector != null));
        if ((individual == null) || (inspector == null)) {
            return;
        }

        EvolPlugin.logStatus("Adopting " + individual.toString());
        final LayoutServices layoutServices = LayoutServices.getInstance();
        // final EvolutionServices evolServices =
        // EvolutionServices.getInstance();
        // final Set<String> metricIds = evolServices.getLayoutMetricsIds();

        // Set layout options according to the genome.
        for (final IGene<?> gene : individual) {
            Assert.isNotNull(gene);

            final Object value = gene.getValue();
            final Object id = gene.getId();

            final LayoutOptionData<?> data = layoutServices.getLayoutOptionData((String) id);
            Assert.isNotNull(data, "No layout option data for " + id);

            final Type layoutOptionType = data.getType();

            switch (layoutOptionType) {

            case BOOLEAN:
                if (value instanceof Boolean) {
                    inspector.setOption(data,
                            Integer.valueOf((((Boolean) value).booleanValue() ? 1 : 0)));
                    // inspector.setPropertyValue(id,
                    // Integer.valueOf((((Boolean) value).booleanValue() ? 1 :
                    // 0)));
                } else {
                    inspector.setOption(data,
                            Integer.valueOf(Math.round(((Float) value).floatValue())));
                }
                break;

            case ENUM:
                try {
                    inspector.setOption(data, value);
                } catch (final NullPointerException e) {
                    EvolPlugin.showError("WARNING: enum property could not be set: " + id, e);
                    Assert.isTrue(false);
                }
                break;

            case INT:
                if (value instanceof Integer) {
                    inspector.setOption(data, value);
                } else {
                    inspector.setOption(data, gene.toString());
                }
                break;

            case STRING:
                if (LayoutOptions.LAYOUTER_HINT_ID.equalsIgnoreCase((String) id)) {
                    // Cannot use the int value of the gene because it is the
                    // index for the internal list in the gene, not for the
                    // layout hint array in the property source which we would
                    // need.

                    // Are we allowed to set the layout hint?
                    final boolean canSetLayoutHint =
                            EvolPlugin.getDefault().getPreferenceStore()
                                    .getBoolean(EvolPlugin.PREF_USE_LAYOUT_HINT_FROM_GENOME);

                    // Even for different types?
                    final boolean canSetForDifferentType =
                            EvolPlugin.getDefault().getPreferenceStore()
                                    .getBoolean(EvolPlugin.PREF_USE_DIFFERENT_TYPE_LAYOUT_HINT);

                    if (!canSetLayoutHint) {
                        // We have nothing to do, for we are not allowed to set
                        // the layout hint at all.
                        break;
                    }

                    final String newLayoutHintId = gene.toString();

                    final Set<Object> oldLayoutHintIds =
                            getPropertyValues(Collections.singletonList(inspector),
                                    LayoutOptions.LAYOUTER_HINT_ID);

                    Assert.isTrue(!oldLayoutHintIds.isEmpty());
                    final String oldLayoutHintId = (String) oldLayoutHintIds.iterator().next();

                    final LayoutProviderData providerData =
                            layoutServices.getLayoutProviderData(newLayoutHintId, null);

                    final String newType = providerData.getType();

                    if ((!canSetForDifferentType && !isCompatibleLayoutProvider(oldLayoutHintId,
                            newType))) {
                        // we are not allowed do this
                        System.err
                                .println("Attempt to set the layout hint to incompatible type: "
                                        + newLayoutHintId);
                        break;
                    }

                    inspector.setOption(data, newLayoutHintId);

                } else {
                    // a normal string option
                    inspector.setOption(data, value.toString());
                }
                break;

            default:
                inspector.setOption(data, value.toString());
                break;
            }
        }
    }

    /**
     * Builds the layout graph for the given editor, using the given manager,
     * and performs layout on it. <strong>NOTE</strong>: The resulting layout is
     * not applied to the diagram. It can be obtained from the {@code manager}.
     *
     * @param manager
     *            a {@link DiagramLayoutManager}
     * @param editor
     *            an {@link IEditorPart}
     * @return the progress monitor, or {@code null} in case of an error.
     */
    private static IKielerProgressMonitor calculateLayout(
            final DiagramLayoutManager manager, final IEditorPart editor) {
        if ((editor == null) || (manager == null)) {
            // We cannot perform the layout.
            return null;
        }

        EvolPlugin.logStatus("Calculating layout in editor: '" + editor.getTitle() + "'");

        // First phase: build the layout graph.
        final KNode layoutGraph = manager.buildLayoutGraph(editor, null, false);

        // Second phase: execute layout algorithms.
        // We need a new monitor each time because the old one
        // gets closed.
        final IKielerProgressMonitor monitor =
                new BasicProgressMonitor(DiagramLayoutManager.MAX_PROGRESS_LEVELS);

        final IStatus status = manager.layout(monitor, false /* layoutAncestors */);

        if (!status.isOK()) {
            // Something went wrong. Report the status.
            StatusManager.getManager().handle(status, StatusManager.SHOW);
            return null;
        }

        final KNode layoutGraphAfterLayout = manager.getLayoutGraph();
        Assert.isTrue(layoutGraph == layoutGraphAfterLayout);

        return monitor;
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
        Assert.isLegal(inspectors != null);
        final int size =
                EvolPlugin.getDefault().getPreferenceStore()
                        .getInt(EvolPlugin.PREF_POPULATION_SIZE);
        return createPopulation(inspectors, size);
    }

    /**
     * Creates a population of the given size, taking initial values from the
     * given list of {@link ILayoutInspector} instances.
     *
     * @param inspectors
     * @param size
     * @return the new population
     * @throws KielerException
     */
    private static Population createPopulation(
            final List<ILayoutInspector> inspectors, final int size) throws KielerException {
        Assert.isLegal(inspectors != null);
        Assert.isLegal(size >= 0);

        final Population result = new Population();

        final Set<Object> presentLayoutHintIds =
                getPropertyValues(inspectors, LayoutOptions.LAYOUTER_HINT_ID);

        // Create the individuals one by one.
        final GenomeFactory genomeFactory = new GenomeFactory(null);

        for (int i = 0; i < size; i++) {
            final Genome genome = genomeFactory.createGenome(inspectors, presentLayoutHintIds);
            result.add(genome);
        }
        return result;
    }

    /**
     * Obtains the layout hint identifier from the given layout inspector.
     *
     * @param inspector
     *            an {@link ILayoutInspector}
     * @param value
     *            the integer value indicating the layout hint
     * @return the layout hint id
     */
    private static String getLayoutHintId(final ILayoutInspector inspector, final Object value) {
        // final LayoutPropertySource source = new
        // LayoutPropertySource(inspector);

        // final ILabelProvider labelProvider = ((IPropertyDescriptor)
        // source).getLabelProvider();

        final LayoutOptionData<?> data =
                LayoutServices.getInstance().getLayoutOptionData(LayoutOptions.LAYOUTER_HINT_ID);

        final String hintId = (String) inspector.getOption(data) + "";

        // Assert.isNotNull(labelProvider,
        // "Could not obtain label provider for " + inspector.toString());

        // String text;

        // Get the caption.
        // text = labelProvider.getText(value);

        // hintId = LayoutPropertySource.getLayoutHint(text);

        Assert.isTrue(hintId.length() > 0, "Could not find layout provider id.");

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
        final List<ILayoutInspector> inspectors = new LinkedList<ILayoutInspector>();

        // Handle current editor.
        final IEditorPart currentEditor = getCurrentEditor();

        if (currentEditor != null) {
            final ILayoutInspector currentInspector = getLayoutInspector(currentEditor, null);
            if (currentInspector != null) {
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

            final ILayoutInspector inspector = getLayoutInspector(editor, null);

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
        Assert.isLegal(manager != null);
        if (manager == null) {
            return null;
        }
        final ILayoutInspector inspector = manager.getInspector(editPart);
        if (inspector == null) {
            return null;
        }
        inspector.initOptions();
        final LayoutProviderData data = inspector.getContainerLayouterData();
        if (data == null) {
            return null;
        }
        return data;
    }

    /**
     * Retrieve the values of the given IDs in from the given layout inspectors.
     * For layout hint, the layout hint identifiers are returned.
     *
     * @param inspectors
     * @param id
     * @return a set of values
     */
    private static Set<Object> getPropertyValues(
            final List<ILayoutInspector> inspectors, final String id) {

        final Set<Object> result = new LinkedHashSet<Object>();

        for (final ILayoutInspector inspector : inspectors) {
            // TODO: get the values via inspector#getOption(), not via
            // LayoutPropertySource
            final LayoutPropertySource source = new LayoutPropertySource(inspector);
            Object value;
            try {
                value = source.getPropertyValue(id);
            } catch (final NullPointerException exception) {
                // getPropertyValue has a problem
                value = null;
            }

            if (LayoutOptions.LAYOUTER_HINT_ID.equals(id)) {
                // "Layout hint" options have an index as value.
                // But we want the layout hint identifier instead of its
                // index.

                if (value == null) {
                    // layout hint not found
                    continue;
                }

                if (value instanceof String) {
                    // we can use this
                    result.add(value);
                    continue;
                }

                final String item = getLayoutHintId(inspector, value);

                if (item != null) {
                    result.add(item);
                }

            } else {
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
        final IEditorPart currentEditor = getCurrentEditor();

        final String prefEditors =
                EvolPlugin.getDefault().getPreferenceStore().getString(EvolPlugin.PREF_EDITORS);

        final boolean wantAllEditors = EvolPlugin.ALL_EDITORS.equalsIgnoreCase(prefEditors);

        // Collect the editor(s).
        final Set<IEditorPart> editors;
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
}
