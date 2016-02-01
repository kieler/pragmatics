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
package de.cau.cs.kieler.kiml.smart;

/**
 * Interface for smart layout rules.
 *
 * @author msp
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow by msp
 */
public interface ISmartRule {
    
    /**
     * Determine whether this smart rule is suitable for the content of the given parent node.
     * 
     * @param metaLayout a meta layout instance
     * @return a normalized value between 0 and 1 indicating suitability as percentage
     */
    double suitability(MetaLayout metaLayout);
    
    /**
     * Apply the layout options according to this rule.
     * 
     * @param metaLayout a meta layout instance
     */
    void applyMetaLayout(MetaLayout metaLayout);

}
