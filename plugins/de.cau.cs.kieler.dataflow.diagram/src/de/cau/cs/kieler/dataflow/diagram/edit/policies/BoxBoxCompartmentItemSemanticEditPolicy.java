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
package de.cau.cs.kieler.dataflow.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;

import de.cau.cs.kieler.dataflow.diagram.edit.commands.Box2CreateCommand;
import de.cau.cs.kieler.dataflow.diagram.providers.DataflowElementTypes;

/**
 * @generated
 */
public class BoxBoxCompartmentItemSemanticEditPolicy extends DataflowBaseItemSemanticEditPolicy {

    /**
     * @generated
     */
    public BoxBoxCompartmentItemSemanticEditPolicy() {
        super(DataflowElementTypes.Box_2001);
    }

    /**
     * @generated
     */
    protected Command getCreateCommand(CreateElementRequest req) {
        if (DataflowElementTypes.Box_3003 == req.getElementType()) {
            return getGEFWrapper(new Box2CreateCommand(req));
        }
        return super.getCreateCommand(req);
    }

}
