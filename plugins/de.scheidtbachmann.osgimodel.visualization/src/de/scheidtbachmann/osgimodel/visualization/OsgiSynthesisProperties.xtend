package de.scheidtbachmann.osgimodel.visualization

import org.eclipse.elk.graph.properties.IProperty
import org.eclipse.elk.graph.properties.Property

/**
 * Class to provide easy access to properties stored for the syntheses of OSGi models.
 * 
 * @author nre
 */
class OsgiSynthesisProperties {
    /** Property pointing towards the main element in this model that should be drawn around. */
    public static final IProperty<Object> MAIN_ELEMENT = new Property<Object>("osgimodel.mainElement")    
}