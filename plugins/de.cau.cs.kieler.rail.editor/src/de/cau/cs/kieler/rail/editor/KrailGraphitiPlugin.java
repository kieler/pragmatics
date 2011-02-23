package de.cau.cs.kieler.rail.editor;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The Krail Graphiti
 * @author hdw
 *
 */
public class KrailGraphitiPlugin extends AbstractUIPlugin {

    /** The plug-in ID. */
    public static final String PLUGIN_ID = "de.cau.cs.kieler.rail.editor";

    /** The shared instance. */
    private static KrailGraphitiPlugin plugin;

    /**
     * The constructor.
     */
    public KrailGraphitiPlugin() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(final BundleContext context) throws Exception {
        super.start(context);
        plugin = this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stop(final BundleContext context) throws Exception {
        plugin = null;
        super.stop(context);
    }

    /**
     * Returns the shared instance.
     * @return the shared instance
     */
    public static KrailGraphitiPlugin getDefault() {
        return plugin;
    }

}
