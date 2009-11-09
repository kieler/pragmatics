/*
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

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.ContributorFactoryOSGi;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IContributor;
import org.eclipse.core.runtime.Path;
import org.osgi.framework.Bundle;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import de.cau.cs.kieler.ksbase.core.EditorTransformationSettings;
import de.cau.cs.kieler.ksbase.core.KSBasEMenuContribution;
import de.cau.cs.kieler.ksbase.core.Transformation;
import de.cau.cs.kieler.ksbase.core.TransformationManager;
import de.cau.cs.kieler.ksbase.ui.KSBasEUIPlugin;

/**
 * Creates menus for all registered editor transformation settings and
 * contributes them when starting an eclipse instance.
 * 
 * @author Michael Matzen - mim AT informatik.uni-kiel.de
 * 
 */
public final class DynamicMenuContributions {


    /** DynamicMenuContribution instance. **/
    public static final DynamicMenuContributions INSTANCE = new DynamicMenuContributions();
    /**
     * A list of cached commands used to increase performance because we will
     * often have the same command added in multiple menus (e.g. main menu and
     * popup).
     **/
    private HashMap<String, Node> cachedTransformationCommands;
    /** The list of command-Ids. **/
    private HashMap<Transformation, String> commandIds;
    /**
     * Storing the key bindings for creating them AFTER the plug-in has been
     * installed and the commands Id's are registered.
     */
    private HashMap<String, String[]> keybindings;

    /**
     * Default constructor.
     */
    private DynamicMenuContributions() {
        cachedTransformationCommands = new HashMap<String, Node>();
        commandIds = new HashMap<Transformation, String>();
        keybindings = new HashMap<String, String[]>();
    }

    /**
     * Creates a menu for an editor.
     * 
     * @param editor
     *            The editor to create the menu for.
     */
    public void createMenuForEditor(final EditorTransformationSettings editor) {
        Assert.isNotNull(editor);

        try {
            try {
                Document extension =
                        javax.xml.parsers.DocumentBuilderFactory
                                .newInstance().newDocumentBuilder().newDocument();
                extension.setXmlStandalone(true);
                // Create extension point elements:
                Element plugin = extension.createElement("plugin");
                Element commandExtension = extension.createElement("extension");
                commandExtension.setAttribute("point", "org.eclipse.ui.commands");
                Element bindingExtension = extension.createElement("extension");
                bindingExtension.setAttribute("point", "org.eclipse.ui.bindings");
                Element contextExtension = extension.createElement("extension");
                contextExtension.setAttribute("point", "org.eclipse.ui.contexts");
                Element menuExtension = extension.createElement("extension");
                menuExtension.setAttribute("point", "org.eclipse.ui.menus");
                Element handlerExtension = extension.createElement("extension");
                handlerExtension.setAttribute("point", "org.eclipse.ui.handlers");

                // Create visibility flags for menus
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
                // Create command extensions for all transformations:
                for (Transformation t : editor.getTransformations()) {
                    String commandID =
                            "de.cau.cs.kieler.ksbase."
                                    + editor.getEditor() + "." + t.getName().replace(' ', '_');
                    // store id
                    commandIds.put(t, commandID);
                    Element command = extension.createElement("command");
                    command.setAttribute("id", commandID);
                    command.setAttribute("name", t.getName());
                    command.setAttribute("categoryId", "de.cau.cs.kieler.ksbase.ui.ksbaseCategory");
                    Element commandParameter = extension.createElement("commandParameter");
                    commandParameter.setAttribute("id", "de.cau.cs.kieler.ksbase.editorParameter");
                    commandParameter.setAttribute("name", "editor");
                    command.appendChild(commandParameter);
                    commandParameter = extension.createElement("commandParameter");
                    commandParameter.setAttribute(
                            "id", "de.cau.cs.kieler.ksbase.transformationParameter");
                    commandParameter.setAttribute("name", "transformation");
                    command.appendChild(commandParameter);
                    commandExtension.appendChild(command);

                    // KeyBindings
                    if (t.getKeyboardShortcut() != null
                            && t.getKeyboardShortcut().length() > 0 && editor.getContext() != null
                            && editor.getContext().length() > 0) {

                        keybindings.put(commandID, new String[] {
                                editor.getContext(), t.getKeyboardShortcut(),
                                t.getTransformationName() });

                        Element key = extension.createElement("key");
                        key.setAttribute("commandId", commandID);
                        key.setAttribute("contextId", editor.getContext());// editorContext);
                        key.setAttribute(
                                "schemeId", "org.eclipse.ui.defaultAcceleratorConfiguration");
                        key.setAttribute("sequence", t.getKeyboardShortcut());
                        // Set key parameters
                        Element keyParam = extension.createElement("parameter");
                        keyParam.setAttribute("id", "de.cau.cs.kieler.ksbase.editorParameter");
                        keyParam.setAttribute("value", editor.getEditor());
                        key.appendChild(keyParam);
                        keyParam = extension.createElement("parameter");
                        keyParam.setAttribute(
                                "id", "de.cau.cs.kieler.ksbase.transformationParameter");
                        keyParam.setAttribute("value", t.getTransformationName());
                        key.appendChild(keyParam);
                        bindingExtension.appendChild(key);
                    }

                    // Create handler commands for transformations:
                    Element handlerCommand = extension.createElement("handler");
                    handlerCommand.setAttribute("commandId", commandIds.get(t));
                    Element classHandler = extension.createElement("class");
                    classHandler.setAttribute("class", "de.cau.cs.kieler.ksbase.ui.handler"
                            + ".TransformationCommandHandler");
                    handlerCommand.appendChild(classHandler);
                    // Handler restrictions
                    Element handlerEnabled = extension.createElement("enabledWhen");
                    Element handlerWith = extension.createElement("with");
                    handlerWith.setAttribute("variable", "selection");
                    Element handlerIt = extension.createElement("iterate");
                    handlerIt.setAttribute("ifEmpty", "false");
                    handlerIt.setAttribute("operator", "and");
                    Element handlerTest = extension.createElement("test");
                    handlerTest.setAttribute("args", editor.getEditor()
                            + "," + t.getTransformationId());
                    handlerTest.setAttribute("forcePluginActivation", "true");
                    handlerTest.setAttribute(
                            "property", "de.cau.cs.kieler.ksbase.ui.modelTesting.isModelInstance");
                    handlerIt.appendChild(handlerTest);
                    handlerWith.appendChild(handlerIt);
                    handlerEnabled.appendChild(handlerWith);
                    handlerCommand.appendChild(handlerEnabled);
                    handlerExtension.appendChild(handlerCommand);

                }
                // Create menu extension:
                for (KSBasEMenuContribution contrib : editor.getMenuContributions()) {
                    // <extension point="org.eclipse.ui.menus>
                    Element menuContribution = extension.createElement("menuContribution");
                    menuContribution.setAttribute("locationURI", contrib.getData());
                    for (String tid : contrib.getCommands()) {
                        // Create commands for root menu
                        Node menuCommand = createElementForMenu(tid, extension, editor);
                        menuCommand.appendChild(menuVisible.cloneNode(true));
                        menuContribution.appendChild(menuCommand);
                    }
                    // create sub menus
                    for (KSBasEMenuContribution m : contrib.getMenus()) {
                        Element menu = extension.createElement("menu");
                        menu.setAttribute("id", m.getData());
                        menu.setAttribute("label", m.getLabel());
                        for (String tid : m.getCommands()) {
                            Node menuCommand;
                            if (cachedTransformationCommands.containsKey(tid)) {
                                menuCommand = cachedTransformationCommands.get(tid).cloneNode(true);
                            } else {
                                menuCommand = createElementForMenu(tid, extension, editor);
                                menuCommand.appendChild(menuVisible.cloneNode(true));
                                cachedTransformationCommands.put(tid, menuCommand.cloneNode(true));
                            }
                            menu.appendChild(menuCommand);
                        }
                        menu.appendChild(menuVisible.cloneNode(true));
                        menuContribution.appendChild(menu);
                    }

                    menuExtension.appendChild(menuContribution);
                }
                plugin.appendChild(commandExtension);
                plugin.appendChild(menuExtension);
                // plugin.appendChild(contextExtension);
                plugin.appendChild(bindingExtension);
                plugin.appendChild(handlerExtension);
                extension.appendChild(plugin);

                // Create plugin.xml
                StringWriter str = new StringWriter();
                TransformerFactory.newInstance().newTransformer().transform(
                        new DOMSource(extension), new StreamResult(str));

                // Create jar bundle
                Bundle contributorBundle = ContributorFactoryOSGi.resolve(editor.getContributor());
                
                String pluginBundle = editor.getEditor() + ".jar";
                String editorDiagramName = contributorBundle.getSymbolicName() + ".generated";

                File jarFile =
                        KSBasEUIPlugin
                                .getDefault().getStateLocation().append(pluginBundle).toFile();
                StringBuffer sbuf = new StringBuffer();
                //Now we are importing the dependencies from the contributing plug-in:  
                
                Dictionary<?, ?> dict = contributorBundle.getHeaders();
                Object dependencies = dict.get("Require-Bundle");
                //And set the rest of the manifest attributes
                sbuf.append("Require-Bundle: " + dependencies.toString() + "\n");
                sbuf.append("Bundle-RequiredExecutionEnvironment: J2SE-1.5\n");
                sbuf.append("Bundle-ActivationPolicy: lazy\n");
                sbuf.append("Bundle-Version: 0.1.0.vqualifier\n");
                sbuf.append("Manifest-Version: 1.0\n");
                sbuf.append("Bundle-SymbolicName: " + editorDiagramName + ";singleton:=true\n");
                sbuf.append("Bundle-Name: KSBasE Menu Contributions for "
                        + editor.getEditor() + "\n");
                sbuf.append("Bundle-ManifestVersion: 2\n");
                //Create manifest file
                Manifest manifest =
                        new Manifest(new ByteArrayInputStream(sbuf.toString().getBytes("UTF-8")));
                //And use is to create a new jar archive
                JarOutputStream jos = new JarOutputStream(new FileOutputStream(jarFile), manifest);
                JarEntry entry = new JarEntry("plugin.xml");
                jos.putNextEntry(entry);
                jos.write(str.toString().getBytes("UTF-8"));
                //If the editor/transformation has icons, we are now exporting them
                if (editor.getDefaultIcon() != null && editor.getDefaultIcon().length() > 0) {
                    copyResourceToJarBundle(jos, editor.getDefaultIcon(), editor.getContributor());
                }
                LinkedList<String> resources = new LinkedList<String>();
                for (Transformation t : editor.getTransformations()) {
                    if (t.getIcon() != null
                            && t.getIcon().length() > 0 && !resources.contains(t.getIcon())) {
                        resources.add(t.getIcon());
                    }
                }
                for (String resource : resources) {
                    copyResourceToJarBundle(jos, resource, editor.getContributor());
                }
                //don't forget the transformation file !
                JarEntry xtendFile = new JarEntry("src/transformations/features.ext");
                jos.putNextEntry(xtendFile);
                jos.write(editor.getExtFile().getBytes("UTF-8"));
                
                jos.flush();
                jos.close();

                DynamicBundleLoader.INSTANCE.addBundle(editor, jarFile.toURI());

            } catch (TransformerConfigurationException e) {
                e.printStackTrace();
            } catch (TransformerException e) {
                e.printStackTrace();
            } catch (TransformerFactoryConfigurationError e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();

            } catch (IOException e) {
                e.printStackTrace();
            } catch (IllegalStateException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        }
    }

    /**
     * Copies a resource, given by the contributor and the path
     * to the given jar stream.
     * @param jarBundle The target jar bundle
     * @param resourcePath The resource to copy
     * @param contributor The contributor which contains the resource
     */
    private void copyResourceToJarBundle(
            final JarOutputStream jarBundle, final String resourcePath, final IContributor contributor) {
        Assert.isNotNull(jarBundle);
        Assert.isNotNull(resourcePath);
        Assert.isNotNull(contributor);

        Bundle contributorBundle = ContributorFactoryOSGi.resolve(contributor);
        URL iconURL = FileLocator.find(contributorBundle, new Path(resourcePath), null);
        try {
            if (iconURL != null) {
                JarEntry iconEntry = new JarEntry(resourcePath);
                jarBundle.putNextEntry(iconEntry);
                InputStream in = iconURL.openStream();
                int dat = 0;
                while (dat != -1) {
                    dat = in.read();
                    jarBundle.write(dat);
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Creates all menu contributions for all existing editors.
     */
    public void createAllMenuContributions() {
        LinkedList<EditorTransformationSettings> editors =
                TransformationManager.INSTANCE.getEditors();
        createMenuForEditors(editors);
    }

    /**
     * Creates a valid plug-in project for each editor and injects it to the
     * eclipse run-time.
     * 
     * @param editors
     *            The list of editors to create the menu for
     */
    public void createMenuForEditors(final LinkedList<EditorTransformationSettings> editors) {
        if (editors != null) {
            // Iterate through editors and create extension point contents
            for (EditorTransformationSettings editor : editors) {
                createMenuForEditor(editor);
            }
        }
    }

    /**
     * Creates menu commands for menus and sub menus.
     * 
     * @param tid
     *            The transformation id
     * @param extension
     *            The base DOM document
     * @param editor
     *            The current editor
     * @return a valid DOM tree containing menu commands
     */
    private Node createElementForMenu(
            final String tid, final Document extension, final EditorTransformationSettings editor) {
        Assert.isNotNull(tid);
        Assert.isNotNull(extension);
        Assert.isNotNull(editor);

        // create menu command
        Transformation t = editor.getTransformationById(tid);
        // Menu commands
        Element menuCommand = extension.createElement("command");
        menuCommand.setAttribute("commandId", commandIds.get(t));
        // Menu icon
        if (t.getIcon() != null && t.getIcon().length() > 0) {
            menuCommand.setAttribute("icon", t.getIcon());
        } else if (editor.getDefaultIcon() != null && editor.getDefaultIcon().length() > 0) {
            menuCommand.setAttribute("icon", editor.getDefaultIcon());
        }
        // Label
        menuCommand.setAttribute("label", t.getName());
        // Set command parameters
        Element handlerParam = extension.createElement("parameter");
        handlerParam.setAttribute("name", "de.cau.cs.kieler.ksbase.editorParameter");
        handlerParam.setAttribute("value", editor.getEditor());
        menuCommand.appendChild(handlerParam);
        handlerParam = extension.createElement("parameter");
        handlerParam.setAttribute("name", "de.cau.cs.kieler.ksbase.transformationParameter");
        handlerParam.setAttribute("value", t.getTransformationName());
        menuCommand.appendChild(handlerParam);

        return menuCommand;
    }
}
