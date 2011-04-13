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
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;

import de.cau.cs.kieler.keg.diagram.custom.KEGDiagramPlugin;
import de.cau.cs.kieler.keg.diagram.custom.random.RandomGraphGenerator;

/**
 * The options page for the TREE graph type.
 * 
 * @author mri
 */
public class RandomGraphTreePage extends AbstractRandomGraphPage {

    /** the page title. */
    private static final String TITLE = "Tree";
    /** the description message for this page. */
    private static final String DESCRIPTION = "Select options for creating a tree";

    /** the label for the number of nodes. */
    private static final String LABEL_NUMBER_OF_NODES = "Number Of &Nodes:";
    /** the label for the maximum degree. */
    private static final String LABEL_MAXIMUM_DEGREE = "Maximum &Degree:";
    /** the label for the maximum width. */
    private static final String LABEL_MAXIMUM_WIDTH = "Maximum &Width:";

    /** the description for the number of nodes. */
    private static final String DESCRIPTION_NUMBER_OF_NODES =
            "The precise number of nodes in the generated graph.";
    /** the description for the maximum degree. */
    private static final String DESCRIPTION_MAXIMUM_DEGREE =
            "The maximum node degree in the generated tree.";
    /** the description for the maximum width. */
    private static final String DESCRIPTION_MAXIMUM_WIDTH =
            "The maximum width of the generated tree.";

    /** the selected number of nodes. */
    private int numberOfNodes;
    /** the selected maximum degree. */
    private int maxDegree;
    /** the selected maximum width. */
    private int maxWidth;

    /**
     * Constructs a RandomGraphTreePage.
     */
    public RandomGraphTreePage() {
        super("randomGraphTreePage");
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
        // add MAX_DEGREE option
        label = new Label(composite, SWT.NULL);
        label.setText(LABEL_MAXIMUM_DEGREE);
        final Spinner degreeSpinner = new Spinner(composite, SWT.BORDER | SWT.SINGLE);
        addHelp(degreeSpinner, DESCRIPTION_MAXIMUM_DEGREE);
        degreeSpinner.setValues(maxDegree, 1, Integer.MAX_VALUE, 0, 1, 10);
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        gridData.widthHint = 50;
        degreeSpinner.setLayoutData(gridData);
        degreeSpinner.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                maxDegree = Integer.parseInt(degreeSpinner.getText());
            }
        });
        // add MAX_WIDTH option
        label = new Label(composite, SWT.NULL);
        label.setText(LABEL_MAXIMUM_WIDTH);
        final Spinner widthSpinner = new Spinner(composite, SWT.BORDER | SWT.SINGLE);
        addHelp(widthSpinner, DESCRIPTION_MAXIMUM_WIDTH);
        widthSpinner.setValues(maxWidth, 1, Integer.MAX_VALUE, 0, 1, 10);
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        gridData.widthHint = 50;
        widthSpinner.setLayoutData(gridData);
        widthSpinner.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                maxWidth = Integer.parseInt(widthSpinner.getText());
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
        preferenceStore.setValue(RandomGraphGenerator.MAX_DEGREE.getIdentifier().toString(),
                maxDegree);
        preferenceStore.setValue(RandomGraphGenerator.MAX_WIDTH.getIdentifier().toString(),
                maxWidth);
    }

    private void loadPreferences() {
        IPreferenceStore preferenceStore = KEGDiagramPlugin.getDefault().getPreferenceStore();
        numberOfNodes =
                preferenceStore.getInt(RandomGraphGenerator.NUMBER_OF_NODES.getIdentifier()
                        .toString());
        preferenceStore.setValue(RandomGraphGenerator.MAX_DEGREE.getIdentifier().toString(),
                maxDegree);
        preferenceStore.setValue(RandomGraphGenerator.MAX_WIDTH.getIdentifier().toString(),
                maxWidth);
    }

    private void setDefaultPreferences() {
        IPreferenceStore preferenceStore = KEGDiagramPlugin.getDefault().getPreferenceStore();
        preferenceStore.setDefault(RandomGraphGenerator.NUMBER_OF_NODES.getIdentifier().toString(),
                RandomGraphGenerator.NUMBER_OF_NODES.getDefault());
        preferenceStore.setDefault(RandomGraphGenerator.MAX_DEGREE.getIdentifier().toString(),
                RandomGraphGenerator.MAX_DEGREE.getDefault());
        preferenceStore.setDefault(RandomGraphGenerator.MAX_WIDTH.getIdentifier().toString(),
                RandomGraphGenerator.MAX_WIDTH.getDefault());
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
     * Returns the selected maximum node degree.
     * 
     * @return the maximum node degree
     */
    public int getMaxDegree() {
        return maxDegree;
    }

    /**
     * Returns the selected maximum tree width.
     * 
     * @return the maximum tree width
     */
    public int getMaxWidth() {
        return maxWidth;
    }
}
