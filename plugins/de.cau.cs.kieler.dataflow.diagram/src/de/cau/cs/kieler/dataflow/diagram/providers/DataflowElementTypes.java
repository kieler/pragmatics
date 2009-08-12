package de.cau.cs.kieler.dataflow.diagram.providers;

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

import de.cau.cs.kieler.dataflow.DataflowPackage;
import de.cau.cs.kieler.dataflow.diagram.edit.parts.Box2EditPart;
import de.cau.cs.kieler.dataflow.diagram.edit.parts.BoxEditPart;
import de.cau.cs.kieler.dataflow.diagram.edit.parts.ConnectionEditPart;
import de.cau.cs.kieler.dataflow.diagram.edit.parts.DataflowModelEditPart;
import de.cau.cs.kieler.dataflow.diagram.edit.parts.InputPortEditPart;
import de.cau.cs.kieler.dataflow.diagram.edit.parts.OutputPortEditPart;
import de.cau.cs.kieler.dataflow.diagram.part.DataflowDiagramEditorPlugin;

/**
 * @generated
 */
public class DataflowElementTypes extends ElementInitializers {

    /**
     * @generated
     */
    private DataflowElementTypes() {
    }

    /**
     * @generated
     */
    private static Map elements;

    /**
     * @generated
     */
    private static ImageRegistry imageRegistry;

    /**
     * @generated
     */
    private static Set KNOWN_ELEMENT_TYPES;

    /**
     * @generated
     */
    public static final IElementType DataflowModel_1000 = getElementType("de.cau.cs.kieler.dataflow.diagram.DataflowModel_1000"); //$NON-NLS-1$
    /**
     * @generated
     */
    public static final IElementType Box_2001 = getElementType("de.cau.cs.kieler.dataflow.diagram.Box_2001"); //$NON-NLS-1$
    /**
     * @generated
     */
    public static final IElementType InputPort_3001 = getElementType("de.cau.cs.kieler.dataflow.diagram.InputPort_3001"); //$NON-NLS-1$
    /**
     * @generated
     */
    public static final IElementType OutputPort_3002 = getElementType("de.cau.cs.kieler.dataflow.diagram.OutputPort_3002"); //$NON-NLS-1$
    /**
     * @generated
     */
    public static final IElementType Box_3003 = getElementType("de.cau.cs.kieler.dataflow.diagram.Box_3003"); //$NON-NLS-1$
    /**
     * @generated
     */
    public static final IElementType Connection_4001 = getElementType("de.cau.cs.kieler.dataflow.diagram.Connection_4001"); //$NON-NLS-1$

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
    private static ImageDescriptor getProvidedImageDescriptor(ENamedElement element) {
        if (element instanceof EStructuralFeature) {
            EStructuralFeature feature = ((EStructuralFeature) element);
            EClass eContainingClass = feature.getEContainingClass();
            EClassifier eType = feature.getEType();
            if (eContainingClass != null && !eContainingClass.isAbstract()) {
                element = eContainingClass;
            }
            else if (eType instanceof EClass && !((EClass) eType).isAbstract()) {
                element = eType;
            }
        }
        if (element instanceof EClass) {
            EClass eClass = (EClass) element;
            if (!eClass.isAbstract()) {
                return DataflowDiagramEditorPlugin.getInstance().getItemImageDescriptor(
                        eClass.getEPackage().getEFactoryInstance().create(eClass));
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
            ImageDescriptor imageDescriptor = getProvidedImageDescriptor(element);
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
            elements = new IdentityHashMap();

            elements.put(DataflowModel_1000, DataflowPackage.eINSTANCE.getDataflowModel());

            elements.put(Box_2001, DataflowPackage.eINSTANCE.getBox());

            elements.put(InputPort_3001, DataflowPackage.eINSTANCE.getInputPort());

            elements.put(OutputPort_3002, DataflowPackage.eINSTANCE.getOutputPort());

            elements.put(Box_3003, DataflowPackage.eINSTANCE.getBox());

            elements.put(Connection_4001, DataflowPackage.eINSTANCE.getConnection());
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
            KNOWN_ELEMENT_TYPES = new HashSet();
            KNOWN_ELEMENT_TYPES.add(DataflowModel_1000);
            KNOWN_ELEMENT_TYPES.add(Box_2001);
            KNOWN_ELEMENT_TYPES.add(InputPort_3001);
            KNOWN_ELEMENT_TYPES.add(OutputPort_3002);
            KNOWN_ELEMENT_TYPES.add(Box_3003);
            KNOWN_ELEMENT_TYPES.add(Connection_4001);
        }
        return KNOWN_ELEMENT_TYPES.contains(elementType);
    }

    /**
     * @generated
     */
    public static IElementType getElementType(int visualID) {
        switch (visualID) {
        case DataflowModelEditPart.VISUAL_ID:
            return DataflowModel_1000;
        case BoxEditPart.VISUAL_ID:
            return Box_2001;
        case InputPortEditPart.VISUAL_ID:
            return InputPort_3001;
        case OutputPortEditPart.VISUAL_ID:
            return OutputPort_3002;
        case Box2EditPart.VISUAL_ID:
            return Box_3003;
        case ConnectionEditPart.VISUAL_ID:
            return Connection_4001;
        }
        return null;
    }

}
