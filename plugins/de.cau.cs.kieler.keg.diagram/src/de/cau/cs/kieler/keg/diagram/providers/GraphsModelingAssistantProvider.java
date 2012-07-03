package de.cau.cs.kieler.keg.diagram.providers;

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

import de.cau.cs.kieler.keg.diagram.edit.parts.Node2EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.Node3EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.Node4EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.Node5EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.NodeEditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.NodeNodeCompartment2EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.NodeNodeCompartmentEditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.PortEditPart;
import de.cau.cs.kieler.keg.diagram.part.GraphsDiagramEditorPlugin;
import de.cau.cs.kieler.keg.diagram.part.Messages;

/**
 * @generated
 */
public class GraphsModelingAssistantProvider extends ModelingAssistantProvider {

    /**
     * @generated
     */
    public List<?> getTypesForPopupBar(IAdaptable host) {
        IGraphicalEditPart editPart = (IGraphicalEditPart) host
                .getAdapter(IGraphicalEditPart.class);
        if (editPart instanceof NodeEditPart) {
            ArrayList<IElementType> types = new ArrayList<IElementType>(2);
            types.add(GraphsElementTypes.Node_2001);
            types.add(GraphsElementTypes.Node_2002);
            return types;
        }
        if (editPart instanceof Node2EditPart) {
            ArrayList<IElementType> types = new ArrayList<IElementType>(1);
            types.add(GraphsElementTypes.Port_3002);
            return types;
        }
        if (editPart instanceof Node4EditPart) {
            ArrayList<IElementType> types = new ArrayList<IElementType>(1);
            types.add(GraphsElementTypes.Port_3002);
            return types;
        }
        if (editPart instanceof NodeNodeCompartmentEditPart) {
            ArrayList<IElementType> types = new ArrayList<IElementType>(2);
            types.add(GraphsElementTypes.Node_3001);
            types.add(GraphsElementTypes.Node_3003);
            return types;
        }
        if (editPart instanceof NodeNodeCompartment2EditPart) {
            ArrayList<IElementType> types = new ArrayList<IElementType>(2);
            types.add(GraphsElementTypes.Node_3001);
            types.add(GraphsElementTypes.Node_3003);
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
        if (sourceEditPart instanceof Node2EditPart) {
            return ((Node2EditPart) sourceEditPart).getMARelTypesOnSource();
        }
        if (sourceEditPart instanceof Node3EditPart) {
            return ((Node3EditPart) sourceEditPart).getMARelTypesOnSource();
        }
        if (sourceEditPart instanceof Node4EditPart) {
            return ((Node4EditPart) sourceEditPart).getMARelTypesOnSource();
        }
        if (sourceEditPart instanceof PortEditPart) {
            return ((PortEditPart) sourceEditPart).getMARelTypesOnSource();
        }
        if (sourceEditPart instanceof Node5EditPart) {
            return ((Node5EditPart) sourceEditPart).getMARelTypesOnSource();
        }
        return Collections.EMPTY_LIST;
    }

    /**
     * @generated
     */
    public List<?> getRelTypesOnTarget(IAdaptable target) {
        IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target
                .getAdapter(IGraphicalEditPart.class);
        if (targetEditPart instanceof Node2EditPart) {
            return ((Node2EditPart) targetEditPart).getMARelTypesOnTarget();
        }
        if (targetEditPart instanceof Node3EditPart) {
            return ((Node3EditPart) targetEditPart).getMARelTypesOnTarget();
        }
        if (targetEditPart instanceof Node4EditPart) {
            return ((Node4EditPart) targetEditPart).getMARelTypesOnTarget();
        }
        if (targetEditPart instanceof PortEditPart) {
            return ((PortEditPart) targetEditPart).getMARelTypesOnTarget();
        }
        if (targetEditPart instanceof Node5EditPart) {
            return ((Node5EditPart) targetEditPart).getMARelTypesOnTarget();
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
        if (sourceEditPart instanceof Node2EditPart) {
            return ((Node2EditPart) sourceEditPart).getMARelTypesOnSourceAndTarget(targetEditPart);
        }
        if (sourceEditPart instanceof Node3EditPart) {
            return ((Node3EditPart) sourceEditPart).getMARelTypesOnSourceAndTarget(targetEditPart);
        }
        if (sourceEditPart instanceof Node4EditPart) {
            return ((Node4EditPart) sourceEditPart).getMARelTypesOnSourceAndTarget(targetEditPart);
        }
        if (sourceEditPart instanceof PortEditPart) {
            return ((PortEditPart) sourceEditPart).getMARelTypesOnSourceAndTarget(targetEditPart);
        }
        if (sourceEditPart instanceof Node5EditPart) {
            return ((Node5EditPart) sourceEditPart).getMARelTypesOnSourceAndTarget(targetEditPart);
        }
        return Collections.EMPTY_LIST;
    }

    /**
     * @generated
     */
    public List<?> getTypesForSource(IAdaptable target, IElementType relationshipType) {
        IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target
                .getAdapter(IGraphicalEditPart.class);
        if (targetEditPart instanceof Node2EditPart) {
            return ((Node2EditPart) targetEditPart).getMATypesForSource(relationshipType);
        }
        if (targetEditPart instanceof Node3EditPart) {
            return ((Node3EditPart) targetEditPart).getMATypesForSource(relationshipType);
        }
        if (targetEditPart instanceof Node4EditPart) {
            return ((Node4EditPart) targetEditPart).getMATypesForSource(relationshipType);
        }
        if (targetEditPart instanceof PortEditPart) {
            return ((PortEditPart) targetEditPart).getMATypesForSource(relationshipType);
        }
        if (targetEditPart instanceof Node5EditPart) {
            return ((Node5EditPart) targetEditPart).getMATypesForSource(relationshipType);
        }
        return Collections.EMPTY_LIST;
    }

    /**
     * @generated
     */
    public List<?> getTypesForTarget(IAdaptable source, IElementType relationshipType) {
        IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source
                .getAdapter(IGraphicalEditPart.class);
        if (sourceEditPart instanceof Node2EditPart) {
            return ((Node2EditPart) sourceEditPart).getMATypesForTarget(relationshipType);
        }
        if (sourceEditPart instanceof Node3EditPart) {
            return ((Node3EditPart) sourceEditPart).getMATypesForTarget(relationshipType);
        }
        if (sourceEditPart instanceof Node4EditPart) {
            return ((Node4EditPart) sourceEditPart).getMATypesForTarget(relationshipType);
        }
        if (sourceEditPart instanceof PortEditPart) {
            return ((PortEditPart) sourceEditPart).getMATypesForTarget(relationshipType);
        }
        if (sourceEditPart instanceof Node5EditPart) {
            return ((Node5EditPart) sourceEditPart).getMATypesForTarget(relationshipType);
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
    @SuppressWarnings("rawtypes")
    protected EObject selectExistingElement(IAdaptable host, Collection types) {
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
    @SuppressWarnings("rawtypes")
    protected boolean isApplicableElement(EObject element, Collection types) {
        IElementType type = ElementTypeRegistry.getInstance().getElementType(element);
        return types.contains(type);
    }

    /**
     * @generated
     */
    protected EObject selectElement(EObject[] elements) {
        Shell shell = Display.getCurrent().getActiveShell();
        ILabelProvider labelProvider = new AdapterFactoryLabelProvider(GraphsDiagramEditorPlugin
                .getInstance().getItemProvidersAdapterFactory());
        ElementListSelectionDialog dialog = new ElementListSelectionDialog(shell, labelProvider);
        dialog.setMessage(Messages.GraphsModelingAssistantProviderMessage);
        dialog.setTitle(Messages.GraphsModelingAssistantProviderTitle);
        dialog.setMultipleSelection(false);
        dialog.setElements(elements);
        EObject selected = null;
        if (dialog.open() == Window.OK) {
            selected = (EObject) dialog.getFirstResult();
        }
        return selected;
    }
}
