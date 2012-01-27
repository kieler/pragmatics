package de.cau.cs.kieler.kaom.importer.ptolemy.improved.xtend;

import com.google.inject.Inject;
import de.cau.cs.kieler.core.annotations.Annotatable;
import de.cau.cs.kieler.core.annotations.Annotation;
import de.cau.cs.kieler.core.annotations.AnnotationsFactory;
import de.cau.cs.kieler.core.annotations.TypedStringAnnotation;
import de.cau.cs.kieler.kaom.Entity;
import de.cau.cs.kieler.kaom.KaomFactory;
import de.cau.cs.kieler.kaom.Link;
import de.cau.cs.kieler.kaom.Linkable;
import de.cau.cs.kieler.kaom.Port;
import de.cau.cs.kieler.kaom.Relation;
import de.cau.cs.kieler.kaom.importer.ptolemy.improved.Messages;
import de.cau.cs.kieler.kaom.importer.ptolemy.improved.PtolemyImportPlugin;
import de.cau.cs.kieler.kaom.importer.ptolemy.improved.xtend.PtolemyInterface;
import de.cau.cs.kieler.kaom.importer.ptolemy.improved.xtend.TransformationUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.xbase.lib.BooleanExtensions;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Functions.Function0;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IntegerExtensions;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.ptolemy.moml.ClassType;
import org.ptolemy.moml.DocumentRoot;
import org.ptolemy.moml.EntityType;
import org.ptolemy.moml.LinkType;
import org.ptolemy.moml.PortType;
import org.ptolemy.moml.PropertyType;
import org.ptolemy.moml.RelationType;

/**
 * Transforms a Ptolemy2 model to a KAOM model. This is part one of the Ptolemy model import process,
 * with part two consisting of the optimization of the transformed model.
 * 
 * <p>In this part, all Ptolemy entities, relations and links are transformed to their KAOM
 * counterparts. The transformed objects are annotated to originate from a Ptolemy2 model. This
 * is fairly straightforward, except for the ports of actors. During the transformation, actors
 * must be instantiated using the KIELER Ptolemy library to find their ports. The problem is that
 * if an actor is not part of the official Ptolemy actor library, the instantiation often fails,
 * leaving us without properly defined ports. In particular, we then cannot determine if a port
 * is an input port or an output port, which leads to problems when the directions of links are
 * inferred in the second part of the import process. (contrary to KAOM links, Ptolemy links are
 * undirected)</p>
 * 
 * <p><b>Note:</b> This transformation cannot simply be reused. To keep things simple, always use
 * a new instance for each model to be transformed.</p>
 * 
 * @author cds
 * @author haf
 */
@SuppressWarnings("all")
public class Ptolemy2KaomTransformation {
  /**
   * Extensions used during the transformation. To make things easier. And stuff.
   */
  @Inject
  private TransformationUtils _transformationUtils;
  
  /**
   * Interface to the Ptolemy library.
   */
  @Inject
  private PtolemyInterface ptolemy;
  
  /**
   * List of warnings collected during the transformation. These will usually only be warnings about
   * actors that couldn't be instantiated.
   */
  private ArrayList<IStatus> warnings = new Function0<ArrayList<IStatus>>() {
    public ArrayList<IStatus> apply() {
      ArrayList<IStatus> _arrayList = new ArrayList<IStatus>();
      return _arrayList;
    }
  }.apply();
  
  /**
   * Entry point for the whole transformation business. Takes a Ptolemy MOML document's
   * root element and returns a KAOM entity representing the converted model.
   * 
   * @param ptDocumentRoot the Ptolemy MOML document's root element.
   * @return the transformed KAOM entity.
   */
  public Entity transform(final DocumentRoot ptDocumentRoot) {
    final ArrayList<?>_cacheKey = CollectionLiterals.newArrayList(ptDocumentRoot);
    final Entity _result;
    synchronized (_createCache_transform) {
      if (_createCache_transform.containsKey(_cacheKey)) {
        return _createCache_transform.get(_cacheKey);
      }
      Entity _createEntity = KaomFactory.eINSTANCE.createEntity();
      _result = _createEntity;
      _createCache_transform.put(_cacheKey, _result);
    }
    _init_transform(_result, ptDocumentRoot);
    return _result;
  }
  
  private final HashMap<ArrayList<?>,Entity> _createCache_transform = CollectionLiterals.newHashMap();
  
  private void _init_transform(final Entity result, final DocumentRoot ptDocumentRoot) {
      EntityType _entity = ptDocumentRoot.getEntity();
      Entity _transform = _entity==null?(Entity)null:this.transform(_entity);
      final Entity kaomEntity = _transform;
      boolean _operator_notEquals = ObjectExtensions.operator_notEquals(kaomEntity, null);
      if (_operator_notEquals) {
        EList<Entity> _childEntities = result.getChildEntities();
        _childEntities.add(kaomEntity);
      }
      ClassType _class_ = ptDocumentRoot.getClass_();
      Entity _transform_1 = _class_==null?(Entity)null:this.transform(_class_);
      final Entity kaomClass = _transform_1;
      boolean _operator_notEquals_1 = ObjectExtensions.operator_notEquals(kaomClass, null);
      if (_operator_notEquals_1) {
        EList<Entity> _childEntities_1 = result.getChildEntities();
        _childEntities_1.add(kaomClass);
      }
  }
  
  /**
   * Transforms the given Ptolemy entity and its children into a KAOM entity.
   * 
   * @param ptEntity the Ptolemy entity to transform.
   * @return the KAOM entity.
   */
  private Entity transform(final EntityType ptEntity) {
    final ArrayList<?>_cacheKey = CollectionLiterals.newArrayList(ptEntity);
    final Entity _result;
    synchronized (_createCache_transform_1) {
      if (_createCache_transform_1.containsKey(_cacheKey)) {
        return _createCache_transform_1.get(_cacheKey);
      }
      Entity _createEntity = KaomFactory.eINSTANCE.createEntity();
      _result = _createEntity;
      _createCache_transform_1.put(_cacheKey, _result);
    }
    _init_transform_1(_result, ptEntity);
    return _result;
  }
  
  private final HashMap<ArrayList<?>,Entity> _createCache_transform_1 = CollectionLiterals.newHashMap();
  
  private void _init_transform_1(final Entity result, final EntityType ptEntity) {
      String _name = ptEntity.getName();
      result.setName(_name);
      this._transformationUtils.addStringAnnotation(result, "language", "ptolemy");
      String _class1 = ptEntity.getClass1();
      this._transformationUtils.addStringAnnotation(result, "ptolemyClass", _class1);
      EList<PropertyType> _property = ptEntity.getProperty();
      this.addProperties(result, _property);
      this.addChildPorts(result, ptEntity);
      EList<EntityType> _entity = ptEntity.getEntity();
      this.addChildEntities(result, ((EntityType[])Conversions.unwrapArray(_entity, EntityType.class)));
      EList<RelationType> _relation = ptEntity.getRelation();
      this.addChildRelations(result, ((RelationType[])Conversions.unwrapArray(_relation, RelationType.class)));
      EList<LinkType> _link = ptEntity.getLink();
      this.addChildLinks(result, ((LinkType[])Conversions.unwrapArray(_link, LinkType.class)));
  }
  
  /**
   * Transforms the given Ptolemy class and its children into a KAOM entity.
   * 
   * <p>Assumption: a ClassType node can only occur at the top level. If this assumption turns out
   * not to be true, we need to overload quite a bunch of methods. Which we don't want to do. So
   * it better be true.</p>
   * 
   * @param ptClass the Ptolemy class to transform.
   * @return the KAOM entity.
   */
  private Entity transform(final ClassType ptClass) {
    final ArrayList<?>_cacheKey = CollectionLiterals.newArrayList(ptClass);
    final Entity _result;
    synchronized (_createCache_transform_2) {
      if (_createCache_transform_2.containsKey(_cacheKey)) {
        return _createCache_transform_2.get(_cacheKey);
      }
      Entity _createEntity = KaomFactory.eINSTANCE.createEntity();
      _result = _createEntity;
      _createCache_transform_2.put(_cacheKey, _result);
    }
    _init_transform_2(_result, ptClass);
    return _result;
  }
  
  private final HashMap<ArrayList<?>,Entity> _createCache_transform_2 = CollectionLiterals.newHashMap();
  
  private void _init_transform_2(final Entity result, final ClassType ptClass) {
      String _name = ptClass.getName();
      result.setName(_name);
      this._transformationUtils.addStringAnnotation(result, "language", "ptolemy");
      String _extends = ptClass.getExtends();
      this._transformationUtils.addStringAnnotation(result, "ptolemyClass", _extends);
      EList<PropertyType> _property = ptClass.getProperty();
      this.addProperties(result, _property);
      this.addChildPorts(result, ptClass);
      EList<EntityType> _entity = ptClass.getEntity();
      this.addChildEntities(result, ((EntityType[])Conversions.unwrapArray(_entity, EntityType.class)));
      EList<RelationType> _relation = ptClass.getRelation();
      this.addChildRelations(result, ((RelationType[])Conversions.unwrapArray(_relation, RelationType.class)));
      EList<LinkType> _link = ptClass.getLink();
      this.addChildLinks(result, ((LinkType[])Conversions.unwrapArray(_link, LinkType.class)));
  }
  
  /**
   * Transforms the given Ptolemy relation into a KAOM relation.
   * 
   * @param ptRelation the Ptolemy relation to transform.
   * @return the KAOM relation.
   */
  private Relation transform(final RelationType ptRelation) {
    final ArrayList<?>_cacheKey = CollectionLiterals.newArrayList(ptRelation);
    final Relation _result;
    synchronized (_createCache_transform_3) {
      if (_createCache_transform_3.containsKey(_cacheKey)) {
        return _createCache_transform_3.get(_cacheKey);
      }
      Relation _createRelation = KaomFactory.eINSTANCE.createRelation();
      _result = _createRelation;
      _createCache_transform_3.put(_cacheKey, _result);
    }
    _init_transform_3(_result, ptRelation);
    return _result;
  }
  
  private final HashMap<ArrayList<?>,Relation> _createCache_transform_3 = CollectionLiterals.newHashMap();
  
  private void _init_transform_3(final Relation result, final RelationType ptRelation) {
      String _name = ptRelation.getName();
      result.setName(_name);
      this._transformationUtils.addStringAnnotation(result, "language", "ptolemy");
      EList<PropertyType> _property = ptRelation.getProperty();
      this.addProperties(result, _property);
  }
  
  /**
   * Transforms the given Ptolemy link into a KAOM link. The problem here is that a Ptolemy link
   * is (1) undirected and (2) has multiple possible attributes for head and tail. The latter is
   * because it can connect either a port and a relation, or two relations.
   * 
   * <p>Note: This method expects relations of the {@code kaomParent} to already have been
   * transformed.</p>
   * 
   * @param ptLink the Ptolemy link to transform.
   * @param kaomParent the link's parent entity, with relations already transformed.
   * @return the transformed KAOM link.
   */
  private Link transform(final LinkType ptLink, final Entity kaomParent) {
    final ArrayList<?>_cacheKey = CollectionLiterals.newArrayList(ptLink, kaomParent);
    final Link _result;
    synchronized (_createCache_transform_4) {
      if (_createCache_transform_4.containsKey(_cacheKey)) {
        return _createCache_transform_4.get(_cacheKey);
      }
      Link _createLink = KaomFactory.eINSTANCE.createLink();
      _result = _createLink;
      _createCache_transform_4.put(_cacheKey, _result);
    }
    _init_transform(_result, ptLink, kaomParent);
    return _result;
  }
  
  private final HashMap<ArrayList<?>,Link> _createCache_transform_4 = CollectionLiterals.newHashMap();
  
  private void _init_transform(final Link result, final LinkType ptLink, final Entity kaomParent) {
    try {
      {
        EList<Relation> _childRelations = kaomParent.getChildRelations();
        final Function1<Relation,Boolean> _function = new Function1<Relation,Boolean>() {
            public Boolean apply(final Relation r) {
              String _name = r.getName();
              String _relation = ptLink.getRelation();
              boolean _equals = _name.equals(_relation);
              return Boolean.valueOf(_equals);
            }
          };
        Relation _findFirst = IterableExtensions.<Relation>findFirst(_childRelations, _function);
        final Relation relation = _findFirst;
        EList<Relation> _childRelations_1 = kaomParent.getChildRelations();
        final Function1<Relation,Boolean> _function_1 = new Function1<Relation,Boolean>() {
            public Boolean apply(final Relation r) {
              String _name = r.getName();
              String _relation1 = ptLink.getRelation1();
              boolean _equals = _name.equals(_relation1);
              return Boolean.valueOf(_equals);
            }
          };
        Relation _findFirst_1 = IterableExtensions.<Relation>findFirst(_childRelations_1, _function_1);
        final Relation relation1 = _findFirst_1;
        EList<Relation> _childRelations_2 = kaomParent.getChildRelations();
        final Function1<Relation,Boolean> _function_2 = new Function1<Relation,Boolean>() {
            public Boolean apply(final Relation r) {
              String _name = r.getName();
              String _relation2 = ptLink.getRelation2();
              boolean _equals = _name.equals(_relation2);
              return Boolean.valueOf(_equals);
            }
          };
        Relation _findFirst_2 = IterableExtensions.<Relation>findFirst(_childRelations_2, _function_2);
        final Relation relation2 = _findFirst_2;
        Port _xifexpression = null;
        String _port = ptLink.getPort();
        boolean _operator_equals = ObjectExtensions.operator_equals(_port, null);
        if (_operator_equals) {
          _xifexpression = null;
        } else {
          String _port_1 = ptLink.getPort();
          Port _orCreatePortByName = this.getOrCreatePortByName(kaomParent, _port_1);
          _xifexpression = _orCreatePortByName;
        }
        final Port port = _xifexpression;
        ArrayList<Linkable> _arrayList = new ArrayList<Linkable>();
        final ArrayList<Linkable> endpoints = _arrayList;
        this._transformationUtils.addStringAnnotation(result, "language", "ptolemy");
        boolean _operator_notEquals = ObjectExtensions.operator_notEquals(relation, null);
        if (_operator_notEquals) {
          endpoints.add(relation);
        }
        boolean _operator_notEquals_1 = ObjectExtensions.operator_notEquals(relation1, null);
        if (_operator_notEquals_1) {
          endpoints.add(relation1);
        }
        boolean _operator_notEquals_2 = ObjectExtensions.operator_notEquals(relation2, null);
        if (_operator_notEquals_2) {
          endpoints.add(relation2);
        }
        boolean _operator_notEquals_3 = ObjectExtensions.operator_notEquals(port, null);
        if (_operator_notEquals_3) {
          endpoints.add(port);
        }
        int _size = endpoints.size();
        boolean _operator_equals_1 = IntegerExtensions.operator_equals(_size, 2);
        if (_operator_equals_1) {
          {
            Linkable _get = endpoints.get(0);
            result.setSource(_get);
            Linkable _get_1 = endpoints.get(1);
            result.setTarget(_get_1);
          }
        }
        this._transformationUtils.markAsUndirected(result, true);
      }
    } catch (Exception _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  /**
   * Transforms the given Ptolemy port into a KAOM port.
   * 
   * @param ptPort the Ptolemy port to transform.
   * @return the KAOM port.
   */
  private Port transform(final PortType ptPort) {
    final ArrayList<?>_cacheKey = CollectionLiterals.newArrayList(ptPort);
    final Port _result;
    synchronized (_createCache_transform_5) {
      if (_createCache_transform_5.containsKey(_cacheKey)) {
        return _createCache_transform_5.get(_cacheKey);
      }
      Port _createPort = KaomFactory.eINSTANCE.createPort();
      _result = _createPort;
      _createCache_transform_5.put(_cacheKey, _result);
    }
    _init_transform_4(_result, ptPort);
    return _result;
  }
  
  private final HashMap<ArrayList<?>,Port> _createCache_transform_5 = CollectionLiterals.newHashMap();
  
  private void _init_transform_4(final Port result, final PortType ptPort) {
      String _name = ptPort.getName();
      result.setName(_name);
      this._transformationUtils.addStringAnnotation(result, "language", "ptolemy");
      EList<PropertyType> _property = ptPort.getProperty();
      this.addProperties(result, _property);
  }
  
  /**
   * Looks for a port with the given name under the given parent entity. The port name may be of the
   * following two forms:
   * 
   * <ol>
   *   <li>{@code portName}<br/>
   *       In this simple form, the port is assumed to belong to the parent entity. If the port does
   *       not exist, it is created and added to the parent entity.
   *   </li>
   *   <li>{@code actorName.portName}<br/>
   *       In this extended form, the port is assumed to belong to an actor of the given name. The
   *       actor must be a direct child of the parent entity. If the actor doesn't have a port of the
   *       given name, it is created and added to the model. If an actor of the given name doesn't
   *       exist, the model is not modified.
   *   </li>
   * </ol>
   * 
   * @param kaomParent the entity to look for ports and actors in.
   * @param name the name of the port to find or create.
   * @return the port.
   * @throws CoreException if the port name does not follow the expected format, or if the referenced
   *                       actor could not be found.
   */
  private Port getOrCreatePortByName(final Entity kaomParent, final String name) throws CoreException {
    Port _xblockexpression = null;
    {
      String[] _split = name.split("\\.");
      ArrayList<String> _newArrayList = CollectionLiterals.<String>newArrayList(_split);
      final ArrayList<String> nameParts = _newArrayList;
      boolean _operator_or = false;
      int _size = nameParts.size();
      boolean _operator_lessThan = IntegerExtensions.operator_lessThan(_size, 1);
      if (_operator_lessThan) {
        _operator_or = true;
      } else {
        int _size_1 = nameParts.size();
        boolean _operator_greaterThan = IntegerExtensions.operator_greaterThan(_size_1, 2);
        _operator_or = BooleanExtensions.operator_or(_operator_lessThan, _operator_greaterThan);
      }
      if (_operator_or) {
        String _replace = Messages.PtolemyTransformation_exception_malformedPortName.replace("%1", name);
        Status _status = new Status(IStatus.ERROR, PtolemyImportPlugin.PLUGIN_ID, _replace, null);
        CoreException _coreException = new CoreException(_status);
        throw _coreException;
      }
      Entity _switchResult = null;
      int _size_2 = nameParts.size();
      final int __valOfSwitchOver = _size_2;
      boolean matched = false;
      if (!matched) {
        if (ObjectExtensions.operator_equals(__valOfSwitchOver,1)) {
          matched=true;
          _switchResult = kaomParent;
        }
      }
      if (!matched) {
        if (ObjectExtensions.operator_equals(__valOfSwitchOver,2)) {
          matched=true;
          EList<Entity> _childEntities = kaomParent.getChildEntities();
          final Function1<Entity,Boolean> _function = new Function1<Entity,Boolean>() {
              public Boolean apply(final Entity a) {
                String _name = a.getName();
                String _get = nameParts.get(0);
                boolean _equals = _name.equals(_get);
                return Boolean.valueOf(_equals);
              }
            };
          Entity _findFirst = IterableExtensions.<Entity>findFirst(_childEntities, _function);
          _switchResult = _findFirst;
        }
      }
      final Entity actor = _switchResult;
      boolean _operator_equals = ObjectExtensions.operator_equals(actor, null);
      if (_operator_equals) {
        String _replace_1 = Messages.PtolemyTransformation_exception_portReferencesUnknownActor.replace("%1", name);
        Status _status_1 = new Status(IStatus.ERROR, PtolemyImportPlugin.PLUGIN_ID, _replace_1, null);
        CoreException _coreException_1 = new CoreException(_status_1);
        throw _coreException_1;
      }
      int _size_3 = nameParts.size();
      int _operator_minus = IntegerExtensions.operator_minus(_size_3, 1);
      String _get = nameParts.get(_operator_minus);
      final String portName = _get;
      EList<Port> _childPorts = actor.getChildPorts();
      final Function1<Port,Boolean> _function_1 = new Function1<Port,Boolean>() {
          public Boolean apply(final Port p) {
            String _name = p.getName();
            boolean _equals = _name.equals(portName);
            return Boolean.valueOf(_equals);
          }
        };
      Port _findFirst_1 = IterableExtensions.<Port>findFirst(_childPorts, _function_1);
      final Port port = _findFirst_1;
      Port _xifexpression = null;
      boolean _operator_equals_1 = ObjectExtensions.operator_equals(port, null);
      if (_operator_equals_1) {
        Port _createPort = this.createPort(actor, portName);
        _xifexpression = _createPort;
      } else {
        _xifexpression = port;
      }
      _xblockexpression = (_xifexpression);
    }
    return _xblockexpression;
  }
  
  /**
   * Creates a port with the given name and adds it to the entity's list of ports.
   * 
   * @param kaomEntity the entity to create the port for.
   * @param name the name of the port to create.
   * @return the created port.
   */
  private Port createPort(final Entity kaomEntity, final String name) {
    Port _xblockexpression = null;
    {
      Port _createPort = KaomFactory.eINSTANCE.createPort();
      final Port result = _createPort;
      result.setName(name);
      this._transformationUtils.addStringAnnotation(result, "language", "ptolemy");
      EList<Port> _childPorts = kaomEntity.getChildPorts();
      _childPorts.add(result);
      _xblockexpression = (result);
    }
    return _xblockexpression;
  }
  
  /**
   * Recursively transforms the given list of properties and adds them to the given annotatable
   * object.
   * 
   * @param annotatable object to add the transformed properties to.
   * @param ptProperties list of properties to transform.
   */
  private void addProperties(final Annotatable annotatable, final List<PropertyType> ptProperties) {
    for (final PropertyType ptProperty : ptProperties) {
      {
        TypedStringAnnotation _createTypedStringAnnotation = AnnotationsFactory.eINSTANCE.createTypedStringAnnotation();
        final TypedStringAnnotation propertyAnnotation = _createTypedStringAnnotation;
        String _name = ptProperty.getName();
        propertyAnnotation.setName(_name);
        String _value = ptProperty.getValue();
        propertyAnnotation.setValue(_value);
        String _class_ = ptProperty.getClass_();
        propertyAnnotation.setType(_class_);
        EList<PropertyType> _property = ptProperty.getProperty();
        this.addProperties(propertyAnnotation, _property);
        EList<Annotation> _annotations = annotatable.getAnnotations();
        _annotations.add(propertyAnnotation);
      }
    }
  }
  
  /**
   * Transforms the given list of entities and adds them to the given parent entity.
   * 
   * @param kaomParent the new parent entity of the transformed entities.
   * @param ptEntities list of entities to transform
   */
  private void addChildEntities(final Entity kaomParent, final EntityType[] ptEntities) {
    for (final EntityType ptEntity : ptEntities) {
      EList<Entity> _childEntities = kaomParent.getChildEntities();
      Entity _transform = this.transform(ptEntity);
      _childEntities.add(_transform);
    }
  }
  
  /**
   * Transforms the given list of relations and adds them to the given parent entity.
   * 
   * @param kaomParent the new parent entity of the transformed relations.
   * @param ptRelations list of relations to transform
   */
  private void addChildRelations(final Entity kaomParent, final RelationType[] ptRelations) {
    for (final RelationType ptRelation : ptRelations) {
      EList<Relation> _childRelations = kaomParent.getChildRelations();
      Relation _transform = this.transform(ptRelation);
      _childRelations.add(_transform);
    }
  }
  
  /**
   * Transforms the given list of links and adds them to the given parent entity.
   * 
   * @param kaomParent the new parent entity of the transformed links.
   * @param ptLinks list of links to transform
   */
  private void addChildLinks(final Entity kaomParent, final LinkType[] ptLinks) {
    for (final LinkType ptLink : ptLinks) {
      EList<Link> _childLinks = kaomParent.getChildLinks();
      Link _transform = this.transform(ptLink, kaomParent);
      _childLinks.add(_transform);
    }
  }
  
  /**
   * Adds the entity's ports to it.
   * 
   * <p>This is no trivial task. Ports are not necessarily explicitly defined objects in a Ptolemy
   * model. Usually, they are created implicitly in an actor's Java implementation. Hence, to see
   * what ports an entity has, we need to instantiate it in Ptolemy.</p>
   * 
   * <p>If the actor is not available in KIELER's Ptolemy library, instantiating an actor to check
   * for its ports will of cause fail, leaving the entity without ports. To handle these cases, such
   * ports are created once they are referenced later on. However, their specific attributes (e.g.,
   * whether they are input ports or output ports) will then be unavailable, which in turn may cause
   * link directions to be incorrectly inferred.</p>
   * 
   * @param kaomEntity the entity to add the ports to.
   * @param entityOrClass the entity or class that was transformed into the KAOM entity.
   */
  private void addChildPorts(final Entity kaomEntity, final EObject entityOrClass) {
      ArrayList<Port> _arrayList = new ArrayList<Port>();
      final ArrayList<Port> ports = _arrayList;
      try {
        List<Port> _portsFromImplementation = this.ptolemy.getPortsFromImplementation(entityOrClass);
        ports.addAll(_portsFromImplementation);
      } catch (final Throwable _t) {
        if (_t instanceof Exception) {
          final Exception e = (Exception)_t;
          String _message = e.getMessage();
          Status _status = new Status(IStatus.WARNING, PtolemyImportPlugin.PLUGIN_ID, _message, e);
          this.warnings.add(_status);
        } else {
          throw Exceptions.sneakyThrow(_t);
        }
      }
      List<Port> _xifexpression = null;
      if ((entityOrClass instanceof EntityType)) {
        EList<PortType> _port = ((EntityType) entityOrClass).getPort();
        final Function1<PortType,Port> _function = new Function1<PortType,Port>() {
            public Port apply(final PortType p) {
              Port _transform = Ptolemy2KaomTransformation.this.transform(p);
              return _transform;
            }
          };
        List<Port> _map = ListExtensions.<PortType, Port>map(_port, _function);
        _xifexpression = _map;
      } else {
        List<Port> _xifexpression_1 = null;
        if ((entityOrClass instanceof ClassType)) {
          EList<PortType> _port_1 = ((ClassType) entityOrClass).getPort();
          final Function1<PortType,Port> _function_1 = new Function1<PortType,Port>() {
              public Port apply(final PortType p) {
                Port _transform = Ptolemy2KaomTransformation.this.transform(p);
                return _transform;
              }
            };
          List<Port> _map_1 = ListExtensions.<PortType, Port>map(_port_1, _function_1);
          _xifexpression_1 = _map_1;
        }
        _xifexpression = _xifexpression_1;
      }
      final List<Port> modelPorts = _xifexpression;
      for (final Port modelPort : modelPorts) {
        {
          final Function1<Port,Boolean> _function_2 = new Function1<Port,Boolean>() {
              public Boolean apply(final Port p) {
                String _name = p.getName();
                String _name_1 = modelPort.getName();
                boolean _equals = _name.equals(_name_1);
                return Boolean.valueOf(_equals);
              }
            };
          Port _findFirst = IterableExtensions.<Port>findFirst(ports, _function_2);
          final Port existingPort = _findFirst;
          boolean _operator_notEquals = ObjectExtensions.operator_notEquals(existingPort, null);
          if (_operator_notEquals) {
            EList<Annotation> _annotations = existingPort.getAnnotations();
            EList<Annotation> _annotations_1 = modelPort.getAnnotations();
            _annotations.addAll(_annotations_1);
          } else {
            ports.add(modelPort);
          }
        }
      }
      EList<Port> _childPorts = kaomEntity.getChildPorts();
      _childPorts.addAll(ports);
  }
  
  /**
   * Returns the list of warnings produced by the transformation.
   */
  public List<IStatus> getWarnings() {
    return this.warnings;
  }
}
