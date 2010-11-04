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
package de.cau.cs.kieler.core.math;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.core.util.IDataObject;

/**
 * A chain of vectors. Can be used to describe polylines or similar constructs.
 *
 * @author msp
 */
public class KVectorChain extends LinkedList<KVector> implements IDataObject {
    
    /** the serial version UID. */
    private static final long serialVersionUID = -7978287459602078559L;

    /**
     * Creates an empty vector chain.
     */
    public KVectorChain() {
        super();
    }
    
    /**
     * Creates a vector chain from a given collection of vectors.
     * 
     * @param collection a collection of vectors
     */
    public KVectorChain(final Collection<KVector> collection) {
        super(collection);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("(");
        Iterator<KVector> iter = iterator();
        while (iter.hasNext()) {
            KVector vector = iter.next();
            builder.append(vector.x + "," + vector.y);
            if (iter.hasNext()) {
                builder.append("; ");
            }
        }
        return builder.append(")").toString();
    }
    
    /**
     * {@inheritDoc}
     */
    public void parse(final String string) throws KielerException {
        StringTokenizer tokenizer = new StringTokenizer(string, ",;()[]{} \t\n");
        clear();
        try {
            while (tokenizer.countTokens() >= 2) {
                double x = Double.parseDouble(tokenizer.nextToken());
                double y = Double.parseDouble(tokenizer.nextToken());
                add(new KVector(x, y));
            }
        } catch (NumberFormatException exception) {
            throw new KielerException(
                    "The given string does not match the expected format for vectors." + exception);
        }
    }

}
