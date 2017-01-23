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
package de.cau.cs.kieler.graphs.klighd;

import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;

import de.cau.cs.kieler.kiml.formats.GraphFormatsService;
import de.cau.cs.kieler.klighd.ui.view.controller.AbstractViewUpdateController;
import de.cau.cs.kieler.klighd.ui.view.controllers.EditorSaveAdapter;

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

    // -- Activation
    // -------------------------------------------------------------------------

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
        
    // -- Save Listener
    // -------------------------------------------------------------------------
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void onEditorSaved(final IEditorPart editor) {
        if (getDiagramView().isLinkedWithActiveEditor()) {
            updateModel(readModel(editor));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void refresh() {
        updateModel(readModel(getEditor()));
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
    protected static ElkNode readModel(final IEditorPart editor) {
        
        final IEditorInput input = editor.getEditorInput();
        if (input instanceof IFileEditorInput) {
            final IFile file = ((IFileEditorInput) input).getFile();
            
            try {
                // if we know how to handle the graph's format, load it
                ElkNode[] kgraph = GraphFormatsService.getInstance().loadElkGraph(file);
                return kgraph[0];
                
            } catch (IOException | CoreException e) {
                return null;
            }
            
        } 

        return null;
    }
    
}
