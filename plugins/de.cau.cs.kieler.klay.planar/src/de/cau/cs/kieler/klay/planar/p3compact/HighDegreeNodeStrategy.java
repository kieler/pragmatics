/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.planar.p3compact;

/**
 * An enum layout option to choose a high-degree node algorithm.
 * 
 * @author pkl
 * @kieler.rating proposed yellow by pkl
 */
public enum HighDegreeNodeStrategy {

    /** The Giotto algorithm. */
    QUOD,

    /** The Quod algorithm. */
    GIOTTO;

}