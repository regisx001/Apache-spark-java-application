package com.regisx001;

import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;

public class RddCreation {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf()
                .setAppName("RDD Creation")
                .setMaster("local[*]");

        try (JavaSparkContext sc = new JavaSparkContext(conf)) {
            List<Integer> numbers;
            numbers = List.of(1, 2, 3, 4, 5);

            long count = sc.parallelize(numbers).count();

            System.out.println("Total numbers = " + count);
        }
    }

}
