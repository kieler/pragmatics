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
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IContributor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.elk.core.data.ILayoutMetaData;
import org.eclipse.elk.core.data.ILayoutMetaDataProvider;
import org.eclipse.elk.core.data.ILayoutMetaDataProvider.Registry;
import org.eclipse.elk.core.data.LayoutAlgorithmData;
import org.eclipse.elk.core.data.LayoutCategoryData;
import org.eclipse.elk.core.data.LayoutMetaDataService;
import org.eclipse.elk.core.data.LayoutOptionData;
import org.eclipse.elk.core.data.LayoutOptionData.Target;
import org.eclipse.elk.core.data.LayoutOptionData.Visibility;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import de.cau.cs.kieler.formats.GraphFormatData;
import de.cau.cs.kieler.formats.GraphFormatsService;
import de.cau.cs.kieler.kwebs.server.Application;
import de.cau.cs.kieler.kwebs.server.logging.Logger;
import de.cau.cs.kieler.kwebs.server.logging.Logger.Severity;
import de.cau.cs.kieler.kwebs.server.servicedata.Category;
import de.cau.cs.kieler.kwebs.server.servicedata.KnownOption;
import de.cau.cs.kieler.kwebs.server.servicedata.LayoutAlgorithm;
import de.cau.cs.kieler.kwebs.server.servicedata.LayoutOption;
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

    /** Keep track of plugins contributing layout algorithms. */
    private Map<LayoutAlgorithmData, IContributor> contributorPlugins = Maps.newHashMap();
    /**
     * The cached preview images of the layout algorithms. The index is derived from the plug-in
     * name of the defining plug-in and the path to the preview image.
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

        ContributorAwareRegistry contribAwareRegistry = new ContributorAwareRegistry();

        for (IConfigurationElement element : extensions) {
            try {
                if (ELEMENT_PROVIDER.equals(element.getName())) {
                    ILayoutMetaDataProvider provider = (ILayoutMetaDataProvider)
                            element.createExecutableExtension(ATTRIBUTE_CLASS);

                    contribAwareRegistry.setContributor(element.getContributor());

                    if (provider != null) {
                        service.registerLayoutMetaDataProvider(provider);
                        provider.apply(contribAwareRegistry);
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
            Category type = factory.createCategory();
            type.setId(element.getId());
            type.setName(element.getName());
            type.setDescription(element.getDescription());
            serviceData.getCategories().add(type);
        }
    }

    /**
     * @param factory
     * @param extensions
     */
    private void readExtensionLayoutOptions(final ServiceDataFactory factory,
            final LayoutMetaDataService metaDataService) {
        // TODO MIGRATE remove the set as soon as we get rid of legacyIds
        Set<String> known = Sets.newHashSet();
        for (LayoutOptionData element : metaDataService.getOptionData()) {
            if (known.contains(element.getId())) {
                continue;
            } else {
                known.add(element.getId());
            }
            LayoutOption option = factory.createLayoutOption();
            option.setId(element.getId());
            option.setName(element.getName());
            option.setDescription(element.getDescription());
            String appliesTo = "";
            for (Target target : element.getTargets()) {
                appliesTo += (appliesTo.isEmpty() ? "" : ", ") + target.toString();
            }
            option.setAppliesTo(appliesTo);
            Object defaultValue = element.getDefault();
            option.setDefault(defaultValue != null ? defaultValue.toString() : "");
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
                        = (Class<? extends Enum>) element.getOptionClass();       
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

            IContributor contributor = contributorPlugins.get(element);
            try {
                algorithm.setVersion(
                        Platform.getBundle(contributor.getName()).getVersion().toString());
            } catch (Exception e) {
                // Possible missing version number is not so bad
            }

            String preview = element.getPreviewImagePath();
            if (preview != null) {
                try {
                    byte[] data = Resources.readStreamAsByteArray(
                            Resources.getResourceStream(contributor.getName(), preview));
                    String previewImageName =
                            Integer.toHexString((contributor.getName() + preview).hashCode()) + "/"
                                    + new File(preview).getName();
                    String previewImagePath = previewImageName;
                    previewImages.put(previewImageName, data);
                    algorithm.setPreviewImagePath(previewImagePath);
                } catch (Exception e) {
                    Logger.log(Severity.WARNING,
                            "Could not load preview image (" + "contributor="
                                    + contributor.getName() + ", algorithm=" + element.getId()
                                    + ", preview=" + preview + ")",
                            e);
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

            // TODO MIGRATE remove the set as soon as we get rid of legacyIds
            Set<String> known = Sets.newHashSet();
            for (LayoutOptionData anOption : metaDataService.getOptionData()) {
                if (known.contains(anOption.getId())) {
                    continue;
                } else {
                    known.add(anOption.getId());
                }
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
                    Object defaultValue = element.getDefaultValue(anOption);
                    knownOption.setDefault(defaultValue != null ? defaultValue.toString() : "");
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

    /**
     * Custom {@link Registry} implementation recording the contributor plugins of 
     * layout algorithms, e.g. to be able to load preview images from them.  
     */
    private class ContributorAwareRegistry implements ILayoutMetaDataProvider.Registry {

        /** the current contributor. */
        private IContributor contributor;

        public void setContributor(final IContributor contributor) {
            this.contributor = contributor;
        }

        @Override
        public void register(final LayoutAlgorithmData algorithmData) {
            contributorPlugins.put(algorithmData, contributor);
        }

        @Override
        public void register(final LayoutOptionData optionData) {
        }

        @Override
        public void register(final LayoutCategoryData categoryData) {
        }

        @Override
        public void addDependency(final String sourceOption, final String targetOption,
                final Object requiredValue) {
        }

        @Override
        public void addOptionSupport(final String algorithm, final String option,
                final Object defaultValue) {
        }

    }

}
