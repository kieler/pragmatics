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
package de.cau.cs.kieler.kiml.util;

import java.awt.geom.Rectangle2D;
import java.util.List;

import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.kiml.options.PortSide;

/**
 * @author uru
 */
public interface GraphAdapterFactory<G> {

    GraphAdapter<G> getGraphAdapter(G graph);

    /**
     * .
     */
    public interface GraphElementAdapter<T> {

        KVector getSize();

        void setSize(final KVector size);

        KVector getPosition();

        void setPosition(final KVector pos);

        <P> P getProperty(final IProperty<P> prop);
    }

    /**
     * .
     */
    public interface GraphAdapter<T> extends GraphElementAdapter<T> {

        List<NodeAdapter<?>> getNodes();

    }

    /**
     * .
     */
    public interface NodeAdapter<T> extends GraphElementAdapter<T> {

        List<LabelAdapter<?>> getLabels();

        List<PortAdapter<?>> getPorts();

        void setInsets(final Rectangle2D.Double insets);
    }

    /**
     * .
     */
    public interface PortAdapter<T> extends GraphElementAdapter<T> {

        PortSide getSide();

        Rectangle2D.Double getMargin();

        void setMargin(final Rectangle2D.Double margin);

        List<LabelAdapter<?>> getLabels();
    }

    /**
     * .
     */
    public interface LabelAdapter<T> extends GraphElementAdapter<T> {

    }

}
