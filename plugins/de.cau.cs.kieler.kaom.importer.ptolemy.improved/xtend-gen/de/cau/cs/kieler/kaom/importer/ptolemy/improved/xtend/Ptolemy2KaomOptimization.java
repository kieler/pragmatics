package de.cau.cs.kieler.kaom.importer.ptolemy.improved.xtend;

import com.google.inject.Inject;
import de.cau.cs.kieler.core.annotations.Annotation;
import de.cau.cs.kieler.core.annotations.TypedStringAnnotation;
import de.cau.cs.kieler.kaom.Entity;
import de.cau.cs.kieler.kaom.KaomFactory;
import de.cau.cs.kieler.kaom.Link;
import de.cau.cs.kieler.kaom.Linkable;
import de.cau.cs.kieler.kaom.Port;
import de.cau.cs.kieler.kaom.Relation;
import de.cau.cs.kieler.kaom.importer.ptolemy.improved.xtend.TransformationUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.xbase.lib.BooleanExtensions;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IntegerExtensions;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;

/**
 * Optimizes a KAOM model freshly transformed from a Ptolemy2 model. This is part two of the Ptolemy
 * model import process.
 * 
 * <p>In this part, link directions are computed, relations that only connect two ports are replaced
 * by a single link, special annotations are replaced by entities (the Ptolemy director, ...), and
 * links of states are changed.</p>
 * 
 * @author cds
 * @author haf
 */
@SuppressWarnings("all")
public class Ptolemy2KaomOptimization {
  /**
   * Extensions used during the transformation. To make things easier. And stuff.
   */
  @Inject
  private TransformationUtils _transformationUtils;
  
  /**
   * Optimizes the given KAOM model.
   * 
   * @param kaomModel the model to optimize.
   */
  public void optimize(final Entity kaomModel) {
      this.inferLinkDirections(kaomModel);
      this.makeStatesPortless(kaomModel);
      this.removeUnnecessaryRelations(kaomModel);
      this.convertAnnotationsToEntities(kaomModel);
  }
  
  /**
   * Infers the direction of the links in the model tree rooted at the given entity.
   * 
   * <p>First, a list of all ports of unknown type, all links of unknown direction, and all relations
   * are collected. In that process, the direction of links connected to ports of known type is
   * inferred.</p>
   * 
   * <p>Second, an attempt is made to infer the type of ports of yet unknown type. This succeeds if
   * the port is connected to a link whose direction is known. The direction of all links connected
   * to such a port is inferred in that process.</p>
   * 
   * <p>Third, all relations are traversed, looking for a relation with only incoming or only outgoing
   * links and just one link of unknown direction. If such a relation is found, the direction of that
   * one link is inferred. If no such relation is found, a random undirected link's direction is
   * fixed.</p>
   * 
   * <p>Repeat at step two.</p>
   * 
   * <p>By this time, either all link directions have been inferred or there are some links left whose
   * direction cannot be determined with the information available. These links are left untouched,
   * since we might as well leave them in the direction they currently have.</p>
   * 
   * @param kaomModel root of the model tree.
   */
  private void inferLinkDirections(final Entity kaomModel) {
      ArrayList<Port> _newArrayList = CollectionLiterals.<Port>newArrayList();
      final List<Port> knownPorts = _newArrayList;
      ArrayList<Port> _newArrayList_1 = CollectionLiterals.<Port>newArrayList();
      final List<Port> unknownPorts = _newArrayList_1;
      ArrayList<Link> _newArrayList_2 = CollectionLiterals.<Link>newArrayList();
      final List<Link> unknownLinks = _newArrayList_2;
      ArrayList<Relation> _newArrayList_3 = CollectionLiterals.<Relation>newArrayList();
      final List<Relation> unknownRelations = _newArrayList_3;
      this.gatherModelElements(kaomModel, knownPorts, unknownPorts, unknownLinks, unknownRelations);
      for (final Port knownPort : knownPorts) {
        this.propagatePortTypeToIncidentLinks(knownPort, unknownLinks);
      }
      boolean portTypesChanged = false;
      boolean relationsChanged = false;
      boolean randomLinkFixed = false;
      boolean _dowhile = false;
      do {
        {
          boolean _inferPortTypes = this.inferPortTypes(unknownPorts, unknownLinks);
          portTypesChanged = _inferPortTypes;
          boolean _traverseRelations = this.traverseRelations(unknownRelations, unknownLinks, true);
          relationsChanged = _traverseRelations;
          boolean _operator_or = false;
          if (portTypesChanged) {
            _operator_or = true;
          } else {
            _operator_or = BooleanExtensions.operator_or(portTypesChanged, relationsChanged);
          }
          boolean _operator_not = BooleanExtensions.operator_not(_operator_or);
          if (_operator_not) {
            boolean _traverseRelations_1 = this.traverseRelations(unknownRelations, unknownLinks, false);
            randomLinkFixed = _traverseRelations_1;
          }
        }
        boolean _operator_or_1 = false;
        boolean _operator_or_2 = false;
        if (portTypesChanged) {
          _operator_or_2 = true;
        } else {
          _operator_or_2 = BooleanExtensions.operator_or(portTypesChanged, relationsChanged);
        }
        if (_operator_or_2) {
          _operator_or_1 = true;
        } else {
          _operator_or_1 = BooleanExtensions.operator_or(_operator_or_2, randomLinkFixed);
        }
        _dowhile = _operator_or_1;
      } while(_dowhile);
  }
  
  /**
   * Traverses the model, filling the given lists with interesting elements.
   * 
   * @param root the root of the model tree.
   * @param knownPorts list to which ports of known type are added.
   * @param unknownPorts list to which ports of unknown type are added.
   * @param unknownLinks list to which links of unknown direction are added.
   * @param unknownRelations list to which relations with incident links of unknown direction
   *                         are added.
   */
  private void gatherModelElements(final Entity root, final List<Port> knownPorts, final List<Port> unknownPorts, final List<Link> unknownLinks, final List<Relation> unknownRelations) {
      EList<Port> _childPorts = root.getChildPorts();
      for (final Port port : _childPorts) {
        boolean _operator_or = false;
        boolean _isMarkedAsInputPort = this._transformationUtils.isMarkedAsInputPort(port);
        if (_isMarkedAsInputPort) {
          _operator_or = true;
        } else {
          boolean _isMarkedAsOutputPort = this._transformationUtils.isMarkedAsOutputPort(port);
          _operator_or = BooleanExtensions.operator_or(_isMarkedAsInputPort, _isMarkedAsOutputPort);
        }
        if (_operator_or) {
          knownPorts.add(port);
        } else {
          unknownPorts.add(port);
        }
      }
      EList<Link> _childLinks = root.getChildLinks();
      for (final Link link : _childLinks) {
        boolean _isMarkedAsUndirected = this._transformationUtils.isMarkedAsUndirected(link);
        if (_isMarkedAsUndirected) {
          unknownLinks.add(link);
        }
      }
      EList<Relation> _childRelations = root.getChildRelations();
      for (final Relation relation : _childRelations) {
        boolean _hasUnknownIncidentLink = this._transformationUtils.hasUnknownIncidentLink(relation);
        if (_hasUnknownIncidentLink) {
          unknownRelations.add(relation);
        }
      }
      EList<Entity> _childEntities = root.getChildEntities();
      for (final Entity childEntity : _childEntities) {
        this.gatherModelElements(childEntity, knownPorts, unknownPorts, unknownLinks, unknownRelations);
      }
  }
  
  /**
   * For a port of known type, sets the directions of its incident unknown links accordingly. This
   * succeeds if the port is marked as being either an input port or an output port, not both.
   * 
   * @param port the port.
   * @param unknownLinks list of links with unknown direction. Links whose direction is set are
   *                     removed from this list.
   * @return {@code true} if at least one link's direction is fixed or reversed.
   */
  private boolean propagatePortTypeToIncidentLinks(final Port port, final List<Link> unknownLinks) {
    boolean _xblockexpression = false;
    {
      ArrayList<Link> _newArrayList = CollectionLiterals.<Link>newArrayList();
      final List<Link> linksToBeReversed = _newArrayList;
      ArrayList<Link> _newArrayList_1 = CollectionLiterals.<Link>newArrayList();
      final List<Link> linksToBeKept = _newArrayList_1;
      boolean result = false;
      boolean _operator_and = false;
      boolean _isMarkedAsInputPort = this._transformationUtils.isMarkedAsInputPort(port);
      if (!_isMarkedAsInputPort) {
        _operator_and = false;
      } else {
        boolean _isMarkedAsOutputPort = this._transformationUtils.isMarkedAsOutputPort(port);
        boolean _operator_not = BooleanExtensions.operator_not(_isMarkedAsOutputPort);
        _operator_and = BooleanExtensions.operator_and(_isMarkedAsInputPort, _operator_not);
      }
      if (_operator_and) {
        {
          EList<Link> _outgoingLinks = port.getOutgoingLinks();
          linksToBeReversed.addAll(_outgoingLinks);
          EList<Link> _incomingLinks = port.getIncomingLinks();
          linksToBeKept.addAll(_incomingLinks);
        }
      } else {
        boolean _operator_and_1 = false;
        boolean _isMarkedAsOutputPort_1 = this._transformationUtils.isMarkedAsOutputPort(port);
        if (!_isMarkedAsOutputPort_1) {
          _operator_and_1 = false;
        } else {
          boolean _isMarkedAsInputPort_1 = this._transformationUtils.isMarkedAsInputPort(port);
          boolean _operator_not_1 = BooleanExtensions.operator_not(_isMarkedAsInputPort_1);
          _operator_and_1 = BooleanExtensions.operator_and(_isMarkedAsOutputPort_1, _operator_not_1);
        }
        if (_operator_and_1) {
          {
            EList<Link> _incomingLinks_1 = port.getIncomingLinks();
            linksToBeReversed.addAll(_incomingLinks_1);
            EList<Link> _outgoingLinks_1 = port.getOutgoingLinks();
            linksToBeKept.addAll(_outgoingLinks_1);
          }
        }
      }
      for (final Link link : linksToBeReversed) {
        {
          this._transformationUtils.reverseLink(link);
          result = true;
          boolean _isMarkedAsUndirected = this._transformationUtils.isMarkedAsUndirected(link);
          if (_isMarkedAsUndirected) {
            {
              this._transformationUtils.markAsUndirected(link, false);
              unknownLinks.remove(link);
            }
          }
        }
      }
      for (final Link link_1 : linksToBeKept) {
        boolean _isMarkedAsUndirected_1 = this._transformationUtils.isMarkedAsUndirected(link_1);
        if (_isMarkedAsUndirected_1) {
          {
            this._transformationUtils.markAsUndirected(link_1, false);
            unknownLinks.remove(link_1);
            result = true;
          }
        }
      }
      _xblockexpression = (result);
    }
    return _xblockexpression;
  }
  
  /**
   * Iterates over the given list of ports of unknown type and tries to infer the type of as many
   * ports as possible. For ports whose type is inferred, the incident links are fixed accordingly.
   * 
   * @param unknownPorts list of unknown ports. Ports whose type is set are removed from this list.
   * @param unknownLinks list of links with unknown direction. Links whose direction is set are
   *                     removed from this list.
   * @return {@code true} if at least one link's direction is fixed.
   */
  private boolean inferPortTypes(final List<Port> unknownPorts, final List<Link> unknownLinks) {
    boolean _xblockexpression = false;
    {
      boolean result = false;
      ListIterator<Port> _listIterator = unknownPorts.listIterator();
      final ListIterator<Port> unknownPortsIterator = _listIterator;
      boolean _hasNext = unknownPortsIterator.hasNext();
      boolean _while = _hasNext;
      while (_while) {
        {
          Port _next = unknownPortsIterator.next();
          final Port unknownPort = _next;
          EList<Link> _incomingLinks = unknownPort.getIncomingLinks();
          boolean _containsDirectedLink = this._transformationUtils.containsDirectedLink(_incomingLinks);
          if (_containsDirectedLink) {
            this._transformationUtils.markAsInputPort(unknownPort);
          } else {
            EList<Link> _outgoingLinks = unknownPort.getOutgoingLinks();
            boolean _containsDirectedLink_1 = this._transformationUtils.containsDirectedLink(_outgoingLinks);
            if (_containsDirectedLink_1) {
              this._transformationUtils.markAsOutputPort(unknownPort);
            } else {
              boolean _operator_or = false;
              boolean _operator_or_1 = false;
              String _name = unknownPort.getName();
              boolean _equals = _name.equals("in");
              if (_equals) {
                _operator_or_1 = true;
              } else {
                String _name_1 = unknownPort.getName();
                boolean _equals_1 = _name_1.equals("input");
                _operator_or_1 = BooleanExtensions.operator_or(_equals, _equals_1);
              }
              if (_operator_or_1) {
                _operator_or = true;
              } else {
                String _name_2 = unknownPort.getName();
                boolean _equals_2 = _name_2.equals("incomingPort");
                _operator_or = BooleanExtensions.operator_or(_operator_or_1, _equals_2);
              }
              if (_operator_or) {
                this._transformationUtils.markAsInputPort(unknownPort);
              } else {
                boolean _operator_or_2 = false;
                String _name_3 = unknownPort.getName();
                boolean _equals_3 = _name_3.equals("out");
                if (_equals_3) {
                  _operator_or_2 = true;
                } else {
                  String _name_4 = unknownPort.getName();
                  boolean _equals_4 = _name_4.equals("output");
                  _operator_or_2 = BooleanExtensions.operator_or(_equals_3, _equals_4);
                }
                if (_operator_or_2) {
                  this._transformationUtils.markAsOutputPort(unknownPort);
                }
              }
            }
          }
          boolean _operator_or_3 = false;
          boolean _isMarkedAsInputPort = this._transformationUtils.isMarkedAsInputPort(unknownPort);
          if (_isMarkedAsInputPort) {
            _operator_or_3 = true;
          } else {
            boolean _isMarkedAsOutputPort = this._transformationUtils.isMarkedAsOutputPort(unknownPort);
            _operator_or_3 = BooleanExtensions.operator_or(_isMarkedAsInputPort, _isMarkedAsOutputPort);
          }
          if (_operator_or_3) {
            {
              boolean _operator_or_4 = false;
              boolean _propagatePortTypeToIncidentLinks = this.propagatePortTypeToIncidentLinks(unknownPort, unknownLinks);
              if (_propagatePortTypeToIncidentLinks) {
                _operator_or_4 = true;
              } else {
                _operator_or_4 = BooleanExtensions.operator_or(_propagatePortTypeToIncidentLinks, result);
              }
              result = _operator_or_4;
              unknownPortsIterator.remove();
            }
          }
        }
        boolean _hasNext_1 = unknownPortsIterator.hasNext();
        _while = _hasNext_1;
      }
      _xblockexpression = (result);
    }
    return _xblockexpression;
  }
  
  /**
   * Iterates over the list of relations with unknown links and tries to infer link directions. This is
   * done by looking for relations with only incoming or only outgoing links whose direction is known,
   * and links whose direction is unknown. This method can operate in two different modes regarding
   * the number of links with unknown direction.
   * 
   * <p>The first mode is conservative. It only fixes an undirected link's direction if it is the only
   * undirected link incident to a relation. This is the safe mode of operation.</p>
   * 
   * <p>The second mode also accepts relations with more than one incident undirected links. It takes
   * the first of them, sets its direction and returns, thereby only fixing the direction of at most
   * one link in the whole model. This mode is used to fix a link's direction if the other methods
   * cannot infer any more link directions.</p>
   * 
   * @param unknownRelation list of relations with unknown incident links. Relations whose incident
   *                        links are all fixed are removed from this list.
   * @param unknownLinks list of links with unknown direction. Links whose direction is set are
   *                     removed from this list.
   * @param conservative if {@code false}, operates in a mode that fixes a single link more or less
   *                     at random, if possible.
   * @return {@code true} if at least one link's direction is fixed or reversed.
   */
  private boolean traverseRelations(final List<Relation> unknownRelations, final List<Link> unknownLinks, final boolean conservative) {
    boolean _xblockexpression = false;
    {
      boolean result = false;
      ListIterator<Relation> _listIterator = unknownRelations.listIterator();
      final ListIterator<Relation> unknownRelationsIterator = _listIterator;
      boolean _hasNext = unknownRelationsIterator.hasNext();
      boolean _while = _hasNext;
      while (_while) {
        {
          Relation _next = unknownRelationsIterator.next();
          final Relation unknownRelation = _next;
          boolean fixedLinkInThisIteration = false;
          EList<Link> _incomingLinks = unknownRelation.getIncomingLinks();
          final Function1<Link,Boolean> _function = new Function1<Link,Boolean>() {
              public Boolean apply(final Link l) {
                boolean _isMarkedAsUndirected = Ptolemy2KaomOptimization.this._transformationUtils.isMarkedAsUndirected(l);
                boolean _operator_not = BooleanExtensions.operator_not(_isMarkedAsUndirected);
                return Boolean.valueOf(_operator_not);
              }
            };
          Iterable<Link> _filter = IterableExtensions.<Link>filter(_incomingLinks, _function);
          final Iterable<Link> fixedIncomingLinks = _filter;
          EList<Link> _outgoingLinks = unknownRelation.getOutgoingLinks();
          final Function1<Link,Boolean> _function_1 = new Function1<Link,Boolean>() {
              public Boolean apply(final Link l) {
                boolean _isMarkedAsUndirected = Ptolemy2KaomOptimization.this._transformationUtils.isMarkedAsUndirected(l);
                boolean _operator_not = BooleanExtensions.operator_not(_isMarkedAsUndirected);
                return Boolean.valueOf(_operator_not);
              }
            };
          Iterable<Link> _filter_1 = IterableExtensions.<Link>filter(_outgoingLinks, _function_1);
          final Iterable<Link> fixedOutgoingLinks = _filter_1;
          List<Link> _incidentLinks = this._transformationUtils.getIncidentLinks(unknownRelation);
          final Function1<Link,Boolean> _function_2 = new Function1<Link,Boolean>() {
              public Boolean apply(final Link l) {
                boolean _isMarkedAsUndirected = Ptolemy2KaomOptimization.this._transformationUtils.isMarkedAsUndirected(l);
                return Boolean.valueOf(_isMarkedAsUndirected);
              }
            };
          Iterable<Link> _filter_2 = IterableExtensions.<Link>filter(_incidentLinks, _function_2);
          final Iterable<Link> undirectedIncidentLinks = _filter_2;
          int _size = IterableExtensions.size(undirectedIncidentLinks);
          final int undirectedIncidentLinksSize = _size;
          boolean _operator_or = false;
          boolean _operator_and = false;
          if (!conservative) {
            _operator_and = false;
          } else {
            boolean _operator_equals = IntegerExtensions.operator_equals(undirectedIncidentLinksSize, 1);
            _operator_and = BooleanExtensions.operator_and(conservative, _operator_equals);
          }
          if (_operator_and) {
            _operator_or = true;
          } else {
            boolean _operator_and_1 = false;
            boolean _operator_not = BooleanExtensions.operator_not(conservative);
            if (!_operator_not) {
              _operator_and_1 = false;
            } else {
              boolean _operator_greaterThan = IntegerExtensions.operator_greaterThan(undirectedIncidentLinksSize, 0);
              _operator_and_1 = BooleanExtensions.operator_and(_operator_not, _operator_greaterThan);
            }
            _operator_or = BooleanExtensions.operator_or(_operator_and, _operator_and_1);
          }
          if (_operator_or) {
            {
              Iterator<Link> _iterator = undirectedIncidentLinks.iterator();
              Link _next_1 = _iterator.next();
              final Link undirectedLink = _next_1;
              boolean _operator_and_2 = false;
              int _size_1 = IterableExtensions.size(fixedIncomingLinks);
              boolean _operator_greaterThan_1 = IntegerExtensions.operator_greaterThan(_size_1, 0);
              if (!_operator_greaterThan_1) {
                _operator_and_2 = false;
              } else {
                int _size_2 = IterableExtensions.size(fixedOutgoingLinks);
                boolean _operator_equals_1 = IntegerExtensions.operator_equals(_size_2, 0);
                _operator_and_2 = BooleanExtensions.operator_and(_operator_greaterThan_1, _operator_equals_1);
              }
              if (_operator_and_2) {
                {
                  Linkable _source = undirectedLink.getSource();
                  boolean _operator_notEquals = ObjectExtensions.operator_notEquals(_source, unknownRelation);
                  if (_operator_notEquals) {
                    this._transformationUtils.reverseLink(undirectedLink);
                  }
                  this._transformationUtils.markAsUndirected(undirectedLink, false);
                  unknownLinks.remove(undirectedLink);
                  fixedLinkInThisIteration = true;
                }
              } else {
                boolean _operator_and_3 = false;
                int _size_3 = IterableExtensions.size(fixedOutgoingLinks);
                boolean _operator_greaterThan_2 = IntegerExtensions.operator_greaterThan(_size_3, 0);
                if (!_operator_greaterThan_2) {
                  _operator_and_3 = false;
                } else {
                  int _size_4 = IterableExtensions.size(fixedIncomingLinks);
                  boolean _operator_equals_2 = IntegerExtensions.operator_equals(_size_4, 0);
                  _operator_and_3 = BooleanExtensions.operator_and(_operator_greaterThan_2, _operator_equals_2);
                }
                if (_operator_and_3) {
                  {
                    Linkable _target = undirectedLink.getTarget();
                    boolean _operator_notEquals_1 = ObjectExtensions.operator_notEquals(_target, unknownRelation);
                    if (_operator_notEquals_1) {
                      this._transformationUtils.reverseLink(undirectedLink);
                    }
                    this._transformationUtils.markAsUndirected(undirectedLink, false);
                    unknownLinks.remove(undirectedLink);
                    fixedLinkInThisIteration = true;
                  }
                }
              }
            }
          }
          if (fixedLinkInThisIteration) {
            {
              result = true;
              boolean _operator_equals_3 = IntegerExtensions.operator_equals(undirectedIncidentLinksSize, 1);
              if (_operator_equals_3) {
                unknownRelationsIterator.remove();
              }
              boolean _operator_not_1 = BooleanExtensions.operator_not(conservative);
              if (_operator_not_1) {
                return result;
              }
            }
          }
        }
        boolean _hasNext_1 = unknownRelationsIterator.hasNext();
        _while = _hasNext_1;
      }
      _xblockexpression = (result);
    }
    return _xblockexpression;
  }
  
  /**
   * Removes ports from entities representing modal model states in the model rooted at the given root
   * entity. Also, entities containing such states are annotated for the layout algorithm to know to
   * use a layout algorithm for state machines.
   * 
   * @param root the model tree's root entity.
   */
  private void makeStatesPortless(final Entity root) {
      String _stringAnnotationValue = this._transformationUtils.getStringAnnotationValue(root, "ptolemyClass");
      boolean _equals = _stringAnnotationValue.equals("ptolemy.domains.modal.kernel.State");
      if (_equals) {
        {
          EList<Port> _childPorts = root.getChildPorts();
          for (final Port port : _childPorts) {
            List<Link> _incidentLinks = this._transformationUtils.getIncidentLinks(port);
            for (final Link link : _incidentLinks) {
              Linkable _source = link.getSource();
              boolean _operator_equals = ObjectExtensions.operator_equals(_source, port);
              if (_operator_equals) {
                link.setSource(root);
              } else {
                link.setTarget(root);
              }
            }
          }
          EList<Port> _childPorts_1 = root.getChildPorts();
          _childPorts_1.clear();
          EObject _eContainer = root.eContainer();
          this._transformationUtils.addStringAnnotation(((Entity) _eContainer), "DiagramType", "StateMachine");
        }
      }
      EList<Entity> _childEntities = root.getChildEntities();
      for (final Entity childEntity : _childEntities) {
        this.makeStatesPortless(childEntity);
      }
  }
  
  /**
   * Removes unnecessary relations in the model rooted at the given entity. Unnecessary relations are
   * those that have one incoming and one outgoing link.
   * 
   * @param root the model's root entity.
   */
  private void removeUnnecessaryRelations(final Entity root) {
      EList<Relation> _childRelations = root.getChildRelations();
      ListIterator<Relation> _listIterator = _childRelations.listIterator();
      final ListIterator<Relation> relationsIterator = _listIterator;
      boolean _hasNext = relationsIterator.hasNext();
      boolean _while = _hasNext;
      while (_while) {
        {
          Relation _next = relationsIterator.next();
          final Relation relation = _next;
          boolean _operator_and = false;
          EList<Link> _incomingLinks = relation.getIncomingLinks();
          int _size = _incomingLinks.size();
          boolean _operator_equals = IntegerExtensions.operator_equals(_size, 1);
          if (!_operator_equals) {
            _operator_and = false;
          } else {
            EList<Link> _outgoingLinks = relation.getOutgoingLinks();
            int _size_1 = _outgoingLinks.size();
            boolean _operator_equals_1 = IntegerExtensions.operator_equals(_size_1, 1);
            _operator_and = BooleanExtensions.operator_and(_operator_equals, _operator_equals_1);
          }
          if (_operator_and) {
            {
              EList<Link> _incomingLinks_1 = relation.getIncomingLinks();
              Link _get = _incomingLinks_1.get(0);
              final Link inLink = _get;
              EList<Link> _outgoingLinks_1 = relation.getOutgoingLinks();
              Link _get_1 = _outgoingLinks_1.get(0);
              final Link outLink = _get_1;
              Linkable _target = outLink.getTarget();
              inLink.setTarget(_target);
              outLink.setSource(null);
              outLink.setTarget(null);
              EObject _eContainer = outLink.eContainer();
              EList<Link> _childLinks = ((Entity) _eContainer).getChildLinks();
              _childLinks.remove(outLink);
              relationsIterator.remove();
            }
          }
        }
        boolean _hasNext_1 = relationsIterator.hasNext();
        _while = _hasNext_1;
      }
      EList<Entity> _childEntities = root.getChildEntities();
      for (final Entity childEntity : _childEntities) {
        this.removeUnnecessaryRelations(childEntity);
      }
  }
  
  /**
   * Converts certain annotations into entities of their own right. In particular, Ptolemy directors
   * are persisted as annotations in Ptolemy models, but need to be entities in KAOM models to be
   * correctly displayed.
   * 
   * @param root root element of the model to look for convertible annotations in.
   */
  private void convertAnnotationsToEntities(final Entity root) {
      boolean _isMarkedAsFormerAnnotationEntity = this._transformationUtils.isMarkedAsFormerAnnotationEntity(root);
      if (_isMarkedAsFormerAnnotationEntity) {
        return;
      }
      EList<Annotation> _annotations = root.getAnnotations();
      ListIterator<Annotation> _listIterator = _annotations.listIterator();
      final ListIterator<Annotation> annotationsIterator = _listIterator;
      boolean _hasNext = annotationsIterator.hasNext();
      boolean _while = _hasNext;
      while (_while) {
        {
          Annotation _next = annotationsIterator.next();
          final Annotation annotation = _next;
          if ((annotation instanceof TypedStringAnnotation)) {
            {
              final TypedStringAnnotation tsAnnotation = ((TypedStringAnnotation) annotation);
              String _type = tsAnnotation.getType();
              boolean _endsWith = _type.endsWith("Director");
              if (_endsWith) {
                {
                  Entity _createEntity = KaomFactory.eINSTANCE.createEntity();
                  final Entity directorEntity = _createEntity;
                  String _name = tsAnnotation.getName();
                  directorEntity.setName(_name);
                  this._transformationUtils.addStringAnnotation(directorEntity, "language", "ptolemy");
                  this._transformationUtils.addAnnotation(directorEntity, "Director");
                  this._transformationUtils.markAsFormerAnnotationEntity(directorEntity, true);
                  annotationsIterator.remove();
                  EList<Annotation> _annotations_1 = directorEntity.getAnnotations();
                  _annotations_1.add(tsAnnotation);
                  EList<Entity> _childEntities = root.getChildEntities();
                  _childEntities.add(directorEntity);
                }
              }
            }
          }
        }
        boolean _hasNext_1 = annotationsIterator.hasNext();
        _while = _hasNext_1;
      }
      EList<Entity> _childEntities = root.getChildEntities();
      for (final Entity childEntity : _childEntities) {
        this.convertAnnotationsToEntities(childEntity);
      }
  }
}
