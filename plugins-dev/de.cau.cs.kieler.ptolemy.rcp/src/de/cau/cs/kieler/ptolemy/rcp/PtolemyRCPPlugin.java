package de.cau.cs.kieler.ptolemy.rcp;

import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IStartup;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.ActionSetContributionItem;
import org.eclipse.ui.internal.PluginActionContributionItem;
import org.eclipse.ui.internal.WorkbenchWindow;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import com.google.common.collect.ImmutableSet;

/**
 * The activator class controls the plug-in life cycle
 */
@SuppressWarnings("restriction")
public class PtolemyRCPPlugin extends AbstractUIPlugin implements IStartup {

    // The plug-in ID
    public static final String PLUGIN_ID = "de.cau.cs.kieler.ptolemy.rcp"; //$NON-NLS-1$

    // The shared instance
    private static PtolemyRCPPlugin plugin;

    private static final String OPEN_FILE = "org.eclipse.ui.openLocalFile";

    /** a list with all accepted menu contributions. */
    final ImmutableSet<String> acceptedMenuContribs = ImmutableSet.of(OPEN_FILE, "quit",
            "reopenEditors", "mru", "null", "quit", "fileEnd", "org.eclipse.ui.file.exit");

    /**
     * The constructor
     */
    public PtolemyRCPPlugin() {
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
     * Returns the shared instance
     * 
     * @return the shared instance
     */
    public static PtolemyRCPPlugin getDefault() {
        return plugin;
    }

    /**
     * {@inheritDoc}
     */
    public void earlyStartup() {
        // switch to the ui thread
        Display.getDefault().syncExec(new Runnable() {

            public void run() {
                for (IWorkbenchWindow workbenchWindow : PlatformUI.getWorkbench()
                        .getWorkbenchWindows()) {
                    // hide the toolbar and the perspective area
                    WorkbenchWindow ww = (WorkbenchWindow) workbenchWindow;
                    ww.setCoolBarVisible(false);
                    ww.setPerspectiveBarVisible(false);

                    // hide ALL menus that we do not accept
                    // keep in mind, that the plugin.xml also hides elements!
                    MenuManager mm = ww.getMenuManager();
                    for (IContributionItem item : mm.getItems()) {
                        if (item instanceof MenuManager) {
                            boolean allInvisible = true;
                            for (IContributionItem innerItem : ((MenuManager) item).getItems()) {
                                if (acceptedMenuContribs.contains(innerItem.getId())) {
                                    allInvisible = false;
                                    specialTreatment(innerItem);
                                } else {
                                    innerItem.setVisible(false);
                                }
                                // System.out.println(innerItem.getId());
                            }
                            if (allInvisible) {
                                mm.remove(item);
                            }
                        }
                    }

                    // refresh the workbench
                    mm.update();
                    ww.updateActionBars();
                    ww.updateActionSets();
                }
            }
        });
    }

    private void specialTreatment(final IContributionItem item) {

        if (item.getId().equals(OPEN_FILE)) {
            // rename
            ActionSetContributionItem cItem = (ActionSetContributionItem) item;
            PluginActionContributionItem paItem =
                    (PluginActionContributionItem) cItem.getInnerItem();
            paItem.getAction().setText("Open...");
        }
    }

}
