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
package de.cau.cs.kieler.rail.editor;

import org.eclipse.emf.ecore.EObject;

import de.cau.cs.kieler.core.model.graphiti.ui.AbstractReInitGraphitiDiagramCommand;
import de.cau.cs.kieler.rail.Topologie.Basegraph.Edge;

/**
 * 
 * @author soh
 */
public class ReInitKrailDiagramCommand extends
        AbstractReInitGraphitiDiagramCommand {

    /**
     * 
     * Creates a new ReInitKrailDiagramCommand.
     * 
     */
    public ReInitKrailDiagramCommand() {
        super(KrailDiagramEditor.DIAGRAM_TYPE, 0, false);
    }

    @Override
    protected String getEditorId() {
        return KrailDiagramEditor.EDITOR_ID;
    }

    @Override
    protected boolean isConnection(final EObject eObj) {
        return eObj instanceof Edge;
    }

    @Override
    protected EObject getConnectionSource(final EObject connection) {
        if (connection instanceof Edge) {
            Edge e = (Edge) connection;
            return e.getBegin();
        }
        return null;
    }

    @Override
    protected EObject getConnectionTarget(final EObject connection) {
        if (connection instanceof Edge) {
            Edge e = (Edge) connection;
            return e.getEnd();
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getDiagramExtension() {
        return KrailDiagramEditor.DIAGRAM_FILE_EXTENSION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getModelExtension() {
        return KrailDiagramEditor.MODEL_FILE_EXTENSION;
    }

    @Override
    protected boolean addChildrenRecursively(final EObject eObj) {
        return false;
    }

}
