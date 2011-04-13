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
 * The options page for utility settings.
 * 
 * @author mri
 */
public class RandomGraphUtilityPage extends AbstractRandomGraphPage {

    /** the page title. */
    private static final String TITLE = "Utility";
    /** the description message for this page. */
    private static final String DESCRIPTION = "Select utility options for all graph types";

    /** the label for the hierarchy chance. */
    private static final String LABEL_HIERARCHY_CHANCE = "&Hierarchy Chance:";
    /** the label for the maximum hierarchy level. */
    private static final String LABEL_MAX_HIERARCHY_LEVEL = "&Maximum Hierarchy Level:";
    /** the label for the hierarchy nodes factor. */
    private static final String LABEL_HIERARCHY_NODES_FACTOR = "Hierarchy Nodes &Factor:";
    /** the label for the hypernode chance. */
    private static final String LABEL_HYPERNODE_CHANCE = "H&ypernode Chance:";
    /** the label for the edge directed chance. */
    private static final String LABEL_EDGE_DIRECTED_CHANCE = "&Directed Edge Chance:";
    /** the label for the ports option. */
    private static final String LABEL_PORTS = "Use &Ports";

    /** the description for the hierarchy chance. */
    private static final String DESCRIPTION_HIERARCHY_CHANCE =
            "The chance that a node in the generated graph turns into a compound node.\n"
                    + "A graph is generated for every such node recursively using the same algorithm.";
    /** the description for the maximum hierarchy level. */
    private static final String DESCRIPTION_MAX_HIERARCHY_LEVEL =
            "The maximum depth for nested compound nodes in the generated graph.";
    /** the description for the hierarchy nodes factor. */
    private static final String DESCRIPTION_HIERARCHY_NODES_FACTOR =
            "This factor is used to determine the number of nodes of graphs inside compound nodes.\n"
                    + "The number is between 1 and the factor multiplied by the number"
                    + " of nodes in the parent graph.";
    /** the description for the hypernode chance. */
    private static final String DESCRIPTION_HYPERNODE_CHANCE =
            "The chance for every node in the generated graph to become a hypernode.";
    /** the description for the edge directed chance. */
    private static final String DESCRIPTION_EDGE_DIRECTED_CHANCE =
            "The chance for every edge in the generated graph to become a directed edge.\n"
                    + "In particular a 1 makes every edge directed and a 0 makes every edge undirected.";
    /** the description for the ports option. */
    private static final String DESCRIPTION_PORTS =
            "Whether edges are not connected directly to nodes but through ports.";

    /** the selected hierarchy chance. */
    private float hierarchyChance;
    /** the selected maximum hierarchy level. */
    private int maxHierarchyLevel;
    /** the selected hierarchy nodes factor. */
    private float hierarchyNodesFactor;
    /** the selected hypernode chance. */
    private float hypernodeChance;
    /** the selected chance for directed edges. */
    private float edgeDirectedChance;
    /** the selected port usage. */
    private boolean ports;

    /**
     * Constructs a RandomGraphUtilityPage.
     */
    public RandomGraphUtilityPage() {
        super("randomGraphUtilityPage");
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
        // add HIERARCHY_CHANCE option
        Label label = new Label(composite, SWT.NULL);
        label.setText(LABEL_HIERARCHY_CHANCE);
        final Spinner hierarchySpinner = new Spinner(composite, SWT.BORDER | SWT.SINGLE);
        addHelp(hierarchySpinner, DESCRIPTION_HIERARCHY_CHANCE);
        hierarchySpinner.setValues((int) (hierarchyChance * 100), 0, 100, 2, 1, 10);
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        gridData.widthHint = 30;
        hierarchySpinner.setLayoutData(gridData);
        // add MAX_HIERARCHY_LEVEL option
        label = new Label(composite, SWT.NULL);
        label.setText(LABEL_MAX_HIERARCHY_LEVEL);
        final Spinner hierarchyLevelSpinner = new Spinner(composite, SWT.BORDER | SWT.SINGLE);
        addHelp(hierarchyLevelSpinner, DESCRIPTION_MAX_HIERARCHY_LEVEL);
        hierarchyLevelSpinner.setValues(maxHierarchyLevel, 1, Integer.MAX_VALUE, 0, 1, 10);
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        gridData.widthHint = 50;
        hierarchyLevelSpinner.setLayoutData(gridData);
        hierarchyLevelSpinner.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                maxHierarchyLevel = Integer.parseInt(hierarchyLevelSpinner.getText());
            }
        });
        hierarchyLevelSpinner.setEnabled(hierarchyChance > 0.0f);
        // add HIERARCHY_NODES_FACTOR option
        label = new Label(composite, SWT.NULL);
        label.setText(LABEL_HIERARCHY_NODES_FACTOR);
        final Spinner hierarchyFactorSpinner = new Spinner(composite, SWT.BORDER | SWT.SINGLE);
        addHelp(hierarchyFactorSpinner, DESCRIPTION_HIERARCHY_NODES_FACTOR);
        hierarchyFactorSpinner.setValues((int) (hierarchyNodesFactor * 100), 0, Integer.MAX_VALUE,
                2, 1, 10);
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        gridData.widthHint = 30;
        hierarchyFactorSpinner.setLayoutData(gridData);
        hierarchyFactorSpinner.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                hierarchyNodesFactor = Float.parseFloat(hierarchyFactorSpinner.getText());
            }
        });
        hierarchyFactorSpinner.setEnabled(hierarchyChance > 0.0f);
        // set the modify listener for the HIERARCHY_CHANCE option
        hierarchySpinner.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                hierarchyChance = Float.parseFloat(hierarchySpinner.getText());
                if (hierarchyChance > 0.0f) {
                    hierarchyLevelSpinner.setEnabled(true);
                    hierarchyFactorSpinner.setEnabled(true);
                } else {
                    hierarchyLevelSpinner.setEnabled(false);
                    hierarchyFactorSpinner.setEnabled(false);
                }
            }
        });
        // add HYPERNODE_CHANCE option
        label = new Label(composite, SWT.NULL);
        label.setText(LABEL_HYPERNODE_CHANCE);
        final Spinner hypernodeSpinner = new Spinner(composite, SWT.BORDER | SWT.SINGLE);
        addHelp(hypernodeSpinner, DESCRIPTION_HYPERNODE_CHANCE);
        hypernodeSpinner.setValues((int) (hypernodeChance * 100), 0, 100, 2, 1, 10);
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        gridData.widthHint = 30;
        hypernodeSpinner.setLayoutData(gridData);
        hypernodeSpinner.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                hypernodeChance = Float.parseFloat(hypernodeSpinner.getText());
            }
        });
        // add EDGE_DIRECTED_CHANCE option
        label = new Label(composite, SWT.NULL);
        label.setText(LABEL_EDGE_DIRECTED_CHANCE);
        final Spinner edgeDirectedSpinner = new Spinner(composite, SWT.BORDER | SWT.SINGLE);
        addHelp(edgeDirectedSpinner, DESCRIPTION_EDGE_DIRECTED_CHANCE);
        edgeDirectedSpinner.setValues((int) (edgeDirectedChance * 100), 0, 100, 2, 1, 10);
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        gridData.widthHint = 30;
        edgeDirectedSpinner.setLayoutData(gridData);
        edgeDirectedSpinner.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                edgeDirectedChance = Float.parseFloat(edgeDirectedSpinner.getText());
            }
        });
        // add PORTS option
        final Button portsButton = new Button(composite, SWT.CHECK);
        addHelp(portsButton, DESCRIPTION_PORTS);
        portsButton.setText(LABEL_PORTS);
        portsButton.setSelection(ports);
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        gridData.horizontalSpan = 2;
        portsButton.setLayoutData(gridData);
        portsButton.addSelectionListener(new SelectionListener() {

            public void widgetSelected(final SelectionEvent e) {
                ports = portsButton.getSelection();
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
        preferenceStore.setValue(RandomGraphGenerator.HIERARCHY_CHANCE.getIdentifier().toString(),
                hierarchyChance);
        preferenceStore.setValue(RandomGraphGenerator.MAX_HIERARCHY_LEVEL.getIdentifier()
                .toString(), maxHierarchyLevel);
        preferenceStore.setValue(RandomGraphGenerator.HIERARCHY_NODES_FACTOR.getIdentifier()
                .toString(), hierarchyNodesFactor);
        preferenceStore.setValue(RandomGraphGenerator.HYPERNODE_CHANCE.getIdentifier().toString(),
                hypernodeChance);
        preferenceStore.setValue(RandomGraphGenerator.EDGE_DIRECTED_CHANCE.getIdentifier()
                .toString(), edgeDirectedChance);
        preferenceStore.setValue(RandomGraphGenerator.PORTS.getIdentifier().toString(), ports);
    }

    private void loadPreferences() {
        IPreferenceStore preferenceStore = KEGDiagramPlugin.getDefault().getPreferenceStore();
        hierarchyChance =
                preferenceStore.getFloat(RandomGraphGenerator.HIERARCHY_CHANCE.getIdentifier()
                        .toString());
        maxHierarchyLevel =
                preferenceStore.getInt(RandomGraphGenerator.MAX_HIERARCHY_LEVEL.getIdentifier()
                        .toString());
        hierarchyNodesFactor =
                preferenceStore.getFloat(RandomGraphGenerator.HIERARCHY_NODES_FACTOR
                        .getIdentifier().toString());
        hypernodeChance =
                preferenceStore.getFloat(RandomGraphGenerator.HYPERNODE_CHANCE.getIdentifier()
                        .toString());
        edgeDirectedChance =
                preferenceStore.getFloat(RandomGraphGenerator.EDGE_DIRECTED_CHANCE.getIdentifier()
                        .toString());
        ports = preferenceStore.getBoolean(RandomGraphGenerator.PORTS.getIdentifier().toString());
    }

    private void setDefaultPreferences() {
        IPreferenceStore preferenceStore = KEGDiagramPlugin.getDefault().getPreferenceStore();
        preferenceStore.setDefault(
                RandomGraphGenerator.HIERARCHY_CHANCE.getIdentifier().toString(),
                RandomGraphGenerator.HIERARCHY_CHANCE.getDefault());
        preferenceStore.setDefault(RandomGraphGenerator.MAX_HIERARCHY_LEVEL.getIdentifier()
                .toString(), RandomGraphGenerator.MAX_HIERARCHY_LEVEL.getDefault());
        preferenceStore.setDefault(RandomGraphGenerator.HIERARCHY_NODES_FACTOR.getIdentifier()
                .toString(), RandomGraphGenerator.HIERARCHY_NODES_FACTOR.getDefault());
        preferenceStore.setDefault(
                RandomGraphGenerator.HYPERNODE_CHANCE.getIdentifier().toString(),
                RandomGraphGenerator.HYPERNODE_CHANCE.getDefault());
        preferenceStore.setDefault(RandomGraphGenerator.EDGE_DIRECTED_CHANCE.getIdentifier()
                .toString(), RandomGraphGenerator.EDGE_DIRECTED_CHANCE.getDefault());
        preferenceStore.setDefault(RandomGraphGenerator.PORTS.getIdentifier().toString(),
                RandomGraphGenerator.PORTS.getDefault());
    }

    /**
     * Returns the hierarchy chance.
     * 
     * @return the hierarchy chance
     */
    public float getHierarchyChance() {
        return hierarchyChance;
    }

    /**
     * Returns the maximum hierarchy level.
     * 
     * @return the maximum hierarchy level
     */
    public int getMaximumHierarchyLevel() {
        return maxHierarchyLevel;
    }

    /**
     * Returns the factor for computing the number of nodes in a compound node.
     * 
     * @return the factor
     */
    public float getHierarchyNodesFactor() {
        return hierarchyNodesFactor;
    }

    /**
     * Returns the hypernode chance.
     * 
     * @return the hypernode chance
     */
    public float getHypernodeChance() {
        return hypernodeChance;
    }

    /**
     * Returns the chance for creating a directed edge.
     * 
     * @return the chance for creating a directed edge
     */
    public float getEdgeDirectedChance() {
        return edgeDirectedChance;
    }

    /**
     * Returns whether ports have to be used to connect nodes.
     * 
     * @return true if ports have to be used; false else
     */
    public boolean getPorts() {
        return ports;
    }
}
