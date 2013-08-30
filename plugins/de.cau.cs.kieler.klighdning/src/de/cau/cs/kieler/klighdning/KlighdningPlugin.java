package de.cau.cs.kieler.klighdning;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class KlighdningPlugin implements BundleActivator {

    public static final String PLUGIN_ID = "de.cau.cs.kieler.klighdning";

    private static BundleContext context;

    static BundleContext getContext() {
        return context;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
     */
    public void start(final BundleContext bundleContext) throws Exception {
        KlighdningPlugin.context = bundleContext;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
     */
    public void stop(final BundleContext bundleContext) throws Exception {
        KlighdningPlugin.context = null;
    }

}
