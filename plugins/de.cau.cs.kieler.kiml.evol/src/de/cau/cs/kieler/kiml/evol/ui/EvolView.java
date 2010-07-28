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
package de.cau.cs.kieler.kiml.evol.ui;

import org.eclipse.core.runtime.Assert;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import de.cau.cs.kieler.core.ui.util.MonitoredOperation;
import de.cau.cs.kieler.kiml.evol.EvolPlugin;
import de.cau.cs.kieler.kiml.evol.EvolUtil;
import de.cau.cs.kieler.kiml.evol.alg.BasicEvolutionaryAlgorithm;
import de.cau.cs.kieler.kiml.evol.genetic.Genome;
import de.cau.cs.kieler.kiml.evol.genetic.IItemFilter;
import de.cau.cs.kieler.kiml.evol.genetic.Population;
import de.cau.cs.kieler.kiml.ui.layout.DiagramLayoutManager;
import de.cau.cs.kieler.kiml.ui.layout.EclipseLayoutServices;
import de.cau.cs.kieler.kiml.ui.views.LayoutPropertySource;
import de.cau.cs.kieler.kiml.ui.views.LayoutViewPart;

/**
 * Test view for EvolPlugin.
 *
 * @author bdu
 *
 */
public class EvolView extends ViewPart {
    /**
     * Determines which individuals of a population shall be affected by an
     * operation.
     *
     * @author bdu
     *
     */
    public enum TargetIndividuals {
        /** Affect all individuals. */
        ALL,
        /** Affect only unrated individuals. */
        UNRATED,
        /** Affect only rated individuals. */
        RATED
    }

    /**
     * Refresher for the layout view.
     *
     * @author bdu
     *
     */
    private static class LayoutViewRefresher implements Runnable {
        public void run() {
            System.out.println("layoutView refresh start.");
            final LayoutViewPart layoutView = LayoutViewPart.findView();
            if (layoutView != null) {
                try {
                    Thread.sleep(200);
                } catch (final InterruptedException e) {
                    e.printStackTrace();
                }
                layoutView.refresh(); // async!
            }
            System.out.println("layoutView refresh called.");
        }
    }

    /**
     * provides labels for LayoutSet table.
     *
     * @author bdu
     *
     */
    private class PopulationTableLabelProvider extends LabelProvider implements ITableLabelProvider {
        private final Image currentImage = AbstractUIPlugin.imageDescriptorFromPlugin(
                EvolPlugin.PLUGIN_ID, "icons/current.png").createImage();
        private final Image defaultImage = AbstractUIPlugin.imageDescriptorFromPlugin(
                EvolPlugin.PLUGIN_ID, "icons/default.png").createImage();

        @Override
        public void dispose() {
            this.currentImage.dispose();
            this.defaultImage.dispose();
        }

        public Image getColumnImage(final Object element, final int columnIndex) {
            switch (columnIndex) {
            case 0:
                if ((element instanceof PopulationTableEntry)
                        && (((PopulationTableEntry) element).getIndex() == EvolView.this.position)) {
                    return this.currentImage;
                }
                return this.defaultImage;
            default:
                return null;
            }
        }

        // TODO: use CellLabelProviders
        public String getColumnText(final Object element, final int columnIndex) {
            //
            switch (columnIndex) {
            case 0:
                return ((PopulationTableEntry) element).getId();
            case 1:
                final Genome individual = ((PopulationTableEntry) element).getIndividual();
                return (individual.size() + " genes, rating: " + individual.getUserRating());
            default: // do nothing
                return null;
            }
        }

        @Override
        public boolean isLabelProperty(final Object element, final String property) {
            return false;
        }
    }

    /**
     * A table viewer with selectable rows.
     *
     * @author bdu
     *
     */
    public class SelectorTableViewer extends TableViewer {
        /**
         * Creates a TableViewer for the given table.
         *
         * @param table
         *            a table
         */
        public SelectorTableViewer(final Table table) {
            super(table);
        }

        private void selectRow(final int pos) {
            if (getPopulation() == null) {
                return;
            }
            Assert.isTrue((pos >= 0) && (pos <= getPopulation().size()), "position out of range");
            Display.getCurrent().syncExec(new Runnable() {
                public void run() {
                    getTableViewer().doSelect(new int[] { pos });
                }
            });
        }
    }

    /**
     *
     */
    public EvolView() {
        super();
        this.tableViewer = null;
    }

    // private fields
    private SelectorTableViewer tableViewer;
    private BasicEvolutionaryAlgorithm evolAlg;
    private int position = 0;
    private Population population;
    private IEditorPart lastEditor;
    private String layoutProviderId = "";

    /**
     * Column width for columns in viewer table.
     */
    private static final int DEFAULT_COLUMN_WIDTH = 150;
    /**
     * Identifier for the EvolView.
     */
    public static final String ID = "de.cau.cs.kieler.kiml.evol.evolView";

    // individual property settings
    @Override
    public void createPartControl(final Composite parent) {
        final Table table = new Table(parent, SWT.BORDER | SWT.BORDER_SOLID);
        // Composite tableComposite = new Composite(parent, SWT.NONE);
        // TableColumnLayout tableColumnLayout = new TableColumnLayout();
        // tableComposite.setLayout(tableColumnLayout);
        // tableViewer = new TableViewer(tableComposite, SWT.BORDER |
        // SWT.FULL_SELECTION);
        // Table table = tableViewer.getTable();
        final TableColumn column = new TableColumn(table, SWT.NONE);
        column.setWidth(DEFAULT_COLUMN_WIDTH);
        final TableColumn column2 = new TableColumn(table, SWT.NONE);
        column2.setWidth(DEFAULT_COLUMN_WIDTH);
        final SelectorTableViewer tv = new SelectorTableViewer(table);
        tv.setContentProvider(new PopulationTableContentProvider());
        tv.setLabelProvider(new PopulationTableLabelProvider());
        this.tableViewer = tv;
        reset();
        final ISelectionChangedListener listener = new ISelectionChangedListener() {
            public synchronized void selectionChanged(final SelectionChangedEvent event) {
                final ISelection selection = event.getSelection();
                System.out.println("selectionChanged");
                if ((selection != null) && (!selection.isEmpty())
                        && (selection instanceof IStructuredSelection)) {
                    final Object element = ((IStructuredSelection) selection).getFirstElement();
                    if (element instanceof PopulationTableEntry) {
                        tv.removeSelectionChangedListener(this);
                        EvolView.this.position = ((PopulationTableEntry) element).getIndex();
                        onSelectIndividual();
                        System.out.println("after onSelectIndividual");
                        tv.refresh();
                        tv.addPostSelectionChangedListener(this);
                        System.out.println();
                    }
                } else {
                    System.out.println("empty or null selection");
                }
            }
        };
        tv.addPostSelectionChangedListener(listener);
    }

    /**
     *
     * @return a shallow copy of the current population.
     */
    public Population getPopulation() {
        final Population pop = this.population;
        if (pop == null) {
            return null;
        }
        return new Population(pop);
    }

    /**
     *
     * @return the table viewer
     */
    public SelectorTableViewer getTableViewer() {
        return this.tableViewer;
    }

    @Override
    public void setFocus() {
        this.tableViewer.getControl().setFocus();
    }

    /**
     * Performs auto-rating on each individual of the given population that
     * satisfies the given filter criteria.
     *
     * @param thePopulation
     *            a {@link Population}
     * @param filter
     *            Indicates which individuals shall be rated.
     * @param theEditor
     *            Specifies the editor in which the individuals shall be rated.
     *            If this is {@code null}, then the most recently used editor is
     *            used. If this is {@code null} then the current editor is used.
     */
    public void autorateIndividuals(
            final Population thePopulation, final TargetIndividuals filter,
            final IEditorPart theEditor) {
        if (!isValidState()) {
            return;
        }
        final Population pop = thePopulation;
        if ((pop == null) || pop.isEmpty()) {
            return;
        }

        final IEditorPart editor;
        if (theEditor != null) {
            editor = theEditor;
        } else if (this.lastEditor != null) {
            editor = this.lastEditor;
        } else {
            editor = EvolUtil.getCurrentEditor();
        }
        this.lastEditor = editor;

        final Population selection;
        switch (filter) {
        case ALL:
            selection = pop;
            break;
        case RATED:
            selection = pop.select(new RatedFilter());
            break;
        case UNRATED:
            selection = pop.select(new UnratedFilter());
            break;
        default:
            selection = null;
            break;
        }
        EvolUtil.autoRateIndividuals(selection, editor);
        this.tableViewer.refresh();
    }

    /**
     * Filter for unrated individuals.
     *
     * @author bdu
     *
     */
    private static class UnratedFilter implements IItemFilter<Genome> {
        public boolean isMatch(final Genome item) {
            return (!item.hasUserRating());
        }
    }

    /**
     * Filter for rated individuals.
     *
     * @author bdu
     *
     */
    private static class RatedFilter implements IItemFilter<Genome> {
        public boolean isMatch(final Genome item) {
            return (item.hasUserRating());
        }
    }
    /**
     * Performs a step of the evolutionary algorithm.
     */
    public void evolve() {
        if (!isValidState()) {
            return;
        }

        final BasicEvolutionaryAlgorithm alg = this.evolAlg;
        if (alg == null) {
            return;
        }
        alg.step();
        setInput(alg.getPopulation());
        final int firstUnrated = firstUnrated();
        if (firstUnrated > -1) {
            this.position = firstUnrated;
        }
        final int lim = getPopulation().size();
        if (this.position >= lim) {
            this.position = lim - 1;
        }
        autorateIndividuals(this.population, TargetIndividuals.UNRATED, null);
        Assert.isTrue(this.position >= 0);
        getTableViewer().selectRow(this.position);
        getTableViewer().refresh();
        onSelectIndividual();
        // BasicNetwork b = new BasicNetwork();
        // b.addLayer(new BasicLayer(2));
        // b.addLayer(new BasicLayer(3));
        // b.addLayer(new BasicLayer(6));
        // b.addLayer(new BasicLayer(1));
        // b.getStructure().finalizeStructure();
        // System.out.println(b.calculateNeuronCount());
    }

    /**
     *
     * @return the current {@code Individual}, or {@code null} if none is
     *         selected.
     */
    public Genome getCurrentIndividual() {
        final Population pop = this.population;
        final int pos = this.position;
        Assert.isTrue((pos >= 0) && (pos < pop.size()), "position out of range");
        if ((pop == null) || pop.isEmpty()) {
            return null;
        }
        // ensure that the position is within the valid range
        if ((pos >= pop.size()) || (pos < 0)) {
            return null;
        }
        return pop.get(pos);
    }

    /**
     * Reset the population and restart the algorithm.
     */
    public void reset() {
        this.position = 0;
        final IEditorPart editor = EvolUtil.getCurrentEditor();
        final EditPart part = EvolUtil.getEditPart(editor);
        this.lastEditor = editor;
        if (editor != null) {
            // TODO: test whether the editor is for KIML
            final DiagramLayoutManager manager =
                    EclipseLayoutServices.getInstance().getManager(editor, part);
            if (manager != null) {
                final LayoutPropertySource propertySource =
                        new LayoutPropertySource(manager.getInspector(part));
                final Population sourcePopulation =
 EvolUtil.createPopulation(propertySource);
                this.layoutProviderId = EvolUtil.getLayoutProviderId(manager, part);
                final BasicEvolutionaryAlgorithm alg =
                        new BasicEvolutionaryAlgorithm(sourcePopulation);
                this.evolAlg = alg;
                alg.step();
                setInput(alg.getPopulation());
            }
        }
        this.tableViewer.selectRow(0);
        this.tableViewer.refresh();
    }


    /**
     * Return position of first unrated individual in the list.
     *
     * @return {@code -1} if no unrated individual exists.
     */
    private int firstUnrated() {
        final Population pop = this.population;
        if (pop == null) {
            return -1;
        }
        int result = -1;
        for (int i = 0; i < pop.size(); i++) {
            final Genome ind = pop.get(i);
            if (!ind.hasUserRating()) {
                result = i;
                break;
            }
        }
        return result;
    }


    /**
     *
     * @return indicates whether the layout provider has changes since the last
     *         reset.
     */
    private boolean layoutProviderHasChanged() {
        final IEditorPart editor = EvolUtil.getCurrentEditor();
        final EditPart editPart = EvolUtil.getEditPart(editor);
        final DiagramLayoutManager manager =
                EclipseLayoutServices.getInstance().getManager(editor, editPart);
        final String newId = EvolUtil.getLayoutProviderId(manager, editPart);
        if ((newId == null) || (this.layoutProviderId == null)) {
            return (newId != null) || (this.layoutProviderId != null);
        }
        return !this.layoutProviderId.equalsIgnoreCase(newId);
    }

    /**
     * Refresh the layout according to selected individual.
     */
    private void onSelectIndividual() {
        if (!isValidState()) {
            return;
        }
        final Genome currentIndividual = getCurrentIndividual();
        Assert.isNotNull(currentIndividual);
        final LayoutPropertySource source = EvolUtil.getLayoutPropertySource();
        EvolUtil.adoptIndividual(currentIndividual, source);

        // refresh layout view
        MonitoredOperation.runInUI(new LayoutViewRefresher(), false);
        final IEditorPart editor = EvolUtil.getCurrentEditor();
        if (editor == null) {
            // we have nothing to layout.
            return;
        }
        // We don't specify the edit part because we want a manager for
        // the whole diagram.
        final DiagramLayoutManager manager =
                EclipseLayoutServices.getInstance().getManager(editor, null);

        // layoutDiagram(false, false);
        // System.out.println("after layoutDiagram");
        final int rating = EvolUtil.layoutAndMeasure(manager, editor);

        if (!currentIndividual.hasUserRating()) {
            currentIndividual.setUserRating(rating);
        }

        // apply the layout to the diagram
        // XXX it would be more straightforward to call manager.applyLayout()
        // directly, but that method is private
        EclipseLayoutServices.getInstance().layout(editor, null, false, false);
    }

    private boolean isValidState() {
        final Population pop = this.population;
        if (pop == null) {
            System.out.println("Population is not set.");
            return false;
        }
        if (pop.isEmpty()) {
            System.out.println("Population is empty.");
            return false;
        }

        final Genome currentIndividual = getCurrentIndividual();
        if (currentIndividual == null) {
            System.out.println("No individual selected.");
            return false;
        }
        final LayoutViewPart layoutViewPart = LayoutViewPart.findView();
        if (layoutViewPart == null) {
            System.out.println("LayoutView not found.");
            return false;
        }
        if (layoutProviderHasChanged()) {
            // need to reset
            System.out.println("LayoutProvider was changed. Need to reset.");
            return false;
        }

        return true;
    }

    /**
     * Sets a population as the input of the viewer.
     *
     * @param thePopulation
     *            new source population
     */
    private void setInput(final Population thePopulation) {
        if ((thePopulation != null) && (thePopulation != this.population)) {
            this.population = thePopulation;
            final Runnable runnable = new Runnable() {
                public void run() {
                    EvolView.this.tableViewer.setInput(thePopulation);
                }
            };
            runnable.run();
        }
    }
}
