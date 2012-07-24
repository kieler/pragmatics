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
 * @kieler.ignore (excluded from review process)
 */
public class RandomGraphTypePage extends WizardPage {

    /** the selected graph type. */
    private RandomGraphGenerator.GraphType graphType = RandomGraphGenerator.GraphType.ANY;

    /**
     * Constructs a RandomGraphTypePage.
     */
    public RandomGraphTypePage() {
        super("randomGraphTypePage"); //$NON-NLS-1$
        setTitle(Messages.RandomGraphTypePage_title);
        setDescription(Messages.RandomGraphTypePage_description);
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
                addRadioButton(composite, Messages.RandomGraphTypePage_any_graph_type_caption,
                        RandomGraphGenerator.GraphType.ANY, graphType);
        Util.addHelp(anyButton, Messages.RandomGraphTypePage_any_graph_type_help);
        Button treeButton =
                addRadioButton(composite, Messages.RandomGraphTypePage_tree_graph_type_caption,
                        RandomGraphGenerator.GraphType.TREE, graphType);
        Util.addHelp(treeButton, Messages.RandomGraphTypePage_tree_graph_type_help);
        Button biconnectedButton =
                addRadioButton(composite,
                        Messages.RandomGraphTypePage_biconnected_graph_type_caption,
                        RandomGraphGenerator.GraphType.BICONNECTED, graphType);
        Util.addHelp(biconnectedButton, Messages.RandomGraphTypePage_biconnected_graph_type_help);
        Button triconnectedButton =
                addRadioButton(composite,
                        Messages.RandomGraphTypePage_triconnected_graph_type_caption,
                        RandomGraphGenerator.GraphType.TRICONNECTED, graphType);
        Util.addHelp(triconnectedButton, Messages.RandomGraphTypePage_triconnected_graph_type_help);
        Button anteButton =
                addRadioButton(composite, Messages.RandomGraphTypePage_ante_graph_type_caption,
                        RandomGraphGenerator.GraphType.ACYCLIC_NO_TRANSITIV_EDGES, graphType);
        Util.addHelp(anteButton, Messages.RandomGraphTypePage_ante_graph_type_help);
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
        preferenceStore.setValue(RandomGraphGenerator.GRAPH_TYPE.getId(),
                graphType.toString());
    }

    private void loadPreferences() {
        IPreferenceStore preferenceStore = KEGDiagramPlugin.getDefault().getPreferenceStore();
        graphType =
                RandomGraphGenerator.GraphType.valueOf(preferenceStore
                        .getString(RandomGraphGenerator.GRAPH_TYPE.getId()));
    }

    private void setDefaultPreferences() {
        IPreferenceStore preferenceStore = KEGDiagramPlugin.getDefault().getPreferenceStore();
        preferenceStore.setDefault(RandomGraphGenerator.GRAPH_TYPE.getId(),
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
