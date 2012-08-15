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
package de.cau.cs.kieler.kiml.formats.svg;

import java.io.ByteArrayInputStream;
import java.util.Collections;

import org.apache.batik.svggen.SVGGraphics2D;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.statushandlers.StatusManager;
import org.eclipse.xtext.resource.XtextResourceSet;

import com.google.inject.Injector;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.text.ui.internal.KGraphActivator;
import de.cau.cs.kieler.kiml.service.formats.IGraphTransformer;
import de.cau.cs.kieler.kiml.service.formats.TransformationData;

/**
 * Test class for the SVG exporter.
 *
 * @author msp
 */
public class Test extends AbstractHandler {

    /**
     * {@inheritDoc}
     */
    public Object execute(final ExecutionEvent event) throws ExecutionException {
        try {
            ISelection selection = HandlerUtil.getCurrentSelection(event);
            if (selection instanceof IStructuredSelection) {
                final Object[] elements = ((IStructuredSelection) selection).toArray();
                for (Object object : elements) {
                    if (object instanceof IFile) {
                        IFile inputFile = (IFile) object;
                        Injector injector = KGraphActivator.getInstance().getInjector(KGraphActivator.DE_CAU_CS_KIELER_CORE_KGRAPH_TEXT_KGRAPH);
                        ResourceSet resourceSet = injector.getInstance(XtextResourceSet.class);
                        URI inputFileURI = URI.createPlatformResourceURI(inputFile.getFullPath().toString(), false);
                        Resource resource = resourceSet.createResource(inputFileURI);
                        resource.load(Collections.EMPTY_MAP);
                        if (resource.getContents().size() != 1) {
                            throw new RuntimeException("The input file must contain exactly one KGraph model.");
                        }
                        EObject content = resource.getContents().get(0);
                        if (!(content instanceof KNode)) {
                            throw new RuntimeException("The input model must be an instance of KNode.");
                        }
                        
                        SvgHandler svgHandler = new SvgHandler();
                        IGraphTransformer<KNode, SVGGraphics2D> exporter = svgHandler.getExporter();
                        TransformationData<KNode, SVGGraphics2D> data = new TransformationData<KNode, SVGGraphics2D>();
                        data.setSourceGraph((KNode) content);
                        exporter.transform(data);
                        String output = svgHandler.serialize(data.getTargetGraphs().get(0));
                        
                        IFile outputFile = inputFile.getParent().getFile(new Path(inputFile.getName()).removeFileExtension().addFileExtension("svg"));
                        outputFile.create(new ByteArrayInputStream(output.getBytes()), true, null);
                    }
                }
            }
        } catch (Exception e) {
            Status status = new Status(Status.ERROR, "de.cau.cs.kieler.kiml.formats.svg",
                    "Error while performing SVG export.", e);
            StatusManager.getManager().handle(status, StatusManager.SHOW | StatusManager.LOG);
        }
        
        return null;
    }

}
