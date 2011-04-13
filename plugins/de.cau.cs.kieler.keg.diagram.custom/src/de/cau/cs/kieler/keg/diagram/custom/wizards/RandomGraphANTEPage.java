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
 * The options page for the ACYCLIC_NO_TRANSITIV_EDGES graph type.
 * 
 * @author mri
 */
public class RandomGraphANTEPage extends AbstractRandomGraphPage {

    /** the page title. */
    private static final String TITLE = "Acyclic Graph without Transitiv Edges";
    /** the description message for this page. */
    private static final String DESCRIPTION =
            "Select options for creating an acyclic graph without transitiv edges";

    /** the label for the number of nodes. */
    private static final String LABEL_NUMBER_OF_NODES = "Number Of &Nodes:";
    /** the label for the number of edges. */
    private static final String LABEL_NUMBER_OF_EDGES = "Number Of &Edges:";
    /** the label for the force planarity option. */
    private static final String LABEL_FORCE_PLANARITY = "Force &Planarity";

    /** the description for the number of nodes. */
    private static final String DESCRIPTION_NUMBER_OF_NODES =
            "The precise number of nodes in the generated graph.";
    /** the description for the number of edges. */
    private static final String DESCRIPTION_NUMBER_OF_EDGES =
            "The number of edges in the generated graph. The actual number can differ slightly.";
    /** the description for the force planarity option. */
    private static final String DESCRIPTION_FORCE_PLANARITY =
            "Whether the created graph will be planar.";

    /** the selected number of nodes. */
    private int numberOfNodes;
    /** the selected number of edges. */
    private int numberOfEdges;
    /** the selected planar restriction. */
    private boolean planar;

    /**
     * Constructs a RandomGraphANTEPage.
     */
    public RandomGraphANTEPage() {
        super("randomGraphANTEPage");
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
        layout.numColumns = 2;
        layout.verticalSpacing = 9;
        composite.setLayout(layout);
        // add NUMBER_OF_NODES option
        Label label = new Label(composite, SWT.NULL);
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
        label = new Label(composite, SWT.NULL);
        label.setText(LABEL_NUMBER_OF_EDGES);
        final Spinner edgesSpinner = new Spinner(composite, SWT.BORDER | SWT.SINGLE);
        addHelp(edgesSpinner, DESCRIPTION_NUMBER_OF_EDGES);
        edgesSpinner.setValues(numberOfEdges, 0, Integer.MAX_VALUE, 0, 1, 10);
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        gridData.widthHint = 50;
        edgesSpinner.setLayoutData(gridData);
        edgesSpinner.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                numberOfEdges = Integer.parseInt(edgesSpinner.getText());
            }
        });
        // add PLANAR option
        final Button planarButton = new Button(composite, SWT.CHECK);
        addHelp(planarButton, DESCRIPTION_FORCE_PLANARITY);
        planarButton.setText(LABEL_FORCE_PLANARITY);
        planarButton.setSelection(planar);
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        gridData.horizontalSpan = 2;
        planarButton.setLayoutData(gridData);
        planarButton.addSelectionListener(new SelectionListener() {

            public void widgetSelected(final SelectionEvent e) {
                planar = planarButton.getSelection();
            }

            public void widgetDefaultSelected(final SelectionEvent e) {
                // do nothing
            }
        });
    }

    // CHECKSTYLEON MagicNumber

    /**
     * Saves the selected options to the preference store.
     */
    public void savePreferences() {
        IPreferenceStore preferenceStore = KEGDiagramPlugin.getDefault().getPreferenceStore();
        preferenceStore.setValue(RandomGraphGenerator.NUMBER_OF_NODES.getIdentifier().toString(),
                numberOfNodes);
        preferenceStore.setValue(RandomGraphGenerator.NUMBER_OF_EDGES.getIdentifier().toString(),
                numberOfEdges);
        preferenceStore.setValue(RandomGraphGenerator.PLANAR.getIdentifier().toString(), planar);
    }

    private void loadPreferences() {
        IPreferenceStore preferenceStore = KEGDiagramPlugin.getDefault().getPreferenceStore();
        numberOfNodes =
                preferenceStore.getInt(RandomGraphGenerator.NUMBER_OF_NODES.getIdentifier()
                        .toString());
        numberOfEdges =
                preferenceStore.getInt(RandomGraphGenerator.NUMBER_OF_EDGES.getIdentifier()
                        .toString());
        planar = preferenceStore.getBoolean(RandomGraphGenerator.PLANAR.getIdentifier().toString());
    }

    private void setDefaultPreferences() {
        IPreferenceStore preferenceStore = KEGDiagramPlugin.getDefault().getPreferenceStore();
        preferenceStore.setDefault(RandomGraphGenerator.NUMBER_OF_NODES.getIdentifier().toString(),
                RandomGraphGenerator.NUMBER_OF_NODES.getDefault());
        preferenceStore.setDefault(RandomGraphGenerator.NUMBER_OF_EDGES.getIdentifier().toString(),
                RandomGraphGenerator.NUMBER_OF_EDGES.getDefault());
        preferenceStore.setDefault(RandomGraphGenerator.PLANAR.getIdentifier().toString(),
                RandomGraphGenerator.PLANAR.getDefault());
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
     * Returns the selected number of edges.
     * 
     * @return the number of edges
     */
    public int getNumberOfEdges() {
        return numberOfEdges;
    }

    /**
     * Returns whether the generated graph should be planar.
     * 
     * @return true if the generated graph should be planar; false else
     */
    public boolean getPlanar() {
        return planar;
    }
}
