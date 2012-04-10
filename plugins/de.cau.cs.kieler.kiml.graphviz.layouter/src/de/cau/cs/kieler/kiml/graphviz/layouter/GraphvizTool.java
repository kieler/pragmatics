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

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.PreferencesUtil;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.core.WrappedException;
import de.cau.cs.kieler.kiml.graphviz.dot.transform.Command;
import de.cau.cs.kieler.kiml.graphviz.layouter.preferences.GraphvizPreferencePage;

/**
 * Handler for accessing Graphviz via a separate process.
 * 
 * @kieler.rating 2009-12-11 proposed yellow msp
 * @author msp
 */
public class GraphvizTool {
    
    /**
     * Available cleanup modes.
     */
    public static enum Cleanup {
        /** normal cleanup. */
        NORMAL,
        /** read error output and stop the Graphviz process. */
        ERROR,
        /** stop the Graphviz process and the watcher thread. */
        STOP;
    }

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

    /** the process instance that is used for multiple layout runs. */
    private Process process;
    /** the command that is used to create the process. */
    private Command command;
    /** the watcher thread used to cancel a blocked read operation. */
    private Watchdog watchdog;
    /** the input stream given by the Graphviz process. */
    private GraphvizStream graphvizStream;
    
    /**
     * Create a Graphviz tool instance for the given command.
     * 
     * @param thecommand a Graphviz command
     */
    public GraphvizTool(final Command thecommand) {
        if (thecommand == Command.INVALID) {
            throw new IllegalArgumentException("Invalid Graphviz command.");
        }
        this.command = thecommand;
    }

    /**
     * Initialize the Graphviz tool instance by starting the dot process and the
     * watcher thread as necessary.
     */
    public void initialize() {
        initialize(null);
    }
    
    /**
     * Initialize the Graphviz tool instance by starting the dot process and the watcher
     * thread as necessary. The given command line arguments are appended to the default
     * arguments.
     * 
     * @param arguments command line arguments to be added to the default list of arguments.
     *                  May be {@code null} or empty.
     */
    public void initialize(final List<String> arguments) {
        if (watchdog == null) {
            // start the watcher thread for timeout checking
            watchdog = new Watchdog();
            watchdog.setName("Graphviz Watchdog");
            watchdog.start();
        }

        if (process == null) {
            // get a string to the dot executable
            IPreferenceStore preferenceStore =
                    GraphvizLayouterPlugin.getDefault().getPreferenceStore();
            String dotExecutable = preferenceStore.getString(PREF_GRAPHVIZ_EXECUTABLE);
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
            
            // assemble the final list of command-line arguments
            List<String> args = Lists.newArrayList(
                    dotExecutable,
                    ARG_NOWARNINGS,
                    ARG_INVERTYAXIS,
                    ARG_COMMAND + command);
            
            if (arguments != null) {
                args.addAll(arguments);
            }
            
            // create the process
            try {
                process = Runtime.getRuntime().exec(args.toArray(new String[0]));
            } catch (IOException exception) {
                throw new WrappedException(exception, "Failed to start Graphviz process."
                        + " Please check your Graphviz installation.");
            }
        }
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
                        PreferencesUtil.createPreferenceDialogOn(display.getActiveShell(),
                                GraphvizPreferencePage.ID, new String[] {}, null);
                preferenceDialog.open();
            }
        });
    }

    /**
     * Return the stream that is used to give input to Graphviz.
     * 
     * @return an output stream for writing to the tool
     */
    public OutputStream input() {
        if (process != null) {
            return new BufferedOutputStream(process.getOutputStream());
        }
        throw new IllegalStateException("Graphviz tool has not been initialized.");
    }
    
    /**
     * Return the stream for reading the output of the Graphviz process.
     * 
     * @return an input stream for reading from the tool
     */
    public InputStream output() {
        if (process != null) {
            synchronized (nextJob) {
                // create an input stream and make it visible to the watcher thread
                graphvizStream = new GraphvizStream(process.getInputStream());
                // wake the watcher, which will then sleep until a timeout occurs
                nextJob.notify();
            }
            return graphvizStream;
        }
        throw new IllegalStateException("Graphviz tool has not been initialized.");
    }
    
    /** maximal number of characters to read from error stream. */
    private static final int MAX_ERROR_OUTPUT = 512;
    /** time to wait before checking process errors. */
    private static final int PROC_ERROR_TIME = 500;
    
    /**
     * Clean up, optionally preparing the tool for the next use.
     * 
     * @param c the cleanup option
     */
    public void cleanup(final Cleanup c) {
        StringBuilder error = null;
        if (process != null) {
            InputStream errorStream = process.getErrorStream();
            try {
                if (c == Cleanup.ERROR) {
                    // wait a bit so the process can either terminate or generate error
                    Thread.sleep(PROC_ERROR_TIME);
                    // read the error stream to display a meaningful error message
                    error = new StringBuilder();
                    int ch;
                    do {
                        ch = errorStream.read();
                        if (ch >= 0) {
                            error.append((char) ch);
                        }
                    } while (error.length() < MAX_ERROR_OUTPUT && ch >= 0);
                    if (error.length() == 0) {
                        // no error message -- check for exit value
                        int exitValue = process.exitValue();
                        if (exitValue != 0) {
                            error.append("Process terminated with exit value " + exitValue + ".");
                        }
                    }
                }
                // if error stream is not empty, the process may not terminate
                while (errorStream.available() > 0) {
                    errorStream.read();
                }
            } catch (Exception ex) {
                // ignore exception
            }
            // terminate the Graphviz process if requested
            if (c == Cleanup.ERROR || c == Cleanup.STOP) {
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
        
        if (error == null) {
            synchronized (nextJob) {
                // reset the stream to indicate that the job is done
                graphvizStream = null;
                if (watchdog != null) {
                    // wake the watcher if it is still waiting for timeout
                    watchdog.interrupt();
                    // if requested, reset the watcher to indicate that it should terminate
                    if (c == Cleanup.STOP) {
                        watchdog = null;
                    }
                }
            }
        } else if (error.length() > 0) {
            // an error output could be read from Graphviz, so display that to the user
            throw new GraphvizException("Graphviz error: " + error.toString());
        }
    }

    /**
     * A specialized input stream for reading data from the Graphviz process.
     */
    private class GraphvizStream extends InputStream {
        
        /** the stream of process data output. */
        private InputStream stream;
        /** how many opening curly braces have been read that haven't closed yet. */
        private int depth = 0;
        /** how many graphs have been completely processed. */
        private int finished = 0;
        /** buffered character to return on the next read. */
        private int buf = -1;
        
        /**
         * Create a Graphviz input stream.
         * 
         * @param thestream the process stream to read from
         */
        GraphvizStream(final InputStream thestream) {
            this.stream = thestream;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int read() throws IOException {
            if (buf >= 0) {
                int c = buf;
                buf = -1;
                return c;
            }
            // don't block if we already finished reading a graph
            if (finished > 0 && stream.available() == 0) {
                return -1;
            }
            
            // track the opening and closing braces while reading the stream
            int c = stream.read();
            if (c == '{') {
                depth++;
            } else if (c == '}') {
                depth--;
                if (depth == 0) {
                    finished++;
                }
            } else if (c == '\\') {
                // discard any line breaks that have been escaped
                buf = stream.read();
                if (buf == '\n' || buf == '\r') {
                    c = stream.read();
                    if (buf == '\r' && c == '\n') {
                        c = stream.read();
                    }
                    buf = -1;
                }
            }
            return c;
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public int available() throws IOException {
            return stream.available();
        }
            
    }
    
    /** synchronization object between the main thread and the watcher thread. */
    private Object nextJob = new Object();
    
    /**
     * A watcher thread that takes action when a timeout occurs.
     */
    private class Watchdog extends Thread {
        
        /**
         * {@inheritDoc}
         */
        @Override
        public void run() {
            do {
                synchronized (nextJob) {
                    // the watcher starts working as soon as a stream is made visible
                    while (graphvizStream == null) {
                        try {
                            // wait for notification by the main thread
                            nextJob.wait();
                        } catch (InterruptedException ex) {
                            // an interrupt can happen when a shutdown is requested
                            if (watchdog == null) {
                                return;
                            }
                        }
                    }
                }
                
                // retrieve the current timeout value
                IPreferenceStore preferenceStore =
                        GraphvizLayouterPlugin.getDefault().getPreferenceStore();
                int timeout = preferenceStore.getInt(PREF_TIMEOUT);
                if (timeout < PROCESS_MIN_TIMEOUT) {
                    timeout = PROCESS_DEF_TIMEOUT;
                }
                
                boolean interrupted = false;
                try {
                    Thread.sleep(timeout);
                }  catch (InterruptedException ex) {
                    // this means the main thread has done a cleanup before the timeout occurred
                    interrupted = true;
                }
                
                if (!interrupted) {
                    synchronized (nextJob) {
                        // timeout has occurred! close the stream so the main thread will wake
                        Process myProcess = process;
                        if (myProcess != null) {
                            try {
                                myProcess.getInputStream().close();
                                myProcess.getErrorStream().close();
                                graphvizStream = null;
                            } catch (IOException ex) {
                                // ignore exception
                            }
                        }
                    }
                }
                
            } while (watchdog != null);
        }
        
    }

}
