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
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import de.cau.cs.kieler.core.ui.util.MonitoredOperation;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.LayoutServices;
import de.cau.cs.kieler.kiml.evol.EvolPlugin;
import de.cau.cs.kieler.kiml.evol.EvolUtil;
import de.cau.cs.kieler.kiml.evol.Individual;
import de.cau.cs.kieler.kiml.evol.alg.BasicEvolutionaryAlgorithm;
import de.cau.cs.kieler.kiml.evol.genetic.Genome;
import de.cau.cs.kieler.kiml.evol.genetic.IGene;
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
        ALL, UNRATED, RATED
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
                    Thread.sleep(400);
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
                final Individual individual = ((PopulationTableEntry) element).getIndividual();
                return (individual.getGenome().size() + " genes, rating: " + individual.getRating());
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
    private class SelectorTableViewer extends TableViewer {
        public SelectorTableViewer(final Table table) {
            super(table);
            // TODO Auto-generated constructor stub
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
     * Initial population size.
     */
    private static final int DEFAULT_INITIAL_POPULATION_SIZE = 12;

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

    private String layoutProviderId;

    /**
     * Column width for columns in viewer table.
     */
    private static final int DEFAULT_COLUMN_WIDTH = 140;

    public static final String ID = "de.cau.cs.kieler.kiml.evol.wildlife";

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
        createToolBar();
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
                        position = ((PopulationTableEntry) element).getIndex();
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
    public Population getPopulation() {
        return population;
    }
    public SelectorTableViewer getTableViewer() {
        return tableViewer;
    }
    @Override
    public void setFocus() {
        tableViewer.getControl().setFocus();
    }
    public void setPopulation(final Population population) {
        this.population = population;
    }
    public void setTableViewer(final SelectorTableViewer tableViewer) {
        this.tableViewer = tableViewer;
    }

    /**
     * Performs auto-rating on each individual that belongs to the given target.
     */
    public void autorateIndividuals(final TargetIndividuals target) {
        System.out.println("autorate population");
        final Population pop = this.population;
        if ((pop == null) || pop.isEmpty()) {
            return;
        }

        if (this.lastEditor == null) {
            this.lastEditor = getCurrentEditor();
        }

        final IEditorPart editor = this.lastEditor;
        // we don't specify the edit part because we want a manager for
        // the whole diagram
        final DiagramLayoutManager manager =
                EclipseLayoutServices.getInstance().getManager(editor, null);

        final Runnable layoutLoop = new Runnable() {
            public void run() {

                for (int pos = 0; pos < pop.size(); pos++) {

                    System.out.println("Position: " + pos);
                    final Individual ind = pop.get(pos);
                    System.out.println(ind.toString());

                    // TODO: synchronize on the layout graph?
                    if (isAffected(ind, target)) {
                        adoptIndividual(ind, false);
                        // TODO: get a new manager for every iteration?
                        final int rating = EvolUtil.layoutAndMeasure(manager, editor);
                        ind.setRating(rating);
                    }
                }
            }

            private boolean isAffected(final Individual ind, final TargetIndividuals target) {
                switch (target) {
                case ALL:
                    return true;
                case RATED:
                    return (ind.hasRating());
                case UNRATED:
                    return (!ind.hasRating());
                default: // this case should never happen
                    return false;
                }
            }
        };

        // the current diagram gets layouted and measured.
        MonitoredOperation.runInUI(layoutLoop, true);

        this.tableViewer.refresh();
    }

    /**
     * Performs a step of the evolutionary algorithm.
     */
    void evolve() {
        final BasicEvolutionaryAlgorithm alg = this.evolAlg;
        if (alg == null) {
            return;
        }
        alg.step();
        setInput(alg.getPopulation());
        final int firstUnrated = firstUnrated();
        if (firstUnrated > -1) {
            position = firstUnrated;
        }
        final int lim = getPopulation().size();
        if (position >= lim) {
            position = lim - 1;
        }
        autorateIndividuals(TargetIndividuals.UNRATED);
        Assert.isTrue(position >= 0);
        getTableViewer().selectRow(position);
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
    Individual getCurrentIndividual() {
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
     * Reset the population.
     */
    void reset() {
        this.position = 0;

        final IEditorPart editor = getCurrentEditor();
        final EditPart part = getEditPart(editor);
        this.lastEditor = editor;
        if (editor != null) {
            // TODO: test whether the editor is for KIML
            // FIXME: should share synchronized property source with LayoutView?
            DiagramLayoutManager manager = EclipseLayoutServices.getInstance().getManager(
                    editor, part);
            final LayoutPropertySource propertySource = new LayoutPropertySource(
                    manager.getInspector(part));
            final Population sourcePopulation =
                    EvolUtil.createPopulation(propertySource, DEFAULT_INITIAL_POPULATION_SIZE);
            final BasicEvolutionaryAlgorithm alg = new BasicEvolutionaryAlgorithm(sourcePopulation);
            this.evolAlg = alg;
            alg.step();
            setInput(alg.getPopulation());
        }
        this.tableViewer.selectRow(0);
        this.tableViewer.refresh();
    }

    /**
     * Adopts layout options from the given {@code Individual}. The given
     * individual must not be {@code null}.
     *
     * @param theIndividual
     *            the individual
     * @param wantLayoutViewRefresh
     *            whether the layout view shall be refreshed
     */
    private void adoptIndividual(final Individual theIndividual, final boolean wantLayoutViewRefresh) {
        System.out.println("in adoptIndividual");

        Assert.isLegal(theIndividual != null);
        final Population pop = this.population;
        if ((pop == null) || pop.isEmpty() || (theIndividual == null)) {
            return;
        }
        final LayoutPropertySource source = getLayoutPropertySource();

        System.out.println("adopt " + theIndividual.toString());
        final Genome genome = theIndividual.getGenome();
        final LayoutServices layoutServices = LayoutServices.getInstance();
        for (final IGene<?> gene : genome) {
            Assert.isNotNull(gene);
            final Object theValue = gene.getValue();
            final Object id = gene.getId();
            final LayoutOptionData data = layoutServices.getLayoutOptionData((String) id);
            Assert.isNotNull(data);
            switch (data.getType()) {
            case BOOLEAN:
                source.setPropertyValue(id, ((Boolean) theValue ? 1 : 0));
                break;
            case ENUM:
                try {
                    source.setPropertyValue(id, theValue);
                } catch (final NullPointerException e) {
                    System.out.println("WARNING: enum property could not be set.");
                    Assert.isTrue(false);
                }
                break;
            default:
                source.setPropertyValue(id, theValue.toString());
                break;
            }
        }
        // refresh layout view?
        if (wantLayoutViewRefresh) {
            MonitoredOperation.runInUI(new LayoutViewRefresher(), false);
        }
        System.out.println("leaving adoptIndividual");
    }

    /**
     * Creates the tool bar for the view.
     */
    private void createToolBar() {
        // Get tool bar manager.
        final IToolBarManager toolBarManager = getViewSite().getActionBars().getToolBarManager();
        // Create some actions and add them to the tool bar manager.
        // toolBarManager.add(new AutorateAllAction(this));
        toolBarManager.add(new PromoteAction(this));
        toolBarManager.add(new DemoteAction(this));
        toolBarManager.add(new EvolveAction(this));
        toolBarManager.add(new ResetAction(this));
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
            final Individual ind = pop.get(i);
            if (!ind.hasRating()) {
                result = i;
                break;
            }
        }
        return result;
    }

    /**
     * Returns the current editor.
     *
     * @return the current editor or {@code null} if none exists.
     */
    private IEditorPart getCurrentEditor() {

        // TODO: cache editor and assert that it is not replaced?
        final LayoutViewPart layoutViewPart = LayoutViewPart.findView();
        if (layoutViewPart == null) {
            final IWorkbench workbench = PlatformUI.getWorkbench();
            final IWorkbenchPage page = workbench.getActiveWorkbenchWindow().getActivePage();
            if (page != null) {
                return page.getActiveEditor();
            }
            return null;
        }
        final IEditorPart result = layoutViewPart.getCurrentEditor();
        return result;
    }

    /**
     * Returns the current edit part.
     *
     * @return the current edit part or {@code null} if none exists.
     */
    private EditPart getEditPart(final IEditorPart editor) {
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
     * @return a {@code LayoutPropertySource} for the current editor.
     */
    private LayoutPropertySource getLayoutPropertySource() {
        final IEditorPart editor = getCurrentEditor();
        final IGraphicalEditPart part = (IGraphicalEditPart) getEditPart(editor);
        // TODO: use root edit part
        DiagramLayoutManager manager = EclipseLayoutServices.getInstance().getManager(editor, part);
        final LayoutPropertySource result = new LayoutPropertySource(
                manager.getInspector(part));
        return result;
    }



    /**
     * Layout the diagram in the current editor.
     *
     * @param showAnimation
     *            indicates whether the layout shall be animated
     * @param showProgressBar
     *            indicates whether a progress bar shall be shown
     * @deprecated
     */
    @Deprecated
    private void layoutDiagram(final boolean showAnimation, final boolean showProgressBar) {
        System.out.println("in layoutDiagram");
        final Population pop = this.population;
        if (pop != null) {
            final IEditorPart editor = getCurrentEditor();
            if (editor == null) {
                // so we have nothing to layout.
                return;
            }
            // we don't need to specify the edit part because we want to layout
            // the whole diagram
            EclipseLayoutServices.getInstance()
                    .layout(editor, null, showAnimation, showProgressBar);
        }
        System.out.println("leaving layoutDiagram");
    }


    /**
     * Refresh the layout according to selected individual.
     */
    private void onSelectIndividual() {
        final Population pop = this.population;
        Assert.isNotNull(pop, "population is null");
        // Assert.isTrue(population.size() > 0, "zero population");
        System.out.println("in onSelectIndividual");
        // System.out.println(pop.toString());
        final Individual currentIndividual = getCurrentIndividual();
        Assert.isNotNull(currentIndividual);

        adoptIndividual(currentIndividual, true);

        final IEditorPart editor = getCurrentEditor();
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
        currentIndividual.setRating(rating);
        // apply the layout to the diagram
        // XXX it would be more straightforward to call manager.applyLayout()
        // directly, but that method is private
        EclipseLayoutServices.getInstance().layout(editor, null, false, false);

        // System.out.println("remove layout listener");
        // registry.removeLayoutListener(listener);
        // tableViewer.refresh();

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


