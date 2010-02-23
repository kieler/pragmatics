package de.cau.cs.kieler.graphs.diagram.providers;

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

import de.cau.cs.kieler.graphs.diagram.edit.parts.CompoundNode2EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.CompoundNodeEditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.CompoundNodeNodeCompartment2EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.CompoundNodeNodeCompartmentEditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.GraphEditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.Node2EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.NodeEditPart;
import de.cau.cs.kieler.graphs.diagram.part.GraphsDiagramEditorPlugin;
import de.cau.cs.kieler.graphs.diagram.part.Messages;

/**
 * @generated
 */
public class GraphsModelingAssistantProvider extends ModelingAssistantProvider {

    /**
     * @generated
     */
    public List getTypesForPopupBar(IAdaptable host) {
        IGraphicalEditPart editPart = (IGraphicalEditPart) host.getAdapter(IGraphicalEditPart.class);
        if (editPart instanceof CompoundNodeNodeCompartmentEditPart) {
            ArrayList types = new ArrayList(2);
            types.add(GraphsElementTypes.Node_3003);
            types.add(GraphsElementTypes.CompoundNode_3004);
            return types;
        }
        if (editPart instanceof CompoundNodeNodeCompartment2EditPart) {
            ArrayList types = new ArrayList(2);
            types.add(GraphsElementTypes.Node_3003);
            types.add(GraphsElementTypes.CompoundNode_3004);
            return types;
        }
        if (editPart instanceof GraphEditPart) {
            ArrayList types = new ArrayList(2);
            types.add(GraphsElementTypes.Node_2003);
            types.add(GraphsElementTypes.CompoundNode_2004);
            return types;
        }
        return Collections.EMPTY_LIST;
    }

    /**
     * @generated
     */
    public List getRelTypesOnSource(IAdaptable source) {
        IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source
                .getAdapter(IGraphicalEditPart.class);
        if (sourceEditPart instanceof NodeEditPart) {
            return ((NodeEditPart) sourceEditPart).getMARelTypesOnSource();
        }
        if (sourceEditPart instanceof CompoundNodeEditPart) {
            return ((CompoundNodeEditPart) sourceEditPart).getMARelTypesOnSource();
        }
        if (sourceEditPart instanceof Node2EditPart) {
            return ((Node2EditPart) sourceEditPart).getMARelTypesOnSource();
        }
        if (sourceEditPart instanceof CompoundNode2EditPart) {
            return ((CompoundNode2EditPart) sourceEditPart).getMARelTypesOnSource();
        }
        return Collections.EMPTY_LIST;
    }

    /**
     * @generated
     */
    public List getRelTypesOnTarget(IAdaptable target) {
        IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target
                .getAdapter(IGraphicalEditPart.class);
        if (targetEditPart instanceof NodeEditPart) {
            return ((NodeEditPart) targetEditPart).getMARelTypesOnTarget();
        }
        if (targetEditPart instanceof CompoundNodeEditPart) {
            return ((CompoundNodeEditPart) targetEditPart).getMARelTypesOnTarget();
        }
        if (targetEditPart instanceof Node2EditPart) {
            return ((Node2EditPart) targetEditPart).getMARelTypesOnTarget();
        }
        if (targetEditPart instanceof CompoundNode2EditPart) {
            return ((CompoundNode2EditPart) targetEditPart).getMARelTypesOnTarget();
        }
        return Collections.EMPTY_LIST;
    }

    /**
     * @generated
     */
    public List getRelTypesOnSourceAndTarget(IAdaptable source, IAdaptable target) {
        IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source
                .getAdapter(IGraphicalEditPart.class);
        IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target
                .getAdapter(IGraphicalEditPart.class);
        if (sourceEditPart instanceof NodeEditPart) {
            return ((NodeEditPart) sourceEditPart).getMARelTypesOnSourceAndTarget(targetEditPart);
        }
        if (sourceEditPart instanceof CompoundNodeEditPart) {
            return ((CompoundNodeEditPart) sourceEditPart)
                    .getMARelTypesOnSourceAndTarget(targetEditPart);
        }
        if (sourceEditPart instanceof Node2EditPart) {
            return ((Node2EditPart) sourceEditPart).getMARelTypesOnSourceAndTarget(targetEditPart);
        }
        if (sourceEditPart instanceof CompoundNode2EditPart) {
            return ((CompoundNode2EditPart) sourceEditPart)
                    .getMARelTypesOnSourceAndTarget(targetEditPart);
        }
        return Collections.EMPTY_LIST;
    }

    /**
     * @generated
     */
    public List getTypesForSource(IAdaptable target, IElementType relationshipType) {
        IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target
                .getAdapter(IGraphicalEditPart.class);
        if (targetEditPart instanceof NodeEditPart) {
            return ((NodeEditPart) targetEditPart).getMATypesForSource(relationshipType);
        }
        if (targetEditPart instanceof CompoundNodeEditPart) {
            return ((CompoundNodeEditPart) targetEditPart).getMATypesForSource(relationshipType);
        }
        if (targetEditPart instanceof Node2EditPart) {
            return ((Node2EditPart) targetEditPart).getMATypesForSource(relationshipType);
        }
        if (targetEditPart instanceof CompoundNode2EditPart) {
            return ((CompoundNode2EditPart) targetEditPart).getMATypesForSource(relationshipType);
        }
        return Collections.EMPTY_LIST;
    }

    /**
     * @generated
     */
    public List getTypesForTarget(IAdaptable source, IElementType relationshipType) {
        IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source
                .getAdapter(IGraphicalEditPart.class);
        if (sourceEditPart instanceof NodeEditPart) {
            return ((NodeEditPart) sourceEditPart).getMATypesForTarget(relationshipType);
        }
        if (sourceEditPart instanceof CompoundNodeEditPart) {
            return ((CompoundNodeEditPart) sourceEditPart).getMATypesForTarget(relationshipType);
        }
        if (sourceEditPart instanceof Node2EditPart) {
            return ((Node2EditPart) sourceEditPart).getMATypesForTarget(relationshipType);
        }
        if (sourceEditPart instanceof CompoundNode2EditPart) {
            return ((CompoundNode2EditPart) sourceEditPart).getMATypesForTarget(relationshipType);
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
    protected EObject selectExistingElement(IAdaptable host, Collection types) {
        if (types.isEmpty()) {
            return null;
        }
        IGraphicalEditPart editPart = (IGraphicalEditPart) host.getAdapter(IGraphicalEditPart.class);
        if (editPart == null) {
            return null;
        }
        Diagram diagram = (Diagram) editPart.getRoot().getContents().getModel();
        Collection elements = new HashSet();
        for (Iterator it = diagram.getElement().eAllContents(); it.hasNext();) {
            EObject element = (EObject) it.next();
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
