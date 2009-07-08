#! /bin/sh

cd $1
export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:.
chmod +x dot
./dot -Tdot
