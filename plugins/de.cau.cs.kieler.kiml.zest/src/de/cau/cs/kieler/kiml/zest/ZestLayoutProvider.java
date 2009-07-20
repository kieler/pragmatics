package de.cau.cs.kieler.kiml.zest;

import org.eclipse.zest.layouts.algorithms.AbstractLayoutAlgorithm;
import org.eclipse.zest.layouts.algorithms.GridLayoutAlgorithm;
import org.eclipse.zest.layouts.algorithms.RadialLayoutAlgorithm;
import org.eclipse.zest.layouts.algorithms.SpringLayoutAlgorithm;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.layout.services.AbstractLayoutProvider;


/**
 * Layout provider that uses the Zest grid layout algorithm.
 * 
 * @author <a href="mailto:msp@informatik.uni-kiel.de">Miro Sp&ouml;nemann</a>
 */
public class ZestLayoutProvider extends AbstractLayoutProvider {
	
    /** parameter value for the grid layout algorithm */
    public final static String GRID_LAYOUT = "GridLayout";
    /** parameter value for the spring layout algorithm */
    public final static String SPRING_LAYOUT = "SpringLayout";
    /** parameter value for the radial layout algorithm */
    public final static String RADIAL_LAYOUT = "RadialLayout";
    
    /** parameter string for the configured Zest algorithm */
    private String algoParam = null;
    
    /**
     * Initializes the Zest layout provider by setting the layout algorithm
     * with the given parameter string.
     * 
     * @param parameter parameter string that holds one of the pre-defined
     *     algorithm names
     */
    public void initialize(String parameter) {
        this.algoParam = parameter;
    }
    
	/*
	 * (non-Javadoc)
	 * @see de.cau.cs.kieler.kiml.layout.services.AbstractLayoutProvider#doLayout(de.cau.cs.kieler.core.kgraph.KNode, de.cau.cs.kieler.core.alg.IKielerProgressMonitor)
	 */
	public void doLayout(KNode layoutNode,
			IKielerProgressMonitor progressMonitor) throws KielerException {
	    AbstractLayoutAlgorithm zestLayoutAlgo;
	    if (GRID_LAYOUT.equals(algoParam))
	        zestLayoutAlgo = new GridLayoutAlgorithm();
	    else if (RADIAL_LAYOUT.equals(algoParam))
	        zestLayoutAlgo = new RadialLayoutAlgorithm();
	    else {
	        SpringLayoutAlgorithm springAlgo = new SpringLayoutAlgorithm();
	        springAlgo.setRandom(false);
	        zestLayoutAlgo = springAlgo;
	    }
		ZestAlgorithmWrapper wrapper = new ZestAlgorithmWrapper(zestLayoutAlgo);
		wrapper.doLayout(layoutNode, progressMonitor);
	}

}
