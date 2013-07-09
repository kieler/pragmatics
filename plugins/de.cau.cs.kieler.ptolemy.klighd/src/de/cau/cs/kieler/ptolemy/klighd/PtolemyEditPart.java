/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.ptolemy.klighd;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IStorage;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IPathEditorInput;
import org.eclipse.ui.IStorageEditorInput;
import org.eclipse.ui.IURIEditorInput;
import org.eclipse.ui.PartInitException;
import org.ptolemy.moml.DocumentRoot;

import com.google.common.collect.Maps;
import com.google.inject.Guice;
import com.google.inject.Injector;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.ptolemy.klighd.transformation.Ptolemy2KGraphOptimization;
import de.cau.cs.kieler.ptolemy.klighd.transformation.Ptolemy2KGraphTransformation;
import de.cau.cs.kieler.klighd.views.DiagramEditorPart;

/**
 * TODO: Document.
 * 
 * @author uru
 * @author cds
 */
public class PtolemyEditPart extends DiagramEditorPart {

    /**
     * Map containing a standard list of parser features used for loading EMF resources.
     */
    private Map<String, Boolean> parserFeatures = null;

    /**
     * Create a new editor part for displaying a Ptolemy model.
     */
    public PtolemyEditPart() {
        // Prepare parser feature map. These options avoid searching for DTDs online, which would
        // require an internet connection to load models
        parserFeatures = Maps.newHashMap();
        parserFeatures.put(
                "http://xml.org/sax/features/validation", //$NON-NLS-1$
                Boolean.FALSE);
        parserFeatures.put(
                "http://apache.org/xml/features/nonvalidating/load-dtd-grammar", //$NON-NLS-1$
                Boolean.FALSE);
        parserFeatures.put(
                "http://apache.org/xml/features/nonvalidating/load-external-dtd", //$NON-NLS-1$
                Boolean.FALSE);
    }

    /**
     * Load a model from the editor input. The result is put into {@link #model}.
     * 
     * @throws PartInitException
     *             if loading the model fails
     */
    @Override
    protected void loadModel() throws PartInitException {
        // Get a URI or an input stream from the editor input
        URI uri = null;
        InputStream inputStream = null;
        IEditorInput input = getEditorInput();
        if (input instanceof IFileEditorInput) {
            IFile file = ((IFileEditorInput) input).getFile();
            uri = URI.createPlatformResourceURI(file.getFullPath().toString(), false);
        } else if (input instanceof IURIEditorInput) {
            java.net.URI inputUri = ((IURIEditorInput) input).getURI();
            uri = URI.createURI(inputUri.toString());
        } else if (input instanceof IPathEditorInput) {
            IPath path = ((IPathEditorInput) input).getPath();
            uri = URI.createFileURI(path.toString());
        } else if (input instanceof IStorageEditorInput) {
            try {
                IStorage storage = ((IStorageEditorInput) input).getStorage();
                inputStream = storage.getContents();
            } catch (CoreException exception) {
                throw new PartInitException("An error occurred while accessing the storage.",
                        exception);
            }
        } else {
            throw new PartInitException("The given editor input is not supported.");
        }
        
        // Load the Ptolemy model and convert it to a KGraph
        ResourceSet resourceSet;
        Resource resourceMoml;
        DocumentRoot ptModel = null;
        try {
            resourceSet = new ResourceSetImpl();
            resourceSet.getLoadOptions().put(XMIResource.OPTION_RECORD_UNKNOWN_FEATURE, true);
            resourceSet.getLoadOptions().put(XMLResource.OPTION_PARSER_FEATURES, parserFeatures);

            if (inputStream != null) {
                // load a stream-based resource
                uri = URI.createFileURI("temp.xmi");
                resourceMoml = resourceSet.createResource(uri);
                resourceMoml.load(inputStream, Collections.EMPTY_MAP);
            } else {
                // load a URI-based resource
                resourceMoml = resourceSet.getResource(uri, true);
            }

            if (resourceMoml.getContents().isEmpty()) {
                throw new PartInitException("The resource is empty.");
            }

            ptModel = (DocumentRoot) resourceMoml.getContents().get(0);

            // Three steps: transformation, optimization, and visualization
            Injector injector = Guice.createInjector();

            Ptolemy2KGraphTransformation transformation =
                    injector.getInstance(Ptolemy2KGraphTransformation.class);
            Ptolemy2KGraphOptimization optimization =
                    injector.getInstance(Ptolemy2KGraphOptimization.class);

            // Transform and optimize
            KNode kGraph = null;

            kGraph = transformation.transform(ptModel);
            optimization.optimize(kGraph);

            model = kGraph;

            // FIXME
            // Advanced annotation handling, if requested
            // if (advancedAnnotationsHandling) {
            // PtolemyAnnotationHandler annotationHandler = new PtolemyAnnotationHandler(
            // (XMLResource) srcResource, ptModel, kaomModel, heuristicsOverride);
            //
            // annotationHandler.handleAnnotations();
            // }

        } catch (IOException exception) {
            throw new PartInitException("An error occurred while loading the resource.", exception);
        }
    }

}