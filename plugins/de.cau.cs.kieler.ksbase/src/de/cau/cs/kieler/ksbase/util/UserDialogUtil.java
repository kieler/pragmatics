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
 * 
 *****************************************************************************/
package de.cau.cs.kieler.ksbase.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ListDialog;

/**
 * Utility class to create question dialogs to the user. Can be used in Xtend
 * transformations in JAVA extensions to get input from the user.
 * 
 * @author haf
 */
public class UserDialogUtil {

    public String getUserString(final String message, final String defaultValue) {
        String title = "User input required";
        InputDialog dialog = new InputDialog(PlatformUI.getWorkbench().getDisplay()
                .getActiveShell(), title, message, defaultValue, null);
        dialog.setBlockOnOpen(true);
        dialog.open();
        if (dialog.getValue() != null) {
            return dialog.getValue();
        }
        // if user hits cancel
        return defaultValue;
    }

    public int getUserInt(final String message, final Integer defaultValue) {
        String title = "User input required";
        IInputValidator validator = new IInputValidator() {
            public String isValid(final String newText) {
                try {
                    Integer.parseInt(newText);
                } catch (NumberFormatException e) {
                    return "Only integer numbers accepted!";
                }
                return null;
            }
        };
        InputDialog dialog = new InputDialog(PlatformUI.getWorkbench().getDisplay()
                .getActiveShell(), title, message, "" + defaultValue, validator);
        dialog.setBlockOnOpen(true);
        dialog.open();
        if (dialog.getValue() != null) {
            return Integer.parseInt(dialog.getValue());
        }
        // if user hits cancel
        return defaultValue;
    }

    public float getUserFloat(final String message, final Float defaultValue) {
        String title = "User input required";
        IInputValidator validator = new IInputValidator() {
            public String isValid(final String newText) {
                try {
                    Float.parseFloat(newText);
                } catch (NumberFormatException e) {
                    return "Only float numbers accepted!";
                }
                return null;
            }
        };
        InputDialog dialog = new InputDialog(PlatformUI.getWorkbench().getDisplay()
                .getActiveShell(), title, message, "" + defaultValue, validator);
        dialog.setBlockOnOpen(true);
        dialog.open();
        if (dialog.getValue() != null) {
            return Float.parseFloat(dialog.getValue());
        }
        // if user hits cancel
        return defaultValue;
    }

    public boolean getUserBoolean(final String message) {
        String title = "User input required";
        return MessageDialog.openQuestion(PlatformUI.getWorkbench().getDisplay().getActiveShell(),
                title, message);
    }

    public <T> List<T> getUserObjectFromList(final String message, final List<T> list) {
        String title = "User input required";
        ListDialog dialog = new ListDialog(PlatformUI.getWorkbench().getDisplay().getActiveShell());
        dialog.setMessage(message);
        dialog.setBlockOnOpen(true);
        dialog.setLabelProvider(new LabelProvider());
        dialog.setContentProvider(new SimpleListContentProvider());
        dialog.setInput(list);
        dialog.setHelpAvailable(false);
        dialog.open();

        if (dialog.getResult() != null) {
            return (List<T>) Arrays.asList(dialog.getResult());
        }
        // if user hits cancel
        return Collections.emptyList();
    }

    private static final class SimpleListContentProvider<T> implements IStructuredContentProvider {
        public Object[] getElements(Object inputElement) {
            if (inputElement instanceof List<?>) {
                return ((List<?>) inputElement).toArray();
            }
            return null;
        }

        public void dispose() {
        }

        public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        }
    }

}
