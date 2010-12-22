package de.cau.cs.kieler.core.kivi.internal;


import org.eclipse.swt.SWT;
import org.eclipse.ui.menus.CommandContributionItem;
import org.eclipse.ui.menus.CommandContributionItemParameter;
import org.eclipse.ui.menus.ExtensionContributionFactory;
import org.eclipse.ui.menus.IContributionRoot;
import org.eclipse.ui.services.IServiceLocator;

public class KiViButtonContributionFactory extends ExtensionContributionFactory {

    public KiViButtonContributionFactory() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void createContributionItems(IServiceLocator serviceLocator, IContributionRoot additions) {
        System.out.println("KiViButtonContributionFactory createContributionItems");
        CommandContributionItemParameter p = new CommandContributionItemParameter(serviceLocator,"","org.eclipse.ui.file.exit",SWT.PUSH);
        p.label = "Exit test";
        p.tooltip = "Hallo";
        //Command
        CommandContributionItem item = new CommandContributionItem(p);
        item.setVisible(true);
        additions.addContributionItem(item, null); 
    }

}
