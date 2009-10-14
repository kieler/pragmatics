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
package de.cau.cs.kieler.dataflow.diagram.navigator;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.PlatformObject;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;

/**
 * @generated
 */
public abstract class DataflowAbstractNavigatorItem extends PlatformObject {

    /**
     * @generated
     */
    static {
        final Class[] supportedTypes = new Class[] { ITabbedPropertySheetPageContributor.class };
        final ITabbedPropertySheetPageContributor propertySheetPageContributor = new ITabbedPropertySheetPageContributor() {
            public String getContributorId() {
                return "de.cau.cs.kieler.dataflow.diagram"; //$NON-NLS-1$
            }
        };
        Platform.getAdapterManager().registerAdapters(new IAdapterFactory() {

            public Object getAdapter(Object adaptableObject, Class adapterType) {
                if (adaptableObject instanceof de.cau.cs.kieler.dataflow.diagram.navigator.DataflowAbstractNavigatorItem
                        && adapterType == ITabbedPropertySheetPageContributor.class) {
                    return propertySheetPageContributor;
                }
                return null;
            }

            public Class[] getAdapterList() {
                return supportedTypes;
            }
        }, de.cau.cs.kieler.dataflow.diagram.navigator.DataflowAbstractNavigatorItem.class);
    }

    /**
     * @generated
     */
    private Object myParent;

    /**
     * @generated
     */
    protected DataflowAbstractNavigatorItem(Object parent) {
        myParent = parent;
    }

    /**
     * @generated
     */
    public Object getParent() {
        return myParent;
    }

}
