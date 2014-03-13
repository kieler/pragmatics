    @ECHO OFF

    REM Startup script for the KWebS server
    REM Version 0.02
    REM
    REM Author swe
    REM Last edit 09.08.2012

    SETLOCAL

    ".\kwebs.exe" %* >kwebs.out 2>kwebs.err

    GOTO :end

:end

    ENDLOCAL
