package de.cau.cs.kieler.ksbase.ui.menus;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.commands.Category;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.core.expressions.EvaluationResult;
import org.eclipse.core.expressions.Expression;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.core.internal.expressions.CountExpression;
import org.eclipse.core.internal.expressions.InstanceofExpression;
import org.eclipse.core.internal.expressions.IterateExpression;
import org.eclipse.core.internal.expressions.WithExpression;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.bindings.TriggerSequence;
import org.eclipse.jface.bindings.keys.KeyBinding;
import org.eclipse.jface.bindings.keys.KeySequence;
import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.bindings.keys.ParseException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.keys.IBindingService;
import org.eclipse.ui.menus.AbstractContributionFactory;
import org.eclipse.ui.menus.CommandContributionItem;
import org.eclipse.ui.menus.CommandContributionItemParameter;
import org.eclipse.ui.menus.IContributionRoot;
import org.eclipse.ui.menus.IMenuService;
import org.eclipse.ui.services.IServiceLocator;

import de.cau.cs.kieler.ksbase.core.EditorTransformationSettings;
import de.cau.cs.kieler.ksbase.core.Transformation;
import de.cau.cs.kieler.ksbase.core.TransformationManager;
import de.cau.cs.kieler.ksbase.ui.handler.TransformationCommandHandler;
import de.cau.cs.kieler.ksbase.ui.handler.TransformationEditPolicyProvider;

public class DynamicMenuContributions {

    private final IMenuService menuService = (IMenuService) PlatformUI
            .getWorkbench().getService(IMenuService.class);
    private final IBindingService bindService = (IBindingService) PlatformUI
            .getWorkbench().getService(IBindingService.class);
    private final ICommandService cmdService = (ICommandService) PlatformUI
            .getWorkbench().getService(ICommandService.class);
    private final Category kielerCategory = cmdService
            .getCategory("de.cau.cs.kieler.ksbase.ui.ksbaseCategory");

    private final ISelectionService selectionService = (ISelectionService) PlatformUI.getWorkbench().getService(ISelectionService.class);
    
    // The currently registered contributions
    private HashMap<String, AbstractContributionFactory> registeredContributions;

    // Since we are using the same commands for multiple menus, we have to store
    // them
    private HashMap<String, LinkedList<CommandContributionItem>> editorCommands;

    public static DynamicMenuContributions instance = new DynamicMenuContributions();

    private DynamicMenuContributions() {
        registeredContributions = new HashMap<String, AbstractContributionFactory>();
        editorCommands = new HashMap<String, LinkedList<CommandContributionItem>>();
    }

    public void createUICommandsForEditor(
            final EditorTransformationSettings editor, IContributionRoot additions) {
        if (editorCommands.containsKey(editor.getEditor())) {
            System.out.println("editor exists, not regenerating stuff");
            // unregister all commands ? or just return ?
            // editorCommands.remove(editor.getEditor());
            // check transformations and commands and create new commands for
            // new transfs
        } else {
            LinkedList<CommandContributionItem> commands = new LinkedList<CommandContributionItem>();
            for (final Transformation t : editor.getTransformations()) {
                String cmdID = "de.cau.cs.kieler.ksbase.commands."
                        + editor.getMenuName() + "."
                        + t.getName().replace(' ', '_');
                Command transformationCommand = cmdService.getCommand(cmdID);
                if (!transformationCommand.isDefined()) {
                    transformationCommand.define(t.getName(), "",
                            kielerCategory);
                }
                    transformationCommand
                            .setHandler(new TransformationCommandHandler(
                                    editor, t));

                    CommandContributionItemParameter p = new CommandContributionItemParameter(
                            PlatformUI.getWorkbench(), null, cmdID, null,
                            null, null, null, t.getName(), t
                                    .getKeyboardShortcut(), t
                                    .getTransformationName(),
                            CommandContributionItem.STYLE_PUSH, null, true);
                    CommandContributionItem cmd = new CommandContributionItem(
                            p);

                    
                    Expression visibility = new Expression() {
                        
                        @Override
                        public EvaluationResult evaluate(IEvaluationContext context)
                                throws CoreException {
                            ISelection selection = selectionService.getSelection();
                            if ( selection.isEmpty() )
                                    return EvaluationResult.FALSE;
                            if (selection instanceof StructuredSelection) {
                                StructuredSelection s = (StructuredSelection)selection;
                                if (s.size() != t.getNumSelections()) {
                                    return EvaluationResult.FALSE;
                                }
                                //Check if selection.element instanceof part
                                for ( String part : t.getPartConfig()) {
                                    
                                }
                            }
                            return null;
                        }
                    };
                    
                    additions.registerVisibilityForChild(cmd,
                            visibility);
                    commands.add(cmd);
            }
            editorCommands.put(editor.getEditor(), commands);
        }

    }

    /**
     * Creates or modifies a menu contribution for one specific editor
     * 
     * @param editor
     */
    public void createMenuContributions(
            final EditorTransformationSettings editor) {
        
        // let's see if we already have this editor in our map.
        if (registeredContributions.containsKey(editor.getEditor())) {
            menuService.removeContributionFactory(registeredContributions
                    .get(editor.getEditor()));
            registeredContributions.remove(editor.getEditor());
        }
        
        AbstractContributionFactory editorContribution = new AbstractContributionFactory(
                editor.getMenuLocation(), "de.cau.cs.kieler") {

            @Override
            public void createContributionItems(IServiceLocator serviceLocator,
                    IContributionRoot additions) {
                MenuManager dynamicMenu = new MenuManager(editor.getMenuName(),
                        "de.cau.cs.kieler.ksbase.menu." + editor.getMenuName());
                
                //To be sure we have commands for this editor:
                createUICommandsForEditor(editor,additions);
                
                //Add commands to menu
                for (CommandContributionItem item : editorCommands.get(editor.getEditor()))
                    dynamicMenu.add(item);
                

                additions.addContributionItem(dynamicMenu, null);
            }
        };
        registeredContributions.put(editor.getEditor(), editorContribution);
        menuService.addContributionFactory(editorContribution);
    }

    public void removeContributionForEditor(
            final EditorTransformationSettings editor) {
        if (registeredContributions.containsKey(editor.getEditor())) {
            menuService.removeContributionFactory(registeredContributions
                    .get(editor.getEditor()));
            registeredContributions.remove(editor.getEditor());
        }
    }

    /**
     * Removes all contributions
     */
    public void clearContributions() {
        for (AbstractContributionFactory value : registeredContributions
                .values()) {
            menuService.removeContributionFactory(value);
        }
        registeredContributions.clear();
    }
}
