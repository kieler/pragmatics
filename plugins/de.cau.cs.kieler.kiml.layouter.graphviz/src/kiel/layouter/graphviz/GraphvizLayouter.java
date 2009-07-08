package kiel.layouter.graphviz;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;

/**
 * Interface for GraphViz layout access classes.
 * 
 * @author ?
 */
public interface GraphvizLayouter {
	
    /**
     * Actual commands for the dot executable to call the different engines.
     */
    public final static String DOT_COMMAND = "dot";
    public final static String NEATO_COMMAND = "neato";
    public final static String TWOPI_COMMAND = "twopi";
    public final static String FDP_COMMAND = "fdp";
    public final static String CIRCO_COMMAND = "circo";
    public final static String COMMAND_PARAMETER = "-K";
    
	/**
	 * Performs layout on the given parent node.
	 * 
	 * @param node parent node
	 * @param progressMonitor progress monitor
	 * @throws KielerException if the layout process fails
	 */
	public void visit(KNode node, IKielerProgressMonitor progressMonitor)
			throws KielerException;
	
}
