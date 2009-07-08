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
package de.cau.cs.kieler.klodd.ui;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import de.cau.cs.kieler.core.IKielerPreferenceStore;
import de.cau.cs.kieler.core.ui.KielerPreferenceStore;
import de.cau.cs.kieler.klodd.hierarchical.HierarchicalDataflowLayoutProvider;
import de.cau.cs.kieler.klodd.orthogonal.OrthogonalDataflowLayoutProvider;

/**
 * The activator class controls the plug-in life cycle
 */
public class KloddUIPlugin extends AbstractUIPlugin {

	/** the plug-in ID */
	public static final String PLUGIN_ID = "de.cau.cs.kieler.klodd.core";
	/** name of the KLoDD layouters collection */
	public static final String COLLECTION_NAME = "KLoDD Layouters";
	
	/** the shared instance */
	private static KloddUIPlugin plugin;
	
	/**
	 * The constructor
	 */
	public KloddUIPlugin() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		// set preference stores
		IKielerPreferenceStore preferenceStore =
				new KielerPreferenceStore(getPreferenceStore());
		HierarchicalDataflowLayoutProvider.setPreferenceStore(preferenceStore);
		OrthogonalDataflowLayoutProvider.setPreferenceStore(preferenceStore);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance.
	 *
	 * @return the shared instance
	 */
	public static KloddUIPlugin getDefault() {
		return plugin;
	}
	
	/**
	 * Returns an image descriptor for the image file at the given
	 * plug-in relative path
	 *
	 * @param path the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}
}
