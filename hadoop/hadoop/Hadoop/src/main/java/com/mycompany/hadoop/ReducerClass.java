package com.mycompany.hadoop;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class ReducerClass extends Reducer<DoubleWritable, Text, Text, DoubleWritable> {

    protected void reduce(DoubleWritable key, Iterable<Text> values, Context context)
            throws IOException, InterruptedException {
    
        for (Text t : values) {
            context.write(t, key);
        }
    }
}
