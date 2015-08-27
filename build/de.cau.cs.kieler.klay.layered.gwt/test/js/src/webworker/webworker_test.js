var Worker = require('webworker-threads').Worker;
var graphs = require('../graphs.js');


exports.testSuccessfulLayout = function(test) {
  var worker = new Worker('src/klay.js');
  test.expect(1);

  worker.postMessage({
    graph: graphs.valid_graph,
    options: {
      spacing: 50
    }
  });

  var timeout = setTimeout(function(){ test.done(); }, 10000);
  worker.addEventListener('message', function (e) {
    var result = e.data;
    test.equal(result.id, 'root', 'Valid graph should be successfully layouted.');
    worker.terminate();
    clearTimeout(timeout);
    test.done();
  });
};

exports.testInvalidLayout = function(test) {
  var worker = new Worker('src/klay.js');
  test.expect(1);

  worker.postMessage({
    graph: graphs.invalid_graph,
    options: {
      spacing: 50
    }
  });

  var timeout = setTimeout(function(){ test.done(); }, 10000);
  worker.addEventListener('message', function (e) {
    var result = e.data;
    test.equal(result.type, 'de.cau.cs.kieler.klay.gwt.client.layout.UnsupportedJsonGraphException', 'Invalid graph should not be successful layouted.');
    worker.terminate();
    clearTimeout(timeout);
    test.done();
  });
};
