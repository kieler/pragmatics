package de.cau.cs.kieler.ksbase.transformations;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.mwe.internal.core.Workflow;
import org.eclipse.emf.mwe.utils.StandaloneSetup;

import org.eclipse.xtend.XtendComponent;
import org.eclipse.xtend.typesystem.emf.EmfMetaModel;

@SuppressWarnings("restriction")
public class KielerWorkflow extends Workflow {

    XtendComponent xtendComponent;

    /**
     * Creates and initializes an oAW Workflow
     * @param modelURI URI of model instance
     * @param operation Xtend function name
     * @param modelSelections selected model elements
     */
    public KielerWorkflow(String operation, String fileName, EPackage basePacakge) {
        super();

        StandaloneSetup setup = new StandaloneSetup();
        setup.addRegisterGeneratedEPackage("de.cau.cs.kieler.synccharts.SyncchartsPackage");
        this.addBean(setup);

        // We are using the XtendComponent,
        xtendComponent = new XtendComponent();
        // with an EMFMetaMetaModel,
        EmfMetaModel emfmodel;
        // and the SyncchartsMetamodel, loaded from the SyncchartsPackage
        
        emfmodel = new EmfMetaModel(
                basePacakge);
        
        // Set metaModel-Slot
        xtendComponent.addMetaModel(emfmodel);
        
        String value = "de/cau/cs/kieler/synccharts/diagram/features/transformations/"+fileName+"::"+operation+"(model)";
        
        xtendComponent
                .setInvoke(value);

        // Don't forget to add the components to the workflow !!
        this.addComponent(xtendComponent);
    }
    
    public void setInvokeValue(String  fileName, String operation, String[] modelSelections) {
    	String value = "de/cau/cs/kieler/synccharts/diagram/features/transformations/"+fileName+"::"+operation+"(";
        
        for (int i = 0; i < modelSelections.length; ++i) {
            value += "model"+ modelSelections[i];
            if ( i < (modelSelections.length-1) ) {
                value += ",";
            }
        }
        
        value += ")";
        
        xtendComponent
                .setInvoke(value);
    }
}
