#!/bin/bash

# Shutdown script for the KWebS server
# Version 0.02
#
# Author swe
# Last edit 10.08.2012
#
# Please remember making this script executable by
# doing chmod u+x kwebs_stop.sh

MAIN=de.cau.cs.kieler.kwebs.server.management.ManagementApplication
PLUGIN=`ls -tr ./plugins/de.cau.cs.kieler.kwebs.server*.jar | head -n 1`

./java -classpath $PLUGIN $MAIN command=shutdown $*
