/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.cola.graph;

import java.util.ArrayList;
import java.util.List;

import org.adaptagrams.Rectangle;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.kiml.util.nodespacing.Spacing.Margins;
import de.cau.cs.kieler.klay.cola.properties.ColaProperties;
import de.cau.cs.kieler.klay.cola.properties.InternalColaProperties;

/**
 * @author uru
 */
public class CNode extends CShape {

    private static final long serialVersionUID = -8556727908262767615L;

    // CHECKSTYLEOFF VisibilityModifier
    // CHECKSTYLEOFF Javadoc

    /** The adaptagrams {@link Rectangle} representing this node. */
    public Rectangle rect;

    protected List<CNode> children;
    protected List<CEdge> outgoingEdges;
    protected List<CEdge> incomingEdges;
    protected List<CPort> ports;

    /** Edges to external ports. */
    protected List<CEdge> externalEdges;
    
    protected CGraphElement parent = null;

    private boolean considerPreviousPositions = false; 
    /**
     * .
     */
    public CNode(final CGraph graph) {
        super(graph);

        considerPreviousPositions = graph.getProperty(ColaProperties.CONSIDER_PREVIOUS_POSITION);
        
        // setup the internal lists
        children = new ArrayList<CNode>();
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
        double spacing = graph.getProperty(LayoutOptions.SPACING);
        Margins margin = getMargins();

        // currently the adaptagrams rectangles do not support any border or margin,
        // hence we add it to the top and bottom margin of the rectangle.
        // Horizontal spacing will be considered during separation constraint generation
        margin.top += spacing;
        margin.bottom += spacing;
        
        // x X y Y meaning x width y height
        // assure that the size is at least 1
        if (considerPreviousPositions) {
            rect =
                    new Rectangle(getPos().x - margin.left, Math.max(1,
                            0 + getPos().x + this.getSize().x + margin.right), getPos().y
                            - margin.top, Math.max(1, 0 + getPos().y + this.getSize().y
                            + margin.bottom));
        } else {
            // position them at 0,0
            rect =
                    new Rectangle(0 - margin.left,
                            Math.max(1, 0 + this.getSize().x + margin.right), 0 - margin.top,
                            Math.max(1, 0 + this.getSize().y + margin.bottom));
        }
        cIndex = graph.nodeIndex++;

        // register in graph
        graph.nodes.add(rect);

        System.out.println("Initialized " + this);
    }

    /**
     * @param parent
     *            the parent to set
     */
    public void setParent(final CGraphElement parent) {
        this.parent = parent;
    }

    /**
     * @return the parent
     */
    public CGraphElement getParent() {
        return parent;
    }

    /**
     * @return the outgoing and incoming edges
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
     * @return the ports
     */
    public Iterable<CPort> getPorts(final PortSide side) {
        Iterable<CPort> filtered = Iterables.filter(ports, new Predicate<CPort>() {
            public boolean apply(final CPort p) {
                return p.side == side;
            }
        });
        return filtered;
    }
    
    public KVector getRectPos() {
        return new KVector(rect.getMinX() + getMargins().left, rect.getMinY() + getMargins().top);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("CNode { ");
        Object origin = getProperty(InternalColaProperties.ORIGIN);
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
