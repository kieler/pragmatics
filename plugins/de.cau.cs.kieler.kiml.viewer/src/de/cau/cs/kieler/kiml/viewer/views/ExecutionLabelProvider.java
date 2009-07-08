/******************************************************************************
 * KIELER - Kiel Integrated Environment for Layout for the Eclipse RCP
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
package de.cau.cs.kieler.kiml.viewer.views;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.kiml.viewer.KimlViewerPlugin;

/**
 * Label provider for execution times retrieved from KIELER progress
 * monitors.
 * 
 * @author msp
 */
public class ExecutionLabelProvider extends LabelProvider {

	/** path to the image used for elements */
	private static final String IMAGE_PATH = "/icons/execution.gif";
	
	/** the image used for each element */
	private Image elementImage;
	
	/**
	 * Creates an execution label provider.
	 */
	public ExecutionLabelProvider() {
		elementImage = KimlViewerPlugin.imageDescriptorFromPlugin(
				KimlViewerPlugin.PLUGIN_ID, IMAGE_PATH).createImage();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ILabelProvider#getImage(java.lang.Object)
	 */
	public Image getImage(Object element) {
		if (element instanceof IKielerProgressMonitor)
			return elementImage;
		else return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ILabelProvider#getText(java.lang.Object)
	 */
	public String getText(Object element) {
		if (element instanceof IKielerProgressMonitor) {
			IKielerProgressMonitor monitor = (IKielerProgressMonitor)element;
			String baseText = monitor.getTaskName() + ": ";
			double time = monitor.getExecutionTime();
			if (time >= 1.0)
				return baseText + String.format("%1$.3f s", time);
			else
				return baseText + String.format("%1$.3f ms", time * 1000);
		}
		else return null;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.BaseLabelProvider#dispose()
	 */
	public void dispose() {
		super.dispose();
		if (elementImage != null)
			elementImage.dispose();
	}

}
