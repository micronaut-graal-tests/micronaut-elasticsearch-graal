#!/bin/bash

./gradlew nativeCompile
cp build/native/nativeCompile/elasticsearch .
