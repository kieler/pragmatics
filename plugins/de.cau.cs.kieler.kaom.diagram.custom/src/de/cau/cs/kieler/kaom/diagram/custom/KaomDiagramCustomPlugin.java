package de.cau.cs.kieler.kaom.diagram.custom;

import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class KaomDiagramCustomPlugin extends AbstractUIPlugin {

    // The plug-in ID
    public static final String PLUGIN_ID = "de.cau.cs.kieler.kaom.diagram.custom";

    // The shared instance
    private static KaomDiagramCustomPlugin plugin;

    /**
     * The constructor
     */
    public KaomDiagramCustomPlugin() {
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
     */
    @Override
    public void start(BundleContext context) throws Exception {
        super.start(context);
        plugin = this;
    }

    /**
     * Getter for the active editor part.
     * 
     * @return the editor part or null
     */
    public IEditorPart getActiveEditorPart() {
        IEditorPart result = null;
        IWorkbench workbench = getWorkbench();
        if (workbench != null) {
            IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
            if (window != null) {
                IWorkbenchPage page = window.getActivePage();
                if (page != null) {
                    result = page.getActiveEditor();
                }
            }
        }
        return result;
    }

    /**
     * Getter for the display of the active workbench.
     * 
     * @return the display
     */
    public Display getDisplay() {
        return getWorkbench().getDisplay();
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
     */
    @Override
    public void stop(BundleContext context) throws Exception {
        plugin = null;
        super.stop(context);
    }

    /**
     * Returns the shared instance
     * 
     * @return the shared instance
     */
    public static KaomDiagramCustomPlugin getDefault() {
        return plugin;
    }

}
