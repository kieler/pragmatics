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
import de.cau.cs.kieler.klighd.krendering.KBottomPosition
import de.cau.cs.kieler.klighd.krendering.KTopPosition
import de.cau.cs.kieler.klighd.krendering.KYPosition
import java.io.IOException

/**
 * Type adapter to correctly serialize {@link KYPosition}.
 * {@link KTopPosition}s are serialized under the key 'top',
 * {@link KVottomPosition}s are serialized under the key 'bottom'.
 * 
 * @author nir
 */
public class KYPositionTypeAdapter extends TypeAdapter<KYPosition>{
        
    override write(JsonWriter out, KYPosition value) throws IOException {
        if (value === null) {
            out.nullValue
            return
        }
        
        out.beginObject
        if (value instanceof KTopPosition) {
            out.name("top")
        } else if (value instanceof KBottomPosition) {
            out.name("bottom")
        }
            out.beginObject
                out.name("absolute").value(value.absolute)
                out.name("relative").value(value.relative)
            out.endObject
        out.endObject
    }

    override read(JsonReader in) throws IOException {
        return null; // Only serialization is needed yet.
    }
}
