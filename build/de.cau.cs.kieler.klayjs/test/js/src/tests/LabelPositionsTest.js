{
  active: false, /* currently there is a bug ... */
  name: 'LabelPositionTest',
  options: { },
  graph: {
    "id": "root",
    "properties": {
        "direction": "DOWN",
        "spacing": 40
    },
    "children": [
      {
        "id": "n1",
        "width": 40,
        "height": 40,
        "labels": [{"text": "Label-Node-n1", "width": 10, "height": 30}],
        "properties": {
          "sizeConstraint": "MINIMUM_SIZE NODE_LABELS",
          "nodeLabelPlacement": "INSIDE V_CENTER H_CENTER"
        }
      }, {
        "id": "n2",
        "width": 40,
        "height": 40
      }, {
        "id": "n3",
        "width": 40,
        "height": 40
      }
    ],
    "edges": [
      {
        "id": "e1",
        "source": "n1",
        "target": "n2",
        "labels": [{"text": "Label-Edge-n1_n2"}]
      },
      {
        "id": "e2",
        "source": "n1",
        "target": "n3"
      },
      {
        "id": "e3",
        "source": "n2",
        "target": "n3"
      }
    ]
  },
  expected: {  },
  check: function(result) {
    // valid result?
    if (typeof result.id === 'undefined' || result.id != 'root') {
      return false;
    }
    // check if there are positions
    var valid = true;

    // in this example every label has to have a position different from (0, 0)
    result.children.forEach(function(child) {
      if (child.labels) {
        child.labels.forEach(function(label) {
          valid &= label.x != 0;
          valid &= label.y != 0;
        });
      }
    });

    result.edges.forEach(function(edge) {
      if (edge.labels) {
        edge.labels.forEach(function(label) {
          valid &= label.x != 0;
          valid &= label.y != 0;
        });
      }
    });

    return valid;
  },
  errorMsg: function(result) {
    return "There are labels without a position.";
  }
}