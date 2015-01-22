package de.cau.cs.kieler.klay.layered.intermediate.greedyswitch;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CrossingCounterTest.class, DoublyLinkeHashSetTest.class, SwitchDeciderTest.class,
        TwoNodeInLayerEdgeCrossingCounterTest.class, TwoNodeTwoLayerCrossingCounterTest.class,
        GreedySwitchTest.class })
public class AllGreedySwitchTests {

}
