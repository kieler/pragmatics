/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.ptolemy.klighd.transformation.util

import de.cau.cs.kieler.klighd.IStyleModifier
import de.cau.cs.kieler.klighd.IStyleModifier.StyleModificationContext
import de.cau.cs.kieler.core.krendering.KRotation
import de.cau.cs.kieler.core.krendering.KStyle
import de.cau.cs.kieler.core.kgraph.KPort
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout
import de.cau.cs.kieler.kiml.options.LayoutOptions
import de.cau.cs.kieler.kiml.options.PortSide
import com.google.inject.Guice
import de.cau.cs.kieler.ptolemy.klighd.transformation.extensions.MarkerExtensions

/**
 * @author uru
 */
class PtolemyPortStyleModifier implements IStyleModifier {
    
    val injector = Guice.createInjector();
    extension MarkerExtensions = injector.getInstance(typeof(MarkerExtensions))
    
    override modify(StyleModificationContext context) {
        
        if (context.style instanceof KRotation) {
           
           val style = (context.style as KRotation)
           val port = context.style.getPort()
           val portLayout = port.getData(typeof(KShapeLayout))
           
           //println(style.rotationAnchor.x)
           
           val isInput = port.markedAsInputPort
           
           switch portLayout.getProperty(LayoutOptions.PORT_SIDE) -> isInput {
               case PortSide.NORTH -> true: {
                 style.rotation = 90f  
               }
               case PortSide.NORTH -> false: {
                 style.rotation = 270f 
               } 
               case PortSide.EAST -> true: {
                 style.rotation = 180f 
               }
               case PortSide.EAST -> false: {
                 style.rotation = 90f
               } 
               case PortSide.SOUTH -> true: {
                 style.rotation = 270f
               }
               case PortSide.SOUTH -> false: {
                 style.rotation = 90f
               } 
               case PortSide.WEST -> true: {
                 style.rotation = 0f
               }
               case PortSide.WEST -> false: {
                 style.rotation = 180f  
               } 
           }
        
           // by default the rotation was zero           
           return style.rotation != 0f
        }
        
        // did not change anything
        return false
    }
    
    private def getPort(KStyle s) {
        var parent = s.eContainer
        var KPort port = null
        while (parent != null && port == null) {
            if (parent instanceof KPort) {
                port = parent as KPort
            }
            parent = parent.eContainer
        }
        return port
    }
    
    
}