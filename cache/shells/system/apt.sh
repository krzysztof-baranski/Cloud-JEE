#!/bin/bash

echo "------- shell apt -------"

apt-get install maven openjdk-8-jdk dos2unix -y unzip
update-alternatives --set java /usr/lib/jvm/java-8-openjdk-amd64/jre/bin/java