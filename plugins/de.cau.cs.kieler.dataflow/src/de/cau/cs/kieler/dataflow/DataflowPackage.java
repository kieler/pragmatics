/**
 * KIELER - Kiel Integrated Environment for Layout for the Eclipse RCP
 * 
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 *
 * $Id$
 */
package de.cau.cs.kieler.dataflow;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

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
 * @see de.cau.cs.kieler.dataflow.DataflowFactory
 * @model kind="package"
 * @generated
 */
public interface DataflowPackage extends EPackage {
    /**
	 * The package name.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    String eNAME = "dataflow";

    /**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    String eNS_URI = "http://kieler.cs.cau.de/Dataflow";

    /**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    String eNS_PREFIX = "dataflow";

    /**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    DataflowPackage eINSTANCE = de.cau.cs.kieler.dataflow.impl.DataflowPackageImpl.init();

    /**
	 * The meta object id for the '{@link de.cau.cs.kieler.dataflow.impl.BoxImpl <em>Box</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see de.cau.cs.kieler.dataflow.impl.BoxImpl
	 * @see de.cau.cs.kieler.dataflow.impl.DataflowPackageImpl#getBox()
	 * @generated
	 */
    int BOX = 0;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int BOX__NAME = 0;

    /**
	 * The feature id for the '<em><b>Inputs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int BOX__INPUTS = 1;

    /**
	 * The feature id for the '<em><b>Outputs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int BOX__OUTPUTS = 2;

    /**
	 * The feature id for the '<em><b>Boxes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int BOX__BOXES = 3;

    /**
	 * The feature id for the '<em><b>Connections</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int BOX__CONNECTIONS = 4;

    /**
	 * The number of structural features of the '<em>Box</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int BOX_FEATURE_COUNT = 5;

    /**
	 * The meta object id for the '{@link de.cau.cs.kieler.dataflow.impl.ConnectionImpl <em>Connection</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see de.cau.cs.kieler.dataflow.impl.ConnectionImpl
	 * @see de.cau.cs.kieler.dataflow.impl.DataflowPackageImpl#getConnection()
	 * @generated
	 */
    int CONNECTION = 1;

    /**
	 * The feature id for the '<em><b>Source Port</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int CONNECTION__SOURCE_PORT = 0;

    /**
	 * The feature id for the '<em><b>Target Port</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int CONNECTION__TARGET_PORT = 1;

    /**
	 * The number of structural features of the '<em>Connection</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int CONNECTION_FEATURE_COUNT = 2;

    /**
	 * The meta object id for the '{@link de.cau.cs.kieler.dataflow.impl.DataflowModelImpl <em>Model</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see de.cau.cs.kieler.dataflow.impl.DataflowModelImpl
	 * @see de.cau.cs.kieler.dataflow.impl.DataflowPackageImpl#getDataflowModel()
	 * @generated
	 */
    int DATAFLOW_MODEL = 2;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int DATAFLOW_MODEL__NAME = BOX__NAME;

    /**
	 * The feature id for the '<em><b>Inputs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int DATAFLOW_MODEL__INPUTS = BOX__INPUTS;

    /**
	 * The feature id for the '<em><b>Outputs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int DATAFLOW_MODEL__OUTPUTS = BOX__OUTPUTS;

    /**
	 * The feature id for the '<em><b>Boxes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int DATAFLOW_MODEL__BOXES = BOX__BOXES;

    /**
	 * The feature id for the '<em><b>Connections</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int DATAFLOW_MODEL__CONNECTIONS = BOX__CONNECTIONS;

    /**
	 * The number of structural features of the '<em>Model</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int DATAFLOW_MODEL_FEATURE_COUNT = BOX_FEATURE_COUNT + 0;

    /**
	 * The meta object id for the '{@link de.cau.cs.kieler.dataflow.impl.PortImpl <em>Port</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see de.cau.cs.kieler.dataflow.impl.PortImpl
	 * @see de.cau.cs.kieler.dataflow.impl.DataflowPackageImpl#getPort()
	 * @generated
	 */
    int PORT = 3;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int PORT__NAME = 0;

    /**
	 * The number of structural features of the '<em>Port</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int PORT_FEATURE_COUNT = 1;

    /**
	 * The meta object id for the '{@link de.cau.cs.kieler.dataflow.impl.InputPortImpl <em>Input Port</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see de.cau.cs.kieler.dataflow.impl.InputPortImpl
	 * @see de.cau.cs.kieler.dataflow.impl.DataflowPackageImpl#getInputPort()
	 * @generated
	 */
    int INPUT_PORT = 4;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int INPUT_PORT__NAME = PORT__NAME;

    /**
	 * The feature id for the '<em><b>Input Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int INPUT_PORT__INPUT_PARENT = PORT_FEATURE_COUNT + 0;

    /**
	 * The number of structural features of the '<em>Input Port</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int INPUT_PORT_FEATURE_COUNT = PORT_FEATURE_COUNT + 1;

    /**
	 * The meta object id for the '{@link de.cau.cs.kieler.dataflow.impl.OutputPortImpl <em>Output Port</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see de.cau.cs.kieler.dataflow.impl.OutputPortImpl
	 * @see de.cau.cs.kieler.dataflow.impl.DataflowPackageImpl#getOutputPort()
	 * @generated
	 */
    int OUTPUT_PORT = 5;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int OUTPUT_PORT__NAME = PORT__NAME;

    /**
	 * The feature id for the '<em><b>Output Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int OUTPUT_PORT__OUTPUT_PARENT = PORT_FEATURE_COUNT + 0;

    /**
	 * The number of structural features of the '<em>Output Port</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int OUTPUT_PORT_FEATURE_COUNT = PORT_FEATURE_COUNT + 1;


    /**
	 * Returns the meta object for class '{@link de.cau.cs.kieler.dataflow.Box <em>Box</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Box</em>'.
	 * @see de.cau.cs.kieler.dataflow.Box
	 * @generated
	 */
    EClass getBox();

    /**
	 * Returns the meta object for the attribute '{@link de.cau.cs.kieler.dataflow.Box#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see de.cau.cs.kieler.dataflow.Box#getName()
	 * @see #getBox()
	 * @generated
	 */
    EAttribute getBox_Name();

    /**
	 * Returns the meta object for the containment reference list '{@link de.cau.cs.kieler.dataflow.Box#getInputs <em>Inputs</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Inputs</em>'.
	 * @see de.cau.cs.kieler.dataflow.Box#getInputs()
	 * @see #getBox()
	 * @generated
	 */
    EReference getBox_Inputs();

    /**
	 * Returns the meta object for the containment reference list '{@link de.cau.cs.kieler.dataflow.Box#getOutputs <em>Outputs</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Outputs</em>'.
	 * @see de.cau.cs.kieler.dataflow.Box#getOutputs()
	 * @see #getBox()
	 * @generated
	 */
    EReference getBox_Outputs();

    /**
	 * Returns the meta object for the containment reference list '{@link de.cau.cs.kieler.dataflow.Box#getBoxes <em>Boxes</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Boxes</em>'.
	 * @see de.cau.cs.kieler.dataflow.Box#getBoxes()
	 * @see #getBox()
	 * @generated
	 */
    EReference getBox_Boxes();

    /**
	 * Returns the meta object for the containment reference list '{@link de.cau.cs.kieler.dataflow.Box#getConnections <em>Connections</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Connections</em>'.
	 * @see de.cau.cs.kieler.dataflow.Box#getConnections()
	 * @see #getBox()
	 * @generated
	 */
    EReference getBox_Connections();

    /**
	 * Returns the meta object for class '{@link de.cau.cs.kieler.dataflow.Connection <em>Connection</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Connection</em>'.
	 * @see de.cau.cs.kieler.dataflow.Connection
	 * @generated
	 */
    EClass getConnection();

    /**
	 * Returns the meta object for the reference '{@link de.cau.cs.kieler.dataflow.Connection#getSourcePort <em>Source Port</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Source Port</em>'.
	 * @see de.cau.cs.kieler.dataflow.Connection#getSourcePort()
	 * @see #getConnection()
	 * @generated
	 */
    EReference getConnection_SourcePort();

    /**
	 * Returns the meta object for the reference '{@link de.cau.cs.kieler.dataflow.Connection#getTargetPort <em>Target Port</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target Port</em>'.
	 * @see de.cau.cs.kieler.dataflow.Connection#getTargetPort()
	 * @see #getConnection()
	 * @generated
	 */
    EReference getConnection_TargetPort();

    /**
	 * Returns the meta object for class '{@link de.cau.cs.kieler.dataflow.DataflowModel <em>Model</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Model</em>'.
	 * @see de.cau.cs.kieler.dataflow.DataflowModel
	 * @generated
	 */
    EClass getDataflowModel();

    /**
	 * Returns the meta object for class '{@link de.cau.cs.kieler.dataflow.Port <em>Port</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Port</em>'.
	 * @see de.cau.cs.kieler.dataflow.Port
	 * @generated
	 */
    EClass getPort();

    /**
	 * Returns the meta object for the attribute '{@link de.cau.cs.kieler.dataflow.Port#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see de.cau.cs.kieler.dataflow.Port#getName()
	 * @see #getPort()
	 * @generated
	 */
    EAttribute getPort_Name();

    /**
	 * Returns the meta object for class '{@link de.cau.cs.kieler.dataflow.InputPort <em>Input Port</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Input Port</em>'.
	 * @see de.cau.cs.kieler.dataflow.InputPort
	 * @generated
	 */
    EClass getInputPort();

    /**
	 * Returns the meta object for the container reference '{@link de.cau.cs.kieler.dataflow.InputPort#getInputParent <em>Input Parent</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Input Parent</em>'.
	 * @see de.cau.cs.kieler.dataflow.InputPort#getInputParent()
	 * @see #getInputPort()
	 * @generated
	 */
    EReference getInputPort_InputParent();

    /**
	 * Returns the meta object for class '{@link de.cau.cs.kieler.dataflow.OutputPort <em>Output Port</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Output Port</em>'.
	 * @see de.cau.cs.kieler.dataflow.OutputPort
	 * @generated
	 */
    EClass getOutputPort();

    /**
	 * Returns the meta object for the container reference '{@link de.cau.cs.kieler.dataflow.OutputPort#getOutputParent <em>Output Parent</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Output Parent</em>'.
	 * @see de.cau.cs.kieler.dataflow.OutputPort#getOutputParent()
	 * @see #getOutputPort()
	 * @generated
	 */
    EReference getOutputPort_OutputParent();

    /**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
    DataflowFactory getDataflowFactory();

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
		 * The meta object literal for the '{@link de.cau.cs.kieler.dataflow.impl.BoxImpl <em>Box</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see de.cau.cs.kieler.dataflow.impl.BoxImpl
		 * @see de.cau.cs.kieler.dataflow.impl.DataflowPackageImpl#getBox()
		 * @generated
		 */
        EClass BOX = eINSTANCE.getBox();

        /**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute BOX__NAME = eINSTANCE.getBox_Name();

        /**
		 * The meta object literal for the '<em><b>Inputs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference BOX__INPUTS = eINSTANCE.getBox_Inputs();

        /**
		 * The meta object literal for the '<em><b>Outputs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference BOX__OUTPUTS = eINSTANCE.getBox_Outputs();

        /**
		 * The meta object literal for the '<em><b>Boxes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference BOX__BOXES = eINSTANCE.getBox_Boxes();

        /**
		 * The meta object literal for the '<em><b>Connections</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference BOX__CONNECTIONS = eINSTANCE.getBox_Connections();

        /**
		 * The meta object literal for the '{@link de.cau.cs.kieler.dataflow.impl.ConnectionImpl <em>Connection</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see de.cau.cs.kieler.dataflow.impl.ConnectionImpl
		 * @see de.cau.cs.kieler.dataflow.impl.DataflowPackageImpl#getConnection()
		 * @generated
		 */
        EClass CONNECTION = eINSTANCE.getConnection();

        /**
		 * The meta object literal for the '<em><b>Source Port</b></em>' reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference CONNECTION__SOURCE_PORT = eINSTANCE.getConnection_SourcePort();

        /**
		 * The meta object literal for the '<em><b>Target Port</b></em>' reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference CONNECTION__TARGET_PORT = eINSTANCE.getConnection_TargetPort();

        /**
		 * The meta object literal for the '{@link de.cau.cs.kieler.dataflow.impl.DataflowModelImpl <em>Model</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see de.cau.cs.kieler.dataflow.impl.DataflowModelImpl
		 * @see de.cau.cs.kieler.dataflow.impl.DataflowPackageImpl#getDataflowModel()
		 * @generated
		 */
        EClass DATAFLOW_MODEL = eINSTANCE.getDataflowModel();

        /**
		 * The meta object literal for the '{@link de.cau.cs.kieler.dataflow.impl.PortImpl <em>Port</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see de.cau.cs.kieler.dataflow.impl.PortImpl
		 * @see de.cau.cs.kieler.dataflow.impl.DataflowPackageImpl#getPort()
		 * @generated
		 */
        EClass PORT = eINSTANCE.getPort();

        /**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute PORT__NAME = eINSTANCE.getPort_Name();

        /**
		 * The meta object literal for the '{@link de.cau.cs.kieler.dataflow.impl.InputPortImpl <em>Input Port</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see de.cau.cs.kieler.dataflow.impl.InputPortImpl
		 * @see de.cau.cs.kieler.dataflow.impl.DataflowPackageImpl#getInputPort()
		 * @generated
		 */
        EClass INPUT_PORT = eINSTANCE.getInputPort();

        /**
		 * The meta object literal for the '<em><b>Input Parent</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference INPUT_PORT__INPUT_PARENT = eINSTANCE.getInputPort_InputParent();

        /**
		 * The meta object literal for the '{@link de.cau.cs.kieler.dataflow.impl.OutputPortImpl <em>Output Port</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see de.cau.cs.kieler.dataflow.impl.OutputPortImpl
		 * @see de.cau.cs.kieler.dataflow.impl.DataflowPackageImpl#getOutputPort()
		 * @generated
		 */
        EClass OUTPUT_PORT = eINSTANCE.getOutputPort();

        /**
		 * The meta object literal for the '<em><b>Output Parent</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference OUTPUT_PORT__OUTPUT_PARENT = eINSTANCE.getOutputPort_OutputParent();

    }

} //DataflowPackage
