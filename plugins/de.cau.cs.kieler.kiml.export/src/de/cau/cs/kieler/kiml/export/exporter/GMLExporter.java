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

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.export.AbstractExporter;
import de.cau.cs.kieler.kiml.export.ExportUtil;
import de.cau.cs.kieler.kiml.export.ExporterConfiguration;
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
    private static final String[] SUPPORTED_FILE_EXTENSIONS = { "gml" };
    /** the indentation used in the gml file. */
    private static final String INDENTATION = "    ";
    private static final String INDENTATION2 = INDENTATION + INDENTATION;
    private static final String INDENTATION3 = INDENTATION2 + INDENTATION;
    private static final String INDENTATION4 = INDENTATION3 + INDENTATION;
    private static final String INDENTATION5 = INDENTATION4 + INDENTATION;

    /** the option for the include of layout information. */
    private static final ExporterOption<Boolean> OPTION_LAYOUT_INFORMATION =
            new ExporterOption<Boolean>("layoutInformation",
                    "Include layout information?", true);

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
        return "GML";
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
    @Override
    public void doExport(final IKielerProgressMonitor monitor,
            final ExporterConfiguration configuration, final KNode graph)
            throws KielerException {
        monitor.begin("Exporting KGraph to GML", 1);

        try {
            OutputStream outputStream =
                    ExportUtil.createOutputStream(
                            configuration.getExportFilePath(),
                            configuration.isWorkspacePath());
            serializeKGraphAsGML(monitor.subTask(1), graph, outputStream,
                    configuration.getProperty(OPTION_LAYOUT_INFORMATION));
            outputStream.close();

        } catch (IOException e) {
            throw new KielerException(ERROR_MESSAGE_EXPORT_FAILED, e);
        }

        monitor.done();
    }

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
    public static void serializeKGraphAsGML(
            final IKielerProgressMonitor monitor, final KNode graph,
            final OutputStream outputStream, final boolean layoutInformation)
            throws IOException {
        monitor.begin("Serializing KGraph as GML", 2);
        OutputStreamWriter writer =
                new OutputStreamWriter(outputStream, "UTF-8");
        // serialize
        int currentNodeId = 0;
        Map<KNode, Integer> nodeIds = new HashMap<KNode, Integer>();
        writer.write("graph [\n");
        // process nodes
        Stack<KNode> nodes = new Stack<KNode>();
        nodes.addAll(graph.getChildren());
        while (!nodes.isEmpty()) {
            writer.write(INDENTATION + "node [\n");
            KNode node = nodes.pop();
            int id = currentNodeId++;
            nodeIds.put(node, id);
            writer.write(INDENTATION2 + "id " + id + "\n");
            if (node.getLabel().getText().length() > 0) {
                writer.write(INDENTATION2 + "label \""
                        + node.getLabel().getText() + "\"\n");
            }
            // layout information
            if (layoutInformation) {
                KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
                writer.write(INDENTATION2 + "graphics [\n");
                writer.write(INDENTATION3 + "x "
                        + (nodeLayout.getXpos() + nodeLayout.getWidth() / 2)
                        + "\n");
                writer.write(INDENTATION3 + "y "
                        + (nodeLayout.getYpos() + nodeLayout.getHeight() / 2)
                        + "\n");
                writer.write(INDENTATION3 + "w " + nodeLayout.getWidth() + "\n");
                writer.write(INDENTATION3 + "h " + nodeLayout.getHeight()
                        + "\n");
                writer.write(INDENTATION2 + "]\n");
            }
            writer.write(INDENTATION + "]\n");
            nodes.addAll(node.getChildren());
        }
        monitor.worked(1);
        // process edges
        nodes.addAll(graph.getChildren());
        while (!nodes.isEmpty()) {
            KNode node = nodes.pop();
            int sourceId = nodeIds.get(node);
            for (KEdge edge : node.getOutgoingEdges()) {
                writer.write(INDENTATION + "edge [\n");
                int targetId = nodeIds.get(edge.getTarget());
                writer.write(INDENTATION2 + "source " + sourceId + "\n");
                writer.write(INDENTATION2 + "target " + targetId + "\n");
                // layout information
                if (layoutInformation) {
                    KEdgeLayout edgeLayout = edge.getData(KEdgeLayout.class);
                    writer.write(INDENTATION2 + "graphics [\n");
                    writer.write(INDENTATION3 + "type \"line\"\n");
                    writer.write(INDENTATION3 + "Line [\n");
                    // source point
                    writer.write(INDENTATION4 + "point [\n");
                    writer.write(INDENTATION5 + "x "
                            + edgeLayout.getSourcePoint().getX() + "\n");
                    writer.write(INDENTATION5 + "y "
                            + edgeLayout.getSourcePoint().getY() + "\n");
                    writer.write(INDENTATION4 + "]\n");
                    // bend points
                    for (KPoint point : edgeLayout.getBendPoints()) {
                        writer.write(INDENTATION4 + "point [\n");
                        writer.write(INDENTATION5 + "x " + point.getX() + "\n");
                        writer.write(INDENTATION5 + "y " + point.getY() + "\n");
                        writer.write(INDENTATION4 + "]\n");
                    }
                    // target point
                    writer.write(INDENTATION4 + "point [\n");
                    writer.write(INDENTATION5 + "x "
                            + edgeLayout.getTargetPoint().getX() + "\n");
                    writer.write(INDENTATION5 + "y "
                            + edgeLayout.getTargetPoint().getY() + "\n");
                    writer.write(INDENTATION4 + "]\n");
                    writer.write(INDENTATION3 + "]\n");
                    writer.write(INDENTATION2 + "]\n");
                }
                writer.write(INDENTATION + "]\n");
            }
            nodes.addAll(node.getChildren());
        }
        writer.write("]\n");
        writer.close();
        monitor.done();
    }
}
