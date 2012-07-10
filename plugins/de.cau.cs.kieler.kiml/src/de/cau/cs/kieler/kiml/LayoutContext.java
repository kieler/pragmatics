/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml;

import java.util.Set;

import org.eclipse.emf.ecore.EObject;

import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.MapPropertyHolder;
import de.cau.cs.kieler.core.properties.Property;

/**
 * Context information for configuration of layout options.
 *
 * @author msp
 * @kieler.rating 2012-07-10 proposed yellow msp
 */
public class LayoutContext extends MapPropertyHolder {
    
    /** the serial version UID. */
    private static final long serialVersionUID = -7544617305602906672L;

    /** the graph element in the current context. */
    public static final IProperty<KGraphElement> GRAPH_ELEM = new Property<KGraphElement>(
            "context.graphElement");
    
    /** the main domain model element in the current context. */
    public static final IProperty<EObject> DOMAIN_MODEL = new Property<EObject>(
            "context.domainModelElement");
    
    /** the domain model element of the container of the current graph element. */
    public static final IProperty<EObject> CONTAINER_DOMAIN_MODEL = new Property<EObject>(
            "context.containerDomainModelElement");
    
    /** the main diagram part in the current context. */
    public static final IProperty<Object> DIAGRAM_PART = new Property<Object>(
            "context.diagramPart");
    
    /** the diagram part for the container of the current graph element. */
    public static final IProperty<Object> CONTAINER_DIAGRAM_PART = new Property<Object>(
            "context.containerDiagramPart");
    
    /** the types of targets for layout options. */
    public static final IProperty<Set<LayoutOptionData.Target>> OPT_TARGETS
            = new Property<Set<LayoutOptionData.Target>>("context.optionTargets");

    /**
     * Create an empty layout context.
     */
    public LayoutContext() {
    }
    
    /**
     * Copy the content of the given layout context into a new one.
     * 
     * @param other another layout context
     */
    public LayoutContext(final LayoutContext other) {
        this.copyProperties(other);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return super.getAllProperties().toString();
    }
    
}
