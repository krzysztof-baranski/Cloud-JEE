package com.mycompany.hadoop;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.LocalFileSystem;
import org.apache.hadoop.hdfs.DistributedFileSystem;

public class HadoopConfig extends Configuration{
    
    public HadoopConfig() {
        set("fs.hdfs.impl", DistributedFileSystem.class.getName()); // hadoop file system
        set("fs.file.impl", LocalFileSystem.class.getName()); // local file system
        
        set("mapreduce.jobtracker.address", "192.168.5.10:54311"); // The host and port that the MapReduce job tracker runs at
        set("mapreduce.framework.name", "yarn"); // The runtime framework for executing MapReduce jobs
        set("mapreduce.app-submission.cross-platform", "true"); // f enabled, user can submit an application cross-platform i.e. submit an 
                                                        //application from a Windows client to a Linux/Unix server or vice versa
        set("dfs.replication", "2"); // Default block replication
        
        set("fs.defaultFS", "hdfs://192.168.5.10:9000"); // The name of the default file system
        
        set("yarn.resourcemanager.hostname", "192.168.5.10"); // The hostname of the resources manager
        set("yarn.nodemanager.aux-services", "mapreduce_shuffle"); // The valid service name should only contain a-zA-Z0-9_ and can not start with numbers
    }
}

