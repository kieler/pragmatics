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

package de.cau.cs.kieler.kwebs.logging;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

/**
 * Logger for the KIELER layout web service.
 *
 * @kieler.rating  2011-05-04 red
 * @author  swe
 */
public final class Logger {

    /** The id of the extension point for logging configuration. */
    private static final String EXTENSIONPOINT_ID
        = "de.cau.cs.kieler.kwebs.logging";

    /** Name of the class element. */
    private static final String ELEMENT_CLASS
        = "class";

    /** Name of the default element. */
    private static final String ELEMENT_DEFAULT
        = "default";

    /** This attribute defines the fully qualified name of a java class for which a
     *  log level is to be defined. */
    private static final String ATTRIBUTE_QUALIFIEDNAME
        = "qualifiedName";

    /** From which log level on shall an logging event be notified to listeners? */
    private static final String ATTRIBUTE_LOGLEVEL
        = "logLevel";

    /** Enum defining the different severities of log entries. */
    public static enum Severity {
        /** Do logging despite of configured log level. */
        ALWAYS,
        /** Debug severity. */
        DEBUG,
        /** Normal information, generally discarded. */
        INFO,
        /** Warning level. This is the default level where a logging event is published to listeners. */
        WARNING,
        /** Failure level means that something has generally gone wrong. */
        FAILURE,
        /** Critical level means that administrative intervention is required. */
        CRITICAL
    };

    /** Run modes of this logger. */
    public static enum Mode {
        /** Normal operation. */
        NORMAL,
        /** Debug mode. Only in this mode debugging messages are logged. */
        DEBUG
    }
    
    /** The current run mode of the logger. */
    private Mode runMode
        = Mode.NORMAL;
    
    /** Map of qualified class or package name to log level. */
    private static Map<String, Severity> logLevel
        = new HashMap<String, Severity>();

    /** Default log level for notifying listeners. */
    private static Severity defaultLogLevel
        = Severity.WARNING;

    /** The singleton instance. */
    public static final Logger INSTANCE
        = new Logger();

    /** */
    private static final Lock LOCK_LOG
        = new ReentrantLock();

    /** The registered listeners. */
    private Vector<ILoggerListener> listeners
        = new Vector<ILoggerListener>();

    /**
     * Private constructor.
     */
    private Logger() {
        initialize();
    }

    /**
     * Read logging configuration from extension registry. The logging configuration is realized
     * through the extension point XXX.
     */
    private void initialize() {
        IExtensionRegistry registry = Platform.getExtensionRegistry();
        // if no registry is found, the logger is used on a non
        // osgi platform so extensions can not be read.
        if (registry == null) {
            return;
        }
        String name = null;
        String loglevel = null;
        Severity sLog = null;
        for (IConfigurationElement element : registry.getConfigurationElementsFor(EXTENSIONPOINT_ID)) {
            if (element.getName().equals(ELEMENT_CLASS)) {
                name = element.getAttribute(ATTRIBUTE_QUALIFIEDNAME);
                loglevel = element.getAttribute(ATTRIBUTE_LOGLEVEL);
                if (name != null && name.length() > 0) {
                    if (loglevel != null && loglevel.length() > 0) {
                        sLog = Enum.valueOf(Severity.class, loglevel);
                        logLevel.put(name, sLog);
                    }
                }
            } else if (element.getName().equals(ELEMENT_DEFAULT)) {
                loglevel = element.getAttribute(ATTRIBUTE_LOGLEVEL);
                if (loglevel != null && loglevel.length() > 0) {
                    defaultLogLevel = Enum.valueOf(
                        Severity.class, loglevel
                    );
                }
            }
        }

    }

    /**
     * Returns the run mode of this logger.
     * 
     * @return the run mode
     */
    public Mode getRunMode() {
        return runMode;        
    }
    
    /**
     * Sets the run mode of this logger.
     * 
     * @param therunMode
     *            the new run mode
     */
    public void setRunMode(final Mode therunMode) {
        runMode = therunMode;        
    }
    
    /**
     * Log a message with {@code Severity.INFO}.
     *
     * @param message
     *            the message to be logged
     */
    public static void log(final String message) {
        StackTraceElement element = Thread.currentThread().getStackTrace()[2];
        INSTANCE.log(
            element.getClassName(),
            element.getMethodName(),
            Integer.toString(element.getLineNumber()),
            Severity.INFO,
            message,
            null,
            null
        );
    }

    /**
     * Log a message and additional data with {@code Severity.INFO}.
     *
     * @param message
     *            the message to be logged
     * @param data
     *            additional data to be logged
     */
    public static void log(final String message, final String data) {
        StackTraceElement element = Thread.currentThread().getStackTrace()[2];
        INSTANCE.log(
            element.getClassName(),
            element.getMethodName(),
            Integer.toString(element.getLineNumber()),
            Severity.INFO,
            message,
            data,
            null
        );
    }

    /**
     * Log a message with severity {@code severity}.
     *
     * @param severity
     *            the severity of the message
     * @param message
     *            the message to be logged
     */
    public static void log(final Severity severity, final String message) {
        StackTraceElement element = Thread.currentThread().getStackTrace()[2];
        INSTANCE.log(
            element.getClassName(),
            element.getMethodName(),
            Integer.toString(element.getLineNumber()),
            severity,
            message,
            null,
            null
        );
    }

    /**
     * Log a message and additional data with severity {@code severity}.
     *
     * @param severity
     *            the severity of the message
     * @param message
     *            the message to be logged
     * @param data
     *            additional data to be logged
     */
    public static void log(final Severity severity, final String message, final String data) {
        StackTraceElement element = Thread.currentThread().getStackTrace()[2];
        INSTANCE.log(
            element.getClassName(),
            element.getMethodName(),
            Integer.toString(element.getLineNumber()),
            severity,
            message,
            data,
            null
        );
    }

    /**
     * Log a message and a throwable with severity {@code severity}.
     *
     * @param severity
     *            the severity of the message
     * @param message
     *            the message to be logged
     * @param throwable
     *            the throwable to be logged
     */
    public static void log(final Severity severity, final String message, final Throwable throwable) {
        StackTraceElement element = Thread.currentThread().getStackTrace()[2];
        INSTANCE.log(
            element.getClassName(),
            element.getMethodName(),
            Integer.toString(element.getLineNumber()),
            severity,
            message,
            null,
            throwable
        );
    }

    /**
     * Log a message, additional dataand a throwable with severity {@code severity}.
     *
     * @param severity
     *            the severity of the message
     * @param message
     *            the message to be logged
     * @param data
     *            additional data to be logged
     * @param throwable
     *            the throwable to be logged
     */
    public static void log(final Severity severity, final String message,
        final String data, final Throwable throwable) {
        StackTraceElement element = Thread.currentThread().getStackTrace()[2];
        INSTANCE.log(
            element.getClassName(),
            element.getMethodName(),
            Integer.toString(element.getLineNumber()),
            severity,
            message,
            data,
            throwable
        );
    }

    /** The used date formatter. */
    private static final SimpleDateFormat DATE_FORMATTER
        = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss Z");

    /**
     * This method does the actual logging by creating a logger event and notifing all listeners
     * in the order in which they have registered themselves.
     *
     * @param clas
     *            the class in which the logging event occured
     * @param method
     *            the method in which the logging event occured
     * @param line
     *            the line in which the logging event occured
     * @param severity
     *            the severity of the message
     * @param message
     *            the message to be logged
     * @param data
     *            additional data to be logged
     * @param throwable
     *            the throwable to be logged
     */
    private void log(final String clas, final String method, final String line,
        final Severity severity, final String message, final String data, final Throwable throwable) {
        Date date = new Date();
        Severity sLog = getLogLevel(clas, logLevel);
        if (sLog == null) {
            sLog = defaultLogLevel;
        }
        String clasShort = (clas.lastIndexOf(".") > -1
                ? clas.substring(clas.lastIndexOf(".") + 1)
                        : clas
        );
        String tmp = message.replace("$C", clas)
                        .replace("$M", method)
                            .replace("$L", line)
                                .replace("$P", clasShort + "::" + method) //$NON-NLS-2$
                                    .replace("$SC", clasShort);
        LoggerEvent event = new LoggerEvent(
            date, clas, method, line, severity, tmp, data, throwable
        );
        if (severity == Severity.ALWAYS || runMode == Mode.DEBUG && severity == Severity.DEBUG
            || severity.compareTo(sLog) >= 0) {
            LOCK_LOG.lock();
            for (ILoggerListener listener : listeners) {
                listener.loggerEvent(event);
            }
            LOCK_LOG.unlock();
        }
    }

    /**
     *
     * @param clas
     *            fully qualified class name
     * @param map
     *            the map containing log levels indexed by package name or class name
     * @return
     */
    private Severity getLogLevel(final String clas, final Map<String, Severity> map) {
        String tmp = clas;
        Severity severity = map.get(tmp);
        while (severity == null && tmp.length() > 0) {
            int endIndex = tmp.lastIndexOf(".");
            if (endIndex <= 0) {
                break;
            }
            tmp = tmp.substring(0, endIndex);
            severity = map.get(tmp);
        }
        return severity;
    }

    /**
     *
     * @param listener
     *            the listener to be added
     */
    public static void addLoggerListener(final ILoggerListener listener) {
        if (!INSTANCE.listeners.contains(listener)) {
            INSTANCE.listeners.add(listener);
        }
    }

    /**
     *
     * @param listener
     *            the listener to be removed
     */
    public static void removeLoggerListener(final ILoggerListener listener) {
        if (INSTANCE.listeners.contains(listener)) {
            INSTANCE.listeners.remove(listener);
        }
    }

    /**
     * The class defining a logger event which contains the information passed
     * to any registered listener.
     *
     * @author swe
     *
     */
    public final class LoggerEvent {

        /** Date of the logging event. */
        private Date date;

        /** Class in which logging was done. */
        private String clas;

        /** Method in which logging was done. */
        private String method;

        /** Line in which logging was done. */
        private String line;

        /** Severity of the event. */
        private Severity severity;

        /** The message of the event. */
        private String message;

        /** Optional additional data. */
        private String data;

        /** Optional exception. */
        private Throwable throwable;

        /**
         * Constructs a logging event.
         *
         * @param thedate
         *            the date the logging event occured.
         * @param theclas
         *            the class in which the logging event occured
         * @param themethod
         *            the method in which the logging event occured
         * @param theline
         *            the line in which the logging event occured
         * @param theseverity
         *            the severity of the message
         * @param themessage
         *            the message to be logged
         * @param thedata
         *            additional data to be logged
         * @param thethrowable
         *            the throwable to be logged
         */
        public LoggerEvent(final Date thedate, final String theclas,
            final String themethod, final String theline,
            final Severity theseverity, final String themessage,
            final String thedata, final Throwable thethrowable) {

            date = thedate;
            clas = theclas;
            method = themethod;
            line = theline;
            severity = theseverity;
            message = themessage;
            data = thedata;
            throwable = thethrowable;

        }

        /**
         * Returns the date associated with this logging event.
         *
         * @return the date
         */
        public Date getDate() {
            return date;
        }

        /**
         * Returns the class associated with this logging event.
         *
         * @return the class
         */
        public String getClas() {
            return clas;
        }

        /**
         * Returns the method associated with this logging event.
         *
         * @return the method
         */
        public String getMethod() {
            return method;
        }

        /**
         * Returns the line associated with this logging event.
         *
         * @return the line
         */
        public String getLine() {
            return line;
        }

        /**
         * Returns the severity associated with this logging event.
         *
         * @return the severity
         */
        public Severity getSeverity() {
            return severity;
        }

        /**
         * Returns the message associated with this logging event.
         *
         * @return the message
         */
        public String getMessage() {
            return message;
        }

        /**
         * Returns the data associated with this logging event.
         *
         * @return the data
         */
        public String getData() {
            return data;
        }

        /**
         * Returns the throwable associated with this logging event.
         *
         * @return the throwable
         */
        public Throwable getThrowable() {
            return throwable;
        }

        /**
         * Returns the contained information as array of strings.
         *
         * @return the contained information as array of strings
         */
        public Object[] toObjectArray() {
            return new String[] {
                DATE_FORMATTER.format(date),
                severity.name(),
                message,
                clas,
                method,
                line,
                data,
                (throwable != null ? throwable.getMessage() : "")
            };
        }

        /**
         * Returns a string representation of this event.
         *
         * @return a string representation of this event
         */
        @Override
        public String toString() {
            return "["
                   + DATE_FORMATTER.format(date)
                   + "] - "
                   + severity.toString()
                   + " : "
                   + message;
        }

    }

}
