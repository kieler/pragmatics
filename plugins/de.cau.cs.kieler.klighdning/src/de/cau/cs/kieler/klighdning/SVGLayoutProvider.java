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
 * 
 * @author uru
 */
public final class SVGLayoutProvider {

    // KlighD and Layout facilities
    private KlighdLayoutManager mng;

    private static SVGLayoutProvider instance;

    /**
     * 
     */
    private SVGLayoutProvider() {
        mng = new KlighdLayoutManager();
    }

    /**
     * @return the instance
     */
    public static SVGLayoutProvider getInstance() {
        if (instance == null) {
            instance = new SVGLayoutProvider();
        }
        return instance;
    }

    /**
     * @param viewer
     *            onto which to perform the layout
     * @param zoomToFit
     *            whether to zoom and fit
     * @return a rendered svg
     */
    public String layout(final SVGBrowsingViewer viewer, final boolean zoomToFit) {

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
        // TODO currently always
        // if(zoomToFit) {
        viewer.zoomToFit();
        // }

        // redraw
        String svg = viewer.render();
        return processSVG(viewer, svg);
    }

    private String processSVG(final SVGBrowsingViewer viewer, final String svg) {

        // remove the xml declaration so that we can embed the svg
        String res3 = svg.substring(svg.indexOf("<svg"), svg.length());

        // insert an id for the first group element
        StringBuffer sb = new StringBuffer(res3);
        sb.insert(sb.indexOf("<g") + 2, " id=\"group\"");

        // add an initial transform
        if (viewer.getSvgTransform() != null) {
            sb.insert(sb.indexOf("<g") + 2, " transform=\"" + viewer.getSvgTransform() + "\"");
        }

        return sb.toString();
    }
}
