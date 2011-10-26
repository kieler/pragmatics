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

package de.cau.cs.kieler.kwebs.server.layout;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;

import de.cau.cs.kieler.kiml.LayoutDataService;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.service.ProgrammaticLayoutDataService;
import de.cau.cs.kieler.kiml.service.formats.GraphFormatData;
import de.cau.cs.kieler.kwebs.server.Application;
import de.cau.cs.kieler.kwebs.server.logging.Logger;
import de.cau.cs.kieler.kwebs.server.logging.Logger.Severity;
import de.cau.cs.kieler.kwebs.servicedata.Category;
import de.cau.cs.kieler.kwebs.servicedata.KnownOption;
import de.cau.cs.kieler.kwebs.servicedata.LayoutAlgorithm;
import de.cau.cs.kieler.kwebs.servicedata.LayoutOption;
import de.cau.cs.kieler.kwebs.servicedata.LayoutType;
import de.cau.cs.kieler.kwebs.servicedata.RemoteEnum;
import de.cau.cs.kieler.kwebs.servicedata.ServiceData;
import de.cau.cs.kieler.kwebs.servicedata.ServiceDataFactory;
import de.cau.cs.kieler.kwebs.servicedata.SupportedDiagram;
import de.cau.cs.kieler.kwebs.servicedata.SupportedFormat;
import de.cau.cs.kieler.kwebs.servicedata.impl.ServiceDataFactoryImpl;
import de.cau.cs.kieler.kwebs.servicedata.transformation.ServiceDataXmiTransformer;
import de.cau.cs.kieler.kwebs.util.Resources;

/**
 * This singleton class provides all extension based registered layout information at runtime 
 * and also the client side needed meta data about supported layout capabilities.
 *
 * @kieler.rating  2011-05-09 red
 * @author  swe
 */
public final class ServerLayoutDataService extends ProgrammaticLayoutDataService {

    /** Caching the layout service meta data. */
    private static String serviceDataXMI;
    
    /** Caching the model instance of the service meta data. */
    private static ServiceData serviceData;

    /** 
     *  The cached preview images of the layout algorithms. The index is derived from the plug-in
     *  name of the defining plug-in and the path to the preview image.
     */
    private Map<String, byte[]> previewImages
        = new HashMap<String, byte[]>();

    /** Mapping of layout option identifiers to layout option instances. */
    private Map<String, LayoutOptionData<?>> layoutOptions
        = new HashMap<String, LayoutOptionData<?>>();
    
    /**
     * Private constructor.
     */
    private ServerLayoutDataService() {
        // Create the transformation service instance; needed for building the meta data model
        ServerTransformationService.create();
        // Build the meta data model
        createServiceData();
    }

    /**
     * Initialize the singleton instance from the extension points.
     */
    public static void create() {
        if (LayoutDataService.getInstance() == null
            || LayoutDataService.getInstanceOf(LayoutDataService.SERVICEDATASERVICE) == null) {
            ServerLayoutDataService lds = new ServerLayoutDataService();
            LayoutDataService.addService(lds);
            lds.loadLayoutProviderExtensions();
            lds.registerProgrammaticOptions();
            lds.registerLayoutOptions();
        }
    }

    /** 
     * Creates the mapping from layout option identifiers to layout options.
     */
    private void registerLayoutOptions() {
        Collection<LayoutOptionData<?>> optionDataList = getOptionData();
        for (LayoutOptionData<?> optionData : optionDataList) {
            layoutOptions.put(optionData.getId(), optionData);
        }
    }

    /**
     * Returns the first registered option data instance where the identifier ends 
     * with the given suffix. The suffix is case sensitive.
     * 
     * @param idSuffix
     *            the suffix of the identifier of the layout option
     * @return the first registered option data instance where the identifier ends with the given suffix
     *         or {@code null} if no matching option data is registered in the data services. 
     */
    public LayoutOptionData<?> getMatchingOptionData(final String idSuffix) {
        LayoutOptionData<?> optionData = null;
        for (String id : layoutOptions.keySet()) {
            if (id.endsWith(idSuffix)) {
                optionData = layoutOptions.get(id);
                break;
            }
        }
        return optionData;
    }
    
    /**
     * Returns the singleton instance of {@code ServerLayoutDataService}.
     *
     * @return the singleton instance, or {@code null} if {@code ServerLayoutDataService}
     *         has not been registered yet
     */
    public static ServerLayoutDataService getInstance() {
        return LayoutDataService.getInstanceOf(
            LayoutDataService.SERVICEDATASERVICE
        );
    }

    /**
     * Returns the services meta data in XMI.
     *
     * @return String
     *             the meta data
     */
    public String getServiceData() {
        return serviceDataXMI;
    }

    /**
     * Returns the services meta data model.
     *
     * @return ServiceData
     *             the meta data model
     */
    public ServiceData getServiceDataModel() {
        return serviceData;
    }

    /**
     * Returns the requested preview image.
     * 
     * @param previewImage
     *            the identifier of the preview image
     * @return the requested preview image or {@code null} if the specified
     *         preview image could not be found
     */
    public byte[] getPreviewImage(final String previewImage) {
        return previewImages.get(previewImage);
    }
    
    /** Type attribute value for enumeration layout options. */
    private static final String TYPE_ENUM
        = "enum";

    /**
     * Creates the model instance and the XMI representation of the services meta data.
     */
    private void createServiceData() {
        ServiceDataFactory factory = ServiceDataFactoryImpl.init();
        serviceData = factory.createServiceData();      
        serviceData.setVersion(Application.getVersion());
        IConfigurationElement[] extensions = null;
        extensions =
            Platform.getExtensionRegistry()
                .getConfigurationElementsFor(EXTP_ID_LAYOUT_PROVIDERS);        
        readExtensionCategories(factory, extensions);
        readExtensionLayoutTypes(factory, extensions);
        readExtensionLayoutOptions(factory, extensions);
        readExtensionLayoutAlgorithms(factory, extensions);
        // Add transformation to the service meta data
        for (GraphFormatData data : ServerTransformationService.getInstance().getFormatData()) {
            SupportedFormat format = factory.createSupportedFormat();
            format.setId(data.getId());
            format.setDescription(data.getDescription());
            format.setName(data.getName());
            serviceData.getSupportedFormats().add(format);    
        }
        // Create XMI notation of the service meta data
        serviceDataXMI = new ServiceDataXmiTransformer().serialize(serviceData);
    }
        
    /**
     * 
     * @param factory
     * @param extensions
     */
    private void readExtensionCategories(final ServiceDataFactory factory, 
        final IConfigurationElement[] extensions) {
        for (IConfigurationElement element : extensions) {
            if (element.getName().equals(ELEMENT_CATEGORY)) {
                Category category = factory.createCategory();
                category.setId(element.getAttribute(ATTRIBUTE_ID));
                category.setName(element.getAttribute(ATTRIBUTE_NAME));
                serviceData.getCategories().add(category);
            }
        }
    }

    /**
     * 
     * @param factory
     * @param extensions
     */
    private void readExtensionLayoutTypes(final ServiceDataFactory factory, 
        final IConfigurationElement[] extensions) {
        for (IConfigurationElement element : extensions) {
            if (element.getName().equals(ELEMENT_LAYOUT_TYPE)) {
                LayoutType type = factory.createLayoutType();
                type.setId(element.getAttribute(ATTRIBUTE_ID));
                type.setName(element.getAttribute(ATTRIBUTE_NAME));
                type.setDescription(element.getAttribute(ATTRIBUTE_DESCRIPTION));
                serviceData.getLayoutTypes().add(type);
            }
        }
    }

    /**
     * 
     * @param factory
     * @param extensions
     */
    private void readExtensionLayoutOptions(final ServiceDataFactory factory, 
        final IConfigurationElement[] extensions) {
        for (IConfigurationElement element : extensions) {
            if (element.getName().equals(ELEMENT_LAYOUT_OPTION)) {
                LayoutOption option = factory.createLayoutOption();
                option.setId(element.getAttribute(ATTRIBUTE_ID));
                option.setName(element.getAttribute(ATTRIBUTE_NAME));
                option.setDescription(element.getAttribute(ATTRIBUTE_DESCRIPTION));
                option.setAppliesTo(element.getAttribute(ATTRIBUTE_APPLIESTO));
                option.setDefault(element.getAttribute(ATTRIBUTE_DEFAULT));
                option.setImplementation(element.getAttribute(ATTRIBUTE_CLASS));
                option.setAdvanced(Boolean.parseBoolean(element.getAttribute(ATTRIBUTE_ADVANCED)));
                if (element.getAttribute(ATTRIBUTE_TYPE).equals(TYPE_ENUM)) {
                    option.setType(LayoutOptionData.REMOTEENUM_LITERAL);        
                    try {
                        String className = element.getAttribute(ATTRIBUTE_CLASS);
                        if (className == null || className.length() == 0) {
                            throw new IllegalArgumentException(
                                "Class name of enum layout option is not defined"
                            );
                        }
                        @SuppressWarnings("rawtypes")
                        Class<? extends Enum> classInstance 
                            = Platform.getBundle(element.getContributor().getName()).
                                loadClass(className).asSubclass(Enum.class);       
                        RemoteEnum remoteEnum = factory.createRemoteEnum();
                        for (Enum<?> enumInstance : (Enum<?>[]) classInstance.getEnumConstants()) {
                            remoteEnum.getValues().add(enumInstance.toString());
                        }
                        option.setRemoteEnum(remoteEnum);
                    } catch (Exception e) {
                        reportError(null,  null, null, e);
                    }
                } else {
                    option.setType(element.getAttribute(ATTRIBUTE_TYPE));                    
                }
                serviceData.getLayoutOptions().add(option);
            }
        }
    }

    /** name of the 'preview' attribute in the extension points. */
    public static final String ATTRIBUTE_PREVIEW = "preview";

    /**
     * 
     * @param factory
     * @param extensions
     */
    private void readExtensionLayoutAlgorithms(final ServiceDataFactory factory, 
        final IConfigurationElement[] extensions) {
        for (IConfigurationElement element : extensions) {
            if (element.getName().equals(ELEMENT_LAYOUT_ALGORITHM)) {
                LayoutAlgorithm algorithm = factory.createLayoutAlgorithm();
                try {
                    algorithm.setVersion(
                        Platform.getBundle(element.getContributor().getName()).getVersion().toString()
                    );
                } catch (Exception e) {
                    // Possible missing version number is not so bad
                }
                String pluginId = element.getContributor().getName();
                String preview = element.getAttribute(ATTRIBUTE_PREVIEW);
                if (preview != null) {
                    try {
                        byte[] data = Resources.readStreamAsByteArray(
                            Resources.getResourceStream(pluginId, preview)
                        );
                        String previewImageName = Integer.toHexString((pluginId + preview).hashCode()) 
                                                  + "/" + new File(preview).getName();
                        String previewImagePath = previewImageName;
                        previewImages.put(previewImageName, data);
                        algorithm.setPreviewImagePath(previewImagePath);  
                    } catch (Exception e) {
                        Logger.log(Severity.WARNING,
                            "Could not load preview image ("
                            + "contributor=" + pluginId
                            + ", algorithm=" + element.getAttribute(ATTRIBUTE_ID)
                            + ", preview=" + preview + ")",
                            e
                        );
                    }
                }
                algorithm.setId(element.getAttribute(ATTRIBUTE_ID));
                algorithm.setName(element.getAttribute(ATTRIBUTE_NAME));
                Category category = getCategory(element.getAttribute(ATTRIBUTE_CATEGORY));
                if (category != null) {
                    algorithm.setCategory(category);
                }
                LayoutType type = getLayoutType(element.getAttribute(ATTRIBUTE_TYPE));
                if (type != null) {
                    algorithm.setType(type);
                }
                algorithm.setDescription(element.getAttribute(ATTRIBUTE_DESCRIPTION));
                serviceData.getLayoutAlgorithms().add(algorithm); 
                for (IConfigurationElement child : element.getChildren()) {
                    if (child.getName().equals(ELEMENT_KNOWN_OPTION)) {
                        KnownOption option = factory.createKnownOption();
                        LayoutOption tmpOption = getLayoutOption(
                            child.getAttribute(ATTRIBUTE_OPTION)
                        );
                        if (tmpOption == null) {
/*                            
                            throw new IllegalStateException(
                                "Option for layout algorithm not found"
                                + " (algorithm=" + algorithm.getId() 
                                + ", option=" + child.getAttribute("option") + ")"
                            );
*/
                            Logger.log(
                                Severity.FAILURE,
                                "Option for layout algorithm not found, "
                                + " algorithm=" + algorithm.getId() 
                                + ", option=" + child.getAttribute("option")
                            );
                            continue;
                        }
                        option.setOption(tmpOption);
                        option.setDefault(child.getAttribute(ATTRIBUTE_DEFAULT));
                        algorithm.getKnownOptions().add(option);
                    } else if (child.getName().equals(ELEMENT_SUPPORTED_DIAGRAM)) {
                        SupportedDiagram diagram = factory.createSupportedDiagram();
                        diagram.setType(child.getAttribute(ATTRIBUTE_TYPE));
                        diagram.setPriority(Integer.parseInt(child.getAttribute(ATTRIBUTE_PRIORITY)));
                        algorithm.getSupportedDiagrams().add(diagram);                        
                    }
                }
            }
        }
    }

    /** */
    private static final String ATTRIBUTE_DESCRIPTION
        = "description";

    /**
     * 
     * @param id
     * @return
     */
    private Category getCategory(final String id) {
        for (Category category : serviceData.getCategories()) {
            if (category.getId().equals(id)) {
                return category;
            }
        }
        return null;
    }

    /**
     * 
     * @param type
     * @return
     */
    private LayoutType getLayoutType(final String type) {
        for (LayoutType tmpType : serviceData.getLayoutTypes()) {
            if (tmpType.getId().equals(type)) {
                return tmpType;
            }
        }
        return null;
    }

    /**
     * 
     * @param option
     * @return
     */
    private LayoutOption getLayoutOption(final String option) {
        for (LayoutOption tmpOption : serviceData.getLayoutOptions()) {
            if (tmpOption.getId().equals(option)) {
                return tmpOption;
            }
        }
        return null;
    }
    
    /**
     * {@inheritDoc}
     */
    protected void reportError(final String extensionPoint,
        final IConfigurationElement element, final String attribute,
        final Throwable exception) {
        Logger.log(
            Severity.FAILURE, 
            "Error while parsing extension point", 
            "\n\nExtension point: " + (extensionPoint != null ? extensionPoint : "<unknown>") 
            + "\n\nElement: " + (element != null ? element.toString() : "<unknown>")
            + "\n\nAttribute: " + (attribute != null ? attribute : "<unknown>")
            + "\n\nException: " + (exception != null ? exception.getMessage() : "<unknown>")
        );
    }

    /**
     * {@inheritDoc}
     */
    protected void reportError(final CoreException exception) {
        reportError(null, null, null, exception);
    }

}
