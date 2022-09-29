/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2020 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.grana.analyses;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.eclipse.elk.alg.rectpacking.options.InternalProperties;
import org.eclipse.elk.alg.rectpacking.options.RectPackingOptions;
import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.graph.ElkNode;

import de.cau.cs.kieler.grana.AnalysisContext;
import de.cau.cs.kieler.grana.AnalysisFailed;
import de.cau.cs.kieler.grana.IAnalysis;

/**
 * A drawing analysis that computes the scale measure of a graph.
 * 
 * @author sdo
 */
public class RegionSizeHackAnalysis implements IAnalysis {
    
    public static int INDEX = 0;

    /**
     * Identifier of the area analysis.
     */
    public static final String ID = "de.cau.cs.kieler.grana.regionsizehack";
    
    public static final int DESIRED_ASPECT_RATIO = 0;
    public static final int ACTUAL_ASPECT_RATIO = 1;
    public static final int AREA = 2;
    
    
    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(final ElkNode parentNode, final AnalysisContext context,
            final IElkProgressMonitor progressMonitor) {
        progressMonitor.begin("Region Size Analysis", 1);
        // Fetch the results of the area analysis
        Object o = context.getResult(AreaAnalysis.ID);
        if (o == null) {
            progressMonitor.done();
            
            return new AnalysisFailed(AnalysisFailed.Type.Dependency);
        }
        
        // Compute the aspect ratio
        Object[] areaResults = (Object[]) o;
        double width = (Double) areaResults[AreaAnalysis.INDEX_WIDTH];
        double height = (Double) areaResults[AreaAnalysis.INDEX_HEIGHT];
        double area = width * height;
        double dar = 0;
        if (parentNode.hasProperty(CoreOptions.ASPECT_RATIO)) {
            dar = parentNode.getProperty(CoreOptions.ASPECT_RATIO);
        }
        double aar = width / height;
        
        try {
//            FileOutputStream fos = new FileOutputStream("output" + INDEX + ".csv"); 
//            OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
//            CSVWriter writer = new CSVWriter(osw);
            FileWriter fileWriter = new FileWriter("output-rectpacking-new" + ".csv", true);
            PrintWriter writer = new PrintWriter(fileWriter);
            StringBuilder sb = new StringBuilder();
//            sb.append("Index");
//            sb.append(';');
//            sb.append("Elements"); // Regions or nodes
//            sb.append(';');
//            sb.append("Edges"); // Rectangles have no edges
//            sb.append(';');
//            sb.append("Area");
//            sb.append(';');
//            sb.append("Width");
//            sb.append(';');
//            sb.append("Height");
//            sb.append(';');
//            sb.append("AR");
//            sb.append(';');
//            sb.append("Identifier");
//            sb.append(';');
//            sb.append("Type"); // layered or rectpacking null for leaf nodes or top node (eliminate it)
//            sb.append(';');
//            sb.append("Depth"); // Hierarchical depth 0 means no children, 1 only children, 2 up to grandchildren, ...
//            sb.append(';');
//            sb.append("SizeOfChildren"); // Size of children summed up
//            sb.append(';');
//            sb.append("stackable"); // Size of children summed up
//            sb.append(';');
//            sb.append("secondBetter"); // Size of children summed up
//            sb.append('\n');
            // Analyze all regions
            analyzeGraph(parentNode, sb);

            writer.write(sb.toString());
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new Object[] {dar, aar, area};
    }
    
    /**
     * Go through graph and save for each supgraph (rectpacking, layered has to be looked at separately).
     * Id, Type (based on algorithm), Number of Top Level Elements (Nodes, Rectangles),
     * Edges if any, Area, Width, Height, Aspect Ratio
     * @param parentNode Graph
     * @param sb string for csv
     */
    public void analyzeGraph(final ElkNode parentNode, final StringBuilder sb) {
        // Analyze parent node

        sb.append(INDEX++);
        sb.append(';');
        sb.append(parentNode.getChildren().size()); // Regions or nodes
        sb.append(';');
        sb.append(parentNode.getContainedEdges().size()); // Rectangles have no edges
        sb.append(';');
        sb.append(parentNode.getHeight() * parentNode.getWidth());
        sb.append(';');
        sb.append(parentNode.getWidth());
        sb.append(';');
        sb.append(parentNode.getHeight());
        sb.append(';');
        sb.append(parentNode.getWidth() / parentNode.getHeight());
        sb.append(';');
        sb.append(parentNode.getIdentifier());
        sb.append(';');
        sb.append(parentNode.getProperty(CoreOptions.ALGORITHM)); // Region or State
        sb.append(';');                
        sb.append(calculateMaxDepth(parentNode, 0)); // Depth
        sb.append(';');                
        sb.append(sizeOfChildren(parentNode)); // Size of Children

        sb.append(';');                
        sb.append(0.0); // Size of Children
        sb.append(';');                
        sb.append(0.0); // Size of Children
        sb.append(';');                
        sb.append(0.0); // Size of Children
        sb.append(';');                
        sb.append(0.0); // Size of Children
//        sb.append(';');
//        sb.append(parentNode.getProperty(RectPackingOptions.STACKABLE));
//        sb.append(';');
//        sb.append(parentNode.getProperty(RectPackingOptions.REVISED_WIDTH));
        sb.append('\n');
        // Analyze children
        for (ElkNode node : parentNode.getChildren()) {
            analyzeGraph(node, sb);
        }
    }
    
    private int calculateMaxDepth(ElkNode parent, int depth) {
        for (ElkNode child : parent.getChildren()) {
            depth = Math.max(depth, (1 + calculateMaxDepth(child, 0)));
        }
        return depth;
    }
    
    private double sizeOfChildren(ElkNode parent) {
        double size = 0;
        for (ElkNode child: parent.getChildren()) {
            size += child.getHeight() * child.getWidth();
        }
        return size;
    }
}