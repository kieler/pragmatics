package de.cau.cs.kieler.graphs.diagram.navigator;

import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserOptions;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.ITreePathLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.ViewerLabel;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.ICommonLabelProvider;

import de.cau.cs.kieler.graphs.Node;
import de.cau.cs.kieler.graphs.diagram.edit.parts.Edge2EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.Edge3EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.Edge4EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.Edge5EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.Edge6EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.Edge7EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.Edge8EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeEditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeHeadLabel12EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeHeadLabel13EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeHeadLabel14EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeHeadLabel15EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeHeadLabel16EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeHeadLabel17EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeHeadLabel18EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeHeadLabel1EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.Node2EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.Node3EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.Node4EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.Node5EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.NodeEditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.NodeNodeLabel2EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.NodeNodeLabelEditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.PortEditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.PortPortLabelEditPart;
import de.cau.cs.kieler.graphs.diagram.part.GraphsDiagramEditorPlugin;
import de.cau.cs.kieler.graphs.diagram.part.GraphsVisualIDRegistry;
import de.cau.cs.kieler.graphs.diagram.providers.GraphsElementTypes;
import de.cau.cs.kieler.graphs.diagram.providers.GraphsParserProvider;

/**
 * @generated
 */
public class GraphsNavigatorLabelProvider extends LabelProvider implements
        ICommonLabelProvider, ITreePathLabelProvider {

    /**
     * @generated
     */
    static {
        GraphsDiagramEditorPlugin
                .getInstance()
                .getImageRegistry()
                .put(
                        "Navigator?UnknownElement", ImageDescriptor.getMissingImageDescriptor()); //$NON-NLS-1$
        GraphsDiagramEditorPlugin
                .getInstance()
                .getImageRegistry()
                .put(
                        "Navigator?ImageNotFound", ImageDescriptor.getMissingImageDescriptor()); //$NON-NLS-1$
    }

    /**
     * @generated
     */
    public void updateLabel(ViewerLabel label, TreePath elementPath) {
        Object element = elementPath.getLastSegment();
        if (element instanceof GraphsNavigatorItem
                && !isOwnView(((GraphsNavigatorItem) element).getView())) {
            return;
        }
        label.setText(getText(element));
        label.setImage(getImage(element));
    }

    /**
     * @generated
     */
    public Image getImage(Object element) {
        if (element instanceof GraphsNavigatorGroup) {
            GraphsNavigatorGroup group = (GraphsNavigatorGroup) element;
            return GraphsDiagramEditorPlugin.getInstance().getBundledImage(
                    group.getIcon());
        }

        if (element instanceof GraphsNavigatorItem) {
            GraphsNavigatorItem navigatorItem = (GraphsNavigatorItem) element;
            if (!isOwnView(navigatorItem.getView())) {
                return super.getImage(element);
            }
            return getImage(navigatorItem.getView());
        }

        return super.getImage(element);
    }

    /**
     * @generated
     */
    public Image getImage(View view) {
        switch (GraphsVisualIDRegistry.getVisualID(view)) {
        case NodeEditPart.VISUAL_ID:
            return getImage(
                    "Navigator?Diagram?http://kieler.cs.cau.de/graphs?Node", GraphsElementTypes.Node_1000); //$NON-NLS-1$
        case Node2EditPart.VISUAL_ID:
            return getImage(
                    "Navigator?TopLevelNode?http://kieler.cs.cau.de/graphs?Node", GraphsElementTypes.Node_2001); //$NON-NLS-1$
        case Node3EditPart.VISUAL_ID:
            return getImage(
                    "Navigator?TopLevelNode?http://kieler.cs.cau.de/graphs?Node", GraphsElementTypes.Node_2002); //$NON-NLS-1$
        case Node4EditPart.VISUAL_ID:
            return getImage(
                    "Navigator?Node?http://kieler.cs.cau.de/graphs?Node", GraphsElementTypes.Node_3001); //$NON-NLS-1$
        case PortEditPart.VISUAL_ID:
            return getImage(
                    "Navigator?Node?http://kieler.cs.cau.de/graphs?Port", GraphsElementTypes.Port_3002); //$NON-NLS-1$
        case Node5EditPart.VISUAL_ID:
            return getImage(
                    "Navigator?Node?http://kieler.cs.cau.de/graphs?Node", GraphsElementTypes.Node_3003); //$NON-NLS-1$
        case EdgeEditPart.VISUAL_ID:
            return getImage(
                    "Navigator?Link?http://kieler.cs.cau.de/graphs?Edge", GraphsElementTypes.Edge_4001); //$NON-NLS-1$
        case Edge2EditPart.VISUAL_ID:
            return getImage(
                    "Navigator?Link?http://kieler.cs.cau.de/graphs?Edge", GraphsElementTypes.Edge_4002); //$NON-NLS-1$
        case Edge3EditPart.VISUAL_ID:
            return getImage(
                    "Navigator?Link?http://kieler.cs.cau.de/graphs?Edge", GraphsElementTypes.Edge_4003); //$NON-NLS-1$
        case Edge4EditPart.VISUAL_ID:
            return getImage(
                    "Navigator?Link?http://kieler.cs.cau.de/graphs?Edge", GraphsElementTypes.Edge_4004); //$NON-NLS-1$
        case Edge5EditPart.VISUAL_ID:
            return getImage(
                    "Navigator?Link?http://kieler.cs.cau.de/graphs?Edge", GraphsElementTypes.Edge_4005); //$NON-NLS-1$
        case Edge6EditPart.VISUAL_ID:
            return getImage(
                    "Navigator?Link?http://kieler.cs.cau.de/graphs?Edge", GraphsElementTypes.Edge_4006); //$NON-NLS-1$
        case Edge7EditPart.VISUAL_ID:
            return getImage(
                    "Navigator?Link?http://kieler.cs.cau.de/graphs?Edge", GraphsElementTypes.Edge_4007); //$NON-NLS-1$
        case Edge8EditPart.VISUAL_ID:
            return getImage(
                    "Navigator?Link?http://kieler.cs.cau.de/graphs?Edge", GraphsElementTypes.Edge_4008); //$NON-NLS-1$
        }
        return getImage("Navigator?UnknownElement", null); //$NON-NLS-1$
    }

    /**
     * @generated
     */
    private Image getImage(String key, IElementType elementType) {
        ImageRegistry imageRegistry = GraphsDiagramEditorPlugin.getInstance()
                .getImageRegistry();
        Image image = imageRegistry.get(key);
        if (image == null && elementType != null
                && GraphsElementTypes.isKnownElementType(elementType)) {
            image = GraphsElementTypes.getImage(elementType);
            imageRegistry.put(key, image);
        }

        if (image == null) {
            image = imageRegistry.get("Navigator?ImageNotFound"); //$NON-NLS-1$
            imageRegistry.put(key, image);
        }
        return image;
    }

    /**
     * @generated
     */
    public String getText(Object element) {
        if (element instanceof GraphsNavigatorGroup) {
            GraphsNavigatorGroup group = (GraphsNavigatorGroup) element;
            return group.getGroupName();
        }

        if (element instanceof GraphsNavigatorItem) {
            GraphsNavigatorItem navigatorItem = (GraphsNavigatorItem) element;
            if (!isOwnView(navigatorItem.getView())) {
                return null;
            }
            return getText(navigatorItem.getView());
        }

        return super.getText(element);
    }

    /**
     * @generated
     */
    public String getText(View view) {
        if (view.getElement() != null && view.getElement().eIsProxy()) {
            return getUnresolvedDomainElementProxyText(view);
        }
        switch (GraphsVisualIDRegistry.getVisualID(view)) {
        case NodeEditPart.VISUAL_ID:
            return getNode_1000Text(view);
        case Node2EditPart.VISUAL_ID:
            return getNode_2001Text(view);
        case Node3EditPart.VISUAL_ID:
            return getNode_2002Text(view);
        case Node4EditPart.VISUAL_ID:
            return getNode_3001Text(view);
        case PortEditPart.VISUAL_ID:
            return getPort_3002Text(view);
        case Node5EditPart.VISUAL_ID:
            return getNode_3003Text(view);
        case EdgeEditPart.VISUAL_ID:
            return getEdge_4001Text(view);
        case Edge2EditPart.VISUAL_ID:
            return getEdge_4002Text(view);
        case Edge3EditPart.VISUAL_ID:
            return getEdge_4003Text(view);
        case Edge4EditPart.VISUAL_ID:
            return getEdge_4004Text(view);
        case Edge5EditPart.VISUAL_ID:
            return getEdge_4005Text(view);
        case Edge6EditPart.VISUAL_ID:
            return getEdge_4006Text(view);
        case Edge7EditPart.VISUAL_ID:
            return getEdge_4007Text(view);
        case Edge8EditPart.VISUAL_ID:
            return getEdge_4008Text(view);
        }
        return getUnknownElementText(view);
    }

    /**
     * @generated
     */
    private String getNode_1000Text(View view) {
        Node domainModelElement = (Node) view.getElement();
        if (domainModelElement != null) {
            return domainModelElement.getNodeLabel();
        } else {
            GraphsDiagramEditorPlugin.getInstance().logError(
                    "No domain element for view with visualID = " + 1000); //$NON-NLS-1$
            return ""; //$NON-NLS-1$
        }
    }

    /**
     * @generated
     */
    private String getNode_2001Text(View view) {
        IParser parser = GraphsParserProvider.getParser(
                GraphsElementTypes.Node_2001, view.getElement() != null ? view
                        .getElement() : view, GraphsVisualIDRegistry
                        .getType(NodeNodeLabelEditPart.VISUAL_ID));
        if (parser != null) {
            return parser.getPrintString(new EObjectAdapter(
                    view.getElement() != null ? view.getElement() : view),
                    ParserOptions.NONE.intValue());
        } else {
            GraphsDiagramEditorPlugin.getInstance().logError(
                    "Parser was not found for label " + 5003); //$NON-NLS-1$
            return ""; //$NON-NLS-1$
        }
    }

    /**
     * @generated
     */
    private String getNode_2002Text(View view) {
        Node domainModelElement = (Node) view.getElement();
        if (domainModelElement != null) {
            return domainModelElement.getNodeLabel();
        } else {
            GraphsDiagramEditorPlugin.getInstance().logError(
                    "No domain element for view with visualID = " + 2002); //$NON-NLS-1$
            return ""; //$NON-NLS-1$
        }
    }

    /**
     * @generated
     */
    private String getNode_3001Text(View view) {
        IParser parser = GraphsParserProvider.getParser(
                GraphsElementTypes.Node_3001, view.getElement() != null ? view
                        .getElement() : view, GraphsVisualIDRegistry
                        .getType(NodeNodeLabel2EditPart.VISUAL_ID));
        if (parser != null) {
            return parser.getPrintString(new EObjectAdapter(
                    view.getElement() != null ? view.getElement() : view),
                    ParserOptions.NONE.intValue());
        } else {
            GraphsDiagramEditorPlugin.getInstance().logError(
                    "Parser was not found for label " + 5002); //$NON-NLS-1$
            return ""; //$NON-NLS-1$
        }
    }

    /**
     * @generated
     */
    private String getPort_3002Text(View view) {
        IParser parser = GraphsParserProvider.getParser(
                GraphsElementTypes.Port_3002, view.getElement() != null ? view
                        .getElement() : view, GraphsVisualIDRegistry
                        .getType(PortPortLabelEditPart.VISUAL_ID));
        if (parser != null) {
            return parser.getPrintString(new EObjectAdapter(
                    view.getElement() != null ? view.getElement() : view),
                    ParserOptions.NONE.intValue());
        } else {
            GraphsDiagramEditorPlugin.getInstance().logError(
                    "Parser was not found for label " + 5001); //$NON-NLS-1$
            return ""; //$NON-NLS-1$
        }
    }

    /**
     * @generated
     */
    private String getNode_3003Text(View view) {
        Node domainModelElement = (Node) view.getElement();
        if (domainModelElement != null) {
            return domainModelElement.getNodeLabel();
        } else {
            GraphsDiagramEditorPlugin.getInstance().logError(
                    "No domain element for view with visualID = " + 3003); //$NON-NLS-1$
            return ""; //$NON-NLS-1$
        }
    }

    /**
     * @generated
     */
    private String getEdge_4001Text(View view) {
        IParser parser = GraphsParserProvider.getParser(
                GraphsElementTypes.Edge_4001, view.getElement() != null ? view
                        .getElement() : view, GraphsVisualIDRegistry
                        .getType(EdgeHeadLabel1EditPart.VISUAL_ID));
        if (parser != null) {
            return parser.getPrintString(new EObjectAdapter(
                    view.getElement() != null ? view.getElement() : view),
                    ParserOptions.NONE.intValue());
        } else {
            GraphsDiagramEditorPlugin.getInstance().logError(
                    "Parser was not found for label " + 6001); //$NON-NLS-1$
            return ""; //$NON-NLS-1$
        }
    }

    /**
     * @generated
     */
    private String getEdge_4002Text(View view) {
        IParser parser = GraphsParserProvider.getParser(
                GraphsElementTypes.Edge_4002, view.getElement() != null ? view
                        .getElement() : view, GraphsVisualIDRegistry
                        .getType(EdgeHeadLabel12EditPart.VISUAL_ID));
        if (parser != null) {
            return parser.getPrintString(new EObjectAdapter(
                    view.getElement() != null ? view.getElement() : view),
                    ParserOptions.NONE.intValue());
        } else {
            GraphsDiagramEditorPlugin.getInstance().logError(
                    "Parser was not found for label " + 6006); //$NON-NLS-1$
            return ""; //$NON-NLS-1$
        }
    }

    /**
     * @generated
     */
    private String getEdge_4003Text(View view) {
        IParser parser = GraphsParserProvider.getParser(
                GraphsElementTypes.Edge_4003, view.getElement() != null ? view
                        .getElement() : view, GraphsVisualIDRegistry
                        .getType(EdgeHeadLabel13EditPart.VISUAL_ID));
        if (parser != null) {
            return parser.getPrintString(new EObjectAdapter(
                    view.getElement() != null ? view.getElement() : view),
                    ParserOptions.NONE.intValue());
        } else {
            GraphsDiagramEditorPlugin.getInstance().logError(
                    "Parser was not found for label " + 6011); //$NON-NLS-1$
            return ""; //$NON-NLS-1$
        }
    }

    /**
     * @generated
     */
    private String getEdge_4004Text(View view) {
        IParser parser = GraphsParserProvider.getParser(
                GraphsElementTypes.Edge_4004, view.getElement() != null ? view
                        .getElement() : view, GraphsVisualIDRegistry
                        .getType(EdgeHeadLabel14EditPart.VISUAL_ID));
        if (parser != null) {
            return parser.getPrintString(new EObjectAdapter(
                    view.getElement() != null ? view.getElement() : view),
                    ParserOptions.NONE.intValue());
        } else {
            GraphsDiagramEditorPlugin.getInstance().logError(
                    "Parser was not found for label " + 6016); //$NON-NLS-1$
            return ""; //$NON-NLS-1$
        }
    }

    /**
     * @generated
     */
    private String getEdge_4005Text(View view) {
        IParser parser = GraphsParserProvider.getParser(
                GraphsElementTypes.Edge_4005, view.getElement() != null ? view
                        .getElement() : view, GraphsVisualIDRegistry
                        .getType(EdgeHeadLabel15EditPart.VISUAL_ID));
        if (parser != null) {
            return parser.getPrintString(new EObjectAdapter(
                    view.getElement() != null ? view.getElement() : view),
                    ParserOptions.NONE.intValue());
        } else {
            GraphsDiagramEditorPlugin.getInstance().logError(
                    "Parser was not found for label " + 6021); //$NON-NLS-1$
            return ""; //$NON-NLS-1$
        }
    }

    /**
     * @generated
     */
    private String getEdge_4006Text(View view) {
        IParser parser = GraphsParserProvider.getParser(
                GraphsElementTypes.Edge_4006, view.getElement() != null ? view
                        .getElement() : view, GraphsVisualIDRegistry
                        .getType(EdgeHeadLabel16EditPart.VISUAL_ID));
        if (parser != null) {
            return parser.getPrintString(new EObjectAdapter(
                    view.getElement() != null ? view.getElement() : view),
                    ParserOptions.NONE.intValue());
        } else {
            GraphsDiagramEditorPlugin.getInstance().logError(
                    "Parser was not found for label " + 6026); //$NON-NLS-1$
            return ""; //$NON-NLS-1$
        }
    }

    /**
     * @generated
     */
    private String getEdge_4007Text(View view) {
        IParser parser = GraphsParserProvider.getParser(
                GraphsElementTypes.Edge_4007, view.getElement() != null ? view
                        .getElement() : view, GraphsVisualIDRegistry
                        .getType(EdgeHeadLabel17EditPart.VISUAL_ID));
        if (parser != null) {
            return parser.getPrintString(new EObjectAdapter(
                    view.getElement() != null ? view.getElement() : view),
                    ParserOptions.NONE.intValue());
        } else {
            GraphsDiagramEditorPlugin.getInstance().logError(
                    "Parser was not found for label " + 6031); //$NON-NLS-1$
            return ""; //$NON-NLS-1$
        }
    }

    /**
     * @generated
     */
    private String getEdge_4008Text(View view) {
        IParser parser = GraphsParserProvider.getParser(
                GraphsElementTypes.Edge_4008, view.getElement() != null ? view
                        .getElement() : view, GraphsVisualIDRegistry
                        .getType(EdgeHeadLabel18EditPart.VISUAL_ID));
        if (parser != null) {
            return parser.getPrintString(new EObjectAdapter(
                    view.getElement() != null ? view.getElement() : view),
                    ParserOptions.NONE.intValue());
        } else {
            GraphsDiagramEditorPlugin.getInstance().logError(
                    "Parser was not found for label " + 6036); //$NON-NLS-1$
            return ""; //$NON-NLS-1$
        }
    }

    /**
     * @generated
     */
    private String getUnknownElementText(View view) {
        return "<UnknownElement Visual_ID = " + view.getType() + ">"; //$NON-NLS-1$  //$NON-NLS-2$
    }

    /**
     * @generated
     */
    private String getUnresolvedDomainElementProxyText(View view) {
        return "<Unresolved domain element Visual_ID = " + view.getType() + ">"; //$NON-NLS-1$  //$NON-NLS-2$
    }

    /**
     * @generated
     */
    public void init(ICommonContentExtensionSite aConfig) {
    }

    /**
     * @generated
     */
    public void restoreState(IMemento aMemento) {
    }

    /**
     * @generated
     */
    public void saveState(IMemento aMemento) {
    }

    /**
     * @generated
     */
    public String getDescription(Object anElement) {
        return null;
    }

    /**
     * @generated
     */
    private boolean isOwnView(View view) {
        return NodeEditPart.MODEL_ID.equals(GraphsVisualIDRegistry
                .getModelID(view));
    }

}
