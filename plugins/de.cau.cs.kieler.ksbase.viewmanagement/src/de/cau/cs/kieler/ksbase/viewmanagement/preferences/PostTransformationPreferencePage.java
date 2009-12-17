/**
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
package de.cau.cs.kieler.ksbase.viewmanagement.preferences;

import java.util.Vector;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import de.cau.cs.kieler.ksbase.viewmanagement.KSBasEViewManagementPlugin;
import de.cau.cs.kieler.ksbase.viewmanagement.combinations.KSBasECombination;
import de.cau.cs.kieler.viewmanagement.RunLogic;

/**
 * Preference page that configures the post-transformation actions.
 * 
 * @author Michael Matzen - mim AT informatik.uni-kiel.de
 * 
 */
public class PostTransformationPreferencePage extends PreferencePage implements
        IWorkbenchPreferencePage {

    /** List of available and active effects. **/
    private List availableList, activeList;

    /** Buttons used to add and remove effects. **/
    private Button addEffect, removeEffect, addAll, removeAll;

    /** Image for the UP button. **/
    private static final Image UP_ARROW = AbstractUIPlugin.imageDescriptorFromPlugin(
            "de.cau.cs.kieler.ksbase.viewmanagement", "icons/up.png").createImage();

    /** Image for the DOWN button. **/
    private static final Image DOWN_ARROW = AbstractUIPlugin.imageDescriptorFromPlugin(
            "de.cau.cs.kieler.ksbase.viewmanagement", "icons/down.png").createImage();

    /**
     * Default constructor.
     */
    public PostTransformationPreferencePage() {
        super("KSBasE Post-Transformation Settings");
    }

    /**
     * Creates the contents of the preference page.
     * 
     * @param parent
     *            The parent of this preference page.
     * @return The created controls.
     */
    @Override
    protected Control createContents(final Composite parent) {
        new Label(parent, SWT.NONE)
                .setText("Select the effects that should be executed after a transformation.");
        new Label(parent, SWT.NONE).setText("The order of execution is controlled by"
                + " the order of the effects in the active effects list");

        // new Label(container, SWT.NONE);
        Composite container = new Composite(parent, SWT.NONE);
        container.setLayout(new FillLayout(SWT.VERTICAL));// (new GridLayout(1,true));
        // Initialize RunLogic if not done already
        if (!RunLogic.getInstance().getState()) {
            RunLogic.getInstance().registerListeners();
        }
        Vector<String> activeEffects = KSBasECombination.getEffects();

        // Available effects group
        Group availableGroup = new Group(container, SWT.SHADOW_ETCHED_IN);
        availableGroup.setText("Available Effects");
        availableList = new List(availableGroup, SWT.SINGLE | SWT.V_SCROLL);
        for (String effect : RunLogic.getInstance().getEffectsAsText()) {
            if (!activeEffects.contains(effect)) {
                availableList.add(effect);
            }
        }
        availableList.addMouseListener(new MouseListener() {
            
            public void mouseUp(final MouseEvent e) {
                //nothing
            }
            
            public void mouseDown(final MouseEvent e) {
                //nothing
            }
            
            public void mouseDoubleClick(final MouseEvent e) {
                for (String item : availableList.getSelection()) {
                    activeList.add(item);
                    KSBasECombination.addEffect(item, activeList.indexOf(item));
                    availableList.remove(item);
                }
                checkButtonStatus();
            }
        });
        availableList
                .setLayoutData(new GridData(GridData.BEGINNING, GridData.BEGINNING, true, true));
        // availableList.pack();

        Composite availableButtonComposite = new Composite(availableGroup, SWT.NONE);
        addEffect = new Button(availableButtonComposite, SWT.PUSH);
        addEffect.setText("Add");
        addEffect.setLayoutData(new GridData(SWT.TOP, SWT.LEFT, false, false));
        addEffect.addSelectionListener(new SelectionListener() {

            public void widgetSelected(final SelectionEvent e) {
                for (String item : availableList.getSelection()) {
                    activeList.add(item);
                    KSBasECombination.addEffect(item, activeList.indexOf(item));
                    availableList.remove(item);
                }
                checkButtonStatus();
            }

            public void widgetDefaultSelected(final SelectionEvent e) {
            }
        });
        addAll = new Button(availableButtonComposite, SWT.PUSH);
        addAll.setText("Add All");
        addAll.setLayoutData(new GridData(SWT.TOP, SWT.RIGHT, false, false));
        addAll.addSelectionListener(new SelectionListener() {

            public void widgetSelected(final SelectionEvent e) {
                for (String item : availableList.getItems()) {
                    activeList.add(item);
                    KSBasECombination.addEffect(item, activeList.indexOf(item));
                }
                availableList.removeAll();
                checkButtonStatus();
            }

            public void widgetDefaultSelected(final SelectionEvent e) {
            }
        });

        availableButtonComposite.setLayout(new GridLayout(2, true));

        availableGroup.setLayout(new GridLayout(1, true));
        /*
         * availableGroup.setLayoutData(new GridData(GridData.BEGINNING, GridData.BEGINNING, true,
         * true));
         */
        availableGroup.pack();

        // Active effects group
        Group activeGroup = new Group(container, SWT.SHADOW_ETCHED_IN);
        activeGroup.setText("Active Effects");
        Composite activeListComposite = new Composite(activeGroup, SWT.NONE);

        activeList = new List(activeListComposite, SWT.SINGLE | SWT.V_SCROLL);
        for (String effect : activeEffects) {
            activeList.add(effect);

        }
        activeList.addMouseListener(new MouseListener() {
            
            public void mouseUp(final MouseEvent e) {
                //nothing
            }
            
            public void mouseDown(final MouseEvent e) {
                //nothing
            }
            
            public void mouseDoubleClick(final MouseEvent e) {
                for (String item : activeList.getSelection()) {
                    availableList.add(item);
                    KSBasECombination.removeEffect(item);
                    activeList.remove(item);
                }
                checkButtonStatus();
            }
        });
        
        activeList.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        // activeList.pack(true);
        Composite prioButtonComposite = new Composite(activeListComposite, SWT.NONE);
        Button up = new Button(prioButtonComposite, SWT.PUSH);
        up.setImage(UP_ARROW);
        up.addSelectionListener(new SelectionListener() {

            public void widgetSelected(final SelectionEvent e) {
                if (activeList.getSelectionCount() == 1) {
                    String element = activeList.getSelection()[0];
                    int index = activeList.indexOf(element) - 1;
                    activeList.remove(element);

                    try {
                        activeList.add(element, index);
                    } catch (IllegalArgumentException excep) {
                        // if we are receiving an illegal argument exception, we
                        // simply add the element to the end of the list because
                        // there are now other elements
                        activeList.add(element);
                    }
                    KSBasECombination.changeIndex(element, activeList.indexOf(element));
                    activeList.setSelection(activeList.indexOf(element));
                }
            }

            public void widgetDefaultSelected(final SelectionEvent e) {
            }
        });

        Button down = new Button(prioButtonComposite, SWT.PUSH);
        down.setImage(DOWN_ARROW);
        down.addSelectionListener(new SelectionListener() {

            public void widgetSelected(final SelectionEvent e) {
                if (activeList.getSelectionCount() == 1) {
                    String element = activeList.getSelection()[0];
                    int index = activeList.indexOf(element) + 1;
                    activeList.remove(element);
                    try {
                        activeList.add(element, index);
                    } catch (IllegalArgumentException excep) {
                        // if we are receiving an illegal argument exception, we
                        // simply add the element to the end of the list because
                        // there are now other elements
                        activeList.add(element);
                    }
                    KSBasECombination.changeIndex(element, activeList.indexOf(element));
                    activeList.setSelection(activeList.indexOf(element));
                }
            }

            public void widgetDefaultSelected(final SelectionEvent e) {
            }
        });

        prioButtonComposite.setLayout(new GridLayout(1, false));
        activeListComposite.setLayout(new GridLayout(2, false));
        activeListComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        Composite activeButtonComposite = new Composite(activeGroup, SWT.NONE);
        removeEffect = new Button(activeButtonComposite, SWT.PUSH);
        removeEffect.setText("Remove");
        removeEffect.setLayoutData(new GridData(SWT.TOP, SWT.LEFT, false, false));
        removeEffect.addSelectionListener(new SelectionListener() {

            public void widgetSelected(final SelectionEvent e) {
                for (String item : activeList.getSelection()) {
                    availableList.add(item);
                    activeList.remove(item);
                    KSBasECombination.removeEffect(item);
                }
                checkButtonStatus();
            }

            public void widgetDefaultSelected(final SelectionEvent e) {
            }
        });

        removeAll = new Button(activeButtonComposite, SWT.PUSH);
        removeAll.setText("Remove All");
        removeAll.setLayoutData(new GridData(SWT.TOP, SWT.RIGHT, false, false));
        removeAll.addSelectionListener(new SelectionListener() {

            public void widgetSelected(final SelectionEvent e) {
                for (String item : activeList.getItems()) {
                    availableList.add(item);
                    KSBasECombination.removeEffect(item);
                }
                activeList.removeAll();
                checkButtonStatus();
            }

            public void widgetDefaultSelected(final SelectionEvent e) {
            }
        });
        activeButtonComposite.setLayout(new GridLayout(2, true));

        activeGroup.setLayout(new GridLayout(1, true));
        // activeGroup.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, true, true));

        container.pack();
        checkButtonStatus();

        return null;
    }

    /**
     * When effects have been added or removed we are checking if the buttons should be active or
     * not.
     */
    private void checkButtonStatus() {
        if (activeList.getItemCount() == 0) {
            removeEffect.setEnabled(false);
            removeAll.setEnabled(false);
        } else {
            removeEffect.setEnabled(true);
            removeAll.setEnabled(true);
        }
        if (availableList.getItemCount() == 0) {
            addEffect.setEnabled(false);
            addAll.setEnabled(false);
        } else {
            addEffect.setEnabled(true);
            addAll.setEnabled(true);
        }
    }

    /**
     * Initializes the preference page.
     * 
     * @param workbench
     *            The workbench for this preference page
     */
    public void init(final IWorkbench workbench) {
        KSBasECombination.initalizeEffects(KSBasEViewManagementPlugin.getDefault()
                .getPreferenceStore());
    }

    /**
     * Performs an 'OK' command. i.e. stores the settings.
     * 
     * @return False if an error occurred while storing the settings.
     */
    @Override
    public boolean performOk() {

        KSBasECombination
                .storeEffects(KSBasEViewManagementPlugin.getDefault().getPreferenceStore());
        return true;
    }
}
