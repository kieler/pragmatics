package de.cau.cs.rtprak.graphs.diagram.part;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.Tool;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeConnectionTool;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeCreationTool;

import de.cau.cs.rtprak.graphs.diagram.providers.GraphsElementTypes;

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
	 * Creates "Graphs" palette tool group
	 * @generated
	 */
	private PaletteContainer createGraphs1Group() {
		PaletteGroup paletteContainer = new PaletteGroup(
				Messages.Graphs1Group_title);
		paletteContainer.setId("createGraphs1Group"); //$NON-NLS-1$
		paletteContainer.add(createNode1CreationTool());
		paletteContainer.add(createCompoundNode2CreationTool());
		paletteContainer.add(createEdge3CreationTool());
		return paletteContainer;
	}

	/**
	 * @generated
	 */
	private ToolEntry createNode1CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(2);
		types.add(GraphsElementTypes.Node_2001);
		types.add(GraphsElementTypes.Node_3001);
		NodeToolEntry entry = new NodeToolEntry(
				Messages.Node1CreationTool_title, null, types);
		entry.setId("createNode1CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(GraphsElementTypes
				.getImageDescriptor(GraphsElementTypes.Node_2001));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createCompoundNode2CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(2);
		types.add(GraphsElementTypes.CompoundNode_2002);
		types.add(GraphsElementTypes.CompoundNode_3002);
		NodeToolEntry entry = new NodeToolEntry(
				Messages.CompoundNode2CreationTool_title, null, types);
		entry.setId("createCompoundNode2CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(GraphsElementTypes
				.getImageDescriptor(GraphsElementTypes.CompoundNode_2002));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createEdge3CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(GraphsElementTypes.Edge_4001);
		LinkToolEntry entry = new LinkToolEntry(
				Messages.Edge3CreationTool_title, null, types);
		entry.setId("createEdge3CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(GraphsElementTypes
				.getImageDescriptor(GraphsElementTypes.Edge_4001));
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
