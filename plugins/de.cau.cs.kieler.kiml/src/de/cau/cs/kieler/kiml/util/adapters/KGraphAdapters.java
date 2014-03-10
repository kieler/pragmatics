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

import java.util.Collection;
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
import de.cau.cs.kieler.kiml.options.LabelSide;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.kiml.util.adapters.GraphAdapters.EdgeAdapter;
import de.cau.cs.kieler.kiml.util.adapters.GraphAdapters.GraphAdapter;
import de.cau.cs.kieler.kiml.util.adapters.GraphAdapters.GraphElementAdapter;
import de.cau.cs.kieler.kiml.util.adapters.GraphAdapters.LabelAdapter;
import de.cau.cs.kieler.kiml.util.adapters.GraphAdapters.NodeAdapter;
import de.cau.cs.kieler.kiml.util.adapters.GraphAdapters.PortAdapter;
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
     * Implements basic adpater functionality for {@link KGraphElement}s.
     */
    public abstract static class AbstractKGraphElementAdapter<T extends KGraphElement> implements
            GraphElementAdapter<T> {

        // let the elements be accessed by extending classes
        // CHECKSTYLEOFF VisibilityModifier
        /** The wrapped element. */
        protected T element;
        /** The layout data of the wrapped element. */
        protected KShapeLayout layout;
        // CHECKSTYLEON VisibilityModifier
        
        private static final IProperty<Float> OFFSET_PROXY = new Property<Float>(
                LayoutOptions.OFFSET, 0.0f);

        /**
         * @param element
         *            the element that is wrapped in this adapter.
         */
        public AbstractKGraphElementAdapter(final T element) {
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
    }

    /**
     * .
     */
    public static class KGraphAdapter extends AbstractKGraphElementAdapter<KNode> implements
            GraphAdapter<KNode> {
        /**
         * @param node
         *            .
         */
        public KGraphAdapter(final KNode node) {
            super(node);
        }

        /**
         * {@inheritDoc}
         */
        public List<NodeAdapter<?>> getNodes() {
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
    public static class KNodeAdapter extends AbstractKGraphElementAdapter<KNode> implements
            NodeAdapter<KNode> {

        /**
         * @param node
         *            .
         */
        public KNodeAdapter(final KNode node) {
            super(node);
        }

        /**
         * {@inheritDoc}
         */
        public List<LabelAdapter<?>> getLabels() {
            List<LabelAdapter<?>> labelAdapters = Lists.newLinkedList();
            for (KLabel l : element.getLabels()) {
                labelAdapters.add(new KLabelAdapter(l));
            }
            return labelAdapters;
        }

        /**
         * {@inheritDoc}
         */
        public List<PortAdapter<?>> getPorts() {
            List<PortAdapter<?>> portAdapters = Lists.newLinkedList();
            for (KPort p : element.getPorts()) {
                portAdapters.add(new KPortAdapter(p));
            }
            return portAdapters;
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
    private static class KLabelAdapter extends AbstractKGraphElementAdapter<KLabel> implements
            LabelAdapter<KLabel> {

        /**
         * 
         */
        public KLabelAdapter(final KLabel label) {
            super(label);
        }

        /**
         * {@inheritDoc}
         */
        public LabelSide getSide() {
            return layout.getProperty(LayoutOptions.LABEL_SIDE);
        }
    }

    /**
     * .
     */
    private static class KPortAdapter extends AbstractKGraphElementAdapter<KPort> implements
            PortAdapter<KPort> {

        /**
         * 
         */
        public KPortAdapter(final KPort port) {
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
        public List<LabelAdapter<?>> getLabels() {
            List<LabelAdapter<?>> labelAdapters = Lists.newLinkedList();
            for (KLabel l : element.getLabels()) {
                labelAdapters.add(new KLabelAdapter(l));
            }
            return labelAdapters;
        }

        /**
         * {@inheritDoc}
         */
        public Collection<EdgeAdapter<?>> getIncomingEdges() {
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
        public Collection<EdgeAdapter<?>> getOutgoingEdges() {
            List<EdgeAdapter<?>> edgeAdapters = Lists.newLinkedList();
            for (KEdge e : element.getEdges()) {
                if (e.getSource().equals(element)) {
                    edgeAdapters.add(new KEdgeAdapter(e));
                }
            }
            return edgeAdapters;
        }
    }

    /**
     * .
     */
    private static class KEdgeAdapter implements EdgeAdapter<KEdge> {

        private KEdge e;

        /**
         * .
         */
        public KEdgeAdapter(final KEdge e) {
            this.e = e;
        }

        /**
         * {@inheritDoc}
         */
        public Collection<LabelAdapter<?>> getLabels() {
            List<LabelAdapter<?>> labelAdapters = Lists.newLinkedList();
            for (KLabel l : e.getLabels()) {
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
