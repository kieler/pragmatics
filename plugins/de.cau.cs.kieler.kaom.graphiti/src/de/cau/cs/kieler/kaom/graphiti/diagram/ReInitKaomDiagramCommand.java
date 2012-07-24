/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kaom.graphiti.diagram;

import org.eclipse.emf.ecore.EObject;

import de.cau.cs.kieler.core.model.graphiti.ui.AbstractInitGraphitiDiagramHandler;
import de.cau.cs.kieler.kaom.Link;

/**
 * @author soh
 * @kieler.ignore (excluded from review process)
 */
public class ReInitKaomDiagramCommand extends
        AbstractInitGraphitiDiagramHandler {

    /**
     * Creates a new ReInitKaomDiagramCommand.java.
     * 
     */
    public ReInitKaomDiagramCommand() {
        super(KaomDiagramEditor.DIAGRAM_TYPE, 0, false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getEditorId() {
        return KaomDiagramEditor.EDITOR_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getDiagramExtension() {
        return KaomDiagramEditor.DIAGRAM_FILE_EXTENSION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean isConnection(final EObject eObj) {
        if (eObj instanceof Link) {
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected EObject getConnectionSource(final EObject connection) {
        if (connection instanceof Link) {
            Link link = (Link) connection;
            return link.getSource();
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected EObject getConnectionTarget(final EObject connection) {
        if (connection instanceof Link) {
            Link link = (Link) connection;
            return link.getTarget();
        }
        return null;
    }

}
