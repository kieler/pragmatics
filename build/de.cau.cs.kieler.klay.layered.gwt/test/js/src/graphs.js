//if not loaded in nodejs, add gradphs to a new exports-dummy
if (!exports) {
  var exports = {}
}

// assemble a graph
exports.valid_graph = {
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
};

// invalid graph
exports.invalid_graph = {
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
};