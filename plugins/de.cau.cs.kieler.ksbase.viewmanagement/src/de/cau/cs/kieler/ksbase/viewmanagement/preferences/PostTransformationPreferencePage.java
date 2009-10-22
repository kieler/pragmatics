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
package de.cau.cs.kieler.ksbase.viewmanagement.preferences;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import de.cau.cs.kieler.ksbase.ui.TransformationUIManager;
import de.cau.cs.kieler.ksbase.viewmanagement.KSBasEViewManagementPlugin;
import de.cau.cs.kieler.ksbase.viewmanagement.combinations.KSBasECombination;
import de.cau.cs.kieler.viewmanagement.RunLogic;

/**
 * Preference page that configures the post-transformation
 * actions.
 * @author Michael Matzen - mim AT informatik.uni-kiel.de
 *
 */
public class PostTransformationPreferencePage extends PreferencePage implements
		IWorkbenchPreferencePage {

	TransformationUIManager manager;
	IPreferenceStore prefStore;
	
	public PostTransformationPreferencePage() {
		super("KSBasE Post-Transformation Settings");
		manager = TransformationUIManager.instance;
		prefStore = null;
	}
	
	@Override
	protected Control createContents(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(1, true);
		
		new Label(container, SWT.NONE).setText("Select the effects that should be executed after a transformation.");
		new Label(container, SWT.NONE).setText("If some effects have to be executed earlier, give them a lower priority");
		DataTableViewer viewer = new DataTableViewer(container,SWT.HIDE_SELECTION |
				SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL | SWT.SINGLE);
		createColumns(viewer);
		viewer.setContentProvider(new TableDataContentProvider());
		viewer.setLabelProvider(new TableDataLabelProvider());
		TableDataList input = new TableDataList(viewer);
		
		container.setLayout(layout);
		container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
				true));
		
		for (String effect : RunLogic.getEffects() ) {
			boolean active = false;
			int effectPrio = 0;
			for (Integer prio : KSBasECombination.effects.keySet()) {
				if (KSBasECombination.effects.get(prio).contains(effect)) {
					active = true;
					effectPrio = prio;
				}
			}
			TableData effData = new TableData(input, active, effect, effectPrio);
			
			input.add(effData);
		}
		viewer.setInput(input);
		container.pack();
		return null;
	}

    private void createColumns(DataTableViewer viewer) {
		String[] titles = { "Active", "Effect" ,"Priority" };
		String[] toolTip = { "Status of Effect", "Name of Effect", "Priority of Effect"};
		int[] bounds = {52, 450,45};
		
		for (int i = 0; i < titles.length; i++) {
			TreeViewerColumn column = new TreeViewerColumn(viewer, SWT.NONE);
			column.getColumn().setText(titles[i]);
			column.getColumn().setWidth(bounds[i]);
			column.getColumn().setToolTipText(toolTip[i]);
			column.getColumn().setResizable(true);
			column.getColumn().setMoveable(true);
			if (i == 0 || i == 2){
				column.getColumn().setResizable(false);
				column.setEditingSupport(new TableDataEditing(viewer, i));
			}
		}
		Tree tree = viewer.getTree();
		tree.setHeaderVisible(true);
		tree.setLinesVisible(true);
	}



    /**
     * Initializes the preferences from the preference store.
     */
	public void init(IWorkbench workbench) {
		KSBasECombination.initalizeEffects(KSBasEViewManagementPlugin.getDefault().getPreferenceStore());
	}
	
    /**
     * Stores the settings in the preference store.
     */
    @Override
    public boolean performOk() {
    	KSBasECombination.storeEffects(KSBasEViewManagementPlugin.getDefault().getPreferenceStore());
    	return true;
    }
}
