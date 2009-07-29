package de.cau.cs.kieler.dataflow.diagram.preferences;

import org.eclipse.gmf.runtime.diagram.ui.preferences.ConnectionsPreferencePage;

import de.cau.cs.kieler.dataflow.diagram.part.DataflowDiagramEditorPlugin;

/**
 * @generated
 */
public class DiagramConnectionsPreferencePage extends ConnectionsPreferencePage {

	/**
	 * @generated
	 */
	public DiagramConnectionsPreferencePage() {
		setPreferenceStore(DataflowDiagramEditorPlugin.getInstance()
				.getPreferenceStore());
	}
}
