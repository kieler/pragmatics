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

import java.util.Arrays;
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

import de.cau.cs.kieler.core.WrappedException;
import de.cau.cs.kieler.core.alg.DefaultFactory;
import de.cau.cs.kieler.core.alg.IFactory;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.grana.dependency.Dependency;
import de.cau.cs.kieler.kiml.grana.dependency.DependencyGraph;

/**
 * Singleton class for global access to the KIML graph analysis services.
 * 
 * @author mri
 * @author msp
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow 2012-07-10 msp
 */
public final class AnalysisService {

    /** identifier of the extension point for analysis providers. */
    public static final String EXTP_ID_ANALYSIS_PROVIDERS
            = "de.cau.cs.kieler.kiml.grana.analysisProviders";
    /** name of the 'analysis' element. */
    protected static final String ELEMENT_ANALYSIS = "analysis";
    /** name of the 'category' element. */
    protected static final String ELEMENT_CATEGORY = "category";
    /** name of the 'dependency' element. */
    protected static final String ELEMENT_DEPENDENCY = "dependency";
    /** name of the 'component' element. */
    protected static final String ELEMENT_COMPONENT = "component";
    /** name of the 'analysis' attribute in the extension points. */
    protected static final String ATTRIBUTE_ANALYSIS = "analysis";
    /** name of the 'category' attribute in the extension points. */
    protected static final String ATTRIBUTE_CATEGORY = "category";
    /** name of the 'class' attribute in the extension points. */
    protected static final String ATTRIBUTE_CLASS = "class";
    /** name of the 'description' attribute in the extension points. */
    protected static final String ATTRIBUTE_DESCRIPTION = "description";
    /** name of the 'id' attribute in the extension points. */
    protected static final String ATTRIBUTE_ID = "id";
    /** name of the 'name' attribute in the extension points. */
    protected static final String ATTRIBUTE_NAME = "name";
    /** name of the 'abbreviation' attribute in the extension points. */
    protected static final String ATTRIBUTE_ABBREVIATION = "abbreviation";
    /** name of the 'weak' attribute in the extension point. */
    protected static final String ATTRIBUTE_WEAK = "weak";
    /** name of the 'programmatic' attribute in the extension point. */
    protected static final String ATTRIBUTE_PROGRAMMATIC = "programmatic";
    
    /** id of the default category. */
    public static final String DEFAULT_CATEGORY_ID = "de.cau.cs.kieler.kiml.grana.defaultCategory";

    /** the graph analysis service instance, which is created lazily. */
    private static AnalysisService instance;
    /** the factory for creation of service instances. */
    private static IFactory<? extends AnalysisService> instanceFactory
            = new DefaultFactory<AnalysisService>(AnalysisService.class);

    /**
     * Returns the graph analysis service instance. If no instance is created yet, create one
     * using the configured factory.
     * 
     * @return the graph analysis service instance
     */
    public static synchronized AnalysisService getInstance() {
        if (instance == null) {
            instance = instanceFactory.create();
        }
        return instance;
    }
    
    /**
     * Set the factory for creating instances. If an instance is already created, it is cleared
     * so the next call to {@link #getInstance()} uses the new factory.
     * 
     * @param factory an instance factory
     */
    public static void setInstanceFactory(final IFactory<? extends AnalysisService> factory) {
        instanceFactory = factory;
        instance = null;
    }
    
    
    /** the analysis dependency graph. */
    private final DependencyGraph<String, AnalysisData> dependencyGraph
            = new DependencyGraph<String, AnalysisData>();
    /** the analysis categories. */
    private final List<AnalysisCategory> categories = new LinkedList<AnalysisCategory>();
    /** the category id mapped on the appropriate category. */
    private final Map<String, AnalysisCategory> categoryIdMapping
            = new HashMap<String, AnalysisCategory>();
    /** the analysis id mapped on the appropriate analysis. */
    private final Map<String, AnalysisData> analysisIdMapping = new HashMap<String, AnalysisData>();
    /** the default category. */
    private AnalysisCategory defaultCategory;
    
    /**
     * Create the analysis service and load all registered extensions.
     */
    public AnalysisService() {
        loadAnalysisProviderExtension();
    }

    /**
     * Report an error that occurred while reading extensions.
     * 
     * @param extensionPoint the identifier of the extension point
     * @param element the configuration element
     * @param attribute the attribute that contains an invalid entry
     * @param exception an optional exception that was caused by the invalid entry
     */
    protected void reportError(final String extensionPoint, final IConfigurationElement element,
            final String attribute, final Throwable exception) {
        String message;
        if (element != null && attribute != null) {
            message = "Extension point " + extensionPoint + ": Invalid entry in attribute '"
                    + attribute + "' of element " + element.getName() + ", contributed by "
                    + element.getContributor().getName();
        } else {
            message = "Extension point " + extensionPoint
                    + ": An error occured while loading extensions.";
        }
        IStatus status = new Status(IStatus.WARNING, GranaPlugin.PLUGIN_ID, 0, message, exception);
        StatusManager.getManager().handle(status);
    }

    /**
     * Report an error that occurred while reading extensions.
     * 
     * @param exception a core exception holding a status with further information
     */
    protected void reportError(final CoreException exception) {
        StatusManager.getManager().handle(exception, GranaPlugin.PLUGIN_ID);
    }
    
    /**
     * Report a warning from reading extensions.
     * 
     * @param message a message to put as warning
     */
    protected void reportWarning(final String message) {
        IStatus status = new Status(IStatus.WARNING, GranaPlugin.PLUGIN_ID, 0, message, null);
        StatusManager.getManager().handle(status);
    }

    /**
     * Loads and registers all graph analyses and categories from the extension point.
     */
    private void loadAnalysisProviderExtension() {
        IConfigurationElement[] extensions = Platform.getExtensionRegistry()
                .getConfigurationElementsFor(EXTP_ID_ANALYSIS_PROVIDERS);

        // to sort the analyses into categories they have to be buffered first
        List<AnalysisData> analyses = new LinkedList<AnalysisData>();

        for (IConfigurationElement element : extensions) {
            if (ELEMENT_CATEGORY.equals(element.getName())) {
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
                    AnalysisCategory category = new AnalysisCategory();
                    category.setId(id);
                    category.setName(name);
                    category.setDescription(description);
                    categories.add(category);
                    categoryIdMapping.put(id, category);
                }

            } else if (ELEMENT_ANALYSIS.equals(element.getName())) {
                // initialize an analysis from the extension point
                String id = element.getAttribute(ATTRIBUTE_ID);
                String name = element.getAttribute(ATTRIBUTE_NAME);
                String description = element.getAttribute(ATTRIBUTE_DESCRIPTION);
                String category = element.getAttribute(ATTRIBUTE_CATEGORY);
                String isProgrString = element.getAttribute(ATTRIBUTE_PROGRAMMATIC);
                boolean isProgr = Boolean.parseBoolean(isProgrString);
                if (id == null || id.length() == 0) {
                    reportError(EXTP_ID_ANALYSIS_PROVIDERS, element, ATTRIBUTE_ID, null);
                } else if (name == null) {
                    reportError(EXTP_ID_ANALYSIS_PROVIDERS, element, ATTRIBUTE_NAME, null);
                } else if (description == null) {
                    reportError(EXTP_ID_ANALYSIS_PROVIDERS, element, ATTRIBUTE_DESCRIPTION, null);
                } else {
                    if (category == null || category.length() == 0) {
                        category = DEFAULT_CATEGORY_ID;
                    }
                    AnalysisData analysisData = new AnalysisData();
                    final IConfigurationElement factoryElement = element;
                    IFactory<IAnalysis> analysisFactory = new IFactory<IAnalysis>() {
                        public IAnalysis create() {
                            try {
                                return (IAnalysis)
                                        factoryElement.createExecutableExtension(ATTRIBUTE_CLASS);
                            } catch (CoreException e) {
                                throw new WrappedException(e);
                            }
                        }
                        public void destroy(final IAnalysis provider) {
                        }
                    };
                    analysisData.createPool(analysisFactory);
                    analysisData.setId(id);
                    analysisData.setName(name);
                    analysisData.setDescription(description);
                    analysisData.setCategory(category);
                    analysisData.setProgrammatic(isProgr);
                    analyses.add(analysisData);
                    analysisIdMapping.put(id, analysisData);
                    // read the analysis dependencies
                    for (IConfigurationElement child : element.getChildren()) {
                        if (ELEMENT_DEPENDENCY.equals(child.getName())) {
                            String analysisId = child.getAttribute(ATTRIBUTE_ANALYSIS);
                            String weakString = child.getAttribute(ATTRIBUTE_WEAK);
                            if (analysisId == null || analysisId.length() == 0) {
                                reportError(EXTP_ID_ANALYSIS_PROVIDERS, child,
                                        ATTRIBUTE_ANALYSIS, null);
                            } else {
                                // if the weak string is invalid the parser returns false, which handles
                                // the case correctly
                                boolean weak = Boolean.parseBoolean(weakString);
                                analysisData.getDependencies().add(new Dependency<String>(
                                                analysisId, weak));
                            }
                        } else if (ELEMENT_COMPONENT.equals(child.getName())) {
                            String componentName = child.getAttribute(ATTRIBUTE_NAME);
                            String componentAbbreviation = child.getAttribute(ATTRIBUTE_ABBREVIATION);
                            if (componentName == null || componentName.length() == 0) {
                                reportError(EXTP_ID_ANALYSIS_PROVIDERS, child, ATTRIBUTE_NAME, null);
                            } else  {
                                if (componentAbbreviation == null
                                        || componentAbbreviation.length() == 0) {
                                    componentAbbreviation = componentName;
                                }
                                analysisData.getComponents().add(new Pair<String, String>(
                                                componentName, componentAbbreviation));
                            }
                        }
                    }
                }
            }
        }
        
        // add the analyses to the dependency graph and remove analyses which
        // had unresolved or cyclic dependencies
        List<AnalysisData> unresolvedAnalyses = dependencyGraph.addAll(analyses);
        analyses.removeAll(unresolvedAnalyses);
        for (AnalysisData analysis : unresolvedAnalyses) {
            analysisIdMapping.remove(analysis.getId());
            // display a warning
            String message = "Analysis " + analysis.getId()
                            + " is missing a dependency or is part of a dependency cycle.";
            reportWarning(message);
        }
        
        // get the default category
        defaultCategory = categoryIdMapping.get(DEFAULT_CATEGORY_ID);
        // sort analyses into the categories
        for (AnalysisData analysis : analyses) {
            if (!analysis.isProgrammatic()) {
                AnalysisCategory category = categoryIdMapping.get(analysis.getCategory());
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
        Collections.sort(categories, new Comparator<AnalysisCategory>() {
            public int compare(final AnalysisCategory category1, final AnalysisCategory category2) {
                if (category1.equals(defaultCategory)) {
                    return category2.equals(defaultCategory) ? 0 : 1;
                } else {
                    return category2.equals(defaultCategory) ? -1
                            : category1.getName().compareTo(category2.getName());
                }
            }
        });
        
        // sort the analyses
        for (AnalysisCategory category : categories) {
            Collections.sort(category.getAnalyses(), new Comparator<AnalysisData>() {
                public int compare(final AnalysisData analysis1, final AnalysisData analysis2) {
                    return analysis1.getName().compareTo(analysis2.getName());
                }
            });
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
    public Collection<AnalysisData> getAnalyses() {
        return analysisIdMapping.values();
    }

    /**
     * Returns the category specified by id or null if no such category exists.
     * 
     * @param id
     *            the id
     * @return the category or null if it does not exist
     */
    public AnalysisCategory getCategory(final String id) {
        return categoryIdMapping.get(id);
    }

    /**
     * Returns the analysis specified by id or null if no such analysis exists.
     * 
     * @param id
     *            the id
     * @return the analysis or null if it does not exist
     */
    public AnalysisData getAnalysis(final String id) {
        return analysisIdMapping.get(id);
    }
    
    /**
     * Perform the given analysis on a graph using a prepared cache. Analysis dependencies are
     * resolved before the given analysis is executed.
     * 
     * @param graph the parent node of the graph to analyze
     * @param analysisId analysis identifier
     * @param monitor a progress monitor
     * @param context the result cache with stored values
     * @return the analysis result value
     */
    public Object analyze(final KNode graph, final String analysisId,
            final IKielerProgressMonitor monitor, final AnalysisContext context) {
        AnalysisData analysis = analysisIdMapping.get(analysisId);
        if (analysis != null) {
            Object result = context.getResult(analysisId);
            if (result == null) {
                result = analyze(graph, analysis, monitor, context);
            }
            return result;
        }
        return null;
    }
    
    /**
     * Perform the given analysis on a graph using a prepared cache. Analysis dependencies are
     * resolved before the given analysis is executed.
     * 
     * @param graph the parent node of the graph to analyze
     * @param primalAnalysisData the analysis to execute
     * @param monitor a progress monitor
     * @param context the result cache with stored values
     * @return the analysis result value
     */
    public Object analyze(final KNode graph, final AnalysisData primalAnalysisData,
            final IKielerProgressMonitor monitor, final AnalysisContext context) {
        List<AnalysisData> analysesSequence = getExecutionOrder(primalAnalysisData);
        doAnalyze(graph, analysesSequence, monitor, context);
        return context.getResult(primalAnalysisData.getId());
    }

    /**
     * Perform the given analyses on a graph and create a results cache. Analysis dependencies are
     * resolved before the given analyses are executed.
     *
     * @param graph the parent node of the graph to analyze
     * @param analyses the analyses to execute
     * @param monitor a progress monitor
     * @return the analyses results
     */
    public AnalysisContext analyze(final KNode graph, final Collection<AnalysisData> analyses,
            final IKielerProgressMonitor monitor) {
        List<AnalysisData> analysesSequence = getExecutionOrder(analyses);
        AnalysisContext context = new AnalysisContext();
        doAnalyze(graph, analysesSequence, monitor, context);
        return context;
    }

    /**
     * Perform the given analyses on a graph and create a results cache. Analysis dependencies must
     * be resolved using {@link #getExecutionOrder(Collection)} before this method is called.
     *
     * @param graph the parent node of the graph to analyze
     * @param analyses the analyses to execute, in correct order and with resolved dependencies
     * @param monitor a progress monitor
     * @return the analyses results
     */
    public AnalysisContext analyzePresorted(final KNode graph, final List<AnalysisData> analyses,
            final IKielerProgressMonitor monitor) {
        AnalysisContext context = new AnalysisContext();
        doAnalyze(graph, analyses, monitor, context);
        return context;
    }
    
    /**
     * Iterate over the given sequence of analyses and execute them.
     * 
     * @param graph the parent node of the graph to analyze
     * @param analysesSequence the analyses to execute, in correct order
     * @param monitor a progress monitor
     * @param resultCache the result cache with stored values
     */
    private void doAnalyze(final KNode graph, final List<AnalysisData> analysesSequence,
            final IKielerProgressMonitor monitor, final AnalysisContext context) {
        monitor.begin("Graph analyses", analysesSequence.size());
        
        // create a context object
        Map<String, Object> resultCache = context.getResults();
        for (AnalysisData analysisData : analysesSequence) {
            if (monitor.isCanceled()) {
                resultCache.put(analysisData.getId(), new AnalysisFailed(AnalysisFailed.Type.Canceled));
            } else if (resultCache.containsKey(analysisData.getId())) {
                monitor.worked(1);
            } else {
                try {
                    IAnalysis analysis = analysisData.getInstancePool().fetch();
                    Object result = analysis.doAnalysis(graph, context, monitor.subTask(1));
                    resultCache.put(analysisData.getId(), result);
                    context.putFinishedAnalysis(analysis);
                } catch (Exception exception) {
                    resultCache.put(analysisData.getId(), new AnalysisFailed(
                            AnalysisFailed.Type.Failed, exception));
                }
            }
        }
        
        // finally release all analysis instances from the instance pools
        for (AnalysisData analysisData : analysesSequence) {
            analysisData.getInstancePool().clear();
        }
        monitor.done();
    }
    
    /**
     * Takes one or more analyses and returns a list that includes the given analyses and their
     * dependencies in an order so that all dependencies of an analysis are listed before it.
     * Note that the underlying implementation is not thread-safe, so threads must synchronize
     * on the analysis service.
     * 
     * @param analyses
     *            the analyses
     * @return the modified and sorted list of analyses
     */
    public synchronized List<AnalysisData> getExecutionOrder(final AnalysisData... analyses) {
        return dependencyGraph.dependencySort(Arrays.asList(analyses));
    }

    /**
     * Takes a collection of analyses and returns a list that includes the given analyses and their
     * dependencies in an order so that all dependencies of an analysis are listed before it.
     * Note that the underlying implementation is not thread-safe, so threads must synchronize
     * on the analysis service.
     * 
     * @param analyses
     *            the analyses
     * @return the modified and sorted list of analyses
     */
    public synchronized List<AnalysisData> getExecutionOrder(final Collection<AnalysisData> analyses) {
        return dependencyGraph.dependencySort(analyses);
    }

}
