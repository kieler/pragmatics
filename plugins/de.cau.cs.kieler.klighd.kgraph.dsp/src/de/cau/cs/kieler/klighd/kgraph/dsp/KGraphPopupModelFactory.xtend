/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2018 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.kgraph.dsp

import io.typefox.sprotty.api.IDiagramServer
import io.typefox.sprotty.api.IPopupModelFactory
import io.typefox.sprotty.api.RequestPopupModelAction
import io.typefox.sprotty.api.SModelElement

/**
 * 
 * Based on the yang-lsp implementation by TypeFox.
 * 
 * @author nir
 * @see <a href="https://github.com/theia-ide/yang-lsp/blob/master/yang-lsp/io.typefox.yang.diagram/src/main/java/io/typefox/yang/diagram/YangPopupModelFactory.xtend">
 *      YangPopupModelFactory</a>
 */
class KGraphPopupModelFactory implements IPopupModelFactory {
	override createPopupModel(SModelElement element, RequestPopupModelAction request, IDiagramServer server) {
	    // TODO: program similar to yang-lsp YangPopupModelFactory if needed
    }
}
