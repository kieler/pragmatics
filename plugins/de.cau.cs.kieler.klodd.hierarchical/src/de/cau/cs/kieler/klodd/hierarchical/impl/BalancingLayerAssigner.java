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
import de.cau.cs.kieler.kiml.layout.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.layout.options.LayoutDirection;
import de.cau.cs.kieler.kiml.layout.options.LayoutOptions;
import de.cau.cs.kieler.kiml.layout.util.KimlLayoutUtil;
import de.cau.cs.kieler.klodd.hierarchical.modules.ILayerAssigner;
import de.cau.cs.kieler.klodd.hierarchical.structures.Layer;
import de.cau.cs.kieler.klodd.hierarchical.structures.LayerElement;
import de.cau.cs.kieler.klodd.hierarchical.structures.LayeredGraph;

/**
 * Layer assigner that balances the output of a basic layer assigner.
 * 
 * @kieler.rating 2009-12-11 proposed yellow msp
 * @author msp
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

    /** minimal number of layers for balancing of the layering. */
    private static final int MIN_LAYERS = 3;
    
    /**
     * {@inheritDoc}
     */
    public LayeredGraph assignLayers(final KSlimGraph graph, final KNode parentNode,
            final float objSpacing, final boolean balanceOverSize) {
        getMonitor().begin("Balancing layer assignment", 1);
        basicLayerAssigner.reset(getMonitor().subTask(1));
        LayeredGraph layeredGraph = basicLayerAssigner.assignLayers(graph, parentNode,
                objSpacing, balanceOverSize);
        KShapeLayout parentLayout = KimlLayoutUtil.getShapeLayout(parentNode);
        boolean interactive = LayoutOptions.getBoolean(parentLayout, LayoutOptions.INTERACTIVE);
        LayoutDirection layoutDirection = LayoutOptions.getEnum(parentLayout, LayoutDirection.class);
        boolean vertical = layoutDirection == LayoutDirection.DOWN;

        // balance layer assignment of each element in the layering
        if (layeredGraph.getLayers().size() >= MIN_LAYERS) {
            ListIterator<Layer> layerIter = layeredGraph.getLayers().listIterator(2);
            while (layerIter.hasNext()) {
                Layer layer = layerIter.next();
                if (layer.getHeight() > 0) {
                    ListIterator<LayerElement> elemIter = layer.getElements().listIterator();
                    while (elemIter.hasNext()) {
                        balanceElement(layeredGraph, elemIter, objSpacing,
                                balanceOverSize, interactive, vertical);
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
     * @param objSpacing minimal distance between objects
     * @param balanceOverSize indicates whether node balancing has priority over
     *            diagram size
     * @param interactive if true, initial positioning is considered
     * @param vertical if true, vertical layout is performed
     */
    private void balanceElement(final LayeredGraph layeredGraph,
            final ListIterator<LayerElement> elemIter, final float objSpacing,
            final boolean balanceOverSize, final boolean interactive, final boolean vertical) {
        LayerElement element = elemIter.next();
        Layer currentLayer = element.getLayer();
        KSlimNode kNode = element.getKNode();
        int incoming = 0, outgoing = 0, minShiftRank = 0;
        for (KSlimNode.IncEntry edgeEntry : kNode.getIncidence()) {
            if (edgeEntry.getType() == KSlimNode.IncEntry.Type.OUT) {
                outgoing++;
            } else {
                incoming++;
                int shiftRank = layeredGraph.getLayerElement(edgeEntry.endpoint()
                        .getObject()).getLayer().getRank() + 1;
                minShiftRank = Math.max(minShiftRank, shiftRank);
            }
        }
        int layerOffset = layeredGraph.getLayers().get(0).getRank();
        if (interactive) {
            if (minShiftRank - layerOffset < 0) {
                minShiftRank = 1;
            }
            ListIterator<Layer> layerIter = layeredGraph.getLayers().listIterator(
                    minShiftRank - layerOffset);
            Layer layer = layerIter.next();
            while (layerIter.nextIndex() <= currentLayer.getRank() - layerOffset) {
                Layer nextLayer = layerIter.next();
                boolean fits = true;
                for (LayerElement nextElement : nextLayer.getElements()) {
                    KSlimNode nextKnode = nextElement.getKNode();
                    if (!nextKnode.equals(kNode)
                            && (vertical && kNode.getYpos() > nextKnode.getYpos() - objSpacing / 2
                            || !vertical && kNode.getXpos() > nextKnode.getXpos() - objSpacing / 2)) {
                        fits = false;
                        break;
                    }
                }
                if (fits) {
                    // move the current element to the new layer
                    elemIter.remove();
                    element.setLayer(layer);
                    break;
                }
                layer = nextLayer;
            }            
        } else if (minShiftRank > 0 && incoming >= outgoing) {
            if (balanceOverSize) {
                if (minShiftRank < currentLayer.getRank()) {
                    elemIter.remove();
                    element.setLayer(layeredGraph.getLayers().get(minShiftRank - layerOffset));
                }
            } else {
                ListIterator<Layer> layerIter = layeredGraph.getLayers().listIterator(
                        minShiftRank - layerOffset);
                int currentSize = currentLayer.getElements().size();
                while (layerIter.nextIndex() < currentLayer.getRank() - layerOffset) {
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
