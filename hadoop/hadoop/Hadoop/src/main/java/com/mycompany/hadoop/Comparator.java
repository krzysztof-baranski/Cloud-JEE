package com.mycompany.hadoop;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class Comparator extends WritableComparator {

    @SuppressWarnings("rawtypes")
    public Comparator() {
        super(DoubleWritable.class, true);
    }

    public int compare(WritableComparable x, WritableComparable y) {
        DoubleWritable x1 = (DoubleWritable) x;
        DoubleWritable y2 = (DoubleWritable) y;

        return y2.compareTo(x1);
    }
}
