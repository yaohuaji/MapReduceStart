package com.yaohuaji.xinlang.tf;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class FirstJob {

	public static void main(String[] args) {
		Configuration config =new Configuration();
		config.set("fs.defaultFS", "hdfs://node1:8020");
		config.set("yarn.resourcemanager.hostname", "node1");
		try {
			FileSystem fs =FileSystem.get(config);
//			JobConf job =new JobConf(config);
			Job job =Job.getInstance(config);
			job.setJarByClass(FirstJob.class);
			job.setJobName("weibo1");
			
			job.setOutputKeyClass(Text.class);
			job.setOutputValueClass(IntWritable.class);
//			job.setMapperClass();
			job.setNumReduceTasks(4);
			job.setPartitionerClass(FirstPartition.class);
			job.setMapperClass(FirstMapper.class);
			job.setCombinerClass(FirstReduce.class);
			job.setReducerClass(FirstReduce.class);
			
			
			FileInputFormat.addInputPath(job, new Path("/root/input/tf-idf"));
			
			Path path =new Path("/root/output/weibo1");
			if(fs.exists(path)){
				fs.delete(path, true);
			}
			FileOutputFormat.setOutputPath(job,path);
			
			boolean f= job.waitForCompletion(true);
			if(f){
				System.out.println("success");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
