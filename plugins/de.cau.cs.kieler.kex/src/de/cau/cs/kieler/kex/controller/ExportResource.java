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
package de.cau.cs.kieler.kex.controller;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;

public class ExportResource {

	private final IResource resource;

	private final IPath localPath;

	private boolean directOpen;

	public ExportResource(IResource resource, IPath localPath) {
		this.resource = resource;
		this.localPath = localPath;
	}

	public IResource getResource() {
		return resource;
	}

	public IPath getLocalPath() {
		return localPath;
	}

	public void setDirectOpen(boolean directOpen) {
		this.directOpen = directOpen;
	}

	public boolean isDirectOpen() {
		return directOpen;
	}

}
