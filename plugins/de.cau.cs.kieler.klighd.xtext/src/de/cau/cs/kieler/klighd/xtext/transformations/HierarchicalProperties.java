/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2017 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.xtext.transformations;

import org.eclipse.elk.graph.properties.IProperty;
import org.eclipse.elk.graph.properties.Property;

import de.cau.cs.kieler.klighd.kgraph.KNode;

/**
 * @author ybl
 *
 */
public class HierarchicalProperties {

    public static final IProperty<KNode> HIERARCHICAL_PARENT =
            new Property<KNode>("de.cau.cs.kieler.hierarchicalParent", null);

}
