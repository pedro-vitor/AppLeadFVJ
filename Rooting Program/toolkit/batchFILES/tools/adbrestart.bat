::You are free to tweak or modify this as you see fit,
::Just please be respectful is all I ask.

::Source: Generic Android ToolKit
::Author: Social-Design-Concepts

::called with: call "%ADB_RESTART%"
@echo off
goto adbrestart

:adbRESTART
    title "%PROGRAM_NAME%" resetting Andriid Debugging Bridge
    ::print our default header
    call "%HEADER%"
    call "%GOOGLE_TOOL%adb" kill-server
    call "%GOOGLE_TOOL%adb" start-server
    cls
    echo. =======================================================================
GOTO:EOF