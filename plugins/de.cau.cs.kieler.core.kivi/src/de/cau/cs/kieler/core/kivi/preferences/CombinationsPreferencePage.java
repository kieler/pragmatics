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
package de.cau.cs.kieler.core.kivi.preferences;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.ColorFieldEditor;
import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import de.cau.cs.kieler.core.kivi.CombinationParameter;
import de.cau.cs.kieler.core.kivi.Descriptor;
import de.cau.cs.kieler.core.kivi.KiVi;
import de.cau.cs.kieler.core.ui.util.DoubleFieldEditor;
import de.cau.cs.kieler.core.ui.util.FloatFieldEditor;

/**
 * Displays all available preferences for all registered combinations.
 * 
 * @author mmu
 * 
 */
public class CombinationsPreferencePage extends PreferencePage implements IWorkbenchPreferencePage {

    private List<FieldEditor> parameterEditors = new ArrayList<FieldEditor>();

    /**
     * {@inheritDoc}
     */
    public void init(final IWorkbench workbench) {
    }

    @Override
    public boolean performOk() {
        if (super.performOk()) {
            for (FieldEditor editor : parameterEditors) {
                editor.store();
            }
            return true;
        }
        return false;
    }

    @Override
    protected void performDefaults() {
        super.performDefaults();
        for (FieldEditor editor : parameterEditors) {
            editor.loadDefault();
        }
    }

    @Override
    protected Control createContents(final Composite parent) {
        Font font = parent.getFont();
        Composite main = new Composite(parent, SWT.NONE);
        main.setLayout(new GridLayout());

        List<Descriptor> combinations = KiVi.getInstance().getAvailableCombinations();
        for (Descriptor descriptor : combinations) {
            CombinationParameter[] parameters = CombinationParameter.getParameters(descriptor
                    .getClazz());
            if (parameters.length > 0) {
                Composite current = new Composite(main, SWT.NONE);
                current.setLayout(new GridLayout());
                Label label = new Label(main, SWT.NONE);
                label.setText(descriptor.getName());
                label.setFont(font);

                for (CombinationParameter parameter : parameters) {
                    FieldEditor editor = createFieldEditor(parameter, current);
                    if (editor != null) {
                        parameterEditors.add(editor);
                    }
                }
            }
        }
        return null;
    }

    private FieldEditor createFieldEditor(final CombinationParameter parameter,
            final Composite parent) {
        FieldEditor editor = null;
        if (parameter.getType().equals(String.class)) {
            editor = new StringFieldEditor(parameter.getKey(), parameter.getName(), parent);
        } else if (parameter.getType().equals(Integer.class)) {
            editor = new IntegerFieldEditor(parameter.getKey(), parameter.getName(), parent);
        } else if (parameter.getType().equals(Float.class)) {
            editor = new FloatFieldEditor(parameter.getKey(), parameter.getName(), parent);
        } else if (parameter.getType().equals(Double.class)) {
            editor = new DoubleFieldEditor(parameter.getKey(), parameter.getName(), parent);
        } else if (parameter.getType().equals(Boolean.class)) {
            editor = new BooleanFieldEditor(parameter.getKey(), parameter.getName(), parent);
        } else if (parameter.getType().equals(RGB.class)) {
            editor = new ColorFieldEditor(parameter.getKey(), parameter.getName(), parent);
        } else {
            editor = new StringFieldEditor(parameter.getKey(), parameter.getName()
                    + " (unknown type!)", parent);
        }

        if (editor != null) {
            editor.setPreferenceStore(parameter.getPreferenceStore());
            editor.load();
        }
        return editor;
    }
}
