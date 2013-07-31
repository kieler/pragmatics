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
package de.cau.cs.kieler.klay.tree.util;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import com.google.common.collect.Iterables;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KGraphData;
import de.cau.cs.kieler.core.kgraph.KGraphFactory;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.klayoutdata.KInsets;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutDataFactory;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.util.KimlUtil;

/**
 * @author sor
 * @author sgu
 * 
 */
public class GenTestGraph {

    /**
     * @param size
     * @return
     */
    public KNode generateKgraph(int size) {

        // note a single Random object is reused here
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(size) + 1;

        size = size - randomInt;

        KNode superRootNode = KGraphFactory.eINSTANCE.createKNode();

        KShapeLayout shapeLayout = KLayoutDataFactory.eINSTANCE.createKShapeLayout();

        KInsets insets = KLayoutDataFactory.eINSTANCE.createKInsets();

        shapeLayout.setInsets(insets);

        superRootNode.getData().add(shapeLayout);

        KLabel label = KGraphFactory.eINSTANCE.createKLabel();

        label.setText("0");

        superRootNode.getLabels().add(label);

        int level = 1;

        addNodes(randomInt, superRootNode, level);

        LinkedList<KNode> currentLevel = new LinkedList<KNode>();

        LinkedList<KNode> nextLevel = new LinkedList<KNode>();

        nextLevel.addAll(superRootNode.getChildren());

        while (0 < size) {
            level++;
            currentLevel.clear();
            currentLevel.addAll(nextLevel);
            nextLevel.clear();

            Iterator<KNode> currentIterator = currentLevel.iterator();

            while (currentIterator.hasNext() && 0 < size) {
                KNode kNode = currentIterator.next();
                randomInt = randomGenerator.nextInt(size) + 1;
                addNodes(randomInt, kNode, level);
                nextLevel.addAll(kNode.getChildren());
                size = size - randomInt;
            }
        }

        return superRootNode;
    }

    /**
     * @param size
     * @param parentNode
     * @param level
     */
    private void addNodes(int size, KNode parentNode, int level) {
        EList<KNode> children = parentNode.getChildren();
        EList<KEdge> outgoingEdges = parentNode.getOutgoingEdges();
        String parentLabel = Iterables.getFirst(parentNode.getLabels(),
                KGraphFactory.eINSTANCE.createKLabel()).getText();
        ;
        String childLabel;
        int digits = (int) (Math.floor(Math.log10(size)) + 1);
        for (int i = 0; i < size; i++) {
            KNode childNode = KGraphFactory.eINSTANCE.createKNode();

            KShapeLayout shapeLayout = KLayoutDataFactory.eINSTANCE.createKShapeLayout();

            KInsets insets = KLayoutDataFactory.eINSTANCE.createKInsets();

            shapeLayout.setInsets(insets);

            childNode.getData().add(shapeLayout);

            KEdge kEdge = KGraphFactory.eINSTANCE.createKEdge();
            kEdge.setSource(parentNode);
            kEdge.setTarget(childNode);
            childNode.getIncomingEdges().add(kEdge);
            outgoingEdges.add(kEdge);

            childLabel = FillStrings.formatRight(String.valueOf(i), digits);
            KLabel label = KGraphFactory.eINSTANCE.createKLabel();
            label.setText(parentLabel + childLabel);
            childNode.getLabels().add(label);

            children.add(childNode);
        }
    }

    /**
     * Export the given layout graph in KGraph format.
     * 
     * @param graph
     *            the parent node of the layout graph
     */
    public void exportLayoutGraph(final KNode graph) {
        String path = System.getProperty("user.home");
        if (path != null) {
            if (path.endsWith(File.separator)) {
                path += "tmp" + File.separator + "layout" + File.separator
                        + Integer.toHexString(graph.hashCode()) + ".keg";
            } else {
                path += File.separator + "tmp" + File.separator + "layout" + File.separator
                        + Integer.toHexString(graph.hashCode()) + ".keg";
            }

            // serialize all properties of the graph
            KimlUtil.persistDataElements(graph);

            // save the KGraph to a file
            ResourceSet resourceSet = new ResourceSetImpl();

            Resource resource = resourceSet.createResource(URI.createFileURI(path));
            resource.getContents().add(graph);
            try {
                resource.save(Collections.emptyMap());
            } catch (IOException e) {
                // ignore the exception and abort the layout graph exporting
            }
        }
    }
}
