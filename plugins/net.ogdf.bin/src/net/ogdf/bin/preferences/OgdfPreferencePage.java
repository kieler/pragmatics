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
package net.ogdf.bin.preferences;

import net.ogdf.bin.OgdfPlugin;
import net.ogdf.bin.OgdfServer;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * The preference page for the OGDF integration.
 * 
 * @author mri
 */
public class OgdfPreferencePage extends FieldEditorPreferencePage implements
        IWorkbenchPreferencePage {

    /** identifier of the preference page. */
    public static final String ID = "net.ogdf.bin.preferences";

    /**
     * Creates the OgdfPreferencePage.
     */
    public OgdfPreferencePage() {
        super(FLAT);
        setDescription("Preferences for the OGDF integration.");
    }

    private static final int NUM_COLUMNS = 3;

    /**
     * {@inheritDoc}
     */
    @Override
    public void createFieldEditors() {
        // add the process group
        Composite parent = getFieldEditorParent();
        Group processGroup = new Group(parent, SWT.NONE);
        processGroup.setText("OGDF Process");
        // add timeout editor
        IntegerFieldEditor timeoutEditor =
                new IntegerFieldEditor(OgdfServer.PREF_TIMEOUT, "Timeout for OGDF output (ms):",
                        processGroup);
        timeoutEditor.setValidRange(OgdfServer.PROCESS_MIN_TIMEOUT, Integer.MAX_VALUE);
        addField(timeoutEditor);
        // set layout
        processGroup.setLayout(new GridLayout(NUM_COLUMNS, false));
        parent.setLayout(new FillLayout());
    }

    /**
     * {@inheritDoc}
     */
    public void init(final IWorkbench workbench) {
        setPreferenceStore(OgdfPlugin.getDefault().getPreferenceStore());
    }
}
