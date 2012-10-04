/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kaom.text.ui;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;

import de.cau.cs.kieler.core.annotations.Annotatable;
import de.cau.cs.kieler.core.annotations.Annotation;
import de.cau.cs.kieler.core.annotations.ImportAnnotation;
import de.cau.cs.kieler.core.annotations.StringAnnotation;
import de.cau.cs.kieler.core.annotations.TypedStringAnnotation;
import de.cau.cs.kieler.core.model.handlers.ConvertModelHandler;
import de.cau.cs.kieler.kaom.Entity;
import de.cau.cs.kieler.kaom.Link;
import de.cau.cs.kieler.kaom.Linkable;
import de.cau.cs.kieler.kaom.Port;
import de.cau.cs.kieler.kaom.Relation;

/**
 * A specialized conversion command handler that transforms the model prior to saving it
 * in the new file.
 *
 * @author msp
 * @kieler.ignore (excluded from review process)
 */
public class ConvertKaomModelHandler extends ConvertModelHandler {
    
    /** identifier prefix for entities. */
    private static final String ID_PREFIX_ENTITY = "__e";
    /** identifier prefix for relations. */
    private static final String ID_PREFIX_RELATION = "__r";
    /** identifier prefix for ports. */
    private static final String ID_PREFIX_PORT = "__p";
    /** identifier prefix for annotations. */
    private static final String ID_PREFIX_ANNOT = "__a";
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void transform(final EObject model) {
        usedIds.clear();
        nr = 0;
        if (model instanceof Entity) {
            process((Entity) model);
        }
    }

    /** the number used for identification. */
    private int nr;
    /** set of already used identifiers. */
    private Set<String> usedIds = new HashSet<String>();
    
    /**
     * Process the given entity.
     * 
     * @param entity an entity
     */
    private void process(final Entity entity) {
        checkLinkableId(entity);
        checkAnnotationsId(entity);
        for (Port port : entity.getChildPorts()) {
            checkLinkableId(port);
            checkAnnotationsId(port);
        }
        for (Relation relation : entity.getChildRelations()) {
            checkLinkableId(relation);
            checkAnnotationsId(relation);
        }
        for (Entity child : entity.getChildEntities()) {
            process(child);
        }
        for (Link link : entity.getChildLinks()) {
            checkAnnotationsId(link);
        }
    }
    
    /**
     * Check the identifier of the given linkable and correct it if necessary.
     * 
     * @param linkable a linkable
     */
    private void checkLinkableId(final Linkable linkable) {
        String id = linkable.getId();
        if (id == null || id.length() == 0 || usedIds.contains(id)) {
            if (linkable instanceof Relation) {
                id = ID_PREFIX_RELATION;
            } else if (linkable instanceof Port) {
                id = ID_PREFIX_PORT;
            } else {
                id = ID_PREFIX_ENTITY;
            }
            id += nextNumber();
        } else {
            id = checkId(id);
        }
        linkable.setId(id);
        usedIds.add(id);
    }
    
    /**
     * Check the identifiers of the annotations and correct them if necessary.
     * 
     * @param annotatable an annotatable
     */
    private void checkAnnotationsId(final Annotatable annotatable) {
        for (Annotation annotation : annotatable.getAnnotations()) {
            String id = annotation.getName();
            if (id == null || id.length() == 0) {
                id = ID_PREFIX_ANNOT + nextNumber();
            } else {
                id = checkId(id);
            }
            annotation.setName(id);
            usedIds.add(id);
            // recursively check the sub-annotations
            checkAnnotationsId(annotation);
            
            // check the value of special annotations
            if (annotation instanceof StringAnnotation) {
                StringAnnotation stringAnnot = (StringAnnotation) annotation;
                if (stringAnnot.getValue() == null || stringAnnot.getValue().length() == 0) {
                    stringAnnot.setValue("_");
                }
                if (stringAnnot instanceof TypedStringAnnotation) {
                    TypedStringAnnotation tstringAnnot = (TypedStringAnnotation) stringAnnot;
                    if (tstringAnnot.getType() == null || tstringAnnot.getType().length() == 0) {
                        tstringAnnot.setType("_");
                    }
                }
            } else if (annotation instanceof ImportAnnotation) {
                ImportAnnotation importAnnot = (ImportAnnotation) annotation;
                if (importAnnot.getImportURI() == null || importAnnot.getImportURI().length() == 0) {
                    importAnnot.setImportURI("_");
                }
            }
        }
    }
    
    /**
     * Calculate the next number for identification.
     * 
     * @return a unique number for identification
     */
    private int nextNumber() {
        return nr++;
    }
    
    /**
     * Check for illegal characters in the given identifier and replace them.
     * 
     * @param id an identifier
     * @return a checked identifier with only legal characters
     */
    private String checkId(final String id) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < id.length(); i++) {
            char c = id.charAt(i);
            if (c == '_' || (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')
                    || (i > 0 && c >= '0' && c <= '9')) {
                builder.append(c);
            } else {
                builder.append('_');
            }
        }
        return builder.toString();
    }

}
