We're using gradle to build and bundle our layouters to libraries which are usable from plain Java projects.

For information on how to build them, run ```./gradlew help```.
These libraries are build in three different configurations (see [here](build/de.cau.cs.kieler.klay.libraries/deploy/README.md) for more details on the produced artifacts).

# Bundled KLay

Bundle of our layouters with or without dependencies to use with our KGraph format.

# Plugin-like KLay

Collection of our layouters (with dependencies) in separate jars. Uses the KGraph format too.

# Plugin-like KLay with formats support

Collection of our layouters (with dependencies) in separate jars including our JSON importer.

This configuration uses the nonosgi-wrapper to load the registered layout algorithms via extension points in an non-eclipse environment.
