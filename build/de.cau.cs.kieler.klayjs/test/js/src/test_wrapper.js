// wrapper functions for each context (browser,nodejs,webworker)
// -------------------------------------------------------------

function testInBrowser(config) {
  QUnit.test('[browser]' + config.name, function(assert) {
    expect(1);
    var done = assert.async();

    $klay.layout({
      graph: config.graph,
      options: config.options,
      success: function(result){
        assert.ok(config.check(result), config.errorMsg(result));
      },
      error: function(result){
        assert.ok(config.check(result), config.errorMsg(result));
      }
    });

    done();
  });
};

function testInNodejs(config) {
  exports['[nodejs]' + config.name] = function(assert) {
    var klay = require('./klay.js');
    assert.expect(1);

    klay.layout({
      graph: config.graph,
      options: config.options,
      success: function(result){
        assert.ok(config.check(result), config.errorMsg(result));
      },
      error: function(result){
        assert.ok(config.check(result), config.errorMsg(result));
      }
    });

    assert.done();
  };
};

function testInWebworker(config) {
  exports['[webworker]' + config.name] = function(assert) {
    var Worker = require('webworker-threads').Worker;
    var worker = new Worker('src/klay.js');
    assert.expect(1);

    var timeout = setTimeout(function() {
      worker.terminate();
      assert.done();
    }, 10000);

    worker.addEventListener('message', function (e) {
      var result = e.data;
      assert.ok(config.check(result), config.errorMsg(result));
      worker.terminate();
      clearTimeout(timeout);
      assert.done();
    });

    worker.postMessage({
      graph: config.graph,
      options: config.options
    });
  };
};

// Determine context, active tasks and call corresponding wrapper function
// -----------------------------------------------------------------------

var tests;
if (typeof tests === 'undefined') {
  tests = require('./tests.js');
}
var testInContext;
var args;

// browser context
if (typeof document !== 'undefined') {
  testInContext = testInBrowser;
}

// nodejs context
else if (module && module.exports) {
  // Parse cmdline paramters
  args = process.argv.reduce(function(result, element) {
    var v = element.split('=');
    result[v[0]] = v[1];
    return result;
  }, {});
  // Determine cintext
  switch (args.context) {
    case 'webworker':
      testInContext = testInWebworker;
      break;
    case 'nodejs':
      testInContext = testInNodejs;
      break;
    default:
      throw new Error('No valid context given: ' + args.context + '\nExpected one of nodejs,webworker or embed in html.');
  }
}

// no known context
else {
  throw new Error('No known context available!');
}

// run all active tests
tests.forEach(function(config){
  // Execute test if active flag is not present or true.
  if (typeof config.active === 'undefined' || config.active) {
    // If explicit test is given only execute test if name matches.
    if (typeof args.test === 'undefined' || args.test == config.name) {
      testInContext(config);
    }
  }
});
