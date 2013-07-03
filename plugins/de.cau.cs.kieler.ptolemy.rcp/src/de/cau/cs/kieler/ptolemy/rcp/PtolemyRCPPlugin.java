package de.cau.cs.kieler.ptolemy.rcp;

import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IStartup;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
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

    /** a list with all accepted menu contributions. */
    final ImmutableSet<String> acceptedMenuContribs = ImmutableSet
            .of("org.eclipse.ui.openLocalFile");

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

                    // hide ALL menus that we do not accpet
                    MenuManager mm = ww.getMenuManager();
                    for (IContributionItem item : mm.getItems()) {
                        if (item instanceof MenuManager) {
                            boolean allInvisible = true;
                            for (IContributionItem innerItem : ((MenuManager) item).getItems()) {
                                if (acceptedMenuContribs.contains(innerItem.getId())) {
                                    allInvisible = false;
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

}
