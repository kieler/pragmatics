/******************************************************************************
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 * 
 *****************************************************************************/
package de.cau.cs.kieler.ksbase.core;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.mwe.internal.core.Workflow;
import org.eclipse.xtend.XtendComponent;
import org.eclipse.xtend.typesystem.emf.EcoreUtil2;
import org.eclipse.xtend.typesystem.emf.EmfMetaModel;

/**
 * An extension of a MWE default workflow, used to perform custom
 * initializations.
 * 
 * @author Michael Matzen - mim AT informatik.uni-kiel.de
 * 
 */
@SuppressWarnings("restriction")
public class KielerWorkflow extends Workflow {

    private XtendComponent xtendComponent;

    /**
     * Creates and initializes the workflow.
     * 
     * @param operation Xtend function name
	 * @param basePackage The meta model package class to use
     * @param modelSelection selected model elements
     */
    public KielerWorkflow(final String operation, final String fileName, final String basePackage, final String modelSelection) {
        super();

        // We are using the XtendComponent,
        xtendComponent = new XtendComponent();
        // with an EMFMetaMetaModel,
        EmfMetaModel emfmodel;
        
        //Load the EPackage class by using EcoreUtils 
        EPackage pack = EcoreUtil2.getEPackageByClassName(basePackage);
        //create EMFMetaModel with the given EPackage
        emfmodel = new EmfMetaModel(pack);

        // Set metaModel-Slot
        xtendComponent.addMetaModel(emfmodel);
        //Create transformation value
        String value = fileName + "::" + operation + "(" + modelSelection + ")";

        xtendComponent.setInvoke(value);

        // Don't forget to add the components to the workflow !!
        this.addComponent(xtendComponent);

    }
}
