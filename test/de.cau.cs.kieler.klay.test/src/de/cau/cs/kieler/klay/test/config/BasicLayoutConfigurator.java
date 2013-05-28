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

/**
 * A simple {@link ILayoutConfigurator} that takes a description as constructor argument.
 * 
 * @author uru
 */
public abstract class BasicLayoutConfigurator implements ILayoutConfigurator {

    private String description;

    /**
     * @param descr
     *            a description of this layout configurator.
     */
    public BasicLayoutConfigurator(final String descr) {
        this.description = descr;
    }

    /**
     * {@inheritDoc}
     */
    public String getDescription() {
        return description;
    }

}
