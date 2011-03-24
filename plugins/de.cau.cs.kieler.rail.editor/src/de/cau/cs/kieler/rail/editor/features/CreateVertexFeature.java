package de.cau.cs.kieler.rail.editor.features;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.context.ITargetContext;
import org.eclipse.graphiti.features.context.impl.AddContext;
import org.eclipse.graphiti.features.context.impl.AreaContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IPeCreateService;

import de.menges.topologie.Topologie.Model;
import de.menges.topologie.Topologie.Basegraph.BasegraphFactory;
import de.menges.topologie.Topologie.Basegraph.EPort;
import de.menges.topologie.Topologie.Basegraph.Port;
import de.menges.topologie.Topologie.Basegraph.Vertex;
import de.menges.topologie.Topologie.SpecializedVertices.*;

import de.cau.cs.kieler.rail.editor.SemanticProvider;


public class CreateVertexFeature extends AbstractCreateFeature  {
    private static final String TITLE = "Create class";

    private static final String USER_QUESTION = "Enter new class name";
    
    /** the semantic provider used to fetch the top-level element of the current diagram. */
    private SemanticProvider semanticProvider;

	private VertexType type;

    public CreateVertexFeature(IFeatureProvider fp,VertexType type, final SemanticProvider sp) {
        // set name and description of the creation feature
    	super(fp, getName(type), getName(type) + " erstellen");
    	this.type = type;
        this.semanticProvider = sp;
    }

    public boolean canCreate(ICreateContext context) {
        return context.getTargetContainer() instanceof Diagram;
    }


    public Object[] create(ICreateContext context) {
    	Vertex vertex = getVertex();

        ContainerShape tc = context.getTargetContainer();
        Model model = semanticProvider.fetchModel(tc);

        model.getVertices().add(vertex);
        // the 'vertices' reference is _not_ containment, therefore add the vertex to the resource
        // FIXME why is the reference not containment?
        model.eResource().getContents().add(vertex);

        // do the add
        vertex.getPorts().addAll(addGraphicalRepresentationForPorts(vertex));

        PictogramElement vertexPE = addGraphicalRepresentation(context, vertex);

        // activate direct editing after object creation
        getFeatureProvider().getDirectEditingInfo().setActive(true);

        // return newly created business object(s)
        return new Object[] {vertex};
    }

    /**
     * Creates the ports and addGraphicalRepresentation for them.
     * @param context
     * @param vertex
     * @param vertexPE
     * @return the created ports as bisnes
     */
    private EList<Port> addGraphicalRepresentationForPorts(final Object vertex){
    	EList<Port> ports = new BasicEList<Port>();

    	switch (type) {
    		case SWITCH_LEFT:
    		case SWITCH_RIGHT:
    			Port abzweig = BasegraphFactory.eINSTANCE.createPort();
    	    	abzweig.setName(EPort.ABZWEIG);
    	    	Port stamm = BasegraphFactory.eINSTANCE.createPort();
    	    	stamm.setName(EPort.STAMM);
    	    	Port spitze = BasegraphFactory.eINSTANCE.createPort();
    	    	spitze.setName(EPort.SPITZE);
    			ports.add(abzweig);
    			ports.add(stamm);
    			ports.add(spitze);
    			break;
    		case DEADEND:
    		case BREACH:
    			Port ende = BasegraphFactory.eINSTANCE.createPort();
    	    	ende.setName(EPort.ENDE);
    	    	ports.add(ende);
    	    	break;
		default:
			break;
    	}
    	return ports;
    }

    /**
     * Creates the ports and addGraphicalRepresentation for them.
     * @param context
     * @param vertex
     * @param vertexPE
     * @param vertexPE
     * @return the created ports as bisnes 
     */
    private EList<Port> addGraphicalRepresentationForPorts(ITargetContext context, Object vertex, PictogramElement vertexPE) {
    	EList<Port> ports = new BasicEList<Port>();

    	AddContext addBookContext = new AddContext(new AreaContext(), vertex);

        IPeCreateService peCreateService = Graphiti.getPeCreateService();
        ContainerShape contShape =
           peCreateService.createContainerShape(
           context.getTargetContainer(), false);

        contShape.setGraphicsAlgorithm(vertexPE.getGraphicsAlgorithm());
        addBookContext.setTargetContainer(contShape);

    	switch (type) {
    		case SWITCH_LEFT:
    		case SWITCH_RIGHT:
    			Port abzweig = BasegraphFactory.eINSTANCE.createPort();
    	    	abzweig.setName(EPort.ABZWEIG);
    	    	Port stamm = BasegraphFactory.eINSTANCE.createPort();
    	    	stamm.setName(EPort.STAMM);
    	    	Port spitze = BasegraphFactory.eINSTANCE.createPort();
    	    	spitze.setName(EPort.SPITZE);
    			ports.add(abzweig);
    			ports.add(stamm);
    			ports.add(spitze);

    			addGraphicalRepresentation(addBookContext, stamm);
    	    	addGraphicalRepresentation(addBookContext, spitze);
    	    	addGraphicalRepresentation(addBookContext, abzweig);
		default:
			break;
    	}
    	return ports;
    }
    /**
     *Creates the right vertex from the SpecializedVerticesFactory
     *@return a right vertex in dependence form the type
     */
    private Vertex getVertex()
    {
    	Vertex vertex;
		switch(type){
    	case BREACH:
        	return SpecializedVerticesFactory.eINSTANCE.createEinbruchsknoten();
    	case DEADEND:
    		return SpecializedVerticesFactory.eINSTANCE.createStumpfgleisknoten();
    	//TODO Make for both cases possible.
    	case SWITCH_LEFT:
    		vertex = SpecializedVerticesFactory.eINSTANCE.createWeichenknoten();
    		((Weichenknoten)vertex).setAbzweigendeLage(EOrientation.LINKS);
    		return vertex;
    	case SWITCH_RIGHT:
    		vertex = SpecializedVerticesFactory.eINSTANCE.createWeichenknoten();
    		((Weichenknoten)vertex).setAbzweigendeLage(EOrientation.RECHTS);
    		return vertex;
		}
    	return null;
    }

    /**
     * Get the name of the vertex
     * @param type the type where the name comes from
     * @return a String witch insert the name of this type of vertex.
     */
    private static String getName(VertexType type){
    	switch(type) {
    	case BREACH:
        	return "Einbruchstelle";
    	case DEADEND:
    		return "Stumpfgleisknoten";
    	case SWITCH_LEFT:
    		return "Weiche links";
    	case SWITCH_RIGHT:
    		return "Weiche rechts";
		default:
			break;
    	}
    	return "";
    }
}
