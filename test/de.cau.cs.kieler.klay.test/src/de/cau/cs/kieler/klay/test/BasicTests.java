/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.test;

import static org.junit.Assert.assertTrue;

import java.util.ListIterator;

import org.eclipse.draw2d.geometry.Rectangle;
import org.junit.Test;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.klay.test.utils.GraphTestObject;
import de.cau.cs.kieler.klay.test.utils.TestPath;

/**
 * An Example test Class
 * 
 * @author Wahbi
 */
public class BasicTests extends KlayAutomatedJUnitTest {

    // Object containing the current graphTestObject that contains both, a File and a KNode.
    private GraphTestObject graphObject;

    /**
     * Instantiates a new KlayTestExample test and set the graphObject to the current graph to test.
     * 
     * @param testObject
     */
    public BasicTests(final GraphTestObject testObject) {
        graphObject = testObject;
    }

    /**
     * {@inheritDoc}
     */
    protected TestPath[] getBundleTestPath() {

        TestPath[] testPaths = { new TestPath("keg/testtest", false, true) };
        return testPaths;
    }

    /**
     * A Junit Method to test if two nodes overlaps
     */
    @Test
    public void testNodeOverlaps() {
        KNode parentNode = graphObject.getKnode();
        ListIterator<KNode> nodeIter1 = parentNode.getChildren().listIterator();
        while (nodeIter1.hasNext()) {
            KNode node1 = nodeIter1.next();
            KShapeLayout nodeLayout1 = node1.getData(KShapeLayout.class);
            ListIterator<KNode> nodeIter2 = parentNode.getChildren().listIterator(
                    nodeIter1.nextIndex());
            while (nodeIter2.hasNext()) {
                KNode node2 = nodeIter2.next();
                KShapeLayout nodeLayout2 = node2.getData(KShapeLayout.class);

                assertTrue(!intersect(nodeLayout1, nodeLayout2));
            }
        }
    }

    /**
     * 
     * @param layout1
     *            the KShapeLayout of the first Node
     * @param layout2
     *            the KShapeLayout of the first Node
     * @return true if the nodes overlaps and false otherwise
     */
    private static boolean intersect(KShapeLayout layout1, KShapeLayout layout2) {
        float x1 = Math.max(layout1.getXpos(), layout2.getXpos());
        float x2 = Math.min(layout1.getXpos() + layout1.getWidth(),
                layout2.getXpos() + layout2.getWidth());
        float y1 = Math.max(layout1.getYpos(), layout2.getYpos());
        float y2 = Math.min(layout1.getYpos() + layout1.getHeight(),
                layout2.getYpos() + layout2.getHeight());
        return (x2 >= x1 && y2 >= y1);
    }

    // TODO test the overlaps between nodes and
    // edges(seede.cau.cs.kieler.kiml.service.grana.analyses.NodeEdgeOverlapsAnalysis)

}
