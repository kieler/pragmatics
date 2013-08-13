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
package de.cau.cs.kieler.klighdning.viewer;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.util.EcoreUtil;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.klighd.piccolo.internal.controller.DiagramController;
import de.cau.cs.kieler.klighd.piccolo.internal.nodes.KNodeNode;
import de.cau.cs.kieler.klighd.piccolo.svg.KlighdSVGCanvas;
import edu.umd.cs.piccolo.PCamera;
import edu.umd.cs.piccolo.PLayer;
import edu.umd.cs.piccolo.PNode;

/**
 * This is an offscreen Klighd Viewer, hence no display is required.
 * 
 * @author uru
 * 
 */
public class SVGBrowsingViewer {

    /** the canvas used for drawing. */
    private KlighdSVGCanvas canvas;

    /** the graph controller. */
    private DiagramController controller;

    /** used to expand and collapse nodes. */
    private Map<Integer, KNode> currentHashCodeMapping = Maps.newHashMap();

    // permalink
    private String resourcePath;
    private String svgTransform;

    /**
     * 
     */
    public SVGBrowsingViewer() {
        canvas = new KlighdSVGCanvas();
    }

    public String render() {

        // wrap all expandable nodes
        if (controller != null) {
            currentHashCodeMapping.clear();
            recurseNodes(controller.getNode());
        }

        // render and return svg
        return canvas.render();
    }

    public void setModel(KNode model, boolean sync) {
        // prepare the camera
        PCamera camera = canvas.getCamera();

        // remove the old nodes from the camera
        @SuppressWarnings("unchecked")
        Iterable<PLayer> layers = camera.getLayersReference();
        for (PLayer layer : layers) {
            layer.removeAllChildren();
        }

        // create a controller for the graph
        controller = new DiagramController(model, camera.getLayer(0), sync);
        controller.initialize();
        controller.setRecording(false);
    }

    public KNode getModel() {
        if (controller != null) {
            return controller.getNode().getGraphElement();
        }
        return null;
    }

    /**
     * @param hashCode
     *            of the node to expand
     */
    public void toggleExpansion(final String hashCode) {
        try {
            int hash = Integer.valueOf(hashCode);
            KNode node = currentHashCodeMapping.get(hash);
            toggleExpansion(node);
        } catch (NumberFormatException ex) {
            // fail silent
        }
    }

    public void toggleExpansion(final KNode diagramElement) {
        controller.toggleExpansion(diagramElement);
    }
    
    public void zoomToFit() {
        controller.zoomToFit(0);    
    }

    /**
     * @return the canvas
     */
    public KlighdSVGCanvas getCanvas() {
        return canvas;
    }

    /**
     * @param currentResourcePath
     *            the currentResourcePath to set
     */
    public void setResourcePath(String currentResourcePath) {
        this.resourcePath = currentResourcePath;
    }

    /**
     * @param svgTransform
     *            the svgTransform to set
     */
    public void setSvgTransform(String svgTransform) {
        this.svgTransform = svgTransform;
    }

    /**
     * @return the svgTransform
     */
    public String getSvgTransform() {
        return svgTransform;
    }

    /*
     * ------------------------------------ Internal functionality.
     */

    /**
     * Adds a wrapper to all expandable nodes that is uniquely identifyable within the svg.
     */
    private void recurseNodes(PNode parent) {
        List<WrappedKNodeNode> toAdd = Lists.newLinkedList();

        for (int i = 0; i < parent.getChildrenCount(); i++) {
            PNode child = parent.getChild(i);
            recurseNodes(child);

            if (child instanceof KNodeNode) {
                WrappedKNodeNode wrapper =
                        new WrappedKNodeNode(child, ((KNodeNode) child).getGraphElement());
                toAdd.add(wrapper);
                child.addChild(wrapper);

                currentHashCodeMapping.put(((KNodeNode) child).getGraphElement().hashCode(),
                        ((KNodeNode) child).getGraphElement());
            }
        }
    }

    public String assemblePermaLink() {
        Set<String> expanded = new HashSet<String>();
        assemblePermaLink(getModel(), expanded);

        try {
            String joined = Joiner.on("$").join(expanded);
            String permaLink = resourcePath + "?perma=" + URLEncoder.encode(joined, "utf8");
            if (svgTransform != null) {
                permaLink += "&transform=" + URLEncoder.encode(svgTransform, "utf8");
            }

            return permaLink;
        } catch (UnsupportedEncodingException uee) {
            return null;
        }
    }

    private void assemblePermaLink(final KNode parent, final Set<String> expanded) {
        for (KNode node : parent.getChildren()) {

            if (!node.getChildren().isEmpty()) {
                assemblePermaLink(node, expanded);

                if (node != getModel() && controller.isExpanded(node)) {
                    expanded.add(EcoreUtil.getURI(node).toString());
                }
            }
        }
    }

    public boolean applyPermalink(final Set<String> expanded) {

        applyPermalink(getModel(), expanded);

        return expanded.isEmpty();
    }

    private void applyPermalink(final KNode parent, final Set<String> expanded) {

        for (KNode node : parent.getChildren()) {

            // possibly expand
            String thisFragment = EcoreUtil.getURI(node).toString();

            // System.out.println("Looking for: " + thisFragment + " contained: " +
            // expanded.contains(thisFragment));

            if (expanded.contains(thisFragment)) {
                System.out.println("Expanding: " + thisFragment + " contained: "
                        + expanded.contains(thisFragment));
                controller.expand(node);
                // controller.toggleExpansion(node);
                expanded.remove(thisFragment);
            }

            // controller.expand(node);
            // System.out.println(controller.isExpanded(node));

            // recurse
            if (!node.getChildren().isEmpty()) {
                applyPermalink(node, expanded);
            }
        }
    }

}
