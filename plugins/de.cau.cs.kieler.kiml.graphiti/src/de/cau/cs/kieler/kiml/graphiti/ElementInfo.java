/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.graphiti;

import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.BoxRelativeAnchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.util.Pair;

/**
 * Class that holds information about one element. It is used to store
 * information before the kgraph is passed to the layouter and later retrieves
 * the information to make sure that all modifications are properly reverted.
 * 
 * @author soh
 */
public class ElementInfo {

    /**
     * Holds information about a port.
     * 
     * @author soh
     */
    public static class PortInfo extends ElementInfo {

        /** Offset of the port. */
        private Pair<Float, Float> offset;

        /** True if the ports parent has an invisible parent. */
        private boolean containerHasInvisibleParent = false;

        /**
         * 
         * Creates a new PortInfo.
         * 
         * @param graphElem
         *            the graph element
         * @param picElem
         *            the pictogram element
         */
        public PortInfo(final KGraphElement graphElem,
                final PictogramElement picElem) {
            super(graphElem, picElem);
        }

        /**
         * Set the offset.
         * 
         * @param offsetParam
         *            the offset
         */
        public void setOffset(final Pair<Float, Float> offsetParam) {
            this.offset = offsetParam;
        }

        /**
         * Get the offset.
         * 
         * @return the offset
         */
        public Pair<Float, Float> getOffset() {
            return offset;
        }

        /**
         * Set the containerHasInvisibleParent attribute.
         * 
         * @param b
         *            ...
         */
        public void setContainerHasInvisibleParent(final boolean b) {
            containerHasInvisibleParent = b;

        }

        /**
         * Get the containerHasInvisibleParent attribute.
         * 
         * @return ...
         */
        public boolean containerHasInvisibleParent() {
            return containerHasInvisibleParent;
        }

        @Override
        public BoxRelativeAnchor getPicElem() {
            return (BoxRelativeAnchor) super.getPicElem();
        }

        @Override
        public KPort getGraphElem() {
            return (KPort) super.getGraphElem();
        }
    }

    /**
     * Holds information about a connection.
     * 
     * @author soh
     */
    public static class ConnectionInfo extends ElementInfo {

        /** Source anchor. */
        private Anchor src;

        /** Target anchor. */
        private Anchor target;

        /**
         * 
         * 
         * Creates a new ConnectionInfo.
         * 
         * @param graphElem
         *            the graph element
         * @param picElem
         *            the pictogram element
         */
        public ConnectionInfo(final KGraphElement graphElem,
                final PictogramElement picElem) {
            super(graphElem, picElem);
        }

        /**
         * 
         * @param srcParam
         *            the source anchor
         */
        public void setSrc(final Anchor srcParam) {
            this.src = srcParam;
        }

        /**
         * 
         * @return the source anchor
         */
        public Anchor getSrc() {
            return src;
        }

        /**
         * 
         * @param targetParam
         *            target anchor
         */
        public void setTarget(final Anchor targetParam) {
            this.target = targetParam;
        }

        /**
         * 
         * @return target anchor
         */
        public Anchor getTarget() {
            return target;
        }

        @Override
        public Connection getPicElem() {
            return (Connection) super.getPicElem();
        }

        @Override
        public KEdge getGraphElem() {
            return (KEdge) super.getGraphElem();
        }

    }

    /** Graph element. */
    private KGraphElement graphElem;

    /** Pictogram element. */
    private PictogramElement picElem;

    /**
     * Creates a new ElementInfo.
     * 
     * @param graphElemParam
     *            graph element
     * @param picElemParam
     *            pictogram element
     */
    public ElementInfo(final KGraphElement graphElemParam,
            final PictogramElement picElemParam) {
        super();
        this.graphElem = graphElemParam;
        this.picElem = picElemParam;
    }

    /**
     * 
     * @param picElemParam
     *            pictogram element
     */
    public void setPicElem(final PictogramElement picElemParam) {
        this.picElem = picElemParam;
    }

    /**
     * 
     * @return pictogram element
     */
    public PictogramElement getPicElem() {
        return picElem;
    }

    /**
     * 
     * @param graphElemParam
     *            graph element
     */
    public void setGraphElem(final KGraphElement graphElemParam) {
        this.graphElem = graphElemParam;
    }

    /**
     * 
     * @return graph element
     */
    public KGraphElement getGraphElem() {
        return graphElem;
    }
}
