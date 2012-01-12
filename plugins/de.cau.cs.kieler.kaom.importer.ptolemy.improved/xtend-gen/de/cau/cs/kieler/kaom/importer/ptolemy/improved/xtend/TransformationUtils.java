package de.cau.cs.kieler.kaom.importer.ptolemy.improved.xtend;

import de.cau.cs.kieler.core.annotations.Annotatable;
import de.cau.cs.kieler.core.annotations.Annotation;
import de.cau.cs.kieler.core.annotations.AnnotationsFactory;
import de.cau.cs.kieler.core.annotations.StringAnnotation;
import de.cau.cs.kieler.kaom.Entity;
import de.cau.cs.kieler.kaom.Link;
import de.cau.cs.kieler.kaom.Linkable;
import de.cau.cs.kieler.kaom.Port;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtext.xbase.lib.BooleanExtensions;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;

/**
 * Utility methods used by the Ptolemy to KAOM transformation.
 * 
 * @author cds
 */
@SuppressWarnings("all")
public class TransformationUtils {
  /**
   * Checks if the given annotatable object has an annotation of the given key.
   * 
   * @param annotatable the annotatable object.
   * @param key key of the annotation to look for.
   * @return {@code true} if such an annotation exists, {@code false} otherwise.
   */
  public boolean hasAnnotation(final Annotatable annotatable, final String key) {
    Annotation _annotation = annotatable.getAnnotation(key);
    boolean _operator_notEquals = ObjectExtensions.operator_notEquals(_annotation, null);
    return _operator_notEquals;
  }
  
  /**
   * Adds an annotation with the given key to the given annotatable object, if no annotation with that
   * key already exists.
   * 
   * @param annotatable object to add the annotation to.
   * @param key the annotation's key.
   */
  public void addAnnotation(final Annotatable annotatable, final String key) {
    boolean _hasAnnotation = this.hasAnnotation(annotatable, key);
    boolean _operator_not = BooleanExtensions.operator_not(_hasAnnotation);
    if (_operator_not) {
      {
        Annotation _createAnnotation = AnnotationsFactory.eINSTANCE.createAnnotation();
        final Annotation annotation = _createAnnotation;
        annotation.setName(key);
        EList<Annotation> _annotations = annotatable.getAnnotations();
        _annotations.add(annotation);
      }
    }
  }
  
  /**
   * Removes the annotation with the given key from the given annotatable object, if any exists.
   * 
   * @param annotatable object to remove the annotation from.
   * @param key the annotation's key.
   */
  public void removeAnnotation(final Annotatable annotatable, final String key) {
      Annotation _annotation = annotatable.getAnnotation(key);
      final Annotation annotation = _annotation;
      boolean _operator_notEquals = ObjectExtensions.operator_notEquals(annotation, null);
      if (_operator_notEquals) {
        EList<Annotation> _annotations = annotatable.getAnnotations();
        _annotations.remove(annotation);
      }
  }
  
  /**
   * Adds an annotation with the given key and value to the given annotatable object, if no annotation
   * with that key already exists.
   * 
   * @param annotatable object to add the annotation to.
   * @param key the annotation's key.
   * @param value the annotation's value.
   */
  public void addStringAnnotation(final Annotatable annotatable, final String key, final String value) {
    boolean _hasAnnotation = this.hasAnnotation(annotatable, key);
    boolean _operator_not = BooleanExtensions.operator_not(_hasAnnotation);
    if (_operator_not) {
      {
        StringAnnotation _createStringAnnotation = AnnotationsFactory.eINSTANCE.createStringAnnotation();
        final StringAnnotation annotation = _createStringAnnotation;
        annotation.setName(key);
        annotation.setValue(value);
        EList<Annotation> _annotations = annotatable.getAnnotations();
        _annotations.add(annotation);
      }
    }
  }
  
  /**
   * Returns the value of the string annotation with the given key, if any.
   * 
   * @param annotatable the annotatable object to fetch the annotation from.
   * @param key the annotation's key.
   * @return the annotation's value, if it exists, or the empty string if it doesn't.
   */
  public String getStringAnnotationValue(final Annotatable annotatable, final String key) {
    String _xblockexpression = null;
    {
      Annotation _annotation = annotatable.getAnnotation(key);
      final Annotation annotation = _annotation;
      String _xifexpression = null;
      if ((annotation instanceof StringAnnotation)) {
        String _value = ((StringAnnotation) annotation).getValue();
        _xifexpression = _value;
      } else {
        _xifexpression = "";
      }
      _xblockexpression = (_xifexpression);
    }
    return _xblockexpression;
  }
  
  /**
   * Marks the given link as being undirected. This means that the link's direction has not been
   * inferred yet.
   * 
   * @param link the link to mark.
   * @param mark {@code true} if the link should be marked as being undirected, {@code false} if the
   *             marking should be removed.
   */
  public void markAsUndirected(final Link link, final boolean mark) {
    if (mark) {
      this.addAnnotation(link, "undirected");
    } else {
      this.removeAnnotation(link, "undirected");
    }
  }
  
  /**
   * Checks if the given link is marked as being undirected.
   * 
   * @param link the link to check.
   * @return {@code true} if the link is marked as undirected, {@code false} otherwise.
   */
  public boolean isMarkedAsUndirected(final Link link) {
    boolean _hasAnnotation = this.hasAnnotation(link, "undirected");
    return _hasAnnotation;
  }
  
  /**
   * Marks the given port as being an input port.
   * 
   * @param port the port to mark.
   */
  public void markAsInputPort(final Port port) {
    this.addAnnotation(port, "input");
  }
  
  /**
   * Checks if the given port is marked as being an input port.
   * 
   * @param port the port to check.
   * @return {@code true} if the port is marked as being an input port, {@code false} otherwise.
   */
  public boolean isMarkedAsInputPort(final Port port) {
    boolean _hasAnnotation = this.hasAnnotation(port, "input");
    return _hasAnnotation;
  }
  
  /**
   * Marks the given port as being an output port.
   * 
   * @param port the port to mark.
   */
  public void markAsOutputPort(final Port port) {
    this.addAnnotation(port, "output");
  }
  
  /**
   * Checks if the given port is marked as being an output port.
   * 
   * @param port the port to check.
   * @return {@code true} if the port is marked as being an output port, {@code false} otherwise.
   */
  public boolean isMarkedAsOutputPort(final Port port) {
    boolean _hasAnnotation = this.hasAnnotation(port, "output");
    return _hasAnnotation;
  }
  
  /**
   * Marks an entity as formerly having been an annotation. Some KAOM entities do not map to actors in
   * the original Ptolemy model, but to annotations. For example, the director used to execute a model
   * is persisted as an annotation in Ptolemy. In the corresponding KAOM model, we need it to be an
   * entity to display it correctly.
   * 
   * @param entity the entity to mark.
   * @param mark {@code true} if the entity should be marked, {@code false} if the marking should be
   *             removed.
   */
  public void markAsFormerAnnotationEntity(final Entity entity, final boolean mark) {
    if (mark) {
      this.addAnnotation(entity, "annotationNode");
    } else {
      this.removeAnnotation(entity, "annotationNode");
    }
  }
  
  /**
   * Checks if the given entity is marked as having been created from an annotation.
   * 
   * @param entity the entity to check.
   * @return {@code true} if it is marked, {@code false} otherwise.
   */
  public boolean isMarkedAsFormerAnnotationEntity(final Entity entity) {
    boolean _hasAnnotation = this.hasAnnotation(entity, "annotationNode");
    return _hasAnnotation;
  }
  
  /**
   * Checks if the given linkable object has an incident link of unknown direction.
   * 
   * @param linkable the linkable object to check.
   * @return {@code true} if the linkable object has an incident link of unknown direction,
   *         {@code false} otherwise.
   */
  public boolean hasUnknownIncidentLink(final Linkable linkable) {
    boolean _xblockexpression = false;
    {
      EList<Link> _incomingLinks = linkable.getIncomingLinks();
      for (final Link link : _incomingLinks) {
        boolean _isMarkedAsUndirected = this.isMarkedAsUndirected(link);
        if (_isMarkedAsUndirected) {
          return true;
        }
      }
      EList<Link> _outgoingLinks = linkable.getOutgoingLinks();
      for (final Link link_1 : _outgoingLinks) {
        boolean _isMarkedAsUndirected_1 = this.isMarkedAsUndirected(link_1);
        if (_isMarkedAsUndirected_1) {
          return true;
        }
      }
      _xblockexpression = (false);
    }
    return _xblockexpression;
  }
  
  /**
   * Reverses the given link's direction.
   * 
   * @param link the link to reverse.
   */
  public void reverseLink(final Link link) {
      Linkable _source = link.getSource();
      final Linkable oldSource = _source;
      Linkable _target = link.getTarget();
      link.setSource(_target);
      link.setTarget(oldSource);
  }
  
  /**
   * Checks if the given list of links contains at least one link of known direction.
   * 
   * @param links list of links to check for directed links.
   * @return {@code true} if at least one link is of known direction.
   */
  public boolean containsDirectedLink(final List<Link> links) {
    boolean _xblockexpression = false;
    {
      for (final Link link : links) {
        boolean _isMarkedAsUndirected = this.isMarkedAsUndirected(link);
        boolean _operator_not = BooleanExtensions.operator_not(_isMarkedAsUndirected);
        if (_operator_not) {
          return true;
        }
      }
      _xblockexpression = (false);
    }
    return _xblockexpression;
  }
  
  /**
   * Returns a list of all links incident to the given linkable object.
   * 
   * @param linkable the object whose incident links to return.
   * @return a list of all incident links.
   */
  public List<Link> getIncidentLinks(final Linkable linkable) {
    List<Link> _xblockexpression = null;
    {
      ArrayList<Link> _newArrayList = CollectionLiterals.<Link>newArrayList();
      final List<Link> incidentLinks = _newArrayList;
      EList<Link> _incomingLinks = linkable.getIncomingLinks();
      incidentLinks.addAll(_incomingLinks);
      EList<Link> _outgoingLinks = linkable.getOutgoingLinks();
      incidentLinks.addAll(_outgoingLinks);
      _xblockexpression = (incidentLinks);
    }
    return _xblockexpression;
  }
}
