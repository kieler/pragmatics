/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kaom.graphiti.util;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.mm.pictograms.Diagram;

import de.cau.cs.kieler.kaom.Entity;
import de.cau.cs.kieler.kaom.KaomFactory;

/**
 * 
 * @author atr
 * Checks and creates a top entity
 */
public class TopParentEntity {
    
    
    private static Entity parentEntity = null;
    
    /**
     * 
     * @param diag .
     * @return
     * creates and returns the parent Entity
     */
    public static Entity createParentEntity(final Diagram diag) {
        if (diag == null) {
            System.out.println("This is not possible");
            }
        if (parentEntity == null) {
        List<EObject> contents = diag.eResource().getContents();
      /*  for (EObject obj : contents) {
            if (obj instanceof Entity) {
                parentEntity = (Entity) obj;
             //   topEntityFlag = false;
                break;
            }
        }*/
     //   if(parentEntity==null) {
            parentEntity = KaomFactory.eINSTANCE.createEntity();
            contents.add(parentEntity);
           
       
        }
        return parentEntity;     
                
    }
    
    /**
     * 
     * @return
     * returns the parent entity
     */
    public static Entity getParentEntity() {
        return parentEntity;
    }
  
}
    
    

