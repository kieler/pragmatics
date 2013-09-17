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
package de.cau.cs.kieler.klay.layered.test.phases;

import com.google.common.base.Function;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.klay.layered.ILayoutProcessor;
import de.cau.cs.kieler.klay.test.config.ILayoutConfigurator;

/**
 * Configures a certain phase of the layered layout algorithm with a specific implementation.
 * 
 * @author uru
 */
public class SimplePhaseLayoutConfigurator implements ILayoutConfigurator {

    private IProperty<?> phase;
    private Object strategy;
    private Class<? extends ILayoutProcessor> strategyImpl;
    private Function<KNode, KNode>[] modifiers;

    /**
     * Exemplarily creation.
     * 
     * <pre>
     * new SimplePhaseLayoutConfigurator(Properties.CYCLE_BREAKING, CycleBreakingStrategy.GREEDY,
     *         GreedyCycleBreaker.class)
     * </pre>
     * 
     * @param phase
     *            the phase for which to set the strategy.
     * @param strategy
     *            the Enum descriptor of the strategy.
     * @param strategyImpl
     *            the implementing {@link Class} of the phase.
     * @param modifierFuns
     *            function that modify the root node in an arbitrary manner. The type has to be
     *            Function<KNode, KNode>, the constructor leaves it raw to avoid some nasty compiler
     *            warnings.
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public SimplePhaseLayoutConfigurator(final IProperty<?> phase, final Object strategy,
            final Class<? extends ILayoutProcessor> strategyImpl, final Function... modifierFuns) {
        super();
        this.phase = phase;
        this.strategy = strategy;
        this.strategyImpl = strategyImpl;
        modifiers = modifierFuns;
    }

    /**
     * {@inheritDoc}
     */
    public void applyConfiguration(final KNode root) {
        root.getData(KShapeLayout.class).setProperty(phase, strategy);

        for (Function<KNode, KNode> m : modifiers) {
            m.apply(root);
        }
    }

    /**
     * @return the phase
     */
    public IProperty<?> getPhase() {
        return phase;
    }

    /**
     * @return the strategy
     */
    public Class<? extends ILayoutProcessor> getStrategyImpl() {
        return strategyImpl;
    }

    /**
     * @return The short name of the phase followed by the strategy.
     */
    public String getDescription() {
        String phaseName = phase.toString();
        String simplePhaseName =
                phaseName.substring(phaseName.lastIndexOf('.') + 1, phaseName.length());
        return "-" + simplePhaseName + ", " + strategy.toString() + "";
    }
}
