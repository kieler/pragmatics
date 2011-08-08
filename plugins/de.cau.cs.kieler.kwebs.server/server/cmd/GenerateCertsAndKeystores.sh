#!/bin/bash

function CreateOpenSSLInf() {
    SERIAL=
    for i in `seq 1 10`;
    do
      R=$RANDOM
      SERIAL=$SERIAL${R:0:1}
    done
    echo $SERIAL>serial
    echo -n>index.txt
}

function RemoveTemps() {
    if [ "$1"=="true" ]; 
    then
        rm -rf *.jks
    fi
    rm -rf *.der
    rm -rf *.pem
    rm -rf *.pkcs12
    rm -rf index*
    rm -rf serial*
    rm -rf rand*
}

echo KWebS - KIELER Web Service for layout
echo
echo Generator script for server side self signed certificate and key store
echo and client side trust store
echo
echo Copyright 2011 by Real-Time and Embedded Systems Group, Department
echo of Computer Science, Christian-Albrechts-University of Kiel
echo Published under the EPL v1.0 \(see http://www.eclipse.org/legal/epl-v10.html\)
echo

if [ "$JAVA_HOME" == "" ];
then
    echo You must set JAVA_HOME to point at your Java Development Kit installation
    exit 1
fi

KEYTOOL=$JAVA_HOME/bin/keytool

RemoveTemps true
CreateOpenSSLInf

#The common name (CN) field of our self signed root ca certificate as defined in 'ca.conf'
TRUSTCA_ALIAS="RTSYS CA"

#The common name \(CN\) field of the server certificate as defined in 'csr.conf'
SERVER_ALIAS=KIELERServer

#The common name \(CN\) field of the client certificate
CLIENT_ALIAS=KIELERClient

#The number of days a certificate shall be valid
VALIDITY=3650

echo Please enter the password for the servers java keystore:
read SERVER_PASS

#Generate CA
ERRORLEVEL=$(openssl req -new -x509 -out cacert.pem -days $VALIDITY -config ca.conf)

if [ "$RESULT" == "0" ];
then
    exit 1;
fi

#Generate certificate signing request
ERRORLEVEL=$(openssl req -new -nodes -out servercsr.pem -config csr.conf)

if [ "$RESULT" == "0" ];
then
    exit 1;
fi

#Sign server certificate
ERRORLEVEL=$(openssl ca -in servercsr.pem -notext -out servercert.pem -config sign.conf)

if [ "$RESULT" == "0" ];
then
    exit 1;
fi

#Java keytool can not import private keys so we make a key store in pkcs12 format which we will later
#convert to java key store format by importing it with the keytool
ERRORLEVEL=$(openssl pkcs12 -passout pass:$SERVER_PASS -export -in servercert.pem -inkey serverkey.pem -out server.pkcs12)

if [ "$RESULT" == "0" ];
then
    exit 1;
fi

#Import the PKCS12 key store into a newly created java key store
ERRORLEVEL=$($KEYTOOL -importkeystore -deststorepass $SERVER_PASS -destkeypass $SERVER_PASS -destkeystore server.jks -srckeystore server.pkcs12 -srcstoretype PKCS12 -srcstorepass $SERVER_PASS -srcalias 1 -destalias $SERVER_ALIAS)

if [ "$RESULT" == "0" ];
then
    exit 1;
fi

#Change format of the ca certificate from PEM to DER
ERRORLEVEL=$(openssl x509 -in cacert.pem -inform PEM -out cacert.der -outform DER)

if [ "$RESULT" == "0" ];
then
    exit 1;
fi

#Change format of the server certificate from PEM to DER
ERRORLEVEL=$(openssl x509 -in servercert.pem -inform PEM -out servercert.der -outform DER)

if [ "$RESULT" == "0" ];
then
    exit 1;
fi

echo Please enter the password for the clients java trust store:
read CLIENT_PASS

#Add the server certificate to the client trust store
ERRORLEVEL=$($KEYTOOL -import -v -trustcacerts -alias "$SERVER_ALIAS" -file servercert.der -keystore client.jks -keypass $CLIENT_PASS -storepass $CLIENT_PASS)

if [ "$RESULT" == "0" ];
then
    exit 1;
fi

#Add the ca certificate to the client trust store to complete the verification chain
ERRORLEVEL=$($KEYTOOL -import -v -trustcacerts -alias "$TRUSTCA_ALIAS" -file cacert.der -keystore client.jks -keypass $CLIENT_PASS -storepass $CLIENT_PASS)

if [ "$RESULT" == "0" ];
then
    exit 1;
fi

if [ ! -d "../kwebs/security/keystores" ];
then
    mkdir -p "../kwebs/security/keystores"
fi

if [ ! -d "../kwebs/web/security" ];
then
    mkdir -p "../kwebs/web/security"
fi

cp *.jks ../kwebs/security/keystores/
cp client.jks ../kwebs/web/security/

RemoveTemps
