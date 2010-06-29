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
import org.eclipse.core.runtime.IStatus;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
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

import de.cau.cs.kieler.core.alg.BasicProgressMonitor;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.ui.util.MonitoredOperation;
import de.cau.cs.kieler.kiml.evol.EvolPlugin;
import de.cau.cs.kieler.kiml.evol.EvolUtil;
import de.cau.cs.kieler.kiml.evol.Individual;
import de.cau.cs.kieler.kiml.evol.IndividualLayoutListener;
import de.cau.cs.kieler.kiml.evol.LayoutListener;
import de.cau.cs.kieler.kiml.evol.alg.BasicEvolutionaryAlgorithm;
import de.cau.cs.kieler.kiml.evol.genetic.Genome;
import de.cau.cs.kieler.kiml.evol.genetic.IGene;
import de.cau.cs.kieler.kiml.evol.genetic.Population;
import de.cau.cs.kieler.kiml.layout.LayoutOptionData;
import de.cau.cs.kieler.kiml.layout.LayoutServices;
import de.cau.cs.kieler.kiml.layout.LayoutServices.Registry;
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
public class TestView extends ViewPart {
    /**
     * An action that assigns an automatic rating to all individuals.
     * 
     * @author bdu
     * 
     */
    private class AutorateAllAction extends Action {
        public AutorateAllAction() {
            this.setText("Auto-rate");
            this.setToolTipText("Auto-rate all individuals");
        }
        
        @Override
        public void run() {
            autorateIndividuals(TargetIndividuals.ALL);
        }
    }
    
    /**
     * Action for rating an individual.
     * 
     * @author bdu
     * 
     */
    private class ChangeRatingAction extends Action {
        public ChangeRatingAction(final int theDelta) {
            delta = theDelta;
        }
        
        private final int delta;
        
        @Override
        public void run() {
            if (population != null) {
                final Individual ind = getCurrentIndividual();
                final int rating = ind.getRating() + delta;
                ind.setRating(rating);
                tableViewer.refresh();
            }
        }
    }
    
    /**
     * Action for giving a negative rating to an individual.
     * 
     * @author bdu
     * 
     */
    private class DemoteAction extends ChangeRatingAction {
        private static final int AMOUNT = -10;
        
        public DemoteAction() {
            super(AMOUNT);
            setText("Disregard");
            setToolTipText("Demote the selected individual.");
        }
    }
    
    /**
     * Action for performing a step of the evolutionary algorithm. This creates
     * a new generation.
     * 
     * @author bdu
     * 
     */
    private class EvolveAction extends Action {
        public EvolveAction() {
            setText("Evolve");
        }
        
        @Override
        public void run() {
            final BasicEvolutionaryAlgorithm alg = TestView.this.evolAlg;
            if (alg == null) {
                return;
            }
            alg.step();
            setInput(alg.getPopulation());
            final int firstUnrated = firstUnrated();
            if (firstUnrated > -1) {
                position = firstUnrated;
            }
            final int lim = population.size();
            if (position >= lim) {
                position = lim - 1;
            }
            autorateIndividuals(TargetIndividuals.UNRATED);
            Assert.isTrue(position >= 0);
            tableViewer.selectRow(position);
            tableViewer.refresh();
            onSelectIndividual();
            // BasicNetwork b = new BasicNetwork();
            // b.addLayer(new BasicLayer(2));
            // b.addLayer(new BasicLayer(3));
            // b.addLayer(new BasicLayer(6));
            // b.addLayer(new BasicLayer(1));
            // b.getStructure().finalizeStructure();
            // System.out.println(b.calculateNeuronCount());
        }
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
                    Thread.sleep(800);
                } catch (final InterruptedException e) {
                    e.printStackTrace();
                }
                layoutView.refresh(); // async!
            }
            System.out.println("layoutView refresh called.");
        }
    }

    /**
     * Content provider for table view.
     * 
     * @author bdu
     * 
     */
    private static class PopulationTableContentProvider implements IStructuredContentProvider {
        public void dispose() {
            // do nothing
        }
        
        public Object[] getElements(final Object inputElement) {
            // suppose inputElement contains a reference to a Population object.
            final Population inputPopulation;
            if (!(inputElement instanceof Population)) {
                return new PopulationTableEntry[] {};
            }
            inputPopulation = (Population) inputElement;
            final PopulationTableEntry[] result = new PopulationTableEntry[inputPopulation.size()];
            int i = 0;
            for (final Individual individual : inputPopulation) {
                result[i] = new PopulationTableEntry();
                result[i].setIndividual(individual);
                result[i].index = i;
                i++;
            }
            return result;
        }
        
        public void inputChanged(final Viewer viewer, final Object oldInput, final Object newInput) {
            System.out.println("Viewer " + viewer.toString() + " input changed.");
        }
    }

    /**
     * A population table entry contains an individual.
     * 
     * @author bdu
     * 
     */
    private static class PopulationTableEntry {
        private int index = 0;
        private Individual individual;
        
        public String getId() {
            if (this.individual != null) {
                return this.individual.getGeneration() + "." + this.individual.getId();
            }
            return null;
        }
        
        public Individual getIndividual() {
            return this.individual;
        }
        
        public void setIndividual(final Individual theIndividual) {
            this.individual = theIndividual;
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
                        && (((PopulationTableEntry) element).index == TestView.this.position)) {
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
     * Action for giving a positive rating to an individual.
     * 
     * @author bdu
     * 
     */
    private class PromoteAction extends ChangeRatingAction {
        private static final int AMOUNT = +30;
        
        public PromoteAction() {
            super(AMOUNT);
            setText("Favor");
            setToolTipText("Promote the selected individual.");
        }
    }
    
    /**
     * Action for resetting the view.
     * 
     * @author bdu
     * 
     */
    private class ResetAction extends Action {
        public ResetAction() {
            setText("Reset");
            setToolTipText("Restart with a new population.");
        }
        
        @Override
        public void run() {
            position = 0;

            final IEditorPart editor = getCurrentEditor();
            final EditPart part = getEditPart(editor);
            LayoutPropertySource propertySource = new LayoutPropertySource(editor, part);
            final Population newPopulation =
                    EvolUtil.createPopulation(propertySource, DEFAULT_INITIAL_POPULATION_SIZE);
            final BasicEvolutionaryAlgorithm alg = new BasicEvolutionaryAlgorithm(newPopulation);
            alg.step();
            TestView.this.evolAlg = alg;
            setInput(alg.getPopulation());
            tableViewer.selectRow(position);
            tableViewer.refresh();
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
            Assert.isTrue((pos >= 0) && (pos <= population.size()), "position out of range");
            Display.getCurrent().syncExec(new Runnable() {
                public void run() {
                    tableViewer.doSelect(new int[] { pos });
                }
            });
        }
    }
    
    /**
     * Determines which individuals of a population shall be affected by an
     * operation.
     * 
     * @author bdu
     * 
     */
    private enum TargetIndividuals {
        ALL, UNRATED, RATED
    }
    
    /**
     * Initial population size.
     */
    private static final int DEFAULT_INITIAL_POPULATION_SIZE = 25;
    
    /**
     *
     */
    public TestView() {
        super();
        this.tableViewer = null;
    }
    
    // private fields
    private SelectorTableViewer tableViewer;

    private int position = 0;
    private BasicEvolutionaryAlgorithm evolAlg;
    private Population population;
    private IEditorPart graphEditor;
    private String layouter;
    /**
     * Column width for columns in viewer table.
     */
    private static final int DEFAULT_COLUMN_WIDTH = 140;

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
        final TableColumn column2 = new TableColumn(table, SWT.NONE);
        column.setWidth(DEFAULT_COLUMN_WIDTH);
        column2.setWidth(DEFAULT_COLUMN_WIDTH);
        final SelectorTableViewer tv = new SelectorTableViewer(table);
        tv.setContentProvider(new PopulationTableContentProvider());
        tv.setLabelProvider(new PopulationTableLabelProvider());

        final IEditorPart editor = getCurrentEditor();
        final IGraphicalEditPart part = (IGraphicalEditPart) getEditPart(editor);
        this.graphEditor = editor;
        this.tableViewer = tv;
        if (editor != null) {
            // TODO: test whether editor is for KIML
            // FIXME: should share synchronized property source with LayoutView?
            final LayoutPropertySource source = new LayoutPropertySource(editor, part);
            final Population sourcePopulation =
                    EvolUtil.createPopulation(source, DEFAULT_INITIAL_POPULATION_SIZE);
            final BasicEvolutionaryAlgorithm alg = new BasicEvolutionaryAlgorithm(sourcePopulation);
            alg.step();
            this.evolAlg = alg;
            setInput(alg.getPopulation());
        }

        createToolBar();
        final ISelectionChangedListener listener = new ISelectionChangedListener() {
            public synchronized void selectionChanged(final SelectionChangedEvent event) {
                final ISelection selection = event.getSelection();
                System.out.println("selectionChanged");
                if ((selection != null) && (!selection.isEmpty())
                        && (selection instanceof IStructuredSelection)) {
                    final Object element = ((IStructuredSelection) selection).getFirstElement();
                    if (element instanceof PopulationTableEntry) {
                        tv.removeSelectionChangedListener(this);
                        // final int oldPosition = position;
                        position = ((PopulationTableEntry) element).index;
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
    
    @Override
    public void setFocus() {
        tableViewer.getControl().setFocus();
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
        if ((pop == null) || pop.isEmpty() || theIndividual == null) {
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
            final Thread t = new Thread(new LayoutViewRefresher());
            t.start();
        }
        System.out.println("leaving adoptIndividual");
    }
    
    /**
     * Performs auto-rating on each individual that belongs to the given target.
     */
    private void autorateIndividuals(final TargetIndividuals target) {
        System.out.println("autorate population");
        final Population pop = this.population;
        if (pop == null || pop.isEmpty()) {
            return;
        }

        if (this.graphEditor == null) {
            this.graphEditor = getCurrentEditor();
        }
        final Registry registry = LayoutServices.getRegistry();
        final IndividualLayoutListener listener =
                new IndividualLayoutListener(getCurrentIndividual());
        // we don't specify the edit part because we want a manager for the
        // whole diagram
        final DiagramLayoutManager manager =
                EclipseLayoutServices.getInstance().getManager(this.graphEditor, null);
        Runnable layoutLoop = new Runnable() {
            public void run() {
                for (int pos = 0; pos < pop.size(); pos++) {

                    System.out.println("Position: " + pos);
                    final Individual ind = pop.get(pos);
                    System.out.println(ind.toString());
                    listener.setIndividual(ind);

                    // TODO: synchronize on the layout graph?
                    final boolean isAffected;
                    switch (target) {
                    case ALL:
                        isAffected = true;
                        break;
                    case RATED:
                        isAffected = (ind.hasRating());
                        break;
                    case UNRATED:
                        isAffected = (!ind.hasRating());
                        break;
                    default: // this case should never happen
                        isAffected = false;
                        break;
                    }
                    if (isAffected) {
                        adoptIndividual(ind, false);
                        // first phase: build the layout graph
                        // TODO: get a new manager for every iteration?
                        manager.buildLayoutGraph(graphEditor, null, true);
                        // second phase: execute layout algorithms
                        // We need a new monitor each time because the old one
                        // gets closed.
                        final IKielerProgressMonitor monitor =
                                new BasicProgressMonitor(DiagramLayoutManager.MAX_PROGRESS_LEVELS);
                        final IStatus status = manager.layout(monitor, true, false);
                        // layout should trigger the measurement of the current
                        // diagram
                        System.out.println("after manager.layout. result: " + status.getCode());
                        // tableViewer.refresh();
                    }
                }
            }
        };
        registry.addLayoutListener(listener);
        // the current diagram gets layouted and measured.
        MonitoredOperation.runInUI(layoutLoop, true);
        System.out.println("remove layout listener");
        registry.removeLayoutListener(listener);

        this.tableViewer.refresh();
    }
    
    /**
     * Creates the tool bar for the view.
     */
    private void createToolBar() {
        // Get tool bar manager.
        final IToolBarManager toolBarManager = getViewSite().getActionBars().getToolBarManager();
        // Create some actions and add them to tool bar manager.
        toolBarManager.add(new AutorateAllAction());
        toolBarManager.add(new PromoteAction());
        toolBarManager.add(new DemoteAction());
        toolBarManager.add(new EvolveAction());
        toolBarManager.add(new ResetAction());
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
     * 
     * @return the current {@code Individual}, or {@code null} if none is
     *         selected.
     */
    private Individual getCurrentIndividual() {
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
        final LayoutPropertySource result = new LayoutPropertySource(editor, part);
        return result;
    }
    
    /**
     * Layout the diagram in the current editor.
     * 
     * @param showAnimation
     *            indicates whether the layout shall be animated
     * @param showProgressBar
     *            indicates whether a progress bar shall be shown
     */
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
        Assert.isNotNull(population, "population is null");
        // Assert.isTrue(population.size() > 0, "zero population");
        System.out.println("in onSelectIndividual");
        System.out.println(population.toString());
        final Individual currentIndividual = getCurrentIndividual();
        Assert.isNotNull(currentIndividual);
        adoptIndividual(currentIndividual, true);
        try {
            Thread.sleep(100);
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }
        // need a layout listener to wait for the layouting to be
        // finished and applied before measuring it.
        final Registry registry = LayoutServices.getRegistry();
        final LayoutListener listener = new IndividualLayoutListener(currentIndividual);
        registry.addLayoutListener(listener);
        layoutDiagram(false, false);
        System.out.println("after layoutDiagram");
        try {
            Thread.sleep(50);
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("remove layout listener");
        registry.removeLayoutListener(listener);
        tableViewer.refresh();
    }

    /**
     * Sets a population as the input of the viewer.
     * 
     * @param thePopulation
     *            new source population
     */
    private void setInput(final Population thePopulation) {
        if ((thePopulation != null) && (thePopulation != population)) {
            population = thePopulation;
            final Runnable runnable = new Runnable() {
                public void run() {
                    tableViewer.setInput(thePopulation);
                }
            };
            runnable.run();
        }
    }
}
