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

import de.cau.cs.kieler.core.annotations.Annotation;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;

/**
 * Properties used when representing Ptolemy models as KGraphs.
 * 
 * @author cds
 */
public final class PtolemyProperties {
    /**
     * TODO: Document.
     */
    public static final IProperty<List<Annotation>> PT_ANNOTATIONS =
            new Property<List<Annotation>>("ptolemy.annotations", new ArrayList<Annotation>());
    
    
    /**
     * This class is not to be instantiated.
     */
    private PtolemyProperties() {
        
    }
}
