package de.cau.cs.kieler.dataflow.diagram.part;

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

import de.cau.cs.kieler.dataflow.Box;
import de.cau.cs.kieler.dataflow.Connection;
import de.cau.cs.kieler.dataflow.DataflowModel;
import de.cau.cs.kieler.dataflow.DataflowPackage;
import de.cau.cs.kieler.dataflow.InputPort;
import de.cau.cs.kieler.dataflow.OutputPort;
import de.cau.cs.kieler.dataflow.Port;
import de.cau.cs.kieler.dataflow.diagram.edit.parts.Box2EditPart;
import de.cau.cs.kieler.dataflow.diagram.edit.parts.BoxBoxCompartment2EditPart;
import de.cau.cs.kieler.dataflow.diagram.edit.parts.BoxBoxCompartmentEditPart;
import de.cau.cs.kieler.dataflow.diagram.edit.parts.BoxEditPart;
import de.cau.cs.kieler.dataflow.diagram.edit.parts.ConnectionEditPart;
import de.cau.cs.kieler.dataflow.diagram.edit.parts.DataflowModelEditPart;
import de.cau.cs.kieler.dataflow.diagram.edit.parts.InputPortEditPart;
import de.cau.cs.kieler.dataflow.diagram.edit.parts.OutputPortEditPart;
import de.cau.cs.kieler.dataflow.diagram.providers.DataflowElementTypes;

/**
 * @generated
 */
public class DataflowDiagramUpdater {

	/**
	 * @generated
	 */
	public static List getSemanticChildren(View view) {
		switch (DataflowVisualIDRegistry.getVisualID(view)) {
		case BoxEditPart.VISUAL_ID:
			return getBox_2001SemanticChildren(view);
		case Box2EditPart.VISUAL_ID:
			return getBox_3003SemanticChildren(view);
		case BoxBoxCompartmentEditPart.VISUAL_ID:
			return getBoxBoxCompartment_7001SemanticChildren(view);
		case BoxBoxCompartment2EditPart.VISUAL_ID:
			return getBoxBoxCompartment_7002SemanticChildren(view);
		case DataflowModelEditPart.VISUAL_ID:
			return getDataflowModel_1000SemanticChildren(view);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getBox_2001SemanticChildren(View view) {
		if (!view.isSetElement()) {
			return Collections.EMPTY_LIST;
		}
		Box modelElement = (Box) view.getElement();
		List result = new LinkedList();
		for (Iterator it = modelElement.getInputs().iterator(); it.hasNext();) {
			InputPort childElement = (InputPort) it.next();
			int visualID = DataflowVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == InputPortEditPart.VISUAL_ID) {
				result.add(new DataflowNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		for (Iterator it = modelElement.getOutputs().iterator(); it.hasNext();) {
			OutputPort childElement = (OutputPort) it.next();
			int visualID = DataflowVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == OutputPortEditPart.VISUAL_ID) {
				result.add(new DataflowNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List getBox_3003SemanticChildren(View view) {
		if (!view.isSetElement()) {
			return Collections.EMPTY_LIST;
		}
		Box modelElement = (Box) view.getElement();
		List result = new LinkedList();
		for (Iterator it = modelElement.getInputs().iterator(); it.hasNext();) {
			InputPort childElement = (InputPort) it.next();
			int visualID = DataflowVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == InputPortEditPart.VISUAL_ID) {
				result.add(new DataflowNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		for (Iterator it = modelElement.getOutputs().iterator(); it.hasNext();) {
			OutputPort childElement = (OutputPort) it.next();
			int visualID = DataflowVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == OutputPortEditPart.VISUAL_ID) {
				result.add(new DataflowNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List getBoxBoxCompartment_7001SemanticChildren(View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.EMPTY_LIST;
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.EMPTY_LIST;
		}
		Box modelElement = (Box) containerView.getElement();
		List result = new LinkedList();
		for (Iterator it = modelElement.getBoxes().iterator(); it.hasNext();) {
			Box childElement = (Box) it.next();
			int visualID = DataflowVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == Box2EditPart.VISUAL_ID) {
				result.add(new DataflowNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List getBoxBoxCompartment_7002SemanticChildren(View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.EMPTY_LIST;
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.EMPTY_LIST;
		}
		Box modelElement = (Box) containerView.getElement();
		List result = new LinkedList();
		for (Iterator it = modelElement.getBoxes().iterator(); it.hasNext();) {
			Box childElement = (Box) it.next();
			int visualID = DataflowVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == Box2EditPart.VISUAL_ID) {
				result.add(new DataflowNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List getDataflowModel_1000SemanticChildren(View view) {
		if (!view.isSetElement()) {
			return Collections.EMPTY_LIST;
		}
		DataflowModel modelElement = (DataflowModel) view.getElement();
		List result = new LinkedList();
		for (Iterator it = modelElement.getBoxes().iterator(); it.hasNext();) {
			Box childElement = (Box) it.next();
			int visualID = DataflowVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == BoxEditPart.VISUAL_ID) {
				result.add(new DataflowNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List getContainedLinks(View view) {
		switch (DataflowVisualIDRegistry.getVisualID(view)) {
		case DataflowModelEditPart.VISUAL_ID:
			return getDataflowModel_1000ContainedLinks(view);
		case BoxEditPart.VISUAL_ID:
			return getBox_2001ContainedLinks(view);
		case InputPortEditPart.VISUAL_ID:
			return getInputPort_3001ContainedLinks(view);
		case OutputPortEditPart.VISUAL_ID:
			return getOutputPort_3002ContainedLinks(view);
		case Box2EditPart.VISUAL_ID:
			return getBox_3003ContainedLinks(view);
		case ConnectionEditPart.VISUAL_ID:
			return getConnection_4001ContainedLinks(view);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getIncomingLinks(View view) {
		switch (DataflowVisualIDRegistry.getVisualID(view)) {
		case BoxEditPart.VISUAL_ID:
			return getBox_2001IncomingLinks(view);
		case InputPortEditPart.VISUAL_ID:
			return getInputPort_3001IncomingLinks(view);
		case OutputPortEditPart.VISUAL_ID:
			return getOutputPort_3002IncomingLinks(view);
		case Box2EditPart.VISUAL_ID:
			return getBox_3003IncomingLinks(view);
		case ConnectionEditPart.VISUAL_ID:
			return getConnection_4001IncomingLinks(view);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getOutgoingLinks(View view) {
		switch (DataflowVisualIDRegistry.getVisualID(view)) {
		case BoxEditPart.VISUAL_ID:
			return getBox_2001OutgoingLinks(view);
		case InputPortEditPart.VISUAL_ID:
			return getInputPort_3001OutgoingLinks(view);
		case OutputPortEditPart.VISUAL_ID:
			return getOutputPort_3002OutgoingLinks(view);
		case Box2EditPart.VISUAL_ID:
			return getBox_3003OutgoingLinks(view);
		case ConnectionEditPart.VISUAL_ID:
			return getConnection_4001OutgoingLinks(view);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getDataflowModel_1000ContainedLinks(View view) {
		DataflowModel modelElement = (DataflowModel) view.getElement();
		List result = new LinkedList();
		result
				.addAll(getContainedTypeModelFacetLinks_Connection_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getBox_2001ContainedLinks(View view) {
		Box modelElement = (Box) view.getElement();
		List result = new LinkedList();
		result
				.addAll(getContainedTypeModelFacetLinks_Connection_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getInputPort_3001ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getOutputPort_3002ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getBox_3003ContainedLinks(View view) {
		Box modelElement = (Box) view.getElement();
		List result = new LinkedList();
		result
				.addAll(getContainedTypeModelFacetLinks_Connection_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getConnection_4001ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getBox_2001IncomingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getInputPort_3001IncomingLinks(View view) {
		InputPort modelElement = (InputPort) view.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingTypeModelFacetLinks_Connection_4001(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getOutputPort_3002IncomingLinks(View view) {
		OutputPort modelElement = (OutputPort) view.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingTypeModelFacetLinks_Connection_4001(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getBox_3003IncomingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getConnection_4001IncomingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getBox_2001OutgoingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getInputPort_3001OutgoingLinks(View view) {
		InputPort modelElement = (InputPort) view.getElement();
		List result = new LinkedList();
		result
				.addAll(getOutgoingTypeModelFacetLinks_Connection_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getOutputPort_3002OutgoingLinks(View view) {
		OutputPort modelElement = (OutputPort) view.getElement();
		List result = new LinkedList();
		result
				.addAll(getOutgoingTypeModelFacetLinks_Connection_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getBox_3003OutgoingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getConnection_4001OutgoingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	private static Collection getContainedTypeModelFacetLinks_Connection_4001(
			Box container) {
		Collection result = new LinkedList();
		for (Iterator links = container.getConnections().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof Connection) {
				continue;
			}
			Connection link = (Connection) linkObject;
			if (ConnectionEditPart.VISUAL_ID != DataflowVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Port dst = link.getTargetPort();
			Port src = link.getSourcePort();
			result.add(new DataflowLinkDescriptor(src, dst, link,
					DataflowElementTypes.Connection_4001,
					ConnectionEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getIncomingTypeModelFacetLinks_Connection_4001(
			Port target, Map crossReferences) {
		Collection result = new LinkedList();
		Collection settings = (Collection) crossReferences.get(target);
		for (Iterator it = settings.iterator(); it.hasNext();) {
			EStructuralFeature.Setting setting = (EStructuralFeature.Setting) it
					.next();
			if (setting.getEStructuralFeature() != DataflowPackage.eINSTANCE
					.getConnection_TargetPort()
					|| false == setting.getEObject() instanceof Connection) {
				continue;
			}
			Connection link = (Connection) setting.getEObject();
			if (ConnectionEditPart.VISUAL_ID != DataflowVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Port src = link.getSourcePort();
			result.add(new DataflowLinkDescriptor(src, target, link,
					DataflowElementTypes.Connection_4001,
					ConnectionEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getOutgoingTypeModelFacetLinks_Connection_4001(
			Port source) {
		Box container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element
				.eContainer()) {
			if (element instanceof Box) {
				container = (Box) element;
			}
		}
		if (container == null) {
			return Collections.EMPTY_LIST;
		}
		Collection result = new LinkedList();
		for (Iterator links = container.getConnections().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof Connection) {
				continue;
			}
			Connection link = (Connection) linkObject;
			if (ConnectionEditPart.VISUAL_ID != DataflowVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Port dst = link.getTargetPort();
			Port src = link.getSourcePort();
			if (src != source) {
				continue;
			}
			result.add(new DataflowLinkDescriptor(src, dst, link,
					DataflowElementTypes.Connection_4001,
					ConnectionEditPart.VISUAL_ID));
		}
		return result;
	}

}
