/**
 * 
 */
package de.cau.cs.kieler.rail.editor;

import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.ICreateConnectionFeature;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IDirectEditingFeature;
import org.eclipse.graphiti.features.IResizeShapeFeature;
import org.eclipse.graphiti.features.IUpdateFeature;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.IDirectEditingContext;
import org.eclipse.graphiti.features.context.IResizeShapeContext;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.ui.features.DefaultFeatureProvider;

import de.cau.cs.kieler.core.model.graphiti.IStyleProvider;
import de.cau.cs.kieler.kaom.graphiti.diagram.StyleProvider;
import de.cau.cs.kieler.rail.Topologie.Basegraph.Edge;
import de.cau.cs.kieler.rail.Topologie.SpecializedVertices.EOrientation;
import de.cau.cs.kieler.rail.Topologie.SpecializedVertices.Einbruchsknoten;
import de.cau.cs.kieler.rail.Topologie.SpecializedVertices.Stumpfgleisknoten;
import de.cau.cs.kieler.rail.Topologie.SpecializedVertices.Weichenknoten;
import de.cau.cs.kieler.rail.editor.features.AddBreachFeature;
import de.cau.cs.kieler.rail.editor.features.AddDeadEndVertexFeature;
import de.cau.cs.kieler.rail.editor.features.AddEdgeFeature;
import de.cau.cs.kieler.rail.editor.features.AddFeature;
import de.cau.cs.kieler.rail.editor.features.CreateEdgeFeature;
import de.cau.cs.kieler.rail.editor.features.CreateFeature;
import de.cau.cs.kieler.rail.editor.features.DirectEditBreachFeatures;
import de.cau.cs.kieler.rail.editor.features.ResizeFeature;
import de.cau.cs.kieler.rail.editor.features.TypeFeatures;
import de.cau.cs.kieler.rail.editor.features.UpdateBreachFeature;

/**
 * @author hdw
 *
 */
public class FeatureProvider extends DefaultFeatureProvider {
    /** the style provider that is used by the features. */
    private IStyleProvider styleProvider;
	/**
	 * 
	 */
	public FeatureProvider( IDiagramTypeProvider dtp) {
        super(dtp);
        styleProvider = new StyleProvider(dtp);
    }
	
    @Override
    public IAddFeature getAddFeature(IAddContext context) {
    	if (context.getNewObject() instanceof Einbruchsknoten) {
            return new AddFeature(this,this.styleProvider,TypeFeatures.BREANCH);
        } else if (context.getNewObject() instanceof Stumpfgleisknoten) {
        	return new AddFeature(this,this.styleProvider,TypeFeatures.DEADENDVERTEX);
        } else if (context.getNewObject() instanceof Weichenknoten) {
        	EOrientation E = ((Weichenknoten)(context.getNewObject())).getAbzweigendeLage();
        	switch (E){
        	case LINKS:
        		return new AddFeature(this,this.styleProvider,TypeFeatures.SWITCHVERTEX_LEFT);
        	case RECHTS:
        		return new AddFeature(this,this.styleProvider,TypeFeatures.SWITCHVERTEX_RIGHT);
        	}
        }
        else if (context.getNewObject() instanceof Edge) {
            return new AddEdgeFeature(this,styleProvider);
        }
        return super.getAddFeature(context);
    }


    @Override
    public ICreateFeature[] getCreateFeatures() {
    	//, new CreateDeadEndVertexFeature(this)
    	
    	return new ICreateFeature[] { new CreateFeature(this,TypeFeatures.BREANCH ),new CreateFeature(this,TypeFeatures.DEADENDVERTEX ), new CreateFeature(this,TypeFeatures.SWITCHVERTEX_LEFT),new CreateFeature(this,TypeFeatures.SWITCHVERTEX_RIGHT)};
    }
    
    @Override
    public ICreateConnectionFeature[] getCreateConnectionFeatures() {
        return new ICreateConnectionFeature[] {
            new CreateEdgeFeature (this) };
    }
    
    @Override
    public IUpdateFeature getUpdateFeature(IUpdateContext context) {
        PictogramElement pictogramElement = context.getPictogramElement();
        if (pictogramElement instanceof ContainerShape) {
            Object bo = getBusinessObjectForPictogramElement(pictogramElement);
            if (bo instanceof Einbruchsknoten) {
                return new UpdateBreachFeature(this);
            }
        }
        return super.getUpdateFeature(context);
    }
    
    @Override
    public IDirectEditingFeature getDirectEditingFeature(
        IDirectEditingContext context) {
        PictogramElement pe = context.getPictogramElement();
        Object bo = getBusinessObjectForPictogramElement(pe);
        if (bo instanceof Einbruchsknoten) {
            return new DirectEditBreachFeatures(this);
        }
        return super.getDirectEditingFeature(context);
    }

    
    @Override
    public IResizeShapeFeature getResizeShapeFeature(
            IResizeShapeContext context) {
        Shape shape = context.getShape();
        Object bo = getBusinessObjectForPictogramElement(shape);
        if (bo instanceof Einbruchsknoten) {
            return new ResizeFeature(this,TypeFeatures.BREANCH);
        } else if (bo instanceof Stumpfgleisknoten) {
        	return new ResizeFeature(this,TypeFeatures.DEADENDVERTEX);
        } else if (bo instanceof Weichenknoten) {
        	EOrientation E = ((Weichenknoten)(bo)).getAbzweigendeLage();
        	switch (E){
        	case LINKS:
        		return new ResizeFeature(this,TypeFeatures.SWITCHVERTEX_LEFT);
        	case RECHTS:
        		return new ResizeFeature(this,TypeFeatures.SWITCHVERTEX_RIGHT);
        	}
        }
        return super.getResizeShapeFeature(context);
    }
}
