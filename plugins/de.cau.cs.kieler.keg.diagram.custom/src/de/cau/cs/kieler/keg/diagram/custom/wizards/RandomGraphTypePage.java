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
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
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
public class RandomGraphTypePage extends AbstractRandomGraphPage {

    /** the page title. */
    private static final String TITLE = "Graph Type";
    /** the description message for this page. */
    private static final String DESCRIPTION =
            "Select a graph type (defines which algorithm is used for generation)";

    /** the label for the graph type 'ANY'. */
    private static final String LABEL_ANY = "Any graph";
    /** the label for the graph type 'TREE'. */
    private static final String LABEL_TREE = "Tree";
    /** the label for the graph type 'BICONNECTED'. */
    private static final String LABEL_BICONNECTED = "Biconnected graph";
    /** the label for the graph type 'TRICONNECTED'. */
    private static final String LABEL_TRICONNECTED = "Triconnected graph";
    /** the label for the graph type 'ACYCLIC_NO_TRANSITIV_EDGES'. */
    private static final String LABEL_ANTE = "Acyclic graph without transitiv edges";

    /** the description for the graph type 'ANY'. */
    private static final String DESCRIPTION_ANY =
            "A customizable algorithm that can generate any kind of graphs.";
    /** the description for the graph type 'TREE'. */
    private static final String DESCRIPTION_TREE =
            "This algorithm generates trees and has special options to limit the randomness.";
    /** the description for the graph type 'BICONNECTED'. */
    private static final String DESCRIPTION_BICONNECTED =
            "This algorithm generates biconnected graphs.";
    /** the description for the graph type 'TRICONNECTED'. */
    private static final String DESCRIPTION_TRICONNECTED =
            "This algorithm generates triconnected graphs.";
    /** the description for the graph type 'ACYCLIC_NO_TRANSITIV_EDGES'. */
    private static final String DESCRIPTION_ANTE =
            "This algorithm generates acyclic graphs without transitiv edges.";

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

    // CHECKSTYLEOFF MagicNumber
    private void createGraphTypeGroup(final Composite parent) {
        Composite composite = new Composite(parent, SWT.NONE);
        RowLayout rowLayout = new RowLayout(SWT.VERTICAL);
        rowLayout.spacing = 20;
        composite.setLayout(rowLayout);
        // create buttons
        Button anyButton =
                addRadioButton(composite, LABEL_ANY, RandomGraphGenerator.GraphType.ANY, graphType);
        addHelp(anyButton, DESCRIPTION_ANY);
        Button treeButton =
                addRadioButton(composite, LABEL_TREE, RandomGraphGenerator.GraphType.TREE,
                        graphType);
        addHelp(treeButton, DESCRIPTION_TREE);
        Button biconnectedButton =
                addRadioButton(composite, LABEL_BICONNECTED,
                        RandomGraphGenerator.GraphType.BICONNECTED, graphType);
        addHelp(biconnectedButton, DESCRIPTION_BICONNECTED);
        Button triconnectedButton =
                addRadioButton(composite, LABEL_TRICONNECTED,
                        RandomGraphGenerator.GraphType.TRICONNECTED, graphType);
        addHelp(triconnectedButton, DESCRIPTION_TRICONNECTED);
        Button anteButton =
                addRadioButton(composite, LABEL_ANTE,
                        RandomGraphGenerator.GraphType.ACYCLIC_NO_TRANSITIV_EDGES, graphType);
        addHelp(anteButton, DESCRIPTION_ANTE);
    }

    private Button addRadioButton(final Composite parent, final String description,
            final RandomGraphGenerator.GraphType type, final RandomGraphGenerator.GraphType selected) {
        final Button radio = new Button(parent, SWT.RADIO | SWT.LEFT);
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
        return radio;
    }

    // CHECKSTYLEON MagicNumber

    /**
     * Saves the selected options to the preference store.
     */
    public void savePreferences() {
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
