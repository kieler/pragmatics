#!/bin/bash

echo
echo KWebS - KIELER Web Service for layout
echo
echo Generator script for JAX-WS implementation classes
echo
echo Copyright 2011 by Real-Time and Embedded Systems Group, Department
echo of Computer Science, Christian-Albrechts-University of Kiel
echo Published under the EPL v1.0 (see http://www.eclipse.org/legal/epl-v10.html)

if [ "$JAVA_HOME"=="" ];
then
  echo You have to set the environment variable JAVA_HOME pointing to your
  echo local Java installation.
  exit
fi

if [ "$JAXWS_HOME"=="" ];
then
  echo You have to set the environment variable JAXWS_HOME pointing to your
  echo local installation of the JAX-WS reference implementation.
  exit
fi

if [ -d "../src" ];
then
    rm -rf "../src"
fi
if [ ! -d "../src" ];
then
    mkdir "../src"
fi

if [ -d "../bin" ];
then
    rm -rf "../bin"
fi
if [ ! -d "../bin" ];
then
    mkdir "../bin"
fi

"$JAVA_HOME/bin/java.exe" -jar "$JAXWS_HOME/lib/jaxws-tools.jar" -Xendorsed -keep -s "../src" -d "../bin" -p de.cau.cs.kieler.kwebs.jaxws layout.wsdl
