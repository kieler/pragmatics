/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2008 by
 * + Christian-Albrechts-University of Kiel
 *     + Department of Computer Science
 *         + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.kwebs.server.logging;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import de.cau.cs.kieler.kwebs.logging.ILoggerListener;
import de.cau.cs.kieler.kwebs.logging.Logger.LoggerEvent;

/**
 * This class implements {@link ILoggerListener} and provides basic
 * file based logging in csv format.
 *
 * @kieler.rating  2011-05-04 red
 * @author  swe
 */
public class FileLogging implements ILoggerListener {

    /** */
    private String logFileName;

    /** */
    private File logFile;

    /** */
    private Writer logWriter;

    /** */
    private final Lock logLock
        = new ReentrantLock();

    /**
     *
     * @param logFileName
     */
    public FileLogging(final String thelogFileName) {
        setLogFileName(thelogFileName);
    }

    /**
     *
     */
    @Override
    protected void finalize() throws Throwable {
        flushNClose();
        super.finalize();
    }

    /**
     *
     * @return
     */
    public final String getLogFileName() {
        return logFileName;
    }

    /**
     *
     * @param logFileName
     * @throws IllegalArgumentException
     */
    public final void setLogFileName(final String thelogFileName) {
        if (thelogFileName == null || thelogFileName.length() == 0) {
            throw new IllegalArgumentException(
                "Empty or null log file name not allowed"
            );
        }
        logLock.lock();
        if (!thelogFileName.equals(logFileName)) {
            try {
                flushNClose();
                logFileName = thelogFileName;
                openNInit();
            } catch (IOException e) {
                logWriter = null;
                logFile = null;
                logLock.unlock();
                throw new IllegalStateException(
                    "Unable to initialize logging stream"
                );
            }
        }
        logLock.unlock();
    }

    /**
     *
     * @return
     */
    public final boolean isInitialized() {
        return (logWriter != null);
    }

    /**
     *
     * @return
     * @throws IllegalAccessException
     */
    public final long size() throws IllegalAccessException {
        if (!isInitialized()) {
            throw new IllegalAccessException(
                "Logger is not initialized"
            );
        }
        return logFile.length();
    }

    /**
     *
     */
    public final void clear() {
        logLock.lock();
        try {
            flushNClose();
            openNInit(false);
        } catch (IOException e) {
            logWriter = null;
            logFile = null;
            logLock.unlock();
            throw new IllegalStateException(
                "Unable to initialize logging stream"
            );
        }
        logLock.unlock();
    }

    /**
     * Flushes and closes the log.
     */
    public final void close() throws IOException {
        flushNClose();
    }

    /**
     * Opens the log and appends to its content.
     *
     * @throws IOException
     */
    private void openNInit() throws IOException {
        openNInit(true);
    }

    /**
     * Opens the log.
     *
     *  @param appendTo
     *             whether to append to an existing log or to start with an empty log.
     *
     * @throws IOException
     */
    private void openNInit(final boolean appendTo) throws IOException {
        if (logFileName != null) {
            logFile = new File(logFileName);
            boolean doCreate = !logFile.exists();
            if (doCreate) {
                logFile.getParentFile().mkdirs();
                logFile.createNewFile();
            }
            logWriter = new BufferedWriter(new FileWriter(logFile, appendTo));
            if (doCreate) {
                logWriter.write("DATE,CLASS,METHOD,LINE,LEVEL,MESSAGE,THROWABLE\n");
            }
        }
    }

    /**
     * Flushes the log and closes it.
     *
     * @throws IOException
     */
    private void flushNClose() throws IOException {
        if (logWriter != null) {
            logWriter.flush();
            logWriter.close();
            logWriter = null;
        }
        if (logFile != null) {
            logFile = null;
        }
    }

    /**
     * {@inheritDoc}
     */
    public void loggerEvent(final LoggerEvent event) {
        if (event != null) {
            logLock.lock();
            try {
                if (logWriter != null) {
                    logWriter.write(getCsvRecord(event) + "\n");
                }
            } catch (Exception e) {
                System.out.println("FATAL: Logger could not write to output.");
            }
            logLock.unlock();
        }
    }

    /** The date formatter. */
    private static final SimpleDateFormat DATE_FORMATTER
        = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss Z");

    /**
     * Creates a csv record from a logger event.
     *
     * @param event
     *            the logger event
     * @return the csv record
     */
    public final String getCsvRecord(final LoggerEvent event) {
        String str = "";
        str += getCsvField(DATE_FORMATTER.format(event.getDate())) + ", ";
        str += getCsvField(event.getClas()) + ", ";
        str += getCsvField(event.getMethod()) + ", ";
        str += getCsvField(event.getLine()) + ", ";
        str += getCsvField(event.getSeverity().toString()) + ", ";
        str += getCsvField(event.getMessage()) + ", ";
        str += getCsvField(event.getData()) + ", ";
        Throwable th = event.getThrowable();
        if (th != null) {
            str += getCsvField(th.getMessage() + " - " + Arrays.toString(th.getStackTrace()));
        }
        return str;
    }

    /**
     * Create a csv field entry by escaping the given string.
     *
     * @param string
     *            the string to be escaped
     * @return the csv field
     */
    private String getCsvField(final String string) {
        String tmp = string;
        if (tmp == null) {
            return "";
        }
        if (tmp.startsWith(" ") || tmp.endsWith(" ") || tmp.startsWith("\t")
            || tmp.endsWith("\t") || tmp.contains(",") || tmp.contains("\"")
            || tmp.contains("\r") || tmp.contains("\n")) {
            tmp = "\""
                + tmp.replace("\"", "\"\"")
                + "\"";
        }
        return tmp;
    }

}
