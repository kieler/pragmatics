/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.cs.kieler.kwebs.kstatistics;

import de.cau.cs.kieler.core.kgraph.KGraphData;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>KStatistics</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A Container holding statistical data about a local or remote layout operation.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.kwebs.kstatistics.KStatistics#getType <em>Type</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kwebs.kstatistics.KStatistics#getBytes <em>Bytes</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kwebs.kstatistics.KStatistics#isCompression <em>Compression</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kwebs.kstatistics.KStatistics#getEdges <em>Edges</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kwebs.kstatistics.KStatistics#getNodes <em>Nodes</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kwebs.kstatistics.KStatistics#getPorts <em>Ports</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kwebs.kstatistics.KStatistics#getLabels <em>Labels</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kwebs.kstatistics.KStatistics#getTimeTotal <em>Time Total</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kwebs.kstatistics.KStatistics#getTimeNetwork <em>Time Network</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kwebs.kstatistics.KStatistics#getTimeLayout <em>Time Layout</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kwebs.kstatistics.KStatistics#getTimeLocalSupplemental <em>Time Local Supplemental</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kwebs.kstatistics.KStatistics#getTimeRemoteSupplemental <em>Time Remote Supplemental</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.kwebs.kstatistics.KStatisticsPackage#getKStatistics()
 * @model
 * @generated
 */
public interface KStatistics extends KGraphData {

    /**
     * Returns the value of the '<em><b>Type</b></em>' attribute.
     * The default value is <code>""</code>.
     * The literals are from the enumeration {@link de.cau.cs.kieler.kwebs.kstatistics.LayoutType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Type</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The type of layout the statiscics is used for.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Type</em>' attribute.
     * @see de.cau.cs.kieler.kwebs.kstatistics.LayoutType
     * @see #setType(LayoutType)
     * @see de.cau.cs.kieler.kwebs.kstatistics.KStatisticsPackage#getKStatistics_Type()
     * @model default="" required="true"
     * @generated
     */
    LayoutType getType();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.kwebs.kstatistics.KStatistics#getType <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Type</em>' attribute.
     * @see de.cau.cs.kieler.kwebs.kstatistics.LayoutType
     * @see #getType()
     * @generated
     */
    void setType(LayoutType value);

    /**
     * Returns the value of the '<em><b>Bytes</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Bytes</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The number of bytes of the serial representation of the graph.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Bytes</em>' attribute.
     * @see #setBytes(int)
     * @see de.cau.cs.kieler.kwebs.kstatistics.KStatisticsPackage#getKStatistics_Bytes()
     * @model
     * @generated
     */
    int getBytes();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.kwebs.kstatistics.KStatistics#getBytes <em>Bytes</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Bytes</em>' attribute.
     * @see #getBytes()
     * @generated
     */
    void setBytes(int value);

    /**
     * Returns the value of the '<em><b>Compression</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Compression</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Whether the used transfer format used compression or not.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Compression</em>' attribute.
     * @see #setCompression(boolean)
     * @see de.cau.cs.kieler.kwebs.kstatistics.KStatisticsPackage#getKStatistics_Compression()
     * @model
     * @generated
     */
    boolean isCompression();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.kwebs.kstatistics.KStatistics#isCompression <em>Compression</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Compression</em>' attribute.
     * @see #isCompression()
     * @generated
     */
    void setCompression(boolean value);

    /**
     * Returns the value of the '<em><b>Edges</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Returns the total number of edges of the graph that was layouted.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Edges</em>' attribute.
     * @see #setEdges(int)
     * @see de.cau.cs.kieler.kwebs.kstatistics.KStatisticsPackage#getKStatistics_Edges()
     * @model
     * @generated
     */
    int getEdges();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.kwebs.kstatistics.KStatistics#getEdges <em>Edges</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Edges</em>' attribute.
     * @see #getEdges()
     * @generated
     */
    void setEdges(int value);

    /**
     * Returns the value of the '<em><b>Nodes</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Returns the total number of nodes of the graph that was layouted.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Nodes</em>' attribute.
     * @see #setNodes(int)
     * @see de.cau.cs.kieler.kwebs.kstatistics.KStatisticsPackage#getKStatistics_Nodes()
     * @model
     * @generated
     */
    int getNodes();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.kwebs.kstatistics.KStatistics#getNodes <em>Nodes</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Nodes</em>' attribute.
     * @see #getNodes()
     * @generated
     */
    void setNodes(int value);

    /**
     * Returns the value of the '<em><b>Ports</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Returns the total number of ports of the graph that was layouted.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Ports</em>' attribute.
     * @see #setPorts(int)
     * @see de.cau.cs.kieler.kwebs.kstatistics.KStatisticsPackage#getKStatistics_Ports()
     * @model
     * @generated
     */
    int getPorts();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.kwebs.kstatistics.KStatistics#getPorts <em>Ports</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Ports</em>' attribute.
     * @see #getPorts()
     * @generated
     */
    void setPorts(int value);

    /**
     * Returns the value of the '<em><b>Labels</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Returns the total number of labels of the graph that was layouted.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Labels</em>' attribute.
     * @see #setLabels(int)
     * @see de.cau.cs.kieler.kwebs.kstatistics.KStatisticsPackage#getKStatistics_Labels()
     * @model
     * @generated
     */
    int getLabels();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.kwebs.kstatistics.KStatistics#getLabels <em>Labels</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Labels</em>' attribute.
     * @see #getLabels()
     * @generated
     */
    void setLabels(int value);

    /**
     * Returns the value of the '<em><b>Time Total</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The total time needed for the layout process. The time is measured in nano seconds.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Time Total</em>' attribute.
     * @see #setTimeTotal(double)
     * @see de.cau.cs.kieler.kwebs.kstatistics.KStatisticsPackage#getKStatistics_TimeTotal()
     * @model
     * @generated
     */
    double getTimeTotal();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.kwebs.kstatistics.KStatistics#getTimeTotal <em>Time Total</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Time Total</em>' attribute.
     * @see #getTimeTotal()
     * @generated
     */
    void setTimeTotal(double value);

    /**
     * Returns the value of the '<em><b>Time Network</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The time needed for network transport if remote layout is used. The time is measured in nano seconds.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Time Network</em>' attribute.
     * @see #setTimeNetwork(double)
     * @see de.cau.cs.kieler.kwebs.kstatistics.KStatisticsPackage#getKStatistics_TimeNetwork()
     * @model
     * @generated
     */
    double getTimeNetwork();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.kwebs.kstatistics.KStatistics#getTimeNetwork <em>Time Network</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Time Network</em>' attribute.
     * @see #getTimeNetwork()
     * @generated
     */
    void setTimeNetwork(double value);

    /**
     * Returns the value of the '<em><b>Time Layout</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The total time needed for the local layout process. The time is measured in nano seconds.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Time Layout</em>' attribute.
     * @see #setTimeLayout(double)
     * @see de.cau.cs.kieler.kwebs.kstatistics.KStatisticsPackage#getKStatistics_TimeLayout()
     * @model
     * @generated
     */
    double getTimeLayout();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.kwebs.kstatistics.KStatistics#getTimeLayout <em>Time Layout</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Time Layout</em>' attribute.
     * @see #getTimeLayout()
     * @generated
     */
    void setTimeLayout(double value);

    /**
     * Returns the value of the '<em><b>Time Local Supplemental</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The total time needed locally for supplementary operations. The time is measured in nano seconds.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Time Local Supplemental</em>' attribute.
     * @see #setTimeLocalSupplemental(double)
     * @see de.cau.cs.kieler.kwebs.kstatistics.KStatisticsPackage#getKStatistics_TimeLocalSupplemental()
     * @model
     * @generated
     */
    double getTimeLocalSupplemental();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.kwebs.kstatistics.KStatistics#getTimeLocalSupplemental <em>Time Local Supplemental</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Time Local Supplemental</em>' attribute.
     * @see #getTimeLocalSupplemental()
     * @generated
     */
    void setTimeLocalSupplemental(double value);

    /**
     * Returns the value of the '<em><b>Time Remote Supplemental</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The total time needed remotely for supplementary operations. The time is measured in nano seconds.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Time Remote Supplemental</em>' attribute.
     * @see #setTimeRemoteSupplemental(double)
     * @see de.cau.cs.kieler.kwebs.kstatistics.KStatisticsPackage#getKStatistics_TimeRemoteSupplemental()
     * @model
     * @generated
     */
    double getTimeRemoteSupplemental();

    /**
     * Sets the value of the '{@link de.cau.cs.kieler.kwebs.kstatistics.KStatistics#getTimeRemoteSupplemental <em>Time Remote Supplemental</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Time Remote Supplemental</em>' attribute.
     * @see #getTimeRemoteSupplemental()
     * @generated
     */
    void setTimeRemoteSupplemental(double value);

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Returns the total number of elements of the graph that was layouted.
     * <!-- end-model-doc -->
     * @model kind="operation" required="true"
     * @generated
     */
    int getElementCount();

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Returns the number of elements layouted per second. This value is being calculated on the basis of the total time needed for calculating the layout including network transfer.
     * <!-- end-model-doc -->
     * @model kind="operation" required="true"
     * @generated
     */
    double getTotalSpeed();

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Returns the number of elements layouted per second. This value is being calculated on the basis of the time the server needed to calculate the layout without network transfer.
     * <!-- end-model-doc -->
     * @model kind="operation" required="true"
     * @generated
     */
    double getLayoutSpeed();

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Returns the part of the layout process responsible for network transfer in percent.
     * <!-- end-model-doc -->
     * @model kind="operation"
     * @generated
     */
    double getNetworkPart();

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Returns the part of the layout process responsible for calculating the layout in percent.
     * <!-- end-model-doc -->
     * @model kind="operation"
     * @generated
     */
    double getLayoutPart();

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Returns the part of the layout process responsible for supplementary operations in percent.
     * <!-- end-model-doc -->
     * @model kind="operation"
     * @generated
     */
    double getSupplementalPart();

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Returns the part of the layout process responsible for local supplementary operations in percent.
     * <!-- end-model-doc -->
     * @model kind="operation"
     * @generated
     */
    double getLocalSupplementalPart();

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Returns the part of the layout process responsible for remote supplementary operations in percent.
     * <!-- end-model-doc -->
     * @model kind="operation"
     * @generated
     */
    double getRemoteSupplementalPart();
} // KStatistics
