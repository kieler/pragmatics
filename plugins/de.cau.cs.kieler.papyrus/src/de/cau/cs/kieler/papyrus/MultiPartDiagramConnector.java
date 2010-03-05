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
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPropertyListener;

import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.ui.editors.IEditorChangeListener;
import de.cau.cs.kieler.kiml.ui.editors.IDiagramEditorConnector;

/**
 * 
 * Implementation of the editor connector interface for the Papyrus multi part diagram editor.
 * 
 * @author jjc
 * @author msp
 */
public class MultiPartDiagramConnector implements IDiagramEditorConnector {

    /** map of editor change listeners to all editors for which they have registered. */
    private Map<IEditorChangeListener, List<Pair<IMultiDiagramEditor, IPropertyListener>>> listenerMap
            = new HashMap<IEditorChangeListener, List<Pair<IMultiDiagramEditor, IPropertyListener>>>();

    /**
     * {@inheritDoc}
     */
    public IEditorPart getActiveEditor(final IEditorPart editorPart) {
        IEditorPart result = editorPart;
        if (editorPart instanceof IMultiDiagramEditor) {
            result = ((IMultiDiagramEditor) editorPart).getActiveEditor();
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    public boolean supports(final IEditorPart editorPart) {
        return editorPart instanceof IMultiDiagramEditor;
    }

    /**
     * {@inheritDoc}
     */
    public void addChangeListener(final IEditorPart editorPart,
            final IEditorChangeListener editorListener) {
        if (editorPart instanceof IMultiDiagramEditor) {
            final IMultiDiagramEditor diagramEditor = (IMultiDiagramEditor) editorPart;
            IPropertyListener tabListener = new IPropertyListener() {
                public void propertyChanged(final Object source, final int propId) {
                    editorListener.editorChanged(diagramEditor.getActiveEditor());
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
        }
    }

    /**
     * {@inheritDoc}
     */
    public void removeChangeListener(final IEditorChangeListener editorListener) {
        List<Pair<IMultiDiagramEditor, IPropertyListener>> editorList
                = listenerMap.remove(editorListener);
        if (editorList != null) {
            for (Pair<IMultiDiagramEditor, IPropertyListener> pair : editorList) {
                pair.getFirst().removePropertyListener(pair.getSecond());
            }
        }
    }

}
