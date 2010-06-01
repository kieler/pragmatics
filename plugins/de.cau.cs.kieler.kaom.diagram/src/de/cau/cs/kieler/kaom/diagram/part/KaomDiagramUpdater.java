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

import de.cau.cs.kieler.kaom.Entity;
import de.cau.cs.kieler.kaom.KaomPackage;
import de.cau.cs.kieler.kaom.Link;
import de.cau.cs.kieler.kaom.Linkable;
import de.cau.cs.kieler.kaom.Port;
import de.cau.cs.kieler.kaom.Relation;
import de.cau.cs.kieler.kaom.diagram.edit.parts.Entity2EditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.Entity3EditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.EntityEditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.EntityEntityCompartment2EditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.EntityEntityCompartmentEditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.LinkEditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.PortEditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.Relation2EditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.RelationEditPart;
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
		case Entity2EditPart.VISUAL_ID:
			return getEntity_2001SemanticChildren(view);
		case Entity3EditPart.VISUAL_ID:
			return getEntity_3002SemanticChildren(view);
		case EntityEntityCompartmentEditPart.VISUAL_ID:
			return getEntityEntityCompartment_7001SemanticChildren(view);
		case EntityEntityCompartment2EditPart.VISUAL_ID:
			return getEntityEntityCompartment_7002SemanticChildren(view);
		case EntityEditPart.VISUAL_ID:
			return getEntity_1000SemanticChildren(view);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getEntity_2001SemanticChildren(View view) {
		if (!view.isSetElement()) {
			return Collections.EMPTY_LIST;
		}
		Entity modelElement = (Entity) view.getElement();
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
	public static List getEntity_3002SemanticChildren(View view) {
		if (!view.isSetElement()) {
			return Collections.EMPTY_LIST;
		}
		Entity modelElement = (Entity) view.getElement();
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
	public static List getEntityEntityCompartment_7001SemanticChildren(View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.EMPTY_LIST;
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.EMPTY_LIST;
		}
		Entity modelElement = (Entity) containerView.getElement();
		List result = new LinkedList();
		for (Iterator it = modelElement.getChildEntities().iterator(); it
				.hasNext();) {
			Entity childElement = (Entity) it.next();
			int visualID = KaomVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == Entity3EditPart.VISUAL_ID) {
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
	public static List getEntityEntityCompartment_7002SemanticChildren(View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.EMPTY_LIST;
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.EMPTY_LIST;
		}
		Entity modelElement = (Entity) containerView.getElement();
		List result = new LinkedList();
		for (Iterator it = modelElement.getChildEntities().iterator(); it
				.hasNext();) {
			Entity childElement = (Entity) it.next();
			int visualID = KaomVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == Entity3EditPart.VISUAL_ID) {
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
	public static List getEntity_1000SemanticChildren(View view) {
		if (!view.isSetElement()) {
			return Collections.EMPTY_LIST;
		}
		Entity modelElement = (Entity) view.getElement();
		List result = new LinkedList();
		for (Iterator it = modelElement.getChildEntities().iterator(); it
				.hasNext();) {
			Entity childElement = (Entity) it.next();
			int visualID = KaomVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == Entity2EditPart.VISUAL_ID) {
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
		case EntityEditPart.VISUAL_ID:
			return getEntity_1000ContainedLinks(view);
		case Entity2EditPart.VISUAL_ID:
			return getEntity_2001ContainedLinks(view);
		case RelationEditPart.VISUAL_ID:
			return getRelation_2002ContainedLinks(view);
		case PortEditPart.VISUAL_ID:
			return getPort_3001ContainedLinks(view);
		case Entity3EditPart.VISUAL_ID:
			return getEntity_3002ContainedLinks(view);
		case Relation2EditPart.VISUAL_ID:
			return getRelation_3003ContainedLinks(view);
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
		case Entity2EditPart.VISUAL_ID:
			return getEntity_2001IncomingLinks(view);
		case RelationEditPart.VISUAL_ID:
			return getRelation_2002IncomingLinks(view);
		case PortEditPart.VISUAL_ID:
			return getPort_3001IncomingLinks(view);
		case Entity3EditPart.VISUAL_ID:
			return getEntity_3002IncomingLinks(view);
		case Relation2EditPart.VISUAL_ID:
			return getRelation_3003IncomingLinks(view);
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
		case Entity2EditPart.VISUAL_ID:
			return getEntity_2001OutgoingLinks(view);
		case RelationEditPart.VISUAL_ID:
			return getRelation_2002OutgoingLinks(view);
		case PortEditPart.VISUAL_ID:
			return getPort_3001OutgoingLinks(view);
		case Entity3EditPart.VISUAL_ID:
			return getEntity_3002OutgoingLinks(view);
		case Relation2EditPart.VISUAL_ID:
			return getRelation_3003OutgoingLinks(view);
		case LinkEditPart.VISUAL_ID:
			return getLink_4001OutgoingLinks(view);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getEntity_1000ContainedLinks(View view) {
		Entity modelElement = (Entity) view.getElement();
		List result = new LinkedList();
		result.addAll(getContainedTypeModelFacetLinks_Link_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getEntity_2001ContainedLinks(View view) {
		Entity modelElement = (Entity) view.getElement();
		List result = new LinkedList();
		result.addAll(getContainedTypeModelFacetLinks_Link_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getRelation_2002ContainedLinks(View view) {
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
	public static List getEntity_3002ContainedLinks(View view) {
		Entity modelElement = (Entity) view.getElement();
		List result = new LinkedList();
		result.addAll(getContainedTypeModelFacetLinks_Link_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getRelation_3003ContainedLinks(View view) {
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
	public static List getEntity_2001IncomingLinks(View view) {
		Entity modelElement = (Entity) view.getElement();
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
	public static List getRelation_2002IncomingLinks(View view) {
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
	public static List getEntity_3002IncomingLinks(View view) {
		Entity modelElement = (Entity) view.getElement();
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
	public static List getRelation_3003IncomingLinks(View view) {
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
	public static List getEntity_2001OutgoingLinks(View view) {
		Entity modelElement = (Entity) view.getElement();
		List result = new LinkedList();
		result.addAll(getOutgoingTypeModelFacetLinks_Link_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getRelation_2002OutgoingLinks(View view) {
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
	public static List getEntity_3002OutgoingLinks(View view) {
		Entity modelElement = (Entity) view.getElement();
		List result = new LinkedList();
		result.addAll(getOutgoingTypeModelFacetLinks_Link_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getRelation_3003OutgoingLinks(View view) {
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
