/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.damos.layout;

import java.util.EnumSet;
import java.util.Set;

import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.CompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.LabelEditPart;
import org.eclipselabs.damos.diagram.ui.editparts.ComponentEditPart;
import org.eclipselabs.damos.diagram.ui.editparts.PortEditPart;

import de.cau.cs.kieler.core.util.Maybe;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.gmf.GmfLayoutConfig;

/**
 * Specialized layout configuration for Damos.
 *
 * @author msp
 */
public class DamosLayoutConfig extends GmfLayoutConfig {

    /**
     * {@inheritDoc}
     */
    @Override
    protected Set<LayoutOptionData.Target> findTarget(final IGraphicalEditPart editPart,
            final Maybe<IGraphicalEditPart> containerEditPart,
            final Maybe<Boolean> hasPorts) {
        Set<LayoutOptionData.Target> partTarget = null;
        if (editPart instanceof PortEditPart) {
            partTarget = EnumSet.of(LayoutOptionData.Target.PORTS);
            containerEditPart.set((IGraphicalEditPart) editPart.getParent().getParent());
            
        } else if (editPart instanceof ComponentEditPart) {
            partTarget = EnumSet.of(LayoutOptionData.Target.NODES);
            containerEditPart.set((IGraphicalEditPart) editPart.getParent());
            hasPorts.set(Boolean.TRUE);
            
        } else if (editPart instanceof ConnectionEditPart) {
            partTarget = EnumSet.of(LayoutOptionData.Target.EDGES);
            EditPart sourcePart = ((ConnectionEditPart) editPart).getSource();
            if (sourcePart instanceof PortEditPart) {
                containerEditPart.set((IGraphicalEditPart) sourcePart.getParent().getParent());
            } else {
                containerEditPart.set((IGraphicalEditPart) sourcePart.getParent());
            }
            
        } else if (editPart instanceof LabelEditPart) {
            partTarget = EnumSet.of(LayoutOptionData.Target.LABELS);
            containerEditPart.set((IGraphicalEditPart) editPart.getParent());
            if (containerEditPart.get() instanceof ConnectionEditPart) {
                EditPart sourcePart = ((ConnectionEditPart) containerEditPart.get()).getSource();
                if (sourcePart instanceof PortEditPart) {
                    containerEditPart.set((IGraphicalEditPart) sourcePart.getParent().getParent());
                } else {
                    containerEditPart.set((IGraphicalEditPart) sourcePart.getParent());
                }
            } else if (containerEditPart.get() instanceof PortEditPart) {
                containerEditPart.set((IGraphicalEditPart) containerEditPart.get()
                        .getParent().getParent());
            } else if (containerEditPart.get() instanceof ComponentEditPart) {
                containerEditPart.set((IGraphicalEditPart) containerEditPart.get().getParent());
            }
            
        } else if (editPart instanceof DiagramEditPart) {
            partTarget = EnumSet.of(LayoutOptionData.Target.PARENTS);
        }
        
        if (containerEditPart.get() instanceof CompartmentEditPart) {
            containerEditPart.set((IGraphicalEditPart) containerEditPart.get().getParent());
        }
        return partTarget;
    }

}
