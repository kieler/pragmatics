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
package de.cau.cs.kieler.klighd.views;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IStorage;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IPathEditorInput;
import org.eclipse.ui.IStorageEditorInput;
import org.eclipse.ui.IURIEditorInput;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.ui.statushandlers.StatusManager;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

import de.cau.cs.kieler.core.properties.MapPropertyHolder;
import de.cau.cs.kieler.klighd.KlighdPlugin;
import de.cau.cs.kieler.klighd.LightDiagramServices;
import de.cau.cs.kieler.klighd.ViewContext;
import de.cau.cs.kieler.klighd.krendering.SimpleUpdateStrategy;
import de.cau.cs.kieler.klighd.util.Iterables2;
import de.cau.cs.kieler.klighd.viewers.ContextViewer;

/**
 * An editor which is able to display models in light-weight diagrams.
 *
 * @author msp
 */
public class DiagramEditorPart extends EditorPart {
    
    /** the resource set managed by this editor part. */
    private ResourceSet resourceSet;
    /** the model represented by this editor part. */
    private Object model;
    /** the viewer for this editor part. */
    private ContextViewer viewer;

    /**
     * Creates a diagram editor part.
     */
    public DiagramEditorPart() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init(final IEditorSite site, final IEditorInput input) throws PartInitException {
        setSite(site);
        setInput(input);
        loadModel();
        ResourcesPlugin.getWorkspace().addResourceChangeListener(resourceChangeListener,
                IResourceChangeEvent.POST_CHANGE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createPartControl(final Composite parent) {
        // set the part name
        setPartName(getEditorInput().getName());
        
        // create a context viewer
        viewer = new ContextViewer(parent, getEditorInput().getName(), this);
        
        MapPropertyHolder props = new MapPropertyHolder();
        props.setProperty(LightDiagramServices.REQUESTED_UPDATE_STRATEGY, SimpleUpdateStrategy.ID);
        
        // create a view context for the viewer
        ViewContext viewContext = LightDiagramServices.getInstance().createViewContext(model, props);
        if (viewContext != null) {
            viewer.setModel(viewContext);
            // do an initial update of the view context
            LightDiagramServices.getInstance().updateViewContext(viewContext, model);

            // since no initial selection is set in the view context/context viewer implementation,
            // define some here by selection the root of the view model representing the diagram canvas!
            viewer.selection(null, Iterables2.singletonIterable((EObject) viewContext.getViewModel()));
        } else {
            viewer.setModel("The selected file does not contain any supported model.", false);
        }
        
        // register the context viewer as selection provider on the workbench
        getSite().setSelectionProvider(viewer);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void dispose() {
        ResourcesPlugin.getWorkspace().removeResourceChangeListener(resourceChangeListener);
        viewer.dispose();
        super.dispose();
    }
    
    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("rawtypes")
    public Object getAdapter(final Class type) {
        if (type == IContentOutlinePage.class) {
            IContentOutlinePage outlinePage = viewer.getOutlinePage();
            if (outlinePage != null) {
                return outlinePage;
            }
        }
        return super.getAdapter(type);
    }

    /**
     * Returns the context viewer represented by this editor part.
     * 
     * @return the context viewer
     */
    public ContextViewer getContextViewer() {
        return viewer;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFocus() {
        viewer.getControl().setFocus();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isDirty() {
        // this is merely a viewer, so the content is never dirty
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doSave(final IProgressMonitor monitor) {
        // this is merely a viewer, so we have nothing to save
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSaveAsAllowed() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doSaveAs() {
        throw new UnsupportedOperationException();
    }
    
    /**
     * Load a model from the editor input. The result is put into {@link #model}.
     * 
     * @throws PartInitException if loading the model fails
     */
    private void loadModel() throws PartInitException {
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
        
        Resource resource;
        try {
            resourceSet = new ResourceSetImpl();
            if (inputStream != null) {
                // load a stream-based resource
                uri = URI.createFileURI("temp.xmi");
                resource = resourceSet.createResource(uri);
                resource.load(inputStream, Collections.EMPTY_MAP);
            } else {
                // load a URI-based resource
                resource = resourceSet.createResource(uri);
                resource.load(Collections.EMPTY_MAP);
            }
        } catch (IOException exception) {
            throw new PartInitException("An error occurred while loading the resource.", exception);
        }
        
        if (resource.getContents().isEmpty()) {
            throw new PartInitException("The resource is empty.");
        }
        // default behavior: get the first element in the resource
        model = resource.getContents().get(0);
    }
    
    /**
     * Update the viewed model using the given resource.
     * 
     * @param resource a resource
     */
    private void updateModel(final Resource resource) {
        if (resource.isLoaded()) {
            resource.unload();
            try {
                resource.load(Collections.EMPTY_MAP);

                // default behavior: get the first element in the resource
                model = resource.getContents().get(0);
                
                ViewContext viewContext = viewer.getCurrentViewContext();
                LightDiagramServices.getInstance().updateViewContext(viewContext, model);
            } catch (IOException exception) {
                IStatus status = new Status(IStatus.ERROR, KlighdPlugin.PLUGIN_ID,
                        "Failed to update " + resource.getURI().toString(), exception);
                StatusManager.getManager().handle(status);
            }
        }
    }
    
    /**
     * A resource change listener that updates the editor when resources are removed or changed.
     */
    private IResourceChangeListener resourceChangeListener = new IResourceChangeListener() {
        public void resourceChanged(final IResourceChangeEvent event) {
            try {
                event.getDelta().accept(new IResourceDeltaVisitor() {
                    public boolean visit(final IResourceDelta delta) {
                        if (delta.getResource().getType() == IResource.FILE) {
                            if (delta.getKind() == IResourceDelta.REMOVED
                                    || delta.getKind() == IResourceDelta.CHANGED
                                    && delta.getFlags() != IResourceDelta.MARKERS) {
                                // this won't work if the resource was loaded from an absolute path
                                final Resource resource = resourceSet.getResource(
                                        URI.createPlatformResourceURI(
                                        delta.getFullPath().toString(), true), false);
                                if (resource != null) {
                                    getSite().getShell().getDisplay().asyncExec(new Runnable() {
                                        public void run() {
                                            if (delta.getKind() == IResourceDelta.REMOVED) {
                                                // a required resource was removed, so close the editor
                                                getSite().getPage().closeEditor(
                                                        DiagramEditorPart.this, false);
                                            } else {
                                                updateModel(resource);
                                            }
                                        }
                                    });
                                }
                            }
                        }
    
                        return true;
                    }
                });
            } catch (CoreException exception) {
                StatusManager.getManager().handle(exception, KlighdPlugin.PLUGIN_ID);
            }
        }
    };
            
}
