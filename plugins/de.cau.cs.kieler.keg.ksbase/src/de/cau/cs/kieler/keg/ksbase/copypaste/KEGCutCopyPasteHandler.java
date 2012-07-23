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
package de.cau.cs.kieler.keg.ksbase.copypaste;

import java.util.Iterator;

import org.eclipse.gef.EditPart;
import org.eclipse.jface.viewers.IStructuredSelection;

import de.cau.cs.kieler.core.model.gmf.handlers.AbstractCutCopyPasteHandler;
import de.cau.cs.kieler.core.model.gmf.handlers.ICutCopyPasteCommandFactory;
import de.cau.cs.kieler.keg.custom.KEGConnection;
import de.cau.cs.kieler.keg.custom.KEGNode;

/**
 * Graphs specific handler for cut, copy and paste operations.
 * 
 * @author mri
 * @kieler.ignore (excluded from review process)
 */
public class KEGCutCopyPasteHandler extends AbstractCutCopyPasteHandler {

    /**
     * Constructs a new GraphsCutCopyPasteHandler.
     */
    public KEGCutCopyPasteHandler() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean isValidSelection(final IStructuredSelection selection) {
        Iterator<?> iter = selection.iterator();

        while (iter.hasNext()) {
            Object object = iter.next();
            if (object instanceof EditPart) {
                EditPart editPart = (EditPart) object;
                if (editPart instanceof KEGConnection) {
                    continue;
                } else if (editPart instanceof KEGNode) {
                    continue;
                }
            }
            // selection contains invalid parts, return.
            return false;
        }
        // no invalid parts found
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected ICutCopyPasteCommandFactory getCommandFactory() {
        return new KEGCutCopyPasteCommandFactory();
    }

}
