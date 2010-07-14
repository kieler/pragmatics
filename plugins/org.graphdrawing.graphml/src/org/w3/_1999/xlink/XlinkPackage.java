/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.w3._1999.xlink;

import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.w3._1999.xlink.XlinkFactory
 * @model kind="package"
 * @generated
 */
public interface XlinkPackage extends EPackage {
    /**
     * The package name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "xlink";

    /**
     * The package namespace URI.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "http://www.w3.org/1999/xlink";

    /**
     * The package namespace name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "xlink";

    /**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    XlinkPackage eINSTANCE = org.w3._1999.xlink.impl.XlinkPackageImpl.init();

    /**
     * The meta object id for the '{@link org.w3._1999.xlink.TypeType <em>Type Type</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.w3._1999.xlink.TypeType
     * @see org.w3._1999.xlink.impl.XlinkPackageImpl#getTypeType()
     * @generated
     */
    int TYPE_TYPE = 0;

    /**
     * The meta object id for the '<em>Type Type Object</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.w3._1999.xlink.TypeType
     * @see org.w3._1999.xlink.impl.XlinkPackageImpl#getTypeTypeObject()
     * @generated
     */
    int TYPE_TYPE_OBJECT = 1;


    /**
     * Returns the meta object for enum '{@link org.w3._1999.xlink.TypeType <em>Type Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Type Type</em>'.
     * @see org.w3._1999.xlink.TypeType
     * @generated
     */
    EEnum getTypeType();

    /**
     * Returns the meta object for data type '{@link org.w3._1999.xlink.TypeType <em>Type Type Object</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for data type '<em>Type Type Object</em>'.
     * @see org.w3._1999.xlink.TypeType
     * @model instanceClass="org.w3._1999.xlink.TypeType"
     *        extendedMetaData="name='type_._type:Object' baseType='type_._type'"
     * @generated
     */
    EDataType getTypeTypeObject();

    /**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
    XlinkFactory getXlinkFactory();

    /**
     * <!-- begin-user-doc -->
     * Defines literals for the meta objects that represent
     * <ul>
     *   <li>each class,</li>
     *   <li>each feature of each class,</li>
     *   <li>each enum,</li>
     *   <li>and each data type</li>
     * </ul>
     * <!-- end-user-doc -->
     * @generated
     */
    interface Literals {
        /**
         * The meta object literal for the '{@link org.w3._1999.xlink.TypeType <em>Type Type</em>}' enum.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.w3._1999.xlink.TypeType
         * @see org.w3._1999.xlink.impl.XlinkPackageImpl#getTypeType()
         * @generated
         */
        EEnum TYPE_TYPE = eINSTANCE.getTypeType();

        /**
         * The meta object literal for the '<em>Type Type Object</em>' data type.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.w3._1999.xlink.TypeType
         * @see org.w3._1999.xlink.impl.XlinkPackageImpl#getTypeTypeObject()
         * @generated
         */
        EDataType TYPE_TYPE_OBJECT = eINSTANCE.getTypeTypeObject();

    }

} //XlinkPackage
