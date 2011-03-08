package de.cau.cs.kieler.rail.editor.features;

import java.util.Iterator;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IDirectEditingContext;
import org.eclipse.graphiti.features.impl.AbstractDirectEditingFeature;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;

import de.cau.cs.kieler.rail.Topologie.Basegraph.Vertex;

/**
 * {@inheritDoc}
 */
public class DirectEditBreachFeatures extends AbstractDirectEditingFeature {

	/**
	 * The Constructor
	 * @param fp the FeatureProvider
	 */
    public DirectEditBreachFeatures(final IFeatureProvider fp) {
        super(fp);
    }
    /**
     * {@inheritDoc}
     */
    public final int getEditingType() {
        // there are several possible editor-types supported:
        // text-field, checkbox, color-chooser, combobox, ...
        return TYPE_TEXT;
    }
    /**
     * {@inheritDoc}
     */
    @Override
	public final boolean canDirectEdit(
			final IDirectEditingContext context) {
        PictogramElement pe = context.getPictogramElement();
        Object bo = getBusinessObjectForPictogramElement(pe);
        GraphicsAlgorithm ga = context.getGraphicsAlgorithm();
        // support direct editing, if it is a EClass, and the user clicked
        // directly on the text and not somewhere else in the rectangle
        if (bo instanceof Vertex && ga instanceof Text) {
        	System.out.println("canDirectEdit true");
            return true;
        }
        // direct editing not supported in all other cases
        System.out.println("canDirectEdit false");
        return false;
    }
    /**
     * {@inheritDoc}
     */
    public final String getInitialValue(final IDirectEditingContext context) {
        // return the current name of the EClass
        PictogramElement pe = context.getPictogramElement();
        Vertex eClass = (Vertex) getBusinessObjectForPictogramElement(pe);
        System.out.println("eClass.getName " + eClass.getName());
        return eClass.getName();
    }
    /**
     * {@inheritDoc}
     */
    @Override
	public final String checkValueValid(final String value,
			final IDirectEditingContext context) {
        if (value.length() < 1) {
			return "Please enter any text as class name.";
		}
        if (value.contains(" ")) {
			return "Spaces are not allowed in class names.";
		}
        if (value.contains("\n")) {
			return "Line breakes are not allowed in class names.";
		}
        // null means, that the value is valid
        return null;
    }
    /**
     * {@inheritDoc}
     */
    public final void setValue(final String value,
    		final IDirectEditingContext context) {
        // set the new name for the MOF class
        PictogramElement pe = context.getPictogramElement();
        Vertex vertex = (Vertex) getBusinessObjectForPictogramElement(pe);
        vertex.setName(value);
        //Text text = getText(context);
        //text.setValue(value);
        // Explicitly update the shape to display the new value in the diagram
        // Note, that this might not be necessary in future versions of Graphiti
        // (currently in discussion)
 
        // we know, that pe is the Shape of the Text, so its container is the
        // main shape of the EClass
        updatePictogramElement(((Shape) pe).getContainer());
    }
    /**
     * Get the Text Object from the context
     * @param context from where the Text Object comes from
     * @return the Text Object or if it has no then null
     */
    private Text getText(final IDirectEditingContext context) {
    	ContainerShape containerShape =
            (ContainerShape) context.getPictogramElement();
    	Iterator<Shape> iter = containerShape.getChildren().iterator();
	    while (iter.hasNext()) {
	    	Shape shape = iter.next();
	    	if (shape instanceof Text) {
	    		return (Text) shape;
	    	}
	    }
	    return null;
    }
}