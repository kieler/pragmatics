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

import org.eclipse.core.runtime.Assert;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ILabelProviderListener;
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

import de.cau.cs.kieler.kiml.evol.alg.AbstractEvolutionaryAlgorithm;
import de.cau.cs.kieler.kiml.evol.alg.DefaultEvolutionaryAlgorithm;
import de.cau.cs.kieler.kiml.grana.AbstractInfoAnalysis;
import de.cau.cs.kieler.kiml.grana.AnalysisServices;
import de.cau.cs.kieler.kiml.grana.ui.DiagramAnalyser;
import de.cau.cs.kieler.kiml.layout.LayoutOptionData;
import de.cau.cs.kieler.kiml.layout.LayoutServices;
import de.cau.cs.kieler.kiml.ui.layout.DiagramLayoutManager;
import de.cau.cs.kieler.kiml.ui.views.GmfLayoutPropertySource;
import de.cau.cs.kieler.kiml.ui.views.LayoutViewPart;

/**
 * Test view for EvolPlugin.
 * 
 * @author bdu
 * 
 */
public class TestView extends ViewPart {
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
        final IWorkbench workbench = PlatformUI.getWorkbench();
        final IWorkbenchPage page = workbench.getActiveWorkbenchWindow().getActivePage();
        IEditorPart editor = null;
        if (page != null) {
            editor = page.getActiveEditor();
        }
        IGraphicalEditPart part = null;
        if (editor != null) {
            // TODO: test whether editor is for KIML
            final ISelection selection = editor.getEditorSite().getSelectionProvider()
                    .getSelection();
            Object element = null;
            if (selection != null) {
                if (selection instanceof StructuredSelection) {
                    element = ((StructuredSelection) selection).getFirstElement();
                    if (element instanceof IGraphicalEditPart) {
                        part = (IGraphicalEditPart) element;
                    }
                }
            }
            final GmfLayoutPropertySource source = new GmfLayoutPropertySource(part);
            propertySource = source;
            final Population sourcePopulation = EvolUtil.createPopulation(source,
                    DEFAULT_INITIAL_POPULATION_SIZE);
            evolAlg = new DefaultEvolutionaryAlgorithm(sourcePopulation);
            evolAlg.step();
            setInput(((DefaultEvolutionaryAlgorithm) evolAlg).getPopulation());
        }
        createToolBar();
        final ISelectionChangedListener listener = new ISelectionChangedListener() {
            public void selectionChanged(final SelectionChangedEvent event) {
                final ISelection selection = event.getSelection();
                System.out.println("selectionChanged");
                if ((selection != null) && (!selection.isEmpty())
                        && (selection instanceof IStructuredSelection)) {
                    final Object element = ((IStructuredSelection) selection).getFirstElement();
                    if (element instanceof LayoutTableEntry) {
                        int oldPosition = position;
                        position = ((LayoutTableEntry) element).index;
                        if (position != oldPosition) {
                            // tableViewer.selectRow(position);
                            tableViewer.refresh();
                        }
                        refreshLayout();
                        System.out.println();
                    }
                }
            }
        };
        tableViewer.addSelectionChangedListener(listener);
    }

    @Override
    public void setFocus() {
        tableViewer.getControl().setFocus();
    }

    // private fields
    private SelectorTableViewer tableViewer;
    private int position = 0;
    private GmfLayoutPropertySource propertySource;
    private AbstractEvolutionaryAlgorithm evolAlg;
    private Population population;
    /**
     * column width for columns in viewer table.
     */
    private static final int DEFAULT_COLUMN_WIDTH = 140;
    private static final int DEFAULT_INITIAL_POPULATION_SIZE = 5;

    /**
     * Sets a population as the input of the viewer.
     * 
     * @param sourcePopulation
     */
    private void setInput(final Population sourcePopulation) {
        if ((sourcePopulation != null) && (sourcePopulation != population)) {
            population = sourcePopulation;
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
            Population inputPopulation;
            if (!(inputElement instanceof Population)) {
                return new LayoutTableEntry[] {};
            }
            inputPopulation = (Population) inputElement;
            final LayoutTableEntry[] result = new LayoutTableEntry[inputPopulation.size()];
            int i = 0;
            for (final Individual individual : inputPopulation) {
                result[i] = new LayoutTableEntry();
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
            // System.out.println("Viewer " + viewer.toString() +
            // " input changed.");
        }
    }

    /**
     * provides labels for LayoutSet table.
     * 
     * @author bdu
     * 
     */
    private class LayoutSetLabelProvider extends LabelProvider implements ITableLabelProvider {
        private final Image currentImage = AbstractUIPlugin.imageDescriptorFromPlugin(
                EvolPlugin.PLUGIN_ID, "icons/current.png").createImage();
        private final Image defaultImage = AbstractUIPlugin.imageDescriptorFromPlugin(
                EvolPlugin.PLUGIN_ID, "icons/default.png").createImage();

        public Image getColumnImage(final Object element, final int columnIndex) {
            switch (columnIndex) {
            case 0:
                if ((element instanceof LayoutTableEntry)
                        && (((LayoutTableEntry) element).index == position)) {
                    return currentImage;
                }
                return defaultImage;
            default:
                return null;
            }
        }

        // TODO: use CellLabelProviders
        public String getColumnText(final Object element, final int columnIndex) {
            //
            switch (columnIndex) {
            case 0:
                return ((LayoutTableEntry) element).getId();
            case 1:
                final Individual individual = ((LayoutTableEntry) element).getIndividual();
                return (individual.getGenome().size() + " genes, rating: " + individual.getRating());
            default: // do nothing
                return null;
            }
        }

        @Override
        public void addListener(final ILabelProviderListener listener) {
            // TODO Auto-generated method stub
        }

        @Override
        public void dispose() {
            currentImage.dispose();
            defaultImage.dispose();
        }

        @Override
        public boolean isLabelProperty(final Object element, final String property) {
            return false;
        }

        @Override
        public void removeListener(final ILabelProviderListener listener) {
            // TODO Auto-generated method stub
        }
    }

    /**
     * A layout table entry contains an individual.
     * 
     * @author bdu
     * 
     */
    private static class LayoutTableEntry {
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

    // /**
    // * Action for moving to next table entry.
    // *
    // * @author bdu
    // *
    // */
    // private class MoveNextAction extends Action {
    // public MoveNextAction() {
    // setText("Down");
    // }
    //
    // public void run() {
    // if (propertySource == null) {
    // return;
    // }
    // if (population != null && position < population.size() - 1) {
    // position++;
    // tableViewer.selectRow(position);
    // tableViewer.refresh();
    // refreshLayoutViewPart();
    // layoutDiagram(false, true);
    // }
    // }
    // }
    // /**
    // * Action for moving to previous table entry.
    // *
    // * @author bdu
    // *
    // */
    // private class MovePrevAction extends Action {
    // public MovePrevAction() {
    // setText("Up");
    // }
    //
    // public void run() {
    // if (position > 0) {
    // position--;
    // tableViewer.selectRow(position);
    // tableViewer.refresh();
    // refreshLayoutViewPart();
    // layoutDiagram(false, true);
    // }
    // }
    // }
    /**
     * Action for resetting the view.
     * 
     * @author bdu
     * 
     */
    private class ResetAction extends Action {
        public ResetAction() {
            setText("Reset");
        }

        @Override
        public void run() {
            position = 0;
            final LayoutViewPart layoutViewPart = LayoutViewPart.findView();
            final IEditorPart editor = layoutViewPart.getCurrentEditor();
            IGraphicalEditPart part = null;
            if (editor != null) {
                final ISelection selection = editor.getEditorSite().getSelectionProvider()
                        .getSelection();
                Object element = null;
                if (selection != null) {
                    if (selection instanceof StructuredSelection) {
                        element = ((StructuredSelection) selection).getFirstElement();
                        if (element instanceof IGraphicalEditPart) {
                            part = (IGraphicalEditPart) element;
                        }
                    }
                }
            }
            propertySource = new GmfLayoutPropertySource(part);
            final Population newPopulation = EvolUtil.createPopulation(propertySource,
                    DEFAULT_INITIAL_POPULATION_SIZE);
            evolAlg = new DefaultEvolutionaryAlgorithm(newPopulation);
            evolAlg.step();
            setInput(((DefaultEvolutionaryAlgorithm) evolAlg).getPopulation());
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
        private static final int AMOUNT = +3;

        public PromoteAction() {
            super(AMOUNT);
            setText("Favor");
        }
    }

    /**
     * Action for giving a negative rating to an individual.
     * 
     * @author bdu
     * 
     */
    private class DemoteAction extends ChangeRatingAction {
        private static final int AMOUNT = -1;

        public DemoteAction() {
            super(AMOUNT);
            setText("Disregard");
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
            setInput(((DefaultEvolutionaryAlgorithm) evolAlg).getPopulation());
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
            refreshLayout();
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
                    tableViewer.doSelect(new int[] { position });
                }
            });
        }
    }

    /**
     * Return position of first unrated individual.
     * 
     * @return -1 if no unrated individual exists.
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
    private void refreshLayout() {
        refreshLayoutViewPart();
        layoutDiagram(false, false);
        measureDiagram(false);
    }
    
    /**
     * Layout the diagram.
     * 
     * @param showAnimation
     * @param showProgressBar
     */
    private void layoutDiagram(final boolean showAnimation, final boolean showProgressBar) {
        System.out.println("layout diagram");
        if ((propertySource != null) && (population != null)) {
            final LayoutViewPart layoutViewPart = LayoutViewPart.findView();
            if (layoutViewPart == null) {
                // without the layoutViewPart, we cannot find the editor.
                // so we have nothing to layout.
                return;
            }
            final IEditorPart editor = layoutViewPart.getCurrentEditor();
            IGraphicalEditPart part = null;
            if (editor != null) {
                final ISelection selection = editor.getEditorSite().getSelectionProvider()
                        .getSelection();
                Object element = null;
                if (selection != null) {
                    if (selection instanceof StructuredSelection) {
                        element = ((StructuredSelection) selection).getFirstElement();
                        if (element instanceof IGraphicalEditPart) {
                            part = (IGraphicalEditPart) element;
                        }
                    }
                }
            }
            // refreshLayoutViewPart();
            DiagramLayoutManager.layout(editor, part, showAnimation, showProgressBar);
        }
    }

    private void measureDiagram(final boolean showProgressBar) {
        System.out.println("measure diagram");
        if ((propertySource == null) || (population == null)) {
            return;
        }
        final LayoutViewPart layoutViewPart = LayoutViewPart.findView();
        if (layoutViewPart == null) {
            // without the layoutViewPart, we cannot find the editor.
            // so we have nothing to measure.
            return;
        }
        final IEditorPart editor = layoutViewPart.getCurrentEditor();
        IGraphicalEditPart part = null;
        if (editor != null) {
            final ISelection selection = editor.getEditorSite().getSelectionProvider()
                    .getSelection();
            Object element = null;
            if (selection != null) {
                if (selection instanceof StructuredSelection) {
                    element = ((StructuredSelection) selection).getFirstElement();
                    if (element instanceof IGraphicalEditPart) {
                        part = (IGraphicalEditPart) element;
                    }
                }
            }
        }
        final String bendsMetricId = "de.cau.cs.kieler.kiml.grana.bendsMetric";
        final String hCompactnessMetricId = "de.cau.cs.kieler.kiml.grana.horizontalCompactnessMetric";
        final AbstractInfoAnalysis bendsMetric = AnalysisServices.getInstance().getAnalysisById(
                bendsMetricId);
        final AbstractInfoAnalysis hCompactnessMetric = AnalysisServices.getInstance()
                .getAnalysisById(hCompactnessMetricId);
        final Object[] results = DiagramAnalyser.analyse(editor, part,
 new AbstractInfoAnalysis[] {
                bendsMetric, hCompactnessMetric }, showProgressBar);
        final int newRating = (int) ((Double.parseDouble(results[0].toString()) + Double
                .parseDouble(results[1].toString())) * 100);
        final Individual ind = getCurrentIndividual();
        ind.setRating(newRating);
        tableViewer.refresh();
    }

    private Individual getCurrentIndividual() {
        if ((population == null) || population.isEmpty()) {
            return null;
        }
        return population.get(position);
    }

    /**
     * Refreshes the layout view, if it exists. <code>population</code> and
     * <code>propertySource</code> need to be valid.
     */
    private void refreshLayoutViewPart() {
        System.out.println("refresh layout viewpart");
        Assert.isNotNull(population, "population is null");
        Assert.isNotNull(propertySource, "propertySource is null");
        // Assert.isTrue(population.size() > 0, "zero population");
        Assert.isTrue((position >= 0) && (position <= population.size()), "position out of range");
        if ((population == null) || population.isEmpty() || (propertySource == null)) {
            return;
        }
        // ensure that the position is not out of range
        if ((position > population.size()) || (position < 0)) {
            return;
        }
        final Individual currentIndividual = population.get(position);
        final Genome genome = currentIndividual.getGenome();
        final LayoutServices layoutServices = LayoutServices.getInstance();
        for (final IGene<?> gene : genome) {
            Assert.isNotNull(gene);
            final Object theValue = gene.getValue();
            final Object id = gene.getId();
            final LayoutOptionData data = layoutServices.getLayoutOptionData((String) id);
            Assert.isNotNull(data);
            switch (data.getType()) {
            case BOOLEAN:
                propertySource.setPropertyValue(id, ((Boolean) theValue ? 1 : 0));
                break;
            case ENUM:
                try {
                    propertySource.setPropertyValue(id, theValue);
                } catch (final NullPointerException e) {
                    System.out.println("WARNING: enum property could not be set.");
                    Assert.isTrue(false);
                }
                break;
            default:
                propertySource.setPropertyValue(id, theValue.toString());
                break;
            }
        }
        final LayoutViewPart layoutView = LayoutViewPart.findView();
        if (layoutView != null) {
            layoutView.refresh();
        }
    }

    /**
     * Creates the tool bar for the view.
     */
    private void createToolBar() {
        // Get tool bar manager.
        final IToolBarManager toolBarManager = getViewSite().getActionBars().getToolBarManager();
        // Create some actions and add them to tool bar manager.
        toolBarManager.add(new PromoteAction());
        toolBarManager.add(new DemoteAction());
        toolBarManager.add(new EvolveAction());
        toolBarManager.add(new ResetAction());
    }
}
