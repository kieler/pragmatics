package de.cau.cs.kieler.klay.layered.test.intermediate;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.klay.layered.intermediate.CrossingCounter;
import de.cau.cs.kieler.klay.layered.p3order.LayerSweepCrossingMinimizer;
import de.cau.cs.kieler.klay.layered.test.AbstractLayeredProcessorTest;
import de.cau.cs.kieler.klay.test.config.ILayoutConfigurator;
import de.cau.cs.kieler.klay.test.utils.GraphTestObject;
import de.cau.cs.kieler.klay.test.utils.TestPath;

public class CrossingCounterTest extends AbstractLayeredProcessorTest {
    private static final String TEST_FOLDER = "klay_layered/greedy_switch_testgraphs";

    // CHECKSTYLEOFF javadoc
    public CrossingCounterTest(final GraphTestObject testObject, final ILayoutConfigurator config) {
        super(testObject, config);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected TestPath[] getBundleTestPath() {
        TestPath[] testPaths = { new TestPath(TEST_FOLDER, false, false, TestPath.Type.KGRAPH) };
        return testPaths;
    }

    /**
     * {@inheritDoc} TODOALAN ???
     */
    @Override
    protected List<ILayoutConfigurator> getConfigurators() {
        return Lists.newArrayList();
    }

    private CrossingCounter crossingCounter;

    @Before
    public void runUntil() {
        layered.runLayoutTestUntil(LayerSweepCrossingMinimizer.class, true, state);
        crossingCounter = new CrossingCounter(state.getGraphs().get(0));
    }

    @Test
    public void testAmountOfCrossings0() {
        assertEquals(3, crossingCounter.countAllCrossingsInGraph());
    }

    @Test
    public void testAmountOfCrossings2() {
        assertEquals(2, crossingCounter.countAllCrossingsInGraph());
    }

    @Test
    public void testAmountOfCrossings3() {
        assertEquals(4, crossingCounter.countAllCrossingsInGraph());
    }
}
