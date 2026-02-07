package com.regisx001.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) throws InterruptedException {
        SparkConf conf = new SparkConf()
                .setAppName("Spark Maven First App");
        JavaSparkContext sc = new JavaSparkContext(conf);

        long count = sc.parallelize(
                java.util.List.of(1, 2, 3, 4, 5),
                2).count();

        System.out.println("Count = " + count);

        // Keep the driver alive so we can inspect the UI
        Thread.sleep(60_000);

        sc.close();

    }
}
