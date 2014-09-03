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

import de.cau.cs.kieler.kiml.config.CompoundLayoutConfig
import de.cau.cs.kieler.kiml.config.text.LayoutConfigTransformer
import de.cau.cs.kieler.kiml.grana.AnalysisService
import de.cau.cs.kieler.kiml.grana.text.grana.Grana
import de.cau.cs.kieler.kiml.grana.ui.batch.Batch
import de.cau.cs.kieler.kiml.grana.ui.batch.BatchJob
import de.cau.cs.kieler.kiml.grana.ui.batch.CSVResultSerializer
import de.cau.cs.kieler.kiml.grana.ui.batch.FileKGraphProvider
import de.cau.cs.kieler.kiml.service.util.ProgressMonitorAdapter
import java.util.regex.Pattern
import org.eclipse.core.resources.IContainer
import org.eclipse.core.resources.ResourcesPlugin
import org.eclipse.core.runtime.IPath
import org.eclipse.core.runtime.IProgressMonitor
import org.eclipse.core.runtime.Path
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.resource.impl.ExtensibleURIConverterImpl
import de.cau.cs.kieler.kiml.grana.text.grana.ResourceReference
import de.cau.cs.kieler.kiml.grana.text.grana.LocalResource

/**
 * @author uru
 */
class GranaTextToBatchJob {

    def transform(Grana grana, IProgressMonitor progressMonitor) {

        val pm = new ProgressMonitorAdapter(progressMonitor)
        val wsRoot = ResourcesPlugin.workspace.root

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
                    val p = wsRoot.projects.findFirst[p|resource.path.contains(p.name)]
                    if (p == null) 
                        throw new IllegalArgumentException("Could not find resource " + resource.path)
                    val wsloc = p.findMember(resource.path.replace(p.name, ""))
    
                    // add all files to the batch job
                    for (file : (wsloc as IContainer).members) {
                        if (filter == null || filter.matcher(file.name).matches) {
                            val provider = new FileKGraphProvider
                            provider.setLayoutBeforeAnalysis(job.layoutBeforeAnalysis)
    
                            // TODO set layout options
                            val clc = new CompoundLayoutConfig
                            for (cfg : job.layoutOptions) {
                                clc.add(LayoutConfigTransformer.transformConfig(cfg))
                            }
                            provider.setLayoutConfigurator(clc)
                            
                            // the batch executer expects a workspace relative path
                            val batchJob = new BatchJob<IPath>(new Path(resource.path + "/" + file.name), provider)
                            batch.appendJob(batchJob)
                        }
                    }
                }
    
                // execute the analyses
                val result = batch.execute(pm.subTask(1))
                if (pm.canceled) {
                    return
                }
                
                if (!result.failedJobs.empty) {
                    for (entry : result.failedJobs) {
                        // FIXME :)
                        throw entry.second
                    }
                }
    
                // write results
                val fileURI = URI.createPlatformResourceURI(job.output, true)
                val uriConv = new ExtensibleURIConverterImpl
                val os = uriConv.createOutputStream(fileURI)
                val serializer = new CSVResultSerializer
                serializer.serialize(os, result, pm.subTask(1))
                os.close

            }
        }
    }

//        // display problems
//        if (!result.getFailedJobs().isEmpty()) {
//            IStatus[] stati = new IStatus[result.getFailedJobs().size()];
//            int i = 0;
//            for (Pair<BatchJob<?>, Throwable> entry : result.getFailedJobs()) {
//                stati[i++] = new Status(IStatus.ERROR, GranaPlugin.PLUGIN_ID,
//                        "Failed analysis of " + entry.getFirst().getParameter(),
//                        entry.getSecond());
//            }
//            StatusManager.getManager().handle(new MultiStatus(GranaPlugin.PLUGIN_ID,
//                    0, stati, MESSAGE_BATCH_FAILED, null),
//                    StatusManager.SHOW | StatusManager.LOG);
//        }
//        
//    } catch (Exception e) {
//        IStatus status = new Status(IStatus.ERROR, GranaPlugin.PLUGIN_ID, 0,
//                MESSAGE_BATCH_FAILED, e);
//        StatusManager.getManager().handle(status,
//                StatusManager.SHOW | StatusManager.LOG);
//    } finally {
//        monitor.done();
//    }
//}
}
