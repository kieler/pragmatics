/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.planar;

import java.util.List;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.AbstractLayoutProvider;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.util.KimlLayoutUtil;
import de.cau.cs.kieler.klay.planar.alg.IOrthogonalizer;
import de.cau.cs.kieler.klay.planar.alg.IPlanarityTester;
import de.cau.cs.kieler.klay.planar.alg.IPlanarizer;
import de.cau.cs.kieler.klay.planar.alg.impl.BoyerMyrvoldPlanarityTester;
import de.cau.cs.kieler.klay.planar.alg.impl.EdgeInsertionPlanarization;
import de.cau.cs.kieler.klay.planar.alg.impl.LRPlanarityTester;
import de.cau.cs.kieler.klay.planar.graph.IEdge;
import de.cau.cs.kieler.klay.planar.graph.IGraph;
import de.cau.cs.kieler.klay.planar.graph.IGraphFactory;
import de.cau.cs.kieler.klay.planar.graph.impl.PGraphFactory;

/**
 * Layout provider for an orthogonal layout.
 * 
 * @author ocl
 * @author pdo
 */
public class OrthogonalLayoutProvider extends AbstractLayoutProvider {

    // ======================== Layout Options =====================================================

    /**
     * An enum for the possible planar testing algorithms used by the layouter.
     * 
     * @author ocl
     */
    public enum PlanarTestingAlgorithm {
        /** The algorithm by Boyer and Myrvold. */
        BOYER_MYRVOLD_ALGORITHM,

        /** The algorithm based on the left-right-planarity test. */
        LEFT_RIGT_PLANARITY_ALGORITHM,
    }

    /** The layout option ID for the planar testing algorithm option. */
    public static final String PLANAR_TESTING_ALGORITHM = new String(
            "de.cau.cs.kieler.klay.planar.options.planarTestingAlgorithm");

    /** The default algorithm for planar testing. */
    private static final PlanarTestingAlgorithm DEFAULT_ALGORITHM = PlanarTestingAlgorithm.BOYER_MYRVOLD_ALGORITHM;

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getDefault(final String option) {
        if (OrthogonalLayoutProvider.PLANAR_TESTING_ALGORITHM.equals(option)) {
            return DEFAULT_ALGORITHM;
        } else {
            return super.getDefault(option);
        }
    }

    // ======================== Attributes =========================================================

    /** Graph factory. */
    private IGraphFactory factory = new PGraphFactory();

    /** Algorithm for planar testing. */
    private IPlanarityTester tester = new BoyerMyrvoldPlanarityTester();

    /** Algorithm for planarization. */
    private IPlanarizer planarizer = new EdgeInsertionPlanarization();

    /** Algorithm for orthogonalization. */
    private IOrthogonalizer orthogonalizer = null; // new QuodOrthogonalizer(); TODO check into trac

    // ======================== Constructor ========================================================

    /**
     * Default Constructor.
     */
    public OrthogonalLayoutProvider() {
        LayoutOptions.registerEnum(PLANAR_TESTING_ALGORITHM, PlanarTestingAlgorithm.class);
    }

    // ======================== Layout Provider ====================================================

    @Override
    public void doLayout(final KNode layoutNode, final IKielerProgressMonitor monitor)
            throws KielerException {
        monitor.begin("Orthogonal Layout", 1);

        // Get layout options
        KShapeLayout parentLayout = KimlLayoutUtil.getShapeLayout(layoutNode);
        PlanarTestingAlgorithm algorithm = LayoutOptions.getEnum(parentLayout,
                PlanarTestingAlgorithm.class);
        switch (algorithm) {
        case BOYER_MYRVOLD_ALGORITHM:
            if (!(this.tester instanceof BoyerMyrvoldPlanarityTester)) {
                this.tester = new BoyerMyrvoldPlanarityTester();
            }
            break;
        case LEFT_RIGT_PLANARITY_ALGORITHM:
            if (!(this.tester instanceof LRPlanarityTester)) {
                this.tester = new LRPlanarityTester();
            }
        }

        // KGraph -> PGraph conversion
        IGraph graph = this.factory.createGraphFromKGraph(layoutNode);

        // Step 1: Planarity Testing
        List<IEdge> edges = this.tester.planarSubgraph(graph);

        // Step 2: Planarization
        this.planarizer.planarize(graph, edges);

        // Step 3: Orthogonalization
        this.orthogonalizer.orthogonalize(graph);

        monitor.done();
    }

}
