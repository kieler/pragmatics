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
package de.cau.cs.kieler.kiml.export.exporter;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import de.cau.cs.kieler.core.WrappedException;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.MapPropertyHolder;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.kiml.export.AbstractExporter;
import de.cau.cs.kieler.kiml.export.ExporterOption;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;

/**
 * A graph exporter for the GML file format.
 * 
 * @author mri
 */
public class GMLExporter extends AbstractExporter {

    /** the supported file extensions. */
    private static final String[] SUPPORTED_FILE_EXTENSIONS = { "gml" }; //$NON-NLS-1$
    /** the indentation used in the gml file. */
    private static final String INDENTATION = "    "; //$NON-NLS-1$
    private static final String INDENTATION2 = INDENTATION + INDENTATION;
    private static final String INDENTATION3 = INDENTATION2 + INDENTATION;
    private static final String INDENTATION4 = INDENTATION3 + INDENTATION;
    private static final String INDENTATION5 = INDENTATION4 + INDENTATION;

    /** the option for the include of layout information. */
    private static final ExporterOption<Boolean> OPTION_LAYOUT_INFORMATION =
            new ExporterOption<Boolean>("gml.layoutInformation", //$NON-NLS-1$
                    Messages.GMLExporter_include_layout_info_message, true);

    /**
     * Constructs the GML exporter.
     */
    public GMLExporter() {
        addOption(OPTION_LAYOUT_INFORMATION);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return Messages.GMLExporter_gml_name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDefaultExtension() {
        return SUPPORTED_FILE_EXTENSIONS[0];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String[] getExtensions() {
        return SUPPORTED_FILE_EXTENSIONS;
    }

    /**
     * {@inheritDoc}
     */
    public void doExport(final KNode graph, final OutputStream stream,
            final MapPropertyHolder options, final IKielerProgressMonitor monitor) {
        monitor.begin(Messages.GMLExporter_export_kgraph_to_gml, 1);

        try {
            serializeKGraphAsGML(graph, stream, options.getProperty(OPTION_LAYOUT_INFORMATION),
                    monitor.subTask(1));

        } catch (IOException e) {
            throw new WrappedException(e, ERROR_MESSAGE_EXPORT_FAILED);
        }

        monitor.done();
    }

    private static final IProperty<Offset> OFFSET_PROPERTY = new Property<Offset>(
            "de.cau.cs.kieler.kiml.export.offsetProperty", //$NON-NLS-1$
            new Offset());

    /**
     * Serializes the given KGraph using GML.
     * 
     * @param monitor
     *            the progress monitor
     * @param graph
     *            the graph
     * @param outputStream
     *            the output stream
     * @param layoutInformation
     *            true if the serialized graph should contain layout information
     * @throws IOException
     *             thrown when an io operation failed
     */
    public static void serializeKGraphAsGML(final KNode graph, final OutputStream outputStream,
            final boolean layoutInformation, final IKielerProgressMonitor monitor)
            throws IOException {
        monitor.begin(Messages.GMLExporter_serialize_kgraph_as_gml_task, 2);
        OutputStreamWriter writer = new OutputStreamWriter(outputStream, "UTF-8"); //$NON-NLS-1$
        // serialize
        int currentNodeId = 0;
        Map<KNode, Integer> nodeIds = new HashMap<KNode, Integer>();
        writer.write("graph [\n"); //$NON-NLS-1$
        writer.write(INDENTATION + "hierarchic 1\n"); //$NON-NLS-1$
        // process nodes
        Stack<KNode> nodes = new Stack<KNode>();
        nodes.addAll(graph.getChildren());
        while (!nodes.isEmpty()) {
            writer.write(INDENTATION + "node [\n"); //$NON-NLS-1$
            KNode node = nodes.pop();
            int id = currentNodeId++;
            nodeIds.put(node, id);
            writer.write(INDENTATION2 + "id " + id + "\n"); //$NON-NLS-1$ //$NON-NLS-2$
            if (node.getLabels().size() > 0 && node.getLabels().get(0).getText().length() > 0) {
                writer.write(INDENTATION2 + "label \"" //$NON-NLS-1$
                        + node.getLabels().get(0).getText() + "\"\n"); //$NON-NLS-1$
            }
            // layout information
            if (layoutInformation) {
                KShapeLayout parentLayout = node.getParent().getData(KShapeLayout.class);
                KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
                Offset offset = parentLayout.getProperty(OFFSET_PROPERTY);
                writer.write(INDENTATION2 + "graphics [\n"); //$NON-NLS-1$
                writer.write(INDENTATION3 + "x " //$NON-NLS-1$
                        + (offset.getX() + nodeLayout.getXpos() + nodeLayout.getWidth() / 2)
                        + "\n"); //$NON-NLS-1$
                writer.write(INDENTATION3
                        + "y " //$NON-NLS-1$
                        + (offset.getY() + nodeLayout.getYpos() + nodeLayout.getHeight() / 2)
                        + "\n"); //$NON-NLS-1$
                writer.write(INDENTATION3 + "w " + nodeLayout.getWidth() 
                        + "\n"); //$NON-NLS-1$ //$NON-NLS-2$
                writer.write(INDENTATION3 + "h " + nodeLayout.getHeight() //$NON-NLS-1$
                        + "\n"); //$NON-NLS-1$
                writer.write(INDENTATION2 + "]\n"); //$NON-NLS-1$
                // attach offset information if the node is a compound node
                if (node.getChildren().size() > 0) {
                    Offset newOffset = new Offset(offset);
                    newOffset.addX(nodeLayout.getXpos());
                    newOffset.addY(nodeLayout.getYpos());
                    nodeLayout.setProperty(OFFSET_PROPERTY, newOffset);
                }
            }
            // is the node located inside a compound node?
            if (!node.getParent().equals(graph)) {
                writer.write(INDENTATION2 + "gid " //$NON-NLS-1$
                        + nodeIds.get(node.getParent()) + "\n"); //$NON-NLS-1$
            }
            // is the node a compound node?
            if (node.getChildren().size() > 0) {
                writer.write(INDENTATION2 + "isGroup 1\n"); //$NON-NLS-1$
            }
            writer.write(INDENTATION + "]\n"); //$NON-NLS-1$
            nodes.addAll(node.getChildren());
        }
        monitor.worked(1);
        // process edges
        nodes.addAll(graph.getChildren());
        while (!nodes.isEmpty()) {
            KNode node = nodes.pop();
            int sourceId = nodeIds.get(node);
            for (KEdge edge : node.getOutgoingEdges()) {
                writer.write(INDENTATION + "edge [\n"); //$NON-NLS-1$
                int targetId = nodeIds.get(edge.getTarget());
                writer.write(INDENTATION2 + "source " + sourceId + "\n"); //$NON-NLS-1$ //$NON-NLS-2$
                writer.write(INDENTATION2 + "target " + targetId + "\n"); //$NON-NLS-1$ //$NON-NLS-2$
                // layout information
                if (layoutInformation) {
                    KShapeLayout parentLayout = node.getParent().getData(KShapeLayout.class);
                    KEdgeLayout edgeLayout = edge.getData(KEdgeLayout.class);
                    Offset offset = parentLayout.getProperty(OFFSET_PROPERTY);
                    writer.write(INDENTATION2 + "graphics [\n"); //$NON-NLS-1$
                    writer.write(INDENTATION3 + "type \"line\"\n"); //$NON-NLS-1$
                    writer.write(INDENTATION3 + "Line [\n"); //$NON-NLS-1$
                    // source point
                    writer.write(INDENTATION4 + "point [\n"); //$NON-NLS-1$
                    writer.write(INDENTATION5 + "x " //$NON-NLS-1$
                            + (offset.getX() + edgeLayout.getSourcePoint().getX()) + "\n"); //$NON-NLS-1$
                    writer.write(INDENTATION5 + "y " //$NON-NLS-1$
                            + (offset.getY() + edgeLayout.getSourcePoint().getY()) + "\n"); //$NON-NLS-1$
                    writer.write(INDENTATION4 + "]\n"); //$NON-NLS-1$
                    // bend points
                    for (KPoint point : edgeLayout.getBendPoints()) {
                        writer.write(INDENTATION4 + "point [\n"); //$NON-NLS-1$
                        writer.write(INDENTATION5 + "x " //$NON-NLS-1$
                                + (offset.getX() + point.getX()) + "\n"); //$NON-NLS-1$
                        writer.write(INDENTATION5 + "y " //$NON-NLS-1$
                                + (offset.getY() + point.getY()) + "\n"); //$NON-NLS-1$
                        writer.write(INDENTATION4 + "]\n"); //$NON-NLS-1$
                    }
                    // target point
                    writer.write(INDENTATION4 + "point [\n"); //$NON-NLS-1$
                    writer.write(INDENTATION5 + "x " //$NON-NLS-1$
                            + (offset.getX() + edgeLayout.getTargetPoint().getX()) + "\n"); //$NON-NLS-1$
                    writer.write(INDENTATION5 + "y " //$NON-NLS-1$
                            + (offset.getY() + edgeLayout.getTargetPoint().getY()) + "\n"); //$NON-NLS-1$
                    writer.write(INDENTATION4 + "]\n"); //$NON-NLS-1$
                    writer.write(INDENTATION3 + "]\n"); //$NON-NLS-1$
                    writer.write(INDENTATION2 + "]\n"); //$NON-NLS-1$
                }
                writer.write(INDENTATION + "]\n"); //$NON-NLS-1$
            }
            nodes.addAll(node.getChildren());
        }
        writer.write("]\n"); //$NON-NLS-1$
        writer.close();
        monitor.done();
    }

    /**
     * A helper class for remembering node offsets.
     */
    private static class Offset {
        private float x = 0;
        private float y = 0;

        public Offset() {
            // do nothing
        }

        public Offset(final Offset offset) {
            x = offset.x;
            y = offset.y;
        }

        public float getX() {
            return x;
        }

        public float getY() {
            return y;
        }

        public void addX(final float dX) {
            x += dX;
        }

        public void addY(final float dY) {
            y += dY;
        }
    }
}
