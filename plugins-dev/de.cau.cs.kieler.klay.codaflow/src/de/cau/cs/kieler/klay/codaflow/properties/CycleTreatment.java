/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.codaflow.properties;

/**
 * 
 * @author uru
 */
public enum CycleTreatment {

    /** Generate all constraints no matter if they contradict or not. */
    NONE,
    /** Do not generate any constraints for cycles. */
    IGNORE_SCCS,
    /** Align nodes of a scc and introduce a 'ladder'. */ 
    ALIGN_SCC, 
    /** Find the minimal feedback arc set for each strongly connected component. */
    MFAS_SCC, 
    /** Find the minimal feedback arc set for the entire graph. */
    MFAS_GLOBAL

}
