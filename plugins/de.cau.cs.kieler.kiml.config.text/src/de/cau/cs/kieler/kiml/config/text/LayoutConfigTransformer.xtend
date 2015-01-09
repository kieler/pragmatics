package de.cau.cs.kieler.kiml.config.text

import com.google.common.collect.Lists
import de.cau.cs.kieler.core.kgraph.KNode
import de.cau.cs.kieler.kiml.LayoutMetaDataService
import de.cau.cs.kieler.kiml.LayoutOptionData
import de.cau.cs.kieler.kiml.config.VolatileLayoutConfig
import java.util.List
import org.eclipse.emf.ecore.resource.Resource
import de.cau.cs.kieler.kiml.klayoutdata.KIdentifier

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
    public static def List<VolatileLayoutConfig> from(Resource resource, Pair<String, Number>... additionalOptions) {

        val List<VolatileLayoutConfig> volatileConfigs = Lists.newLinkedList
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
                            currentConfig.setValue(optData, value)
                        }
                    }
                ]
            ]
        ]

        return volatileConfigs
    }

    def static transformConfig(KIdentifier cfg) {

        val currentConfig = new VolatileLayoutConfig

        // add all textually specified layout options
        cfg.persistentEntries.forEach [ entry |
            val optData = LayoutMetaDataService.getInstance().getOptionDataBySuffix(entry.key) as LayoutOptionData
            // if valid, parse its value
            if (optData != null) {
                val value = optData.parseValue(entry.value)
                if (value != null) {
                    currentConfig.setValue(optData, value)
                }
            }
        ]
        
        return currentConfig
    }
}
