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

import java.awt.geom.Rectangle2D;
import java.util.List;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.kiml.klayoutdata.KInsets;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutDataFactory;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.kiml.util.adapters.GraphAdapters.GraphAdapter;
import de.cau.cs.kieler.kiml.util.adapters.GraphAdapters.GraphElementAdapter;
import de.cau.cs.kieler.kiml.util.adapters.GraphAdapters.LabelAdapter;
import de.cau.cs.kieler.kiml.util.adapters.GraphAdapters.NodeAdapter;
import de.cau.cs.kieler.kiml.util.adapters.GraphAdapters.PortAdapter;

/**
 * @author uru
 */
public class KGraphAdapters {

    /**
     * .
     */
    public abstract static class AbstractKGraphElementAdapter<T extends KGraphElement> implements
            GraphElementAdapter<T> {

        // CHECKSTYLEOFF
        protected T element;
        protected KShapeLayout layout;

        /**
         * @param element
         *            .
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
        public <P> P getProperty(final IProperty<P> prop) {
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
        public void setInsets(final Rectangle2D.Double insets) {
            layout.getInsets().setLeft((float) insets.x);
            layout.getInsets().setTop((float) insets.y);
            layout.getInsets().setRight((float) insets.width);
            layout.getInsets().setBottom((float) insets.height);
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
        public Rectangle2D.Double getMargin() {
            // FIXME is this the same as insets??
            KInsets insets = layout.getInsets();
            if (insets == null) {
                layout.setInsets(KLayoutDataFactory.eINSTANCE.createKInsets());
            }
            return new Rectangle2D.Double(insets.getLeft(), insets.getTop(), insets.getRight(),
                    insets.getBottom());
        }

        /**
         * {@inheritDoc}
         */
        public void setMargin(final Rectangle2D.Double margin) {
            // FIXME as above
            KInsets insets = layout.getInsets();
            insets.setLeft((float) margin.x);
            insets.setTop((float) margin.y);
            insets.setRight((float) margin.width);
            insets.setBottom((float) margin.height);
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

    }
}
