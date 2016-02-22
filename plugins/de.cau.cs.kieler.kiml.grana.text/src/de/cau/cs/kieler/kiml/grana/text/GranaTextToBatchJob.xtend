/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.grana.text

import com.google.common.collect.ContiguousSet
import com.google.common.collect.DiscreteDomain
import com.google.common.collect.Lists
import com.google.common.collect.Range
import de.cau.cs.kieler.kiml.config.text.LayoutConfigTransformer
import de.cau.cs.kieler.kiml.grana.AnalysisService
import de.cau.cs.kieler.kiml.grana.text.grana.FloatRange
import de.cau.cs.kieler.kiml.grana.text.grana.Grana
import de.cau.cs.kieler.kiml.grana.text.grana.IntRangeRange
import de.cau.cs.kieler.kiml.grana.text.grana.IntRangeValues
import de.cau.cs.kieler.kiml.grana.text.grana.Job
import de.cau.cs.kieler.kiml.grana.text.grana.LocalOutput
import de.cau.cs.kieler.kiml.grana.text.grana.LocalResource
import de.cau.cs.kieler.kiml.grana.text.grana.OutputReference
import de.cau.cs.kieler.kiml.grana.text.grana.RangeJob
import de.cau.cs.kieler.kiml.grana.text.grana.RegularJob
import de.cau.cs.kieler.kiml.grana.text.grana.ResourceReference
import de.cau.cs.kieler.kiml.grana.ui.batch.Batch
import de.cau.cs.kieler.kiml.grana.ui.batch.BatchJob
import de.cau.cs.kieler.kiml.grana.ui.batch.BatchRangeJob
import de.cau.cs.kieler.kiml.grana.ui.batch.BatchResult
import de.cau.cs.kieler.kiml.grana.ui.batch.CSVResultSerializer
import de.cau.cs.kieler.kiml.grana.ui.batch.FileKGraphProvider
import java.io.File
import java.io.IOException
import java.io.OutputStream
import java.util.regex.Pattern
import org.eclipse.core.resources.IContainer
import org.eclipse.core.resources.ResourcesPlugin
import org.eclipse.core.runtime.IPath
import org.eclipse.core.runtime.IProgressMonitor
import org.eclipse.core.runtime.Path
import org.eclipse.elk.core.service.LayoutMetaDataService
import org.eclipse.elk.core.service.util.ProgressMonitorAdapter
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.resource.impl.ExtensibleURIConverterImpl
import org.eclipse.elk.core.service.DiagramLayoutEngine.Parameters

/**
 * Utility class to convert textually specified grana executions to 
 * {@link BatchJob}s and execute them.
 * 
 * Note that there is a slight difference between grana 'Jobs' and grana.text 'Jobs'.
 * To be precise, a grana.text 'Job' corresponds to a grana 'Batch'. 
 * 
 * @author uru
 * @kieler.ignore (excluded from review process)
 */
final class GranaTextToBatchJob {

    /**
     * @return an iterable with the results of all executed batch jobs.
     *         These can be used to print problems that might have occurred during the execution. 
     */
    public static def Iterable<BatchResult> execute(Grana grana, IProgressMonitor progressMonitor) {

        val pm = new ProgressMonitorAdapter(progressMonitor)
        val wsRoot = ResourcesPlugin.workspace.root

        val results = Lists.<BatchResult>newLinkedList
        
        // execute for every job by creating a batch
        for (job : grana.jobs
                        // ignore deactivated jobs
                        .filter[ grana.executeAll || grana.execute.contains(it) ]
                        .filter[ !it.name.startsWith("_")]) {
            
            // collect requested analyses
            val analyses = job.analyses.map[AnalysisService.instance.getAnalysis(it.name)]
            val batch = new Batch(analyses)

            // further configure the batch              
            switch (job) {
                RangeJob: {
                    val rangeAnalysis = AnalysisService.instance.getAnalysis(job.rangeAnalysis.name)
                    if (rangeAnalysis == null) {
                        throw new IllegalArgumentException("Invalid range analysis: " + job.rangeAnalysis.name);
                    }
                    batch.rangeAnalysis = rangeAnalysis
                    batch.rangeAnalysisComponent = job.rangeAnalysisComponent
                    
                    val layOpt = LayoutMetaDataService.getInstance.getOptionDataBySuffix(job.rangeOption)
                    if (layOpt == null) {
                        throw new IllegalArgumentException("Invalid layout option: " + job.rangeOption);
                    }
                    batch.rangeOption = layOpt
                    
                    val v = job.rangeValues
                    val vals = switch (v) {
                      IntRangeRange: ContiguousSet.create(Range.closed(v.start, v.end), DiscreteDomain.integers)
                      IntRangeValues: ContiguousSet.copyOf(v.values)
                      FloatRange: ContiguousSet.copyOf(v.values)                  
                    }
                    batch.rangeValues = vals
                }
            }

            // resolve references to resources
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

                try {
                    // first we try to interpret the path as workspace relative
                    val p = wsRoot.projects.findFirst[p|resource.path.contains(p.name)]
                    if (p == null) 
                        throw new IllegalArgumentException("Could not find resource " + resource.path)
                    val wsloc = p.findMember(resource.path.replace(p.name, ""))
    
                    // add all files to the batch job
                    for (file : (wsloc as IContainer).members) {
                        if (filter == null || filter.matcher(file.name).matches) {
                            // add the job
                            addBatchJob(batch, job, new Path(resource.path + "/" + file.name))
                        }
                    }
                } catch (Exception e) {
                	
                	val fileURI = URI.createURI(resource.path, true)
                	val dir = new File(fileURI.toFileString)
					if (dir == null || !dir.exists) {
						throw new IllegalArgumentException("Could not find resource location: '" 
							+ resource.path + "'")
					}                    	
                	
                    for (file : dir.listFiles) {
                        if (filter == null || filter.matcher(file.name).matches) {
                            addBatchJob(batch, job, new Path(file.absolutePath))
                        }
                    }                    
                }
            }
            
            // resolve output file
            val outputPath = switch job.output {
                OutputReference: (job.output as OutputReference).outputRef.output.path
                default: (job.output as LocalOutput).path
            }
                
            try {
                // write results (of successful analyses)
                var OutputStream os
                val fileURI = URI.createURI(outputPath, true)
                val uriConv = new ExtensibleURIConverterImpl
                os = uriConv.createOutputStream(fileURI)
                os.write(0)
                os.close
            } catch (IOException e) {
                throw new IllegalArgumentException("Could not store results to file '" 
                    + outputPath + "'")
            }

            // everything should be fine ... execute the analyses!
            val result = batch.execute(pm.subTask(1))
            if (pm.canceled) {
                return #[]
            }

           try {
                // write results (of successful analyses)
                var OutputStream os
                val fileURI = URI.createURI(outputPath, true)
                val uriConv = new ExtensibleURIConverterImpl
                os = uriConv.createOutputStream(fileURI)
                val serializer = new CSVResultSerializer
                serializer.serialize(os, result, pm.subTask(1))
                os.close
            } catch (IOException e) {
                throw new IllegalArgumentException("Could not store results to file '" 
                    + outputPath + "'")
            }

            results += result
        }
        
        return results
    }
    
    /**
     * @param batch 
     *          grana's current batch instance
     * @param job 
     *          grana.text's Job corresponding to the batch
     * @param path
     *          the path of a test case (corresponding to a grana Job) 
     */
    private static def addBatchJob(Batch batch, Job job, IPath path) {
        val provider = new FileKGraphProvider
        
        switch (job) {
            RegularJob: {
                provider.setLayoutBeforeAnalysis(job.layoutBeforeAnalysis)
                provider.setExecutionTimeAnalysis(job.measureExecutionTime)
            }
            RangeJob: {
                provider.setLayoutBeforeAnalysis(true)
                provider.setExecutionTimeAnalysis(false)
            }
        }
    
        // set the layout options
        val params = new Parameters
        for (cfg : job.layoutOptions) {
            params.addLayoutRun(LayoutConfigTransformer.transformConfig(cfg))
        }
        provider.setLayoutConfigurator(params)
        
        // the batch executer expects a workspace relative path
        val batchJob = switch (job) {
            RegularJob: new BatchJob<IPath>(path, provider)
            RangeJob: new BatchRangeJob<IPath>(batch, path, provider)
        }
        batch.appendJob(batchJob) 
    }
}
