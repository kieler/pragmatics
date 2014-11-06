package de.cau.cs.kieler.klay.layered.intermediate;

import java.util.List;

import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.p3order.NodeGroup;
import de.cau.cs.kieler.klay.layered.properties.InternalProperties;

public class GreedySwitchCounterProcessor extends AbstractGreedySwitchProcessor {

    @Override
    protected void switchInLayer(boolean forward, int layerIndex) {
        int freeLayerIndex = forward ? layerIndex + 1 : layerIndex - 1;
        NodeGroup[] fixedLayer = curSweep[layerIndex];
        NodeGroup[] freeLayer = curSweep[freeLayerIndex];
        int currentAmountOfCrossings =
                forward ? countCrossings(fixedLayer, freeLayer) : countCrossings(fixedLayer,
                        freeLayer);
        if (currentAmountOfCrossings == 0) {
            return;
        }

        boolean crossingNumberHasImproved = true;

        while (crossingNumberHasImproved) {
            crossingNumberHasImproved = false;
            for (int i = 0; i < freeLayer.length - 1; i++) {
                LNode currentNode = freeLayer[i].getNode();
                LNode nextNode = freeLayer[i + 1].getNode();
                List<LNode> constraints =
                        currentNode.getProperty(InternalProperties.IN_LAYER_SUCCESSOR_CONSTRAINTS);
                if (constraints == null || constraints.size() == 0
                        || !constraints.get(0).equals(nextNode)) {
                    // TODOAlan watch for all constraints!
                    exchangeNodes(i, i + 1, freeLayer);

                    int newAmountOfCrossings =
                            forward ? countCrossings(fixedLayer, freeLayer) : countCrossings(
                                    fixedLayer, freeLayer);

                    if (newAmountOfCrossings < currentAmountOfCrossings) {
                        crossingNumberHasImproved = true;
                        currentAmountOfCrossings = newAmountOfCrossings;
                    } else {
                        exchangeNodes(i + 1, i, freeLayer);
                    }
                }
            }
        }
    }

}
