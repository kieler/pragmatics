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
package de.cau.cs.kieler.kiml.ogdf;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import net.ogdf.bin.OgdfServerAPI;

import de.cau.cs.kieler.core.KielerRuntimeException;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.IPropertyHolder;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;

/**
 * The upward-planarization layout algorithm from the OGDF library.
 * 
 * @author mri
 */
public class UpwardPlanarizationLayouter extends OgdfLayouter {

    /** default value for spacing. */
    private static final float DEF_SPACING = 16.0f;

    /** 'spacing' property. */
    private static final IProperty<Float> SPACING = new Property<Float>(LayoutOptions.SPACING,
            DEF_SPACING);
    /** the 'layerDistance' option identifier. */
    private static final String LAYER_DISTANCE_ID =
            "de.cau.cs.kieler.kiml.ogdf.option.layerDistance";
    /** 'layerDistance' property. */
    private static final IProperty<Float> LAYER_DISTANCE = new Property<Float>(LAYER_DISTANCE_ID,
            20.0f);

    /** the self-loop router algorithm. */
    private SelfLoopRouter loopRouter = new SelfLoopRouter();

    /**
     * Returns whether the graph defined by the given parent node is connected.
     * 
     * @param node
     *            the parent node of the graph
     * @return whether the graph is connected
     */
    private boolean isConnected(final KNode node) {
        // empty graphs are connected
        if (node.getChildren().size() == 0) {
            return true;
        }
        // mark all nodes connected to a random node
        Queue<KNode> checkNodes = new LinkedList<KNode>();
        Set<KNode> marker = new HashSet<KNode>();
        checkNodes.addAll(node.getChildren());
        Queue<KNode> nodes = new LinkedList<KNode>();
        nodes.add(checkNodes.remove());
        while (!nodes.isEmpty()) {
            KNode currentNode = nodes.remove();
            if (!marker.contains(currentNode)) {
                marker.add(currentNode);
                for (KEdge edge : currentNode.getOutgoingEdges()) {
                    nodes.add(edge.getTarget());
                }
                for (KEdge edge : currentNode.getIncomingEdges()) {
                    nodes.add(edge.getSource());
                }
            }
        }
        // if there is still a not marked node the graph is not connected
        for (KNode currentNode : checkNodes) {
            if (!marker.contains(currentNode)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Constructs a UpwardPlanarizationLayouter.
     */
    public UpwardPlanarizationLayouter() {
        super("UPWARD_PLANARIZATION");
    }

    /**
     * {@inheritDoc}
     */
    protected void prepareLayouter(final KNode layoutNode) {
        // the layouter crashes on not connected graphs
        if (!isConnected(layoutNode)) {
            throw new KielerRuntimeException("Layouter does not support not-connected graphs.");
        }
        KShapeLayout parentLayout = layoutNode.getData(KShapeLayout.class);
        // minSpacing
        float minSpacing = parentLayout.getProperty(SPACING);
        addOption(OgdfServerAPI.OPTION_NODE_DISTANCE, minSpacing);
        // layerDistance
        float layerDistance = parentLayout.getProperty(LAYER_DISTANCE);
        addOption(OgdfServerAPI.OPTION_LAYER_DISTANCE, layerDistance);
        // remove self-loops from the graph
        loopRouter.preProcess(layoutNode);
    }

    /**
     * {@inheritDoc}
     */
    protected void postProcess(final KNode layoutNode) {
        loopRouter.exclude();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initDefaults(final IPropertyHolder defaultsHolder) {
        super.initDefaults(defaultsHolder);
        defaultsHolder.setProperty(LayoutOptions.SPACING, DEF_SPACING);
    }

}
