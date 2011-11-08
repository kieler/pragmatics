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
package de.cau.cs.kieler.kiml.portlayouter;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.kiml.AbstractLayoutProvider;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;


/**
 * TODO: Document.
 * 
 * @author cds
 */
public class PortLayoutProvider extends AbstractLayoutProvider {
    
    ///////////////////////////////////////////////////////////////////////////////
    // Placement Enumeration
    
    /**
     * Enumeration of all the possible port placements.
     * 
     * @author cds
     */
    private static enum Placement {
        /**
         * A port should be placed normalle. (input ports on the western side, output
         * ports on the eastern side)
         */
        NORMAL,
        
        /**
         * A port should be inverted. (input ports on the eastern side, output ports
         * on the western side)
         */
        INVERTED,
        
        /**
         * A port should be placed on the northern side.
         */
        NORTH,
        
        /**
         * A port should be placed on the southern side.
         */
        SOUTH;
    }
    
    
    ///////////////////////////////////////////////////////////////////////////////
    // Properties
    
    /**
     * Property ID.
     */
    private static final String INVERTED_PORT_SIDE_PROB_ID =
        "de.cau.cs.kieler.kiml.portlayouter.oddPortSideProbability";
    
    /**
     * Default probability for ports to be placed on inverted sides.
     */
    private static final float DEF_INVERTED_PORT_SIDE_PROB = 0.05f;
    
    /**
     * Probability for ports to be placed on inverted sides.
     */
    private static final Property<Float> INVERTED_PORT_SIDE_PROB = new Property<Float>(
            INVERTED_PORT_SIDE_PROB_ID, DEF_INVERTED_PORT_SIDE_PROB);
    
    /**
     * Property ID.
     */
    private static final String NORTH_SOUTH_PORT_SIDE_PROB_ID =
        "de.cau.cs.kieler.kiml.portlayouter.northSouthPortSideProbability";
    
    /**
     * Default probability for ports to be placed on the northern or southern side.
     */
    private static final float DEF_NORTH_SOUTH_PORT_SIDE_PROB = 0.1f;
    
    /**
     * Probability for ports to be placed on the northern or southern side.
     */
    private static final Property<Float> NORTH_SOUTH_PORT_SIDE_PROB = new Property<Float>(
            NORTH_SOUTH_PORT_SIDE_PROB_ID, DEF_NORTH_SOUTH_PORT_SIDE_PROB);
    
    
    ///////////////////////////////////////////////////////////////////////////////
    // Constants
    
    /**
     * Half a factor.
     */
    private static final float HALF_FACTOR = 0.5f;
    
    
    ///////////////////////////////////////////////////////////////////////////////
    // Layout
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void doLayout(final KNode layoutNode, final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Port layout", 1);
        
        // Retrieve the relevant properties
        KShapeLayout graphShapeLayout = layoutNode.getData(KShapeLayout.class);
        boolean debug = graphShapeLayout.getProperty(LayoutOptions.DEBUG_MODE);
        float oddSideProb = graphShapeLayout.getProperty(INVERTED_PORT_SIDE_PROB);
        float northSouthSideProb = graphShapeLayout.getProperty(NORTH_SOUTH_PORT_SIDE_PROB);
        
        // Check for errors
        if (oddSideProb < 0.0f || oddSideProb > 1.0f) {
            throw new IllegalArgumentException(
                    "The odd port side probability must be between 0.0 and 1.0.");
        }

        if (northSouthSideProb < 0.0f || northSouthSideProb > 1.0f) {
            throw new IllegalArgumentException(
                    "The north/south port side probability must be between 0.0 and 1.0.");
        }
        
        if (oddSideProb + northSouthSideProb > 1.0f) {
            throw new IllegalArgumentException(
                    "The sum of the odd port side probability and the "
                    + "north/south port side probability must not be bigger than 1.0.");
        }
        
        // Initialize randomizer
        Random randomizer = null;
        Integer randomSeed = graphShapeLayout.getProperty(LayoutOptions.RANDOM_SEED);
        if (randomSeed != null) {
            int val = randomSeed;
            if (val == 0) {
                randomizer = new Random();
            } else {
                randomizer = new Random(val);
            }
        } else {
            randomizer = new Random();
        }
        
        // Iterate through the nodes, placing the ports accordingly
        for (KNode node : layoutNode.getChildren()) {
            placePorts(node, oddSideProb, northSouthSideProb, randomizer, debug);
        }
        
        progressMonitor.done();
    }
    
    /**
     * Places the ports of the given node.
     * 
     * @param node the node whose ports to place.
     * @param oddSideProb the probability for ports to be placed on an odd side.
     * @param northSouthSideProb the probability for ports to be placed on the northern or
     *                           southern side.
     * @param randomizer the randomizer to use when deciding where to place a port.
     * @param debug {@code true} if debug information should be printed to the console.
     */
    private void placePorts(final KNode node, final float oddSideProb, final float northSouthSideProb,
            final Random randomizer, final boolean debug) {
        
        // Assemble lists of ports to go on the four sides
        List<KPort> northPorts = new LinkedList<KPort>();
        List<KPort> eastPorts = new LinkedList<KPort>();
        List<KPort> southPorts = new LinkedList<KPort>();
        List<KPort> westPorts = new LinkedList<KPort>();
        
        // Iterate through the ports
        for (KPort port : node.getPorts()) {
            boolean inputPort = calculateNetFlow(port) > 0;
            Placement placement = determinePortPlacement(oddSideProb, northSouthSideProb, randomizer);
            
            switch (placement) {
            case INVERTED:
                (inputPort ? eastPorts : westPorts).add(port);
                break;
            
            case NORTH:
                northPorts.add(port);
                break;
            
            case SOUTH:
                southPorts.add(port);
                break;
            
            case NORMAL:
                (inputPort ? westPorts : eastPorts).add(port);
                break;
            }
        }
        
        // Get the node's layout data and apply port positions
        KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
        
        if (!northPorts.isEmpty()) {
            applyNorthSouthPortPositions(
                    northPorts,
                    nodeLayout.getWidth() / (northPorts.size() + 1),
                    nodeLayout.getHeight(),
                    true,
                    debug);
        }
        
        if (!southPorts.isEmpty()) {
            applyNorthSouthPortPositions(
                    southPorts,
                    nodeLayout.getWidth() / (southPorts.size() + 1),
                    nodeLayout.getHeight(),
                    false,
                    debug);
        }
        
        if (!westPorts.isEmpty()) {
            applyWestEastPortPositions(
                    westPorts,
                    nodeLayout.getHeight() / (westPorts.size() + 1),
                    nodeLayout.getWidth(),
                    true,
                    debug);
        }
        
        if (!eastPorts.isEmpty()) {
            applyWestEastPortPositions(
                    eastPorts,
                    nodeLayout.getHeight() / (eastPorts.size() + 1),
                    nodeLayout.getWidth(),
                    false,
                    debug);
        }
    }
    
    
    ///////////////////////////////////////////////////////////////////////////////
    // Utility methods
    
    /**
     * Returns the given port's net flow. The net flow is defined as the number of incoming
     * edges minus the number of outgoing edges.
     * 
     * @param port the port whose net flow to calculate.
     * @return the port's net flow.
     */
    private int calculateNetFlow(final KPort port) {
        int netFlow = 0;
        
        for (KEdge edge : port.getEdges()) {
            if (edge.getTargetPort() == port) {
                netFlow++;
            } else {
                netFlow--;
            }
        }
        
        return netFlow;
    }
    
    /**
     * Calculates a port placement.
     * 
     * @param invertedSideProb the probability for ports to be inverted.
     * @param northSouthSideProb the probability for ports to be placed on the northern or
     *                           southern side.
     * @param randomizer a randomizer to decide between the various options.
     * @return a port placement.
     */
    private Placement determinePortPlacement(final float invertedSideProb,
            final float northSouthSideProb, final Random randomizer) {
        
        float random = randomizer.nextFloat();
        float border = invertedSideProb;
        
        if (random < border) {
            return Placement.INVERTED;
        }
        
        border += HALF_FACTOR * northSouthSideProb;
        
        if (random < border) {
            return Placement.NORTH;
        }
        
        border += HALF_FACTOR * northSouthSideProb;
        
        if (random < border) {
            return Placement.SOUTH;
        }
        
        return Placement.NORMAL;
    }
    
    /**
     * Places the northern or southern ports.
     * 
     * @param ports the ports to place.
     * @param spacing the spacing between the middle points of two ports.
     * @param nodeHeight height of the node whose ports to place.
     * @param north {@code true} if northern ports are to be placed.
     * @param debug {@code true} if debug information should be printed to the console.
     */
    private void applyNorthSouthPortPositions(final List<KPort> ports, final float spacing,
            final float nodeHeight, final boolean north, final boolean debug) {
        
        float xPos = spacing;
        float yPos = north ? 0.0f : nodeHeight;
        float yFactor = north ? -1.0f : 1.0f;
        
        if (debug) {
            System.out.print(ports.get(0).getNode().getLabel().getText());
            if (north) {
                System.out.println(" North port placement (y = " + yPos + ")");
            } else {
                System.out.println(" South port placement (y = " + yPos + ")");
            }
        }
        
        for (KPort port : ports) {
            KShapeLayout portLayout = port.getData(KShapeLayout.class);
            
            portLayout.setXpos(xPos - portLayout.getWidth() * HALF_FACTOR);
            portLayout.setYpos(yPos + yFactor * portLayout.getHeight());
            
            if (debug) {
                System.out.println("   Port at x = " + xPos);
            }
            
            xPos += spacing;
        }
    }
    
    /**
     * Places the western or eastern ports.
     * 
     * @param ports the ports to place.
     * @param spacing the spacing between the middle points of two ports.
     * @param nodeWidth width of the node whose ports to place.
     * @param west {@code true} if western ports are to be placed.
     * @param debug {@code true} if debug information should be printed to the console.
     */
    private void applyWestEastPortPositions(final List<KPort> ports, final float spacing,
            final float nodeWidth, final boolean west, final boolean debug) {
        
        float yPos = spacing;
        float xPos = west ? 0.0f : nodeWidth;
        float xFactor = west ? -1.0f : 1.0f;

        if (debug) {
            System.out.print(ports.get(0).getNode().getLabel().getText());
            if (west) {
                System.out.println(" West port placement (x = " + xPos + ")");
            } else {
                System.out.println(" East port placement (x = " + xPos + ")");
            }
        }
        
        for (KPort port : ports) {
            KShapeLayout portLayout = port.getData(KShapeLayout.class);

            portLayout.setXpos(xPos + xFactor * portLayout.getWidth());
            portLayout.setYpos(yPos - portLayout.getHeight() * HALF_FACTOR);

            if (debug) {
                System.out.println("   Port at y = " + yPos);
            }
            
            yPos += spacing;
        }
    }

}
