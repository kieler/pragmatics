/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.zest.preferences;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import de.cau.cs.kieler.core.ui.util.FloatFieldEditor;
import de.cau.cs.kieler.kiml.zest.Activator;

/**
 * Preference page for the Zest layouters plugin.
 * 
 * @author <a href="mailto:msp@informatik.uni-kiel.de">Miro Sp&ouml;nemann</a>
 */
public class ZestLayouterPreferencePage extends FieldEditorPreferencePage implements
        IWorkbenchPreferencePage {

    /** scale factor to use for the size of each parent node. */
    public static final String PREF_SCALE_BASE = "zest.scale_base";

    /**
     * Creates a Zest preference page.
     */
    public ZestLayouterPreferencePage() {
        super(FLAT);
        setDescription("Preferences for the Zest Layouters.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createFieldEditors() {
        // options group
        Composite parent = getFieldEditorParent();
        Group optionsGroup = new Group(parent, SWT.NONE);
        optionsGroup.setText("General Options:");

        FloatFieldEditor scaleBaseEditor = new FloatFieldEditor(PREF_SCALE_BASE,
                "&Scale base for parent nodes:", optionsGroup);
        addField(scaleBaseEditor);

        optionsGroup.setLayout(new GridLayout(2, true));
        parent.setLayout(new FillLayout());
    }

    /**
     * {@inheritDoc}
     */
    public void init(final IWorkbench workbench) {
        setPreferenceStore(Activator.getDefault().getPreferenceStore());
    }

}
