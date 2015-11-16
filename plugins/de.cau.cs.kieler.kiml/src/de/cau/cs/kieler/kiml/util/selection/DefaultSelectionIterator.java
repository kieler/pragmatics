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
package de.cau.cs.kieler.kiml.util.selection;

import java.util.Iterator;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterators;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KPort;

/**
 * The default {@link SelectionIterator} for usage in {@link de.cau.cs.kieler.kiml.util.KimlUtil}.
 * The iterator consists of all edges transitively connected to the initial edge. Optionally the
 * iterator can also include the corresponding ports in the selection.
 * 
 * @author nbw
 */
public class DefaultSelectionIterator extends SelectionIterator {

    private static final long serialVersionUID = 2945508835051993888L;

    private boolean addPorts;

    private boolean towardsHead;

    /**
     * Creates a new iterator which can optionally include ports in its selection and can be
     * configured to either iterate towards the tail or the head of the selected {@link KEdge}.
     * 
     * @param addPorts
     *            flag to determine whether {@link KPorts KPort} should be included in the selection
     * @param towardsHead
     *            flag wheter the iterator should iterate towards the head or the tail of the edge
     */
    public DefaultSelectionIterator(final boolean addPorts, final boolean towardsHead) {
        super();
        this.addPorts = addPorts;
        this.towardsHead = towardsHead;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Iterator<? extends KGraphElement> getChildren(final Object object) {
        if (object instanceof KEdge) {
            final KPort port =
                    towardsHead ? ((KEdge) object).getTargetPort() : ((KEdge) object)
                            .getSourcePort();

            if (port == null || visited.contains(port)) {
                // return an empty iterator if no target/source port is configured
                // or if the target/source port has been visited already, in order
                // to break infinite loops
                return Iterators.<KGraphElement>emptyIterator();
            }

            visited.add(port);

            // for each object (kedge) visited by this iterator check all the edges
            // connected to
            // 'port' and visit those edges satisfying the criterion stated above
            // this criterion btw. prevents from visiting 'object' immediately again,
            // as "port == input.getSourcePort()" implies "object != input"
            Iterator<KEdge> resultEdges =
                    Iterators.filter(port.getEdges().iterator(), new Predicate<KEdge>() {

                        public boolean apply(final KEdge input) {
                            return towardsHead ? port == input.getSourcePort() : port == input
                                    .getTargetPort();
                        }
                    });

            // If the port should be added to the selection, add it to the result set
            if (addPorts) {
                Iterator<KGraphElement> portIterator =
                        Iterators.singletonIterator((KGraphElement) port);
                return Iterators.concat(portIterator, resultEdges);
            } else {
                return resultEdges;
            }
        } else {
            return Iterators.<KGraphElement>emptyIterator();
        }
    }
}
