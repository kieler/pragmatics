/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.formats.svg;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.Collections;

import org.apache.batik.svggen.SVGGraphics2D;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.preferences.ScopedPreferenceStore;
import org.eclipse.ui.statushandlers.StatusManager;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.service.formats.IGraphTransformer;
import de.cau.cs.kieler.kiml.service.formats.TransformationData;

/**
 * Test class for the SVG exporter.
 *
 * @author msp
 */
public class Test extends AbstractHandler {

    private static IPreferenceStore preferenceStore;
    
    /**
     * {@inheritDoc}
     */
    public Object execute(ExecutionEvent event) throws ExecutionException {
        
        if (preferenceStore == null) {
            preferenceStore = new ScopedPreferenceStore(new InstanceScope(), "de.cau.cs.kieler.kiml.formats.svg");
        }
        
        try {
            FileDialog fileDialog = new FileDialog(HandlerUtil.getActiveShell(event));
            fileDialog.setText("Select KGraph File");
            String lastFile = preferenceStore.getString("test.lastFile");
            if (lastFile.length() > 0) {
                fileDialog.setFileName(lastFile);
            }
            String inputFileName = fileDialog.open();
            if (inputFileName != null) {
                preferenceStore.setValue("test.lastFile", inputFileName);
                
                ResourceSet resourceSet = new ResourceSetImpl();
                URI inputFileURI = URI.createFileURI(new File(inputFileName).getAbsolutePath());
                Resource resource = resourceSet.createResource(inputFileURI);
                resource.load(Collections.EMPTY_MAP);
                if (resource.getContents().size() != 1) {
                    throw new RuntimeException("The input file must contain exactly one KGraph model.");
                }
                EObject content = resource.getContents().get(0);
                if (!(content instanceof KNode)) {
                    throw new RuntimeException("The input model must be an instance of KNode.");
                }
                
                SvgHandler svgHandler = new SvgHandler();
                IGraphTransformer<KNode, SVGGraphics2D> exporter = svgHandler.getExporter();
                TransformationData<KNode, SVGGraphics2D> data = new TransformationData<KNode, SVGGraphics2D>();
                data.setSourceGraph((KNode) content);
                exporter.transform(data);
                String output = svgHandler.serialize(data.getTargetGraphs().get(0));
                
                URI outputFileURI = inputFileURI.trimFileExtension().appendFileExtension("svg");
                Writer outputFileWriter = new FileWriter(outputFileURI.toFileString());
                outputFileWriter.write(output);
                outputFileWriter.close();
                
                MessageDialog.openInformation(HandlerUtil.getActiveShell(event), "Success",
                        "The SVG file was successfully created in " + outputFileURI.toFileString());
                
            }
        } catch (Exception e) {
            Status status = new Status(Status.ERROR, "de.cau.cs.kieler.kiml.formats.svg",
                    "Error while performing SVG export.", e);
            StatusManager.getManager().handle(status, StatusManager.SHOW);
        }
        
        return null;
    }

}
