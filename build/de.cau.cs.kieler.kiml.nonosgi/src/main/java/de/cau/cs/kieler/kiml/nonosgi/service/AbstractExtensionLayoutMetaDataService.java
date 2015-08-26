/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.nonosgi.service;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.RegistryFactory;

import com.google.common.base.Strings;

import de.cau.cs.kieler.core.WrappedException;
import de.cau.cs.kieler.core.alg.IFactory;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.AbstractLayoutProvider;
import de.cau.cs.kieler.kiml.LayoutAlgorithmData;
import de.cau.cs.kieler.kiml.LayoutMetaDataService;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.LayoutTypeData;
import de.cau.cs.kieler.kiml.options.GraphFeature;

/**
 * A layout data service that reads its content from the Eclipse extension registry.
 * 
 * <strong>IMPORTANT:</strong></br> This class is copied from
 * {@link de.cau.cs.kieler.kiml.nonosgi.service.service.AbstractExtensionLayoutMetaDataService
 * AbstractExtensionLayoutMetaDataService} and adapted such that it has no Eclipse dependencies,
 * i.e. calls to {@link org.eclipse.runtime.Platform#getBundle(...).loadClass(...)} were replaced by
 * {@link Class#forName(String)}.
 * The changes in below code are marked by 'CHANGED'
 * 
 *
 * @author msp
 * @author csp
 */
abstract class AbstractExtensionLayoutMetaDataService extends LayoutMetaDataService {
    
    /** identifier of the extension point for layout providers. */
    protected static final String EXTP_ID_LAYOUT_PROVIDERS = "de.cau.cs.kieler.kiml.layoutProviders";
    /** name of the 'layout algorithm' element in the 'layout providers' extension point. */
    protected static final String ELEMENT_LAYOUT_ALGORITHM = "layoutAlgorithm";
    /** name of the 'layout type' element in the 'layout providers' extension point. */
    protected static final String ELEMENT_LAYOUT_TYPE = "layoutType";
    /** name of the 'category' element in the 'layout providers' extension point. */
    protected static final String ELEMENT_CATEGORY = "category";
    /** name of the 'dependency' element in the 'layout providers' extension point. */
    protected static final String ELEMENT_DEPENDENCY = "dependency";
    /** name of the 'known option' element in the 'layout providers' extension point. */
    protected static final String ELEMENT_KNOWN_OPTION = "knownOption";
    /** name of the 'layout  option' element in the 'layout providers' extension point. */
    protected static final String ELEMENT_LAYOUT_OPTION = "layoutOption";
    /** name of the 'supported diagram' element in the 'layout providers' extension point. */
    protected static final String ELEMENT_SUPPORTED_DIAGRAM = "supportedDiagram";
    /** name of the 'supported feature' element in the 'layout providers' extension point. */
    protected static final String ELEMENT_SUPPORTED_FEATURE = "supportedFeature";
    /** name of the 'advanced' attribute in the extension points. */
    protected static final String ATTRIBUTE_ADVANCED = "advanced";
    /** name of the 'appliesTo' attribute in the extension points. */
    protected static final String ATTRIBUTE_APPLIESTO = "appliesTo";
    /** name of the 'category' attribute in the extension points. */
    protected static final String ATTRIBUTE_CATEGORY = "category";
    /** name of the 'class' attribute in the extension points. */
    protected static final String ATTRIBUTE_CLASS = "class";
    /** name of the 'default' attribute in the extension points. */
    protected static final String ATTRIBUTE_DEFAULT = "default";
    /** name of the 'description' attribute in the extension points. */
    protected static final String ATTRIBUTE_DESCRIPTION = "description";
    /** name of the 'enumValues' attribute used in doing remote layout. */
    protected static final String ATTRIBUTE_ENUMVALUES = "enumValues";
    /** name of the 'feature' attribute in the extension points. */
    protected static final String ATTRIBUTE_FEATURE = "feature";
    /** name of the 'id' attribute in the extension points. */
    protected static final String ATTRIBUTE_ID = "id";
    /** name of the 'implementation' attribute of a layout option of type 'remoteenum'. */
    protected static final String ATTRIBUTE_IMPLEMENTATION = "implementation";
    /** name of the 'lowerBound' attribute in the extension points. */
    protected static final String ATTRIBUTE_LOWER_BOUND = "lowerBound";
    /** name of the 'name' attribute in the extension points. */
    protected static final String ATTRIBUTE_NAME = "name";
    /** name of the 'option' attribute in the extension points. */
    protected static final String ATTRIBUTE_OPTION = "option";
    /** name of the 'parameter' attribute in the extension points. */
    protected static final String ATTRIBUTE_PARAMETER = "parameter";
    /** name of the 'preview' attribute in the extension points. */
    protected static final String ATTRIBUTE_PREVIEW = "preview";
    /** name of the 'priority' attribute in the extension points. */
    protected static final String ATTRIBUTE_PRIORITY = "priority";
    /** name of the 'type' attribute in the extension points. */
    protected static final String ATTRIBUTE_TYPE = "type";
    /** name of the 'upperBound' attribute in the extension points. */
    protected static final String ATTRIBUTE_UPPER_BOUND = "upperBound";
    /** name of the 'value' attribute in the extension points. */
    protected static final String ATTRIBUTE_VALUE = "value";
    /** name of the 'variance' attribute in the extension points. */
    protected static final String ATTRIBUTE_VARIANCE = "variance";

    /**
     * Load all registered extensions for the layout providers extension point.
     */
    public AbstractExtensionLayoutMetaDataService() {
        loadLayoutProviderExtensions();
    }
    
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
     * Returns the extensions responsible for providing layout meta data. This method
     * can be overridden by subclasses in order to get extensions from a different source
     * than the Eclipse platform.
     * 
     * @return the extensions responsible for providing layout meta data
     */
    protected IConfigurationElement[] getProviderExtensions() {
        IConfigurationElement[] result = null;
        IExtensionRegistry registry = null;
        try {
            registry = RegistryFactory.getRegistry();
        } catch (Exception e) {
            // Ignore since an exception here means that this instance
            // is not being run in an eclipse environment
        }
        if (registry != null) {
            result = registry.getConfigurationElementsFor(EXTP_ID_LAYOUT_PROVIDERS);
        }
        return result;
    }
    
    /**
     * Loads and registers all layout provider extensions from the extension point.
     */
    private void loadLayoutProviderExtensions() {    
        List<String[]> knownOptions = new LinkedList<String[]>();
        List<String[]> dependencies = new LinkedList<String[]>();
        
        IConfigurationElement[] extensions = getProviderExtensions();
        if (extensions == null || extensions.length == 0) {
            return;
        }
        Registry registry = getRegistry();
        for (IConfigurationElement element : extensions) {
            if (ELEMENT_LAYOUT_ALGORITHM.equals(element.getName())) {
                // register a layout algorithm from the extension
                loadLayoutAlgorithm(element, knownOptions);
            } else if (ELEMENT_LAYOUT_OPTION.equals(element.getName())) {
                // register a layout option from the extension
                loadLayoutOption(element, dependencies);
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
            LayoutOptionData optionData = getOptionData(entry[1]);
            if (algoData != null && optionData != null) {
                try {
                    Object defaultValue = optionData.parseValue(entry[2]);
                    algoData.setOption(optionData, defaultValue);
                } catch (IllegalStateException exception) {
                    reportError(EXTP_ID_LAYOUT_PROVIDERS, null, null, exception);
                }
            }
        }
        
        // load layout option dependencies
        for (String[] entry : dependencies) {
            LayoutOptionData sourceOption = getOptionData(entry[0]);
            LayoutOptionData targetOption = getOptionData(entry[1]);
            if (sourceOption != null && targetOption != null) {
                try {
                    Object value = targetOption.parseValue(entry[2]);
                    sourceOption.getDependencies().add(new Pair<LayoutOptionData, Object>(
                            targetOption, value));
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
    protected abstract LayoutAlgorithmData createLayoutAlgorithmData(
            IConfigurationElement element);
    
    /**
     * Create a layout provider factory from a configuration element.
     * 
     * @param element a configuration element from an extension
     * @return a factory for layout provider instances
     */
    private IFactory<AbstractLayoutProvider> getLayoutProviderFactory(
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
            public void destroy(final AbstractLayoutProvider provider) {
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
    private Class<?> loadClass(final IConfigurationElement element) {
        String className = element.getAttribute(ATTRIBUTE_CLASS);
        if (className != null && className.length() > 0) {
            
            // CHANGED
            try {
                Class<?> clazz = Class.forName(className);
                return clazz;
            } catch (ClassNotFoundException exception) {
                reportError(EXTP_ID_LAYOUT_PROVIDERS, element, ATTRIBUTE_CLASS, exception);
            }
            // CHANGE END
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
            
            // process child elements (known options and supported diagrams and features)
            for (IConfigurationElement child : element.getChildren()) {
                if (ELEMENT_KNOWN_OPTION.equals(child.getName())) {
                    String option = child.getAttribute(ATTRIBUTE_OPTION);
                    if (!Strings.isNullOrEmpty(option)) {
                        String defaultValue = child.getAttribute(ATTRIBUTE_DEFAULT);
                        knownOptions.add(new String[] { layouterId, option, defaultValue });
                    } else {
                        reportError(EXTP_ID_LAYOUT_PROVIDERS, child, ATTRIBUTE_OPTION, null);
                    }
                } else if (ELEMENT_SUPPORTED_DIAGRAM.equals(child.getName())) {
                    String type = child.getAttribute(ATTRIBUTE_TYPE);
                    if (Strings.isNullOrEmpty(type)) {
                        reportError(EXTP_ID_LAYOUT_PROVIDERS, child, ATTRIBUTE_TYPE, null);
                    } else {
                        String priority = child.getAttribute(ATTRIBUTE_PRIORITY);
                        if (Strings.isNullOrEmpty(priority)) {
                            algoData.setDiagramSupport(type, 0);
                        }
                        try {
                            algoData.setDiagramSupport(type, Integer.parseInt(priority));
                        } catch (NumberFormatException exception) {
                            reportError(EXTP_ID_LAYOUT_PROVIDERS, child, ATTRIBUTE_PRIORITY,
                                    exception);
                        }
                    }
                } else if (ELEMENT_SUPPORTED_FEATURE.equals(child.getName())) {
                    String featureString = child.getAttribute(ATTRIBUTE_FEATURE);
                    if (Strings.isNullOrEmpty(featureString)) {
                        reportError(EXTP_ID_LAYOUT_PROVIDERS, child, ATTRIBUTE_FEATURE, null);
                    } else {
                        String priority = child.getAttribute(ATTRIBUTE_PRIORITY);
                        String description = child.getAttribute(ATTRIBUTE_DESCRIPTION);
                        try {
                            GraphFeature feature = GraphFeature.valueOf(featureString.toUpperCase());
                            if (Strings.isNullOrEmpty(priority)) {
                                algoData.setFeatureSupport(feature, 0);
                            } else {
                                algoData.setFeatureSupport(feature, Integer.parseInt(priority));
                            }
                            if (!Strings.isNullOrEmpty(description)) {
                                algoData.setSupportedFeatureDescription(feature, description);
                            }
                        } catch (IllegalArgumentException exception) {
                            reportError(EXTP_ID_LAYOUT_PROVIDERS, child, ATTRIBUTE_FEATURE, exception);
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
     * @param dependencies the list of option dependencies
     */
    private void loadLayoutOption(final IConfigurationElement element,
            final List<String[]> dependencies) {
        LayoutOptionData optionData = new LayoutOptionData();
        // get option identifier
        String optionId = element.getAttribute(ATTRIBUTE_ID);
        if (Strings.isNullOrEmpty(optionId)) {
            reportError(EXTP_ID_LAYOUT_PROVIDERS, element, ATTRIBUTE_ID, null);
            return;
        }
        optionData.setId(optionId);
        // get option type
        String optionType = element.getAttribute(ATTRIBUTE_TYPE);        
        try {
            optionData.setType(optionType);
            optionData.setOptionClass(loadClass(element));
        } catch (IllegalArgumentException exception) {
            reportError(EXTP_ID_LAYOUT_PROVIDERS, element, ATTRIBUTE_TYPE, exception);
            return;
        }
        // get default value, lower bound, and upper bound
        try {
            Object defaultValue = optionData.parseValue(element.getAttribute(ATTRIBUTE_DEFAULT));
            optionData.setDefault(defaultValue);
            Object lowerBound = optionData.parseValue(element.getAttribute(ATTRIBUTE_LOWER_BOUND));
            optionData.setLowerBound(lowerBound);
            Object upperBound = optionData.parseValue(element.getAttribute(ATTRIBUTE_UPPER_BOUND));
            optionData.setUpperBound(upperBound);
        } catch (IllegalStateException exception) {
            reportError(EXTP_ID_LAYOUT_PROVIDERS, element, ATTRIBUTE_CLASS, exception);
        }
        // get name and description
        optionData.setName(element.getAttribute(ATTRIBUTE_NAME));
        optionData.setDescription(element.getAttribute(ATTRIBUTE_DESCRIPTION));
        // get option targets (which graph elements it applies to)
        optionData.setTargets(element.getAttribute(ATTRIBUTE_APPLIESTO));
        // whether the option should only be shown in advanced mode
        String advanced = element.getAttribute(ATTRIBUTE_ADVANCED);
        optionData.setAdvanced(advanced != null && advanced.equals("true"));
        // get variance for automatic configuration
        try {
            String varianceString = element.getAttribute(ATTRIBUTE_VARIANCE);
            if (varianceString != null) {
                optionData.setVariance(Float.parseFloat(varianceString));
            }
        } catch (NumberFormatException exception) {
            reportError(EXTP_ID_LAYOUT_PROVIDERS, element, ATTRIBUTE_VARIANCE, exception);
        }
        
        // process child elements (dependencies)
        for (IConfigurationElement childElement : element.getChildren()) {
            if (ELEMENT_DEPENDENCY.equals(childElement.getName())) {
                String depId = childElement.getAttribute(ATTRIBUTE_OPTION);
                if (Strings.isNullOrEmpty(depId)) {
                    reportError(EXTP_ID_LAYOUT_PROVIDERS, childElement, ATTRIBUTE_OPTION, null);
                } else {
                    dependencies.add(new String[] { optionId, depId,
                            childElement.getAttribute(ATTRIBUTE_VALUE) });
                }
            }
        }
        
        getRegistry().addLayoutOption(optionData);
    }
    
}
