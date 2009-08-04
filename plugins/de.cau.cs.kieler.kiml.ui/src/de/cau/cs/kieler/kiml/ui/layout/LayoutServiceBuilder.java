/******************************************************************************
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

import de.cau.cs.kieler.kiml.layout.services.ILayoutListener;
import de.cau.cs.kieler.kiml.layout.services.AbstractLayoutProvider;
import de.cau.cs.kieler.kiml.layout.services.LayoutOptionData;
import de.cau.cs.kieler.kiml.layout.services.LayoutProviderData;
import de.cau.cs.kieler.kiml.layout.services.LayoutServices;
import de.cau.cs.kieler.kiml.ui.KimlUiPlugin;
import de.cau.cs.kieler.kiml.ui.preferences.LayoutPreferencePage;

/**
 * This class is responsible for reading all extension point elements
 * for layout services.
 * 
 * @author <a href="mailto:msp@informatik.uni-kiel.de">Miro Sp&ouml;nemann</a>
 */
public class LayoutServiceBuilder {

    /** identifier of the extension point for layout providers */
    public static final String EXTP_ID_LAYOUT_PROVIDERS = "de.cau.cs.kieler.kiml.layout.layoutProviders";
    /** identifier of the extension point for layout listeners */
    public static final String EXTP_ID_LAYOUT_LISTENERS = "de.cau.cs.kieler.kiml.layout.layoutListeners";
    /** identifier of the extension point for layout info */
    public static final String EXTP_ID_LAYOUT_INFO = "de.cau.cs.kieler.kiml.layout.layoutInfo";
    /** name of the 'layoutProvider' element in the 'layout providers' extension point */
    public static final String ELEMENT_LAYOUT_PROVIDER = "layoutProvider";
    /** name of the 'layoutType' element in the 'layout providers' extension point */
    public static final String ELEMENT_LAYOUT_TYPE = "layoutType";
    /** name of the 'category' element in the 'layout providers' extension point */
    public static final String ELEMENT_CATEGORY = "category";
    /** name of the 'layoutOption' element in the 'layout providers' extension point */
    public static final String ELEMENT_LAYOUT_OPTION = "layoutOption";
    /** name of the 'knownOption' element in the 'layout providers' extension point */
    public static final String ELEMENT_KNOWN_OPTION = "knownOption";
    /** name of the 'supportedDiagram' element in the 'layout providers' extension point */
    public static final String ELEMENT_SUPPORTED_DIAGRAM = "supportedDiagram";
    /** name of the 'diagramType' element in the 'layout info' extension point */
    public static final String ELEMENT_DIAGRAM_TYPE = "diagramType";
    /** name of the 'binding' element in the 'layout info' extension point */
    public static final String ELEMENT_BINDING = "binding";
    /** name of the 'option' element in the 'layout info' extension point */
    public static final String ELEMENT_OPTION = "option";
    /** name of the 'layoutListener' element in the 'layout listeners' extension point */
    public static final String ELEMENT_LAYOUT_LISTENER = "layoutListener";
    /** name of the 'category' attribute in the extension points */
    public static final String ATTRIBUTE_CATEGORY = "category";
    /** name of the 'class' attribute in the extension points */
    public static final String ATTRIBUTE_CLASS = "class";
    /** name of the 'description' attribute in the extension points */
    public static final String ATTRIBUTE_DESCRIPTION = "description";
    /** name of the 'diagram' attribute in the extension points */
    public static final String ATTRIBUTE_DIAGRAM = "diagram";
    /** name of the 'id' attribute in the extension points */
    public static final String ATTRIBUTE_ID = "id";
    /** name of the 'name' attribute in the extension points */
    public static final String ATTRIBUTE_NAME = "name";
    /** name of the 'option' attribute in the extension points */
    public static final String ATTRIBUTE_OPTION = "option";
    /** name of the 'parameter' attribute in the extension points */
    public static final String ATTRIBUTE_PARAMETER = "parameter";
    /** name of the 'priority' attribute in the extension points */
    public static final String ATTRIBUTE_PRIORITY = "priority";
    /** name of the 'type' attribute in the extension points */
    public static final String ATTRIBUTE_TYPE = "type";
    /** name of the 'value' attribute in the extension points */
    public static final String ATTRIBUTE_VALUE = "value";

    /** default name for layout providers for which no name is given */
    public static final String DEFAULT_PROVIDER_NAME = "<Unnamed Layouter>";
    /** default name for layout options for which no name is given */
    public static final String DEFAULT_OPTION_NAME = "<Unnamed Option>";
    
	/**
	 * Build the layout services.
	 */
	public static void buildLayoutServices() {
		// create instance of the layout service holder class
		LayoutServices.INSTANCE = new LayoutServices();
		// build layout services for all extension points
		loadLayoutProviderExtensions();
		loadLayoutListenerExtensions();
		loadLayoutInfoExtensions();
		LayoutServices.INSTANCE.initialize();
		// load preferences for KIML
		LoadPreferences();
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
	public static void readSupportPriorities(int[][] priorityData,
	        String[] layoutProviders, String[] diagramTypes) {
	    List<String> layoutProviderList = Arrays.asList(layoutProviders);
	    List<String> diagramTypesList = Arrays.asList(diagramTypes);
	    for (int i = 0; i < layoutProviders.length; i++)
	        Arrays.fill(priorityData[i], LayoutProviderData.MIN_PRIORITY);
	    
        IConfigurationElement[] extensions = Platform.getExtensionRegistry()
                .getConfigurationElementsFor(EXTP_ID_LAYOUT_PROVIDERS);
        for (IConfigurationElement element : extensions) {
            if (ELEMENT_LAYOUT_PROVIDER.equals(element.getName())) {
                int providerIndex = layoutProviderList.indexOf(
                        element.getAttribute(ATTRIBUTE_ID));
                if (providerIndex >= 0) {
                    for (IConfigurationElement child : element.getChildren()) {
                        if (ELEMENT_SUPPORTED_DIAGRAM.equals(child.getName())) {
                            int typeIndex = diagramTypesList.indexOf(
                                    child.getAttribute(ATTRIBUTE_TYPE));
                            if (typeIndex >= 0) {
                                String priority = child.getAttribute(ATTRIBUTE_PRIORITY);
                                try {
                                   priorityData[providerIndex][typeIndex] = Integer.parseInt(priority);
                                } catch (NumberFormatException exception) {}
                            }
                        }
                    }
                }
            }
        }
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
    				AbstractLayoutProvider layoutProvider = (AbstractLayoutProvider)element
    						.createExecutableExtension(ATTRIBUTE_CLASS);
    				if (layoutProvider != null) {
    				    LayoutProviderData providerData = new LayoutProviderData();
    				    providerData.instance = layoutProvider;
    				    providerData.id = element.getAttribute(ATTRIBUTE_ID);
    				    if (providerData.id == null) {
    				    	IStatus status = new Status(IStatus.WARNING, KimlUiPlugin.PLUGIN_ID,
    				    			0, "Layout provider with empty identifier submitted by "
    				    			+ element.getNamespaceIdentifier(), null);
    				    	StatusManager.getManager().handle(status, StatusManager.LOG);
    				    	continue;
    				    }
    				    providerData.name = element.getAttribute(ATTRIBUTE_NAME);
    				    if (providerData.name == null)
    				        providerData.name = DEFAULT_PROVIDER_NAME;
    				    layoutProvider.initialize(element.getAttribute(ATTRIBUTE_PARAMETER));
    				    providerData.type = element.getAttribute(ATTRIBUTE_TYPE);
    				    if (providerData.type == null)
    				        providerData.type = "";
    				    providerData.category = element.getAttribute(ATTRIBUTE_CATEGORY);
    				    if (providerData.category == null)
    				        providerData.category = "";
    				    for (IConfigurationElement child : element.getChildren()) {
    				        if (ELEMENT_KNOWN_OPTION.equals(child.getName())) {
    				            String option = child.getAttribute(ATTRIBUTE_OPTION);
    				            if (option != null && option.length() > 0)
    				                providerData.setOption(option, true);
    				        }
    				        else if (ELEMENT_SUPPORTED_DIAGRAM.equals(child.getName())) {
    				            String type = child.getAttribute(ATTRIBUTE_TYPE);
    				            String priority = child.getAttribute(ATTRIBUTE_PRIORITY);
    				            try {
    				                providerData.setDiagramSupport(type,
    				                        Integer.parseInt(priority));
    				            } catch (NumberFormatException exception) {
    			                    IStatus status = new Status(IStatus.WARNING, KimlUiPlugin.PLUGIN_ID, 0,
    			                            "Failed to parse 'priority' attribute in 'supportedDiagram' element.", exception);
    			                    StatusManager.getManager().handle(status);
    				            }
    				        }
    				    }
    					LayoutServices.INSTANCE.addLayoutProvider(providerData);
    				}
    			}
	            catch (CoreException exception) {
    				StatusManager.getManager().handle(exception, KimlUiPlugin.PLUGIN_ID);
    			}
		    }
		    else if (ELEMENT_LAYOUT_TYPE.equals(element.getName())) {
		        // register a layout type from the extension
		        LayoutServices.INSTANCE.addLayoutType(
		                element.getAttribute(ATTRIBUTE_ID),
		                element.getAttribute(ATTRIBUTE_NAME));
		    }
		    else if (ELEMENT_CATEGORY.equals(element.getName())) {
		        // register a category from the extension
		        LayoutServices.INSTANCE.addCategory(
		                element.getAttribute(ATTRIBUTE_ID),
		                element.getAttribute(ATTRIBUTE_NAME));
		    }
		    else if (ELEMENT_LAYOUT_OPTION.equals(element.getName())) {
		    	// register a layout option from the extension
		        LayoutOptionData optionData = new LayoutOptionData();
		        optionData.id = element.getAttribute(ATTRIBUTE_ID);
		        if (optionData.id == null) {
			    	IStatus status = new Status(IStatus.WARNING, KimlUiPlugin.PLUGIN_ID,
			    			0, "Layout option with empty identifier submitted by "
			    			+ element.getNamespaceIdentifier(), null);
			    	StatusManager.getManager().handle(status);
			    	continue;
			    }
		        optionData.type = element.getAttribute(ATTRIBUTE_TYPE);
		        if (optionData.type == null)
		        	optionData.type = LayoutOptionData.TYPE_STRING;
		        optionData.name = element.getAttribute(ATTRIBUTE_NAME);
		        if (optionData.name == null)
		        	optionData.name = DEFAULT_OPTION_NAME;
		        optionData.description = element.getAttribute(ATTRIBUTE_DESCRIPTION);
		        if (optionData.description == null)
		        	optionData.description = "";
		        LayoutServices.INSTANCE.addLayoutOption(optionData);
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
    					LayoutServices.INSTANCE.addLayoutListener(layoutListener);
    				}
    			}
    			catch (CoreException exception) {
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
                LayoutServices.INSTANCE.addDiagramType(
                        element.getAttribute(ATTRIBUTE_ID),
                        element.getAttribute(ATTRIBUTE_NAME));
            }
            else if (ELEMENT_BINDING.equals(element.getName())) {
                // register a binding from the extension
                try {
                    String typeName = element.getAttribute(ATTRIBUTE_CLASS);
                    if (typeName != null) {
                        Class<?> editPartType = Platform.getBundle(
                                element.getContributor().getName()).loadClass(typeName);
                        LayoutServices.INSTANCE.addEditPartBinding(editPartType,
                                element.getAttribute(ATTRIBUTE_TYPE),
                                element.getAttribute(ATTRIBUTE_ID));
                    }
                }
                catch (Exception exception) {
                    IStatus status = new Status(IStatus.WARNING, KimlUiPlugin.PLUGIN_ID, 0,
                            "Failed to load 'binding' element from extension point.", exception);
                    StatusManager.getManager().handle(status);
                }
            }
            else if (ELEMENT_OPTION.equals(element.getName())) {
            	// register a layout option from the extension
            	LayoutServices.INSTANCE.setupOption(
            			element.getAttribute(ATTRIBUTE_DIAGRAM),
            			element.getAttribute(ATTRIBUTE_OPTION),
            			element.getAttribute(ATTRIBUTE_VALUE));
            }
        }
    }
    
    /**
     * Loads preferences for KIML.
     */
    private static void LoadPreferences() {
        IPreferenceStore preferenceStore = KimlUiPlugin.getDefault().getPreferenceStore();
        Collection<LayoutProviderData> layoutProviderData = LayoutServices.INSTANCE.getLayoutProviderData();
        Collection<String> diagramTypes = LayoutServices.INSTANCE.getDiagramTypes();
        for (LayoutProviderData data : layoutProviderData) {
            for (String diagramType : diagramTypes) {
                String preference = LayoutPreferencePage.getPreference(data.id, diagramType);
                if (preferenceStore.contains(preference)) {
                    data.setDiagramSupport(diagramType, preferenceStore.getInt(preference));
                }
            }
        }
    }
	
}
