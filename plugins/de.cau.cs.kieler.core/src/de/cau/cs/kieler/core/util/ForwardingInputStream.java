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
package de.cau.cs.kieler.core.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * An input stream that forwards all read data to an output stream.
 * 
 * @author <a href="mailto:msp@informatik.uni-kiel.de">Miro Sp&ouml;nemann</a>
 */
public class ForwardingInputStream extends InputStream {

    /** the input stream that is wrapped by this instance. */
    private InputStream inputStream;
    /** the output stream to which all read data is forwarded. */
    private OutputStream outputStream;
    
    /**
     * Creates a forwarding input stream with the given input and output streams.
     * 
     * @param theinputStream the input stream from which data is read
     * @param theoutputStream the output stream to which data is written
     */
    public ForwardingInputStream(final InputStream theinputStream,
            final OutputStream theoutputStream) {
        this.inputStream = theinputStream;
        this.outputStream = theoutputStream;
    }
    
    /* (non-Javadoc)
     * @see java.io.InputStream#read()
     */
    @Override
    public int read() throws IOException {
        int data = inputStream.read();
        if (data >= 0) {
            outputStream.write(data);
        }
        return data;
    }
    
    /*
     * (non-Javadoc)
     * @see java.io.InputStream#read(byte[])
     */
    @Override
    public int read(final byte[] b) throws IOException {
        int count = inputStream.read(b);
        if (count > 0) {
            outputStream.write(b, 0, count);
        }
        return count;
    }
    
    /*
     * (non-Javadoc)
     * @see java.io.InputStream#read(byte[], int, int)
     */
    @Override
    public int read(final byte[] b, final int off, final int len) throws IOException {
        int count = inputStream.read(b, off, len);
        if (count > 0) {
            outputStream.write(b, off, count);
        }
        return count;
    }
    
    /*
     * (non-Javadoc)
     * @see java.io.InputStream#skip(long)
     */
    @Override
    public long skip(final long n) throws IOException {
        return inputStream.skip(n);
    }

    /*
     * (non-Javadoc)
     * @see java.io.InputStream#available()
     */
    @Override
    public int available() throws IOException {
        return inputStream.available();
    }
    
    /**
     * Closes this input stream and releases any system resources associated
     * with the stream. This method does <em>not</em> close the output stream.
     * 
     * @throws IOException if an I/O error occurs.
     */
    public void close() throws IOException {
        inputStream.close();
    }
    
    /*
     * (non-Javadoc)
     * @see java.io.InputStream#mark(int)
     */
    @Override
    public synchronized void mark(final int readlimit) {
        inputStream.mark(readlimit);
    }
    
    /*
     * (non-Javadoc)
     * @see java.io.InputStream#reset()
     */
    @Override
    public synchronized void reset() throws IOException {
        inputStream.reset();
    }
    
    /*
     * (non-Javadoc)
     * @see java.io.InputStream#markSupported()
     */
    @Override
    public boolean markSupported() {
        return inputStream.markSupported();
    }

}
