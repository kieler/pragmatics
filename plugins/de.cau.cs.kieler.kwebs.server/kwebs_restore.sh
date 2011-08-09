#!/bin/bash

# Restore script for the KWebS server
# Version 0.00
#
# Author swe
# Last edit 10.08.2011

# !!!!!
# Please remember making this script executable by
# doing chmod u+x kwebs_restore.sh
# !!!!!

KWEBS_BACKUP=../kwebs-backup

# Assure backup folder exists

if [ ! -d $KWEBS_BACKUP ];
then
  echo Backup does not exist
  exit 1
fi

# Restore user server configuration file
if [ -f $KWEBS_BACKUP/kwebs.user ];
then
    mv $KWEBS_BACKUP/kwebs.user .
fi

SERVER_SSL_CONTENT=server/kwebs/security/keystores
WEB_SSL_CONTENT=server/kwebs/web/security

# Restore server SSL configuration
if [ -d $KWEBS_BACKUP/$SERVER_SSL_CONTENT ];
then
    mkdir -p $SERVER_SSL_CONTENT
    mv $KWEBS_BACKUP/$SERVER_SSL_CONTENT/*.jks $SERVER_SSL_CONTENT
fi

# Restore user SSL configuration
if [ -d $KWEBS_BACKUP/$WEB_SSL_CONTENT ];
then
    mkdir -p $WEB_SSL_CONTENT
    mv $KWEBS_BACKUP/$WEB_SSL_CONTENT/client.jks $WEB_SSL_CONTENT
fi