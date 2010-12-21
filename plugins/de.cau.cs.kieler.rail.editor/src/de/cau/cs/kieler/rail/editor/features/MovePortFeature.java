/**
 * 
 */
package de.cau.cs.kieler.rail.editor.features;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IMoveShapeContext;
import org.eclipse.graphiti.features.impl.DefaultMoveShapeFeature;
import org.eclipse.graphiti.mm.pictograms.Shape;

import de.cau.cs.kieler.rail.Topologie.Basegraph.Port;

/**
 * @author hdw
 *
 */
public class MovePortFeature extends DefaultMoveShapeFeature {
 
    public MovePortFeature(IFeatureProvider fp) {
        super(fp);
    }
 
    @Override
    public boolean canMoveShape(IMoveShapeContext context) {
        boolean canMove = super.canMoveShape(context);
 
        // perform further check only if move allowed by default feature
        if (canMove) {
            Shape shape = context.getShape();
            Object bo = getBusinessObjectForPictogramElement(shape);
            System.out.println(shape.getGraphicsAlgorithm().getX());
            if (bo instanceof Port) {
                Port c = (Port) bo;
            }
        }
        return canMove;
    }
}
