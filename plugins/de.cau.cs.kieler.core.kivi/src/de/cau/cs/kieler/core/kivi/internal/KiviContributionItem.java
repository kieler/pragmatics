/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.core.kivi.internal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.commands.Category;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.IParameter;
import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.core.expressions.Expression;
import org.eclipse.core.internal.expressions.AndExpression;
import org.eclipse.core.internal.expressions.CompositeExpression;
import org.eclipse.core.internal.expressions.EqualsExpression;
import org.eclipse.core.internal.expressions.OrExpression;
import org.eclipse.core.internal.expressions.WithExpression;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.bindings.Binding;
import org.eclipse.jface.bindings.keys.KeyBinding;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.ui.actions.CompoundContributionItem;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.internal.Workbench;
import org.eclipse.ui.internal.keys.BindingService;
import org.eclipse.ui.internal.menus.InternalMenuService;
import org.eclipse.ui.keys.IBindingService;
import org.eclipse.ui.menus.CommandContributionItem;
import org.eclipse.ui.menus.CommandContributionItemParameter;
import org.eclipse.ui.menus.IMenuService;
import org.eclipse.ui.menus.IWorkbenchContribution;
import org.eclipse.ui.services.IEvaluationService;
import org.eclipse.ui.services.IServiceLocator;

import de.cau.cs.kieler.core.kivi.KiVi;
import de.cau.cs.kieler.core.kivi.menu.ButtonHandler;
import de.cau.cs.kieler.core.kivi.menu.KiviMenuContributionService;
import de.cau.cs.kieler.core.kivi.menu.KiviMenuContributionService.ButtonConfiguration;
import de.cau.cs.kieler.core.kivi.menu.KiviMenuContributionService.LocationScheme;

/**
 * Dynamic toolbar contribution for the use with KIELER View Management. It extends the idea of
 * CompoundContributionItems, which are only defined for menus, to toolbars. Hence, the toolbar is
 * filled dynamically with buttons, which only get updated at the ToolbarManager updates, which are
 * not in control of the user. Hence, newly inserted buttons or visibility updates might be delayed
 * until the next ToolbarManager update, which is for example at a change to a different kind of
 * editor or the change of the perspective.
 * 
 * All visibility management is implemented using the standard core expressions of eclipse that are
 * also used by the org.eclipse.ui.menu extension point. However, the core expressions API is not
 * publicly available due to restrictions in the corresponding org.eclipse.core.expressions plug-in
 * and therefore result in compiler warnings.
 * 
 * @author haf
 * 
 */
@SuppressWarnings("restriction")
public class KiviContributionItem extends CompoundContributionItem implements
        IWorkbenchContribution {

    /**
     * A ServiceLocator that is given to this class from outside at initialization. It is used to
     * find command- and menu services and is required by the ContributionItemParameter
     */
    private IServiceLocator serviceLocator;

    /**
     * The CommandService gives access to the Command API of Eclipse and allows to programmatically
     * create new commands.
     */
    private ICommandService commandService;

    /**
     * The Evaluation service allows to manually trigger evaluation of visibility expressions used
     * by the Command Framework to control visibility of UI contributions. We need to trigger this
     * immediately after we created a new such expression in order to not delay the correct
     * visibility because the expressions are false in the beginning.
     */
    private IEvaluationService evaluationService;

    /**
     * The MenuService gives access to some menu management functionality. Here it is used to
     * register the enablement expressions for menu-entries. The MenuService gives access to some
     * menu management functionality. Here it is used to register the enablement expressions for
     * menu-entries.
     */
    /*
     * haf: need to have access to the registerVisibleWhen method currently defined in
     * InternalMenuService. Its class documentation says that these methods may move into the public
     * IMenuService interface API some day. Then this can be changed here also into IMenuService.
     * This will remove the warning about discouraged access due to restrictions on internal menu
     * code.
     */
    private InternalMenuService menuService;

    // dunno how to get the formatter to make a linebreak here
    // CHECKSTYLEOFF MaximumLineLength
    private static Map<String, IContributionItem> idButtonMap
            = new HashMap<String, IContributionItem>();
    private static Map<IContributionItem, ButtonHandler> buttonsHandlerMap
            = new HashMap<IContributionItem, ButtonHandler>();
    private static Map<IContributionItem, ButtonHandler> oldButtonsHandlerMap;
    private static List<IContributionItem> buttons = new ArrayList<IContributionItem>();

    // CHECKSTYLEON MaximumLineLength

    /**
     * 
     * @author chsch
     */
    private enum InternalLocationScheme {
        MENU,
        POPUP,
        TOOLBAR;
        
        public boolean isContainedIn(final LocationScheme theLocation) {
            switch (this) {
            case MENU:
                return theLocation.equals(LocationScheme.MENU)
                        || theLocation.equals(LocationScheme.MENU_POPUP)
                        || theLocation.equals(LocationScheme.MENU_TOOLBAR)
                        || theLocation.equals(LocationScheme.MENU_POPUP_TOOLBAR);
            case POPUP:
                return theLocation.equals(LocationScheme.POPUP)
                        || theLocation.equals(LocationScheme.MENU_POPUP)
                        || theLocation.equals(LocationScheme.POPUP_TOOLBAR)
                        || theLocation.equals(LocationScheme.MENU_POPUP_TOOLBAR);
            case TOOLBAR:
                return theLocation.equals(LocationScheme.TOOLBAR)
                        || theLocation.equals(LocationScheme.MENU_TOOLBAR)
                        || theLocation.equals(LocationScheme.POPUP_TOOLBAR)
                        || theLocation.equals(LocationScheme.MENU_POPUP_TOOLBAR);
            default:
                return true;
            }
        }
    }
    
    private InternalLocationScheme location;
    
    /**
     * {@inheritDoc}
     */
    public void initialize(final IServiceLocator theServiceLocator) {
        this.serviceLocator = theServiceLocator;
        this.commandService = (ICommandService) serviceLocator.getService(ICommandService.class);
        this.menuService = (InternalMenuService) serviceLocator.getService(IMenuService.class);
        this.evaluationService = (IEvaluationService) serviceLocator
                .getService(IEvaluationService.class);
        
        if (this.getId().endsWith("menu")) {
            this.location = InternalLocationScheme.MENU;
        } else if (this.getId().endsWith("popup")) {
            this.location = InternalLocationScheme.POPUP;
        } else if (this.getId().endsWith("toolbar")) {
            this.location = InternalLocationScheme.TOOLBAR;
        }
    }

    /**
     * The main code to create a button. It reads the button configurations from the
     * KiviMenuContributionService and adds buttons accordingly. {@inheritDoc}
     */
    @Override
    protected IContributionItem[] getContributionItems() {

        // make sure to initialize KiVi so all MenuContributions from the combinations are made.
        KiVi.getInstance().initialize();

        /*
         * haf: Tried to add a lot of caching for ContributionItems and Commands, but this seems
         * very problematic with the Eclipse Command framework. Concrete problems: - old Commands
         * may get "undefinded" randomly between calls of getContributionItems - old
         * CommandContributionItems sometimes keep their "widget", sometimes it gets reset to null.
         * Only if it is null, the fill method (see below) will actually do something. So sometimes
         * the widget seems to be not correctly reset Therefore for now, any caching is removed,
         * which also requires to clear the local memories.
         */
        buttons.clear();
        oldButtonsHandlerMap = buttonsHandlerMap;
        buttonsHandlerMap = new HashMap<IContributionItem, ButtonHandler>();

        List<ButtonConfiguration> buttonConfigurations = KiviMenuContributionService.INSTANCE
                .getButtonConfigurations();
        for (ButtonConfiguration config : buttonConfigurations) {

            // only create a button if the corresponding combination is active
            if (config.isSeparator()) {
                if (this.location.isContainedIn(config.getLocationSchemeExpression())) {
                    //unload(config.getId());
                    Separator separator = new Separator();
                    
                    separator.setId(config.getId());
                    idButtonMap.put(config.getId(), separator);
                    buttonsHandlerMap.put(separator, new ButtonHandler());
                    buttons.add(separator);
                    
                    Expression visibilityExpression = null;
                    // specify visibility for active editors
                    if (config.getActiveEditors() != null && config.getActiveEditors().length > 0) {
                        CompositeExpression or = new OrExpression();
                        visibilityExpression = or;
                        for (String editorId : config.getActiveEditors()) {
                            CompositeExpression with = new WithExpression("activeEditorId");
                            Expression equals = new EqualsExpression(editorId);
                            with.add(equals);
                            or.add(with);
                        }
                    }
                    // specify visibility for a given core expression
                    if (config.getVisibilityExpression() != null) {
                        if (visibilityExpression == null) {
                            visibilityExpression = config.getVisibilityExpression();
                        } else {
                            // there are some active editor specifications already
                            CompositeExpression and = new AndExpression();
                            and.add(visibilityExpression);
                            and.add(config.getVisibilityExpression());
                            visibilityExpression = and;
                        }
                    }
                    if (visibilityExpression != null) {
                        menuService.registerVisibleWhen(separator, visibilityExpression, null, null);
                    }
                    
                }
            } else {
                
            if (config.getResponsiveCombination().isActive()
                    && this.location.isContainedIn(config.getLocationSchemeExpression())) {
                
                
                // get a command and register the Kivi ButtonHandler for it
                Command cmd = commandService.getCommand(config.getId());
                Category category = commandService.getCategory("de.cau.cs.kieler");
                IParameter[] params = {};
                cmd.define(config.getLabel(), null, category, params);
                // define a Handler for the command
                ButtonHandler buttonHandler = new ButtonHandler();
                cmd.setHandler(buttonHandler);

                //System.out.println("Created command " + cmd.getId() + " " + cmd.isDefined());
                // now specify the button
                CommandContributionItemParameter parameter = new CommandContributionItemParameter(
                        serviceLocator, config.getId(), config.getId(),
                        new HashMap<String, String>(), config.getIcon(), null, null,
                        config.getLabel(), null, config.getTooltip(), config.getStyle(), null,
                        false);
                // this is the button
                IContributionItem item;
                item = new CommandContributionItem(parameter);
                
                //bind keysequence to command
                if (config.getKeySequence() != null) {
                    BindingService bindingService = 
                            (BindingService) Workbench.getInstance().getService(IBindingService.class);
                    ParameterizedCommand pc = ((CommandContributionItem) item).getCommand();
                    bindingService.addBinding(new KeyBinding(config.getKeySequence(), pc, bindingService.getActiveScheme().getId(), Workbench.getInstance().getContextSupport().CONTEXT_ID_WINDOW, null, null, null, Binding.USER));
                }
                
                //deactivate the old button if it exists
                unload(config.getId());
                // remember some relations between button, its handler and the
                // corresponding configuration
                idButtonMap.put(config.getId(), item);
                buttonsHandlerMap.put(item, buttonHandler);
                buttons.add(item);

                // specify visibility
                Expression visibilityExpression = null;
                // specify visibility for active editors
                if (config.getActiveEditors() != null && config.getActiveEditors().length > 0) {
                    CompositeExpression or = new OrExpression();
                    visibilityExpression = or;
                    for (String editorId : config.getActiveEditors()) {
                        CompositeExpression with = new WithExpression("activeEditorId");
                        Expression equals = new EqualsExpression(editorId);
                        with.add(equals);
                        or.add(with);
                    }
                }
                // specify visibility for a given core expression
                if (config.getVisibilityExpression() != null) {
                    if (visibilityExpression == null) {
                        visibilityExpression = config.getVisibilityExpression();
                    } else {
                        // there are some active editor specifications already
                        CompositeExpression and = new AndExpression();
                        and.add(visibilityExpression);
                        and.add(config.getVisibilityExpression());
                        visibilityExpression = and;
                    }
                }
                if (visibilityExpression != null) {
                    menuService.registerVisibleWhen(item, visibilityExpression, null, null);
                }
                
                // if (!config.getResponsiveCombination().isActive()) {
                // item.setVisible(false);
                // } else {
                // item.setVisible(true);
                // }
            }
        }
        }
        // request evaluation of all visibility expressions registered for a certain
        // variable. This must be done to show buttons also from the beginning,
        // because
        // expressions are always false in the beginning
        if (evaluationService != null) {
            evaluationService.requestEvaluation("activeEditorId");
        }
        // return list of menu contributions in the correct order
        return buttons.toArray(new IContributionItem[buttonsHandlerMap.size()]);
    }

    /**
     * Set the enabled state of a menu contribution handler associated with the given ID. This state
     * is used by corresponding menu contributions (buttons, menu entries, etc.) to determine the
     * enabled state of that menu item, e.g. whether a button should be grayed out or not.
     * 
     * @author haf
     * @param buttonID
     *            the id associated in KiVi with the menu item
     * @param enabled
     *            true if the handler is enabled.
     */
    public static void setEnabledState(final String buttonID, final boolean enabled) {
        IContributionItem item = idButtonMap.get(buttonID);
        if (item != null) {
            ButtonHandler handler = buttonsHandlerMap.get(item);
            if (handler != null) {
                handler.setEnabled(enabled);
            }
        }
    }
    
    /**
     * Unload old buttons, i.e. send a not-pushed trigger if it was pushed before.
     * 
     * @param buttonID the button identifier
     */
    private static void unload(final String buttonID) {
        IContributionItem item = idButtonMap.get(buttonID);
        if (item != null) {
            ButtonHandler handler = oldButtonsHandlerMap.get(item);
            if (handler != null) {
                handler.unload();
            }
        }
    }

    /**
     * haf: simply copied the code from the CompoundContributionItem. This also supports the
     * compound idea for the Toolbar and not only for menus. {@inheritDoc}
     */
    @Override
    public void fill(final ToolBar parent, final int index) {
        int myIndex = index;
        if (myIndex == -1) {
            myIndex = parent.getItemCount();
        }
        IContributionItem[] items = getContributionItems();
        for (int i = 0; i < items.length; i++) {
            IContributionItem item = items[i];
            int oldItemCount = parent.getItemCount();
            if (item.isVisible()) {
                item.fill(parent, myIndex);
            }
            int newItemCount = parent.getItemCount();
            int numAdded = newItemCount - oldItemCount;
            myIndex += numAdded;
        }
    }

}
