#!/bin/bash

if [[ -z "$1" ]]; then echo -e "\nusage: start [models-folder]"; exit 1; fi

./KlighDning.exe -rootFolder $1