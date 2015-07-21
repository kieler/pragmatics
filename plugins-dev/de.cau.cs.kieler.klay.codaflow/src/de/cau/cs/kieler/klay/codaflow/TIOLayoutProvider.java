/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.codaflow;

import java.util.Map.Entry;

import org.adaptagrams.AvoidTopologyAddon;
import org.adaptagrams.FixedRelativeConstraint;
import org.adaptagrams.PageBoundaryConstraints;
import org.adaptagrams.Rectangle;
import org.adaptagrams.ShapeRef;
import org.adaptagrams.Unsigneds;
import org.adaptagrams.VariableIDMap;

import de.cau.cs.kieler.adaptagrams.avoid.LibavoidGraph;
import de.cau.cs.kieler.adaptagrams.cgraph.CGraph;
import de.cau.cs.kieler.adaptagrams.cgraph.CNode;
import de.cau.cs.kieler.adaptagrams.properties.CGraphProperties;
import de.cau.cs.kieler.adaptagrams.properties.AvoidProperties;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.kiml.AbstractLayoutProvider;
import de.cau.cs.kieler.kiml.klayoutdata.KInsets;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutData;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.util.adapters.KGraphAdapters;
import de.cau.cs.kieler.kiml.util.adapters.KGraphAdapters.KGraphAdapter;
import de.cau.cs.kieler.kiml.util.nodespacing.KimlNodeDimensionCalculation;
import de.cau.cs.kieler.kiml.util.nodespacing.Spacing.Margins;
import de.cau.cs.kieler.klay.codaflow.graphimport.KGraphImporter;
import de.cau.cs.kieler.klay.codaflow.properties.CodaflowProperties;

/**
 * @author uru
 * 
 */
public class TIOLayoutProvider extends AbstractLayoutProvider {

    private CGraph graph;
    private double moveLimit;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void doLayout(final KNode parentNode, final IKielerProgressMonitor progressMonitor) {

        progressMonitor.begin("Improve Layout Topology Preserving", 1);

        KLayoutData rootLayout = parentNode.getData(KLayoutData.class);

        // always suppress dummy port generation here
        rootLayout.setProperty(CodaflowProperties.PORT_DUMMIES, false);

        moveLimit = rootLayout.getProperty(CodaflowProperties.MOVE_LIMIT).doubleValue();

        // calculate margins of the nodes
        KGraphAdapter adapter = KGraphAdapters.adapt(parentNode);
        KimlNodeDimensionCalculation.sortPortLists(adapter);
        KimlNodeDimensionCalculation.calculateLabelAndNodeSizes(adapter);
        KimlNodeDimensionCalculation.getNodeMarginCalculator(adapter).process();

        graph = new KGraphImporter().importGraph(parentNode);

        LibavoidGraph libGraph = new LibavoidGraph(parentNode) {
            /**
             * {@inheritDoc}
             */
            @Override
            protected void applyLayout(final KNode root) {

                KVector offset = new KVector();

                // move the nodes to their new positions
                for (Entry<Integer, ShapeRef> entry : this.idShapeRefMap.entrySet()) {
                    
                    if (entry.getKey() < LibavoidGraph.NODE_ID_START) {
                        continue;
                    }

                    KNode node = nodeIdMap.get(entry.getKey());
                    Margins margins =
                            node.getData(KShapeLayout.class).getProperty(LayoutOptions.MARGINS);
                    ShapeRef sr = entry.getValue();

                    KShapeLayout layout = node.getData(KShapeLayout.class);
                    // FIXME why add half the port size here?
                    layout.setXpos((float) (sr.routingBox().getMin().getX() 
                            + offset.x + margins.left + 4));
                    layout.setYpos((float) (sr.routingBox().getMin().getY() 
                            + offset.y + margins.top + 4));

                }

                // no need to call the original implementation, we use it to move the nodes alone
                // TODO might be an option to do both at once
                 //super.applyLayout(root);
            }
        };
        libGraph.transformGraph();

        // Map all cola rectangles to libavoid shapeidrefs
        VariableIDMap idmap = new VariableIDMap();
        for (CNode n : graph.getChildren()) {
            KNode origin = (KNode) n.getProperty(CGraphProperties.ORIGIN);
            Integer libId = libGraph.getNodeIdMap().inverse().get(origin);
            idmap.addMappingForVariable(n.cIndex, libId);
        }

        // the topology stuff is not allowed to move the restricting nodes of a
        // compound node, hence we generate a FixedRelativeConstraint to keep this in position.
        if (parentNode.getParent() != null) {

            // create copies of the four bounding nodes as cola rectangles
            KShapeLayout shape = parentNode.getData(KShapeLayout.class);
            KInsets insets = shape.getInsets();
            float borderSpacing = shape.getProperty(CodaflowProperties.BORDER_SPACING);

            // offset each side by the shape buffer distance to let edges route properly
            float bufferDistance = shape.getProperty(AvoidProperties.SHAPE_BUFFER_DISTANCE);
            // top
            double y = 0 - LibavoidGraph.SURROUNDING_RECT_SIZE - bufferDistance;
            Rectangle r =
                    new Rectangle(0, shape.getWidth(), y, y + LibavoidGraph.SURROUNDING_RECT_SIZE);
            graph.nodes.add(r);
            idmap.addMappingForVariable(graph.getLastNodeIndex() + 0, LibavoidGraph.NODE_COMPOUND_NORTH);

            // right
            double x = 0 + shape.getWidth() + bufferDistance;
            Rectangle r2 =
                    new Rectangle(x, x + LibavoidGraph.SURROUNDING_RECT_SIZE, 0, shape.getHeight());
            graph.nodes.add(r2);
            idmap.addMappingForVariable(graph.getLastNodeIndex() + 1,
                    LibavoidGraph.NODE_COMPOUND_EAST);

            // bottom
            y = 0 + shape.getHeight() + bufferDistance;
            Rectangle r3 =
                    new Rectangle(0, shape.getWidth(), y, y + LibavoidGraph.SURROUNDING_RECT_SIZE);
            graph.nodes.add(r3);
            idmap.addMappingForVariable(graph.getLastNodeIndex() + 2,
                    LibavoidGraph.NODE_COMPOUND_SOUTH);

            // left
            x = 0 - bufferDistance - LibavoidGraph.SURROUNDING_RECT_SIZE;
            Rectangle r4 =
                    new Rectangle(x, x + LibavoidGraph.SURROUNDING_RECT_SIZE + insets.getLeft()
                            + borderSpacing, 0, shape.getHeight());
            graph.nodes.add(r4);
            // CHECKSTYLEOFF MagicNumber NEXT 10 Lines
            idmap.addMappingForVariable(graph.getLastNodeIndex() + 3, 
                    LibavoidGraph.NODE_COMPOUND_WEST);

            Unsigneds surroundingRects = new Unsigneds();
            surroundingRects.add(graph.getLastNodeIndex() + 0);
            surroundingRects.add(graph.getLastNodeIndex() + 1);
            surroundingRects.add(graph.getLastNodeIndex() + 2);
            surroundingRects.add(graph.getLastNodeIndex() + 3);
            // add a constraint that keeps the bounding nodes fixed relative to each other
            FixedRelativeConstraint frc =
                    new FixedRelativeConstraint(graph.nodes, surroundingRects, false);
            graph.constraints.add(frc);
            
            // we also have to fix the surrounding nodes at their positions, otherwise
            // the topologyaddon might move them together into an arbitrary direction. 
            PageBoundaryConstraints pb =
                    new PageBoundaryConstraints(r4.getMinX(),  r2.getMaxX(),
                            r.getMinY(), r3.getMaxY());
            pb.addShape(graph.getLastNodeIndex() + 0, r.width() / 2, r.height() / 2);
            pb.addShape(graph.getLastNodeIndex() + 1, r2.width() / 2, r2.height() / 2);
            pb.addShape(graph.getLastNodeIndex() + 2, r3.width() / 2, r3.height() / 2);
            pb.addShape(graph.getLastNodeIndex() + 3, r4.width() / 2, r4.height() / 2);
            graph.constraints.add(pb);
        }

        // create the libavoid addon that will improve the node positioning while preserving topology
        AvoidTopologyAddon addon =
                new AvoidTopologyAddon(graph.nodes, graph.constraints, graph.rootCluster, idmap,
                        moveLimit);
        libGraph.getRouter().setTopologyAddon(addon);

        // execute libavoid, applyLayout is called by this method
        libGraph.process();

        progressMonitor.done();
    }
}
