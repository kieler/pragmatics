/*******************************************************************************
 * Copyright (c) 2008 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package de.itemis.gmf.runtime.extensions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;

public class DiagramEditorInfoRegistry {

	private static final String FILE_EXTENSION = "fileExtension";
	private static final String EXTENSION_POINT_ID = "de.itemis.gmf.runtime.extensions.diagramEditorInfo";
	private static final List<String> registeredDiagramFileExtensions;
	
	static {
		List<String> fileExtensions = new ArrayList<String>();
		IConfigurationElement[] configurationElements = Platform.getExtensionRegistry().getConfigurationElementsFor(EXTENSION_POINT_ID);
		for (IConfigurationElement configurationElement : configurationElements) {
			String fileExtension = configurationElement.getAttribute(FILE_EXTENSION);
			fileExtensions.add(fileExtension);
		}
		registeredDiagramFileExtensions = Collections.unmodifiableList(fileExtensions);
	}
	
	public static List<String> getRegisteredDiagramFileExtensions() {
		return registeredDiagramFileExtensions;
	}
	
	
}
