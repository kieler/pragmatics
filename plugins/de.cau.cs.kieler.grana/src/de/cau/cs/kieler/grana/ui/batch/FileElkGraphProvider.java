/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.grana.ui.batch;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.elk.alg.layered.options.InternalProperties;
import org.eclipse.elk.core.IGraphLayoutEngine;
import org.eclipse.elk.core.LayoutConfigurator;
import org.eclipse.elk.core.RecursiveGraphLayoutEngine;
import org.eclipse.elk.core.util.BasicProgressMonitor;
import org.eclipse.elk.core.util.ElkUtil;
import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.core.util.WrappedException;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

import de.cau.cs.kieler.formats.GraphFormatsService;

/**
 * The provider that derives a graph from a file. 
 *
 * @author msp
 * @author uru
 * @kieler.ignore (excluded from review process)
 */
public class FileElkGraphProvider implements IElkGraphProvider<IPath> {

    /** the layout engine for graph layout. */
    private static IGraphLayoutEngine layoutEngine;
    
    /** the 'layout before analysis' option. */
    private boolean layoutBeforeAnalysis;
    /** whether to measure the execution times. */
    private boolean executionTimeAnalysis;
    /** the layout configurator to apply to each graph. */
    private LayoutConfigurator configurator;
    
    private static final int NO_EXEC_TIME_RUNS = 5;
    
    /**
     * {@inheritDoc}
     */
    public ElkNode getElkGraph(final IPath parameter, final IElkProgressMonitor monitor) {
        monitor.begin("Retrieving graph from " + parameter.toString(), 2);
        
        // try to load the file as a elk graph 
        // possibly converting a different format such as graphml
        ElkNode graph = null;
        try {

            String extension = parameter.getFileExtension();
            InputStream is;
            IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(parameter);
            if (file.exists()) {
                is = file.getContents();
            } else {
                // try as absolute path
                is = new FileInputStream(parameter.toFile());
            }
            
            ElkNode[] nodes = GraphFormatsService.getInstance().loadElkGraph(is, extension);
            // if a file contains multiple graphs, we consider only the first graph
            if (nodes.length > 0) {
                graph = nodes[0];
            }
        } catch (IOException ie) {
            // silent, try other options to load the kgraph
        } catch (CoreException ce) {
            // silent
        }

        EObject content = null;
        ResourceSet resourceSet = new ResourceSetImpl();
        
        if (graph == null) {
            // load the notation diagram element
            URI uri = URI.createPlatformResourceURI(parameter.toString(), true);

            final Resource resource = resourceSet.createResource(uri);
            try {
                resource.load(Collections.emptyMap());
            } catch (IOException e) {
                throw new WrappedException(e);
            }
            if (resource.getContents().isEmpty()) {
                throw new IllegalArgumentException("The selected file is empty.");
            }
            monitor.worked(1);
            content = resource.getContents().get(0);
        }

        if (graph != null || content instanceof ElkNode) {
            if (graph == null) {
                graph = (ElkNode) content;
            }
            
            if (layoutBeforeAnalysis) {
                if (configurator != null) {
                    ElkUtil.applyVisitors(graph, configurator);
                }
                
                if (layoutEngine == null) {
                    layoutEngine = new RecursiveGraphLayoutEngine();
                }
                
                // shall we perform execution time analysis?
                if (executionTimeAnalysis) {
                    // Do a bunch of layout runs and take the one that took the least amount of time
                    double minTime = Double.POSITIVE_INFINITY;
                    IElkProgressMonitor fastest = null;
                    ElkNode theGraph = graph;
                    for (int j = 0; j < NO_EXEC_TIME_RUNS; j++) {
                        
                        IElkProgressMonitor recLayoutMonitor = new BasicProgressMonitor();
                        
                        Thread t = new Thread() {
                            public void run() {
                                Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
                                // perform layout on a copy of the graph since the 
                                // layout algorithm may alter the graph's layout options
                                ElkNode copy = (ElkNode) EcoreUtil.copy(theGraph);
                                System.gc();
                                layoutEngine.layout(copy, recLayoutMonitor);
                                copy = null;
                                System.gc();
                            }
                        };
                        t.start();
                        try {
                            t.join();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } finally {
                            t = null;
                        }
                        
                        if (!recLayoutMonitor.getSubMonitors().isEmpty()) {
                            // we are interested in the top level progress monitor 
                            // of the recursive layout engine
                            IElkProgressMonitor layoutMonitor =
                                    recLayoutMonitor.getSubMonitors().get(0);
                            double time = layoutMonitor.getExecutionTime();
                            if (time < minTime) {
                                minTime = time;
                                fastest = layoutMonitor;
                            }
                        }
                    }
                    
                    // attach the results to the graph such that the 
                    // execution time analysis can print them
                    graph.setProperty(BatchHandler.EXECUTION_TIME_RESULTS, fastest);

                } else {
                    // plain layout - no hassle
                    graph.setProperty(InternalProperties.MODEL_NAME, parameter.toFile().toURI());
                    layoutEngine.layout(graph, monitor.subTask(1));
                }
            }
            
            monitor.done();
            return graph;
        } else {
            throw new IllegalArgumentException(
                    "The selected file does not contain a supported format.");
        }
    }

    /**
     * Sets the option which specifies whether layout should be performed before
     * the KGraph is returned.
     * 
     * @param layoutBeforeAnalysisOption true if layout should be performed
     */
    public void setLayoutBeforeAnalysis(final boolean layoutBeforeAnalysisOption) {
        layoutBeforeAnalysis = layoutBeforeAnalysisOption;
    }
    
    /**
     * @param executionTimeAnalysis the executionTimeAnalysis to set
     */
    public void setExecutionTimeAnalysis(final boolean executionTimeAnalysis) {
        this.executionTimeAnalysis = executionTimeAnalysis;
    }
    
    /**
     * Sets the layout configurator to apply to each graph.
     * 
     * @param configuratorOption the layout configurator
     */
    public void setLayoutConfigurator(final LayoutConfigurator configuratorOption) {
        this.configurator = configuratorOption;
    }
    
}
