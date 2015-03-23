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
package de.cau.cs.kieler.ptolemy.attachmenteval.editors.attachment;

import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutData;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.klighd.ViewContext;

/**
 * @author cds
 *
 */
class AttachmentContentProvider implements ITreeContentProvider {

    /**
     * {@inheritDoc}
     */
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    public Object[] getElements(Object inputElement) {
        if (inputElement instanceof ViewContext) {
            KNode viewModel = ((ViewContext) inputElement).getViewModel();
            if (viewModel != null) {
                return new Object[] {viewModel};
            }
        }
        
        return new Object[0];
    }

    /**
     * {@inheritDoc}
     */
    public Object[] getChildren(Object parentElement) {
        // Prepare a list of all children that either have children themselves or that are comments
        List<KNode> children = ((KNode) parentElement).getChildren();
        List<KNode> result = Lists.newArrayListWithCapacity(children.size());
        
        for (KNode child : children) {
            KLayoutData layoutData = child.getData(KLayoutData.class);
            
            if (child.getChildren().size() > 0 || layoutData.getProperty(LayoutOptions.COMMENT_BOX)) {
                result.add(child);
            }
        }
        
        return result.toArray();
    }

    /**
     * {@inheritDoc}
     */
    public Object getParent(Object element) {
        return ((KNode) element).getParent();
    }

    /**
     * {@inheritDoc}
     */
    public boolean hasChildren(Object element) {
        return getChildren(element).length > 0;
    }

    /**
     * {@inheritDoc}
     */
    public void dispose() {
    }

}
