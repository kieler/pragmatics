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
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
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
import org.w3c.dom.Node;

import de.cau.cs.kieler.ksbase.core.EditorTransformationSettings;
import de.cau.cs.kieler.ksbase.core.KSBasEMenuContribution;
import de.cau.cs.kieler.ksbase.core.Transformation;
import de.cau.cs.kieler.ksbase.core.TransformationManager;

@SuppressWarnings("restriction")
public class DynamicMenuContributions {

	public static final DynamicMenuContributions instance = new DynamicMenuContributions();
	private HashMap<String, Node> cachedTransformationCommands = new HashMap<String, Node>();
	private HashMap<Transformation, String> commandIds = new HashMap<Transformation, String>();
	private Object token = null;

	private DynamicMenuContributions() {

	}

	public void stop() {
		if (token != null) {
			IExtensionRegistry reg = RegistryFactory.getRegistry();
			reg.stop(token);
			token = null;
		}
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
				// Iterate through editors and create extension point contents
				for (EditorTransformationSettings editor : editors) {
					Document extension = javax.xml.parsers.DocumentBuilderFactory
							.newInstance().newDocumentBuilder().newDocument();
					extension.setXmlStandalone(true);
					// Create extension point elements:
					Element plugin = extension.createElement("plugin");
					Element commandExtension = extension
							.createElement("extension");
					commandExtension.setAttribute("point",
							"org.eclipse.ui.commands");
					Element bindingExtension = extension
							.createElement("extension");
					bindingExtension.setAttribute("point",
							"org.eclipse.ui.bindings");
					Element menuExtension = extension
							.createElement("extension");
					menuExtension.setAttribute("point", "org.eclipse.ui.menus");

					Element handlerExtension = extension
							.createElement("extension");
					handlerExtension.setAttribute("point",
							"org.eclipse.ui.handlers");

					// Create visibility flags for menus
					Element menuVisible = extension
							.createElement("visibleWhen");
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
						String commandID = "de.cau.cs.kieler.ksbase."
								+ editor.getEditor() + "."
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
						commandParameter = extension
								.createElement("commandParameter");
						commandParameter
								.setAttribute("id",
										"de.cau.cs.kieler.ksbase.transformationParameter");
						commandParameter.setAttribute("name", "transformation");
						command.appendChild(commandParameter);
						commandExtension.appendChild(command);

						// KeyBindings
						if (t.getKeyboardShortcut() != null
								&& t.getKeyboardShortcut().length() > 0) {
							Element key = extension.createElement("key");
							key.setAttribute("commandId", commandID);
							key.setAttribute("contextId", editor.getContext());
							key
									.setAttribute("schemeId",
											"org.eclipse.ui.defaultAcceleratorConfiguration");
							key.setAttribute("sequence", t
									.getKeyboardShortcut());
							// Set key parameters
							Element keyParam = extension
									.createElement("parameter");
							keyParam.setAttribute("id",
									"de.cau.cs.kieler.ksbase.editorParameter");
							keyParam.setAttribute("value", editor.getEditor());
							key.appendChild(keyParam);
							keyParam = extension.createElement("parameter");
							keyParam
									.setAttribute("id",
											"de.cau.cs.kieler.ksbase.transformationParameter");
							keyParam.setAttribute("value", t
									.getTransformationName());
							key.appendChild(keyParam);
							bindingExtension.appendChild(key);
						}
						// Create handler commands for transformations:
						Element handlerCommand = extension
								.createElement("handler");
						handlerCommand.setAttribute("commandId", commandIds
								.get(t));
						Element classHandler = extension.createElement("class");
						classHandler
								.setAttribute("class",
										"de.cau.cs.kieler.ksbase.ui.handler.TransformationCommandHandler");
						handlerCommand.appendChild(classHandler);
						// Handler restrictions
						Element handlerEnabled = extension
								.createElement("enabledWhen");
						Element handlerVisAnd = extension.createElement("and");
						Element handlerVisWith = extension
								.createElement("with");
						handlerVisWith.setAttribute("variable", "selection");
						Element handlerVisCount = extension
								.createElement("count");
						handlerVisCount.setAttribute("value", String.valueOf(t
								.getNumSelections()));
						handlerVisWith.appendChild(handlerVisCount);

						Element handlerElementsAnd = extension
								.createElement("and");

						if (t.getPartConfig().length > 0) {
							for (Object part : t.getPartConfig()) {
								Element handlerVisIterate = extension
										.createElement("iterate");
								handlerVisIterate
										.setAttribute("operator", "or");
								handlerVisIterate.setAttribute("ifEmpty",
										"true");

								if (part instanceof String) {
									Element handlerVisInstance = extension
											.createElement("instanceof");
									handlerVisInstance.setAttribute("value",
											(String) part);
									handlerVisIterate
											.appendChild(handlerVisInstance);
								} else if (part instanceof String[]) {
									Element handlerVisOr = extension
											.createElement("or");
									for (String inPart : (String[]) part) {
										Element handlerVisInstance = extension
												.createElement("instanceof");
										handlerVisInstance.setAttribute(
												"value", inPart);
										handlerVisOr
												.appendChild(handlerVisInstance);
									}
									handlerVisIterate.appendChild(handlerVisOr);

								}
								handlerElementsAnd
										.appendChild(handlerVisIterate);
							}
						}
						handlerVisWith.appendChild(handlerElementsAnd);
						handlerVisAnd.appendChild(handlerVisWith);
						handlerEnabled.appendChild(handlerVisAnd);
						handlerCommand.appendChild(handlerEnabled);
						handlerExtension.appendChild(handlerCommand);
					}
					// Create menu extension:
					for (KSBasEMenuContribution contrib : editor
							.getMenuContributions()) {
						// <extension point="org.eclipse.ui.menus>
						Element menuContribution = extension
								.createElement("menuContribution");
						menuContribution.setAttribute("locationURI", contrib
								.getData());
						for (String tid : contrib.getCommands()) {
							// Create commands for root menu
							Node menuCommand = createElementForMenu(tid,
									extension, editor);
							menuCommand
									.appendChild(menuVisible.cloneNode(true));
							menuContribution.appendChild(menuCommand);
						}
						// create sub menus
						for (KSBasEMenuContribution m : contrib.getMenus()) {
							Element menu = extension.createElement("menu");
							menu.setAttribute("id", m.getData());
							menu.setAttribute("label", m.getLabel());
							for (String tid : m.getCommands()) {
								Node menuCommand = createElementForMenu(tid,
										extension, editor);
								menuCommand.appendChild(menuVisible
										.cloneNode(true));
								menu.appendChild(menuCommand);

								cachedTransformationCommands.put(tid,
										menuCommand.cloneNode(true));
							}
							menu.appendChild(menuVisible.cloneNode(true));
							menuContribution.appendChild(menu);
						}

						menuExtension.appendChild(menuContribution);
					}
					plugin.appendChild(commandExtension);
					plugin.appendChild(menuExtension);
					plugin.appendChild(bindingExtension);
					plugin.appendChild(handlerExtension);
					extension.appendChild(plugin);

					StringWriter str = new StringWriter();
					TransformerFactory.newInstance().newTransformer()
							.transform(new DOMSource(extension),
									new StreamResult(str));

					//System.out.println(str.toString());

					IExtensionRegistry reg = RegistryFactory.getRegistry();

					if (editor.getContributor() != null) {
						Bundle bundle = Activator.getDefault().getBundle(
								editor.getContributor());

						IContributor contributor = ContributorFactoryOSGi
								.createContributor(bundle);
						ByteArrayInputStream is = new ByteArrayInputStream(str
								.toString().getBytes("UTF-8"));
						token = ((ExtensionRegistry) reg)
								.getTemporaryUserToken();
						reg.addContribution(is, contributor, false, null, null,
								token);

					}
				}
			} catch (TransformerConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TransformerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TransformerFactoryConfigurationError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ParserConfigurationException pce) {

		}
	}

	private Node createElementForMenu(String tid, Document extension,
			EditorTransformationSettings editor) {
		// entry has been created before?
		if (cachedTransformationCommands.containsKey(tid)) {
			return cachedTransformationCommands.get(tid).cloneNode(true);
		} else {
			// create menu command
			Transformation t = editor.getTransformationById(tid);
			// Menu commands
			Element menuCommand = extension.createElement("command");
			menuCommand.setAttribute("commandId", commandIds.get(t));
			if (t.getIcon() != null && t.getIcon().length() > 0) {
				menuCommand.setAttribute("icon", t.getIcon());
			} else if (editor.getDefaultIcon() != null
					&& editor.getDefaultIcon().length() > 0) {
				menuCommand.setAttribute("icon", editor.getDefaultIcon());
			}
			menuCommand.setAttribute("label", t.getName());
			// Set command parameters
			Element handlerParam = extension.createElement("parameter");
			handlerParam.setAttribute("name",
					"de.cau.cs.kieler.ksbase.editorParameter");
			handlerParam.setAttribute("value", editor.getEditor());
			menuCommand.appendChild(handlerParam);
			handlerParam = extension.createElement("parameter");
			handlerParam.setAttribute("name",
					"de.cau.cs.kieler.ksbase.transformationParameter");
			handlerParam.setAttribute("value", t.getTransformationName());
			menuCommand.appendChild(handlerParam);

			return menuCommand;
		}
	}

	public void removeContributionForEditor(
			final EditorTransformationSettings editor) {

	}

	/**
	 * Removes all contributions
	 */
	public void clearContributions() {

	}
}
