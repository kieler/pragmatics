/******************************************************************************
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
import org.eclipse.core.runtime.ContributorFactoryOSGi;
import org.eclipse.core.runtime.IContributor;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.RegistryFactory;
import org.eclipse.jface.bindings.BindingManagerEvent;
import org.eclipse.jface.bindings.IBindingManagerListener;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.keys.BindingService;
import org.eclipse.ui.keys.IBindingService;
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
 * Creates menus for all registered editor
 * transformation settings and contributes them when
 * starting an eclipse instance.
 * 
 * @author Michael Matzen - mim AT informatik.uni-kiel.de
 *
 */
@SuppressWarnings("restriction")
public final class DynamicMenuContributions {

	public static final DynamicMenuContributions instance = new DynamicMenuContributions();
	private HashMap<String, Node> cachedTransformationCommands;
	private HashMap<Transformation, String> commandIds;
	private Object token = null;

	/**
	 * Default constructor.
	 */
	private DynamicMenuContributions() {
		cachedTransformationCommands = new HashMap<String, Node>();
		commandIds = new HashMap<Transformation, String>();
	}

	/**
	 * Creates a valid plug-in project for each editor and injects it to the
	 * eclipse run-time.
	 */
	public final void createAllMenuContributions() {
		LinkedList<EditorTransformationSettings> editors = TransformationManager.instance
				.getEditors();
		// If the editors are 'null' they are maybe not initialized yet so we
		// give it a try
		if (editors == null || editors.size() == 0) {
			TransformationManager.instance.initializeTransformations();
			// still 'null' ? Ok then there are no transformations
			if (editors == null || editors.size() == 0) {
				return;
			}
		}
		try {
			try {
				IExtensionRegistry reg = RegistryFactory.getRegistry();
				token = ((ExtensionRegistry) reg).getTemporaryUserToken();
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
								&& t.getKeyboardShortcut().length() > 0 && editor.getContext() != null && editor.getContext().length() > 0) {
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
						Element handlerWith = extension.createElement("with");
						handlerWith.setAttribute("variable", "selection");
						Element handlerIt = extension.createElement("iterate");
						handlerIt.setAttribute("ifEmpty", "false");
						handlerIt.setAttribute("operator", "and");
						Element handlerTest = extension.createElement("test");
						handlerTest.setAttribute("args", editor.getEditor()
								+ "," + t.getTransformationId());
						handlerTest.setAttribute("forcePluginActivation",
								"true");
						handlerTest
								.setAttribute("property",
										"de.cau.cs.kieler.ksbase.ui.modelTesting.isModelInstance");
						handlerIt.appendChild(handlerTest);
						handlerWith.appendChild(handlerIt);
						handlerEnabled.appendChild(handlerWith);
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
								Node menuCommand;
								if (cachedTransformationCommands
										.containsKey(tid)) {
									menuCommand = cachedTransformationCommands
											.get(tid).cloneNode(true);
								} else {
									menuCommand = createElementForMenu(tid,
											extension, editor);
									menuCommand.appendChild(menuVisible
											.cloneNode(true));
									menu.appendChild(menuCommand);

									cachedTransformationCommands.put(tid,
											menuCommand.cloneNode(true));
								}
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

					System.out.println(str.toString());
					IBindingService bserv = (BindingService) PlatformUI.getWorkbench().getService(IBindingService.class);
					bserv.addBindingManagerListener(new IBindingManagerListener() {
						
						public void bindingManagerChanged(BindingManagerEvent event) {
							if (event.isActiveBindingsChanged()) {
								
							}
						}
					});
					IContributor contributor;
					if (editor.getContributor() != null) {
						contributor = editor.getContributor();
					} else {
						Bundle bundle = KSBasEUIPlugin.getDefault().getBundle();

						contributor = ContributorFactoryOSGi
								.createContributor(bundle);
					}

					ByteArrayInputStream is = new ByteArrayInputStream(str
							.toString().getBytes("UTF-8"));
					reg.addContribution(is, contributor, false, null, null,
							token);
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
			pce.printStackTrace();
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
