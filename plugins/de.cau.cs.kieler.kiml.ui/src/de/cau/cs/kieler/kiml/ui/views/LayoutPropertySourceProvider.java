/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.ui.views;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.IPropertySourceProvider;

import com.google.common.collect.Maps;

import de.cau.cs.kieler.core.ui.UnsupportedPartException;
import de.cau.cs.kieler.kiml.LayoutContext;
import de.cau.cs.kieler.kiml.config.ILayoutConfig;
import de.cau.cs.kieler.kiml.config.IMutableLayoutConfig;
import de.cau.cs.kieler.kiml.ui.diagram.DiagramLayoutEngine;
import de.cau.cs.kieler.kiml.ui.diagram.IDiagramLayoutManager;
import de.cau.cs.kieler.kiml.ui.service.EclipseLayoutConfig;
import de.cau.cs.kieler.kiml.ui.service.EclipseLayoutInfoService;
import de.cau.cs.kieler.kiml.ui.service.LayoutOptionManager;

/**
 * A property source provider used by the layout view.
 *
 * @author msp
 */
public class LayoutPropertySourceProvider implements IPropertySourceProvider {

    /** property sources that have been created for the current selection. */
    private final Map<Object, LayoutPropertySource> propertySources = Maps.newHashMap();
    /** the workbench part containing the current selection. */
    private IWorkbenchPart workbenchPart;
    
    /**
     * Reset the cached content of this property source provider, so new
     * property sources will be created on request.
     */
    public void resetContext() {
        propertySources.clear();
    }
    
    /**
     * Reset the cached content and set up for a new workbench part.
     * 
     * @param theworkbenchPart a workbench part, or {@code null}
     */
    public void resetContext(final IWorkbenchPart theworkbenchPart) {
        this.workbenchPart = theworkbenchPart;
        resetContext();
    }
    
    /**
     * {@inheritDoc}
     */
    public IPropertySource getPropertySource(final Object object) {
        if (propertySources.containsKey(object)) {
            return propertySources.get(object);
        }
        try {
            IDiagramLayoutManager<?> manager = EclipseLayoutInfoService.getInstance().getManager(
                    workbenchPart, object);
            if (manager != null) {
                LayoutOptionManager optionManager = DiagramLayoutEngine.INSTANCE.getOptionManager();
                Object diagramPart = manager.getAdapter(object, manager.getAdapterList()[0]);
                EObject domainElement = (EObject) manager.getAdapter(object, EObject.class);
                ILayoutConfig elc = (ILayoutConfig) manager.getAdapter(null, ILayoutConfig.class);
                TransactionalEditingDomain editingDomain = (TransactionalEditingDomain)
                        manager.getAdapter(object, TransactionalEditingDomain.class);
                if (diagramPart != null) {
                    IMutableLayoutConfig layoutConfig;
                    if (elc == null) {
                        layoutConfig = optionManager.createConfig(domainElement);
                    } else {
                        layoutConfig = optionManager.createConfig(domainElement, elc);
                    }
                    
                    LayoutContext context = new LayoutContext();
                    context.setProperty(EclipseLayoutConfig.WORKBENCH_PART, workbenchPart);
                    context.setProperty(LayoutContext.DOMAIN_MODEL, domainElement);
                    context.setProperty(LayoutContext.DIAGRAM_PART, diagramPart);
                    LayoutPropertySource propSource = new LayoutPropertySource(layoutConfig, context,
                            editingDomain);
                    
                    propertySources.put(object, propSource);
                    return propSource;
                }
            }
        } catch (UnsupportedPartException exception) {
            // ignore exception
        }
        return null;
    }
    
    /**
     * Return the currently tracked workbench part.
     * 
     * @return the current workbench part
     */
    public IWorkbenchPart getWorkbenchPart() {
        return workbenchPart;
    }
    
    /**
     * Return a layout context for the current selection. This context will merge
     * information of all objects in the selection, but most values will be overridden
     * by the last element of the selection.
     * 
     * @return a layout context for the selection
     */
    public LayoutContext getContext() {
        Collection<LayoutPropertySource> sources = propertySources.values();
        LayoutContext context = new LayoutContext();
        for (LayoutPropertySource s : sources) {
            context.copyProperties(s.getContext());
        }
        return context;
    }
    
    /**
     * Determine whether the property source provider has any cached content.
     * 
     * @return true if there is cached content
     */
    public boolean hasContent() {
        return !propertySources.isEmpty();
    }

}
