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
package de.cau.cs.kieler.klay.layered.intermediate;

import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.util.List;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.kiml.util.GraphAdapterFactory;
import de.cau.cs.kieler.kiml.util.GraphAdapterFactory.GraphAdapter;
import de.cau.cs.kieler.kiml.util.GraphAdapterFactory.GraphElementAdapter;
import de.cau.cs.kieler.kiml.util.GraphAdapterFactory.LabelAdapter;
import de.cau.cs.kieler.kiml.util.GraphAdapterFactory.NodeAdapter;
import de.cau.cs.kieler.kiml.util.GraphAdapterFactory.PortAdapter;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LInsets;
import de.cau.cs.kieler.klay.layered.graph.LLabel;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.LShape;
import de.cau.cs.kieler.klay.layered.graph.Layer;

/**
 * @author uru
 */
public class LGraphAdapterFactory implements GraphAdapterFactory<LGraph> {

    /**
     * {@inheritDoc}
     */
    public GraphAdapter<LGraph> getGraphAdapter(final LGraph graph) {
        return new LGraphAdapter(graph);
    }

    class AbstractLGraphAdapter<T extends LShape> implements GraphElementAdapter<T> {

        protected T element;

        /**
         * .
         */
        public AbstractLGraphAdapter(final T element) {
            this.element = element;
        }

        /**
         * {@inheritDoc}
         */
        public KVector getSize() {
            return element.getSize();
        }

        /**
         * {@inheritDoc}
         */
        public void setSize(final KVector size) {
            element.getSize().x = size.x;
            element.getSize().y = size.y;
        }

        /**
         * {@inheritDoc}
         */
        public KVector getPosition() {
            return element.getPosition();
        }

        /**
         * {@inheritDoc}
         */
        public void setPosition(final KVector pos) {
            element.getPosition().x = pos.x;
            element.getPosition().y = pos.y;

            System.out.println("Position of " + element + " " + pos + " " + element.getPosition());
        }

        /**
         * {@inheritDoc}
         */
        public <P> P getProperty(final IProperty<P> prop) {
            return element.getProperty(prop);
        }

    }

    class LGraphAdapter implements GraphAdapter<LGraph> {

        protected LGraph element;

        /**
      * 
      */
        public LGraphAdapter(final LGraph element) {
            this.element = element;
        }

        /**
         * {@inheritDoc}
         */
        public KVector getSize() {
            return element.getSize();
        }

        /**
         * {@inheritDoc}
         */
        public void setSize(final KVector size) {
            element.getSize().x = size.x;
            element.getSize().y = size.y;
        }

        /**
         * {@inheritDoc}
         */
        public KVector getPosition() {
            throw new UnsupportedOperationException("Not supported by LGraph");
        }

        /**
         * {@inheritDoc}
         */
        public void setPosition(final KVector pos) {
            throw new UnsupportedOperationException("Not supported by LGraph");
        }

        /**
         * {@inheritDoc}
         */
        public <P> P getProperty(final IProperty<P> prop) {
            return element.getProperty(prop);
        }

        /**
         * {@inheritDoc}
         */
        public List<NodeAdapter<?>> getNodes() {
            List<NodeAdapter<?>> nodeAdapter = Lists.newLinkedList();
            for (Layer l : element.getLayers()) {
                for (LNode n : l.getNodes()) {
                    nodeAdapter.add(new LNodeAdapter(n));
                }
            }
            return nodeAdapter;
        }

    }

    class LNodeAdapter extends AbstractLGraphAdapter<LNode> implements NodeAdapter<LNode> {

        /**
         * @param element
         */
        public LNodeAdapter(final LNode element) {
            super(element);
        }

        /**
         * {@inheritDoc}
         */
        public List<LabelAdapter<?>> getLabels() {
            List<LabelAdapter<?>> labelAdapters = Lists.newLinkedList();
            for (LLabel l : element.getLabels()) {
                labelAdapters.add(new LLabelAdapter(l));
            }
            return labelAdapters;
        }

        /**
         * {@inheritDoc}
         */
        public List<PortAdapter<?>> getPorts() {
            List<PortAdapter<?>> portAdapters = Lists.newLinkedList();
            for (LPort p : element.getPorts()) {
                portAdapters.add(new LPortAdapter(p));
            }
            return portAdapters;
        }

        /**
         * {@inheritDoc}
         */
        public void setInsets(Rectangle2D.Double insets) {
            element.getInsets().left = insets.x;
            element.getMargin().top = insets.y;
            element.getMargin().right = insets.width;
            element.getMargin().bottom = insets.height;

        }

    }

    class LPortAdapter extends AbstractLGraphAdapter<LPort> implements PortAdapter<LPort> {

        /**
         * @param element
         */
        public LPortAdapter(final LPort element) {
            super(element);
        }

        /**
         * {@inheritDoc}
         */
        public PortSide getSide() {
            return element.getSide();
        }

        /**
         * {@inheritDoc}
         */
        public Double getMargin() {
            LInsets margin = element.getMargin();
            return new Rectangle2D.Double(margin.left, margin.top, margin.right, margin.bottom);
        }

        /**
         * {@inheritDoc}
         */
        public void setMargin(final Rectangle2D.Double margin) {
            element.getMargin().left = margin.x;
            element.getMargin().top = margin.y;
            element.getMargin().right = margin.width;
            element.getMargin().bottom = margin.height;
        }

        /**
         * {@inheritDoc}
         */
        public List<LabelAdapter<?>> getLabels() {
            List<LabelAdapter<?>> labelAdapters = Lists.newLinkedList();
            for (LLabel l : element.getLabels()) {
                labelAdapters.add(new LLabelAdapter(l));
            }
            return labelAdapters;
        }

    }

    class LLabelAdapter extends AbstractLGraphAdapter<LLabel> implements LabelAdapter<LLabel> {

        /**
         * @param element
         */
        public LLabelAdapter(LLabel element) {
            super(element);
        }

    }

}
