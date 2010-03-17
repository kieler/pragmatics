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
package de.cau.cs.kieler.dataflow.diagram.edit.helpers;

import org.eclipse.gmf.runtime.common.core.command.CompositeCommand;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.edithelper.AbstractEditHelper;
import org.eclipse.gmf.runtime.emf.type.core.edithelper.IEditHelperAdvice;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyReferenceRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.IEditCommandRequest;

/**
 * @generated
 */
public class DataflowBaseEditHelper extends AbstractEditHelper {

    /**
     * @generated
     */
    public static final String EDIT_POLICY_COMMAND = "edit policy command"; //$NON-NLS-1$

    /**
     * @generated
     */
    public static final String CONTEXT_ELEMENT_TYPE = "context element type"; //$NON-NLS-1$

    /**
     * @generated
     */
    protected IEditHelperAdvice[] getEditHelperAdvice(IEditCommandRequest req) {
        if (req.getParameter(CONTEXT_ELEMENT_TYPE) instanceof IElementType) {
            return ElementTypeRegistry.getInstance().getEditHelperAdvice(
                    (IElementType) req.getParameter(CONTEXT_ELEMENT_TYPE));
        }
        return super.getEditHelperAdvice(req);
    }

    /**
     * @generated
     */
    protected ICommand getInsteadCommand(IEditCommandRequest req) {
        ICommand epCommand = (ICommand) req.getParameter(EDIT_POLICY_COMMAND);
        req.setParameter(EDIT_POLICY_COMMAND, null);
        ICommand ehCommand = super.getInsteadCommand(req);
        if (epCommand == null) {
            return ehCommand;
        }
        if (ehCommand == null) {
            return epCommand;
        }
        CompositeCommand command = new CompositeCommand(null);
        command.add(epCommand);
        command.add(ehCommand);
        return command;
    }

    /**
     * @generated
     */
    protected ICommand getCreateCommand(CreateElementRequest req) {
        return null;
    }

    /**
     * @generated
     */
    protected ICommand getCreateRelationshipCommand(
            CreateRelationshipRequest req) {
        return null;
    }

    /**
     * @generated
     */
    protected ICommand getDestroyElementCommand(DestroyElementRequest req) {
        return null;
    }

    /**
     * @generated
     */
    protected ICommand getDestroyReferenceCommand(DestroyReferenceRequest req) {
        return null;
    }
}
