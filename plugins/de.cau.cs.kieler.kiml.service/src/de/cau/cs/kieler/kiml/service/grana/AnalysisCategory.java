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
package de.cau.cs.kieler.kiml.service.grana;

import java.util.LinkedList;
import java.util.List;

import de.cau.cs.kieler.kiml.ILayoutData;

/**
 * An analysis category. Objects of this class are defined through the
 * 'analysisProviders' extension point or the preference page.
 * 
 * @author mri
 * @author msp
 * @kieler.rating proposed yellow 2012-07-10 msp
 */
public class AnalysisCategory implements ILayoutData {

    /** the categories id. */
    private String categoryId;
    /** the categories name. */
    private String categoryName;
    /** the categories description. */
    private String categoryDescription;
    /** the analyses in this category. */
    private final List<AnalysisData> analyses = new LinkedList<AnalysisData>();

    /**
     * {@inheritDoc}
     */
    public String getId() {
        return categoryId;
    }

    /**
     * {@inheritDoc}
     */
    public void setId(final String id) {
        this.categoryId = id;
    }

    /**
     * {@inheritDoc}
     */
    public String getName() {
        return categoryName;
    }

    /**
     * {@inheritDoc}
     */
    public void setName(final String name) {
        this.categoryName = name;
    }

    /**
     * {@inheritDoc}
     */
    public String getDescription() {
        return categoryDescription;
    }

    /**
     * {@inheritDoc}
     */
    public void setDescription(final String description) {
        this.categoryDescription = description;
    }

    /**
     * Returns the analyses of this category.
     * 
     * @return the analyses
     */
    public List<AnalysisData> getAnalyses() {
        return analyses;
    }
    
}
