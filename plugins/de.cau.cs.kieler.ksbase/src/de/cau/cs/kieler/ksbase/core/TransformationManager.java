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

import java.net.URI;
import java.util.LinkedList;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramCommandStack;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.handlers.HandlerUtil;

import de.cau.cs.kieler.ksbase.KSBasEPlugin;
import de.cau.cs.kieler.ksbase.ui.handler.ExecuteTransformationRequest;

/**
 * Manages the creation of ExecuteTransformationCommands and the list of
 * currently registered editors
 * 
 * @author Michael Matzen
 * 
 */
public class TransformationManager {

	private LinkedList<EditorTransformationSettings> registeredEditors;// The
	// currently
	// registered
	// editors

	// Thread-safe initialization
	private static TransformationManager instance = new TransformationManager();

	private TransformationManager() {
		registeredEditors = new LinkedList<EditorTransformationSettings>();
	}

	public static TransformationManager getInstance() {
		return instance;
	}

	public void createAndExecuteTransformationCommand(ExecutionEvent event,
			EditorTransformationSettings editor, Transformation t) {
		IEditorPart activeEditor = HandlerUtil.getActiveEditor(event);

		ISelection selection = HandlerUtil.getActiveWorkbenchWindow(event)
				.getSelectionService().getSelection();

		if (selection instanceof StructuredSelection && !selection.isEmpty()) {
			EditPart selectedElement = (EditPart) ((StructuredSelection) selection)
					.getFirstElement();

			// Create request
			ExecuteTransformationRequest request = new ExecuteTransformationRequest(
					activeEditor, t.getTransformationName(), editor
							.getExtFile(), selection, t.getNumSelections(),
					editor.getModelPackageClass());
			Command transformationCommand = selectedElement.getCommand(request);

			// gets a command stack to execute the command
			DiagramCommandStack commandStack = null;
			Object adapter = activeEditor.getAdapter(CommandStack.class);
			if (adapter instanceof DiagramCommandStack)
				commandStack = (DiagramCommandStack) adapter;
			if (commandStack == null)
				commandStack = new DiagramCommandStack(null);
			commandStack.execute(transformationCommand);

		}
	}

	public LinkedList<EditorTransformationSettings> getEditors() {
		return registeredEditors;
	}

	public EditorTransformationSettings getEditorByName(String editor) {
		for (EditorTransformationSettings settings : registeredEditors) {
			if (settings.getEditor().equals(editor))
				return settings;
		}
		return null;
	}

	public void addEditor(EditorTransformationSettings editor) {
		if (registeredEditors == null)
			registeredEditors = new LinkedList<EditorTransformationSettings>();
		if (editor.getEditor().length() > 0)
			registeredEditors.add(editor);
	}

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

	public void removeEditor(String editor) {
		for (int i = 0; i < registeredEditors.size(); ++i) {
			if (registeredEditors.get(i).getEditor().equals(editor))
				registeredEditors.remove(i);
		}
	}

	/**
	 * Loads the editor settings from preference store
	 */
	public void initializeTransformations() {
		registeredEditors = new LinkedList<EditorTransformationSettings>();
		IPreferenceStore store = KSBasEPlugin.getDefault().getPreferenceStore();

		String[] editors = store.getString(
				Messages.Preferences_RegisteredEditors).split(","); //$NON-NLS-2$
		for (String editor : editors) {
			if (editor.length() > 0) {
				EditorTransformationSettings settings = new EditorTransformationSettings(
						editor);
				settings.setModelPackageClass(store.getString(editor
						+ Messages.Preferences_ModelPackageClass));
				settings.setExtFile(store.getString(editor
						+ Messages.Preferences_ExtFile));
				settings.setMenuName(store.getString(editor
						+ Messages.Preferences_MenuName));
				settings.setMenuLocation(store.getString(editor
						+ Messages.Preferences_MenuLocation));
				settings.setToolbarLocation(store.getString(editor
						+ Messages.Preferences_ToolbarLocation));
				settings.setVisibilityFlags(store.getInt(editor
						+ Messages.Preferences_Visibility));
				settings.setDefaultIconURI(URI.create(store.getString(editor
						+ Messages.Preferences_DefaultIcon)));
				String[] transformations = store.getString(
						editor + Messages.Preferences_Transformations).split(
						","); //$NON-NLS-2$
				for (String transformation : transformations) {
					if (transformation.length() > 0) {
						String prefix = editor + "." + transformation; //$NON-NLS-1$
						Transformation t = new Transformation(transformation,
								transformation);
						t
								.setNumSelections(store
										.getInt(prefix
												+ Messages.Preferences_Transformation_Selections));
						t.setIconURI(URI.create(store.getString(prefix
								+ Messages.Preferences_Transformation_Icon)));
						t
								.setKeyboardShortcut(store
										.getString(prefix
												+ Messages.Preferences_Transformation_Shortcut));
						t
								.setPartConfig(store
										.getString(
												prefix
														+ Messages.Preferences_Transformation_PartConfig)
										.split(",")); //$NON-NLS-1$
						settings.addTransformation(t);
					}
				}
				registeredEditors.add(settings);
			}
		}
	}

	/**
	 * Stores the currently registered editor settings to the preference store
	 * Stores the values as strings: EditorName.ModelPackageName
	 * EditorName.MenuName etc.
	 */
	public void storeTransformations() {

		IPreferenceStore store = KSBasEPlugin.getDefault().getPreferenceStore();
		String editors = ""; //$NON-NLS-1$
		for (EditorTransformationSettings settings : registeredEditors) {
			String prefix = settings.getEditor();
			editors += prefix + ","; //$NON-NLS-1$
			store.setValue(prefix + Messages.Preferences_ModelPackageClass,
					settings.getModelPackageClass());
			store.setValue(prefix + Messages.Preferences_ExtFile, settings
					.getExtFile());
			store.setValue(prefix + Messages.Preferences_MenuName, settings
					.getMenuName());
			store.setValue(prefix + Messages.Preferences_MenuLocation, settings
					.getMenuLocation());
			store.setValue(prefix + Messages.Preferences_ToolbarLocation,
					settings.getToolbarLocation());
			store.setValue(prefix + Messages.Preferences_Visibility, settings
					.getVisibility());
			store.setValue(prefix + Messages.Preferences_DefaultIcon, settings
					.getDefaultIconURI().toString());
			String transformations = ""; //$NON-NLS-1$
			for (Transformation t : settings.getTransformations()) {
				String tprefix = prefix + "." + t.getName(); //$NON-NLS-1$
				transformations += t.getName() + ","; //$NON-NLS-1$
				store.setValue(tprefix
						+ Messages.Preferences_Transformation_Command, t
						.getTransformationName());
				store.setValue(tprefix
						+ Messages.Preferences_Transformation_Selections, t
						.getNumSelections());
				store.setValue(tprefix
						+ Messages.Preferences_Transformation_PartConfig, t
						.getPartConfigList());
				store.setValue(tprefix
						+ Messages.Preferences_Transformation_Icon, t
						.getIconString());
				store.setValue(tprefix
						+ Messages.Preferences_Transformation_Shortcut, t
						.getKeyboardShortcut());
			}
			if (transformations.length() > 0) {
				store.setValue(prefix + Messages.Preferences_Transformations,
						transformations.substring(0,
								transformations.length() - 1));
			}
		}
		// trunc the last ','
		store.setValue(Messages.Preferences_RegisteredEditors, editors
				.substring(0, editors.length() - 1));
	}
}
