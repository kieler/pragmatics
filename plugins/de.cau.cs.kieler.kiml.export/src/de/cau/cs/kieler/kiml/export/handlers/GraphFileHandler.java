/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.export.handlers;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.ui.OffscreenEditPartFactory;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.util.Maybe;
import de.cau.cs.kieler.kiml.service.TransformationService;
import de.cau.cs.kieler.kiml.service.formats.GraphFormatData;
import de.cau.cs.kieler.kiml.service.formats.IGraphTransformer;
import de.cau.cs.kieler.kiml.service.formats.ITransformationHandler;
import de.cau.cs.kieler.kiml.service.formats.TransformationData;
import de.cau.cs.kieler.kiml.ui.diagram.LayoutMapping;
import de.cau.cs.kieler.kiml.ui.service.EclipseLayoutInfoService;

/**
 *  This class is responsible for transforming and exporting graphs from graphical diagrams.
 * 
 * @author wah
 * 
 */
public class GraphFileHandler {
    /** The source file to export.  */
    private IPath sourceFile;
    /** The target format to export file into ( without leading period ). */
    private String targetFormat;
    /** The target directory to export file into. */
    private IPath targetDirectory;
    /** The workspace directory. */    
    private IPath workspacePath = ResourcesPlugin.getWorkspace().getRoot().getLocation();

    /**
     * 
     * @param sourceFile
     *            the source file
     * @param targetFormat
     *            the target format(extension) without leading period
     * @param targetDirectory
     *            the target directory
     */
    public GraphFileHandler(final IPath sourceFile, final String targetFormat,
            final IPath targetDirectory) {
        super();
        this.sourceFile = sourceFile;
        this.targetFormat = targetFormat;
        this.targetDirectory = targetDirectory;
    }

    /**
     * @return the Workspace sourceFile
     */
    public File getWorkspaceSourceFile() {
        return new File(sourceFile.toString());
    }

    /**
     * @param sourceFile
     *            the sourceFile to set
     */
    public void setSourceFile(final IPath sourceFile) {
        this.sourceFile = sourceFile;
    }

    /**
     * @return the targetFormat
     */
    public String getTargetFormat() {
        return targetFormat;
    }

    /**
     * @param targetFormat
     *            the targetFormat to set (without leading period))
     */
    public void setTargetFormat(final String targetFormat) {
        this.targetFormat = targetFormat;
    }

    /**
     * @return the targetDirectory
     */
    public IPath getWorkspaceTargetDirectory() {
        return targetDirectory;
    }

    /**
     * @return the Absolute targetDirectory
     */
    public IPath getAbsoluteTargetDirectory() {
        return workspacePath.append(targetDirectory);
    }

    /**
     * @param tarDirectory 
     *            the targetDirectory to set
     *            
     */
    public void setDirectory(final IPath tarDirectory) {
        this.targetDirectory = tarDirectory;
    }

    /**
     * @return the Workspace targetIPath
     */
    public IPath getWorkspaceTargetIPath() {
        // get the last dot position
        int dotPos = this.sourceFile.toFile().getName().toString().lastIndexOf(".");
        // replace the file extension with the new one
        
        return this.targetDirectory.append(this.sourceFile.toFile().getName().substring(0, dotPos)
                .concat(".").concat(this.getTargetFormat()));
    }

    /**
     * @return the absolute targetIPath
     */
    public IPath getAbsoluteTargetIPath() {
        return workspacePath.append(getWorkspaceTargetIPath());
    }

    /**
     * @return the workspace targetFile
     */
    public File getWorkspaceTargetFile() {
        return this.getWorkspaceTargetIPath().toFile();
    }

    /**
     * @return the absolute targetFile
     */
    public File getAbsoluteTargetFile() {
        return workspacePath.append(this.getWorkspaceTargetIPath()).toFile();
    }

    /**
     * @return the KGraph
     */
    public KNode getKGraph() {
        // load the notation diagram element
        URI uri = URI.createPlatformResourceURI(this.getWorkspaceSourceFile().toString(), true);
        ResourceSet resourceSet = new ResourceSetImpl();
        final Resource resource = resourceSet.createResource(uri);
        try {
            resource.load(Collections.emptyMap());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (resource.getContents().isEmpty() || !(resource.getContents().get(0) instanceof Diagram)) {
            throw new IllegalArgumentException("The selected file does not contain a diagram.");
        }

        // create a diagram edit part
        TransactionalEditingDomain.Factory.INSTANCE.createEditingDomain(resourceSet);
        final Maybe<DiagramEditPart> editPart = new Maybe<DiagramEditPart>();
        final Maybe<RuntimeException> wrappedException = new Maybe<RuntimeException>();
        Display.getDefault().syncExec(new Runnable() {
            public void run() {
                try {
                    Diagram diagram = (Diagram) resource.getContents().get(0);
                    OffscreenEditPartFactory offscreenFactory = OffscreenEditPartFactory
                            .getInstance();
                    editPart.set(offscreenFactory.createDiagramEditPart(diagram, new Shell()));
                } catch (RuntimeException re) {
                    wrappedException.set(re);
                }
            }
        });
        if (wrappedException.get() != null) {
            throw wrappedException.get();
        }

        // retrieve a kgraph representation of the diagram
        LayoutMapping<?> mapping = EclipseLayoutInfoService.getInstance()
                .getManager(null, editPart.get()).buildLayoutGraph(null, editPart.get());
        KNode inputGraph = mapping.getLayoutGraph();

        return inputGraph;
    }

    /**
     * {@inheritDoc}
     */
    public <T> String performExport(final KNode kgraph, final ITransformationHandler<T> transHandler) {
        TransformationData<KNode, T> transData = new TransformationData<KNode, T>();
        transData.setSourceGraph(kgraph);
        IGraphTransformer<KNode, T> transformer = transHandler.getExporter();
        transformer.transform(transData);
        return transHandler.serialize(transData.getTargetGraphs().get(0));
    }

    /**
     * Call the performExport() method and return the string value from the graph.
     * 
     * @return the string value from the converted graph
     */
    public String graphToString() {
        // get the selected configuration
        return performExport(getKGraph(), getGraphFormatData().getHandler());
    }

    /**
     * function to return the selected GraphFormatData.
     * 
     * @return a GraphFormatData
     */
    public GraphFormatData getGraphFormatData() {
        Collection<GraphFormatData> formatData = TransformationService.getInstance()
                .getFormatData();
        for (GraphFormatData gfd : formatData) {
            if (gfd.getName().toLowerCase().equals(targetFormat)) {
                return gfd;
            }
        }
        return null;
    }

}
