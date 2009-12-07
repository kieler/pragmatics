/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.graphviz.layouter;

import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author msp
 */
public class NonBlockingInputStream extends InputStream {

    /** the nested input stream. */
    private InputStream inputStream;
    
    /**
     * Creates a non-blocking input stream for the given stream.
     * 
     * @param theinputStream the input stream
     */
    public NonBlockingInputStream(final InputStream theinputStream) {
        this.inputStream = theinputStream;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public int read() throws IOException {
        if (inputStream.available() > 0) {
            return inputStream.read();
        } else {
            return -1;
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public int read(final byte[] b) throws IOException {
        return read(b, 0, b.length);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public int read(final byte[] b, final int off, final int len) throws IOException {
        int avail = inputStream.available();
        if (avail > 0) {
            return inputStream.read(b, off, len <= avail ? len : avail);
        } else {
            return -1;
        }
    }

    /**
     * This implementation of {@code close} does nothing. Use {@link #forceClose()} to
     * close the input stream.
     */
    @Override
    public void close() {
        // ignore closure command
    }
    
    /**
     * Forces the nested input stream to be closed.
     * 
     * @throws IOException if an I/O error occurs
     */
    public void forceClose() throws IOException {
        inputStream.close();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public int available() throws IOException {
        return inputStream.available();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void mark(final int readlimit) {
        inputStream.mark(readlimit);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean markSupported() {
        return inputStream.markSupported();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void reset() throws IOException {
        inputStream.reset();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public long skip(final long n) throws IOException {
        return inputStream.skip(n);
    }

}
