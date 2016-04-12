{
  active: true,
  name: 'LabelPositionTest2',
  options: {
    intCoordinates: true
  },
  graph: {
    "id": "root",
    "labels": [
        {
            "text": "root"
        }
    ],
    "edges": [
        {
            "id": "off_on",
            "source": "off",
            "target": "on",
            "labels": [
                {
                    "text": "touch",
                    "width": 10.140625,
                    "height": 5
                }
            ]
        },
        {
            "id": "on_off",
            "source": "on",
            "target": "off",
            "labels": [
                {
                    "text": "touch",
                    "width": 10.140625,
                    "height": 5
                }
            ]
        }
    ],
    "width": 10,
    "height": 10,
    "children": [
        {
            "id": "off",
            "labels": [
                {
                    "text": "off"
                }
            ],
            "edges": [],
            "width": 10,
            "height": 10
        },
        {
            "id": "on",
            "labels": [
                {
                    "text": "on"
                }
            ],
            "edges": [],
            "width": 10,
            "height": 10
        }
    ]
},
  expected: {  },
  check: function(result) {
    // valid result?
    if (typeof result.id === 'undefined' || result.id != 'root') {
      return false;
    }

    // console.log(JSON.stringify(result, null, " "));
    // check if there are positions
    var valid = true;

    // in this example both labels should be positioned at x 49
    result.edges.forEach(function(edge) {
      if (edge.labels) {
        edge.labels.forEach(function(label) {
          valid &= label.x == 49;
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