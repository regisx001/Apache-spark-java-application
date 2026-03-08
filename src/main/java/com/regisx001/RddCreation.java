package com.regisx001;

import java.util.Arrays;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import scala.Tuple2;

public class RddCreation {
    public static void main(String[] args) throws InterruptedException {
        SparkConf conf = new SparkConf()
                .setAppName("RDD Creation")
                .setMaster("local[*]");

        try (JavaSparkContext sc = new JavaSparkContext(conf)) {
            JavaRDD<Integer> numbers = sc.parallelize(List.of(1, 2, 3, 4, 5));
            JavaRDD<Integer> squared = numbers.map(x -> x * x);

            long count = numbers.count();

            JavaRDD<Integer> evens = numbers.filter(n -> n % 2 == 0);

            System.out.println(evens.collect());

            System.out.println("Total Numbers = " + count);
            System.out.println("SQUARED Numbers = " + squared.collect());
            System.out.println("Even Numbers = " + evens.collect());

            JavaRDD<String> lines = sc.textFile("data/text.txt");

            JavaRDD<String> words = lines.flatMap(line -> Arrays.asList(line.split(" ")).iterator());

            JavaPairRDD<String, Integer> counts = words.mapToPair(word -> new Tuple2<>(word, 1))
                    .reduceByKey(Integer::sum);

            counts.collect().forEach(System.out::println);

            sc.close();
        }
    }

}
