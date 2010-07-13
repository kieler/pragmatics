package de.cau.cs.kieler.graphs.diagram.preferences;

import org.eclipse.draw2d.Connection;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.common.ui.preferences.ComboFieldEditor;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.preferences.ConnectionsPreferencePage;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import de.cau.cs.kieler.core.ui.figures.SplineConnection;
import de.cau.cs.kieler.graphs.diagram.part.GraphsDiagramEditor;
import de.cau.cs.kieler.graphs.diagram.part.GraphsDiagramEditorPlugin;

/**
 * @generated
 */
public class DiagramConnectionsPreferencePage extends ConnectionsPreferencePage {

	/**
	 * @generated
	 */
	public DiagramConnectionsPreferencePage() {
		setPreferenceStore(GraphsDiagramEditorPlugin.getInstance()
				.getPreferenceStore());
	}

	/**
	 * @generated
	 */
	private String SPLINE_LABEL = "Spline Mode:";

	/**
	 * @generated
	 */
	private ComboFieldEditor splineModeFieldEditor = null;

	/**
	 * @generated
	 */
	protected void addFieldEditors(Composite composite) {
		super.addFieldEditors(composite);

		// spline mode
		splineModeFieldEditor = new ComboFieldEditor(
				SplineConnection.PREF_SPLINE_MODE, SPLINE_LABEL, composite,
				ComboFieldEditor.INT_TYPE, true, 0, 0, true);
		splineModeFieldEditor.autoStorage = true;
		addField(splineModeFieldEditor);
		Combo splineModeCombo = splineModeFieldEditor.getComboControl();
		splineModeCombo.add("Off");
		splineModeCombo.add("On (cubic sections)");
		splineModeCombo.add("On (cubic sections approximation)");
	}

	/**
	 * @generated
	 */
	@Override
	public boolean performOk() {
		boolean ok = super.performOk();
		IWorkbench wb = PlatformUI.getWorkbench();
		if (wb != null) {
			IWorkbenchWindow wbw = wb.getActiveWorkbenchWindow();
			if (wbw != null) {
				IWorkbenchPage wbp = wbw.getActivePage();
				if (wbp != null) {
					IEditorReference[] ers = wbp.getEditorReferences();
					for (IEditorReference er : ers) {
						IEditorPart editor = er.getEditor(true);
						if (editor instanceof GraphsDiagramEditor) {
							GraphsDiagramEditor gde = (GraphsDiagramEditor) editor;
							applySplineMode(gde.getDiagramEditPart());
						}
					}
				}
			}
		}
		return ok;
	}

	/**
	 * @generated
	 */
	protected void applySplineMode(EditPart part) {
		if (part instanceof ConnectionNodeEditPart) {
			Connection c = ((ConnectionNodeEditPart) part)
					.getConnectionFigure();
			if (c instanceof SplineConnection) {
				((SplineConnection) c).setSplineMode(getPreferenceStore()
						.getInt(SplineConnection.PREF_SPLINE_MODE));
			}
		}
		if (part instanceof ShapeNodeEditPart) {
			ShapeNodeEditPart state = (ShapeNodeEditPart) part;
			for (Object so : state.getSourceConnections()) {
				if (so instanceof EditPart) {
					applySplineMode((EditPart) so);
				}
			}
			for (Object to : state.getSourceConnections()) {
				if (to instanceof EditPart) {
					applySplineMode((EditPart) to);
				}
			}
		}
		for (Object child : part.getChildren()) {
			if (child instanceof EditPart) {
				applySplineMode((EditPart) child);
			}
		}
	}
}
