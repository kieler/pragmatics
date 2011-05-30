/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.force.model;

import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.klay.force.graph.FGraph;
import de.cau.cs.kieler.klay.force.graph.FParticle;
import de.cau.cs.kieler.klay.force.properties.Properties;

/**
 * A force model after the Eades approach.
 *
 * @author msp
 */
public class EadesModel extends AbstractForceModel {

    private int maxIterations = Properties.DEF_ITERATIONS;
    private double springLength = Properties.DEF_SPACING;
    private double repulsionFactor = Properties.DEF_REPULSION;
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void initialize(final FGraph graph) {
        super.initialize(graph);
        maxIterations = graph.getProperty(Properties.ITERATIONS);
        springLength = graph.getProperty(Properties.SPACING);
        repulsionFactor = graph.getProperty(Properties.REPULSION);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean moreIterations(final int count) {
        return count < maxIterations;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected KVector calcDisplacement(final FParticle forcer, final FParticle forcee) {
        avoidSamePosition(getRandom(), forcer, forcee);

        // compute distance (z in the original algorithm)
        KVector displacement = forcee.getPosition().differenceCreate(forcer.getPosition());
        double d = displacement.getLength();
        
        // calculate attractive or repulsive force, depending of adjacency
        double force;
        int connection = getGraph().getConnection(forcer, forcee);
        if (connection > 0) {
            force = -attractive(d, springLength) * connection;
        } else {
            force = repulsive(d, repulsionFactor) * forcer.getProperty(Properties.PRIORITY);
        }

        // scale distance vector to the amount of repulsive forces
        displacement.scale(force / d);

        return displacement;
    }
    
    /**
     * Compute repulsion force between the forcee and the forcer.
     *
     * @param d the distance between the two particles
     * @param r the factor for repulsive force
     * @return a force exerted on the forcee 
     */
    private static double repulsive(final double d, final double r) {
        return r / (d * d);
    }
    
    /**
     * Compute attraction force between the forcee and the forcer.
     * 
     * @param d the distance between the two particles
     * @param s the spring length
     * @return a force exerted on the forcee
     */
    public static double attractive(final double d, final double s) {
        return Math.log(d / s);
    }

}
