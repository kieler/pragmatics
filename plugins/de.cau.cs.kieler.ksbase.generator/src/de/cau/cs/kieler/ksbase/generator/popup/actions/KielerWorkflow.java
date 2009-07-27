package de.cau.cs.kieler.ksbase.generator.popup.actions;

import org.eclipse.emf.mwe.internal.core.Workflow;
import org.eclipse.emf.mwe.utils.StandaloneSetup;
import org.eclipse.xpand2.Generator;
import org.eclipse.xpand2.output.Outlet;
import org.eclipse.xtend.typesystem.emf.EmfMetaModel;
import org.eclipse.xtext.MweReader;

import de.cau.cs.kieler.ksbase.FeatureDefinitionStandaloneSetup;
import de.cau.cs.kieler.ksbase.featureDefinition.FeatureDefinitionPackage;

@SuppressWarnings("restriction")
public class KielerWorkflow extends Workflow {

    /**
     * Creates and initializes an oAW Workflow
     * @param sbfURI URI to feature file
     * @param genPath Output path for generated files
     */
    public KielerWorkflow(String sbfURI, String genPath) {
        super();
        System.out.println("Gen-Path: "+genPath);
        StandaloneSetup setup = new StandaloneSetup();
        
        setup.addRegisterGeneratedEPackage("de.cau.cs.kieler.ksbase.featureDefinition.FeatureDefinitionPackage");
        setup.setPlatformUri("..");
        this.addBean(setup);
                
        // EMFReader
        org.eclipse.xtext.MweReader reader;
        // EMFReader
        reader = new MweReader();
        reader.setUri(sbfURI);
        reader.setOutputSlot("model");
        reader.setRegister( new FeatureDefinitionStandaloneSetup() );
        
        // We are using the XpandComponent,
        Generator generator = new Generator();
        
        // with an EMFMetaMetaModel,
        EmfMetaModel emfmodel;
        // and the SyncchartsMetamodel, loaded from the SyncchartsPackage
        emfmodel = new EmfMetaModel(
                FeatureDefinitionPackage.eINSTANCE);
        
        generator.addMetaModel(emfmodel);
        generator.setFileEncoding("UTF-8");
        generator.setExpand("templates::Template::main FOR model");
        generator.addOutlet(new Outlet(genPath));
        
        // Don't forget to add the components to the workflow !!
        this.addComponent(reader);
        this.addComponent(generator);
    }
}


