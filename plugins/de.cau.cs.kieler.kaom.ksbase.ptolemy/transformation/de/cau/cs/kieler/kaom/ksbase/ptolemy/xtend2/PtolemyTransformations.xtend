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

package de.cau.cs.kieler.kaom.ksbase.ptolemy.xtend2

import de.cau.cs.kieler.kaom.Entity
import de.cau.cs.kieler.kaom.KaomFactory
import java.util.List
import de.cau.cs.kieler.kaom.Link
import de.cau.cs.kieler.kaom.Linkable
import de.cau.cs.kieler.kaom.Port
import de.cau.cs.kieler.core.annotations.StringAnnotation
import de.cau.cs.kieler.core.annotations.AnnotationsFactory
import java.util.LinkedList
import de.cau.cs.kieler.core.annotations.Annotatable
import de.cau.cs.kieler.core.annotations.ReferenceAnnotation
import org.eclipse.emf.ecore.EObject


class PtolemyTransformations {

    //Encapsule the given entities in a single parent
    def encapsulate(List<Entity> entities) {
        //System::out.println("encapsulate")
        val Entity newEntity = KaomFactory::eINSTANCE.createEntity()
        val Entity parent = entities.get(0).eContainer as Entity
        val StringAnnotation ann = AnnotationsFactory::eINSTANCE.createStringAnnotation()
        ann.setName("capsuleType")
        ann.setValue("withoutPorts")
        newEntity.annotations.add(ann)
        parent.childEntities.add(newEntity)
        for (Entity e : entities) {
        	newEntity.childEntities.add(e)
        	parent.childEntities.remove(e)
        	for (Link l : e.incomingLinks) {
        		encapsuleIncoming(parent,l,newEntity)
        	}
        	for (Link l : e.outgoingLinks) {
        		encapsuleOutgoging(parent, l, newEntity, e)
        	}
        	for (Port childPort : e.childPorts) {
        		for (Link l : childPort.incomingLinks) {
        			encapsuleIncoming(parent,l,newEntity)
        		}
        		for (Link l : childPort.outgoingLinks) {
                    encapsuleOutgoging(parent, l, newEntity, e)
                }
        	}
        }
    }
    
    //adds a new link linking from a port of the parent to an inner entity and moves the old link to the port
    def encapsuleIncoming(Entity parent, Link l, Entity newEntity) {
    	if (containsLink(parent, l.source)) {
            val Port port = KaomFactory::eINSTANCE.createPort()
            val Link link = KaomFactory::eINSTANCE.createLink()
            val Linkable target = l.target
            newEntity.childLinks.add(link)
            addReferenceAnnotation(link, "oldSource", port)
            port.outgoingLinks.add(link)
            target.incomingLinks.add(link)
            link.setTarget(target)
            link.setSource(port)
            newEntity.childPorts.add(port)
            addReferenceAnnotation(l, "oldTarget", l.target)
            l.setTarget(port)
            addStringAnnotation(link, "language", "ptolemy")
        }
    }
    
    //adds a new link linking from an inner entity to a port of the parent and moves the old link to the port
    def encapsuleOutgoging(Entity parent, Link l, Entity newEntity, Entity e) {
    	if (containsLink(parent, l.target)) {
            e.childLinks.remove(l)
            newEntity.childLinks.add(l)
            val Port port = KaomFactory::eINSTANCE.createPort()
            val Linkable source = l.source
            val Link link = KaomFactory::eINSTANCE.createLink()
            if (source instanceof Entity) {
                (source as Entity).childLinks.add(link)    
            } else if (source.eContainer instanceof Entity) {
                (source.eContainer as Entity).childLinks.add(link)
            }
            addReferenceAnnotation(link, "oldTarget", port)
            source.outgoingLinks.add(link)
            port.incomingLinks.add(link)
            link.setSource(source)
            link.setTarget(port)
            newEntity.childPorts.add(port)
            addReferenceAnnotation(l, "oldSource", l.source)
            l.setSource(port)
            addStringAnnotation(link, "language", "ptolemy")
        }
    }
    
    //checks whether the given entity contains the linkable 
    def boolean containsLink(Entity parent, Linkable linkable) {
    	if (parent.childEntities.contains(linkable)) {
    		return true
    	}
    	for (Entity child : parent.childEntities) {
    	   if(child.childPorts.contains(linkable)) {
    	   	return true
    	   }
    	}
    	return false
    }
    
    //toggles usage of portentities as input and output
    def toggleInputOutput(Entity entity) {
    	if ((entity.getAnnotation("capsuleType") == null) || ((entity.getAnnotation("capsuleType") as StringAnnotation).value == "withoutPorts")) {
    	    val List<Port> childPorts = new LinkedList<Port>()
            childPorts.addAll(entity.childPorts)
    		for (Port childPort: childPorts) {
    		    val List<Link> outgoingLinks = new LinkedList<Link>()
                outgoingLinks.addAll(childPort.outgoingLinks)
    		    for (Link link: outgoingLinks) {   		        
    		        moveLinkToInput(link, entity.childEntities, entity)
    		    }
    		    
    		    val List<Link> incomingLinks = new LinkedList<Link>()
                incomingLinks.addAll(childPort.incomingLinks)
    		    for (Link link: incomingLinks) {
    		        moveLinkToOutput(link, entity.childEntities, entity)
    		    }
    		}
    		
    		if (entity.getAnnotation("capsuleType") == null) {
    		    val StringAnnotation ann = AnnotationsFactory::eINSTANCE.createStringAnnotation()
                ann.setName("capsuleType")
                ann.setValue("withPorts")
                entity.annotations.add(ann)
            } else {
                (entity.getAnnotation("capsuleType") as StringAnnotation).setValue("withPorts")
            }
    		
    	} else {
    	    val List<Entity> childEntities = new LinkedList<Entity>()
            childEntities.addAll(entity.childEntities)
    	    for (Entity child: childEntities) {    	        
    	        if ((child.getAnnotation("type") != null) && ((child.getAnnotation("type") as StringAnnotation).value == "inputPort")) {
    	            val List<Link> outgoingLinks = new LinkedList<Link>()
                    outgoingLinks.addAll(child.outgoingLinks)
    	            for(Link link : outgoingLinks) {    	                
    	                moveLinkToInputPort(link)
    	            }
    	        } else if ((child.getAnnotation("type") != null) && ((child.getAnnotation("type") as StringAnnotation).value == "outputPort")) {
    	            val List<Link> incomingLinks = new LinkedList<Link>()
                    incomingLinks.addAll(child.incomingLinks)
                    for(Link link : incomingLinks) {
                        moveLinkToOutputPort(link)
                    }
                }
    	    }
    	    if (entity.getAnnotation("capsuleType") == null) {
                val StringAnnotation ann = AnnotationsFactory::eINSTANCE.createStringAnnotation()
                ann.setName("capsuleType")
                ann.setValue("withoutPorts")
                entity.annotations.add(ann)
            } else {
                (entity.getAnnotation("capsuleType") as StringAnnotation).setValue("withoutPorts")
            }
    	}
    }
    
    //moves a link to an outputportentity
    def moveLinkToOutput(Link link, List<Entity> innerEntities, Entity parent) {
        var boolean trigger = false
        for (Entity entity : innerEntities.unmodifiableView) {
                if (entity.childPorts.contains(link.source)) {
                    trigger = true
                }
        }
        if (innerEntities.contains(link.source) || trigger) {
            val outputPort = KaomFactory::eINSTANCE.createEntity()
            if (link.getAnnotation("oldTarget") == null) {
                addReferenceAnnotation(link, "oldTarget", link.target)
            }
            parent.childEntities.add(outputPort)
            link.target.incomingLinks.remove(link)
            outputPort.incomingLinks.add(link)
            link.setTarget(outputPort)
            outputPort.setName("output")
            addStringAnnotation(outputPort, "language", "ptolemy")
            addStringAnnotation(outputPort, "type", "outputPort")
        
        
        }
    }
    
    //moves a link to an inputportentity
    def moveLinkToInput(Link link, List<Entity> innerEntities, Entity parent) {
        var boolean trigger = false
        for (Entity entity : innerEntities) {
                if (entity.childPorts.contains(link.target)) {
                    trigger = true
                }
        }
        if (innerEntities.contains(link.target) || trigger) {
            val inputPort = KaomFactory::eINSTANCE.createEntity()
            if (link.getAnnotation("oldSource") == null) {
              addReferenceAnnotation(link, "oldSource", link.source)
            }
            parent.childEntities.add(inputPort)
            parent.childLinks.remove(link)
            inputPort.childLinks.add(link)
            link.source.outgoingLinks.remove(link)
            inputPort.outgoingLinks.add(link)
            link.setSource(inputPort)
            inputPort.setName("input")
            addStringAnnotation(inputPort, "language", "ptolemy")
            addStringAnnotation(inputPort, "type", "inputPort")
        }
    }
    
    //adds a stringannotation to the given annotatable
    def addStringAnnotation(Annotatable annotatable, String name, String value) {
        val StringAnnotation ann = AnnotationsFactory::eINSTANCE.createStringAnnotation()
        ann.setName(name)
        ann.setValue(value)
        annotatable.annotations.add(ann)
    }
    
    //adds a referenceannotation to the given annotatable
    def addReferenceAnnotation(Annotatable annotatable, String name, EObject value) {
        val ReferenceAnnotation ann = AnnotationsFactory::eINSTANCE.createReferenceAnnotation()
        ann.setName(name)
        ann.setObject(value)
        annotatable.annotations.add(ann)
    }
    
    //moves a link from an inputportentity to real input port of the parent
    def moveLinkToInputPort(Link link) {
        if (link.source instanceof Entity) {
            val Entity oldSource = link.source as Entity
            oldSource.childLinks.remove(link)
            (oldSource.eContainer as Entity).childLinks.add(link)
            oldSource.outgoingLinks.remove(link) ->
            (oldSource.eContainer as Entity).childEntities.remove(oldSource)
        } else {
            val Port oldSource = link.source as Port
            (oldSource.eContainer as Entity).childLinks.add(link)
            oldSource.outgoingLinks.remove(link)
            (oldSource.eContainer as Entity).childEntities.remove(oldSource)
        }
        val Linkable newSource = (((link.getAnnotation("oldSource") as ReferenceAnnotation).object) as Linkable)       
        link.setSource(newSource)
        link.removeAllAnnotations("oldSource")
        newSource.outgoingLinks.add(link)             
    }
    
    //moves link from outputportentity to real outputport of the parent
    def moveLinkToOutputPort(Link link) {
        if (link.target instanceof Entity) {
            val Entity oldTarget = link.target as Entity
            oldTarget.incomingLinks.remove(link)
            (oldTarget.eContainer as Entity).childEntities.remove(oldTarget)
        } else {
            val Port oldTarget = link.target as Port
            oldTarget.incomingLinks.remove(link)
            (oldTarget.eContainer as Entity).childEntities.remove(oldTarget)
        }
        
        
        val newTarget = (((link.getAnnotation("oldTarget") as ReferenceAnnotation).object) as Linkable)
        link.removeAllAnnotations("oldTarget")
        link.setTarget(newTarget)
        newTarget.incomingLinks.add(link) 
        
    }
    
    // moves child entity one hierarchy level higher and removes parent
    def flatten(Entity entity) {
    	val Entity parent = entity.eContainer as Entity
    	val List<Port> childPorts = new LinkedList<Port>()
        childPorts.addAll(entity.childPorts)
    	for (Port childPort: childPorts) {
    	    val List<Link> incomingLinks = new LinkedList<Link>()
            incomingLinks.addAll(childPort.incomingLinks)
    	    for (Link link: incomingLinks) {
    	        link.moveFlattenIncomingLink(entity.childEntities, parent, entity)
    	    }
    	    val List<Link> outgoingLinks = new LinkedList<Link>()
            outgoingLinks.addAll(childPort.outgoingLinks)
    	    for (Link link: outgoingLinks) {
    	        link.moveFlattenOutgoingLink(entity.childEntities,  parent, entity)
    	    }
    	}
        parent.childEntities.addAll(entity.childEntities)
        parent.childLinks.addAll(entity.childLinks)
        //entity.childPorts.removePort()
        parent.childEntities.remove(entity)
    }
    
    // removes redundant links and redirects the others to the fitting targets
    def moveFlattenOutgoingLink(Link link, List<Entity> innerEntities, Entity parent, Entity entity) {
        var boolean trigger = false
        for (Entity e : innerEntities) {
            if (e.childPorts.contains(link.target)) {
                trigger = true
            }
        }
        if (!innerEntities.contains(link.target) && !trigger) {
            val Linkable sourcePort = (link.getAnnotation("oldSource") as ReferenceAnnotation).object as Linkable//.source.incomingLinks.get(0).source            
            link.source.incomingLinks.remove(link)
            link.setSource(sourcePort)
            sourcePort.outgoingLinks.add(link)
            
        } else {
            link.source.outgoingLinks.remove(link)
            link.target.incomingLinks.remove(link)
            (link.eContainer as Entity).childLinks.remove(link)
        }
    }
    
    // removes redundant links and redirects the others to the fitting targets
    def moveFlattenIncomingLink(Link link, List<Entity> innerEntities, Entity parent, Entity entity) {
        var boolean trigger = false
        for (Entity e : innerEntities) {
            if (e.childPorts.contains(link.source)) {
                trigger = true
            }
        }
        if (!innerEntities.contains(link.source) && !trigger) {
            val Linkable targetPort = (link.getAnnotation("oldTarget") as ReferenceAnnotation).getObject as Linkable //link.target.outgoingLinks.get(0).target
            link.target.outgoingLinks.remove(link)
            targetPort.incomingLinks.add(link)
            link.setTarget(targetPort)
        } else {
            link.target.incomingLinks.remove(link)
            link.source.outgoingLinks.remove(link)
            (link.eContainer as Entity).childLinks.remove(link)
        }
    }
    
}