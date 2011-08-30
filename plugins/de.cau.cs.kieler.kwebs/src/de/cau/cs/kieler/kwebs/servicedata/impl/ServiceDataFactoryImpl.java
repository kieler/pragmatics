/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.cs.kieler.kwebs.servicedata.impl;

import de.cau.cs.kieler.kwebs.servicedata.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ServiceDataFactoryImpl extends EFactoryImpl implements ServiceDataFactory {
    /**
     * Creates the default factory implementation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static ServiceDataFactory init() {
        try {
            ServiceDataFactory theServiceDataFactory = (ServiceDataFactory)EPackage.Registry.INSTANCE.getEFactory("http://de.cau.cs.kieler.kwebs.service.data/2011-07-25/ServiceData/1.0"); 
            if (theServiceDataFactory != null) {
                return theServiceDataFactory;
            }
        }
        catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new ServiceDataFactoryImpl();
    }

    /**
     * Creates an instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ServiceDataFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
            case ServiceDataPackage.SERVICE_DATA: return createServiceData();
            case ServiceDataPackage.LAYOUT_ALGORITHM: return createLayoutAlgorithm();
            case ServiceDataPackage.LAYOUT_TYPE: return createLayoutType();
            case ServiceDataPackage.LAYOUT_OPTION: return createLayoutOption();
            case ServiceDataPackage.CATEGORY: return createCategory();
            case ServiceDataPackage.KNOWN_OPTION: return createKnownOption();
            case ServiceDataPackage.SUPPORTED_DIAGRAM: return createSupportedDiagram();
            case ServiceDataPackage.REMOTE_ENUM: return createRemoteEnum();
            case ServiceDataPackage.SUPPORTED_FORMAT: return createSupportedFormat();
            default:
                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ServiceData createServiceData() {
        ServiceDataImpl serviceData = new ServiceDataImpl();
        return serviceData;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public LayoutAlgorithm createLayoutAlgorithm() {
        LayoutAlgorithmImpl layoutAlgorithm = new LayoutAlgorithmImpl();
        return layoutAlgorithm;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public LayoutType createLayoutType() {
        LayoutTypeImpl layoutType = new LayoutTypeImpl();
        return layoutType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public LayoutOption createLayoutOption() {
        LayoutOptionImpl layoutOption = new LayoutOptionImpl();
        return layoutOption;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Category createCategory() {
        CategoryImpl category = new CategoryImpl();
        return category;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KnownOption createKnownOption() {
        KnownOptionImpl knownOption = new KnownOptionImpl();
        return knownOption;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SupportedDiagram createSupportedDiagram() {
        SupportedDiagramImpl supportedDiagram = new SupportedDiagramImpl();
        return supportedDiagram;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public RemoteEnum createRemoteEnum() {
        RemoteEnumImpl remoteEnum = new RemoteEnumImpl();
        return remoteEnum;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SupportedFormat createSupportedFormat() {
        SupportedFormatImpl supportedFormat = new SupportedFormatImpl();
        return supportedFormat;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ServiceDataPackage getServiceDataPackage() {
        return (ServiceDataPackage)getEPackage();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
    @Deprecated
    public static ServiceDataPackage getPackage() {
        return ServiceDataPackage.eINSTANCE;
    }

} //ServiceDataFactoryImpl
