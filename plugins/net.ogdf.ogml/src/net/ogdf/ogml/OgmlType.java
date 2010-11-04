/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.ogdf.ogml;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * <!-- something missing -->
 * 	
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link net.ogdf.ogml.OgmlType#getKeys <em>Keys</em>}</li>
 *   <li>{@link net.ogdf.ogml.OgmlType#getGraph <em>Graph</em>}</li>
 * </ul>
 * </p>
 *
 * @see net.ogdf.ogml.OgmlPackage#getOgmlType()
 * @model extendedMetaData="name='ogml.type' kind='elementOnly'"
 * @generated
 */
public interface OgmlType extends EObject {
    /**
     * Returns the value of the '<em><b>Keys</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Keys</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Keys</em>' containment reference.
     * @see #setKeys(KeysType)
     * @see net.ogdf.ogml.OgmlPackage#getOgmlType_Keys()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='keys' namespace='##targetNamespace'"
     * @generated
     */
    KeysType getKeys();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.OgmlType#getKeys <em>Keys</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Keys</em>' containment reference.
     * @see #getKeys()
     * @generated
     */
    void setKeys(KeysType value);

    /**
     * Returns the value of the '<em><b>Graph</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Graph</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Graph</em>' containment reference.
     * @see #setGraph(GraphType)
     * @see net.ogdf.ogml.OgmlPackage#getOgmlType_Graph()
     * @model containment="true" required="true"
     *        extendedMetaData="kind='element' name='graph' namespace='##targetNamespace'"
     * @generated
     */
    GraphType getGraph();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.OgmlType#getGraph <em>Graph</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Graph</em>' containment reference.
     * @see #getGraph()
     * @generated
     */
    void setGraph(GraphType value);

} // OgmlType
