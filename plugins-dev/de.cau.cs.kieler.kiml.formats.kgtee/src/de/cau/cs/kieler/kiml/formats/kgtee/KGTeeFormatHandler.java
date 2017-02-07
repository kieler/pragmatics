/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2015 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.formats.kgtee;

import org.eclipse.elk.graph.KNode;
import org.eclipse.emf.ecore.resource.ResourceSet;

import de.cau.cs.kieler.kgraph.text.ui.KGraphTextHandler;
import de.cau.cs.kieler.formats.IGraphTransformer;
import de.cau.cs.kieler.formats.TransformationData;
import de.cau.cs.kieler.klighd.LightDiagramServices;
import de.cau.cs.kieler.klighd.util.KlighdSynthesisProperties;
import de.cau.cs.kieler.graphs.klighd.syntheses.KGraphDiagramSynthesis;

/**
 * Handles opening {@code .kgtee} files, puts them through the {@link KGraphDiagramSynthesis} and
 * returns the result.
 * 
 * @author cds
 */
public class KGTeeFormatHandler extends KGraphTextHandler {

    /** File extension of KGT files. */
    public static final String KGT_EXTENSION = "kgte";
    
    /**
     * {@inheritDoc}
     */
    public IGraphTransformer<KNode, KNode> getImporter() {
        return new KgtImporter();
    }

    /**
     * {@inheritDoc}
     */
    public IGraphTransformer<KNode, KNode> getExporter() {
        throw new UnsupportedOperationException("Exporting KNodes to KGT is not supported.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected ResourceSet createResourceSet() {
        return super.createResourceSet();
    }

    private class KgtImporter implements IGraphTransformer<KNode, KNode> {
        /**
         * {@inheritDoc}
         */
        public void transform(TransformationData<KNode, KNode> data) {
            KNode model = data.getSourceGraph();
            if (model != null) {
                KlighdSynthesisProperties props = KlighdSynthesisProperties.create();
                
                // always use ptolemy diagram synthesis
                props.useDiagramSynthesis(KGraphDiagramSynthesis.TRANSFORMATION_ID);

                // TODO Check if any synthesis options need to be configured here
                // props.configureSynthesisOptionValue(PtolemyDiagramSynthesis.FLATTEN, true);
                
                KNode graph = LightDiagramServices.translateModel(model, null, props);
                                        
                data.getTargetGraphs().clear();
                data.getTargetGraphs().add(graph);
            }
        }

        /**
         * {@inheritDoc}
         */
        public void transferLayout(TransformationData<KNode, KNode> data) {
            throw new UnsupportedOperationException("Applying layout to a KGTe file is not supported.");
        }
    }

}
