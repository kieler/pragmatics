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
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;

import de.cau.cs.kieler.kiml.LayoutAlgorithmData;
import de.cau.cs.kieler.kiml.LayoutDataService;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.formats.GraphFormatData;
import de.cau.cs.kieler.kiml.formats.GraphFormatsService;
import de.cau.cs.kieler.kwebs.server.Application;
import de.cau.cs.kieler.kwebs.server.logging.Logger;
import de.cau.cs.kieler.kwebs.server.logging.Logger.Severity;
import de.cau.cs.kieler.kwebs.server.servicedata.Category;
import de.cau.cs.kieler.kwebs.server.servicedata.KnownOption;
import de.cau.cs.kieler.kwebs.server.servicedata.LayoutAlgorithm;
import de.cau.cs.kieler.kwebs.server.servicedata.LayoutOption;
import de.cau.cs.kieler.kwebs.server.servicedata.LayoutType;
import de.cau.cs.kieler.kwebs.server.servicedata.RemoteEnum;
import de.cau.cs.kieler.kwebs.server.servicedata.ServiceData;
import de.cau.cs.kieler.kwebs.server.servicedata.ServiceDataFactory;
import de.cau.cs.kieler.kwebs.server.servicedata.SupportedDiagram;
import de.cau.cs.kieler.kwebs.server.servicedata.SupportedFormat;
import de.cau.cs.kieler.kwebs.server.servicedata.impl.ServiceDataFactoryImpl;
import de.cau.cs.kieler.kwebs.server.servicedata.util.ServiceDataXmiTransformer;
import de.cau.cs.kieler.kwebs.server.util.Resources;

/**
 * This singleton class provides all extension based registered layout information at runtime 
 * and also the client side needed meta data about supported layout capabilities.
 *
 * @author swe
 */
public final class ServerLayoutDataService extends ExtensionLayoutDataService {

    /** Caching the layout service meta data. */
    private static String serviceDataXMI;
    
    /** Caching the model instance of the service meta data. */
    private static ServiceData serviceData;

    /** 
     *  The cached preview images of the layout algorithms. The index is derived from the plug-in
     *  name of the defining plug-in and the path to the preview image.
     */
    private Map<String, byte[]> previewImages = new HashMap<String, byte[]>();
    
    /**
     * Create the server layout data service.
     */
    public ServerLayoutDataService() {
        // Read extensions for the extension point
        super();
        // Build the meta data model
        createServiceData();
    }
    
    /**
     * Returns the singleton instance of {@code ServerLayoutDataService}.
     *
     * @return the singleton instance, or {@code null} if {@code ServerLayoutDataService}
     *         has not been registered yet
     */
    public static ServerLayoutDataService getInstance() {
        LayoutDataService service = LayoutDataService.getInstance();
        if (service instanceof ServerLayoutDataService) {
            return (ServerLayoutDataService) service;
        }
        return null;
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

    /**
     * {@inheritDoc}
     */
    @Override
    protected LayoutAlgorithmData createLayoutAlgorithmData(final IConfigurationElement element) {
        return new LayoutAlgorithmData();
    }

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
        for (GraphFormatData data : GraphFormatsService.getInstance().getFormatData()) {
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
                String type = element.getAttribute(ATTRIBUTE_TYPE);
                option.setType(type);
                if (type.equals(LayoutOptionData.ENUM_LITERAL)
                        || type.equals(LayoutOptionData.ENUMSET_LITERAL)) {
                    try {
                        String className = element.getAttribute(ATTRIBUTE_CLASS);
                        if (className == null || className.length() == 0) {
                            throw new IllegalArgumentException(
                                "Class name of enum layout option is not defined");
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
                    } catch (Exception exception) {
                        reportError(EXTP_ID_LAYOUT_PROVIDERS, element, ATTRIBUTE_CLASS, exception);
                    }
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
                        KnownOption knownOption = factory.createKnownOption();
                        LayoutOption option = getLayoutOption(child.getAttribute(ATTRIBUTE_OPTION));
                        if (option == null) {
                            Logger.log(
                                Severity.FAILURE,
                                "Option for layout algorithm not found, "
                                + " algorithm=" + algorithm.getId() 
                                + ", option=" + child.getAttribute("option")
                            );
                            continue;
                        }
                        knownOption.setOption(option);
                        knownOption.setDefault(child.getAttribute(ATTRIBUTE_DEFAULT));
                        algorithm.getKnownOptions().add(knownOption);
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
    @Override
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
    @Override
    protected void reportError(final CoreException exception) {
        reportError(null, null, null, exception);
    }

}
