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
package de.cau.cs.kieler.kaom.importer.ptolemy;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xml.type.AnyType;
import org.ptolemy.moml.ConfigureType;
import org.ptolemy.moml.DocumentRoot;
import org.ptolemy.moml.EntityType;
import org.ptolemy.moml.PropertyType;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.core.annotations.Annotatable;
import de.cau.cs.kieler.core.annotations.Annotation;
import de.cau.cs.kieler.core.annotations.AnnotationsFactory;
import de.cau.cs.kieler.core.annotations.AnnotationsPackage;
import de.cau.cs.kieler.core.annotations.ReferenceAnnotation;
import de.cau.cs.kieler.core.annotations.TypedStringAnnotation;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kaom.Entity;


/**
 * Handles Ptolemy annotations and their associations with actors. For each model
 * transformation, a new instance must be created and the {@link #handleAnnotations()}
 * method called.
 * 
 * <p>In Ptolemy, certain annotations in a model are like comments in source code.
 * There are two ways how they can be represented in MOML:</p>
 * 
 * <ol>
 *   <li>Using a property of type "ptolemy.vergil.kernel.attributes.TextAttribute"
 *       with another property named "text", as follows:
 *     <pre>
 *        &lt;property
 *              name="Annotation2"
 *              class="ptolemy.vergil.kernel.attributes.TextAttribute"&gt;
 *              
 *              &lt;property
 *                      name="text"
 *                      class="ptolemy.kernel.util.StringAttribute"
 *                      value="This is my annotation's text."&gt;
 *              &lt;/property&gt;
 *              &lt;property
 *                      name="_location"
 *                      class="ptolemy.kernel.util.Location"
 *                      value="[140.0, 440.0]"&gt;
 *              &lt;/property&gt;
 *        &lt;/property&gt;
 *     </pre>
 *   </li>
 *   
 *   <li>Using a property of type "ptolemy.kernel.util.Attribute" with an SVG
 *       as its "_iconDescription" property, as follows:
 *     <pre>
 *        &lt;property
 *              name="annotation3"
 *              class="ptolemy.kernel.util.Attribute"&gt;
 *              
 *              &lt;property
 *                      name="_iconDescription"
 *                      class="ptolemy.kernel.util.SingletonConfigurableAttribute"&gt;
 *                      
 *                      &lt;configure&gt;&lt;svg&gt;
 *                              &lt;text x="20" y="20" style="..."&gt;
 *                                      This is my annotation's text.
 *                              &lt;/text&gt;
 *                      &lt;/svg&gt;&lt;/configure&gt;
 *              &lt;/property&gt;
 *              &lt;property
 *                      name="_location"
 *                      class="ptolemy.kernel.util.Location"
 *                      value="[325.0, 10.0]"&gt;
 *              &lt;/property&gt;
 *        &lt;/property&gt;
 *     </pre>
 *   </li>
 * </ol>
 *      
 * <p>It's the latter version that causes a whole lot of problems when transforming
 * the Ptolemy model into a KAOM model. The {@code configure} element is a mixed
 * element, which means that it can contain anything, not just XML. However, it
 * does contain XML (usually an {@code svg} element and its children), which
 * disturbs the quiet peace of the parser. (which, in turn, disturbs my quiet peace.)
 * The {@code configure} element and its children are then dropped by the parser
 * during the transformation and are added to a list of unknown features. Recovering
 * such comments is one of the two tasks of this handler.</p>
 * 
 * <p>The second task is recognizing links between comments and model elements. Such
 * links indicate that a comment is providing additional information for a model
 * element, and should be preserved. In the MoML file, such a link is represented by
 * the following property:</p>
 * 
 * <pre>
 *   &lt;property
 *         name="_location"
 *         class="ptolemy.kernel.util.RelativeLocation"
 *         value="[-195.0, -80.0]"&gt;
 *         
 *         &lt;property
 *               name="relativeTo"
 *               class="ptolemy.kernel.util.StringAttribute"
 *               value="Const"/&gt;
 *         &lt;property
 *               name="relativeToElementName"
 *               class="ptolemy.kernel.util.StringAttribute"
 *               value="entity"/&gt;
 *   &lt;/property&gt;
 * </pre>
 * 
 * <p>That is, the annotation's {@code _location} property is extended by two additional
 * properties that define that the location is to be interpreted relative to a given
 * model element. So far, we only support linking comments to entities, but that could
 * well be changed.</p>
 * 
 * <p>The handler is executed on a Ptolemy model once the model was transformed and
 * optimized. It takes a look at the transformed model and starts by trying to recover
 * lost comments, that is, comments of the second of the above styles. If it finds any,
 * it adds a {@code TypedStringAnnotation} to the transformed model element that contains
 * the annotation's text, thus preserving the comment's text even in the face of severely
 * hopeless circumstances. Hurray!</p>
 * 
 * <p>After that's done, the model is traversed in an attempt to link annotations to the
 * model elements they are most likely annotating. There are two cases here:</p>
 * 
 * <ol>
 *   <li>The model contains explicit links between at least one annotation and model
 *   element. In this case, we assume that the model developer added all the links he
 *   wants explicitly and only transform such links into the KAOM way of representing
 *   them.</li>
 *   
 *   <li>The model does not contain any explicit such link. In this case, a heuristic
 *   is applied to each comment annotation, trying to find the entity it is probably
 *   annotating. The heuristic simply looks for the nearest entity and checks if the
 *   distance is within a certain threshold value.
 * </ol>
 * 
 * <p>The link between a comment and a model element is represented in the KAOM model
 * by means of a {@code ReferenceAnnotation} named {@code attachedTo}. This annotation
 * is added to all comments, whether they are actually attached to anything or not. Thus,
 * the presence of this annotation indicates whether an annotation represents a comment
 * or not.</p>
 * 
 * <p>This is still kind of experimental. It does work, but the heuristic is quite
 * simpllistic and doesn't always give correct results.</p>
 * 
 * @author cds
 */
public class PtolemyAnnotationHandler {
    
    // CONSTANTS
    /**
     * Name of the {@code attachedTo} annotation.
     */
    private static final String ANNOTATIONS_ATTACHMENT = "attachedTo"; //$NON-NLS-1$
    
    /**
     * Name of the {@code _iconDescription} annotation.
     */
    private static final String ANNOTATIONS_ICON_DESCRIPTION = "_iconDescription"; //$NON-NLS-1$
    
    /**
     * Name of the {@code _location} annotation.
     */
    private static final String ANNOTATION_LOCATION = "_location"; //$NON-NLS-1$
    
    /**
     * Name of the {@code relativeTo} annotation.
     */
    private static final String ANNOTATION_RELATIVE_TO = "relativeTo"; //$NON-NLS-1$
    
    /**
     * Name of the {@code relativeToElementName} annotation.
     */
    private static final String ANNOTATION_RELATIVE_TO_ELEMENT_NAME =
            "relativeToElementName"; //$NON-NLS-1$ 
    
    /**
     * Name of the {@code text} annotation.
     */
    private static final String ANNOTATIONS_TEXT = "text"; //$NON-NLS-1$
    
    /**
     * The element name identifying Ptolemy entities.
     */
    private static final String ELEMENT_NAME_ENTITY = "entity"; //$NON-NLS-1$
    
    /**
     * Tag of an {@code svg} element.
     */
    private static final String ELEM_SVG = "svg"; //$NON-NLS-1$
    
    /**
     * Tag of a {@code text} element.
     */
    private static final String ELEM_TEXT = "text"; //$NON-NLS-1$
    
    /**
     * Type name of an attribute in Ptolemy.
     */
    private static final String TYPE_ATTRIBUTE = "ptolemy.kernel.util.Attribute"; //$NON-NLS-1$
    
    /**
     * Type name of a string attribute in Ptolemy.
     */
    private static final String TYPE_STRING_ATTRIBUTE =
        "ptolemy.kernel.util.StringAttribute"; //$NON-NLS-1$
    
    /**
     * Type name of a text attribute in Ptolemy.
     */
    private static final String TYPE_TEXT_ATTRIBUTE =
        "ptolemy.vergil.kernel.attributes.TextAttribute"; //$NON-NLS-1$
    
    
    /**
     * The highest distance for annotations to be eligible for attachment to an
     * entity. If the distance between an annotation and an entity is higher than
     * this value, the annotation is considered unrelated to that entity and won't
     * be attached to it.
     */
    private static final double MAX_ATTACHMENT_DISTANCE = 10000.0;
    
    // VARIABLES
    /**
     * Root of the Ptolemy model.
     */
    private DocumentRoot ptolemyModel = null;
    
    /**
     * Root of the KAOM model.
     */
    private Entity kaomModel = null;
    
    /**
     * Map of unknown features. These are elements that the input reader couldn't
     * make sense of.
     */
    private Map<EObject, AnyType> unknownFeatures = new HashMap<EObject, AnyType>();
    
    /**
     * A list of attachment annotations for comments that were generated from explicit
     * attachments done by the Ptolemy model developer. If any of these are present,
     * the distance heuristic determining comment attachments is disabled. These
     * annotations are generated while traversing the model tree, and applied once that
     * is over with.
     */
    private List<Pair<Annotatable, Annotation>> explicitAttachments = Lists.newArrayList();
    
    /**
     * A list of attachment annotations for comments that were generated using the
     * distance heuristic. These annotations are generated while traversing the model tree,
     * and applied once that is over with if the list of explicit attachments is empty.
     */
    private List<Pair<Annotatable, Annotation>> heuristicAttachments = Lists.newArrayList();
    
    /**
     * If {@code true}, the attachment heuristic is disabled once explicit attachments are
     * found.
     */
    private boolean heuristicsOverride;
    
    
    /**
     * Creates a new annotation handler for the given XML resource of the input model, the Ptolemy input
     * model, and the KAOM output model.
     * 
     * @param ptolemyModelResource XML resource holding the input Ptolemy model.
     * @param ptolemyModel the input Ptolemy model.
     * @param kaomModel the transformed KAOM model.
     * @param heuristicsOverride if {@code true}, the attachment heuristic is disabled once explicit
     *                           attachments are found.
     */
    public PtolemyAnnotationHandler(final XMLResource ptolemyModelResource,
            final DocumentRoot ptolemyModel, final Entity kaomModel, final boolean heuristicsOverride) {
        
        unknownFeatures.putAll(ptolemyModelResource.getEObjectToExtensionMap());
        this.ptolemyModel = ptolemyModel;
        this.kaomModel = kaomModel;
        this.heuristicsOverride = heuristicsOverride;
    }
    
    
    /**
     * Starts converting annotations and attaching them to the entity they are probably annotating.
     */
    public void handleAnnotations() {
        traverseModelTree(kaomModel);
        applyAttachmentAnnotations();
    }
    
    
    /**
     * Recursively iterates through the model tree looking for annotations
     * representing comments. Such annotations are marked as comments and
     * an attempt is made to attach them to a model element.
     * 
     * @param node the current node being examined.
     */
    private void traverseModelTree(final Entity node) {
        // Look at the entity's annotations
        for (Annotation annotation : node.getAnnotations()) {
            if (annotation instanceof TypedStringAnnotation) {
                examineAnnotation(node, (TypedStringAnnotation) annotation);
            }
        }
        
        // Recursively look at the entity's children
        for (Entity child : node.getChildEntities()) {
            traverseModelTree(child);
        }
    }
    
    /**
     * Determines whether or not the given annotation represents a comment. If
     * so, normalizes the annotation (see item 1 of the class description) and
     * tries to attach it to an entity. An attempt is also made to determine
     * which model object to connect the comment to.
     * 
     * @param parent the annotation's parent entity.
     * @param annotation the annotation to look at.
     */
    private void examineAnnotation(final Entity parent, final TypedStringAnnotation annotation) {
        boolean isComment = false;
        
        // Check if the annotation represents a comment
        if (annotation.getType().equals(TYPE_TEXT_ATTRIBUTE)) {
            isComment = true;
        } else if (annotation.getType().equals(TYPE_ATTRIBUTE)) {
            // For plain attributes with an _iconDescription annotation, we first
            // need to find the original element in the input model. Then, we need
            // to figure out if that element contains unparsed <configure> children
            // that define an SVG drawing.
            
            // _iconDescription annotation?
            Annotation iconDescription = annotation.getAnnotation(ANNOTATIONS_ICON_DESCRIPTION);
            if (iconDescription != null) {
                // Find the original property
                PropertyType originalIconDescription = findOriginalProperty(iconDescription);
                
                if (originalIconDescription != null) {
                    // Try to extract the annotation's text
                    isComment = convertAnnotation(annotation, originalIconDescription);
                }
            }
        }
        
        if (isComment) {
            // Try to attach the comment annotation to an entity
            attemptCommentAttachment(annotation);
        }
    }


    ///////////////////////////////////////////////////////////////////////////////
    // Annotation Conversion Handling
    
    /**
     * Returns the property from the original input model that the given annotation
     * was transformed from.
     * 
     * @param annotation the annotation whose original property to find.
     * @return the property, or {@code null} if it couldn't be found.
     */
    private PropertyType findOriginalProperty(final Annotation annotation) {
        // Find the annotation's full path
        List<String> annotationPath = getFullPath(annotation);
        if (annotationPath == null) {
            return null;
        }
        
        // Iterate through the path and try to find the desired element
        EObject currentElement = ptolemyModel;
        for (String pathElement : annotationPath) {
            // Check what kind of element we're currently looking at
            if (currentElement instanceof DocumentRoot) {
                // Here, we can only look at the document root's child entity
                DocumentRoot specificElement = (DocumentRoot) currentElement;
                EntityType candidate = specificElement.getEntity();
                
                if (candidate != null && candidate.getName().equals(pathElement)) {
                    currentElement = candidate;
                    continue;
                } else {
                    // Error condition
                    return null;
                }
            } else if (currentElement instanceof EntityType) {
                // For entities, we need to look at child entities and child properties
                EntityType specificElement = (EntityType) currentElement;
                EObject foundElement = null;
                
                for (EntityType childEntity : specificElement.getEntity()) {
                    if (childEntity.getName().equals(pathElement)) {
                        foundElement = childEntity;
                        break;
                    }
                }
                
                if (foundElement == null) {
                    for (PropertyType childProperty : specificElement.getProperty()) {
                        if (childProperty.getName().equals(pathElement)) {
                            foundElement = childProperty;
                            break;
                        }
                    }
                }
                
                currentElement = foundElement;
                if (currentElement == null) {
                    // Error condition - we should have found a matching child by now
                    return null;
                }
            } else if (currentElement instanceof PropertyType) {
                // For properties, we need to look at child properties
                PropertyType specificElement = (PropertyType) currentElement;
                EObject foundElement = null;

                for (PropertyType childProperty : specificElement.getProperty()) {
                    if (childProperty.getName().equals(pathElement)) {
                        foundElement = childProperty;
                        break;
                    }
                }
                
                currentElement = foundElement;
                if (currentElement == null) {
                    // Error condition - we should have found a matching child by now
                    return null;
                }
            }
        }
        
        if (currentElement instanceof PropertyType) {
            return (PropertyType) currentElement;
        } else {
            return null;
        }
    }
    
    /**
     * Returns a list with the complete path from the model's root element to
     * the given model element.
     * 
     * @param entity element whose path to return.
     * @return the path or {@code null} if the path couldn't be determined.
     */
    private List<String> getFullPath(final Entity entity) {
        EObject container = entity.eContainer();
        
        if (container == null) {
            // This is where the recursion ends; return a list with just this
            // entity's name, if it's != null
            List<String> result = new ArrayList<String>();
            if (entity.getName() != null) {
                result.add(entity.getName());
            }
            
            return result;
        } else if (container instanceof Entity) {
            List<String> result = getFullPath((Entity) container);
            
            if (result != null) {
                result.add(entity.getName());
                return result;
            }
        }
        
        return null;
    }

    /**
     * Returns a list with the complete path from the model's root element to
     * the given model element.
     * 
     * @param annotation element whose path to return.
     * @return the path or {@code null} if the path couldn't be determined.
     */
    private List<String> getFullPath(final Annotation annotation) {
        EObject container = annotation.eContainer();
        List<String> result = null;
        
        if (container instanceof Entity) {
            result = getFullPath((Entity) container);
        } else if (container instanceof Annotation) {
            result = getFullPath((Annotation) container);
        }
        
        if (result != null) {
            result.add(annotation.getName());
            return result;
        }
        
        return null;
    }
    
    /**
     * Checks if the given icon description property defines an SVG image that only
     * contains text. If so, the defined text is added to the given annotation using
     * an annotation named {@code text}.
     * 
     * @param annotation the annotation to convert.
     * @param iconDescription the original icon description possibly containing the
     *                        annotation text.
     * @return {@code true} if a text annotation was added, {@code false} otherwise.
     */
    private boolean convertAnnotation(final Annotation annotation,
            final PropertyType iconDescription) {
        
        // Check if the icon description contains a <configure> element
        if (iconDescription.getConfigure().isEmpty()) {
            return false;
        }
        ConfigureType configureElement = iconDescription.getConfigure().get(0);
        
        // Check if there are unknown features associated with the configure element
        AnyType unknownFeature = unknownFeatures.get(configureElement);
        if (unknownFeature == null) {
            return false;
        }
        
        // Check if we can find an SVG element
        AnyType svgElement = getUnknownFeature(unknownFeature.getMixed(), ELEM_SVG);
        if (svgElement == null) {
            return false;
        }
        
        // Check if we can find a TEXT element
        AnyType textElement = getUnknownFeature(svgElement.getMixed(), ELEM_TEXT);
        if (textElement == null) {
            return false;
        }
        
        // Get the text element's character data
        if (textElement.getMixed().isEmpty()) {
            return false;
        }
        
        Object data = textElement.getMixed().get(0).getValue();
        if (!(data instanceof String)) {
            return false;
        }
        String text = (String) data;
        
        // ALRIGHTY, we've found the comment. Annotate the annotation appropriately
        TypedStringAnnotation textAnnotation =
            AnnotationsPackage.eINSTANCE.getAnnotationsFactory().createTypedStringAnnotation();
        textAnnotation.setName(ANNOTATIONS_TEXT);
        textAnnotation.setType(TYPE_STRING_ATTRIBUTE);
        textAnnotation.setValue(text);
        annotation.getAnnotations().add(textAnnotation);
        
        return true;
    }
    
    /**
     * Goes through the given feature map looking for an entry whose structural feature
     * name equals the given name.
     * 
     * @param features the feature map.
     * @param elementName name of the feature to look for. This will usually be the
     *                    name of an XML element.
     * @return the element or {@code null} if none could be found.
     */
    private AnyType getUnknownFeature(final FeatureMap features, final String elementName) {
        for (int i = 0; i < features.size(); i++) {
            FeatureMap.Entry entry = features.get(i);
            
            if (entry.getEStructuralFeature().getName().equals(elementName)) {
                Object value = entry.getValue();
                
                if (value instanceof AnyType) {
                    return (AnyType) value;
                }
            }
        }
        
        return null;
    }


    ///////////////////////////////////////////////////////////////////////////////
    // Comment Attachment Handling
    
    /**
     * Applies the attachment annotations generated while traversing the model tree to the
     * respective comment annotations. If the heuristics override is turned on and there are
     * explicit attachments, only those are applied. If not, the heuristically determined
     * attachments are applied.
     */
    private void applyAttachmentAnnotations() {
        // Explicit attachments are always applied
        for (Pair<Annotatable, Annotation> attachment : explicitAttachments) {
            attachment.getFirst().getAnnotations().add(attachment.getSecond());
        }
        
        // If there are no explicit attachments or the heuristics override is turned off,
        // apply heuristically determined attachments
        if (!heuristicsOverride || explicitAttachments.isEmpty()) {
            for (Pair<Annotatable, Annotation> attachment : heuristicAttachments) {
                attachment.getFirst().getAnnotations().add(attachment.getSecond());
            }
        }
    }
    
    /**
     * Tries to generate a comment attachment for the given comment annotation. The attachment
     * is produced in the form of an annotation, placed in one of the two attachment annotation
     * lists. 
     * 
     * @param annotation the comment annotation to try to find an attached entity for.
     */
    private void attemptCommentAttachment(final Annotation annotation) {
        Entity attachedEntity = null;
        boolean explicitlyDefinedAttachment = true;
        
        // We first try to find an explicit attachment
        attachedEntity = findExplicitlyAttachedEntity(annotation);
        
        // We may not have found an explicit attachment...
        if (attachedEntity == null) {
            explicitlyDefinedAttachment = false;
            
            // If the heuristic is still active (that is, if we haven't found any explicit
            // attachment), summon it!
            if (explicitAttachments.isEmpty() || !heuristicsOverride) {
                attachedEntity = findNearestEntity(annotation);
            }
        }
        
        // Generate an attachment annotation
        AnnotationsFactory annotationFactory =
            AnnotationsPackage.eINSTANCE.getAnnotationsFactory();
        
        ReferenceAnnotation referenceAnnotation = annotationFactory.createReferenceAnnotation();
        referenceAnnotation.setName(ANNOTATIONS_ATTACHMENT);
        referenceAnnotation.setObject(attachedEntity);
        
        // We directly annotate the comment if we couldn't find an attached entity; if we
        // were instead able to find one, we add the attachment annotation to one of the
        // two lists
        if (attachedEntity == null) {
            annotation.getAnnotations().add(referenceAnnotation);
        } else {
            if (explicitlyDefinedAttachment) {
                explicitAttachments.add(
                        new Pair<Annotatable, Annotation>(annotation, referenceAnnotation));
            } else {
                heuristicAttachments.add(
                        new Pair<Annotatable, Annotation>(annotation, referenceAnnotation));
            }
        }
    }
    
    /**
     * Looks for explicit attachment information for the given comment annotation.
     * 
     * @param annotation the annotation whose explicit attachment to find.
     * @return an entity explicitly attached to the given annotation, or {@code null} if no such
     *         entity could be found.
     */
    private Entity findExplicitlyAttachedEntity(final Annotation annotation) {
        // Find the location annotation
        Annotation locAnnotation = annotation.getAnnotation(ANNOTATION_LOCATION);
        if (locAnnotation == null) {
            return null;
        }
        
        // Check if the location is marked as being relative
        TypedStringAnnotation relativeToAnnotation =
                (TypedStringAnnotation) locAnnotation.getAnnotation(ANNOTATION_RELATIVE_TO);
        TypedStringAnnotation relativeToElementNameAnnotation =
                (TypedStringAnnotation) locAnnotation.getAnnotation(ANNOTATION_RELATIVE_TO_ELEMENT_NAME);
        if (relativeToAnnotation == null || relativeToElementNameAnnotation == null) {
            return null;
        }
        
        // Check if we support the element type the annotation is relative to
        if (relativeToElementNameAnnotation.getValue() == null
                || !relativeToElementNameAnnotation.getValue().equals(ELEMENT_NAME_ENTITY)) {
            
            return null;
        }
        
        // Otherwise, look for an entity with the given name in the comment annotation's hierarchy
        // level of the model
        String entityName = relativeToAnnotation.getValue();
        
        return findNamedEntity(annotation, entityName);
    }
    
    /**
     * From the siblings of the given object, finds the entity with the given name.
     * 
     * @param sibling sibling of the entity.
     * @param name
     * @return
     */
    private Entity findNamedEntity(final Annotation sibling, final String name) {
        // The annotation's parent must be an entity
        if (sibling.eContainer() instanceof Entity) {
            // Find a child entity with the given name
            for (Entity child : ((Entity) sibling.eContainer()).getChildEntities()) {
                if (child.getName().equals(name)) {
                    return child;
                }
            }
        }
        
        // We didn't find anything
        return null;
    }
    
    /**
     * Finds the entity nearest to an annotation provided they are within a certain
     * distance of one another.
     * 
     * @param annotation the annotation for which to find a nearest entity.
     * @return the nearest entity, or {@code null} if there was no nearest entity
     *         in a {@link #MAX_ATTACHMENT_DISTANCE} radius of the annotation.
     */
    private Entity findNearestEntity(final Annotation annotation) {
        // Find the parent and position of the annotation
        Entity annotationParent = (Entity) annotation.eContainer();
        Point2D.Double annotationLocation = getLocation(annotation);
        
        if (annotationLocation == null) {
            return null;
        }
        
        // Iterate through the parent's child entities, looking for one that's closest
        // to the annotation
        double minDistance = MAX_ATTACHMENT_DISTANCE + 1.0;
        Entity nearestEntity = null;
        
        for (Entity entity : annotationParent.getChildEntities()) {
            // Find the entity's location
            Point2D.Double entityLocation = getLocation(entity);
            if (entityLocation == null) {
                continue;
            }
            
            // Determine the distance between the entity and the annotation
            double distance = computeDistance(annotation, annotationLocation, entity,
                    entityLocation);
            
            if (distance < minDistance && distance <= MAX_ATTACHMENT_DISTANCE) {
                minDistance = distance;
                nearestEntity = entity;
            }
        }
        
        return nearestEntity;
    }
    
    /**
     * Computes the distance between the given annotation and entity.
     * 
     * <p>This simple implementation just uses the annotation's and entity's locations
     * and computes the squared euclidian distance. More sophisticated implementations
     * could take the annotation and entity sizes into account.</p>
     * 
     * @param annotation the annotation.
     * @param annotationLocation the annotation's location.
     * @param entity the entity.
     * @param entityLocation the entity's location.
     * @return a distance according to some metric.
     */
    private double computeDistance(final Annotation annotation,
            final Point2D.Double annotationLocation, final Entity entity,
            final Point2D.Double entityLocation) {
        
        return (annotationLocation.x - entityLocation.x)
             * (annotationLocation.x - entityLocation.x)
             + (annotationLocation.y - entityLocation.y)
             * (annotationLocation.y - entityLocation.y);
    }
    
    /**
     * Tries to find the given object's location. This is done by looking for
     * a {@code location} annotation.
     * 
     * @param object the object whose position to find.
     * @return the object's location.
     */
    private Point2D.Double getLocation(final Annotatable object) {
        // We're turning off the MagicNumber checks because we have a legitimate use
        // for one...
        // CHECKSTYLEOFF MagicNumber
        
        // Find the location annotation
        Annotation locationAnnotation = object.getAnnotation(ANNOTATION_LOCATION);
        if (locationAnnotation == null) {
            return null;
        } else if (!(locationAnnotation instanceof TypedStringAnnotation)) {
            return null;
        } else {
            // Split the location string into an array of components. Locations have
            // one of the following three representations:
            //   "[140.0, 20.0]"     "{140.0, 20.0}"     "140.0, 20.0"
            String locationString = ((TypedStringAnnotation) locationAnnotation).getValue();
            String[] locationArray = locationString.split("[\\s,\\[\\]{}]+"); //$NON-NLS-1$
            
            // There must be two or three components: an optional empty first string,
            // (occurs with the first two representations outlined above) and the x
            // and y values.
            int locationArrayLength = locationArray.length;
            if (locationArrayLength == 2 || locationArrayLength == 3) {
                try {
                    Point2D.Double result = new Point2D.Double();
                    result.x = Double.valueOf(locationArray[locationArrayLength - 2]);
                    result.y = Double.valueOf(locationArray[locationArrayLength - 1]);
                    
                    return result;
                } catch (NumberFormatException e) {
                    return null;
                }
            } else {
                return null;
            }
        }
        
        // CHECKSTYLEON MagicNumber
    }
}
