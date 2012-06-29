package de.cau.cs.kieler.kaom.diagram.providers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.ui.services.modelingassistant.ModelingAssistantProvider;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;

import de.cau.cs.kieler.kaom.diagram.edit.parts.Entity2EditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.Entity3EditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.EntityEditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.EntityEntityCompartment2EditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.EntityEntityCompartmentEditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.PortEditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.Relation2EditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.RelationEditPart;
import de.cau.cs.kieler.kaom.diagram.part.KaomDiagramEditorPlugin;
import de.cau.cs.kieler.kaom.diagram.part.Messages;

/**
 * @generated
 */
public class KaomModelingAssistantProvider extends ModelingAssistantProvider {

    /**
     * @generated
     */
    public List<?> getTypesForPopupBar(IAdaptable host) {
        IGraphicalEditPart editPart = (IGraphicalEditPart) host
                .getAdapter(IGraphicalEditPart.class);
        if (editPart instanceof EntityEditPart) {
            ArrayList<IElementType> types = new ArrayList<IElementType>(2);
            types.add(KaomElementTypes.Entity_2001);
            types.add(KaomElementTypes.Relation_2002);
            return types;
        }
        if (editPart instanceof Entity2EditPart) {
            ArrayList<IElementType> types = new ArrayList<IElementType>(1);
            types.add(KaomElementTypes.Port_3001);
            return types;
        }
        if (editPart instanceof Entity3EditPart) {
            ArrayList<IElementType> types = new ArrayList<IElementType>(1);
            types.add(KaomElementTypes.Port_3001);
            return types;
        }
        if (editPart instanceof EntityEntityCompartmentEditPart) {
            ArrayList<IElementType> types = new ArrayList<IElementType>(2);
            types.add(KaomElementTypes.Entity_3002);
            types.add(KaomElementTypes.Relation_3003);
            return types;
        }
        if (editPart instanceof EntityEntityCompartment2EditPart) {
            ArrayList<IElementType> types = new ArrayList<IElementType>(2);
            types.add(KaomElementTypes.Entity_3002);
            types.add(KaomElementTypes.Relation_3003);
            return types;
        }
        return Collections.EMPTY_LIST;
    }

    /**
     * @generated
     */
    public List<?> getRelTypesOnSource(IAdaptable source) {
        IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source
                .getAdapter(IGraphicalEditPart.class);
        if (sourceEditPart instanceof Entity2EditPart) {
            return ((Entity2EditPart) sourceEditPart).getMARelTypesOnSource();
        }
        if (sourceEditPart instanceof RelationEditPart) {
            return ((RelationEditPart) sourceEditPart).getMARelTypesOnSource();
        }
        if (sourceEditPart instanceof PortEditPart) {
            return ((PortEditPart) sourceEditPart).getMARelTypesOnSource();
        }
        if (sourceEditPart instanceof Entity3EditPart) {
            return ((Entity3EditPart) sourceEditPart).getMARelTypesOnSource();
        }
        if (sourceEditPart instanceof Relation2EditPart) {
            return ((Relation2EditPart) sourceEditPart).getMARelTypesOnSource();
        }
        return Collections.EMPTY_LIST;
    }

    /**
     * @generated
     */
    public List<?> getRelTypesOnTarget(IAdaptable target) {
        IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target
                .getAdapter(IGraphicalEditPart.class);
        if (targetEditPart instanceof Entity2EditPart) {
            return ((Entity2EditPart) targetEditPart).getMARelTypesOnTarget();
        }
        if (targetEditPart instanceof RelationEditPart) {
            return ((RelationEditPart) targetEditPart).getMARelTypesOnTarget();
        }
        if (targetEditPart instanceof PortEditPart) {
            return ((PortEditPart) targetEditPart).getMARelTypesOnTarget();
        }
        if (targetEditPart instanceof Entity3EditPart) {
            return ((Entity3EditPart) targetEditPart).getMARelTypesOnTarget();
        }
        if (targetEditPart instanceof Relation2EditPart) {
            return ((Relation2EditPart) targetEditPart).getMARelTypesOnTarget();
        }
        return Collections.EMPTY_LIST;
    }

    /**
     * @generated
     */
    public List<?> getRelTypesOnSourceAndTarget(IAdaptable source, IAdaptable target) {
        IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source
                .getAdapter(IGraphicalEditPart.class);
        IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target
                .getAdapter(IGraphicalEditPart.class);
        if (sourceEditPart instanceof Entity2EditPart) {
            return ((Entity2EditPart) sourceEditPart)
                    .getMARelTypesOnSourceAndTarget(targetEditPart);
        }
        if (sourceEditPart instanceof RelationEditPart) {
            return ((RelationEditPart) sourceEditPart)
                    .getMARelTypesOnSourceAndTarget(targetEditPart);
        }
        if (sourceEditPart instanceof PortEditPart) {
            return ((PortEditPart) sourceEditPart).getMARelTypesOnSourceAndTarget(targetEditPart);
        }
        if (sourceEditPart instanceof Entity3EditPart) {
            return ((Entity3EditPart) sourceEditPart)
                    .getMARelTypesOnSourceAndTarget(targetEditPart);
        }
        if (sourceEditPart instanceof Relation2EditPart) {
            return ((Relation2EditPart) sourceEditPart)
                    .getMARelTypesOnSourceAndTarget(targetEditPart);
        }
        return Collections.EMPTY_LIST;
    }

    /**
     * @generated
     */
    public List<?> getTypesForSource(IAdaptable target, IElementType relationshipType) {
        IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target
                .getAdapter(IGraphicalEditPart.class);
        if (targetEditPart instanceof Entity2EditPart) {
            return ((Entity2EditPart) targetEditPart).getMATypesForSource(relationshipType);
        }
        if (targetEditPart instanceof RelationEditPart) {
            return ((RelationEditPart) targetEditPart).getMATypesForSource(relationshipType);
        }
        if (targetEditPart instanceof PortEditPart) {
            return ((PortEditPart) targetEditPart).getMATypesForSource(relationshipType);
        }
        if (targetEditPart instanceof Entity3EditPart) {
            return ((Entity3EditPart) targetEditPart).getMATypesForSource(relationshipType);
        }
        if (targetEditPart instanceof Relation2EditPart) {
            return ((Relation2EditPart) targetEditPart).getMATypesForSource(relationshipType);
        }
        return Collections.EMPTY_LIST;
    }

    /**
     * @generated
     */
    public List<?> getTypesForTarget(IAdaptable source, IElementType relationshipType) {
        IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source
                .getAdapter(IGraphicalEditPart.class);
        if (sourceEditPart instanceof Entity2EditPart) {
            return ((Entity2EditPart) sourceEditPart).getMATypesForTarget(relationshipType);
        }
        if (sourceEditPart instanceof RelationEditPart) {
            return ((RelationEditPart) sourceEditPart).getMATypesForTarget(relationshipType);
        }
        if (sourceEditPart instanceof PortEditPart) {
            return ((PortEditPart) sourceEditPart).getMATypesForTarget(relationshipType);
        }
        if (sourceEditPart instanceof Entity3EditPart) {
            return ((Entity3EditPart) sourceEditPart).getMATypesForTarget(relationshipType);
        }
        if (sourceEditPart instanceof Relation2EditPart) {
            return ((Relation2EditPart) sourceEditPart).getMATypesForTarget(relationshipType);
        }
        return Collections.EMPTY_LIST;
    }

    /**
     * @generated
     */
    public EObject selectExistingElementForSource(IAdaptable target, IElementType relationshipType) {
        return selectExistingElement(target, getTypesForSource(target, relationshipType));
    }

    /**
     * @generated
     */
    public EObject selectExistingElementForTarget(IAdaptable source, IElementType relationshipType) {
        return selectExistingElement(source, getTypesForTarget(source, relationshipType));
    }

    /**
     * @generated
     */
    protected EObject selectExistingElement(IAdaptable host, Collection<?> types) {
        if (types.isEmpty()) {
            return null;
        }
        IGraphicalEditPart editPart = (IGraphicalEditPart) host
                .getAdapter(IGraphicalEditPart.class);
        if (editPart == null) {
            return null;
        }
        Diagram diagram = (Diagram) editPart.getRoot().getContents().getModel();
        HashSet<EObject> elements = new HashSet<EObject>();
        for (Iterator<EObject> it = diagram.getElement().eAllContents(); it.hasNext();) {
            EObject element = it.next();
            if (isApplicableElement(element, types)) {
                elements.add(element);
            }
        }
        if (elements.isEmpty()) {
            return null;
        }
        return selectElement((EObject[]) elements.toArray(new EObject[elements.size()]));
    }

    /**
     * @generated
     */
    protected boolean isApplicableElement(EObject element, Collection<?> types) {
        IElementType type = ElementTypeRegistry.getInstance().getElementType(element);
        return types.contains(type);
    }

    /**
     * @generated
     */
    protected EObject selectElement(EObject[] elements) {
        Shell shell = Display.getCurrent().getActiveShell();
        ILabelProvider labelProvider = new AdapterFactoryLabelProvider(KaomDiagramEditorPlugin
                .getInstance().getItemProvidersAdapterFactory());
        ElementListSelectionDialog dialog = new ElementListSelectionDialog(shell, labelProvider);
        dialog.setMessage(Messages.KaomModelingAssistantProviderMessage);
        dialog.setTitle(Messages.KaomModelingAssistantProviderTitle);
        dialog.setMultipleSelection(false);
        dialog.setElements(elements);
        EObject selected = null;
        if (dialog.open() == Window.OK) {
            selected = (EObject) dialog.getFirstResult();
        }
        return selected;
    }
}
