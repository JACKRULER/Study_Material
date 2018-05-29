#!/bin/bash -e

sudo docker run -i -v "$HOME/.m2":/root/.m2 -v "$(pwd)":/usr/src/mymaven -w /usr/src/mymaven maven:3.5-jdk-8-alpine mvn test -Dtest=com.quintype.scripts.metype.*