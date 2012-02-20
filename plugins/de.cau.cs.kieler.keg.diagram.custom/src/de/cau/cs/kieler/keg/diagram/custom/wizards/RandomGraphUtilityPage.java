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
 * The options page for utility settings.
 * 
 * @author mri
 */
public class RandomGraphUtilityPage extends WizardPage {

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
    /** whether cross-hierarchy edges are allowed. */
    private boolean crossHierarchyEdges;

    /**
     * Constructs a RandomGraphUtilityPage.
     */
    public RandomGraphUtilityPage() {
        super("randomGraphUtilityPage"); //$NON-NLS-1$
        setTitle(Messages.RandomGraphUtilityPage_title);
        setDescription(Messages.RandomGraphUtilityPage_description);
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
        label.setText(Messages.RandomGraphUtilityPage_hierarchy_caption);
        final Spinner hierarchySpinner = new Spinner(composite, SWT.BORDER | SWT.SINGLE);
        Util.addHelp(hierarchySpinner, Messages.RandomGraphUtilityPage_hierarchy_help);
        hierarchySpinner.setValues((int) (hierarchyChance * 100), 0, 100, 2, 1, 10);
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        gridData.widthHint = 80;
        hierarchySpinner.setLayoutData(gridData);
        
        // add MAX_HIERARCHY_LEVEL option
        label = new Label(composite, SWT.NULL);
        label.setText(Messages.RandomGraphUtilityPage_max_hierarchy_caption);
        final Spinner hierarchyLevelSpinner = new Spinner(composite, SWT.BORDER | SWT.SINGLE);
        Util.addHelp(hierarchyLevelSpinner, Messages.RandomGraphUtilityPage_max_hierarchy_help);
        hierarchyLevelSpinner.setValues(maxHierarchyLevel, 1, Integer.MAX_VALUE, 0, 1, 10);
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        gridData.widthHint = 80;
        hierarchyLevelSpinner.setLayoutData(gridData);
        hierarchyLevelSpinner.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                maxHierarchyLevel = hierarchyLevelSpinner.getSelection();
            }
        });
        hierarchyLevelSpinner.setEnabled(hierarchyChance > 0.0f);
        
        // add HIERARCHY_NODES_FACTOR option
        label = new Label(composite, SWT.NULL);
        label.setText(Messages.RandomGraphUtilityPage_hierarchy_factor_caption);
        final Spinner hierarchyFactorSpinner = new Spinner(composite, SWT.BORDER | SWT.SINGLE);
        Util.addHelp(hierarchyFactorSpinner, Messages.RandomGraphUtilityPage_hierarchy_factor_help);
        hierarchyFactorSpinner.setValues((int) (hierarchyNodesFactor * 100), 0, Integer.MAX_VALUE,
                2, 1, 10);
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        gridData.widthHint = 80;
        hierarchyFactorSpinner.setLayoutData(gridData);
        hierarchyFactorSpinner.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                hierarchyNodesFactor = ((float) hierarchyFactorSpinner.getSelection()) / 100f;
            }
        });
        hierarchyFactorSpinner.setEnabled(hierarchyChance > 0.0f);
        // set the modify listener for the HIERARCHY_CHANCE option
        hierarchySpinner.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                hierarchyChance = ((float) hierarchySpinner.getSelection()) / 100f;
                if (hierarchyChance > 0.0f) {
                    hierarchyLevelSpinner.setEnabled(true);
                    hierarchyFactorSpinner.setEnabled(true);
                } else {
                    hierarchyLevelSpinner.setEnabled(false);
                    hierarchyFactorSpinner.setEnabled(false);
                }
            }
        });
        
        // add CROSS-HIERARCHY EDGES option
        final Button hierarchyEdgesButton = new Button(composite, SWT.CHECK);
        Util.addHelp(hierarchyEdgesButton, Messages.RandomGraphUtilityPage_hierarchy_edges_help);
        hierarchyEdgesButton.setText(Messages.RandomGraphUtilityPage_hierarchy_edges_caption);
        hierarchyEdgesButton.setSelection(crossHierarchyEdges);
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        gridData.horizontalSpan = 2;
        hierarchyEdgesButton.setLayoutData(gridData);
        hierarchyEdgesButton.addSelectionListener(new SelectionListener() {

            public void widgetSelected(final SelectionEvent e) {
                crossHierarchyEdges = hierarchyEdgesButton.getSelection();
            }

            public void widgetDefaultSelected(final SelectionEvent e) {
                // do nothing
            }
        });
        
        // add HYPERNODE_CHANCE option
        label = new Label(composite, SWT.NULL);
        label.setText(Messages.RandomGraphUtilityPage_hypernode_caption);
        final Spinner hypernodeSpinner = new Spinner(composite, SWT.BORDER | SWT.SINGLE);
        Util.addHelp(hypernodeSpinner, Messages.RandomGraphUtilityPage_hypernode_help);
        hypernodeSpinner.setValues((int) (hypernodeChance * 100), 0, 100, 2, 1, 10);
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        gridData.widthHint = 80;
        hypernodeSpinner.setLayoutData(gridData);
        hypernodeSpinner.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                hypernodeChance = ((float) hypernodeSpinner.getSelection()) / 100f;
            }
        });
        
        // add EDGE_DIRECTED_CHANCE option
        label = new Label(composite, SWT.NULL);
        label.setText(Messages.RandomGraphUtilityPage_directed_caption);
        final Spinner edgeDirectedSpinner = new Spinner(composite, SWT.BORDER | SWT.SINGLE);
        Util.addHelp(edgeDirectedSpinner, Messages.RandomGraphUtilityPage_directed_help);
        edgeDirectedSpinner.setValues((int) (edgeDirectedChance * 100), 0, 100, 2, 1, 10);
        gridData = new GridData(SWT.LEFT, SWT.NONE, false, false);
        gridData.widthHint = 80;
        edgeDirectedSpinner.setLayoutData(gridData);
        edgeDirectedSpinner.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                edgeDirectedChance = ((float) edgeDirectedSpinner.getSelection()) / 100f;
            }
        });
        
        // add PORTS option
        final Button portsButton = new Button(composite, SWT.CHECK);
        Util.addHelp(portsButton, Messages.RandomGraphUtilityPage_ports_help);
        portsButton.setText(Messages.RandomGraphUtilityPage_ports_caption);
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
        preferenceStore.setValue(RandomGraphGenerator.HIERARCHY_CHANCE.getId(), hierarchyChance);
        preferenceStore.setValue(RandomGraphGenerator.MAX_HIERARCHY_LEVEL.getId(),
                maxHierarchyLevel);
        preferenceStore.setValue(RandomGraphGenerator.HIERARCHY_NODES_FACTOR.getId(),
                hierarchyNodesFactor);
        preferenceStore.setValue(RandomGraphGenerator.HYPERNODE_CHANCE.getId(), hypernodeChance);
        preferenceStore.setValue(RandomGraphGenerator.EDGE_DIRECTED_CHANCE.getId(),
                edgeDirectedChance);
        preferenceStore.setValue(RandomGraphGenerator.PORTS.getId(), ports);
        preferenceStore.setValue(RandomGraphGenerator.CROSS_HIERARCHY_EDGES.getId(),
                crossHierarchyEdges);
    }

    private void loadPreferences() {
        IPreferenceStore preferenceStore = KEGDiagramPlugin.getDefault().getPreferenceStore();
        hierarchyChance = preferenceStore.getFloat(RandomGraphGenerator.HIERARCHY_CHANCE.getId());
        maxHierarchyLevel = preferenceStore
                .getInt(RandomGraphGenerator.MAX_HIERARCHY_LEVEL.getId());
        hierarchyNodesFactor = preferenceStore.getFloat(RandomGraphGenerator.HIERARCHY_NODES_FACTOR
                .getId());
        hypernodeChance = preferenceStore.getFloat(RandomGraphGenerator.HYPERNODE_CHANCE.getId());
        edgeDirectedChance = preferenceStore.getFloat(RandomGraphGenerator.EDGE_DIRECTED_CHANCE
                .getId());
        ports = preferenceStore.getBoolean(RandomGraphGenerator.PORTS.getId());
        crossHierarchyEdges = preferenceStore.getBoolean(RandomGraphGenerator.CROSS_HIERARCHY_EDGES
                .getId());
    }

    private void setDefaultPreferences() {
        IPreferenceStore preferenceStore = KEGDiagramPlugin.getDefault().getPreferenceStore();
        preferenceStore.setDefault(RandomGraphGenerator.HIERARCHY_CHANCE.getId(),
                RandomGraphGenerator.HIERARCHY_CHANCE.getDefault());
        preferenceStore.setDefault(RandomGraphGenerator.MAX_HIERARCHY_LEVEL.getId(),
                RandomGraphGenerator.MAX_HIERARCHY_LEVEL.getDefault());
        preferenceStore.setDefault(RandomGraphGenerator.HIERARCHY_NODES_FACTOR.getId(),
                RandomGraphGenerator.HIERARCHY_NODES_FACTOR.getDefault());
        preferenceStore.setDefault(RandomGraphGenerator.HYPERNODE_CHANCE.getId(),
                RandomGraphGenerator.HYPERNODE_CHANCE.getDefault());
        preferenceStore.setDefault(RandomGraphGenerator.EDGE_DIRECTED_CHANCE.getId(),
                RandomGraphGenerator.EDGE_DIRECTED_CHANCE.getDefault());
        preferenceStore.setDefault(RandomGraphGenerator.PORTS.getId(),
                RandomGraphGenerator.PORTS.getDefault());
        preferenceStore.setDefault(RandomGraphGenerator.CROSS_HIERARCHY_EDGES.getId(),
                RandomGraphGenerator.CROSS_HIERARCHY_EDGES.getDefault());
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
    
    /**
     * Returns whether cross-hierarchy edges are allowed.
     * 
     * @return true if cross-hierarchy edges are allowed
     */
    public boolean getCrossHierarchyEdges() {
        return crossHierarchyEdges;
    }
    
}
