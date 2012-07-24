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
package de.cau.cs.kieler.klay.test.jtest;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
//import org.junit.runner.RunWith;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.klay.test.utils.GraphTestUtil;

/**
 * @author wah
 * 
 */
//@RunWith(GraphTestRunner.class)
public class GraphUnitTest {
    
    private List<KNode> graphList;
    
    @Before
    public void loadGraphs(){
        graphList = GraphTestUtil.loadGraphs("test/", true, true);
    }
    
    /**
     *  test graph
     */
    @Test
    public void test(){
        for(KNode g:graphList)
            assert(true);
    }

}
