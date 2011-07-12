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
package de.cau.cs.kieler.kiml.ui;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import de.cau.cs.kieler.kiml.LayoutAlgorithmData;
import de.cau.cs.kieler.kiml.LayoutDataService;
import de.cau.cs.kieler.kiml.ui.service.EclipseLayoutDataService;
import de.cau.cs.kieler.kiml.ui.service.EclipseLayoutInfoService;

/**
 * The activator class controls the plug-in life cycle.
 * 
 * @kieler.rating 2009-12-11 proposed yellow msp
 * @author haf
 */
public class KimlUiPlugin extends AbstractUIPlugin {

    /** the plug-in ID. */
    public static final String PLUGIN_ID = "de.cau.cs.kieler.kiml.ui";

    /** the shared instance. */
    private static KimlUiPlugin plugin;

    /** nested class used to store images that are loaded at runtime. */
    public static class Images {

        /** image for choice property. */
        private final Image propChoice;
        /** image for false boolean property. */
        private final Image propFalse;
        /** image for float property. */
        private final Image propFloat;
        /** image for integer property. */
        private final Image propInt;
        /** image for text property. */
        private final Image propText;
        /** image for true boolean property. */
        private final Image propTrue;

        /**
         * Loads property images for the KIML UI plugin.
         */
        Images() {
            propChoice = getImageDescriptor("icons/obj16/prop_choice.gif").createImage();
            propFalse = getImageDescriptor("icons/obj16/prop_false.gif").createImage();
            propFloat = getImageDescriptor("icons/obj16/prop_float.gif").createImage();
            propInt = getImageDescriptor("icons/obj16/prop_int.gif").createImage();
            propText = getImageDescriptor("icons/obj16/prop_text.gif").createImage();
            propTrue = getImageDescriptor("icons/obj16/prop_true.gif").createImage();
        }

        /**
         * Frees all resources used by loaded images.
         */
        void dispose() {
            getPropChoice().dispose();
            getPropFalse().dispose();
            getPropFloat().dispose();
            getPropInt().dispose();
            getPropText().dispose();
            getPropTrue().dispose();
        }

        /**
         * Returns the image for choice properties.
         *
         * @return the propChoice
         */
        public Image getPropChoice() {
            return propChoice;
        }

        /**
         * Returns the image for false boolean properties.
         *
         * @return the propFalse
         */
        public Image getPropFalse() {
            return propFalse;
        }

        /**
         * Returns the image for float properties.
         *
         * @return the propFloat
         */
        public Image getPropFloat() {
            return propFloat;
        }

        /**
         * Returns the image for integer properties.
         *
         * @return the propInt
         */
        public Image getPropInt() {
            return propInt;
        }

        /**
         * Returns the image for text properties.
         *
         * @return the propText
         */
        public Image getPropText() {
            return propText;
        }

        /**
         * Returns the image for true boolean properties.
         *
         * @return the propTrue
         */
        public Image getPropTrue() {
            return propTrue;
        }

    }

    /**
     * The constructor.
     */
    public KimlUiPlugin() {
    }

    /** the images class instance. */
    private Images images;

    /**
     * Returns the images, or {@code null} if the plugin was disposed.
     * 
     * @return the images
     */
    public Images getImages() {
        return images;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(final BundleContext context) throws Exception {
        super.start(context);
        plugin = this;
        images = new Images();
        EclipseLayoutDataService.create();
        EclipseLayoutInfoService.create();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stop(final BundleContext context) throws Exception {
        EclipseLayoutInfoService layoutInfoService = EclipseLayoutInfoService.getInstance();
        if (layoutInfoService != null) {
            layoutInfoService.storePreferences();
        }
        LayoutDataService layoutDataService = LayoutDataService.getInstance();
        for (LayoutAlgorithmData algoData : layoutDataService.getAlgorithmData()) {
            algoData.getProviderPool().clear();
        }
        plugin = null;
        if (images != null) {
            images.dispose();
            images = null;
        }
        super.stop(context);
    }

    /**
     * Returns the shared instance.
     * 
     * @return the shared instance
     */
    public static KimlUiPlugin getDefault() {
        return plugin;
    }

    /**
     * Returns an image descriptor for the image file at the given plug-in
     * relative path.
     * 
     * @param path the path
     * @return the image descriptor
     */
    public static ImageDescriptor getImageDescriptor(final String path) {
        return imageDescriptorFromPlugin(PLUGIN_ID, path);
    }
}
