package de.cau.cs.kieler.kaom.diagram.providers;

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

import de.cau.cs.kieler.kaom.KaomPackage;
import de.cau.cs.kieler.kaom.diagram.edit.parts.Entity2EditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.Entity3EditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.EntityEditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.LinkEditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.PortEditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.Relation2EditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.RelationEditPart;
import de.cau.cs.kieler.kaom.diagram.part.KaomDiagramEditorPlugin;

/**
 * @generated
 */
public class KaomElementTypes {

    /**
     * @generated
     */
    private KaomElementTypes() {
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
    public static final IElementType Entity_1000 = getElementType("de.cau.cs.kieler.kaom.diagram.Entity_1000"); //$NON-NLS-1$
    /**
     * @generated
     */
    public static final IElementType Entity_2001 = getElementType("de.cau.cs.kieler.kaom.diagram.Entity_2001"); //$NON-NLS-1$
    /**
     * @generated
     */
    public static final IElementType Relation_2002 = getElementType("de.cau.cs.kieler.kaom.diagram.Relation_2002"); //$NON-NLS-1$
    /**
     * @generated
     */
    public static final IElementType Port_3001 = getElementType("de.cau.cs.kieler.kaom.diagram.Port_3001"); //$NON-NLS-1$
    /**
     * @generated
     */
    public static final IElementType Entity_3002 = getElementType("de.cau.cs.kieler.kaom.diagram.Entity_3002"); //$NON-NLS-1$
    /**
     * @generated
     */
    public static final IElementType Relation_3003 = getElementType("de.cau.cs.kieler.kaom.diagram.Relation_3003"); //$NON-NLS-1$
    /**
     * @generated
     */
    public static final IElementType Link_4001 = getElementType("de.cau.cs.kieler.kaom.diagram.Link_4001"); //$NON-NLS-1$

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
            } else if (eType instanceof EClass && !((EClass) eType).isAbstract()) {
                element = eType;
            }
        }
        if (element instanceof EClass) {
            EClass eClass = (EClass) element;
            if (!eClass.isAbstract()) {
                return KaomDiagramEditorPlugin.getInstance().getItemImageDescriptor(
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
            elements = new IdentityHashMap<IElementType, ENamedElement>();

            elements.put(Entity_1000, KaomPackage.eINSTANCE.getEntity());

            elements.put(Entity_2001, KaomPackage.eINSTANCE.getEntity());

            elements.put(Relation_2002, KaomPackage.eINSTANCE.getRelation());

            elements.put(Port_3001, KaomPackage.eINSTANCE.getPort());

            elements.put(Entity_3002, KaomPackage.eINSTANCE.getEntity());

            elements.put(Relation_3003, KaomPackage.eINSTANCE.getRelation());

            elements.put(Link_4001, KaomPackage.eINSTANCE.getLink());
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
            KNOWN_ELEMENT_TYPES.add(Entity_1000);
            KNOWN_ELEMENT_TYPES.add(Entity_2001);
            KNOWN_ELEMENT_TYPES.add(Relation_2002);
            KNOWN_ELEMENT_TYPES.add(Port_3001);
            KNOWN_ELEMENT_TYPES.add(Entity_3002);
            KNOWN_ELEMENT_TYPES.add(Relation_3003);
            KNOWN_ELEMENT_TYPES.add(Link_4001);
        }
        return KNOWN_ELEMENT_TYPES.contains(elementType);
    }

    /**
     * @generated
     */
    public static IElementType getElementType(int visualID) {
        switch (visualID) {
        case EntityEditPart.VISUAL_ID:
            return Entity_1000;
        case Entity2EditPart.VISUAL_ID:
            return Entity_2001;
        case RelationEditPart.VISUAL_ID:
            return Relation_2002;
        case PortEditPart.VISUAL_ID:
            return Port_3001;
        case Entity3EditPart.VISUAL_ID:
            return Entity_3002;
        case Relation2EditPart.VISUAL_ID:
            return Relation_3003;
        case LinkEditPart.VISUAL_ID:
            return Link_4001;
        }
        return null;
    }

}
