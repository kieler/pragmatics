package de.cau.cs.kieler.keg.diagram.preferences;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.figures.DiagramColorConstants;
import org.eclipse.gmf.runtime.diagram.ui.preferences.AppearancePreferencePage;
import org.eclipse.gmf.runtime.diagram.ui.preferences.IPreferenceConstants;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.swt.graphics.Color;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import de.cau.cs.kieler.keg.custom.KEGNode;
import de.cau.cs.kieler.keg.diagram.edit.parts.Node2EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.Node4EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.PortEditPart;
import de.cau.cs.kieler.keg.diagram.part.GraphsDiagramEditor;
import de.cau.cs.kieler.keg.diagram.part.GraphsDiagramEditorPlugin;

/**
 * @generated
 */
public class DiagramAppearancePreferencePage extends AppearancePreferencePage {

    /**
     * @generated
     */
    public DiagramAppearancePreferencePage() {
        setPreferenceStore(GraphsDiagramEditorPlugin.getInstance().getPreferenceStore());
    }

    /**
     * @generated
     */
    @Override
    public boolean performOk() {
        boolean ok = super.performOk();
        if (ok) {
            org.eclipse.swt.graphics.RGB rgb = PreferenceConverter.getColor(getPreferenceStore(),
                    IPreferenceConstants.PREF_LINE_COLOR);
            Color fgColor = new Color(null, rgb);
            rgb = PreferenceConverter.getColor(getPreferenceStore(),
                    IPreferenceConstants.PREF_FILL_COLOR);
            Color bgColor = new Color(null, rgb);
            // set the color for the open editors
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
                                applyColor(gde.getDiagramEditPart(), fgColor, bgColor);
                            }
                        }
                    }
                }
            }
        }
        return ok;
    }

    private void applyColor(final EditPart part, final Color fgColor, final Color bgColor) {
        if (part instanceof KEGNode) {
            if (part instanceof Node2EditPart) {
                Node2EditPart nodePart = (Node2EditPart) part;
                IFigure figure = nodePart.getPrimaryShape();
                figure.setForegroundColor(fgColor);
                figure.setBackgroundColor(bgColor);
            } else if (part instanceof Node4EditPart) {
                Node4EditPart nodePart = (Node4EditPart) part;
                IFigure figure = nodePart.getPrimaryShape();
                figure.setForegroundColor(fgColor);
                figure.setBackgroundColor(bgColor);
            }
        } else if (part instanceof PortEditPart) {
            PortEditPart portPart = (PortEditPart) part;
            IFigure figure = portPart.getPrimaryShape();
            figure.setForegroundColor(fgColor);
            figure.setBackgroundColor(bgColor);
        }
        for (Object child : part.getChildren()) {
            if (child instanceof EditPart) {
                applyColor((EditPart) child, fgColor, bgColor);
            }
        }
    }

    public static void initDefaults(IPreferenceStore store) {
        AppearancePreferencePage.initDefaults(store);
        // overwrite the default colors for node line and fill
        Color fillColor = new Color(null, 90, 255, 164);
        PreferenceConverter.setDefault(store, IPreferenceConstants.PREF_FILL_COLOR,
                fillColor.getRGB());
        fillColor.dispose();
        Color lineColor = DiagramColorConstants.black;
        PreferenceConverter.setDefault(store, IPreferenceConstants.PREF_LINE_COLOR,
                lineColor.getRGB());
    }
}
