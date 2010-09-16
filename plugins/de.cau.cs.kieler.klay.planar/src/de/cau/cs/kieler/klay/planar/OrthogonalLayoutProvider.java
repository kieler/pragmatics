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
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.kiml.AbstractLayoutProvider;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.klay.planar.alg.orthogonal.GiottoCompactor;
import de.cau.cs.kieler.klay.planar.alg.orthogonal.ICompactor;
import de.cau.cs.kieler.klay.planar.alg.orthogonal.IOrthogonalizer;
import de.cau.cs.kieler.klay.planar.alg.orthogonal.TamassiaOrthogonalizer;
import de.cau.cs.kieler.klay.planar.alg.orthogonal.IOrthogonalizer.OrthogonalRepresentation;
import de.cau.cs.kieler.klay.planar.alg.planarity.BoyerMyrvoldPlanarityTester;
import de.cau.cs.kieler.klay.planar.alg.planarity.EdgeInsertionPlanarization;
import de.cau.cs.kieler.klay.planar.alg.planarity.IPlanarityTester;
import de.cau.cs.kieler.klay.planar.alg.planarity.IPlanarizer;
import de.cau.cs.kieler.klay.planar.alg.planarity.LRPlanarityTester;
import de.cau.cs.kieler.klay.planar.graph.IEdge;
import de.cau.cs.kieler.klay.planar.graph.IGraph;
import de.cau.cs.kieler.klay.planar.graph.IGraphFactory;
import de.cau.cs.kieler.klay.planar.graph.impl.PGraphFactory;
import de.cau.cs.kieler.klay.planar.options.PlanarityTestAlgorithm;

/**
 * Layout provider for an orthogonal layout.
 * 
 * @author ocl
 * @author pdo
 */
public class OrthogonalLayoutProvider extends AbstractLayoutProvider {

    // ======================== Layout Options =====================================================

    /** The layout option ID for the planar testing algorithm option. */
    public static final String PLANAR_TESTING_ALGORITHM_ID = "de.cau.cs.kieler.klay.planar.options.planarTestingAlgorithm";
    /** property for planar testing algorithm. */
    public static final IProperty<PlanarityTestAlgorithm> PLANAR_TESTING_ALGORITHM = new Property<PlanarityTestAlgorithm>(
            PLANAR_TESTING_ALGORITHM_ID, PlanarityTestAlgorithm.BOYER_MYRVOLD_ALGORITHM);

    // ======================== Attributes =========================================================

    /** Graph factory. */
    private IGraphFactory factory = new PGraphFactory();

    /** Algorithm for planar testing. */
    private IPlanarityTester tester = new BoyerMyrvoldPlanarityTester();

    /** Algorithm for planarization. */
    private IPlanarizer planarizer = new EdgeInsertionPlanarization();

    /** Algorithm for orthogonalization. */
    private IOrthogonalizer orthogonalizer = new TamassiaOrthogonalizer();

    /** Algorithm for compaction. */
    private ICompactor compactor = new GiottoCompactor();

    // ======================== Layout Provider ====================================================

    @Override
    public void doLayout(final KNode layoutNode, final IKielerProgressMonitor monitor)
            throws KielerException {
        monitor.begin("Orthogonal Layout", 1);

        // Get layout options
        KShapeLayout parentLayout = layoutNode.getData(KShapeLayout.class);
        PlanarityTestAlgorithm algorithm = parentLayout.getProperty(PLANAR_TESTING_ALGORITHM);
        switch (algorithm) {
        case BOYER_MYRVOLD_ALGORITHM:
            if (!(this.tester instanceof BoyerMyrvoldPlanarityTester)) {
                this.tester = new BoyerMyrvoldPlanarityTester();
            }
            break;
        case LEFT_RIGHT_PLANARITY_ALGORITHM:
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
        OrthogonalRepresentation orthogonal = this.orthogonalizer.orthogonalize(graph);

        // Step 4: Compaction
        this.compactor.compact(graph, orthogonal);

        monitor.done();
    }

}
