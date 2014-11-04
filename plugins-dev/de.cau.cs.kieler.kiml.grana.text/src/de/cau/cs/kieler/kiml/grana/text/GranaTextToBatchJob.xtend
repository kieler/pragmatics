/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.grana.text

import com.google.common.collect.Lists
import de.cau.cs.kieler.kiml.config.CompoundLayoutConfig
import de.cau.cs.kieler.kiml.config.text.LayoutConfigTransformer
import de.cau.cs.kieler.kiml.grana.AnalysisService
import de.cau.cs.kieler.kiml.grana.text.grana.Grana
import de.cau.cs.kieler.kiml.grana.text.grana.Job
import de.cau.cs.kieler.kiml.grana.text.grana.LocalResource
import de.cau.cs.kieler.kiml.grana.text.grana.ResourceReference
import de.cau.cs.kieler.kiml.grana.ui.batch.Batch
import de.cau.cs.kieler.kiml.grana.ui.batch.BatchJob
import de.cau.cs.kieler.kiml.grana.ui.batch.BatchResult
import de.cau.cs.kieler.kiml.grana.ui.batch.CSVResultSerializer
import de.cau.cs.kieler.kiml.grana.ui.batch.FileKGraphProvider
import de.cau.cs.kieler.kiml.service.util.ProgressMonitorAdapter
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.util.regex.Pattern
import org.eclipse.core.resources.IContainer
import org.eclipse.core.resources.ResourcesPlugin
import org.eclipse.core.runtime.IPath
import org.eclipse.core.runtime.IProgressMonitor
import org.eclipse.core.runtime.Path
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.resource.impl.ExtensibleURIConverterImpl

/**
 * @author uru
 * @kieler.ignore (excluded from review process)
 */
class GranaTextToBatchJob {

    /**
     * @return an iterable with the results of all executed batch jobs. These can be used to print problems that might have occurred during the execution. 
     */
    def Iterable<BatchResult> execute(Grana grana, IProgressMonitor progressMonitor) {

        val pm = new ProgressMonitorAdapter(progressMonitor)
        val wsRoot = ResourcesPlugin.workspace.root

        val results = Lists.<BatchResult>newLinkedList
        
        // execute for every job
        for (job : grana.jobs) {
            
            // ignore deactivated jobs
            if (!job.name.startsWith("_")) {
                
                // collect requested analyses
                val analyses = job.analyses.map[AnalysisService.instance.getAnalysis(it.name)]
                val batch = new Batch(analyses)
    
                // resolve possible references
                val resources =  job.resources.map[ r |
                    switch r {
                        ResourceReference: (r as ResourceReference).resourceRefs.map[it.resources].flatten
                        default: #[(r as LocalResource)]
                    }
                ].flatten
                
                // collect all model files within the specified resources            
                for (resource : resources) {
                    val filter = if (!resource.filter.nullOrEmpty)
                        Pattern.compile(resource.filter) else null
    
                    // FIXME path handling is very very bad !!!!!! 
                    try {
                        // first we try to interpret the path as workspace relative
                        val p = wsRoot.projects.findFirst[p|resource.path.contains(p.name)]
                        if (p == null) 
                            throw new IllegalArgumentException("Could not find resource " + resource.path)
                        val wsloc = p.findMember(resource.path.replace(p.name, ""))
        
                        // add all files to the batch job
                        for (file : (wsloc as IContainer).members) {
                            if (filter == null || filter.matcher(file.name).matches) {
                                addBatchJob(batch, job, new Path(resource.path + "/" + file.name))
                            }
                        }
                    } catch (Exception e) {
                        // if this fails, try absolute file system
                        val dir = new File(wsRoot.location.toFile, resource.path)
                        if (!dir.exists) 
                            throw new IllegalArgumentException("Could not find resource " + resource.path)
                        for (file : dir.listFiles) {
                            if (filter == null || filter.matcher(file.name).matches) {
                                addBatchJob(batch, job, new Path(file.absolutePath))
                            }
                        }                    
                    }
                }

                // execute the analyses
                val result = batch.execute(pm.subTask(1))
                if (pm.canceled) {
                    return #[]
                }
                
                results += result
    
                // write results (of successful analyses)
                var OutputStream os
                try {
                    // try to use the workspace
                    val fileURI = URI.createPlatformResourceURI(job.output, true)
                    val uriConv = new ExtensibleURIConverterImpl
                    os = uriConv.createOutputStream(fileURI)
                } catch (Exception e) {
                    // fall back to the file system
                    val dir = wsRoot.location.toFile
                    os = new FileOutputStream(new File(dir, job.output))
                }
                
                val serializer = new CSVResultSerializer
                serializer.serialize(os, result, pm.subTask(1))
                os.close
            }
        }
        
        return results
    }
    
    private def addBatchJob(Batch batch, Job job, IPath path) {
        val provider = new FileKGraphProvider
        provider.setLayoutBeforeAnalysis(job.layoutBeforeAnalysis)
        provider.setExecutionTimeAnalysis(job.measureExecutionTime)
    
        // set the layout options
        val clc = new CompoundLayoutConfig
        for (cfg : job.layoutOptions) {
            clc.add(LayoutConfigTransformer.transformConfig(cfg))
        }
        provider.setLayoutConfigurator(clc)
        
        // the batch executer expects a workspace relative path
        val batchJob = new BatchJob<IPath>(path, provider)
        batch.appendJob(batchJob)
    }
}
