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
package de.cau.cs.kieler.klodd.orthogonal.impl.ec;

import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.slimgraph.KSlimGraph;
import de.cau.cs.kieler.klodd.orthogonal.modules.IPlanarizer;

/**
 * Planarizer implementation that handles embedding constraints by performing EC
 * expansion and EC planarity test.
 * 
 * @author <a href="mailto:msp@informatik.uni-kiel.de">Miro Sp&ouml;nemann</a>
 */
public class ExpansionECPlanarizer extends AbstractAlgorithm implements IPlanarizer {

    /**
     * {@inheritDoc}
     */
    public void planarize(final KSlimGraph graph) {
        getMonitor().begin("EC Expansion planarization", 1);

        // expand the given embedding constraints
        ConstraintExpander constraintExpander = new ConstraintExpander();
        constraintExpander.expand(graph);

        // TODO implement EC-planarization of the expanded graph

        getMonitor().done();
    }

}
