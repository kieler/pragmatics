/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klodd.hierarchical.impl;

import java.util.ListIterator;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.slimgraph.KSlimGraph;
import de.cau.cs.kieler.core.slimgraph.KSlimNode;
import de.cau.cs.kieler.klodd.hierarchical.modules.ILayerAssigner;
import de.cau.cs.kieler.klodd.hierarchical.structures.Layer;
import de.cau.cs.kieler.klodd.hierarchical.structures.LayerElement;
import de.cau.cs.kieler.klodd.hierarchical.structures.LayeredGraph;

/**
 * Layer assigner that balances the output of a basic layer assigner.
 * 
 * @author <a href="mailto:msp@informatik.uni-kiel.de">Miro Sp&ouml;nemann</a>
 */
public class BalancingLayerAssigner extends AbstractAlgorithm implements ILayerAssigner {

    /** the basic layer assigner instance. */
    private ILayerAssigner basicLayerAssigner;

    /**
     * Creates a balancing layer assigner using a basic layer assigner.
     * 
     * @param thebasicLayerAssigner basic layer assigner
     */
    public BalancingLayerAssigner(final ILayerAssigner thebasicLayerAssigner) {
        this.basicLayerAssigner = thebasicLayerAssigner;
    }

    /**
     * {@inheritDoc}
     */
    public LayeredGraph assignLayers(final KSlimGraph graph, final KNode parentNode,
            final boolean balanceOverSize) {
        getMonitor().begin("Balancing layer assignment", 1);
        basicLayerAssigner.reset(getMonitor().subTask(1));
        LayeredGraph layeredGraph = basicLayerAssigner.assignLayers(graph, parentNode, balanceOverSize);

        // balance layer assignment of each element in the layering
        if (layeredGraph.getLayers().size() >= 3) {
            ListIterator<Layer> layerIter = layeredGraph.getLayers().listIterator(2);
            while (layerIter.hasNext()) {
                Layer layer = layerIter.next();
                if (layer.height > 0) {
                    ListIterator<LayerElement> elemIter = layer.getElements().listIterator();
                    while (elemIter.hasNext()) {
                        balanceElement(layeredGraph, elemIter, balanceOverSize);
                    }
                }
            }
        }

        getMonitor().done();
        return layeredGraph;
    }

    /**
     * Balances the given element by finding a possibly better layer above the
     * element.
     * 
     * @param layeredGraph layered graph
     * @param elemIter iterator whose next element shall be balanced
     * @param balanceOverSize indicates whether node balancing has priority over
     *            diagram size
     */
    private void balanceElement(final LayeredGraph layeredGraph,
            final ListIterator<LayerElement> elemIter, final boolean balanceOverSize) {
        LayerElement element = elemIter.next();
        Layer currentLayer = element.getLayer();
        KSlimNode kNode = element.getKNode();
        int incoming = 0, outgoing = 0, minShiftRank = 0;
        for (KSlimNode.IncEntry edgeEntry : kNode.getIncidence()) {
            if (edgeEntry.getType() == KSlimNode.IncEntry.Type.OUT) {
                outgoing++;
            } else {
                incoming++;
                int shiftRank = layeredGraph.getLayerElement(edgeEntry.endpoint().getObject()).getLayer().rank + 1;
                minShiftRank = Math.max(minShiftRank, shiftRank);
            }
        }
        if (minShiftRank > 0 && incoming >= outgoing) {
            int layerOffset = layeredGraph.getLayers().get(0).rank;
            if (balanceOverSize) {
                if (minShiftRank < currentLayer.rank) {
                    elemIter.remove();
                    element.setLayer(layeredGraph.getLayers().get(minShiftRank - layerOffset));
                }
            } else {
                ListIterator<Layer> layerIter = layeredGraph.getLayers().listIterator(
                        minShiftRank - layerOffset);
                int currentSize = currentLayer.getElements().size();
                while (layerIter.nextIndex() < currentLayer.rank - layerOffset) {
                    Layer layer = layerIter.next();
                    if (layer.getElements().size() <= currentSize) {
                        // move the current element to the new layer
                        elemIter.remove();
                        element.setLayer(layer);
                        break;
                    }
                }
            }
        }
    }

}
