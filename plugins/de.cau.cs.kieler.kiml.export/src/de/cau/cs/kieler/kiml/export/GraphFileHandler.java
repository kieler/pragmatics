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
package de.cau.cs.kieler.kiml.export;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.elk.core.util.WrappedException;
import org.eclipse.elk.graph.ElkEdge;
import org.eclipse.elk.graph.ElkGraphFactory;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.elk.graph.util.ElkGraphUtil;
import org.eclipse.elk.graph.util.GraphIdentifierGenerator;

import de.cau.cs.kieler.formats.GraphFormatData;
import de.cau.cs.kieler.formats.GraphFormatsService;
import de.cau.cs.kieler.formats.IGraphFormatHandler;
import de.cau.cs.kieler.formats.IGraphTransformer;
import de.cau.cs.kieler.formats.TransformationData;

/**
 * This class is responsible for transforming and exporting graphs from graphical diagrams.
 * 
 * @author wah
 * @author msp
 * @kieler.ignore (excluded from review process)
 */
public class GraphFileHandler {

    /** textual graph file extension. */
    private static final String EXT_ELK_TEXT = "elkt";
    
    /** The source file to export.  */
    private IFile sourceFile;
    /** The target format to export file into. */
    private GraphFormatData targetFormat;
    /** The target directory to export file into. */
    private IPath targetDirectory;

    /**
     * 
     * @param sourceFile
     *            the source file
     * @param targetFormat
     *            the target format
     * @param targetDirectory
     *            the target directory
     */
    public GraphFileHandler(final IFile sourceFile, final GraphFormatData targetFormat,
            final IPath targetDirectory) {
        super();
        this.sourceFile = sourceFile;
        this.targetFormat = targetFormat;
        this.targetDirectory = targetDirectory;
    }
    
    
    //////////////////////////////////////////////////////////////////////////////////////////////
    // Getters
    
    /**
     * @return the source file.
     */
    public IFile getSourceFile() {
        return sourceFile;
    }

    /**
     * @return the targetFormat
     */
    public GraphFormatData getTargetFormat() {
        return targetFormat;
    }

    /**
     * @return the targetDirectory
     */
    public IPath getWorkspaceTargetDirectory() {
        return targetDirectory;
    }
    
    
    //////////////////////////////////////////////////////////////////////////////////////////////
    // Graph Import and Export

    /**
     * Retrieve a graph from the selected file.
     * 
     * @return the ELK Graph
     */
    private ElkNode retrieveGraph() {
        try {
            ElkNode[] graphs = GraphFormatsService.getInstance().loadElkGraph(sourceFile);
            if (graphs.length == 0) {
                throw new IllegalArgumentException(
                        "The selected file does not contain a supported format.");
            }
            return graphs[0];
        } catch (IOException | CoreException e) {
            throw new WrappedException("Error while loading " + sourceFile.getName(), e);
        }
    }
    
    /**
     * Perform the actual graph export.
     * 
     * @param elkgraph a graph
     * @param transHandler the transformation handler
     * @return the exported graph
     */
    private <T> String performExport(final ElkNode elkgraph,
            final IGraphFormatHandler<T> transHandler) {
        
        TransformationData<ElkNode, T> transData = new TransformationData<ElkNode, T>();
        transData.setSourceGraph(elkgraph);
        IGraphTransformer<ElkNode, T> transformer = transHandler.getExporter();
        transformer.transform(transData);
        return transHandler.serialize(transData);
    }
    
    /**
     * Ensures the graph has unique identifiers so that it can be properly serialized into an ELKT
     * file, if that is in fact our target format.
     */
    private void ensureElktCompatibility(final ElkNode graph) {
        String extension = targetFormat.getExtensions()[0];
        if (extension.equals(EXT_ELK_TEXT)) {
            // we want to convert to the textual format, so write missing identifiers into the graph
            GraphIdentifierGenerator.forGraph(graph)
                .assertExists()
                .assertUnique()
                .execute();
        }
    }
    
    
    //////////////////////////////////////////////////////////////////////////////////////////////
    // Main Interface Methods

    /**
     * Call the performExport() method and return the string value from the graph.
     * 
     * @return the string that represents the exported graph.
     */
    public String graphToString() {
        ElkNode graph = retrieveGraph();
        ensureElktCompatibility(graph);
        return performExport(graph, targetFormat.getHandler());
    }
    
    /**
     * Call the {@link #performExport(ElkNode, IGraphFormatHandler)} method on each hierarchy level
     * of the graph and return the exported String for each to be saved in separate files.
     * 
     * @param filterLevelsWithoutEdges
     *            if {@code true}, hierarchy levels that only have nodes without any edges
     *            connecting them are filtered out.
     * @param filterLevelsWithoutEdgeLabels
     *            if {@code true}, hierarchy levels that don't have edge labels are filtered out.
     * @param filterSelfLoops
     *            if {@code true}, self loops are removed from the graphs.
     * @param minNodeCount
     *            the minimum number of nodes a graph must have for it to be exported.
     * @return the strings that represent the exported hierarchy levels.
     */
    public String[] hierarchyGraphsToStrings(final boolean filterLevelsWithoutEdges,
            final boolean filterLevelsWithoutEdgeLabels, final boolean filterSelfLoops,
            final int minNodeCount) {
        
        ElkNode graph = retrieveGraph();
        ensureElktCompatibility(graph);
        
        List<String> graphStrings = new ArrayList<>();
        for (ElkNode level : toHierarchyGraphs(graph)) {
            // Check the filter conditions
            if (level.getChildren().size() < minNodeCount) {
                continue;
            }
            
            if (filterSelfLoops) {
                removeSelfLoops(level);
            }
            
            if (level.getContainedEdges().isEmpty() && filterLevelsWithoutEdges) {
                continue;
            }
            
            if (filterLevelsWithoutEdgeLabels && !hasEdgeLabels(level)) {
                continue;
            }
            
            graphStrings.add(performExport(level, targetFormat.getHandler()));
        }
        
        return graphStrings.toArray(new String[graphStrings.size()]);
    }
    
    private boolean hasEdgeLabels(final ElkNode graph) {
        return graph.getContainedEdges().stream()
                .anyMatch(edge -> !edge.getLabels().isEmpty());
    }
    
    
    //////////////////////////////////////////////////////////////////////////////////////////////
    // Graph Flattening
    
    /**
     * Splits the given graph into its separate hierarchy levels. Removes any edges that cross
     * hierarchy levels.
     */
    private List<ElkNode> toHierarchyGraphs(final ElkNode graph) {
        // The current node is a hierarchical node. Copy it and move its children to the copy
        ElkNode graphCopy = copyGraph(graph);
        
        List<ElkNode> result = new ArrayList<>();
        result.add(graphCopy);
        
        // Recurse into children that are compound nodes as well
        for (ElkNode child : graphCopy.getChildren()) {
            if (child.isHierarchical()) {
                result.addAll(toHierarchyGraphs(child));
            }
        }
        
        return result;
    }
    
    /**
     * Makes a copy of the given graph node. Copies properties and moves child nodes and contained
     * edges to the copy (the latter only if the edges are not hierarchical).
     */
    private ElkNode copyGraph(final ElkNode graph) {
        ElkNode copy = ElkGraphFactory.eINSTANCE.createElkNode();
        copy.copyProperties(graph);
        
        // Move children over to the copy
        ElkNode[] children = graph.getChildren().toArray(new ElkNode[0]);
        for (ElkNode child : children) {
            child.setParent(copy);
        }
        
        // Move contained edges over as well, but kill them if they are hierarchical
        ElkEdge[] edges = graph.getContainedEdges().toArray(new ElkEdge[0]);
        for (ElkEdge edge : edges) {
            if (edge.isHyperedge()) {
                killEdge(edge);
            } else {
                // Check which graph the source and target are contained in
                ElkNode srcGraph = ElkGraphUtil.containingGraph(edge.getSources().get(0));
                ElkNode tgtGraph = ElkGraphUtil.containingGraph(edge.getTargets().get(0));
                
                if (srcGraph == tgtGraph && srcGraph == copy) {
                    edge.setContainingNode(copy);
                } else {
                    killEdge(edge);
                }
            }
        }
        
        return copy;
    }
    
    /**
     * Removes the given edge from the graph.
     */
    private void killEdge(final ElkEdge edge) {
        edge.setContainingNode(null);
        edge.getSources().clear();
        edge.getTargets().clear();
    }
    
    /**
     * Removes self loops from the given graph.
     */
    private void removeSelfLoops(final ElkNode graph) {
        ElkEdge[] edges = graph.getContainedEdges().toArray(new ElkEdge[0]);
        for (ElkEdge edge : edges) {
            if (edge.isSelfloop()) {
                killEdge(edge);
            }
        }
    }

}
