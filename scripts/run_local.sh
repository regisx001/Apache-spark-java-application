#!/bin/bash

set -e

APP_NAME="spark-java-app"
JAR_PATH="target/${APP_NAME}-1.0.jar"

if [ -z "$1" ]; then
    echo "Usage: $0 <main-class>"
    echo "Example: $0 com.regisx001.wordcount.WordCount"
    exit 1
fi

MAIN_CLASS="com.regisx001.$1"

echo "=================================="
echo "Building Spark project with Maven"
echo "=================================="

mvn clean package

echo ""
echo "=================================="
echo "Checking JAR file"
echo "=================================="

if [ ! -f "$JAR_PATH" ]; then
    echo "JAR file not found: $JAR_PATH"
    exit 1
fi

echo "JAR found: $JAR_PATH"

echo ""
echo "=================================="
echo "Submitting Spark job (local mode)"
echo "=================================="

spark-submit \
  --class $MAIN_CLASS \
  --master local[*] \
  $JAR_PATH

echo ""
echo "=================================="
echo "Spark job finished"
echo "=================================="