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
package de.cau.cs.kieler.kiml.ogdf;

import net.ogdf.lib.Ogdf;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;

/**
 * The Spring Embedder algorithm by Fruchterman and Reingold.
 * 
 * @author mri
 */
public class SpringEmbedderFRLayouter extends OgdfLayouter {

    /** the iterations option identifier. */
    private static final String ITERATIONS_ID = "de.cau.cs.kieler.ogdf.iterations";
    /** default value for the number of iterations. */
    private static final int DEF_ITERATIONS = 400;
    /** number of iterations property. */
    private static final IProperty<Integer> ITERATIONS = new Property<Integer>(
            ITERATIONS_ID, DEF_ITERATIONS);

    /**
     * {@inheritDoc}
     */
    protected void prepareLayouter(final KNode layoutNode) {
        KShapeLayout parentLayout = layoutNode.getData(KShapeLayout.class);
        
        // get the number of iterations
        int iter = parentLayout.getProperty(ITERATIONS);
        if (iter <= 0) {
            iter = DEF_ITERATIONS;
        }
        
        Ogdf.createSpringEmbedderFRLayouter(iter);
    }

}
