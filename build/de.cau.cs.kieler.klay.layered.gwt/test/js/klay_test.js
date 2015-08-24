// assemble a graph
var graph = {
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
var invalid_graph = {
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

function testLayout(graph, callback_success, callback_error) {
  if (!callback_error) {
    callback_error = callback_success;
  }
  $klay.layout({
    graph: graph,
    options: {
      spacing: 50
    },
    success: callback_success,
    error: callback_error
  });
}

QUnit.jUnitReport = function(report) {
    console.log(report.xml);
};

QUnit.test("klayJS layout finished", function(assert) {
  var done = {};

  var checkResult = function(result, expected, msg) {
      console.log(result);
      // assert.equal(result.id, "root", "Layout successfully finished");
      assert.equal(result, expected, msg);
      done();
    }
    // execute the layout
  done = assert.async();
  testLayout(graph, function(result) {
    checkResult(result.id, "root", "Expected successful layout");
  })
  done = assert.async();
  testLayout(invalid_graph, function(result) {
    checkResult(result.type, "de.cau.cs.kieler.klay.gwt.client.layout.UnsupportedJsonGraphException", "Expected invalid graph");
  })
});

