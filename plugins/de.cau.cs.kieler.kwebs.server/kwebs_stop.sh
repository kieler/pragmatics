#!/bin/bash

# Shutdown script for the KWebS server
# Version 0.01
#
# Author swe
# Last edit 03.08.2012
#
# Please remember making this script executable by
# doing chmod u+x kwebs_stop.sh

# !!!!!
# This script relies on a file "kwebs.pid" containing the process id of the
# currently running server. If the server was started in another directory
# or it was not started with the "kwebs_start.sh" script, it will not be
# affected by this script. In such cases the process must be stopped manually.
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
    
    echo "KWebS server stopped, process id was $PID"

    # Delete the file containing the process id
    rm -rf $PIDFILE

	# Wait some time just to be sure...
	sleep 5
	
fi

