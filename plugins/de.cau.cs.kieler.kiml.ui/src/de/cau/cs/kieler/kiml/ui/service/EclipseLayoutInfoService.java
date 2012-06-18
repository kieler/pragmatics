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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.StringTokenizer;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.statushandlers.StatusManager;

import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.IGraphLayoutEngine;
import de.cau.cs.kieler.kiml.LayoutDataService;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.service.LayoutInfoService;
import de.cau.cs.kieler.kiml.ui.KimlUiPlugin;
import de.cau.cs.kieler.kiml.ui.diagram.IDiagramLayoutManager;

/**
 * An extension of the layout info service for diagram layout managers and preference handling.
 *
 * @author msp
 */
public final class EclipseLayoutInfoService extends LayoutInfoService implements IAdapterFactory {
    
    /** identifier of the extension point for layout managers. */
    public static final String EXTP_ID_LAYOUT_MANAGERS = "de.cau.cs.kieler.kiml.ui.layoutManagers";
    /** name of the 'manager' element in the 'layout managers' extension point. */
    public static final String ELEMENT_MANAGER = "manager";
    /** name of the 'engine' element in the 'layout managers' extension point. */
    public static final String ELEMENT_ENGINE = "engine";
    /** name of the 'priority' attribute in the extension points. */
    public static final String ATTRIBUTE_PRIORITY = "priority";

    /** preference identifier for the list of registered diagram elements. */
    public static final String PREF_REG_ELEMENTS = "kiml.reg.elements";
    /** preference identifier for oblique edge routing. */
    public static final String PREF_OBLIQUE_ROUTE = "kiml.oblique.route";
    
    /** list of registered diagram layout managers. */
    private final List<Pair<Integer, IDiagramLayoutManager<?>>> managers
            = new LinkedList<Pair<Integer, IDiagramLayoutManager<?>>>();
    /** list of registered graph layout engines. */
    private final List<Pair<Integer, IGraphLayoutEngine>> layoutEngines
            = new LinkedList<Pair<Integer, IGraphLayoutEngine>>();
    /** set of registered diagram elements. */
    private Set<String> registeredElements = new HashSet<String>();
    
    /**
     * Returns the singleton instance of the layout info service.
     * 
     * @return the singleton instance
     */
    public static EclipseLayoutInfoService getInstance() {
        return (EclipseLayoutInfoService) LayoutInfoService.getInstance();
    }

    /**
     * Hidden default constructor to prevent instantiation from outside this class.
     */
    private EclipseLayoutInfoService() {
        super();
    }
    
    /**
     * Create the layout info service and load extension points.
     */
    public static synchronized void create() {
        // creating an instance stores this instance as the singleton instance
        EclipseLayoutInfoService instance = new EclipseLayoutInfoService();
        instance.loadLayoutInfoExtensions();
        instance.loadLayoutManagerExtensions();
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
     * @param workbenchPart the workbench part for which the layout manager should be
     *     fetched, or {@code null}
     * @param diagramPart the diagram part for which the layout manager should be
     *     fetched, or {@code null}
     * @return the most suitable diagram layout manager
     */
    public IDiagramLayoutManager<?> getManager(final IWorkbenchPart workbenchPart,
            final Object diagramPart) {
        for (Pair<Integer, IDiagramLayoutManager<?>> entry : managers) {
            IDiagramLayoutManager<?> manager = entry.getSecond();
            if (workbenchPart == null) {
                if (manager.supports(diagramPart)) {
                    return manager;
                }
            } else if (manager.supports(workbenchPart)
                    && (diagramPart == null || manager.supports(diagramPart))) {
                return manager;
            }
        }
        return null;
    }

    /**
     * Query the registered layout managers for an adapter of given type. The manager of highest
     * priority that supports the given adaptable object is called. If the adapter type is
     * {@code null}, the layout manager's default diagram part type is taken.
     * 
     * @param adaptableObject an object that is supported by one of the layout managers,
     *          typically a diagram part
     * @param adapterType expected type of return value, or {@code null} if the default type
     *          shall be used
     * @return a object castable to the given adapter type, or {@code null}
     */
    @SuppressWarnings("rawtypes")
    public Object getAdapter(final Object adaptableObject, final Class adapterType) {
        IDiagramLayoutManager<?> manager = null;
        for (Pair<Integer, IDiagramLayoutManager<?>> entry : managers) {
            if (entry.getSecond().supports(adaptableObject)) {
                manager = entry.getSecond();
                break;
            }
        }
        if (manager != null) {
            if (adapterType == null) {
                // use the layout manager's diagram part type as adapter type
                return manager.getAdapter(adaptableObject, manager.getAdapterList()[0]);
            } else {
                // use the adapter type given as parameter
                return manager.getAdapter(adaptableObject, adapterType);
            }
        }
        return null;
    }

    /**
     * Compute an array of all adapter types supported by registered layout managers.
     * These are typically the diagram part types that are used for mapping diagrams to
     * layout graph elements.
     * 
     * @return adapter types supported by registered layout managers
     */
    public Class<?>[] getAdapterList() {
        ArrayList<Class<?>> resultList = new ArrayList<Class<?>>();
        for (Pair<Integer, IDiagramLayoutManager<?>> entry : managers) {
            IDiagramLayoutManager<?> manager = entry.getSecond();
            for (Class<?> adapterType : manager.getAdapterList()) {
                resultList.add(adapterType);
            }
        }
        return resultList.toArray(new Class<?>[resultList.size()]);
    }
    
    /**
     * Returns the active graph layout engine with highest priority.
     * 
     * @return the active graph layout engine with highest priority
     */
    public IGraphLayoutEngine getLayoutEngine() {
        for (Pair<Integer, IGraphLayoutEngine> entry : layoutEngines) {
            IGraphLayoutEngine engine = entry.getSecond();
            if (engine.isActive()) {
                return engine;
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
     * @param diagramPart a diagram part
     * @param optionData a layout option data
     * @param valueString the value to store for the edit part and option
     * @param storeDomainModel if true, the option is stored for the domain model element
     *     associated with the edit part, else for the edit part itself
     */
    public void storeOption(final Object diagramPart, final LayoutOptionData<?> optionData,
            final String valueString, final boolean storeDomainModel) {
        Object value = optionData.parseValue(valueString);
        if (value != null) {
            String clazzName;
            if (storeDomainModel) {
                EObject model = (EObject) getAdapter(diagramPart, EObject.class);
                clazzName = model == null ? null : model.eClass().getInstanceTypeName();
            } else {
                Object relevantPart = getAdapter(diagramPart, null);
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
            try {
                if (ELEMENT_MANAGER.equals(element.getName())) {
                    IDiagramLayoutManager<?> manager = (IDiagramLayoutManager<?>)
                            element.createExecutableExtension(ATTRIBUTE_CLASS);
                    if (manager != null) {
                        int priority = 0;
                        String prioEntry = element.getAttribute(ATTRIBUTE_PRIORITY);
                        if (prioEntry != null) {
                            try {
                                priority = Integer.parseInt(prioEntry);
                            } catch (NumberFormatException exception) {
                                // ignore exception
                            }
                        }
                        insertSorted(manager, priority, managers);
                    }
                    
                } else if (ELEMENT_ENGINE.equals(element.getName())) {
                    IGraphLayoutEngine engine = (IGraphLayoutEngine)
                            element.createExecutableExtension(ATTRIBUTE_CLASS);
                    if (engine != null) {
                        int priority = 0;
                        String prioEntry = element.getAttribute(ATTRIBUTE_PRIORITY);
                        if (prioEntry != null) {
                            try {
                                priority = Integer.parseInt(prioEntry);
                            } catch (NumberFormatException exception) {
                                // ignore exception
                            }
                        }
                        insertSorted(engine, priority, layoutEngines);
                    }
                }
            } catch (CoreException exception) {
                StatusManager.getManager().handle(exception, KimlUiPlugin.PLUGIN_ID);
            }
        }
    }
    
    /**
     * Insert the given object into a sorted list.
     * 
     * @param object the object to insert
     * @param priority priority at which the object is inserted
     * @param list list of sorted objects
     */
    private static <T> void insertSorted(final T object, final int priority,
            final List<Pair<Integer, T>> list) {
        ListIterator<Pair<Integer, T>> iter = list.listIterator();
        while (iter.hasNext()) {
            Pair<Integer, T> next = iter.next();
            if (next.getFirst() <= priority) {
                iter.previous();
                break;
            }
        }
        iter.add(new Pair<Integer, T>(priority, object));
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
    
    /**
     * Fill the given menu manager with contribution items for layout configurations.
     * 
     * @param menuManager a menu manager
     */
    public void fillConfigMenu(final IMenuManager menuManager) {
        for (ConfigData data : getConfigData()) {
            if (data.getActivationText() != null && data.getActivationText().length() > 0
                    && data.getActivationProperty() != null) {
                final String text = data.getActivationText();
                final IProperty<Boolean> activation = data.getActivationProperty();
                final Runnable activationAction = data.getActivationAction();
                menuManager.add(new ContributionItem() {
                    public void fill(final Menu parent, final int index) {
                        final MenuItem menuItem = new MenuItem(parent, SWT.CHECK, index);
                        menuItem.setText(text);
                        menuItem.setSelection(getConfigProperties().getProperty(activation));
                        menuItem.addSelectionListener(new SelectionListener() {
                            public void widgetSelected(final SelectionEvent e) {
                                getConfigProperties().setProperty(activation, menuItem.getSelection());
                                if (activationAction != null) {
                                    activationAction.run();
                                }
                            }
                            public void widgetDefaultSelected(final SelectionEvent e) {
                            }
                        });
                        // execute the activation action initially
                        if (activationAction != null) {
                            activationAction.run();
                        }
                    }
                });
            }
        }
    }

}
