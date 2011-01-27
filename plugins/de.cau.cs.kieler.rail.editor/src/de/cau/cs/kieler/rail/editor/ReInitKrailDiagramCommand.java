package de.cau.cs.kieler.rail.editor;

import org.eclipse.emf.ecore.EObject;

import de.cau.cs.kieler.core.model.graphiti.ui.AbstractReInitGraphitiDiagramCommand;
import de.cau.cs.kieler.rail.Topologie.Basegraph.Edge;

public class ReInitKrailDiagramCommand extends
        AbstractReInitGraphitiDiagramCommand {

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

}
