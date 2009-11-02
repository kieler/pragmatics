/*
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
 */
package de.cau.cs.kieler.klodd.ui.preferences;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.jface.preference.RadioGroupFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import de.cau.cs.kieler.klodd.hierarchical.HierarchicalDataflowLayoutProvider;
import de.cau.cs.kieler.klodd.ui.KloddUIPlugin;
import de.cau.cs.kieler.klodd.ui.Messages;

/**
 * Preference page for all KLoDD layout algorithms.
 * 
 * @author <a href="mailto:msp@informatik.uni-kiel.de">Miro Sp&ouml;nemann</a>
 */
public class KloddLayouterPreferencePage extends FieldEditorPreferencePage implements
        IWorkbenchPreferencePage {

    /**
     * Creates the preference page.
     */
    public KloddLayouterPreferencePage() {
        super(FLAT);
        setDescription(Messages.getString("klodd.ui.0")); //$NON-NLS-1$
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createFieldEditors() {
        // options group for hierarchical layouter
        Composite parent = getFieldEditorParent();
        Group hieraGroup = new Group(parent, SWT.NONE);
        hieraGroup.setText(Messages.getString("klodd.ui.1")); //$NON-NLS-1$

        FieldEditor balanceEditor = new BooleanFieldEditor(
                HierarchicalDataflowLayoutProvider.PREF_BALANCE_VS_SIZE, Messages
                        .getString("klodd.ui.15"), hieraGroup);
        addField(balanceEditor);

        FieldEditor cycleRemEditor = new RadioGroupFieldEditor(
                HierarchicalDataflowLayoutProvider.PREF_CYCLE_REM,
                Messages.getString("klodd.ui.5"), 1, new String[][] {//$NON-NLS-1$
                    {Messages.getString("klodd.ui.6"), //$NON-NLS-1$
                        HierarchicalDataflowLayoutProvider.VAL_DFS_CYCLE_REM },
                    {Messages.getString("klodd.ui.7"), //$NON-NLS-1$
                        HierarchicalDataflowLayoutProvider.VAL_GREEDY_CYCLE_REM } }, hieraGroup,
                false);
        addField(cycleRemEditor);

        FieldEditor layerAssEditor = new RadioGroupFieldEditor(
                HierarchicalDataflowLayoutProvider.PREF_LAYER_ASS,
                Messages.getString("klodd.ui.8"), 1, new String[][] {//$NON-NLS-1$
                    {Messages.getString("klodd.ui.9"), //$NON-NLS-1$
                        HierarchicalDataflowLayoutProvider.VAL_LONGP_LAYER_ASS },
                    {Messages.getString("klodd.ui.10"), //$NON-NLS-1$
                        HierarchicalDataflowLayoutProvider.VAL_BAL_LAYER_ASS } }, hieraGroup,
                false);
        addField(layerAssEditor);

        FieldEditor edgeRouteEditor = new RadioGroupFieldEditor(
                HierarchicalDataflowLayoutProvider.PREF_LAYER_EDGEROUTER, Messages
                        .getString("klodd.ui.12"), 1, new String[][] {//$NON-NLS-1$
                    {Messages.getString("klodd.ui.13"), //$NON-NLS-1$
                        HierarchicalDataflowLayoutProvider.VAL_SORT_LAYER_EDGEROUTER },
                    {Messages.getString("klodd.ui.14"), //$NON-NLS-1$
                        HierarchicalDataflowLayoutProvider.VAL_TOPO_LAYER_EDGEROUTER } },
                hieraGroup, false);
        addField(edgeRouteEditor);

        Composite crossRedPassesParent = new Composite(hieraGroup, SWT.NONE);
        IntegerFieldEditor crossRedPassesEditor = new IntegerFieldEditor(
                HierarchicalDataflowLayoutProvider.PREF_CROSSRED_PASSES, Messages
                        .getString("klodd.ui.11"), crossRedPassesParent, 3); //$NON-NLS-1$
        crossRedPassesEditor.setValidRange(1, 999);
        crossRedPassesParent.setLayout(new GridLayout(2, false));
        crossRedPassesParent.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1));
        addField(crossRedPassesEditor);

        hieraGroup.setLayout(new GridLayout(1, false));
        parent.setLayout(new FillLayout());

        // options group for orthogonal layouter
        // Group orthoGroup = new Group(this.getFieldEditorParent(), SWT.NONE);
        //    orthoGroup.setText(Messages.getString("klodd.ui.3")); //$NON-NLS-1$
        //
        // orthoGroup.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false,
        // 1, 1));
        // orthoGroup.setLayout(new GridLayout(2, false));
    }

    /**
     * {@inheritDoc}
     */
    public void init(final IWorkbench workbench) {
        setPreferenceStore(KloddUIPlugin.getDefault().getPreferenceStore());
    }

}
