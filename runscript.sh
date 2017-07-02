#!/bin/sh
#./runscript.sh <ID>
if [ $# -eq 0 ]
 then
 echo "Correct...FORMAT ----  ./runscript.sh  <ID> "
 exit 1
fi
ID=$1

nohup java -cp /<path>/neo4j-cypher-1.4.jar:/<path>/neo4j-kernel-1.8.2.jar:/<path>/neo4j-java-driver-1.0.5.jar:/<path>/javasource/bin/ Delrelations $ID &
wait
echo "Relations have been droped"
exit