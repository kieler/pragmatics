package de.cau.cs.kieler.kaom.graphiti.features;


import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddConnectionContext;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddFeature;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ConnectionDecorator;
import org.eclipse.graphiti.mm.pictograms.GraphicsAlgorithmContainer;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Polyline;
import org.eclipse.graphiti.mm.pictograms.Text;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;


import de.cau.cs.kieler.kaom.Link;

public class AddLinkFeature extends AbstractAddFeature {

    public AddLinkFeature(IFeatureProvider fp) {
        super(fp);
        // TODO Auto-generated constructor stub
    }

    public PictogramElement add(IAddContext context) {
        // TODO Auto-generated method stub
        //return null;
    //    System.out.println("Helelelelelelellelelelelelelelelellllooooooo");
        IAddConnectionContext addConContext = (IAddConnectionContext) context;
        Link elink = (Link) context.getNewObject();
        IPeCreateService peCreateService = Graphiti.getPeCreateService();
       
        Connection conn = peCreateService.createFreeFormConnection(getDiagram());
        conn.setStart(addConContext.getSourceAnchor());
        conn.setEnd(addConContext.getTargetAnchor());
        IGaService gaService = Graphiti.getGaService();
        Polyline polyline = gaService.createPolyline(conn);
        polyline.setStyle(StyleUtil.getStyleForEClass(getDiagram()));
    //    System.out.println("I have come at the last position too");             
        
        link(conn, elink);
    //    System.out.println("I have come at the last position too78979789");             
 
        ConnectionDecorator textDecorator = 
            peCreateService.createConnectionDecorator(conn, true, 0.5, true);
        Text text = gaService.createDefaultText(textDecorator);
        text.setStyle(StyleUtil.getStyleForEClassText((getDiagram())));
        gaService.setLocation(text, 10, 0);
        // set reference name in the text decorator
    //    System.out.println("I have come at the last position too");
        Link link = (Link) context.getNewObject();
        text.setValue(link.getName());

        // add static graphical decorators (composition and navigable)
        ConnectionDecorator cd;
        cd = peCreateService.createConnectionDecorator(conn, false,1.0, true);
     //   System.out.println("I have come at the last position too");
        createArrow(cd);
           
        return conn;
               
    }

    public boolean canAdd(final IAddContext context) {
        // TODO Auto-generated method stub
     //   System.out.println("hbdfisd");
       if (context instanceof IAddConnectionContext
               && context.getNewObject() instanceof Link) {
           return true;
       }
       return false;
    }

    private Polyline createArrow(GraphicsAlgorithmContainer gaContainer) {
        Polyline polyline = 
            Graphiti.getGaCreateService().createPolyline(gaContainer, 
                    new int[] { -15, 10, 0, 0, -15, -10 });
        polyline.setStyle(StyleUtil.getStyleForEClass(getDiagram()));
  //      System.out.println("I have come at the last position too");
        return polyline;
}
    
}
