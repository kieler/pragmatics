/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.core.kivi.menu;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;

import de.cau.cs.kieler.core.kivi.AbstractTrigger;
import de.cau.cs.kieler.core.kivi.AbstractTriggerState;
import de.cau.cs.kieler.core.kivi.ITrigger;
import de.cau.cs.kieler.core.kivi.ITriggerState;

/**
 * Listens to various buttons for the view management.
 * 
 * @author mmu
 */
public class ButtonTrigger extends AbstractTrigger {

    private static ButtonTrigger instance;

    static ButtonTrigger getInstance() {
        return instance;
    }

    @Override
    public void register() {
        instance = this;
    }

    @Override
    public void unregister() {
        instance = null;
    }

    /**
     * Contains information about the last view management button pressed.
     * 
     * @author mmu
     * 
     */
    public static class ButtonState extends AbstractTriggerState {

        private IEditorPart editor;

        private String buttonId = "";

        private boolean pushedIn = false;
        private Map<?, ?> parameters = new HashMap<Object, Object>();

        private Map<String, Boolean> buttonStatusMap = new HashMap<String, Boolean>();

        /**
         * Default constructor.
         */
        public ButtonState() {
        }

        /**
         * Create a new button state for the given editor and button id.
         * 
         * @param editorPart
         *            the active editor
         * @param id
         *            the button id
         * @param params
         *            a map of button parameters
         * @param pushed
         *            true if a toggle button is pushed in
         */
        public ButtonState(final IEditorPart editorPart, final String id, final Map<?, ?> params,
                final boolean pushed) {
            editor = editorPart;
            buttonId = id;
            pushedIn = pushed;
            parameters = params;
            buttonStatusMap.put(id, pushed);
        }

        /**
         * Get the active editor associated with the button.
         * 
         * @return the active editor
         */
        public IEditorPart getEditor() {
            if (editor == null) {              
                editor = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
                        .getActivePage().getActiveEditor();
            }
            return editor;
        }

        /**
         * Get the id for the last button pressed. 
         * 
         * @return the id
         */
        public String getButtonId() {
            return buttonId;
        }

        /**
         * Get the parameters of the button.
         * 
         * @return a map of parameters
         */
        public Map<?, ?> getParameters() {
            return parameters;
        }

        /**
         * Check if a toggle button is pushed in or not. Gives the state of the last button
         * pressed.
         * 
         * @return true if pushed in
         */
        public boolean isPushedIn() {
            return pushedIn;
        }

        /**
         * {@inheritDoc}
         */
        public Class<? extends ITrigger> getTriggerClass() {
            return ButtonTrigger.class;
        }

        /**
         * Get the map of button IDs to the state of the button. Returns false if the button Id is
         * not registered.
         * 
         * @param thebuttonId the button identifier
         * @return the map of button IDs to the state of the button.
         */
        public boolean isPushedIn(final String thebuttonId) {
            if (buttonStatusMap.containsKey(thebuttonId)) {
                return buttonStatusMap.get(thebuttonId);
            }
            return false;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void merge(final ITriggerState previous) {
            /* a button even creates only information about this specific button, therefore
             * this information has to be merged with the previous state to get the settings
             * for the other buttons. Therefore only entries that are not set in this state are
             * older and may be safely overriden.
             */
            if (previous instanceof ButtonState) {
                for (String id : ((ButtonState) previous).buttonStatusMap.keySet()) {
                    if (!this.buttonStatusMap.containsKey(id)) {
                        this.buttonStatusMap.put(id, ((ButtonState) previous).buttonStatusMap.get(id));
                    }
                }
            }
        }

    }

}
