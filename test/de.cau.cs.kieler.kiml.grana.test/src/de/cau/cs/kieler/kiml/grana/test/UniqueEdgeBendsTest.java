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
package de.cau.cs.kieler.kiml.grana.test;

import java.util.Collection;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import de.cau.cs.kieler.kiml.grana.analyses.BendsAnalysis;
import de.cau.cs.kieler.klay.test.utils.GraphTestObject;
import de.cau.cs.kieler.klay.test.utils.TestPath;

/**
 * Tests the 'unique bendpoints' counting of the {@link BendsAnalysis}.
 * 
 * @author uru
 */
public class UniqueEdgeBendsTest extends AbstractGranaTest {

    public UniqueEdgeBendsTest(final GraphTestObject testObject) {
        super(testObject);
    }

    private final Collection<String> analyses = ImmutableList.of(BendsAnalysis.ID); 
    
    @Override
    protected Collection<String> getAnalysesIdsToRun() {
        return analyses;
    }
    
    final TestPath[] testPaths = { new TestPath("grana/bends", true, true, TestPath.Type.KGRAPH) };

    @Override
    protected TestPath[] getBundleTestPath() {
        return testPaths;
    }
    
    private Map<String, Object[]> expectedResults = ImmutableMap.of(
            "uniqueBendpointsHierarchy.kgt", new Object[]{1,1f,1,2},
            "uniqueBendpointsHyperedge.kgt", new Object[]{1,1f,1,2},
            "uniqueBendpointsExternalPort.kgt", new Object[]{1,1f,1,2}
            );
    
    /* ----------------------
     *        TESTS
     * ---------------------- */
    
    /**
     * Test that consecutive analyses runs on the same graph yield the same results.
     */
    @Test
    public void uniqueBendpointsTest() {

        // execute analysis
        Object bpResult = result.getResult(BendsAnalysis.ID);
        
        Object[] expected = expectedResults.get(testObject.getFile().getName());
        if (expected == null) {
            throw new IllegalArgumentException("No expected result specified for test file "
                    + testObject.getFile() + " ");
        }
        
        Assert.assertArrayEquals(expected, (Object[]) bpResult);
    }
    


}
