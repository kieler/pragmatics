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
package de.cau.cs.kieler.ksbase.ui.menus;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import org.eclipse.core.commands.Category;
import org.eclipse.core.commands.Command;
import org.eclipse.core.expressions.EvaluationResult;
import org.eclipse.core.expressions.Expression;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
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

public class DynamicMenuContributions {

    private final IMenuService menuService = (IMenuService) PlatformUI
            .getWorkbench().getService(IMenuService.class);
    private final IBindingService bindService = (IBindingService) PlatformUI
            .getWorkbench().getService(IBindingService.class);
    private final ICommandService cmdService = (ICommandService) PlatformUI
            .getWorkbench().getService(ICommandService.class);
    private final Category kielerCategory = cmdService
            .getCategory("de.cau.cs.kieler.ksbase.ui.ksbaseCategory");

    // The currently registered contributions
    private HashMap<String, AbstractContributionFactory> registeredContributions;

    // Since we are using the same commands for multiple menus, we have to store
    // them
    private HashMap<String, LinkedList<CommandContributionItem>> editorCommands;

    public static DynamicMenuContributions instance = new DynamicMenuContributions();

    private class CheckPartExpression extends Expression {
        
        String[] parts;
        int numSelections;
        
        public CheckPartExpression(String[] parts, int numSelections) {
            super();
            this.parts = parts;
            this.numSelections = numSelections;
        }
        
        @Override
        public EvaluationResult evaluate(IEvaluationContext context)
                throws CoreException {
            
            Object selectionVar =  context.getRoot().getVariable("selection");
            if ( selectionVar == null || !(selectionVar instanceof ISelection) )
                return EvaluationResult.TRUE;
            
            ISelection selection = (ISelection)selectionVar;
            
            if ( selection == null || selection.isEmpty() )
                    return EvaluationResult.TRUE;
            if (selection instanceof StructuredSelection) {
                StructuredSelection s = (StructuredSelection)selection;
                if (s.size() != numSelections) {
                    return EvaluationResult.FALSE;
                }

                Iterator<?> it = s.iterator();
                //each element has to fit at least one part
                boolean result = true;
                while (it.hasNext()) {
                    boolean partResult = false;
                    for (String part : parts) {
                        Object current = it.next();
                        if ( current != null && current.getClass().getCanonicalName().equals(part) ) {
                            partResult = true;
                        }
                    }
                    //so result will be false if there is any part which does not fit:
                    result &= partResult;
                    
                }
                if (result)
                    return EvaluationResult.TRUE;
                else
                    return EvaluationResult.FALSE;
            }
            return null;
        }
    }
    private class CheckEditorExpression  extends Expression {

        private String editorID;
        
        public CheckEditorExpression(String editorID) {
            super();
            this.editorID = editorID;
        }
        @Override
        public EvaluationResult evaluate(IEvaluationContext context)
                throws CoreException {
            Object editorVar =  context.getRoot().getVariable("activePart");
            if (editorVar == null )
                return EvaluationResult.FALSE;
            if (editorID == null)
                return EvaluationResult.TRUE;
            if ( editorVar.getClass().getCanonicalName().equals(editorID) )
                return EvaluationResult.TRUE;
            
            return EvaluationResult.FALSE;
        }
        
    }
    private DynamicMenuContributions() {
        registeredContributions = new HashMap<String, AbstractContributionFactory>();
        editorCommands = new HashMap<String, LinkedList<CommandContributionItem>>();
    }

    public void createAllMenuContributions() {
        LinkedList<EditorTransformationSettings> editors = TransformationManager.instance.getEditors();
        //If the editors are 'null' they are maybe not initialized yet so we give it a try
        if (editors == null) {
            TransformationManager.instance.initializeTransformations();
            //still 'null' ? Ok then there are no transformations
            if (editors == null)
                return;
        }
        //Create contributions:
        for ( EditorTransformationSettings editor : editors) {
            createMenuContributions(editor);
        }
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
                    CommandContributionItemParameter p = new CommandContributionItemParameter(PlatformUI.getWorkbench(), null, cmdID,CommandContributionItem.STYLE_PUSH);
                    p.label = t.getName();
                    p.tooltip = t.getTransformationName();
                    CommandContributionItem cmd = new CommandContributionItem(
                            p);

                    
                    Expression visibility = new CheckPartExpression(t.getPartConfig(), t.getNumSelections());
                    
                    additions.registerVisibilityForChild(cmd,visibility);
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
                
                additions.addContributionItem(dynamicMenu, new CheckEditorExpression(editor.getEditor()));
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

/*
 *  public void createMenu() {

        IMenuService menuService = (IMenuService) PlatformUI.getWorkbench()
                .getService(IMenuService.class);
        final IBindingService bindService = (IBindingService) PlatformUI
                .getWorkbench().getService(IBindingService.class);
        final ICommandService cmdService = (ICommandService) PlatformUI
                .getWorkbench().getService(ICommandService.class);
        final Category kielerCategory = cmdService
                .getCategory("de.cau.cs.kieler.ksbase.ui.ksbaseCategory");
        
        final ArrayList<Binding> bindings = new ArrayList<Binding>();
        for (Binding b : bindService.getBindings()) {
            bindings.add(b);
        }
        
        //
        // !! Hier weiter mit den anderen Menüs und vor allem den visibleWhen
        // Expressions !!

        for (final EditorTransformationSettings settings : TransformationManager.instance
                .getEditors()) {

            if (settings.isShownInMenu()
                    && settings.getMenuLocation().length() > 0) {
                // Create menu contributions
                //if (menuContribution != null)
                  //  menuService.removeContributionFactory(menuContribution);

                menuContribution = new AbstractContributionFactory(settings
                        .getMenuLocation(), "de.cau.cs.kieler") {

                    @Override
                    public void createContributionItems(
                            IServiceLocator serviceLocator,
                            IContributionRoot additions) {

                        MenuManager dynamicMenu = new MenuManager(settings
                                .getMenuName(), "de.cau.cs.kieler.ksbase.menu."
                                + settings.getMenuName());
                        for (final Transformation t : settings
                                .getTransformations()) {
                            String cmdID = "de.cau.cs.kieler.ksbase.commands."
                                    + settings.getMenuName() + "."
                                    + t.getName().replace(' ', '_');
                            Command transformationCommand = cmdService
                                    .getCommand(cmdID);
                            if (!transformationCommand.isDefined()) {
                                transformationCommand.define(t.getName(), "",
                                        kielerCategory);
                            }
                            try {
                                transformationCommand
                                        .setHandler(new TransformationCommandHandler(
                                                settings, t));

                                String shortcut = t.getKeyboardShortcut();
                                if (shortcut.length() > 0) {
                                    String[] keyString = shortcut.split(" ");
                                    // shortcut = "CTRL\u002Bx";
                                    KeyStroke ks = KeyStroke
                                            .getInstance(keyString[0]);
                                    KeySequence k;
                                    if (keyString.length > 1)
                                        k = KeySequence.getInstance(ks
                                                + "\u007F" + keyString[1]);
                                    else
                                        k = KeySequence.getInstance(ks);
                                    ParameterizedCommand pCommand = new ParameterizedCommand(
                                            transformationCommand, null);
                                    TriggerSequence[] oldBind =  bindService.getActiveBindingsFor(pCommand);
                                    
                                    KeyBinding kb = new KeyBinding(
                                            k,
                                            pCommand,
                                            "org.eclipse.ui.defaultAcceleratorConfiguration",
                                            settings.getContext(),
                                            null, null, null, KeyBinding.USER);
                                    
                                    if (oldBind != null && oldBind.length > 0) {
                                            //Don't add sequence, or we will have a mess after a few additions
                                            oldBind[0] = kb.getTriggerSequence();
                                    }
                                    else {
                                        bindings.add(kb);
                                    }

                                }

                                CommandContributionItemParameter p = new CommandContributionItemParameter(
                                        PlatformUI.getWorkbench(), null, cmdID,
                                        null, null, null, null, t.getName(), t
                                                .getKeyboardShortcut(), t
                                                .getTransformationName(),
                                        CommandContributionItem.STYLE_PUSH,
                                        null, true);
                                CommandContributionItem cmd = new CommandContributionItem(
                                        p);

                                // create the visibleWhen expressions:
                                IterateExpression baseIterate = new IterateExpression(
                                        "and", "false");
                                // Create visibility with instance-of
                                // expressions
                                if (t.getPartConfig().length > 0) {
                                    WithExpression withSelection = new WithExpression(
                                            "selection");
                                    IterateExpression iterate = new IterateExpression(
                                            "or", "false");

                                    for (String part : t.getPartConfig()) {
                                        InstanceofExpression instance = new InstanceofExpression(
                                                part);
                                        iterate.add(instance);
                                    }
                                    withSelection.add(iterate);
                                    baseIterate.add(withSelection);
                                }
                                
                                // Create visibility with count expression
                                WithExpression withSelection = new WithExpression(
                                        "selection");
                                CountExpression ce = new CountExpression(String
                                        .valueOf(t.getNumSelections()));
                                withSelection.add(ce);
                                baseIterate.add(withSelection);

                                additions.registerVisibilityForChild(cmd,
                                        baseIterate);
                                dynamicMenu.add(cmd);
                            } catch (CoreException e) {

                            } catch (ParseException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }

                        WithExpression we = new WithExpression("activeEditor");

                        InstanceofExpression exp = new InstanceofExpression(
                                settings.getEditor());
                        we.add(exp);

                        additions.addContributionItem(dynamicMenu, we);
                    }
                };
                try {
                    menuService.addContributionFactory(menuContribution);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("strange error here");
                }
            }
            if (settings.isShownIToolbar()
                    && settings.getToolbarLocation().length() > 0) {
                // Create menu contributions
                AbstractContributionFactory toolbarContribution = new AbstractContributionFactory(
                        settings.getToolbarLocation(), null) {

                    @Override
                    public void createContributionItems(
                            IServiceLocator serviceLocator,
                            IContributionRoot additions) {

                        ToolBarManager dynamicToolbar = new ToolBarManager();

                        // + settings.getMenuName());
                        for (final Transformation t : settings
                                .getTransformations()) {
                            String cmdID = "de.cau.cs.kieler.ksbase.commands."
                                    + settings.getMenuName() + "."
                                    + t.getName().replace(' ', '_');
                            Command transformationCommand = cmdService
                                    .getCommand(cmdID);
                            if (!transformationCommand.isDefined()) {
                                transformationCommand.define(t.getName(), "",
                                        kielerCategory);
                            }
                            transformationCommand
                                    .setHandler(new TransformationCommandHandler(
                                            settings, t));
                            CommandContributionItemParameter p = new CommandContributionItemParameter(
                                    PlatformUI.getWorkbench(), null, cmdID,
                                    CommandContributionItem.STYLE_PUSH);
                            CommandContributionItem cmd = new CommandContributionItem(
                                    p);
                            if (t.getPartConfig() != null
                                    && t.getPartConfig().length > 0) {
                                try {

                                    IterateExpression iterate = new IterateExpression(
                                            "or", "false");

                                    for (String part : t.getPartConfig()) {
                                        InstanceofExpression instance = new InstanceofExpression(
                                                part);
                                        iterate.add(instance);
                                    }
                                    /*
                                     * additions.registerVisibilityForChild(cmd,
                                     * iterate);
                                     *
                                    dynamicToolbar.add(cmd);
                                } catch (CoreException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                };
                menuService.addContributionFactory(toolbarContribution);
            }
        }
        try {
            Binding[] bs = new Binding[bindings.size()];
            
            System.arraycopy(bindings.toArray(), 0, bs, 0, bindings.size());
            bindService
                    .savePreferences(
                            bindService
                                    .getScheme("org.eclipse.ui.defaultAcceleratorConfiguration"),
                            bs);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
*/