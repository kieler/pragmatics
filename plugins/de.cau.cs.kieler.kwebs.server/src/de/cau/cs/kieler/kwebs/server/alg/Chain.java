/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.kwebs.server.alg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 
 * @param <DataType>
 * 
 * @author swe
 */
public class Chain<DataType> {

    //////////
    
    /** */
    private final List<Segment<DataType>> segments
        = new ArrayList<Segment<DataType>>();

    //////////
    
    /**
     * 
     * @param segment
     */
    public void addSegment(final Segment<DataType> segment) {
        if (!segments.contains(segment)) {
            segments.add(segment);
        }
    }
    
    /**
     * 
     * @param segment
     */
    public void removeSegment(final Segment<DataType> segment) {
        if (segments.contains(segment)) {
            segments.remove(segment);
        }
    }
    
    /**
     * 
     * @param comparator
     */
    public void sortSegments(final Comparator<Segment<DataType>> comparator) {
        Collections.sort(segments, comparator);
    }
    
    /**
     * 
     * @param data
     * @return
     */
    public boolean apply(final DataType data) {
        final List<Message> messages 
            = new ArrayList<Message>();
        for (Segment<DataType> segment : segments) {
            if (!segment.apply(data, messages)) {
                return false;
            }
        }
        return true;
    }
    
}
