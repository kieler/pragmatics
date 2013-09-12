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

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLFilter;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.AttributesImpl;
import org.xml.sax.helpers.XMLFilterImpl;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

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
    private String resourceChecksum;

    /**
     * 
     */
    public SVGBrowsingViewer() {
        // FIXME normally we would like to render text as path, however, this is not possible due to
        // the way we pass ids to the expandable elements
        canvas = new KlighdSVGCanvas(false);
    }

    /**
     * @return the rendered SVG of the current model within the viewport.
     */
    public String render() {

        // wrap all expandable nodes
        if (controller != null) {
            currentHashCodeMapping.clear();
            recursivelyWrapKNodeNodes(controller.getNode());
        }

        // render
        String rendered = canvas.render();

        // post process the textual hashcodes to allow expansion of hierarchical elements
        String expandifized = expandifySVG(rendered);

        return expandifized;
    }

    /**
     * Sets a new model for this viewer.
     * 
     * @param model
     *            the new model
     * @param sync
     *            whether to sync in the {@link DiagramController}.
     */
    public void setModel(final KNode model, final boolean sync) {
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

    /**
     * @return the top level {@link KNode} of the model.
     */
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

    /**
     * Toggles the expansion state of the passed element.
     * 
     * @param diagramElement
     *            the element to toggle.
     */
    public void toggleExpansion(final KNode diagramElement) {
        controller.toggleExpansion(diagramElement);
    }

    /**
     * Zooms and translates the current model such that it fits into the current viewport.
     */
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
    public void setResourcePath(final String currentResourcePath) {
        this.resourcePath = currentResourcePath;
    }

    /**
     * @param svgTransform
     *            the svgTransform to set
     */
    public void setSvgTransform(final String svgTransform) {
        this.svgTransform = svgTransform;
    }

    /**
     * @return the svgTransform
     */
    public String getSvgTransform() {
        return svgTransform;
    }
    
    /**
     * @param resourceLastModified the resourceLastModified to set
     */
    public void setResourceChecksum(final String resourceLastModified) {
        this.resourceChecksum = resourceLastModified;
    }
    
    /*------------------------------------
     * Internal functionality.
     */

    /**
     * Adds a wrapper to all expandable nodes that is uniquely identifiable within the svg.
     */
    private void recursivelyWrapKNodeNodes(final PNode parent) {

        // if already wrapped dont wrap it again, but remember it in the hash map
        if ((parent instanceof WrappedKNodeNode)) {
            WrappedKNodeNode wrapper = (WrappedKNodeNode) parent;
            KNode wrappedGraphElement = wrapper.getKnodeNode().getGraphElement();
            currentHashCodeMapping.put(wrappedGraphElement.hashCode(), wrappedGraphElement);

            // recurse the children
            for (int i = 0; i < wrapper.getKnodeNode().getChildrenCount(); i++) {
                PNode child = wrapper.getKnodeNode().getChild(i);
                recursivelyWrapKNodeNodes(child);
            }

        } else {
            // newly wrap the children
            List<WrappedKNodeNode> toAdd = Lists.newLinkedList();

            for (int i = 0; i < parent.getChildrenCount(); i++) {
                PNode child = parent.getChild(i);
                recursivelyWrapKNodeNodes(child);

                // only handle KNodeNodes as they can contain children
                if (child instanceof KNodeNode) {
                    KNodeNode knodenode = (KNodeNode) child;
                    // if there are any children
                    if (knodenode.getGraphElement().getChildren().size() > 0) {

                        // wrap it
                        WrappedKNodeNode wrapper = new WrappedKNodeNode(knodenode);
                        toAdd.add(wrapper);

                        // place it in the hash map
                        currentHashCodeMapping.put(knodenode.hashCode(),
                                knodenode.getGraphElement());
                    }

                }
                for (WrappedKNodeNode pair : toAdd) {
                    parent.addChild(pair);
                }
            }

        }

    }

    /**
     * @return an permalink that represents the current state of the viewer. This is the current
     *         state of expanded/collapsed nodes and the viewer's viewport.
     */
    public String assemblePermaLink() {
        Set<String> expanded = new HashSet<String>();
        assemblePermaLink(getModel(), expanded);

        try {
            String joined = Joiner.on("$").join(expanded);
            String permaLink = resourcePath + "?perma=" + URLEncoder.encode(joined, "utf8");
            if (svgTransform != null) {
                permaLink += "&transform=" + URLEncoder.encode(svgTransform, "utf8");
            }
            
            // add checksum
            permaLink += "&cs=" + resourceChecksum;

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

    /**
     * Applies a perma link.
     * 
     * @param expanded
     *            a $ separated string containing references to the expanded elements.
     * @param transform
     *            the specified transform for the SVG.
     * 
     * 
     * @return true if all specified elements were expanded
     */
    public boolean applyPermalink(final String expanded, final String transform) {

        // transform (it will be added automatically to the SVG during post processing in the
        // SVGLayoutProvider).
        setSvgTransform(transform);

        // expand
        Set<String> fragments = Sets.newHashSet(Splitter.on("$").split(expanded));
        applyPermalink(getModel(), fragments);

        return expanded.isEmpty();
    }

    private void applyPermalink(final KNode parent, final Set<String> expanded) {

        for (KNode node : parent.getChildren()) {

            // possibly expand
            String thisFragment = EcoreUtil.getURI(node).toString();

            if (expanded.contains(thisFragment)) {
                System.out.println("Expanding: " + thisFragment + " contained: "
                        + expanded.contains(thisFragment));

                controller.expand(node);
                expanded.remove(thisFragment);
            }

            // recurse
            if (!node.getChildren().isEmpty()) {
                applyPermalink(node, expanded);
            }
        }
    }

    /*--------------------------------------------------------
     * Filter mechanisms to make the SVG expandable 
     */

    // Initialize SAX factories
    private static SAXTransformerFactory factory = (SAXTransformerFactory) SAXTransformerFactory
            .newInstance();
    private static SAXParserFactory spf = SAXParserFactory.newInstance();

    static {
        spf.setNamespaceAware(false);
        spf.setValidating(false);
        try {
            spf.setFeature("http://xml.org/sax/features/namespaces", false);
            spf.setFeature("http://xml.org/sax/features/validation", false);
            spf.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
            spf.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String expandifySVG(final String svg) {

        try {
            // prepare an outputstream
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            BufferedOutputStream bos = new BufferedOutputStream(baos);

            // set up parser and serializer
            SAXParser parser = spf.newSAXParser();
            XMLReader reader = parser.getXMLReader();

            TransformerHandler serializer = factory.newTransformerHandler();
            serializer.setResult(new StreamResult(bos));

            // create the filter
            XMLFilter filter = new ExpandifierFilter();
            filter.setContentHandler(serializer);
            filter.setParent(reader);

            // prepare an input stream and parse
            BufferedInputStream bais =
                    new BufferedInputStream(new ByteArrayInputStream(svg.getBytes()));
            filter.parse(new InputSource(bais));

            // retrieve the result
            String s = new String(baos.toByteArray());

            return s;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * The filter looks for an SVG text element that starts with {@link WrappedKNodeNode#ID_TEXT}.
     * The element will be omitted while the specified it is added as an <code>data-id</code>
     * attribute to the <b>next</b> element within the SVG. Furthermore, the next element will get
     * an <code>class</code> attribute with the value <code>expandable</code>.
     * 
     * @author uru
     */
    private static class ExpandifierFilter extends XMLFilterImpl {

        private boolean textMarker = false;
        private String nextId = null;

        @Override
        public void startElement(final String uri, final String localName, final String qName,
                final Attributes atts) throws SAXException {

            // if we found an id, add it to the element's attributes
            if (nextId != null) {
                AttributesImpl impl = new AttributesImpl(atts);
                impl.addAttribute(uri, localName, "data-id", "string", nextId);
                impl.addAttribute(uri, localName, "class", "string", "expandable");

                nextId = null;

                super.startElement(uri, localName, qName, impl);
                return;

            } else {

                // check for id texts
                if (qName.equals("text")) {
                    textMarker = true;
                }
            }

            super.startElement(uri, localName, qName, atts);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void characters(final char[] ch, final int start, final int length)
                throws SAXException {

            if (textMarker) {
                // check if the current text marks an expandable node
                String currentText = new String(Arrays.copyOfRange(ch, start, start + length));
                if (!Strings.isNullOrEmpty(currentText.trim())) {
                    if (currentText.trim().startsWith(WrappedKNodeNode.ID_TEXT)) {
                        // found one!! add the id as property
                        nextId = currentText.split(":")[1];
                    }
                }
            }

            super.characters(ch, start, length);
        }

        @Override
        public void endElement(final String uri, final String localName, final String qName)
                throws SAXException {
            super.endElement(uri, localName, qName);
            textMarker = false;
        }
    }

}
