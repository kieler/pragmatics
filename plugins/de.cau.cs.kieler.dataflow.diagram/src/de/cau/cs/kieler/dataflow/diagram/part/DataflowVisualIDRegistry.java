package de.cau.cs.kieler.dataflow.diagram.part;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;

import de.cau.cs.kieler.dataflow.DataflowModel;
import de.cau.cs.kieler.dataflow.DataflowPackage;
import de.cau.cs.kieler.dataflow.diagram.edit.parts.Box2EditPart;
import de.cau.cs.kieler.dataflow.diagram.edit.parts.BoxBoxCompartment2EditPart;
import de.cau.cs.kieler.dataflow.diagram.edit.parts.BoxBoxCompartmentEditPart;
import de.cau.cs.kieler.dataflow.diagram.edit.parts.BoxEditPart;
import de.cau.cs.kieler.dataflow.diagram.edit.parts.BoxName2EditPart;
import de.cau.cs.kieler.dataflow.diagram.edit.parts.BoxNameEditPart;
import de.cau.cs.kieler.dataflow.diagram.edit.parts.ConnectionEditPart;
import de.cau.cs.kieler.dataflow.diagram.edit.parts.DataflowModelEditPart;
import de.cau.cs.kieler.dataflow.diagram.edit.parts.InputPortEditPart;
import de.cau.cs.kieler.dataflow.diagram.edit.parts.InputPortNameEditPart;
import de.cau.cs.kieler.dataflow.diagram.edit.parts.OutputPortEditPart;
import de.cau.cs.kieler.dataflow.diagram.edit.parts.OutputPortNameEditPart;

/**
 * This registry is used to determine which type of visual object should be
 * created for the corresponding Diagram, Node, ChildNode or Link represented
 * by a domain model object.
 * 
 * @generated
 */
public class DataflowVisualIDRegistry {

	/**
	 * @generated
	 */
	private static final String DEBUG_KEY = "de.cau.cs.kieler.dataflow.diagram/debug/visualID"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static int getVisualID(View view) {
		if (view instanceof Diagram) {
			if (DataflowModelEditPart.MODEL_ID.equals(view.getType())) {
				return DataflowModelEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		return de.cau.cs.kieler.dataflow.diagram.part.DataflowVisualIDRegistry
				.getVisualID(view.getType());
	}

	/**
	 * @generated
	 */
	public static String getModelID(View view) {
		View diagram = view.getDiagram();
		while (view != diagram) {
			EAnnotation annotation = view.getEAnnotation("Shortcut"); //$NON-NLS-1$
			if (annotation != null) {
				return (String) annotation.getDetails().get("modelID"); //$NON-NLS-1$
			}
			view = (View) view.eContainer();
		}
		return diagram != null ? diagram.getType() : null;
	}

	/**
	 * @generated
	 */
	public static int getVisualID(String type) {
		try {
			return Integer.parseInt(type);
		} catch (NumberFormatException e) {
			if (Boolean.TRUE.toString().equalsIgnoreCase(
					Platform.getDebugOption(DEBUG_KEY))) {
				DataflowDiagramEditorPlugin.getInstance().logError(
						"Unable to parse view type as a visualID number: "
								+ type);
			}
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static String getType(int visualID) {
		return String.valueOf(visualID);
	}

	/**
	 * @generated
	 */
	public static int getDiagramVisualID(EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		if (DataflowPackage.eINSTANCE.getDataflowModel().isSuperTypeOf(
				domainElement.eClass())
				&& isDiagram((DataflowModel) domainElement)) {
			return DataflowModelEditPart.VISUAL_ID;
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static int getNodeVisualID(View containerView, EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		String containerModelID = de.cau.cs.kieler.dataflow.diagram.part.DataflowVisualIDRegistry
				.getModelID(containerView);
		if (!DataflowModelEditPart.MODEL_ID.equals(containerModelID)) {
			return -1;
		}
		int containerVisualID;
		if (DataflowModelEditPart.MODEL_ID.equals(containerModelID)) {
			containerVisualID = de.cau.cs.kieler.dataflow.diagram.part.DataflowVisualIDRegistry
					.getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = DataflowModelEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		switch (containerVisualID) {
		case BoxEditPart.VISUAL_ID:
			if (DataflowPackage.eINSTANCE.getInputPort().isSuperTypeOf(
					domainElement.eClass())) {
				return InputPortEditPart.VISUAL_ID;
			}
			if (DataflowPackage.eINSTANCE.getOutputPort().isSuperTypeOf(
					domainElement.eClass())) {
				return OutputPortEditPart.VISUAL_ID;
			}
			break;
		case Box2EditPart.VISUAL_ID:
			if (DataflowPackage.eINSTANCE.getInputPort().isSuperTypeOf(
					domainElement.eClass())) {
				return InputPortEditPart.VISUAL_ID;
			}
			if (DataflowPackage.eINSTANCE.getOutputPort().isSuperTypeOf(
					domainElement.eClass())) {
				return OutputPortEditPart.VISUAL_ID;
			}
			break;
		case BoxBoxCompartmentEditPart.VISUAL_ID:
			if (DataflowPackage.eINSTANCE.getBox().isSuperTypeOf(
					domainElement.eClass())) {
				return Box2EditPart.VISUAL_ID;
			}
			break;
		case BoxBoxCompartment2EditPart.VISUAL_ID:
			if (DataflowPackage.eINSTANCE.getBox().isSuperTypeOf(
					domainElement.eClass())) {
				return Box2EditPart.VISUAL_ID;
			}
			break;
		case DataflowModelEditPart.VISUAL_ID:
			if (DataflowPackage.eINSTANCE.getBox().isSuperTypeOf(
					domainElement.eClass())) {
				return BoxEditPart.VISUAL_ID;
			}
			break;
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static boolean canCreateNode(View containerView, int nodeVisualID) {
		String containerModelID = de.cau.cs.kieler.dataflow.diagram.part.DataflowVisualIDRegistry
				.getModelID(containerView);
		if (!DataflowModelEditPart.MODEL_ID.equals(containerModelID)) {
			return false;
		}
		int containerVisualID;
		if (DataflowModelEditPart.MODEL_ID.equals(containerModelID)) {
			containerVisualID = de.cau.cs.kieler.dataflow.diagram.part.DataflowVisualIDRegistry
					.getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = DataflowModelEditPart.VISUAL_ID;
			} else {
				return false;
			}
		}
		switch (containerVisualID) {
		case BoxEditPart.VISUAL_ID:
			if (BoxNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (BoxBoxCompartmentEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (InputPortEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (OutputPortEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case InputPortEditPart.VISUAL_ID:
			if (InputPortNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case OutputPortEditPart.VISUAL_ID:
			if (OutputPortNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case Box2EditPart.VISUAL_ID:
			if (BoxName2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (BoxBoxCompartment2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (InputPortEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (OutputPortEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case BoxBoxCompartmentEditPart.VISUAL_ID:
			if (Box2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case BoxBoxCompartment2EditPart.VISUAL_ID:
			if (Box2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case DataflowModelEditPart.VISUAL_ID:
			if (BoxEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		}
		return false;
	}

	/**
	 * @generated
	 */
	public static int getLinkWithClassVisualID(EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		if (DataflowPackage.eINSTANCE.getConnection().isSuperTypeOf(
				domainElement.eClass())) {
			return ConnectionEditPart.VISUAL_ID;
		}
		return -1;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 * 
	 * @generated
	 */
	private static boolean isDiagram(DataflowModel element) {
		return true;
	}

}
