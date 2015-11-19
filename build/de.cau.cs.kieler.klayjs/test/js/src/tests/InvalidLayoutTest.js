{
  active: true,
  name: 'InvalidLayoutTest',
  graph: {
    "id": "root",
    "properties": {
      "direction": "DOWN",
      "spacing": 40
    },
    "children": [{
      "id": "n1",
      "width": 40,
      "height": 40
    }, {
      "id": "n2",
      "width": 40,
      "height": 40
    }, {
      "id": "n3",
      "width": 40,
      "height": 40
    }],
    "edges": [{
      "id": "e1",
      "source": "n1",
      "target": "n2"
    }, {
      "id": "e2",
      "source": "n1",
      "target": "n3"
    }, {
      "id": "e3",
      "source": "n2",
      "target": "62"
    }]
  },
  check: function(result) {
    return result.type === 'de.cau.cs.kieler.klay.gwt.client.layout.UnsupportedJsonGraphException';
  },
  error_msg: function(result) {
    return 'Invalid graph should not be successful layouted!';
  }
}