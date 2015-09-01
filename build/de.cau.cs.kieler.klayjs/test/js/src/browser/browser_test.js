QUnit.module('browser_test');
QUnit.test("Test for successful layout", function(assert) {
  expect(1);
  var done = assert.async();

  $klay.layout({
    graph: exports.valid_graph,
    options: {
      spacing: 50
    },
    success: function(result){
      assert.ok(true, 'Valid graph successful layouted.');
    },
    error: function(result){
      assert.ok(false, 'Valid graph layout failed with: ' + result.type + '\n' + result.text);
    }
  });

  done();
});

QUnit.test("Test for expectedly failed layout", function(assert) {
  expect(1);
  var done = assert.async();

  $klay.layout({
    graph: exports.invalid_graph,
    options: {
      spacing: 50
    },
    success: function(result){
      assert.ok(false, 'Invalid graph should not be successful layouted.: ' + result);
    },
    error: function(result){
      assert.ok(true, 'Invalid graph layout failed as expected');
    }
  });

  done();
});
