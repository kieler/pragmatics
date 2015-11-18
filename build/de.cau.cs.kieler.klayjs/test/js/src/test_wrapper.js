var tests;
if (typeof tests === 'undefined') {
  tests = require('./tests.js');
}

tests.forEach(function(config){
  if (config.active) {
    // browser context
    if (typeof document !== 'undefined') {
      testInBrowser(config);
    }
    // nodejs context
    else if (module && module.exports) {
      // Parse cmdline paramters for given context
      // console.log('before:');
      // console.log(process.argv);
      var args = process.argv.reduce(function(result, element) {
        var v = element.split('=');
        // console.log('split:');
        // console.log(v);
        result[v[0]] = v[1];
        return result;
      }, {});
      // console.log('after:');
      // console.log(process.argv);
      // console.log('args:');
      // console.log(args);
      switch (args.context) {
        case 'webworker':
          testInWebworker(config);
          break;
        case 'nodejs':
          testInNodejs(config);
          break;
        default:
          throw new Error('No valid context given: ' + args.context + '\nExpected one of nodejs,webworker or embed in html.');
      }
    }
    // no known context
    else {
      throw new Error('No known context available!');
    }
  }
});

function testInBrowser(config) {
  QUnit.test('[browser]' + config.name, function(assert) {
    expect(1);
    var done = assert.async();

    $klay.layout({
      graph: config.graph,
      options: config.options,
      success: function(result){
        assert.ok(config.check(result), config.error_msg(result));
      },
      error: function(result){
        assert.ok(config.check(result), config.error_msg(result));
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
        assert.ok(config.check(result), config.error_msg(result));
      },
      error: function(result){
        assert.ok(config.check(result), config.error_msg(result));
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

    worker.postMessage({
      graph: config.graph,
      options: config.options
    });
    var timeout = setTimeout(function(){ assert.done(); }, 10000);

    worker.addEventListener('message', function (e) {
      var result = e.data;
      assert.ok(config.check(result), config.error_msg(result));
      worker.terminate();
      clearTimeout(timeout);
      assert.done();
    });
  };
};
