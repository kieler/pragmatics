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
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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

            IGene<?> result;

            final IConfigurationElement evolutionData =
                    EvolutionServices.getInstance().getEvolutionData((String) theId);
            // FIXME: evolutionData might be null
            Assert.isNotNull(evolutionData);

            final String lowerBoundAttr = evolutionData.getAttribute(ATTRIBUTE_LOWER_BOUND);
            final String upperBoundAttr = evolutionData.getAttribute(ATTRIBUTE_UPPER_BOUND);
            final String distrNameAttr = evolutionData.getAttribute(ATTRIBUTE_DISTRIBUTION);
            final String varianceAttr = evolutionData.getAttribute(ATTRIBUTE_VARIANCE);

            final Distribution distr = Distribution.valueOf(distrNameAttr);
            // FIXME: distrNameAttr might be wrong

            final LayoutOptionData layoutOptionData =
                    LayoutServices.getInstance().getLayoutOptionData((String) theId);
            // FIXME: layoutOptionData might be null
            Assert.isNotNull(layoutOptionData);

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

        public IGene<?> newGene(
                final Object theId, final Object theValue, final TypeInfo<?> theTypeInfo,
                final MutationInfo theMutationInfo) {
            Assert.isLegal(theTypeInfo instanceof FloatTypeInfo);

            if (theTypeInfo instanceof FloatTypeInfo) {
                return new UniversalGene(theId, (Float) theValue, (FloatTypeInfo) theTypeInfo,
                        theMutationInfo);
            }

            return null;
        }

        /**
         *
         * @param theId
         * @param theValue
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
            System.out.println(theId + ": " + result);
            return result;
        }

        /**
         * @param theId
         * @param theRawValue
         * @param theMutationProbability
         * @param layoutOptionData
         * @return
         */
        private IGene<?> newEnumGene(
                final Object theId, final Object theRawValue,
                final double theMutationProbability, final LayoutOptionData layoutOptionData) {
            IGene<?> result;
            final int choicesCount = layoutOptionData.getChoices().length;
            final Class<? extends Enum<?>> enumClass = LayoutOptions.getEnumClass((String) theId);
            Assert.isNotNull(enumClass);
            Assert.isTrue(enumClass.getEnumConstants().length == choicesCount);
            final Integer value = Integer.valueOf(theRawValue.toString());
            result = new EnumGene(theId, value.intValue(), enumClass, theMutationProbability);
            System.out.println("Enum " + enumClass.getSimpleName() + "(" + choicesCount + "): "
                    + value);
            return result;
        }

        /**
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
            final Float value = Float.valueOf((String) theRawValue);
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
                // we need a strictly positive float gene
                formatter = UniversalGene.STRICTLY_POSITIVE_FLOAT_FORMATTER;

            } else {
                // we use a general float gene
                formatter = UniversalGene.FLOAT_FORMATTER;
            }

            final TypeInfo<Float> typeInfo =
                    new FloatTypeInfo(value, lowerBound, upperBound, formatter, Float.class);

            final MutationInfo mutationInfo =
                    new MutationInfo(theMutationProbability, variance, distr);
            result = new UniversalGene(theId, value, typeInfo, mutationInfo);
            System.out.println(theId + ": " + result);
            return result;
        }

        /**
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
            final Integer value = Integer.valueOf((String) theRawValue);

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

            final TypeInfo<Float> typeInfo =
                    new FloatTypeInfo(Float.valueOf(value.floatValue()), Float.valueOf(lowerBound
                            .floatValue()), Float.valueOf(upperBound.floatValue()), formatter,
                            Integer.class);

            final MutationInfo mutationInfo =
                    new MutationInfo(theMutationProbability, variance, distr);
            result =
                    new UniversalGene(theId, Float.valueOf(value.floatValue()), typeInfo,
                            mutationInfo);
            System.out.println(theId + ": " + result);
            return result;
        }
    }

    /**
     * Adopt, layout and measure the given individual.
     *
     * @param individual
     *            a {@link Genome}
     * @param expectedLayoutProviderId
     *            the expected layout provider id
     *
     */
    public static void applyIndividual(
            final Genome individual, final String expectedLayoutProviderId) {

        // Get the current editor (may be null).
        final IEditorPart currentEditor = getCurrentEditor();

        final String prefEditors =
                EvolPlugin.getDefault().getPreferenceStore().getString(EvolPlugin.PREF_EDITORS);

        final boolean wantAllEditors = EvolPlugin.ALL_EDITORS.equalsIgnoreCase(prefEditors);

        // Collect the editor(s).
        final Collection<IEditorPart> editors;
        if (wantAllEditors) {
            editors = getEditors();
        } else {
            editors = new ArrayList<IEditorPart>(1);
            if (currentEditor != null) {
                editors.add(currentEditor);
            }
        }

        // Do the layout in each selected editor.
        for (final IEditorPart editor : editors) {
            System.out.println();
            System.out.println("Editor: " + editor.getTitle());

            final EditPart editPart = getEditPart(editor);

            // See which layout provider suits for the editor.
            final String layoutProviderId = getLayoutProviderId(editor, editPart);

            if (!expectedLayoutProviderId.equalsIgnoreCase(layoutProviderId)) {
                // The editor is not compatible to the current population.
                // --> skip it
                System.out.println("Cannot adopt " + individual.getId() + " to "
                        + layoutProviderId);
                continue;
            }

            // Use the options that are encoded in the individual.
            adoptIndividual(individual, editor);

            // We don't specify the edit part because we want a manager for
            // the whole diagram.
            final DiagramLayoutManager manager =
                    EclipseLayoutServices.getInstance().getManager(editor, null);
            Assert.isNotNull(manager);

            final Map<String, Double> coeffsMap = extractMetricWeights(individual);
            final int rating = layoutAndMeasure(manager, editor, coeffsMap);

            // Update the rating.
            if ((editor == currentEditor) && !individual.hasUserRating()) {
                individual.setUserRating(rating);
            }

            // Apply the layout to the diagram.
            // XXX it would be more straightforward to call
            // manager.applyLayout()
            // directly, but that method is private

            final boolean showAnimation = false;
            final boolean showProgressBar = false;
            EclipseLayoutServices.getInstance().layout(editor, null, showAnimation,
                    showProgressBar);
        }
    }

    /**
     * Layouts the given individuals in the given editor and calculates
     * automatic ratings for them. This must be run in the UI thread.
     *
     * @param population
     *            the {@link Population} (list of individuals) to be rated
     * @param editor
     *            Specifies the editor in which the individuals shall be
     *            layouted.
     */
    public static void autoRatePopulation(final Population population, final IEditorPart editor) {
        // We don't specify the edit part because we want a manager for
        // the whole diagram.
        final DiagramLayoutManager manager =
                EclipseLayoutServices.getInstance().getManager(editor, null);
        Assert.isNotNull(manager);

        final LayoutPropertySource source = getLayoutPropertySource();
        Assert.isNotNull(source);

        // The current diagram gets layouted and measured for each individual.
        for (int pos = 0; pos < population.size(); pos++) {
            final Genome ind = population.get(pos);
            // TODO: synchronize on the layout graph?

            autoRateIndividual(ind, editor, null, manager, source);
        }
    }

    /**
     * Layouts the given individual in the given editor and calculates automatic
     * ratings for them. This must be run in the UI thread.
     *
     * @param ind
     *            the {@link Genome} to be rated
     * @param editor
     *            Specifies the editor in which the individual shall be
     *            layouted.
     * @param monitor
     *            a progress monitor. May be {@code null}.
     */
    public static void autoRateIndividual(
            final Genome ind, final IEditorPart editor, final IProgressMonitor monitor) {

        // We don't specify the edit part because we want a manager for
        // the whole diagram.
        final DiagramLayoutManager manager =
                EclipseLayoutServices.getInstance().getManager(editor, null);
        Assert.isNotNull(manager);

        final LayoutPropertySource source = getLayoutPropertySource();
        Assert.isNotNull(source);

        autoRateIndividual(ind, editor, monitor, manager, source);
    }

    /**
     * @param ind
     * @param editor
     * @param monitor
     * @param manager
     * @param source
     */
    private static void autoRateIndividual(
            final Genome ind, final IEditorPart editor, final IProgressMonitor monitor,
            final DiagramLayoutManager manager, final LayoutPropertySource source) {
        adoptIndividual(ind, source);

        final Map<String, Double> weightsMap = extractMetricWeights(ind);
        final int rating = layoutAndMeasure(manager, editor, weightsMap);

        ind.setUserRating(rating);

        if (monitor != null) {
            try {
                final int delay = 200;
                Thread.sleep(delay);
            } catch (final InterruptedException exception) {
                exception.printStackTrace();
            }
            monitor.worked(1);
        }
    }

    /**
     * Creates a population of the default size, taking initial values from the
     * given diagram editor and edit part.
     *
     * @param editor
     *            an {@link IEditorPart}
     * @param part
     *            an {@link EditPart}
     * @return a new {@link Population} of default size, or an empty
     *         {@link Population} in case of an error
     */
    public static Population createPopulation(final IEditorPart editor, final EditPart part) {
        if ((editor == null) || !(editor instanceof DiagramEditor)) {
            return new Population();
        }

        final DiagramLayoutManager manager =
                EclipseLayoutServices.getInstance().getManager(editor, part);

        if (manager == null) {
            return new Population();
        }

        final ILayoutInspector inspector = manager.getInspector(part);
        Assert.isNotNull(inspector);
        final LayoutPropertySource propertySource = new LayoutPropertySource(inspector);
        final Population result = createPopulation(propertySource);

        return result;
    }

    /**
     * Returns the current editor.
     *
     * @return the current editor or {@code null} if none exists.
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
    public static String getLayoutProviderId(
            final IEditorPart theEditor, final EditPart theEditPart) {
        final DiagramLayoutManager manager =
                EclipseLayoutServices.getInstance().getManager(theEditor, theEditPart);
        if (manager == null) {
            return null;
        }
        final String result = getLayoutProviderId(manager, theEditPart);
        return result;
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
    private static void adoptIndividual(final Genome theIndividual, final IEditorPart theEditor) {
        Assert.isLegal(theIndividual != null);

        final LayoutPropertySource propertySource = getLayoutPropertySource(theEditor);
        Assert.isNotNull(propertySource);
        adoptIndividual(theIndividual, propertySource);
    }

    /**
     * Adopts layout options from the given {@link Genome} into the given
     * {@link LayoutPropertySource}.
     *
     * @param theIndividual
     *            the {@link Genome}; must not be {@code null}
     * @param thePropertySource
     *            the {@link LayoutPropertySource}; must not be {@code null}
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
        final EvolutionServices evolService = EvolutionServices.getInstance();
        final Set<String> metricIds = evolService.getLayoutMetricsIds();

        // set layout options according to genome
        for (final IGene<?> gene : theIndividual) {

            Assert.isNotNull(gene);
            final Object value = gene.getValue();
            final Object id = gene.getId();

            if (metricIds.contains(id)) {
                // it is a metric id --> skip
                continue;
            }

            final LayoutOptionData data = layoutServices.getLayoutOptionData((String) id);
            Assert.isNotNull(data, "No layout option data for " + id);

            switch (data.getType()) {
            case BOOLEAN:
                if (value instanceof Boolean) {
                    thePropertySource.setPropertyValue(id,
                            Integer.valueOf((((Boolean) value).booleanValue() ? 1 : 0)));
                } else {
                    thePropertySource.setPropertyValue(id,
                            Integer.valueOf(Math.round(((Float) value).floatValue())));
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
            case INT:
                if (value instanceof Integer) {
                    thePropertySource.setPropertyValue(id, value);
                } else {
                    thePropertySource.setPropertyValue(id, gene.toString());
                }
                break;
            default:
                thePropertySource.setPropertyValue(id, value.toString());
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
    private static int countLearnableProperties(
            final List<IPropertyDescriptor> propertyDescriptors) {
        int result = 0;
        final Set<String> learnables = EvolutionServices.getInstance().getEvolutionDataIds();
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
     * Create a {@link Genome} from the given source. Meta-evolution genes are
     * appended for the given metric ids.
     *
     * @param propertySource
     *            a {@link LayoutPropertySource}
     * @param metricIds
     *            a set of layout metric ids; may not be {@code null}.
     * @return a genome, or {@code null}.
     */
    private static Genome createGenome(
            final LayoutPropertySource propertySource, final Set<String> metricIds) {
        Assert.isLegal(metricIds != null);
        if ((propertySource == null) || (metricIds == null)) {
            return null;
        }

        // Get the set of all learnable elements that are registered.
        final Set<String> learnables = EvolutionServices.getInstance().getEvolutionDataIds();
        final Genome result = new Genome();

        // Get data from property descriptors.
        final IPropertyDescriptor[] propertyDescriptors = propertySource.getPropertyDescriptors();

        // Determine uniformly distributed mutation probability.
        double uniformProb = 0.0;
        final int learnableCount = countLearnableProperties(Arrays.asList(propertyDescriptors));
        if (learnableCount > 0) {
            uniformProb = 1.0 / learnableCount;
        }

        System.out.println("Creating genome of " + learnableCount + " genes ...");
        final GeneFactory gf = new GeneFactory();

        // Iterate the property descriptors of the property source.
        for (final IPropertyDescriptor p : propertyDescriptors) {

            final String id = (String) p.getId();
            final Object value = propertySource.getPropertyValue(id);
            // Check the property descriptor id.
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
                    final IGene<?> gene = gf.newGene(id, value, uniformProb);
                    Assert.isNotNull(gene, "Failed to create gene for " + id);
                    result.add(gene);
                } else {
                    System.out.println("Not registered: " + id);
                }
            }
        }
        Assert.isTrue(learnableCount == result.size());

        // Add meta-evolution genes for the layout metrics.
        System.out.println("Adding metric weights ...");
        final TypeInfo<Float> typeInfo =
                new FloatTypeInfo(Float.valueOf(1.0f), Float.valueOf(0.0f), Float.valueOf(10.0f),
                        UniversalGene.STRICTLY_POSITIVE_FLOAT_FORMATTER, Float.class);
        final MutationInfo mutationInfo = new MutationInfo(0.01, .05, Distribution.GAUSSIAN);

        for (final String id : metricIds) {
            final IGene<?> gene = gf.newGene(id, Float.valueOf(1.0f), typeInfo, mutationInfo);
            Assert.isNotNull(gene, "Failed to create gene for " + id);
            result.add(gene);
        }

        System.out.println("Created genome: " + result.size() + " genes.");
        System.out.println();

        return result;
    }

    /**
     * Creates a population of the default size, taking initial values from the
     * given {@link LayoutPropertySource}.
     *
     * @param propertySource
     *            where the initial data is taken from; may not be {@code null}
     * @return the new population
     */
    private static Population createPopulation(final LayoutPropertySource propertySource) {
        Assert.isLegal(propertySource != null);
        final int size =
                EvolPlugin.getDefault().getPreferenceStore()
                        .getInt(EvolPlugin.PREF_POPULATION_SIZE);
        return createPopulation(propertySource, size);
    }

    /**
     * Create a population of the given size, taking initial values from the
     * given {@link LayoutPropertySource}.
     *
     * @param propertySource
     *            where the initial data is taken from; may not be {@code null}
     * @param size
     *            a non-negative value that specifies the initial size of the
     *            population.
     * @return population
     */
    private static Population createPopulation(
            final LayoutPropertySource propertySource, final int size) {
        Assert.isLegal(propertySource != null);
        Assert.isLegal(size >= 0);

        final Population result = new Population();
        final Set<String> metricIds = EvolutionServices.getInstance().getLayoutMetricsIds();

        for (int i = 0; i < size; i++) {
            final Genome genome = createGenome(propertySource, metricIds);
            Assert.isNotNull(genome);
            result.add(genome);
        }
        return result;
    }

    /**
     * Extracts the metric weight from the given genome.
     *
     * @param genome
     *            a {@link Genome}
     * @return a map associating metric weights to metric ids.
     */
    private static Map<String, Double> extractMetricWeights(final Genome genome) {
        // TODO: This method could be moved into {@code Genome}.
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
     *         part, or {@code null} if none can be found.
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

        final ILayoutInspector inspector = manager.getInspector(rootPart);
        final LayoutPropertySource result = new LayoutPropertySource(inspector);
        return result;
    }

    /**
     * Finds a layout provider for the given manager and the given edit part.
     *
     * @param manager
     *            a {@link DiagramLayoutManager}; must not be {@code null}
     * @param editPart
     *            an {@link EditPart}
     * @return the id of the layouter, or {@code null} if none can be found.
     */
    private static String getLayoutProviderId(
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
        return data.getId();
    }

    /**
     * Builds the layout graph for the given editor, using the given manager,
     * and performs layout on it.
     *
     * @param manager
     *            a {@link DiagramLayoutManager}
     * @param editor
     *            an {@link IEditorPart}
     * @return the layout graph, or {@code null} in case of an error.
     */
    private static KNode layout(final DiagramLayoutManager manager, final IEditorPart editor) {
        if ((editor == null) || (manager == null)) {
            // We cannot perform the layout.
            return null;
        }

        // First phase: build the layout graph.
        final KNode layoutGraph = manager.buildLayoutGraph(editor, null, true);

        // Second phase: execute layout algorithms.
        // We need a new monitor each time because the old one
        // gets closed.
        final IKielerProgressMonitor monitor =
                new BasicProgressMonitor(DiagramLayoutManager.MAX_PROGRESS_LEVELS);
        final IStatus status = manager.layout(monitor, true, false);

        if (!status.isOK()) {
            // TODO: what to do about the layouting failure? Log it? Abort?
            System.out.println(status.getMessage());
            return null;
        }

        final KNode layoutGraphAfterLayout = manager.getLayoutGraph();
        Assert.isTrue(layoutGraph == layoutGraphAfterLayout);
        return layoutGraphAfterLayout;
    }

    /**
     * Layout the diagram and measure it.
     *
     * @param manager
     *            a {@code DiagramLayoutManager}
     * @param editor
     *            the editor
     * @param coeffsMap
     *            a map that associates weights to the metrics; must not be
     *            {@code null}
     * @return measurement result (rating proposal)
     *
     */
    private static int layoutAndMeasure(
            final DiagramLayoutManager manager, final IEditorPart editor,
            final Map<String, Double> coeffsMap) {

        Assert.isLegal(coeffsMap != null);

        final KNode layoutGraph = layout(manager, editor);
        return measure(layoutGraph, coeffsMap);
    }

    /**
     * Measures the given layout graph, according to the given metric weights.
     * The given map is modified so that its values are normalized to yield a
     * sum of one.
     *
     * @param parentNode
     *            the KGraph to be analyzed.
     * @param weightsMap
     *            a map that associates weights to metric ids; may not be
     *            {@code null}
     * @return a rating proposal
     */
    private static int measure(final KNode parentNode, final Map<String, Double> weightsMap) {
        Assert.isLegal(weightsMap != null);

        if ((parentNode == null) || (weightsMap == null)) {
            return 0;
        }

        // Get the metric ids.
        final Set<String> metricIds = EvolutionServices.getInstance().getLayoutMetricsIds();

        // We have the metric ids, now get the metrics.
        final AnalysisServices as = AnalysisServices.getInstance();
        final List<AbstractInfoAnalysis> metricsList =
                new ArrayList<AbstractInfoAnalysis>(metricIds.size());
        for (final String metricId : metricIds) {
            final AbstractInfoAnalysis metric = as.getAnalysisById(metricId);
            Assert.isNotNull(metric, "Could not find analysis: " + metricId);
            metricsList.add(metric);
        }

        // Make sure the metric weights are normalized.
        normalize(weightsMap);

        // Perform the measurement.
        final boolean showProgressBar = false;
        final Map<String, Object> results =
                DiagramAnalyser.analyse(parentNode, metricsList, showProgressBar);

        // final double[] scaledResults = new double[metrics.length];
        double sum = 0.0;
        double scaledSum = 0;
        for (final AbstractInfoAnalysis metric : metricsList) {
            final String metricResult = results.get(metric.getID()).toString();
            final double coeff = weightsMap.get(metric.getID()).doubleValue();

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
            // System.out.println(metric.getID() + ": " + val + " ");
        }
        // System.out.println();
        // System.out.println("Difference from uniform scaling: "
        // + Math.abs(scaledSum - (sum / metricsList.size())));
        final int newRating = (int) Math.round((scaledSum * 10000));
        return newRating;
    }

    /**
     * Scales the values in the given map so that their sum equals one. This
     * operation changes the map entries of the given map.
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
            // Scale values.
            final double factor = 1.0 / sum;

            for (final Entry<String, Double> entry : map.entrySet()) {
                final double value = entry.getValue().doubleValue();
                entry.setValue(Double.valueOf(value * factor));
            }
        }
    }

    /** Hidden constructor to avoid instantiation. **/
    private EvolUtil() {
        // nothing
    }
}
