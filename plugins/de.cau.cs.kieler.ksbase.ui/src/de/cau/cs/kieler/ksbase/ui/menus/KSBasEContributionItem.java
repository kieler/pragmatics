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
package de.cau.cs.kieler.ksbase.ui.menus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.commands.Category;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.IParameter;
import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.core.expressions.Expression;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.bindings.Binding;
import org.eclipse.jface.bindings.keys.KeyBinding;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.ui.actions.CompoundContributionItem;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.contexts.IContextService;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.internal.Workbench;
import org.eclipse.ui.internal.keys.BindingService;
import org.eclipse.ui.keys.IBindingService;
import org.eclipse.ui.menus.CommandContributionItem;
import org.eclipse.ui.menus.CommandContributionItemParameter;
import org.eclipse.ui.menus.IWorkbenchContribution;
import org.eclipse.ui.services.IEvaluationReference;
import org.eclipse.ui.services.IEvaluationService;
import org.eclipse.ui.services.IServiceLocator;

import de.cau.cs.kieler.core.kivi.KiVi;
import de.cau.cs.kieler.core.kivi.menu.ButtonHandler;
import de.cau.cs.kieler.ksbase.ui.menus.KSBasEMenuContributionService.ButtonConfiguration;
import de.cau.cs.kieler.ksbase.ui.menus.KSBasEMenuContributionService.LocationScheme;

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
public class KSBasEContributionItem extends CompoundContributionItem implements
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
    //private WorkbenchMenuService menuService;
    //private SlaveMenuService slaveMenuService;

    // dunno how to get the formatter to make a linebreak here
    // CHECKSTYLEOFF MaximumLineLength
    private static Map<String, IContributionItem> idButtonMap = new HashMap<String, IContributionItem>();
    private static Map<IContributionItem, ButtonHandler> buttonsHandlerMap = new HashMap<IContributionItem, ButtonHandler>();
    private static Map<IContributionItem, ButtonHandler> oldButtonsHandlerMap;
    private static List<IContributionItem> buttons = new ArrayList<IContributionItem>();
    private static Map<String, KeyBinding> bindings = new HashMap<String, KeyBinding>();
    private static Map<IContributionItem, IEvaluationReference> visibilities = new HashMap<IContributionItem, IEvaluationReference>();
    // CHECKSTYLEON MaximumLineLength

    private IHandlerService handlerService;

    /**
     * 
     * @author chsch
     */
    private enum InternalLocationScheme {
        MENU, POPUP, TOOLBAR,
        // chsch: insertion due to KIELER-2281
        TEMPLATES;

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
            // chsch: insertion due to KIELER-2281
            case TEMPLATES:
                return theLocation.equals(LocationScheme.TEMPLATES_MENU);
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
        
        //this.menuService = (WorkbenchMenuService) serviceLocator.getService(IMenuService.class);
        this.evaluationService = (IEvaluationService) serviceLocator
                .getService(IEvaluationService.class);
        this.handlerService = (IHandlerService) serviceLocator.getService(IHandlerService.class);
        if (this.getId().endsWith("menu")) {
            this.location = InternalLocationScheme.MENU;
        } else if (this.getId().endsWith("popup")) {
            this.location = InternalLocationScheme.POPUP;
        } else if (this.getId().endsWith("toolbar")) {
            this.location = InternalLocationScheme.TOOLBAR;
        // chsch: insertion due to KIELER-2281
        } else if (this.getId().endsWith("templates")) {
            this.location = InternalLocationScheme.TEMPLATES;
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

        List<ButtonConfiguration> buttonConfigurations = KSBasEMenuContributionService.INSTANCE
                .getButtonConfigurations();
        for (ButtonConfiguration config : buttonConfigurations) {

            // only create a button if the corresponding combination is active
            if (config.isSeparator()) {
                if (this.location.isContainedIn(config.getLocationSchemeExpression())) {
                    // unload(config.getId());
                    Separator separator = new Separator();

                    separator.setId(config.getId());
                    idButtonMap.put(config.getId(), separator);
                    buttonsHandlerMap.put(separator, new ButtonHandler());
                    buttons.add(separator);

                    Expression visibilityExpression = null;
                    // specify visibility for active editors
                    /*
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
                    */
                    // specify visibility for a given core expression
                    if (config.getVisibilityExpression() != null) {
                        if (visibilityExpression == null) {
                            visibilityExpression = config.getVisibilityExpression();
                        } /* else {
                            // there are some active editor specifications already
                            CompositeExpression and = new AndExpression();
                            and.add(visibilityExpression);
                            and.add(config.getVisibilityExpression());
                            visibilityExpression = and;
                        }
                        */
                    }
                    if (visibilityExpression != null) {
                        //menuService.registerVisibleWhen(separator, visibilityExpression, null, null);
                        
                        if (!visibilities.containsKey(separator)) {
                            ContributionItemUpdater listener = new ContributionItemUpdater(
                                    separator);
                            IEvaluationReference ref = evaluationService.addEvaluationListener(
                                    visibilityExpression, listener, "visibilityExpression");
                            visibilities.put(separator, ref);
                        }
                        
                    }

                }
            } else {

                if (config.getResponsiveCombination().isActive()
                        && this.location.isContainedIn(config.getLocationSchemeExpression())) {

                    // get a command and register the Kivi ButtonHandler for it
                    Command cmd = commandService.getCommand(config.getId());
                    Category category = commandService.getCategory("de.cau.cs.kieler.ksbase.ui.ksbaseCategory");
                    IParameter[] params = {};
                    cmd.define(config.getLabel(), null, category, params);
                    // define a Handler for the command
                    ButtonHandler buttonHandler = new ButtonHandler();
                    cmd.setHandler(buttonHandler);
                    buttonHandler.setId(cmd.getId());
                    handlerService.activateHandler(cmd.getId(), buttonHandler);
                    // System.out.println("Created command " + cmd.getId() + " " + cmd.isDefined());
                    // now specify the button
                    CommandContributionItemParameter parameter = new CommandContributionItemParameter(
                            serviceLocator, config.getId(), config.getId(),
                            new HashMap<String, String>(), config.getIcon(), null, null,
                            config.getLabel(), null, config.getTooltip(), config.getStyle(), null,
                            false);
                    // this is the button
                    IContributionItem item;
                    item = new CommandContributionItem(parameter);

                    // bind keysequence to command
                    if (config.getKeySequence() != null) {
                        BindingService bindingService = (BindingService) Workbench.getInstance()
                                .getService(IBindingService.class);
                        ParameterizedCommand pc = ((CommandContributionItem) item).getCommand();
                        if (config.getShortcutContext() == null) {
                            // TriggerSequence[] oldBindings =
                            // bindingService.getActiveBindingsFor(pc);
                            // if (oldBindings != null && oldBindings.length == 0) {
                            if (bindingService.getConflictsFor(config.getKeySequence()) == null
                                    && !bindings.containsKey(item.getId())) {
                                KeyBinding binding = new KeyBinding(config.getKeySequence(), pc,
                                        bindingService.getActiveScheme().getId(),
                                        IContextService.CONTEXT_ID_WINDOW, null, null, null,
                                        Binding.USER);
                                bindingService.addBinding(binding);
                                bindings.put(item.getId(), binding);
                            }
                        } else {
                            // TriggerSequence[] oldBindings =
                            // bindingService.getActiveBindingsFor(pc);
                            // if (oldBindings != null && oldBindings.length == 0) {
                            if (bindingService.getConflictsFor(config.getKeySequence()) == null
                                    && !bindings.containsKey(item.getId())) {
                                KeyBinding binding = new KeyBinding(config.getKeySequence(), pc,
                                        bindingService.getActiveScheme().getId(),
                                        config.getShortcutContext(), null, null, null, Binding.USER);
                                bindingService.addBinding(binding);
                                bindings.put(item.getId(), binding);
                            }
                        }
                    }

                    // deactivate the old button if it exists
                    unload(config.getId());
                    // remember some relations between button, its handler and the
                    // corresponding configuration
                    idButtonMap.put(config.getId(), item);
                    buttonsHandlerMap.put(item, buttonHandler);
                    buttons.add(item);

                    // specify visibility
                    Expression visibilityExpression = null;
                    // specify visibility for active editors
                    /*
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
                    */
                    // specify visibility for a given core expression
                    if (config.getVisibilityExpression() != null) {
                        if (visibilityExpression == null) {
                            visibilityExpression = config.getVisibilityExpression();
                        } /*else {
                            // there are some active editor specifications already
                            CompositeExpression and = new AndExpression();
                            and.add(visibilityExpression);
                            and.add(config.getVisibilityExpression());
                            visibilityExpression = and;
                        }
                        */
                    }
                    if (visibilityExpression != null) {
                        //menuService.registerVisibleWhen(item, visibilityExpression, null, null);
                        
                        if (!visibilities.containsKey(item)) {
                            ContributionItemUpdater listener = new ContributionItemUpdater(item);
                            IEvaluationReference ref = evaluationService.addEvaluationListener(
                                    visibilityExpression, listener, "visibilityExpression");
                            visibilities.put(item, ref);
                        }
                        

                    }

                }
            }
        }
        // request evaluation of all visibility expressions registered for a certain
        // variable. This must be done to show buttons also from the beginning,
        // because
        // expressions are always false in the beginning
        /*
        if (evaluationService != null) {
            evaluationService.requestEvaluation("activeEditorId");
        }
        */
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
     * @param buttonID
     *            the button identifier
     */
    private static void unload(final String buttonID) {
        if (!softUpdate) {
            IContributionItem item = idButtonMap.get(buttonID);
            if (item != null) {
                ButtonHandler handler = oldButtonsHandlerMap.get(item);
                if (handler != null) {
                    handler.unload();
                }
            }
        }
    }

    /**
     * This method has been copied from {@link org.eclipse.ui.actions.CompoundContributionItem}.
     * This also supports the compound idea for the Toolbar and not only for menus.
     * {@inheritDoc}
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
                try {
                    item.fill(parent, myIndex);
                } catch (java.lang.RuntimeException e) {
                    System.out.println(e.getMessage());
                }
            }
            int newItemCount = parent.getItemCount();
            int numAdded = newItemCount - oldItemCount;
            myIndex += numAdded;
        }
    }

    private static boolean softUpdate = false;

    private class ContributionItemUpdater implements IPropertyChangeListener {

        private boolean visible = true;

        private IContributionItem item;

        public ContributionItemUpdater(IContributionItem item) {
            this.item = item;
        }

        public void propertyChange(PropertyChangeEvent event) {
            if (event.getProperty() == "visibilityExpression") {
                if (event.getNewValue() != null
                        && ((Boolean) event.getNewValue()).booleanValue() != visible) {
                    this.visible = ((Boolean) event.getNewValue()).booleanValue();
                    item.setVisible(visible);
                    /*
                     * IContributionManager parent = null; if (item instanceof ContributionItem) {
                     * parent = ((ContributionItem) item).getParent();
                     * 
                     * } else if (item instanceof MenuManager) { parent = ((MenuManager)
                     * item).getParent(); } if (parent != null) { parent.markDirty();
                     * //managersAwaitingUpdates.add(parent); }
                     */
                }
            }

        }
    }

    /**
     * Activate or deactivate soft update.
     * 
     * @param softUpdate
     *            whether soft update is active
     */
    public static void setSoftUpdate(final boolean softUpdate) {
        KSBasEContributionItem.softUpdate = softUpdate;
    }

}
