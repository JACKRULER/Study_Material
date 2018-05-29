#!/bin/bash
env=${1:-'dev'}
pipeline=$2
domain=$3

if [ "$domain" == "metype" ]
then
	Path_Of_Data_File="./dataFileMeType.properties"
	xvfb-run -a --server-args='-screen 1, 1366x768x16' -n 2 java -cp build/libs/bumblebee.jar -DdataPath=$Path_Of_Data_File -Denv=$env -Dsuite=$pipeline -Ddomain=$domain org.testng.TestNG ${pipeline}test.xml
else
	Path_Of_Data_File="./dataFile.properties"
	xvfb-run -a --server-args='-screen 1, 1366x768x16' -n 2 java -cp build/libs/bumblebee.jar -DdataPath=$Path_Of_Data_File -Denv=$env -Dsuite=$pipeline -Ddomain=$domain org.testng.TestNG ${pipeline}test.xml
fi
		
