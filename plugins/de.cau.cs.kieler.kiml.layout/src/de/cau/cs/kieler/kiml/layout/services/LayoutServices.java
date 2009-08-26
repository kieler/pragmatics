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

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KLayoutData;
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
    public static final String DIAGRAM_TYPE_GENERAL = "de.cau.cs.kieler.layout.diagrams.general";
    /** identifier of the 'nolayout' diagram type, for which no automatic layout must be applied */
    public static final String DIAGRAM_TYPE_NOLAYOUT = "de.cau.cs.kieler.layout.diagrams.nolayout";
    
	/** the singleton instance of the layout service */
	public static LayoutServices INSTANCE = null;
	/** the singleton instance of the registry class */
	public static Registry REGISTRY = null;
	
	/** the list of layout listeners that have been loaded at startup */
	private List<ILayoutListener> listeners = new LinkedList<ILayoutListener>();
	/** mapping of layout provider identifiers to their data instances */
	private Map<String, LayoutProviderData> layoutProviderMap
			= new LinkedHashMap<String, LayoutProviderData>();
	/** mapping of layout option identifiers to their data instances */
	private Map<String, LayoutOptionData> layoutOptionMap
			= new LinkedHashMap<String, LayoutOptionData>();
	/** mapping of layout type identifiers to their names */
	private Map<String, String> layoutTypeMap = new HashMap<String, String>();
	/** mapping of category identifiers to their names */
	private Map<String, String> categoryMap = new HashMap<String, String>();
    /** mapping of diagram type identifiers to their names */
    private Map<String, String> diagramTypeMap = new LinkedHashMap<String, String>();
	/** mapping of graphical edit parts to associated binding identifiers */
	private Map<Class<?>, String> editPartBindingMap
	        = new LinkedHashMap<Class<?>, String>();
	/** mapping of binding identifiers to associated configured options */
	private Map<String, Map<String, Object>> optionSetupMap
	        = new HashMap<String, Map<String, Object>>();
	

	/**
	 * Creates an instance of the layout services and assigns the singleton
	 * instance of the registry.
	 */
	public LayoutServices() {
		REGISTRY = new Registry();
	}
	
	/** Class used to register the layout services */
	public class Registry {
		
		/** list of unprocessed layout options */
		private List<String[]> unprocessedOptions = new LinkedList<String[]>();

		/**
		 * Initializes the layout services <em>after</em> all contributions have been
		 * registered from the extension points.
		 */
		public void initialize() {
			// process all layout option configurations
			for (String[] option : unprocessedOptions) {
                String optionId = option[1];
				LayoutOptionData optionData = layoutOptionMap.get(optionId);
				if (optionData != null) {
	                String valueString = option[2]; 
				    Object valueObj = optionData.parseValue(valueString);
				    if (valueObj != null) {
		                String bindingId = option[0];
		                Map<String, Object> optionsMap = optionSetupMap.get(bindingId);
		                if (optionsMap != null)
		                    optionsMap.put(optionId, valueObj);
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
		 * Registers the given layout provider. If there is already a
		 * registered provider data instance with the same identifier, it
		 * is overwritten.
		 * 
		 * @param providerData data instance of the layout provider to register
		 */
		public void addLayoutProvider(LayoutProviderData providerData) {
	        layoutProviderMap.put(providerData.id, providerData);
		}
		
		/**
		 * Registers the given layout option. If there is already a
		 * registered option data instance with the same identifier, it
		 * is overwritten.
		 * 
		 * @param optionData data instance of the layout option to register
		 */
		public void addLayoutOption(LayoutOptionData optionData) {
	        layoutOptionMap.put(optionData.id, optionData);
		}
		
		/**
		 * Registers the given layout type.
		 * 
		 * @param id identifier of the type
		 * @param name user friendly name of the type
		 */
		public void addLayoutType(String id, String name) {
	        layoutTypeMap.put(id, name);
		}
		
		/**
		 * Registers the given edit part with the binding identifier.
		 * 
		 * @param editPartType class of edit parts to register
		 * @param bindingId identifier of the category of edit parts
		 */
		public void addEditPartBinding(Class<?> editPartType, String bindingId) {
	        editPartBindingMap.put(editPartType, bindingId);
	        if (!optionSetupMap.containsKey(bindingId))
	            optionSetupMap.put(bindingId, new LinkedHashMap<String, Object>());
		}
		
		/**
		 * Registers the given category.
		 * 
		 * @param id identifier of the category
		 * @param name user friendly name of the category
		 */
		public void addCategory(String id, String name) {
	        categoryMap.put(id, name);
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
		    if (!DIAGRAM_TYPE_NOLAYOUT.equals(id))
		        diagramTypeMap.put(id, name);
		}
		
		/**
		 * Registers a layout option configuration for the given diagram setup.
		 * 
		 * @param bindingId identifier of the category of edit parts
		 * @param optionId identifier of a layout option
		 * @param value value for the layout option
		 */
		public void setupOption(String bindingId, String optionId, String value) {
			unprocessedOptions.add(new String[] {bindingId, optionId, value});
		}
		
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
	 * @throws KielerException if there is no registered layout provider
	 */
	public AbstractLayoutProvider getLayoutProvider(KNode layoutNode)
	        throws KielerException {
	    KShapeLayout nodeLayout = KimlLayoutUtil.getShapeLayout(layoutNode);
	    String layoutHint = LayoutOptions.getLayoutHint(nodeLayout);
	    // try to get a specific provider for the given node
	    LayoutProviderData providerData = layoutProviderMap.get(layoutHint);
	    if (providerData != null)
	        return providerData.instance;

	    // find the most appropriate provider from the layout type and diagram type
	    String diagramType = LayoutOptions.getDiagramType(nodeLayout);
	    providerData = findAppropriateProvider(layoutHint, diagramType);
	    if (providerData != null)
	        return providerData.instance;
	    else
	        throw new KielerException("No registered layout provider is available.");
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
	 * Returns a data collection for all registered layout options.
	 * 
	 * @return collection of registered layout options
	 */
	public Collection<LayoutOptionData> getLayoutOptionData() {
	    return layoutOptionMap.values();
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
	 * Returns the name of the given category.
	 * 
	 * @param id identifier of the category
	 * @return user friendly name of the category
	 */
	public String getCategoryName(String id) {
	    return categoryMap.get(id);
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
	 * Sets all preconfigured layout options for the given layout data.
	 * 
	 * @param editPartType class of edit part
	 * @param layoutData layout data for which the layout options shall be added
	 */
	public void setLayoutOptions(Class<?> editPartType, KLayoutData layoutData) {
	    String bindingId = editPartBindingMap.get(editPartType);
	    if (bindingId != null) {
	        Map<String, Object> options = optionSetupMap.get(bindingId);
	        for (String option : options.keySet()) {
	            Object value = options.get(option);
	            LayoutOptionData optionData = layoutOptionMap.get(option);
                optionData.setValue(layoutData, value);
	        }
	    }
	}

	/**
	 * Checks whether the diagram type configured for the given edit part is
	 * a "no layout" diagram.
	 * 
	 * @param editPartType class of edit part
	 * @return true if no layout must be performed for the given edit part
	 */
	public boolean isNolayout(Class<?> editPartType) {
	    String bindingId = editPartBindingMap.get(editPartType);
        if (bindingId != null) {
            Map<String, Object> options = optionSetupMap.get(bindingId);
            String diagramType = (String)options.get(LayoutOptions.DIAGRAM_TYPE);
            return DIAGRAM_TYPE_NOLAYOUT.equals(diagramType);
        }
        else return false;
	}

	/**
	 * Determines an appropriate layout provider for the given layout type and
	 * diagram type.
	 * 
	 * @param layoutType hint about the layout type to choose, or {@code null}
	 *     if no specific layout type is selected
	 * @param diagramType identifier of the diagram type that is to be layouted
	 * @return data instance for the most appropriate layout provider, or {@code null}
	 *     if there is no layout provider
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
