/*
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
*/
package de.cau.cs.kieler.dataflow.diagram.part;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;

/**
 * @generated
 */
public class DataflowLinkDescriptor extends DataflowNodeDescriptor {

    /**
     * @generated
     */
    private EObject mySource;

    /**
     * @generated
     */
    private EObject myDestination;

    /**
     * @generated
     */
    private IAdaptable mySemanticAdapter;

    /**
     * @generated
     */
    private DataflowLinkDescriptor(EObject source, EObject destination, EObject linkElement,
            int linkVID) {
        super(linkElement, linkVID);
        mySource = source;
        myDestination = destination;
    }

    /**
     * @generated
     */
    public DataflowLinkDescriptor(EObject source, EObject destination, IElementType elementType,
            int linkVID) {
        this(source, destination, (EObject) null, linkVID);
        final IElementType elementTypeCopy = elementType;
        mySemanticAdapter = new IAdaptable() {
            public Object getAdapter(Class adapter) {
                if (IElementType.class.equals(adapter)) {
                    return elementTypeCopy;
                }
                return null;
            }
        };
    }

    /**
     * @generated
     */
    public DataflowLinkDescriptor(EObject source, EObject destination, EObject linkElement,
            IElementType elementType, int linkVID) {
        this(source, destination, linkElement, linkVID);
        final IElementType elementTypeCopy = elementType;
        mySemanticAdapter = new EObjectAdapter(linkElement) {
            public Object getAdapter(Class adapter) {
                if (IElementType.class.equals(adapter)) {
                    return elementTypeCopy;
                }
                return super.getAdapter(adapter);
            }
        };
    }

    /**
     * @generated
     */
    public EObject getSource() {
        return mySource;
    }

    /**
     * @generated
     */
    public EObject getDestination() {
        return myDestination;
    }

    /**
     * @generated
     */
    public IAdaptable getSemanticAdapter() {
        return mySemanticAdapter;
    }

}
