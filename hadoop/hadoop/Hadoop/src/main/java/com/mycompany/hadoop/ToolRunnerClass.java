package com.mycompany.hadoop;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Random;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.filecache.DistributedCache;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class ToolRunnerClass extends Configured implements Tool {

    public static void main(String[] args) throws Exception {
        int res = ToolRunner.run(new Configuration(), new ToolRunnerClass(), args);
        System.exit(res);
    }

    @Override
    public int run(String[] args) throws Exception {

        // When implementing tool
        Configuration conf = this.getConf();
        addClassToDistributedCache(MapperClass.class, conf);
        Path inputFile = new Path(args[0]);
        Path inputDir = inputFile.getParent();
        Path outputDir = new Path(args[1]);
        Path inputFileToCopy = new Path(args[2]);

        FileSystem fs = FileSystem.get(conf);
        if (fs.exists(inputDir)) {
            fs.delete(inputDir, true);
        }
        fs.mkdirs(inputDir);

        if (fs.exists(outputDir)) {
            fs.delete(outputDir, true);
        }

        fs.copyFromLocalFile(false, true, inputFileToCopy, inputDir);
        // Create job
        @SuppressWarnings("deprecation")
        Job job = new Job(conf, "Entropia");
        job.setJarByClass(ToolRunnerClass.class);

        // Setup MapReduce job
        // Do not specify the number of Reducer
        job.setMapperClass(MapperClass.class);
        job.setReducerClass(ReducerClass.class);
        job.setSortComparatorClass(Comparator.class);

        // Specify key / value
        job.setOutputKeyClass(DoubleWritable.class);
        job.setOutputValueClass(Text.class);

        // Input
        FileInputFormat.addInputPath(job, inputFile);
        job.setInputFormatClass(TextInputFormat.class);

        // Output
        FileOutputFormat.setOutputPath(job, outputDir);
        job.setOutputFormatClass(TextOutputFormat.class);

        // Execute job and return status
        // configuration should contain reference to your namenode
        // true stands for recursively deleting the folder you gave
        return job.waitForCompletion(true) ? 0 : 1;
    }

    private static void addClassToDistributedCache(
            Class classToAdd, Configuration configuration) throws IOException {
        String classPath = classToAdd
                .getProtectionDomain()
                .getCodeSource()
                .getLocation()
                .getPath();

        classPath = URLDecoder.decode(classPath);
        File classFile = new File(classPath);

        int rnd = new Random().nextInt();
        // hadoop file system do przenoszenia plików z mastera do hadoop'a
        Path hdfsJar = new Path("/user/vagrant/lib" + rnd + "/" + classFile.getName());

        FileSystem hdfs = FileSystem.get(configuration);

        hdfs.copyFromLocalFile(false, true, new Path(classPath), hdfsJar);
        DistributedCache.addFileToClassPath(hdfsJar, configuration);
    }
}
