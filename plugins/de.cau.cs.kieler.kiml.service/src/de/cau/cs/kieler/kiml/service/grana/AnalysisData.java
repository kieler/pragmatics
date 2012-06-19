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

import de.cau.cs.kieler.core.alg.IFactory;
import de.cau.cs.kieler.core.alg.InstancePool;
import de.cau.cs.kieler.core.util.Dependency;
import de.cau.cs.kieler.core.util.IDepending;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.ILayoutData;

/**
 * Analyses that derive from this class are required to provide an id, name and
 * description. A category, strong and weak dependencies are optional.
 * 
 * @author mri
 * @author msp
 */
public class AnalysisData implements ILayoutData, IDepending<String> {

    /** runtime instance of the analysis. */
    private InstancePool<IAnalysis> analysisPool;
    /** the analysis id. */
    private String analysisId;
    /** the analysis name. */
    private String analysisName;
    /** the analysis description. */
    private String analysisDescription;
    /** the analysis category. */
    private String analysisCategory;
    /** is the analysis programmatic and hidden from the user? */
    private boolean isProgrAnalysis;
    /** the components. */
    private final List<Pair<String, String>> components = new LinkedList<Pair<String, String>>();
    /** the analysis dependencies. */
    private final List<Dependency<String>> dependencies = new LinkedList<Dependency<String>>();
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return analysisId;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object object) {
        if (object instanceof AnalysisData) {
            AnalysisData other = (AnalysisData) object;
            return this.analysisId.equals(other.analysisId);
        }
        return false;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return analysisId.hashCode();
    }

    /**
     * Create a pool for instances of the analysis.
     *
     * @param analysisFactory a factory for analysis instances
     */
    public void createPool(final IFactory<IAnalysis> analysisFactory) {
        this.analysisPool = new InstancePool<IAnalysis>(analysisFactory);
    }

    /**
     * Returns an instance pool for analyses.
     *
     * @return an analysis instance pool
     */
    public InstancePool<IAnalysis> getInstancePool() {
        return analysisPool;
    }

    /**
     * {@inheritDoc}
     */
    public String getId() {
        return analysisId;
    }

    /**
     * {@inheritDoc}
     */
    public void setId(String id) {
        this.analysisId = id;
    }

    /**
     * {@inheritDoc}
     */
    public String getName() {
        return analysisName;
    }

    /**
     * {@inheritDoc}
     */
    public void setName(String name) {
        this.analysisName = name;
    }

    /**
     * {@inheritDoc}
     */
    public String getDescription() {
        return analysisDescription;
    }

    /**
     * {@inheritDoc}
     */
    public void setDescription(String description) {
        this.analysisDescription = description;
    }

    /**
     * Returns the category.
     * 
     * @return the category
     */
    public String getCategory() {
        return analysisCategory;
    }
    
    /**
     * Sets the category.
     * 
     * @param category the category
     */
    public void setCategory(final String category) {
        this.analysisCategory = category;
    }

    /**
     * Whether this is a programmatic analysis that should not be shown to the user.
     * 
     * @return true if this is a helper analysis
     */
    public boolean isProgrammatic() {
        return isProgrAnalysis;
    }
    
    /**
     * Set the programmatic flag.
     * 
     * @param progr whether this is a programmatic analysis
     */
    public void setProgrammatic(final boolean progr) {
        this.isProgrAnalysis = progr;
    }

    /**
     * Returns the list of result components.
     * 
     * @return the result components
     */
    public List<Pair<String, String>> getComponents() {
        return components;
    }

    /**
     * {@inheritDoc}
     */
    public List<Dependency<String>> getDependencies() {
        return dependencies;
    }
    
}
