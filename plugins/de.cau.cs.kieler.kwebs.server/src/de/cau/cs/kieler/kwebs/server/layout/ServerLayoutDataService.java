/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2008 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.kwebs.server.layout;

import java.io.File;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;

import de.cau.cs.kieler.kiml.LayoutDataService;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.service.ProgrammaticLayoutDataService;
import de.cau.cs.kieler.kwebs.server.Application;
import de.cau.cs.kieler.kwebs.server.configuration.Configuration;
import de.cau.cs.kieler.kwebs.server.logging.Logger;
import de.cau.cs.kieler.kwebs.server.logging.Logger.Severity;
import de.cau.cs.kieler.kwebs.server.publishing.PreviewImageHandler;
import de.cau.cs.kieler.kwebs.server.publishing.SupportingServerManager;
import de.cau.cs.kieler.kwebs.servicedata.Category;
import de.cau.cs.kieler.kwebs.servicedata.KnownOption;
import de.cau.cs.kieler.kwebs.servicedata.LayoutAlgorithm;
import de.cau.cs.kieler.kwebs.servicedata.LayoutOption;
import de.cau.cs.kieler.kwebs.servicedata.LayoutType;
import de.cau.cs.kieler.kwebs.servicedata.RemoteEnum;
import de.cau.cs.kieler.kwebs.servicedata.ServiceData;
import de.cau.cs.kieler.kwebs.servicedata.ServiceDataFactory;
import de.cau.cs.kieler.kwebs.servicedata.SupportedDiagram;
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

    /** 
     *  The cached preview images of the layout algorithms. The index is derived from the plug-in
     *  name of the defining plug-in and the path to the preview image.
     */
    private static Map<String, byte[]> previewImages
        = new HashMap<String, byte[]>();
    
    /** Default path for the preview image handler. */
    private static final String DEFAULT_PREVIEWIMAGESPATH
        = "preview";
    
    /** The path under which the preview image handler is published. */
    private static String previewImagesPath
        = DEFAULT_PREVIEWIMAGESPATH;
    
    /** Default port for accessing preview images. Same as port of support server. */
    private static final int DEFAULT_PREVIEWIMAGESPORT
        = 8444;
    
    /** The port under which the preview image handler is published. */
    //private static int previewImagesPort
    //    = -1;
    
    static {
        String implementation = null;      
        for (IConfigurationElement element : SupportingServerManager.getHandlerConfigurationElements()) {
            if (element.getName().equals(SupportingServerManager.ELEMENT_SUPPORTHANDLER)) {
                implementation = element.getAttribute(SupportingServerManager.ATTRIBUTE_IMPLEMENTATION);
                if (implementation.equals(PreviewImageHandler.class.getCanonicalName())) {
                    previewImagesPath = element.getAttribute(SupportingServerManager.ATTRIBUTE_PATH);
                }
            }
        }
        if (previewImagesPath == null) {
            Logger.log(
                Severity.WARNING, 
                "No path for the preview image handler found. Using default path of "
                + DEFAULT_PREVIEWIMAGESPATH + "."
            );            
        }
/*        
        try {
            URI uri = new URI(
                Configuration.getInstance().getConfigProperty(Configuration.SUPPORTINGSERVER_ADDRESS)
            );
            previewImagesPort = uri.getPort();
        } catch (Exception e) {
            // Ignore since an undefined port will be handled after the try-catch-block
        }
        if (previewImagesPort == -1) {
            Logger.log(
                Severity.WARNING, 
                "No port for the preview image handler found. Using default path of "
                + DEFAULT_PREVIEWIMAGESPATH + "."
            );
            previewImagesPort = DEFAULT_PREVIEWIMAGESPORT;
        }
*/        
    }
    
    /**
     * Private constructor.
     */
    private ServerLayoutDataService() {
        createServiceDataXMI();
    }

    /**
     * Initializes singleton instance of {@code ServerLayoutServiceService}
     * from the extension points.
     */
    public static void create() {
        if (LayoutDataService.getInstance() == null
            || LayoutDataService.getInstanceOf(LayoutDataService.SERVICEDATASERVICE) == null) {
            ServerLayoutDataService lds = new ServerLayoutDataService();
            LayoutDataService.addService(lds);
            lds.loadLayoutProviderExtensions();
            lds.registerProgrammaticOptions();
        }
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
     * Returns the layout meta data in XMI.
     *
     * @return String
     *             the meta data
     */
    public static String getServiceData() {
        return serviceDataXMI;
    }

    /**
     * Returns the requested preview image.
     * 
     * @param previewImage
     *            the identifier of the preview image
     * @return the requested preview image or {@code null} if the specified
     *         preview image could not be found
     */
    public static byte[] getPreviewImage(final String previewImage) {
        return previewImages.get(previewImage);
    }
    
    /** Type attribute value for enumeration layout options. */
    private static final String TYPE_ENUM
        = "enum";

    /**
     * Creates the XML representation of the layout capabilities of this
     * server.
     */
    private void createServiceDataXMI() {
        IConfigurationElement[] extensions =
            Platform.getExtensionRegistry()
                .getConfigurationElementsFor(EXTP_ID_LAYOUT_PROVIDERS);        
        ServiceDataFactory factory = ServiceDataFactoryImpl.init();
        ServiceData serviceData = factory.createServiceData();      
        serviceData.setVersion(Application.getVersion());
        readExtensionCategories(factory, serviceData, extensions);
        readExtensionLayoutTypes(factory, serviceData, extensions);
        readExtensionLayoutOptions(factory, serviceData, extensions);
        readExtensionLayoutAlgorithms(factory, serviceData, extensions);
        serviceDataXMI = new ServiceDataXmiTransformer().serialize(serviceData);
    }
        
    /**
     * 
     * @param factory
     * @param serviceData
     * @param extensions
     */
    private void readExtensionCategories(final ServiceDataFactory factory, 
        final ServiceData serviceData, final IConfigurationElement[] extensions) {
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
     * @param serviceData
     * @param extensions
     */
    private void readExtensionLayoutTypes(final ServiceDataFactory factory, 
        final ServiceData serviceData, final IConfigurationElement[] extensions) {
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
     * @param serviceData
     * @param extensions
     */
    private void readExtensionLayoutOptions(final ServiceDataFactory factory, 
        final ServiceData serviceData, final IConfigurationElement[] extensions) {
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
     * @param serviceData
     * @param extensions
     */
    private void readExtensionLayoutAlgorithms(final ServiceDataFactory factory, 
        final ServiceData serviceData, final IConfigurationElement[] extensions) {
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
                        String previewImagePath = /*previewImagesPath + "/" +*/ previewImageName;
                        previewImages.put(previewImageName, data);
                        algorithm.setPreviewImagePath(previewImagePath);  
                        //algorithm.setPreviewImagePort(previewImagesPort);
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
                Category category = getCategory(serviceData, element.getAttribute(ATTRIBUTE_CATEGORY));
                if (category != null) {
                    algorithm.setCategory(category);
                }
                LayoutType type = getLayoutType(serviceData, element.getAttribute(ATTRIBUTE_TYPE));
                if (type != null) {
                    algorithm.setType(type);
                }
                algorithm.setDescription(element.getAttribute(ATTRIBUTE_DESCRIPTION));
                serviceData.getLayoutAlgorithms().add(algorithm); 
                for (IConfigurationElement child : element.getChildren()) {
                    if (child.getName().equals(ELEMENT_KNOWN_OPTION)) {
                        KnownOption option = factory.createKnownOption();
                        LayoutOption tmpOption = getLayoutOption(
                            serviceData, child.getAttribute(ATTRIBUTE_OPTION)
                        );
                        if (tmpOption == null) {
                            throw new IllegalStateException(
                                "Option for layout algorithm not found"
                                + " (algorithm=" + algorithm.getId() 
                                + ", option=" + child.getAttribute("option") + ")"
                            );
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
    
    /**
     * 
     * @param serviceData
     * @param id
     * @return
     */
    private Category getCategory(final ServiceData serviceData, final String id) {
        for (Category category : serviceData.getCategories()) {
            if (category.getId().equals(id)) {
                return category;
            }
        }
        return null;
    }

    /**
     * 
     * @param serviceData
     * @param type
     * @return
     */
    private LayoutType getLayoutType(final ServiceData serviceData, final String type) {
        for (LayoutType tmpType : serviceData.getLayoutTypes()) {
            if (tmpType.getId().equals(type)) {
                return tmpType;
            }
        }
        return null;
    }

    /**
     * 
     * @param serviceData
     * @param option
     * @return
     */
    private LayoutOption getLayoutOption(final ServiceData serviceData, final String option) {
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
            (extensionPoint != null ? "\n\nExtension point: " + extensionPoint.toString() : "") 
            + (element != null ? "\n\nElement: " + element.toString() : "")
            + (attribute != null ? "\n\nAttribute: " + attribute : ""),
            exception
        );
    }

    /**
     * {@inheritDoc}
     */
    protected void reportError(final CoreException exception) {
        reportError(null, null, null, exception);
    }

}
