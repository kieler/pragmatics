package de.cau.cs.kieler.kiml.config.text

import com.google.common.collect.Lists
import java.util.List
import org.eclipse.elk.core.LayoutConfigurator
import org.eclipse.elk.core.data.LayoutMetaDataService
import org.eclipse.elk.core.data.LayoutOptionData
import org.eclipse.elk.core.klayoutdata.KIdentifier
import org.eclipse.elk.graph.KGraphElement
import org.eclipse.elk.graph.KNode
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.elk.graph.KEdge
import org.eclipse.elk.graph.KPort
import org.eclipse.elk.graph.KLabel

/**
 * Utility class for transformaing textually specified layout configurations
 * to the internal layout properties using KIML's {@link LayoutMetaDataService}.
 * 
 * @author uru
 */
final class LayoutConfigTransformer {

	/**
	 * 
	 * @param resource
	 * 		a resource containing the textually specified layout configuration
	 * @param additionalOptions
	 * 		an optional list of layout options that should be added to the 
	 * 		internal configuration
	 * @return a list of {@link VolatileLayoutConfig}s that represent 
	 * 		   the configurations specified in the passed resource. 
	 */
    public static def List<LayoutConfigurator> from(Resource resource, Pair<String, Number>... additionalOptions) {

        val List<LayoutConfigurator> volatileConfigs = Lists.newLinkedList
        val dataService = LayoutMetaDataService.getInstance()

        // these are no actual KNodes, we just use them as containers
        // for the layout options that are specified in the textual config 
        resource.contents.filter(typeof(KNode)).forEach [ root |
            
            // NOTE: by convention we ignore any configuration which's id starts with a _
            // iterate through all configs
            root.data.filter(typeof(KIdentifier)).filter[println(it.id); !it.id.startsWith("_")].forEach [ cfg |
                val currentConfig = cfg.transformConfig
                
                volatileConfigs += currentConfig
                // add options that are added by additional elements, e.g. scales
                additionalOptions.forEach [ opt | 
                  val optData = dataService.getOptionDataBySuffix(opt.key) as LayoutOptionData
                    
                    // if valid, parse its value
                    if (optData != null) {
                        val value = optData.parseValue(opt.value.toString)
                        if (value != null) {
                            currentConfig.configure(KGraphElement).setProperty(optData, value)
                        }
                    }
                ]
            ]
        ]

        return volatileConfigs
    }

    def static transformConfig(KIdentifier cfg) {

        val currentConfig = new LayoutConfigurator

        // add all textually specified layout options
        cfg.persistentEntries.forEach [ entry |
            val optData = LayoutMetaDataService.getInstance().getOptionDataBySuffix(entry.key) as LayoutOptionData
            // if valid, parse its value
            if (optData != null) {
                val value = optData.parseValue(entry.value)
                if (value != null) {
                    
                    if (optData.targets.contains(LayoutOptionData.Target.NODES) 
                        || optData.targets.contains(LayoutOptionData.Target.PARENTS))
                        currentConfig.configure(KNode).setProperty(optData, value)
                    
                    if (optData.targets.contains(LayoutOptionData.Target.EDGES))
                        currentConfig.configure(KEdge).setProperty(optData, value)
                        
                    if (optData.targets.contains(LayoutOptionData.Target.PORTS))
                        currentConfig.configure(KPort).setProperty(optData, value)
                        
                    if (optData.targets.contains(LayoutOptionData.Target.LABELS))
                        currentConfig.configure(KLabel).setProperty(optData, value)    
                        
                }
            }
        ]
        
        return currentConfig
    }
}
