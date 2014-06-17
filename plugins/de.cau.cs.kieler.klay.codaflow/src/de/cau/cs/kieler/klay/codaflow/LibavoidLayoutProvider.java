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
package de.cau.cs.kieler.klay.codaflow;

import java.util.Iterator;

import org.adaptagrams.Router;
import org.adaptagrams.RoutingOption;

import com.google.common.collect.Iterators;

import de.cau.cs.kieler.adaptagrams.cgraph.CGraph;
import de.cau.cs.kieler.adaptagrams.properties.CGraphProperties;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.math.KVectorChain;
import de.cau.cs.kieler.kiml.AbstractLayoutProvider;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutData;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.Direction;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.kiml.util.KimlUtil;
import de.cau.cs.kieler.kiml.util.adapters.KGraphAdapters;
import de.cau.cs.kieler.kiml.util.adapters.KGraphAdapters.KGraphAdapter;
import de.cau.cs.kieler.kiml.util.nodespacing.KimlNodeDimensionCalculation;
import de.cau.cs.kieler.klay.codaflow.avoid.CGraphAvoidImporter;
import de.cau.cs.kieler.klay.codaflow.graphimport.HierarchicalKGraphImporter;
import de.cau.cs.kieler.klay.codaflow.graphimport.IGraphImporter;

/**
 * @author uru
 *
 */
public class LibavoidLayoutProvider extends AbstractLayoutProvider {

    /**
     * {@inheritDoc}
     */
    @Override
    public void doLayout(final KNode parentNode, final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Global Libavoid Layout", 1);
        
        // bloody hack to fix port sides
        Iterator<KPort> ports = Iterators.filter(parentNode.eAllContents(), KPort.class);
        while (ports.hasNext()) {
            KPort p = ports.next();
            PortSide ps = KimlUtil.calcPortSide(p, Direction.RIGHT);
            p.getData(KShapeLayout.class).setProperty(LayoutOptions.PORT_SIDE, ps);
        }
        
        calculateMarginsAndSizes(parentNode);

        parentNode.getData(KLayoutData.class).setProperty(CGraphProperties.MARGIN_INCLUDES_SPACING,
                false);

        // importing
        IGraphImporter<KNode, CGraph> cImporter;
        cImporter = new HierarchicalKGraphImporter();
        
        IGraphImporter<CGraph, Router> rImporter;
        rImporter = new CGraphAvoidImporter();
        
        final CGraph cGraph = cImporter.importGraph(parentNode);
        final Router router = rImporter.importGraph(cGraph);
        
        // FIXME
        router.setRoutingOption(RoutingOption.nudgeSharedPathsWithCommonEndPoint, false);
        
        // do layout
        router.processTransaction();
        router.outputInstanceToSVG("hierarchy libavoid");
        
        
        // apply layout
        rImporter.applyLayout(router);
        cImporter.applyLayout(cGraph);
        
        // determine junction points
        Iterator<KEdge> edges = Iterators.filter(parentNode.eAllContents(), KEdge.class);
        while (edges.hasNext()) {
            KEdge edge = edges.next();
            KVectorChain junctionPoints = KimlUtil.determineJunctionPoints(edge);
            edge.getData(KLayoutData.class).setProperty(LayoutOptions.JUNCTION_POINTS,
                    junctionPoints);
        }
        
        progressMonitor.done();
    }
    

    private void calculateMarginsAndSizes(final KNode parent) {
        
        KGraphAdapter adapter = KGraphAdapters.adapt(parent);
        KimlNodeDimensionCalculation.sortPortLists(adapter);
        KimlNodeDimensionCalculation.calculateLabelAndNodeSizes(adapter);
        KimlNodeDimensionCalculation.getNodeMarginCalculator(adapter).process();

        if (parent.getData(KLayoutData.class).getProperty(LayoutOptions.LAYOUT_HIERARCHY)) {
            for (KNode child : parent.getChildren()) {
                calculateMarginsAndSizes(child);
            }
        }
    }
}
