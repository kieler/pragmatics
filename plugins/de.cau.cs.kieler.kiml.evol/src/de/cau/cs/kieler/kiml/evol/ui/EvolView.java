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
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.preference.IPreferenceStore;
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
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import de.cau.cs.kieler.core.ui.util.MonitoredOperation;
import de.cau.cs.kieler.kiml.evol.EvolModel;
import de.cau.cs.kieler.kiml.evol.EvolPlugin;
import de.cau.cs.kieler.kiml.evol.EvolUtil;
import de.cau.cs.kieler.kiml.evol.genetic.Genome;
import de.cau.cs.kieler.kiml.evol.genetic.Population;

/**
 * View for EvolPlugin.
 *
 * @author bdu
 *
 */
public class EvolView extends ViewPart {
    /**
     * An implementation of {@link IEvolModelListener} that is associated to an
     * {@link EvolView}.
     * 
     * @author bdu
     * 
     */
    private abstract static class EvolModelListener implements IEvolModelListener {
        private final EvolView evolView;

        /**
         *
         * @return the associated {@link EvolView}
         */
        public EvolView getEvolView() {
            return this.evolView;
        }

        /**
         * Creates a new {@link EvolView.EvolModelListener} instance.
         *
         * @param theEvolView
         *
         */
        public EvolModelListener(final EvolView theEvolView) {
            this.evolView = theEvolView;
        }

        /**
         *
         * @param pop
         * @param tv
         */
        void populationChange(final Population pop, final SelectorTableViewer tv) {
            if (pop != null) {
                final Runnable inputSetterRunnable = new InputSetterRunnable(pop, tv);
                MonitoredOperation.runInUI(inputSetterRunnable, true);
            }
        }

        /**
         * Refreshes the layout according to the selected individual in the
         * model.
         *
         * @param em
         *            the evolution model
         *
         */
        protected void applySelectedIndividual(final EvolModel em) {
            Assert.isNotNull(em);

            if (!em.isValid()) {
                return;
            }

            // Get the current individual from the model.
            final Genome currentIndividual = em.getCurrentIndividual();
            Assert.isNotNull(currentIndividual);

            // Get the expected layout provider id.
            final String expectedLayoutProviderId = em.getLayoutProviderId();
            Assert.isNotNull(expectedLayoutProviderId);

            // Adopt, layout and measure the current individual.
            EvolUtil.syncApplyIndividual(currentIndividual, expectedLayoutProviderId);

            // Refresh the layout view, if it can be found.
            EvolUtil.asyncRefreshLayoutView();
        }

    }

    /**
     * A listener to the evolution model.
     */
    private final EvolModelListener modelListener = new EvolModelListener(this) {

        /**
         * {@inheritDoc}
         */
        public void afterChange(final EvolModel source, final String cause) {

            EvolPlugin.logStatus("afterChange: " + cause);

            Assert.isNotNull(source);

            if (EvolUtil.getCurrentEditor() == null) {
                System.err.println("We are not in the UI thread: " + cause);
                // TODO: eliminate these cases
            }

            if ("setPosition".equalsIgnoreCase(cause)) {
                applySelectedIndividual(source);
                return;
            }

            // Refresh the table viewer.
            final SelectorTableViewer tv = EvolView.this.getTableViewer();
            if (tv == null) {
                System.err.println("tableViewer is null");
                return;
            }

            final boolean isOnlyCurrent = ("changeCurrentRating".equalsIgnoreCase(cause));

            if (!isOnlyCurrent) {
                // Set the new population as input.

                final Population newPop = source.getPopulation();
                populationChange(newPop, tv);
            }

            final int row = source.getPosition();
            if ("reset".equalsIgnoreCase(cause)) {
                Assert.isTrue(row == 0);
            }

            MonitoredOperation.runInUI(new Runnable() {
                public void run() {
                    tv.selectRow(row);
                    getEvolView().refresh(isOnlyCurrent);
                }
            }, true);
        }
    };

    @Override
    public void init(final IViewSite theSite) throws PartInitException {
        super.init(theSite);

        // Reset the model.
        Assert.isNotNull(this.evolModel);
        this.evolModel.reset();
    }

    /**
     * @author bdu
     *
     */
    private static final class MultipleEditorsAction extends Action {
        /**
         * Creates a new {@link MultipleEditorsAction} instance.
         *
         * @param theText
         */
        MultipleEditorsAction(final String theText) {
            super(theText);
            setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(EvolPlugin.PLUGIN_ID,
                    "icons/multiple16.png"));
            final String currentValue =
                    EvolPlugin.getDefault().getPreferenceStore()
                            .getString(EvolPlugin.PREF_EDITORS);
            setChecked(EvolPlugin.ALL_EDITORS.equals(currentValue));
        }

        @Override
        public void run() {
            final IPreferenceStore store = EvolPlugin.getDefault().getPreferenceStore();

            final String newValue =
                    ((isChecked()) ? EvolPlugin.ALL_EDITORS : EvolPlugin.CURRENT_EDITOR);

            store.setValue(EvolPlugin.PREF_EDITORS, newValue);
        }
    }

    /**
     * @author bdu
     *
     */
    private static final class InputSetterRunnable implements Runnable {
        /**
         *
         */
        private final Population population;
        /**
         *
         */
        private final TableViewer tableViewer;

        /**
         * Creates a new {@link InputSetterRunnable} instance.
         *
         * @param thePopulation
         * @param theTableViewer
         */
        InputSetterRunnable(final Population thePopulation, final TableViewer theTableViewer) {
            this.population = thePopulation;
            this.tableViewer = theTableViewer;
        }

        /**
         * Sets the population as input for the table viewer.
         */
        public void run() {
            if (this.tableViewer.getInput() != this.population) {
                this.tableViewer.setInput(this.population);
            } else {
                EvolPlugin.showError("TableViewer: input already set.", null);
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
                System.err.println("empty or null selection");
                return;
            }

            final Object element = ((IStructuredSelection) selection).getFirstElement();
            if (element instanceof PopulationTableEntry) {
                this.tv.removeSelectionChangedListener(this);

                // Update the model.
                final EvolModel em = EvolView.this.getEvolModel();
                final int oldPos = em.getPosition();
                final int newPos = ((PopulationTableEntry) element).getIndex();
                em.setPosition(newPos);

                if (oldPos != newPos) {
                    // Update the table viewer.
                    final Object oldElement1 = this.tv.getElementAt(oldPos);
                    if ((oldElement1 != element) && (oldElement1 != this.oldElement)) {
                        this.tv.update(oldElement1, null);
                    }
                }

                if ((this.oldElement != null) && (this.oldElement != element)) {
                    this.tv.update(this.oldElement, null);
                }

                this.tv.update(element, null);

                this.tv.addPostSelectionChangedListener(this);
                System.out.println();
                this.oldElement = element;
            }
        }
    }

    /**
     * A table viewer with selectable rows.
     *
     * @author bdu
     *
     */
    private static class SelectorTableViewer extends TableViewer {
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
        public void doSetSelection(final int[] indices) {
            super.doSetSelection(indices);
        }

        /**
         * @param row
         *            zero-relative row index
         */
        void selectRow(final int row) {
            final int itemCount = doGetItemCount();
            if (itemCount == 0) {
                return;
            }

            Assert.isTrue((row >= 0) && (row < itemCount), "position out of range");
            Display.getCurrent().syncExec(new Runnable() {
                public void run() {
                    final int[] indices = new int[] { row };
                    doSetSelection(indices);
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

        this.evolModel.addListener(this.modelListener);
    }

    // private fields
    private SelectorTableViewer tableViewer;
    private final EvolModel evolModel = new EvolModel();

    // individual property settings
    @Override
    public void createPartControl(final Composite parent) {

        // create table view
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
        final TableColumn column3 = new TableColumn(table, SWT.NONE);
        column3.setWidth(DEFAULT_COLUMN_WIDTH);

        column.setText("Title1");

        table.setHeaderVisible(true);
        table.setLinesVisible(true);

        final SelectorTableViewer tv = new SelectorTableViewer(table);
        tv.setContentProvider(new PopulationTableContentProvider());
        tv.setLabelProvider(new PopulationTableLabelProvider(this));
        this.tableViewer = tv;

        final ISelectionChangedListener listener = new SelectionChangedListener(tv);
        tv.addPostSelectionChangedListener(listener);

        // Add the toggle button
        final IToolBarManager tm = this.getViewSite().getActionBars().getToolBarManager();
        final IAction multipleEditorsAction = new MultipleEditorsAction("Multiple editors");
        tm.add(multipleEditorsAction);
        // XXX too bad all the other buttons are added later via extension point
        // so we can't change their order as we would like

        // Set the population as input in order to populate the view.
        this.modelListener.populationChange(this.evolModel.getPopulation(), this.tableViewer);
    }

    /**
     * @return the evolution model that is displayed in the view.
     */
    public EvolModel getEvolModel() {
        return this.evolModel;
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

    @Override
    public void setFocus() {
        this.tableViewer.getControl().setFocus();
    }

}
