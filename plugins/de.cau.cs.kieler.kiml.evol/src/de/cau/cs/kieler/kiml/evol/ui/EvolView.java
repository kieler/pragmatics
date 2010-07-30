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

import java.util.ArrayList;
import java.util.Collection;

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
import de.cau.cs.kieler.kiml.evol.genetic.Population;
import de.cau.cs.kieler.kiml.ui.layout.DiagramLayoutManager;
import de.cau.cs.kieler.kiml.ui.layout.EclipseLayoutServices;
import de.cau.cs.kieler.kiml.ui.views.LayoutViewPart;

/**
 * Test view for EvolPlugin.
 *
 * @author bdu
 *
 */
public class EvolView extends ViewPart {
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

        void selectRow(final int pos) {
            if (getPopulation() == null) {
                return;
            }
            Assert.isTrue((pos >= 0) && (pos <= getPopulation().size()), "position out of range");
            Display.getCurrent().syncExec(new Runnable() {
                public void run() {
                    SelectorTableViewer.this.doSelect(new int[] { pos });
                }
            });
        }
    }

    /**
     * Determines which individuals of a population shall be affected by an
     * operation.
     *
     * @author bdu
     * @deprecated
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
        /**
         *
         */
        public LayoutViewRefresher() {
            // nothing to do here.
        }

        public void run() {
            System.out.println("layoutView refresh start.");
            final LayoutViewPart layoutView = LayoutViewPart.findView();
            if (layoutView != null) {
                try {
                    Thread.sleep(100);
                } catch (final InterruptedException e) {
                    e.printStackTrace();
                }
                layoutView.refresh(); // async!
            }
            System.out.println("layoutView refresh called.");
        }
    }

    /**
     * Provides labels for LayoutSet table.
     *
     * @author bdu
     *
     */
    private class PopulationTableLabelProvider extends LabelProvider implements ITableLabelProvider {
        /**
         * Creates a new {@link PopulationTableLabelProvider}.
         */
        public PopulationTableLabelProvider() {
            // Nothing to do here.
        }

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
                final int pos = EvolView.this.getEvolModel().getPosition();
                if ((element instanceof PopulationTableEntry)
                        && (((PopulationTableEntry) element).getIndex() == pos)) {
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
     * Identifier for the EvolView.
     */
    public static final String ID = "de.cau.cs.kieler.kiml.evol.evolView";

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

    /**
     * Column width for columns in viewer table.
     */
    private static final int DEFAULT_COLUMN_WIDTH = 150;

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
            private Object oldElement;

            public synchronized void selectionChanged(final SelectionChangedEvent event) {
                final ISelection selection = event.getSelection();
                System.out.println("selectionChanged");
                if ((selection != null) && (!selection.isEmpty())
                        && (selection instanceof IStructuredSelection)) {
                    final Object element = ((IStructuredSelection) selection).getFirstElement();
                    if (element instanceof PopulationTableEntry) {
                        tv.removeSelectionChangedListener(this);
                        EvolView.this.getEvolModel().setPosition(
                                ((PopulationTableEntry) element).getIndex());
                        onSelectIndividual();
                        System.out.println("after onSelectIndividual");

                        if (this.oldElement != null) {
                            tv.update(this.oldElement, null);
                        } else {
                            tv.refresh();
                        }
                        tv.update(element, null);
                        tv.addPostSelectionChangedListener(this);
                        System.out.println();
                        this.oldElement = element;
                    }
                } else {
                    System.out.println("empty or null selection");
                }
            }
        };
        tv.addPostSelectionChangedListener(listener);
    }

    /**
     * Performs a step of the evolutionary algorithm.
     */
    public void evolve() {
        if (!this.evolModel.isValid()) {
            return;
        }

        final BasicEvolutionaryAlgorithm alg = this.evolModel.getEvolAlg();

        Assert.isNotNull(alg);

        this.evolModel.step();

        this.evolModel.selectInterestingIndividual();

        setInput(this.evolModel.getPopulation());

        EvolUtil.autoRateIndividuals(getPopulation().select(Population.UNRATED_FILTER), null, null);

        Assert.isTrue(this.evolModel.getPosition() >= 0);
        getTableViewer().selectRow(this.evolModel.getPosition());
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
     * Reset the population and restart the algorithm.
     */
    public void reset() {
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
    void onSelectIndividual() {
        if (!this.evolModel.isValid()) {
            return;
        }

        // Get the current individual from the model.
        final Genome currentIndividual = this.evolModel.getCurrentIndividual();
        Assert.isNotNull(currentIndividual);

        // Get the expected layout provider id.
        final String expectedLayoutProviderId = this.evolModel.getLayoutProviderId();

        // Get the current editor.
        final IEditorPart currentEditor = EvolUtil.getCurrentEditor();

        final boolean wantAllEditors = true;

        final Collection<IEditorPart> editors;
        if (wantAllEditors) {
            editors = EvolUtil.getEditors();
        } else {
            editors = new ArrayList<IEditorPart>(1);
            if (currentEditor != null) {
                editors.add(currentEditor);
            }
        }

        // do the layout in all the selected editors
        for (final IEditorPart editor : editors) {
            System.out.println("Editor: " + editor.getTitle());

            final EditPart editPart = EvolUtil.getEditPart(editor);

            // See which layout provider suits for the editor.
            final String layoutProviderId = EvolUtil.getLayoutProviderId(editor, editPart);

            if (!expectedLayoutProviderId.equalsIgnoreCase(layoutProviderId)) {
                // The editor is not compatible to the current population.
                // --> skip it
                System.out.println("Cannot adopt to " + layoutProviderId);
                continue;
            }

            // Use the options that are encoded in the individual.
            EvolUtil.adoptIndividual(currentIndividual, editor);

            // Refresh the layout view.
            MonitoredOperation.runInUI(new LayoutViewRefresher(), false);

            // We don't specify the edit part because we want a manager for
            // the whole diagram.
            final DiagramLayoutManager manager =
                    EclipseLayoutServices.getInstance().getManager(editor, null);

            final int rating = EvolUtil.layoutAndMeasure(manager, editor);

            if ((editor == currentEditor) && !currentIndividual.hasUserRating()) {
                currentIndividual.setUserRating(rating);
            }

            // Apply the layout to the diagram.
            // XXX it would be more straightforward to call
            // manager.applyLayout()
            // directly, but that method is private
            EclipseLayoutServices.getInstance().layout(editor, null, false, false);
        }
    }

    /**
     * Sets a population as the input of the viewer.
     *
     * @param thePopulation
     *            new source population
     */
    private void setInput(final Population thePopulation) {
        if ((thePopulation != null)) {
            // this.evolModel.setPopulation(thePopulation);

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
