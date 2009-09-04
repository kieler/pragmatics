/*******************************************************************************
 * Copyright (c) 2008 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package de.itemis.gmf.runtime.editingdomain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramEditorInput;
import org.eclipse.gmf.runtime.emf.core.util.EMFCoreUtil;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;

import de.itemis.gmf.runtime.extensions.Activator;

public class ResourceUnloadingTool {

	public static void unloadEditorInput(ResourceSet resourceSet,
			IEditorInput editorInput) {
		EList<Resource> resources = resourceSet.getResources();
		List<Resource> resourcesToUnload = new ArrayList<Resource>(resources);
		IEditorReference[] editorReferences = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage()
				.getEditorReferences();
		for (IEditorReference openEditorReference : editorReferences) {
			try {
				IEditorInput openEditorInput = openEditorReference
						.getEditorInput();
				if (openEditorInput != editorInput) {
					IEditorPart openEditor = openEditorReference
							.getEditor(false);
					if (openEditor instanceof DiagramEditor) {
						DiagramEditor openDiagramEditor = (DiagramEditor) openEditor;
						ResourceSet diagramResourceSet = openDiagramEditor
								.getEditingDomain().getResourceSet();
						if (diagramResourceSet == resourceSet) {
							Resource diagramResource = getDiagramResource(
									diagramResourceSet, openEditorInput);
							resourcesToUnload.remove(diagramResource);
							Collection<?> imports = EMFCoreUtil
									.getImports(diagramResource);
							resourcesToUnload.removeAll(imports);
						}
					}
				}
			} catch (Exception exc) {
				Activator.logError("Error unloading resource", exc);
			}
		}
		for (Resource resourceToUnload : resourcesToUnload) {
			try {
				resourceToUnload.unload();
				resources.remove(resourceToUnload);
			} catch (Throwable t) {
				Activator.logError("Error unloading resource", t);
			}
		}
	}

	private static Resource getDiagramResource(ResourceSet resourceSet,
			IEditorInput editorInput) {
		Resource diagramResource = null;
		if (editorInput instanceof URIEditorInput) {
			URI resourceURI = ((URIEditorInput) editorInput).getURI()
					.trimFragment();
			diagramResource = resourceSet.getResource(resourceURI, false);
		} else if (editorInput instanceof IDiagramEditorInput) {
			Diagram diagram = ((IDiagramEditorInput) editorInput).getDiagram();
			diagramResource = diagram.eResource();
		} else if (editorInput instanceof FileEditorInput) {
			URI resourceURI = URI.createPlatformResourceURI(
					((FileEditorInput) editorInput).getFile().getFullPath()
							.toString(), true);
			diagramResource = resourceSet.getResource(resourceURI, false);
		}
		return diagramResource;
	}

}
