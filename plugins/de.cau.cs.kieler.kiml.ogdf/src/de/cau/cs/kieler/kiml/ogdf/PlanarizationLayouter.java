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

import net.ogdf.lib.PlanarizationLayout;
import net.ogdf.lib.UMLLayoutModule;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.layout.options.LayoutOptions;

/**
 * The Mixed-Upward Planarization layouter from the OGDF library.
 * 
 * @author mri
 */
public class PlanarizationLayouter extends OgdfUMLLayouter {

    /** default value for border spacing. */
    public static final float DEF_BORDER_SPACING = 8;
    
    /** default value for label edge distance. */
    public static final float DEF_LABEL_EDGE_DISTANCE = 15.0f;
    
    /** default value for label margin distance. */
    public static final float DEF_LABEL_MARGIN_DISTANCE = 15.0f;
    
    /**
     * {@inheritDoc}
     */
    protected UMLLayoutModule prepareLayouter(final KNode node,
            final IKielerProgressMonitor progressMonitor) {
        // create the layouter
        PlanarizationLayout layout = new PlanarizationLayout();
        
        // set options
        //TODO set the options and modules
        
        return layout;
    }
    
    /**
     * {@inheritDoc}
     */
    public Object getDefault(final String optionId) {
        if (optionId.equals(LayoutOptions.BORDER_SPACING)) {
            return DEF_BORDER_SPACING;
        } else if (optionId.equals(OPT_LABEL_EDGE_DISTANCE)) {
            return DEF_LABEL_EDGE_DISTANCE;
        } else if (optionId.equals(OPT_LABEL_MARGIN_DISTANCE)) {
            return DEF_LABEL_MARGIN_DISTANCE;
        } else {
            return null;
        }
    }
}
