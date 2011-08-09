#!/bin/bash

# Backup script for the KWebS server
# Version 0.00
#
# Author swe
# Last edit 10.08.2011

# !!!!!
# Please remember making this script executable by
# doing chmod u+x kwebs_backup.sh
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
    mv kwebs.user ../kwebs-backup
fi

# Backup server configuration and content folder
if [ -d server ];
then
    mkdir -p $KWEBS_BACKUP/server
    mv server/* $KWEBS_BACKUP/server
fi