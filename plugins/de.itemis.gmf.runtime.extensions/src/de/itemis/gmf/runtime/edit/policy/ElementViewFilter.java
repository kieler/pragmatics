/*******************************************************************************
 * Copyright (c) 2008 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package de.itemis.gmf.runtime.edit.policy;

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.notation.View;

public class ElementViewFilter {

	@SuppressWarnings("unchecked")
	public static List<EObject> filterElementsWithoutViewInParent(List<EObject> semanticChildren, View parentView) {
		EList<View> childViews = parentView.getChildren();
		SEMANTIC_LOOP: for(Iterator<EObject> semanticChildrenIter = semanticChildren.iterator(); semanticChildrenIter.hasNext();) {
			EObject semanticChild = semanticChildrenIter.next();
			URI sematicChildURI = EcoreUtil.getURI(semanticChild);
			for(View childView:childViews) {
				if(sematicChildURI.equals(EcoreUtil.getURI(childView.getElement()))) {
					System.out.println("Keeping " + semanticChild);
					continue SEMANTIC_LOOP;
				}
			}
			System.out.println("Removing " + semanticChild);
			semanticChildrenIter.remove();
		}
		System.out.println("X\n");
		return semanticChildren;
	}
	
}
