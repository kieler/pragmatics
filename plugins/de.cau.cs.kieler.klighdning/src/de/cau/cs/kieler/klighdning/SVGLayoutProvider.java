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
package de.cau.cs.kieler.klighdning;

import de.cau.cs.kieler.core.alg.BasicProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.ui.diagram.DiagramLayoutEngine;
import de.cau.cs.kieler.kiml.ui.diagram.LayoutMapping;
import de.cau.cs.kieler.klighd.internal.macrolayout.KlighdLayoutManager;
import de.cau.cs.kieler.klighdning.viewer.SVGBrowsingViewer;

/**
 * @author uru
 * 
 * 
 */
@SuppressWarnings("restriction")
public class SVGLayoutProvider {

    // KlighD and Layout facilities
    private KlighdLayoutManager mng;

    private static SVGLayoutProvider instance;

    /**
     * 
     */
    private SVGLayoutProvider() {
        mng = new KlighdLayoutManager();
    }

    public static SVGLayoutProvider getInstance() {
        if (instance == null) {
            instance = new SVGLayoutProvider();
        }
        return instance;
    }

    public String layout(final SVGBrowsingViewer viewer) {
                 
        // build the layout mapping
        KNode model = viewer.getModel();
        // initially the viewer might not have a model set
        if (model == null) {
            return "";
        }
        LayoutMapping<KGraphElement> mapping = mng.buildLayoutGraph(model);

        // perform the layout
        DiagramLayoutEngine.INSTANCE.layout(mapping, new BasicProgressMonitor());
        mng.applyLayout(mapping, true, 0);

        // zoom to fit
        viewer.zoomToFit();
        
        // redraw
        String svg = viewer.render();
        return processSVG(viewer, svg); 
    }

    public String processSVG(final SVGBrowsingViewer viewer, final String svg) {

        // insert an id for the first group element
        String res3 = svg.substring(svg.indexOf("<svg") - 1, svg.length());
//        String res4 = res3.replaceFirst("width=", "w=");
//        String res5 = res4.replaceFirst("height=", "h=");

        StringBuffer sb = new StringBuffer(res3);
        sb.insert(sb.indexOf("<g") + 2, " id=\"group\"");

        if (viewer.getSvgTransform() != null) {
            sb.insert(sb.indexOf("<g") + 2, " transform=\"" + viewer.getSvgTransform() + "\"");
        }

        return sb.toString();
    }

    public String layoutAndGetSVG(final SVGBrowsingViewer viewer) {
        return layout(viewer);
    }
}
