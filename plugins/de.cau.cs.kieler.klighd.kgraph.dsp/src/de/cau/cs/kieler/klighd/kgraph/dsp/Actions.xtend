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

import io.typefox.sprotty.api.Action
import io.typefox.sprotty.api.ElementAndBounds
import io.typefox.sprotty.api.SModelRoot
import java.util.List
import java.util.function.Consumer
import org.eclipse.xtend.lib.annotations.Accessors
import org.eclipse.xtend.lib.annotations.EqualsHashCode
import org.eclipse.xtend.lib.annotations.ToString

/**
 * @author nir
 */

/**
 * Sent from the server to the client to request bounds for the given texts. The texts are rendered
 * invisibly so the bounds can derived from the DOM. The response is a {@link ComputedTextBoundsAction}.
 */
@Accessors
@EqualsHashCode
@ToString(skipNulls = true)
public class RequestTextBoundsAction implements Action {
    public static val KIND = 'requestTextBounds'
    private String kind = KIND
    
    private SModelRoot textDiagram
    
    public new() {}
    public new(Consumer<RequestTextBoundsAction> initializer) {
        initializer.accept(this)
    }
    public new(SModelRoot textDiagram) {
        this.textDiagram = textDiagram
    }
}

/**
 * Sent from the client to the server to transmit the result of text bounds computation as a response
 * to a {@link RequestTextBoundsAction}.
 */
@Accessors
@EqualsHashCode
@ToString(skipNulls = true)
public class ComputedTextBoundsAction implements Action {
    public static val KIND = 'computedTextBounds'
    private String kind = KIND
    private int revision 
    
    private List<ElementAndBounds> bounds
    
    public new() {}
    public new(Consumer<ComputedTextBoundsAction> initializer) {
        initializer.accept(this)
    }
}