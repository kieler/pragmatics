{
  active: true,
  name: 'ValidLayoutTest',
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
      "target": "n3"
    }]
  },
  check: function(result) {
    return result.id === 'root';
  },
  errorMsg: function(result) {
    return 'Valid graph should be successfully layouted.\nError was: ' + result.type;
  }
}