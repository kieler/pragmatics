package de.cau.cs.kieler.klay.layered.intermediate.greedyswitch;

import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;

abstract class InBetweenLayerEdgeAllCrossingsCounter {

    protected int[] portPos;
    int portCount;

    /**
     * Calculate the number of crossings between the two given layers. Taken from
     *
     * @param leftLayer
     *            the left layer
     * @param rightLayer
     *            the right layer
     * @return the number of edge crossings
     */
    public abstract int countCrossings(LNode[] leftLayer, LNode[] rightLayer);

    protected void initialize(final LNode[][] graph) {
        portCount = 0;
        for (LNode[] layer : graph) {
            for (LNode node : layer) {
                for (LPort port : node.getPorts()) {
                    port.id = portCount++;
                }
            }
        }
    
        // Initialize the port positions and ranks arrays
        portPos = new int[portCount];
    }

}
