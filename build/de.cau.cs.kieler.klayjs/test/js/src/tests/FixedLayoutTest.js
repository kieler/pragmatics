{
  active: true,
  name: 'FixedLayoutTest',
  options: {
    algorithm: 'de.cau.cs.kieler.fixed',
    layoutHierarchy: false
  },
  graph: {
    "id": "root",
    "children": [
      {
        "id": "n3",
        "labels": [
          {
            "text": "n3"
          }
        ],
        "width": 40,
        "height": 40,
        "x": 20,
        "y": 40
      },
      {
        "id": "n4",
        "labels": [
          {
            "text": "n4"
          }
        ],
        "width": 40,
        "height": 40,
        "x": 140,
        "y": 95
      }
    ],
    "edges": [
      {
        "id": "e4",
        "source": "n3",
        "target": "n4",
        "properties": {
          "bendPoints": "60, 60, 80, 10, 140, 115"
        }
      }
    ]
  },
  expected: {
    n3: {
      x: 20,
      y: 40
    },
    n4: {
      x: 140,
      y: 95
    },
    e4: {
      "sourcePoint": {
        "x": 60,
        "y": 60
      },
      "targetPoint": {
        "x": 140,
        "y": 115
      },
      "bendPoints": [
        {
          "x": 80,
          "y": 10
        }
      ]
    }
  },
  check: function(result) {
    if (typeof result.id === 'undefined' || result.id != 'root') {
      return false;
    }
    function pointsEqual(pointA, pointB) {
      var equal = pointA.x == pointB.x && pointA.y == pointB.y;
      return equal;
    }
    // console.log(JSON.stringify(result));
    var expected = this.expected;
    var valid = true;
    result.children.forEach(function(child) {
      valid &= pointsEqual(child, expected[child.id]);
    });
    result.edges.forEach(function(edge) {
      valid &= pointsEqual(edge.sourcePoint, expected[edge.id].sourcePoint);
      valid &= pointsEqual(edge.targetPoint, expected[edge.id].targetPoint);
      for (var i = 0; i < edge.bendPoints.length; i++) {
        valid &= pointsEqual(edge.bendPoints[i], expected[edge.id].bendPoints[i]);
      }
    });

    return valid;
  },
  errorMsg: function(result) {
    return 'Positions of nodes and edge bendpoints should remain the same!';
  }
}