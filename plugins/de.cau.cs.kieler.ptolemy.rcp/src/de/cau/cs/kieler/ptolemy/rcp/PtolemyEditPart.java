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
package de.cau.cs.kieler.ptolemy.rcp;

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

import de.cau.cs.kieler.kaom.Entity;
import de.cau.cs.kieler.kaom.importer.ptolemy.xtend.Ptolemy2KaomOptimization;
import de.cau.cs.kieler.kaom.importer.ptolemy.xtend.Ptolemy2KaomTransformation;
import de.cau.cs.kieler.klighd.views.DiagramEditorPart;

/**
 * @author uru
 * 
 */
public class PtolemyEditPart extends DiagramEditorPart {


    /**
     * Map containing a standard list of parser features used for loading EMF resources.
     */
    private Map<String, Boolean> parserFeatures = null;

    /**
     * 
     */
    public PtolemyEditPart() {
        // Prepare parser feature map. These options avoid searching for DTDs online, which would
        // require an internet connection to load models
        parserFeatures = Maps.newHashMap();
        parserFeatures.put("http://xml.org/sax/features/validation", //$NON-NLS-1$
                Boolean.FALSE);
        parserFeatures.put("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", //$NON-NLS-1$
                Boolean.FALSE);
        parserFeatures.put("http://apache.org/xml/features/nonvalidating/load-external-dtd", //$NON-NLS-1$
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
        // get a URI or an input stream from the editor input
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
                // resource = resourceSet.createResource(uri);
                // resource.load(Collections.EMPTY_MAP);

                // Load the source model
                resourceMoml = resourceSet.getResource(uri, true);
                // ptModel = (DocumentRoot) srcResource.getContents().get(0);
            }
        } catch (IOException exception) {
            throw new PartInitException("An error occurred while loading the resource.", exception);
        }

        if (resourceMoml.getContents().isEmpty()) {
            throw new PartInitException("The resource is empty.");
        }

        ptModel = (DocumentRoot) resourceMoml.getContents().get(0);

        // Two steps: transformation, and optimization
        Injector injector = Guice.createInjector();

        Ptolemy2KaomTransformation transformation =
                injector.getInstance(Ptolemy2KaomTransformation.class);
        Ptolemy2KaomOptimization optimization =
                injector.getInstance(Ptolemy2KaomOptimization.class);

        // Transform and optimize
        Entity kaomModel = null;

//        try {
            kaomModel = transformation.transform(ptModel);
            optimization.optimize(kaomModel);
            
            model = kaomModel;
//        } catch (Exception e) {
//            throw new CoreException(new Status(IStatus.ERROR, PtolemyRCPPlugin.PLUGIN_ID,
//                    "Model transformation failed: " + e.getMessage(), e));
//        }

        // Advanced annotation handling, if requested
        // if (advancedAnnotationsHandling) {
        // PtolemyAnnotationHandler annotationHandler = new PtolemyAnnotationHandler(
        // (XMLResource) srcResource, ptModel, kaomModel, heuristicsOverride);
        //
        // annotationHandler.handleAnnotations();
        // }
  System.out.println(kaomModel);          
            
    }

  
}
