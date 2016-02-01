/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2015 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.p4nodes.bk;

/**
 * Specifies how the compaction step of the {@link BKNodePlacer} should be executed.
 * 
 * @author uru
 */
public enum CompactionStrategy {
    /** As specified in the original paper. */
    CLASSIC,
    /** An integrated method trying to increase the number of straight edges. */
    IMPROVE_STRAIGHTNESS,
}
