/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2016 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.xtext.transformations;

import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IMemento;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.formats.GraphFormatsService;
import de.cau.cs.kieler.klighd.ui.view.controller.AbstractViewUpdateController;
import de.cau.cs.kieler.klighd.ui.view.controllers.EditorSaveAdapter;
import de.cau.cs.kieler.klighd.util.KlighdSynthesisProperties;

/**
 * {@link de.cau.cs.kieler.klighd.ui.view.DiagramView DiagramView} controller for opening general
 * graph diagrams.
 * 
 * @author uru
 */
public class GraphUpdateController extends AbstractViewUpdateController implements
        EditorSaveAdapter.EditorSafeListener {

    /** Controller ID. */
    private static final String ID =
            "de.cau.cs.kieler.klighd.ui.view.controllers.GraphUpdateController";

    // CHECKSTYLEOFF VisibilityModifier NEXT
    /** The save adapter for the editor. */
    protected final EditorSaveAdapter saveAdapter;    
    
    /**
     * Default constructor.
     */
    public GraphUpdateController() {
        saveAdapter = new EditorSaveAdapter(this);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String getID() {
        return ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onActivate(final IEditorPart editor) {
        updateModel(readModel(editor));
        saveAdapter.activate(editor);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onDeactivate() {
        saveAdapter.deactivate();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void selectionChanged(final SelectionChangedEvent event) {
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void copy(final AbstractViewUpdateController source) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reset() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveState(final IMemento memento) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void loadState(final IMemento memento) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onDiagramUpdate(final Object model, final KlighdSynthesisProperties properties) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onDispose() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addContributions(final IToolBarManager toolBar, final IMenuManager menu) {
    }
    
    // -- Save Listener
    // -------------------------------------------------------------------------
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void onEditorSaved(final IEditorPart editor) {
        updateModel(readModel(editor));
    }

    // -- Utility
    // -------------------------------------------------------------------------

    /**
     * Reads the graph from the given EdtorPart if it is a supported graph format. 
     * 
     * @param editor
     *            IEditorPart containing model
     * @return EObject model
     */
    protected static KNode readModel(final IEditorPart editor) {
        
        final IEditorInput input = editor.getEditorInput();
        if (input instanceof IFileEditorInput) {
            final IFile file = ((IFileEditorInput) input).getFile();
            
            try {
                // if we know how to handle the graph's format, load it
                KNode[] kgraph = GraphFormatsService.getInstance().loadKGraph(file);
                return kgraph[0];
                
            } catch (IOException | CoreException e) {
                return null;
            }
            
        } 

        return null;
    }
    
}
