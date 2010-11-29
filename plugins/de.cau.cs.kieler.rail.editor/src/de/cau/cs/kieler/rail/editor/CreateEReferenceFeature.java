package de.cau.cs.kieler.rail.editor;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateConnectionContext;
import org.eclipse.graphiti.features.context.impl.AddConnectionContext;
import org.eclipse.graphiti.features.impl.AbstractCreateConnectionFeature;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;

public class CreateEReferenceFeature extends
       AbstractCreateConnectionFeature {
 
    public CreateEReferenceFeature (IFeatureProvider fp) {
        // provide name and description for the UI, e.g. the palette
        super(fp, "Verbindung", "Verbindung erstellen");
    }
 
    public boolean canCreate(ICreateConnectionContext context) {
        // return true if both anchors belong to an EClass
        // and those EClasses are not identical
        EClass source = getEClass(context.getSourceAnchor());
        EClass target = getEClass(context.getTargetAnchor());
        if (source != null && target != null && source != target) {
            return true;
        }
        return false;
    }
 
    public boolean canStartConnection(ICreateConnectionContext context) {
        // return true if start anchor belongs to a EClass
        if (getEClass(context.getSourceAnchor()) != null) {
            return true;
        }
        return false;
    }
 
    public Connection create(ICreateConnectionContext context) {
        Connection newConnection = null;
 
        // get EClasses which should be connected
        EClass source = getEClass(context.getSourceAnchor());
        EClass target = getEClass(context.getTargetAnchor());
 
        if (source != null && target != null) {
            // create new business object
            EReference eReference = createEReference(source, target);
            // add connection for business object
            AddConnectionContext addContext =
                new AddConnectionContext(context.getSourceAnchor(), context
                    .getTargetAnchor());
            addContext.setNewObject(eReference);
            newConnection =
                (Connection) getFeatureProvider().addIfPossible(addContext);
        }
       
        return newConnection;
    }
 
    /**
     * Returns the EClass belonging to the anchor, or null if not available.
     */
    private EClass getEClass(Anchor anchor) {
        if (anchor != null) {
            Object object =
                getBusinessObjectForPictogramElement(anchor.getParent());
            if (object instanceof EClass) {
                return (EClass) object;
            }
        }
        return null;
    }
 
    /**
    * Creates a EReference between two EClasses.
    */
    private EReference createEReference(EClass source, EClass target) {
        EReference eReference = EcoreFactory.eINSTANCE.createEReference();
        eReference.setName("new EReference");
        eReference.setEType(target);
        eReference.setLowerBound(0);
        eReference.setUpperBound(1);
        source.getEStructuralFeatures().add(eReference);
        return eReference;
   }
}
