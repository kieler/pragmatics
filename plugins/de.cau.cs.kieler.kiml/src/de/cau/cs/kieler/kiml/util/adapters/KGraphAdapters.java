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
package de.cau.cs.kieler.kiml.util.adapters;


import java.util.Comparator;
import java.util.List;

import org.eclipse.emf.common.util.ECollections;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.kiml.klayoutdata.KInsets;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutData;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.kiml.util.KimlUtil;
import de.cau.cs.kieler.kiml.util.adapters.GraphAdapters.EdgeAdapter;
import de.cau.cs.kieler.kiml.util.adapters.GraphAdapters.GraphAdapter;
import de.cau.cs.kieler.kiml.util.adapters.GraphAdapters.GraphElementAdapter;
import de.cau.cs.kieler.kiml.util.adapters.GraphAdapters.LabelAdapter;
import de.cau.cs.kieler.kiml.util.adapters.GraphAdapters.NodeAdapter;
import de.cau.cs.kieler.kiml.util.adapters.GraphAdapters.PortAdapter;
import de.cau.cs.kieler.kiml.util.nodespacing.LabelSide;
import de.cau.cs.kieler.kiml.util.nodespacing.Spacing.Insets;
import de.cau.cs.kieler.kiml.util.nodespacing.Spacing.Margins;

/**
 * Contains implementations of the {@link GraphAdapters} interfaces for the KGraph.
 * 
 * @author uru
 */
public final class KGraphAdapters {

    private KGraphAdapters() {
    }

    /**
     * @param graph
     *            the graph that should be wrapped in an adapter
     * @return an {@link KGraphAdapter} for the passed graph.
     */
    public static KGraphAdapter adapt(final KNode graph) {
        return new KGraphAdapter(graph);
    }

    /**
     * @param node
     *            the node that should be wrapped in an adapter
     * @return an {@link KNodeAdapter} for the passed node.
     */
    public static KNodeAdapter adaptSingleNode(final KNode node) {
        return new KNodeAdapter(node);
    }

    /**
     * Implements basic adpater functionality for {@link KGraphElement}s.
     */
    private abstract static class AbstractKGraphElementAdapter<T extends KGraphElement> implements
            GraphElementAdapter<T> {

        // let the elements be accessed by extending classes
        // CHECKSTYLEOFF VisibilityModifier
        /** The wrapped element. */
        protected T element;
        /** The layout data of the wrapped element. */
        protected KShapeLayout layout;
        // CHECKSTYLEON VisibilityModifier
        /**
         * Internally used versatile data field. Can be used for arbitrary information.
         * No assumptions about its value or validity should be made.
         */
        private int id;
        
        private static final IProperty<Float> OFFSET_PROXY = new Property<Float>(
                LayoutOptions.OFFSET, 0.0f);

        /**
         * @param element
         *            the element that is wrapped in this adapter.
         */
        protected AbstractKGraphElementAdapter(final T element) {
            this.element = element;
            try {
                layout = element.getData(KShapeLayout.class);
            } catch (ClassCastException cce) {
                throw new RuntimeException(
                        "Graph adapters are only supported for shape-full types.");
            }
        }

        /**
         * {@inheritDoc}
         */
        @SuppressWarnings("unchecked")
        public <P> P getProperty(final IProperty<P> prop) {
            
            // the nodespacing implementation requires a default value for the offset property
            if (prop.equals(LayoutOptions.OFFSET)) {
                return (P) layout.getProperty(OFFSET_PROXY);
            }
            
            return layout.getProperty(prop);
        }

        /**
         * {@inheritDoc}
         */
        public KVector getPosition() {
            return new KVector(layout.getXpos(), layout.getYpos());
        }

        /**
         * {@inheritDoc}
         */
        public KVector getSize() {
            return new KVector(layout.getWidth(), layout.getHeight());
        }

        /**
         * {@inheritDoc}
         */
        public void setSize(final KVector size) {
            layout.setWidth((float) size.x);
            layout.setHeight((float) size.y);
        }

        /**
         * {@inheritDoc}
         */
        public void setPosition(final KVector pos) {
            layout.setXpos((float) pos.x);
            layout.setYpos((float) pos.y);
        }

        /**
         * {@inheritDoc}
         */
        public Insets getInsets() {
            KInsets kinsets = layout.getInsets();
            Insets insets =
                    new Insets(kinsets.getTop(), kinsets.getLeft(), kinsets.getBottom(),
                            kinsets.getRight());
            return insets;
        }

        /**
         * {@inheritDoc}
         */
        public void setInsets(final Insets insets) {
            layout.getInsets().setLeft((float) insets.left);
            layout.getInsets().setTop((float) insets.top);
            layout.getInsets().setRight((float) insets.right);
            layout.getInsets().setBottom((float) insets.bottom);
        }

        /**
         * {@inheritDoc}
         */
        public Margins getMargin() {
            Margins margins = layout.getProperty(LayoutOptions.MARGINS);
            if (margins == null) {
                margins = new Margins();
            }
            return margins;
        }

        /**
         * {@inheritDoc}
         */
        public void setMargin(final Margins margin) {
            // analog to the insets case, we copy the margins object here
            Margins newMargin = new Margins(margin); 
            layout.setProperty(LayoutOptions.MARGINS, newMargin);
        }

        /**
         * @return the id
         */
        public int getVolatileId() {
            return id;
        }

        /**
         * @param id the id to set
         */
        public void setVolatileId(final int volatileId) {
            this.id = volatileId;
        }
    }

    /**
     * .
     */
    public static final class KGraphAdapter extends AbstractKGraphElementAdapter<KNode> implements
            GraphAdapter<KNode> {
        /**
         * @param node
         *            .
         */
        private KGraphAdapter(final KNode node) {
            super(node);
        }

        /**
         * {@inheritDoc}
         */
        public Iterable<NodeAdapter<?>> getNodes() {
            List<NodeAdapter<?>> children = Lists.newLinkedList();
            for (KNode n : element.getChildren()) {
                children.add(new KNodeAdapter(n));
            }
            return children;
        }
    }

    /**
     * .
     */
    public static final class KNodeAdapter extends AbstractKGraphElementAdapter<KNode> implements
            NodeAdapter<KNode> {

        /**
         * @param node
         *            .
         */
        private KNodeAdapter(final KNode node) {
            super(node);
        }

        /**
         * {@inheritDoc}
         */
        public List<LabelAdapter<?>> getLabels() {
            List<LabelAdapter<?>> labelAdapters =
                    Lists.newArrayListWithExpectedSize(element.getLabels().size());
            for (KLabel l : element.getLabels()) {
                labelAdapters.add(new KLabelAdapter(l));
            }
            return labelAdapters;
        }

        /**
         * {@inheritDoc}
         */
        public List<PortAdapter<?>> getPorts() {
            List<PortAdapter<?>> portAdapters =
                    Lists.newArrayListWithExpectedSize(element.getPorts().size());
            for (KPort p : element.getPorts()) {
                portAdapters.add(new KPortAdapter(p));
            }
            return portAdapters;
        }
        
        /**
         * {@inheritDoc}
         */
        public Iterable<EdgeAdapter<?>> getIncomingEdges() {
            List<EdgeAdapter<?>> edgeAdapter =
                    Lists.newArrayListWithExpectedSize(element.getIncomingEdges().size());
            for (KEdge e : element.getIncomingEdges()) {
                edgeAdapter.add(new KEdgeAdapter(e));
            }
            return edgeAdapter;
        }
        
        /**
         * {@inheritDoc}
         */
        public Iterable<EdgeAdapter<?>> getOutgoingEdges() {
            List<EdgeAdapter<?>> edgeAdapter =
                    Lists.newArrayListWithExpectedSize(element.getOutgoingEdges().size());
            for (KEdge e : element.getOutgoingEdges()) {
                edgeAdapter.add(new KEdgeAdapter(e));
            }
            return edgeAdapter;
        }

        /**
         * {@inheritDoc}
         */
        public void sortPortList() {
            sortPortList(DEFAULT_PORTLIST_SORTER);
        }

        /**
         * {@inheritDoc}
         */
        @SuppressWarnings("unchecked")
        public void sortPortList(final Comparator<?> comparator) {
            // Iterate through the nodes of all layers
            KLayoutData layout = element.getData(KLayoutData.class);
            if (layout.getProperty(LayoutOptions.PORT_CONSTRAINTS).isOrderFixed()) {
                ECollections.sort(element.getPorts(), (Comparator<KPort>) comparator);
            }
        }

        /**
         * {@inheritDoc}
         */
        public boolean isCompoundNode() {
            return !element.getChildren().isEmpty();
        }
    }

    /**
     * .
     */
    private static final class KLabelAdapter extends AbstractKGraphElementAdapter<KLabel> implements
            LabelAdapter<KLabel> {

        /**
         * 
         */
        private KLabelAdapter(final KLabel label) {
            super(label);
        }

        /**
         * {@inheritDoc}
         */
        public LabelSide getSide() {
            return layout.getProperty(LabelSide.LABEL_SIDE);
        }
    }

    /**
     * .
     */
    private static final class KPortAdapter extends AbstractKGraphElementAdapter<KPort>
            implements PortAdapter<KPort> {

        /**
         * Creates a new adapter for the given port.
         * 
         * @param port the port to adapt.
         */
        private KPortAdapter(final KPort port) {
            super(port);
        }

        /**
         * {@inheritDoc}
         */
        public PortSide getSide() {
            return layout.getProperty(LayoutOptions.PORT_SIDE);
        }

        /**
         * {@inheritDoc}
         */
        public Iterable<LabelAdapter<?>> getLabels() {
            List<LabelAdapter<?>> labelAdapters =
                    Lists.newArrayListWithExpectedSize(element.getLabels().size());
            for (KLabel l : element.getLabels()) {
                labelAdapters.add(new KLabelAdapter(l));
            }
            return labelAdapters;
        }

        /**
         * {@inheritDoc}
         */
        public Iterable<EdgeAdapter<?>> getIncomingEdges() {
            List<EdgeAdapter<?>> edgeAdapters = Lists.newLinkedList();
            for (KEdge e : element.getEdges()) {
                if (e.getTarget().equals(element)) {
                    edgeAdapters.add(new KEdgeAdapter(e));
                }
            }
            return edgeAdapters;
        }

        /**
         * {@inheritDoc}
         */
        public Iterable<EdgeAdapter<?>> getOutgoingEdges() {
            List<EdgeAdapter<?>> edgeAdapters = Lists.newLinkedList();
            for (KEdge e : element.getEdges()) {
                if (e.getSource().equals(element)) {
                    edgeAdapters.add(new KEdgeAdapter(e));
                }
            }
            return edgeAdapters;
        }

        /**
         * {@inheritDoc}
         */
        public boolean hasCompoundConnections() {
            KNode node = element.getNode();
            
            for (KEdge edge : element.getEdges()) {
                if (edge.getSource() == node) {
                    // check if the edge's target is a descendant of its source node
                    if (KimlUtil.isDescendant(edge.getTarget(), node)) {
                        return true;
                    }
                } else {
                    // check if the edge's source is a descendant of its source node
                    if (KimlUtil.isDescendant(edge.getSource(), node)) {
                        return true;
                    }
                }
            }
            
            return false;
        }
    }

    /**
     * .
     */
    private static final class KEdgeAdapter implements EdgeAdapter<KEdge> {

        private KEdge element;

        /**
         * .
         */
        private KEdgeAdapter(final KEdge e) {
            this.element = e;
        }

        /**
         * {@inheritDoc}
         */
        public Iterable<LabelAdapter<?>> getLabels() {
            List<LabelAdapter<?>> labelAdapters =
                    Lists.newArrayListWithExpectedSize(element.getLabels().size());
            for (KLabel l : element.getLabels()) {
                labelAdapters.add(new KLabelAdapter(l));
            }
            return labelAdapters;
        }

    }
    
    /**
     * The default comparator for ports. Ports are sorted by side (north, east, south, west) in
     * clockwise order, beginning at the top left corner.
     */
    public static final PortComparator DEFAULT_PORTLIST_SORTER = new PortComparator();
    
    /**
     * A comparer for ports. Ports are sorted by side (north, east, south, west) in clockwise order,
     * beginning at the top left corner.
     */
    public static class PortComparator implements Comparator<KPort> {

        /**
         * {@inheritDoc}
         */
        public int compare(final KPort port1, final KPort port2) {
            KShapeLayout layout1 = port1.getData(KShapeLayout.class);
            KShapeLayout layout2 = port2.getData(KShapeLayout.class);
            int ordinalDifference =
                    layout1.getProperty(LayoutOptions.PORT_SIDE).ordinal()
                            - layout2.getProperty(LayoutOptions.PORT_SIDE).ordinal();

            // Sort by side first
            if (ordinalDifference != 0) {
                return ordinalDifference;
            }

            // In case of equal sides, sort by port index property
            Integer index1 = layout1.getProperty(LayoutOptions.PORT_INDEX);
            Integer index2 = layout2.getProperty(LayoutOptions.PORT_INDEX);
            if (index1 != null && index2 != null) {
                int indexDifference = index1 - index2;
                if (indexDifference != 0) {
                    return indexDifference;
                }
            }

            // In case of equal index, sort by position
            switch (layout1.getProperty(LayoutOptions.PORT_SIDE)) {
            case NORTH:
                // Compare x coordinates
                return Double.compare(layout1.getXpos(), layout2.getXpos());

            case EAST:
                // Compare y coordinates
                return Double.compare(layout1.getYpos(), layout2.getYpos());

            case SOUTH:
                // Compare x coordinates in reversed order
                return Double.compare(layout2.getXpos(), layout1.getXpos());

            case WEST:
                // Compare y coordinates in reversed order
                return Double.compare(layout2.getYpos(), layout1.getYpos());

            default:
                // Port sides should not be undefined
                throw new IllegalStateException("Port side is undefined");
            }
        }
    }
}
