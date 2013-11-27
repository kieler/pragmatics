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
package de.cau.cs.kieler.kiml.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.statushandlers.StatusManager;

import de.cau.cs.kieler.core.alg.DefaultFactory;
import de.cau.cs.kieler.core.alg.IFactory;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.IGraphLayoutEngine;

/**
 * A service class for layout managers, which are registered through the extension point.
 *
 * @author msp
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow 2012-07-10 msp
 */
public class LayoutManagersService implements IAdapterFactory {

    /** preference identifier for oblique edge routing. */
    public static final String PREF_OBLIQUE_ROUTE = "kiml.oblique.route";
    
    /** identifier of the extension point for layout managers. */
    protected static final String EXTP_ID_LAYOUT_MANAGERS
            = "de.cau.cs.kieler.kiml.service.layoutManagers";
    /** name of the 'manager' element in the 'layout managers' extension point. */
    protected static final String ELEMENT_MANAGER = "manager";
    /** name of the 'engine' element in the 'layout managers' extension point. */
    protected static final String ELEMENT_ENGINE = "engine";
    /** name of the 'class' attribute in the extension points. */
    protected static final String ATTRIBUTE_CLASS = "class";
    /** name of the 'priority' attribute in the extension points. */
    protected static final String ATTRIBUTE_PRIORITY = "priority";
    
    /** the singleton instance of the managers service. */
    private static LayoutManagersService instance;
    /** the factory for creation of service instances. */
    private static IFactory<? extends LayoutManagersService> instanceFactory
            = new DefaultFactory<LayoutManagersService>(LayoutManagersService.class);
    
    /**
     * Returns the singleton instance of the managers service.
     * 
     * @return the singleton instance
     */
    public static LayoutManagersService getInstance() {
        synchronized (LayoutManagersService.class) {
            if (instance == null) {
                instance = instanceFactory.create();
            }
        }
        return instance;
    }
    
    /**
     * Set the factory for creating instances. If an instance is already created, it is cleared
     * so the next call to {@link #getInstance()} uses the new factory.
     * 
     * @param factory an instance factory
     */
    public static void setInstanceFactory(final IFactory<? extends LayoutManagersService> factory) {
        instanceFactory = factory;
        instance = null;
    }

    
    /** list of registered diagram layout managers. */
    private final List<Pair<Integer, IDiagramLayoutManager<?>>> managers
            = new LinkedList<Pair<Integer, IDiagramLayoutManager<?>>>();
    /** list of registered graph layout engines. */
    private final List<Pair<Integer, IGraphLayoutEngine>> layoutEngines
            = new LinkedList<Pair<Integer, IGraphLayoutEngine>>();

    /**
     * Load all registered extensions for the layout managers extension point.
     */
    public LayoutManagersService() {
        loadLayoutManagerExtensions();
    }

    /**
     * Report an error that occurred while reading extensions. May be overridden by subclasses
     * in order to report errors in a different way.
     * 
     * @param extensionPoint the identifier of the extension point
     * @param element the configuration element
     * @param attribute the attribute that contains an invalid entry
     * @param exception an optional exception that was caused by the invalid entry
     */
    protected void reportError(final String extensionPoint, final IConfigurationElement element,
            final String attribute, final Throwable exception) {
        String message;
        if (element != null && attribute != null) {
            message = "Extension point " + extensionPoint + ": Invalid entry in attribute '"
                    + attribute + "' of element " + element.getName() + ", contributed by "
                    + element.getContributor().getName();
        } else {
            message = "Extension point " + extensionPoint
                    + ": An error occured while loading extensions.";
        }
        IStatus status = new Status(IStatus.WARNING, KimlServicePlugin.PLUGIN_ID,
                0, message, exception);
        StatusManager.getManager().handle(status);
    }

    /**
     * Report an error that occurred while reading extensions. May be overridden by subclasses
     * in order to report errors in a different way.
     * 
     * @param exception a core exception holding a status with further information
     */
    protected void reportError(final CoreException exception) {
        StatusManager.getManager().handle(exception, KimlServicePlugin.PLUGIN_ID);
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
    public final IDiagramLayoutManager<?> getManager(final IWorkbenchPart workbenchPart,
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
                // Use the layout manager's diagram part type as adapter type
                Class[] adapterList = manager.getAdapterList();
                if (adapterList != null && adapterList.length > 0 && adapterList[0] != null) {
                    return manager.getAdapter(adaptableObject, adapterList[0]);
                }
            } else {
                // use the adapter type given as parameter
                return manager.getAdapter(adaptableObject, adapterType);
            }
        } else {
            
            // Try to find a layout manager that is able to adapt to the given object even
            // if it doesn't officially support it
            for (Pair<Integer, IDiagramLayoutManager<?>> entry : managers) {
                manager = entry.getSecond();
                Object adapter = null;
                if (adapterType == null) {
                    // Use the layout manager's diagram part type as adapter type
                    Class[] adapterList = manager.getAdapterList();
                    if (adapterList != null && adapterList.length > 0 && adapterList[0] != null) {
                        adapter = manager.getAdapter(adaptableObject, adapterList[0]);
                    }
                } else {
                    // use the adapter type given as parameter
                    adapter = manager.getAdapter(adaptableObject, adapterType);
                }
                if (adapter != null) {
                    return adapter;
                }
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
    public final IGraphLayoutEngine getLayoutEngine() {
        for (Pair<Integer, IGraphLayoutEngine> entry : layoutEngines) {
            IGraphLayoutEngine engine = entry.getSecond();
            if (engine.isActive()) {
                return engine;
            }
        }
        return null;
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
                StatusManager.getManager().handle(exception, KimlServicePlugin.PLUGIN_ID);
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

}
