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

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.statushandlers.StatusManager;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.core.alg.BasicProgressMonitor;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.util.Dependency;
import de.cau.cs.kieler.core.util.DependencyGraph;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.grana.plugin.GranaPlugin;

/**
 * Singleton class for global access to the KIML graph analysis services.
 * 
 * @author mri
 */
public final class AnalysisServices implements IBundleChangedListener {

    /** identifier of the extension point for analysis providers. */
    public static final String EXTP_ID_ANALYSIS_PROVIDERS =
            "de.cau.cs.kieler.kiml.grana.analysisProviders";
    /** name of the 'provider' element. */
    public static final String ELEMENT_ANALYSIS_PROVIDER = "provider";
    /** name of the 'bundle' element. */
    public static final String ELEMENT_ANALYSIS_BUNDLE = "bundle";
    /** name of the 'category' element. */
    public static final String ELEMENT_ANALYSIS_CATEGORY = "category";
    /** name of the 'dependency' element. */
    public static final String ELEMENT_ANALYSIS_DEPENDENCY = "dependency";
    /** name of the 'component' element. */
    public static final String ELEMENT_ANALYSIS_COMPONENT = "component";
    /** name of the 'analysis' attribute in the extension points. */
    public static final String ATTRIBUTE_ANALYSIS = "analysis";
    /** name of the 'category' attribute in the extension points. */
    public static final String ATTRIBUTE_CATEGORY = "category";
    /** name of the 'class' attribute in the extension points. */
    public static final String ATTRIBUTE_CLASS = "class";
    /** name of the 'description' attribute in the extension points. */
    public static final String ATTRIBUTE_DESCRIPTION = "description";
    /** name of the 'id' attribute in the extension points. */
    public static final String ATTRIBUTE_ID = "id";
    /** name of the 'name' attribute in the extension points. */
    public static final String ATTRIBUTE_NAME = "name";
    /** name of the 'abbreviation' attribute in the extension points. */
    public static final String ATTRIBUTE_ABBREVIATION = "abbreviation";
    /** name of the 'weak' attribute in the extension point. */
    private static final String ATTRIBUTE_WEAK = "weak";
    /** name of the 'helper' attribute in the extension point. */
    private static final String ATTRIBUTE_HELPER = "helper";
    /** id of the default category. */
    public static final String DEFAULT_CATEGORY_ID =
            "de.cau.cs.kieler.kiml.grana.defaultCategory";

    /** the singleton instance. */
    private static AnalysisServices instance;
    /** the analysis dependency graph. */
    private final DependencyGraph<String, AbstractInfoAnalysis> dependencyGraph =
            new DependencyGraph<String, AbstractInfoAnalysis>();
    /** the analysis categories. */
    private final List<AnalysisCategory> categories =
            new LinkedList<AnalysisCategory>();
    /** the category id mapped on the appropriate category. */
    private final Map<String, AnalysisCategory> categoryIdMapping =
            new HashMap<String, AnalysisCategory>();
    /** the analysis id mapped on the appropriate analysis. */
    private final Map<String, AbstractInfoAnalysis> analysisIdMapping =
            new HashMap<String, AbstractInfoAnalysis>();
    /** the default category. */
    private AnalysisCategory defaultCategory = null;

    /**
     * Prevents this class to be instantiated from outside.
     */
    private AnalysisServices() {
    }

    /**
     * Creates the singleton and initializes it with the data from the extension
     * point.
     */
    static {
        instance = new AnalysisServices();
        // load the data from the extension point
        instance.loadAnalysisProviderExtension();
    }

    /**
     * Returns the singleton instance.
     * 
     * @return the singleton
     */
    public static AnalysisServices getInstance() {
        return instance;
    }

    /**
     * Reports an error that occurred while reading extensions.
     * 
     * @param extensionPoint
     *            the identifier of the extension point
     * @param element
     *            the configuration element
     * @param attribute
     *            the attribute that contains an invalid entry
     * @param exception
     *            an optional exception that was caused by the invalid entry
     */
    private static void reportError(final String extensionPoint,
            final IConfigurationElement element, final String attribute,
            final Exception exception) {
        String message =
                "Extension point " + extensionPoint
                        + ": Invalid entry in attribute '" + attribute
                        + "' of element " + element.getName()
                        + ", contributed by "
                        + element.getContributor().getName();
        IStatus status =
                new Status(IStatus.WARNING, GranaPlugin.PLUGIN_ID, 0, message,
                        exception);
        StatusManager.getManager().handle(status);
    }

    /**
     * Loads and registers all graph analyses and categories from the extension
     * point.
     */
    private void loadAnalysisProviderExtension() {
        IConfigurationElement[] extensions =
                Platform.getExtensionRegistry().getConfigurationElementsFor(
                        EXTP_ID_ANALYSIS_PROVIDERS);

        // to sort the analyses into categories they have to be buffered first
        List<AbstractInfoAnalysis> analyses =
                new LinkedList<AbstractInfoAnalysis>();

        for (IConfigurationElement element : extensions) {
            if (ELEMENT_ANALYSIS_CATEGORY.equals(element.getName())) {
                // initialize a category from the extension point
                String id = element.getAttribute(ATTRIBUTE_ID);
                String name = element.getAttribute(ATTRIBUTE_NAME);
                String description =
                        element.getAttribute(ATTRIBUTE_DESCRIPTION);
                if (id == null || id.length() == 0) {
                    reportError(EXTP_ID_ANALYSIS_PROVIDERS, element,
                            ATTRIBUTE_ID, null);
                } else if (name == null) {
                    reportError(EXTP_ID_ANALYSIS_PROVIDERS, element,
                            ATTRIBUTE_NAME, null);
                } else if (description == null) {
                    reportError(EXTP_ID_ANALYSIS_PROVIDERS, element,
                            ATTRIBUTE_DESCRIPTION, null);
                } else {
                    AnalysisCategory category =
                            new AnalysisCategory(id, name, description);
                    categories.add(category);
                    categoryIdMapping.put(id, category);
                }

            } else if (ELEMENT_ANALYSIS_PROVIDER.equals(element.getName())) {
                // initialize an analysis from the extension point
                try {
                    IAnalysis analysis =
                            (IAnalysis) element
                                    .createExecutableExtension(ATTRIBUTE_CLASS);
                    if (analysis != null) {
                        String id = element.getAttribute(ATTRIBUTE_ID);
                        String name = element.getAttribute(ATTRIBUTE_NAME);
                        String description =
                                element.getAttribute(ATTRIBUTE_DESCRIPTION);
                        String category =
                                element.getAttribute(ATTRIBUTE_CATEGORY);
                        String helperString =
                                element.getAttribute(ATTRIBUTE_HELPER);
                        // if the helper string is invalid the
                        // parser returns false, which handles
                        // the case correctly
                        boolean helper = Boolean.parseBoolean(helperString);
                        if (id == null || id.length() == 0) {
                            reportError(EXTP_ID_ANALYSIS_PROVIDERS, element,
                                    ATTRIBUTE_ID, null);
                        } else if (name == null) {
                            reportError(EXTP_ID_ANALYSIS_PROVIDERS, element,
                                    ATTRIBUTE_NAME, null);
                        } else if (description == null) {
                            reportError(EXTP_ID_ANALYSIS_PROVIDERS, element,
                                    ATTRIBUTE_DESCRIPTION, null);
                        } else {
                            if (category == null || category.length() == 0) {
                                category = DEFAULT_CATEGORY_ID;
                            }
                            InfoAnalysis infoAnalysis =
                                    new InfoAnalysis(analysis, id, name,
                                            description, category, helper);
                            analyses.add(infoAnalysis);
                            analysisIdMapping.put(id, infoAnalysis);
                            // read the analysis dependencies
                            for (IConfigurationElement child : element
                                    .getChildren()) {
                                if (ELEMENT_ANALYSIS_DEPENDENCY.equals(child
                                        .getName())) {
                                    String analysisId =
                                            child.getAttribute(ATTRIBUTE_ANALYSIS);
                                    String weakString =
                                            child.getAttribute(ATTRIBUTE_WEAK);
                                    if (analysisId == null
                                            || analysisId.length() == 0) {
                                        reportError(EXTP_ID_ANALYSIS_PROVIDERS,
                                                child, ATTRIBUTE_ANALYSIS, null);
                                    } else {
                                        // if the weak string is invalid the
                                        // parser returns false, which handles
                                        // the case correctly
                                        boolean weak =
                                                Boolean.parseBoolean(weakString);
                                        infoAnalysis.getDependencies().add(
                                                new Dependency<String>(
                                                        analysisId, weak));
                                    }
                                } else if (ELEMENT_ANALYSIS_COMPONENT
                                        .equals(child.getName())) {
                                    String componentName =
                                            child.getAttribute(ATTRIBUTE_NAME);
                                    String componentAbbreviation =
                                            child.getAttribute(ATTRIBUTE_ABBREVIATION);
                                    if (componentName == null
                                            || componentName.length() == 0) {
                                        reportError(EXTP_ID_ANALYSIS_PROVIDERS,
                                                child, ATTRIBUTE_NAME, null);
                                    } else  {
                                        if (componentAbbreviation == null
                                                || componentAbbreviation.length() == 0) {
                                            componentAbbreviation = componentName;
                                        }
                                        infoAnalysis.getComponents().add(
                                                new Pair<String, String>(
                                                        componentName,
                                                        componentAbbreviation));
                                    }
                                }
                            }
                        }
                    }
                } catch (CoreException exception) {
                    StatusManager.getManager().handle(exception,
                            GranaPlugin.PLUGIN_ID);
                }
            } else if (ELEMENT_ANALYSIS_BUNDLE.equals(element.getName())) {
                // initialize an analysis bundle from the extension point
                try {
                    IAnalysisBundle analysisBundle =
                            (IAnalysisBundle) element
                                    .createExecutableExtension(ATTRIBUTE_CLASS);
                    if (analysisBundle != null) {
                        String name = element.getAttribute(ATTRIBUTE_NAME);
                        String description =
                                element.getAttribute(ATTRIBUTE_DESCRIPTION);
                        if (name == null) {
                            reportError(EXTP_ID_ANALYSIS_PROVIDERS, element,
                                    ATTRIBUTE_NAME, null);
                        } else if (description == null) {
                            reportError(EXTP_ID_ANALYSIS_PROVIDERS, element,
                                    ATTRIBUTE_DESCRIPTION, null);
                        } else {
                            for (AbstractInfoAnalysis analysis : analysisBundle
                                    .getAnalyses()) {
                                analyses.add(analysis);
                                analysisIdMapping.put(analysis.getId(),
                                        analysis);
                            }
                            analysisBundle.addBundleChangedListener(this);
                        }
                    }
                } catch (CoreException exception) {
                    StatusManager.getManager().handle(exception,
                            GranaPlugin.PLUGIN_ID);
                }
            }
        }
        // add the analyses to the dependency graph and remove analyses which
        // had unresolved or cyclic dependencies
        List<AbstractInfoAnalysis> unresolvedAnalyses =
                dependencyGraph.addAll(analyses);
        analyses.removeAll(unresolvedAnalyses);
        for (AbstractInfoAnalysis analysis : unresolvedAnalyses) {
            analysisIdMapping.remove(analysis.getId());
            // display a warning
            String message =
                    "Analysis "
                            + analysis.getId()
                            + " is missing a dependency or is part of a dependency cycle.";
            IStatus status =
                    new Status(IStatus.WARNING, GranaPlugin.PLUGIN_ID, 0,
                            message, null);
            StatusManager.getManager().handle(status);
        }
        // get the default category
        defaultCategory = categoryIdMapping.get(DEFAULT_CATEGORY_ID);
        // sort analyses into the categories
        for (AbstractInfoAnalysis analysis : analyses) {
            if (!analysis.isHelper()) {
                AnalysisCategory category =
                        categoryIdMapping.get(analysis.getCategory());
                // if the category does not exists take default one
                if (category == null) {
                    category = defaultCategory;
                }
                if (category != null) {
                    category.getAnalyses().add(analysis);
                }
            }
        }
        // sort the categories
        Collections.sort(categories, new CategoryComparator());
        // sort the analyses
        for (AnalysisCategory category : categories) {
            Collections.sort(category.getAnalyses(), new AnalysisComparator());
        }
    }

    /**
     * Returns the categories.
     * 
     * @return the categories
     */
    public List<AnalysisCategory> getCategories() {
        return categories;
    }

    /**
     * Returns the analyses.
     * 
     * @return the analyses
     */
    public Collection<AbstractInfoAnalysis> getAnalyses() {
        return analysisIdMapping.values();
    }

    /**
     * Returns the category specified by id or null if no such category exists.
     * 
     * @param id
     *            the id
     * @return the category or null if it does not exist
     */
    public AnalysisCategory getCategoryById(final String id) {
        return categoryIdMapping.get(id);
    }

    /**
     * Returns the analysis specified by id or null if no such analysis exists.
     * 
     * @param id
     *            the id
     * @return the analysis or null if it does not exist
     */
    public AbstractInfoAnalysis getAnalysisById(final String id) {
        return analysisIdMapping.get(id);
    }
    
    /**
     * Perform the given analysis on a graph.
     * 
     * @param node the parent node of the graph to analyze
     * @param analysisId analysis identifier
     * @param resultCache the result cache with stored values
     * @return the analysis result value
     */
    public Object analyze(final KNode node, final String analysisId,
            final Map<String, Object> resultCache) {
        Object result = resultCache.get(analysisId);
        if (result == null) {
            AbstractInfoAnalysis analysis = analysisIdMapping.get(analysisId);
            if (analysis != null) {
                List<AbstractInfoAnalysis> analysesSequence = getExecutionOrder(
                        Lists.newArrayList(analysis));
                for (AbstractInfoAnalysis a : analysesSequence) {
                    if (!resultCache.containsKey(a.getId())) {
                        try {
                            Object o = a.doAnalysis(node, resultCache, new BasicProgressMonitor(0));
                            resultCache.put(a.getId(), o);
                        } catch (Exception exception) {
                            resultCache.put(a.getId(), new AnalysisFailed(AnalysisFailed.Type.Failed,
                                    exception));
                        }
                    }
                }
                result = resultCache.get(analysisId);
            }
        }
        return result;
    }

    /**
     * Takes a list of analyses and returns a list that includes the given
     * analysis and their dependencies in an order so that all dependencies of
     * an analysis are listed before it.
     * 
     * @param analyses
     *            the analyses
     * @return the modified and sorted list of analyses
     */
    public List<AbstractInfoAnalysis> getExecutionOrder(
            final List<AbstractInfoAnalysis> analyses) {
        return dependencyGraph.dependencySort(analyses);
    }

    /**
     * {@inheritDoc}
     */
    public void analysisAdded(final AbstractInfoAnalysis analysis) {
        // check if all dependencies for the analysis are present
        if (dependencyGraph.add(analysis)) {
            analysisIdMapping.put(analysis.getId(), analysis);
            AnalysisCategory category =
                    categoryIdMapping.get(analysis.getCategory());
            // if the category does not exist take default one
            if (category == null) {
                category = defaultCategory;
            }
            if (category != null) {
                category.getAnalyses().add(analysis);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public void analysisRemoved(final AbstractInfoAnalysis analysis) {
        List<AbstractInfoAnalysis> removedAnalyses =
                dependencyGraph.remove(analysis);
        for (AnalysisCategory category : categories) {
            category.getAnalyses().removeAll(removedAnalyses);
        }
        analysisIdMapping.remove(analysis.getId());
    }

    /**
     * Helper class for comparing categories.
     */
    private class CategoryComparator implements Comparator<AnalysisCategory> {

        /**
         * {@inheritDoc}
         */
        public int compare(final AnalysisCategory category1,
                final AnalysisCategory category2) {
            if (category1 == defaultCategory) {
                if (category2 == defaultCategory) {
                    return 0;
                } else {
                    return 1;
                }
            } else {
                if (category2 == defaultCategory) {
                    return -1;
                } else {
                    return category1.getName().compareTo(category2.getName());
                }
            }
        }

    }

    /**
     * Helper class for comparing analyses.
     */
    private class AnalysisComparator implements
            Comparator<AbstractInfoAnalysis> {

        /**
         * {@inheritDoc}
         */
        public int compare(final AbstractInfoAnalysis analysis1,
                final AbstractInfoAnalysis analysis2) {
            return analysis1.getName().compareTo(analysis2.getName());
        }

    }

    /**
     * This class is a wrapper for analyses to attach additional informations
     * received through the extension point.
     */
    private class InfoAnalysis extends AbstractInfoAnalysis {

        /** the wrapped analysis. */
        private final IAnalysis wrappedAnalysis;
        /** the analysis id. */
        private final String analysisId;
        /** the analysis name. */
        private final String analysisName;
        /** the analysis description. */
        private final String analysisDescription;
        /** the analysis category. */
        private final String analysisCategory;
        /** is the analysis a helper analysis? */
        private final boolean analysisHelper;
        /** the components. */
        private List<Pair<String, String>> components =
                new LinkedList<Pair<String, String>>();
        /** the analysis dependencies. */
        private final List<Dependency<String>> dependencies =
                new LinkedList<Dependency<String>>();

        /**
         * Constructs a analysis with attached information.
         * 
         * @param analysis
         *            the analysis this object is wrapping
         * @param id
         *            the analysis id
         * @param name
         *            the analysis name
         * @param description
         *            the analysis description
         * @param category
         *            the analysis category
         */
        public InfoAnalysis(final IAnalysis analysis, final String id,
                final String name, final String description,
                final String category, final boolean helper) {
            wrappedAnalysis = analysis;
            analysisId = id;
            analysisName = name;
            analysisDescription = description;
            analysisCategory = category;
            analysisHelper = helper;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getId() {
            return analysisId;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getName() {
            return analysisName;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getDescription() {
            return analysisDescription;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getCategory() {
            return analysisCategory;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean isHelper() {
            return analysisHelper;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public List<Pair<String, String>> getComponents() {
            return components;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public List<Dependency<String>> getDependencies() {
            return dependencies;
        }

        /**
         * {@inheritDoc}
         */
        public Object doAnalysis(final KNode parentNode,
                final Map<String, Object> results,
                final IKielerProgressMonitor progressMonitor) {
            if (wrappedAnalysis != null) {
                return wrappedAnalysis.doAnalysis(parentNode, results,
                        progressMonitor);
            } else {
                return null;
            }
        }
    }
}
