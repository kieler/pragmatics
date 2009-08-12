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
