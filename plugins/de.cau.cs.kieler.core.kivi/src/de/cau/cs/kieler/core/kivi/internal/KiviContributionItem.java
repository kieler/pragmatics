package de.cau.cs.kieler.core.kivi.internal;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IContributionManager;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.ui.actions.CompoundContributionItem;
import org.eclipse.ui.menus.CommandContributionItem;
import org.eclipse.ui.menus.CommandContributionItemParameter;
import org.eclipse.ui.menus.IWorkbenchContribution;
import org.eclipse.ui.services.IServiceLocator;

import de.cau.cs.kieler.core.kivi.KiViPlugin;

/**
 * not yet working dynamic toolbar contribution.
 * @author haf
 *
 */
public class KiviContributionItem extends CompoundContributionItem implements IWorkbenchContribution {

    IServiceLocator serviceLocator;
    private boolean dirty = true;
    
    private IMenuListener menuListener = new IMenuListener() {
        public void menuAboutToShow(IMenuManager manager) {
            manager.markDirty();
            dirty = true;
        }
    };
    
    /**
     * {@inheritDoc}
     */
    public void initialize(IServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    protected IContributionItem[] getContributionItems() {
        
        System.out.println("KiviContributionItem.getContributionItems()");
        
        Map<String,String> parameters = new HashMap<String,String>();
        ImageDescriptor icon = KiViPlugin.imageDescriptorFromPlugin(KiViPlugin.PLUGIN_ID, "icons/runIcon.png");
        String label = "Test Button";
        String mnemonic = null;
        String tooltip = "My Tooltip is great!";
        int style = SWT.PUSH;
        String helpContextId = null;
        boolean visibleEnabled = true;
        // FIXME: there is no Command yet defined for that Command ID. However, dunno how
        // to instantiate a Command programmatically. How do I obtain the CommandManager here?
        CommandContributionItemParameter parameter = new CommandContributionItemParameter(serviceLocator, "de.cau.cs.kieler.kivi.command", "de.cau.cs.kieler.kivi.command", parameters, icon, icon, icon, label, mnemonic, tooltip, style, helpContextId, visibleEnabled);
        
        CommandContributionItem item = new CommandContributionItem(parameter);
        item.setVisible(true);
        
        IContributionItem[] items = {item};
        return items;
    }

  
    /**
     * (haf) simply copied the code from the CompoundContributionItem
     * {@inheritDoc}
     */
    @Override
    public void fill(ToolBar parent, int index) {
        /* (non-Javadoc)
         * @see org.eclipse.jface.action.ContributionItem#fill(org.eclipse.swt.widgets.Menu, int)
         */
            if (index == -1) {
                            index = parent.getItemCount();
                    }
            IContributionItem[] items = getContributionItems();
            for (int i = 0; i < items.length; i++) {
                IContributionItem item = items[i];
                int oldItemCount = parent.getItemCount();
                if (item.isVisible()) {
                    item.fill(parent, index);
                }
                int newItemCount = parent.getItemCount();
                int numAdded = newItemCount - oldItemCount;
                index += numAdded;
            }
            dirty = false;
    }
    
    /**
     * {@inheritDoc}
     */
    /*
     * FIXME: (haf) this is strange. The ToolBarManager has no menu listener
     *        API. What to do here?
     */
    @Override
    public void setParent(IContributionManager parent) {
        if (getParent() instanceof IToolBarManager) {
            IToolBarManager oldMgr = (IToolBarManager) getParent();
            //oldMgr.remove(this);
        }
        if (parent instanceof IToolBarManager) {
            IToolBarManager newMgr = (IToolBarManager) parent;
            //newMgr.add(this);
        }
        super.setParent(parent);
    }
}
