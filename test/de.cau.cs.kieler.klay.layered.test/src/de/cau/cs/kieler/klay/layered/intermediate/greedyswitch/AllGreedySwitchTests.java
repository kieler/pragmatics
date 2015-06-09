/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.intermediate.greedyswitch;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Suite which runs all greedy switch tests which don't need to start an Eclipse instance.
 * 
 * @author alan
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ AllCrossingsCounterTest.class, AllInLayerEdgeCrossingCounterTest.class,
        SwitchDeciderTest.class, InLayerEdgeTwoNodeCrossingCounterTest.class,
        BetweenLayerEdgeTwoNodeCrossingsCounterTest.class, GreedySwitchProcessorTest.class,
        NorthSouthEdgeNeighbouringNodeCrossingsCounterTest.class, GreedySwitchOffTest.class,
        BetweenLayerStraightEdgeAllCrossingsCounterTest.class })
public class AllGreedySwitchTests {

}
