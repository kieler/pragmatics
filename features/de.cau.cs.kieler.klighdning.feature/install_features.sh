#!/bin/bash

echo ""

help="\nusage: install_features [installable features] [repositories]"

if [[ -z "$1" || -z "$2" ]]; 
then 
	echo "Error: Provide a comma separated list with feature ids as first argument and a comma separated list with repositories ids as second argument."
	echo -e "${help}"
	exit 1
fi

opts="-consoleLog -debug -nosplash"
iuFeatures=$1
repositories=$2
errfile=err.log

echo "############################################"
echo "####### KlighDning Feature Installer #######"
echo ""
echo "Using repositories ${repositories}"
echo "Trying to install ${iuFeatures}"
echo ""
echo "Starting equinox p2 ..."

# Perform the actual command, redirect error stream to a file
./KlighDning \
	-application org.eclipse.equinox.p2.director \
	-repository "$repositories" \
	-installIU "$iuFeatures" 2> $errfile

# Check for errors
if [ ! -s $errfile ];
then
	echo "Successful."
else
	echo -e "\nError:"
	cat $errfile
fi

# Cleanup
rm $errfile