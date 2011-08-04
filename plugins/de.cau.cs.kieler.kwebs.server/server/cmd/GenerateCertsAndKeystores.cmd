    @ECHO OFF

    ECHO KWebS - KIELER Web Service for layout
    ECHO.
    ECHO Generator script for server side self signed certificate and key store
    ECHO and client side trust store
    ECHO.
    ECHO Copyright 2011 by Real-Time and Embedded Systems Group, Department
    ECHO of Computer Science, Christian-Albrechts-University of Kiel
    ECHO Published under the EPL v1.0 (see http://www.eclipse.org/legal/epl-v10.html)
    ECHO.

    SETLOCAL
    SETLOCAL ENABLEEXTENSIONS
    SETLOCAL ENABLEDELAYEDEXPANSION

    IF "%JAVA_HOME%" EQU "" GOTO NoJavaHome

    SET KEYTOOL="%JAVA_HOME%\bin\keytool"

    CALL :RemoveTemps TRUE
    CALL :CreateOpenSSLInf

    ::The common name (CN) field of our self signed root ca certificate as defined in 'ca.conf'
    SET TRUSTCA_ALIAS=RTSYS CA

    ::The common name (CN) field of the server certificate as defined in 'csr.conf'
    SET SERVER_ALIAS=KIELERServer

    ::The common name (CN) field of the client certificate
    SET CLIENT_ALIAS=KIELERClient

    ::The number of days a certificate shall be valid
    SET VALIDITY=3650

    ECHO Please enter the password for the servers java keystore:
    SET /P INPUT=

    SET SERVER_PASS=%INPUT%
    ::SET SERVER_PASS=server

    ::Generate CA
    openssl req -new -x509 -out cacert.pem -days %VALIDITY% -config ca.conf

    IF "%ERRORLEVEL%" NEQ "0" GOTO :end

    ::Generate certificate signing request
    openssl req -new -nodes -out servercsr.pem -config csr.conf

    IF "%ERRORLEVEL%" NEQ "0" GOTO :end

    ::Sign server certificate
    openssl ca -in servercsr.pem -notext -out servercert.pem -config sign.conf

    IF "%ERRORLEVEL%" NEQ "0" GOTO :end

    ::Java keytool can not import private keys so we make a key store in pkcs12 format which we will later
    ::convert to java key store format by importing it with the keytool
    openssl pkcs12 -passout pass:%SERVER_PASS% -export -in servercert.pem -inkey serverkey.pem -out server.pkcs12

    IF "%ERRORLEVEL%" NEQ "0" GOTO :end

    ::Import the PKCS12 key store into a newly created java key store
    %KEYTOOL% -importkeystore -deststorepass %SERVER_PASS% -destkeypass %SERVER_PASS% -destkeystore server.jks -srckeystore server.pkcs12 -srcstoretype PKCS12 -srcstorepass %SERVER_PASS% -srcalias 1 -destalias %SERVER_ALIAS%

    IF "%ERRORLEVEL%" NEQ "0" GOTO :end

    ::Change format of the ca certificate from PEM to DER
    openssl x509 -in cacert.pem -inform PEM -out cacert.der -outform DER

    IF "%ERRORLEVEL%" NEQ "0" GOTO :end

    ::Change format of the server certificate from PEM to DER
    openssl x509 -in servercert.pem -inform PEM -out servercert.der -outform DER

    IF "%ERRORLEVEL%" NEQ "0" GOTO :end

    ECHO Please enter the password for the clients java trust store:
    SET /P INPUT=

    SET CLIENT_PASS=%INPUT%
    ::SET CLIENT_PASS=client

    ::Add the server certificate to the client trust store
    %KEYTOOL% -import -v -trustcacerts -alias "%SERVER_ALIAS%" -file servercert.der -keystore client.jks -keypass %CLIENT_PASS% -storepass %CLIENT_PASS%

    IF "%ERRORLEVEL%" NEQ "0" GOTO :end

    ::Add the ca certificate to the client trust store to complete the verification chain
    %KEYTOOL% -import -v -trustcacerts -alias "%TRUSTCA_ALIAS%" -file cacert.der -keystore client.jks -keypass %CLIENT_PASS% -storepass %CLIENT_PASS%

    IF "%ERRORLEVEL%" NEQ "0" GOTO :end

    CALL :RemoveTemps

    GOTO :end

:NoJavaHome

    ECHO You must set JAVA_HOME to point at your Java Development Kit installation

    GOTO :end

:CreateOpenSSLInf

    SET SERIAL=
    FOR /L %%i IN (0,1,9) DO (
        SET R=!RANDOM!
        SET SERIAL=!SERIAL!!R:~-1!
    )

    ECHO !SERIAL!>"serial"
    ECHO OFF>"index.txt"

    GOTO :EOF

:RemoveTemps

    IF "%~1" EQU "TRUE" (
        IF EXIST "*.jks" DEL /Q "*.jks"
    )

    IF EXIST "*.der"    DEL /Q "*.der"
    IF EXIST "*.pem"    DEL /Q "*.pem"
    IF EXIST "*.pkcs12" DEL /Q "*.pkcs12"
    IF EXIST "index*"   DEL /Q "index*"
    IF EXIST "serial*"  DEL /Q "serial*"
    IF EXIST "rand*"  DEL /Q "rand*"

    GOTO :EOF

:end

    ENDLOCAL
