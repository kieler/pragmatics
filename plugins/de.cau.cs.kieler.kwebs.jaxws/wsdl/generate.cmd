	@ECHO OFF
	
	ECHO.
	ECHO KWebS - KIELER Web Service for layout
	ECHO.
	ECHO Generator script for JAX-WS implementation classes
	ECHO.
	ECHO Copyright 2011 by Real-Time and Embedded Systems Group, Department
	ECHO of Computer Science, Christian-Albrechts-University of Kiel
	ECHO Published under the EPL v1.0 (see http://www.eclipse.org/legal/epl-v10.html)
	ECHO.
	
	SETLOCAL
	
	IF "%JAVA_HOME%"=="" GOTO :NoJavaHome
	IF "%JAXWS_HOME%"=="" GOTO :NoJaxWsHome
	
	IF EXIST "..\src\de\cau\cs\kieler\kwebs\jaxws" DEL /Q "..\src\de\cau\cs\kieler\kwebs\jaxws\*.java"
	IF EXIST "..\bin" rd /q /s "..\bin"
	
	IF NOT EXIST "..\src\de\cau\cs\kieler\kwebs\jaxws" MD "..\src\de\cau\cs\kieler\kwebs\jaxws"
	IF NOT EXIST "..\bin" MD "..\bin"
	
	"%JAVA_HOME%\bin\java.exe" -jar "%JAXWS_HOME%\lib\jaxws-tools.jar" -Xendorsed -keep -s "..\src" -d "..\bin" -p de.cau.cs.kieler.kwebs.jaxws layout.wsdl 
	
	GOTO :end

:NoJavaHome
	
	ECHO You have to set the environment variable JAVA_HOME pointing to your
	ECHO local Java installation.

	GOTO :EOF

:NoJaxWsHome
	
	ECHO You have to set the environment variable JAXWS_HOME pointing to your
	ECHO local installation of the JAX-WS reference implementation.

	GOTO :EOF

:end

	ENDLOCAL