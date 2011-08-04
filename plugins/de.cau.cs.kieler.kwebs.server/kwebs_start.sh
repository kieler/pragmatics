#!/bin/bash

# Startup script for the KWebS server
# Version 0.00
#
# Author swe
# Last edit 21.07.2011

# !!!!!
# Please remember making this script executable by
# doing chmod u+x kwebs.sh
# !!!!!

# Start the server ignoring the HUP signal so the server
# keeps running after closing the ssh connection
nohup ./kwebs >kwebs.out 2>kwebs.err </dev/null &

# Store process id in file
echo $! > kwebs.pid

# Wait some time just to be sure...
sleep 5
