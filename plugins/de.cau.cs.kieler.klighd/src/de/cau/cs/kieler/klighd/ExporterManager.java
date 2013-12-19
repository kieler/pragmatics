/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd;

import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.Bundle;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Management class for {@link IDiagramExporter} to export the currently visible diagram of a
 * {@link de.cau.cs.kieler.klighd.piccolo.viewer.PiccoloViewer PiccoloViewer}.
 * 
 * @author uru
 */
public final class ExporterManager {

    /** name of the 'exporter' element. */
    private static final String ELEMENT_EXPORTER = "exporter";
    

    private static ExporterManager instance;

    private Map<String, IDiagramExporter> exportersMap = Maps.newHashMap();
    private List<ExporterDescriptor> descriptors = Lists.newArrayList();

    private ExporterManager() {
        // singleton
    }

    static {
        instance = new ExporterManager();

        instance.loadExporters();
    }

    /**
     * @return the instance
     */
    public static ExporterManager getInstance() {
        return instance;
    }

    /**
     * @return return a copy of the original list
     */
    public List<ExporterDescriptor> getAvailableExporters() {
        return Lists.newLinkedList(descriptors);
    }

    /**
     * @param id
     *            the id of the registered {@link IDiagramExporter}.
     * @return the registered exporter for the passed id.
     * 
     * @throws IllegalArgumentException
     *             if the passed {@code id} is not registered.
     */
    public IDiagramExporter getExporter(final String id) {
        IDiagramExporter exporter = exportersMap.get(id);
        if (exporter == null) {
            throw new IllegalArgumentException("Id of " + IDiagramExporter.class + " not registered: "
                    + id + ".");
        }
        return exporter;
    }

    private void loadExporters() {
        // read the extension point
        IConfigurationElement[] extensions =
                Platform.getExtensionRegistry().getConfigurationElementsFor(
                        KlighdDataManager.EXTP_ID_EXTENSIONS);
        for (IConfigurationElement element : extensions) {
            if (!ELEMENT_EXPORTER.equals(element.getName())) {
                continue;
            }

            try {
                // store the generator
                String id = element.getAttribute("id");
                String subFormat = element.getAttribute("subFormat");
                if (subFormat == null) {
                    subFormat = "";
                }
                String extension = element.getAttribute("extension");
                String descr = element.getAttribute("description");

                // create the descriptor
                ExporterDescriptor descriptor =
                        new ExporterDescriptor(id, subFormat, descr, extension);
                descriptors.add(descriptor);

                // create the exporter class
                IDiagramExporter exporter =
                        (IDiagramExporter) element.createExecutableExtension("class");
                exportersMap.put(id, exporter);

            } catch (Exception e) {
                reportError(KlighdDataManager.EXTP_ID_EXTENSIONS, element, e);
            }

        }
    }

    private static void reportError(final String extensionPoint,
            final IConfigurationElement element, final Exception exception) {
        String message =
                "Extension point " + extensionPoint + ": Invalid entry in element "
                        + element.getName() + ", contributed by "
                        + element.getContributor().getName();
        IStatus status = new Status(IStatus.WARNING, KlighdPlugin.PLUGIN_ID, 0, message, exception);
        Bundle kp = Platform.getBundle(KlighdPlugin.PLUGIN_ID);
        Platform.getLog(kp).log(status);
    }

    /**
     * Aggregator for information about an exporter.
     */
    public static class ExporterDescriptor {
        private String exporterId;
        private String subFormatId;
        private String description;
        private String fileExtension;

        // CHECKSTYLEOFF javadoc
        public ExporterDescriptor(final String exporterId, final String subFormatId,
                final String description, final String fileExtension) {
            super();
            this.exporterId = exporterId;
            this.subFormatId = subFormatId;
            this.description = description;
            this.fileExtension = fileExtension;
        }

        /**
         * @return the formatId
         */
        public String getExporterId() {
            return exporterId;
        }

        /**
         * @return the subFormatId
         */
        public String getSubFormatId() {
            return subFormatId;
        }

        /**
         * @return the description
         */
        public String getDescription() {
            return description;
        }

        /**
         * @return the fileExtension
         */
        public String getFileExtension() {
            return fileExtension;
        }
    }
}
