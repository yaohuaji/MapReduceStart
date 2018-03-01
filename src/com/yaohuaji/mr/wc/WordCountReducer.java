package com.yaohuaji.mr.wc;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable>{

	//ÿ�����һ�Σ���һ������ص㣺key��ͬ��value�����ж����
	protected void reduce(Text arg0, Iterable<IntWritable> arg1,
			Context arg2)
			throws IOException, InterruptedException {
		int sum =0;
		for(IntWritable i: arg1){
			sum=sum+i.get();
		}
		arg2.write(arg0, new IntWritable(sum));
	}
}
