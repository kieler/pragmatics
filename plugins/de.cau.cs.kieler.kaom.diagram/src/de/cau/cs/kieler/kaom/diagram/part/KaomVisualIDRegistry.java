package de.cau.cs.kieler.kaom.diagram.part;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;

import de.cau.cs.kieler.kaom.KaomPackage;
import de.cau.cs.kieler.kaom.State;
import de.cau.cs.kieler.kaom.diagram.edit.parts.Actor2EditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.ActorActorCompartment2EditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.ActorActorCompartmentEditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.ActorEditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.ActorName2EditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.ActorNameEditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.LinkEditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.LinkNameEditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.PortEditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.PortNameEditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.Relation2EditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.RelationEditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.RelationName2EditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.RelationNameEditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.State2EditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.State3EditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.StateEditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.StateName2EditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.StateNameEditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.StateStateCompartment2EditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.StateStateCompartmentEditPart;

/**
 * This registry is used to determine which type of visual object should be
 * created for the corresponding Diagram, Node, ChildNode or Link represented
 * by a domain model object.
 * 
 * @generated
 */
public class KaomVisualIDRegistry {

	/**
	 * @generated
	 */
	private static final String DEBUG_KEY = "de.cau.cs.kieler.kaom.diagram/debug/visualID"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static int getVisualID(View view) {
		if (view instanceof Diagram) {
			if (StateEditPart.MODEL_ID.equals(view.getType())) {
				return StateEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		return de.cau.cs.kieler.kaom.diagram.part.KaomVisualIDRegistry
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
				KaomDiagramEditorPlugin.getInstance().logError(
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
		if (KaomPackage.eINSTANCE.getState().isSuperTypeOf(
				domainElement.eClass())
				&& isDiagram((State) domainElement)) {
			return StateEditPart.VISUAL_ID;
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
		String containerModelID = de.cau.cs.kieler.kaom.diagram.part.KaomVisualIDRegistry
				.getModelID(containerView);
		if (!StateEditPart.MODEL_ID.equals(containerModelID)) {
			return -1;
		}
		int containerVisualID;
		if (StateEditPart.MODEL_ID.equals(containerModelID)) {
			containerVisualID = de.cau.cs.kieler.kaom.diagram.part.KaomVisualIDRegistry
					.getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = StateEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		switch (containerVisualID) {
		case ActorEditPart.VISUAL_ID:
			if (KaomPackage.eINSTANCE.getPort().isSuperTypeOf(
					domainElement.eClass())) {
				return PortEditPart.VISUAL_ID;
			}
			break;
		case Actor2EditPart.VISUAL_ID:
			if (KaomPackage.eINSTANCE.getPort().isSuperTypeOf(
					domainElement.eClass())) {
				return PortEditPart.VISUAL_ID;
			}
			break;
		case ActorActorCompartmentEditPart.VISUAL_ID:
			if (KaomPackage.eINSTANCE.getActor().isSuperTypeOf(
					domainElement.eClass())) {
				return Actor2EditPart.VISUAL_ID;
			}
			if (KaomPackage.eINSTANCE.getState().isSuperTypeOf(
					domainElement.eClass())) {
				return State3EditPart.VISUAL_ID;
			}
			if (KaomPackage.eINSTANCE.getRelation().isSuperTypeOf(
					domainElement.eClass())) {
				return Relation2EditPart.VISUAL_ID;
			}
			break;
		case ActorActorCompartment2EditPart.VISUAL_ID:
			if (KaomPackage.eINSTANCE.getActor().isSuperTypeOf(
					domainElement.eClass())) {
				return Actor2EditPart.VISUAL_ID;
			}
			if (KaomPackage.eINSTANCE.getState().isSuperTypeOf(
					domainElement.eClass())) {
				return State3EditPart.VISUAL_ID;
			}
			if (KaomPackage.eINSTANCE.getRelation().isSuperTypeOf(
					domainElement.eClass())) {
				return Relation2EditPart.VISUAL_ID;
			}
			break;
		case StateStateCompartmentEditPart.VISUAL_ID:
			if (KaomPackage.eINSTANCE.getActor().isSuperTypeOf(
					domainElement.eClass())) {
				return Actor2EditPart.VISUAL_ID;
			}
			if (KaomPackage.eINSTANCE.getState().isSuperTypeOf(
					domainElement.eClass())) {
				return State3EditPart.VISUAL_ID;
			}
			if (KaomPackage.eINSTANCE.getRelation().isSuperTypeOf(
					domainElement.eClass())) {
				return Relation2EditPart.VISUAL_ID;
			}
			break;
		case StateStateCompartment2EditPart.VISUAL_ID:
			if (KaomPackage.eINSTANCE.getActor().isSuperTypeOf(
					domainElement.eClass())) {
				return Actor2EditPart.VISUAL_ID;
			}
			if (KaomPackage.eINSTANCE.getState().isSuperTypeOf(
					domainElement.eClass())) {
				return State3EditPart.VISUAL_ID;
			}
			if (KaomPackage.eINSTANCE.getRelation().isSuperTypeOf(
					domainElement.eClass())) {
				return Relation2EditPart.VISUAL_ID;
			}
			break;
		case StateEditPart.VISUAL_ID:
			if (KaomPackage.eINSTANCE.getActor().isSuperTypeOf(
					domainElement.eClass())) {
				return ActorEditPart.VISUAL_ID;
			}
			if (KaomPackage.eINSTANCE.getState().isSuperTypeOf(
					domainElement.eClass())) {
				return State2EditPart.VISUAL_ID;
			}
			if (KaomPackage.eINSTANCE.getRelation().isSuperTypeOf(
					domainElement.eClass())) {
				return RelationEditPart.VISUAL_ID;
			}
			break;
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static boolean canCreateNode(View containerView, int nodeVisualID) {
		String containerModelID = de.cau.cs.kieler.kaom.diagram.part.KaomVisualIDRegistry
				.getModelID(containerView);
		if (!StateEditPart.MODEL_ID.equals(containerModelID)) {
			return false;
		}
		int containerVisualID;
		if (StateEditPart.MODEL_ID.equals(containerModelID)) {
			containerVisualID = de.cau.cs.kieler.kaom.diagram.part.KaomVisualIDRegistry
					.getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = StateEditPart.VISUAL_ID;
			} else {
				return false;
			}
		}
		switch (containerVisualID) {
		case ActorEditPart.VISUAL_ID:
			if (ActorNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (ActorActorCompartmentEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (PortEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case State2EditPart.VISUAL_ID:
			if (StateNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (StateStateCompartment2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case RelationEditPart.VISUAL_ID:
			if (RelationNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case PortEditPart.VISUAL_ID:
			if (PortNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case Actor2EditPart.VISUAL_ID:
			if (ActorName2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (ActorActorCompartment2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (PortEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case State3EditPart.VISUAL_ID:
			if (StateName2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (StateStateCompartmentEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case Relation2EditPart.VISUAL_ID:
			if (RelationName2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case ActorActorCompartmentEditPart.VISUAL_ID:
			if (Actor2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (State3EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (Relation2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case ActorActorCompartment2EditPart.VISUAL_ID:
			if (Actor2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (State3EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (Relation2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case StateStateCompartmentEditPart.VISUAL_ID:
			if (Actor2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (State3EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (Relation2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case StateStateCompartment2EditPart.VISUAL_ID:
			if (Actor2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (State3EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (Relation2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case StateEditPart.VISUAL_ID:
			if (ActorEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (State2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (RelationEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case LinkEditPart.VISUAL_ID:
			if (LinkNameEditPart.VISUAL_ID == nodeVisualID) {
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
		if (KaomPackage.eINSTANCE.getLink().isSuperTypeOf(
				domainElement.eClass())) {
			return LinkEditPart.VISUAL_ID;
		}
		return -1;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 * 
	 * @generated
	 */
	private static boolean isDiagram(State element) {
		return true;
	}

}
