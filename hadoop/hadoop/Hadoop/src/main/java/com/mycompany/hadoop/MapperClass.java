package com.mycompany.hadoop;

import java.io.IOException;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MapperClass extends Mapper<Object, Text, DoubleWritable, Text> {

    @Override
    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        DoubleWritable entropyValue = new DoubleWritable(Compute.entropy(value.toString()));
        context.write(entropyValue, value);
    }
}
