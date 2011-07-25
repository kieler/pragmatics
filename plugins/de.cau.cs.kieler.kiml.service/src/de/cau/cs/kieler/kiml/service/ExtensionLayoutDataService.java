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

import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

import de.cau.cs.kieler.core.WrappedException;
import de.cau.cs.kieler.core.alg.IFactory;
import de.cau.cs.kieler.kiml.AbstractLayoutProvider;
import de.cau.cs.kieler.kiml.LayoutAlgorithmData;
import de.cau.cs.kieler.kiml.LayoutDataService;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.LayoutTypeData;

/**
 * A layout data service that reads its content from the Eclipse extension registry.
 *
 * @author msp
 */
public abstract class ExtensionLayoutDataService extends LayoutDataService {
    
    /** identifier of the extension point for layout providers. */
    public static final String EXTP_ID_LAYOUT_PROVIDERS = "de.cau.cs.kieler.kiml.layoutProviders";
    /** name of the 'layout algorithm' element in the 'layout providers' extension point. */
    public static final String ELEMENT_LAYOUT_ALGORITHM = "layoutAlgorithm";
    /** name of the 'layout type' element in the 'layout providers' extension point. */
    public static final String ELEMENT_LAYOUT_TYPE = "layoutType";
    /** name of the 'category' element in the 'layout providers' extension point. */
    public static final String ELEMENT_CATEGORY = "category";
    /** name of the 'known option' element in the 'layout providers' extension point. */
    public static final String ELEMENT_KNOWN_OPTION = "knownOption";
    /** name of the 'layout  option' element in the 'layout providers' extension point. */
    public static final String ELEMENT_LAYOUT_OPTION = "layoutOption";
    /** name of the 'supported diagram' element in the 'layout providers' extension point. */
    public static final String ELEMENT_SUPPORTED_DIAGRAM = "supportedDiagram";
    /** name of the 'advanced' attribute in the extension points. */
    public static final String ATTRIBUTE_ADVANCED = "advanced";
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

    /**
     * Report an error that occurred while reading extensions.
     * 
     * @param extensionPoint the identifier of the extension point
     * @param element the configuration element
     * @param attribute the attribute that contains an invalid entry
     * @param exception an optional exception that was caused by the invalid entry
     */
    protected abstract void reportError(String extensionPoint, IConfigurationElement element,
            String attribute, Throwable exception);

    /**
     * Report an error that occurred while reading extensions.
     * 
     * @param exception a core exception holding a status with further information
     */
    protected abstract void reportError(CoreException exception);
    
    /**
     * Loads and registers all layout provider extensions from the extension point.
     */
    protected void loadLayoutProviderExtensions() {
        List<String[]> knownOptions = new LinkedList<String[]>();
        IConfigurationElement[] extensions = Platform.getExtensionRegistry()
                .getConfigurationElementsFor(EXTP_ID_LAYOUT_PROVIDERS);
        Registry registry = getRegistry();

        for (IConfigurationElement element : extensions) {
            if (ELEMENT_LAYOUT_ALGORITHM.equals(element.getName())) {
                // register a layout algorithm from the extension
                loadLayoutAlgorithm(element, knownOptions);
            } else if (ELEMENT_LAYOUT_OPTION.equals(element.getName())) {
                // register a layout option from the extension
                loadLayoutOption(element);
            } else if (ELEMENT_LAYOUT_TYPE.equals(element.getName())) {
                // register a layout type from the extension
                String id = element.getAttribute(ATTRIBUTE_ID);
                if (id == null || id.length() == 0) {
                    reportError(EXTP_ID_LAYOUT_PROVIDERS, element, ATTRIBUTE_ID, null);
                } else {
                    LayoutTypeData typeData = new LayoutTypeData();
                    typeData.setId(id);
                    typeData.setName(element.getAttribute(ATTRIBUTE_NAME));
                    typeData.setDescription(element.getAttribute(ATTRIBUTE_DESCRIPTION));
                    registry.addLayoutType(typeData);
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
                    registry.addCategory(id, name);
                }
            }
        }
        
        // load layout algorithm options
        for (String[] entry : knownOptions) {
            LayoutAlgorithmData algoData = getAlgorithmData(entry[0]);
            LayoutOptionData<?> optionData = getOptionData(entry[1]);
            if (algoData != null && optionData != null) {
                try {
                    Object defaultValue = optionData.parseValue(entry[2]);
                    algoData.setOption(optionData, defaultValue);
                } catch (IllegalStateException exception) {
                    reportError(EXTP_ID_LAYOUT_PROVIDERS, null, null, exception);
                }
            }
        }
    }
    
    /**
     * Create a layout algorithm data instance and configure it with platform-specific extensions.
     * 
     * @param element a configuration element to use for configuration
     * @return a new layout algorithm data instance
     */
    protected LayoutAlgorithmData createLayoutAlgorithmData(final IConfigurationElement element) {
        return new LayoutAlgorithmData();
    }
    
    /**
     * Create a layout provider factory from a configuration element.
     * 
     * @param element a configuration element from an extension
     * @return a factory for layout provider instances
     */
    protected IFactory<AbstractLayoutProvider> getLayoutProviderFactory(
            final IConfigurationElement element) {
        return new IFactory<AbstractLayoutProvider>() {
            public AbstractLayoutProvider create() {
                try {
                    AbstractLayoutProvider provider = (AbstractLayoutProvider)
                            element.createExecutableExtension(ATTRIBUTE_CLASS);
                    provider.initialize(element.getAttribute(ATTRIBUTE_PARAMETER));
                    return provider;
                } catch (CoreException e) {
                    throw new WrappedException(e);
                }
            }
            public void destroy(AbstractLayoutProvider provider) {
                provider.dispose();
            }
        };
    }
    
    /**
     * Load a class from a configuration element.
     * 
     * @param element a configuration element from an extension
     * @return a class, or {@code null} if none could be loaded
     */
    protected Class<?> loadClass(final IConfigurationElement element) {
        String className = element.getAttribute(ATTRIBUTE_CLASS);
        if (className != null && className.length() > 0) {
            Bundle contributor = Platform.getBundle(element.getContributor().getName());
            if (contributor != null) {
                try {
                    Class<?> clazz = contributor.loadClass(className);
                    return clazz;
                } catch (ClassNotFoundException exception) {
                    reportError(EXTP_ID_LAYOUT_PROVIDERS, element, ATTRIBUTE_CLASS, exception);
                }
            }
        }
        return null;
    }
    
    /**
     * Load a layout algorithm from a configuration element.
     * 
     * @param element a configuration element from an extension
     * @param knownOptions the list of known layout options
     */
    private void loadLayoutAlgorithm(final IConfigurationElement element,
            final List<String[]> knownOptions) {
        try {
            IFactory<AbstractLayoutProvider> layoutProviderFactory = getLayoutProviderFactory(element);
            LayoutAlgorithmData algoData = createLayoutAlgorithmData(element);
            if (layoutProviderFactory != null) {
                algoData.createPool(layoutProviderFactory);
            }
            String layouterId = element.getAttribute(ATTRIBUTE_ID);
            if (layouterId == null || layouterId.length() == 0) {
                reportError(EXTP_ID_LAYOUT_PROVIDERS, element, ATTRIBUTE_ID, null);
                return;
            }
            algoData.setId(layouterId);
            algoData.setName(element.getAttribute(ATTRIBUTE_NAME));
            algoData.setDescription(element.getAttribute(ATTRIBUTE_DESCRIPTION));
            algoData.setCategory(element.getAttribute(ATTRIBUTE_CATEGORY));
            
            // process the layout type
            String layoutType = element.getAttribute(ATTRIBUTE_TYPE);
            if (layoutType == null) {
                layoutType = "";
            }
            LayoutTypeData typeData = getTypeData(layoutType);
            if (typeData == null) {
                typeData = new LayoutTypeData();
                typeData.setId(layoutType);
                getRegistry().addLayoutType(typeData);
            }
            algoData.setType(layoutType);
            typeData.getLayouters().add(algoData);
            
            // process child elements (known options and supported diagrams)
            for (IConfigurationElement child : element.getChildren()) {
                if (ELEMENT_KNOWN_OPTION.equals(child.getName())) {
                    String option = child.getAttribute(ATTRIBUTE_OPTION);
                    if (option != null && option.length() > 0) {
                        String defaultValue = child.getAttribute(ATTRIBUTE_DEFAULT);
                        knownOptions.add(new String[] { layouterId, option, defaultValue });
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
                            algoData.setDiagramSupport(type,
                                    Integer.parseInt(priority));
                        } catch (NumberFormatException exception) {
                            reportError(EXTP_ID_LAYOUT_PROVIDERS, child,
                                    ATTRIBUTE_PRIORITY, exception);
                        }
                    }
                }
            }
            
            getRegistry().addLayoutProvider(algoData);
            
        } catch (Throwable throwable) {
            reportError(EXTP_ID_LAYOUT_PROVIDERS, element, ATTRIBUTE_CLASS, throwable);
        }
    }
    
    /**
     * Load a layout option from a configuration element.
     * 
     * @param element a configuration element from an extension
     */
    private void loadLayoutOption(final IConfigurationElement element) {
        LayoutOptionData<Object> optionData = new LayoutOptionData<Object>();
        String optionId = element.getAttribute(ATTRIBUTE_ID);
        if (optionId == null || optionId.length() == 0) {
            reportError(EXTP_ID_LAYOUT_PROVIDERS, element, ATTRIBUTE_ID, null);
            return;
        }
        optionData.setId(optionId);
        try {
            optionData.setType(element.getAttribute(ATTRIBUTE_TYPE));
        } catch (IllegalArgumentException exception) {
            reportError(EXTP_ID_LAYOUT_PROVIDERS, element, ATTRIBUTE_TYPE, exception);
            return;
        }
        optionData.setOptionClass(loadClass(element));
        try {
            Object defaultValue = optionData.parseValue(
                    element.getAttribute(ATTRIBUTE_DEFAULT));
            optionData.setDefault(defaultValue);
        } catch (IllegalStateException exception) {
            reportError(EXTP_ID_LAYOUT_PROVIDERS, element, ATTRIBUTE_CLASS, exception);
        }
        optionData.setName(element.getAttribute(ATTRIBUTE_NAME));
        optionData.setDescription(element.getAttribute(ATTRIBUTE_DESCRIPTION));
        optionData.setTargets(element.getAttribute(ATTRIBUTE_APPLIESTO));
        String advanced = element.getAttribute(ATTRIBUTE_ADVANCED);
        optionData.setAdvanced(advanced != null && advanced.equals("true"));
        getRegistry().addLayoutOption(optionData);
    }
    
}
