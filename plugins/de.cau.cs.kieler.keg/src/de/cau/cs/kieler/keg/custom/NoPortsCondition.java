/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.keg.custom;

import de.cau.cs.kieler.karma.ICustomCondition;
import de.cau.cs.kieler.keg.Node;

/**
 * The condition for a node without ports.
 * 
 * @author mri
 * @kieler.ignore (excluded from review process)
 */
public class NoPortsCondition extends ICustomCondition<Node> {

    /**
     * {@inheritDoc}
     */
    public boolean evaluate(final Node node) {
        return node.getPorts().size() == 0;
    }
}
