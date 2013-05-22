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

package de.cau.cs.kieler.kaom.klighd.ptolemy;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.kaom.Entity;
import de.cau.cs.kieler.klighd.LightDiagramServices;
import de.cau.cs.kieler.klighd.effects.KlighdUpdateDiagramEffect;
import de.cau.cs.kieler.klighd.krendering.SimpleUpdateStrategy;

/**
 * Handler for kaom klight view menu button.
 * @author ckru
 *
 */
public class OpenKaomKlighdViewHandler extends AbstractHandler implements IHandler {

    /**
     * {@inheritDoc}
     */
    public Object execute(final ExecutionEvent event) throws ExecutionException {
        String commandid = event.getCommand().getId();
        if (commandid.endsWith("openkaomklighd")) {
            ISelection selection = HandlerUtil.getCurrentSelection(event);
            if (selection instanceof IStructuredSelection) {
                Iterator<?> iter = ((IStructuredSelection) selection).iterator();
                while (iter.hasNext()) {
                    Object o = iter.next();
                    IFile file = null;
                    if (o instanceof IFile) {
                        file = (IFile) o;
                        EList<EObject> objects = loadModel(file);
                        EObject obj = objects.get(0);
                        if (obj instanceof Entity) {
                            KlighdUpdateDiagramEffect effect = new KlighdUpdateDiagramEffect(file.getName(), obj);
                            effect.setProperty(LightDiagramServices.REQUESTED_TRANSFORMATIONS,
                                    Lists.newArrayList(KaomKrenderingTransformation.ID));
                            effect.setProperty(LightDiagramServices.REQUESTED_UPDATE_STRATEGY,
                                    SimpleUpdateStrategy.ID);
                            effect.execute();
                        }
                    }
                }
            } 
        }
        return null;
    }
    
    /**
     * Load emf model from a given file.
     * @param file the emf model file
     * @return the loaded model
     */
    private EList<EObject> loadModel(final IFile file) {
            try {
                ResourceSet resourceSet = new ResourceSetImpl();
                Resource resource = resourceSet.getResource(URI.createPlatformResourceURI(
                                file.getFullPath().toString(), true), true);
                final Map<String, Boolean> options = new HashMap<String, Boolean>();
                options.put(XMLResource.OPTION_DECLARE_XML, Boolean.TRUE);
                
                resource.load(options);
                
                
                
                EList<EObject> models = new BasicEList<EObject>(resource.getContents());
                //recursiveSetNamesIfUnset(models);
                return models;
        } catch (final Exception e) {
            return null;
        }
    }
    
}
