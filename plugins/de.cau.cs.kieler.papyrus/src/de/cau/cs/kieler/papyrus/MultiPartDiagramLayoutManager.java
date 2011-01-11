/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.papyrus;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.papyrus.core.editor.IMultiDiagramEditor;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPropertyListener;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.model.GmfEditingProvider;
import de.cau.cs.kieler.core.ui.IEditingProvider;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.gmf.GmfDiagramLayoutManager;
import de.cau.cs.kieler.kiml.ui.IEditorChangeListener;

/**
 * Layout manager wrapper for the Papyrus multi diagram editor.
 * 
 * @author msp
 */
public class MultiPartDiagramLayoutManager extends GmfDiagramLayoutManager {

    /** map of editor change listeners to all editors for which they have registered. */
    private Map<IEditorChangeListener, List<Pair<IMultiDiagramEditor, IPropertyListener>>> listenerMap
            = new HashMap<IEditorChangeListener, List<Pair<IMultiDiagramEditor, IPropertyListener>>>();

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean supports(final IEditorPart editorPart) {
        return editorPart instanceof IMultiDiagramEditor;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public KNode buildLayoutGraph(final IEditorPart editorPart,
            final EditPart editPart, final boolean layoutAncestors) {
        if (editorPart instanceof IMultiDiagramEditor) {
            return super.buildLayoutGraph(((IMultiDiagramEditor) editorPart).getActiveEditor(),
                    editPart, layoutAncestors);
        } else {
            return super.buildLayoutGraph(editorPart, editPart, layoutAncestors);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addChangeListener(final IEditorPart editorPart,
            final IEditorChangeListener editorListener) {
        if (editorPart instanceof IMultiDiagramEditor) {
            final IMultiDiagramEditor diagramEditor = (IMultiDiagramEditor) editorPart;
            IPropertyListener tabListener = new IPropertyListener() {
                private IEditorPart currentEditor = diagramEditor.getActiveEditor();
                public void propertyChanged(final Object source, final int propId) {
                    internalRemoveListener(editorListener);
                    editorListener.editorChanged();
                    currentEditor = diagramEditor.getActiveEditor();
                    internalAddListener(currentEditor, editorListener);
                }
            };
            diagramEditor.addPropertyListener(tabListener);
            List<Pair<IMultiDiagramEditor, IPropertyListener>> editorList
                    = listenerMap.get(editorListener);
            if (editorList == null) {
                editorList = new LinkedList<Pair<IMultiDiagramEditor, IPropertyListener>>();
                listenerMap.put(editorListener, editorList);
            }
            editorList.add(new Pair<IMultiDiagramEditor, IPropertyListener>(
                    diagramEditor, tabListener));
            super.addChangeListener(diagramEditor.getActiveEditor(), editorListener);
        } else {
            super.addChangeListener(editorPart, editorListener);
        }
    }
    
    /**
     * Calls the superclass method to add a change listener.
     * 
     * @param editorPart a diagram editor
     * @param editorListener an editor change listener
     */
    private void internalAddListener(final IEditorPart editorPart,
            final IEditorChangeListener editorListener) {
        super.addChangeListener(editorPart, editorListener);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeChangeListener(final IEditorChangeListener editorListener) {
        List<Pair<IMultiDiagramEditor, IPropertyListener>> editorList
                = listenerMap.remove(editorListener);
        if (editorList != null) {
            for (Pair<IMultiDiagramEditor, IPropertyListener> pair : editorList) {
                pair.getFirst().removePropertyListener(pair.getSecond());
            }
        }
        super.removeChangeListener(editorListener);
    }
    
    /**
     * Calls the superclass method to remove a change listener.
     * 
     * @param editorListener an editor change listener
     */
    private void internalRemoveListener(final IEditorChangeListener editorListener) {
        super.removeChangeListener(editorListener);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IEditingProvider getProvider() {
        return new GmfEditingProvider() {
            @Override
            public ISelection getSelection(final IEditorPart editorPart) {
                if (editorPart instanceof IMultiDiagramEditor) {
                    return super.getSelection(((IMultiDiagramEditor) editorPart).getActiveEditor());
                } else {
                    return super.getSelection(editorPart);
                }
            }
        };
    }

}
