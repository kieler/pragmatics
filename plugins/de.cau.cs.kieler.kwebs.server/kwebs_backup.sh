#!/bin/bash

# Backup script for the KWebS server
# Version 0.01
#
# Author swe
# Last edit 03.08.2012
#
# Please remember making this script executable by
# doing chmod u+x kwebs_backup.sh

# !!!!!
# This script will remove everything contained in the directory ../kwebs-backup
# !!!!!

KWEBS_BACKUP=../kwebs-backup

# Assure backup folder exists and is empty

if [ ! -d $KWEBS_BACKUP ];
then
  mkdir -p $KWEBS_BACKUP
else
    rm -rf $KWEBS_BACKUP/*
fi

# Backup user server configuration file
if [ -f kwebs.user ];
then
    cp -v kwebs.user $KWEBS_BACKUP/
fi

# Backup server configuration and content folder
if [ -d server ];
then
    cp -v -r server $KWEBS_BACKUP/
fi
