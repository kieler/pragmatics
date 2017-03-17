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
package de.cau.cs.kieler.grana.text

import com.google.common.collect.ContiguousSet
import com.google.common.collect.DiscreteDomain
import com.google.common.collect.Lists
import com.google.common.collect.Range
import de.cau.cs.kieler.grana.AnalysisService
import de.cau.cs.kieler.grana.text.grana.CompareJob
import de.cau.cs.kieler.grana.text.grana.FloatRange
import de.cau.cs.kieler.grana.text.grana.Grana
import de.cau.cs.kieler.grana.text.grana.IntRangeRange
import de.cau.cs.kieler.grana.text.grana.IntRangeValues
import de.cau.cs.kieler.grana.text.grana.Job
import de.cau.cs.kieler.grana.text.grana.LocalOutput
import de.cau.cs.kieler.grana.text.grana.LocalResource
import de.cau.cs.kieler.grana.text.grana.OutputReference
import de.cau.cs.kieler.grana.text.grana.RangeJob
import de.cau.cs.kieler.grana.text.grana.RegularJob
import de.cau.cs.kieler.grana.text.grana.ResourceReference
import de.cau.cs.kieler.grana.ui.batch.Batch
import de.cau.cs.kieler.grana.ui.batch.BatchJob
import de.cau.cs.kieler.grana.ui.batch.BatchResult
import de.cau.cs.kieler.grana.ui.batch.CSVResultSerializer
import de.cau.cs.kieler.grana.ui.batch.FileElkGraphProvider
import de.cau.cs.kieler.grana.ui.batch.JsonResultSerializer
import de.cau.cs.kieler.kiml.config.text.LayoutConfigTransformer
import java.io.File
import java.io.IOException
import java.io.OutputStream
import java.util.List
import java.util.regex.Pattern
import org.eclipse.core.resources.IContainer
import org.eclipse.core.resources.IFile
import org.eclipse.core.resources.ResourcesPlugin
import org.eclipse.core.runtime.IPath
import org.eclipse.core.runtime.IProgressMonitor
import org.eclipse.core.runtime.Path
import org.eclipse.elk.core.data.LayoutMetaDataService
import org.eclipse.elk.core.service.util.ProgressMonitorAdapter
import org.eclipse.elk.core.util.IElkProgressMonitor
import org.eclipse.elk.core.util.Pair
import org.eclipse.elk.graph.properties.MapPropertyHolder
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.resource.impl.ExtensibleURIConverterImpl
import de.cau.cs.kieler.grana.text.grana.EnumRange

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

    /** Work per job, used for the progress monitors. */ 
    private static val WORK_LOAD = 1f
    private static val WORK_ANALYSES = 10f
    private static val WORK_SERIALIZE = 1f
    private static val WORK_TOTAL = WORK_ANALYSES + WORK_LOAD + WORK_SERIALIZE

    /**
     * @return an iterable with the results of all executed batch jobs.
     *         These can be used to print problems that might have occurred during the execution. 
     */
    public static def Iterable<BatchResult> execute(Grana grana, IProgressMonitor progressMonitor) {

        val pm = new ProgressMonitorAdapter(progressMonitor)

        val results = Lists.<BatchResult>newLinkedList
        val activeJobs = grana.jobs
                        // ignore deactivated jobs
                        .filter[ grana.executeAll || grana.execute.contains(it) ]
                        .filter[ !it.name.startsWith("_")]
        val work = (WORK_TOTAL * activeJobs.size)
        
        pm.begin("Executing batch analysis", work)
        
        // execute for every job by creating a batch
        for (job : activeJobs) {
            
            val batch = initializeJob(job, pm.subTask(WORK_LOAD))
            
            // parallel execution?
            batch.parallel = grana.parallel
            
            // resolve output file
            val outputPath = switch job.output {
                OutputReference: (job.output as OutputReference).outputRef.output.path
                default: (job.output as LocalOutput).path
            }
            checkOutputPathExist(outputPath)

            // everything should be fine ... execute the analyses!
            val result = batch.execute(pm.subTask(WORK_ANALYSES))

            writeResults(job, outputPath, result, pm);

            results += result
            
            // do not cancel before serialization has been taken place
            if (pm.canceled) {
                // return what we analyzed so far
                return results
            }
        }
        
        pm.done();
        return results
    }
    
    /**
     * For the given {@link Job}, initialize and configure a corresponding {@link Batch} object.
     * Note that the term 'job' is unfortunately overloaded here.  
     */
    private static def Batch initializeJob(Job job, IElkProgressMonitor monitor) {
        monitor.begin("Initializing jobs ...", 1)
        
        // collect requested analyses
        val analyses = job.analyses.map[AnalysisService.instance.getAnalysis(it.name)]
        val Batch batch = switch (job) {
            RegularJob: new Batch.Simple(job.name, analyses)  
            RangeJob: {
                val batch = new Batch.Range(job.name, analyses)
                
                // gather the range analyses
                if (job.rangeAnalysis != null) {
                    val rangeAnalysis = AnalysisService.instance.getAnalysis(job.rangeAnalysis.name)
                    if (rangeAnalysis == null) {
                        throw new IllegalArgumentException("Invalid range analysis: " + job.rangeAnalysis.name);
                    }
                    batch.rangeAnalysis = rangeAnalysis
                    batch.rangeAnalysisComponent = job.rangeAnalysisComponent
                
                } else {                
                    val tanalyses = job.rangeAnalyses.map[ an |
                        val rangeAnalysis = AnalysisService.instance.getAnalysis(an.name)
                        if (rangeAnalysis == null) {
                            throw new IllegalArgumentException("Invalid range analysis: " + job.rangeAnalysis.name);
                        }
                        rangeAnalysis
                    ]
                    batch.rangeAnalyses = tanalyses 
                }
                
                // configure the range option
                val layOpt = LayoutMetaDataService.getInstance.getOptionDataBySuffix(job.rangeOption)
                if (layOpt == null) {
                    throw new IllegalArgumentException("Invalid layout option: " + job.rangeOption);
                }
                batch.rangeOption = layOpt
                
                val v = job.rangeValues
                val List<?> vals = switch (v) {
                  IntRangeRange: ContiguousSet.create(Range.closed(v.start, v.end), DiscreteDomain.integers)
                  IntRangeValues: ContiguousSet.copyOf(v.values)
                  FloatRange: ContiguousSet.copyOf(v.values)   
                  EnumRange: v.values
                }.toList
                batch.rangeValues = vals

                // assemble layout configs for the range job
                val rangeOptionCfgs = vals.map[ value |
                    val parsed = layOpt.parseValue(value.toString())
                    if (parsed == null) {
                        throw new IllegalArgumentException(
                            "Value " + value + " is not valid for layout option " + layOpt);
                    }
                    new MapPropertyHolder().setProperty(layOpt, parsed)
                ].toList
                batch.rangeConfigurations = rangeOptionCfgs
                
                batch
            } 
        }

        // resolve references to resources
        val resources =  job.resources.map[ r |
            switch r {
                ResourceReference: (r as ResourceReference).resourceRefs.map[it.resources].flatten
                default: #[(r as LocalResource)]
            }
        ].flatten

        // FIXME the file handling below is a mess ... 

        // collect all model files within the specified resources            
        for (resource : resources) {
            val filter = if (!resource.filter.nullOrEmpty)
                Pattern.compile(resource.filter) else null

            if (!resource.path.startsWith("file://")) {
                // should be workspace relative
                val p = ResourcesPlugin.workspace.root.projects.findFirst[p|resource.path.contains(p.name)]
                val wsloc = p?.findMember(resource.path.replaceFirst(p.name, ""))
                if (p == null || !(wsloc instanceof IContainer)) 
                    throw new IllegalArgumentException("Invalid resource " + resource.path)
                
                (wsloc as IContainer).collectJobsEclipse(batch, job, filter, resource.recurse)
            } else {
                // somewhere in the file system ...
                val fileURI = URI.createURI(resource.path, true)
                val dir = new File(fileURI.toFileString)
                if (dir == null || !dir.exists || !dir.isDirectory)
                    throw new IllegalArgumentException("Could not find resource location: '" + resource.path + "'")
    
                dir.collectJobsFilesystem(batch, job, filter, resource.recurse)
            }
        }
        
        monitor.done()
        return batch
    }
    
    def static void collectJobsEclipse(IContainer container, Batch batch, Job job, Pattern filter, boolean recurse) {
        for (member : container.members) {
            switch member {
                IContainer case recurse: member.collectJobsEclipse(batch, job, filter, recurse)
                IFile case filter == null || filter.matcher(member.name).matches: 
                    addBatchJob(batch, job, member.fullPath)
                    //addBatchJob(batch, job, new Path(resource.path + "/" + file.name))
            }
        } 
    }
    
    def static void collectJobsFilesystem(File dir, Batch batch, Job job, Pattern filter, boolean recurse) {
        for (member : dir.listFiles) {
            switch member {
                case member.isDirectory && recurse: member.collectJobsFilesystem(batch, job, filter, recurse)
                case member.isFile && (filter == null || filter.matcher(member.name).matches):
                    addBatchJob(batch, job, new Path(member.absolutePath))
            }
        }
    }
    
    /**
     * Checks if the specified output path exists and can be written. 
     */
    private static def checkOutputPathExist(String outputPath) {
        try {
            // probe the output files, if it fails don't bother to analyze
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
    }
    
    /**
     * Serialize the results.
     */
    private static def writeResults(Job job, String outputPath, BatchResult result, IElkProgressMonitor pm) {
        try {
            // write results (of successful analyses)
            var OutputStream os
            val fileURI = URI.createURI(outputPath, true)
            val uriConv = new ExtensibleURIConverterImpl
            os = uriConv.createOutputStream(fileURI)

            val serializer = switch (job.outputType) {
                case CSV: new CSVResultSerializer
                case JSON: new JsonResultSerializer
            }

            serializer.serialize(os, result, pm.subTask(WORK_SERIALIZE))
            os.close
        } catch (IOException e) {
            throw new IllegalArgumentException("Could not store results to file '" + outputPath + "'")
        }
        
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
        val provider = new FileElkGraphProvider
         
        switch (job) {
            RegularJob: {
                provider.setLayoutBeforeAnalysis(job.layoutBeforeAnalysis)
                provider.setExecutionTimeAnalysis(job.measureExecutionTime)
            }
            RangeJob: {
                provider.setLayoutBeforeAnalysis(false)
                provider.setExecutionTimeAnalysis(job.measureExecutionTime)
            }
            CompareJob: {
                provider.setLayoutBeforeAnalysis(false)
                provider.setExecutionTimeAnalysis(false)
            }
        }
        
        switch job {
            CompareJob: {
                if (job.layoutOptions.size != 2) 
                    throw new UnsupportedOperationException("Compare jobs require exactly two layout configurations.")
            }
            RegularJob, RangeJob: {
                // TODO it should be possible to add multiple layout runs
                if (job.layoutOptions.size > 1) 
                    throw new UnsupportedOperationException("Multiple layout runs are not yet supported.")        
            } 
        }
        
        val cfgs = job.layoutOptions.map[LayoutConfigTransformer.transformConfig(it)]
        
        // set the layout options
        if (!job.layoutOptions.isEmpty) {
            provider.layoutConfigurator = cfgs.head
        }
         
        // the batch executer expects a workspace relative path
        val batchJob = switch (job) {
            RegularJob: new BatchJob.Simple<IPath>(path, provider)
            RangeJob: { 
                val br = batch as Batch.Range
                new BatchJob.Range<IPath>(path, provider, cfgs.head, br.getRangeConfigurations())
            }
            CompareJob: {
                new BatchJob.Compare(path, provider, Pair.of(cfgs.head, cfgs.last))
            }
        }
        batch.appendJob(batchJob) 
    }
}
