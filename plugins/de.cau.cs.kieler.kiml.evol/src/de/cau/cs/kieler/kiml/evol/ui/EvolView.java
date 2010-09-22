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
    }

    /**
     * A listener to the evolution model.
     */
    private final EvolModelListener modelListener = new EvolModelListener(this) {

        /**
         * {@inheritDoc}
         */
        public void afterChange(final EvolModel source, final ModelChangeType cause) {

            EvolPlugin.logStatus("afterChange: " + cause);

            Assert.isNotNull(source);

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
                Assert.isTrue(row == 0);
            }

            // synchronously
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
     * A Runnable that sets a population as input for a table viewer.
     *
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
                    final Object elementAtOldPos = this.tv.getElementAt(oldPos);
                    if ((elementAtOldPos != element)) {
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
        final Table table = new Table(parent, SWT.BORDER | SWT.BORDER_SOLID);

        final TableColumn column = new TableColumn(table, SWT.NONE);
        column.setWidth(WIDE_COLUMN_WIDTH);
        final TableColumn column2 = new TableColumn(table, SWT.NONE);
        column2.setWidth(NARROW_COLUMN_WIDTH);
        final TableColumn column3 = new TableColumn(table, SWT.NONE);
        column3.setWidth(WIDE_COLUMN_WIDTH);

        column.setText("Genome");
        column2.setText("Rating");
        column3.setText("Layout provider");

        table.setHeaderVisible(true);
        table.setLinesVisible(true);

        final SelectorTableViewer tv = new SelectorTableViewer(table);
        tv.setContentProvider(new PopulationTableContentProvider());
        tv.setLabelProvider(new PopulationTableLabelProvider(this));
        this.tableViewer = tv;

        final ISelectionChangedListener listener = new SelectionChangedListener(tv);
        tv.addPostSelectionChangedListener(listener);

        // Add the "multiple editors" toggle button. All the other buttons are
        // added later via extension point, so we can't change their order as we
        // would like.
        final IToolBarManager tm = this.getViewSite().getActionBars().getToolBarManager();
        final IAction multipleEditorsAction = new MultipleEditorsAction("Multiple editors");
        tm.add(multipleEditorsAction);

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
     * Asynchronously refreshes the view.
     *
     * @param onlyCurrent
     *            if set to {@code true}, only the currently selected entry is
     *            refreshed.
     */
    public void refresh(final boolean onlyCurrent) {

        Display.getDefault().asyncExec(new Runnable() {
            public void run() {
                final SelectorTableViewer viewer = getTableViewer();
                if (!onlyCurrent) {
                    viewer.refresh();
                }

                final int pos = getEvolModel().getPosition();
                final Object element = viewer.selectRowAndGetElement(pos);

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
