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
package de.cau.cs.kieler.kiml.grana;

import java.util.LinkedList;
import java.util.List;

/**
 * An analysis category. Objects of this class are defined through the
 * 'analysisProviders' extension point or the preference page.
 * 
 * @author mri
 */
public class AnalysisCategory {

    /** the categories id. */
    private String categoryId;

    /** the categories name. */
    private String categoryName;

    /** the categories description. */
    private String categoryDescription;

    /** the analyses in this category. */
    private List<AbstractInfoAnalysis> analyses = new LinkedList<AbstractInfoAnalysis>();

    /**
     * Constructs an analysis category.
     * 
     * @param id
     *            the analysis id
     * @param name
     *            the analysis name
     * @param description
     *            the analysis description
     */
    public AnalysisCategory(final String id, final String name,
            final String description) {
        categoryId = id;
        categoryName = name;
        categoryDescription = description;
    }

    /**
     * Returns the category id.
     * 
     * @return the category id
     */
    public String getId() {
        return categoryId;
    }

    /**
     * Returns the category name.
     * 
     * @return the category name
     */
    public String getName() {
        return categoryName;
    }

    /**
     * Returns the category description.
     * 
     * @return the category description
     */
    public String getDescription() {
        return categoryDescription;
    }

    /**
     * Returns the analyses of this category.
     * 
     * @return the analyses
     */
    public List<AbstractInfoAnalysis> getAnalyses() {
        return analyses;
    }
}
