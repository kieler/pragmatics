package de.cau.cs.kieler.kaom.diagram.navigator;

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

import de.cau.cs.kieler.kaom.Entity;
import de.cau.cs.kieler.kaom.diagram.edit.parts.Entity2EditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.Entity3EditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.EntityEditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.EntityName2EditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.EntityNameEditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.LinkEditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.LinkNameEditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.PortEditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.PortNameEditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.Relation2EditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.RelationEditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.RelationName2EditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.RelationNameEditPart;
import de.cau.cs.kieler.kaom.diagram.part.KaomDiagramEditorPlugin;
import de.cau.cs.kieler.kaom.diagram.part.KaomVisualIDRegistry;
import de.cau.cs.kieler.kaom.diagram.providers.KaomElementTypes;
import de.cau.cs.kieler.kaom.diagram.providers.KaomParserProvider;

/**
 * @generated
 */
public class KaomNavigatorLabelProvider extends LabelProvider implements ICommonLabelProvider,
        ITreePathLabelProvider {

    /**
     * @generated
     */
    static {
        KaomDiagramEditorPlugin.getInstance().getImageRegistry()
                .put("Navigator?UnknownElement", ImageDescriptor.getMissingImageDescriptor()); //$NON-NLS-1$
        KaomDiagramEditorPlugin.getInstance().getImageRegistry()
                .put("Navigator?ImageNotFound", ImageDescriptor.getMissingImageDescriptor()); //$NON-NLS-1$
    }

    /**
     * @generated
     */
    public void updateLabel(ViewerLabel label, TreePath elementPath) {
        Object element = elementPath.getLastSegment();
        if (element instanceof KaomNavigatorItem
                && !isOwnView(((KaomNavigatorItem) element).getView())) {
            return;
        }
        label.setText(getText(element));
        label.setImage(getImage(element));
    }

    /**
     * @generated
     */
    public Image getImage(Object element) {
        if (element instanceof KaomNavigatorGroup) {
            KaomNavigatorGroup group = (KaomNavigatorGroup) element;
            return KaomDiagramEditorPlugin.getInstance().getBundledImage(group.getIcon());
        }

        if (element instanceof KaomNavigatorItem) {
            KaomNavigatorItem navigatorItem = (KaomNavigatorItem) element;
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
        switch (KaomVisualIDRegistry.getVisualID(view)) {
        case Entity3EditPart.VISUAL_ID:
            return getImage(
                    "Navigator?Node?http://kieler.cs.cau.de/KAOM?Entity", KaomElementTypes.Entity_3002); //$NON-NLS-1$
        case Entity2EditPart.VISUAL_ID:
            return getImage(
                    "Navigator?TopLevelNode?http://kieler.cs.cau.de/KAOM?Entity", KaomElementTypes.Entity_2001); //$NON-NLS-1$
        case Relation2EditPart.VISUAL_ID:
            return getImage(
                    "Navigator?Node?http://kieler.cs.cau.de/KAOM?Relation", KaomElementTypes.Relation_3003); //$NON-NLS-1$
        case EntityEditPart.VISUAL_ID:
            return getImage(
                    "Navigator?Diagram?http://kieler.cs.cau.de/KAOM?Entity", KaomElementTypes.Entity_1000); //$NON-NLS-1$
        case RelationEditPart.VISUAL_ID:
            return getImage(
                    "Navigator?TopLevelNode?http://kieler.cs.cau.de/KAOM?Relation", KaomElementTypes.Relation_2002); //$NON-NLS-1$
        case PortEditPart.VISUAL_ID:
            return getImage(
                    "Navigator?Node?http://kieler.cs.cau.de/KAOM?Port", KaomElementTypes.Port_3001); //$NON-NLS-1$
        case LinkEditPart.VISUAL_ID:
            return getImage(
                    "Navigator?Link?http://kieler.cs.cau.de/KAOM?Link", KaomElementTypes.Link_4001); //$NON-NLS-1$
        }
        return getImage("Navigator?UnknownElement", null); //$NON-NLS-1$
    }

    /**
     * @generated
     */
    private Image getImage(String key, IElementType elementType) {
        ImageRegistry imageRegistry = KaomDiagramEditorPlugin.getInstance().getImageRegistry();
        Image image = imageRegistry.get(key);
        if (image == null && elementType != null
                && KaomElementTypes.isKnownElementType(elementType)) {
            image = KaomElementTypes.getImage(elementType);
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
        if (element instanceof KaomNavigatorGroup) {
            KaomNavigatorGroup group = (KaomNavigatorGroup) element;
            return group.getGroupName();
        }

        if (element instanceof KaomNavigatorItem) {
            KaomNavigatorItem navigatorItem = (KaomNavigatorItem) element;
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
        switch (KaomVisualIDRegistry.getVisualID(view)) {
        case Entity3EditPart.VISUAL_ID:
            return getEntity_3002Text(view);
        case Entity2EditPart.VISUAL_ID:
            return getEntity_2001Text(view);
        case Relation2EditPart.VISUAL_ID:
            return getRelation_3003Text(view);
        case EntityEditPart.VISUAL_ID:
            return getEntity_1000Text(view);
        case RelationEditPart.VISUAL_ID:
            return getRelation_2002Text(view);
        case PortEditPart.VISUAL_ID:
            return getPort_3001Text(view);
        case LinkEditPart.VISUAL_ID:
            return getLink_4001Text(view);
        }
        return getUnknownElementText(view);
    }

    /**
     * @generated
     */
    private String getEntity_1000Text(View view) {
        Entity domainModelElement = (Entity) view.getElement();
        if (domainModelElement != null) {
            return domainModelElement.getName();
        } else {
            KaomDiagramEditorPlugin.getInstance().logError(
                    "No domain element for view with visualID = " + 1000); //$NON-NLS-1$
            return ""; //$NON-NLS-1$
        }
    }

    /**
     * @generated
     */
    private String getEntity_2001Text(View view) {
        IParser parser = KaomParserProvider.getParser(KaomElementTypes.Entity_2001,
                view.getElement() != null ? view.getElement() : view,
                KaomVisualIDRegistry.getType(EntityNameEditPart.VISUAL_ID));
        if (parser != null) {
            return parser.getPrintString(
                    new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
                    ParserOptions.NONE.intValue());
        } else {
            KaomDiagramEditorPlugin.getInstance()
                    .logError("Parser was not found for label " + 5004); //$NON-NLS-1$
            return ""; //$NON-NLS-1$
        }
    }

    /**
     * @generated
     */
    private String getRelation_2002Text(View view) {
        IParser parser = KaomParserProvider.getParser(KaomElementTypes.Relation_2002,
                view.getElement() != null ? view.getElement() : view,
                KaomVisualIDRegistry.getType(RelationNameEditPart.VISUAL_ID));
        if (parser != null) {
            return parser.getPrintString(
                    new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
                    ParserOptions.NONE.intValue());
        } else {
            KaomDiagramEditorPlugin.getInstance()
                    .logError("Parser was not found for label " + 5005); //$NON-NLS-1$
            return ""; //$NON-NLS-1$
        }
    }

    /**
     * @generated
     */
    private String getPort_3001Text(View view) {
        IParser parser = KaomParserProvider.getParser(KaomElementTypes.Port_3001,
                view.getElement() != null ? view.getElement() : view,
                KaomVisualIDRegistry.getType(PortNameEditPart.VISUAL_ID));
        if (parser != null) {
            return parser.getPrintString(
                    new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
                    ParserOptions.NONE.intValue());
        } else {
            KaomDiagramEditorPlugin.getInstance()
                    .logError("Parser was not found for label " + 5001); //$NON-NLS-1$
            return ""; //$NON-NLS-1$
        }
    }

    /**
     * @generated
     */
    private String getEntity_3002Text(View view) {
        IParser parser = KaomParserProvider.getParser(KaomElementTypes.Entity_3002,
                view.getElement() != null ? view.getElement() : view,
                KaomVisualIDRegistry.getType(EntityName2EditPart.VISUAL_ID));
        if (parser != null) {
            return parser.getPrintString(
                    new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
                    ParserOptions.NONE.intValue());
        } else {
            KaomDiagramEditorPlugin.getInstance()
                    .logError("Parser was not found for label " + 5003); //$NON-NLS-1$
            return ""; //$NON-NLS-1$
        }
    }

    /**
     * @generated
     */
    private String getRelation_3003Text(View view) {
        IParser parser = KaomParserProvider.getParser(KaomElementTypes.Relation_3003,
                view.getElement() != null ? view.getElement() : view,
                KaomVisualIDRegistry.getType(RelationName2EditPart.VISUAL_ID));
        if (parser != null) {
            return parser.getPrintString(
                    new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
                    ParserOptions.NONE.intValue());
        } else {
            KaomDiagramEditorPlugin.getInstance()
                    .logError("Parser was not found for label " + 5002); //$NON-NLS-1$
            return ""; //$NON-NLS-1$
        }
    }

    /**
     * @generated
     */
    private String getLink_4001Text(View view) {
        IParser parser = KaomParserProvider.getParser(KaomElementTypes.Link_4001,
                view.getElement() != null ? view.getElement() : view,
                KaomVisualIDRegistry.getType(LinkNameEditPart.VISUAL_ID));
        if (parser != null) {
            return parser.getPrintString(
                    new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
                    ParserOptions.NONE.intValue());
        } else {
            KaomDiagramEditorPlugin.getInstance()
                    .logError("Parser was not found for label " + 6001); //$NON-NLS-1$
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
        return EntityEditPart.MODEL_ID.equals(KaomVisualIDRegistry.getModelID(view));
    }

}
