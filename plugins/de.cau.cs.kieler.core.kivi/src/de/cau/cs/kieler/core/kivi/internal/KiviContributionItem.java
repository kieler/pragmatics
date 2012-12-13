/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.core.kivi.internal;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.commands.Category;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.IParameter;
import org.eclipse.core.commands.State;
import org.eclipse.core.expressions.Expression;
import org.eclipse.core.internal.expressions.AndExpression;
import org.eclipse.core.internal.expressions.CompositeExpression;
import org.eclipse.core.internal.expressions.EqualsExpression;
import org.eclipse.core.internal.expressions.OrExpression;
import org.eclipse.core.internal.expressions.WithExpression;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.ui.actions.CompoundContributionItem;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.handlers.IHandlerActivation;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.handlers.RegistryToggleState;
import org.eclipse.ui.internal.menus.WorkbenchMenuService;
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
 * @author ckru
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
    private WorkbenchMenuService menuService;
    
    /**
     * The HandlerService to activate the handlers.
     */
    private IHandlerService handlerService;
    
    //private IContributionItem[] contributionItems = null;
    
    /**
     * Cached handler activations to avoid activating the same handler twice.
     */
    private static Map<String, IHandlerActivation> handlerActivations = 
            new HashMap<String, IHandlerActivation>();
    
    /**
     * Cached toggle states to make them persistent between menu construction cycles.
     */
    private Map<String, State> toggleStates = new HashMap<String, State>();
    
    /**
     * {@inheritDoc}
     */
    public void initialize(final IServiceLocator newServiceLocator) {
        this.serviceLocator = newServiceLocator;
        this.commandService = (ICommandService) serviceLocator.getService(ICommandService.class);
        this.menuService = (WorkbenchMenuService) serviceLocator.getService(IMenuService.class);
        this.evaluationService = (IEvaluationService) serviceLocator
                .getService(IEvaluationService.class);
        this.handlerService = (IHandlerService) serviceLocator.getService(IHandlerService.class);
        
        //contributionItems = makeContributionItems();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected IContributionItem[] getContributionItems() {
        KiVi.getInstance().initialize();
        System.out.println("getContributionItems");
        for (IHandlerActivation act: handlerActivations.values()) {
            handlerService.deactivateHandler(act);
        }
        handlerActivations.clear();
        
        return makeContributionItems();
    }

    /**
     * The main code to create a button. It reads the button configurations from the
     * KiviMenuContributionService and adds buttons accordingly.
     */
    private IContributionItem[] makeContributionItems() {
        List<IContributionItem> items = new LinkedList<IContributionItem>();
        
        List<ButtonConfiguration> buttonConfigurations = KiviMenuContributionService.INSTANCE
                .getButtonConfigurations();
        for (ButtonConfiguration config : buttonConfigurations) {
            IContributionItem item = null;
            
            if (config.isSeparator()) {
                Separator separator = new Separator();
                item = separator;
                separator.setId(config.getId());
                
                
                
            } else if (config.getResponsiveCombination().isActive()) {
                Command cmd = commandService.getCommand(config.getId());
                State state = toggleStates.get(config.getId());
                if (state == null) {
                    state = new RegistryToggleState();
                    toggleStates.put(config.getId(), state);
                }
                
                cmd.addState("org.eclipse.ui.commands.toggleState", state);
                Category category = commandService.getCategory("de.cau.cs.kieler");
                IParameter[] params = {};
                cmd.define(config.getLabel(), null, category, params);
                // define a Handler for the command
                ButtonHandler buttonHandler = new ButtonHandler();
                cmd.setHandler(buttonHandler);
                buttonHandler.setId(cmd.getId());
                IHandlerActivation handlerActivation = 
                        handlerService.activateHandler(cmd.getId(), buttonHandler);
                handlerActivations.put(config.getId(), handlerActivation);
                CommandContributionItemParameter parameter = new CommandContributionItemParameter(
                        serviceLocator, config.getId(), config.getId(),
                        new HashMap<String, String>(), config.getIcon(), null, null,
                        config.getLabel(), null, config.getTooltip(), config.getStyle(), null,
                        false);
                
                item = new CommandContributionItem(parameter);
                
            }
            
            Expression visibilityExpression = getVisibilityExpression(config);
            if (visibilityExpression != null) {
                menuService.registerVisibleWhen(item, visibilityExpression, null, null);
                
                ContributionItemUpdater listener = new ContributionItemUpdater(item);
                evaluationService.addEvaluationListener(
                        visibilityExpression, listener, "visibilityExpression");
                
            }
            
            items.add(item);
            
        }
        
        return items.toArray(new IContributionItem[items.size()]);
    }
    
    /**
     * Creates a fitting visibility expression for the menucontribution described by config.
     * @param config the description of a menu contribution.
     * @return the visibility expression
     */
    private Expression getVisibilityExpression(final ButtonConfiguration config) {
        Expression visibilityExpression = null;
        
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
        
        return visibilityExpression;
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
         ButtonHandler handler = (ButtonHandler) handlerActivations.get(buttonID).getHandler();
         if (handler != null) {
             handler.setEnabled(enabled);
         }
    }
    
    private class ContributionItemUpdater implements IPropertyChangeListener {

        private boolean visible = true;

        private IContributionItem item;

        /**
         * 
         * @param item
         */
        public ContributionItemUpdater(final IContributionItem item) {
            this.item = item;
        }

        /**
         * 
         * {@inheritDoc}
         */
        public void propertyChange(final PropertyChangeEvent event) {
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
}
