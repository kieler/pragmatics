/*******************************************************************************
 * Copyright (c) 2008 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 **/
package de.itemis.gmf.runtime.edit.policy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.DiagramDragDropEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.DropObjectsRequest;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.notation.Node;

public class GenericDiagramDropEditPolicy extends DiagramDragDropEditPolicy {

	DiagramEditPart getDiagramEditPart() {
		return (DiagramEditPart) getHost();
	}

	@SuppressWarnings("unchecked")
	public Command getDropObjectsCommand(DropObjectsRequest dropRequest) {
		List viewDescriptors = new ArrayList();
		EObject hostDomainElement = ((IGraphicalEditPart) getHost())
				.resolveSemanticElement();
		for (Iterator it = dropRequest.getObjects().iterator(); it.hasNext();) {
			Object nextObject = it.next();
			EObject eObject = reloadAsEObjectInEditingDomain(nextObject);
			if (eObject != null) {
				if (eObject.eContainer() == hostDomainElement) {
				CreateViewRequest.ViewDescriptor viewDescriptor = new CreateViewRequest.ViewDescriptor(
						new EObjectAdapter(eObject), Node.class, null,
						getDiagramEditPart().getDiagramPreferencesHint());
					viewDescriptors.add(viewDescriptor);
				}
			}
		}
		Command command = createViewsAndArrangeCommand(dropRequest,
				viewDescriptors);
		return command;
	}

	public EObject reloadAsEObjectInEditingDomain(Object object) {
		if (!(object instanceof EObject)) {
			return null;
		}
		ResourceSet resourceSet = getEditingDomain().getResourceSet();
		URI proxyURI = EcoreUtil.getURI((EObject) object);
		if (proxyURI != null) {
			EObject eObjectInEditingDomain = resourceSet.getEObject(proxyURI,
					true);
			return eObjectInEditingDomain;
		}
		return null;
	}

	private TransactionalEditingDomain getEditingDomain() {
		return ((IGraphicalEditPart) getHost())
				.getEditingDomain();
	}
}
