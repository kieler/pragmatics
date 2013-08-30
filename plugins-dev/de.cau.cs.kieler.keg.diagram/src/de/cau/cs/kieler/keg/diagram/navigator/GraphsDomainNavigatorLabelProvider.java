package de.cau.cs.kieler.keg.diagram.navigator;

import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.ICommonLabelProvider;

import de.cau.cs.kieler.keg.diagram.part.GraphsDiagramEditorPlugin;

/**
 * @generated
 */
public class GraphsDomainNavigatorLabelProvider implements ICommonLabelProvider {

    /**
     * @generated
     */
    private AdapterFactoryLabelProvider myAdapterFactoryLabelProvider = new AdapterFactoryLabelProvider(
            GraphsDiagramEditorPlugin.getInstance().getItemProvidersAdapterFactory());

    /**
     * @generated
     */
    public void init(ICommonContentExtensionSite aConfig) {
    }

    /**
     * @generated
     */
    public Image getImage(Object element) {
        if (element instanceof GraphsDomainNavigatorItem) {
            return myAdapterFactoryLabelProvider.getImage(((GraphsDomainNavigatorItem) element)
                    .getEObject());
        }
        return null;
    }

    /**
     * @generated
     */
    public String getText(Object element) {
        if (element instanceof GraphsDomainNavigatorItem) {
            return myAdapterFactoryLabelProvider.getText(((GraphsDomainNavigatorItem) element)
                    .getEObject());
        }
        return null;
    }

    /**
     * @generated
     */
    public void addListener(ILabelProviderListener listener) {
        myAdapterFactoryLabelProvider.addListener(listener);
    }

    /**
     * @generated
     */
    public void dispose() {
        myAdapterFactoryLabelProvider.dispose();
    }

    /**
     * @generated
     */
    public boolean isLabelProperty(Object element, String property) {
        return myAdapterFactoryLabelProvider.isLabelProperty(element, property);
    }

    /**
     * @generated
     */
    public void removeListener(ILabelProviderListener listener) {
        myAdapterFactoryLabelProvider.removeListener(listener);
    }

    /**
     * @generated
     */
    public void restoreState(IMemento aMemento) {
    }

    /**
     * @generated
     */
    public void saveState(IMemento aMemento) {
    }

    /**
     * @generated
     */
    public String getDescription(Object anElement) {
        return null;
    }

}
