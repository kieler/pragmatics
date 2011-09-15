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

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedList;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource.Diagnostic;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;

import de.cau.cs.kieler.kiml.graphviz.dot.dot.GraphvizModel;
import de.cau.cs.kieler.kiml.graphviz.dot.transformations.KGraphDotTransformation;
import de.cau.cs.kieler.kiml.graphviz.dot.transformations.KGraphDotTransformation.Command;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.core.WrappedException;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.util.ForkedOutputStream;
import de.cau.cs.kieler.core.util.ForwardingInputStream;
import de.cau.cs.kieler.core.util.Pair;

/**
 * Layouter that calls Graphviz through a child process to perform layout. The graph structure and
 * layout information is passed through a textual format called Dot, see the <a
 * href="http://www.graphviz.org/doc/info/lang.html">Dot language specification</a>. Serialization
 * and parsing of this textual format is done using <a
 * href="http://www.eclipse.org/modeling/tmf/">Xtext</a>.
 * 
 * @kieler.rating 2009-12-11 proposed yellow msp
 * @author ars
 * @author haf
 * @author msp
 * @author mri
 */
public class GraphvizLayouter {

    /** base file name for debug output. */
    private String debugFileName;

    /** amount of work for small tasks. */
    private static final int SMALL_TASK = 5;
    /** amount of work for large tasks. */
    private static final int LARGE_TASK = 10;

    /**
     * Performs the actual work of the layout process. Translates the KNode into a structure
     * GraphViz understands, calls the desired GraphViz layouter and annotates the KLayoutGraph with
     * the position and size information provided by GraphViz.
     * 
     * @param parentNode
     *            the node to process
     * @param progressMonitor
     *            a monitor to which progress is reported
     * @param command
     *            Graphviz command to use, determines the layout algorithm
     * @param graphvizTool
     *            a Graphviz tool instance for interaction with the process
     */
    public void layout(final KNode parentNode, final IKielerProgressMonitor progressMonitor,
            final Command command, final GraphvizTool graphvizTool) {
        progressMonitor.begin("Graphviz layout (" + command + ")", SMALL_TASK + LARGE_TASK + LARGE_TASK
                + LARGE_TASK + SMALL_TASK);
        boolean debugMode = parentNode.getData(KShapeLayout.class).getProperty(LayoutOptions.DEBUG_MODE);
        if (debugMode) {
            debugFileName = Integer.toString(parentNode.hashCode());
        }
        // return if there is nothing in this node
        if (parentNode.getChildren().isEmpty()) {
            progressMonitor.done();
            return;
        }

        // start the graphviz process, or retrieve the previously used process
        Process graphvizProcess = graphvizTool.startProcess(command);

        // create an Xtext resource set for parsing and serialization
        KGraphDotTransformation transformation = new KGraphDotTransformation(parentNode);
        XtextResourceSet resourceSet = transformation.createResourceSet();

        // translate the KGraph to Graphviz and write to the process
        GraphvizModel graphvizInput = transformation.transform(command,
                progressMonitor.subTask(SMALL_TASK));
        writeDotGraph(graphvizInput, new BufferedOutputStream(graphvizProcess.getOutputStream()),
                progressMonitor.subTask(LARGE_TASK), debugMode, graphvizTool, resourceSet);

        // wait for Graphviz to give some output
        graphvizTool.waitForInput(graphvizProcess.getInputStream(), graphvizProcess.getErrorStream(),
                progressMonitor.subTask(LARGE_TASK), debugMode);

        if (!progressMonitor.isCanceled()) {
            // read Graphviz output and apply layout information to the KGraph
            GraphvizModel graphvizOutput = readDotGraph(
                    new BufferedInputStream(graphvizProcess.getInputStream()),
                    progressMonitor.subTask(LARGE_TASK), debugMode, graphvizTool, resourceSet);
            transformation.applyLayout(graphvizOutput, progressMonitor.subTask(SMALL_TASK));
        }

        progressMonitor.done();
    }

    /**
     * Writes a serialized version of the Graphviz model to the given output stream.
     * 
     * @param graphvizModel
     *            Graphviz model to serialize
     * @param processStream
     *            output stream to which the model is written
     * @param monitor
     *            a monitor to which progress is reported
     * @param debugMode
     *            whether debug mode is active
     * @param graphvizTool
     *            Graphviz process handler
     * @param resourceSet
     *            the resource set for serialization
     */
    private void writeDotGraph(final GraphvizModel graphvizModel, final OutputStream processStream,
            final IKielerProgressMonitor monitor, final boolean debugMode,
            final GraphvizTool graphvizTool, final XtextResourceSet resourceSet) {
        monitor.begin("Serialize model", 1);
        OutputStream outputStream = processStream;
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
                debugStream = new FileOutputStream(new File(path + File.separator + debugFileName
                        + "-in.dot"));
                outputStream = new ForkedOutputStream(processStream, debugStream);
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
            graphvizTool.endProcess();
            throw new WrappedException(exception, "Failed to serialize the Dot graph.");
        } finally {
            if (debugStream != null) {
                try {
                    debugStream.close();
                } catch (IOException exception) {
                    // ignore exception
                }
            }
        }
        monitor.done();
    }

    /**
     * Reads and parses a serialized Graphviz model.
     * 
     * @param processStream
     *            input stream from which the model is read
     * @param monitor
     *            a monitor to which progress is reported
     * @param debugMode
     *            whether debug mode is active
     * @param graphvizTool
     *            Graphviz process handler
     * @param resourceSet
     *            the resoure set for parsing
     * @return an instance of the parsed graphviz model
     */
    private GraphvizModel readDotGraph(final InputStream processStream,
            final IKielerProgressMonitor monitor, final boolean debugMode,
            final GraphvizTool graphvizTool, final XtextResourceSet resourceSet) {
        monitor.begin("Parse output", 1);
        InputStream inputStream = processStream;
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
                debugStream = new FileOutputStream(new File(path + File.separator + debugFileName
                        + "-out.dot"));
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
            resource.load(getFilteredStream(inputStream), null);
        } catch (IOException exception) {
            graphvizTool.endProcess();
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
            graphvizTool.endProcess();
            throw new GraphvizException(errorString.toString());
        }
        GraphvizModel graphvizModel = (GraphvizModel) resource.getParseResult().getRootASTElement();
        if (graphvizModel.getGraphs().isEmpty()) {
            graphvizTool.endProcess();
            throw new GraphvizException("No output from the Graphviz process.");
        }

        monitor.done();
        return graphvizModel;
    }
    
    /** buffer size used for the filtered input stream. */
    private static final int BUFFER_SIZE = 256;
    
    /**
     * Read the whole input stream into a buffer and filter all line breaks from it.
     * 
     * @param inputStream an input stream
     * @return a new input stream that reads from a filtered buffer
     * @throws IOException if any read operation fails
     */
    private InputStream getFilteredStream(final InputStream inputStream) throws IOException {
        final LinkedList<Pair<byte[], Integer>> buffer = new LinkedList<Pair<byte[], Integer>>();
        while (inputStream.available() > 0) {
            byte[] bs = new byte[BUFFER_SIZE];
            int max = Math.min(bs.length, inputStream.available());
            int n = inputStream.read(bs, 0, max);
            buffer.add(new Pair<byte[], Integer>(bs, n));
        }
        return new InputStream() {
            private int p = 0;

            /**
             * Read the next character from the buffer.
             * 
             * @return the next character
             */
            private int internalRead() {
                if (buffer.isEmpty()) {
                    return -1;
                }
                return buffer.getFirst().getFirst()[p];
            }
            
            /**
             * Advance by one character in the buffer.
             */
            private void internalAdvance() {
                p++;
                if (p >= buffer.getFirst().getSecond()) {
                    buffer.removeFirst();
                    p = 0;
                }
            }
            
            /**
             * {@inheritDoc}
             */
            @Override
            public int read() throws IOException {
                int c = internalRead();
                if (c >= 0) {
                    internalAdvance();
                    if (c == '\\') {
                        int c2 = internalRead();
                        if (c2 == '\n' || c2 == '\r') {
                            internalAdvance();
                            if (c2 == '\r') {
                                int c3 = internalRead();
                                if (c3 == '\n') {
                                    internalAdvance();
                                }
                            }
                            c = internalRead();
                            if (c >= 0) {
                                internalAdvance();
                            }
                        }
                    }
                }
                return c;
            }
            
            /**
             * {@inheritDoc}
             */
            @Override
            public int available() {
                if (buffer.isEmpty()) {
                    return 0;
                }
                return buffer.getFirst().getSecond() - p;
            }
        };
    }
    
}
