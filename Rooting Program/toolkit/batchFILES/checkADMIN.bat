::You are free to tweak or modify this as you see fit,
::Just please be respectful is all I ask.

::Source: Generic Android ToolKit
::Author: Social-Design-Concepts

@echo off

::called with: call "%CHECK_ADMIN%"

goto check_Permissions

:check_Permissions
    cls
    :: set window title specific to this section 
    title %PROGRAM_NAME% CHECK ADMIN PERMISSION
    set nxt=0
    set ric=0
    ::print our default header
    call "%HEADER%"
    echo. MAIN MENU
    echo. =======================================================================
    echo.       administrative permissions required. detecting permissions...
::  echo. *                                  *                                  * ::this line is used as a reference nothing prints here
NET SESSION >nul 2>&1
    IF %ERRORLEVEL% EQU 0 (
            ECHO. administrative privileges detected!
            GOTO:EOF
    ) ELSE (
            ECHO. NOT AN ADMIN!
            PAUSE
            CALL "%CLOSE_TOOL%"
    )
>nul
:EOF