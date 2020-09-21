/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 * 
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2019,2020 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.pragmatics.language.server

import com.google.inject.Singleton
import de.cau.cs.kieler.klighd.lsp.launch.AbstractRegistrationLanguageServerExtension
import de.cau.cs.kieler.klighd.lsp.launch.Language

/**
 * Extends the language server with support for the KGraph and Elk Graph languages.
 * 
 * @author sdo, nre
 */
 @Singleton
class PragmaticsRegistrationLanguageServerExtension extends AbstractRegistrationLanguageServerExtension {
    
    override getLanguageExtensions() {
        val kgtKeywords = #['absolutePos','actions','anchor','areaData','background','bevel','bold','bottom',
        'bottomRightAnchor','center','chord','clipShape','columns','custom','dash','dashOffset','dashPattern',
        'decoratorData','dot','double','doubleClick','error','flat','flexibleHeight','flexibleWidth','fontName',
        'fontSize','foreground','grid','gridData','hAlign','height','horizontalAlignment','horizontalMargin','insets',
        'invisible','italic','junction','karc','kchildArea','kcustomRendering','kedge','kellipse','kgraph','kimage',
        'klabel','knode','kpolygon','kpolyline','kport','krectangle','krendering','krenderingLibrary','kroundedPolyline',
        'kroundedRectangle','kspline','kstylesTemplate','ktext','left','lineCap','lineJoin','lineStyle','lineWidth',
        'link','middleDoubleClick','middleSingleClick','middleSingleOrMultiClick','minCellHeight','minCellWidth',
        'minimalHeight','minimalWidth','miter','modifier','none','null','open','pie','pointData','points','pos','propagate',
        'properties','reference','referencePoint','relativePos','right','rotateWithLine','rotation','round','scale',
        'selection','shadow','single','singleClick','singleOrMultiClick','size','solid','square','squiggle','styles',
        'top','topLeftAnchor','underline','vAlign','verticalAlignment','verticalMargin','width','x','xoffset','y',
        'yoffset']
        
        val elkKeywords = #["bends","edge","end","false","graph","incoming","label","layout","node","null","outgoing",
        "port","position","section","size","start","true"]
        
        return newArrayList(new Language("kgt", "KGraph", kgtKeywords), new Language("elkt", "Elk Graph", elkKeywords))
    }
    
}