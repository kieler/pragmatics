/**
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
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
import java.util.LinkedList;
import java.util.Map;

import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.IParameter;
import org.eclipse.core.commands.IParameterValues;
import org.eclipse.core.commands.ParameterValuesException;
import org.eclipse.core.internal.expressions.EqualsExpression;
import org.eclipse.core.internal.expressions.IterateExpression;
import org.eclipse.core.internal.expressions.TestExpression;
import org.eclipse.core.internal.expressions.WithExpression;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.CompoundContributionItem;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.internal.expressions.AndExpression;
import org.eclipse.ui.menus.CommandContributionItem;
import org.eclipse.ui.menus.CommandContributionItemParameter;

import de.cau.cs.kieler.ksbase.core.EditorTransformationSettings;
import de.cau.cs.kieler.ksbase.core.Transformation;
import de.cau.cs.kieler.ksbase.core.TransformationManager;
import de.cau.cs.kieler.ksbase.ui.KSBasEUIPlugin;
import de.cau.cs.kieler.ksbase.ui.handler.TransformationCommandHandler;

/**
 * A class implementing a dynamic menu. Used to add custom transformation commands that have been
 * configured by using the preference page.
 * 
 * @author mim
 * 
 * 
 */
@SuppressWarnings("restriction")
public class DynamicMenu extends CompoundContributionItem {

    /**
     * Default empty constructor.
     */
    public DynamicMenu() {
    }

    /**
     * Creates a contribution with the given id.
     * 
     * @param id
     *            The contribution item id
     */
    public DynamicMenu(final String id) {
        super(id);
    }

    @Override
    public boolean isDynamic() {
        return true;
    }

    @Override
    protected IContributionItem[] getContributionItems() {
        // System.out.println("get items");
        LinkedList<IContributionItem> items = new LinkedList<IContributionItem>();

        /*
         * IHandlerService hserv = (IHandlerService) PlatformUI.getWorkbench().getService(
         * IHandlerService.class);
         */
        ICommandService ics = (ICommandService) PlatformUI.getWorkbench().getService(
                ICommandService.class);
        for (EditorTransformationSettings editor : TransformationManager.INSTANCE
                .getUserDefinedEditors()) {
            for (Transformation transf : editor.getTransformations()) {
                if (!transf.isVisible()) {
                    continue;
                }
                String paramId = editor.getEditorId() + "." + transf.getExtension();
                String cmdId = paramId + ".command";
                Command command = ics.getCommand(cmdId);
                //if (!command.isDefined()) {
                    IParameter[] cmdParam = new IParameter[2];
                    cmdParam[0] = new IParameter() {

                        public boolean isOptional() {
                            return false;
                        }

                        public IParameterValues getValues() throws ParameterValuesException {
                            return new IParameterValues() {

                                public Map<Object,Object> getParameterValues() {
                                    return new HashMap<Object, Object>();
                                }
                            };
                        }

                        public String getName() {
                            return "editor";
                        }

                        public String getId() {
                            return "de.cau.cs.kieler.ksbase.editorParameter";
                        }
                    };
                    cmdParam[1] = new IParameter() {

                        public boolean isOptional() {
                            return false;
                        }

                        public IParameterValues getValues() throws ParameterValuesException {
                            return new IParameterValues() {

                                public Map<Object,Object> getParameterValues() {
                                    return new HashMap<Object, Object>();
                                }
                            };
                        }

                        public String getName() {
                            return "transformation";
                        }

                        public String getId() {
                            return "de.cau.cs.kieler.ksbase.transformationParameter";
                        }
                    };
                    command.define(transf.getName(), "Structure based editing command", ics
                            .getCategory("de.cau.cs.kieler.ksbase.ui.ksbaseCategory"), cmdParam);
                    command.setHandler(new TransformationCommandHandler());
                //}

                
                CommandContributionItemParameter param = new CommandContributionItemParameter(
                        PlatformUI.getWorkbench().getActiveWorkbenchWindow(), paramId, cmdId,
                        CommandContributionItem.STYLE_PUSH);
                param.label = transf.getName();
                param.visibleEnabled = false;
                param.commandId = cmdId;
                Map<String, String> parameters = new HashMap<String, String>();
                parameters.put("de.cau.cs.kieler.ksbase.editorParameter", editor.getEditorId());
                parameters.put("de.cau.cs.kieler.ksbase.transformationParameter", transf
                        .getExtension());
                param.parameters = parameters;
                IContributionItem ci = new CommandContributionItem(param);
                ci.setVisible(true);
                try {

                    AndExpression andExp = new AndExpression();
                    // Object selection
                    WithExpression withExp = new WithExpression("selection");
                    IterateExpression itExp = new IterateExpression("and", "false");
                    String val = editor.getEditorId() + "," + transf.getExtension();
                    TestExpression testExp = new TestExpression("",
                            "de.cau.cs.kieler.ksbase.ui.modelTesting.isModelInstance",
                            new Object[] {val }, true, true);
                    itExp.add(testExp);
                    withExp.add(itExp);
                    andExp.add(withExp);
                    // EditorId
                    IterateExpression itEdExp = new IterateExpression("or", "false");
                    WithExpression withEditorExp = new WithExpression("activeEditorId");
                    EqualsExpression equalsExp = new EqualsExpression(editor.getEditorId());
                    withEditorExp.add(equalsExp);
                    itEdExp.add(withEditorExp);
                    andExp.add(itEdExp);

                    // ((IContributionRoot) getParent()).registerVisibilityForChild(ci, andExp);
                } catch (CoreException ce) {
                    KSBasEUIPlugin.getDefault().logWarning(
                            "visibility expression could not be created");
                }

                items.add(ci);

            }
        }
        return items.toArray(new IContributionItem[items.size()]);
    }

    @Override
    public boolean isVisible() {
        return true;
    }

}
