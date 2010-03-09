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
package de.cau.cs.kieler.core.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import de.cau.cs.kieler.core.KielerException;

/**
 * Utility class to execute external programs.
 * 
 * @author ctr
 * 
 */
public class KonsoleExec {
    /**
     * Run an external process with a timeout. This method works for Linux/Mac and Windows.
     * 
     * @param cmd
     *            command that is actually executed
     * @param input
     *            the input that is feed to the executed program (stdin)
     * @param inittime
     *            time initialy, the process has this amount of time to produe the initial output.
     * @param timeout
     *            the process is terminated timeout milliseconds after the last output was generated
     * @param steptime
     *            time in milliseconds to wait between polling for output
     * 
     * @return the output that was generated by the program (stdout)
     * @throws KielerException
     *             throw for communication errors or if the return value of the process is not 0. In
     *             this case, also the error stream is contained in the exception.
     * 
     * @throw KiemInitializationException if the compilation is not successful
     */
    public static InputStream exec(final String cmd, final InputStream input, final int inittime,
            final int timeout, final int steptime) throws KielerException {

        Process p;
        try {
            p = Runtime.getRuntime().exec(cmd);

            ByteArrayOutputStream output = new ByteArrayOutputStream();

            InputStream stdout = p.getInputStream();
            InputStream stderr = p.getErrorStream();
            OutputStream stdin = p.getOutputStream();

            while (input.available() > 0) {
                int r = input.read();
                stdin.write(r);
            }
            stdin.close();
            // wait for initial output
            long time = System.currentTimeMillis();
            while (stdout.available() == 0) {
                if (System.currentTimeMillis() - time > inittime) {
                    throw new KielerException("Timeout executing " + cmd);
                }
                try {
                    Thread.sleep(steptime);
                } catch (InterruptedException e) {
                    // silently ignore
                }
            }
            // get output
            time = System.currentTimeMillis();
            while (System.currentTimeMillis() - time < timeout) {
                if (stdout.available() > 0) {
                    do {
                        int t = stdout.read();
                        // System.out.print(Character.toChars(t));
                        output.write(t);
                    } while (stdout.available() > 0);
                    time = System.currentTimeMillis();
                } else {
                    try {
                        Thread.sleep(steptime);
                    } catch (InterruptedException e) {
                        // silently ignore
                    }
                }
            }
            try {
                StringBuffer err = new StringBuffer();
                while (stderr.available() > 0) {
                    err.append(Character.toChars(stderr.read()));
                }
                stdin.close();
                stdout.close();
                stderr.close();
                // give process some time to terminate

                try {
                    Thread.sleep(steptime);
                } catch (InterruptedException e) {
                    // silently ignore
                }

                if (p.exitValue() != 0 && err.length() > 0) {
                    throw new KielerException("Parse Error: " + err.toString(), null);
                }
            } finally {
                p.destroy();
            }

            if (p.exitValue() != 0) {
                throw new KielerException("error executing " + cmd, null);
            }
            return new ByteArrayInputStream(output.toByteArray());
        } catch (IOException e1) {
            throw new KielerException("error executing " + cmd, e1);
        }

    }
}
