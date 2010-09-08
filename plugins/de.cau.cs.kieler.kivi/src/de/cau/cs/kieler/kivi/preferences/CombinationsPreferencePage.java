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
package de.cau.cs.kieler.kivi.preferences;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import de.cau.cs.kieler.kivi.KiViPlugin;
import de.cau.cs.kieler.kivi.core.CombinationDescriptor;
import de.cau.cs.kieler.kivi.core.CombinationParameter;
import de.cau.cs.kieler.kivi.core.Viewmanagement;

/**
 * Preference page for the view management.
 * 
 * @author mmu
 * 
 */
public class CombinationsPreferencePage extends PreferencePage implements IWorkbenchPreferencePage {

    private List<CombinationDescriptor> combinations = Viewmanagement.getInstance()
            .getAvailableCombinations();

    private List<StringFieldEditor> parameterEditors = new ArrayList<StringFieldEditor>();

    /**
     * Default constructor.
     */
    public CombinationsPreferencePage() {
        super("View management preference page - combinations");
    }

    /**
     * {@inheritDoc}
     */
    public void init(final IWorkbench workbench) {
        setPreferenceStore(KiViPlugin.getDefault().getPreferenceStore());
    }

    /**
     * {@inheritDoc}
     */
    public boolean performOk() {
        if (super.performOk()) {
            for (StringFieldEditor editor : parameterEditors) {
                editor.store();
            }
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    protected void performDefaults() {
        for (CombinationDescriptor descriptor : combinations) {
            Class<?> clazz = descriptor.getClazz();
            try {
                Method method = clazz.getMethod("getParameters");
                Object result = method.invoke(null);
                if (result instanceof CombinationParameter[]) {
                    CombinationParameter[] parameters = (CombinationParameter[]) result;
                    for (CombinationParameter parameter : parameters) {
                        getPreferenceStore().setValue(
                                clazz.getCanonicalName() + "." + parameter.getName(),
                                parameter.getDefaultValue());
                    }
                }
            } catch (SecurityException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        for (StringFieldEditor editor : parameterEditors) {
            editor.load();
        }
    }

    @Override
    protected Control createContents(final Composite parent) {

        Font font = parent.getFont();

        Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        // TODO check how much of all this layout stuff is actually needed
        GridLayout layout = new GridLayout();
        layout.marginWidth = 0;
        layout.marginHeight = 0;
        layout.verticalSpacing = 10;
        mainComposite.setLayout(layout);

        Label topLabel = new Label(mainComposite, SWT.NONE);
        topLabel.setText("Combination Preferences");
        topLabel.setFont(font);

        // FIXME better layout

        for (CombinationDescriptor descriptor : combinations) {
            Class<?> clazz = descriptor.getClazz();
            try {
                Method method = clazz.getMethod("getParameters");
                Object result = method.invoke(null);
                if (result instanceof CombinationParameter[]) {
                    CombinationParameter[] parameters = (CombinationParameter[]) result;
                    if (parameters.length > 0) {
                        new Label(mainComposite, SWT.NONE).setText(descriptor.getName());
                    }
                    for (CombinationParameter parameter : parameters) {
                        StringFieldEditor editor = new StringFieldEditor(clazz.getCanonicalName()
                                + "." + parameter.getName(), parameter.getName(), mainComposite);
                        editor.setPreferenceStore(getPreferenceStore());
                        if (getPreferenceStore().contains(
                                clazz.getCanonicalName() + "." + parameter.getName())) {
                            editor.load();
                        } else {
                            editor.setStringValue(parameter.getDefaultValue());
                        }
                        parameterEditors.add(editor);
                    }
                }
            } catch (SecurityException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return null;
    }

    /**
     * {@inheritDoc}
     */
    protected IPreferenceStore doGetPreferenceStore() {
        return KiViPlugin.getDefault().getPreferenceStore();
    }

}
