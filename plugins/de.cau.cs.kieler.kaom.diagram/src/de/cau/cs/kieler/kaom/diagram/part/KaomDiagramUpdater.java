package de.cau.cs.kieler.kaom.diagram.part;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.notation.View;

import de.cau.cs.kieler.kaom.Actor;
import de.cau.cs.kieler.kaom.Entity;
import de.cau.cs.kieler.kaom.KaomPackage;
import de.cau.cs.kieler.kaom.Link;
import de.cau.cs.kieler.kaom.Linkable;
import de.cau.cs.kieler.kaom.Port;
import de.cau.cs.kieler.kaom.Relation;
import de.cau.cs.kieler.kaom.State;
import de.cau.cs.kieler.kaom.diagram.edit.parts.Actor2EditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.ActorActorCompartment2EditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.ActorActorCompartmentEditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.ActorEditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.LinkEditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.PortEditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.Relation2EditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.RelationEditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.State2EditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.State3EditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.StateEditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.StateStateCompartment2EditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.StateStateCompartmentEditPart;
import de.cau.cs.kieler.kaom.diagram.providers.KaomElementTypes;

/**
 * @generated
 */
public class KaomDiagramUpdater {

	/**
	 * @generated
	 */
	public static List getSemanticChildren(View view) {
		switch (KaomVisualIDRegistry.getVisualID(view)) {
		case ActorEditPart.VISUAL_ID:
			return getActor_2001SemanticChildren(view);
		case Actor2EditPart.VISUAL_ID:
			return getActor_3002SemanticChildren(view);
		case ActorActorCompartmentEditPart.VISUAL_ID:
			return getActorActorCompartment_7001SemanticChildren(view);
		case ActorActorCompartment2EditPart.VISUAL_ID:
			return getActorActorCompartment_7002SemanticChildren(view);
		case StateStateCompartmentEditPart.VISUAL_ID:
			return getStateStateCompartment_7003SemanticChildren(view);
		case StateStateCompartment2EditPart.VISUAL_ID:
			return getStateStateCompartment_7004SemanticChildren(view);
		case StateEditPart.VISUAL_ID:
			return getState_1000SemanticChildren(view);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getActor_2001SemanticChildren(View view) {
		if (!view.isSetElement()) {
			return Collections.EMPTY_LIST;
		}
		Actor modelElement = (Actor) view.getElement();
		List result = new LinkedList();
		for (Iterator it = modelElement.getChildPorts().iterator(); it
				.hasNext();) {
			Port childElement = (Port) it.next();
			int visualID = KaomVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == PortEditPart.VISUAL_ID) {
				result.add(new KaomNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List getActor_3002SemanticChildren(View view) {
		if (!view.isSetElement()) {
			return Collections.EMPTY_LIST;
		}
		Actor modelElement = (Actor) view.getElement();
		List result = new LinkedList();
		for (Iterator it = modelElement.getChildPorts().iterator(); it
				.hasNext();) {
			Port childElement = (Port) it.next();
			int visualID = KaomVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == PortEditPart.VISUAL_ID) {
				result.add(new KaomNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List getActorActorCompartment_7001SemanticChildren(View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.EMPTY_LIST;
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.EMPTY_LIST;
		}
		Actor modelElement = (Actor) containerView.getElement();
		List result = new LinkedList();
		for (Iterator it = modelElement.getChildEntities().iterator(); it
				.hasNext();) {
			Entity childElement = (Entity) it.next();
			int visualID = KaomVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == Actor2EditPart.VISUAL_ID) {
				result.add(new KaomNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == State3EditPart.VISUAL_ID) {
				result.add(new KaomNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		for (Iterator it = modelElement.getChildRelations().iterator(); it
				.hasNext();) {
			Relation childElement = (Relation) it.next();
			int visualID = KaomVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == Relation2EditPart.VISUAL_ID) {
				result.add(new KaomNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List getActorActorCompartment_7002SemanticChildren(View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.EMPTY_LIST;
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.EMPTY_LIST;
		}
		Actor modelElement = (Actor) containerView.getElement();
		List result = new LinkedList();
		for (Iterator it = modelElement.getChildEntities().iterator(); it
				.hasNext();) {
			Entity childElement = (Entity) it.next();
			int visualID = KaomVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == Actor2EditPart.VISUAL_ID) {
				result.add(new KaomNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == State3EditPart.VISUAL_ID) {
				result.add(new KaomNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		for (Iterator it = modelElement.getChildRelations().iterator(); it
				.hasNext();) {
			Relation childElement = (Relation) it.next();
			int visualID = KaomVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == Relation2EditPart.VISUAL_ID) {
				result.add(new KaomNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List getStateStateCompartment_7003SemanticChildren(View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.EMPTY_LIST;
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.EMPTY_LIST;
		}
		State modelElement = (State) containerView.getElement();
		List result = new LinkedList();
		for (Iterator it = modelElement.getChildEntities().iterator(); it
				.hasNext();) {
			Entity childElement = (Entity) it.next();
			int visualID = KaomVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == Actor2EditPart.VISUAL_ID) {
				result.add(new KaomNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == State3EditPart.VISUAL_ID) {
				result.add(new KaomNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		for (Iterator it = modelElement.getChildRelations().iterator(); it
				.hasNext();) {
			Relation childElement = (Relation) it.next();
			int visualID = KaomVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == Relation2EditPart.VISUAL_ID) {
				result.add(new KaomNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List getStateStateCompartment_7004SemanticChildren(View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.EMPTY_LIST;
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.EMPTY_LIST;
		}
		State modelElement = (State) containerView.getElement();
		List result = new LinkedList();
		for (Iterator it = modelElement.getChildEntities().iterator(); it
				.hasNext();) {
			Entity childElement = (Entity) it.next();
			int visualID = KaomVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == Actor2EditPart.VISUAL_ID) {
				result.add(new KaomNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == State3EditPart.VISUAL_ID) {
				result.add(new KaomNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		for (Iterator it = modelElement.getChildRelations().iterator(); it
				.hasNext();) {
			Relation childElement = (Relation) it.next();
			int visualID = KaomVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == Relation2EditPart.VISUAL_ID) {
				result.add(new KaomNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List getState_1000SemanticChildren(View view) {
		if (!view.isSetElement()) {
			return Collections.EMPTY_LIST;
		}
		State modelElement = (State) view.getElement();
		List result = new LinkedList();
		for (Iterator it = modelElement.getChildEntities().iterator(); it
				.hasNext();) {
			Entity childElement = (Entity) it.next();
			int visualID = KaomVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == ActorEditPart.VISUAL_ID) {
				result.add(new KaomNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == State2EditPart.VISUAL_ID) {
				result.add(new KaomNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		for (Iterator it = modelElement.getChildRelations().iterator(); it
				.hasNext();) {
			Relation childElement = (Relation) it.next();
			int visualID = KaomVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == RelationEditPart.VISUAL_ID) {
				result.add(new KaomNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List getContainedLinks(View view) {
		switch (KaomVisualIDRegistry.getVisualID(view)) {
		case StateEditPart.VISUAL_ID:
			return getState_1000ContainedLinks(view);
		case ActorEditPart.VISUAL_ID:
			return getActor_2001ContainedLinks(view);
		case State2EditPart.VISUAL_ID:
			return getState_2002ContainedLinks(view);
		case RelationEditPart.VISUAL_ID:
			return getRelation_2003ContainedLinks(view);
		case PortEditPart.VISUAL_ID:
			return getPort_3001ContainedLinks(view);
		case Actor2EditPart.VISUAL_ID:
			return getActor_3002ContainedLinks(view);
		case State3EditPart.VISUAL_ID:
			return getState_3003ContainedLinks(view);
		case Relation2EditPart.VISUAL_ID:
			return getRelation_3004ContainedLinks(view);
		case LinkEditPart.VISUAL_ID:
			return getLink_4001ContainedLinks(view);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getIncomingLinks(View view) {
		switch (KaomVisualIDRegistry.getVisualID(view)) {
		case ActorEditPart.VISUAL_ID:
			return getActor_2001IncomingLinks(view);
		case State2EditPart.VISUAL_ID:
			return getState_2002IncomingLinks(view);
		case RelationEditPart.VISUAL_ID:
			return getRelation_2003IncomingLinks(view);
		case PortEditPart.VISUAL_ID:
			return getPort_3001IncomingLinks(view);
		case Actor2EditPart.VISUAL_ID:
			return getActor_3002IncomingLinks(view);
		case State3EditPart.VISUAL_ID:
			return getState_3003IncomingLinks(view);
		case Relation2EditPart.VISUAL_ID:
			return getRelation_3004IncomingLinks(view);
		case LinkEditPart.VISUAL_ID:
			return getLink_4001IncomingLinks(view);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getOutgoingLinks(View view) {
		switch (KaomVisualIDRegistry.getVisualID(view)) {
		case ActorEditPart.VISUAL_ID:
			return getActor_2001OutgoingLinks(view);
		case State2EditPart.VISUAL_ID:
			return getState_2002OutgoingLinks(view);
		case RelationEditPart.VISUAL_ID:
			return getRelation_2003OutgoingLinks(view);
		case PortEditPart.VISUAL_ID:
			return getPort_3001OutgoingLinks(view);
		case Actor2EditPart.VISUAL_ID:
			return getActor_3002OutgoingLinks(view);
		case State3EditPart.VISUAL_ID:
			return getState_3003OutgoingLinks(view);
		case Relation2EditPart.VISUAL_ID:
			return getRelation_3004OutgoingLinks(view);
		case LinkEditPart.VISUAL_ID:
			return getLink_4001OutgoingLinks(view);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getState_1000ContainedLinks(View view) {
		State modelElement = (State) view.getElement();
		List result = new LinkedList();
		result.addAll(getContainedTypeModelFacetLinks_Link_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getActor_2001ContainedLinks(View view) {
		Actor modelElement = (Actor) view.getElement();
		List result = new LinkedList();
		result.addAll(getContainedTypeModelFacetLinks_Link_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getState_2002ContainedLinks(View view) {
		State modelElement = (State) view.getElement();
		List result = new LinkedList();
		result.addAll(getContainedTypeModelFacetLinks_Link_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getRelation_2003ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getPort_3001ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getActor_3002ContainedLinks(View view) {
		Actor modelElement = (Actor) view.getElement();
		List result = new LinkedList();
		result.addAll(getContainedTypeModelFacetLinks_Link_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getState_3003ContainedLinks(View view) {
		State modelElement = (State) view.getElement();
		List result = new LinkedList();
		result.addAll(getContainedTypeModelFacetLinks_Link_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getRelation_3004ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getLink_4001ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getActor_2001IncomingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getState_2002IncomingLinks(View view) {
		State modelElement = (State) view.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingTypeModelFacetLinks_Link_4001(modelElement,
				crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getRelation_2003IncomingLinks(View view) {
		Relation modelElement = (Relation) view.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingTypeModelFacetLinks_Link_4001(modelElement,
				crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getPort_3001IncomingLinks(View view) {
		Port modelElement = (Port) view.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingTypeModelFacetLinks_Link_4001(modelElement,
				crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getActor_3002IncomingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getState_3003IncomingLinks(View view) {
		State modelElement = (State) view.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingTypeModelFacetLinks_Link_4001(modelElement,
				crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getRelation_3004IncomingLinks(View view) {
		Relation modelElement = (Relation) view.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingTypeModelFacetLinks_Link_4001(modelElement,
				crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getLink_4001IncomingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getActor_2001OutgoingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getState_2002OutgoingLinks(View view) {
		State modelElement = (State) view.getElement();
		List result = new LinkedList();
		result.addAll(getOutgoingTypeModelFacetLinks_Link_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getRelation_2003OutgoingLinks(View view) {
		Relation modelElement = (Relation) view.getElement();
		List result = new LinkedList();
		result.addAll(getOutgoingTypeModelFacetLinks_Link_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getPort_3001OutgoingLinks(View view) {
		Port modelElement = (Port) view.getElement();
		List result = new LinkedList();
		result.addAll(getOutgoingTypeModelFacetLinks_Link_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getActor_3002OutgoingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getState_3003OutgoingLinks(View view) {
		State modelElement = (State) view.getElement();
		List result = new LinkedList();
		result.addAll(getOutgoingTypeModelFacetLinks_Link_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getRelation_3004OutgoingLinks(View view) {
		Relation modelElement = (Relation) view.getElement();
		List result = new LinkedList();
		result.addAll(getOutgoingTypeModelFacetLinks_Link_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getLink_4001OutgoingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	private static Collection getContainedTypeModelFacetLinks_Link_4001(
			Entity container) {
		Collection result = new LinkedList();
		for (Iterator links = container.getChildLinks().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof Link) {
				continue;
			}
			Link link = (Link) linkObject;
			if (LinkEditPart.VISUAL_ID != KaomVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Linkable dst = link.getTarget();
			Linkable src = link.getSource();
			result.add(new KaomLinkDescriptor(src, dst, link,
					KaomElementTypes.Link_4001, LinkEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getIncomingTypeModelFacetLinks_Link_4001(
			Linkable target, Map crossReferences) {
		Collection result = new LinkedList();
		Collection settings = (Collection) crossReferences.get(target);
		for (Iterator it = settings.iterator(); it.hasNext();) {
			EStructuralFeature.Setting setting = (EStructuralFeature.Setting) it
					.next();
			if (setting.getEStructuralFeature() != KaomPackage.eINSTANCE
					.getLink_Target()
					|| false == setting.getEObject() instanceof Link) {
				continue;
			}
			Link link = (Link) setting.getEObject();
			if (LinkEditPart.VISUAL_ID != KaomVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Linkable src = link.getSource();
			result.add(new KaomLinkDescriptor(src, target, link,
					KaomElementTypes.Link_4001, LinkEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getOutgoingTypeModelFacetLinks_Link_4001(
			Linkable source) {
		Entity container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element
				.eContainer()) {
			if (element instanceof Entity) {
				container = (Entity) element;
			}
		}
		if (container == null) {
			return Collections.EMPTY_LIST;
		}
		Collection result = new LinkedList();
		for (Iterator links = container.getChildLinks().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof Link) {
				continue;
			}
			Link link = (Link) linkObject;
			if (LinkEditPart.VISUAL_ID != KaomVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Linkable dst = link.getTarget();
			Linkable src = link.getSource();
			if (src != source) {
				continue;
			}
			result.add(new KaomLinkDescriptor(src, dst, link,
					KaomElementTypes.Link_4001, LinkEditPart.VISUAL_ID));
		}
		return result;
	}

}
