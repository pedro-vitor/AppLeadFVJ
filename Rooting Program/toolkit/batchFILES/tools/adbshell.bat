::You are free to tweak or modify this as you see fit,
::Just please be respectful is all I ask.

::Source: Generic Android ToolKit
::Author: Social-Design-Concepts

::called with: call "%BATCH_TOOLS%adbshell.bat"
@echo off
:adbSHELL
    @echo off
    call "%CHECK_DEVICE%"
    ::we need to make sure status is ADB-ONLINE
    if /i not %status% == ADB-ONLINE ( GOTO:CONNECTION_FAILED )
    title %PROGRAM_NAME% ADB SHELL
    call "%HEADER%"
    echo. ADB SHELL           to close the adb shell type exit
::  echo. *                                  *                                  * ::this line is used as a reference nothing prints here
    echo. =======================================================================
    call "%PRINT_DEVICE%"
    start %TOOL_TOP%toolkit\other\mintty.exe -e "%GOOGLE_TOOL%adb" shell
GOTO:EOF

:CONNECTION_FAILED
    echo.
    echo.       ERROR ! ! ! ERROR PLEASE CHECK YOUR DEVICE ERROR ! ! ! ERROR
    echo.&set /p = FAILED TO COMUNICATE WITH DEVICE [ %status% ] HIT ANY KEY TO RETURN: 
GOTO:EOF