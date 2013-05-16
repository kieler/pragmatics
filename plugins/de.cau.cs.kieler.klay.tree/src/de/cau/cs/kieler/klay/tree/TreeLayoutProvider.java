/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.tree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.kiml.AbstractLayoutProvider;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.klay.tree.graph.TGraph;
import de.cau.cs.kieler.klay.tree.graph.TGraphBuilder;
import de.cau.cs.kieler.klay.tree.graph.TNode;
import de.cau.cs.kieler.klay.tree.properties.Properties;

/**
 * TODO: Document this class.
 * 
 * @author cds
 */
@SuppressWarnings("unused")
public class TreeLayoutProvider extends AbstractLayoutProvider {
    
    /** default value for spacing between nodes. */
    private static final float DEFAULT_SPACING = 15.0f;
	
    /**
     * {@inheritDoc}
     */
    @Override
    public void doLayout(KNode parentNode, IKielerProgressMonitor progressMonitor) {
        //TODO move this algorithm into a phase and set all of KlayTree dolayout
        TGraph graph = TGraphBuilder.createTGraphFromKGraph(parentNode);
        progressMonitor.begin("Mr.T", 1);
        KShapeLayout parentLayout = parentNode.getData(KShapeLayout.class);

        float objectSpacing = parentLayout.getProperty(LayoutOptions.SPACING);
        if (objectSpacing < 0) {
            objectSpacing = DEFAULT_SPACING;
        }

        float borderSpacing = parentLayout.getProperty(LayoutOptions.BORDER_SPACING);
        if (borderSpacing < 0) {
            borderSpacing = DEFAULT_SPACING;
        }
        
        // TODO structure add root or potential roots
        List<TNode> root = new ArrayList<TNode>();
        for (TNode tNode : graph.getNodes()) {
			if (tNode.getParent() == null)
				root.add(tNode);
		}
        
        TNode tempRoot;
        
        Iterator<TNode> iterator = root.iterator();
        if (iterator.hasNext()){
        	iterator.next();
        	tempRoot = iterator.next();
        	for (; iterator.hasNext();) {
        		TNode tNode = iterator.next();
        		tNode.setParent(tempRoot);
        		tempRoot.addChild(tNode);
        	}
        }
        else{
        	tempRoot = root.isEmpty() ? null : root.get(0); // SEND ERROR
        }
        
        int depth = tempRoot.getProperty(Properties.DEPTH);
        int fan = tempRoot.getProperty(Properties.FAN);
        
        tempRoot.setPosition(new KVector(fan/2,0.0));
       
        //TODO complete algorithm
        for (TNode tNode : tempRoot.getChildren()) {
			
		}
    }
    
}
