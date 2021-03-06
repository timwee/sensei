#!/usr/bin/env bash

bin=`dirname "$0"`
bin=`cd "$bin"; pwd`

lib=$bin/../lib
dist=$bin/../dist
resources=$bin/../resources

CLASSPATH=$lib/fastutil-5.0.5.jar:$lib/log4j-1.2.15.jar:$lib/lucene-core-3.0.0.jar:\
$lib/protobuf-java-2.3.0.jar:$lib/bobo-browse-2.5.0-rc1.jar:$lib/kamikaze-2.0.0.jar:$lib/commons-logging-1.1.jar:\
$lib/netty-3.1.5.GA.jar:$lib/spring-2.5.5.jar:$lib/scala-library.jar:$lib/zoie-2.0.0-rc3.jar:\
$lib/norbert-java-cluster-1.0-SNAPSHOT.jar:$lib/norbert-java-network-1.0-SNAPSHOT.jar:$lib/norbert-cluster-1.0-SNAPSHOT.jar:$lib/norbert-network-1.0-SNAPSHOT.jar:$lib/zookeeper-3.2.0.jar:$dist/sensei-0.0.1.jar:$resources


java -classpath $CLASSPATH com.sensei.search.cluster.client.SenseiClusterClient
