#!/bin/bash

if [[ -z "$1" ]]; then echo -e "\nusage: start.sh [models-folder]"; exit 1; fi

nohup ./KlighDning -rootFolder $1 >/dev/null &
