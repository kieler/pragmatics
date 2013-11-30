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
package de.cau.cs.kieler.kiml.service;

import org.eclipse.core.runtime.Platform;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import de.cau.cs.kieler.core.alg.DefaultFactory;
import de.cau.cs.kieler.kiml.LayoutAlgorithmData;
import de.cau.cs.kieler.kiml.LayoutConfigService;
import de.cau.cs.kieler.kiml.LayoutDataService;

/**
 * The activator class controls the plug-in life cycle.
 * 
 * @author msp
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow by msp
 */
public class KimlServicePlugin extends AbstractUIPlugin {

    /** the plug-in ID. */
    public static final String PLUGIN_ID = "de.cau.cs.kieler.kiml.service";

    /** the shared instance. */
    private static KimlServicePlugin plugin;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void start(final BundleContext context) throws Exception {
        super.start(context);
        plugin = this;
        
        // The layout services are only created here if the UI plugin is present.
        // If not, the services are expected to be created in another component
        // that does not require UI, e.g. a server.
        if (Platform.getBundle("org.eclipse.ui") != null) {
            LayoutDataService.setInstanceFactory(new DefaultFactory<LayoutDataService>(
                    ExtensionLayoutDataService.class));
            LayoutConfigService.setInstanceFactory(new DefaultFactory<LayoutConfigService>(
                    ExtensionLayoutConfigService.class));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stop(final BundleContext context) throws Exception {
        LayoutConfigService layoutConfigService = LayoutConfigService.getInstance();
        if (layoutConfigService instanceof ExtensionLayoutConfigService) {
            ((ExtensionLayoutConfigService) layoutConfigService).storePreferences();
        }
        
        LayoutDataService layoutDataService = LayoutDataService.getInstance();
        for (LayoutAlgorithmData algoData : layoutDataService.getAlgorithmData()) {
            algoData.getInstancePool().clear();
        }
        
        plugin = null;
        super.stop(context);
    }

    /**
     * Returns the shared instance.
     * 
     * @return the shared instance
     */
    public static KimlServicePlugin getDefault() {
        return plugin;
    }
    
}
