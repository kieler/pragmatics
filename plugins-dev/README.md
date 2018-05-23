
# Reasons the plugins exist

| Plugin(s) | Description |
|-----------|-------------|
| `de.cau.cs.kieler.formats.*` | The plugins are usually required to open the underlying graph of some model type (e.g. an SCChart) as part of a GrAna analysis. Note that to create the underlying graph, in most cases a KLighD synthesis will be invoked. |
| `de.cau.cs.kieler.graphs.hierarchicalViews` | The plugin contains the code that has been created during the [WS 16/17 Layout project](https://rtsys.informatik.uni-kiel.de/confluence/pages/viewpage.action?pageId=18776118), which allows to view hierarchical models in an alternative fashion. |
| `de.cau.cs.kieler.graphs.testcases` | The plugin can be used to convert a set of models to plain graphs in a batch-like fashion. An application for this is to assemble a benchmark set. |
| `de.cau.cs.kieler.kiml.adaptagrams` | The plugin contains a [SWIG](http://www.swig.org/)-based bridge to the [adaptagrams](http://www.adaptagrams.org/) algorithms. It additionally requires the `org.adaptagrams` plugin from the `pragmatics-libraries` repository, which contains the necessary binaries. |
| `de.cau.cs.kieler.klighd.offscreen.application` | A simple example on how to perform a batch-like offscreen execution of a KLighD synthesis for a set of models. |
| `de.cau.cs.kieler.klay.debugview` | The plugin contains extended debug facilities for the ELK Layered algorithm. |
| `de.cau.cs.kieler.ptolemy.attachmenteval` | ? |
| `de.cau.cs.kieler.solvers` | The plugin contains some classes that help calling solvers for optimization problems (e.g. CPLEX) from within ELK or KIELER.|