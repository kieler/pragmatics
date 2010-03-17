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
package de.cau.cs.kieler.dataflow.diagram.part;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.Tool;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeConnectionTool;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeCreationTool;

import de.cau.cs.kieler.dataflow.diagram.providers.DataflowElementTypes;

/**
 * @generated
 */
public class DataflowPaletteFactory {

    /**
     * @generated
     */
    public void fillPalette(PaletteRoot paletteRoot) {
        paletteRoot.add(createDataflow1Group());
    }

    /**
     * Creates "dataflow" palette tool group
     * @generated
     */
    private PaletteContainer createDataflow1Group() {
        PaletteGroup paletteContainer = new PaletteGroup(
                Messages.Dataflow1Group_title);
        paletteContainer.setId("createDataflow1Group"); //$NON-NLS-1$
        paletteContainer.add(createBox1CreationTool());
        paletteContainer.add(createConnection2CreationTool());
        paletteContainer.add(createPort3CreationTool());
        return paletteContainer;
    }

    /**
     * @generated
     */
    private ToolEntry createBox1CreationTool() {
        List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(2);
        types.add(DataflowElementTypes.Box_2001);
        types.add(DataflowElementTypes.Box_3003);
        NodeToolEntry entry = new NodeToolEntry(
                Messages.Box1CreationTool_title,
                Messages.Box1CreationTool_desc, types);
        entry.setId("createBox1CreationTool"); //$NON-NLS-1$
        entry.setSmallIcon(DataflowElementTypes
                .getImageDescriptor(DataflowElementTypes.Box_2001));
        entry.setLargeIcon(entry.getSmallIcon());
        return entry;
    }

    /**
     * @generated
     */
    private ToolEntry createConnection2CreationTool() {
        List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
        types.add(DataflowElementTypes.Connection_4001);
        LinkToolEntry entry = new LinkToolEntry(
                Messages.Connection2CreationTool_title,
                Messages.Connection2CreationTool_desc, types);
        entry.setId("createConnection2CreationTool"); //$NON-NLS-1$
        entry.setSmallIcon(DataflowElementTypes
                .getImageDescriptor(DataflowElementTypes.Connection_4001));
        entry.setLargeIcon(entry.getSmallIcon());
        return entry;
    }

    /**
     * @generated
     */
    private ToolEntry createPort3CreationTool() {
        List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(2);
        types.add(DataflowElementTypes.InputPort_3001);
        types.add(DataflowElementTypes.OutputPort_3002);
        NodeToolEntry entry = new NodeToolEntry(
                Messages.Port3CreationTool_title,
                Messages.Port3CreationTool_desc, types);
        entry.setId("createPort3CreationTool"); //$NON-NLS-1$
        entry.setSmallIcon(DataflowElementTypes
                .getImageDescriptor(DataflowElementTypes.InputPort_3001));
        entry.setLargeIcon(entry.getSmallIcon());
        return entry;
    }

    /**
     * @generated
     */
    private static class NodeToolEntry extends ToolEntry {

        /**
         * @generated
         */
        private final List elementTypes;

        /**
         * @generated
         */
        private NodeToolEntry(String title, String description,
                List elementTypes) {
            super(title, description, null, null);
            this.elementTypes = elementTypes;
        }

        /**
         * @generated
         */
        public Tool createTool() {
            Tool tool = new UnspecifiedTypeCreationTool(elementTypes);
            tool.setProperties(getToolProperties());
            return tool;
        }
    }

    /**
     * @generated
     */
    private static class LinkToolEntry extends ToolEntry {

        /**
         * @generated
         */
        private final List relationshipTypes;

        /**
         * @generated
         */
        private LinkToolEntry(String title, String description,
                List relationshipTypes) {
            super(title, description, null, null);
            this.relationshipTypes = relationshipTypes;
        }

        /**
         * @generated
         */
        public Tool createTool() {
            Tool tool = new UnspecifiedTypeConnectionTool(relationshipTypes);
            tool.setProperties(getToolProperties());
            return tool;
        }
    }
}
