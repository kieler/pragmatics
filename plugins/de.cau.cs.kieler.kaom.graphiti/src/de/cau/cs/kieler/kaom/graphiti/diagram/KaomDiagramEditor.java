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
package de.cau.cs.kieler.kaom.graphiti.diagram;

import org.eclipse.graphiti.ui.editor.DiagramEditor;

/**
 * Diagram editor class for the Graphiti KAOM editor.
 * 
 * @author msp
 * @kieler.ignore (excluded from review process)
 */
public class KaomDiagramEditor extends DiagramEditor {

    /** the editor identifier. */
    public static final String EDITOR_ID = "de.cau.cs.kieler.kaom.graphiti.editor";
    /** the diagram type name to store in diagram files. */
    public static final String DIAGRAM_TYPE = "KAOM";
    /** the diagram type identifier. */
    public static final String DIAGRAM_TYPE_ID = "de.cau.cs.kieler.kaom.diagramType";
    /** the diagram type provider identifier. */
    public static final String DIAGRAM_TYPE_PROVIDER_ID = "de.cau.cs.kieler.kaom.diagramTypeProvider";
    /** file extension for Graphiti KAOM diagram files. */
    public static final String DIAGRAM_FILE_EXTENSION = "kaog";
    /** file extension for KAOM model files. */
    public static final String MODEL_FILE_EXTENSION = "kaom";

}
