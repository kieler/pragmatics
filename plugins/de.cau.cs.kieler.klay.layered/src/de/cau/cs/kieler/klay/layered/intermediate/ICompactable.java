/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2015 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.intermediate;

import java.util.List;

/**
 * @author dag
 *
 */
interface ICompactable {
    /**
     * TODO docs
     * @return
     */
    List<CNode> getCNodes();
    int getPendingPlacements();
    //FIXME meh
    void setordecpendingplacements();
    double getOffset(CNode cNode);
    double getStartX();
    // TODO List<CNode>getSinks();
}
