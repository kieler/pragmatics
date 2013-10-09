#!/bin/bash

echo ""

help="\nusage: install_features [installable features] [repositories]"

if [[ -z "$1" || -z "$2" ]]; then echo "Error: Provide a comma separated list with feature ids as first argument and a comma separated list with repositories ids as second argument."; echo -e "${help}"; exit 1; fi

opts="-consoleLog -debug -nosplash"
iuFeatures=$1
repositories=$2

echo "############################################"
echo "####### KlighDning Feature Installer #######"
echo ""
echo "Using repositories ${repositories}"
echo "Installing ${iuFeatures}"
echo ""
echo "Starting equinox p2 ..."
	
./KlighDning \
	-application org.eclipse.equinox.p2.director \
	-repository "${repositories}" \
	-installIU "${iuFeatures}"

echo "Program exited with:" 
echo $?
