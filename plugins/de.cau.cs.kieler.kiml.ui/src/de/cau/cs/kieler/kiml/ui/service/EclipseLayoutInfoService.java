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
package de.cau.cs.kieler.kiml.ui.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.StringTokenizer;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.statushandlers.StatusManager;

import de.cau.cs.kieler.core.model.GraphicalFrameworkService;
import de.cau.cs.kieler.core.model.IGraphicalFrameworkBridge;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.LayoutDataService;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.service.LayoutInfoService;
import de.cau.cs.kieler.kiml.ui.KimlUiPlugin;
import de.cau.cs.kieler.kiml.ui.diagram.DiagramLayoutManager;

/**
 * An extension of the layout info service for diagram layout managers and preference handling.
 *
 * @author msp
 */
public final class EclipseLayoutInfoService extends LayoutInfoService {
    
    /** identifier of the extension point for layout managers. */
    public static final String EXTP_ID_LAYOUT_MANAGERS = "de.cau.cs.kieler.kiml.ui.layoutManagers";
    /** name of the 'manager' element in the 'layout managers' extension point. */
    public static final String ELEMENT_MANAGER = "manager";
    /** name of the 'priority' attribute in the extension points. */
    public static final String ATTRIBUTE_PRIORITY = "priority";

    /** preference identifier for the list of registered diagram elements. */
    public static final String PREF_REG_ELEMENTS = "kiml.reg.elements";
    /** preference identifier for oblique edge routing. */
    public static final String PREF_OBLIQUE_ROUTE = "kiml.oblique.route";
    
    /** list of registered diagram layout managers. */
    private final List<DiagramLayoutManager<?>> managers = new LinkedList<DiagramLayoutManager<?>>();
    /** set of registered diagram elements. */
    private Set<String> registeredElements = new HashSet<String>();
    
    /** the singleton instance of the layout info service. */
    private static EclipseLayoutInfoService instance;
    
    /**
     * Returns the singleton instance of the layout info service.
     * 
     * @return the singleton instance
     */
    public static EclipseLayoutInfoService getInstance() {
        return instance;
    }

    /**
     * Hidden default constructor to prevent instantiation from outside this class.
     */
    private EclipseLayoutInfoService() {
    }
    
    /**
     * Create the layout info service and load extension points.
     */
    public static synchronized void create() {
        instance = new EclipseLayoutInfoService();
        instance.loadLayoutInfoExtensions();
        instance.loadLayoutManagerExtensions();
        // load preferences for KIML
        instance.loadPreferences();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void reportError(final String extensionPoint,
            final IConfigurationElement element, final String attribute, final Throwable exception) {
        String message;
        if (element != null && attribute != null) {
            message = "Extension point " + extensionPoint + ": Invalid entry in attribute '"
                    + attribute + "' of element " + element.getName() + ", contributed by "
                    + element.getContributor().getName();
        } else {
            message = "Extension point " + extensionPoint
                    + ": An error occured while loading extensions.";
        }
        IStatus status = new Status(IStatus.WARNING, KimlUiPlugin.PLUGIN_ID, 0, message, exception);
        StatusManager.getManager().handle(status);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void reportError(final CoreException exception) {
        StatusManager.getManager().handle(exception, KimlUiPlugin.PLUGIN_ID);
    }
    
    /**
     * Returns the most suitable layout manager for the given workbench and diagram part.
     * 
     * @param workbenchPart the workbench part for which the layout manager should be fetched
     * @param diagramPart the diagram part for which the layout manager should be
     *     fetched, or {@code null}
     * @return the most suitable diagram layout manager
     */
    public DiagramLayoutManager<?> getManager(final IWorkbenchPart workbenchPart,
            final Object diagramPart) {
        for (DiagramLayoutManager<?> manager : managers) {
            if (manager.supports(workbenchPart)
                    && (diagramPart == null || manager.supports(diagramPart))) {
                return manager;
            }
        }
        return null;
    }

    /**
     * Returns the preference name associated with the two identifiers.
     * 
     * @param id1 first identifier
     * @param id2 second identifier
     * @return a preference name for the combination of both identifiers
     */
    public static String getPreferenceName(final String id1, final String id2) {
        return id1 + "-" + id2; //$NON-NLS-1$
    }
    
    /**
     * Stores the layout option with given value for the diagram type.
     * 
     * @param diagramType a diagram type identifier
     * @param optionData a layout option data
     * @param valueString the value to store for the diagram type and option
     */
    public void storeOption(final String diagramType, final LayoutOptionData<?> optionData,
            final String valueString) {
        Object value = optionData.parseValue(valueString);
        if (value != null) {
            addOptionValue(diagramType, optionData.getId(), value);
            IPreferenceStore preferenceStore = KimlUiPlugin.getDefault().getPreferenceStore();
            preferenceStore.setValue(getPreferenceName(diagramType, optionData.getId()), valueString);
        }
    }
    
    /**
     * Stores the layout option with given value for the edit part.
     * 
     * @param editPart an edit part
     * @param optionData a layout option data
     * @param valueString the value to store for the edit part and option
     * @param storeDomainModel if true, the option is stored for the domain model element
     *     associated with the edit part, else for the edit part itself
     */
    public void storeOption(final EditPart editPart, final LayoutOptionData<?> optionData,
            final String valueString, final boolean storeDomainModel) {
        Object value = optionData.parseValue(valueString);
        IGraphicalFrameworkBridge bridge = GraphicalFrameworkService.getInstance().getBridge(editPart);
        if (value != null && bridge != null) {
            String clazzName;
            if (storeDomainModel) {
                EObject model = bridge.getElement(editPart);
                clazzName = model == null ? null : model.eClass().getInstanceTypeName();
            } else {
                EditPart relevantPart = bridge.getEditPart(editPart);
                clazzName = relevantPart == null ? null : relevantPart.getClass().getName();
            }
            if (clazzName != null) {
                addOptionValue(clazzName, optionData.getId(), value);
                registeredElements.add(clazzName);
                IPreferenceStore preferenceStore = KimlUiPlugin.getDefault().getPreferenceStore();
                preferenceStore.setValue(getPreferenceName(clazzName, optionData.getId()), valueString);
            }
        }
    }

    /**
     * Loads all diagram layout manager extensions from the extension point.
     */
    private void loadLayoutManagerExtensions() {
        IConfigurationElement[] extensions = Platform.getExtensionRegistry()
                .getConfigurationElementsFor(EXTP_ID_LAYOUT_MANAGERS);
        
        for (IConfigurationElement element : extensions) {
            if (ELEMENT_MANAGER.equals(element.getName())) {
                try {
                    DiagramLayoutManager<?> manager = (DiagramLayoutManager<?>)
                            element.createExecutableExtension(ATTRIBUTE_CLASS);
                    int priority = 0;
                    String prioEntry = element.getAttribute(ATTRIBUTE_PRIORITY);
                    if (prioEntry != null) {
                        try {
                            priority = Integer.parseInt(prioEntry);
                        } catch (NumberFormatException exception) {
                            // ignore exception
                        }
                    }
                    if (manager != null) {
                        insertManager(manager, priority);
                    }
                } catch (CoreException exception) {
                    StatusManager.getManager().handle(exception, KimlUiPlugin.PLUGIN_ID);
                }
            }
        }
    }
    
    /**
     * Insert the given diagram layout manager with a specific priority.
     * 
     * @param manager a diagram layout manager
     * @param priority priority at which the manager is inserted
     */
    private void insertManager(final DiagramLayoutManager<?> manager, final int priority) {
        ListIterator<DiagramLayoutManager<?>> iter = managers.listIterator();
        while (iter.hasNext()) {
            DiagramLayoutManager<?> next = iter.next();
            if (next.getPriority() <= priority) {
                iter.previous();
                break;
            }
        }
        iter.add(manager);
        manager.setPriority(priority);
    }

    /**
     * Loads preferences for KIML.
     */
    private void loadPreferences() {
        IPreferenceStore preferenceStore = KimlUiPlugin.getDefault().getPreferenceStore();
        LayoutDataService layoutDataService = LayoutDataService.getInstance();
        
        // load default options for diagram types
        List<Pair<String, String>> diagramTypes = getDiagramTypes();
        Collection<LayoutOptionData<?>> layoutOptionData = layoutDataService.getOptionData();
        for (Pair<String, String> diagramType : diagramTypes) {
            for (LayoutOptionData<?> data : layoutOptionData) {
                String preference = getPreferenceName(diagramType.getFirst(), data.getId());
                if (preferenceStore.contains(preference)) {
                    Object value = data.parseValue(preferenceStore.getString(preference));
                    if (value != null) {
                        addOptionValue(diagramType.getFirst(), data.getId(), value);
                    }
                }
            }
        }
        
        // load default options for diagram elements
        StringTokenizer editPartsTokenizer = new StringTokenizer(
                preferenceStore.getString(PREF_REG_ELEMENTS), ";");
        while (editPartsTokenizer.hasMoreTokens()) {
            registeredElements.add(editPartsTokenizer.nextToken());
        }
        for (String elementName : registeredElements) {
            for (LayoutOptionData<?> data : layoutOptionData) {
                String preference = getPreferenceName(elementName, data.getId());
                if (preferenceStore.contains(preference)) {
                    Object value = data.parseValue(preferenceStore.getString(preference));
                    if (value != null) {
                        addOptionValue(elementName, data.getId(), value);
                    }
                }
            }
        }
    }
    
    /**
     * Stores preferences for KIML.
     */
    public void storePreferences() {
        IPreferenceStore preferenceStore = KimlUiPlugin.getDefault().getPreferenceStore();

        // store set of registered diagram elements
        StringBuilder elementsString = new StringBuilder();
        for (String elementName : registeredElements) {
            elementsString.append(elementName + ";");
        }
        preferenceStore.setValue(PREF_REG_ELEMENTS, elementsString.toString());
    }

    /**
     * Returns the set of registered diagram elements.
     * 
     * @return the set of registered diagram elements
     */
    public Set<String> getRegisteredElements() {
        return registeredElements;
    }

}
