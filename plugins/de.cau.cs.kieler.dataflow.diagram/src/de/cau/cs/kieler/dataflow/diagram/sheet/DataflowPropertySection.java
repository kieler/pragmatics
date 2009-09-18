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
package de.cau.cs.kieler.dataflow.diagram.sheet;

import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.ui.provider.PropertySource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.properties.sections.AdvancedPropertySection;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.IPropertySourceProvider;

/**
 * @generated
 */
public class DataflowPropertySection extends AdvancedPropertySection implements
        IPropertySourceProvider {

    /**
     * @generated
     */
    public IPropertySource getPropertySource(Object object) {
        if (object instanceof IPropertySource) {
            return (IPropertySource) object;
        }
        AdapterFactory af = getAdapterFactory(object);
        if (af != null) {
            IItemPropertySource ips = (IItemPropertySource) af.adapt(object,
                    IItemPropertySource.class);
            if (ips != null) {
                return new PropertySource(object, ips);
            }
        }
        if (object instanceof IAdaptable) {
            return (IPropertySource) ((IAdaptable) object).getAdapter(IPropertySource.class);
        }
        return null;
    }

    /**
     * @generated
     */
    protected IPropertySourceProvider getPropertySourceProvider() {
        return this;
    }

    /**
     * Modify/unwrap selection.
     * @generated
     */
    protected Object transformSelection(Object selected) {

        if (selected instanceof EditPart) {
            Object model = ((EditPart) selected).getModel();
            return model instanceof View ? ((View) model).getElement() : null;
        }
        if (selected instanceof View) {
            return ((View) selected).getElement();
        }
        if (selected instanceof IAdaptable) {
            View view = (View) ((IAdaptable) selected).getAdapter(View.class);
            if (view != null) {
                return view.getElement();
            }
        }
        return selected;
    }

    /**
     * @generated
     */
    public void setInput(IWorkbenchPart part, ISelection selection) {
        if (selection.isEmpty() || false == selection instanceof StructuredSelection) {
            super.setInput(part, selection);
            return;
        }
        final StructuredSelection structuredSelection = ((StructuredSelection) selection);
        ArrayList transformedSelection = new ArrayList(structuredSelection.size());
        for (Iterator it = structuredSelection.iterator(); it.hasNext();) {
            Object r = transformSelection(it.next());
            if (r != null) {
                transformedSelection.add(r);
            }
        }
        super.setInput(part, new StructuredSelection(transformedSelection));
    }

    /**
     * @generated
     */
    protected AdapterFactory getAdapterFactory(Object object) {
        if (getEditingDomain() instanceof AdapterFactoryEditingDomain) {
            return ((AdapterFactoryEditingDomain) getEditingDomain()).getAdapterFactory();
        }
        TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(object);
        if (editingDomain != null) {
            return ((AdapterFactoryEditingDomain) editingDomain).getAdapterFactory();
        }
        return null;
    }

}
