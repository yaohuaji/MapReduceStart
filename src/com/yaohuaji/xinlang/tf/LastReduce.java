package com.yaohuaji.xinlang.tf;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class LastReduce extends Reducer<Text, Text, Text, Text>{
	
	protected void reduce(Text key, Iterable<Text> arg1,
			Context context)
			throws IOException, InterruptedException {
		
		StringBuffer sb =new StringBuffer();
		
		for( Text i :arg1 ){
			sb.append(i.toString()+"\t");
		}
		
		context.write(key, new Text(sb.toString()));
	}

}
