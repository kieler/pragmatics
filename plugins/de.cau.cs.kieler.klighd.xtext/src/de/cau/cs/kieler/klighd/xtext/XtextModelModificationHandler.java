/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.xtext;

import java.lang.reflect.Method;
import java.util.List;

import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;

import com.google.common.base.Function;

import de.cau.cs.kieler.klighd.ui.modifymodel.AbstractKlighdModelModificationHandler;

/**
 * This class can handle the modification of xtext models.
 * 
 * @author ckru
 */
public class XtextModelModificationHandler extends AbstractKlighdModelModificationHandler {

    /**
     * {@inheritDoc}
     */
    public boolean canHandle(final IWorkbenchPart workbenchPart) {
        if (workbenchPart instanceof XtextEditor) {
            return true;
        }
        return false;
    }

    @Override
    public void execute(final IWorkbenchPart workbenchPart, final Method m,
            final Object classObject, final List<Object> params) {
        Display.getDefault().asyncExec(new Runnable() {
            public void run() {
                ((XtextEditor) workbenchPart).getDocument().modify(
                        new IUnitOfWork.Void<XtextResource>() {
                            public void process(final XtextResource state) throws Exception {
                                m.invoke(classObject, params.toArray());
                            }
                        });
            }
        });
    }
    
    @Override
    public void execute(final IWorkbenchPart workbenchPart, final Function<String, Void> m,
            final String param) {
        Display.getDefault().asyncExec(new Runnable() {
            public void run() {
                ((XtextEditor) workbenchPart).getDocument().modify(
                        new IUnitOfWork.Void<XtextResource>() {
                            public void process(final XtextResource state) throws Exception {
                                m.apply(param);
                            }
                        });
            }
        });
    }

}
