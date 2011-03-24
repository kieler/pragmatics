package de.cau.cs.kieler.rail.editor;

import org.eclipse.graphiti.ui.editor.DiagramEditor;

/**
 * Special DiagramEditor
 * @author hdw
 *
 */
public class KrailDiagramEditor extends DiagramEditor {

    /** the editor identifier. */
    public static final String EDITOR_ID = "de.cau.cs.kieler.rail.editor";
    /** the diagram type name to store in diagram files. */
    public static final String DIAGRAM_TYPE = "Rail";
    /** the diagram type identifier. */
    public static final String DIAGRAM_TYPE_ID =
       "de.cau.cs.kieler.rail.editor.DiagramType";
    /** the diagram type provider identifier. */
    public static final String DIAGRAM_TYPE_PROVIDER_ID =
        "de.cau.cs.kieler.rail.editor.DiagramTypeProvider";
    /** file extension for Graphiti rail diagram files. */
    public static final String DIAGRAM_FILE_EXTENSION = "krail";
    /** file extension for KAOM model files. */
    public static final String MODEL_FILE_EXTENSION = "topologie";

}
