/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.core.kivi.menu;

import java.util.Collections;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.handlers.HandlerUtil;

import de.cau.cs.kieler.core.kivi.menu.ButtonTrigger.ButtonState;

/**
     * The handler that receives commands from various view management buttons.
     * 
     * @author mmu
     * 
     */
    public class ButtonHandler extends AbstractHandler {

        private boolean pushed = false;
        private String id = "";
        private IEditorPart editorPart;
        
        /**
         * {@inheritDoc}
         */
        public Object execute(final ExecutionEvent event) throws ExecutionException {
            if (ButtonTrigger.getInstance() != null) {
                editorPart = HandlerUtil.getActiveEditor(event);
                id = event.getCommand().getId();
                if (event.getTrigger() instanceof Event) {
                    Event e = (Event) event.getTrigger();
                    if (e.widget instanceof ToolItem) {
                        ToolItem tool = (ToolItem) e.widget;
                        pushed = tool.getSelection();
                    }

                }
                ButtonTrigger.getInstance().trigger(
                        new ButtonState(editorPart, id, event.getParameters(), pushed));
            }
            return null;
        }
        
        /**
         * Unload this ButtonHandler. I.e. trigger a last not-pushed state if the button was
         * pushed before.
         */
        public void unload() {
            if (pushed) {
                pushed = false;
                ButtonTrigger.getInstance().trigger(
                        new ButtonState(null, id, Collections.EMPTY_MAP, pushed));
            }
        }
        
        /**
         * Set the enabled state of this handler. This state is used by corresponding
         * menu contributions (buttons, menu entries, etc.) to determine the enabled state
         * of that menu item, e.g. whether a button should be grayed out or not.
         * @author haf
         * @param enabled true iff the handler is enabled.
         */
        public void setEnabled(final boolean enabled) {
            this.setBaseEnabled(enabled);
        }
        
        /**
         * Returns the pushed status.
         * 
         * @return the pushed status
         */
        public boolean getPushed() {
            return pushed;
        }
        
        
    }