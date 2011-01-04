/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kaom.diagram.custom;

import de.cau.cs.kieler.core.annotations.ui.properties.AnnotationsPropertySection;
import de.cau.cs.kieler.core.model.GmfDomainProvider;

/**
 * A property section implementation to display annotations of KAOM diagrams.
 *
 * @author msp
 */
public class KaomAnnotationsPropertySection extends AnnotationsPropertySection {

    /**
     * Creates a property section for KAOM annotations.
     */
    public KaomAnnotationsPropertySection() {
        super(new GmfDomainProvider());
    }

}
