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
package de.cau.cs.kieler.klay.planar.p1planar;

/**
 * An enum layout option to choose a planarity test algorithm.
 * 
 * @author ocl
 * @kieler.rating proposed yellow by pkl
 */
public enum PlanarityTestStrategy {

    /** The algorithm by Boyer and Myrvold. */
    BOYER_MYRVOLD_ALGORITHM,

    /** The algorithm based on the left-right-planarity test. */
    LEFT_RIGHT_PLANARITY_ALGORITHM;

}
