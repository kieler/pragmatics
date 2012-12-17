/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.papyrus.sequence;

/**
 * Enumeration of possible message types for sequence diagrams.
 * 
 * @author grh
 * @kieler.design 2012-11-20 cds, msp
 * @kieler.rating yellow 2012-12-11 cds, ima
 */
public enum MessageType {
    /** Standard messages for sequence diagrams. */
    ASYNCHRONOUS,
    /** Synchronous messages. */
    SYNCHRONOUS,
    /** Reply messages. */
    REPLY,
    /** Create messages. */
    CREATE,
    /** Delete messages. */
    DELETE,
    /** Lost messages. */
    LOST,
    /** Found messages. */
    FOUND;
}
