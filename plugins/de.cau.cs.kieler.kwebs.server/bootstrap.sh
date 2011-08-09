#!/bin/bash

# Script for extracting the management scripts from the
# server plug-in archive
# 
# Version 0.00
#
# Author swe
# Last edit 10.08.2011

PATH=$(ls kwebs/plugins/*kwebs.server_*)

if [ "$PATH" == "" ];
then
    echo No server plugin path found, can not unzip management scripts.
    exit 1
fi

NAME=${PATH##*/}

if [ "$NAME" == "" ];
then
    echo No server plugin archive found, can not unzip management scripts.
    exit 2
fi

echo Unzipping management scripts from archive $NAME
/usr/bin/unzip -o kwebs/plugins/$NAME kwebs_*.sh -d kwebs/

if [ "$?" == "0" ];
then
    echo Unzipping OK    
else
    echo Unzipping FAILED
    exit 3
fi