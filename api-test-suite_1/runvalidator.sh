#!/bin/bash -e
pubURL=$1
env=$2
slug1=$3
slug2=$4

sudo docker run -i -v "$HOME/.m2":/root/.m2 -v "$(pwd)":/usr/src/mymaven -w /usr/src/mymaven maven:3.5-jdk-8-alpine mvn test -Dtest=com.quintype.scripts.ace.validator.TestValidator -DpublisherURL=$pubURL -Denvironment=$env -DstorySlug=$slug1 -DsectionSlug=$slug2
#mvn test -Dtest=com.quintype.scripts.ace.validator.TestValidator -DpublisherURL=$pubURL -Denvironment=$env -DstorySlug=$slug1 -DsectionSlug=$slug2

