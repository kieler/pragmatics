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

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.smart.MetaLayout;
import de.cau.cs.kieler.kiml.smart.SmartLayoutService;
import de.cau.cs.kieler.klay.info.KimlViewerPlugin;
import de.cau.cs.kieler.klay.info.views.SmartLayoutContentProvider.ResultData;

/**
 * Label provider for the smart layout view.
 *
 * @author msp
 */
public class SmartLayoutLabelProvider extends LabelProvider {
    
    /** path to the image used for meta layouts. */
    private static final String META_LAYOUT_PATH = "/icons/meta.gif";
    /** path to the image used for layout rules. */
    private static final String SMART_RULE_PATH = "/icons/ruler.gif";
    
    /** the image used for meta layouts. */
    private Image metaLayoutImage;
    /** the image used for layout rules. */
    private Image smartRuleImage;

    /**
     * Creates a smart layout label provider.
     */
    public SmartLayoutLabelProvider() {
        metaLayoutImage = KimlViewerPlugin.imageDescriptorFromPlugin(
                KimlViewerPlugin.PLUGIN_ID, META_LAYOUT_PATH).createImage();
        smartRuleImage = KimlViewerPlugin.imageDescriptorFromPlugin(
                KimlViewerPlugin.PLUGIN_ID, SMART_RULE_PATH).createImage();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Image getImage(final Object element) {
        if (element instanceof MetaLayout) {
            return metaLayoutImage;
        } else  if (element instanceof ResultData) {
            return smartRuleImage;
        }
        return null;
    }
    
    // CHECKSTYLEOFF MagicNumber
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String getText(final Object element) {
        if (element instanceof MetaLayout) {
            MetaLayout metaLayout = (MetaLayout) element;
            String name;
            KNode node = metaLayout.getGraph();
            if (node.getLabels().isEmpty() || node.getLabels().get(0).getText().length() == 0) {
                name = "Unlabeled Node";
            } else {
                name = "Node " + node.getLabels().get(0).getText();
            }
            return name + " - " + timeString(metaLayout.getTimestamp()) + " ago";
        } else  if (element instanceof ResultData) {
            ResultData resultData = (ResultData) element;
            String name = SmartLayoutService.getInstance().getName(resultData.getSmartRule());
            if (resultData.getSuitability() < 0) {
                return name + ": failed";
            } else {
                return name + ": " + (int) (resultData.getSuitability() * 100) + "%";
            }
        }
        return null;
    }
    
    /**
     * Create a string containing the age of the given timestamp.
     * 
     * @param timestamp a timestamp
     * @return the age as a string
     */
    private String timeString(final long timestamp) {
        long age = System.currentTimeMillis() - timestamp;
        if (age >= 1000) {
            return String.format("%1$.2f s", (double) age / 1000);
        } else {
            return String.format("%1$d ms", age);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void dispose() {
        super.dispose();
        if (metaLayoutImage != null) {
            metaLayoutImage.dispose();
            metaLayoutImage = null;
        }
        if (smartRuleImage != null) {
            smartRuleImage.dispose();
            smartRuleImage = null;
        }
    }

}
