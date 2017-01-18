/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.adaptagrams.cgraph;

import java.util.ArrayList;
import java.util.List;

import org.adaptagrams.Rectangle;
import org.eclipse.elk.core.math.ElkMargin;
import org.eclipse.elk.core.options.PortSide;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

import de.cau.cs.kieler.adaptagrams.properties.CGraphProperties;
import de.cau.cs.kieler.kiml.adaptagrams.properties.ColaOptions;

/**
 * <h3>Usage:</h3>
 * Upon {@link #init()} the node is automatically added to the graph's nodes list.
 * 
 * @author uru
 */
public class CNode extends CShape {

    private static final long serialVersionUID = -8556727908262767615L;

    // CHECKSTYLEOFF VisibilityModifier
    // CHECKSTYLEOFF Javadoc

    protected List<CEdge> outgoingEdges;
    protected List<CEdge> incomingEdges;
    protected List<CPort> ports;

    /** Edges to external ports. */
    protected List<CEdge> externalEdges;
    
    /** Artificial margin added to guarantee spacing around the node. */
    private ElkMargin artificialMargin = new ElkMargin();
    
    /**
     * .
     */
    public CNode(final CGraph graph) {
        super(graph);

        // setup the internal lists
        outgoingEdges = new ArrayList<CEdge>();
        incomingEdges = new ArrayList<CEdge>();
        externalEdges = new ArrayList<CEdge>();
        ports = new ArrayList<CPort>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init() {
        // get spacing and margins
        double spacing = graph.getProperty(ColaOptions.SPACING_NODE_NODE);
        ElkMargin margin = getMargins();

        if (graph.getProperty(CGraphProperties.INCLUDE_SPACING_IN_MARGIN)) {
            // currently the adaptagrams rectangles do not support any border or margin,
            // hence we add it to the rectangle.
            artificialMargin.set(spacing / 2f, spacing / 2f, spacing / 2f, spacing / 2f);
            margin.left += artificialMargin.left;
            margin.top += artificialMargin.top;
            margin.right += artificialMargin.right;
            margin.bottom += artificialMargin.bottom;
        }
        
        boolean considerPreviousPositions =
                graph.getProperty(ColaOptions.CONSIDER_PREVIOUS_POSITIONS);
        // x X y Y meaning x width y height
        // assure that the size is at least 1
        if (considerPreviousPositions) {
            rect =
                    new Rectangle(getPos().x - margin.left, 
                            Math.max(1, getPos().x + this.getSize().x + margin.right), 
                            getPos().y - margin.top, 
                            Math.max(1, getPos().y + this.getSize().y + margin.bottom));
        } else {
            // position them at 0, 0
            rect =
                    new Rectangle(0 - margin.left,
                            Math.max(1, 0 + this.getSize().x + margin.right), 0 - margin.top,
                            Math.max(1, 0 + this.getSize().y + margin.bottom));
        }
        cIndex = graph.nodeIndex++;

        // register in graph
        graph.nodes.add(rect);
    }

    /**
     * @return a view of the outgoing and incoming edges
     */
    public Iterable<CEdge> getConnectedEdges() {
        return Iterables.concat(outgoingEdges, incomingEdges);
    }

    /**
     * @return the outgoingEdges
     */
    public List<CEdge> getOutgoingEdges() {
        return outgoingEdges;
    }

    /**
     * @return the incomingEdges
     */
    public List<CEdge> getIncomingEdges() {
        return incomingEdges;
    }
    
    /**
     * @return the externalEdges
     */
    public List<CEdge> getExternalEdges() {
        return externalEdges;
    }

    /**
     * @return the ports
     */
    public List<CPort> getPorts() {
        return ports;
    }

    /**
     * @return a view of the ports on the specified side
     */
    public Iterable<CPort> getPorts(final PortSide side) {
        Iterable<CPort> filtered = Iterables.filter(ports, new Predicate<CPort>() {
            public boolean apply(final CPort p) {
                return p.side == side;
            }
        });
        return filtered;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("CNode { ");
        Object origin = getProperty(CGraphProperties.ORIGIN);
        if (origin != null) {
            sb.append(origin).append(" ");
        }
        if (rect != null) {
            sb.append("cIndex=").append(cIndex).append(" ");
            sb.append(rect).append(" ");
        }

        sb.append(" }");
        return sb.toString();
    }
}
