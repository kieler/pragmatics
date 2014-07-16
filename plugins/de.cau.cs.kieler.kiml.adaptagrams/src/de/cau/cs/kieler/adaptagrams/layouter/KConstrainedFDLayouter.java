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

import org.adaptagrams.ConstrainedFDLayout;
import org.adaptagrams.Doubles;
import org.adaptagrams.TestConvergence;
import org.adaptagrams.UnsatisfiableConstraintInfoPtrs;
import org.adaptagrams.Unsigneds;

import de.cau.cs.kieler.adaptagrams.cgraph.CGraph;
import de.cau.cs.kieler.adaptagrams.cgraph.CNode;
import de.cau.cs.kieler.adaptagrams.cgraph.CPort;

/**
 * Wrapper class for adaptagram's {@link ConstrainedFDLayout} algorithm. It allows convenient
 * configuration and execution by {@link de.cau.cs.kieler.kiml.AbstractLayoutProvider
 * AbstractLayoutProvider}s.
 * 
 * <h3>Limitation</h3> FIXME currently we cannot call
 * {@link ConstrainedFDLayout#freeAssociatedObjects()} as elements might be reused that are deleted
 * by this call. Thus, we have a memory leak.
 * 
 * @see org.adaptagrams.ConstrainedFDLayout
 * 
 * @author uru
 */
public class KConstrainedFDLayouter {

    // SUPPRESS CHECKSTYLE NEXT 70 VisibilityModifier
    /** The graph to be layouted. */
    protected CGraph graph;
    /** Whether overlap removal is performed during layout. */
    protected boolean overlapPrevention = false;
    /** The ideal edge length for all edges or a multiplier for individual edge lengths. */
    protected double idealEdgeLength = 1;
    /** Whether to use individual edge lengths. */
    protected boolean individualEdgeLengths = false;
    /** A custom convergence test. */
    protected TestConvergence convergenceTest = null;
    /** Whether make feasible should be called prior to run. */
    protected boolean makeFeasible = false;
    /** Whether to start with the y dimension when removing overlaps. */
    protected boolean removeOverlapsYFirst = false;
    
    /** The internal layouter object. */
    private ConstrainedFDLayout layouter;

    // for debugging
    /** Constraints that couldn't be satisfied in the x dimension. */
    protected UnsatisfiableConstraintInfoPtrs xPtrs;
    /** Constraints that couldn't be satisfied in the y dimension. */
    protected UnsatisfiableConstraintInfoPtrs yPtrs;

    /**
     * Creates a new wrapper object that can be configured.
     */
    public KConstrainedFDLayouter() {
    }

    /**
     * @param cgraph
     *            the {@link CGraph} to layout.
     * @return this.
     */
    public KConstrainedFDLayouter withCGraph(final CGraph cgraph) {
        if (graph != null) {
            throw new IllegalStateException(
                    "A CGraph has already been specified for this layouter.");
        } else if (cgraph == null) {
            throw new IllegalArgumentException("null is not allowed for cgraph.");
        }
        this.graph = cgraph;
        return this;
    }

    /**
     * Turns on overlap removal during the layout.
     * 
     * @return this.
     */
    public KConstrainedFDLayouter withRemoveOverlaps() {
        this.overlapPrevention = true;
        return this;
    }

    /**
     * Turns overlap removal during the layout on or off.
     * 
     * @param removeOverlaps
     *            Whether to remove overlaps.
     * @return this.
     */
    public KConstrainedFDLayouter withRemoveOverlaps(final boolean removeOverlaps) {
        this.overlapPrevention = removeOverlaps;
        return this;
    }
    
    /**
     * Prior to run {@link ConstrainedFDLayout#makeFeasible()} will be called.
     * 
     * @return this.
     */
    public KConstrainedFDLayouter withMakeFeasible() {
        this.makeFeasible = true;
        return this;
    }

    /**
     * Prior to run {@link ConstrainedFDLayout#makeFeasible()} will be called.
     * 
     * @param makeFeasiblee
     *            Whether to call makeFeasible.
     * @return this.
     */
    public KConstrainedFDLayouter withMakeFeasible(final boolean makeFeasiblee) {
        this.makeFeasible = makeFeasiblee;
        return this;
    }

    /**
     * @param ie
     *            the ideal edge length to use for all edges. If
     *            {@link #withIndividualEdgeLengths()} is used, this value serves as a multiplier
     *            for every value in the individual length array. Default is 1.0.
     * @return this.
     */
    public KConstrainedFDLayouter withIdealEdgeLength(final double ie) {
        this.idealEdgeLength = ie;
        return this;
    }

    /**
     * If activated, the {@link CGraph#getIdealEdgeLengths()} are used as edge lengths. Note that
     * the value specified by {@link #withIdealEdgeLength(double)} is used as a multiplier.
     * 
     * The individual edge lengths are retrieved from the CGraph, {@link CGraph#getIdealEdgeLengths()}.
     * 
     * @return this.
     */
    public KConstrainedFDLayouter withIndividualEdgeLengths() {
        this.individualEdgeLengths = true;
        return this;
    }

    /**
     * Whether to start with the y dimension when removing overlaps.
     * 
     * @return this.
     */
    public KConstrainedFDLayouter withRemoveOverlapsYFirst() {
        this.removeOverlapsYFirst = true;
        return this;
    }

    /**
     * @param yFirst
     *            Whether to start with the y dimension when removing overlaps.
     * @return this.
     */
    public KConstrainedFDLayouter withRemoveOverlapsYFirst(final boolean yFirst) {
        this.removeOverlapsYFirst = yFirst;
        return this;
    }
    
    /**
     * @param test
     *            a subclass of {@link TestConvergence}.
     * @return this.
     */
    public KConstrainedFDLayouter withConvergenceTest(final TestConvergence test) {
        this.convergenceTest = test;
        return this;
    }

    /**
     * Mainly for debugging purposes. Dont use this if you dont know what it means :).
     * 
     * @param xPtrrs
     *            .
     * @param yPtrrs
     *            .
     * @return this.
     */
    public KConstrainedFDLayouter withUnsatisfiableConstraintInfos(
            final UnsatisfiableConstraintInfoPtrs xPtrrs,
            final UnsatisfiableConstraintInfoPtrs yPtrrs) {
        this.xPtrs = xPtrrs;
        this.yPtrs = yPtrrs;
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
                    new ConstrainedFDLayout(graph.nodes, graph.edges, idealEdgeLength,
                            overlapPrevention, graph.getIdealEdgeLengths(), convergenceTest);
        } else if (convergenceTest != null) {
            // use the test but add a default array for individual edge lengths
            Doubles ones = new Doubles();
            for (int i = 0; i < graph.edges.size(); ++i) {
                ones.add(1);
            }
            layouter =
                    new ConstrainedFDLayout(graph.nodes, graph.edges, idealEdgeLength,
                            overlapPrevention, ones, convergenceTest);
        } else if (individualEdgeLengths) {
            layouter =
                    new ConstrainedFDLayout(graph.nodes, graph.edges, idealEdgeLength,
                            overlapPrevention, graph.getIdealEdgeLengths());
        } else {
            layouter =
                    new ConstrainedFDLayout(graph.nodes, graph.edges, idealEdgeLength,
                            overlapPrevention);
        }

        layouter.setClusterHierarchy(graph.rootCluster);
        layouter.setConstraints(graph.constraints);
        
        layouter.setM_doYAxisFirst(removeOverlapsYFirst);
        
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

        // debugging
        if (xPtrs != null && yPtrs != null) {
            layouter.setUnsatisfiableConstraintInfo(xPtrs, yPtrs);
        }
        
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

        if (makeFeasible) {
            layouter.makeFeasible();
        }

        layouter.run();

        // FIXME see javadoc of the class
        // layouter.freeAssociatedObjects();

        return this;
    }

    /**
     * For debugging purposes only! Do <b>not</b> call run manually on the layouter object.
     * 
     * @return the layouter
     */
    public ConstrainedFDLayout getLayouter() {
        if (layouter == null) {
            throw new IllegalStateException("Cannot access the internal layouter "
                    + "instance before #prepare() or #run() was called.");
        }
        return layouter;
    }

}
