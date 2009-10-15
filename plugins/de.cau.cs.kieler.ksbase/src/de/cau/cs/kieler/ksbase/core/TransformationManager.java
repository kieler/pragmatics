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
package de.cau.cs.kieler.ksbase.core;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.LinkedList;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.InvalidRegistryObjectException;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleException;
import org.osgi.framework.launch.Framework;

/**
 * The main storage and management class. Contains a list of currently
 * registered editors. Manages the creation of ExecuteTransformationCommands.
 * Handles import, export and Xtend file parsing.
 * 
 * @author Michael Matzen
 * 
 */
public class TransformationManager {

	private LinkedList<EditorTransformationSettings> registeredEditors;// The
	// currently
	// registered
	// editors
	private boolean isInitialized;

	// Thread-safe initialization
	public static final TransformationManager instance = new TransformationManager();

	/**
	 * Since this is a singleton class the constructor is private.
	 */
	private TransformationManager() {
		isInitialized = false;
		registeredEditors = new LinkedList<EditorTransformationSettings>();
	}

	/**
	 * Get the currently registered editors.
	 * 
	 * @return A list of the registered editors
	 */
	public final LinkedList<EditorTransformationSettings> getEditors() {
		return registeredEditors;
	}

	/**
	 * Tries to find an editor with it's name.
	 * 
	 * @param editor
	 *            The editor's name.
	 * @return The first editor in the list of registered editors which has the
	 *         given name
	 */
	public final EditorTransformationSettings getEditorByName(
			final String editor) {
		for (EditorTransformationSettings settings : registeredEditors) {
			if (settings.getEditor().equals(editor)) {
				return settings;
			}
		}
		return null;
	}

	/**
	 * return True if the manager is initialized.
	 */
	public final boolean isInitialized() {
		return isInitialized;
	}

	/**
	 * Adds a new editor to the list of registered editors.
	 * 
	 * @param editor
	 *            The EditorTransformationSetting that describes the editor
	 */
	public final void addEditor(final EditorTransformationSettings editor) {
		if (registeredEditors == null) {
			registeredEditors = new LinkedList<EditorTransformationSettings>();
		}
		if (editor.getEditor().length() > 0) {
			registeredEditors.add(editor);
		}
	}

	/**
	 * Adds a new editor to the list of registered editors. This class creates
	 * an empty EditorTransformationSetting with the given editorName.
	 * 
	 * @param editorName
	 *            The name of the new editor
	 * @return The newly created EditorTransformationSettings
	 */
	public final EditorTransformationSettings addEditor(final String editorName) {
		if (registeredEditors == null) {
			registeredEditors = new LinkedList<EditorTransformationSettings>();
		}
		if (editorName.length() > 0) {
			EditorTransformationSettings editor = new EditorTransformationSettings(
					editorName);
			registeredEditors.add(editor);
			return editor;
		} else
			return null;
	}

	/**
	 * Removes an editor from the registry.
	 * 
	 * @param editor
	 *            The fqn class name of the editor
	 */
	public final void removeEditor(final String editor) {
		for (int i = 0; i < registeredEditors.size(); ++i) {
			if (registeredEditors.get(i).getEditor().equals(editor)) {
				registeredEditors.remove(i);
			}
		}
	}

	/**
	 * Loads the editor settings either from the extension point settings or the
	 * preference store.
	 */
	public final void initializeTransformations() {
		registeredEditors = new LinkedList<EditorTransformationSettings>();

		IConfigurationElement[] configurations = Platform
				.getExtensionRegistry().getConfigurationElementsFor(
						"de.cau.cs.kieler.ksbase.configuration");
		
		for (IConfigurationElement settings : configurations) {
			EditorTransformationSettings editor = new EditorTransformationSettings(
					settings.getAttribute("editor"));
			editor.setContributor(settings.getContributor());
			editor.setContext(settings.getAttribute("contextId"));
			editor.setDefaultIcon(settings.getAttribute("defautlIcon"));
			editor.setModelPackageClass(settings.getAttribute("packageName"));

			IConfigurationElement[] transformations = settings
					.getChildren("transformations");
			if (transformations != null && transformations.length > 0) {
				// since we only allowed one single <transformations> child, we
				// are using it w/o iteration
				for (IConfigurationElement t : transformations[0]
						.getChildren("transformation")) {
					Transformation transformation = new Transformation(t
							.getAttribute("name"), t
							.getAttribute("transformation"));
					transformation.setKeyboardShortcut(t
							.getAttribute("keyboardShortcut"));
					transformation.setTransformationId(t
							.getAttribute("transformationId"));
					transformation.setIcon(t.getAttribute("icon"));
					editor.addTransformation(transformation);
				}
			}
			// Read menu contributions
			IConfigurationElement[] menus = settings.getChildren("menus");
			// ----DEBUG-START-----
			if (menus != null && menus.length > 0) {
				for (IConfigurationElement c : menus[0]
						.getChildren("menuContribution")) {
					System.out.println("MenuLocation @ "
							+ c.getAttribute("locationURI"));

					for (IConfigurationElement m : c.getChildren("menu")) {
						System.out.println("\tMenuContrib: "
								+ m.getAttribute("id") + " : "
								+ m.getAttribute("label"));
						for (IConfigurationElement com : m.getChildren()) {
							System.out.println("\t\tTransformation:"
									+ com.getAttribute("transformationId"));
						}

					}
					for (IConfigurationElement com : c
							.getChildren("transformationCommand")) {
						System.out.println("\tTransformation: "
								+ com.getAttribute("transformationId"));
					}
				}
			}
			// ----DEBUG-END-----

			if (menus != null && menus.length > 0) {
				// since we only allowed one single <menuContribution> child, we
				// are using it w/o iteration
				for (IConfigurationElement c : menus[0]
						.getChildren("menuContribution")) {
					KSBasEMenuContribution contrib = new KSBasEMenuContribution(
							c.getAttribute("locationURI"));
					for (IConfigurationElement m : c.getChildren("menu")) {
						KSBasEMenuContribution menu = new KSBasEMenuContribution(
								m.getAttribute("id"));
						menu.setLabel(m.getAttribute("label"));
						for (IConfigurationElement com : m.getChildren()) {
							menu.addCommand(com
									.getAttribute("transformationId"));
						}
						contrib.addSubMenu(menu);
					}
					for (IConfigurationElement com : c
							.getChildren("transformationCommand")) {
						contrib
								.addCommand(com
										.getAttribute("transformationId"));
					}
					editor.addMenuContribution(contrib);
				}
			}

			// Read Xtend file from extension point configuration
			InputStream path;
			StringBuffer contentBuffer = new StringBuffer();
			try {
				path = Platform.getBundle(settings.getContributor().getName())
						.getEntry("/" + settings.getAttribute("XtendFile"))
						.openStream();
				while (path.available() > 0) {
					contentBuffer.append((char) path.read());

				}
				editor.setExtFile(contentBuffer.toString());
			} catch (IOException e) {
				System.err
						.println("KSBasE configuration exception: Can't read Xtend file");
			}

			registeredEditors.add(editor);
		}
		isInitialized = true;
	}

	/**
	 * Tries to store settings by overwriting the existing extension point
	 * scheme. This method is executed, when a user changes the settings via the
	 * preferences pages.
	 */
	public final void storeSettings() {
		
		for (EditorTransformationSettings editor : registeredEditors) {
			try {
				Bundle b = Platform.getBundle(editor.getContributor().getName());
				b.stop();
				File f  = FileLocator.getBundleFile(b);
				if (f.isDirectory()) {
					File[] files = f.listFiles();
					for (File file : files) {
						if (file.getName().equals("plugin.xml")) {
							FileOutputStream fos = new FileOutputStream(file);
							//create plugin from settings
							String plugin = "";
							fos.write(plugin.getBytes());
							fos.flush();
							fos.close();
						}
					}
				}
			} catch (BundleException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidRegistryObjectException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
