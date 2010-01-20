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
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.statushandlers.StatusManager;

import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.layout.AbstractLayoutProvider;
import de.cau.cs.kieler.kiml.layout.ILayoutListener;
import de.cau.cs.kieler.kiml.layout.LayoutOptionData;
import de.cau.cs.kieler.kiml.layout.LayoutProviderData;
import de.cau.cs.kieler.kiml.layout.LayoutServices;
import de.cau.cs.kieler.kiml.ui.KimlUiPlugin;

/**
 * This class is responsible for reading all extension point elements for layout
 * services.
 * 
 * @kieler.rating 2009-12-11 proposed yellow msp
 * @author msp
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
    
    /** preference identifier for the list of registered edit parts. */
    private static final String PREF_REG_EDIT_PARTS = "kiml.reg.edit.parts";
    
    /** set of registered edit parts. */
    private Set<String> registeredEditParts = new HashSet<String>();

    /**
     * Builds the layout services for the Eclipse platform.
     */
    public static void createLayoutServices() {
        // create instance of the layout service holder class
        EclipseLayoutServices layoutServices = new EclipseLayoutServices();
        LayoutServices.createLayoutServices(layoutServices);
        // build layout services for all extension points
        layoutServices.loadLayoutProviderExtensions();
        layoutServices.loadLayoutListenerExtensions();
        layoutServices.loadLayoutInfoExtensions();
        layoutServices.registry().processOptions();
        // load preferences for KIML
        layoutServices.loadPreferences();
        // register an instance of the GMF diagram layout manager
        DiagramLayoutManager.registerManager(new GmfDiagramLayoutManager());
    }
    
    /**
     * Returns the singleton instance as Eclipse layout services.
     * 
     * @return the singleton instance, or {@code null} if the instance is not
     *         of instance of Eclipse layout services
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
    public void storeOption(final String diagramType, final LayoutOptionData optionData,
            final String valueString) {
        Object value = optionData.parseValue(valueString);
        if (value != null) {
            registry().addDiagramTypeOption(diagramType, optionData.getId(), value);
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
     */
    public void storeOption(final EditPart editPart, final LayoutOptionData optionData,
            final String valueString) {
        Object value = optionData.parseValue(valueString);
        if (value != null) {
            String className = editPart.getClass().getName();
            registry().addEditPartOption(className, optionData.getId(), value);
            registeredEditParts.add(className);
            IPreferenceStore preferenceStore = KimlUiPlugin.getDefault().getPreferenceStore();
            preferenceStore.setValue(getPreferenceName(editPart.getClass().getName(),
                    optionData.getId()), valueString);
        }
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
                        registry().addLayoutProvider(providerData);
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
                registry().addLayoutOption(optionData);
            }
        }
    }

    /**
     * Loads and registers all layout listeners from the extension point.
     */
    private void loadLayoutListenerExtensions() {
        IConfigurationElement[] extensions = Platform.getExtensionRegistry()
                .getConfigurationElementsFor(EXTP_ID_LAYOUT_LISTENERS);

        for (IConfigurationElement element : extensions) {
            if (ELEMENT_LAYOUT_LISTENER.equals(element.getName())) {
                // register a layout listener from the extension
                try {
                    ILayoutListener layoutListener = (ILayoutListener) element
                            .createExecutableExtension(ATTRIBUTE_CLASS);
                    if (layoutListener != null) {
                        registry().addLayoutListener(layoutListener);
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
            } else if (ELEMENT_BINDING.equals(element.getName())) {
                // register a binding from the extension
                String typeName = element.getAttribute(ATTRIBUTE_CLASS);
                String id = element.getAttribute(ATTRIBUTE_ID);
                if (typeName == null || typeName.length() == 0) {
                    reportError(EXTP_ID_LAYOUT_INFO, element, ATTRIBUTE_CLASS, null);
                } else if (id == null || id.length() == 0) {
                    reportError(EXTP_ID_LAYOUT_INFO, element, ATTRIBUTE_ID, null);
                } else {
                    registry().addEditPartBinding(typeName, id);
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
                    registry().setupOption(object, option, value);
                }
            }
        }
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
        Collection<LayoutOptionData> layoutOptionData = getLayoutOptionData();
        for (Pair<String, String> diagramType : diagramTypes) {
            for (LayoutOptionData data : layoutOptionData) {
                String preference = getPreferenceName(diagramType.getFirst(), data.getId());
                if (preferenceStore.contains(preference)) {
                    Object value = data.parseValue(preferenceStore.getString(preference));
                    if (value != null) {
                        registry().addDiagramTypeOption(diagramType.getFirst(), data.getId(), value);
                    }
                }
            }
        }
        
        // load default options for edit parts
        StringTokenizer editPartsTokenizer = new StringTokenizer(
                preferenceStore.getString(PREF_REG_EDIT_PARTS), ";");
        while (editPartsTokenizer.hasMoreTokens()) {
            registeredEditParts.add(editPartsTokenizer.nextToken());
        }
        for (String editPartName : registeredEditParts) {
            for (LayoutOptionData data : layoutOptionData) {
                String preference = getPreferenceName(editPartName, data.getId());
                if (preferenceStore.contains(preference)) {
                    Object value = data.parseValue(preferenceStore.getString(preference));
                    if (value != null) {
                        registry().addEditPartOption(editPartName, data.getId(), value);
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
        for (String editPartName : registeredEditParts) {
            editPartsString.append(editPartName + ";");
        }
        preferenceStore.setValue(PREF_REG_EDIT_PARTS, editPartsString.toString());
    }

    /**
     * Returns the set of registered edit parts.
     * 
     * @return the set of registered edit parts
     */
    public Set<String> getRegisteredEditParts() {
        return registeredEditParts;
    }

}
