var graphs = require('../graphs.js');
var klay = require('../klay.js');

exports.testSuccessfulLayout = function(test) {
  test.expect(1);

  klay.layout({
    graph: graphs.valid_graph,
    options: {
      spacing: 50
    },
    success: function(result){
      console.log(result);
      test.ok(true, 'Valid graph successful layouted.');
    },
    error: function(result){
      test.ok(false, 'Valid graph layout failed with: ' + result.type + '\n' + result.text);
    }
  });

  test.done();
};

exports.testInvalidLayout = function(test) {
  test.expect(1);

  klay.layout({
    graph: graphs.invalid_graph,
    options: {
      spacing: 50
    },
    success: function(result){
      test.ok(false, 'Invalid graph should not be successful layouted.: ' + result);
    },
    error: function(result){
      test.ok(true, 'Invalid graph layout failed as expected');
    }
  });

  test.done();
};
