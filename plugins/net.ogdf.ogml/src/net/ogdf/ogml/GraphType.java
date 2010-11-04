/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.ogdf.ogml;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Graph Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 *       this is the main container for the described graph which is divided into structure and layout.
 *       structure: declarations of nodes (including compound-structure), edges, labels and application-data.
 *       layout:    appearance of nodes, edges, labels and templates. Also constraint-definitions.
 *     
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link net.ogdf.ogml.GraphType#getData <em>Data</em>}</li>
 *   <li>{@link net.ogdf.ogml.GraphType#getStructure <em>Structure</em>}</li>
 *   <li>{@link net.ogdf.ogml.GraphType#getLayout <em>Layout</em>}</li>
 * </ul>
 * </p>
 *
 * @see net.ogdf.ogml.OgmlPackage#getGraphType()
 * @model extendedMetaData="name='graph.type' kind='elementOnly'"
 * @generated
 */
public interface GraphType extends EObject {
    /**
     * Returns the value of the '<em><b>Data</b></em>' containment reference list.
     * The list contents are of type {@link net.ogdf.ogml.DataType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Data</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Data</em>' containment reference list.
     * @see net.ogdf.ogml.OgmlPackage#getGraphType_Data()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='data' namespace='##targetNamespace'"
     * @generated
     */
    EList<DataType> getData();

    /**
     * Returns the value of the '<em><b>Structure</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Structure</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Structure</em>' containment reference.
     * @see #setStructure(StructureType)
     * @see net.ogdf.ogml.OgmlPackage#getGraphType_Structure()
     * @model containment="true" required="true"
     *        extendedMetaData="kind='element' name='structure' namespace='##targetNamespace'"
     * @generated
     */
    StructureType getStructure();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.GraphType#getStructure <em>Structure</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Structure</em>' containment reference.
     * @see #getStructure()
     * @generated
     */
    void setStructure(StructureType value);

    /**
     * Returns the value of the '<em><b>Layout</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Layout</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Layout</em>' containment reference.
     * @see #setLayout(LayoutType)
     * @see net.ogdf.ogml.OgmlPackage#getGraphType_Layout()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='layout' namespace='##targetNamespace'"
     * @generated
     */
    LayoutType getLayout();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.GraphType#getLayout <em>Layout</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Layout</em>' containment reference.
     * @see #getLayout()
     * @generated
     */
    void setLayout(LayoutType value);

} // GraphType
