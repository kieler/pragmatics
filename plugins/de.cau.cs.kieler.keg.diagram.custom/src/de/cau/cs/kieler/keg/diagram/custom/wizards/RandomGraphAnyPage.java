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
public class RandomGraphAnyPage extends AbstractRandomGraphPage {

    /** the page title. */
    private static final String TITLE = "Customizable Random Graph";
    /** the description message for this page. */
    private static final String DESCRIPTION = "Select options for creating a random graph";

    /** the label for the number of nodes. */
    private static final String LABEL_NUMBER_OF_NODES = "Number Of &Nodes:";
    /** the label for the number of edges. */
    private static final String LABEL_NUMBER_OF_EDGES = "Number Of &Edges:";
    /** the label for the density. */
    private static final String LABEL_DENSITY = "&Density:";
    /** the label for the minimum number of outgoing edges. */
    private static final String LABEL_MIN_OUTGOING = "&Minimum Outgoing Edges:";
    /** the label for the maximum number of outgoing edges. */
    private static final String LABEL_MAX_OUTGOING = "Ma&ximum Outgoing Edges:";
    /** the label for the self-loops option. */
    private static final String LABEL_SELF_LOOPS = "Allow &Self-Loops";
    /** the label for the multi-edges option. */
    private static final String LABEL_MULTI_EDGES = "Allow M&ulti-Edges";
    /** the label for the cycles option. */
    private static final String LABEL_CYCLES = "Allow &Cycles";

    /** the description for the number of nodes. */
    private static final String DESCRIPTION_NUMBER_OF_NODES =
            "The precise number of nodes in the generated graph.";
    /** the description for the number of edges. */
    private static final String DESCRIPTION_NUMBER_OF_EDGES =
            "The number of edges in the generated graph. The actual number can differ slightly.";
    /** the description for the density. */
    private static final String DESCRIPTION_DENSITY =
            "An alternative way to specify the number of edges in the generated graph.\n"
                    + "The density d of a graph G=(V,E) with n=|V| and m=|E| is defined as d=m/(n*n).";
    /** the description for the minimum number of outgoing edges. */
    private static final String DESCRIPTION_MIN_OUTGOING =
            "The minimum number of outgoing edges per node in the generated graph.";
    /** the description for the maximum number of outgoing edges. */
    private static final String DESCRIPTION_MAX_OUTGOING =
            "The maximum number of outgoing edges per node in the generated graph.";
    /** the description for the self-loops option. */
    private static final String DESCRIPTION_SELF_LOOPS =
            "Whether self-loops can appear in the generated graph.";
    /** the description for the multi-edges option. */
    private static final String DESCRIPTION_MULTI_EDGES =
            "Whether multi-edges can appear in the generated graph.";
    /** the description for the cycles option. */
    private static final String DESCRIPTION_CYCLES =
            "Whether cycles can appear in the generated graph.";

    /** the id of the NUMBER_OF_EDGES edge determination for the preferences. */
    public static final int EDGE_DETERMINATION_EDGES = 0;
    /** the id of the density edge determination for the preferences. */
    public static final int EDGE_DETERMINATION_DENSITY = 1;
    /** the id of the MIN/MAX_OUTGOING_EDGES edge determination for the preferences. */
    public static final int EDGE_DETERMINATION_OUTGOING = 2;

    /** the preference key for the density. */
    private static final String PREFERENCE_DENSITY = "randomWizard.density";

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
        super("randomGraphAnyPage");
        setTitle(TITLE);
        setDescription(DESCRIPTION);
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
        label.setText(LABEL_NUMBER_OF_NODES);
        final Spinner nodesSpinner = new Spinner(composite, SWT.BORDER | SWT.SINGLE);
        addHelp(nodesSpinner, DESCRIPTION_NUMBER_OF_NODES);
        nodesSpinner.setValues(numberOfNodes, 1, Integer.MAX_VALUE, 0, 1, 10);
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        gridData.widthHint = 50;
        nodesSpinner.setLayoutData(gridData);
        nodesSpinner.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                numberOfNodes = Integer.parseInt(nodesSpinner.getText());
            }
        });
        // add NUMBER_OF_EDGES option
        Button edgesSwitch = new Button(composite, SWT.RADIO | SWT.LEFT);
        edgesSwitch.setText(LABEL_NUMBER_OF_EDGES);
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        gridData.horizontalSpan = 2;
        edgesSwitch.setLayoutData(gridData);
        final Spinner edgesSpinner = new Spinner(composite, SWT.BORDER | SWT.SINGLE);
        addHelp(edgesSpinner, DESCRIPTION_NUMBER_OF_EDGES);
        edgesSpinner.setValues(numberOfEdges, 0, Integer.MAX_VALUE, 0, 1, 10);
        edgesSpinner.setEnabled(false);
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        gridData.widthHint = 50;
        edgesSpinner.setLayoutData(gridData);
        edgesSpinner.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                numberOfEdges = Integer.parseInt(edgesSpinner.getText());
            }
        });
        // add another way to specify the NUMBER_OF_EDGES option
        Button densitySwitch = new Button(composite, SWT.RADIO | SWT.LEFT);
        densitySwitch.setText(LABEL_DENSITY);
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        gridData.horizontalSpan = 2;
        densitySwitch.setLayoutData(gridData);
        final Spinner densitySpinner = new Spinner(composite, SWT.BORDER | SWT.SINGLE);
        addHelp(densitySpinner, DESCRIPTION_DENSITY);
        densitySpinner.setValues((int) (density * 100), 0, Integer.MAX_VALUE, 2, 1, 10);
        densitySpinner.setEnabled(false);
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        gridData.widthHint = 30;
        densitySpinner.setLayoutData(gridData);
        densitySpinner.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                density = Float.parseFloat(densitySpinner.getText());
            }
        });
        // add MIN_OUTGOING_EDGES option
        Button outgoingSwitch = new Button(composite, SWT.RADIO | SWT.LEFT);
        outgoingSwitch.setText(LABEL_MIN_OUTGOING);
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        gridData.horizontalSpan = 2;
        outgoingSwitch.setLayoutData(gridData);
        final Spinner minOutSpinner = new Spinner(composite, SWT.BORDER | SWT.SINGLE);
        addHelp(minOutSpinner, DESCRIPTION_MIN_OUTGOING);
        minOutSpinner.setValues(minOutgoingEdges, 0, Integer.MAX_VALUE, 0, 1, 10);
        minOutSpinner.setEnabled(false);
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        gridData.widthHint = 50;
        minOutSpinner.setLayoutData(gridData);
        minOutSpinner.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                minOutgoingEdges = Integer.parseInt(minOutSpinner.getText());
            }
        });
        // add MAX_OUTGOING_EDGES option
        // empty label to fill first column
        label = new Label(composite, SWT.NULL);
        label = new Label(composite, SWT.NULL);
        label.setText(LABEL_MAX_OUTGOING);
        final Spinner maxOutSpinner = new Spinner(composite, SWT.BORDER | SWT.SINGLE);
        addHelp(maxOutSpinner, DESCRIPTION_MAX_OUTGOING);
        maxOutSpinner.setValues(maxOutgoingEdges, 0, Integer.MAX_VALUE, 0, 1, 10);
        maxOutSpinner.setEnabled(false);
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        gridData.widthHint = 50;
        maxOutSpinner.setLayoutData(gridData);
        maxOutSpinner.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                maxOutgoingEdges = Integer.parseInt(maxOutSpinner.getText());
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
        addHelp(selfLoopsButton, DESCRIPTION_SELF_LOOPS);
        selfLoopsButton.setText(LABEL_SELF_LOOPS);
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
        addHelp(multiEdgesButton, DESCRIPTION_MULTI_EDGES);
        multiEdgesButton.setText(LABEL_MULTI_EDGES);
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
        addHelp(cyclesButton, DESCRIPTION_CYCLES);
        cyclesButton.setText(LABEL_CYCLES);
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
    public void safePreferences() {
        IPreferenceStore preferenceStore = KEGDiagramPlugin.getDefault().getPreferenceStore();
        preferenceStore.setValue(RandomGraphGenerator.NUMBER_OF_NODES.getIdentifier().toString(),
                numberOfNodes);
        preferenceStore.setValue(
                RandomGraphGenerator.EDGE_DETERMINATION.getIdentifier().toString(),
                edgeDetermination);
        preferenceStore.setValue(RandomGraphGenerator.NUMBER_OF_EDGES.getIdentifier().toString(),
                numberOfEdges);
        preferenceStore.setValue(PREFERENCE_DENSITY, density);
        preferenceStore.setValue(
                RandomGraphGenerator.MIN_OUTGOING_EDGES.getIdentifier().toString(),
                minOutgoingEdges);
        preferenceStore.setValue(
                RandomGraphGenerator.MAX_OUTGOING_EDGES.getIdentifier().toString(),
                maxOutgoingEdges);
        preferenceStore.setValue(RandomGraphGenerator.SELF_LOOPS.getIdentifier().toString(),
                selfLoops);
        preferenceStore.setValue(RandomGraphGenerator.MULTI_EDGES.getIdentifier().toString(),
                multiEdges);
        preferenceStore.setValue(RandomGraphGenerator.CYCLES.getIdentifier().toString(), cycles);
    }

    private void loadPreferences() {
        IPreferenceStore preferenceStore = KEGDiagramPlugin.getDefault().getPreferenceStore();
        numberOfNodes =
                preferenceStore.getInt(RandomGraphGenerator.NUMBER_OF_NODES.getIdentifier()
                        .toString());
        edgeDetermination =
                preferenceStore.getInt(RandomGraphGenerator.EDGE_DETERMINATION.getIdentifier()
                        .toString());
        numberOfEdges =
                preferenceStore.getInt(RandomGraphGenerator.NUMBER_OF_EDGES.getIdentifier()
                        .toString());
        density = preferenceStore.getFloat(PREFERENCE_DENSITY);
        minOutgoingEdges =
                preferenceStore.getInt(RandomGraphGenerator.MIN_OUTGOING_EDGES.getIdentifier()
                        .toString());
        maxOutgoingEdges =
                preferenceStore.getInt(RandomGraphGenerator.MAX_OUTGOING_EDGES.getIdentifier()
                        .toString());
        selfLoops =
                preferenceStore.getBoolean(RandomGraphGenerator.SELF_LOOPS.getIdentifier()
                        .toString());
        multiEdges =
                preferenceStore.getBoolean(RandomGraphGenerator.MULTI_EDGES.getIdentifier()
                        .toString());
        cycles = preferenceStore.getBoolean(RandomGraphGenerator.CYCLES.getIdentifier().toString());
    }

    private void setDefaultPreferences() {
        IPreferenceStore preferenceStore = KEGDiagramPlugin.getDefault().getPreferenceStore();
        preferenceStore.setDefault(RandomGraphGenerator.NUMBER_OF_NODES.getIdentifier().toString(),
                RandomGraphGenerator.NUMBER_OF_NODES.getDefault());
        preferenceStore.setDefault(RandomGraphGenerator.EDGE_DETERMINATION.getIdentifier()
                .toString(), EDGE_DETERMINATION_EDGES);
        preferenceStore.setDefault(RandomGraphGenerator.NUMBER_OF_EDGES.getIdentifier().toString(),
                RandomGraphGenerator.NUMBER_OF_EDGES.getDefault());
        preferenceStore.setDefault(PREFERENCE_DENSITY, 0.0f);
        preferenceStore.setDefault(RandomGraphGenerator.MIN_OUTGOING_EDGES.getIdentifier()
                .toString(), RandomGraphGenerator.MIN_OUTGOING_EDGES.getDefault());
        preferenceStore.setDefault(RandomGraphGenerator.MAX_OUTGOING_EDGES.getIdentifier()
                .toString(), RandomGraphGenerator.MAX_OUTGOING_EDGES.getDefault());
        preferenceStore.setDefault(RandomGraphGenerator.SELF_LOOPS.getIdentifier().toString(),
                RandomGraphGenerator.SELF_LOOPS.getDefault());
        preferenceStore.setDefault(RandomGraphGenerator.MULTI_EDGES.getIdentifier().toString(),
                RandomGraphGenerator.MULTI_EDGES.getDefault());
        preferenceStore.setDefault(RandomGraphGenerator.CYCLES.getIdentifier().toString(),
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
