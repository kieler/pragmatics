/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2008 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.graphviz.layouter.preferences;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import de.cau.cs.kieler.kiml.graphviz.layouter.GraphvizLayouterPlugin;
import de.cau.cs.kieler.kiml.graphviz.layouter.GraphvizAPI;

/**
 * The Graphviz preference page.
 * 
 * @kieler.rating 2009-12-21 proposed yellow msp
 * @author ars
 */
public class GraphvizPreferencePage extends FieldEditorPreferencePage implements
        IWorkbenchPreferencePage {

    /** identifier of the preference page. */
    public static final String ID = "de.cau.cs.kieler.kiml.graphviz.preferences";

    /**
     * Creates a Graphviz preference page.
     */
    public GraphvizPreferencePage() {
        super(FLAT);
        setDescription("Preferences for the Graphviz layouter.");
    }

    private static final int NUM_COLUMNS = 3;
    private static final int LABEL_WIDTH = 450;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void createFieldEditors() {
        // process group
        Composite parent = getFieldEditorParent();
        Group processGroup = new Group(parent, SWT.NONE);
        processGroup.setText("Graphviz Process");

        Label label = new Label(processGroup, SWT.WRAP);
        label.setText("The Graphviz layout tool is available at http://www.graphviz.org/. If the"
                + " 'dot' executable cannot be found in default locations, its path must be entered"
                + " here.");
        GridData labelLayoutData = new GridData(SWT.LEFT, SWT.FILL, false, false, NUM_COLUMNS, 1);
        labelLayoutData.widthHint = LABEL_WIDTH;
        label.setLayoutData(labelLayoutData);
        FileFieldEditor executableEditor = new FileFieldEditor(GraphvizAPI.PREF_GRAPHVIZ_EXECUTABLE,
                "Set path to the 'dot' executable:", processGroup);
        executableEditor.setValidateStrategy(FileFieldEditor.VALIDATE_ON_KEY_STROKE);
        addField(executableEditor);

        IntegerFieldEditor timeoutEditor = new IntegerFieldEditor(GraphvizAPI.PREF_TIMEOUT,
                "Timeout for Graphviz output (ms):", processGroup);
        timeoutEditor.setValidRange(GraphvizAPI.PROCESS_MIN_TIMEOUT, Integer.MAX_VALUE);
        addField(timeoutEditor);

        processGroup.setLayout(new GridLayout(NUM_COLUMNS, false));
        parent.setLayout(new FillLayout());
    }

    /**
     * {@inheritDoc}
     */
    public void init(final IWorkbench workbench) {
        setPreferenceStore(GraphvizLayouterPlugin.getDefault().getPreferenceStore());
    }

}
