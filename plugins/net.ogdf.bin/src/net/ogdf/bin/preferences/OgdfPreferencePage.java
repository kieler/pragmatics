/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package net.ogdf.bin.preferences;

import net.ogdf.bin.OgdfServer;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.swt.widgets.Composite;
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
        super(GRID);
        setDescription("Controls how KIELER interacts with the OGDF layout library.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createFieldEditors() {
        // add the process group
        Composite parent = getFieldEditorParent();
        
        // add timeout editor
        IntegerFieldEditor timeoutEditor =
                new IntegerFieldEditor(OgdfServer.PREF_TIMEOUT, "Timeout (ms):", parent);
        timeoutEditor.setValidRange(OgdfServer.PROCESS_MIN_TIMEOUT, Integer.MAX_VALUE);
        addField(timeoutEditor);
    }

    /**
     * {@inheritDoc}
     */
    public void init(final IWorkbench workbench) {
        setPreferenceStore(OgdfPreferenceStore.getInstance().getPreferenceStore());
    }

}
