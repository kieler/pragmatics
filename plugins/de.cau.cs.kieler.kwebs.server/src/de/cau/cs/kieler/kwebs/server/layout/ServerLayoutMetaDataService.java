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

package de.cau.cs.kieler.kwebs.server.layout;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.elk.core.data.ILayoutMetaData;
import org.eclipse.elk.core.data.ILayoutMetaDataProvider;
import org.eclipse.elk.core.data.LayoutAlgorithmData;
import org.eclipse.elk.core.data.LayoutCategoryData;
import org.eclipse.elk.core.data.LayoutMetaDataService;
import org.eclipse.elk.core.data.LayoutOptionData;
import org.eclipse.elk.core.data.LayoutOptionData.Target;
import org.eclipse.elk.core.data.LayoutOptionData.Visibility;

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
public final class ServerLayoutMetaDataService {

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
    private ServerLayoutMetaDataService() {
        // Read extensions for the extension point
        super();
        // Regularly load the extension point with all its specified 
        // meta data providers
        loadLayoutProviders();
        // Build the meta data model for the layout service
        createServiceData();
    }
    
    // The following constants and the #loadLayoutProviders() method is 
    // duplicated from org.eclipse.elk.service.ElkServicePlugin
    /** identifier of the extension point for layout providers. */
    protected static final String EXTP_ID_LAYOUT_PROVIDERS = "org.eclipse.elk.core.layoutProviders";
    /** name of the 'provider' element in the 'layout providers' extension point. */
    protected static final String ELEMENT_PROVIDER = "provider";
    /** name of the 'class' attribute in the extension points. */
    protected static final String ATTRIBUTE_CLASS = "class";
    
    /**
     * Creates a new instance, loading the extension point information in the process.
     */
    private void loadLayoutProviders() {
        IConfigurationElement[] extensions = Platform.getExtensionRegistry()
                .getConfigurationElementsFor(EXTP_ID_LAYOUT_PROVIDERS);
        LayoutMetaDataService service = LayoutMetaDataService.getInstance();
        
        for (IConfigurationElement element : extensions) {
            try {
                if (ELEMENT_PROVIDER.equals(element.getName())) {
                    ILayoutMetaDataProvider provider = (ILayoutMetaDataProvider)
                            element.createExecutableExtension(ATTRIBUTE_CLASS);
                    
                    if (provider != null) {
                        service.registerLayoutMetaDataProvider(provider);
                    }
                }
            } catch (CoreException exception) {
                reportError(exception);
            }
        }
    }
    
    private static ServerLayoutMetaDataService instance = null;
    
    /**
     * Returns the singleton instance of {@code ServerLayoutDataService}.
     *
     * @return the singleton instance, or {@code null} if {@code ServerLayoutDataService}
     *         has not been registered yet
        @Deprecated use {@link LayoutMetaDataService#getInstance()}
     */
    public static ServerLayoutMetaDataService getInstance() {
        if (instance == null) {
            instance = new ServerLayoutMetaDataService();
        }
        return instance;
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
     * Creates the model instance and the XMI representation of the services meta data.
     */
    private void createServiceData() {
        ServiceDataFactory factory = ServiceDataFactoryImpl.init();
        serviceData = factory.createServiceData();      
        serviceData.setVersion(Application.getVersion());
        
        LayoutMetaDataService metaDataService = LayoutMetaDataService.getInstance();
        
        readExtensionLayoutTypes(factory, metaDataService);
        readExtensionLayoutOptions(factory, metaDataService);
        readExtensionLayoutAlgorithms(factory, metaDataService);
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
     * @param factory
     * @param metaDataService
     */
    private void readExtensionLayoutTypes(final ServiceDataFactory factory, 
        final LayoutMetaDataService metaDataService) {
        for (LayoutCategoryData element : metaDataService.getCategoryData()) {
            LayoutType type = factory.createLayoutType();
            type.setId(element.getId());
            type.setName(element.getName());
            type.setDescription(element.getDescription());
            serviceData.getLayoutTypes().add(type);
        }
    }

    /**
     * @param factory
     * @param extensions
     */
    private void readExtensionLayoutOptions(final ServiceDataFactory factory, 
        final LayoutMetaDataService metaDataService) {
        for (LayoutOptionData element : metaDataService.getOptionData()) {

            LayoutOption option = factory.createLayoutOption();
            option.setId(element.getId());
            option.setName(element.getName());
            option.setDescription(element.getDescription());
            String appliesTo = "";
            for (Target target : element.getTargets()) {
                appliesTo += (appliesTo.isEmpty() ? "" : ", ") + target.toString();
            }
            option.setAppliesTo(appliesTo);
            option.setDefault(element.getDefault().toString());
            option.setImplementation(element.getOptionClass().getCanonicalName());
            option.setAdvanced(element.getVisibility() == Visibility.ADVANCED);
            String type = element.getType().toString().toLowerCase();
            option.setType(type);
            if (type.equals("enum")
                    || type.equals("enumset")) {
                try {
                    String className = element.getClass().getCanonicalName();
                    if (className == null || className.length() == 0) {
                        throw new IllegalArgumentException(
                            "Class name of enum layout option is not defined");
                    }
                    @SuppressWarnings({ "rawtypes", "unchecked" })
                    Class<? extends Enum> classInstance 
                        = (Class<? extends Enum>) element.getOptionClass().newInstance();       
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

    /** name of the 'preview' attribute in the extension points. */
    public static final String ATTRIBUTE_PREVIEW = "preview";

    /**
     * 
     * @param factory
     * @param extensions
     */
    private void readExtensionLayoutAlgorithms(final ServiceDataFactory factory, 
            final LayoutMetaDataService metaDataService) {
        
        for (LayoutAlgorithmData element : metaDataService.getAlgorithmData()) {

            LayoutAlgorithm algorithm = factory.createLayoutAlgorithm();
            
            int lastDotIndex = element.getId().lastIndexOf('.');
            String pluginId = element.getId().substring(0, lastDotIndex);
            
            try {
                algorithm.setVersion(
                    Platform.getBundle(pluginId).getVersion().toString());
            } catch (Exception e) {
                // Possible missing version number is not so bad
            }
            
            String preview = element.getPreviewImagePath();
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
                        + ", algorithm=" + element.getId()
                        + ", preview=" + preview + ")",
                        e
                    );
                }
            }
            algorithm.setId(element.getId());
            algorithm.setName(element.getName());
            Category category = getCategory(element.getCategoryId());
            if (category != null) {
                algorithm.setCategory(category);
            }
            algorithm.setDescription(element.getDescription());
            serviceData.getLayoutAlgorithms().add(algorithm);
            
            for (LayoutOptionData anOption : metaDataService.getOptionData()) {
                if (element.knowsOption(anOption)) {
                    KnownOption knownOption = factory.createKnownOption();
                    LayoutOption option = getLayoutOption(anOption.getId());
                    if (option == null) {
                        Logger.log(
                            Severity.FAILURE,
                            "Option for layout algorithm not found, "
                            + " algorithm=" + algorithm.getId() 
                            + ", option=" + anOption.getId()
                        );
                        continue;
                    }
                    knownOption.setOption(option);
                    knownOption.setDefault(element.getDefaultValue(anOption).toString());
                    algorithm.getKnownOptions().add(knownOption);
                }
            }
        }
    }

    /**
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
        final ILayoutMetaData element, final String attribute,
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
