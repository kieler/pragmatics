package de.cau.cs.kieler.keg.diagram.part;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.gef.Tool;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.PaletteSeparator;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeConnectionTool;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeCreationTool;

import org.eclipse.gmf.runtime.emf.type.core.IElementType;

import de.cau.cs.kieler.keg.diagram.providers.GraphsElementTypes;

/**
 * @generated
 */
public class GraphsPaletteFactory {

    /**
     * @generated
     */
    public void fillPalette(PaletteRoot paletteRoot) {
        paletteRoot.add(createGraphs1Group());
    }

    /**
     * Creates "graphs" palette tool group
     * @generated
     */
    private PaletteContainer createGraphs1Group() {
        PaletteGroup paletteContainer = new PaletteGroup(Messages.Graphs1Group_title);
        paletteContainer.setId("createGraphs1Group"); //$NON-NLS-1$
        paletteContainer.add(createNode1CreationTool());
        paletteContainer.add(createDirectedEdge2CreationTool());
        paletteContainer.add(createUndirectedEdge3CreationTool());
        paletteContainer.add(new PaletteSeparator());
        paletteContainer.add(createPort5CreationTool());
        paletteContainer.add(createHypernode6CreationTool());
        return paletteContainer;
    }

    /**
     * @generated
     */
    private ToolEntry createNode1CreationTool() {
        ArrayList<IElementType> types = new ArrayList<IElementType>(2);
        types.add(GraphsElementTypes.Node_2001);
        types.add(GraphsElementTypes.Node_3001);
        NodeToolEntry entry = new NodeToolEntry(Messages.Node1CreationTool_title,
                Messages.Node1CreationTool_desc, types);
        entry.setId("createNode1CreationTool"); //$NON-NLS-1$
        entry.setSmallIcon(GraphsElementTypes.getImageDescriptor(GraphsElementTypes.Node_2001));
        entry.setLargeIcon(entry.getSmallIcon());
        return entry;
    }

    /**
     * @generated
     */
    private ToolEntry createDirectedEdge2CreationTool() {
        ArrayList<IElementType> types = new ArrayList<IElementType>(4);
        types.add(GraphsElementTypes.Edge_4001);
        types.add(GraphsElementTypes.Edge_4003);
        types.add(GraphsElementTypes.Edge_4005);
        types.add(GraphsElementTypes.Edge_4007);
        LinkToolEntry entry = new LinkToolEntry(Messages.DirectedEdge2CreationTool_title,
                Messages.DirectedEdge2CreationTool_desc, types);
        entry.setId("createDirectedEdge2CreationTool"); //$NON-NLS-1$
        entry.setSmallIcon(GraphsElementTypes.getImageDescriptor(GraphsElementTypes.Edge_4001));
        entry.setLargeIcon(entry.getSmallIcon());
        return entry;
    }

    /**
     * @generated
     */
    private ToolEntry createUndirectedEdge3CreationTool() {
        ArrayList<IElementType> types = new ArrayList<IElementType>(4);
        types.add(GraphsElementTypes.Edge_4002);
        types.add(GraphsElementTypes.Edge_4004);
        types.add(GraphsElementTypes.Edge_4006);
        types.add(GraphsElementTypes.Edge_4008);
        LinkToolEntry entry = new LinkToolEntry(Messages.UndirectedEdge3CreationTool_title,
                Messages.UndirectedEdge3CreationTool_desc, types);
        entry.setId("createUndirectedEdge3CreationTool"); //$NON-NLS-1$
        entry.setSmallIcon(GraphsElementTypes.getImageDescriptor(GraphsElementTypes.Edge_4002));
        entry.setLargeIcon(entry.getSmallIcon());
        return entry;
    }

    /**
     * @generated
     */
    private ToolEntry createPort5CreationTool() {
        NodeToolEntry entry = new NodeToolEntry(Messages.Port5CreationTool_title,
                Messages.Port5CreationTool_desc, Collections.singletonList(GraphsElementTypes.Port_3002));
        entry.setId("createPort5CreationTool"); //$NON-NLS-1$
        entry.setSmallIcon(GraphsElementTypes.getImageDescriptor(GraphsElementTypes.Port_3002));
        entry.setLargeIcon(entry.getSmallIcon());
        return entry;
    }

    /**
     * @generated
     */
    private ToolEntry createHypernode6CreationTool() {
        ArrayList<IElementType> types = new ArrayList<IElementType>(2);
        types.add(GraphsElementTypes.Node_3003);
        types.add(GraphsElementTypes.Node_2002);
        NodeToolEntry entry = new NodeToolEntry(Messages.Hypernode6CreationTool_title,
                Messages.Hypernode6CreationTool_desc, types);
        entry.setId("createHypernode6CreationTool"); //$NON-NLS-1$
        entry.setSmallIcon(GraphsElementTypes.getImageDescriptor(GraphsElementTypes.Node_3003));
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
        private final List<IElementType> elementTypes;

        /**
         * @generated
         */
        private NodeToolEntry(String title, String description, List<IElementType> elementTypes) {
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
        private final List<IElementType> relationshipTypes;

        /**
         * @generated
         */
        private LinkToolEntry(String title, String description, List<IElementType> relationshipTypes) {
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
