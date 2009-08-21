/******************************************************************************
 * KIELER - Kiel Integrated Environment for Layout for the Eclipse RCP
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 * 
 *****************************************************************************/
package de.cau.cs.kieler.ksbase;

import org.eclipse.core.commands.Category;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.CommandManager;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.GroupMarker;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.menus.AbstractContributionFactory;
import org.eclipse.ui.menus.CommandContributionItem;
import org.eclipse.ui.menus.CommandContributionItemParameter;
import org.eclipse.ui.menus.IContributionRoot;
import org.eclipse.ui.menus.IMenuService;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.ui.services.IServiceLocator;
import org.osgi.framework.BundleContext;

import de.cau.cs.kieler.ksbase.transformations.EditorTransformationSettings;
import de.cau.cs.kieler.ksbase.transformations.ExecuteTransformationCommand;
import de.cau.cs.kieler.ksbase.transformations.Transformation;
import de.cau.cs.kieler.ksbase.transformations.TransformationManager;
import de.cau.cs.kieler.ksbase.ui.handler.TransformationCommandHandler;

/**
 * The activator class controls the plug-in life cycle
 */
public class KSBasEPlugin extends AbstractUIPlugin {

    // The plug-in ID
    public static final String PLUGIN_ID = "de.cau.cs.kieler.ksbase";

    // The shared instance
    private static KSBasEPlugin plugin;

    // Visibility flags for menu entries
    public static final int SHOW_MENU = 1; // Show entry in menu, if activated
    // or activate menu
    public static final int SHOW_TOOLBAR = 1 << 1; // Show entry in tool bar, if
    // activated or activate menu
    public static final int SHOW_CONTEXT = 1 << 2; // Show entry in context
    // menu, if activated or
    // activate context menu
    public static final int SHOW_BALLOON = 1 << 3; // Show entry in balloon

    // popup, if activated or
    // activate balloon popups

    /**
     * The constructor
     */
    public KSBasEPlugin() {
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
     */
    public void start(BundleContext context) throws Exception {
        super.start(context);
        plugin = this;
        TransformationManager.initializeTransformations();
        createMenu();
    }

    public void createMenu() {
        // Foreach editor create menus:
        IMenuService menuService = (IMenuService) PlatformUI.getWorkbench()
                .getService(IMenuService.class);
        final ICommandService cmdService = (ICommandService) PlatformUI
                .getWorkbench().getService(ICommandService.class);
        final Category kielerCategory = cmdService
                .getCategory("de.cau.cs.kieler.commands.category");
        
        for (final EditorTransformationSettings settings : TransformationManager
                .getEditors()) {
            if (settings.isShownInMenu()
                    && settings.getMenuLocation().length() > 0) {
                // Create menu contributions
                AbstractContributionFactory menuContribution = new AbstractContributionFactory(
                        settings.getMenuLocation(), null) {

                    @Override
                    public void createContributionItems(
                            IServiceLocator serviceLocator,
                            IContributionRoot additions) {
                        MenuManager dynamicMenu = new MenuManager(settings
                                .getMenuName(), "de.cau.cs.kieler.ksbase.menu"+settings.getMenuName());
                        for (Transformation t : settings.getTransformations()) {
                            String cmdID = "de.cau.cs.kieler.ksbase.commands"+settings.getMenuName()+"."+t.getName().replace(' ', '_');
                            Command transformationCommand = cmdService
                                    .getCommand(cmdID);
                            if (!transformationCommand.isDefined()) {
                                transformationCommand.define(t.getName(), "",
                                        kielerCategory);
                            }
                            transformationCommand
                                    .setHandler(new TransformationCommandHandler(settings, t));
                            CommandContributionItemParameter p = new CommandContributionItemParameter(PlatformUI.getWorkbench(),null,cmdID,CommandContributionItem.STYLE_PUSH);
                            CommandContributionItem cmd = new CommandContributionItem(p);
                            
                            dynamicMenu.add(cmd);

                        }
                        additions.addContributionItem(dynamicMenu, null);
                    }
                };
                menuService.addContributionFactory(menuContribution);
            }
        }
    }

    /*
     * (non-Javadoc)
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
    public static KSBasEPlugin getDefault() {
        return plugin;
    }

    /**
     * Returns an image descriptor for the image file at the given plug-in
     * relative path
     * 
     * @param path
     *            the path
     * @return the image descriptor
     */
    public static ImageDescriptor getImageDescriptor(String path) {
        return imageDescriptorFromPlugin(PLUGIN_ID, path);
    }
}
