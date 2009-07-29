/******************************************************************************
 * KIELER - Kiel Integrated Environment for Layout for the Eclipse RCP
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2008 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.layout.services;

import java.util.LinkedList;
import java.util.List;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KOption;

/**
 * A layout provider executes a layout algorithm to layout the child elements of
 * a node.
 * <p>
 * When used in Eclipse, layout providers must register through the
 * {@code layoutProviders} extension point. All layout providers
 * published to Eclipse this way are collected in the {@link LayoutServices}
 * singleton, provided the UI plugin is loaded.
 * 
 * @author <a href="mailto:ars@informatik.uni-kiel.de">Arne Schipper</a>
 */
public abstract class AbstractLayoutProvider {

	/** list of layout options to use as default */
	private List<KOption> defaultOptions = new LinkedList<KOption>();

	/**
	 * Initialize the layout provider with the given parameter.
	 * The default implementation does nothing.
	 * 
	 * @param parameter a string used to parameterize the layout provider instance
	 */
	public void initialize(String parameter) {
	}
	
	/**
	 * Performs the actual layout process, that is attaches layout
     * information to the given node object.
	 * 
	 * @param layoutNode the parent node which should be laid out
	 * @param progressMonitor progress monitor used to keep track of progress
	 * @throws KielerException if the method fails to perform layout
	 */
	public abstract void doLayout(KNode layoutNode,
			IKielerProgressMonitor progressMonitor) throws KielerException;

	/**
	 * Returns the list of default options. This list may be
	 * arbitrarily modified to set or remove options for the
	 * layout provider.
	 * 
	 * @return default options for the layout provider
	 * @deprecated the layout options mechanism will be reimplemented soon
	 */
	public List<KOption> getDefaultOptions() {
	    return defaultOptions;
	}
	
	/**
	 * Returns the first default option with given key.
	 * 
	 * @param key key for the option to look up
	 * @return the associated option, or null if there is none
	 */
	protected KOption getDefault(String key) {
	    for (KOption option : defaultOptions) {
	        if (option.getKey().equals(key))
	            return option;
	    }
	    return null;
	}
	
}
