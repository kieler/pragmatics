package de.cau.cs.kieler.grana.test;

import java.util.Collection;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import de.cau.cs.kieler.grana.analyses.OrthogonalCrossingsAnalysis;
import de.cau.cs.kieler.klay.test.utils.GraphTestObject;
import de.cau.cs.kieler.klay.test.utils.TestPath;

public class OrthogonalCrossingAnalysisTest extends AbstractGranaTest {

    final TestPath[] testPaths = { new TestPath("grana/orthogonalCrossingsCounter", true, true,
            TestPath.Type.KGRAPH, null) };
    private final GraphTestObject testObject;
    /**
     * Result is amount of visible crossings.
     */
    private final Map<String, Object[]> expectedResults =
            new ImmutableMap.Builder<String, Object[]>().put("simpleCross.kgt", new Object[] { 1 })
                    .put("edgesDontCross.kgt", new Object[] { 0 })
                    .put("edgesOnTopOfEachOtherDontCross.kgt", new Object[] { 0 })
                    .put("edgesOnTopOfEachOtherOnlyCrossOnce.kgt", new Object[] { 1 })
                    .put("hierarchicalSimpleCross.kgt", new Object[] { 1 }).build();

    /**
     * {@inheritDoc}
     */
    @Override
    protected TestPath[] getBundleTestPath() {
        return testPaths;
    }

    public OrthogonalCrossingAnalysisTest(final GraphTestObject tO) {
        super(tO);
        testObject = tO;
    }

    @Override
    protected Collection<String> getAnalysesIdsToRun() {
        return ImmutableList.of(OrthogonalCrossingsAnalysis.ID);
    }

    /**
     * What is being tested is shown by the file names in expectedResults.
     */
    @Test
    public void test() {
        // execute analysis
        Object bpResult = result.getResult(OrthogonalCrossingsAnalysis.ID);

        Object[] expected = expectedResults.get(testObject.getFile().getName());
        if (expected == null) {
            throw new IllegalArgumentException("No expected result specified for test file "
                    + testObject.getFile() + " ");
        }

        Assert.assertArrayEquals(expected, (Object[]) bpResult);
    }
}
