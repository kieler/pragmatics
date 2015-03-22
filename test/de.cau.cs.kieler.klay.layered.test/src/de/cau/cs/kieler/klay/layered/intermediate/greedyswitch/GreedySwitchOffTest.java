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
package de.cau.cs.kieler.klay.layered.intermediate.greedyswitch;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.cau.cs.kieler.core.alg.BasicProgressMonitor;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.properties.GreedySwitchType;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 * Tests OFF setting.
 * 
 * @author alan
 *
 */
public class GreedySwitchOffTest {

    // CHECKSTYLEOFF Javadoc
    // CHECKSTYLEOFF MagicNumber
    @Test
    public void greedySwitchIsOff() {
        TestGraphCreator creator = new TestGraphCreator();
        LGraph graph = creator.getCrossFormedGraph();
        graph.setProperty(Properties.GREEDY_SWITCH_TYPE, GreedySwitchType.OFF);

        List<LNode> expectedOrderLayerOne = getNodesInLayer(0, graph);
        List<LNode> expectedOrderLayerTwo = getNodesInLayer(1, graph);

        GreedySwitchProcessor greedySwitcher = new GreedySwitchProcessor();
        greedySwitcher.process(graph, new BasicProgressMonitor());

        assertThat(getNodesInLayer(0, graph), is(expectedOrderLayerOne));
        assertThat(getNodesInLayer(1, graph), is(expectedOrderLayerTwo));
    }

    private List<LNode> getNodesInLayer(final int layerIndex, final LGraph graph) {
        return new ArrayList<LNode>(graph.getLayers().get(layerIndex).getNodes());
    }
}
