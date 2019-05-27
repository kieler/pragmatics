/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2019 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.Dialog;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

/**
 * A generic status handler trying to log all messages handled statically here with some class implementing the 
 * IStatusManager. Fallback case is to just log all messages to the console.
 * 
 * @author nre
 */
public final class StatusHandler {
    
    private StatusHandler() { }
    
    /**
     * A style indicating that the status should not be acted on. This is used
     * by objects such as log listeners that do not want to report a status
     * twice.
     */
    public static final int NONE = 0;

    /**
     * A style indicating that the status should be logged only.
     */
    public static final int LOG = 0x01;

    /**
     * A style indicating that handlers should show a problem to an user without
     * blocking the calling method while awaiting user response. This is
     * generally done using a non modal {@link Dialog}.
     */
    public static final int SHOW = 0x02;

    /**
     * A style indicating that the handling should block the calling thread
     * until the status has been handled.
     * <p>
     * A typical usage of this would be to ensure that the user's actions are
     * blocked until they've dealt with the status in some manner. It is
     * therefore likely but not required that the <code>StatusHandler</code>
     * would achieve this through the use of a modal dialog.
     * </p><p>Due to the fact
     * that use of <code>BLOCK</code> will block UI, care should be
     * taken in this use of this flag.
     * </p>
     */
    public static final int BLOCK = 0x04;

    /**
     * Handles the status by searching an IStatusManager, or log it to the console.
     * @param status The status to log.
     */
    public static void handle(final IStatus status) {
        Bundle bundle = FrameworkUtil.getBundle(StatusHandler.class);
        if (bundle == null) {
            fallbackHandle(status);
            return;
        }
        BundleContext bc = bundle.getBundleContext();
        if (bc == null) {
            fallbackHandle(status);
            return;
        }
        ServiceReference<IStatusManager> sr = bc.getServiceReference(IStatusManager.class);
        if (sr == null) {
            fallbackHandle(status);
            return;
        }
        IStatusManager statusManager = bc.getService(sr);
        if (statusManager == null) {
            fallbackHandle(status);
            return;
        }
        statusManager.handle(status);
    }
    
    /**
     * Handle the status. See {@link #handle(IStatus)}
     * 
     * @param status The status to log.
     * @param style The style to log the status in. Currently ignored.
     */
    public static void handle(final IStatus status, final int style) {
        handle(status); // TODO: Consider the style.
    }

    private static void fallbackHandle(final IStatus status) {
        String severity = getSeverity(status.getSeverity());
        System.out.println(severity + ": " + status.getMessage() + "(" + status.getPlugin() + ")");
    }

    /**
     * @param severity
     * @return
     */
    private static String getSeverity(final int severity) {
        switch (severity) {
        case IStatus.CANCEL:
            return "CANCEL";
        case IStatus.ERROR:
            return "ERROR";
        case IStatus.INFO:
            return "INFO";
        case IStatus.OK:
            return "OK";
        case IStatus.WARNING:
            return "WARNING";
        default:
            return "";
        }
    }
}
