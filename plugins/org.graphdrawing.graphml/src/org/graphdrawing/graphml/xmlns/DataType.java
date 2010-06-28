/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.graphdrawing.graphml.xmlns;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Data Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 *       Complex type for the &lt;data&gt; element.
 *       data.type is mixed, that is, &lt;data&gt; may contain #PCDATA.
 *       Content type: extension of data-extension.type which is empty
 *                     per default.
 *     
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.graphdrawing.graphml.xmlns.DataType#getId <em>Id</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.xmlns.DataType#getKey <em>Key</em>}</li>
 *   <li>{@link org.graphdrawing.graphml.xmlns.DataType#getTime <em>Time</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.graphdrawing.graphml.xmlns.XmlnsPackage#getDataType()
 * @model extendedMetaData="name='data.type' kind='mixed'"
 * @generated
 */
public interface DataType extends DataExtensionType {
	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * identifies this &lt;data&gt;.
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see org.graphdrawing.graphml.xmlns.XmlnsPackage#getDataType_Id()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.NMTOKEN"
	 *        extendedMetaData="kind='attribute' name='id'"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.graphdrawing.graphml.xmlns.DataType#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * refers to the id attribute of a &lt;key&gt;.
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Key</em>' attribute.
	 * @see #setKey(String)
	 * @see org.graphdrawing.graphml.xmlns.XmlnsPackage#getDataType_Key()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.NMTOKEN" required="true"
	 *        extendedMetaData="kind='attribute' name='key'"
	 * @generated
	 */
	String getKey();

	/**
	 * Sets the value of the '{@link org.graphdrawing.graphml.xmlns.DataType#getKey <em>Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Key</em>' attribute.
	 * @see #getKey()
	 * @generated
	 */
	void setKey(String value);

	/**
	 * Returns the value of the '<em><b>Time</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Time</em>' attribute.
	 * @see #isSetTime()
	 * @see #unsetTime()
	 * @see #setTime(long)
	 * @see org.graphdrawing.graphml.xmlns.XmlnsPackage#getDataType_Time()
	 * @model default="0" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Long"
	 *        extendedMetaData="kind='attribute' name='time'"
	 * @generated
	 */
	long getTime();

	/**
	 * Sets the value of the '{@link org.graphdrawing.graphml.xmlns.DataType#getTime <em>Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Time</em>' attribute.
	 * @see #isSetTime()
	 * @see #unsetTime()
	 * @see #getTime()
	 * @generated
	 */
	void setTime(long value);

	/**
	 * Unsets the value of the '{@link org.graphdrawing.graphml.xmlns.DataType#getTime <em>Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetTime()
	 * @see #getTime()
	 * @see #setTime(long)
	 * @generated
	 */
	void unsetTime();

	/**
	 * Returns whether the value of the '{@link org.graphdrawing.graphml.xmlns.DataType#getTime <em>Time</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Time</em>' attribute is set.
	 * @see #unsetTime()
	 * @see #getTime()
	 * @see #setTime(long)
	 * @generated
	 */
	boolean isSetTime();

} // DataType
