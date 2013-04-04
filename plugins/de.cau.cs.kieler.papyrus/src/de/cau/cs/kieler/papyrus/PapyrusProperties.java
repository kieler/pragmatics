/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.papyrus;

import java.util.List;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;

/**
 * Properties that are necessary for the sequence diagram layout.
 * 
 * @author grh
 * @kieler.design proposed grh
 * @kieler.rating proposed yellow grh
 * 
 */
public final class PapyrusProperties {
    private PapyrusProperties() {
        // Hide constructor
    }
    /** The type of a message. */
    public static final IProperty<String> MESSAGE_TYPE = new Property<String>(
            "de.cau.cs.kieler.papyrus.messageType", "");

    /** The type of a node. */
    public static final IProperty<String> NODE_TYPE = new Property<String>(
            "de.cau.cs.kieler.papyrus.nodeType", "");

    /** The list of execution specifications of a lifeline. */
    public static final IProperty<List<SequenceExecution>> EXECUTIONS = 
            new Property<List<SequenceExecution>>("de.cau.cs.kieler.papyrus.executionSpecifications");

    /** A single execution specification. */
    public static final IProperty<SequenceExecution> EXECUTION = new Property<SequenceExecution>(
            "de.cau.cs.kieler.papyrus.executionSpecification");

    /** The list of areas (interactions, combined fragments, etc.). */
    public static final IProperty<List<SequenceArea>> AREAS = new Property<List<SequenceArea>>(
            "de.cau.cs.kieler.papyrus.area");

    /** The list of objects, a comment is attached to. */
    public static final IProperty<List<Object>> ATTACHED_TO = new Property<List<Object>>(
            "de.cau.cs.kieler.papyrus.attachedTo");

    /** The destruction event of a lifeline. */
    public static final IProperty<KNode> DESTRUCTION = new Property<KNode>(
            "de.cau.cs.kieler.papyrus.destruction");

    /** Property of a comment that indicates to what kind of element it is attached. */
    public static final IProperty<String> ATTACHED_ELEMENT = new Property<String>(
            "de.cau.cs.kieler.papyrus.attachedElement");
}
