package de.scheidtbachmann.osgimodel.visualization

import java.util.ArrayList
import java.util.List
import org.eclipse.elk.graph.properties.IProperty
import org.eclipse.elk.graph.properties.Property

/**
 * Class to provide easy access to properties stored for the syntheses of OSGi models.
 * 
 * @author nre
 */
class OsgiSynthesisProperties {
    /** 
     * Property pointing towards the list of focused elements in this model. The last element of the list is always
     * the element that should be visualized as the central element during the synthesis.
     */
    public static final IProperty<List<Object>> FOCUSED_ELEMENTS = new Property<List<Object>>("osgimodel.focusedElements",
        new ArrayList<Object>)
}