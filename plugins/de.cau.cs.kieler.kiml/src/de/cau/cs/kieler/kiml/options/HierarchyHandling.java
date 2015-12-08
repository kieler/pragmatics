/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2015 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.kiml.options;

/**
 * Options for setting how children of nodes should be handled in the current layout run. There are
 * three options for this:
 * <ul>
 * <li>INHERIT: The current node should implement the same behaviour as the parent node. If the root
 * node is evaluated and it is set to inherit (or not set at all) the property is set to
 * {@link #SEPARATE_CHILDREN}.</li>
 * <li>INCLUDE_CHILDREN: The children of the current node should be included in the current layout
 * run. This enables edges to be routed across the boundary of hierarchy layers.</li>
 * <li>SEPARATE_CHILDREN: The children of the current node are layouted independently from their
 * parent node. The resulting layout information is then used to layout the parent node.</li>
 * </ul>
 * <p>
 * The inclusion of further children can be disabled by setting the option back to SEPARATE_CHILDREN
 * at lower levels. For this option to have any effect, the option needs to be set to at least two
 * successive levels of hierarchy.
 * </p>
 * <p>
 * If the layout algorithm doesn't support hierarchical layout, this property is ignored and the
 * layout is calculated separately for each child hierarchy.
 * </p>
 * <p>
 * <i>Note:</i> Layout algorithms only need to differentiate between INCLUDE_CHILDREN and
 * SEPARATE_CHILDREN as the inheritance is evaluated by KIML.
 * </p>
 *
 * @author nbw
 */
public enum HierarchyHandling {
    /**
     * Inherit the behaviour from the parent node. If set on the root node, SEPARATE_CHILDREN is
     * assumed.
     */
    INHERIT,

    /**
     * Include the children in the parent layout run.
     */
    INCLUDE_CHILDREN,

    /**
     * Perform a separate layout run for child nodes and layout the parent afterwards.
     */
    SEPARATE_CHILDREN
}
