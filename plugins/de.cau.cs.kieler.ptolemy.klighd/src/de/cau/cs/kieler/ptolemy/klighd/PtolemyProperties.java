/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.ptolemy.klighd;

import java.util.ArrayList;
import java.util.List;

import org.ptolemy.moml.PropertyType;

import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;

/**
 * Properties used when representing Ptolemy models as KGraphs.
 * 
 * @author cds
 */
public final class PtolemyProperties {
    /**
     * Properties of the original Ptolemy object a KGraph element was created from. Such properties are
     * saved as a property with a list instead of being converted to first-class properties. This is
     * mainly because we don't know in advance which kinds of properties Ptolemy objects can have, and
     * because properties can have properties themselves.
     */
    public static final IProperty<List<PropertyType>> PT_PROPERTIES =
            new Property<List<PropertyType>>("ptolemy.properties", new ArrayList<PropertyType>());
    
    /**
     * The text a comment node should display.
     */
    public static final IProperty<String> COMMENT_TEXT = new Property<String>("comment.text", null);
    
    
    /**
     * This class is not to be instantiated.
     */
    private PtolemyProperties() {
        
    }
}
