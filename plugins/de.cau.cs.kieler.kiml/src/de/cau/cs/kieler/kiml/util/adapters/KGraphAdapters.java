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

import java.util.List;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.kiml.klayoutdata.KInsets;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutDataFactory;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LabelSide;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.kiml.util.adapters.GraphAdapters.GraphAdapter;
import de.cau.cs.kieler.kiml.util.adapters.GraphAdapters.GraphElementAdapter;
import de.cau.cs.kieler.kiml.util.adapters.GraphAdapters.LabelAdapter;
import de.cau.cs.kieler.kiml.util.adapters.GraphAdapters.NodeAdapter;
import de.cau.cs.kieler.kiml.util.adapters.GraphAdapters.PortAdapter;
import de.cau.cs.kieler.kiml.util.algs.Spacing.Insets;
import de.cau.cs.kieler.kiml.util.algs.Spacing.Margins;

/**
 * Contains implementations of the {@link GraphAdapters} interfaces for the KGraph.
 * 
 * @author uru
 */
public class KGraphAdapters {

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

        /**
         * {@inheritDoc}
         */
        public Insets getInsets() {
            KInsets kinsets = layout.getInsets();
            Insets insets =
                    new Insets(kinsets.getLeft(), kinsets.getTop(), kinsets.getRight(),
                            kinsets.getBottom());
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
            // FIXME big problem :) there's no margin in the KGraph
            KInsets insets = layout.getInsets();
            if (insets == null) {
                layout.setInsets(KLayoutDataFactory.eINSTANCE.createKInsets());
            }
            return new Margins(insets.getLeft(), insets.getTop(), insets.getRight(),
                    insets.getBottom());
        }

        /**
         * {@inheritDoc}
         */
        public void setMargin(final Margins margin) {
            // FIXME big problem :) there's no margin in the KGraph
            KInsets insets = layout.getInsets();
            insets.setLeft((float) margin.left);
            insets.setTop((float) margin.top);
            insets.setRight((float) margin.right);
            insets.setBottom((float) margin.bottom);
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
    }

    /**
     * .
     */
    private static class KLabelAdapter extends AbstractKGraphElementAdapter<KLabel> implements
            LabelAdapter<KLabel> {

        // FIXME move this to LayoutOptions as soon as the git issue is resolved.
        /**
         * On which side of its corresponding edge a label is situated. [programmatically set]
         */
        public static final IProperty<LabelSide> LABEL_SIDE = new Property<LabelSide>(
                "de.cau.cs.kieler.labelSide", LabelSide.UNKNOWN);
        
        
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
            return layout.getProperty(LABEL_SIDE);
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

    }
}
