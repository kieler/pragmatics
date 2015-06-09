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
package de.cau.cs.kieler.adaptagrams.layouter;

import org.adaptagrams.ACALayout;
import org.adaptagrams.ACASepFlagsStruct;
import org.adaptagrams.Bools;
import org.adaptagrams.ConstrainedFDLayout;
import org.adaptagrams.Doubles;
import org.adaptagrams.Unsigneds;

import de.cau.cs.kieler.adaptagrams.cgraph.CNode;
import de.cau.cs.kieler.adaptagrams.cgraph.CPort;

/**
 * Wrapping class for {@link org.adaptagrams.adaptagrams adaptagrams}' {@link ACALayout}er.
 * 
 * <h3>Remarks</h3> 
 * {@link #getLayouter()} shouldn't be used for the {@link KACALayouter} as it is
 * reinitialized during ACA's execution.
 * 
 * @author uru
 */
public class KACALayouter extends KConstrainedFDLayouter {

    /** ACA's mode to handle overlap prevention. */
    private int overlapPreventionMode = -1;
    /** For an edge e, specifies into which direction e may be aligned. */
    private ACASepFlagsStruct allowedDirections = null;
    /** Whether an edge is not allowed to be aligned. */
    private Bools ignoreEdges = null;
    /** Whether long edges should be favored to short edges during alignment generation. */
    private boolean favorLongEdges = false;

    /** The layouter instance. */
    private ACALayout layouter;

    /**
     * @param op
     *            value of {@link org.adaptagrams.ACAOverlapPrevention ACAOverlapPrevention}.
     * @return this.
     */
    public KACALayouter withOverlapPrevention(final int op) {
        this.overlapPreventionMode = op;
        return this;
    }

    /**
     * @param theAllowedDirections
     *            a vector of where the i-th entry specifies the allowed directions for the i-th
     *            edge in the {@link de.cau.cs.kieler.adaptagrams.cgraph.CGraph CGraph}'s edges
     *            vector.
     * @return this.
     * 
     * @see #withIgnoreEdges(Bools)
     */
    public KACALayouter withAllowedDirections(final ACASepFlagsStruct theAllowedDirections) {
        this.allowedDirections = theAllowedDirections;
        return this;
    }

    /**
     * @param ignoredEdges
     *            a vector of where the i-th entry specifies whether the i-th edge in the
     *            {@link CGraph}'s edges vector may be aligned.
     * @return this.
     * 
     * @see #withAllowedDirections(ACASepFlagsStruct)
     */
    public KACALayouter withIgnoreEdges(final Bools ignoredEdges) {
        this.ignoreEdges = ignoredEdges;
        return this;
    }

    /**
     * When considering edges to be aligned, favor long over short edges.
     * 
     * @return this.
     */
    public KACALayouter withFavorLongEdges() {
        this.favorLongEdges = true;
        return this;
    }

    /**
     * @param fle
     *            When considering edges to be aligned, whether to favor long over short edges.
     * @return this.
     */
    public KACALayouter withFavorLongEdges(final boolean fle) {
        this.favorLongEdges = fle;
        return this;
    }

    /**
     * Instantiates an internal layouter object and configures it appropriately.
     * 
     * @return this.
     */
    public KConstrainedFDLayouter prepare() {

        if (graph == null) {
            throw new IllegalStateException("Cannot execute layouter without specified cgraph.");
        } else if (layouter != null) {
            throw new IllegalStateException("Layouter has already been prepared.");
        }

        if (convergenceTest != null && individualEdgeLengths) {
            // use the test and specified edge lengths
            layouter =
                    new ACALayout(graph.nodes, graph.edges, graph.constraints, idealEdgeLength,
                            overlapPrevention, graph.getIdealEdgeLengths(), convergenceTest);
        } else if (convergenceTest != null) {
            // use the test but add a default array for individual edge lengths
            Doubles ones = new Doubles();
            for (int i = 0; i < graph.edges.size(); ++i) {
                ones.add(1);
            }
            layouter =
                    new ACALayout(graph.nodes, graph.edges, graph.constraints, idealEdgeLength,
                            overlapPrevention, ones, convergenceTest);
        } else if (individualEdgeLengths) {
            layouter =
                    new ACALayout(graph.nodes, graph.edges, graph.constraints, idealEdgeLength,
                            overlapPrevention, graph.getIdealEdgeLengths());
        } else {
            layouter =
                    new ACALayout(graph.nodes, graph.edges, graph.constraints, idealEdgeLength,
                            overlapPrevention);
        }

        layouter.setClusterHierarchy(graph.rootCluster);

        // node exemptions
        for (CNode n : graph.getChildren()) {
            // Only prevent node<->port overlap removal
            // port<->port should still happen
            for (CPort p : n.getPorts()) {
                Unsigneds group = new Unsigneds();
                group.add(n.cIndex);
                group.add(p.cIndex);
                layouter.addGroupOfNonOverlapExemptRectangles(group);
            }
        }

        // aca specific stuff
        if (allowedDirections != null) {
            layouter.setAllowedDirections(allowedDirections);
        }

        if (ignoreEdges != null) {
            layouter.ignoreEdges(ignoreEdges);
        }

        if (overlapPreventionMode != -1) {
            layouter.overlapPrevention(overlapPreventionMode);
        }

        layouter.favourLongEdges(favorLongEdges);

        // FIXME fd layout changes during execution ...
        // layouter.getFDLayout().setM_doYAxisFirst(removeOverlapsYFirst);

        // FIXME ACA defines its own containers
        // debugging
        //if (xPtrs != null && yPtrs != null) {
            // layouter.setUnsatisfiableConstraintInfo(xPtrs, yPtrs);
        //}

        return this;
    }

    /**
     * Runs the layouter, if {@link #prepare()} has not been called beforehand this is done here.
     * 
     * @return this.
     */
    public KConstrainedFDLayouter run() {

        if (layouter == null) {
            // prepare
            prepare();
        }

        layouter.createAlignments();

        // FIXME see javadoc of the class
        // layouter.freeAssociatedObjects();

        return this;
    }

    /**
     * For debugging purposes only! Do <b>not</b> call run manually on the layouter object.
     * 
     * @return the layouter
     * 
     * @deprecated use {@link #getACALayouter()} instead.
     */
    public ConstrainedFDLayout getLayouter() {
        if (layouter == null || layouter.getFDLayout() == null) {
            throw new IllegalStateException("Cannot access the internal layouter "
                    + "instance before #prepare() or #run() was called.");
        }
        return layouter.getFDLayout();
    }

    /**
     * For debugging purposes only! Do <b>not</b> call run manually on the layouter object.
     * 
     * @return the layouter instance.
     */
    public ACALayout getACALayouter() {
        if (layouter == null) {
            throw new IllegalStateException("Cannot access the internal layouter "
                    + "instance before #prepare() or #run() was called.");
        }
        return layouter;
    }
}
