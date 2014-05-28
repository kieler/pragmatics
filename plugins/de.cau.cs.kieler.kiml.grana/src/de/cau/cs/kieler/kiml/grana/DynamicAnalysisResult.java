/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.grana;

import java.util.List;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * Container class for analysis results that can vary in the number of results.
 * 
 * The
 * 
 * @author uru
 */
public class DynamicAnalysisResult {

    private List<Object> labels = Lists.newArrayList();
    private List<Object> values = Lists.newArrayList();

    /**
     * Constructs a new result, where both lists must have the same size.
     * 
     * @param labels
     *            the labels specifying the sub-results more precisely
     * @param values
     *            the result values
     */
    public DynamicAnalysisResult(final Iterable<String> labels, final Iterable<Object> values) {
        Iterables.addAll(this.labels, labels);
        Iterables.addAll(this.values, values);
    }

    /**
     * @return the labels
     */
    public List<Object> getLabels() {
        return labels;
    }

    /**
     * @return the values
     */
    public List<Object> getValues() {
        return values;
    }
}
