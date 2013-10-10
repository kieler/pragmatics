#!/bin/bash

if [[ -z "$1" ]]; then echo -e "\nusage: start.sh [models-folder]"; exit 1; fi

./KlighDning -rootFolder $1 &