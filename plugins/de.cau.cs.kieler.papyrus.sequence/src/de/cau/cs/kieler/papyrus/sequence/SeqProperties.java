/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.papyrus.sequence;

import java.util.List;

import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.papyrus.sequence.graph.SComment;
import de.cau.cs.kieler.papyrus.sequence.graph.SLifeline;
import de.cau.cs.kieler.papyrus.sequence.sorter.LifelineSortingStrategy;

public final class SeqProperties {
    public static final IProperty<MessageType> MESSAGE_TYPE = new Property<MessageType>(
            "de.cau.cs.kieler.papyrus.sequence.messageType", MessageType.ASYNCHRONOUS);

    public static final IProperty<Integer> MESSAGE_LAYER = new Property<Integer>(
            "de.cau.cs.kieler.papyrus.sequence.messageLayer");

    public static final IProperty<SLifeline> BELONGS_TO_LIFELINE = new Property<SLifeline>(
            "de.cau.cs.kieler.papyrus.sequence.belongsToLifeline");

    public static final IProperty<List<SComment>> COMMENTS = new Property<List<SComment>>(
            "de.cau.cs.kieler.papyrus.sequence.comments");

    public static final Property<Float> LIFELINE_SPACING = new Property<Float>(
            "de.cau.cs.kieler.papyrus.sequence.lifelineSpacing", 50.0f);

    public static final Property<Float> MESSAGE_SPACING = new Property<Float>(
            "de.cau.cs.kieler.papyrus.sequence.messageSpacing", 50.0f);

    public static final Property<LifelineSortingStrategy> LIFELINE_SORTING = new Property<LifelineSortingStrategy>(
            "de.cau.cs.kieler.papyrus.sequence.lifelineSorting",
            LifelineSortingStrategy.INTERACTIVE);
}
