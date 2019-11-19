::You are free to tweak or modify this as you see fit,
::Just please be respectful is all I ask.

::Source: Generic Android ToolKit
::Author: Social-Design-Concepts

::called with: call "%BATCH_TOOLS%adbshell.bat"
@echo off
:APP_INSTALLATION
    ::we need to make sure status is ADB-ONLINE
    call "%CHECK_DEVICE%"
    if /i not %status% == ADB-ONLINE ( GOTO:CONNECTION_FAILED )
    cls
    :: set window title specific to this section 
    title %PROGRAM_NAME% SIDELOAD / INSTALL .APK
    cd "%MAIN_DIR%"
    ::print our default header
    call "%HEADER%"
    echo. APK INSTALLATION
    echo. =======================================================================
    echo.
::  echo. *                                  *                                  * ::this line is used as a reference nothing prints here
    echo.    BE SURE TO ENABLE: 
    echo.
    echo.                 Allow installation from unknown sources
    echo.
    echo.    BY CHECKING THE BOX UNDER:
    echo.
    echo.                   Settings / Security / Unknown sources
    echo.
    echo. =======================================================================
    echo.
    echo.         Drag the APP you want to INSTLL here, then press ENTER
    echo.
    echo. to cancell leave blank and just press enter
    set APK=""
    echo.&set /p APK= :
    if /i %APK% == "" ( set APK="" &GOTO:EOF )
    call "%GOOGLE_TOOL%adb" install %APK% &set APK=""
    pause
GOTO:EOF

:CONNECTION_FAILED
    echo.
    echo.       ERROR ! ! ! ERROR PLEASE CHECK YOUR DEVICE ERROR ! ! ! ERROR
    echo.&set /p = FAILED TO COMUNICATE WITH DEVICE [ %status% ] HIT ANY KEY TO RETURN: 
GOTO:EOF