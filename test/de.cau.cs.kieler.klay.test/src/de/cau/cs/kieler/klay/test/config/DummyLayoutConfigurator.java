/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.test.config;

import de.cau.cs.kieler.core.kgraph.KNode;

/**
 * A layout configurator that does nothing.
 * 
 * @author uru
 * 
 */
public class DummyLayoutConfigurator implements ILayoutConfigurator {
    /**
     * {@inheritDoc}
     */
    public void applyConfiguration(final KNode root) {
    }
    
    /**
     * {@inheritDoc}
     */
    public String getDescription() {
        return "";
    }
}
