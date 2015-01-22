package de.cau.cs.kieler.klay.layered.intermediate.greedyswitch;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.klay.layered.ILayoutProcessor;
import de.cau.cs.kieler.klay.layered.test.AbstractLayeredProcessorTest;
import de.cau.cs.kieler.klay.test.config.ILayoutConfigurator;
import de.cau.cs.kieler.klay.test.utils.GraphTestObject;
import de.cau.cs.kieler.klay.test.utils.TestPath;

public class GreedySwitcherIntegrationTest extends AbstractLayeredProcessorTest {

    private static final String TEST_FOLDER = "klay_layered/greedy_switch_testgraphs";

    public GreedySwitcherIntegrationTest(final GraphTestObject testObject,
            final ILayoutConfigurator config) {
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
     * {@inheritDoc} TODO-alan ???
     */
    @Override
    protected List<ILayoutConfigurator> getConfigurators() {
        return Lists.newArrayList();
    }

    private GreedySwitchProcessor greedyProcessor;

    /**
     * Run the layout algorithm until the specified strategy finished.
     */
    @Before
    public void runUntil() {
        layered.runLayoutTestUntil(GreedySwitchProcessor.class, true, state);
        List<ILayoutProcessor> processors = layered.getLayoutTestConfiguration(state);
        greedyProcessor = (GreedySwitchProcessor) processors.get(state.getStep() - 1);
    }

    @Test
    public void allOneSidedMethodsShouldResultInSameGraph() {
        fail("Test not yet implemented.");// TODO-alan
    }

    @Test
    public void allTwoSidedMethodsShouldResultInSameGraph() {
        fail("Test not yet implemented.");// TODO-alan
    }

    @Test
    public void crossingNumbersShouldNotBeWorseAfterward() {
        fail("Test not yet implemented.");// TODO-alan
    }

}
