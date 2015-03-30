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

import de.cau.cs.kieler.core.properties.IProperty;
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
    public static final IProperty<ILabelSizeModifier> LABEL_SIZE_MODIFIER =
            new Property<ILabelSizeModifier>("de.cau.cs.kieler.labels.labelSizeModifier", null);
    
    /**
     * Whether a label's text has been changed by a label size modifier. This property is only
     * necessary in the context of KLighD. KLighD only applies label text changes if this property
     * is set to {@code true}.
     */
    public static final IProperty<Boolean> LABEL_TEXT_CHANGED =
            new Property<Boolean>("de.cau.cs.kieler.labels.labelTextChanged", false);
    
    
    /**
     * Not supposed to be instantiated.
     */
    private LabelLayoutOptions() {
        throw new IllegalStateException("not supposed to be instantiated.");
    }
    
}
