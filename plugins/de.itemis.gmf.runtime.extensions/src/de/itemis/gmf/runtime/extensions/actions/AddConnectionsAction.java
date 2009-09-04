/*******************************************************************************
 * Copyright (c) 2008 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package de.itemis.gmf.runtime.extensions.actions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import de.itemis.gmf.runtime.edit.policy.SemiCanonicalDiagramEditPolicy;

public class AddConnectionsAction implements IObjectActionDelegate {

	private ArrayList<IGraphicalEditPart> selectedEditParts;

	/**
	 * Constructor for Action1.
	 */
	public AddConnectionsAction() {
		super();
	}

	/**
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
	}

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		// TODO: node edit part
		Set<EObject> selectedSemanticElements = new HashSet<EObject>();
		Set<DiagramEditPart> selectedDiagramEditParts = new HashSet<DiagramEditPart>();
		for (IGraphicalEditPart selectedEditPart : selectedEditParts) {
			Object view = selectedEditPart.getModel();
			if (view instanceof Node) {
				selectedSemanticElements.add(selectedEditPart
						.resolveSemanticElement());
				DiagramEditPart diagramEditPart = getDiagramEditPart(selectedEditPart);
				selectedDiagramEditParts.add(diagramEditPart);
			}
		}
		for (DiagramEditPart diagramEditPart : selectedDiagramEditParts) {
			SemiCanonicalDiagramEditPolicy semiCanonicalEditPolicy = (SemiCanonicalDiagramEditPolicy) diagramEditPart
					.getEditPolicy(SemiCanonicalDiagramEditPolicy.SEMI_CANONICAL_ROLE);
			semiCanonicalEditPolicy.addConnections(selectedSemanticElements);
		}
	}


	private DiagramEditPart getDiagramEditPart(EditPart editPart) {
		if (editPart instanceof DiagramEditPart) {
			return (DiagramEditPart) editPart;
		}
		EditPart parent = editPart.getParent();
		if (parent != null) {
			return getDiagramEditPart(parent);
		}
		return null;
	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		action.setEnabled(false);
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			for (Iterator<?> i = structuredSelection.iterator(); i.hasNext();) {
				Object selectedElement = i.next();
				selectedEditParts = new ArrayList<IGraphicalEditPart>();
				if (selectedElement instanceof IGraphicalEditPart) {
					IGraphicalEditPart graphicalEditPart = (IGraphicalEditPart) selectedElement;
					selectedEditParts.add(graphicalEditPart);
					action.setEnabled(true);
				}
			}
		}
	}

}
