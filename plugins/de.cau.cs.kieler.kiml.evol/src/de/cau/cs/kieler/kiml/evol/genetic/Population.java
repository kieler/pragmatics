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
package de.cau.cs.kieler.kiml.evol.genetic;

import java.util.ArrayList;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.MapPropertyHolder;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.kiml.LayoutContext;
import de.cau.cs.kieler.kiml.config.ILayoutConfig;

/**
 * A population has a list of individuals (genomes).
 *
 * @author msp
 */
public class Population extends ArrayList<Genome> {
    
    /** property for the graph used for evaluation of individuals. */
    public static final IProperty<KNode> EVALUATION_GRAPH = new Property<KNode>(
            "evol.evaluationGraph");
    /** property for the layout configuration used for obtaining default values. */
    public static final IProperty<ILayoutConfig> DEFAULT_CONFIG = new Property<ILayoutConfig>(
            "evol.defaultConfig");
    /** property for the layout context used for obtaining default values. */
    public static final IProperty<LayoutContext> DEFAULT_CONTEXT = new Property<LayoutContext>(
            "evol.defaultContext");

    /** The serial version UID. */
    private static final long serialVersionUID = -8591635951271498010L;
    
    /** the property holder map. */
    private final MapPropertyHolder propertyHolder = new MapPropertyHolder();

    /**
     * Constructs an empty population.
     * 
     * @param size the initial size
     */
    public Population(final int size) {
        super(size);
    }
    
    /**
     * Sets a property value using the nested property holder.
     * 
     * @param property the property to set
     * @param value the new value
     */
    public void setProperty(final IProperty<?> property, final Object value) {
        propertyHolder.setProperty(property, value);
    }
    
    /**
     * Retrieves a property value using the nested property holder.
     * 
     * @param <T> type of property
     * @param property the property to get
     * @return the current value, or the default value if the property is not set
     */
    public <T> T getProperty(final IProperty<T> property) {
        return propertyHolder.getProperty(property);
    }
    
}
