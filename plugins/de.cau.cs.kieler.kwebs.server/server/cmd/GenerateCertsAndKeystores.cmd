	@ECHO OFF
	
	SETLOCAL
	
	IF "%JAVA_HOME%" EQU "" GOTO NoJavaHome
	
	SET KEYTOOL="%JAVA_HOME%\bin\keytool"
	
	::have to be the same
	SET SERVER_KEYPASS=svrpass123
	SET SERVER_STOREPASS=svrpass123
	
	SET SERVER_ALIAS=KIELERLayoutServer
	
	::have to be the same
	SET CLIENT_KEYPASS=clnpass123
	SET CLIENT_STOREPASS=clnpass123
	
	SET CLIENT_ALIAS=KIELERLayoutClient
	
	SET ALGORITHM=DSA
	
	::IF EXIST "*.cer" DEL /Q "*.cer"	
	::IF EXIST "*.jks" DEL /Q "*.jks"
	
	ECHO Generating the Server KeyStore in file server.jks
	%KEYTOOL% -genkey -alias %SERVER_ALIAS% -dname "CN=localhost, OU=X, O=Y, L=Z, S=XY, C=YZ" -keyalg %ALGORITHM% -keypass %SERVER_KEYPASS% -storepass %SERVER_STOREPASS% -keystore server.jks
	
	ECHO Exporting the certificate from keystore to an external file server.cer
	%KEYTOOL% -export -alias %SERVER_ALIAS% -storepass %SERVER_STOREPASS% -file server.cer -keystore server.jks
	
	ECHO Generating the Client KeyStore in file client.jks
	%KEYTOOL% -genkey -alias %CLIENT_ALIAS% -dname "CN=Client, OU=X, O=Y, L=Z, S=XY, C=YZ" -keyalg %ALGORITHM% -keypass %CLIENT_KEYPASS% -storepass %CLIENT_STOREPASS% -keystore client.jks
	
	ECHO Exporting the certificate from keystore to external file client.cer
	%KEYTOOL% -export -alias %CLIENT_ALIAS% -storepass %CLIENT_STOREPASS% -file client.cer -keystore client.jks
	
	ECHO Importing Client's certificate into Server's keystore
	%KEYTOOL% -import -v -trustcacerts -alias tomcat -file server.cer -keystore client.jks -keypass %CLIENT_KEYPASS% -storepass %CLIENT_STOREPASS%
	
	ECHO Importing Server's certificate into Client's keystore
	%KEYTOOL% -import -v -trustcacerts -alias tomcat -file client.cer -keystore server.jks -keypass %SERVER_KEYPASS% -storepass %SERVER_STOREPASS%

	MOVE /Y "*.cer" "..\kwebs\security\certs\"
	MOVE /Y "*.jks" "..\kwebs\security\keystores\"
	
	GOTO :end
	
:NoJavaHome

	ECHO You must set JAVA_HOME to point at your Java Development Kit installation

:end

	ENDLOCAL
