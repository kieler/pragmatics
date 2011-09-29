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

package de.cau.cs.kieler.kwebs.transformation;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import de.cau.cs.kieler.core.kgraph.KNode;

import org.apache.commons.codec.binary.Base64InputStream;
import org.apache.commons.codec.binary.Base64OutputStream;
import org.eclipse.emf.ecore.xmi.XMLResource;

/**
 * Transformer for the KGraph model and compressed XMI serialization.
 *
 * !!! EXPERIMENTAL !!!
 * 
 * @kieler.rating 2011-05-04 red
 *
 * @author swe
 */
public class KGraphXmiCompressedTransformer extends KGraphXmiTransformer {

    /** Name of the zip entry. */
    private static final String ZIP_ENTRY
        = "data";
    
    /** Used compression level. */
    private static final int COMPRESSION_LEVEL
        = 9;
    
    private static final Map<Object, Object> options
        = new HashMap<Object, Object>();
    
    static {
        options.put(XMLResource.OPTION_ZIP, Boolean.TRUE);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String serialize(final KNode graph) {
        try {
            persistDataElements(graph);
            ByteArrayOutputStream target = new ByteArrayOutputStream();
            Base64OutputStream codec = new Base64OutputStream(target, true, -1, null);
//            ZipOutputStream zip = new ZipOutputStream(codec);
//            zip.setLevel(COMPRESSION_LEVEL);
//            zip.putNextEntry(new ZipEntry(ZIP_ENTRY));
//            serializeBinary(graph, zip, null);
//            zip.closeEntry();
//            zip.flush();
//            zip.close(); 
//            target.flush();
//            String result = target.toString("US-ASCII");
//            target.close();
            serializeBinary(graph, codec, options);
            target.flush();
            String result = target.toString("US-ASCII");
            target.close();
            return result;
        } catch (IOException e) {
            throw new TransformationException("Could not serialize graph", e);
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public KNode deserialize(final String serializedGraph) {
        try {
            ByteArrayInputStream source = new ByteArrayInputStream(serializedGraph.getBytes("US-ASCII"));
            Base64InputStream codec = new Base64InputStream(source, false, -1, null);
//            ZipInputStream zip = new ZipInputStream(codec);
//            ZipEntry zEntry = zip.getNextEntry();
//            if (!zEntry.getName().equals(ZIP_ENTRY)) {
//                throw new IOException("Graph data not found");
//            }
//            KNode result = super.deserializeBinary(zip, null);
//            unpersistDataElements(result);
//            zip.close();
//            codec.close();
//            source.close();
            KNode result = super.deserializeBinary(codec, options);
            unpersistDataElements(result);
            return result;
        } catch (IOException e) {
            throw new TransformationException("Could not deserialize graph", e);
        }
    }
    
}
