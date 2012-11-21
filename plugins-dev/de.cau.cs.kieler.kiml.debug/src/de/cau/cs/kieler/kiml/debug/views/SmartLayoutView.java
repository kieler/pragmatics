/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.debug.views;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import de.cau.cs.kieler.kiml.service.LayoutInfoService;
import de.cau.cs.kieler.kiml.service.LayoutInfoService.ConfigData;
import de.cau.cs.kieler.kiml.smart.MetaLayout;
import de.cau.cs.kieler.kiml.smart.SmartLayoutConfig;

/**
 * A view that allows insight to the smart layout process.
 *
 * @author msp
 */
public class SmartLayoutView extends ViewPart {
    
    /** the view identifier. */
    public static final String VIEW_ID = "de.cau.cs.kieler.kiml.debug.smart";
    
    /** the tree viewer displayed in the view. */
    private TreeViewer treeViewer;
    /** the currently shown meta layout objects. */
    private BiMap<Object, MetaLayout> metaLayoutMap = HashBiMap.create();

    /**
     * {@inheritDoc}
     */
    @Override
    public void createPartControl(final Composite parent) {
        treeViewer = new TreeViewer(parent);
        treeViewer.setContentProvider(new SmartLayoutContentProvider());
        treeViewer.setLabelProvider(new SmartLayoutLabelProvider());
        treeViewer.setInput(metaLayoutMap.values());
        treeViewer.setComparator(new SmartLayoutComparator());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFocus() {
        treeViewer.getControl().setFocus();
    }
    
    /**
     * Update the content of the smart layout view.
     */
    public void updateContent() {
        for (ConfigData data : LayoutInfoService.getInstance().getConfigData()) {
            if (data.getConfig() instanceof SmartLayoutConfig) {
                SmartLayoutConfig config = (SmartLayoutConfig) data.getConfig();
                metaLayoutMap.clear();
                metaLayoutMap.putAll(config.getMetaLayoutCache());
                treeViewer.refresh();
            }
        }
    }

}
