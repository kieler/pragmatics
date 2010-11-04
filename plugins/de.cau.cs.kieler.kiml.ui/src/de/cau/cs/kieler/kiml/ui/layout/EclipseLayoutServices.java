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
package de.cau.cs.kieler.kiml.ui.layout;

import java.util.Arrays;
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
import org.eclipse.gef.EditPart;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.statushandlers.StatusManager;
import org.osgi.framework.Bundle;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.AbstractLayoutProvider;
import de.cau.cs.kieler.kiml.ILayoutConfig;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.LayoutProviderData;
import de.cau.cs.kieler.kiml.LayoutServices;
import de.cau.cs.kieler.kiml.ui.KimlUiPlugin;
import de.cau.cs.kieler.kiml.ui.Messages;

/**
 * This class is responsible for reading all extension point elements for layout
 * services.
 * 
 * @kieler.rating 2009-12-11 proposed yellow msp
 * @author msp
 */
public class EclipseLayoutServices extends LayoutServices {

    /** identifier of the extension point for layout providers. */
    public static final String EXTP_ID_LAYOUT_PROVIDERS = "de.cau.cs.kieler.kiml.layoutProviders";
    /** identifier of the extension point for layout info. */
    public static final String EXTP_ID_LAYOUT_INFO = "de.cau.cs.kieler.kiml.layoutInfo";
    /** identifier of the extension point for layout managers. */
    public static final String EXTP_ID_LAYOUT_MANAGERS = "de.cau.cs.kieler.kiml.ui.layoutManagers";
    /** name of the 'layoutProvider' element in the 'layout providers' extension point. */
    public static final String ELEMENT_LAYOUT_PROVIDER = "layoutProvider";
    /** name of the 'layoutType' element in the 'layout providers' extension point. */
    public static final String ELEMENT_LAYOUT_TYPE = "layoutType";
    /** name of the 'category' element in the 'layout providers' extension point. */
    public static final String ELEMENT_CATEGORY = "category";
    /** name of the 'manager' element in the 'layout managers' extension point. */
    public static final String ELEMENT_MANAGER = "manager";
    /** name of the 'layoutOption' element in the 'layout providers' extension point. */
    public static final String ELEMENT_LAYOUT_OPTION = "layoutOption";
    /** name of the 'knownOption' element in the 'layout providers' extension point. */
    public static final String ELEMENT_KNOWN_OPTION = "knownOption";
    /** name of the 'supportedDiagram' element in the 'layout providers' extension point. */
    public static final String ELEMENT_SUPPORTED_DIAGRAM = "supportedDiagram";
    /** name of the 'diagramType' element in the 'layout info' extension point. */
    public static final String ELEMENT_DIAGRAM_TYPE = "diagramType";
    /** name of the 'binding' element in the 'layout info' extension point. */
    public static final String ELEMENT_BINDING = "binding";
    /** name of the 'option' element in the 'layout info' extension point. */
    public static final String ELEMENT_OPTION = "option";
    /** name of the 'layoutListener' element in the 'layout listeners' extension point. */
    public static final String ELEMENT_LAYOUT_LISTENER = "layoutListener";
    /** name of the 'appliesTo' attribute in the extension points. */
    public static final String ATTRIBUTE_APPLIESTO = "appliesTo";
    /** name of the 'category' attribute in the extension points. */
    public static final String ATTRIBUTE_CATEGORY = "category";
    /** name of the 'class' attribute in the extension points. */
    public static final String ATTRIBUTE_CLASS = "class";
    /** name of the 'default' attribute in the extension points. */
    public static final String ATTRIBUTE_DEFAULT = "default";
    /** name of the 'description' attribute in the extension points. */
    public static final String ATTRIBUTE_DESCRIPTION = "description";
    /** name of the 'id' attribute in the extension points. */
    public static final String ATTRIBUTE_ID = "id";
    /** name of the 'name' attribute in the extension points. */
    public static final String ATTRIBUTE_NAME = "name";
    /** name of the 'option' attribute in the extension points. */
    public static final String ATTRIBUTE_OPTION = "option";
    /** name of the 'parameter' attribute in the extension points. */
    public static final String ATTRIBUTE_PARAMETER = "parameter";
    /** name of the 'priority' attribute in the extension points. */
    public static final String ATTRIBUTE_PRIORITY = "priority";
    /** name of the 'type' attribute in the extension points. */
    public static final String ATTRIBUTE_TYPE = "type";
    /** name of the 'value' attribute in the extension points. */
    public static final String ATTRIBUTE_VALUE = "value";

    /** default name for layout providers for which no name is given. */
    public static final String DEFAULT_PROVIDER_NAME = "<Unnamed Layouter>";
    /** default name for layout options for which no name is given. */
    public static final String DEFAULT_OPTION_NAME = "<Unnamed Option>";
    
    /** preference identifier for the list of registered diagram elements. */
    public static final String PREF_REG_ELEMENTS = "kiml.reg.elements";
    /** preference identifier for oblique edge routing. */
    public static final String PREF_OBLIQUE_ROUTE = "kiml.oblique.route";
    
    /** list of registered diagram layout managers. */
    private final List<DiagramLayoutManager> managers = new LinkedList<DiagramLayoutManager>();
    /** set of registered diagram elements. */
    private Set<String> registeredElements = new HashSet<String>();
    /** list of default options read from the extension point. */
    private List<String[]> defaultOptions = new LinkedList<String[]>();

    /**
     * Builds the layout services for the Eclipse platform.
     */
    public static void createLayoutServices() {
        // create instance of the layout service holder class
        EclipseLayoutServices layoutServices = new EclipseLayoutServices();
        LayoutServices.createLayoutServices(layoutServices);
        // build layout services for all extension points
        layoutServices.loadLayoutProviderExtensions();
        layoutServices.loadLayoutInfoExtensions();
        layoutServices.loadLayoutManagerExtensions();
        layoutServices.loadDefaultOptions();
        // load preferences for KIML
        layoutServices.loadPreferences();
    }
    
    /**
     * Returns the singleton instance as Eclipse layout services.
     * 
     * @return the singleton instance, or {@code null} if the instance is not
     *         of Eclipse layout services
     */
    public static EclipseLayoutServices getInstance() {
        LayoutServices instance = LayoutServices.getInstance();
        if (instance instanceof EclipseLayoutServices) {
            return (EclipseLayoutServices) instance;
        } else {
            return null;
        }
    }
    
    /**
     * Returns the most suitable layout manager for the given editor and edit part.
     * 
     * @param editorPart the editor for which the layout manager should be
     *     fetched, or {@code null}
     * @param editPart the edit part for which the layout manager should be
     *     fetched, or {@code null}
     * @return the most suitable diagram layout manager
     */
    public DiagramLayoutManager getManager(final IEditorPart editorPart,
            final EditPart editPart) {
        for (DiagramLayoutManager manager : managers) {
            if (manager.supports(editorPart)
                    || editorPart == null && manager.supports(editPart)) {
                return manager;
            }
        }
        return null;
    }

    /**
     * Retrieve an inspector for the given edit part using the most suitable
     * layout manager.
     * 
     * @param editPart the edit part for which the inspector should be fetched
     * @return an inspector for the edit part, or {@code null}
     */
    public ILayoutInspector getInspector(final EditPart editPart) {
        DiagramLayoutManager manager = getManager(null, editPart);
        if (manager != null) {
            return manager.getInspector(editPart);
        }
        return null;
    }
    
    /**
     * Retrieve a layout configuration for the given editor using the most suitable
     * layout manager.
     * 
     * @param editorPart the editor part for which the configuration should be fetched
     * @return a layout configuration for the editor, or {@code null}
     */
    public ILayoutConfig getLayoutConfig(final IEditorPart editorPart) {
        DiagramLayoutManager manager = getManager(editorPart, null);
        if (manager != null) {
            ILayoutInspector inspector = manager.getInspector(editorPart);
            return manager.getLayoutConfig(inspector.getFocusPart());
        }
        return null;
    }

    /**
     * Retrieve a layout configuration for the given edit part using the most suitable
     * layout manager.
     * 
     * @param editPart the edit part for which the configuration should be fetched
     * @return a layout configuration for the edit part, or {@code null}
     */
    public ILayoutConfig getLayoutConfig(final EditPart editPart) {
        DiagramLayoutManager manager = getManager(null, editPart);
        if (manager != null) {
            return manager.getLayoutConfig(editPart);
        }
        return null;
    }
    
    /**
     * Performs layout on the given editor by choosing an appropriate layout
     * manager instance. Animation and a progress bar can be optionally turned
     * on.
     * 
     * @param editorPart
     *            the editor for which layout is performed, or {@code null} if
     *            the diagram is not part of an editor
     * @param editPart
     *            the parent edit part for which layout is performed, or {@code
     *            null} if the whole diagram shall be layouted
     * @param animate
     *            if true, Draw2D animation is activated
     * @param progressBar
     *            if true, a progress bar is displayed
     * @return the diagram layout manager that was used for layout
     */
    public DiagramLayoutManager layout(final IEditorPart editorPart,
            final EditPart editPart, final boolean animate,
            final boolean progressBar) {
        return layout(editorPart, editPart, animate, progressBar, false);
    }

    /**
     * Performs layout on the given editor by choosing an appropriate layout
     * manager instance and caches the layout result. Animation and a progress
     * bar can be optionally turned on.
     * 
     * @param editorPart
     *            the editor for which layout is performed, or {@code null} if
     *            the diagram is not part of an editor
     * @param editPart
     *            the parent edit part for which layout is performed, or {@code
     *            null} if the whole diagram shall be layouted
     * @param animate
     *            if true, Draw2D animation is activated
     * @param progressBar
     *            if true, a progress bar is displayed
     * @return the cached layout result
     */
    public ICachedLayout cacheLayout(
            final IEditorPart editorPart, final EditPart editPart,
            final boolean animate, final boolean progressBar) {
        DiagramLayoutManager manager = layout(editorPart, editPart, animate, progressBar, true);
        return manager.getCachedLayout();
    }

    /**
     * Performs layout on the given editor by choosing an appropriate layout
     * manager instance. Animation, a progress bar, and layout of ancestors can
     * be optionally turned on.
     * 
     * @param editorPart
     *            the editor for which layout is performed, or {@code null} if
     *            the diagram is not part of an editor
     * @param editPart
     *            the parent edit part for which layout is performed, or {@code
     *            null} if the whole diagram shall be layouted
     * @param animate
     *            if true, Draw2D animation is activated
     * @param progressBar
     *            if true, a progress bar is displayed
     * @param layoutAncestors
     *            if true, layout is not only performed for the selected edit
     *            part, but also for its ancestors
     * @return the diagram layout manager that was used for layout
     */
    public DiagramLayoutManager layout(final IEditorPart editorPart,
            final EditPart editPart, final boolean animate,
            final boolean progressBar, final boolean layoutAncestors) {
        DiagramLayoutManager manager = getManager(editorPart, editPart);
        if (manager != null) {
            manager.layout(editorPart, editPart, animate, progressBar,
                    layoutAncestors, false);
            return manager;
        } else {
            throw new UnsupportedOperationException(Messages.getString("kiml.ui.15")
                    + editorPart.getTitle() + ".");
        }
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
     * Fills the given table of priorities with data from the extension point.
     * The number of rows in the table must be equal to the number of layout
     * providers, and the number of columns must be equal to the number of
     * diagram types.
     * 
     * @param priorityData two dimensional array that is filled with data
     * @param layoutProviders array of layout provider identifiers
     * @param diagramTypes array of diagram type identifiers
     */
    public static void readSupportPriorities(final int[][] priorityData,
            final String[] layoutProviders, final String[] diagramTypes) {
        List<String> layoutProviderList = Arrays.asList(layoutProviders);
        List<String> diagramTypesList = Arrays.asList(diagramTypes);
        for (int i = 0; i < layoutProviders.length; i++) {
            Arrays.fill(priorityData[i], LayoutProviderData.MIN_PRIORITY);
        }

        IConfigurationElement[] extensions = Platform.getExtensionRegistry()
                .getConfigurationElementsFor(EXTP_ID_LAYOUT_PROVIDERS);
        for (IConfigurationElement element : extensions) {
            if (ELEMENT_LAYOUT_PROVIDER.equals(element.getName())) {
                int providerIndex = layoutProviderList.indexOf(element.getAttribute(ATTRIBUTE_ID));
                if (providerIndex >= 0) {
                    for (IConfigurationElement child : element.getChildren()) {
                        if (ELEMENT_SUPPORTED_DIAGRAM.equals(child.getName())) {
                            int typeIndex = diagramTypesList.indexOf(child.getAttribute(ATTRIBUTE_TYPE));
                            if (typeIndex >= 0) {
                                String priority = child.getAttribute(ATTRIBUTE_PRIORITY);
                                try {
                                    priorityData[providerIndex][typeIndex] = Integer.parseInt(priority);
                                } catch (NumberFormatException exception) {
                                    // ignore exception
                                }
                            }
                        }
                    }
                }
            }
        }
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
            registry().addOption(diagramType, optionData.getId(), value);
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
        if (value != null) {
            ILayoutInspector inspector = getInspector(editPart);
            if (inspector != null) {
                String clazzName;
                if (storeDomainModel) {
                    clazzName = inspector.getFocusModel().eClass().getInstanceTypeName();
                } else {
                    clazzName = inspector.getFocusPart().getClass().getName();
                }
                registry().addOption(clazzName, optionData.getId(), value);
                registeredElements.add(clazzName);
                IPreferenceStore preferenceStore = KimlUiPlugin.getDefault().getPreferenceStore();
                preferenceStore.setValue(getPreferenceName(clazzName, optionData.getId()), valueString);
            }
        }
    }
    
    /**
     * Loads the default layout options from the extension point.
     */
    public void loadDefaultOptions() {
        for (String[] optionArray : defaultOptions) {
            String className = optionArray[0];
            String optionId = optionArray[1];
            LayoutOptionData<?> optionData = getInstance().getLayoutOptionData(optionId);
            if (optionData != null) {
                String valueString = optionArray[2];
                Object value = optionData.parseValue(valueString);
                if (value != null) {
                    registry().addOption(className, optionId, value);
                }
            }
        }
    }
    
    /**
     * Retrieves the default value for the given element class and option
     * identifier from the extension point.
     * 
     * @param className name of a diagram element class
     * @param optionId layout option identifier
     * @return the default option value for the element class
     */
    public Object getDefault(final String className, final String optionId) {
        for (String[] optionArray : defaultOptions) {
            if (optionArray[0].equals(className) && optionArray[1].equals(optionId)) {
                LayoutOptionData<?> optionData = getInstance().getLayoutOptionData(optionId);
                if (optionData != null) {
                    String valueString = optionArray[2];
                    return optionData.parseValue(valueString);
                }
            }
        }
        return null;
    }
    
    /**
     * Returns the layout option data that matches the given user-friendly name and is known by the
     * given layout provider.
     * 
     * @param providerData a layout provider data
     * @param optionName user-friendly name of a layout option
     * @return the corresponding layout option data
     */
    public LayoutOptionData<?> getOptionData(final LayoutProviderData providerData,
            final String optionName) {
        for (LayoutOptionData<?> data : getLayoutOptionData()) {
            if (data.getName().equals(optionName) && providerData.knowsOption(data.getId())) {
                return data;
            }
        }
        return null;
    }

    /**
     * Reports an error that occurred while reading extensions.
     * 
     * @param extensionPoint the identifier of the extension point
     * @param element the configuration element
     * @param attribute the attribute that contains an invalid entry
     * @param exception an optional exception that was caused by the invalid
     *            entry
     */
    private static void reportError(final String extensionPoint,
            final IConfigurationElement element, final String attribute, final Exception exception) {
        String message = "Extension point " + extensionPoint + ": Invalid entry in attribute '"
                + attribute + "' of element " + element.getName() + ", contributed by "
                + element.getContributor().getName();
        IStatus status = new Status(IStatus.WARNING, KimlUiPlugin.PLUGIN_ID, 0, message, exception);
        StatusManager.getManager().handle(status);
    }

    /**
     * Loads and registers all layout providers from the extension point.
     */
    private void loadLayoutProviderExtensions() {
        IConfigurationElement[] extensions = Platform.getExtensionRegistry()
                .getConfigurationElementsFor(EXTP_ID_LAYOUT_PROVIDERS);

        for (IConfigurationElement element : extensions) {
            if (ELEMENT_LAYOUT_PROVIDER.equals(element.getName())) {
                try {
                    // register a layout provider from the extension
                    AbstractLayoutProvider layoutProvider = (AbstractLayoutProvider) element
                            .createExecutableExtension(ATTRIBUTE_CLASS);
                    if (layoutProvider != null) {
                        LayoutProviderData providerData = new LayoutProviderData();
                        providerData.setInstance(layoutProvider);
                        providerData.setId(element.getAttribute(ATTRIBUTE_ID));
                        if (providerData.getId() == null || providerData.getId().length() == 0) {
                            reportError(EXTP_ID_LAYOUT_PROVIDERS, element, ATTRIBUTE_ID, null);
                            continue;
                        }
                        providerData.setName(element.getAttribute(ATTRIBUTE_NAME));
                        if (providerData.getName() == null || providerData.getName().length() == 0) {
                            providerData.setName(DEFAULT_PROVIDER_NAME);
                        }
                        try {
                            layoutProvider.initialize(element.getAttribute(ATTRIBUTE_PARAMETER));
                            providerData.setType(element.getAttribute(ATTRIBUTE_TYPE));
                            if (providerData.getType() == null) {
                                providerData.setType("");
                            }
                            providerData.setCategory(element.getAttribute(ATTRIBUTE_CATEGORY));
                            if (providerData.getCategory() == null) {
                                providerData.setCategory("");
                            }
                            for (IConfigurationElement child : element.getChildren()) {
                                if (ELEMENT_KNOWN_OPTION.equals(child.getName())) {
                                    String option = child.getAttribute(ATTRIBUTE_OPTION);
                                    if (option != null && option.length() > 0) {
                                        providerData.setOption(option, true);
                                    } else {
                                        reportError(EXTP_ID_LAYOUT_PROVIDERS, child,
                                                ATTRIBUTE_OPTION, null);
                                    }
                                } else if (ELEMENT_SUPPORTED_DIAGRAM.equals(child.getName())) {
                                    String type = child.getAttribute(ATTRIBUTE_TYPE);
                                    if (type == null || type.length() == 0) {
                                        reportError(EXTP_ID_LAYOUT_PROVIDERS, child,
                                                ATTRIBUTE_TYPE, null);
                                    } else {
                                        String priority = child.getAttribute(ATTRIBUTE_PRIORITY);
                                        try {
                                            providerData.setDiagramSupport(type,
                                                    Integer.parseInt(priority));
                                        } catch (NumberFormatException exception) {
                                            reportError(EXTP_ID_LAYOUT_PROVIDERS, child,
                                                    ATTRIBUTE_PRIORITY, exception);
                                        }
                                    }
                                }
                            }
                            registry().addLayoutProvider(providerData);
                        } catch (KielerException exception) {
                            reportError(EXTP_ID_LAYOUT_PROVIDERS, element,
                                    ATTRIBUTE_PARAMETER, exception);
                        }
                    }
                } catch (CoreException exception) {
                    StatusManager.getManager().handle(exception, KimlUiPlugin.PLUGIN_ID);
                }
            } else if (ELEMENT_LAYOUT_TYPE.equals(element.getName())) {
                // register a layout type from the extension
                String id = element.getAttribute(ATTRIBUTE_ID);
                String name = element.getAttribute(ATTRIBUTE_NAME);
                if (id == null || id.length() == 0) {
                    reportError(EXTP_ID_LAYOUT_PROVIDERS, element, ATTRIBUTE_ID, null);
                } else if (name == null) {
                    reportError(EXTP_ID_LAYOUT_PROVIDERS, element, ATTRIBUTE_NAME, null);
                } else {
                    registry().addLayoutType(id, name);
                }
            } else if (ELEMENT_CATEGORY.equals(element.getName())) {
                // register a category from the extension
                String id = element.getAttribute(ATTRIBUTE_ID);
                String name = element.getAttribute(ATTRIBUTE_NAME);
                if (id == null || id.length() == 0) {
                    reportError(EXTP_ID_LAYOUT_PROVIDERS, element, ATTRIBUTE_ID, null);
                } else if (name == null) {
                    reportError(EXTP_ID_LAYOUT_PROVIDERS, element, ATTRIBUTE_NAME, null);
                } else {
                    registry().addCategory(id, name);
                }
            } else if (ELEMENT_LAYOUT_OPTION.equals(element.getName())) {
                // register a layout option from the extension
                LayoutOptionData<Object> optionData = new LayoutOptionData<Object>();
                String optionId = element.getAttribute(ATTRIBUTE_ID);
                optionData.setId(optionId);
                if (optionId == null || optionId.length() == 0) {
                    reportError(EXTP_ID_LAYOUT_PROVIDERS, element, ATTRIBUTE_ID, null);
                    continue;
                }
                try {
                    optionData.setType(element.getAttribute(ATTRIBUTE_TYPE));
                } catch (IllegalArgumentException exception) {
                    reportError(EXTP_ID_LAYOUT_PROVIDERS, element, ATTRIBUTE_TYPE, exception);
                    continue;
                }
                String className = element.getAttribute(ATTRIBUTE_CLASS);
                if (className != null && className.length() > 0) {
                    Bundle contributor = Platform.getBundle(element.getContributor().getName());
                    if (contributor != null) {
                        try {
                            Class<?> clazz = contributor.loadClass(className);
                            optionData.setOptionClass(clazz);
                        } catch (ClassNotFoundException exception) {
                            reportError(EXTP_ID_LAYOUT_PROVIDERS, element, ATTRIBUTE_CLASS, exception);
                        }
                    }
                }
                try {
                    Object defaultValue = optionData.parseValue(
                            element.getAttribute(ATTRIBUTE_DEFAULT));
                    optionData.setDefault(defaultValue);
                } catch (IllegalStateException exception) {
                    reportError(EXTP_ID_LAYOUT_PROVIDERS, element, ATTRIBUTE_CLASS, exception);
                }
                optionData.setName(element.getAttribute(ATTRIBUTE_NAME));
                if (optionData.getName() == null) {
                    optionData.setName(DEFAULT_OPTION_NAME);
                }
                optionData.setDescription(element.getAttribute(ATTRIBUTE_DESCRIPTION));
                if (optionData.getDescription() == null) {
                    optionData.setDescription("");
                }
                optionData.setTargets(element.getAttribute(ATTRIBUTE_APPLIESTO));
                registry().addLayoutOption(optionData);
            }
        }
    }

    /**
     * Loads and registers all layout information from the extension point.
     */
    private void loadLayoutInfoExtensions() {
        IConfigurationElement[] extensions = Platform.getExtensionRegistry()
                .getConfigurationElementsFor(EXTP_ID_LAYOUT_INFO);

        for (IConfigurationElement element : extensions) {
            if (ELEMENT_DIAGRAM_TYPE.equals(element.getName())) {
                // register a diagram type from the extension
                String id = element.getAttribute(ATTRIBUTE_ID);
                String name = element.getAttribute(ATTRIBUTE_NAME);
                if (id == null || id.length() == 0) {
                    reportError(EXTP_ID_LAYOUT_INFO, element, ATTRIBUTE_ID, null);
                } else if (name == null) {
                    reportError(EXTP_ID_LAYOUT_INFO, element, ATTRIBUTE_NAME, null);
                } else {
                    registry().addDiagramType(id, name);
                }
            } else if (ELEMENT_OPTION.equals(element.getName())) {
                // register a layout option from the extension
                String clazz = element.getAttribute(ATTRIBUTE_CLASS);
                String option = element.getAttribute(ATTRIBUTE_OPTION);
                String value = element.getAttribute(ATTRIBUTE_VALUE);
                if (clazz == null || clazz.length() == 0) {
                    reportError(EXTP_ID_LAYOUT_INFO, element, ATTRIBUTE_CLASS, null);
                } else if (option == null || option.length() == 0) {
                    reportError(EXTP_ID_LAYOUT_INFO, element, ATTRIBUTE_OPTION, null);
                } else {
                    defaultOptions.add(new String[] {clazz, option, value});
                }
            }
        }
    }
    
    /**
     * Loads all diagram layout managers from the extension point.
     */
    private void loadLayoutManagerExtensions() {
        IConfigurationElement[] extensions = Platform.getExtensionRegistry()
                .getConfigurationElementsFor(EXTP_ID_LAYOUT_MANAGERS);
        
        for (IConfigurationElement element : extensions) {
            if (ELEMENT_MANAGER.equals(element.getName())) {
                try {
                    DiagramLayoutManager manager = (DiagramLayoutManager)
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
    private void insertManager(final DiagramLayoutManager manager, final int priority) {
        ListIterator<DiagramLayoutManager> iter = managers.listIterator();
        while (iter.hasNext()) {
            DiagramLayoutManager next = iter.next();
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
        
        // load priorities of layout providers
        Collection<LayoutProviderData> layoutProviderData = getLayoutProviderData();
        List<Pair<String, String>> diagramTypes = getDiagramTypes();
        for (LayoutProviderData data : layoutProviderData) {
            for (Pair<String, String> diagramType : diagramTypes) {
                String preference = getPreferenceName(data.getId(), diagramType.getFirst());
                if (preferenceStore.contains(preference)) {
                    data.setDiagramSupport(diagramType.getFirst(), preferenceStore.getInt(preference));
                }
            }
        }
        
        // load default options for diagram types
        Collection<LayoutOptionData<?>> layoutOptionData = getLayoutOptionData();
        for (Pair<String, String> diagramType : diagramTypes) {
            for (LayoutOptionData<?> data : layoutOptionData) {
                String preference = getPreferenceName(diagramType.getFirst(), data.getId());
                if (preferenceStore.contains(preference)) {
                    Object value = data.parseValue(preferenceStore.getString(preference));
                    if (value != null) {
                        registry().addOption(diagramType.getFirst(), data.getId(), value);
                    }
                }
            }
        }
        
        // load default options for edit parts
        StringTokenizer editPartsTokenizer = new StringTokenizer(
                preferenceStore.getString(PREF_REG_ELEMENTS), ";");
        while (editPartsTokenizer.hasMoreTokens()) {
            registeredElements.add(editPartsTokenizer.nextToken());
        }
        for (String editPartName : registeredElements) {
            for (LayoutOptionData<?> data : layoutOptionData) {
                String preference = getPreferenceName(editPartName, data.getId());
                if (preferenceStore.contains(preference)) {
                    Object value = data.parseValue(preferenceStore.getString(preference));
                    if (value != null) {
                        registry().addOption(editPartName, data.getId(), value);
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

        // store set of registered edit parts
        StringBuilder editPartsString = new StringBuilder();
        for (String editPartName : registeredElements) {
            editPartsString.append(editPartName + ";");
        }
        preferenceStore.setValue(PREF_REG_ELEMENTS, editPartsString.toString());
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
