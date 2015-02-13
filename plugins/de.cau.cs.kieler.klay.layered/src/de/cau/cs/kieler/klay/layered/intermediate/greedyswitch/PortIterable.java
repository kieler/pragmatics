/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2015 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.intermediate.greedyswitch;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.google.common.collect.Iterators;

import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;

/**
 * @author alan
 *
 */
class PortIterable implements Iterable<LPort> {
    private final PortSide side;
    private final LNode node;

    public PortIterable(final LNode node, final PortSide side) {
        this.node = node;
        this.side = side;
    }

    public Iterator<LPort> iterator() {
        if (side == PortSide.WEST) {
            final List<LPort> ports = node.getPorts();
            Iterator<LPort> iterable = new Iterator<LPort>() {
                private final ListIterator<LPort> listIterator = ports.listIterator(ports.size());

                public boolean hasNext() {
                    return listIterator.hasPrevious();
                }

                public LPort next() {
                    return listIterator.previous();
                }
            };
            return Iterators.filter(iterable, LPort.WEST_PREDICATE);
        } else {
            Iterator<LPort> iterable = node.getPorts().iterator();
            return Iterators.filter(iterable, LPort.EAST_PREDICATE);
        }
    }
}
