/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2018 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.kgraph.dsp.gson_utils

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import de.cau.cs.kieler.klighd.krendering.KLeftPosition
import de.cau.cs.kieler.klighd.krendering.KRightPosition
import de.cau.cs.kieler.klighd.krendering.KXPosition
import java.io.IOException

/**
 * Type adapter to correctly serialize {@link KXPosition}.
 * {@link KLeftPosition}s are serialized under the key 'left',
 * {@link KRightPosition}s are serialized under the key 'right'.
 * 
 * @author nir
 */
public class KXPositionTypeAdapter extends TypeAdapter<KXPosition>{
        
    override write(JsonWriter out, KXPosition value) throws IOException {
        if (value === null) {
            out.nullValue
            return
        }
        
        out.beginObject
        if (value instanceof KLeftPosition) {
            out.name("left")
        } else if (value instanceof KRightPosition) {
            out.name("right")
        }
            out.beginObject
                out.name("absolute").value(value.absolute)
                out.name("relative").value(value.relative)
            out.endObject
        out.endObject
    }

    override read(JsonReader in) throws IOException {
        return null; // only serialization is needed yet.
    }
}
