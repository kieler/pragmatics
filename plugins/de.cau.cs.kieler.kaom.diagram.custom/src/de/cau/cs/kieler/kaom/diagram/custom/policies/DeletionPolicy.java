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

import org.eclipse.gef.EditPart;

import de.cau.cs.kieler.core.ui.policies.DeletionPolicyProvider;
import de.cau.cs.kieler.kaom.diagram.edit.parts.Entity2EditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.Entity3EditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.EntityEditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.EntityEntityCompartment2EditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.EntityEntityCompartmentEditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.EntityName2EditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.EntityNameEditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.LinkEditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.LinkNameEditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.PortEditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.PortNameEditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.Relation2EditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.RelationEditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.RelationName2EditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.RelationNameEditPart;

/**
 * This policy prevents deletion of edit parts that should not be deleted from a
 * synccharts diagram.
 * 
 * @author soh
 * @kieler.rating 2010-03-12 proposed yellow
 */
public class DeletionPolicy extends DeletionPolicyProvider {

    private static final Class<?>[] ALL_PARTS = { Entity2EditPart.class,
            Entity3EditPart.class, EntityEditPart.class,
            EntityEntityCompartment2EditPart.class,
            EntityEntityCompartmentEditPart.class, EntityName2EditPart.class,
            EntityNameEditPart.class, LinkEditPart.class,
            LinkNameEditPart.class, PortEditPart.class, PortNameEditPart.class,
            Relation2EditPart.class, RelationEditPart.class,
            RelationName2EditPart.class, RelationNameEditPart.class };

    private static final Class<?>[] REMOVABLE_PARTS = { Entity2EditPart.class,
            Entity3EditPart.class, EntityEditPart.class, LinkEditPart.class,
            PortEditPart.class, Relation2EditPart.class, RelationEditPart.class };

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public boolean isUnremovableEditPart(final EditPart editPart) {
        for (Class<?> aClass : ALL_PARTS) {
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
        for (Class<?> aClass : ALL_PARTS) {
            if (aClass.equals(editPart.getClass())) {
                return true;
            }
        }
        return false;
    }
}
