/******************************************************************************
 * KIELER - Kiel Integrated Environment for Layout for the Eclipse RCP
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright ${year} by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 ******************************************************************************/
package de.cau.cs.kieler.dataflow.codegen;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.LogFactoryImpl;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.mwe.emf.Reader;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.part.FileEditorInput;
import org.openarchitectureware.type.emf.EmfMetaModel;
import org.openarchitectureware.workflow.Workflow;
import org.openarchitectureware.workflow.WorkflowContext;
import org.openarchitectureware.workflow.WorkflowContextDefaultImpl;
import org.openarchitectureware.workflow.WorkflowRunner;
import org.openarchitectureware.workflow.issues.Issues;
import org.openarchitectureware.workflow.issues.IssuesImpl;
import org.openarchitectureware.workflow.monitor.NullProgressMonitor;
import org.openarchitectureware.xpand2.Generator;
import org.openarchitectureware.xpand2.output.Outlet;

public class LustreGenerator extends AbstractHandler implements IHandler {

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        IEditorPart ed = HandlerUtil.getActiveEditor(event);
        /* if(ed.isDirty()){
        ed.doSave(new NullProgressMonitor());
        }*/

        FileEditorInput uri = (FileEditorInput) ed.getEditorInput();
        String model = "file:" + uri.getURI().getRawPath();
        if (model.endsWith("_diagram")) {
            model = model.substring(0, model.length() - 8);
        }
        Map<String, String> properties = new HashMap<String, String>();
        Map<String, Object> slotContents = new HashMap<String, Object>();

        /*System.out.println(Activator.getDefault().getStateLocation());
        properties.put("model", model);
        properties.put("src-gen", ".") ;


        WorkflowRunner runner = new WorkflowRunner();  

        String generator = "src/generator.oaw";
        Log logger = LogFactoryImpl.getLog(runner.getClass());
        logger.fatal("Model=" + model);*/
        // Workflow
        Workflow workflow = new Workflow();

        // EMF reader
        Reader emfReader = new Reader();
        emfReader.setUri(model);
        emfReader.setModelSlot("model");

        // Meta model
        EmfMetaModel metaModel = new EmfMetaModel(
                dataflow.DataflowPackage.eINSTANCE);

        // Outlet
        Outlet outlet = new Outlet();
        outlet.setPath(".");

        // Generator
        Generator generator = new Generator();
        generator.addMetaModel(metaModel);
        generator.addOutlet(outlet);

        generator.setExpand("template::Template::main FOR model");

        WorkflowContext wfx = new WorkflowContextDefaultImpl();
        Issues issues = new IssuesImpl();
        NullProgressMonitor monitor = new NullProgressMonitor();

        workflow.addComponent(emfReader);
        workflow.addComponent(generator);
        workflow.invoke(wfx, monitor, issues);

        System.out.print(generator.getLogMessage());

        // System.out.print(issues. .getInfos());
        // System.out.print(issues.getIssues());
        if (issues.getWarnings().length > 0) {
            System.out.print(issues.getWarnings());
        }
        if (issues.hasErrors()) {
            System.out.print(issues.getErrors().toString());
        }
        if (!issues.hasErrors()) {
            System.out.println("Code generation complete");
        } else {
            System.out.println("Code generation failed");
        }

        return null;
    }

}
