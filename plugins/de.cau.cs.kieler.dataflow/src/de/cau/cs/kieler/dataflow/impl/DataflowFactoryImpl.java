/**
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
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
package de.cau.cs.kieler.dataflow.impl;

import de.cau.cs.kieler.dataflow.*;

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
public class DataflowFactoryImpl extends EFactoryImpl implements DataflowFactory {
    /**
     * Creates the default factory implementation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static DataflowFactory init() {
        try {
            DataflowFactory theDataflowFactory = (DataflowFactory)EPackage.Registry.INSTANCE.getEFactory("http://kieler.cs.cau.de/Dataflow"); 
            if (theDataflowFactory != null) {
                return theDataflowFactory;
            }
        }
        catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new DataflowFactoryImpl();
    }

    /**
     * Creates an instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DataflowFactoryImpl() {
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
            case DataflowPackage.DATAFLOW_MODEL: return createDataflowModel();
            case DataflowPackage.BOX: return createBox();
            case DataflowPackage.CONNECTION: return createConnection();
            case DataflowPackage.PORT: return createPort();
            case DataflowPackage.INPUT_PORT: return createInputPort();
            case DataflowPackage.OUTPUT_PORT: return createOutputPort();
            default:
                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DataflowModel createDataflowModel() {
        DataflowModelImpl dataflowModel = new DataflowModelImpl();
        return dataflowModel;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Box createBox() {
        BoxImpl box = new BoxImpl();
        return box;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Connection createConnection() {
        ConnectionImpl connection = new ConnectionImpl();
        return connection;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Port createPort() {
        PortImpl port = new PortImpl();
        return port;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public InputPort createInputPort() {
        InputPortImpl inputPort = new InputPortImpl();
        return inputPort;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public OutputPort createOutputPort() {
        OutputPortImpl outputPort = new OutputPortImpl();
        return outputPort;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DataflowPackage getDataflowPackage() {
        return (DataflowPackage)getEPackage();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
    @Deprecated
    public static DataflowPackage getPackage() {
        return DataflowPackage.eINSTANCE;
    }

} //DataflowFactoryImpl
