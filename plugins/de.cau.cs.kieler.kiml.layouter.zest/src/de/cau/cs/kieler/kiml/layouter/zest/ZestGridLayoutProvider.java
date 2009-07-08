package de.cau.cs.kieler.kiml.layouter.zest;

import org.eclipse.zest.layouts.algorithms.GridLayoutAlgorithm;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.layout.services.AbstractLayoutProvider;


/**
 * Layout provider that uses the Zest grid layout algorithm.
 * 
 * @author msp
 */
public class ZestGridLayoutProvider extends AbstractLayoutProvider {
	
	/*
	 * (non-Javadoc)
	 * @see de.cau.cs.kieler.kiml.layout.services.AbstractLayoutProvider#doLayout(de.cau.cs.kieler.core.kgraph.KNode, de.cau.cs.kieler.core.alg.IKielerProgressMonitor)
	 */
	public void doLayout(KNode layoutNode,
			IKielerProgressMonitor progressMonitor) throws KielerException {
		ZestAlgorithmWrapper wrapper = new ZestAlgorithmWrapper(
				new GridLayoutAlgorithm());
		wrapper.doLayout(layoutNode, progressMonitor);
	}

}
