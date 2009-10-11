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
package de.cau.cs.kieler.ksbase.core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;

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
	 * Since this is a singleton class the constructor is private
	 */
	private TransformationManager() {
		isInitialized = false;
		registeredEditors = new LinkedList<EditorTransformationSettings>();
	}

	/**
	 * Get the currently registered editors
	 * 
	 * @return A list of the registered editors
	 */
	public LinkedList<EditorTransformationSettings> getEditors() {
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
	public EditorTransformationSettings getEditorByName(String editor) {
		for (EditorTransformationSettings settings : registeredEditors) {
			if (settings.getEditor().equals(editor))
				return settings;
		}
		return null;
	}

	public boolean isInitalized() {
		return isInitialized;
	}

	/**
	 * Adds a new editor to the list of registered editors.
	 * 
	 * @param editor
	 *            The EditorTransformationSetting that describes the editor
	 */
	public void addEditor(EditorTransformationSettings editor) {
		if (registeredEditors == null)
			registeredEditors = new LinkedList<EditorTransformationSettings>();
		if (editor.getEditor().length() > 0)
			registeredEditors.add(editor);
	}

	/**
	 * Adds a new editor to the list of registered editors. This class creates
	 * an empty EditorTransformationSetting with the given editorName.
	 * 
	 * @param editorName
	 *            The name of the new editor
	 * @return The newly created EditorTransformationSettings
	 */
	public EditorTransformationSettings addEditor(String editorName) {
		if (registeredEditors == null)
			registeredEditors = new LinkedList<EditorTransformationSettings>();
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
	public void removeEditor(String editor) {
		for (int i = 0; i < registeredEditors.size(); ++i) {
			if (registeredEditors.get(i).getEditor().equals(editor))
				registeredEditors.remove(i);
		}
	}

	/**
	 * Loads the editor settings either from the extension point settings or the
	 * preference store
	 */
	public void initializeTransformations() {
		registeredEditors = new LinkedList<EditorTransformationSettings>();

		IConfigurationElement[] configurations = Platform
				.getExtensionRegistry().getConfigurationElementsFor(
						"de.cau.cs.kieler.ksbase.configuration");
		for (IConfigurationElement settings : configurations) {

			EditorTransformationSettings editor = new EditorTransformationSettings(
					settings.getAttribute("editor"));
			editor.setContributor(settings.getContributor().getName());
			editor.setContext(settings.getAttribute("contextId"));
			editor.setDefaultIcon(settings.getAttribute("defautlIcon"));
			editor.setModelPackageClass(settings.getAttribute("packageName"));
			editor.setPerformAutoLayout(true);

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
					transformation.setNumSelections(Integer.valueOf(t
							.getAttribute("selectionCount")));
					transformation.setKeyboardShortcut(t
							.getAttribute("keyboardShortcut"));
					transformation.setTransformationID(t
							.getAttribute("transformationId"));
					transformation.setIcon(t.getAttribute("icon"));
					editor.addTransformation(transformation);
				}
			}
			// Read menu contributions
			IConfigurationElement[] menus = settings.getChildren("menus");
			if (menus != null && menus.length > 0) {
				// since we only allowed one single <transformations> child, we
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
			IConfigurationElement[] mappings = settings.getChildren("parameterDiagramMapping");
			for (IConfigurationElement mapping : mappings) {
				hier weiter
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
	 * Imports settings from an external file
	 * 
	 * @param file
	 *            The absolute file name
	 */
	@SuppressWarnings("unchecked")
	public void importSettings(String file) {
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(
					file));
			registeredEditors = new LinkedList<EditorTransformationSettings>();
			registeredEditors = (LinkedList<EditorTransformationSettings>) in
					.readObject();
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Exports the settings to an external file
	 * 
	 * @param file
	 *            The absolute file name
	 */
	public void exportSettings(String file) {
		try {
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream(file));
			out.writeObject(registeredEditors);
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
