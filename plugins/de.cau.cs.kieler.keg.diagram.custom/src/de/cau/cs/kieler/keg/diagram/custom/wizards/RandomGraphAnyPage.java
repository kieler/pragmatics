/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.keg.diagram.custom.wizards;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;

import de.cau.cs.kieler.keg.diagram.custom.KEGDiagramPlugin;
import de.cau.cs.kieler.keg.diagram.custom.random.RandomGraphGenerator;

/**
 * The options page for the ANY graph type.
 * 
 * @author mri
 */
public class RandomGraphAnyPage extends WizardPage {

    /** the id of the NUMBER_OF_EDGES edge determination for the preferences. */
    public static final int EDGE_DETERMINATION_EDGES = 0;
    /** the id of the density edge determination for the preferences. */
    public static final int EDGE_DETERMINATION_DENSITY = 1;
    /** the id of the MIN/MAX_OUTGOING_EDGES edge determination for the preferences. */
    public static final int EDGE_DETERMINATION_OUTGOING = 2;

    /** the preference key for the density. */
    private static final String PREFERENCE_DENSITY = "randomWizard.density"; //$NON-NLS-1$

    /** the selected number of nodes. */
    private int numberOfNodes;
    /** the selected edge determination. */
    private int edgeDetermination;
    /** the selected number of edges. */
    private int numberOfEdges;
    /** the selected density. */
    private float density;
    /** the selected minimum number of outgoing edges. */
    private int minOutgoingEdges;
    /** the selected maximum number of outgoing edges. */
    private int maxOutgoingEdges;
    /** the selected self loop allowance. */
    private boolean selfLoops;
    /** the selected multi edges allowance. */
    private boolean multiEdges;
    /** the selected cycle allowance. */
    private boolean cycles;

    /**
     * Constructs a RandomGraphAnyPage.
     */
    public RandomGraphAnyPage() {
        super("randomGraphAnyPage"); //$NON-NLS-1$
        setTitle(Messages.RandomGraphAnyPage_title);
        setDescription(Messages.RandomGraphAnyPage_description);
        setDefaultPreferences();
        loadPreferences();
    }

    /**
     * {@inheritDoc}
     */
    public void createControl(final Composite parent) {
        Composite composite = new Composite(parent, SWT.NULL);
        GridLayout layout = new GridLayout();
        composite.setLayout(layout);
        createOptions(composite);
        setControl(composite);
    }

    // CHECKSTYLEOFF MagicNumber
    private void createOptions(final Composite parent) {
        Composite composite = new Composite(parent, SWT.NULL);
        GridData gridData = new GridData(SWT.FILL, SWT.NONE, true, false);
        composite.setLayoutData(gridData);
        GridLayout layout = new GridLayout();
        layout.numColumns = 3;
        layout.verticalSpacing = 9;
        composite.setLayout(layout);
        // add NUMBER_OF_NODES option
        // empty label to fill first column
        Label label = new Label(composite, SWT.NULL);
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        gridData.widthHint = 16;
        label.setLayoutData(gridData);
        label = new Label(composite, SWT.NULL);
        label.setText(Messages.RandomGraphAnyPage_number_of_nodes_caption);
        final Spinner nodesSpinner = new Spinner(composite, SWT.BORDER | SWT.SINGLE);
        Util.addHelp(nodesSpinner, Messages.RandomGraphAnyPage_number_of_nodes_help);
        nodesSpinner.setValues(numberOfNodes, 1, Integer.MAX_VALUE, 0, 1, 10);
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        gridData.widthHint = 80;
        nodesSpinner.setLayoutData(gridData);
        nodesSpinner.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                numberOfNodes = nodesSpinner.getSelection();
            }
        });
        // add NUMBER_OF_EDGES option
        Button edgesSwitch = new Button(composite, SWT.RADIO | SWT.LEFT);
        edgesSwitch.setText(Messages.RandomGraphAnyPage_number_of_edges_caption);
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        gridData.horizontalSpan = 2;
        edgesSwitch.setLayoutData(gridData);
        final Spinner edgesSpinner = new Spinner(composite, SWT.BORDER | SWT.SINGLE);
        Util.addHelp(edgesSpinner, Messages.RandomGraphAnyPage_number_of_edges_help);
        edgesSpinner.setValues(numberOfEdges, 0, Integer.MAX_VALUE, 0, 1, 10);
        edgesSpinner.setEnabled(false);
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        gridData.widthHint = 80;
        edgesSpinner.setLayoutData(gridData);
        edgesSpinner.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                numberOfEdges = edgesSpinner.getSelection();
            }
        });
        // add another way to specify the NUMBER_OF_EDGES option
        Button densitySwitch = new Button(composite, SWT.RADIO | SWT.LEFT);
        densitySwitch.setText(Messages.RandomGraphAnyPage_density_caption);
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        gridData.horizontalSpan = 2;
        densitySwitch.setLayoutData(gridData);
        final Spinner densitySpinner = new Spinner(composite, SWT.BORDER | SWT.SINGLE);
        Util.addHelp(densitySpinner, Messages.RandomGraphAnyPage_density_help);
        densitySpinner.setValues((int) (density * 100), 0, 100, 2, 1, 10);
        densitySpinner.setEnabled(false);
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        gridData.widthHint = 80;
        densitySpinner.setLayoutData(gridData);
        densitySpinner.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                density = ((float) densitySpinner.getSelection()) / 100f;
            }
        });
        // add MIN_OUTGOING_EDGES option
        Button outgoingSwitch = new Button(composite, SWT.RADIO | SWT.LEFT);
        outgoingSwitch.setText(Messages.RandomGraphAnyPage_min_outgoing_caption);
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        gridData.horizontalSpan = 2;
        outgoingSwitch.setLayoutData(gridData);
        final Spinner minOutSpinner = new Spinner(composite, SWT.BORDER | SWT.SINGLE);
        Util.addHelp(minOutSpinner, Messages.RandomGraphAnyPage_min_outgoing_help);
        minOutSpinner.setValues(minOutgoingEdges, 0, Integer.MAX_VALUE, 0, 1, 10);
        minOutSpinner.setEnabled(false);
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        gridData.widthHint = 80;
        minOutSpinner.setLayoutData(gridData);
        minOutSpinner.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                minOutgoingEdges = minOutSpinner.getSelection();
            }
        });
        // add MAX_OUTGOING_EDGES option
        // empty label to fill first column
        label = new Label(composite, SWT.NULL);
        label = new Label(composite, SWT.NULL);
        label.setText(Messages.RandomGraphAnyPage_max_outgoing_caption);
        final Spinner maxOutSpinner = new Spinner(composite, SWT.BORDER | SWT.SINGLE);
        Util.addHelp(maxOutSpinner, Messages.RandomGraphAnyPage_max_outgoing_help);
        maxOutSpinner.setValues(maxOutgoingEdges, 0, Integer.MAX_VALUE, 0, 1, 10);
        maxOutSpinner.setEnabled(false);
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        gridData.widthHint = 80;
        maxOutSpinner.setLayoutData(gridData);
        maxOutSpinner.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                maxOutgoingEdges = maxOutSpinner.getSelection();
            }
        });
        // add the switch functionality
        edgesSwitch.addSelectionListener(new SelectionListenerAdapter() {
            public void widgetSelected(final SelectionEvent e) {
                edgeDetermination = EDGE_DETERMINATION_EDGES;
                edgesSpinner.setEnabled(true);
                densitySpinner.setEnabled(false);
                minOutSpinner.setEnabled(false);
                maxOutSpinner.setEnabled(false);
            }
        });
        densitySwitch.addSelectionListener(new SelectionListenerAdapter() {
            public void widgetSelected(final SelectionEvent e) {
                edgeDetermination = EDGE_DETERMINATION_DENSITY;
                edgesSpinner.setEnabled(false);
                densitySpinner.setEnabled(true);
                minOutSpinner.setEnabled(false);
                maxOutSpinner.setEnabled(false);
            }
        });
        outgoingSwitch.addSelectionListener(new SelectionListenerAdapter() {
            public void widgetSelected(final SelectionEvent e) {
                edgeDetermination = EDGE_DETERMINATION_OUTGOING;
                edgesSpinner.setEnabled(false);
                densitySpinner.setEnabled(false);
                minOutSpinner.setEnabled(true);
                maxOutSpinner.setEnabled(true);
            }
        });
        switch (edgeDetermination) {
        case EDGE_DETERMINATION_DENSITY:
            densitySwitch.setSelection(true);
            densitySpinner.setEnabled(true);
            break;
        case EDGE_DETERMINATION_OUTGOING:
            outgoingSwitch.setSelection(true);
            minOutSpinner.setEnabled(true);
            maxOutSpinner.setEnabled(true);
            break;
        case EDGE_DETERMINATION_EDGES:
        default:
            edgesSwitch.setSelection(true);
            edgesSpinner.setEnabled(true);
            break;
        }
        // add SELF_LOOPS option
        final Button selfLoopsButton = new Button(composite, SWT.CHECK);
        Util.addHelp(selfLoopsButton, Messages.RandomGraphAnyPage_self_loops_help);
        selfLoopsButton.setText(Messages.RandomGraphAnyPage_self_loops_caption);
        selfLoopsButton.setSelection(selfLoops);
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        gridData.horizontalSpan = 3;
        selfLoopsButton.setLayoutData(gridData);
        selfLoopsButton.addSelectionListener(new SelectionListenerAdapter() {
            public void widgetSelected(final SelectionEvent e) {
                selfLoops = selfLoopsButton.getSelection();
            }
        });
        // add MULTI_EDGES option
        final Button multiEdgesButton = new Button(composite, SWT.CHECK);
        Util.addHelp(multiEdgesButton, Messages.RandomGraphAnyPage_multi_edges_help);
        multiEdgesButton.setText(Messages.RandomGraphAnyPage_multi_edges_caption);
        multiEdgesButton.setSelection(multiEdges);
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        gridData.horizontalSpan = 3;
        multiEdgesButton.setLayoutData(gridData);
        multiEdgesButton.addSelectionListener(new SelectionListenerAdapter() {
            public void widgetSelected(final SelectionEvent e) {
                multiEdges = multiEdgesButton.getSelection();
            }
        });
        // add CYCLES option
        final Button cyclesButton = new Button(composite, SWT.CHECK);
        Util.addHelp(cyclesButton, Messages.RandomGraphAnyPage_cycles_help);
        cyclesButton.setText(Messages.RandomGraphAnyPage_cycles_caption);
        cyclesButton.setSelection(cycles);
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        gridData.horizontalSpan = 3;
        cyclesButton.setLayoutData(gridData);
        cyclesButton.addSelectionListener(new SelectionListenerAdapter() {
            public void widgetSelected(final SelectionEvent e) {
                cycles = cyclesButton.getSelection();
            }
        });
    }

    // CHECKSTYLEON MagicNumber

    /**
     * Saves the selected options to the preference store.
     */
    public void savePreferences() {
        IPreferenceStore preferenceStore = KEGDiagramPlugin.getDefault().getPreferenceStore();
        preferenceStore.setValue(RandomGraphGenerator.NUMBER_OF_NODES.getId(), numberOfNodes);
        preferenceStore
                .setValue(RandomGraphGenerator.EDGE_DETERMINATION.getId(), edgeDetermination);
        preferenceStore.setValue(RandomGraphGenerator.NUMBER_OF_EDGES.getId(), numberOfEdges);
        preferenceStore.setValue(PREFERENCE_DENSITY, density);
        preferenceStore.setValue(RandomGraphGenerator.MIN_OUTGOING_EDGES.getId(), minOutgoingEdges);
        preferenceStore.setValue(RandomGraphGenerator.MAX_OUTGOING_EDGES.getId(), maxOutgoingEdges);
        preferenceStore.setValue(RandomGraphGenerator.SELF_LOOPS.getId(), selfLoops);
        preferenceStore.setValue(RandomGraphGenerator.MULTI_EDGES.getId(), multiEdges);
        preferenceStore.setValue(RandomGraphGenerator.CYCLES.getId(), cycles);
    }

    private void loadPreferences() {
        IPreferenceStore preferenceStore = KEGDiagramPlugin.getDefault().getPreferenceStore();
        numberOfNodes = preferenceStore.getInt(RandomGraphGenerator.NUMBER_OF_NODES.getId());
        edgeDetermination = preferenceStore.getInt(RandomGraphGenerator.EDGE_DETERMINATION.getId());
        numberOfEdges = preferenceStore.getInt(RandomGraphGenerator.NUMBER_OF_EDGES.getId());
        density = preferenceStore.getFloat(PREFERENCE_DENSITY);
        minOutgoingEdges = preferenceStore.getInt(RandomGraphGenerator.MIN_OUTGOING_EDGES.getId());
        maxOutgoingEdges = preferenceStore.getInt(RandomGraphGenerator.MAX_OUTGOING_EDGES.getId());
        selfLoops = preferenceStore.getBoolean(RandomGraphGenerator.SELF_LOOPS.getId());
        multiEdges = preferenceStore.getBoolean(RandomGraphGenerator.MULTI_EDGES.getId());
        cycles = preferenceStore.getBoolean(RandomGraphGenerator.CYCLES.getId());
    }

    private void setDefaultPreferences() {
        IPreferenceStore preferenceStore = KEGDiagramPlugin.getDefault().getPreferenceStore();
        preferenceStore.setDefault(RandomGraphGenerator.NUMBER_OF_NODES.getId(),
                RandomGraphGenerator.NUMBER_OF_NODES.getDefault());
        preferenceStore.setDefault(RandomGraphGenerator.EDGE_DETERMINATION.getId(),
                EDGE_DETERMINATION_EDGES);
        preferenceStore.setDefault(RandomGraphGenerator.NUMBER_OF_EDGES.getId(),
                RandomGraphGenerator.NUMBER_OF_EDGES.getDefault());
        preferenceStore.setDefault(PREFERENCE_DENSITY, 0.0f);
        preferenceStore.setDefault(RandomGraphGenerator.MIN_OUTGOING_EDGES.getId(),
                RandomGraphGenerator.MIN_OUTGOING_EDGES.getDefault());
        preferenceStore.setDefault(RandomGraphGenerator.MAX_OUTGOING_EDGES.getId(),
                RandomGraphGenerator.MAX_OUTGOING_EDGES.getDefault());
        preferenceStore.setDefault(RandomGraphGenerator.SELF_LOOPS.getId(),
                RandomGraphGenerator.SELF_LOOPS.getDefault());
        preferenceStore.setDefault(RandomGraphGenerator.MULTI_EDGES.getId(),
                RandomGraphGenerator.MULTI_EDGES.getDefault());
        preferenceStore.setDefault(RandomGraphGenerator.CYCLES.getId(),
                RandomGraphGenerator.CYCLES.getDefault());
    }

    /**
     * Returns the selected number of nodes.
     * 
     * @return the number of nodes
     */
    public int getNumberOfNodes() {
        return numberOfNodes;
    }

    /**
     * Returns the selected edge determination.
     * 
     * @return the edge determination
     */
    public RandomGraphGenerator.EdgeDetermination getEdgeDetermination() {
        if (edgeDetermination == EDGE_DETERMINATION_OUTGOING) {
            return RandomGraphGenerator.EdgeDetermination.OUTGOING_EDGES;
        } else {
            return RandomGraphGenerator.EdgeDetermination.GRAPH_EDGES;
        }
    }

    /**
     * Returns the selected number of edges.
     * 
     * @return the number of edges
     */
    public int getNumberOfEdges() {
        if (edgeDetermination == EDGE_DETERMINATION_DENSITY) {
            return (int) (density * numberOfNodes * numberOfNodes);
        }
        return numberOfEdges;
    }

    /**
     * Returns the selected minimum number of outgoing edges.
     * 
     * @return the minimum number of outgoing edges
     */
    public int getMinOutgoingEdges() {
        return minOutgoingEdges;
    }

    /**
     * Returns the selected maximum number of outgoing edges.
     * 
     * @return the maximum number of outgoing edges
     */
    public int getMaxOutgoingEdges() {
        return maxOutgoingEdges;
    }

    /**
     * Returns whether self-loops are allowed.
     * 
     * @return true if self-loops are allowed; false else
     */
    public boolean getSelfLoops() {
        return selfLoops;
    }

    /**
     * Returns whether multi-edges are allowed.
     * 
     * @return true if multi-edges are allowed; false else
     */
    public boolean getMultiEdges() {
        return multiEdges;
    }

    /**
     * Returns whether cycles are allowed.
     * 
     * @return true if cycles are allowed; false else
     */
    public boolean getCycles() {
        return cycles;
    }

    /**
     * An adapter class for the SelectionListener.
     */
    private abstract static class SelectionListenerAdapter implements SelectionListener {

        public void widgetDefaultSelected(final SelectionEvent e) {
            // do nothing
        }
    }
}
