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
package de.cau.cs.kieler.klay.tree.intermediate;

import de.cau.cs.kieler.klay.tree.ILayoutProcessor;

/**
 * TODO: Document this class.
 * 
 * @author sor
 * @author sgu
 */
public enum LayoutProcessorStrategy {

    /*
     * In this enumeration, intermediate layout processors are listed by the earliest slot in which
     * they can sensibly be used. The order in which they are listed is determined by the
     * dependencies on other processors.
     */

    // Before Phase 1

    // TODO add actual processors
    TEST_PROCESSOR,
    FAN_PROCESSOR,
    ROOT_PROCESSOR;

    /**
     * Creates an instance of the layout processor described by this instance.
     * 
     * @return the layout processor.
     */
    public ILayoutProcessor create() {

        switch (this) {

        case TEST_PROCESSOR:
            return new TestProcessor();

        case FAN_PROCESSOR:
            return new FanProcessor();

        case ROOT_PROCESSOR:
            return new RootProcessor();
        
        default:
            throw new IllegalArgumentException(
                    "No implementation is available for the layout processor " + this.toString());
        }
    }
}
