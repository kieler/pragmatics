package de.cau.cs.kieler.kaom.diagram.navigator;

import org.eclipse.jface.viewers.ViewerSorter;

import de.cau.cs.kieler.kaom.diagram.part.KaomVisualIDRegistry;

/**
 * @generated
 */
public class KaomNavigatorSorter extends ViewerSorter {

    /**
     * @generated
     */
    private static final int GROUP_CATEGORY = 7004;

    /**
     * @generated
     */
    public int category(Object element) {
        if (element instanceof KaomNavigatorItem) {
            KaomNavigatorItem item = (KaomNavigatorItem) element;
            return KaomVisualIDRegistry.getVisualID(item.getView());
        }
        return GROUP_CATEGORY;
    }

}
