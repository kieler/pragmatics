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
import org.eclipse.core.internal.runtime.Activator;
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

@SuppressWarnings("restriction")
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
                //Create extension point elements:
                Element plugin = extension.createElement("plugin");
                Element commandExtension = extension.createElement("extension");
                commandExtension.setAttribute("point",
                        "org.eclipse.ui.commands");
                Element bindingExtension = extension.createElement("extension");
                bindingExtension.setAttribute("point",
                        "org.eclipse.ui.bindings");
                Element menuExtension = extension.createElement("extension");
                Element menuContribution = extension
                .createElement("menuContribution");
                Element menu = extension.createElement("menu");
                menuExtension.setAttribute("point", "org.eclipse.ui.menus");
                Element handlerExtension = extension.createElement("extension");
                handlerExtension.setAttribute("point", "org.eclipse.ui.handlers");
                //Iterate through editors and create extension point contents
                for (EditorTransformationSettings editor : editors) {
                    String menuID = "de.cau.cs.kieler.ksbase.menu."
                            + editor.getEditor();
                    if (editor.isShownInMenu()) {
                        // Menu Contributions
                        menuContribution.setAttribute("locationURI", editor
                                .getMenuLocation());
                        // FIXME: Add menu to existing location
                        menu.setAttribute("id", menuID);
                        menu.setAttribute("label", editor.getMenuName());
                        Element menuVisible = extension.createElement("visibleWhen");
                        menuVisible.setAttribute("checkEnabled", "false");
                        Element visIterate = extension.createElement("iterate");
                        visIterate.setAttribute("ifEmpty", "false");
                        visIterate.setAttribute("operator", "or");
                        Element visWith = extension.createElement("with");
                        visWith.setAttribute("variable", "activeEditor");
                        Element visInstance = extension.createElement("instanceof");
                        visInstance.setAttribute("value", editor.getEditor());
                        visWith.appendChild(visInstance);
                        visIterate.appendChild(visWith);
                        menuVisible.appendChild(visIterate);
                        menu.appendChild(menuVisible);
                    }

                    for (Transformation t : editor.getTransformations()) {
                        // Command extension points
                        String commandID = "de.cau.cs.kieler.ksbase."
                                + editor.getEditor() + "."
                                + t.getName().replace(' ', '_');
                        Element command = extension.createElement("command");
                        command.setAttribute("id", commandID);
                        command.setAttribute("name", t.getName());
                        command.setAttribute("categoryId",
                                "de.cau.cs.kieler.ksbase.ui.ksbaseCategory");
                        Element commandParameter = extension.createElement("commandParameter");
                        commandParameter.setAttribute("id", "de.cau.cs.kieler.ksbase.editorParameter");
                        commandParameter.setAttribute("name", "editor");
                        command.appendChild(commandParameter);
                        commandParameter = extension.createElement("commandParameter");
                        commandParameter.setAttribute("id", "de.cau.cs.kieler.ksbase.transformationParameter");
                        commandParameter.setAttribute("name", "transformation");
                        command.appendChild(commandParameter);
                        commandExtension.appendChild(command);
                        
                        // KeyBindings
                        Element key = extension.createElement("key");
                        key.setAttribute("commandId", commandID);
                        key.setAttribute("contextId", editor.getContext());
                        key
                                .setAttribute("schemeId",
                                        "org.eclipse.ui.defaultAcceleratorConfiguration");
                        key.setAttribute("sequence", t.getKeyboardShortcut());
                        

                        if (editor.isShownInMenu() && t.isShownInMenu()) {
                            // Menu commands
                            Element menuCommand = extension
                                    .createElement("command");
                            menuCommand.setAttribute("commandId", commandID);
                            // FIXME: menuCommand.setAttribute("icon",
                            // "icons/");
                            menuCommand.setAttribute("label", t.getName());
                            //Set command parameters
                            Element handlerParam = extension.createElement("parameter");
                            handlerParam.setAttribute("name", "de.cau.cs.kieler.ksbase.editorParameter");
                            handlerParam.setAttribute("value", editor.getEditor());
                            menuCommand.appendChild(handlerParam);
                            
                            //key needs attribute 'id' instead of name ....
                            Element keyParam = (Element)handlerParam.cloneNode(true);
                            keyParam.removeAttribute("name");
                            keyParam.setAttribute("id",  "de.cau.cs.kieler.ksbase.editorParameter");
                            key.appendChild(keyParam);
                            
                            handlerParam = extension.createElement("parameter");
                            handlerParam.setAttribute("name", "de.cau.cs.kieler.ksbase.transformationParameter");
                            handlerParam.setAttribute("value", t.getTransformationName());
                            menuCommand.appendChild(handlerParam);
                            menu.appendChild(menuCommand);
                            
                            keyParam = (Element) handlerParam.cloneNode(true);
                            keyParam.removeAttribute("name");
                            keyParam.setAttribute("id",  "de.cau.cs.kieler.ksbase.transformationParameter");
                            key.appendChild(keyParam);
                            
                            //Command handler extensions
                            Element handlerCommand = extension.createElement("handler");
                            handlerCommand.setAttribute("commandId", commandID);
                            Element classHandler = extension.createElement("class");
                            classHandler.setAttribute("class", "de.cau.cs.kieler.ksbase.ui.handler.TransformationCommandHandler");
                            handlerCommand.appendChild(classHandler);
                            //Handler restrictions
                            Element handlerEnabled = extension
                                    .createElement("enabledWhen");
                            Element visAnd = extension.createElement("and");
                            Element visWith = extension.createElement("with");
                            visWith.setAttribute("variable", "selection");
                            Element visCount = extension.createElement("count");
                            visCount.setAttribute("value", String.valueOf(t.getNumSelections()));
                            visWith.appendChild(visCount);
                            
                            Element visOr = extension.createElement("or");
                            
                            if (t.getPartConfig().length > 0) {
                                for (String part : t.getPartConfig()) {
                                    Element visIterate = extension
                                            .createElement("iterate");
                                    Element visInstance = extension
                                            .createElement("instanceof");
                                    visInstance.setAttribute("value", part);
                                    visIterate.appendChild(visInstance);
                                    visOr.appendChild(visIterate);
                                }
                            }
                            visWith.appendChild(visOr);
                            visAnd.appendChild(visWith);
                            handlerEnabled.appendChild(visAnd);
                            
                            handlerCommand.appendChild(handlerEnabled);
                            bindingExtension.appendChild(key);
                            handlerExtension.appendChild(handlerCommand);
                            
                        }

                    }
                }
                //completing menu
                menuContribution.appendChild(menu);
                menuExtension.appendChild(menuContribution);
                
                plugin.appendChild(commandExtension);
                plugin.appendChild(menuExtension);
                plugin.appendChild(bindingExtension);
                plugin.appendChild(handlerExtension);
                extension.appendChild(plugin);
                StringWriter str = new StringWriter();
                TransformerFactory
                        .newInstance()
                        .newTransformer()
                        .transform(
                                new DOMSource(extension),
                                new StreamResult(
                                        str));
                
                //System.out.println(str.toString());
                
                IExtensionRegistry reg = RegistryFactory.getRegistry();
                Object key = ((ExtensionRegistry)reg).getTemporaryUserToken();
                //FIXME: Use 'this' ?
                
                Bundle bundle = Activator.getDefault().getBundle("de.cau.cs.kieler.ksbase.ui");
                
                IContributor contributor = ContributorFactoryOSGi.createContributor(bundle);
                ByteArrayInputStream is = new ByteArrayInputStream(str.toString().getBytes("UTF-8"));
                reg.addContribution(is, contributor, false, null, null, key);
             
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
