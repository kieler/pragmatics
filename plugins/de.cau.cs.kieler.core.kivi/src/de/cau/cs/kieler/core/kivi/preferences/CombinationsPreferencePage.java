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
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import de.cau.cs.kieler.core.kivi.CombinationParameter;
import de.cau.cs.kieler.core.kivi.CombinationDescriptor;
import de.cau.cs.kieler.core.kivi.KiVi;
import de.cau.cs.kieler.core.ui.util.DoubleFieldEditor;
import de.cau.cs.kieler.core.ui.util.FloatFieldEditor;

/**
 * Displays all available preferences for all registered combinations.
 * 
 * @author mmu
 */
public class CombinationsPreferencePage extends PreferencePage implements IWorkbenchPreferencePage {
    
    /**
     * List of generated field editors.
     */
    private List<FieldEditor> parameterEditors = new ArrayList<FieldEditor>();
    
    
    /**
     * {@inheritDoc}
     */
    public void init(final IWorkbench workbench) {
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void dispose() {
        super.dispose();
        for (FieldEditor editor : parameterEditors) {
            editor.setPage(null);
            editor.setPropertyChangeListener(null);
            editor.setPreferenceStore(null);
        }
    }

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    protected void performDefaults() {
        super.performDefaults();
        for (FieldEditor editor : parameterEditors) {
            editor.loadDefault();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Control createContents(final Composite parent) {
        // Turn off magic numbers checking for GUI code
        // CHECKSTYLEOFF MagicNumber
        
        Font font = parent.getFont();
        
        Composite main = new Composite(parent, SWT.NONE);
        main.setLayout(new GridLayout());
        main.setLayoutData(new GridData(GridData.FILL_BOTH));

        List<CombinationDescriptor> combinations = KiVi.getInstance().getAvailableCombinations();
        for (CombinationDescriptor descriptor : combinations) {
            CombinationParameter<?>[] parameters = CombinationParameter.getParameters(descriptor
                    .getClazz());
            if (parameters.length > 0) {
                Group group = new Group(main, SWT.NONE);
                group.setText(descriptor.getName());
                group.setToolTipText(descriptor.getDescription());
                group.setFont(font);
                group.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
                
                for (CombinationParameter<?> parameter : parameters) {
                    FieldEditor editor = createFieldEditor(parameter, group);
                    if (editor != null) {
                        parameterEditors.add(editor);
                    }
                }
                
                /* Workaround!
                 * 
                 * Field editors each set their own layout on their parent control, which
                 * may mess up the layout for other field editors sharing the same parent.
                 * There are two ways to handle this:
                 * 
                 *  1. Give each field editor its own parent composite. This has the
                 *     severe drawback of messing up the tabular layout. Each composite
                 *     will have its own layout with possibly two columns, but the column
                 *     sizes will vary from composite to composite. The result is that
                 *     controls will not be nicely aligned to each other.
                 *  
                 *  2. Set a proper layout after the field editors have done their work.
                 *     This results in a nice tabular layout, but assumes that every field
                 *     editor needs a grid layout with two columns.
                 * 
                 * Having a nice tabular layout is important to usability, so we go with
                 * the second plan. There is currently one field editor that only uses (and
                 * expects) one column: the BooleanFieldEditor. To handle that case, we
                 * iterate through the group's controls looking for check boxes. When we
                 * find one, we make it span two columns instead of just one.
                 */
                GridLayout layout = new GridLayout(2, false);
                group.setLayout(layout);
                
                for (Control child : group.getChildren()) {
                    if (child instanceof Button) {
                        if ((child.getStyle() & SWT.CHECK) != 0) {
                            child.setLayoutData(new GridData(
                                    SWT.FILL, SWT.BEGINNING, true, false, 2, 1));
                        }
                    }
                }
            }
        }
        
        return main;
        
        // CHECKSTYLEON MagicNumber
    }
    
    /**
     * Creates a field editor for the given combination parameter.
     * 
     * @param parameter the parameter to create a field editor for.
     * @param parent the control to place the editor in.
     * @return the generated field editor.
     */
    private FieldEditor createFieldEditor(final CombinationParameter<?> parameter,
            final Composite parent) {
        
        FieldEditor editor = null;
        if (parameter.getType().equals(String.class)) {
            editor = new StringFieldEditor(parameter.getId(), parameter.getName(), parent);
        } else if (parameter.getType().equals(Integer.class)) {
            editor = new IntegerFieldEditor(parameter.getId(), parameter.getName(), parent);
        } else if (parameter.getType().equals(Float.class)) {
            editor = new FloatFieldEditor(parameter.getId(), parameter.getName(), parent);
        } else if (parameter.getType().equals(Double.class)) {
            editor = new DoubleFieldEditor(parameter.getId(), parameter.getName(), parent);
        } else if (parameter.getType().equals(Boolean.class)) {
            editor = new BooleanFieldEditor(parameter.getId(), parameter.getName(), parent);
        } else if (parameter.getType().equals(RGB.class)) {
            editor = new ColorFieldEditor(parameter.getId(), parameter.getName(), parent);
        } else {
            editor = new StringFieldEditor(parameter.getId(), parameter.getName()
                    + " (unknown type!)", parent);
        }

        editor.setPage(this);
        editor.setPreferenceStore(parameter.getPreferenceStore());
        editor.load();
        
        return editor;
    }
}
