package edu.umd.cs.piccolox.swt;

import java.awt.Component;
import java.awt.event.InputEvent;
import java.awt.event.MouseWheelEvent;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Widget;

/**
 * A Piccolo event for mouse wheel input.
 * 
 * @author mri
 */
public class PSWTMouseWheelEvent extends MouseWheelEvent {
    
    private static final long serialVersionUID = -2094261280867051699L;

    private static Component fakeSrc = new Component() {
        private static final long serialVersionUID = 6935771461423211368L;
    };

    /** Event being wrapped. */
    protected org.eclipse.swt.events.MouseEvent swtEvent;

    public PSWTMouseWheelEvent(final org.eclipse.swt.events.MouseEvent me, final int type,
            final int scrollType) {
        super(fakeSrc, type, me.time, 0, me.x, me.y, 1, false, scrollType, me.count, 1);
        swtEvent = me;
    }
    
    // The following is copied from PSWTMouseEvent
    
    /** {@inheritDoc} */
    public Object getSource() {
        return swtEvent.getSource();
    }

    /** {@inheritDoc} */
    public boolean isShiftDown() {
        return (swtEvent.stateMask & SWT.SHIFT) != 0;
    }

    /** {@inheritDoc} */
    public boolean isControlDown() {
        return (swtEvent.stateMask & SWT.CONTROL) != 0;
    }

    /** {@inheritDoc} */
    public boolean isAltDown() {
        return (swtEvent.stateMask & SWT.ALT) != 0;
    }

    /** {@inheritDoc} */
    public int getModifiers() {
        int modifiers = 0;

        if (swtEvent != null) {
            if ((swtEvent.stateMask & SWT.ALT) != 0) {
                modifiers = modifiers | InputEvent.ALT_MASK;
            }
            if ((swtEvent.stateMask & SWT.CONTROL) != 0) {
                modifiers = modifiers | InputEvent.CTRL_MASK;
            }
            if ((swtEvent.stateMask & SWT.SHIFT) != 0) {
                modifiers = modifiers | InputEvent.SHIFT_MASK;
            }
        }

        return modifiers;
    }

    /** {@inheritDoc} */
    public int getModifiersEx() {
        int modifiers = 0;

        if (swtEvent != null) {
            if ((swtEvent.stateMask & SWT.ALT) != 0) {
                modifiers = modifiers | InputEvent.ALT_DOWN_MASK;
            }
            if ((swtEvent.stateMask & SWT.CONTROL) != 0) {
                modifiers = modifiers | InputEvent.CTRL_DOWN_MASK;
            }
            if ((swtEvent.stateMask & SWT.SHIFT) != 0) {
                modifiers = modifiers | InputEvent.SHIFT_DOWN_MASK;
            }
        }

        return modifiers;
    }

    /**
     * Returns the widget from which the event was emitted.
     * 
     * @return source widget
     */
    public Widget getWidget() {
        return swtEvent.widget;
    }

    /**
     * Return the display on which the interaction occurred.
     * 
     * @return display on which the interaction occurred
     */
    public Display getDisplay() {
        return swtEvent.display;
    }

    /**
     * Return the associated SWT data for the event.
     * 
     * @return data associated to the SWT event
     */
    public Object getData() {
        return swtEvent.data;
    }

}
