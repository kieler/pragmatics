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

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import org.eclipse.core.commands.Category;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.core.expressions.EvaluationResult;
import org.eclipse.core.expressions.Expression;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.PopupBarEditPolicy;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.bindings.Binding;
import org.eclipse.jface.bindings.keys.KeyBinding;
import org.eclipse.jface.bindings.keys.KeySequence;
import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.bindings.keys.ParseException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.CompoundContributionItem;
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
    protected HashMap<String, AbstractContributionFactory> registeredMenuContributions,
            registeredPopupContributions;
    
    // The *Managers
    protected HashMap<String, MenuManager> menuManagers, popupManagers;

    // Since we are using the same commands for multiple menus, we have to store
    // them
    protected HashMap<String, LinkedList<CommandContributionItem>> editorCommands;
    protected HashMap<String, LinkedList<CommandContributionItemParameter>> editorParamcommands;
    
    public static DynamicMenuContributions instance = new DynamicMenuContributions();

    private class DynamicCompound extends CompoundContributionItem {

        private String editorName;
        private LinkedList<IContributionItem> items;

        public DynamicCompound(String editorName) {
            super();
            items = new LinkedList<IContributionItem>();
            this.editorName = editorName;
        }

        @Override
        protected IContributionItem[] getContributionItems() {
            //create items if none are existing
            if (items.size() == 0) {
                for (CommandContributionItemParameter item : editorParamcommands
                        .get(editorName)) {
                    
                    items.add(new DynamicTransformationContributionCommand(item,(Transformation) item.parameters.get("transformation"))); 
                }
            }
            return items.toArray(new IContributionItem[items.size()]);
        }
    }

    private class DynamicMenuVisibilityListener implements IMenuListener {

        public void menuAboutToShow(IMenuManager manager) {
            System.out.println("-----------MenuListener-----------");
            System.out.println("Manager:\n" + manager.getId());
            System.out.println(manager.getClass());
            System.out.println(manager.getItems().length + " items : "
                    + manager.getItems().toString());

            ISelection selection = PlatformUI.getWorkbench()
                    .getActiveWorkbenchWindow().getSelectionService()
                    .getSelection();
            int numvis = 0;
            if (selection != null && selection instanceof StructuredSelection) {
                for (IContributionItem item : manager.getItems()) {
                    boolean result = false;
                    if (item instanceof DynamicTransformationContributionCommand
                            && ((DynamicTransformationContributionCommand) item)
                                    .getTransformation().getNumSelections() == ((StructuredSelection) selection)
                                    .size()) {
                        Iterator<?> it = ((StructuredSelection) selection)
                                .iterator();
                        result = true;
                        while (it.hasNext()) {
                            Object current = it.next();
                            System.out.println("Currently selected: "
                                    + current.getClass().getCanonicalName());
                            boolean partResult = false;
                            for (String part : ((DynamicTransformationContributionCommand) item)
                                    .getTransformation().getPartConfig()) {
                                System.out.println("\tCompared to:" + part);
                                if (current != null
                                        && current.getClass()
                                                .getCanonicalName()
                                                .equals(part)) {
                                    numvis++;
                                    partResult |= true;
                                }
                            }
                            result &= partResult;
                        }
                    }
                    System.out.println("item : " + item + " enablement is: "
                            + result);
                    // Set enabled state
                    ((DynamicTransformationContributionCommand) item)
                            .setEnabled(result);
                    item.setVisible(true);
                    item.update();
                }
            }
            System.out.println("manager contains " + manager.getItems().length
                    + " items, " + numvis + " enabled" + manager.isEnabled());

            manager.update();
            manager.setVisible(true);
            System.out.println("-----------MenuListener END-----------");
        }
    }

    private class DynamicPopupBarEditPolicy extends PopupBarEditPolicy {

        private class DynamicPopupBarCommandTool extends
                org.eclipse.gmf.runtime.diagram.ui.tools.AbstractPopupBarTool {

            public DynamicPopupBarCommandTool(EditPart epHost,
                    IElementType elementType) {
                super(epHost, elementType);
                // TODO Auto-generated constructor stub
            }

            @Override
            protected Request createTargetRequest() {
                return null;
            }

            @Override
            protected org.eclipse.gef.commands.Command getCommand() {
                org.eclipse.gef.commands.Command cmd = new org.eclipse.gef.commands.Command(
                        "Execute Transformation") {

                };
                return cmd;
            }

        }

        public DynamicPopupBarEditPolicy() {
            super();
        }

        @Override
        protected void fillPopupBarDescriptors() {

        }

    }

    private class DynamicTransformationContributionCommand extends
            CommandContributionItem {

        private boolean enabled;
        private Transformation transformation;

        public DynamicTransformationContributionCommand(
                CommandContributionItemParameter p, Transformation t) {
            super(p);
            this.transformation = t;
            this.enabled = true;
        }

        @Override
        public void dispose() {
            System.out.println("command disposed");
            //super.dispose();
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public boolean isEnabled() {
            return this.enabled;
        }

        public Transformation getTransformation() {
            return this.transformation;
        }
    }

    private class CheckEditorExpression extends Expression {

        private String editorID;

        public CheckEditorExpression(String editorID) {
            super();
            this.editorID = editorID;
        }

        @Override
        public EvaluationResult evaluate(IEvaluationContext context)
                throws CoreException {
            Object editorVar = context.getRoot().getVariable("activePart");
            if (editorVar == null)
                return EvaluationResult.FALSE;
            if (editorID == null)
                return EvaluationResult.TRUE;
            if (editorVar.getClass().getCanonicalName().equals(editorID))
                return EvaluationResult.TRUE;

            return EvaluationResult.FALSE;
        }

    }

    private DynamicMenuContributions() {
        registeredMenuContributions = new HashMap<String, AbstractContributionFactory>();
        registeredPopupContributions = new HashMap<String, AbstractContributionFactory>();
        menuManagers = new HashMap<String, MenuManager>();
        popupManagers = new HashMap<String, MenuManager>();
        editorCommands = new HashMap<String, LinkedList<CommandContributionItem>>();
        editorParamcommands = new HashMap<String, LinkedList<CommandContributionItemParameter>>();
    }

    /**
     * Creates all command objects. This is necessary because we don't want to
     * create duplicate objects
     * 
     * @param editor
     * @param additions
     */
    public void createUICommandsForEditor(
            final EditorTransformationSettings editor) {
        if (editorCommands.containsKey(editor.getEditor())) {
            System.out.println("editor exists, not regenerating stuff");
            // unregister all commands ? or just return ?
            // editorCommands.remove(editor.getEditor());
            // check transformations and commands and create new commands for
            // new transfs
        } else {
            // Read old bindings
            LinkedList<Binding> bindingList = new LinkedList<Binding>();
            for (Binding b : bindService.getBindings()) {
                bindingList.add(b);
            }

            LinkedList<CommandContributionItemParameter> params = new LinkedList<CommandContributionItemParameter>();
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
                        .setHandler(new TransformationCommandHandler(editor, t));

                CommandContributionItemParameter p = new CommandContributionItemParameter(
                        PlatformUI.getWorkbench(), cmdID, cmdID,
                        CommandContributionItem.STYLE_PUSH);
                p.label = t.getName();
                p.tooltip = t.getTransformationName();
                HashMap<Object,Object> pparams = new HashMap<Object, Object>();
                pparams.put("transformation", t);
                p.parameters = pparams;
                params.add(p);
                
                ParameterizedCommand pCommand = new ParameterizedCommand(
                        transformationCommand, null);
                // Create key bindings
                String shortcut = t.getKeyboardShortcut();
                if (shortcut.length() > 0) {
                    // We have to split the shortcut into KeyStrokes (beginning)
                    // and KeySequence (additional keys)
                    String[] keyString = shortcut.split(" ");
                    try {
                        KeyStroke ks = KeyStroke.getInstance(keyString[0]);
                        KeySequence k;
                        if (keyString.length > 1)
                            k = KeySequence.getInstance(ks + "\u007F"
                                    + keyString[1]);
                        else
                            k = KeySequence.getInstance(ks);
                        // Create the actual key binding
                        KeyBinding kb = new KeyBinding(
                                k,
                                pCommand,
                                "org.eclipse.ui.defaultAcceleratorConfiguration",
                                editor.getContext(), null, null, null,
                                KeyBinding.USER);
                        while (bindingList.contains(kb))
                            bindingList.remove(kb);
                        bindingList.add(kb);

                    } catch (ParseException pe) {
                        System.out.println("invalid shortcut:"
                                + keyString.toString());
                    }
                }
                editorParamcommands.put(editor.getEditor(), params);
            }
            // We now have to write the key bindings
            // this looks a bit ugly but there is no
            // other way to do
            Binding[] newBindings = new Binding[bindingList.size()];
            System.arraycopy(bindingList.toArray(), 0, newBindings, 0,
                    bindingList.size());
            try {
                bindService.savePreferences(bindService.getActiveScheme(),
                        newBindings);
            } catch (IOException e) {
                System.err.println("Error writing keybindings");
            }
        }

    }

    public void createAllMenuContributions() {
        LinkedList<EditorTransformationSettings> editors = TransformationManager.instance
                .getEditors();
        // If the editors are 'null' they are maybe not initialized yet so we
        // give it a try
        if (editors == null) {
            TransformationManager.instance.initializeTransformations();
            // still 'null' ? Ok then there are no transformations
            if (editors == null)
                return;
        }
        // Create contributions:
        for (EditorTransformationSettings editor : editors) {
            // let's see if we already have this editor in our map.
            // until we can see if the editor has changed, we are removing
            // the old one

            if (editor.isShownInContext()) {
                createPopupContributions(editor);
            }
            if (editor.isShownInMenu()) {
                createMenuContributions(editor);
            }
            // createBalloonContributions(editor);
        }
    }

    public void createToolbarContributions(
            final EditorTransformationSettings editor) {

        // To be sure we have commands for this editor:
        createUICommandsForEditor(editor);

        AbstractContributionFactory editorContribution = new AbstractContributionFactory(
                editor.getToolbarLocation(), "de.cau.cs.kieler") {

            @Override
            public void createContributionItems(IServiceLocator serviceLocator,
                    IContributionRoot additions) {
                ToolBarManager dynamicToolbar = new ToolBarManager();

                for (CommandContributionItem item : editorCommands.get(editor
                        .getEditor())) {
                    if (item instanceof DynamicTransformationContributionCommand) {
                        DynamicTransformationContributionCommand cmd = (DynamicTransformationContributionCommand) item;
                        if (cmd.getTransformation().isShownIToolbar()) {
                            dynamicToolbar.add(cmd);
                        }
                    }
                }

                // no enablement for toolbar...have to add selection listener to
                // editor...
            }
        };
        menuService.addContributionFactory(editorContribution);
    }

    public void createPopupContributions(
            final EditorTransformationSettings editor) {
        // To be sure we have commands for this editor:
        createUICommandsForEditor(editor);
        MenuManager popupManager = popupManagers.get(editor.getEditor());

        if (popupManager == null) {
            System.out.println("create popup");
            popupManager = new MenuManager(editor.getMenuName(),
                    "de.cau.cs.kieler.ksbase.popup." + editor.getMenuName()) {
                @Override
                public void dispose() {
                    System.out.println("disposed!");
                    // super.dispose();
                }
            };
        } else {
            System.out.println("recreate popup");
            // menuService.removeContributionFactory(registeredMenuContributions.get(editor.getEditor()));
            menuService.releaseContributions(popupManager);
        }

        // Adds a menu listener to control enable/disable state of menu
        // items
        //popupManager.addMenuListener(new DynamicMenuVisibilityListener());

        AbstractContributionFactory editorContribution = new AbstractContributionFactory(
                editor.getPopupLocation(), "de.cau.cs.kieler") {

            @Override
            public void createContributionItems(IServiceLocator serviceLocator,
                    IContributionRoot additions) {
                MenuManager popupManager = popupManagers
                        .get(editor.getEditor());
                popupManager.add(new DynamicCompound(editor.getEditor()));
                // new CheckEditorExpression(editor.getEditor()
                additions.addContributionItem(popupManager, null);

            }
        };

        popupManagers.put(editor.getEditor(), popupManager);
        registeredPopupContributions
                .put(editor.getEditor(), editorContribution);
        menuService.addContributionFactory(editorContribution);
    }

    /**
     * Creates or modifies a menu contribution for one specific editor
     * 
     * @param editor
     */
    public void createMenuContributions(
            final EditorTransformationSettings editor) {

        // To be sure we have commands for this editor:
        createUICommandsForEditor(editor);
        MenuManager menuManager = menuManagers.get(editor.getEditor());

        if (menuManager == null) {
            System.out.println("create menu");
            menuManager = new MenuManager(editor.getMenuName(),
                    "de.cau.cs.kieler.ksbase.menu." + editor.getMenuName()) {
                @Override
                public void dispose() {
                    System.out.println("menu disposed");
                }
            };
        } else {
            System.out.println("recreate menu");
            // menuService.removeContributionFactory(registeredMenuContributions.get(editor.getEditor()));
            menuService.releaseContributions(menuManager);
        }

        // Add commands to menu
        menuManager.add(new DynamicCompound(editor.getEditor()));

        // Adds a menu listener to control enable/disable state of menu
        // items
        //menuManager.addMenuListener(new DynamicMenuVisibilityListener());

        AbstractContributionFactory editorContribution = new AbstractContributionFactory(
                editor.getMenuLocation(), null) {

            @Override
            public void createContributionItems(IServiceLocator serviceLocator,
                    IContributionRoot additions) {
                MenuManager menuManager = menuManagers.get(editor.getEditor());
                additions.addContributionItem(menuManager,
                        new CheckEditorExpression(editor.getEditor()));
            }
        };
        menuManagers.put(editor.getEditor(), menuManager);
        registeredMenuContributions.put(editor.getEditor(), editorContribution);
        menuService.addContributionFactory(editorContribution);
    }

    public void createBalloonContributions(
            final EditorTransformationSettings editor) {
        /*
        IWorkbenchWindow wp = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
        if ( wp != null) {
            
            wp.getSelectionService().addSelectionListener(new ISelectionListener() {
                
                public void selectionChanged(IWorkbenchPart part, ISelection selection) {
                    if (selection instanceof StructuredSelection) {
                        Object selectedPart = ((StructuredSelection) selection).getFirstElement();
                    if (selectedPart != null && selectedPart instanceof EditPart) {
                        
                        EditPart epart = (EditPart)selectedPart;
                        DynamicPopupBarEditPolicy popPolicy =  new DynamicPopupBarEditPolicy();
                        popPolicy.setHost(epart);
                        epart.installEditPolicy(EditPolicyRoles.POPUPBAR_ROLE, popPolicy);
                    }
                }
                }
            });
        }

        
        for (Transformation t : editor.getTransformations()) {
            if (!t.isShownInBalloon()) {
                for (String part : t.getPartConfig()) {
                    
                    PopupBarEditPolicy p = new PopupBarEditPolicy();


                }
            }
        }
        */
    }

    /*
    TODO:
    - Menüs verschwinden wenn man kontext & menu anklickt
    - Warum diese blöden Fehlermeldungen beim contribute ?
    - Pref page muss noch mal überarbeitet werden
    - MenuIDs als optional (auch ext point)
    */

    public void removeContributionForEditor(
            final EditorTransformationSettings editor) {
        if (registeredMenuContributions.containsKey(editor.getEditor())) {
            menuService.removeContributionFactory(registeredMenuContributions
                    .get(editor.getEditor()));
            registeredMenuContributions.remove(editor.getEditor());
        }
    }

    /**
     * Removes all contributions
     */
    public void clearContributions() {
        for (AbstractContributionFactory value : registeredMenuContributions
                .values()) {
            menuService.removeContributionFactory(value);
        }
        registeredMenuContributions.clear();
    }
}
