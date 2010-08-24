/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.evol.ui;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import de.cau.cs.kieler.kiml.evol.EvolPlugin;

/**
 * Preference page for KIML Evolutionary.
 *
 * @author bdu
 *
 */
public class EvolPreferencePage extends FieldEditorPreferencePage
        implements
            IWorkbenchPreferencePage {

    /**
     * Creates a new preference page.
     */
    public EvolPreferencePage() {
        // The title of this preference page is already specified in the
        // extension details.
        super("", null, FLAT);
        setDescription("Preferences for Evolutionary Meta Layout.");
    }

    /**
     * {@inheritDoc}
     */
    public void init(final IWorkbench workbench) {
        setPreferenceStore(EvolPlugin.getDefault().getPreferenceStore());
    }

    @Override
    protected void createFieldEditors() {
        final Composite parent = getFieldEditorParent();
        // evolution parameters
        final Group algorithmGroup = new Group(parent, SWT.NONE);
        algorithmGroup.setText("Evolution parameters");
        final IntegerFieldEditor popSizeEditor =
                new IntegerFieldEditor(EvolPlugin.PREF_POPULATION_SIZE, "Population size:",
                        algorithmGroup, 30);
        popSizeEditor.setValidateStrategy(StringFieldEditor.VALIDATE_ON_FOCUS_LOST);
        popSizeEditor.setValidRange(1, Integer.MAX_VALUE);
        addField(popSizeEditor);

        // miscellaneous parameters
        final Group miscGroup = new Group(parent, SWT.NONE);
        miscGroup.setText("Misc parameters");
        // final RadioGroupFieldEditor editorsEditor =
        // new RadioGroupFieldEditor(EvolPlugin.PREF_EDITORS, "Use editors", 1,
        // new String[][] { { "all editors", EvolPlugin.ALL_EDITORS },
        // { "current editor", EvolPlugin.CURRENT_EDITOR } }, miscGroup,
        // true /* useGroup */);
        // addField(editorsEditor);

        final BooleanFieldEditor useLayoutHint =
                new BooleanFieldEditor(EvolPlugin.PREF_USE_LAYOUT_HINT_FROM_GENOME,
                        "Adopt layout hint from genome", miscGroup);
        addField(useLayoutHint);

        final BooleanFieldEditor useDifferentTypeLayoutHint =
                new BooleanFieldEditor(EvolPlugin.PREF_USE_DIFFERENT_TYPE_LAYOUT_HINT,
                        "... also for different types", miscGroup);
        addField(useDifferentTypeLayoutHint);

        // layout
        algorithmGroup.setLayout(new GridLayout(NUM_COLUMNS, false));
        miscGroup.setLayout(new GridLayout(1, false));
        parent.setLayout(new FillLayout());
    }

    private static final int NUM_COLUMNS = 2;
}
