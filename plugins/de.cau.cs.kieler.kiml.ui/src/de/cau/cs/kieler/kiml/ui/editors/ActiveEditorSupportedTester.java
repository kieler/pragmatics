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
package de.cau.cs.kieler.kiml.ui.editors;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.ui.IEditorPart;

import de.cau.cs.kieler.kiml.ui.layout.EclipseLayoutServices;

/**
 * Tester class for checking whether the active editor is supported by KIML.
 * 
 * @author jjc
 */
public class ActiveEditorSupportedTester extends PropertyTester {

    /**
     * {@inheritDoc}
     */
    public boolean test(final Object receiver, final String property,
            final Object[] args, final Object expectedValue) {
        if (receiver instanceof IEditorPart) {
            IEditorPart part = (IEditorPart) receiver;
            if (receiver instanceof DiagramEditor) {
                return true;
            }
            for (IDiagramEditorConnector connector : EclipseLayoutServices.getInstance()
                    .getEditorConnectors()) {
                if (connector.supports(part)) {
                    return true;
                }
            }
        }
        return false;
    }

}
