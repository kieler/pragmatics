/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.core.kivi.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.cau.cs.kieler.core.kivi.ICombination;
import de.cau.cs.kieler.core.kivi.ITriggerState;
import de.cau.cs.kieler.core.kivi.KiVi;
import de.cau.cs.kieler.core.kivi.test.TestTriggerA.AState;
import de.cau.cs.kieler.core.kivi.test.TestTriggerB.BState;
import de.cau.cs.kieler.core.kivi.test.TestTriggerSpammer.SpamState;

/**
 * Test of the core of KiVi. Will instanciate the KiVi instance, setup some Combinations and trigger
 * some triggers. First test cases check whether the AbstractCombination correctly retrieves the
 * necessary information from the combinations's execute methods and correctly throws exceptions if
 * the combination is malformed.
 * <p>
 * Later tests run KiVi for some time. Some of the test cases can detect error states themselves
 * (e.g. increase of effects queue) but some cannot. So these should be run manually and the console
 * output needs to be interpreted correctly.
 * <p>
 * Most test cases are meant to play around with KiVi automatically.
 * 
 * @author haf
 * 
 */
public class KiViCoreTest {

    @Before
    public void setupKiVi() {
        // KiVi.getInstance().initialize(); // initialize only works in Eclipse
        KiVi.getInstance().setActive(true);
    }

    /**
     * Combination has no execute method -> exception should be thrown.
     */
    @Test(expected = IllegalArgumentException.class)
    public void TestNoExecute() {
        KiVi.getInstance().registerCombination(new TestCombinationNoExecute(), true);
    }

    /**
     * Combination has multiple execute methods with overlapping parameters -> exception should be
     * thrown.
     */
    @Test(expected = IllegalArgumentException.class)
    public void TestOverlappingParameters() {
        KiVi.getInstance().registerCombination(new TestCombinationOverlappingParameters(), true);
    }

    /**
     * Combination has execute method with not supported parameter type -> exception should be
     * thrown.
     */
    @Test(expected = IllegalArgumentException.class)
    public void TestWrongParameterType() {
        KiVi.getInstance().registerCombination(new TestCombinationWrongParameterType(), true);
    }

    /**
     * Combination has multiple execute methods with effect parameters which is currently not
     * supported; all effect triggers need to be in one execute method. -> exception should be
     * thrown.
     */
    @Test(expected = IllegalArgumentException.class)
    public void TestTwoEffects() {
        KiVi.getInstance().registerCombination(new TestCombinationTwoEffects(), true);
    }

    /**
     * Test whether the trigger states that a combination listens to are correctly extracted via
     * reflection from the execute methods.
     */
    @Test
    public void TestGetTriggerStates() {
        ICombination combo = new TestCombinationManyExecutes();
        Class<? extends ITriggerState>[] actuals = combo.getTriggerStates();
        Class[] expecteds = { AState.class, BState.class, SpamState.class };
        assertArrayEquals("The expected trigger states are not correct.", expecteds, actuals);
    }

    /**
     * Simplest trigger-combination-effect interaction.
     */
    @Test
    public void TestA() throws InterruptedException {
        KiVi.getInstance().registerCombination(new TestCombinationA(), true);
        Thread.sleep(5000);
    }

    /**
     * Two combinations with totally different triggers.
     */
    @Test
    public void TestDisjointCombinations() throws InterruptedException {
        KiVi.getInstance().registerCombination(new TestCombinationA(), true);
        KiVi.getInstance().registerCombination(new TestCombinationB(), true);
        Thread.sleep(3000);
    }

    /**
     * A combination that schedules effects and two others listening for these effects.
     */
    @Test
    public void TestEffect() throws InterruptedException {
        KiVi.getInstance().registerCombination(new TestCombinationA(), true);
        // two different ways how to use effects triggers
        KiVi.getInstance().registerCombination(new TestCombinationEffectsTrigger(), true);
        KiVi.getInstance().registerCombination(new TestCombinationEffectsTrigger2(), true);
        Thread.sleep(3000);
    }

    /**
     * Test whether effects re correctly undone, respectively not undone.
     */
    @Test
    public void TestUndo() throws InterruptedException {
        KiVi.getInstance().registerCombination(new TestCombinationUndo(), true);
        Thread.sleep(5000);
    }
    
    /**
     * Spamming of triggers should not overflow the effects queue.
     */
    @Test
    public void TestSpammerSynchronized() throws InterruptedException {
        KiVi.getInstance().registerCombination(new TestCombinationSpammerSynchronized(), true);
        Thread.sleep(3000);
        int size = KiVi.getInstance().getEffectsQueueSize();
        assertTrue("Effects queue will overflow. After 3s " + size + " effects on queue.",
                size < 3);
    }
    
    /**
     * Spamming of triggers should not overflow the effects queue.
     */
    @Test
    public void TestSpammer() throws InterruptedException {
        KiVi.getInstance().registerCombination(new TestCombinationSpammer(), true);
        Thread.sleep(3000);
        int size = KiVi.getInstance().getEffectsQueueSize();
        assertFalse("Effects queue did not overflow, although we expected this, because this test" +
        		"uses a not-synchronized trigger. After 3s " + size + " effects on queue.",
                size < 100);
    }

}
