#!/bin/bash

# Shutdown script for the KWebS server
# Version 0.00
#
# Author swe
# Last edit 10.08.2011

# !!!!!
# Please remember making this script executable by
# doing chmod u+x kwebs_stop.sh
# !!!!!

PIDFILE=kwebs.pid

if [ -f $PIDFILE ];
then

    # Read the pid from file
    read PID < $PIDFILE

    # Kill child processes of the given process id
    for i in `ps -ef | awk '$3 == '$PID' { print $2 }'`
    do
        kill -9 $i
    done

    # Kill process with the given process id
    kill -9 ${PID}

    # Delete the file containing the process id
    rm -rf $PIDFILE

fi

# Wait some time just to be sure...
sleep 5
