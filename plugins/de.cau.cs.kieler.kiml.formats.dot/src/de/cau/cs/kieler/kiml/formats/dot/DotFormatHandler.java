/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.formats.dot;

import java.util.List;
import java.util.Map;

import org.eclipse.elk.alg.graphviz.dot.GraphvizDotStandaloneSetup;
import org.eclipse.elk.alg.graphviz.dot.dot.GraphvizModel;
import org.eclipse.elk.alg.graphviz.dot.transform.DotExporter;
import org.eclipse.elk.alg.graphviz.dot.transform.DotImporter;
import org.eclipse.elk.alg.graphviz.dot.transform.DotResourceSetProvider;
import org.eclipse.elk.alg.graphviz.dot.transform.IDotTransformationData;
import org.eclipse.elk.graph.KNode;
import org.eclipse.elk.graph.properties.IProperty;
import org.eclipse.elk.graph.properties.IPropertyHolder;
import org.eclipse.emf.ecore.resource.ResourceSet;

import de.cau.cs.kieler.kiml.formats.AbstractEmfHandler;
import de.cau.cs.kieler.kiml.formats.IGraphTransformer;
import de.cau.cs.kieler.kiml.formats.TransformationData;

/**
 * A graph format handler for Graphviz Dot.
 *
 * @author msp
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow by msp
 */
public class DotFormatHandler extends AbstractEmfHandler<GraphvizModel> {
    
    /** The graph format identifier for Graphviz Dot. */
    public static final String ID = "org.graphviz.dot";
    
    /** Provider for dot file resource sets. */
    private DotResourceSetProvider dotResourceSetProvider;
    
    
    public DotFormatHandler() {
        dotResourceSetProvider = new GraphvizDotStandaloneSetup()
            .createInjectorAndDoEMFRegistration()
            .getInstance(DotResourceSetProvider.class);
    }
    
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected String getFileExtension() {
        return "graphviz_dot";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResourceSet createResourceSet() {
        return dotResourceSetProvider.createResourceSet();
    }

    private final DotImporter IMPORTER = new DotImporter();
    
    /**
     * {@inheritDoc}
     */
    public IGraphTransformer<GraphvizModel, KNode> getImporter() {
        return new IGraphTransformer<GraphvizModel, KNode>() {

            @Override
            public void transform(TransformationData<GraphvizModel, KNode> data) {
                IMPORTER.transform(DotTransformationDataWrapper.wrap(data));
            }

            @Override
            public void transferLayout(TransformationData<GraphvizModel, KNode> data) {
                IMPORTER.transferLayout(DotTransformationDataWrapper.wrap(data));
            }
            
        };
    }

    private final DotExporter EXPORTER = new DotExporter();
    
    /**
     * {@inheritDoc}
     */
    public IGraphTransformer<KNode, GraphvizModel> getExporter() {
        return new IGraphTransformer<KNode, GraphvizModel>() {

            @Override
            public void transform(TransformationData<KNode, GraphvizModel> data) {
                EXPORTER.transform(DotTransformationDataWrapper.wrap(data));
            }

            @Override
            public void transferLayout(TransformationData<KNode, GraphvizModel> data) {
                EXPORTER.transferLayout(DotTransformationDataWrapper.wrap(data));
            }
            
        };
    }
    
    
    //////////////////////////////////////////////////////////////////////////////////////////////////
    // DotTransformationData Wrapper
    
    /**
     * Implementation of the {@link IDotTransformationData} interface that simply wraps around a given
     * {@link TransformationData} instance.
     * 
     * @param <S> source model type.
     * @param <T> target model type.
     * @author cds
     */
    private static final class DotTransformationDataWrapper<S, T>
            implements IDotTransformationData<S, T> {
        
        /** The wrapped transformation data. */
        private TransformationData<S, T> wrappedData = null;
        
        private DotTransformationDataWrapper() {
        }
        
        
        /**
         * Creates a new instance wrapping the given transformation data.
         * 
         * @param data the transformation data to wrap.
         */
        public static <V, W> DotTransformationDataWrapper<V, W> wrap(
                final TransformationData<V, W> data) {
            
            DotTransformationDataWrapper<V, W> wrapper = new DotTransformationDataWrapper<>();
            wrapper.wrappedData = data;
            return wrapper;
        }
        

        @Override
        public <V> IPropertyHolder setProperty(IProperty<? super V> property, V value) {
            wrappedData.setProperty(property, value);
            return this;
        }

        @Override
        public <V> V getProperty(IProperty<V> property) {
            return wrappedData.getProperty(property);
        }

        @Override
        public IPropertyHolder copyProperties(IPropertyHolder holder) {
            return wrappedData.copyProperties(holder);
        }

        @Override
        public Map<IProperty<?>, Object> getAllProperties() {
            return wrappedData.getAllProperties();
        }

        @Override
        public void setSourceGraph(S srcGraph) {
            wrappedData.setSourceGraph(srcGraph);
        }

        @Override
        public S getSourceGraph() {
            return wrappedData.getSourceGraph();
        }

        @Override
        public List<T> getTargetGraphs() {
            return wrappedData.getTargetGraphs();
        }

        @Override
        public void log(String msg) {
            wrappedData.log(msg);
        }

        @Override
        public Iterable<String> getMessages() {
            return wrappedData.getMessages();
        }
        
    }
    
}
