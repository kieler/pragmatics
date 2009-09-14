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
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.commands.Category;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.core.expressions.EvaluationResult;
import org.eclipse.core.expressions.Expression;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.common.core.service.IProviderChangeListener;
import org.eclipse.gmf.runtime.common.core.service.ProviderChangeEvent;
import org.eclipse.gmf.runtime.common.ui.services.icon.IconService;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.DiagramPopupBarEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.PopupBarEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.diagram.ui.tools.AddPopupBarTool;
import org.eclipse.gmf.runtime.diagram.ui.tools.PopupBarTool;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.ui.services.modelingassistant.IModelingAssistantOperation;
import org.eclipse.gmf.runtime.emf.ui.services.modelingassistant.IModelingAssistantProvider;
import org.eclipse.gmf.runtime.emf.ui.services.modelingassistant.ModelingAssistantProvider;
import org.eclipse.gmf.runtime.emf.ui.services.modelingassistant.ModelingAssistantService;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.bindings.Binding;
import org.eclipse.jface.bindings.TriggerSequence;
import org.eclipse.jface.bindings.keys.KeyBinding;
import org.eclipse.jface.bindings.keys.KeySequence;
import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.bindings.keys.ParseException;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
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
import de.cau.cs.kieler.ksbase.ui.handler.ExecuteTransformationCommand;
import de.cau.cs.kieler.ksbase.ui.handler.ExecuteTransformationRequest;
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
    
    private class DynamicPopupBarEditPolicy extends PopupBarEditPolicy {
        
        private class DynamicPopupBarCommandTool extends org.eclipse.gmf.runtime.diagram.ui.tools.AbstractPopupBarTool {

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
                org.eclipse.gef.commands.Command cmd = new org.eclipse.gef.commands.Command("Execute Transformation") {
                    
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

        public void setEnabeld(boolean enabled) {
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
        registeredContributions = new HashMap<String, AbstractContributionFactory>();
        editorCommands = new HashMap<String, LinkedList<CommandContributionItem>>();
    }

    /**
     * Creates all command objects. This is necessary because we don't want to
     * create duplicate objects
     * 
     * @param editor
     * @param additions
     */
    public void createUICommandsForEditor(
            final EditorTransformationSettings editor,
            IContributionRoot additions) {
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
                        .setHandler(new TransformationCommandHandler(editor, t));

                CommandContributionItemParameter p = new CommandContributionItemParameter(
                        PlatformUI.getWorkbench(), null, cmdID,
                        CommandContributionItem.STYLE_PUSH);
                p.label = t.getName();
                p.tooltip = t.getTransformationName();
                DynamicTransformationContributionCommand cmd = new DynamicTransformationContributionCommand(
                        p, t);
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
                    commands.add(cmd);
                }
                editorCommands.put(editor.getEditor(), commands);
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
            if (registeredContributions.containsKey(editor.getEditor())) {
                menuService.removeContributionFactory(registeredContributions
                        .get(editor.getEditor()));
                registeredContributions.remove(editor.getEditor());
            }
            
                        if (editor.isShownInMenu())
                            createMenuContributions(editor, false);
                        if (editor.isShownInContext())
                            createMenuContributions(editor, true);
            
            createBalloonContributions(editor);
        }
    }

    public void createToolbarContributions(
            final EditorTransformationSettings editor) {
        AbstractContributionFactory editorContribution = new AbstractContributionFactory(
                editor.getToolbarLocation(), "de.cau.cs.kieler") {

            @Override
            public void createContributionItems(IServiceLocator serviceLocator,
                    IContributionRoot additions) {
                ToolBarManager dynamicToolbar = new ToolBarManager();

                // To be sure we have commands for this editor:
                createUICommandsForEditor(editor, additions);

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

    public void createContextContributions(
            final EditorTransformationSettings editor) {

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
*/            
    /**
     * Creates or modifies a menu contribution for one specific editor
     * 
     * @param editor
     */
    public void createMenuContributions(
            final EditorTransformationSettings editor, final boolean popup) {
        final String location;
        if (popup)
            location = editor.getPopupLocation();
        else
            location = editor.getMenuLocation();

        AbstractContributionFactory editorContribution = new AbstractContributionFactory(
                location, "de.cau.cs.kieler") {

            @Override
            public void createContributionItems(IServiceLocator serviceLocator,
                    IContributionRoot additions) {
                MenuManager dynamicMenu = new MenuManager(editor.getMenuName(),
                        "de.cau.cs.kieler.ksbase.menu." + editor.getMenuName());
                menuService.populateContributionManager(dynamicMenu, location);
                // To be sure we have commands for this editor:
                createUICommandsForEditor(editor, additions);

                // Add commands to menu
                for (CommandContributionItem item : editorCommands.get(editor
                        .getEditor())) {
                    if (item instanceof DynamicTransformationContributionCommand) {
                        DynamicTransformationContributionCommand cmd = (DynamicTransformationContributionCommand) item;
                        if (cmd.getTransformation().isShownInMenu()) {
                            dynamicMenu.add(cmd);
                        }
                    }
                }

                // Adds a menu listener to control enable/disable state of menu
                // items
                dynamicMenu.addMenuListener(new IMenuListener() {

                    public void menuAboutToShow(IMenuManager manager) {
                        ISelection selection = PlatformUI.getWorkbench()
                                .getActiveWorkbenchWindow()
                                .getSelectionService().getSelection();
                        if (selection != null
                                && selection instanceof StructuredSelection) {
                            for (IContributionItem item : manager.getItems()) {
                                boolean result = false;
                                if (item instanceof DynamicTransformationContributionCommand
                                        && ((DynamicTransformationContributionCommand) item)
                                                .getTransformation()
                                                .getNumSelections() == ((StructuredSelection) selection)
                                                .size()) {
                                    Iterator<?> it = ((StructuredSelection) selection)
                                            .iterator();
                                    result = true;
                                    while (it.hasNext()) {
                                        Object current = it.next();
                                        System.out.println("Currently selected: "
                                                + current.getClass()
                                                        .getCanonicalName());
                                        boolean partResult = false;
                                        for (String part : ((DynamicTransformationContributionCommand) item)
                                                .getTransformation()
                                                .getPartConfig()) {
                                            System.out.println("\tCompared to:"
                                                    + part);
                                            if (current != null
                                                    && current.getClass()
                                                            .getCanonicalName()
                                                            .equals(part)) {
                                                partResult |= true;
                                            }
                                        }
                                        result &= partResult;
                                    }
                                }
                                // Set enabled state
                                ((DynamicTransformationContributionCommand) item)
                                        .setEnabeld(result);
                                item.update();
                            }
                        }

                        manager.update(true);
                        manager.setVisible(true);

                    }
                });
                
                additions.addContributionItem(dynamicMenu,
                        new CheckEditorExpression(editor.getEditor()));
            }
        };
        registeredContributions.put(editor.getEditor(), editorContribution);
        //menuService.addContributionFactory(editorContribution);
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