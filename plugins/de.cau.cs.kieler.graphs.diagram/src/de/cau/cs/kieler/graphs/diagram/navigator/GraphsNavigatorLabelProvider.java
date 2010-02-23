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

import de.cau.cs.kieler.graphs.diagram.edit.parts.CompoundNode2EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.CompoundNodeEditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.CompoundNodeLabel2EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.CompoundNodeLabelEditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeEditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeLabelEditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.GraphEditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.Node2EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.NodeEditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.NodeLabel2EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.NodeLabelEditPart;
import de.cau.cs.kieler.graphs.diagram.part.GraphsDiagramEditorPlugin;
import de.cau.cs.kieler.graphs.diagram.part.GraphsVisualIDRegistry;
import de.cau.cs.kieler.graphs.diagram.providers.GraphsElementTypes;
import de.cau.cs.kieler.graphs.diagram.providers.GraphsParserProvider;

/**
 * @generated
 */
public class GraphsNavigatorLabelProvider extends LabelProvider implements ICommonLabelProvider,
        ITreePathLabelProvider {

    /**
     * @generated
     */
    static {
        GraphsDiagramEditorPlugin.getInstance().getImageRegistry().put(
                "Navigator?UnknownElement", ImageDescriptor.getMissingImageDescriptor()); //$NON-NLS-1$
        GraphsDiagramEditorPlugin.getInstance().getImageRegistry().put(
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
            return GraphsDiagramEditorPlugin.getInstance().getBundledImage(group.getIcon());
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
        case GraphEditPart.VISUAL_ID:
            return getImage(
                    "Navigator?Diagram?http://kieler.cs.cau.de/Graphs?Graph", GraphsElementTypes.Graph_1000); //$NON-NLS-1$
        case NodeEditPart.VISUAL_ID:
            return getImage(
                    "Navigator?TopLevelNode?http://kieler.cs.cau.de/Graphs?Node", GraphsElementTypes.Node_2003); //$NON-NLS-1$
        case CompoundNodeEditPart.VISUAL_ID:
            return getImage(
                    "Navigator?TopLevelNode?http://kieler.cs.cau.de/Graphs?CompoundNode", GraphsElementTypes.CompoundNode_2004); //$NON-NLS-1$
        case Node2EditPart.VISUAL_ID:
            return getImage(
                    "Navigator?Node?http://kieler.cs.cau.de/Graphs?Node", GraphsElementTypes.Node_3003); //$NON-NLS-1$
        case CompoundNode2EditPart.VISUAL_ID:
            return getImage(
                    "Navigator?Node?http://kieler.cs.cau.de/Graphs?CompoundNode", GraphsElementTypes.CompoundNode_3004); //$NON-NLS-1$
        case EdgeEditPart.VISUAL_ID:
            return getImage(
                    "Navigator?Link?http://kieler.cs.cau.de/Graphs?Edge", GraphsElementTypes.Edge_4002); //$NON-NLS-1$
        }
        return getImage("Navigator?UnknownElement", null); //$NON-NLS-1$
    }

    /**
     * @generated
     */
    private Image getImage(String key, IElementType elementType) {
        ImageRegistry imageRegistry = GraphsDiagramEditorPlugin.getInstance().getImageRegistry();
        Image image = imageRegistry.get(key);
        if (image == null && elementType != null && GraphsElementTypes.isKnownElementType(elementType)) {
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
        case GraphEditPart.VISUAL_ID:
            return getGraph_1000Text(view);
        case NodeEditPart.VISUAL_ID:
            return getNode_2003Text(view);
        case CompoundNodeEditPart.VISUAL_ID:
            return getCompoundNode_2004Text(view);
        case Node2EditPart.VISUAL_ID:
            return getNode_3003Text(view);
        case CompoundNode2EditPart.VISUAL_ID:
            return getCompoundNode_3004Text(view);
        case EdgeEditPart.VISUAL_ID:
            return getEdge_4002Text(view);
        }
        return getUnknownElementText(view);
    }

    /**
     * @generated
     */
    private String getGraph_1000Text(View view) {
        return ""; //$NON-NLS-1$
    }

    /**
     * @generated
     */
    private String getNode_2003Text(View view) {
        IParser parser = GraphsParserProvider.getParser(GraphsElementTypes.Node_2003,
                view.getElement() != null ? view.getElement() : view, GraphsVisualIDRegistry
                        .getType(NodeLabelEditPart.VISUAL_ID));
        if (parser != null) {
            return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view
                    .getElement() : view), ParserOptions.NONE.intValue());
        } else {
            GraphsDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5005); //$NON-NLS-1$
            return ""; //$NON-NLS-1$
        }
    }

    /**
     * @generated
     */
    private String getCompoundNode_2004Text(View view) {
        IParser parser = GraphsParserProvider.getParser(GraphsElementTypes.CompoundNode_2004, view
                .getElement() != null ? view.getElement() : view, GraphsVisualIDRegistry
                .getType(CompoundNodeLabelEditPart.VISUAL_ID));
        if (parser != null) {
            return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view
                    .getElement() : view), ParserOptions.NONE.intValue());
        } else {
            GraphsDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5008); //$NON-NLS-1$
            return ""; //$NON-NLS-1$
        }
    }

    /**
     * @generated
     */
    private String getNode_3003Text(View view) {
        IParser parser = GraphsParserProvider.getParser(GraphsElementTypes.Node_3003,
                view.getElement() != null ? view.getElement() : view, GraphsVisualIDRegistry
                        .getType(NodeLabel2EditPart.VISUAL_ID));
        if (parser != null) {
            return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view
                    .getElement() : view), ParserOptions.NONE.intValue());
        } else {
            GraphsDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5006); //$NON-NLS-1$
            return ""; //$NON-NLS-1$
        }
    }

    /**
     * @generated
     */
    private String getCompoundNode_3004Text(View view) {
        IParser parser = GraphsParserProvider.getParser(GraphsElementTypes.CompoundNode_3004, view
                .getElement() != null ? view.getElement() : view, GraphsVisualIDRegistry
                .getType(CompoundNodeLabel2EditPart.VISUAL_ID));
        if (parser != null) {
            return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view
                    .getElement() : view), ParserOptions.NONE.intValue());
        } else {
            GraphsDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5007); //$NON-NLS-1$
            return ""; //$NON-NLS-1$
        }
    }

    /**
     * @generated
     */
    private String getEdge_4002Text(View view) {
        IParser parser = GraphsParserProvider.getParser(GraphsElementTypes.Edge_4002,
                view.getElement() != null ? view.getElement() : view, GraphsVisualIDRegistry
                        .getType(EdgeLabelEditPart.VISUAL_ID));
        if (parser != null) {
            return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view
                    .getElement() : view), ParserOptions.NONE.intValue());
        } else {
            GraphsDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 6002); //$NON-NLS-1$
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
        return GraphEditPart.MODEL_ID.equals(GraphsVisualIDRegistry.getModelID(view));
    }

}
