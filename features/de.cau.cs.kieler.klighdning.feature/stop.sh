#!/bin/bash

echo "Stopping KLighDning ... "
for PID in `ps ax | grep -i 'klighdning' | grep -v 'grep' | grep -v 'bash' | awk '{ print $1 }'`; do
   echo " Killing pid ${PID}"
   kill -9 $PID
done
echo "Done ..."
