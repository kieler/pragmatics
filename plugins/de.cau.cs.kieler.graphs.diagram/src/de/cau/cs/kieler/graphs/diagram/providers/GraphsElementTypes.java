package de.cau.cs.kieler.graphs.diagram.providers;

import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;

import de.cau.cs.kieler.graphs.GraphsPackage;
import de.cau.cs.kieler.graphs.diagram.edit.parts.Edge2EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.Edge3EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.Edge4EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.Edge5EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.Edge6EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.Edge7EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.Edge8EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeEditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.Node2EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.Node3EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.Node4EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.Node5EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.NodeEditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.PortEditPart;
import de.cau.cs.kieler.graphs.diagram.part.GraphsDiagramEditorPlugin;

/**
 * @generated
 */
public class GraphsElementTypes {

    /**
     * @generated
     */
    private GraphsElementTypes() {
    }

    /**
     * @generated
     */
    private static Map<IElementType, ENamedElement> elements;

    /**
     * @generated
     */
    private static ImageRegistry imageRegistry;

    /**
     * @generated
     */
    private static Set<IElementType> KNOWN_ELEMENT_TYPES;

    /**
     * @generated
     */
    public static final IElementType Node_1000 =
            getElementType("de.cau.cs.kieler.graphs.diagram.Node_1000"); //$NON-NLS-1$
    /**
     * @generated
     */
    public static final IElementType Node_2001 =
            getElementType("de.cau.cs.kieler.graphs.diagram.Node_2001"); //$NON-NLS-1$
    /**
     * @generated
     */
    public static final IElementType Node_2002 =
            getElementType("de.cau.cs.kieler.graphs.diagram.Node_2002"); //$NON-NLS-1$
    /**
     * @generated
     */
    public static final IElementType Node_3001 =
            getElementType("de.cau.cs.kieler.graphs.diagram.Node_3001"); //$NON-NLS-1$
    /**
     * @generated
     */
    public static final IElementType Port_3002 =
            getElementType("de.cau.cs.kieler.graphs.diagram.Port_3002"); //$NON-NLS-1$
    /**
     * @generated
     */
    public static final IElementType Node_3003 =
            getElementType("de.cau.cs.kieler.graphs.diagram.Node_3003"); //$NON-NLS-1$
    /**
     * @generated
     */
    public static final IElementType Edge_4001 =
            getElementType("de.cau.cs.kieler.graphs.diagram.Edge_4001"); //$NON-NLS-1$
    /**
     * @generated
     */
    public static final IElementType Edge_4002 =
            getElementType("de.cau.cs.kieler.graphs.diagram.Edge_4002"); //$NON-NLS-1$
    /**
     * @generated
     */
    public static final IElementType Edge_4003 =
            getElementType("de.cau.cs.kieler.graphs.diagram.Edge_4003"); //$NON-NLS-1$
    /**
     * @generated
     */
    public static final IElementType Edge_4004 =
            getElementType("de.cau.cs.kieler.graphs.diagram.Edge_4004"); //$NON-NLS-1$
    /**
     * @generated
     */
    public static final IElementType Edge_4005 =
            getElementType("de.cau.cs.kieler.graphs.diagram.Edge_4005"); //$NON-NLS-1$
    /**
     * @generated
     */
    public static final IElementType Edge_4006 =
            getElementType("de.cau.cs.kieler.graphs.diagram.Edge_4006"); //$NON-NLS-1$
    /**
     * @generated
     */
    public static final IElementType Edge_4007 =
            getElementType("de.cau.cs.kieler.graphs.diagram.Edge_4007"); //$NON-NLS-1$
    /**
     * @generated
     */
    public static final IElementType Edge_4008 =
            getElementType("de.cau.cs.kieler.graphs.diagram.Edge_4008"); //$NON-NLS-1$

    /**
     * @generated
     */
    private static ImageRegistry getImageRegistry() {
        if (imageRegistry == null) {
            imageRegistry = new ImageRegistry();
        }
        return imageRegistry;
    }

    /**
     * @generated
     */
    private static String getImageRegistryKey(ENamedElement element) {
        return element.getName();
    }

    /**
     * @generated
     */
    private static ImageDescriptor getProvidedImageDescriptor(
            ENamedElement element) {
        if (element instanceof EStructuralFeature) {
            EStructuralFeature feature = ((EStructuralFeature) element);
            EClass eContainingClass = feature.getEContainingClass();
            EClassifier eType = feature.getEType();
            if (eContainingClass != null && !eContainingClass.isAbstract()) {
                element = eContainingClass;
            } else if (eType instanceof EClass
                    && !((EClass) eType).isAbstract()) {
                element = eType;
            }
        }
        if (element instanceof EClass) {
            EClass eClass = (EClass) element;
            if (!eClass.isAbstract()) {
                return GraphsDiagramEditorPlugin.getInstance()
                        .getItemImageDescriptor(
                                eClass.getEPackage().getEFactoryInstance()
                                        .create(eClass));
            }
        }
        // TODO : support structural features
        return null;
    }

    /**
     * @generated
     */
    public static ImageDescriptor getImageDescriptor(ENamedElement element) {
        String key = getImageRegistryKey(element);
        ImageDescriptor imageDescriptor = getImageRegistry().getDescriptor(key);
        if (imageDescriptor == null) {
            imageDescriptor = getProvidedImageDescriptor(element);
            if (imageDescriptor == null) {
                imageDescriptor = ImageDescriptor.getMissingImageDescriptor();
            }
            getImageRegistry().put(key, imageDescriptor);
        }
        return imageDescriptor;
    }

    /**
     * @generated
     */
    public static Image getImage(ENamedElement element) {
        String key = getImageRegistryKey(element);
        Image image = getImageRegistry().get(key);
        if (image == null) {
            ImageDescriptor imageDescriptor =
                    getProvidedImageDescriptor(element);
            if (imageDescriptor == null) {
                imageDescriptor = ImageDescriptor.getMissingImageDescriptor();
            }
            getImageRegistry().put(key, imageDescriptor);
            image = getImageRegistry().get(key);
        }
        return image;
    }

    /**
     * @generated
     */
    public static ImageDescriptor getImageDescriptor(IAdaptable hint) {
        ENamedElement element = getElement(hint);
        if (element == null) {
            return null;
        }
        return getImageDescriptor(element);
    }

    /**
     * @generated
     */
    public static Image getImage(IAdaptable hint) {
        ENamedElement element = getElement(hint);
        if (element == null) {
            return null;
        }
        return getImage(element);
    }

    /**
     * Returns 'type' of the ecore object associated with the hint.
     * 
     * @generated
     */
    public static ENamedElement getElement(IAdaptable hint) {
        Object type = hint.getAdapter(IElementType.class);
        if (elements == null) {
            elements = new IdentityHashMap<IElementType, ENamedElement>();

            elements.put(Node_1000, GraphsPackage.eINSTANCE.getNode());

            elements.put(Node_2001, GraphsPackage.eINSTANCE.getNode());

            elements.put(Node_2002, GraphsPackage.eINSTANCE.getNode());

            elements.put(Node_3001, GraphsPackage.eINSTANCE.getNode());

            elements.put(Port_3002, GraphsPackage.eINSTANCE.getPort());

            elements.put(Node_3003, GraphsPackage.eINSTANCE.getNode());

            elements.put(Edge_4001, GraphsPackage.eINSTANCE.getEdge());

            elements.put(Edge_4002, GraphsPackage.eINSTANCE.getEdge());

            elements.put(Edge_4003, GraphsPackage.eINSTANCE.getEdge());

            elements.put(Edge_4004, GraphsPackage.eINSTANCE.getEdge());

            elements.put(Edge_4005, GraphsPackage.eINSTANCE.getEdge());

            elements.put(Edge_4006, GraphsPackage.eINSTANCE.getEdge());

            elements.put(Edge_4007, GraphsPackage.eINSTANCE.getEdge());

            elements.put(Edge_4008, GraphsPackage.eINSTANCE.getEdge());
        }
        return (ENamedElement) elements.get(type);
    }

    /**
     * @generated
     */
    private static IElementType getElementType(String id) {
        return ElementTypeRegistry.getInstance().getType(id);
    }

    /**
     * @generated
     */
    public static boolean isKnownElementType(IElementType elementType) {
        if (KNOWN_ELEMENT_TYPES == null) {
            KNOWN_ELEMENT_TYPES = new HashSet<IElementType>();
            KNOWN_ELEMENT_TYPES.add(Node_1000);
            KNOWN_ELEMENT_TYPES.add(Node_2001);
            KNOWN_ELEMENT_TYPES.add(Node_2002);
            KNOWN_ELEMENT_TYPES.add(Node_3001);
            KNOWN_ELEMENT_TYPES.add(Port_3002);
            KNOWN_ELEMENT_TYPES.add(Node_3003);
            KNOWN_ELEMENT_TYPES.add(Edge_4001);
            KNOWN_ELEMENT_TYPES.add(Edge_4002);
            KNOWN_ELEMENT_TYPES.add(Edge_4003);
            KNOWN_ELEMENT_TYPES.add(Edge_4004);
            KNOWN_ELEMENT_TYPES.add(Edge_4005);
            KNOWN_ELEMENT_TYPES.add(Edge_4006);
            KNOWN_ELEMENT_TYPES.add(Edge_4007);
            KNOWN_ELEMENT_TYPES.add(Edge_4008);
        }
        return KNOWN_ELEMENT_TYPES.contains(elementType);
    }

    /**
     * @generated
     */
    public static IElementType getElementType(int visualID) {
        switch (visualID) {
        case NodeEditPart.VISUAL_ID:
            return Node_1000;
        case Node2EditPart.VISUAL_ID:
            return Node_2001;
        case Node3EditPart.VISUAL_ID:
            return Node_2002;
        case Node4EditPart.VISUAL_ID:
            return Node_3001;
        case PortEditPart.VISUAL_ID:
            return Port_3002;
        case Node5EditPart.VISUAL_ID:
            return Node_3003;
        case EdgeEditPart.VISUAL_ID:
            return Edge_4001;
        case Edge2EditPart.VISUAL_ID:
            return Edge_4002;
        case Edge3EditPart.VISUAL_ID:
            return Edge_4003;
        case Edge4EditPart.VISUAL_ID:
            return Edge_4004;
        case Edge5EditPart.VISUAL_ID:
            return Edge_4005;
        case Edge6EditPart.VISUAL_ID:
            return Edge_4006;
        case Edge7EditPart.VISUAL_ID:
            return Edge_4007;
        case Edge8EditPart.VISUAL_ID:
            return Edge_4008;
        }
        return null;
    }

}
