package de.cau.cs.kieler.rail.editor.features;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.graphiti.features.IDirectEditingInfo;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddConnectionContext;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddFeature;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ConnectionDecorator;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;
import org.eclipse.graphiti.util.IColorConstant;

import de.cau.cs.kieler.core.model.graphiti.IStyleProvider;
import de.cau.cs.kieler.kaom.graphiti.diagram.StyleProvider;
import de.cau.cs.kieler.rail.Topologie.Basegraph.Edge;

/**
 * AddFeature for the graphical Representation of the edge.
 * @author hdw
 *
 */
public class AddEdgeFeature extends AbstractAddFeature {
 
	/**
	 * {@value styleProvider} The styleProvider of the styles.
	 */
    private IStyleProvider styleProvider;

    /**
     * Constructor
     * @param fp FeatureProvider
     * @param styleProvider StyleProvider
     */
	public AddEdgeFeature (IFeatureProvider fp,final IStyleProvider styleProvider) {
        super(fp);
		this.styleProvider = styleProvider;
    }
 
	/**
	 * @param context The context on witch the graphical Representation is created to.
	 * @return The Pictogram Element for the Edge (a line for source to target)
	 */
    public PictogramElement add(IAddContext context) {
    	IPeCreateService peCreateService = Graphiti.getPeCreateService();
    	
        IAddConnectionContext addConContext = (IAddConnectionContext) context;
        
        System.out.println("AddEdgeFeature#add");
        
       
        // CONNECTION WITH POLYLINE
        Connection connection = peCreateService
            .createFreeFormConnection(getDiagram());
        connection.setStart(addConContext.getSourceAnchor());
        connection.setEnd(addConContext.getTargetAnchor());
 
        IGaService gaService = Graphiti.getGaService();
        
        Polyline polyline = gaService.createPolyline(connection);
        polyline.setStyle(styleProvider.getStyle(StyleProvider.DEFAULT_STYLE));
 
        // create link and wire it
        if (((EObject) context.getNewObject()).eResource() == null) {
            getDiagram().eResource().getContents().add((EObject) context.getNewObject());
        }
        link(connection, context.getNewObject());
 
        //NEW
        
        ConnectionDecorator textDecorator = peCreateService.createConnectionDecorator(
                connection, true, 0.5, true);
        Text text = gaService.createDefaultText(textDecorator);
        text.setStyle(styleProvider.getStyle());
        gaService.setLocation(text, 10, 0);

        // add static graphical decorators (composition and navigable)
        ConnectionDecorator arrowDecorator = peCreateService.createConnectionDecorator(
                connection, false, 1.0, true);

        // provide information to support direct-editing directly
        // after object creation (must be activated additionally)
        IDirectEditingInfo directEditingInfo = getFeatureProvider().getDirectEditingInfo();
        directEditingInfo.setMainPictogramElement(connection);
        // set shape and graphics algorithm where the editor for
        // direct editing shall be opened after object creation
        directEditingInfo.setPictogramElement(textDecorator);
        directEditingInfo.setGraphicsAlgorithm(text);
        
        //NEW
        
        
        return connection;
    }
 
    /**
     * 
     */
    public boolean canAdd(IAddContext context) {
        // return true if given business object is an EReference
        // note, that the context must be an instance of IAddConnectionContext
        if (context instanceof IAddConnectionContext
        		//TODO Edge (before it was Link) really????
            && context.getNewObject() instanceof Edge) {
            return true;
        }
        return false;
    }
}
