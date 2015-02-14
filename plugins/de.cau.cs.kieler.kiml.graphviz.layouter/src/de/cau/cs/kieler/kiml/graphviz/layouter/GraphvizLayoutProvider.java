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
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource.Diagnostic;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.xtext.resource.SaveOptions;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;

import com.google.inject.Injector;

import de.cau.cs.kieler.core.WrappedException;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.util.ForkedOutputStream;
import de.cau.cs.kieler.core.util.ForwardingInputStream;
import de.cau.cs.kieler.kiml.AbstractLayoutProvider;
import de.cau.cs.kieler.kiml.formats.GraphFormatData;
import de.cau.cs.kieler.kiml.formats.GraphFormatsService;
import de.cau.cs.kieler.kiml.formats.IGraphFormatHandler;
import de.cau.cs.kieler.kiml.formats.TransformationData;
import de.cau.cs.kieler.kiml.graphviz.dot.GraphvizDotStandaloneSetup;
import de.cau.cs.kieler.kiml.graphviz.dot.dot.GraphvizModel;
import de.cau.cs.kieler.kiml.graphviz.dot.transform.Command;
import de.cau.cs.kieler.kiml.graphviz.dot.transform.DotExporter;
import de.cau.cs.kieler.kiml.graphviz.dot.transform.DotFormatHandler;
import de.cau.cs.kieler.kiml.graphviz.layouter.GraphvizTool.Cleanup;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;

/**
 * Layout provider for the Graphviz layout tool.
 * The actual Graphviz layout that is applied is determined by the parameter
 * passed in the {@link #initialize(String)} method.
 * 
 * @author msp
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow by msp
 */
public class GraphvizLayoutProvider extends AbstractLayoutProvider {

    /** preference constant for determining whether to reuse a single Graphviz process. */
    public static final String PREF_GRAPHVIZ_REUSE_PROCESS = "graphviz.reuseProcess";
    /** default setting of above defined preference. */
    public static final boolean REUSE_PROCESS_DEFAULT = true;

    /** the serial call number for usage in debug mode. */
    private static int serialCallNo = 0;
    
    /** command passed to the layouter. */
    private Command command = Command.INVALID;
    /** the Graphviz process pool. */
    private GraphvizTool graphvizTool;
    /** the Graphviz Dot format handler. */
    private DotFormatHandler dotHandler;
    /** the call number for the current execution. */
    private int myCallNo;
    /** the current configuration regarding the process handling. */
    private boolean reuseProcess = REUSE_PROCESS_DEFAULT;
    /** a corresponding pref change listener updating {@link #reuseProcess}. */
    private IPropertyChangeListener prefListener;
    /** lazily created injector for creating required format handlers if running outside of Eclipse. */
    private Injector injector;

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(final String parameter) {
        if (EclipseRuntimeDetector.isEclipseRunning()) {
            final IPreferenceStore store = GraphvizLayouterPlugin.getDefault().getPreferenceStore();
            reuseProcess = store.getBoolean(PREF_GRAPHVIZ_REUSE_PROCESS);
            prefListener = new IPropertyChangeListener() {
                
                public void propertyChange(final PropertyChangeEvent event) {
                   if (PREF_GRAPHVIZ_REUSE_PROCESS.equals(event.getProperty())) {
                       reuseProcess = ((Boolean) event.getNewValue()).booleanValue();
                   }
                }
            };
            store.addPropertyChangeListener(prefListener);
        } 
        command = Command.valueOf(parameter);
        graphvizTool = new GraphvizTool(command);
        
        // the dot format handler is indirectly fetched in order to ensure proper injection (if we're
        // inside Eclipse, use the GraphFormatsService to retrieve the handler; otherwise, use an
        // injector to retrieve an instance)
        IGraphFormatHandler<?> handler = null;
        if (EclipseRuntimeDetector.isEclipseRunning()) {
            GraphFormatData formatData = GraphFormatsService.getInstance().getFormatData(
                    DotFormatHandler.ID);
            if (formatData != null) {
                handler = formatData.getHandler();
            }
            
            if (handler instanceof DotFormatHandler) {
                dotHandler = (DotFormatHandler) handler;
            } else {
                throw new IllegalStateException("The Graphviz Dot language support is not available.");
            }
        } else {
            dotHandler = getInjector().getInstance(DotFormatHandler.class);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void dispose() {
        final GraphvizLayouterPlugin plugin = GraphvizLayouterPlugin.getDefault();

        // since during platform shutdown plug-ins will be stopped in reverse order of their dependencies
        //  'plugin' is likely to be 'null' when kiml.service calls 'dispose()' on the layout managers
        // in this case removing the preference change listener should be obsolete ;-)
        if (plugin != null && prefListener != null) {
            plugin.getPreferenceStore().removePropertyChangeListener(prefListener);
        }
        prefListener = null;

        graphvizTool.cleanup(Cleanup.STOP);
    }
    
    /**
     * Returns the injector, creating a new instance if none was created yet.
     * 
     * @return the injector.
     */
    private Injector getInjector() {
        if (injector == null) {
            injector = new GraphvizDotStandaloneSetup().createInjectorAndDoEMFRegistration();
        }
        
        return injector;
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
            graphvizTool.cleanup(reuseProcess ? Cleanup.NORMAL : Cleanup.STOP);
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
            XtextResource resource = (XtextResource) resourceSet.createResource(
                    URI.createURI("output.graphviz_dot"));
            resource.getContents().add(graphvizModel);
            
            /* KIPRA-1498
             * We disable formatting and validation when saving the resource. Enabling it lead to
             * possible ConcurrentModificationExceptions in Xtext.
             */
            Map<Object, Object> saveOptions =
                    SaveOptions.newBuilder().noValidation().getOptions().toOptionsMap();
            resource.save(outputStream, saveOptions);
            
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
        XtextResource resource = (XtextResource) resourceSet.createResource(
                URI.createURI("input.graphviz_dot"));
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
