package com.mycompany.hadoop;

import org.apache.commons.lang.StringUtils;

public class Compute {

    public static double entropy(String value) {
        if (value.isEmpty()) {
            return 0d;
        } else {
            double entropy = 0d;
            for (int i = 0; i < 256; i++) {
                double x_prob = (StringUtils.countMatches(value, Character.toString((char) i)) / (double) value.length());
                if (x_prob > 0) {
                    entropy += -x_prob * (Math.log(x_prob) / (Math.log(2)));
                }
            }
            return entropy;
        }
    }
}
