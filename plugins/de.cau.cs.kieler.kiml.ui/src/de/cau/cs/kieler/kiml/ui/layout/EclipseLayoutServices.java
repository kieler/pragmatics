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
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.statushandlers.StatusManager;

import de.cau.cs.kieler.kiml.layout.AbstractLayoutProvider;
import de.cau.cs.kieler.kiml.layout.ILayoutListener;
import de.cau.cs.kieler.kiml.layout.LayoutOptionData;
import de.cau.cs.kieler.kiml.layout.LayoutProviderData;
import de.cau.cs.kieler.kiml.layout.LayoutServices;
import de.cau.cs.kieler.kiml.ui.KimlUiPlugin;
import de.cau.cs.kieler.kiml.ui.preferences.LayoutPreferencePage;

/**
 * This class is responsible for reading all extension point elements for layout
 * services.
 * 
 * @author <a href="mailto:msp@informatik.uni-kiel.de">Miro Sp&ouml;nemann</a>
 */
public class EclipseLayoutServices extends LayoutServices {

    /** identifier of the extension point for layout providers. */
    public static final String EXTP_ID_LAYOUT_PROVIDERS = "de.cau.cs.kieler.kiml.layout.layoutProviders";
    /** identifier of the extension point for layout listeners. */
    public static final String EXTP_ID_LAYOUT_LISTENERS = "de.cau.cs.kieler.kiml.layout.layoutListeners";
    /** identifier of the extension point for layout info. */
    public static final String EXTP_ID_LAYOUT_INFO = "de.cau.cs.kieler.kiml.layout.layoutInfo";
    /** name of the 'layoutProvider' element in the 'layout providers' extension point. */
    public static final String ELEMENT_LAYOUT_PROVIDER = "layoutProvider";
    /** name of the 'layoutType' element in the 'layout providers' extension point. */
    public static final String ELEMENT_LAYOUT_TYPE = "layoutType";
    /** name of the 'category' element in the 'layout providers' extension point. */
    public static final String ELEMENT_CATEGORY = "category";
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
    /** name of the 'description' attribute in the extension points. */
    public static final String ATTRIBUTE_DESCRIPTION = "description";
    /** name of the 'id' attribute in the extension points. */
    public static final String ATTRIBUTE_ID = "id";
    /** name of the 'name' attribute in the extension points. */
    public static final String ATTRIBUTE_NAME = "name";
    /** name of the 'object' attribute in the extension points. */
    public static final String ATTRIBUTE_OBJECT = "object";
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

    /**
     * Build the layout services.
     */
    public static void createLayoutServices() {
        // create instance of the layout service holder class
        LayoutServices.createLayoutServices();
        // build layout services for all extension points
        loadLayoutProviderExtensions();
        loadLayoutListenerExtensions();
        loadLayoutInfoExtensions();
        getRegistry().initialize();
        // load preferences for KIML
        loadPreferences();
        // register an instance of the GMF diagram layout manager
        DiagramLayoutManager.registerManager(new GmfDiagramLayoutManager());
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
     * Reports an error that occured while reading extensions.
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
    private static void loadLayoutProviderExtensions() {
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
                                    reportError(EXTP_ID_LAYOUT_PROVIDERS, child, ATTRIBUTE_OPTION, null);
                                }
                            } else if (ELEMENT_SUPPORTED_DIAGRAM.equals(child.getName())) {
                                String type = child.getAttribute(ATTRIBUTE_TYPE);
                                if (type == null || type.length() == 0) {
                                    reportError(EXTP_ID_LAYOUT_PROVIDERS, child, ATTRIBUTE_TYPE, null);
                                } else {
                                    String priority = child.getAttribute(ATTRIBUTE_PRIORITY);
                                    try {
                                        providerData.setDiagramSupport(type, Integer.parseInt(priority));
                                    } catch (NumberFormatException exception) {
                                        reportError(EXTP_ID_LAYOUT_PROVIDERS, child, ATTRIBUTE_PRIORITY,
                                                exception);
                                    }
                                }
                            }
                        }
                        getRegistry().addLayoutProvider(providerData);
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
                    getRegistry().addLayoutType(id, name);
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
                    getRegistry().addCategory(id, name);
                }
            } else if (ELEMENT_LAYOUT_OPTION.equals(element.getName())) {
                // register a layout option from the extension
                LayoutOptionData optionData = new LayoutOptionData();
                optionData.setId(element.getAttribute(ATTRIBUTE_ID));
                if (optionData.getId() == null || optionData.getId().length() == 0) {
                    reportError(EXTP_ID_LAYOUT_PROVIDERS, element, ATTRIBUTE_ID, null);
                    continue;
                }
                try {
                    optionData.setType(element.getAttribute(ATTRIBUTE_TYPE));
                } catch (IllegalArgumentException exception) {
                    reportError(EXTP_ID_LAYOUT_PROVIDERS, element, ATTRIBUTE_TYPE, exception);
                    continue;
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
                getRegistry().addLayoutOption(optionData);
            }
        }
    }

    /**
     * Loads and registers all layout listeners from the extension point.
     */
    private static void loadLayoutListenerExtensions() {
        IConfigurationElement[] extensions = Platform.getExtensionRegistry()
                .getConfigurationElementsFor(EXTP_ID_LAYOUT_LISTENERS);

        for (IConfigurationElement element : extensions) {
            if (ELEMENT_LAYOUT_LISTENER.equals(element.getName())) {
                // register a layout listener from the extension
                try {
                    ILayoutListener layoutListener = (ILayoutListener) element
                            .createExecutableExtension(ATTRIBUTE_CLASS);
                    if (layoutListener != null) {
                        getRegistry().addLayoutListener(layoutListener);
                    }
                } catch (CoreException exception) {
                    StatusManager.getManager().handle(exception, KimlUiPlugin.PLUGIN_ID);
                }
            }
        }
    }

    /**
     * Loads and registers all layout information from the extension point.
     */
    private static void loadLayoutInfoExtensions() {
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
                    getRegistry().addDiagramType(id, name);
                }
            } else if (ELEMENT_BINDING.equals(element.getName())) {
                // register a binding from the extension
                String typeName = element.getAttribute(ATTRIBUTE_CLASS);
                String id = element.getAttribute(ATTRIBUTE_ID);
                if (typeName == null || typeName.length() == 0) {
                    reportError(EXTP_ID_LAYOUT_INFO, element, ATTRIBUTE_CLASS, null);
                } else if (id == null || id.length() == 0) {
                    reportError(EXTP_ID_LAYOUT_INFO, element, ATTRIBUTE_ID, null);
                } else {
                    getRegistry().addEditPartBinding(typeName, id);
                }
            } else if (ELEMENT_OPTION.equals(element.getName())) {
                // register a layout option from the extension
                String object = element.getAttribute(ATTRIBUTE_OBJECT);
                String option = element.getAttribute(ATTRIBUTE_OPTION);
                String value = element.getAttribute(ATTRIBUTE_VALUE);
                if (object == null || object.length() == 0) {
                    reportError(EXTP_ID_LAYOUT_INFO, element, ATTRIBUTE_OBJECT, null);
                } else if (option == null || option.length() == 0) {
                    reportError(EXTP_ID_LAYOUT_INFO, element, ATTRIBUTE_OPTION, null);
                } else {
                    if (value == null) {
                        value = "";
                    }
                    getRegistry().setupOption(object, option, value);
                }
            }
        }
    }

    /**
     * Loads preferences for KIML.
     */
    private static void loadPreferences() {
        IPreferenceStore preferenceStore = KimlUiPlugin.getDefault().getPreferenceStore();
        Collection<LayoutProviderData> layoutProviderData = getInstance().getLayoutProviderData();
        Collection<String> diagramTypes = getInstance().getDiagramTypes();
        for (LayoutProviderData data : layoutProviderData) {
            for (String diagramType : diagramTypes) {
                String preference = LayoutPreferencePage.getPreference(data.getId(), diagramType);
                if (preferenceStore.contains(preference)) {
                    data.setDiagramSupport(diagramType, preferenceStore.getInt(preference));
                }
            }
        }
    }

}
