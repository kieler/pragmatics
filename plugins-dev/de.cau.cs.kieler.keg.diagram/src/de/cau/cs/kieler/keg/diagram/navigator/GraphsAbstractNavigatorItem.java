package de.cau.cs.kieler.keg.diagram.navigator;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.PlatformObject;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;

/**
 * @generated
 */
public abstract class GraphsAbstractNavigatorItem extends PlatformObject {

    /**
     * @generated
     */
    static {
        final Class<?>[] supportedTypes = new Class[] { ITabbedPropertySheetPageContributor.class };
        final ITabbedPropertySheetPageContributor propertySheetPageContributor = new ITabbedPropertySheetPageContributor() {
            public String getContributorId() {
                return "de.cau.cs.kieler.keg.diagram"; //$NON-NLS-1$
            }
        };
        Platform.getAdapterManager().registerAdapters(new IAdapterFactory() {

            @SuppressWarnings("rawtypes")
            public Object getAdapter(Object adaptableObject, Class adapterType) {
                if (adaptableObject instanceof de.cau.cs.kieler.keg.diagram.navigator.GraphsAbstractNavigatorItem
                        && adapterType == ITabbedPropertySheetPageContributor.class) {
                    return propertySheetPageContributor;
                }
                return null;
            }

            public Class<?>[] getAdapterList() {
                return supportedTypes;
            }
        }, de.cau.cs.kieler.keg.diagram.navigator.GraphsAbstractNavigatorItem.class);
    }

    /**
     * @generated
     */
    private Object myParent;

    /**
     * @generated
     */
    protected GraphsAbstractNavigatorItem(Object parent) {
        myParent = parent;
    }

    /**
     * @generated
     */
    public Object getParent() {
        return myParent;
    }

}
