#!/bin/bash

# Startup script for the KWebS server
# Version 0.00
#
# Author swe
# Last edit 10.08.2011

# !!!!!
# Please remember making this script executable by
# doing chmod u+x kwebs_start.sh
# !!!!!

# Start the server ignoring the HUP signal so the server
# keeps running after closing a terminal connection
nohup ./kwebs >kwebs.out 2>kwebs.err </dev/null &

PID=$!

# Store process id in file
echo KWebS server started, process id is $PID
echo $PID > kwebs.pid

# Wait some time just to be sure...
sleep 5
