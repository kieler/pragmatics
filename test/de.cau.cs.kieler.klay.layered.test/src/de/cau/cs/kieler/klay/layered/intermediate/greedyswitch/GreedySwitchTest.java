package de.cau.cs.kieler.klay.layered.intermediate.greedyswitch;
//package de.cau.cs.kieler.klay.layered.test.intermediate;
//
//import static org.hamcrest.CoreMatchers.is;
//import static org.junit.Assert.assertThat;
//
//import java.util.List;
//
//import org.junit.Test;
//
//import com.google.common.collect.Lists;
//
//import de.cau.cs.kieler.klay.layered.ILayoutProcessor;
//import de.cau.cs.kieler.klay.layered.graph.LGraph;
//import de.cau.cs.kieler.klay.layered.graph.LNode;
//import de.cau.cs.kieler.klay.layered.graph.Layer;
//import de.cau.cs.kieler.klay.layered.intermediate.GreedySwitchProcessor;
//import de.cau.cs.kieler.klay.layered.intermediate.GreedySwitchProcessor.Carefulness;
//import de.cau.cs.kieler.klay.layered.intermediate.GreedySwitchProcessor.CrossingCounterType;
//import de.cau.cs.kieler.klay.layered.test.AbstractLayeredProcessorTest;
//import de.cau.cs.kieler.klay.test.config.ILayoutConfigurator;
//import de.cau.cs.kieler.klay.test.utils.GraphTestObject;
//import de.cau.cs.kieler.klay.test.utils.TestPath;
//
//public class GreedySwitchTest extends AbstractLayeredProcessorTest {
//
//    private GreedySwitchProcessor greedyProcessor;
//    private static final String TEST_FOLDER = "klay_layered/greedy_switch_pathol";
//
//    // CHECKSTYLEOFF javadoc
//    public GreedySwitchTest(final GraphTestObject testObject, final ILayoutConfigurator config) {
//        super(testObject, config);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    protected TestPath[] getBundleTestPath() {
//        TestPath[] testPaths = { new TestPath(TEST_FOLDER, false, false, TestPath.Type.KGRAPH) };
//        return testPaths;
//    }
//
//    /**
//     * {@inheritDoc} TODO-alan ???
//     */
//    @Override
//    protected List<ILayoutConfigurator> getConfigurators() {
//        return Lists.newArrayList();
//    }
//
//    public void whenGreedySwitchIsExecutedWithConfiguration(final Carefulness carefulness,
//            final CrossingCounterType counterType) {
//        layered.runLayoutTestUntil(GreedySwitchProcessor.class, false, state);
//        List<ILayoutProcessor> processors = layered.getLayoutTestConfiguration(state);
//        greedyProcessor = (GreedySwitchProcessor) processors.get(state.getStep());
//        greedyProcessor.setConfiguration(carefulness, counterType);
//        layered.runLayoutTestStep(state);
//    }
//
//    @Test
//    public void shouldRepairBarycenterPathol() {
//        // given 00simpleGraphForwardCrossingMatrices.kgt
//        whenGreedySwitchIsExecutedWithConfiguration(Carefulness.ONE_SIDED,
//                CrossingCounterType.COUNT_ALL);
//        barycenterErrorShouldBeRepaired();
//    }
//
//    private void barycenterErrorShouldBeRepaired() {
//        LGraph graph = state.getGraphs().get(0);
//        Layer secondLayer = graph.getLayers().get(1);
//        LNode thirdNode = secondLayer.getNodes().get(2);
//        assertThat(thirdNode.getName(), is("N4"));
//    }
// }
