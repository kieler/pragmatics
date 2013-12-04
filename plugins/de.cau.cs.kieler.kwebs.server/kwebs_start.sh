#!/bin/bash

# Startup script for the KWebS server
# Version 0.02
#
# Author swe
# Last edit 10.08.2012
#
# Please remember making this script executable by
# doing chmod u+x kwebs_start.sh

# Start the server ignoring the HUP signal so the server
# keeps running after closing a terminal connection
echo Starting server...
nohup ./kwebs >kwebs.out 2>kwebs.err </dev/null &


