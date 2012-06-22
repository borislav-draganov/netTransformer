#!/bin/sh

echo "Echo"
LIB=../lib

CLASSPATH=".:${LIB}/topologyViewer-${version}.jar:${LIB}/topologyManagerConfig-${version}.jar:${LIB}/screencap-1.0.jar:${LIB}/rightcliickApi-${version}.jar:${LIB}/rightclick-${version}.jar:${LIB}/fulfilmentFactory-${version}.jar:${LIB}/parameterFactory-${version}.jar:${LIB}/resourceManager-${version}.jar:${LIB}/jta26\jta26.jar:${LIB}/jsch-0.1.44.jar:${LIB}/gritty-0.02.jar:${LIB}/commons-net-3.0.1.jar:${LIB}/log4j-1.2.16.jar:${LIB}/commons-io-2.0.1.jar:${LIB}/collections-generic-4.01.jar:${LIB}/colt-1.2.0.jar:${LIB}/concurrent-1.3.4.jar:${LIB}/jung-algorithms-2.0.1.jar:${LIB}/jung-api-2.0.1.jar:${LIB}/jung-graph-impl-2.0.1.jar:${LIB}/jung-io-2.0.1.jar:${LIB}/jung-visualization-2.0.1.jar:${LIB}/stax-api-1.0.1.jar:${LIB}/wstx-asl-3.2.6.jar:${LIB}/saxon-9.1.0.8.jar"
java -classpath ${CLASSPATH}  net.itransformers.topologyviewer.gui.TopologyViewer -t undirected -d network -g undirected -f iTopologyManager/topologyViewer/conf/xml/undirected.xml $*