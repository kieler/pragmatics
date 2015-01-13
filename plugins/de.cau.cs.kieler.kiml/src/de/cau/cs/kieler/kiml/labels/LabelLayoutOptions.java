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
package de.cau.cs.kieler.kiml.labels;

import de.cau.cs.kieler.core.properties.Property;

/**
 * Layout options specifically concerned with label management.
 * 
 * @author cds
 */
public final class LabelLayoutOptions {
    
    /**
     * A label size modifier can be attached to center edge labels. The modifier can be called
     * by layout algorithms to modify labels that are too wide to try and shorten them to a given
     * target width.
     */
    public static final Property<ILabelSizeModifier<?>> LABEL_SIZE_MODIFIER =
            new Property<ILabelSizeModifier<?>>("de.cau.cs.kieler.labelSizeModifier", null);
    
    
    /**
     * Not intended to be instantiated.
     */
    private LabelLayoutOptions() {
    }
}
