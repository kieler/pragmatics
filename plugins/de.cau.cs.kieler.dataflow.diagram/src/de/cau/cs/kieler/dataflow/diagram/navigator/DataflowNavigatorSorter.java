/*
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
*/
package de.cau.cs.kieler.dataflow.diagram.navigator;

import org.eclipse.jface.viewers.ViewerSorter;

import de.cau.cs.kieler.dataflow.diagram.part.DataflowVisualIDRegistry;

/**
 * @generated
 */
public class DataflowNavigatorSorter extends ViewerSorter {

    /**
     * @generated
     */
    private static final int GROUP_CATEGORY = 7004;

    /**
     * @generated
     */
    public int category(Object element) {
        if (element instanceof DataflowNavigatorItem) {
            DataflowNavigatorItem item = (DataflowNavigatorItem) element;
            return DataflowVisualIDRegistry.getVisualID(item.getView());
        }
        return GROUP_CATEGORY;
    }

}
