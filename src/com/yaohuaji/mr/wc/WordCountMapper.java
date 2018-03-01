package com.yaohuaji.mr.wc;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.util.StringUtils;

public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable>{

	//�÷���ѭ�����ã����ļ���split�ж�ȡÿ�е���һ�Σ��Ѹ������ڵ��±�Ϊkey�����е�����Ϊvalue
	protected void map(LongWritable key, Text value,
			Context context)
			throws IOException, InterruptedException {
		String[] words = StringUtils.split(value.toString(), ' ');
		for(String w :words){
			context.write(new Text(w), new IntWritable(1));
		}
	}
}
