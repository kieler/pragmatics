# Testing in JavaScript

The tests are all run from [nodejs][https://nodejs.org/] with different inbetween layers of runners and/or frameworks.
All framework dependencies are loaded via [npm][https://www.npmjs.com/].

To execute the tests, from the project's root directory, run ```./gradlew testAll```.
To get more information on available tasks, run ```./gradlew help``` or ```./gradlew tasks```.

There are three types of loading KlayJs which are to be tested.

## Loading via plain JavaScript inside an html file

The used testing framework here is [QUnit][https://qunitjs.com/].
The tests are run via [Grunt][http://gruntjs.com/] (a JavaScript taskrunner) which uses [PhantomJS][http://phantomjs.org/] to load the test page.
The Grunt plugin [QUnit JUnit][https://github.com/sbrandwoo/grunt-qunit-junit] is used to generate JUnit compatible xml reports which are then parsed by our Bamboo CI server.

## Loading via nodejs

The used testing framework here is [nodeunit][https://github.com/caolan/nodeunit] which merely exports the build-in assert function that nodejs already provides but provides an additional reporter to produce JUnit compatible xml reports.
These are again parsed by our Bamboo CI server.

## Loading via webworkers

The used testing framework is again nodeunit.
To provide webworker-functionality inside nodejs, the [WebWorker Threads][https://github.com/audreyt/node-webworker-threads] module is used which in turn requires [node gyp][https://github.com/nodejs/node-gyp] which provides cross-platform compilation chains for native addon modules.
