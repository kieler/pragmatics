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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import de.cau.cs.kieler.core.kivi.Descriptor;
import de.cau.cs.kieler.core.kivi.KiVi;
import de.cau.cs.kieler.core.util.Pair;

/**
 * Displays all available preferences for all registered combinations.
 * 
 * @author mmu
 * 
 */
public class CombinationsPreferencePage extends PreferencePage implements IWorkbenchPreferencePage {

    /**
     * {@inheritDoc}
     */
    public void init(final IWorkbench workbench) {
    }

    @Override
    protected Control createContents(final Composite parent) {
        Font font = parent.getFont();
        Composite main = new Composite(parent, SWT.NONE);
        main.setLayout(new GridLayout());
        Label combinationsLabel = new Label(main, SWT.NONE);
        combinationsLabel.setText("Combinations");
        combinationsLabel.setFont(font);

        List<Descriptor> combinations = KiVi.getInstance().getAvailableCombinations();
        for (Descriptor descriptor : combinations) {
            List<Pair<String, Class<?>>> parameters = getParameters(descriptor.getClazz());
            if (parameters.size() > 0) {
                Composite current = new Composite(main, SWT.NONE);
                current.setLayout(new GridLayout());
                Label label = new Label(current, SWT.NONE);
                label.setText(descriptor.getName());
                label.setFont(font);

                for (Pair<String, Class<?>> pair : parameters) {
                    Label tmp = new Label(current, SWT.NONE);
                    tmp.setText(pair.getFirst());
                    tmp.setFont(font);
                }
            }
        }

        return null;
    }

    private List<Pair<String, Class<?>>> getParameters(final Class<?> combination) {
        try {
            Method method = combination.getMethod("getParameters");
            Object result = method.invoke(null);
            if (result instanceof List<?>) {
                return (List<Pair<String, Class<?>>>) result;
            }
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            // nothing to do
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
        return new ArrayList<Pair<String, Class<?>>>();
    }

}
