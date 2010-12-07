/**
 * 
 */
package de.cau.cs.kieler.rail.editor.features;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddFeature;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.util.ColorConstant;
import org.eclipse.graphiti.util.IColorConstant;

import de.cau.cs.kieler.core.model.graphiti.IStyleProvider;

/**
 * @author hdw
 *
 */
public abstract class AddFeature extends AbstractAddFeature {

	/** the style provider. */ 
    protected IStyleProvider styleProvider;
    
    protected static final IColorConstant CLASS_TEXT_FOREGROUND =
        new ColorConstant(51, 51, 153);
 
    protected static final IColorConstant CLASS_FOREGROUND =
        new ColorConstant(255, 102, 0);
 
    protected static final IColorConstant CLASS_BACKGROUND =
        new ColorConstant(255, 204, 153);
    
	public AddFeature(IFeatureProvider fp, final IStyleProvider thestyleProvider) {
		super(fp);
		this.styleProvider = thestyleProvider;
	}


	/* (non-Javadoc)
	 * @see org.eclipse.graphiti.func.IAdd#canAdd(org.eclipse.graphiti.features.context.IAddContext)
	 */
	public abstract boolean canAdd(IAddContext context);

	/* (non-Javadoc)
	 * @see org.eclipse.graphiti.func.IAdd#add(org.eclipse.graphiti.features.context.IAddContext)
	 */
	public abstract PictogramElement add(IAddContext context);

}
