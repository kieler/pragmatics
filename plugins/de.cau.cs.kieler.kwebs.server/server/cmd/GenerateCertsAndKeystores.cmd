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
	ECHO If you generate for the secure access of the web service you will have to copy
	ECHO the generated client trust store to 
	ECHO     server/kwebs/web/security/client.jks
	ECHO if you want it to be accessible to users by the web frontend. If you generate
	ECHO for the management service make sure that the servers key store lies under
	ECHO     server/kwebs/security/mserver.jks
	ECHO and that the key store for the client is not accessible from the server.
	
    SETLOCAL
    SETLOCAL ENABLEEXTENSIONS
    SETLOCAL ENABLEDELAYEDEXPANSION

	::The full path to the java key tool
    SET KEYTOOL="%JAVA_HOME%\bin\keytool"
    
    ::The full path to the OpenSSL configuration file 'openssl.cnf'; has to be set by user
	SET OPENSSL_CONF=

    IF "%JAVA_HOME%"    EQU "" GOTO NoJavaHome
    IF "%OPENSSL_CONF%" EQU "" GOTO NoOpenSSL
	
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
	
    ECHO Please enter the name for the file storing the server key store:
    SET SERVER_FILE=server.jks
    CALL :GetFname SERVER_FILE server.jks
    
    ECHO Please enter the name for the file storing the clients trust store:
    SET CLIENT_FILE=client.jks
    CALL :GetFname CLIENT_FILE client.jks
	
	REN "server.jks" "!SERVER_FILE!"
	REN "client.jks" "!CLIENT_FILE!"
	
	ECHO Copying key store and trust store to the servers security config folder.
	
	IF NOT EXIST "..\kwebs\security\keystores\" MKDIR "..\kwebs\security\keystores\"
	IF NOT EXIST "..\kwebs\web\security\"       MKDIR "..\kwebs\web\security\"
	
	COPY /Y "*.jks" "..\kwebs\security\keystores\"
	
    CALL :RemoveTemps

    GOTO :end

:GetFname %1 %2

	SET /P %1=
	
	IF "!%1!" EQU "" SET %1=%~2
	
	GOTO :EOF
	
:NoJavaHome

    ECHO You must set JAVA_HOME to point at your Java Development Kit installation

    GOTO :end

:NoJavaHome

    ECHO You must configure the full path to the OpenSSL configuration file 'openssl.cnf' packed
    ECHO with your OpenSSL distribution in the scripts environment variable 'OPENSSL_CONF' 

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
