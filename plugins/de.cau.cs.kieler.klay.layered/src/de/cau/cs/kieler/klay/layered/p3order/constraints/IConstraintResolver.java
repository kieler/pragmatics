/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.p3order.constraints;

import java.util.List;

import de.cau.cs.kieler.klay.layered.graph.LNode;

/**
 * Detects and resolves violated constraints.
 * 
 * @author cds
 * @author ima
 * @author msp
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow by msp
 */
public interface IConstraintResolver {

    /**
     * Detects and resolves violated constraints.
     * 
     * @param nodes
     *            the nodes sorted by their barycenter values
     */
    void processConstraints(final List<LNode> nodes);

}
