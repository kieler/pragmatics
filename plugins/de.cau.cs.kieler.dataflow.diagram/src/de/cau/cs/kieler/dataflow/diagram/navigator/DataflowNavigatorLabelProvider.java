/*
* KIELER - Kiel Integrated Environment for Layout for the Eclipse RCP
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
package de.cau.cs.kieler.dataflow.diagram.navigator;

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

import de.cau.cs.kieler.dataflow.DataflowModel;
import de.cau.cs.kieler.dataflow.diagram.edit.parts.Box2EditPart;
import de.cau.cs.kieler.dataflow.diagram.edit.parts.BoxEditPart;
import de.cau.cs.kieler.dataflow.diagram.edit.parts.BoxName2EditPart;
import de.cau.cs.kieler.dataflow.diagram.edit.parts.BoxNameEditPart;
import de.cau.cs.kieler.dataflow.diagram.edit.parts.ConnectionEditPart;
import de.cau.cs.kieler.dataflow.diagram.edit.parts.DataflowModelEditPart;
import de.cau.cs.kieler.dataflow.diagram.edit.parts.InputPortEditPart;
import de.cau.cs.kieler.dataflow.diagram.edit.parts.InputPortNameEditPart;
import de.cau.cs.kieler.dataflow.diagram.edit.parts.OutputPortEditPart;
import de.cau.cs.kieler.dataflow.diagram.edit.parts.OutputPortNameEditPart;
import de.cau.cs.kieler.dataflow.diagram.part.DataflowDiagramEditorPlugin;
import de.cau.cs.kieler.dataflow.diagram.part.DataflowVisualIDRegistry;
import de.cau.cs.kieler.dataflow.diagram.providers.DataflowElementTypes;
import de.cau.cs.kieler.dataflow.diagram.providers.DataflowParserProvider;

/**
 * @generated
 */
public class DataflowNavigatorLabelProvider extends LabelProvider implements ICommonLabelProvider,
        ITreePathLabelProvider {

    /**
     * @generated
     */
    static {
        DataflowDiagramEditorPlugin.getInstance().getImageRegistry().put(
                "Navigator?UnknownElement", ImageDescriptor.getMissingImageDescriptor()); //$NON-NLS-1$
        DataflowDiagramEditorPlugin.getInstance().getImageRegistry().put(
                "Navigator?ImageNotFound", ImageDescriptor.getMissingImageDescriptor()); //$NON-NLS-1$
    }

    /**
     * @generated
     */
    public void updateLabel(ViewerLabel label, TreePath elementPath) {
        Object element = elementPath.getLastSegment();
        if (element instanceof DataflowNavigatorItem
                && !isOwnView(((DataflowNavigatorItem) element).getView())) {
            return;
        }
        label.setText(getText(element));
        label.setImage(getImage(element));
    }

    /**
     * @generated
     */
    public Image getImage(Object element) {
        if (element instanceof DataflowNavigatorGroup) {
            DataflowNavigatorGroup group = (DataflowNavigatorGroup) element;
            return DataflowDiagramEditorPlugin.getInstance().getBundledImage(group.getIcon());
        }

        if (element instanceof DataflowNavigatorItem) {
            DataflowNavigatorItem navigatorItem = (DataflowNavigatorItem) element;
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
        switch (DataflowVisualIDRegistry.getVisualID(view)) {
        case DataflowModelEditPart.VISUAL_ID:
            return getImage(
                    "Navigator?Diagram?http://kieler.cs.cau.de/Dataflow?DataflowModel", DataflowElementTypes.DataflowModel_1000); //$NON-NLS-1$
        case BoxEditPart.VISUAL_ID:
            return getImage(
                    "Navigator?TopLevelNode?http://kieler.cs.cau.de/Dataflow?Box", DataflowElementTypes.Box_2001); //$NON-NLS-1$
        case InputPortEditPart.VISUAL_ID:
            return getImage(
                    "Navigator?Node?http://kieler.cs.cau.de/Dataflow?InputPort", DataflowElementTypes.InputPort_3001); //$NON-NLS-1$
        case OutputPortEditPart.VISUAL_ID:
            return getImage(
                    "Navigator?Node?http://kieler.cs.cau.de/Dataflow?OutputPort", DataflowElementTypes.OutputPort_3002); //$NON-NLS-1$
        case Box2EditPart.VISUAL_ID:
            return getImage(
                    "Navigator?Node?http://kieler.cs.cau.de/Dataflow?Box", DataflowElementTypes.Box_3003); //$NON-NLS-1$
        case ConnectionEditPart.VISUAL_ID:
            return getImage(
                    "Navigator?Link?http://kieler.cs.cau.de/Dataflow?Connection", DataflowElementTypes.Connection_4001); //$NON-NLS-1$
        }
        return getImage("Navigator?UnknownElement", null); //$NON-NLS-1$
    }

    /**
     * @generated
     */
    private Image getImage(String key, IElementType elementType) {
        ImageRegistry imageRegistry = DataflowDiagramEditorPlugin.getInstance().getImageRegistry();
        Image image = imageRegistry.get(key);
        if (image == null && elementType != null
                && DataflowElementTypes.isKnownElementType(elementType)) {
            image = DataflowElementTypes.getImage(elementType);
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
        if (element instanceof DataflowNavigatorGroup) {
            DataflowNavigatorGroup group = (DataflowNavigatorGroup) element;
            return group.getGroupName();
        }

        if (element instanceof DataflowNavigatorItem) {
            DataflowNavigatorItem navigatorItem = (DataflowNavigatorItem) element;
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
        switch (DataflowVisualIDRegistry.getVisualID(view)) {
        case DataflowModelEditPart.VISUAL_ID:
            return getDataflowModel_1000Text(view);
        case BoxEditPart.VISUAL_ID:
            return getBox_2001Text(view);
        case InputPortEditPart.VISUAL_ID:
            return getInputPort_3001Text(view);
        case OutputPortEditPart.VISUAL_ID:
            return getOutputPort_3002Text(view);
        case Box2EditPart.VISUAL_ID:
            return getBox_3003Text(view);
        case ConnectionEditPart.VISUAL_ID:
            return getConnection_4001Text(view);
        }
        return getUnknownElementText(view);
    }

    /**
     * @generated
     */
    private String getDataflowModel_1000Text(View view) {
        DataflowModel domainModelElement = (DataflowModel) view.getElement();
        if (domainModelElement != null) {
            return domainModelElement.getName();
        }
        else {
            DataflowDiagramEditorPlugin.getInstance().logError(
                    "No domain element for view with visualID = " + 1000); //$NON-NLS-1$
            return ""; //$NON-NLS-1$
        }
    }

    /**
     * @generated
     */
    private String getBox_2001Text(View view) {
        IParser parser = DataflowParserProvider.getParser(DataflowElementTypes.Box_2001, view
                .getElement() != null ? view.getElement() : view, DataflowVisualIDRegistry
                .getType(BoxNameEditPart.VISUAL_ID));
        if (parser != null) {
            return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view
                    .getElement() : view), ParserOptions.NONE.intValue());
        }
        else {
            DataflowDiagramEditorPlugin.getInstance().logError(
                    "Parser was not found for label " + 5004); //$NON-NLS-1$
            return ""; //$NON-NLS-1$
        }
    }

    /**
     * @generated
     */
    private String getInputPort_3001Text(View view) {
        IParser parser = DataflowParserProvider.getParser(DataflowElementTypes.InputPort_3001, view
                .getElement() != null ? view.getElement() : view, DataflowVisualIDRegistry
                .getType(InputPortNameEditPart.VISUAL_ID));
        if (parser != null) {
            return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view
                    .getElement() : view), ParserOptions.NONE.intValue());
        }
        else {
            DataflowDiagramEditorPlugin.getInstance().logError(
                    "Parser was not found for label " + 5001); //$NON-NLS-1$
            return ""; //$NON-NLS-1$
        }
    }

    /**
     * @generated
     */
    private String getOutputPort_3002Text(View view) {
        IParser parser = DataflowParserProvider.getParser(DataflowElementTypes.OutputPort_3002,
                view.getElement() != null ? view.getElement() : view, DataflowVisualIDRegistry
                        .getType(OutputPortNameEditPart.VISUAL_ID));
        if (parser != null) {
            return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view
                    .getElement() : view), ParserOptions.NONE.intValue());
        }
        else {
            DataflowDiagramEditorPlugin.getInstance().logError(
                    "Parser was not found for label " + 5002); //$NON-NLS-1$
            return ""; //$NON-NLS-1$
        }
    }

    /**
     * @generated
     */
    private String getBox_3003Text(View view) {
        IParser parser = DataflowParserProvider.getParser(DataflowElementTypes.Box_3003, view
                .getElement() != null ? view.getElement() : view, DataflowVisualIDRegistry
                .getType(BoxName2EditPart.VISUAL_ID));
        if (parser != null) {
            return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view
                    .getElement() : view), ParserOptions.NONE.intValue());
        }
        else {
            DataflowDiagramEditorPlugin.getInstance().logError(
                    "Parser was not found for label " + 5003); //$NON-NLS-1$
            return ""; //$NON-NLS-1$
        }
    }

    /**
     * @generated
     */
    private String getConnection_4001Text(View view) {
        return ""; //$NON-NLS-1$
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
        return DataflowModelEditPart.MODEL_ID.equals(DataflowVisualIDRegistry.getModelID(view));
    }

}
