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
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import de.cau.cs.kieler.core.alg.BasicProgressMonitor;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.ui.util.MonitoredOperation;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.LayoutOptionData.Type;
import de.cau.cs.kieler.kiml.LayoutProviderData;
import de.cau.cs.kieler.kiml.LayoutServices;
import de.cau.cs.kieler.kiml.evol.genetic.Distribution;
import de.cau.cs.kieler.kiml.evol.genetic.EnumGene;
import de.cau.cs.kieler.kiml.evol.genetic.Genome;
import de.cau.cs.kieler.kiml.evol.genetic.IGene;
import de.cau.cs.kieler.kiml.evol.genetic.IGeneFactory;
import de.cau.cs.kieler.kiml.evol.genetic.IValueFormatter;
import de.cau.cs.kieler.kiml.evol.genetic.MutationInfo;
import de.cau.cs.kieler.kiml.evol.genetic.Population;
import de.cau.cs.kieler.kiml.evol.genetic.TypeInfo;
import de.cau.cs.kieler.kiml.evol.genetic.UniversalGene;
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
     * A factory for genes.
     *
     * @author bdu
     *
     */
    private static class GeneFactory implements IGeneFactory {
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
         * Creates a new {@link GeneFactory}.
         */
        public GeneFactory() {
            // nothing to do here
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

            IGene<?> result = null;

            final IConfigurationElement evolutionData =
                    EvolutionService.getInstance().getEvolutionData((String) theId);
            final String lowerBoundAttr = evolutionData.getAttribute(ATTRIBUTE_LOWER_BOUND);
            final String upperBoundAttr = evolutionData.getAttribute(ATTRIBUTE_UPPER_BOUND);
            final String distrNameAttr = evolutionData.getAttribute(ATTRIBUTE_DISTRIBUTION);
            final String varianceAttr = evolutionData.getAttribute(ATTRIBUTE_VARIANCE);

            final Distribution distr = Distribution.valueOf(distrNameAttr);

            final LayoutOptionData layoutOptionData =
                    LayoutServices.getInstance().getLayoutOptionData((String) theId);
            final Type layoutOptionDataType = layoutOptionData.getType();

            switch (layoutOptionDataType) {
            case BOOLEAN: {
                final boolean booleanValue = (Integer.parseInt(theValue.toString()) == 1);
                final Float floatValue = (booleanValue ? 1.0f : 0.0f);
                result =
                        new UniversalGene(theId, floatValue, UniversalGene.BOOLEAN_TYPE_INFO,
                                new MutationInfo(theMutationProbability, distr));
                System.out.println(theId + ": " + result);
                break;
            }

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
                break;
            }

            case INT: {
                final Integer intValue = Integer.parseInt((String) theValue);

                final double variance;
                if (varianceAttr != null) {
                    variance = Double.parseDouble(varianceAttr);
                } else {
                    // estimate desired variance from the absolute value
                    variance = Math.abs(intValue) * VARIANCE_SCALING_FACTOR;
                }

                final Integer lowerBound =
                        ((lowerBoundAttr == null) ? Integer.MIN_VALUE : (Integer
                                .parseInt(lowerBoundAttr)));

                final Integer upperBound =
                        ((upperBoundAttr == null) ? Integer.MAX_VALUE : (Integer
                                .parseInt(upperBoundAttr)));

                final IValueFormatter formatter = UniversalGene.INTEGER_FORMATTER;

                final TypeInfo<Float> typeInfo =
                        new TypeInfo<Float>(intValue.floatValue(), lowerBound.floatValue(),
                                upperBound.floatValue(), formatter,
                                Integer.class);

                // result = new IntegerGene(theId, intValue,
                // theMutationProbability, var);

                result =
                        new UniversalGene(theId, intValue.floatValue(), typeInfo, new MutationInfo(
                                theMutationProbability, variance, distr));
                System.out.println(theId + ": " + result);
                break;
            }

            case FLOAT: {
                final float floatValue = (Float.parseFloat((String) theValue));
                final float lowerBound =
                        (lowerBoundAttr == null ? Float.NEGATIVE_INFINITY : (Float
                                .parseFloat(lowerBoundAttr)));
                final float upperBound =
                        ((upperBoundAttr == null) ? Float.POSITIVE_INFINITY : (Float
                                .parseFloat(upperBoundAttr)));

                final double variance;
                if (varianceAttr != null) {
                    variance = Double.parseDouble(varianceAttr);

                } else {
                    // threshold to prevent very small variances
                    final float verySmall = 1e-3f;

                    // estimate desired variance from the absolute value
                    if ((Math.abs(floatValue) < verySmall)) {
                        variance = MutationInfo.DEFAULT_VARIANCE;
                    } else {
                        variance = Math.abs(floatValue) * VARIANCE_SCALING_FACTOR;
                    }
                }

                final IValueFormatter formatter;
                if (lowerBound >= 0.0f) {
                    // we need a strictly positive float gene
                    formatter = UniversalGene.STRICTLY_POSITIVE_FLOAT_FORMATTER;

                } else {
                    // we use a general float gene
                    formatter = UniversalGene.FLOAT_FORMATTER;
                }

                final TypeInfo<Float> typeInfo =
                        new TypeInfo<Float>(floatValue, lowerBound, upperBound, formatter,
                                Float.class);
                result =
                        new UniversalGene(theId, floatValue, typeInfo, new MutationInfo(
                                theMutationProbability, variance, distr));
                System.out.println(theId + ": " + result);
                break;
            }

            default:
                break;
            }
            return result;
        }

        public IGene<?> newGene(
                final Object theId, final Object theValue, final TypeInfo<?> theTypeInfo,
                final MutationInfo theMutationInfo) {

            final IGene<?> result =
                    new UniversalGene(theId, (Float) theValue, (TypeInfo<Float>) theTypeInfo,
                            theMutationInfo);
            return result;
        }
    }

    /**
     * Adopts layout options from the given {@link Genome} into the given
     * {@link IEditorPart}.
     *
     * @param theIndividual
     *            the {@link Genome}; must not be {@code null}
     * @param theEditor
     *            the {@link IEditorPart}
     */
    public static void adoptIndividual(final Genome theIndividual, final IEditorPart theEditor) {
        final LayoutPropertySource propertySource = EvolUtil.getLayoutPropertySource(theEditor);
        adoptIndividual(theIndividual, propertySource);
    }

    /**
     * Adopts layout options from the given {@link Genome} into the given
     * {@link LayoutPropertySource}.
     *
     * @param theIndividual
     *            the {@link Genome}; must not be {@code null}
     * @param thePropertySource
     *            the layout property source; must not be {@code null}
     */
    private static void adoptIndividual(
            final Genome theIndividual, final LayoutPropertySource thePropertySource) {
        Assert.isLegal(theIndividual != null);
        Assert.isLegal(thePropertySource != null);

        if ((theIndividual == null) || (thePropertySource == null)) {
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
                    thePropertySource.setPropertyValue(id, ((Boolean) value ? 1 : 0));
                } else {
                    thePropertySource.setPropertyValue(id, Math.round((Float) value));
                }
                break;
            case ENUM:
                try {
                    thePropertySource.setPropertyValue(id, value);
                } catch (final NullPointerException e) {
                    System.out.println("WARNING: enum property could not be set: " + id);
                    Assert.isTrue(false);
                }
                break;
            default:
                thePropertySource.setPropertyValue(id, value.toString());
                break;
            }
        }
    }

    /**
     * Layouts the given individuals in the given editor and calculates
     * automatic ratings for them.
     *
     * @param pop
     *            a {@link Population} (list of individuals)
     * @param editor
     *            Specifies the editor in which the individuals shall be
     *            layouted.
     * @param monitor
     *            a progress monitor. May be {@code null}.
     */
    public static void autoRateIndividuals(
            final Population pop, final IEditorPart editor, final IProgressMonitor monitor) {
        // We don't specify the edit part because we want a manager for
        // the whole diagram.
        final DiagramLayoutManager manager =
                EclipseLayoutServices.getInstance().getManager(editor, null);
        if (manager == null) {
            return;
        }

        /**
         *
         * @author bdu
         *
         */
        class SourceGetter implements Runnable {
            private LayoutPropertySource source;

            public LayoutPropertySource getSource() {
                return this.source;
            }

            public void run() {
                this.source = getLayoutPropertySource();
            }
        }

        final SourceGetter sourceGetterRunnable = new SourceGetter();
        MonitoredOperation.runInUI(sourceGetterRunnable, true);
        final LayoutPropertySource source = sourceGetterRunnable.getSource();

        try {
            Thread.sleep(50);
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }

        // The current diagram gets layouted and measured for each individual.
        for (int pos = 0; pos < pop.size(); pos++) {
            final Genome ind = pop.get(pos);
            // TODO: synchronize on the layout graph?

            adoptIndividual(ind, source);

            // TODO: get a new manager for every iteration?
            final int rating = EvolUtil.layoutAndMeasure(manager, editor);

            // compare new rating to previous one
            if (ind.hasUserRating()) {
                final int oldRating = ind.getUserRating();
                if (oldRating < rating) {
                    System.out.println("Ind. under-rated (" + oldRating + " -> " + rating + ")");
                } else if (oldRating > rating) {
                    System.out.println("Ind. was over-rated (" + oldRating + " -> " + rating + ")");
                }
            }

            ind.setUserRating(rating);

            if (monitor != null) {
                monitor.worked(1);
            }
            try {
                Thread.sleep(50);
            } catch (final InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            Thread.sleep(200);
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }

        // TODO: let the EvolView refresh its tableViewer
        // evolView.getTableViewer.refresh();

        // TODO: set lastEditor for EvolView
        // evolView.setLastEditor(editor);
    }

    /**
     * Creates a population of the default size, taking initial values from the
     * given {@code LayoutPropertySource}.
     *
     * @param propertySource
     *            where the initial data is taken from.
     * @return the new population
     */
    public static Population createPopulation(final LayoutPropertySource propertySource) {
        final int size =
                EvolPlugin.getDefault().getPreferenceStore()
                        .getInt(EvolPlugin.PREF_POPULATION_SIZE);

        return createPopulation(propertySource, size);
    }

    /**
     * Create a population of the given size, taking initial values from the
     * given {@code LayoutPropertySource}.
     *
     * @param propertySource
     *            where the initial data is taken from.
     * @param size
     *            determines the initial size of the population.
     * @return population
     */
    public static Population createPopulation(
            final LayoutPropertySource propertySource, final int size) {
        final Population result = new Population();
        for (int i = 0; i < size; i++) {
            final Genome genome = createGenome(propertySource);
            result.add(genome);
        }
        return result;
    }

    /**
     * Returns the current editor.
     *
     * @return the current editor or {@code null} if none exists.
     */
    public static IEditorPart getCurrentEditor() {
        // TODO: cache editor and assert that it is not replaced?

        // Try to get the editor that is tracked by the layout view.
        final LayoutViewPart layoutViewPart = LayoutViewPart.findView();
        if (layoutViewPart != null) {
            final IEditorPart editor = layoutViewPart.getCurrentEditor();
            if (editor != null) {
                return editor;
            }
        }

        // Try to get the active editor of the workbench.
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
     * Find all the visible graphical editors in the workbench.
     *
     * @return a collection of the visible graphical editors; may be empty.
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
     * Returns the current edit part of the given editor.
     *
     * @param editor
     *            an editor
     * @return the current edit part or {@code null} if none exists.
     */
    public static EditPart getEditPart(final IEditorPart editor) {
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
     *
     * @param editor
     *            an {@link IEditorPart}
     * @return a {@link LayoutPropertySource} for the given editor.
     */
    public static LayoutPropertySource getLayoutPropertySource(final IEditorPart editor) {
        return getLayoutPropertySource(editor, getEditPart(editor));
    }

    /**
     * Finds a layout provider for the given {@link IEditorPart} and
     * {@link EditPart}.
     *
     * @param theEditor
     *            a {@link IEditorPart}
     * @param theEditPart
     *            an {@link EditPart}
     * @return the id of the layouter, or {@code null} if none can be found.
     */
    public static String getLayoutProviderId(final IEditorPart theEditor, final EditPart theEditPart) {
        final DiagramLayoutManager manager =
                EclipseLayoutServices.getInstance().getManager(theEditor, theEditPart);
        final String result = getLayoutProviderId(manager, theEditPart);
        return result;
    }

    /**
     * Finds a layout provider for the given manager and the given edit part.
     *
     * @param manager
     *            a {@link DiagramLayoutManager}
     * @param editPart
     *            an {@link EditPart}
     * @return the id of the layouter, or {@code null} if none can be found.
     */
    private static String getLayoutProviderId(
            final DiagramLayoutManager manager, final EditPart editPart) {
        final ILayoutInspector inspector = manager.getInspector(editPart);
        if (inspector == null) {
            return null;
        }
        inspector.initOptions();
        final LayoutProviderData data = inspector.getContainerLayouterData();
        if (data == null) {
            return null;
        }
        return data.getId();
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
            for (final double d : normalize(1, 1, 1, 1, 1)) {
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
            System.out.println(status.getMessage());
            rating = 0;
        }
        return rating;
    }

    /**
     * Scale the given double values so that their sum equals one.
     *
     * @param values
     *            a non-empty array of doubles
     * @return a list of the scaled values in the same order.
     */
    private static List<Double> normalize(final double... values) {

        double sum = 0.0;
        for (final double val : values) {
            sum += val;
        }
        final double factor = 1.0 / sum;

        final int length = values.length;
        final List<Double> result = new ArrayList<Double>(length);
        for (final double val : values) {
            result.add(val * factor);
        }

        return result;
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
     * @param propertySource
     * @return a genome, or {@code null}.
     */
    private static Genome createGenome(final LayoutPropertySource propertySource) {
        if (propertySource == null) {
            return null;
        }

        // Get the set of learnable elements that are registered.
        final Set<String> learnables = EvolutionService.getInstance().getEvolutionDataIds();
        final Genome result = new Genome();

        // get data from property descriptors
        final IPropertyDescriptor[] propertyDescriptors = propertySource.getPropertyDescriptors();

        // determine uniformly distributed mutation probability
        double uniformProb = 0.0;
        final int learnableCount = countLearnableProperties(Arrays.asList(propertyDescriptors));
        if (learnableCount > 0) {
            uniformProb = 1.0 / learnableCount;
        }
        for (final IPropertyDescriptor p : propertyDescriptors) {

            final String id = (String) p.getId();
            final Object value = propertySource.getPropertyValue(id);
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

    /**
     *
     * @return a {@link LayoutPropertySource} for the current editor.
     */
    private static LayoutPropertySource getLayoutPropertySource() {
        final IEditorPart editor = getCurrentEditor();
        final IGraphicalEditPart part = (IGraphicalEditPart) getEditPart(editor);
        final LayoutPropertySource result = getLayoutPropertySource(editor, part);
        return result;
    }

    /**
     *
     * @param editor
     *            an {@link IEditorPart}
     * @param part
     *            an {@link EditPart}
     * @return a {@link LayoutPropertySource} for the given editor and edit
     *         part.
     */
    private static LayoutPropertySource getLayoutPropertySource(
            final IEditorPart editor, final EditPart part) {

        // use root edit part
        EditPart rootPart = part;

        if (rootPart != null) {
            while (rootPart.getParent() != null) {
                rootPart = rootPart.getParent();
            }
        }

        final DiagramLayoutManager manager =
                EclipseLayoutServices.getInstance().getManager(editor, rootPart);
        if (manager == null) {
            return null;
        }

        final LayoutPropertySource result =
                new LayoutPropertySource(manager.getInspector(rootPart));
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
        double scaledSum = 0;
        for (final AbstractInfoAnalysis metric : metricsList) {
            final String metricResult = results.get(metric.getID()).toString();
            final double coeff = coefficientsMap.get(metric.getID());

            double val;
            try {
                final double parsedVal = Double.parseDouble(metricResult);
                val = parsedVal;

            } catch (final NumberFormatException exception) {
                val = 0.0;
            }

            final double scaled = val * coeff;
            // scaledResults[i] = scaled;
            scaledSum += scaled;
            sum += val;
            System.out.println(metric.getID() + ": " + val + " ");
        }
        System.out.println();
        System.out.println("Difference from uniform scaling: "
                + Math.abs(scaledSum - (sum / metricsList.size())));
        final int newRating = (int) Math.round((scaledSum * 10000));
        return newRating;
    }

    /** Hidden constructor to avoid instantiation. **/
    private EvolUtil() {
        // nothing
    }
}
