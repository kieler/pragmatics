package de.cau.cs.kieler.kaom.graphiti.features;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ILayoutContext;
import org.eclipse.graphiti.features.impl.AbstractLayoutFeature;
import org.eclipse.graphiti.mm.pictograms.BoxRelativeAnchor;

import de.cau.cs.kieler.kaom.Port;

public class LayoutPortFeature extends AbstractLayoutFeature {

    public LayoutPortFeature(final IFeatureProvider fp) {
        super(fp);
    }

    public boolean canLayout(final ILayoutContext context) {
        Object object = getBusinessObjectForPictogramElement(context
                .getPictogramElement());
        return object instanceof Port;
    }

    public boolean layout(final ILayoutContext context) {
        BoxRelativeAnchor boxAnchor = (BoxRelativeAnchor) context
                .getPictogramElement();
        float widthPercent = (float) boxAnchor.getRelativeWidth();
        float heightPercent = (float) boxAnchor.getRelativeHeight();
        float deltaY = heightPercent < (1.0f / 2.0f) ? heightPercent
                : 1 - heightPercent;
        float deltaX = widthPercent < (1.0f / 2.0f) ? widthPercent
                : 1 - widthPercent;
        if (deltaY < deltaX) {
            heightPercent = Math.round(heightPercent);
        } else {
            widthPercent = Math.round(widthPercent);
        }

        boxAnchor.setRelativeWidth(widthPercent);
        boxAnchor.setRelativeHeight(heightPercent);

        return true;
    }

}
