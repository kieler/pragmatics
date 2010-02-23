package de.cau.cs.kieler.graphs.diagram.navigator;

import org.eclipse.jface.viewers.ViewerSorter;

import de.cau.cs.kieler.graphs.diagram.part.GraphsVisualIDRegistry;

/**
 * @generated
 */
public class GraphsNavigatorSorter extends ViewerSorter {

    /**
     * @generated
     */
    private static final int GROUP_CATEGORY = 7006;

    /**
     * @generated
     */
    public int category(Object element) {
        if (element instanceof GraphsNavigatorItem) {
            GraphsNavigatorItem item = (GraphsNavigatorItem) element;
            return GraphsVisualIDRegistry.getVisualID(item.getView());
        }
        return GROUP_CATEGORY;
    }

}
