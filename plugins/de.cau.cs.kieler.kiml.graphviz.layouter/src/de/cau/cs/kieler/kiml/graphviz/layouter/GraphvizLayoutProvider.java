/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2008 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.graphviz.layouter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource.Diagnostic;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;

import de.cau.cs.kieler.core.WrappedException;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.util.ForkedOutputStream;
import de.cau.cs.kieler.core.util.ForwardingInputStream;
import de.cau.cs.kieler.kiml.AbstractLayoutProvider;
import de.cau.cs.kieler.kiml.graphviz.dot.dot.GraphvizModel;
import de.cau.cs.kieler.kiml.graphviz.dot.transform.Command;
import de.cau.cs.kieler.kiml.graphviz.dot.transform.DotExporter;
import de.cau.cs.kieler.kiml.graphviz.dot.transform.DotHandler;
import de.cau.cs.kieler.kiml.graphviz.layouter.GraphvizTool.Cleanup;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.service.formats.TransformationData;

/**
 * Layout provider for the Graphviz layout tool.
 * The actual Graphviz layout that is applied is determined by the parameter
 * passed in the {@link #initialize(String)} method.
 * 
 * @kieler.rating 2009-12-11 proposed yellow msp
 * @author msp
 */
public class GraphvizLayoutProvider extends AbstractLayoutProvider {

    /** the serial call number for usage in debug mode. */
    private static int serialCallNo = 0;
    
    /** command passed to the layouter. */
    private Command command = Command.INVALID;
    /** the Graphviz process pool. */
    private GraphvizTool graphvizTool;
    /** the Graphviz Dot format handler. */
    private DotHandler dotHandler = new DotHandler();
    /** the call number for the current execution. */
    private int myCallNo;

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(final String parameter) {
        command = Command.valueOf(parameter);
        graphvizTool = new GraphvizTool(command);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void dispose() {
        graphvizTool.cleanup(Cleanup.STOP);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doLayout(final KNode parentNode,
            final IKielerProgressMonitor progressMonitor) {
        if (command == Command.INVALID) {
            throw new IllegalStateException("The Graphviz layout provider is not initialized.");
        }
        progressMonitor.begin("Graphviz layout (" + command + ")", 2);
        if (parentNode.getChildren().isEmpty()) {
            // return if there is nothing in this node
            progressMonitor.done();
            return;
        }
        boolean debugMode = parentNode.getData(KShapeLayout.class)
                .getProperty(LayoutOptions.DEBUG_MODE);
        myCallNo = ++serialCallNo;

        // start the graphviz process, or retrieve the previously used process
        graphvizTool.initialize();

        // create an Xtext resource set for parsing and serialization
        XtextResourceSet resourceSet = (XtextResourceSet) dotHandler.createResourceSet();

        // translate the KGraph to Graphviz and write to the process
        TransformationData<KNode, GraphvizModel> transData
                = new TransformationData<KNode, GraphvizModel>();
        transData.setSourceGraph(parentNode);
        transData.setProperty(DotExporter.USE_EDGE_IDS, true);
        transData.setProperty(DotExporter.FULL_EXPORT, false);
        transData.setProperty(DotExporter.COMMAND, command);
        dotHandler.getExporter().transform(transData);
        GraphvizModel graphvizInput = transData.getTargetGraphs().get(0);
        writeDotGraph(graphvizInput, progressMonitor.subTask(1), debugMode, resourceSet);

        try {
            // read Graphviz output and apply layout information to the KGraph
            GraphvizModel graphvizOutput = readDotGraph(progressMonitor.subTask(1),
                    debugMode, resourceSet);
            transData.getTargetGraphs().set(0, graphvizOutput);
            dotHandler.getExporter().transferLayout(transData);
        } finally {
            graphvizTool.cleanup(Cleanup.NORMAL);
            progressMonitor.done();
        }
    }

    /**
     * Writes a serialized version of the Graphviz model to the given output stream.
     * 
     * @param graphvizModel
     *            Graphviz model to serialize
     * @param monitor
     *            a monitor to which progress is reported
     * @param debugMode
     *            whether debug mode is active
     * @param resourceSet
     *            the resource set for serialization
     */
    private void writeDotGraph(final GraphvizModel graphvizModel,
            final IKielerProgressMonitor monitor, final boolean debugMode,
            final XtextResourceSet resourceSet) {
        monitor.begin("Serialize model", 1);
        OutputStream outputStream = graphvizTool.input();
        // enable debug output if needed
        FileOutputStream debugStream = null;
        if (debugMode) {
            try {
                String path = System.getProperty("user.home");
                if (path.endsWith(File.separator)) {
                    path += "tmp" + File.separator + "graphviz";
                } else {
                    path += File.separator + "tmp" + File.separator + "graphviz";
                }
                new File(path).mkdirs();
                debugStream = new FileOutputStream(new File(path + File.separator
                        + debugFileBase() + "-in.dot"));
                outputStream = new ForkedOutputStream(outputStream, debugStream);
            } catch (Exception exception) {
                System.out.println("GraphvizLayouter: Could not initialize debug output: "
                        + exception.getMessage());
            }
        }

        try {
            XtextResource resource = (XtextResource) resourceSet.createResource(URI
                    .createURI("process.graphviz_dot"));
            resource.getContents().add(graphvizModel);
            resource.save(outputStream, null);
            outputStream.write('\n');
            outputStream.flush();
        } catch (IOException exception) {
            graphvizTool.cleanup(Cleanup.ERROR);
            throw new WrappedException(exception, "Failed to send the graph to Graphviz.");
        } finally {
            if (debugStream != null) {
                try {
                    debugStream.close();
                } catch (IOException exception) {
                    // ignore exception
                }
            }
            monitor.done();
        }
    }

    /**
     * Reads and parses a serialized Graphviz model.
     * 
     * @param monitor
     *            a monitor to which progress is reported
     * @param debugMode
     *            whether debug mode is active
     * @param resourceSet
     *            the resoure set for parsing
     * @return an instance of the parsed graphviz model
     */
    private GraphvizModel readDotGraph(final IKielerProgressMonitor monitor,
            final boolean debugMode, final XtextResourceSet resourceSet) {
        monitor.begin("Parse output", 1);
        InputStream inputStream = graphvizTool.output();
        // enable debug output if needed
        FileOutputStream debugStream = null;
        if (debugMode) {
            try {
                String path = System.getProperty("user.home");
                if (path.endsWith(File.separator)) {
                    path += "tmp" + File.separator + "graphviz";
                } else {
                    path += File.separator + "tmp" + File.separator + "graphviz";
                }
                new File(path).mkdirs();
                debugStream = new FileOutputStream(new File(path + File.separator
                        + debugFileBase() + "-out.dot"));
                inputStream = new ForwardingInputStream(inputStream, debugStream);
            } catch (Exception exception) {
                System.out.println("GraphvizLayouter: Could not initialize debug output: "
                        + exception.getMessage());
            }
        }

        // parse the output stream of the dot process
        XtextResource resource = (XtextResource) resourceSet.createResource(URI
                .createURI("process.graphviz_dot"));
        try {
            resource.load(inputStream, null);
        } catch (IOException exception) {
            graphvizTool.cleanup(Cleanup.ERROR);
            throw new WrappedException(exception, "Failed to read Graphviz output.");
        } finally {
            if (debugStream != null) {
                try {
                    debugStream.close();
                } catch (IOException exception) {
                    // ignore exception
                }
            }
        }

        // analyze errors and retrieve parse result
        if (!resource.getErrors().isEmpty()) {
            StringBuilder errorString = new StringBuilder("Errors in Graphviz output:");
            for (Diagnostic diagnostic : resource.getErrors()) {
                errorString.append("\n" + diagnostic.getLine() + ": " + diagnostic.getMessage());
            }
            graphvizTool.cleanup(Cleanup.ERROR);
            throw new GraphvizException(errorString.toString());
        }
        GraphvizModel graphvizModel = (GraphvizModel) resource.getParseResult().getRootASTElement();
        if (graphvizModel == null || graphvizModel.getGraphs().isEmpty()) {
            graphvizTool.cleanup(Cleanup.ERROR);
            throw new GraphvizException("No output from the Graphviz process."
                    + " Try increasing the timeout value in the preferences"
                    + " (KIELER / Layout / Graphviz).");
        }

        monitor.done();
        return graphvizModel;
    }
    
    /**
     * Return the base name for debug files.
     * 
     * @return the base name for debug files
     */
    private String debugFileBase() {
        String no = Integer.toString(myCallNo);
        switch (no.length()) {
        case 1:
            return "debug00" + no;
        case 2:
            return "debug0" + no;
        default:
            return "debug" + no;
        }
    }
    
}
