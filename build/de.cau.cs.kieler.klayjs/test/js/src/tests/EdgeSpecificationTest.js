/*
 * In the json format, edges are allowed to be specified
 * anywhere, for instance:
 *  - every edge at the root node
 *  - edges at the source node
 *  - edges at nodes that have nothing to do with
 *    the edge itself, be it a compound node or an atomic one
 */
{
  active: true,
  name: 'EdgeSpecificationTest',
  options: {
    "intCoordinates": true
  },
  graph: {
    "id" : "root",
    "properties" : {
      "direction" : "RIGHT",
      "spacing": 20,
      "inLayerSpacingFactor": 0.5
    },
    "children" : [ {
      "id" : "n1",
      "width" : 40,
      "height" : 40,
      "edges" : [ {
          "id" : "e12_1",
          "source" : "n1",
          "target" : "n2"
      } ]
    }, {
      "id" : "n2",
      "width" : 40,
      "height" : 40,
      "edges" : [ {
          "id" : "e23",
          "source" : "n2",
          "target" : "n3"
      }, {
          "id" : "e31_1",
          "source" : "n3",
          "target" : "n1"
      } ]
    }, {
      "id" : "n3",
      "width" : 40,
      "height" : 40,
      "edges" : [ {
          "id" : "e23_2",
          "source" : "n2",
          "target" : "n3"
      } ]
    } ],
    "edges" : [ {
      "id" : "e12_2",
      "source" : "n1",
      "target" : "n2"
    }, {
      "id" : "e13_1",
      "source" : "n1",
      "target" : "n3"
    } ]
  },
  expected: {
    e12_1: {
      "sourcePoint": { "x": 52, "y": 33 },
      "targetPoint": { "x": 89, "y": 38 },
      "bendPoints": [ { "x": 65, "y": 33 }, { "x": 65, "y": 38 } ]
    },
    e23: {
      "sourcePoint": { "x": 129, "y": 25 },
      "targetPoint": { "x": 167, "y": 25 }
    },
    e31_1: {
      "sourcePoint": { "x": 167, "y": 49 },
      "targetPoint": { "x": 52, "y": 49 },
      "bendPoints": [{ "x": 153, "y": 49 }, { "x": 153, "y": 64 }, { "x": 65, "y": 64 }, { "x": 65, "y": 49 } ]
    },
    e23_2: {
      "sourcePoint": { "x": 129, "y": 38 },
      "targetPoint": { "x": 167, "y": 33 },
      "bendPoints": [{ "x": 143, "y": 38 }, { "x": 143, "y": 33 } ]
    },
    e12_2: {
      "sourcePoint": { "x": 52, "y": 25 },
      "targetPoint": { "x": 89, "y": 25 }
    },
    e13_1: {
      "sourcePoint": { "x": 52, "y": 41 },
      "targetPoint": { "x": 167, "y": 41 },
      "bendPoints": [{ "x": 75, "y": 41 }, { "x": 75, "y": 58 }, { "x": 143, "y": 58 }, { "x": 143, "y": 41 } ]
    }
 },
  pointsEqual: function(pointA, pointB) {
      var equal = pointA.x == pointB.x && pointA.y == pointB.y;
      return equal;
  },
  checkEdges: function(node) {
    var _this = this;
    var valid = true;
    // check this node's edges
    if (node.edges) {
      node.edges.forEach(function(edge) {
        valid &= _this.pointsEqual(edge.sourcePoint, _this.expected[edge.id].sourcePoint);
        valid &= _this.pointsEqual(edge.targetPoint, _this.expected[edge.id].targetPoint);
        if (edge.bendPoints) {
          for (var i = 0; i < edge.bendPoints.length; i++) {
            valid &= _this.pointsEqual(edge.bendPoints[i], _this.expected[edge.id].bendPoints[i]);
          }
        }
      });
    }

    // recurse into the children
    if (node.children) {
      node.children.forEach(function (child) {
        valid &= _this.checkEdges.call(_this, child);
      });
    }

    return valid;
  },
  check: function(result) {
    if (typeof result.id === 'undefined' || result.id != 'root') {
      return false;
    }

    // console.log(JSON.stringify(result, null, " "));
    var success = this.checkEdges.call(this, result);
    return success;
  },
  errorMsg: function(result) {
    return 'A problem with edge specifications occured.\nError was: ' + result.type;
  }
}