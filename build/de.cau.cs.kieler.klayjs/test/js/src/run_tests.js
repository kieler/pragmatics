#!/usr/bin/env node
var reporter = require('nodeunit').reporters.junit;
reporter.run(['src/test_wrapper.js'],{
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
