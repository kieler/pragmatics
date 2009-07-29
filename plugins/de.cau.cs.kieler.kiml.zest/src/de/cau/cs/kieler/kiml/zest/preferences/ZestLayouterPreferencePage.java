package de.cau.cs.kieler.kiml.zest.preferences;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import de.cau.cs.kieler.core.ui.util.FloatFieldEditor;
import de.cau.cs.kieler.kiml.zest.Activator;


/**
 * Preference page for the Zest layouters plugin.
 * 
 * @author <a href="mailto:msp@informatik.uni-kiel.de">Miro Sp&ouml;nemann</a>
 */
public class ZestLayouterPreferencePage extends FieldEditorPreferencePage
        implements IWorkbenchPreferencePage {

	/** scale factor to use for the size of each parent node */
	public static final String PREF_SCALE_BASE = "zest.scale_base";

	/**
	 * Creates a Zest preference page.
	 */
	public ZestLayouterPreferencePage() {
	    super(GRID);
	    setDescription("Preferences for the Zest Layouters.");
	}
	
	/*
	 * (non-Javadoc)
	 * @see de.cau.cs.kieler.kiml.ui.AbstractMultiLayouterPreferencePage#createFieldEditors()
	 */
	public void createFieldEditors() {
		// options group
		Group optionsGroup = new Group(this.getFieldEditorParent(), SWT.NONE);
		optionsGroup.setText("General Options:");

		FloatFieldEditor scaleBaseEditor = new FloatFieldEditor(
				PREF_SCALE_BASE, "&Scale base for parent nodes:", optionsGroup);
		
		optionsGroup.setLayout(new GridLayout(2, true));
		optionsGroup.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1));

		// add all field editors
		addField(scaleBaseEditor);
	}

    /* (non-Javadoc)
     * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
     */
    public void init(IWorkbench workbench) {
        setPreferenceStore(Activator.getDefault().getPreferenceStore());
    }

}
