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
 * Test view for EvolPlugin.
 *
 * @author bdu
 *
 */
public class EvolView extends ViewPart {
    private final IEvolModelListener modelListener = new IEvolModelListener() {
        public void afterChange(final EvolModel source, final String cause) {
            System.out.println("afterChange: " + cause);
            if ("setPosition".equalsIgnoreCase(cause)) {
                System.out.println("setPosition occurred");
                return;
            }
            // TODO: what if currentEditor is null?
            if (EvolUtil.getCurrentEditor() == null) {
                System.err.println("We are not in the UI thread.");
            }

            // Refresh the table viewer.
            final SelectorTableViewer tv = EvolView.this.getTableViewer();

            final boolean isOnlyCurrent = ("changeCurrentRating".equalsIgnoreCase(cause));

            if (tv != null) {
                // Set the new population as input.
                EvolView.this.setInput(source.getPopulation());

                final int row = source.getPosition();
                if ("reset".equalsIgnoreCase(cause)) {
                    Assert.isTrue(source.getPosition() == 0);
                }

                MonitoredOperation.runInUI(new Runnable() {
                    public void run() {
                        tv.selectRow(row);
                        EvolView.this.refresh(isOnlyCurrent);
                    }
                }, true);
            }
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
        /** Creates a new {@link MultipleEditorsAction} instance.
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
    private static final class SetInputRunnable implements Runnable {
        /**
         *
         */
        private final Population population;
        /**
         *
         */
        private final TableViewer tableViewer;

        /**
         * Creates a new {@link SetInputRunnable} instance.
         *
         * @param thePopulation
         * @param theTableViewer
         */
        SetInputRunnable(final Population thePopulation, final TableViewer theTableViewer) {
            this.population = thePopulation;
            this.tableViewer = theTableViewer;
        }

        public void run() {
            this.tableViewer.setInput(this.population);
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

                // Update the table viewer.
                final Runnable elementUpdaterRunnable = new Runnable() {
                    public void run() {
                        SelectionChangedListener.this.getTableViewer().update(element, null);
                    }
                };
                MonitoredOperation.runInUI(elementUpdaterRunnable, true);

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
        protected void doSetSelection(final int[] indices) {
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

        final SelectorTableViewer tv = new SelectorTableViewer(table);
        tv.setContentProvider(new PopulationTableContentProvider());
        tv.setLabelProvider(new PopulationTableLabelProvider(this));
        this.tableViewer = tv;

        final ISelectionChangedListener listener = new SelectionChangedListener(tv);
        tv.addPostSelectionChangedListener(listener);

        final IToolBarManager tm = this.getViewSite().getActionBars().getToolBarManager();

        final IAction multipleEditorsAction = new MultipleEditorsAction("Multiple editors");

        // Add the toggle button
        tm.add(multipleEditorsAction);
        // XXX too bad all the other buttons are added later via extension point
        // so we can't change their order as we would like

        // Set the population as input in order to populate the view.
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

    /**
     * Refresh the layout according to selected individual.
     *
     * @deprecated
     */
    @Deprecated
    private void applySelectedIndividual() {
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

        // Adopt, layout and measure the current individual.
        EvolUtil.syncApplyIndividual(currentIndividual, expectedLayoutProviderId);

        // Refresh the layout view.
        EvolUtil.asyncRefreshLayoutView();
    }

    /**
     * Sets a population as the input of the viewer. Has no effect if the
     * population is {@code null} or if the table viewer is {@code null}.
     *
     * @param thePopulation
     *            new source population
     */
    void setInput(final Population thePopulation) {
        Assert.isNotNull(this.evolModel);

        final SelectorTableViewer tv = this.getTableViewer();

        if (((thePopulation != null) && (tv != null))) {
            final Population modelPop = this.evolModel.getPopulation();
            Assert.isTrue((thePopulation.equals(modelPop)),
                    "Attempt to set a population that is not in the model.");

            final Runnable runnable = new SetInputRunnable(thePopulation, tv);
            MonitoredOperation.runInUI(runnable, true);
        }
    }

}
