::You are free to tweak or modify this as you see fit,
::Just please be respectful is all I ask.

::Original Source: Kindle Fire Utility v0.9.6
::Original Author: Vashypooh

::Modified for Generic Android ToolKit by Social-Design-Concepts
    @echo off
    title %PROGRAM_NAME% CLOSE_TOOL STOP ADB THEN CLOSE toolKIT 
    call "%HEADER%"
    echo. CLOSE_TOOL STOP ADB THEN EXIT 
::  echo. *                                  *                                  * ::this line is used as a reference nothing prints here
    echo. =======================================================================
    echo.
    echo.
    echo.
    echo.
    echo.      we always want to adb kill-server before we close the toolKIT
    echo.         we pause here for debugging where we are in the toolKIT
    echo.
    echo.
    echo.
    echo.
    echo.
    echo.
    echo.
    echo. =======================================================================
    echo.
    echo.
    EndLocal
    call "%GOOGLE_TOOL%adb" kill-server &exit