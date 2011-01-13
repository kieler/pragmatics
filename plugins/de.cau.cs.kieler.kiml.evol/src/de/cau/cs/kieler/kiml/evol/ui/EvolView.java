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
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import de.cau.cs.kieler.core.ui.util.MonitoredOperation;
import de.cau.cs.kieler.kiml.evol.EvolModel;
import de.cau.cs.kieler.kiml.evol.EvolPlugin;
import de.cau.cs.kieler.kiml.evol.EvolUtil;
import de.cau.cs.kieler.kiml.evol.genetic.Population;

/**
 * View for EvolPlugin. This view shows a population table and a tool bar.
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
        /**
         * A Runnable that sets a population as input for a table viewer.
         *
         * @author bdu
         *
         */
        private static final class InputSetterRunnable implements Runnable {
            /**
             * The population that shall be used as input.
             */
            private final Population population;
            /**
             * The target table viewer.
             */
            private final TableViewer tableViewer;

            /**
             * Creates a new {@link InputSetterRunnable} instance.
             *
             * @param thePopulation
             *            the population that shall be set as new input for the
             *            table viewer
             * @param theTableViewer
             *            the table viewer
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
         * The associated view.
         */
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
         *            the view that the listener shall be associated to.
         *
         */
        public EvolModelListener(final EvolView theEvolView) {
            this.evolView = theEvolView;
        }

        /**
         * Synchronously sets the specified population as input for the
         * specified table viewer.
         * 
         * @param pop
         *            the population
         * @param tv
         *            the table viewer
         */
        void populationChange(final Population pop, final SelectorTableViewer tv) {
            if (pop != null) {
                final Runnable inputSetterRunnable = new InputSetterRunnable(pop, tv);
                MonitoredOperation.runInUI(inputSetterRunnable, true /* synch */);
            }
        }
    }

    /**
     * A listener to the evolution model.
     */
    private final EvolModelListener modelListener = new EvolModelListener(this) {

        /**
         * {@inheritDoc}
         */
        public void afterChange(final EvolModel source, final ModelChangeType cause) {
            if (source == null) {
                throw new IllegalArgumentException("Argument must not be null.");
            }

            EvolPlugin.logStatus("afterChange: " + cause);

            if (EvolUtil.getCurrentEditor() == null) {
                System.err.println("There is no current editor: " + cause);
                // TODO: eliminate these cases
            }

            if (cause == ModelChangeType.SET_POSITION) {
                EvolUtil.applyCurrentIndividual(source);
                // nothing more needs to be done
                return;
            }

            // Refresh the table viewer.
            final SelectorTableViewer tv = EvolView.this.getTableViewer();
            if (tv == null) {
                System.err.println("tableViewer is null");
                return;
            }

            // Is only the current entry affected?
            final boolean isOnlyCurrent = false;

            if (!isOnlyCurrent) {
                // Set the new population as input.

                final Population newPop = source.getPopulation();
                populationChange(newPop, tv);
            }

            final int row = source.getPosition();
            if (cause == ModelChangeType.RESET) {
                assert row == 0;
            }

            // synchronously
            MonitoredOperation.runInUI(new Runnable() {
                public void run() {
                    tv.selectRow(row);
                    getEvolView().refresh(isOnlyCurrent);
                }
            }, true /* synch */);
        }
    };

    /**
     * Action for toggling between "multiple editors" and "single editor" mode.
     *
     * @author bdu
     *
     */
    private static final class MultipleEditorsAction extends Action {
        /**
         * Creates a new {@link MultipleEditorsAction} instance.
         *
         * @param theText
         *            the string used as the text for the action, or null if
         *            there is no text
         */
        MultipleEditorsAction(final String theText) {
            super(theText);
            setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(EvolPlugin.PLUGIN_ID,
                    "icons/multiple16.png"));
            String currentValue =
                    EvolPlugin.getDefault().getPreferenceStore()
                            .getString(EvolPlugin.PREF_EDITORS);
            setChecked(EvolPlugin.ALL_EDITORS.equals(currentValue));
        }

        @Override
        public void run() {
            IPreferenceStore store = EvolPlugin.getDefault().getPreferenceStore();

            String newValue = (isChecked()) ? EvolPlugin.ALL_EDITORS : EvolPlugin.CURRENT_EDITOR;

            store.setValue(EvolPlugin.PREF_EDITORS, newValue);
        }
    }

    /**
     * An {@link ISelectionChangedListener} that is supposed to update the
     * evolution model whenever the selection in the population table viewer
     * changes.
     *
     * @author bdu
     *
     */
    private final class SelectionChangedListener implements ISelectionChangedListener {
        /**
         * Creates a new {@link SelectionChangedListener} instance.
         *
         * @param theTableViewer
         *            the associated table viewer
         */
        SelectionChangedListener(final SelectorTableViewer theTableViewer) {
            this.tv = theTableViewer;
        }

        private final SelectorTableViewer tv;
        private Object oldElement;

        /**
         * {@inheritDoc}
         */
        public synchronized void selectionChanged(final SelectionChangedEvent event) {
            ISelection selection = event.getSelection();
            System.out.println("selectionChanged");

            if (!(selection instanceof IStructuredSelection) || (selection.isEmpty())) {
                System.err.println("empty or null selection");
                return;
            }

            Object element = ((IStructuredSelection) selection).getFirstElement();
            if (element instanceof PopulationTableEntry) {
                this.tv.removeSelectionChangedListener(this);

                // Update the model.
                EvolModel em = EvolView.this.getEvolModel();
                int oldPos = em.getPosition();
                int newPos = ((PopulationTableEntry) element).getIndex();
                em.setPosition(newPos);

                if (oldPos != newPos) {
                    // Update the table viewer.
                    Object elementAtOldPos = this.tv.getElementAt(oldPos);
                    if (elementAtOldPos != element) {
                        this.tv.update(elementAtOldPos, null);
                    }
                } else if ((this.oldElement != null) && (this.oldElement != element)) {
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
            int itemCount = doGetItemCount();
            if (itemCount == 0) {
                return;
            }

            assert ((row >= 0) && (row < itemCount)) : "position out of range";
            Display.getCurrent().syncExec(new Runnable() {
                public void run() {
                    int[] indices = new int[] { row };
                    doSetSelection(indices);
                }
            });
        }

        /**
         * @return
         */
        private Object getSelectedElement() {
            IStructuredSelection selection = (IStructuredSelection) this.getSelection();
            Object element = selection.getFirstElement();
            return element;
        }
    }

    /**
     * Identifier for the EvolView.
     */
    public static final String ID = "de.cau.cs.kieler.kiml.evol.evolView";

    /**
     * Width for wide columns in the viewer table.
     */
    private static final int WIDE_COLUMN_WIDTH = 150;

    /**
     * Width for narrow columns in the viewer table.
     */
    private static final int NARROW_COLUMN_WIDTH = 60;

    /**
     * Creates a new {@link EvolView} instance.
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
        Table table = new Table(parent, SWT.BORDER | SWT.BORDER_SOLID);

        TableColumn column = new TableColumn(table, SWT.NONE);
        column.setWidth(WIDE_COLUMN_WIDTH);
        TableColumn column2 = new TableColumn(table, SWT.CENTER);
        column2.setWidth(NARROW_COLUMN_WIDTH);
        TableColumn column3 = new TableColumn(table, SWT.NONE);
        column3.setWidth(WIDE_COLUMN_WIDTH);

        column.setText("Genome");
        column2.setText("Rating");
        column3.setText("Layout Provider");

        table.setHeaderVisible(true);
        table.setLinesVisible(true);

        SelectorTableViewer tv = new SelectorTableViewer(table);
        tv.setContentProvider(new PopulationTableContentProvider());
        tv.setLabelProvider(new PopulationTableLabelProvider(this.evolModel));
        this.tableViewer = tv;

        ISelectionChangedListener listener = new SelectionChangedListener(tv);
        tv.addPostSelectionChangedListener(listener);

        // Add the "multiple editors" toggle button. All the other buttons are
        // added later via extension point, so we can't change their order as we
        // would like.
        IToolBarManager tm = this.getViewSite().getActionBars().getToolBarManager();
        IAction multipleEditorsAction = new MultipleEditorsAction("Multiple editors");
        tm.add(multipleEditorsAction);

        // Set the population as input in order to populate the view.
        // this.modelListener.populationChange(this.evolModel.getPopulation(),
        // this.tableViewer);
        EvolPlugin.logStatus("part control created.");

        // Make the selection available to the workbench
        getSite().setSelectionProvider(tv);
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
     * Asynchronously refreshes the view.
     *
     * @param onlyCurrent
     *            if set to {@code true}, only the currently selected entry is
     *            refreshed.
     */
    public void refresh(final boolean onlyCurrent) {

        Display.getDefault().asyncExec(new Runnable() {
            public void run() {
                SelectorTableViewer viewer = getTableViewer();
                if (!onlyCurrent) {
                    viewer.refresh();
                }

                int pos = getEvolModel().getPosition();
                Object element = viewer.selectRowAndGetElement(pos);

                if (element != null) {
                    viewer.update(element, null);
                }
            }
        });
    }

    @Override
    public void setFocus() {
        this.tableViewer.getControl().setFocus();
    }

}
