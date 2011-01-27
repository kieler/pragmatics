package de.cau.cs.kieler.rail.editor.features;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.custom.AbstractCustomFeature;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.BoxRelativeAnchor;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

import de.cau.cs.kieler.rail.Topologie.Basegraph.EPort;
import de.cau.cs.kieler.rail.Topologie.Basegraph.Port;
import de.cau.cs.kieler.rail.Topologie.SpecializedVertices.EOrientation;
import de.cau.cs.kieler.rail.Topologie.SpecializedVertices.Weichenknoten;

public class ToggleSwitchFeature extends AbstractCustomFeature {
 
    public static final String NAME = "ToggleSwitch";
    public int n=0;

	public ToggleSwitchFeature(IFeatureProvider fp) {
        super(fp);
    }
 
	/**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return NAME;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return "Wandelt Weiche von links Weiche nach rechts Weiche oder umgekert";
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canExecute(ICustomContext context) {
        // allow rename if exactly one pictogram element
        // representing a EClass is selected
        boolean ret = false;
        PictogramElement[] pes = context.getPictogramElements();
        if (pes != null && pes.length == 1) {
            Object bo = getBusinessObjectForPictogramElement(pes[0]);
            if (bo instanceof Weichenknoten) {
                ret = true;
            }
        }
        
        System.out.println("canExecute" + (ret ? " true" : " false"));
        //Exception e = new Exception();
    	//e.printStackTrace();
        if(n==1){
        	n=1;
        }
    	n++;
        return ret;
    }
    /**
     * {@inheritDoc}
     */
    public void execute(ICustomContext context) {
    	
    	PictogramElement[] pes = context.getPictogramElements();
        if (pes != null && pes.length == 1) {
            Object bo = getBusinessObjectForPictogramElement(pes[0]);
            if (bo instanceof Weichenknoten) {
            	Weichenknoten weichenknoten = (Weichenknoten) bo;
                String currentName = weichenknoten.getName();
                
                PictogramElement pictogramElement = context.getInnerPictogramElement();
                
                
                //toggeln
                if (pictogramElement instanceof ContainerShape) {
                	ContainerShape cs = (ContainerShape) pictogramElement;
                    for(Anchor anchor : cs.getAnchors()){
                    	if (anchor instanceof BoxRelativeAnchor){
                    		Port port = (Port)getBusinessObjectForPictogramElement(anchor);
                    		BoxRelativeAnchor box = (BoxRelativeAnchor) anchor.getGraphicsAlgorithm().getPictogramElement();
                    		int boxWidth = anchor.getGraphicsAlgorithm().getWidth();
                    		int boxHeight = anchor.getGraphicsAlgorithm().getWidth();
                    		if (port.getName()==EPort.ABZWEIG){
                    			box.setRelativeWidth(Math.abs(1-box.getRelativeWidth()));
                    			//box.setRelativeWidth(0.5);
                    		}
                    	}
                    }
                //toggeln
                Weichenknoten wk=((Weichenknoten)bo);
                if(wk.getAbzweigendeLage()==EOrientation.LINKS){
                	wk.setAbzweigendeLage(EOrientation.RECHTS);
                }else{
                	wk.setAbzweigendeLage(EOrientation.LINKS);
                }
                updatePictogramElement(pictogramElement);
                
            }
        }
       }
    }
}
