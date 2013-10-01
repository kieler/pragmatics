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

package de.cau.cs.kieler.ptolemy.klighd.transformation.util;

/**
 * Contains constants used during the transformations. This class is not to be instantiated.
 * 
 * <p><em>Note:</em> FindBugs has problems with the arrays defined herein. However, we don't care
 * too much about that.</p>
 * 
 * @author cds
 */
public final class TransformationConstants {
    
    // PORT NAMES
    
    /** Possible names for input ports. Used to infer port types during the transformation. */
    public static final String[] PORT_NAMES_INPUT = {"in", "input", "incomingPort", "sceneGraphIn"};
    
    /** Possible names for output ports. Used to infer port types during the transformation. */
    public static final String[] PORT_NAMES_OUTPUT = {"out", "output", "outgoingPort", "sceneGraphOut"};
    
    /** Regular expression for the separator character used in port names. */
    public static final String PORT_NAME_SEPARATOR_REGEX = "\\.";
    
    
    // PORT TYPES
    
    /** Name for an annotation that marks a port as being a multiport. */
    public static final String IS_MULTIPORT = "_multiport";
    
    /** Name of an annotation that marks a port as being a ParameterPort instance. */
    public static final String IS_PARAMETER_PORT = "_parameterPort";
    
    /** Name of an annotation that marks a port as being an IOPort instance. */
    public static final String IS_IO_PORT = "_ioPort";
    
    /** Class of a refinement port in modal models. */
    public static final String PORT_CLASS_MODAL_MODEL_PORT = "ptolemy.domains.modal.modal.ModalPort";
    
    /** Class of a refinement port in FSM modal models. */
    public static final String PORT_CLASS_FSM_MODAL_MODEL_PORT = "ptolemy.domains.fsm.modal.ModalPort";
    
    /** Class of a refinement port in modal models. */
    public static final String PORT_CLASS_REFINEMENT_PORT = "ptolemy.domains.modal.modal.RefinementPort";

    /** Class of a refinement port in FSM modal models. */
    public static final String PORT_CLASS_FSM_REFINEMENT_PORT =
            "ptolemy.domains.fsm.modal.RefinementPort";
    
    
    // ENTITY TYPES
    
    /** Class of an entity that houses a finite state machine. */
    public static final String ENTITY_CLASS_FSM = "ptolemy.domains.modal.kernel.FSMActor";
    
    /** Class of an entity that houses a modal model. */
    public static final String ENTITY_CLASS_MODAL_MODEL = "ptolemy.domains.modal.modal.ModalModel";
    
    /** Class of a further entity that houses a modal model. */
    public static final String ENTITY_CLASS_FSM_MODAL_MODEL = "ptolemy.domains.fsm.modal.ModalModel";
    
    /** Class of an entity that is the controller of a modal model. */
    public static final String ENTITY_CLASS_MODEL_CONTROLLER =
            "ptolemy.domains.modal.modal.ModalController";
    
    public static final String ENTITY_CLASS_FSM_MODEL_CONTROLLER =
            "ptolemy.domains.fsm.modal.ModalController";
    
    /** Class of an entity that is a refinement for modal model states. */
    public static final String ENTITY_CLASS_STATE_REFINEMENT = "ptolemy.domains.modal.modal.Refinement";
    
    /** Class of an entity that is a state machine state. */
    public static final String ENTITY_CLASS_STATE = "ptolemy.domains.modal.kernel.State";

    /** Class of an entity that is an FSM state machine state. */
    public static final String ENTITY_CLASS_FSM_STATE = "ptolemy.domains.fsm.kernel.State";
    
    /** Class of an entity that is a Const actor. */
    public static final String ENTITY_CLASS_CONST = "ptolemy.actor.lib.Const";
    
    /** Class of an entity that is a String Const actor. */
    public static final String ENTITY_CLASS_STRING_CONST = "ptolemy.actor.lib.StringConst";
    
    
    // ENTITY NAMES
    
    /** Name of the entity that contains the modal model states. */
    public static final String ENTITY_NAME_MODAL_CONTROLLER = "_Controller";
    
    
    // ANNOTATION TYPES
    
    /** Trype of attributes. */
    public static final String ANNOTATION_TYPE_ATTRIBUTE = "ptolemy.kernel.util.Attribute";
    
    /** Type of annotations that describe a comment. */
    public static final String ANNOTATION_TYPE_TEXT_ATTRIBUTE =
            "ptolemy.vergil.kernel.attributes.TextAttribute";
    
    /** Type of annotations that hold the text of comments. */
    public static final String ANNOTATION_TYPE_STRING_ATTRIBUTE = "ptolemy.kernel.util.StringAttribute";
    
    /** Type of annotations that define parameters of models. */
    public static final String ANNOTATION_TYPE_PARAMETER = "ptolemy.data.expr.Parameter";
    
    
    // ANNOTATION NAMES
    
    /**
     * Name for an annotation describing where a model element originally came from if it was
     * transformed from another model.
     */
    public static final String ANNOTATION_LANGUAGE = "_language";
    
    /**
     * Value of the {@link #ANNOTATION_LANGUAGE} annotation identifying elements transformed from a
     * Ptolemy model.
     */
    public static final String ANNOTATION_LANGUAGE_PTOLEMY = "ptolemy";
    
    /**
     * Name for an annotation describing the original class name of an element imported from a
     * Ptolemy model.
     */
    public static final String ANNOTATION_PTOLEMY_CLASS = "_ptolemyClass";
    
    /**
     * Name of the annotation that specifies the name of the element a comment is explicitly attached to.
     */
    public static final String ANNOTATION_RELATIVE_TO ="relativeTo";
    
    /**
     * Name of the annotation that specifies the type of the element a comment is explicitly attached to.
     */
    public static final String ANNOTATION_RELATIVE_TO_ELEMENT_NAME = "relativeToElementName";
    
    /** Name of the annotation that holds the text of a comment. */
    public static final String ANNOTATION_COMMENT_TEXT = "text";
    
    /** Name of the annotation that holds an element's location. */
    public static final String ANNOTATION_LOCATION = "_location";
    
    /** Name of the annotation that holds the anchor whose position is defined by the location. */
    public static final String ANNOTATION_ANCHOR = "anchor";
    
    /** Name of the annotation that holds the name of a state's refinement. */
    public static final String ANNOTATION_REFINEMENT_NAME = "refinementName";
    
    /** Name of the annotation that holds an annotation text for a state machine relation. */
    public static final String ANNOTATION_ANNOTATION = "annotation";
    
    /** Name of the annotation that holds the guard expression for a state machine relation. */
    public static final String ANNOTATION_GUARD_EXPRESSION = "guardExpression";
    
    /** Name of the annotation that holds the output actions for a state machine relation. */
    public static final String ANNOTATION_OUTPUT_ACTIONS = "outputActions";
    
    /** Name of the annotation that holds the set actions for a state machine relation. */
    public static final String ANNOTATION_SET_ACTIONS = "setActions";
    
    /** Name of the annotation that identifies reset transitions. */
    public static final String ANNOTATION_RESET_TRANSITION = "reset";
    
    /** Name of the annotation that identifies reset transitions. */
    public static final String ANNOTATION_PREEMPTIVE_TRANSITION = "preemptive";
    
    /** Name of the annotation that identifies reset transitions. */
    public static final String ANNOTATION_IMMEDIATE_TRANSITION = "immediate";
    
    /** Name of the annotation that identifies reset transitions. */
    public static final String ANNOTATION_DEFAULT_TRANSITION = "defaultTransition";
    
    /** Name of the annotation that identifies reset transitions. */
    public static final String ANNOTATION_ERROR_TRANSITION = "errorTransition";
    
    /** Name of the annotation that identifies reset transitions. */
    public static final String ANNOTATION_NONDETERMINISTIC_TRANSITION = "nondeterministic";
    
    
    /**
     * This class is not meant to be instantiated.
     */
    private TransformationConstants() {
        // This space intentionally left mostly blank
    }
    
}
