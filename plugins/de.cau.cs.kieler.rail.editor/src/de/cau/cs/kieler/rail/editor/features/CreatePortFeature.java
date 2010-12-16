package de.cau.cs.kieler.rail.editor.features;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import org.eclipse.graphiti.mm.pictograms.Diagram;

import de.cau.cs.kieler.rail.Topologie.Basegraph.BasegraphFactory;
import de.cau.cs.kieler.rail.Topologie.Basegraph.Port;
import de.cau.cs.kieler.rail.Topologie.Basegraph.Vertex;

public class CreatePortFeature extends AbstractCreateFeature {
    /**
     * The constructor.
     * 
     * @param fp the feature provider
     */
    public CreatePortFeature(final IFeatureProvider fp) {
        super(fp,  "Port", "Erstelle Port");
    }
    
    /**
     * {@inheritDoc}
     */
    public boolean canCreate(final ICreateContext context) {
        return (getBusinessObjectForPictogramElement(context.getTargetContainer()) instanceof Vertex)
                && !(context.getTargetContainer() instanceof Diagram);
    }
    
    /**
     * {@inheritDoc}
     */
    public Object[] create(final ICreateContext context) {
        Vertex vertex = (Vertex) getBusinessObjectForPictogramElement(context.getTargetContainer());
        Port port = BasegraphFactory.eINSTANCE.createPort();
        //TODO allow only 2 or 3.
        vertex.getPorts().add(port);

        addGraphicalRepresentation(context, port);
        return new Object[] { port };
    }
}
