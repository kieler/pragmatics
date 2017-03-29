/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2017 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.graphs.testcases

import com.google.common.io.Files
import de.cau.cs.kieler.formats.AbstractEmfHandler
import de.cau.cs.kieler.formats.GraphFormatData
import de.cau.cs.kieler.formats.GraphFormatsService
import de.cau.cs.kieler.formats.IGraphFormatHandler
import de.cau.cs.kieler.formats.TransformationData
import de.cau.cs.kieler.klighd.IOffscreenRenderer
import de.cau.cs.kieler.klighd.KlighdDataManager
import java.io.File
import java.io.FileOutputStream
import java.nio.charset.Charset
import java.util.List
import java.util.Map
import java.util.Optional
import org.eclipse.core.resources.IContainer
import org.eclipse.core.resources.IFile
import org.eclipse.core.resources.IResource
import org.eclipse.core.runtime.IPath
import org.eclipse.core.runtime.IProgressMonitor
import org.eclipse.core.runtime.IStatus
import org.eclipse.core.runtime.MultiStatus
import org.eclipse.core.runtime.Status
import org.eclipse.core.runtime.jobs.Job
import org.eclipse.elk.core.util.ElkUtil
import org.eclipse.elk.graph.ElkNode
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.emf.ecore.xmi.XMLResource
import org.eclipse.ui.statushandlers.StatusManager

import static de.cau.cs.kieler.graphs.testcases.GenerateTestFilesHandler.*

class GeneratorJob extends Job {

   private static val Map<Object, Object> XML_OPTIONS = newHashMap(XMLResource.OPTION_PARSER_FEATURES -> 
        newHashMap(
            "http://xml.org/sax/features/validation" -> false,
            "http://apache.org/xml/features/nonvalidating/load-dtd-grammar" -> false,
            "http://apache.org/xml/features/nonvalidating/load-external-dtd" -> false
        )
    )
    
    private static val ELK_FORMAT = "elkg"
    private static val IMAGE_FORMAT = "png"
    private static val FLATTEN_DIRECTORIES = true

    val List<IFile> files
    val IPath initialPath
    val IContainer outputFolderGraphs
    val IContainer outputFolderImages
    val rs = new ResourceSetImpl()
    
    new(String name, List<IFile> files, IPath initialPath, IContainer graphsFolder, IContainer imagesFolder) {
        super(name)
        this.files = files
        this.initialPath = initialPath
        this.outputFolderGraphs = graphsFolder
        this.outputFolderImages = imagesFolder
    }
    
    override protected run(IProgressMonitor monitor) {
        monitor.beginTask("Generating Test Graphs", files.size)
        
        val errors = files.map[ f |
            if (monitor.canceled) 
                return Status.CANCEL_STATUS
            val status = gen(f, initialPath)
            monitor.worked(1)
            return status
        ].filter[s | s.severity == IStatus.ERROR || s.severity == IStatus.WARNING ]
        .toList
        
        monitor.done
        if (!errors.isEmpty) {
            val mStatus = new MultiStatus(PLUGIN_ID, 0, errors, "Test Graph Export Warnings/Errors", null)
            StatusManager.manager.handle(mStatus, (StatusManager.SHOW).bitwiseOr(StatusManager.LOG))
        }
        
        outputFolderGraphs.refreshLocal(IResource.DEPTH_INFINITE, null)
        outputFolderImages.refreshLocal(IResource.DEPTH_INFINITE, null)
        
        return Status.OK_STATUS
    }
    
    def IStatus gen(IFile file, IPath initialPath) {
        val fd = file.fileExtension.formatDataByExtension
        switch fd {
            case fd.isPresent: {
                val handler = fd.get.handler
                    
                    val rData = try {
                        loadGraphs(file, handler)
                    } catch (Exception e) {
                        return new Status(IStatus.ERROR, PLUGIN_ID, "Error loading file: " + file)
                    }
                    
                    val graphs = rData.targetGraphs
                    
                    switch handler {
                    ITestCaseGraphProvider: {
                         val allAccepted = graphs.map[ g |
                            // apply modifiers (side effect)
                            ElkUtil.applyVisitors(g, handler.graphModifiers ?: emptyList)
                            // all filters must be fulfilled
                            val accepted = (handler.graphFilters ?: emptyList).map[ f | f.test(g) ].reduceAnd
                            accepted        
                        ].reduceAnd
                
                        if (!allAccepted) 
                            return new Status(IStatus.WARNING, PLUGIN_ID, file + " rejected by graph filter.")
                    }}
                
                    val exportStatus = graphs.exportGraphs(file)
                    if (!exportStatus.OK)
                        return exportStatus
                        
                    val renderStatus = handler.renderDiagram(file)
                    if (renderStatus !== null && !renderStatus.OK) 
                        return renderStatus
                                        
                    return Status.OK_STATUS
            } 
            
            default: return new Status(IStatus.WARNING, PLUGIN_ID, 
                                    "Unknown file extension: '." + file.fileExtension + "'")
        }
    }
    
    def boolean reduceAnd(Iterable<Boolean> bools) {
        if (bools.nullOrEmpty) return true
        bools.reduce[ p1, p2| p1 && p2 ]
    }
    
    def IStatus exportGraphs(List<ElkNode> graphs, IFile sourceFile) {
       try {
            graphs.forEach[ g,i |
                val graphNumber = if (graphs.size > 1) ("_" + (i+1)) else ""
                val elkg = sourceFile.outputName(initialPath, outputFolderGraphs) + graphNumber  + "." + ELK_FORMAT
                val uri = URI.createPlatformResourceURI(elkg, true)
                val r = rs.createResource(uri)
                
                                        // clean copy of the residual
                        val copy = new GraphCleaner().createCleanCopy(g)
                
                r.contents += copy
                r.save(emptyMap)
            ]
            return Status.OK_STATUS
        } catch (Throwable t) {
            return new Status(IStatus.ERROR, PLUGIN_ID, "Could not export test graph: " + sourceFile.toString)                        
        }
    }
    
    def IStatus renderDiagram(IGraphFormatHandler<?> handler, IFile sourceFile) {
        switch handler {
            ITestCaseGraphProvider case handler.viewContext == null:
                return new Status(IStatus.WARNING, PLUGIN_ID, 
                    handler.class.simpleName + " did not provide a ViewContext for file " + sourceFile)
            ITestCaseGraphProvider: {
                val img = sourceFile.outputNameRaw(initialPath, outputFolderImages) + "." + IMAGE_FORMAT
                var f = new File(img) => [ f | f.parentFile.mkdirs ]
                val fos = new FileOutputStream(f)

                // the following lines resemble the internals of LightDiagramServices.renderOffScreen(...)
                //  however we already synthesized the diagram, no need to do it again                     
                val renderer = KlighdDataManager.getInstance().getOffscreenRenderersByFormat(IMAGE_FORMAT).head
                if (renderer == null) {
                    new Status(IStatus.ERROR, PLUGIN_ID, "Image format unknown to KLighD: " + IMAGE_FORMAT)
                }
                val vc = handler.viewContext
                vc.setProperty(IOffscreenRenderer.OUTPUT_FORMAT, IMAGE_FORMAT);
                vc.setProperty(IOffscreenRenderer.IMAGE_SCALE, 1)
                // finally render the diagram and return the result
                val status = renderer.render(vc, fos, vc)
                return status
            }
            default: return Status.OK_STATUS 
        }
    }
    
    def <T> TransformationData<T, ElkNode> loadGraphs(IFile file, IGraphFormatHandler<T> handler) {
        val tData = new TransformationData<T, ElkNode> 
                        => [ it.setProperty(AbstractEmfHandler.XML_OPTIONS, XML_OPTIONS)]
        val graphString = Files.toString(file.rawLocation.toFile, Charset.defaultCharset)
        handler.deserialize(graphString, tData)
        handler.importer.transform(tData)
        return tData
    }

    def Optional<GraphFormatData> getFormatDataByExtension(String ext) {
        val fd = GraphFormatsService.instance.formatData.findFirst[fd|fd.extensions.contains(ext)]
        if (fd == null) {
            return Optional.empty
        } else {
            return Optional.of(fd)
        }
    }    
    
    def deltaPath(IFile file, IPath initialPath) {
        var s = file.fullPath.toString
            .replace(initialPath.toString, "")
            .removeFileExtension
        if (FLATTEN_DIRECTORIES)
            s = s.directoryStructureToUnderscore
        return s
    }
    
    def outputName(IFile file, IPath initialPath, IContainer outputFolder) {
        outputFolder.fullPath.toString + File.separatorChar + file.deltaPath(initialPath)
    }
 
    def outputNameRaw(IFile file, IPath initialPath, IContainer outputFolder) {
        outputFolder.rawLocation.toFile.toString + File.separatorChar + file.deltaPath(initialPath)
    }
    
    def directoryStructureToUnderscore(String s) {
        val s2 = s.replaceAll("/|\\\\", "_")
        if (s2.startsWith("_")) {
            s2.substring(1)
        } else {
            s2
        }
    }
    
    def removeFileExtension(String s) {
        s.substring(0, s.lastIndexOf('.'))
    }

}

