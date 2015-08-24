module.exports = function(grunt) {

  // Project configuration.
  grunt.initConfig({
    pkg: grunt.file.readJSON('package.json'),
    qunit: {
      all: ['klay_test.html']
    },
    qunit_junit: {
      options: {
        dest: 'build/test-reports'
      }
    }
  });

  // Load required plugins.
  grunt.loadNpmTasks('grunt-contrib-qunit');
  grunt.loadNpmTasks('grunt-qunit-junit');

  // Default task(s).
  grunt.registerTask('default', ['qunit_junit', 'qunit']);

};
