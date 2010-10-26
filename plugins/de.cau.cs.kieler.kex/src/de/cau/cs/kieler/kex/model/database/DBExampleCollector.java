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
package de.cau.cs.kieler.kex.model.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.kex.model.Example;
import de.cau.cs.kieler.kex.model.ExampleCollector;
import de.cau.cs.kieler.kex.model.SourceType;

/**
 * this is an example for extending KEX with online examples.
 * 
 * @author pkl
 * 
 */
public class DBExampleCollector extends ExampleCollector {

    private final Map<String, Example> examplePool;

    private final List<String> categories;

    // private final DatabaseHandler dbHandler;

    public DBExampleCollector() {
        examplePool = new HashMap<String, Example>();
        categories = new ArrayList<String>();
        // dbHandler = new DatabaseHandler();
    }

    @Override
    public Map<String, Example> getExamplePool() {
        return examplePool;

    }

    @Override
    public void load() {
        // database.loadExample();
    }

    public static Example getExample(final String exampleTitle) throws KielerException {
        // to implement
        return null;
    }

    public List<String> getCategories() {
        return categories;
    }

    @Override
    public SourceType getSourceType() {
        return SourceType.PUBLIC;
    }

}
