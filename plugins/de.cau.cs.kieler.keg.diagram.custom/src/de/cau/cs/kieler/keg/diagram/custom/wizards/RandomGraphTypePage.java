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
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import de.cau.cs.kieler.keg.diagram.custom.KEGDiagramPlugin;
import de.cau.cs.kieler.keg.diagram.custom.random.RandomGraphGenerator;

/**
 * The random graph wizard page which lets the user select a graph type. The graph type implies what
 * algorithm is used for the generation.
 * 
 * @author mri
 */
public class RandomGraphTypePage extends WizardPage {

    /** the page title. */
    private static final String TITLE = "Graph Type";
    /** the description message for this page. */
    private static final String DESCRIPTION =
            "Select a graph type (defines what algorithm is used for generation)";

    /** the description for the graph type 'ANY'. */
    private static final String ANY_DESCRIPTION = "Any graph";
    /** the description for the graph type 'TREE'. */
    private static final String TREE_DESCRIPTION = "Tree";
    /** the description for the graph type 'BICONNECTED'. */
    private static final String BICONNECTED_DESCRIPTION = "Biconnected graph";
    /** the description for the graph type 'TRICONNECTED'. */
    private static final String TRICONNECTED_DESCRIPTION = "Triconnected graph";
    /** the description for the graph type 'ACYCLIC_NO_TRANSITIV_EDGES'. */
    private static final String ANTE_DESCRIPTION = "Acyclic graph without transitiv edges";

    /** the selected graph type. */
    private RandomGraphGenerator.GraphType graphType = RandomGraphGenerator.GraphType.ANY;

    /**
     * Constructs a RandomGraphTypePage.
     */
    public RandomGraphTypePage() {
        super("randomGraphTypePage");
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
        createGraphTypeGroup(composite);
        setControl(composite);
    }

    private void createGraphTypeGroup(final Composite parent) {
        Composite composite = new Composite(parent, SWT.NONE);
        RowLayout rowLayout = new RowLayout(SWT.VERTICAL);
        composite.setLayout(rowLayout);
        // create buttons
        addRadioButton(composite, ANY_DESCRIPTION, RandomGraphGenerator.GraphType.ANY, graphType);
        addRadioButton(composite, TREE_DESCRIPTION, RandomGraphGenerator.GraphType.TREE, graphType);
        addRadioButton(composite, BICONNECTED_DESCRIPTION,
                RandomGraphGenerator.GraphType.BICONNECTED, graphType);
        addRadioButton(composite, TRICONNECTED_DESCRIPTION,
                RandomGraphGenerator.GraphType.TRICONNECTED, graphType);
        addRadioButton(composite, ANTE_DESCRIPTION,
                RandomGraphGenerator.GraphType.ACYCLIC_NO_TRANSITIV_EDGES, graphType);
    }

    // CHECKSTYLEOFF MagicNumber
    private void addRadioButton(final Composite parent, final String description,
            final RandomGraphGenerator.GraphType type, final RandomGraphGenerator.GraphType selected) {
        final Button radio = new Button(parent, SWT.RADIO | SWT.LEFT);
        RowData rowData = new RowData();
        rowData.height = 40;
        radio.setLayoutData(rowData);
        radio.setText(description);
        if (type.equals(selected)) {
            radio.setSelection(true);
        }
        radio.addSelectionListener(new SelectionListener() {

            public void widgetSelected(final SelectionEvent e) {
                if (radio.getSelection()) {
                    graphType = type;
                }
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
    public void safePreferences() {
        IPreferenceStore preferenceStore = KEGDiagramPlugin.getDefault().getPreferenceStore();
        preferenceStore.setValue(RandomGraphGenerator.GRAPH_TYPE.getIdentifier().toString(),
                graphType.toString());
    }

    private void loadPreferences() {
        IPreferenceStore preferenceStore = KEGDiagramPlugin.getDefault().getPreferenceStore();
        graphType =
                RandomGraphGenerator.GraphType.valueOf(preferenceStore
                        .getString(RandomGraphGenerator.GRAPH_TYPE.getIdentifier().toString()));
    }

    private void setDefaultPreferences() {
        IPreferenceStore preferenceStore = KEGDiagramPlugin.getDefault().getPreferenceStore();
        preferenceStore.setDefault(RandomGraphGenerator.GRAPH_TYPE.getIdentifier().toString(),
                RandomGraphGenerator.GraphType.ANY.toString());
    }

    /**
     * Returns the selected graph type.
     * 
     * @return the graph type
     */
    public RandomGraphGenerator.GraphType getGraphType() {
        return graphType;
    }
}
