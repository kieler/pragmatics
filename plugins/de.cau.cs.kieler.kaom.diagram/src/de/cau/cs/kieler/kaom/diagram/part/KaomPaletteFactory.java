package de.cau.cs.kieler.kaom.diagram.part;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.gef.Tool;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeConnectionTool;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeCreationTool;

import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import de.cau.cs.kieler.kaom.diagram.providers.KaomElementTypes;

/**
 * @generated
 */
public class KaomPaletteFactory {

    /**
     * @generated
     */
    public void fillPalette(PaletteRoot paletteRoot) {
        paletteRoot.add(createKaom1Group());
    }

    /**
     * Creates "kaom" palette tool group
     * @generated
     */
    private PaletteContainer createKaom1Group() {
        PaletteGroup paletteContainer = new PaletteGroup(Messages.Kaom1Group_title);
        paletteContainer.setId("createKaom1Group"); //$NON-NLS-1$
        paletteContainer.add(createEntity1CreationTool());
        paletteContainer.add(createPort2CreationTool());
        paletteContainer.add(createRelation3CreationTool());
        paletteContainer.add(createLink4CreationTool());
        return paletteContainer;
    }

    /**
     * @generated
     */
    private ToolEntry createEntity1CreationTool() {
        ArrayList<IElementType> types = new ArrayList<IElementType>(2);
        types.add(KaomElementTypes.Entity_2001);
        types.add(KaomElementTypes.Entity_3002);
        NodeToolEntry entry = new NodeToolEntry(Messages.Entity1CreationTool_title,
                Messages.Entity1CreationTool_desc, types);
        entry.setId("createEntity1CreationTool"); //$NON-NLS-1$
        entry.setSmallIcon(KaomElementTypes.getImageDescriptor(KaomElementTypes.Entity_2001));
        entry.setLargeIcon(entry.getSmallIcon());
        return entry;
    }

    /**
     * @generated
     */
    private ToolEntry createPort2CreationTool() {
        NodeToolEntry entry = new NodeToolEntry(Messages.Port2CreationTool_title,
                Messages.Port2CreationTool_desc,
                Collections.singletonList(KaomElementTypes.Port_3001));
        entry.setId("createPort2CreationTool"); //$NON-NLS-1$
        entry.setSmallIcon(KaomElementTypes.getImageDescriptor(KaomElementTypes.Port_3001));
        entry.setLargeIcon(entry.getSmallIcon());
        return entry;
    }

    /**
     * @generated
     */
    private ToolEntry createRelation3CreationTool() {
        ArrayList<IElementType> types = new ArrayList<IElementType>(2);
        types.add(KaomElementTypes.Relation_3003);
        types.add(KaomElementTypes.Relation_2002);
        NodeToolEntry entry = new NodeToolEntry(Messages.Relation3CreationTool_title,
                Messages.Relation3CreationTool_desc, types);
        entry.setId("createRelation3CreationTool"); //$NON-NLS-1$
        entry.setSmallIcon(KaomElementTypes.getImageDescriptor(KaomElementTypes.Relation_3003));
        entry.setLargeIcon(entry.getSmallIcon());
        return entry;
    }

    /**
     * @generated
     */
    private ToolEntry createLink4CreationTool() {
        LinkToolEntry entry = new LinkToolEntry(Messages.Link4CreationTool_title,
                Messages.Link4CreationTool_desc,
                Collections.singletonList(KaomElementTypes.Link_4001));
        entry.setId("createLink4CreationTool"); //$NON-NLS-1$
        entry.setSmallIcon(KaomElementTypes.getImageDescriptor(KaomElementTypes.Link_4001));
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
