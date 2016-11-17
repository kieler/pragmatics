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
package de.cau.cs.kieler.kiml.grana.ui.batch;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.elk.core.IGraphLayoutEngine;
import org.eclipse.elk.core.LayoutConfigurator;
import org.eclipse.elk.core.RecursiveGraphLayoutEngine;
import org.eclipse.elk.core.klayoutdata.KLayoutData;
import org.eclipse.elk.core.util.GraphDataUtil;
import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.core.util.IGraphElementVisitor;
import org.eclipse.elk.core.util.WrappedException;
import org.eclipse.elk.graph.KGraphElement;
import org.eclipse.elk.graph.KNode;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import com.google.common.collect.Iterators;
import com.google.common.collect.Maps;

import de.cau.cs.kieler.kiml.formats.GraphFormatsService;

/**
 * The KGraph provider that derives a graph from a file. It uses GMF services to open a
 * headless diagram, that is without an editor.
 *
 * @author msp
 * @author uru
 * @kieler.ignore (excluded from review process)
 */
public class FileKGraphProvider implements IKGraphProvider<IPath> {

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
    public KNode getKGraph(final IPath parameter, final IElkProgressMonitor monitor) {
        monitor.begin("Retrieving KGraph from " + parameter.toString(), 2);
        
        // try to load the file as a kgraph 
        // possibly converting a different format such as graphml
        KNode graph = null;
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
            
            KNode[] nodes = GraphFormatsService.getInstance().loadKGraph(is, extension);
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

        if (graph != null || content instanceof KNode) {
            if (graph == null) {
                graph = (KNode) content;
            }
            // assure that properties, stored in the model file, are loaded properly 
            GraphDataUtil.loadDataElements(graph, true);
            
            if (layoutBeforeAnalysis) {
                if (configurator != null) {
                    applyVisitors(graph, configurator);
                }
                
                if (layoutEngine == null) {
                    layoutEngine = new RecursiveGraphLayoutEngine();
                }
                
                // shall we perform execution time analysis?
                if (executionTimeAnalysis) {
                    // Do a bunch of layout runs and take the one that took the least amount of time
                    double minTime = Double.MAX_VALUE;
                    Map<String, Double> minPhaseTimes = Maps.newHashMap();
                    for (int j = 0; j < NO_EXEC_TIME_RUNS; j++) {
                        System.gc();
                        
                        IElkProgressMonitor recLayoutMonitor = monitor.subTask(1);
                        layoutEngine.layout(graph, recLayoutMonitor);

                        if (!recLayoutMonitor.getSubMonitors().isEmpty()) {
                            // we are interested in the top level progress monitor of the recursive
                            // layout engine
                            IElkProgressMonitor layoutMonitor =
                                    recLayoutMonitor.getSubMonitors().get(0);
                            double time = layoutMonitor.getExecutionTime();
                            
                            if (time < minTime) {
                                minTime = time;
                                for (IElkProgressMonitor task : layoutMonitor.getSubMonitors()) {
                                    minPhaseTimes.put(task.getTaskName(), task.getExecutionTime());
                                }
                            }
                        }
                    }
                    minPhaseTimes.put("Overall Execution Time", minTime);
                    
                    // attach the results to the graph such that the 
                    // execution time analysis can print them
                    graph.getData(KLayoutData.class).setProperty(
                            BatchHandler.EXECUTION_TIME_RESULTS, minPhaseTimes);

                } else {
                    // plain layout - no hassle
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

    /**
     * Apply the given graph element visitors to the content of the given graph.
     */
    private static void applyVisitors(final KNode graph, final IGraphElementVisitor... visitors) {
        for (int i = 0; i < visitors.length; i++) {
            visitors[i].visit(graph);
        }
        Iterator<KGraphElement> allElements =
                Iterators.filter(graph.eAllContents(), KGraphElement.class);
        while (allElements.hasNext()) {
            KGraphElement element = allElements.next();
            for (int i = 0; i < visitors.length; i++) {
                visitors[i].visit(element);
            }
        }
    }
    
}
