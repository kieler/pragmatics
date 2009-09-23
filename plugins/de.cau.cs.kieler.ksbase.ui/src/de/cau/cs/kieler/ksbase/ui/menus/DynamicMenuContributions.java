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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.LinkedList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.core.internal.registry.ExtensionRegistry;
import org.eclipse.core.runtime.ContributorFactoryOSGi;
import org.eclipse.core.runtime.IContributor;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.RegistryFactory;
import org.osgi.framework.Bundle;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import de.cau.cs.kieler.ksbase.core.EditorTransformationSettings;
import de.cau.cs.kieler.ksbase.core.Transformation;
import de.cau.cs.kieler.ksbase.core.TransformationManager;
import de.cau.cs.kieler.viewmanagement.Activator;

public class DynamicMenuContributions {

    public static final DynamicMenuContributions instance = new DynamicMenuContributions();

    private DynamicMenuContributions() {

    }

    public void createAllMenuContributions() {
        LinkedList<EditorTransformationSettings> editors = TransformationManager.instance
                .getEditors();
        // If the editors are 'null' they are maybe not initialized yet so we
        // give it a try
        if (editors == null || editors.size() == 0) {
            TransformationManager.instance.initializeTransformations();
            // still 'null' ? Ok then there are no transformations
            if (editors == null || editors.size() == 0)
                return;
        }
        try {
            try {
                Document extension = javax.xml.parsers.DocumentBuilderFactory
                        .newInstance().newDocumentBuilder().newDocument();
                extension.setXmlStandalone(true);

                Element plugin = extension.createElement("plugin");
                Element commandExtension = extension.createElement("extension");
                commandExtension.setAttribute("point",
                        "org.eclipse.ui.commands");
                Element bindingExtension = extension.createElement("extension");
                bindingExtension.setAttribute("point",
                        "org.eclipse.ui.bindings");
                Element menuExtension = extension.createElement("extension");
                menuExtension.setAttribute("point", "org.eclipse.ui.menus");
                for (EditorTransformationSettings editor : editors) {
                    String menuID = "de.cau.cs.kieler.ksbase.menu."
                            + editor.getEditor();
                    if (editor.isShownInMenu()) {
                        // Menu Contributions
                        Element menuContribution = extension
                                .createElement("menuContribution");
                        menuContribution.setAttribute("locationURI", editor
                                .getMenuLocation());
                        // FIXME: Add menu to existing location
                        Element menu = extension.createElement("menu");
                        menu.setAttribute("id", menuID);
                        menu.setAttribute("label", editor.getMenuName());
                        menuContribution.appendChild(menu);
                        menuExtension.appendChild(menuContribution);
                    }

                    for (Transformation t : editor.getTransformations()) {
                        // Commands
                        String commandID = "de.cau.cs.kieler.ksbase."
                                + editor.getEditor() + "."
                                + t.getName().replace(' ', '_');
                        Element command = extension.createElement("command");
                        command.setAttribute("id", commandID);
                        command.setAttribute("name", t.getName());
                        command.setAttribute("categoryID",
                                "de.cau.cs.kieler.ksbase.ui.ksbaseCategory");
                        Element handler = extension
                                .createElement("defaultHandler");
                        handler
                                .setAttribute("class",
                                        "de.cau.cs.kieler.ksbase.ui.handler.TransformationCommandHandler");
                        Element parameter = extension
                                .createElement("parameter");
                        parameter.setAttribute("name",
                                "editorAndTransformation");
                        parameter.setAttribute("value", editor.getEditor()
                                + ":" + t.getTransformationName());
                        handler.appendChild(parameter);
                        command.appendChild(handler);
                        commandExtension.appendChild(command);

                        // KeyBindings
                        Element key = extension.createElement("key");
                        key.setAttribute("commandId", commandID);
                        key.setAttribute("contextId", editor.getContext());
                        key
                                .setAttribute("schemeId",
                                        "org.eclipse.ui.defaultAcceleratorConfiguration");
                        key.setAttribute("sequence", t.getKeyboardShortcut());
                        bindingExtension.appendChild(key);

                        if (editor.isShownInMenu() && t.isShownInMenu()) {
                            // Menu contribution commands
                            Element menuCommandContribution = extension
                                    .createElement("menuContribution");
                            menuCommandContribution.setAttribute("locationURI",
                                    menuID);
                            Element menuCommand = extension
                                    .createElement("command");
                            menuCommand.setAttribute("commandId", commandID);
                            // FIXME: menuCommand.setAttribute("icon",
                            // "icons/");
                            menuCommand.setAttribute("label", t.getName());
                            Element visibility = extension
                                    .createElement("visibleWhen");
                            visibility.setAttribute("checkEnabled", "false");
                            Element visAnd = extension.createElement("and");
                            Element visWith = extension.createElement("with");
                            visWith.setAttribute("variable", "activePart");
                            Element visEquals = extension
                                    .createElement("equals");
                            visEquals.setAttribute("value", editor.getEditor());
                            visWith.appendChild(visEquals);
                            visAnd.appendChild(visWith);
                            if (t.getPartConfig().length > 0) {
                                visWith = extension.createElement("with");
                                visWith.setAttribute("variable", "selection");
                                Element visIterate = extension
                                        .createElement("iterate");
                                visIterate.setAttribute("ifEmpty", "false");
                                visIterate.setAttribute("operator", "or");
                                for (String part : t.getPartConfig()) {
                                    Element visInstance = extension
                                            .createElement("instanceof");
                                    visInstance.setAttribute("value", part);
                                    visIterate.appendChild(visInstance);
                                }
                                visWith.appendChild(visIterate);
                                visAnd.appendChild(visWith);
                            }
                            visibility.appendChild(visAnd);
                            menuCommand.appendChild(visibility);
                            menuCommandContribution.appendChild(menuCommand);
                            menuExtension.appendChild(menuCommandContribution);
                        }

                    }
                }

                plugin.appendChild(commandExtension);
                plugin.appendChild(menuExtension);
                plugin.appendChild(bindingExtension);

                extension.appendChild(plugin);
                StringWriter str = new StringWriter();
                TransformerFactory
                        .newInstance()
                        .newTransformer()
                        .transform(
                                new DOMSource(extension),
                                new StreamResult(
                                        str));
                
                System.out.println(str.toString());
                
                IExtensionRegistry reg = RegistryFactory.getRegistry();
                Object key = ((ExtensionRegistry)reg).getTemporaryUserToken();
                //FIXME: Use 'this' ?
                Bundle bundle = Activator.getDefault().getBundle();
                IContributor contributor = ContributorFactoryOSGi.createContributor(bundle);
                ByteArrayInputStream is = new ByteArrayInputStream(str.toString().getBytes("UTF-8"));
                //reg.addContribution(is, contributor, false, null, null, key);
             
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (TransformerConfigurationException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (TransformerException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (TransformerFactoryConfigurationError e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /*
        if (editor.isShownInContext()) {
            createPopupContributions(editor);
        }
        if (editor.isShownInMenu()) {
            createMenuContributions(editor);
        }
        // createBalloonContributions(editor);
    }
    */

    public void removeContributionForEditor(
            final EditorTransformationSettings editor) {

    }

    /**
     * Removes all contributions
     */
    public void clearContributions() {

    }
}
