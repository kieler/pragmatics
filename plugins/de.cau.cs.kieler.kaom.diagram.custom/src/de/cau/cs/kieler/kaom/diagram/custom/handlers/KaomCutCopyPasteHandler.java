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
package de.cau.cs.kieler.kaom.diagram.custom.handlers;

import java.util.Iterator;

import org.eclipse.gef.EditPart;
import org.eclipse.jface.viewers.IStructuredSelection;

import de.cau.cs.kieler.core.model.gmf.handlers.AbstractCutCopyPasteHandler;
import de.cau.cs.kieler.core.model.gmf.handlers.ICutCopyPasteCommandFactory;
import de.cau.cs.kieler.kaom.diagram.custom.commands.KaomCutCopyPasteCommandFactory;
import de.cau.cs.kieler.kaom.diagram.edit.parts.Entity2EditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.Entity3EditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.EntityEditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.LinkEditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.PortEditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.Relation2EditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.RelationEditPart;

/**
 * @author soh
 */
public class KaomCutCopyPasteHandler extends AbstractCutCopyPasteHandler {

    private static final Class<?>[] USABLE_PARTS = { Entity2EditPart.class,
            Entity3EditPart.class, EntityEditPart.class, LinkEditPart.class,
            PortEditPart.class, Relation2EditPart.class, RelationEditPart.class };

    /**
     * Constructor for CopyWithImageSupportGlobalActionHandler.
     */
    public KaomCutCopyPasteHandler() {
        super();
    }

    /**
     * Checks whether the selection contains only valid parts.
     * 
     * @param selection
     *            the selection
     * @return true if only valid parts are in the selection
     */
    @Override
    protected boolean isValidSelection(final IStructuredSelection selection) {
        Iterator<?> iter = selection.iterator();

        while (iter.hasNext()) {
            Object object = iter.next();
            if (object instanceof EditPart) {
                EditPart editPart = (EditPart) object;
                Class<?> editPartClass = editPart.getClass();
                boolean found = false;
                for (Class<?> c : USABLE_PARTS) {
                    if (c.equals(editPartClass)) {
                        found = true;
                        break;
                    }
                }
                if (found) {
                    continue;
                }
            }
            return false;
        }

        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected ICutCopyPasteCommandFactory getCommandFactory() {
        return new KaomCutCopyPasteCommandFactory();
    }

}
