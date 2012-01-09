package de.cau.cs.kieler.kaom.importer.ptolemy.improved;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * Activator for the Ptolemy importer plug-in.
 */
public class PtolemyImportPlugin extends AbstractUIPlugin {

    /** The plug-in ID. */
    public static final String PLUGIN_ID =
            "de.cau.cs.kieler.kaom.importer.ptolemy.improved"; //$NON-NLS-1$

    /** The shared instance. */
    private static PtolemyImportPlugin plugin;
    
    
    /**
     * The constructor.
     */
    public PtolemyImportPlugin() {
        // Nothing to be done here
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
     */
    public void start(BundleContext context) throws Exception {
        super.start(context);
        plugin = this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
     */
    public void stop(BundleContext context) throws Exception {
        plugin = null;
        super.stop(context);
    }

    /**
     * Returns the shared instance.
     * 
     * @return the shared instance.
     */
    public static PtolemyImportPlugin getDefault() {
        return plugin;
    }

}
