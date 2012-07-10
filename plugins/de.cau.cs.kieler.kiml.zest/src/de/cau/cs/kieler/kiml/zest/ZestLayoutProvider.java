/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.zest;

import org.eclipse.zest.layouts.algorithms.AbstractLayoutAlgorithm;
import org.eclipse.zest.layouts.algorithms.GridLayoutAlgorithm;
import org.eclipse.zest.layouts.algorithms.RadialLayoutAlgorithm;
import org.eclipse.zest.layouts.algorithms.SpringLayoutAlgorithm;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.AbstractLayoutProvider;

/**
 * Layout provider that uses the Zest grid layout algorithm.
 * <p>
 * Note: Zest is going to be subject to a lot of changes, see
 * <a href="https://bugs.eclipse.org/bugs/show_bug.cgi?id=283083">Eclipse bug 283083</a>.
 * </p>
 * 
 * @author msp
 */
public class ZestLayoutProvider extends AbstractLayoutProvider {

    /** parameter value for the grid layout algorithm. */
    public static final String GRID_LAYOUT = "GridLayout";
    /** parameter value for the spring layout algorithm. */
    public static final String SPRING_LAYOUT = "SpringLayout";
    /** parameter value for the radial layout algorithm. */
    public static final String RADIAL_LAYOUT = "RadialLayout";

    /** parameter string for the configured Zest algorithm. */
    private String algoParam = null;

    /**
     * Initializes the Zest layout provider by setting the layout algorithm with
     * the given parameter string.
     * 
     * @param parameter parameter string that holds one of the pre-defined
     *            algorithm names
     */
    public void initialize(final String parameter) {
        this.algoParam = parameter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doLayout(final KNode layoutNode, final IKielerProgressMonitor progressMonitor) {
        AbstractLayoutAlgorithm zestLayoutAlgo;
        if (GRID_LAYOUT.equals(algoParam)) {
            zestLayoutAlgo = new GridLayoutAlgorithm();
        } else if (RADIAL_LAYOUT.equals(algoParam)) {
            zestLayoutAlgo = new RadialLayoutAlgorithm();
        } else {
            SpringLayoutAlgorithm springAlgo = new SpringLayoutAlgorithm();
            springAlgo.setRandom(false);
            zestLayoutAlgo = springAlgo;
        }
        ZestAlgorithmWrapper wrapper = new ZestAlgorithmWrapper(zestLayoutAlgo);
        wrapper.doLayout(layoutNode, progressMonitor);
    }

}
