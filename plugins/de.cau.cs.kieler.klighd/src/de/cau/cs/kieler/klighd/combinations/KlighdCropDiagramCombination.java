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
package de.cau.cs.kieler.klighd.combinations;

import static org.eclipse.emf.ecore.util.EcoreUtil.copy;
import static org.eclipse.emf.ecore.util.EcoreUtil.getRootContainer;
import static org.eclipse.emf.ecore.util.EcoreUtil.copyAll;

import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kivi.AbstractCombination;
import de.cau.cs.kieler.klighd.effects.KlighdUpdateDiagramEffect;
import de.cau.cs.kieler.klighd.triggers.KlighdSelectionTrigger.KlighdSelectionState;

/**
 * General realization of a primitive diagram cropping.
 * Copies the selected view model part and throws it into a new KLighD view.<br>
 * <br>
 * <b>Note:</b> This is just a rough prototype, does not claim to be releasable.
 * Therefore, its registration in plugin.xml is deactivated.
 *  
 * @author chsch
 */
public class KlighdCropDiagramCombination extends AbstractCombination {

    /**
     * THE 'execute' method.
     * 
     * @param selection the selection state the combination is sensitive to
     */
    public void execute(final KlighdSelectionState selection) {
        if (selection.getSelections().size() > 1) {
            final KNode root = copy((KNode) getRootContainer(Iterators.getNext(
                    selection.getSelectedDiagramElements(), null)));
            root.getChildren().clear();
            root.getChildren().addAll(
                    copyAll(Lists.newArrayList(Iterators.filter(
                            selection.getSelectedDiagramElements(), KNode.class))));
            this.schedule(new KlighdUpdateDiagramEffect(selection.getViewId() + "_1", root));
        }
    }

}
