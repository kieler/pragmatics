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
package de.cau.cs.kieler.klay.test.utils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author wah
 * 
 */
@RunWith(GraphTestRunner.class)
public abstract class GraphTestUtilTest {
    
	@Before
	public void initialize(){
		System.out.println("Hello");
	}
	
    @Test
    public void test(){
        GraphTestUtil.loadGraphs("test/", true, true);
        assert(true);
    }

}
