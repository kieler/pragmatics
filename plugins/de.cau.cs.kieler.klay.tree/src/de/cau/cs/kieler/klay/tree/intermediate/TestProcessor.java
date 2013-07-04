/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.tree.intermediate;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.klay.tree.ILayoutProcessor;
import de.cau.cs.kieler.klay.tree.graph.TGraph;
import de.cau.cs.kieler.klay.tree.graph.TNode;
import de.cau.cs.kieler.klay.tree.properties.Properties;

/**
 * A debug processor for framework testing.
 * 
 * @author sor
 * @author sgu
 */
public class TestProcessor implements ILayoutProcessor {

    public void process(TGraph tGraph, IKielerProgressMonitor progressMonitor) {
        if (tGraph.getProperty(LayoutOptions.DEBUG_MODE)) {
            for (TNode node : tGraph.getNodes()) {
                String l = node.getLabel();
                String lN = node.getProperty(Properties.LEFTNEIGHBOR) != null ? node.getProperty(
                        Properties.LEFTNEIGHBOR).getLabel() : "null";
                String rN = node.getProperty(Properties.RIGHTNEIGHBOR) != null ? node.getProperty(
                        Properties.RIGHTNEIGHBOR).getLabel() : "null";
                String lS = node.getProperty(Properties.LEFTSIBLING) != null ? node.getProperty(
                        Properties.LEFTSIBLING).getLabel() : "null";
                String rS = node.getProperty(Properties.RIGHTSIBLING) != null ? node.getProperty(
                        Properties.RIGHTSIBLING).getLabel() : "null";
                String x = node.getProperty(Properties.XCOOR) != null ? node.getProperty(
                        Properties.XCOOR).toString() : "null";
                String y = node.getProperty(Properties.YCOOR) != null ? node.getProperty(
                        Properties.YCOOR).toString() : "null";
                System.out.println(l + " lN:" + lN + " rN:" + rN + " lS:" + lS + " rS:" + rS
                        + " X:" + x + " Y:" + y + " pos " + node.getPosition());
            }
            System.out.println();
        }
    }

}
