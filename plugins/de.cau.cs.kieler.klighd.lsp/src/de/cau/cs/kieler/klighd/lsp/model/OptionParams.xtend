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
package de.cau.cs.kieler.klighd.lsp.model

import de.cau.cs.kieler.klighd.SynthesisOption
import java.util.List
import org.eclipse.xtend.lib.annotations.Data

/**
 * Data class to hold the parameters of the keith/diagramOptions/getOptions message.
 * 
 * @author nir
 */
@Data
class GetOptionParam {
    /**
     * The {@code uri} pointing towards the resource of which the {@link SynthesisOption}s should be sent back.
     */
    String uri
}

/**
 * Data class to hold the parameters of the keith/diagramOptions/sendOptions message.
 * 
 * @author nir
 */
@Data
class SetOptionParam {
    /**
     * The {@code uri} pointing towards the resource that should update its options.
     */
    String uri
    
    /**
     * A list of the new {@link SynthesisOptionValue}s that should be set.
     */
    List<SynthesisOptionValue> synthesisOptions
}

/**
 * Data class to hold the identifying {@code sourceHash} for a {@link SynthesisOption} and a {@currentValue} of that option.
 * 
 * @author nir
 */
@Data
class SynthesisOptionValue {
    /**
     * Value that the {@link SynthesisOption} should currently has.
     */
    Object currentValue
    
    /**
     * The identifying hash value of the {@SynthesisOption} this value belongs to.
     */
    int sourceHash
}

/**
 * Data class to hold a synthesisOption and its current value in one convenient object.
 */
@Data
class ValuedSynthesisOption {
    /**
     * The synthesisOption.
     */
     
    SynthesisOption synthesisOption
    /**
     * The current value of the synthesisOption.
     */
    Object currentValue
}