/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klodd.orthogonal.impl.ec;

import java.util.LinkedList;
import java.util.List;

/**
 * Embedding constraint used by the EC embedding planarizer.
 * 
 * @author <a href="mailto:msp@informatik.uni-kiel.de">Miro Sp&ouml;nemann</a>
 */
public class EmbeddingConstraint {

    /**
     * Definition of constraint types.
     */
    public enum Type {
        /** this constraint object contains an input edge. */
        IN_EDGE,
        /** this constraint object contains an output edge. */
        OUT_EDGE,
        /** the order of children is fixed. */
        ORIENTED,
        /** the order of children may be reversed. */
        MIRROR,
        /** the order of children may be arbitrarily permuted. */
        GROUPING
    }

    /** the type of embedding constraint. */
    public Type type;
    /** the children of this embedding constraint. */
    public List<EmbeddingConstraint> children = null;
    /** the parent of this embedding constraint. */
    public EmbeddingConstraint parent;
    /** the contained object. */
    public Object object;

    /**
     * Creates an embedding constraint of given type.
     * 
     * @param thetype the type of embedding constraint
     * @param theparent the parent constraint, or null if there is no parent
     * @param obj the object to be contained
     */
    public EmbeddingConstraint(final Type thetype, final EmbeddingConstraint theparent,
            final Object obj) {
        this.type = thetype;
        this.parent = theparent;
        this.object = obj;
        if (thetype != Type.IN_EDGE && thetype != Type.OUT_EDGE) {
            this.children = new LinkedList<EmbeddingConstraint>();
        }
    }

}
