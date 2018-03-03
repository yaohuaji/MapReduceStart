package com.yaohuaji.xinlang.tf;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class TwoReduce extends Reducer<Text, IntWritable, Text, IntWritable>{
	
	protected void reduce(Text key, Iterable<IntWritable> arg1,
			Context context)
			throws IOException, InterruptedException {
		
		int sum =0;
		for( IntWritable i :arg1 ){
			sum= sum+i.get();
		}
		
		context.write(key, new IntWritable(sum));
	}

}
