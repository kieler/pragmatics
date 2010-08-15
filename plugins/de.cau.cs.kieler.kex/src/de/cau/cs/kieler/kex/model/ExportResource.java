package de.cau.cs.kieler.kex.model;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;

public class ExportResource {

	private final IResource resource;

	private final IPath localPath;

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

}
