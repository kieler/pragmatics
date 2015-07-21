/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2008 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.kiml.config.ILayoutConfig;

/**
 * A layout provider executes a layout algorithm to layout the child elements of a node.
 * <p>When used in Eclipse, layout providers must register through the {@code layoutProviders}
 * extension point. All layout providers published to Eclipse this way are collected in the
 * {@link LayoutMetaDataService} singleton, provided the UI plugin is loaded.
 * 
 * @kieler.design 2011-01-17 reviewed by haf, cmot, soh
 * @kieler.rating yellow 2012-08-10 review KI-23 by cds, sgu
 * @author ars
 * @author msp
 */
public abstract class AbstractLayoutProvider {

    /**
     * Property attached to the shape layout of the top-level {@link KNode} holding a reference
     * to an additional layout configurator that shall be applied for the remaining iterations.
     * This is applicable only when multiple layout iterations are performed, e.g. for applying
     * different layout algorithms one after another. If only a single iteration is performed or
     * the current iteration is the last one, the value of this property is ignored. Otherwise
     * the referenced configurator will be added to all iterations that follow the current one.
     */
    public static final IProperty<ILayoutConfig> ADD_LAYOUT_CONFIG = new Property<ILayoutConfig>(
            "kiml.addLayoutConfig");
    
    /**
     * Initialize the layout provider with the given parameter.
     * 
     * @param parameter a string used to parameterize the layout provider instance
     */
    public void initialize(final String parameter) {
        // do nothing - override in subclasses
    }
    
    /**
     * Dispose the layout provider by releasing any resources that are held.
     */
    public void dispose() {
        // do nothing - override in subclasses
    }

    /**
     * Perform the actual layout process, that is attach layout information to the children of the
     * given parent node.
     * 
     * @param parentNode the parent node containing the graph which should be laid out
     * @param progressMonitor progress monitor used to keep track of progress
     * @throws UnsupportedGraphException if the given graph is not supported by this algorithm
     */
    public abstract void doLayout(KNode parentNode, IKielerProgressMonitor progressMonitor);

}
