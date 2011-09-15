/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2008 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.graphviz.layouter;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.PreferencesUtil;

import de.cau.cs.kieler.core.WrappedException;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.kiml.graphviz.dot.transformations.KGraphDotTransformation.Command;
import de.cau.cs.kieler.kiml.graphviz.layouter.preferences.GraphvizPreferencePage;

/**
 * Handler for accessing Graphviz via a separate process.
 * 
 * @kieler.rating 2009-12-11 proposed yellow msp
 * @author msp
 */
public class GraphvizTool {

    /** preference constant for Graphviz executable. */
    public static final String PREF_GRAPHVIZ_EXECUTABLE = "graphviz.executable";
    /** preference constant for timeout. */
    public static final String PREF_TIMEOUT = "graphviz.timeout";
    /** default timeout for waiting for Graphviz to give some output. */
    public static final int PROCESS_DEF_TIMEOUT = 20000;
    /** minimal timeout for waiting for Graphviz to give some output. */
    public static final int PROCESS_MIN_TIMEOUT = 200;

    /** argument used to specify the command. */
    private static final String ARG_COMMAND = "-K";
    /** argument to suppress warnings of the executable. */
    private static final String ARG_NOWARNINGS = "-q";
    /** argument to invert the Y axis to conform with SWT. */
    private static final String ARG_INVERTYAXIS = "-y";
    /** default locations of the dot executable. */
    private static final String[] DEFAULT_LOCS = { "/opt/local/bin/",
            "/usr/local/bin/", "/usr/bin/", "/bin/" };
    /** starting wait time for polling input from the Graphviz process. */
    private static final int MIN_INPUT_WAIT = 4;
    /** maximal wait time for polling input from the Graphviz process. */
    private static final int MAX_INPUT_WAIT = 500;
    /** maximal number of characters that is read from the Graphviz error output. */
    private static final int MAX_ERROR_OUTPUT = 512;

    /** the process instance that is used for multiple layout runs. */
    private Process process;
    /** the command that was used to create the process. */
    private Command command = Command.INVALID;

    /**
     * Starts a new Graphviz process with the given command. If a process
     * instance was already created, that instance is returned.
     * 
     * @param thecommand
     *            the graphviz command to use
     * @return an instance of the graphviz process
     */
    public Process startProcess(final Command thecommand) {
        if (process == null || this.command != thecommand) {
            IPreferenceStore preferenceStore =
                    GraphvizLayouterPlugin.getDefault().getPreferenceStore();
            String dotExecutable =
                    preferenceStore.getString(PREF_GRAPHVIZ_EXECUTABLE);
            if (!new File(dotExecutable).exists()) {
                boolean foundExec = false;
                for (String location : DEFAULT_LOCS) {
                    dotExecutable = location + "dot";
                    if (new File(dotExecutable).exists()) {
                        foundExec = true;
                        break;
                    }
                }
                if (!foundExec) {
                    handleExecPath();
                    // fetch the executable string again after the user has entered a new path
                    dotExecutable = preferenceStore.getString(PREF_GRAPHVIZ_EXECUTABLE);
                }
            }

            try {
                this.command = thecommand;
                process = Runtime.getRuntime().exec(new String[] { dotExecutable,
                                        ARG_NOWARNINGS, ARG_INVERTYAXIS,
                                        ARG_COMMAND + command });
            } catch (IOException exception) {
                throw new WrappedException(exception, "Failed to start Graphviz process."
                        + " Please check your Graphviz installation.");
            }
        }
        return process;
    }

    /**
     * Handle missing path to the dot executable. The Graphviz preference page
     * is opened so the user can enter the correct path. The method returns
     * after the preference page has been closed.
     */
    private static void handleExecPath() {
        final Display display = PlatformUI.getWorkbench().getDisplay();
        display.syncExec(new Runnable() {
            public void run() {
                PreferenceDialog preferenceDialog =
                        PreferencesUtil.createPreferenceDialogOn(
                                display.getActiveShell(),
                                GraphvizPreferencePage.ID, new String[] {},
                                null);
                preferenceDialog.open();
            }
        });
    }

    /**
     * Closes the currently cached process instance so a new one is created for
     * the next layout run.
     */
    public void endProcess() {
        if (process != null) {
            try {
                process.getOutputStream().close();
                process.getInputStream().close();
            } catch (IOException exception) {
                // ignore exception
            }
            process.destroy();
            process = null;
        }
    }

    /**
     * Waits until there is some input from the given input stream, with a
     * customizable timeout.
     * 
     * @param inputStream
     *            input stream from which input is expected
     * @param errorStream
     *            error stream that is queried if there is no input
     * @param monitor
     *            monitor to which progress is reported
     * @param debugMode
     *            whether debug mode is active
     */
    public void waitForInput(final InputStream inputStream,
            final InputStream errorStream,
            final IKielerProgressMonitor monitor, final boolean debugMode) {
        monitor.begin("Wait for Graphviz", 1);
        IPreferenceStore preferenceStore =
                GraphvizLayouterPlugin.getDefault().getPreferenceStore();
        int timeout = preferenceStore.getInt(PREF_TIMEOUT);
        if (timeout < PROCESS_MIN_TIMEOUT) {
            timeout = PROCESS_DEF_TIMEOUT;
        }
        try {
            // wait until there is input from Graphviz
            long startTime = System.currentTimeMillis();
            try {
                long sleepTime = MIN_INPUT_WAIT;
                while (inputStream.available() == 0
                        && System.currentTimeMillis() - startTime < timeout
                        && !monitor.isCanceled()) {
                    Thread.sleep(sleepTime);
                    // increase sleep time after each step
                    if (sleepTime < MAX_INPUT_WAIT) {
                        sleepTime += MIN_INPUT_WAIT;
                    }
                }
            } catch (InterruptedException exception) {
                // ignore exception
            }
            // read and check error stream if there is still no input from
            // Graphviz
            if (inputStream.available() == 0 && !monitor.isCanceled()) {
                StringBuilder error = new StringBuilder();
                while (error.length() < MAX_ERROR_OUTPUT
                        && errorStream.available() > 0) {
                    error.append((char) errorStream.read());
                }
                endProcess();
                if (error.length() > 0) {
                    throw new GraphvizException("Graphviz error: " + error.toString());
                } else {
                    throw new GraphvizException(
                            "Timeout exceeded while waiting for Graphviz output. "
                                    + "Try increasing the timeout value in the preferences.");
                }
            } else {
                // read the error stream anyway - if error stream is not empty,
                // process may not terminate
                while (errorStream.available() > 0) {
                    if (debugMode) {
                        System.err.print(errorStream.read());
                    } else {
                        errorStream.read();
                    }
                }
            }
        } catch (IOException exception) {
            endProcess();
            throw new WrappedException(exception, "Unable to read Graphviz output.");
        } finally {
            monitor.done();
        }
    }

}
