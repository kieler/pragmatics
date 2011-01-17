package de.cau.cs.kieler.core.kivi.internal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.core.commands.Category;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.IParameter;
import org.eclipse.core.expressions.Expression;
import org.eclipse.core.internal.expressions.AndExpression;
import org.eclipse.core.internal.expressions.CompositeExpression;
import org.eclipse.core.internal.expressions.EqualsExpression;
import org.eclipse.core.internal.expressions.OrExpression;
import org.eclipse.core.internal.expressions.WithExpression;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.ui.actions.CompoundContributionItem;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.internal.menus.InternalMenuService;
import org.eclipse.ui.menus.CommandContributionItem;
import org.eclipse.ui.menus.CommandContributionItemParameter;
import org.eclipse.ui.menus.IMenuService;
import org.eclipse.ui.menus.IWorkbenchContribution;
import org.eclipse.ui.services.IServiceLocator;

import de.cau.cs.kieler.core.kivi.triggers.ButtonTrigger;
import de.cau.cs.kieler.core.kivi.triggers.KiviMenuContributionService;
import de.cau.cs.kieler.core.kivi.triggers.KiviMenuContributionService.ButtonConfiguration;

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
     * The MenuService gives access to some menu management functionality. Here it is used to
     * register the enablement expressions for menu-entries.
     */
    /*
     * haf: need to have access to the registerVisibleWhen method currently defined in
     * InternalMenuService. Its class documentation says that these methods may move into the public
     * IMenuService interface API some day. Then this can be changed here also into IMenuService.
     * This will remove the warning about discouraged access due to restrictions on internal menu
     * code.
     */
    private InternalMenuService menuService;

    private List<IContributionItem> buttons = new ArrayList<IContributionItem>();

    /**
     * {@inheritDoc}
     */
    public void initialize(final IServiceLocator theServiceLocator) {
        this.serviceLocator = theServiceLocator;
        this.commandService = (ICommandService) serviceLocator.getService(ICommandService.class);
        this.menuService = (InternalMenuService) serviceLocator.getService(IMenuService.class);
    }

    /**
     * The main code to create a button. It reads the button configurations from the
     * KiviMenuContributionService and adds buttons accordingly. {@inheritDoc}
     */
    @Override
    protected IContributionItem[] getContributionItems() {

        System.out.println("KiviContributionItem.getContributionItems()");

        // every Toolbar update re-create all buttons.
        // TODO: caching might be more efficient
        buttons.clear();
        List<ButtonConfiguration> buttonConfigurations = KiviMenuContributionService.INSTANCE
                .getButtonConfigurations();
        for (ButtonConfiguration config : buttonConfigurations) {
            // only show button, if the corresponding KiVi combination is actually activated
            if (config.getResponsiveCombination().isActive()) {
                if (commandService != null) {
                    // get a command and register the Kivi ButtonHandler for it
                    Command cmd = commandService.getCommand(config.getId());
                    Category category = commandService.getCategory("de.cau.cs.kieler");
                    IParameter[] params = {};
                    cmd.define(config.getLabel(), null, category, params);
                    cmd.setHandler(new ButtonTrigger.ButtonHandler());
                }
                // now specify the button
                CommandContributionItemParameter parameter = new CommandContributionItemParameter(
                        serviceLocator, config.getId(), config.getId(),
                        new HashMap<String, String>(), config.getIcon(), null, null,
                        config.getLabel(), null, config.getTooltip(), config.getStyle(), null, false);
                // this is the button
                CommandContributionItem item = new CommandContributionItem(parameter);
                item.setVisible(true);
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
                    }
                }
                if (visibilityExpression != null) {
                    menuService.registerVisibleWhen(item, visibilityExpression, null, null);
                }
            }
        }

        return (IContributionItem[]) buttons.toArray(new IContributionItem[buttons.size()]);
    }

    /**
     * (haf) simply copied the code from the CompoundContributionItem.
     *  {@inheritDoc}
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
