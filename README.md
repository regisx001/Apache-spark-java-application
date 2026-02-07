# Spark Java App

This is a simple Apache Spark application written in Java. It demonstrates a basic Spark job that parallelizes a list of integers and counts them.

## Prerequisites

*   Java 21
*   Apache Maven 3.6+
*   Apache Spark 3.5.1 (or compatible version)

## Project Structure

*   `src/main/java/com/regisx001/spark/App.java`: Main application code.
*   `pom.xml`: Maven configuration file.

## Building the Project

To build the project and create a JAR file, run:

```bash
mvn clean package
```

This will generate a JAR file in the `target/` directory (e.g., `spark-java-app-1.0-SNAPSHOT.jar`).

## Running the Application

You can run the application using `spark-submit`. 

**Run locally:**

```bash
spark-submit \
  --class com.regisx001.spark.App \
  --master local[*] \
  target/spark-java-app-1.0-SNAPSHOT.jar
```

**Run on a Spark Cluster:**

Ensure you have your Spark master URL ready (e.g., `spark://spark-master:7077`).

```bash
spark-submit \
  --class com.regisx001.spark.App \
  --master spark://<MASTER_HOST>:<PORT> \
  --deploy-mode client \
  target/spark-java-app-1.0-SNAPSHOT.jar
```

Note: The application includes a `Thread.sleep(60_000)` to keep the driver alive for 60 seconds, allowing time to inspect the Spark UI.
