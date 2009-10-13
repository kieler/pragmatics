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

import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.ICommonLabelProvider;

import de.cau.cs.kieler.dataflow.diagram.part.DataflowDiagramEditorPlugin;

/**
 * @generated
 */
public class DataflowDomainNavigatorLabelProvider implements ICommonLabelProvider {

    /**
     * @generated
     */
    private AdapterFactoryLabelProvider myAdapterFactoryLabelProvider = new AdapterFactoryLabelProvider(
            DataflowDiagramEditorPlugin.getInstance().getItemProvidersAdapterFactory());

    /**
     * @generated
     */
    public void init(ICommonContentExtensionSite aConfig) {
    }

    /**
     * @generated
     */
    public Image getImage(Object element) {
        if (element instanceof DataflowDomainNavigatorItem) {
            return myAdapterFactoryLabelProvider.getImage(((DataflowDomainNavigatorItem) element)
                    .getEObject());
        }
        return null;
    }

    /**
     * @generated
     */
    public String getText(Object element) {
        if (element instanceof DataflowDomainNavigatorItem) {
            return myAdapterFactoryLabelProvider.getText(((DataflowDomainNavigatorItem) element)
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
