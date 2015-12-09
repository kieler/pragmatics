#!/usr/bin/env node
var reporters = require('nodeunit').reporters;

// Parse cmdline paramters
var args = process.argv.reduce(function(result, element) {
  var v = element.split('=');
  result[v[0]] = v[1];
  return result;
}, {});


if ( args.testReporter === 'junit' ) {

  var reporter = reporters.junit;

  reporter.run(['src/test_wrapper.js'], {
    output: 'build/test-reports',
    //defaults from node_modules/bin/nodeunit.json
    "error_prefix": "\u001B[31m",
    "error_suffix": "\u001B[39m",
    "ok_prefix": "\u001B[32m",
    "ok_suffix": "\u001B[39m",
    "bold_prefix": "\u001B[1m",
    "bold_suffix": "\u001B[22m",
    "assertion_prefix": "\u001B[35m",
    "assertion_suffix": "\u001B[39m"
  });

} else {

  var reporter = reporters.default;

  reporter.run(['src/test_wrapper.js']);

}
