/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2015 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.cau.cs.kieler.formats.json.JsonFormatHandler;
import de.cau.cs.kieler.formats.json.JsonImporter;
import de.cau.cs.kieler.formats.standalone.StandaloneGraphLayoutEngine;

/**
 * Tests for successful layout of a graph given as a JSON-String.
 *
 * @author csp
 */
public class JsonTest {

    @Test
    public void testSuccessfulLayout() {
        String exampleGraph =
                "{  id: \"root\", properties: { direction: 'LEFT' },    "
                + "children: [{      id: \"n1\",       labels: [ { text: \"n1\" } ],    "
                + " width: 100,       height: 100   },{       id: \"n2\",       "
                + "labels: [ { text: \"n2\" } ],     width: 100,       height: 50,    "
                + "   ports: [{         id: \"n2_p1\",            width: 10,     "
                + "       height: 10        }],       children: [{              id: \"n3\",    "
                + "       labels: [ { text: \"n3\" } ],         width: 20,            height: 20   "
                + "     },{           id: \"n4\",           labels: [ { text: \"n4\" } ],     "
                + "    width: 20,            height: 20}       ], "
                + "       edges: [{         id: \"e4\", "
                + "          source: \"n3\",           target: \"n4\"        }]    },{"
                + "       id: \"n5\",       labels: [ { text: \"n5\" } ],     width: 100,"
                + "       height: 50    }],   edges: [{     id: \"e1\", "
                + "      labels: [ { text: \"e1\" } ],     source: \"n1\", "
                + "      target: \"n2\",       targetPort: \"n2_p1\" },{   "
                + "    id: \"e2\",       labels: [ { text: \"e2\" } ],   "
                + "  source: \"n1\",       target: \"n5\"    }]}";

        JsonFormatHandler jsonHandler = new JsonFormatHandler();
        JsonImporter jsonImporter = new JsonImporter();

        // execute the layout algorithm
        String result = StandaloneGraphLayoutEngine.performLayout(exampleGraph, jsonHandler, jsonImporter);

        assertTrue(result != null && result != "");
    }

}
