/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright ${year} by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.kgraph.dsp

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import de.cau.cs.kieler.klighd.kgraph.KIdentifier
import de.cau.cs.kieler.klighd.krendering.KRendering
import de.cau.cs.kieler.klighd.krendering.KRenderingRef
import java.io.IOException
import org.eclipse.emf.ecore.EObject

/**
 * A custom type adapter for KGraphData to map to only primitives and ignore self containment
 * DO NOT USE! TODO: remove this class
 * just still here for the purpose of viewing my first try with this
 */
class KGraphDataTypeAdapter extends TypeAdapter<EObject> {
        
    override write(JsonWriter out, EObject value) throws IOException {
        if (value === null) {
            out.nullValue
            return
        }
        out.beginObject
        if (value instanceof KIdentifier) {
            out.name("type").value("KIdentifier")
            out.name("id").value(value.id) 
            
//        } else if (value instanceof KRenderingLibrary) {
            // TODO : call write on some other TypeAdapter handling ELists? KStyleHolder? Also for Properties. Properties also contain entire knodes as references...
            
        } else if (value instanceof KRenderingRef) {
            out.name("type").value("KRenderingRef")
            out.name("rendering")
            write(out, value.rendering) 
            
        } else if (value instanceof KRendering) {
            // skip parent
            
            out.name("type").value("KRendering")
            out.name("placementData")
            write(out, value.placementData)
            
            // out.name("actions")
            // write(out, value.actions)
            out.name("actions").value("not yet supported")
            
        } else {
            out.name("info").value("Yet unsupported type")
            out.name("toString").value(value.toString())
        }
        out.endObject
    }

    override read(JsonReader in) throws IOException {
        return null; // TODO : do we need this direction? Seems we only need to write this class to JSON
    }
}
