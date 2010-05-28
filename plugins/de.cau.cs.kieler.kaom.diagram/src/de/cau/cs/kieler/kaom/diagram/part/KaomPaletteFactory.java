package de.cau.cs.kieler.kaom.diagram.part;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.Tool;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeConnectionTool;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeCreationTool;

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
		PaletteGroup paletteContainer = new PaletteGroup(
				Messages.Kaom1Group_title);
		paletteContainer.setId("createKaom1Group"); //$NON-NLS-1$
		paletteContainer.add(createActor1CreationTool());
		paletteContainer.add(createState2CreationTool());
		paletteContainer.add(createPort3CreationTool());
		paletteContainer.add(createRelation4CreationTool());
		paletteContainer.add(createLink5CreationTool());
		return paletteContainer;
	}

	/**
	 * @generated
	 */
	private ToolEntry createActor1CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(2);
		types.add(KaomElementTypes.Actor_2001);
		types.add(KaomElementTypes.Actor_3002);
		NodeToolEntry entry = new NodeToolEntry(
				Messages.Actor1CreationTool_title,
				Messages.Actor1CreationTool_desc, types);
		entry.setId("createActor1CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(KaomElementTypes
				.getImageDescriptor(KaomElementTypes.Actor_2001));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createState2CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(2);
		types.add(KaomElementTypes.State_3003);
		types.add(KaomElementTypes.State_2002);
		NodeToolEntry entry = new NodeToolEntry(
				Messages.State2CreationTool_title,
				Messages.State2CreationTool_desc, types);
		entry.setId("createState2CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(KaomElementTypes
				.getImageDescriptor(KaomElementTypes.State_3003));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createPort3CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(KaomElementTypes.Port_3001);
		NodeToolEntry entry = new NodeToolEntry(
				Messages.Port3CreationTool_title,
				Messages.Port3CreationTool_desc, types);
		entry.setId("createPort3CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(KaomElementTypes
				.getImageDescriptor(KaomElementTypes.Port_3001));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createRelation4CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(2);
		types.add(KaomElementTypes.Relation_3004);
		types.add(KaomElementTypes.Relation_2003);
		NodeToolEntry entry = new NodeToolEntry(
				Messages.Relation4CreationTool_title,
				Messages.Relation4CreationTool_desc, types);
		entry.setId("createRelation4CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(KaomElementTypes
				.getImageDescriptor(KaomElementTypes.Relation_3004));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createLink5CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(KaomElementTypes.Link_4001);
		LinkToolEntry entry = new LinkToolEntry(
				Messages.Link5CreationTool_title,
				Messages.Link5CreationTool_desc, types);
		entry.setId("createLink5CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(KaomElementTypes
				.getImageDescriptor(KaomElementTypes.Link_4001));
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
