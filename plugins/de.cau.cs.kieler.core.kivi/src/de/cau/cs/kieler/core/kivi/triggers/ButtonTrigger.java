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
package de.cau.cs.kieler.core.kivi.triggers;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.handlers.HandlerUtil;

import de.cau.cs.kieler.core.kivi.AbstractTrigger;
import de.cau.cs.kieler.core.kivi.AbstractTriggerState;
import de.cau.cs.kieler.core.kivi.ITrigger;
import de.cau.cs.kieler.core.ui.util.EditorUtils;
import de.cau.cs.kieler.core.ui.util.MonitoredOperation;
import de.cau.cs.kieler.core.util.Maybe;

/**
 * Listens to various buttons for the view management.
 * 
 * @author mmu
 * 
 */
public class ButtonTrigger extends AbstractTrigger {

    private static ButtonTrigger instance;

    private static ButtonTrigger getInstance() {
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
     * The handler that receives commands from various view management buttons.
     * 
     * @author mmu
     * 
     */
    public static class ButtonHandler extends AbstractHandler {

        /**
         * {@inheritDoc}
         */
        public Object execute(final ExecutionEvent event) throws ExecutionException {
            if (getInstance() != null) {
                IEditorPart editorPart = HandlerUtil.getActiveEditor(event);
                String id = event.getCommand().getId();
                boolean pushed = false;
                if (event.getTrigger() instanceof Event) {
                    Event e = (Event) event.getTrigger();
                    if (e.widget instanceof ToolItem) {
                        ToolItem tool = (ToolItem) e.widget;
                        pushed = tool.getSelection();
                    }

                }
                System.out.println("ButtonTrigger: "+id+" "+pushed);
                getInstance().trigger(
                        new ButtonState(editorPart, id, event.getParameters(), pushed));
            }
            return null;
        }
        
        /**
         * {@inheritDoc}
         */
//        @Override
//        public boolean isEnabled() {
//            return ;
//        }
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
        }

        /**
         * Get the active editor associated with the button.
         * 
         * @return the active editor
         */
        public IEditorPart getEditor() {
            if (editor != null) {
                return editor;
            } else {
                final Maybe<IEditorPart> maybe = new Maybe<IEditorPart>();
                MonitoredOperation.runInUI(new Runnable() {
                    public void run() {
                        maybe.set(EditorUtils.getLastActiveEditor());
                    }
                }, true);
                return maybe.get();
            }
        }

        /**
         * Get the id for the button.
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
         * Check if a toggle button is pushed in or not.
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

    }

}
