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

/**
 * Base package of the layered layouter.
 * 
 * <p>Klay Layered is split into five main phases, each having their own package containing
 * the available implementations. Additionally, there are intermediate processors that
 * do some pre and post processing. Those also have their own package. Finally, Klay Layered
 * operates on its own graph structure, the Layered Graph. That, too, has its own package.</p>
 */
package de.cau.cs.kieler.klay.layered;
