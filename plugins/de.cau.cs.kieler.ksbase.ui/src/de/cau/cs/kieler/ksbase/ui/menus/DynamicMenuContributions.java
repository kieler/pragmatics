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
import java.util.Collection;
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
import de.cau.cs.kieler.ksbase.core.KSBasETransformation;
import de.cau.cs.kieler.ksbase.core.TransformationManager;
import de.cau.cs.kieler.ksbase.ui.KSBasEUIPlugin;

/**
 * Creates menus for all registered editor transformation settings and
 * contributes them when starting an eclipse instance.
 * 
 * @author mim
 * 
 * @kieler.rating 2009-12-15 proposed yellow
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
    private HashMap<KSBasETransformation, String> commandIds;
    /**
     * Storing the key bindings for creating them AFTER the plug-in has been
     * installed and the commands Id's are registered.
     */
    private HashMap<String, String[]> keybindings;

    private static int separatorIndex = 0;

    /**
     * Default constructor.
     */
    private DynamicMenuContributions() {
        cachedTransformationCommands = new HashMap<String, Node>();
        commandIds = new HashMap<KSBasETransformation, String>();
        keybindings = new HashMap<String, String[]>();
    }

    /**
     * Creates a menu for an editor.
     * 
     * @param editorSettings
     *            The editor to create the menu for.
     */
    public void createMenuForEditor(
            final EditorTransformationSettings editorSettings) {
        Assert.isNotNull(editorSettings);

        try {
            Document extension = javax.xml.parsers.DocumentBuilderFactory
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
            Element popupExtension = extension.createElement("extension");
            popupExtension.setAttribute("point",
                    "de.cau.cs.kieler.core.ui.balloonPopupBarContribution");
            Element popupEditPolicyProvider = extension
                    .createElement("extension");
            popupEditPolicyProvider.setAttribute("point",
                    "org.eclipse.gmf.runtime.diagram.ui.editpolicyProviders");
            Element policyProvider = extension
                    .createElement("editpolicyProvider");
            policyProvider
                    .setAttribute("class",
                            "de.cau.cs.kieler.core.model.gmf.policies.BalloonPopupEditPolicyProvider");
            Element policyProviderPrio = extension.createElement("Priority");
            policyProviderPrio.setAttribute("name", "Lowest");
            policyProvider.appendChild(policyProviderPrio);
            popupEditPolicyProvider.appendChild(policyProvider);

            // Create visibility flags for menus
            Element menuVisible = extension.createElement("visibleWhen");
            menuVisible.setAttribute("checkEnabled", "false");
            Element visIterate = extension.createElement("iterate");
            visIterate.setAttribute("ifEmpty", "false");
            visIterate.setAttribute("operator", "or");
            Element visWith = extension.createElement("with");
            visWith.setAttribute("variable", "activeEditorId");
            Element visInstance = extension.createElement("equals");
            visInstance.setAttribute("value", editorSettings.getEditorId());
            visWith.appendChild(visInstance);
            visIterate.appendChild(visWith);
            menuVisible.appendChild(visIterate);
            // Create command extensions for all transformations:
            for (KSBasETransformation t : editorSettings.getTransformations()) {
                String commandID = "de.cau.cs.kieler.ksbase."
                        + editorSettings.getEditorId() + "."
                        + t.getName().replace(' ', '_');
                // store id
                commandIds.put(t, commandID);
                Element command = extension.createElement("command");
                command.setAttribute("id", commandID);
                command.setAttribute("name", t.getName());
                command.setAttribute("categoryId",
                        "de.cau.cs.kieler.ksbase.ui.ksbaseCategory");
                Element commandParameter = extension
                        .createElement("commandParameter");
                commandParameter.setAttribute("id",
                        "de.cau.cs.kieler.ksbase.editorParameter");
                commandParameter.setAttribute("name", "editor");
                command.appendChild(commandParameter);
                commandParameter = extension.createElement("commandParameter");
                commandParameter.setAttribute("id",
                        "de.cau.cs.kieler.ksbase.transformationParameter");
                commandParameter.setAttribute("name", "transformation");
                command.appendChild(commandParameter);
                commandExtension.appendChild(command);

                // KeyBindings
                if (t.getKeyboardShortcut() != null
                        && t.getKeyboardShortcut().length() > 0
                        && editorSettings.getContext() != null
                        && editorSettings.getContext().length() > 0) {

                    keybindings.put(
                            commandID,
                            new String[] { editorSettings.getContext(),
                                    t.getKeyboardShortcut(),
                                    t.getTransformation() });

                    Element key = extension.createElement("key");
                    key.setAttribute("commandId", commandID);
                    key.setAttribute("contextId", editorSettings.getContext());
                    key.setAttribute("schemeId",
                            "org.eclipse.ui.defaultAcceleratorConfiguration");
                    key.setAttribute("sequence", t.getKeyboardShortcut());
                    // Set key parameters
                    Element keyParam = extension.createElement("parameter");
                    keyParam.setAttribute("id",
                            "de.cau.cs.kieler.ksbase.editorParameter");
                    keyParam.setAttribute("value", editorSettings.getEditorId());
                    key.appendChild(keyParam);
                    keyParam = extension.createElement("parameter");
                    keyParam.setAttribute("id",
                            "de.cau.cs.kieler.ksbase.transformationParameter");
                    keyParam.setAttribute("value", t.getTransformation());
                    key.appendChild(keyParam);
                    bindingExtension.appendChild(key);
                }

                // Create handler commands for transformations:
                Element handlerCommand = extension.createElement("handler");
                handlerCommand.setAttribute("commandId", commandIds.get(t));
                Element classHandler = extension.createElement("class");
                // Check if the editor has a custom command handler
                if (editorSettings.getCommandHandler().length() == 0) {
                    classHandler.setAttribute("class",
                            "de.cau.cs.kieler.ksbase.ui.handler"
                                    + ".TransformationCommandHandler");
                } else {
                    classHandler.setAttribute("class",
                            editorSettings.getCommandHandler());
                }
                handlerCommand.appendChild(classHandler);
                // Handler restrictions
                Element handlerEnabled = extension.createElement("enabledWhen");
                Element handlerWith = extension.createElement("with");
                handlerWith.setAttribute("variable", "selection");
                Element handlerIt = extension.createElement("iterate");
                handlerIt.setAttribute("ifEmpty", "false");
                handlerIt.setAttribute("operator", "and");
                Element handlerTest = extension.createElement("test");
                handlerTest.setAttribute("args", editorSettings.getEditorId()
                        + "," + t.getTransformationId());
                handlerTest.setAttribute("forcePluginActivation", "true");
                handlerTest
                        .setAttribute("property",
                                "de.cau.cs.kieler.ksbase.ui.modelTesting.isModelInstance");
                handlerIt.appendChild(handlerTest);
                handlerWith.appendChild(handlerIt);
                handlerEnabled.appendChild(handlerWith);
                if (editorSettings.isCheckVisibility()) {
                    handlerCommand.appendChild(handlerEnabled);
                }
                handlerExtension.appendChild(handlerCommand);

            }
            // Create menu extension:
            for (KSBasEMenuContribution contrib : editorSettings
                    .getMenuContributions()) {
                // PopupBar contributions need a special handling:
                if (contrib.getData().contains("popupBar:")) {
                    for (String tid : contrib.getCommands()) {
                        Element popupContribution = extension
                                .createElement("PopupBarElement");
                        popupContribution
                                .setAttribute("class",
                                        "de.cau.cs.kieler.ksbase.ui.menus.KSbasEBalloonPopup");

                        Element editorAttrib = extension
                                .createElement("Attribute");
                        editorAttrib.setAttribute("key", "editorId");
                        editorAttrib.setAttribute("value",
                                editorSettings.getEditorId());
                        popupContribution.appendChild(editorAttrib);

                        Element transAttrib = extension
                                .createElement("Attribute");
                        transAttrib.setAttribute("key", "transformationId");
                        transAttrib.setAttribute("value", tid);
                        popupContribution.appendChild(transAttrib);
                        popupExtension.appendChild(popupContribution);
                    }

                } else {
                    // <extension point="org.eclipse.ui.menus>
                    Element menuContribution = extension
                            .createElement("menuContribution");
                    menuContribution.setAttribute("locationURI",
                            contrib.getData());
                    String separatorName = "de.cau.cs.kieler.ksbase.separator01";
                    addSeparator(extension, menuContribution, separatorName);

                    for (String tid : contrib.getCommands()) {
                        // only create contents for valid transformationIDs
                        // XXX
                        if (tid.equals("_SEPARATOR")) {
                            addSeparator(extension, menuContribution,
                                    "de.cau.cs.kieler.ksbase.separator"
                                            + separatorIndex++);
                        } else if (editorSettings.getTransformationById(tid) != null) {
                            // Create commands for root menu
                            Node menuCommand = createElementForMenu(tid,
                                    extension, editorSettings);
                            Node menuCommandVisible = menuVisible
                                    .cloneNode(true);
                            // Add visibility for popup
                            if (contrib.getData().contains("popup:")) {
                                Element handlerAnd = extension
                                        .createElement("and");
                                // Create visibility flags for menus
                                Element handlerWith = extension
                                        .createElement("with");
                                handlerWith.setAttribute("variable",
                                        "selection");
                                Element handlerIt = extension
                                        .createElement("iterate");
                                handlerIt.setAttribute("ifEmpty", "false");
                                handlerIt.setAttribute("operator", "and");
                                Element handlerTest = extension
                                        .createElement("test");
                                handlerTest.setAttribute("args",
                                        editorSettings.getEditorId() + ","
                                                + tid);
                                handlerTest.setAttribute(
                                        "forcePluginActivation", "true");
                                handlerTest
                                        .setAttribute(
                                                "property",
                                                "de.cau.cs.kieler.ksbase.ui."
                                                        + "modelTesting.isModelInstance");
                                handlerIt.appendChild(handlerTest);
                                handlerWith.appendChild(handlerIt);

                                handlerAnd.appendChild(menuCommandVisible
                                        .getFirstChild());
                                handlerAnd.appendChild(handlerWith);
                                menuCommandVisible.appendChild(handlerAnd);
                            }
                            if (editorSettings.isCheckVisibility()) {
                                menuCommand.appendChild(menuCommandVisible
                                        .cloneNode(true));
                            }
                            menuContribution.appendChild(menuCommand);
                        }
                    }
                    // create sub menus
                    for (KSBasEMenuContribution m : contrib.getMenus()) {
                        Element menu = extension.createElement("menu");
                        menu.setAttribute("id", m.getData());
                        menu.setAttribute("label", m.getLabel());
                        for (String tid : m.getCommands()) {
                            // only create contents for valid
                            // transformationIDs
                            // XXX
                            if (tid.equals("_SEPARATOR")) {
                                addSeparator(extension, menuContribution,
                                        "de.cau.cs.kieler.ksbase.separator"
                                                + separatorIndex++);
                            } else if (editorSettings
                                    .getTransformationById(tid) != null) {

                                Node menuCommand;
                                if (cachedTransformationCommands
                                        .containsKey(tid)) {
                                    menuCommand = cachedTransformationCommands
                                            .get(tid).cloneNode(true);
                                } else {
                                    menuCommand = createElementForMenu(tid,
                                            extension, editorSettings);
                                    menuCommand.appendChild(menuVisible
                                            .cloneNode(true));
                                    cachedTransformationCommands.put(tid,
                                            menuCommand.cloneNode(true));
                                }
                                menu.appendChild(menuCommand);
                            }
                        }
                        if (editorSettings.isCheckVisibility()) {
                            menu.appendChild(menuVisible.cloneNode(true));
                        }
                        menuContribution.appendChild(menu);
                    }

                    separatorName = "de.cau.cs.kieler.ksbase.separator02";
                    addSeparator(extension, menuContribution, separatorName);
                    menuExtension.appendChild(menuContribution);
                }
            }
            plugin.appendChild(commandExtension);
            plugin.appendChild(menuExtension);
            // plugin.appendChild(contextExtension);
            plugin.appendChild(bindingExtension);
            plugin.appendChild(handlerExtension);
            plugin.appendChild(popupExtension);
            plugin.appendChild(popupEditPolicyProvider);

            extension.appendChild(plugin);

            // Create plugin.xml
            StringWriter str = new StringWriter();
            TransformerFactory.newInstance().newTransformer()
                    .transform(new DOMSource(extension), new StreamResult(str));

            // Create jar bundle
            Bundle contributorBundle = null;
            if (editorSettings.getContributor() != null) {
                contributorBundle = ContributorFactoryOSGi
                        .resolve(editorSettings.getContributor());
            } else {
                contributorBundle = KSBasEUIPlugin.getDefault().getBundle();
            }
            String pluginBundle = editorSettings.getEditorId() + ".jar";

            String editorDiagramName = contributorBundle.getSymbolicName()
                    + "." + editorSettings.getEditorId() + ".generated";

            File jarFile = KSBasEUIPlugin.getDefault().getStateLocation()
                    .append(pluginBundle).toFile();
            StringBuffer sbuf = new StringBuffer();
            // Now we are importing the dependencies from the contributing
            // plug-in:
            Dictionary<?, ?> dict = contributorBundle.getHeaders();
            Object dependencies = dict.get("Require-Bundle");
            // This is important! We have do split the dependencies
            // to new lines because the string line is limited!
            String depString = dependencies.toString();
            depString = depString.replace(",", ",\n ");
            // We really need those deps:
            if (!depString.contains("de.cau.cs.kieler.ksbase.ui")) {
                depString += ",\n de.cau.cs.kieler.ksbase.ui";
            }
            if (!depString.contains("de.cau.cs.kieler.core.ui")) {
                depString += ",\n de.cau.cs.kieler.core.ui";
            }
            if (!depString.contains("org.eclipse.gmf.runtime.diagram.ui")) {
                depString += ",\n org.eclipse.gmf.runtime.diagram.ui";
            }
            if (editorSettings.getContributor() != null
                    && !depString.contains(editorSettings.getContributor()
                            .getName())) {
                depString += ",\n " + editorSettings.getContributor().getName();
            }
            // And set the rest of the manifest attributes

            sbuf.append("Require-Bundle: " + depString + "\n");
            sbuf.append("Bundle-RequiredExecutionEnvironment: J2SE-1.5\n");
            sbuf.append("Bundle-ActivationPolicy: lazy\n");
            sbuf.append("Bundle-Version: 0.1.0.vqualifier\n");
            sbuf.append("Manifest-Version: 1.0\n");
            sbuf.append("Bundle-SymbolicName: " + editorDiagramName
                    + ";singleton:=true\n");
            sbuf.append("Bundle-Name: KSBasE Menu Contributions for "
                    + editorSettings.getEditorId() + "\n");
            sbuf.append("Bundle-ManifestVersion: 2\n");
            // Create manifest file
            Manifest manifest = new Manifest(new ByteArrayInputStream(sbuf
                    .toString().getBytes("UTF-8")));

            JarOutputStream jos = new JarOutputStream(new FileOutputStream(
                    jarFile), manifest);
            JarEntry entry = new JarEntry("plugin.xml");
            jos.putNextEntry(entry);
            jos.write(str.toString().getBytes("UTF-8"));
            // If the editor/transformation has icons, we are now exporting
            // them
            if (editorSettings.getDefaultIcon() != null
                    && editorSettings.getDefaultIcon().length() > 0) {
                copyResourceToJarBundle(jos, editorSettings.getDefaultIcon(),
                        editorSettings.getContributor());
            }
            LinkedList<String> resources = new LinkedList<String>();
            for (KSBasETransformation t : editorSettings.getTransformations()) {
                if (t.getIcon() != null && t.getIcon().length() > 0
                        && !resources.contains(t.getIcon())) {
                    resources.add(t.getIcon());
                }
            }
            // Copy the source resources to the generated bundle if the
            // contributor is valid
            if (editorSettings.getContributor() != null) {
                for (String resource : resources) {
                    copyResourceToJarBundle(jos, resource,
                            editorSettings.getContributor());
                }
            }
            // don't forget the transformation file !
            JarEntry transformationFile = new JarEntry(
                    "src/transformations/features."
                            + editorSettings.getFramework().getFileExtension());
            jos.putNextEntry(transformationFile);
            jos.write(editorSettings.getTransformationFile().getBytes("UTF-8"));

            jos.flush();
            jos.close();

            DynamicBundleLoader.INSTANCE.addBundle(editorSettings,
                    jarFile.toURI());

        } catch (TransformerConfigurationException e) {
            KSBasEUIPlugin.getDefault().logError(
                    "Bundle could not be created: Invalid xml file.");
        } catch (TransformerException e) {
            KSBasEUIPlugin.getDefault().logError(
                    "Bundle could not be created: Invalid xml file.");
        } catch (TransformerFactoryConfigurationError e) {
            KSBasEUIPlugin.getDefault().logError(
                    "Bundle could not be created: Invalid xml file.");
        } catch (UnsupportedEncodingException e) {
            KSBasEUIPlugin.getDefault().logError(
                    "Bundle could not be created: Unsupported encoding.");

        } catch (IOException e) {
            KSBasEUIPlugin.getDefault().logError(
                    "Bundle could not be created: IOException");
        } catch (IllegalStateException e) {
            KSBasEUIPlugin.getDefault().logError(
                    "Bundle could not be created: Invalid state.");
        } catch (ParserConfigurationException pce) {
            KSBasEUIPlugin.getDefault().logError(
                    "Bundle could not be created: Parser error.");
        }
    }

    /**
     * Adds a separator with the given name to the provided menuContribution.
     * 
     * @param extension
     * @param menuContribution
     * @param separatorName
     */
    private void addSeparator(final Document extension,
            final Element menuContribution, final String separatorName) {
        Element separator = extension.createElement("separator");
        separator.setAttribute("name", separatorName);
        separator.setAttribute("visible", "true");
        menuContribution.appendChild(separator);
    }

    /**
     * Copies a resource, given by the contributor and the path to the given jar
     * stream.
     * 
     * @param jarBundle
     *            The target jar bundle
     * @param resourcePath
     *            The resource to copy
     * @param contributor
     *            The contributor which contains the resource
     */
    private void copyResourceToJarBundle(final JarOutputStream jarBundle,
            final String resourcePath, final IContributor contributor) {
        Assert.isNotNull(jarBundle);
        Assert.isNotNull(resourcePath);
        Assert.isNotNull(contributor);

        Bundle contributorBundle = ContributorFactoryOSGi.resolve(contributor);
        URL iconURL = FileLocator.find(contributorBundle,
                new Path(resourcePath), null);
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
            KSBasEUIPlugin.getDefault().logError(
                    "Bundle could not be created: Error while storing resource ("
                            + resourcePath + ").");
        }
    }

    /**
     * Creates all menu contributions for all existing editors.
     */
    public void createAllMenuContributions() {
        createMenuForEditors(TransformationManager.INSTANCE.getEditors());
    }

    /**
     * Creates a valid plug-in project for each editor and injects it to the
     * eclipse run-time.
     * 
     * @param collection
     *            The list of editors to create the menu for
     */
    public void createMenuForEditors(
            final Collection<EditorTransformationSettings> collection) {
        if (collection != null) {
            // Iterate through editors and create extension point contents
            for (EditorTransformationSettings editor : collection) {
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
    private Node createElementForMenu(final String tid,
            final Document extension, final EditorTransformationSettings editor) {
        assert (tid != null);
        assert (extension != null);
        assert (editor != null);

        // create menu command
        KSBasETransformation t = editor.getTransformationById(tid);

        // Menu commands
        Element menuCommand = extension.createElement("command");
        menuCommand.setAttribute("commandId", commandIds.get(t));
        // Menu icon
        if (t.getIcon() != null && t.getIcon().length() > 0) {
            menuCommand.setAttribute("icon", t.getIcon());
        } else if (editor.getDefaultIcon() != null
                && editor.getDefaultIcon().length() > 0) {
            menuCommand.setAttribute("icon", editor.getDefaultIcon());
        }
        // Label
        menuCommand.setAttribute("label", t.getName());
        // ToolTip
        menuCommand.setAttribute("tooltip", t.getToolTip());
        // Set command parameters
        Element handlerParam = extension.createElement("parameter");
        handlerParam.setAttribute("name",
                "de.cau.cs.kieler.ksbase.editorParameter");
        handlerParam.setAttribute("value", editor.getEditorId());
        menuCommand.appendChild(handlerParam);
        handlerParam = extension.createElement("parameter");
        handlerParam.setAttribute("name",
                "de.cau.cs.kieler.ksbase.transformationParameter");
        handlerParam.setAttribute("value", t.getTransformation());
        menuCommand.appendChild(handlerParam);

        return menuCommand;
    }
}
