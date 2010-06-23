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

import java.util.LinkedList;
import java.util.List;

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
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import de.cau.cs.kieler.core.alg.BasicProgressMonitor;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.ui.util.MonitoredOperation;
import de.cau.cs.kieler.kiml.evol.alg.BasicEvolutionaryAlgorithm;
import de.cau.cs.kieler.kiml.grana.AbstractInfoAnalysis;
import de.cau.cs.kieler.kiml.grana.AnalysisServices;
import de.cau.cs.kieler.kiml.grana.ui.DiagramAnalyser;
import de.cau.cs.kieler.kiml.layout.ILayoutListener;
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
     *
     */
    public TestView() {
        super();
        this.tableViewer = null;
    }
    
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
        tableViewer = new SelectorTableViewer(table);
        tableViewer.setContentProvider(new LayoutSetContentProvider());
        tableViewer.setLabelProvider(new LayoutSetLabelProvider());
        // final IWorkbench workbench = PlatformUI.getWorkbench();
        // final IWorkbenchPage page =
        // workbench.getActiveWorkbenchWindow().getActivePage();
        final IEditorPart editor = getCurrentEditor();
        // if (page != null) {
        // editor = page.getActiveEditor();
        // }
        final IGraphicalEditPart part = (IGraphicalEditPart) getEditPart(editor);
        if (editor != null) {
            // // TODO: test whether editor is for KIML
            // final ISelection selection =
            // editor.getEditorSite().getSelectionProvider()
            // .getSelection();
            // Object element = null;
            // if (selection != null) {
            // if (selection instanceof StructuredSelection) {
            // element = ((StructuredSelection) selection).getFirstElement();
            // if (element instanceof IGraphicalEditPart) {
            // part = (IGraphicalEditPart) element;
            // }
            // }
            // }
            // FIXME: should share synchronized property source with LayoutView
            final LayoutPropertySource source = new LayoutPropertySource(editor, part);
            propertySource = source;
            final Population sourcePopulation =
                    EvolUtil.createPopulation(source, DEFAULT_INITIAL_POPULATION_SIZE);
            evolAlg = new BasicEvolutionaryAlgorithm(sourcePopulation);
            evolAlg.step();
            setInput(evolAlg.getPopulation());
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
                        tableViewer.removeSelectionChangedListener(this);
                        final int oldPosition = position;
                        position = ((PopulationTableEntry) element).index;
                        onSelectIndividual();
                        System.out.println("after onSelectIndividual");
                        tableViewer.refresh();
                        tableViewer.addPostSelectionChangedListener(this);
                        System.out.println();
                    }
                } else {
                    System.out.println("empty or null selection");
                }
            }
        };
        tableViewer.addPostSelectionChangedListener(listener);
    }
    
    @Override
    public void setFocus() {
        tableViewer.getControl().setFocus();
    }
    
    // private fields
    private SelectorTableViewer tableViewer;
    private int position = 0;
    private LayoutPropertySource propertySource;
    private BasicEvolutionaryAlgorithm evolAlg;
    private Population population;
    /**
     * column width for columns in viewer table.
     */
    private static final int DEFAULT_COLUMN_WIDTH = 140;
    private static final int DEFAULT_INITIAL_POPULATION_SIZE = 5;
    
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
                    tableViewer.setInput(population);
                }
            };
            runnable.run();
        }
    }
    
    /**
     * Content provider for table view.
     * 
     * @author bdu
     * 
     */
    private static class LayoutSetContentProvider implements IStructuredContentProvider {
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
        
        public void dispose() {
            // do nothing
        }
        
        public void inputChanged(final Viewer viewer, final Object oldInput, final Object newInput) {
            System.out.println("Viewer " + viewer.toString() + " input changed.");
        }
    }
    
    /**
     * provides labels for LayoutSet table.
     * 
     * @author bdu
     * 
     */
    private class LayoutSetLabelProvider extends LabelProvider implements ITableLabelProvider {
        private final Image currentImage =
                AbstractUIPlugin.imageDescriptorFromPlugin(EvolPlugin.PLUGIN_ID,
                        "icons/current.png").createImage();
        private final Image defaultImage =
                AbstractUIPlugin.imageDescriptorFromPlugin(EvolPlugin.PLUGIN_ID,
                        "icons/default.png").createImage();
        
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
        public void dispose() {
            this.currentImage.dispose();
            this.defaultImage.dispose();
        }
        
        @Override
        public boolean isLabelProperty(final Object element, final String property) {
            return false;
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
            if (individual != null) {
                return individual.getGeneration() + "." + individual.getId();
            }
            return null;
        }
        
        public Individual getIndividual() {
            return individual;
        }
        
        public void setIndividual(final Individual theIndividual) {
            this.individual = theIndividual;
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
            propertySource = new LayoutPropertySource(editor, part);
            final Population newPopulation =
                    EvolUtil.createPopulation(propertySource, DEFAULT_INITIAL_POPULATION_SIZE);
            evolAlg = new BasicEvolutionaryAlgorithm(newPopulation);
            evolAlg.step();
            setInput(evolAlg.getPopulation());
            tableViewer.selectRow(position);
            tableViewer.refresh();
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
     * Action for rating an individual.
     * 
     * @author bdu
     * 
     */
    private class ChangeRatingAction extends Action {
        private final int delta;
        
        public ChangeRatingAction(final int theDelta) {
            delta = theDelta;
        }
        
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
     * Action for auto-rating all individuals.
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
            autoratePopulation(Target.ALL);
        }
    }
    
    /**
     * Determines which individuals of a population shall be affected by an
     * operation.
     * 
     * @author bdu
     * 
     */
    private enum Target {
        ALL, UNRATED, RATED
    }
    
    /**
     * Performs auto-rating on each individual that belongs to the given target.
     */
    private void autoratePopulation(final Target target) {
        if (population == null || population.isEmpty()) {
            return;
        }
        System.out.println("autorate population");
        final Population pop = this.population;
        final int oldPosition = this.position;
        final IEditorPart editor = getCurrentEditor();
        final Registry registry = LayoutServices.getRegistry();

        final DiagramLayoutManager manager =
                EclipseLayoutServices.getInstance().getManager(editor, null);
        Runnable layoutLoop = new Runnable() {
            public void run() {
                for (int pos = 0; pos < pop.size(); pos++) {
                    TestView.this.position = pos;
                    System.out.println("Position: " + pos);
                    final Individual ind = pop.get(pos);
                    System.out.println(ind.toString());
                    // TODO: synchronize on the layout graph?
                    final boolean isAffected;
                    switch (target) {
                    case ALL:
                        isAffected = true;
                        break;
                    case RATED:
                        isAffected = (ind.getRating() != 0);
                        break;
                    case UNRATED:
                        isAffected = (ind.getRating() == 0);
                        break;
                    default: // this case should never happen
                        isAffected = false;
                        break;
                    }
                    if (isAffected) {
                        // first phase: build the layout graph
                        manager.buildLayoutGraph(editor, null, true);
                        // second phase: execute layout algorithms
                        adoptIndividual(ind, false);
                        final IKielerProgressMonitor monitor =
                                new BasicProgressMonitor(DiagramLayoutManager.MAX_PROGRESS_LEVELS);
                        final IStatus status = manager.layout(monitor, true, false);
                        // layout should trigger the measurement
                        System.out.println("after manager.layout. result: " + status.getCode());
                    }
                    try {
                        Thread.sleep(100);
                    } catch (final InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        ILayoutListener listener = layoutListener;
        registry.addLayoutListener(listener);
        // FIXME: not the current layout gets measured but the previous one
        MonitoredOperation.runInUI(layoutLoop, true);
        System.out.println("remove layout listener");
        registry.removeLayoutListener(listener);
        this.position = oldPosition;
        this.tableViewer.refresh();
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
            if (evolAlg == null) {
                return;
            }
            evolAlg.step();
            setInput((evolAlg).getPopulation());
            final int firstUnrated = firstUnrated();
            if (firstUnrated > -1) {
                position = firstUnrated;
            }
            final int lim = population.size();
            if (position >= lim) {
                position = lim - 1;
            }
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
     * A layout listener to get noticed when the layout is done.
     * 
     * @author bdu
     * 
     */
    private abstract static class LayoutListener implements ILayoutListener {
        public abstract void layoutPerformed(
                final KNode layoutGraph, final IKielerProgressMonitor monitor);
        
        public void layoutRequested(final KNode layoutGraph) {
            System.out.println("LayoutListener: Layout requested.");
        }
    }
    
    private final ILayoutListener layoutListener = new LayoutListener() {
        @Override
        public void layoutPerformed(final KNode layoutGraph, final IKielerProgressMonitor monitor) {
            System.out.println("LayoutListener: Layout performed.");
            try {
                int rating = measureDiagram(false, layoutGraph);
                final Individual ind = getCurrentIndividual();
                System.out.println("Assign rating " + rating + " to individual " + ind.toString());
                ind.setRating(rating);
                // tableViewer.refresh();
                Thread.sleep(700);
            } catch (final Exception e) {
                e.printStackTrace();
            }
        }
    };
    
    /**
     * Return position of first unrated individual in the list.
     * 
     * @return {@code -1} if no unrated individual exists.
     */
    private int firstUnrated() {
        if (population == null) {
            return -1;
        }
        int result = -1;
        for (int i = 0; i < population.size(); i++) {
            final Individual ind = population.get(i);
            if (ind.getRating() == 0) {
                result = i;
                break;
            }
        }
        return result;
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
            Thread.sleep(600);
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }
        // need a layout listener to wait for the layouting to be
        // finished and applied before measuring it.
        final Registry registry = LayoutServices.getRegistry();
        registry.addLayoutListener(layoutListener);
        layoutDiagram(false, false);
        System.out.println("after layoutDiagram");
        try {
            Thread.sleep(300);
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("remove layout listener");
        registry.removeLayoutListener(layoutListener);
        tableViewer.refresh();
    }
    
    /**
     * Layout the diagram.
     * 
     * @param showAnimation
     * @param showProgressBar
     */
    private void layoutDiagram(final boolean showAnimation, final boolean showProgressBar) {
        System.out.println("in layoutDiagram");
        if ((propertySource != null) && (population != null)) {
            final IEditorPart editor = getCurrentEditor();
            if (editor == null) {
                // so we have nothing to layout.
                return;
            }
            // final EditPart part = getEditPart(editor);
            EclipseLayoutServices.getInstance()
                    .layout(editor, null, showAnimation, showProgressBar);
        }
        System.out.println("leaving layoutDiagram");
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
     * Returns the current editor.
     * 
     * @return the current editor or {@code null} if none exists.
     */
    private IEditorPart getCurrentEditor() {
        final LayoutViewPart layoutViewPart = LayoutViewPart.findView();
        if (layoutViewPart == null) {
            // without the layoutViewPart, we cannot find the editor.
            return null;
        }
        final IEditorPart result = layoutViewPart.getCurrentEditor();
        return result;
    }
    
    private int measureDiagram(final boolean showProgressBar, final KNode parentNode) {
        System.out.println("measure diagram");
        if ((parentNode == null) || (propertySource == null) || (population == null)) {
            return 0;
        }
        final String[] metricIds =
                new String[] { "de.cau.cs.kieler.kiml.granatesting.bendsMetric",
                        "de.cau.cs.kieler.kiml.granatesting.flatnessMetric",
                        "de.cau.cs.kieler.kiml.granatesting.narrownessMetric" };
        final AnalysisServices as = AnalysisServices.getInstance();
        final List<AbstractInfoAnalysis> metricsList = new LinkedList<AbstractInfoAnalysis>();
        for (final String metricId : metricIds) {
            final AbstractInfoAnalysis metric = as.getAnalysisById(metricId);
            metricsList.add(metric);
        }
        final AbstractInfoAnalysis[] metrics =
                metricsList.toArray(new AbstractInfoAnalysis[metricsList.size()]);
        // DiagramAnalyser.analyse possibly uses obsolete layout graph
        // final Object[] results = DiagramAnalyser.analyse(editor, part,
        // metrics, showProgressBar);
        final double[] factors = new double[] { .4, .5, .1 };
        final Object[] results = DiagramAnalyser.analyse(parentNode, metrics, showProgressBar);
        final double bendsResult = Double.parseDouble(results[0].toString());
        final double flatnessResult = Double.parseDouble(results[1].toString());
        final double narrownessResult = Double.parseDouble(results[2].toString());
        System.out.println(bendsResult + "  " + flatnessResult + "  " + narrownessResult);
        final int newRating =
                (int) (((bendsResult * factors[0]) + (flatnessResult * factors[1]) + (narrownessResult * factors[2])) * 100);
        return newRating;
    }
    
    /**
     * 
     * @return the current individual, or {@code null} if none is selected.
     */
    private Individual getCurrentIndividual() {
        Assert.isTrue((position >= 0) && (position < population.size()), "position out of range");
        if ((population == null) || population.isEmpty()) {
            return null;
        }
        // ensure that the position is within the valid range
        if ((position >= population.size()) || (position < 0)) {
            return null;
        }
        return population.get(position);
    }
    
    /**
     * Adopts layout options from the given individual. The
     * <code>propertySource</code> needs to be valid. The given individual must
     * not be {@code null}.
     * 
     * @param theIndividual
     *            the individual
     * @param wantLayoutViewRefresh
     *            whether the layout view shall be refreshed
     */
    private void adoptIndividual(final Individual theIndividual, final boolean wantLayoutViewRefresh) {
        System.out.println("in adoptIndividual");
        Assert.isLegal(theIndividual != null);
        Assert.isNotNull(propertySource, "propertySource is null");
        if ((population == null) || population.isEmpty() || (propertySource == null)
                || theIndividual == null) {
            return;
        }
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
                this.propertySource.setPropertyValue(id, ((Boolean) theValue ? 1 : 0));
                break;
            case ENUM:
                try {
                    this.propertySource.setPropertyValue(id, theValue);
                } catch (final NullPointerException e) {
                    System.out.println("WARNING: enum property could not be set.");
                    Assert.isTrue(false);
                }
                break;
            default:
                this.propertySource.setPropertyValue(id, theValue.toString());
                break;
            }
        }
        // refresh layout view?
        if (wantLayoutViewRefresh) {
            final LayoutViewPart layoutView = LayoutViewPart.findView();
            if (layoutView != null) {
                final Runnable runnable = new Runnable() {
                    public void run() {
                        System.out.println("layoutView refresh start.");
                        try {
                            Thread.sleep(2000);
                        } catch (final InterruptedException e) {
                            e.printStackTrace();
                        }
                        layoutView.refresh(); // async!
                        System.out.println("layoutView refresh called.");
                    }
                };
                final Thread t = new Thread(runnable);
                t.start();
            }
        }
        System.out.println("leaving adoptIndividual");
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
}
