    @ECHO OFF

    REM Shutdown script for the KWebS server
    REM Version 0.02
    REM
    REM Author swe
    REM Last edit 09.08.2012

    SETLOCAL

    SET MAIN=de.cau.cs.kieler.kwebs.server.management.ManagementApplication
    SET PLUGIN=

    FOR /F "" %%i IN ('DIR /B /O-D "plugins\de.cau.cs.kieler.kwebs.server*.jar"') DO (
        SET PLUGIN=%%i
    )

    java -classpath ".\plugins\%PLUGIN%" %MAIN% command=shutdown %*

    GOTO :end

:end

    ENDLOCAL
