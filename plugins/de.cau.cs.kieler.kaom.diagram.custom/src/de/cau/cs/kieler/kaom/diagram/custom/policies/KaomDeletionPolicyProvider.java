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
package de.cau.cs.kieler.kaom.diagram.custom.policies;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.notation.View;

import de.cau.cs.kieler.core.ui.policies.AbstractDeletionPolicyProvider;
import de.cau.cs.kieler.kaom.diagram.edit.parts.Entity2EditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.Entity3EditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.EntityEditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.LinkEditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.PortEditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.Relation2EditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.RelationEditPart;
import de.cau.cs.kieler.kaom.impl.KaomPackageImpl;

/**
 * This policy prevents deletion of edit parts that should not be deleted from a
 * synccharts diagram.
 * 
 * @author soh
 * @kieler.rating 2010-03-12 proposed yellow
 */
public class KaomDeletionPolicyProvider extends AbstractDeletionPolicyProvider {

    private static final Class<?>[] REMOVABLE_PARTS = { Entity2EditPart.class,
            Entity3EditPart.class, EntityEditPart.class, LinkEditPart.class,
            PortEditPart.class, Relation2EditPart.class, RelationEditPart.class };

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public boolean isUnremovableEditPart(final EditPart editPart) {
        for (Class<?> aClass : REMOVABLE_PARTS) {
            if (aClass.equals(editPart.getClass())) {
                return false;
            }
        }
        return true;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public boolean provides(final EditPart editPart) {
        EObject element = ((View) editPart.getModel()).getElement();
        if (element != null) {
            return element.eClass().getEPackage().equals(
                    KaomPackageImpl.eINSTANCE);
        }
        return false;
    }
}
