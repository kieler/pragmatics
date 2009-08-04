/******************************************************************************
 * KIELER - Kiel Integrated Environment for Layout for the Eclipse RCP
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2008 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.layout.services;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.layout.options.LayoutOptions;
import de.cau.cs.kieler.kiml.layout.util.KimlLayoutUtil;

/**
 * Singleton class for access to the KIML layout services. This class is used globally
 * to retrieve appropriate layout providers for a layout graph instance.
 * 
 * @author <a href="mailto:msp@informatik.uni-kiel.de">Miro Sp&ouml;nemann</a>
 */
public class LayoutServices {

    /** identifier of the 'general' diagram type, which applies to all diagrams */
    public static final String DIAGRAM_TYPE_GENERAL = "de.cau.cs.kieler.layout.info.types.general";
    /** identifier of the 'nolayout' diagram type, for which no automatic layout must be applied */
    public static final String DIAGRAM_TYPE_NOLAYOUT = "de.cau.cs.kieler.layout.info.types.nolayout";
    
	/** the singleton instance */
	public static LayoutServices INSTANCE = null;
	
	/** Data type used to store information of diagram type bindings and options */
	private static class DiagramSetup {
		/** identifier of the diagram type */
		String diagramType;
		/** identifier of the diagram setup */
		String setupId;
		/** map of layout option identifiers to their configured values */
		Map<String, String> options = new HashMap<String, String>();
	}

	/** the list of layout listeners that have been loaded at startup */
	private List<ILayoutListener> listeners = new LinkedList<ILayoutListener>();
	/** mapping of layout provider identifiers to their data instances */
	private Map<String, LayoutProviderData> layoutProviderMap
			= new LinkedHashMap<String, LayoutProviderData>();
	/** mapping of layout option identifiers to their data instances */
	private Map<String, LayoutOptionData> layoutOptionMap
			= new HashMap<String, LayoutOptionData>();
	/** mapping of layout type identifiers to their names */
	private Map<String, String> layoutTypeMap = new HashMap<String, String>();
	/** mapping of category identifiers to their names */
	private Map<String, String> categoryMap = new HashMap<String, String>();
	/** mapping of diagram type identifiers to their names */
	private Map<String, String> diagramTypeMap = new LinkedHashMap<String, String>();
	/** mapping of graphical edit parts to associated diagram setups */
	private Map<Class<?>, DiagramSetup> editPartBindingMap
			= new LinkedHashMap<Class<?>, DiagramSetup>();
	/** list of unprocessed layout options */
	private List<String[]> unprocessedOptions = new LinkedList<String[]>();

	/**
	 * Initializes the layout services <em>after</em> all contributions have been
	 * registered from the extension points.
	 */
	public void initialize() {
		// process all layout option configurations
		for (String[] option : unprocessedOptions) {
			String diagramSetupId = option[0];
			String optionId = option[1];
			String value = option[2]; 
			for (DiagramSetup diagramSetup : editPartBindingMap.values()) {
				if (diagramSetupId.equals(diagramSetup.setupId)) {
					diagramSetup.options.put(optionId, value);
					break;
				}
			}
		}
		unprocessedOptions.clear();
	}
	
	/**
	 * Adds the given layout listener to the list of registered listeners.
	 * 
	 * @param listener layout listener to register
	 */
	public void addLayoutListener(ILayoutListener listener) {
		listeners.add(listener);
	}
	
	/**
	 * Removes the given layout listener from the list of registered
	 * listeners.
	 * 
	 * @param listener layout listener to remove
	 */
	public void removeLayoutListener(ILayoutListener listener) {
	    listeners.remove(listener);
	}
	
	/**
	 * Calls the {@link ILayoutListener#layoutRequested layoutRequested}
	 * method of all registered layout listeners.
	 * 
	 * @param layoutGraph layout graph for which layout is requested
	 */
	public void layoutRequested(KNode layoutGraph) {
		for (ILayoutListener listener : listeners) {
			listener.layoutRequested(layoutGraph);
		}
	}
	
	/**
	 * Calls the {@link ILayoutListener#layoutPerformed layoutPerformed}
	 * method of all registered layout listeners.
	 * 
	 * @param layoutGraph layout graph for which layout was performed
	 * @param monitor progress monitor containing execution time results
	 */
	public void layoutPerformed(KNode layoutGraph,
			IKielerProgressMonitor monitor) {
		for (ILayoutListener listener : listeners) {
			listener.layoutPerformed(layoutGraph, monitor);
		}
	}

	/**
	 * Registers the given layout provider. If there is already a
	 * registered provider data instance with the same identifier, it
	 * is overwritten.
	 * 
	 * @param providerData data instance of the layout provider to register
	 */
	public void addLayoutProvider(LayoutProviderData providerData) {
	    if (providerData.id != null)
	        layoutProviderMap.put(providerData.id, providerData);
	}
	
	/**
	 * Returns the layout provider data associated with the given
	 * identifier.
	 * 
	 * @param id layout provider identifier
	 * @return the corresponding layout provider data, or {@code null} if there
	 *     is no provider with the given identifier
	 */
	public LayoutProviderData getLayoutProviderData(String id) {
	    return layoutProviderMap.get(id);
	}

	/**
	 * Returns a data collection for all registered layout providers.
	 * 
	 * @return collection of registered layout providers
	 */
    public Collection<LayoutProviderData> getLayoutProviderData() {
        return layoutProviderMap.values();
    }
	
	/**
	 * Returns the most appropriate layout provider for the given
	 * node.
	 * 
	 * @param layoutNode node for which a layout provider is requested
	 * @return a layout provider instance that fits the layout hints for
	 *     the given node
	 */
	public AbstractLayoutProvider getLayoutProvider(KNode layoutNode) {
	    KShapeLayout nodeLayout = KimlLayoutUtil.getShapeLayout(layoutNode);
	    String layoutHint = LayoutOptions.getLayoutHint(nodeLayout);
	    // try to get a specific provider for the given node
	    LayoutProviderData providerData = layoutProviderMap.get(layoutHint);
	    if (providerData != null)
	        return providerData.instance;

	    // find the most appropriate provider from the layout type and diagram type
	    String diagramType = LayoutOptions.getDiagramType(nodeLayout);
	    return findAppropriateProvider(layoutHint, diagramType).instance;
	}
	
	/**
	 * Registers the given layout option. If there is already a
	 * registered option data instance with the same identifier, it
	 * is overwritten.
	 * 
	 * @param optionData data instance of the layout option to register
	 */
	public void addLayoutOption(LayoutOptionData optionData) {
	    if (optionData.id != null)
	        layoutOptionMap.put(optionData.id, optionData);
	}
	
	/**
	 * Returns the layout option data associated with the given
	 * identifier.
	 * 
	 * @param id layout option identifier
	 * @return the corresponding layout option data, or {@code null} if there
	 *     is no option with the given identifier
	 */
	public LayoutOptionData getLayoutOptionData(String id) {
	    return layoutOptionMap.get(id);
	}
	
	/**
	 * Registers the given layout type.
	 * 
	 * @param id identifier of the type
	 * @param name user friendly name of the type
	 */
	public void addLayoutType(String id, String name) {
	    if (id != null && name != null)
	        layoutTypeMap.put(id, name);
	}
	
	/**
	 * Returns the name of the layout type with given identifier.
	 * 
	 * @param id identifier of the type
	 * @return user friendly name of the type, or {@code null} if the layout type is
	 *     not registered
	 */
	public String getLayoutTypeName(String id) {
	    return layoutTypeMap.get(id);
	}
	
	/**
	 * Registers the given edit part with the diagram type.
	 * 
	 * @param editPartType class of edit parts to register
	 * @param diagramType identifier of the associated diagram type
	 */
	public void addEditPartBinding(Class<?> editPartType, String diagramType,
			String diagramSetupId) {
	    if (editPartType != null && diagramType != null) {
	    	DiagramSetup diagramSetup = new DiagramSetup();
	    	diagramSetup.diagramType = diagramType;
	    	diagramSetup.setupId = diagramSetupId;
	        editPartBindingMap.put(editPartType, diagramSetup);
	    }
	}
	
	/**
	 * Returns the identifier of the diagram type associated with the given edit part.
	 * 
	 * @param editPartType class of edit part
	 * @return identifier of the associated diagram type, or {@code null} if the edit part
	 *     type is not registered
	 */
	public String getDiagramTypeFor(Class<?> editPartType) {
		DiagramSetup diagramSetup = editPartBindingMap.get(editPartType);
		if (diagramSetup == null)
			return null;
		else
			return diagramSetup.diagramType;
	}
	
	/**
	 * Registers the given category.
	 * 
	 * @param id identifier of the category
	 * @param name user friendly name of the category
	 */
	public void addCategory(String id, String name) {
	    if (id != null && name != null)
	        categoryMap.put(id, name);
	}
	
	/**
	 * Returns the name of the given category.
	 * 
	 * @param id identifier of the category
	 * @return user friendly name of the category
	 */
	public String getCategoryName(String id) {
	    return categoryMap.get(id);
	}
	
	/**
	 * Registers the given diagram type. Diagram types with
	 * {@link DIAGRAM_TYPE_NOLAYOUT} as identifier are rejected, since this
	 * is a special value used to indicate that a diagram part shall not
	 * be layouted at all.
	 * 
	 * @param id identifier of the diagram type
	 * @param name user friendly name of the diagram type
	 */
	public void addDiagramType(String id, String name) {
	    if (id != null && name != null && !DIAGRAM_TYPE_NOLAYOUT.equals(id))
	        diagramTypeMap.put(id, name);
	}

	/**
	 * Returns the name of the given diagram type.
	 * 
	 * @param id identifier of the diagram type
	 * @return user friendly name of the diagram type
	 */
	public String getDiagramTypeName(String id) {
	    return diagramTypeMap.get(id);
	}
	
	/**
	 * Returns a collection of registered diagram types.
	 * 
	 * @return the registered diagram types
	 */
	public Collection<String> getDiagramTypes() {
	    return diagramTypeMap.keySet();
	}
	
	/**
	 * Registers a layout option configuration for the given diagram setup.
	 * 
	 * @param diagramSetupId identifier of a diagram setup
	 * @param optionId identifier of a layout option
	 * @param value value for the layout option
	 */
	public void setupOption(String diagramSetupId, String optionId, String value) {
		unprocessedOptions.add(new String[] {diagramSetupId, optionId, value});
	}
	
	/**
	 * Determines an appropriate layout provider for the given layout type and
	 * diagram type.
	 * 
	 * @param layoutType hint about the layout type to choose, or {@code null}
	 *     if no specific layout type is selected
	 * @param diagramType identifier of the diagram type that is to be layouted
	 * @return data instance for the most appropriate layout provider
	 */
	private LayoutProviderData findAppropriateProvider(String layoutType, String diagramType) {
        Iterator<LayoutProviderData> providerIter = layoutProviderMap.values().iterator();
        LayoutProviderData bestProvider = null;
        int bestPrio = LayoutProviderData.MIN_PRIORITY;
        boolean matchesLayoutType = false, matchesDiagramType = false,
                matchesGeneralDiagram = false;
        // look for an appropriate provider and return the best one
        while (providerIter.hasNext()) {
            LayoutProviderData providerData = providerIter.next();
            int currentPrio = providerData.getSupportedPriority(diagramType);
            if (matchesLayoutType) {
                if (providerData.type.equals(layoutType)) {
                    if (matchesDiagramType) {
                        if (currentPrio > bestPrio) {
                            bestProvider = providerData;
                            bestPrio = currentPrio;
                        }
                    }
                    else {
                        if (currentPrio > LayoutProviderData.MIN_PRIORITY) {
                            bestProvider = providerData;
                            bestPrio = currentPrio;
                            matchesDiagramType = true;
                            matchesGeneralDiagram = false;
                        }
                        else {
                            currentPrio = providerData.getSupportedPriority(DIAGRAM_TYPE_GENERAL);
                            if (matchesGeneralDiagram) {
                                if (currentPrio > bestPrio) {
                                    bestProvider = providerData;
                                    bestPrio = currentPrio;
                                }
                            }
                            else {
                                if (currentPrio > LayoutProviderData.MIN_PRIORITY) {
                                    bestProvider = providerData;
                                    bestPrio = currentPrio;
                                    matchesGeneralDiagram = true;
                                }
                                else if (bestProvider == null)
                                    bestProvider = providerData;
                            }
                        }
                    }
                }
            }
            else {
                if (providerData.type.equals(layoutType)) {
                    bestProvider = providerData;
                    matchesLayoutType = true;
                    if (currentPrio > LayoutProviderData.MIN_PRIORITY) {
                        bestPrio = currentPrio;
                        matchesDiagramType = true;
                        matchesGeneralDiagram = false;
                    }
                    else {
                        matchesDiagramType = false;
                        currentPrio = providerData.getSupportedPriority(DIAGRAM_TYPE_GENERAL);
                        if (currentPrio > LayoutProviderData.MIN_PRIORITY) {
                            bestPrio = currentPrio;
                            matchesGeneralDiagram = true;
                        }
                        else
                            matchesGeneralDiagram = false;
                    }
                }
                else {
                    if (matchesDiagramType) {
                        if (currentPrio > bestPrio) {
                            bestProvider = providerData;
                            bestPrio = currentPrio;
                        }
                    }
                    else {
                        if (currentPrio > LayoutProviderData.MIN_PRIORITY) {
                            bestProvider = providerData;
                            bestPrio = currentPrio;
                            matchesDiagramType = true;
                            matchesGeneralDiagram = false;
                        }
                        else {
                            currentPrio = providerData.getSupportedPriority(DIAGRAM_TYPE_GENERAL);
                            if (matchesGeneralDiagram) {
                                if (currentPrio > bestPrio) {
                                    bestProvider = providerData;
                                    bestPrio = currentPrio;
                                }
                            }
                            else {
                                if (currentPrio > LayoutProviderData.MIN_PRIORITY) {
                                    bestProvider = providerData;
                                    bestPrio = currentPrio;
                                    matchesGeneralDiagram = true;
                                }
                                else if (bestProvider == null)
                                    bestProvider = providerData;
                            }
                        }
                    }
                }
            }
        }
        return bestProvider;
	}
	
}
