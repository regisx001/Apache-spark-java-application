# Spark Java App

This is a simple Apache Spark application written in Java. It demonstrates a basic Spark job that parallelizes a list of integers and counts them.

## Prerequisites

*   Java 21
*   Apache Maven 3.6+
*   Apache Spark 3.5.1 (or compatible version, for local run)
*   Docker & Docker Compose
*   Python 3 (for infrastructure tests)

## Project Structure

*   `src/main/java/com/regisx001/spark/App.java`: Main application code.
*   `pom.xml`: Maven configuration file.
*   `infra/`: Infrastructure configuration (Docker Compose) and tests.

## Infrastructure Setup

1.  **Start the Spark Cluster (Master & Worker):**

    ```bash
    docker-compose -f infra/docker-compose.yaml up -d
    ```

    This starts `spark-master` on port 8080 (UI) / 7077 (Spark) and `spark-worker` on port 8081 (UI).

2.  **Test the Infrastructure:**

    Ensure the services are reachable by running the health check script.

    ```bash
    # Install dependencies
    pip install -r infra/requirements.txt

    # Run the test
    python3 infra/tests/test_infrastructure.py
    ```

    You should see "All systems operational!" if everything is running correctly.

## Building the Project

To build the project and create a JAR file, run:

```bash
mvn clean package
```

This will generate a JAR file in the `target/` directory (e.g., `spark-java-app-1.0-SNAPSHOT.jar`).

## Running the Application

You can run the application locally or submit it to the Docker cluster.

### 1. Run Locally (Development)

```bash
spark-submit \
  --class com.regisx001.spark.App \
  --master local[*] \
  target/spark-java-app-1.0-SNAPSHOT.jar
```

### 2. Run on Docker Cluster

To run the application on the containerized Spark cluster, follow these steps:

1.  **Copy the JAR to the Spark Master container:**

    ```bash
    docker cp target/spark-java-app-1.0-SNAPSHOT.jar spark-master:/opt/spark/work/app.jar
    ```

2.  **Submit the job inside the container:**

    ```bash
    docker exec spark-master /opt/spark/bin/spark-submit \
      --class com.regisx001.spark.App \
      --master spark://spark-master:7077 \
      --deploy-mode client \
      /opt/spark/work/app.jar
    ```

    *   The application runs for 60 seconds (due to `Thread.sleep`), allowing you to inspect the Spark Master UI at [http://localhost:8080](http://localhost:8080).

Note: The application includes a `Thread.sleep(60_000)` to keep the driver alive for 60 seconds, allowing time to inspect the Spark UI.
