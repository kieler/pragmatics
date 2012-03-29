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
package de.cau.cs.kieler.klay.info.views;

import org.eclipse.gef.EditPart;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.part.ViewPart;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import de.cau.cs.kieler.core.model.GraphicalFrameworkService;
import de.cau.cs.kieler.core.model.IGraphicalFrameworkBridge;
import de.cau.cs.kieler.core.ui.UnsupportedPartException;
import de.cau.cs.kieler.kiml.service.LayoutInfoService;
import de.cau.cs.kieler.kiml.service.LayoutInfoService.ConfigData;
import de.cau.cs.kieler.kiml.smart.MetaLayout;
import de.cau.cs.kieler.kiml.smart.SmartLayoutConfig;
import de.cau.cs.kieler.klay.info.views.SmartLayoutContentProvider.ResultData;

/**
 * A view that allows insight to the smart layout process.
 *
 * @author msp
 */
public class SmartLayoutView extends ViewPart {
    
    /** the view identifier. */
    public static final String VIEW_ID = "de.cau.cs.kieler.klay.info.smart";
    
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
        
        Tree tree = (Tree) treeViewer.getControl();
        tree.addSelectionListener(new SelectionListener() {
            public void widgetSelected(final SelectionEvent event) {
                if (event.item instanceof TreeItem) {
                    Object data = ((TreeItem) event.item).getData();
                    MetaLayout metaLayout = null;
                    if (data instanceof MetaLayout) {
                        metaLayout = (MetaLayout) data;
                    } else if (data instanceof ResultData) {
                        metaLayout = ((ResultData) data).getMetaLayout();
                    }
                    Object object = metaLayoutMap.inverse().get(metaLayout);
                    if (object != null) {
                        try {
                            IGraphicalFrameworkBridge bridge = GraphicalFrameworkService.getInstance()
                                    .getBridge(object);
                            EditPart editPart = bridge.getEditPart(object);
                            bridge.setSelection(editPart);
                        } catch (UnsupportedPartException exception) {
                            // ignore exception
                        }
                    }
                }
            }
            public void widgetDefaultSelected(final SelectionEvent e) {
            }
        });
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
