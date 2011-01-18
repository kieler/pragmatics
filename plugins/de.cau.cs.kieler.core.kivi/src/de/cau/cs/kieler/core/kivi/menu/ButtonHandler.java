package de.cau.cs.kieler.core.kivi.menu;

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

        /**
         * {@inheritDoc}
         */
        public Object execute(final ExecutionEvent event) throws ExecutionException {
            if (ButtonTrigger.getInstance() != null) {
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
                ButtonTrigger.getInstance().trigger(
                        new ButtonState(editorPart, id, event.getParameters(), pushed));
            }
            return null;
        }
        
        /**
         * Set the enabled state of this handler. This state is used by corresponding
         * menu contributions (buttons, menu entries, etc.) to determine the enabled state
         * of that menu item, e.g. whether a button should be grayed out or not.
         * @author haf
         * @param enabled true iff the handler is enabled.
         */
        public void setEnabled(boolean enabled){
            this.setBaseEnabled(enabled);
        }
        
        
    }