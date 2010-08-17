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
 * 
 *****************************************************************************/
package de.cau.cs.kieler.graphs.diagram.custom;

import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.AbstractEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.notation.View;

/**
 * Edit policy used to update the hypernodes structure. This edit policy creates a
 * {@link HypernodesCommand} to add and remove hypernodes in the graph.
 * 
 * @author msp
 */
public class HypernodesEditPolicy extends AbstractEditPolicy {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean understandsRequest(final Request req) {
        return (HypernodesRequest.REQ_UPDATE_HYPERNODES.equals(req.getType()));
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Command getCommand(final Request request) {
        if (HypernodesRequest.REQ_UPDATE_HYPERNODES.equals(request.getType())) {
            if (request instanceof HypernodesRequest) {
                HypernodesRequest hyperRequest = (HypernodesRequest) request;
                IGraphicalEditPart hostEditPart = (IGraphicalEditPart) getHost();
                HypernodesCommand command = new HypernodesCommand(
                        hostEditPart.getEditingDomain(), "???",
                        new EObjectAdapter((View) hostEditPart.getModel()));
                
                
                
                return new ICommandProxy(command);
            } else {
                return null;
            }
        } else {
            return super.getCommand(request);
        }
    }

}
