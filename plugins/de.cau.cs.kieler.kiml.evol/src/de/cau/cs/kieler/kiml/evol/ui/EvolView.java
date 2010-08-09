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
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.part.ViewPart;

import de.cau.cs.kieler.core.ui.util.MonitoredOperation;
import de.cau.cs.kieler.kiml.evol.EvolUtil;
import de.cau.cs.kieler.kiml.evol.genetic.Genome;
import de.cau.cs.kieler.kiml.evol.genetic.Population;
import de.cau.cs.kieler.kiml.ui.views.LayoutViewPart;

/**
 * Test view for EvolPlugin.
 *
 * @author bdu
 *
 */
public class EvolView extends ViewPart {
    /**
     * @author bdu
     *
     */
    private static final class IndividualApplier implements Runnable {
        /**
         * Creates a new {@link IndividualApplier} instance.
         *
         * @param theExpectedLayoutProviderId
         * @param theCurrentIndividual
         */
        IndividualApplier(
                final String theExpectedLayoutProviderId,
                final Genome theCurrentIndividual) {
            this.expectedLayoutProviderId = theExpectedLayoutProviderId;
            this.currentIndividual = theCurrentIndividual;
        }
        /**
         *
         */
        private final String expectedLayoutProviderId;

        /**
         *
         */
        private final Genome currentIndividual;

        public void run() {
            EvolUtil.applyIndividual(this.currentIndividual, this.expectedLayoutProviderId);
        }
    }

    /**
     * Refresher for the layout view.
     *
     * @author bdu
     *
     */
    private static class LayoutViewRefresher implements Runnable {
        /**
         *
         */
        public LayoutViewRefresher() {
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
     * @author bdu
     *
     */
    private final class SelectionChangedListener implements ISelectionChangedListener {
        /**
         * Creates a new {@link SelectionChangedListener} instance.
         *
         * @param theTableViewer
         */
        SelectionChangedListener(final SelectorTableViewer theTableViewer) {
            this.tv = theTableViewer;
        }

        private final SelectorTableViewer tv;
        private Object oldElement;

        public synchronized void selectionChanged(final SelectionChangedEvent event) {
            final ISelection selection = event.getSelection();
            System.out.println("selectionChanged");

            if ((selection == null) || (selection.isEmpty())
                    || !(selection instanceof IStructuredSelection)) {
                System.out.println("empty or null selection");
                return;
            }

            final Object element = ((IStructuredSelection) selection).getFirstElement();
            if (element instanceof PopulationTableEntry) {
                this.tv.removeSelectionChangedListener(this);

                // Update the model.
                final int oldPos = EvolView.this.getEvolModel().getPosition();
                final int newPos = ((PopulationTableEntry) element).getIndex();
                EvolView.this.getEvolModel().setPosition(newPos);

                MonitoredOperation.runInUI(new Runnable() {
                    public void run() {
                        SelectionChangedListener.this.getTableViewer().update(element, null);
                    }
                }, true);

                // Refresh the layout according to the selected individual.
                System.out.println("before applySelectedIndividual");
                applySelectedIndividual();
                System.out.println("after applySelectedIndividual");
                System.out.println(oldPos + " -> " + newPos);

                if (this.oldElement == null) {
                    this.oldElement = element;
                }

                System.out.println("updating row");
                this.tv.update(this.oldElement, null);
                final Object oldElement1 = this.tv.getElementAt(oldPos);
                this.tv.update(oldElement1, null);

                this.tv.update(element, null);

                this.tv.addPostSelectionChangedListener(this);
                System.out.println();
                this.oldElement = element;
            }
        }

        SelectorTableViewer getTableViewer() {
            return this.tv;
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

        /**
         * @param row
         *            zero-relative row index
         * @return the element that is in the selected row
         */
        public Object selectRowAndGetElement(final int row) {
            selectRow(row);
            return getSelectedElement();
        }

        @Override
        protected void doSetSelection(final int[] indices) {
            super.doSetSelection(indices);
        }

        /**
         * @param row
         *            zero-relative row index
         */
        void selectRow(final int row) {
            if (getPopulation() == null) {
                return;
            }
            Assert.isTrue((row >= 0) && (row <= getPopulation().size()), "position out of range");
            Display.getCurrent().syncExec(new Runnable() {
                public void run() {
                    final int[] indices = new int[] { row };
                    // SelectorTableViewer.this.doSelect(indices);
                    SelectorTableViewer.this.doSetSelection(indices);
                }
            });
        }

        /**
         * @return
         */
        private Object getSelectedElement() {
            final IStructuredSelection selection = (IStructuredSelection) this.getSelection();
            final Object element = selection.getFirstElement();
            return element;
        }

    }

    /**
     * Identifier for the EvolView.
     */
    public static final String ID = "de.cau.cs.kieler.kiml.evol.evolView";

    /**
     * Column width for columns in viewer table.
     */
    private static final int DEFAULT_COLUMN_WIDTH = 150;

    /**
     *
     */
    public EvolView() {
        super();
        this.tableViewer = null;
    }

    // private fields
    private SelectorTableViewer tableViewer;
    private final EvolModel evolModel = new EvolModel();

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
        tv.setLabelProvider(new PopulationTableLabelProvider(this));
        this.tableViewer = tv;
        reset();

        final ISelectionChangedListener listener = new SelectionChangedListener(tv);
        tv.addPostSelectionChangedListener(listener);
    }

    /**
     * Performs a step of the evolutionary algorithm.
     */
    public void evolve() {
        Assert.isNotNull(this.evolModel);
        if (!this.evolModel.isValid()) {
            return;
        }

        this.evolModel.evolve();

        setInput(this.evolModel.getPopulation());
    }

    /**
     * @return the evolution model that is displayed in the view.
     */
    public EvolModel getEvolModel() {
        return this.evolModel;
    }

    /**
     *
     * @return a shallow copy of the current population.
     */
    public Population getPopulation() {
        if (this.evolModel == null) {
            return null;
        }
        final Population pop = this.evolModel.getPopulation();
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

    /**
     * Refreshes the view. Must be run in UI thread.
     *
     * @param onlyCurrent
     *            if set to {@code true}, only the current entry is refreshed.
     */
    public void refresh(final boolean onlyCurrent) {

        if (!onlyCurrent) {
            this.tableViewer.refresh();
        }

        final int pos = this.getEvolModel().getPosition();
        final Object element = this.tableViewer.selectRowAndGetElement(pos);

        if (element != null) {
            this.tableViewer.update(element, null);
        }
    }

    /**
     * Reset the population and restart the algorithm.
     */
    public void reset() {
        Assert.isNotNull(this.evolModel);

        this.evolModel.reset();

        if (EvolUtil.getCurrentEditor() != null) {
            setInput(this.evolModel.getPopulation());
        }

        this.tableViewer.selectRow(0);
        this.tableViewer.refresh();
    }

    @Override
    public void setFocus() {
        this.tableViewer.getControl().setFocus();
    }

    /**
     * Refresh the layout according to selected individual.
     */
    public void applySelectedIndividual() {
        Assert.isNotNull(this.evolModel);

        if (!this.evolModel.isValid()) {
            return;
        }

        // Get the current individual from the model.
        final Genome currentIndividual = this.evolModel.getCurrentIndividual();
        Assert.isNotNull(currentIndividual);

        // Get the expected layout provider id.
        final String expectedLayoutProviderId = this.evolModel.getLayoutProviderId();
        Assert.isNotNull(expectedLayoutProviderId);

        // Adopt and layout the current individual.
        MonitoredOperation.runInUI(new IndividualApplier(expectedLayoutProviderId,
                currentIndividual), true);

        // Refresh the layout view.
        MonitoredOperation.runInUI(new LayoutViewRefresher(), false);
    }

    /**
     * Sets a population as the input of the viewer.
     *
     * @param thePopulation
     *            new source population
     */
    private void setInput(final Population thePopulation) {
        Assert.isNotNull(this.evolModel);

        if ((thePopulation != null)) {

            final Population modelPop = this.evolModel.getPopulation();

            Assert.isTrue((thePopulation.equals(modelPop)));

            final Runnable runnable = new Runnable() {
                public void run() {
                    EvolView.this.getTableViewer().setInput(thePopulation);
                }
            };
            runnable.run();
        }
    }

}
