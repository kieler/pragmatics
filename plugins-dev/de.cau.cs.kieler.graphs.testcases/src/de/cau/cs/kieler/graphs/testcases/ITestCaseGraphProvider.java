/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2017 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.graphs.testcases;

import java.util.function.Predicate;

import org.eclipse.elk.core.util.IGraphElementVisitor;
import org.eclipse.elk.graph.ElkNode;

import de.cau.cs.kieler.formats.GraphFormatData;
import de.cau.cs.kieler.formats.GraphFormatsService;
import de.cau.cs.kieler.formats.IGraphTransformer;
import de.cau.cs.kieler.formats.TransformationData;
import de.cau.cs.kieler.formats.kgraph.KGraphHandler;
import de.cau.cs.kieler.klighd.LightDiagramLayoutConfig;
import de.cau.cs.kieler.klighd.ViewContext;
import de.cau.cs.kieler.klighd.kgraph.KNode;

public interface ITestCaseGraphProvider {

	ViewContext getViewContext();
	
	Iterable<IGraphElementVisitor> getGraphModifiers(); 
	
	Iterable<Predicate<ElkNode>> getGraphFilters();
	
    default ElkNode layoutAndToElkNode(ViewContext vc) {
        // important! otherwise nodes may not have a proper size
        new LightDiagramLayoutConfig(vc).performLayout();
        
        KNode kgraph = vc.getViewModel();
        // now make it a elk graph
        GraphFormatData kgraphFormat =
                GraphFormatsService.getInstance().getFormatData(KGraphHandler.ID);
        @SuppressWarnings("unchecked")
        IGraphTransformer<KNode, ElkNode> importer =
                (IGraphTransformer<KNode, ElkNode>) kgraphFormat.getHandler().getImporter();
        TransformationData<KNode, ElkNode> td = new TransformationData<>();
        td.setSourceGraph(kgraph);
        importer.transform(td);
        ElkNode elkGraph = td.getTargetGraphs().get(0);
        
        return elkGraph;
    }
}
