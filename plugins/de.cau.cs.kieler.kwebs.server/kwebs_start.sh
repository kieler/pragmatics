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
echo Starting server...
nohup ./kwebs >kwebs.out 2>kwebs.err </dev/null &

# Remember process id
PID=$!

# Wait some time just to be sure...
echo Waiting for server to finish bootup process...
sleep 10

# Test if server is running
#if [ ps -A | awk '{print $1}' | grep -q $PID 2>/dev/null ]; then
if [ -f /proc/$PID/exe ]; then

  # If so, store process id in file
  echo KWebS server started, process id is $PID
  echo $PID > kwebs.pid

else

  # If not, exit with an error code
  echo KWebS server failed to start, see kwebs.out and kwebs.err for details
  echo

  echo kwebs.out:
  cat kwebs.out

  echo
  echo kwebs.err:
  cat kwebs.err

  exit 1

fi
