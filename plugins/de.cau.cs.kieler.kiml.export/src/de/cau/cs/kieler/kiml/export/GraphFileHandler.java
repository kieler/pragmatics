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

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.elk.core.util.WrappedException;
import org.eclipse.elk.graph.ElkNode;

import de.cau.cs.kieler.kiml.formats.GraphFormatData;
import de.cau.cs.kieler.kiml.formats.GraphFormatsService;
import de.cau.cs.kieler.kiml.formats.IGraphFormatHandler;
import de.cau.cs.kieler.kiml.formats.IGraphTransformer;
import de.cau.cs.kieler.kiml.formats.TransformationData;

/**
 * This class is responsible for transforming and exporting graphs from graphical diagrams.
 * 
 * @author wah
 * @author msp
 * @kieler.ignore (excluded from review process)
 */
public class GraphFileHandler {

    /**
     * Perform the actual graph export.
     * 
     * @param elkgraph a graph
     * @param transHandler the transformation handler
     * @return the exported graph
     */
    private static <T> String performExport(final ElkNode elkgraph,
            final IGraphFormatHandler<T> transHandler) {
        
        TransformationData<ElkNode, T> transData = new TransformationData<ElkNode, T>();
        transData.setSourceGraph(elkgraph);
        IGraphTransformer<ElkNode, T> transformer = transHandler.getExporter();
        transformer.transform(transData);
        return transHandler.serialize(transData);
    }
    
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

    /**
     * @return the Workspace targetIPath
     */
    public IPath getWorkspaceTarget() {
        String sourceFileName = sourceFile.getFullPath().toFile().getName();
        String extension = targetFormat.getExtensions()[0];
        // get the last dot position
        int dotPos = sourceFileName.lastIndexOf(".");
        if (dotPos < 0) {
            dotPos = sourceFileName.length();
        }
        // replace the file extension with the new one
        return targetDirectory.append(sourceFileName.substring(0, dotPos) + "." + extension);
    }

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
            throw new WrappedException(e);
        }
    }

    /**
     * Call the performExport() method and return the string value from the graph.
     * 
     * @return the string value from the converted graph
     */
    public String graphToString() {
        ElkNode graph = retrieveGraph();
        return performExport(graph, targetFormat.getHandler());
    }

}
