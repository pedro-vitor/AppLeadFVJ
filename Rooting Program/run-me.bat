::You are free to tweak or modify this as you see fit,
::Just please be respectful is all I ask.

::Original Source: Kindle Fire Utility v0.9.6
::Original Author: Vashypooh

::Modified for myKIT_BATCH by Social-Design-Concepts

::This is the main launcher for the toolKIT

::we set default toolKIT parameters from here so that the other toolKITs can use them
::we then prompt the user with a disclaimer asking them to ACCEPT before continuing to the bulk of the toolKIT

::Prepare the Command Processor
SETLOCAL ENABLEEXTENSIONS
SETLOCAL ENABLEDELAYEDEXPANSION

echo off
cls

::setup the window size 
mode con:cols=80 lines=60
::setup background and foreground color
cls
color 0B

::check our shell
:CHECKSHELL
    if %COMSPEC%==%SYSTEMROOT%\system32\cmd.exe goto SHELLOK
    echo.
    echo ERROR: You must run this script from the standard Windows Command Shell
    echo (%SYSTEMROOT%\system32\cmd.exe). Please start this shell first before
    echo running this script.
    pause
GOTO:EOF

:SHELLOK
    ::before we go any further we need to make sure
    ::that we are in the root of the toolKIT package
    ::that this batch the run-me.bat is located
    cd "%~dp0"

    ::set some standard directories and other things that get called a lot
    set TOOL_TOP=%~dp0
    ::use short name for TOOL_TOP
    for %%f in ("%TOOL_TOP%") do set TOOL_TOP=%%~sf
    call "%TOOL_TOP%toolkit\batchFILES\setup-toolKIT.bat"
::    echo debbuging white-space issue type :exit to start myKIT_BATCH
::    echo.
::    echo myKIT_BATCH TOOL_TOP is:
::    echo %TOOL_TOP%
::    echo.
::    cmd

::after all the defaults are set we go to our disclaimer
GOTO:ACCEPT_LOOP

::start of the main batch operation
:ACCEPT_LOOP
    :: set window title specific to this section 
    title %PROGRAM_NAME% disclaimer
    ::print our default header
    call "%HEADER%"
    echo. READ ME:
::  echo. *                                  *                                  * ::this line is used as a centering reference nothing prints here
    echo. =======================================================================
    echo. standard disclaimer:
    echo.
    echo.
    echo.            by proceeding with this toolKIT you accept that
    echo.                  it is carried out at your own risk
    echo.             and you will not hold anyone else responsible
    echo.
    echo.
    echo.   . type " ACCEPT " without quotes to continue to the toolKIT
    echo.
    echo.   . type quit or exit to close this window
    echo.
    echo. =======================================================================
    echo.
    set choice=
    echo.&set /p choice=: ||goto :ACCEPT_LOOP

    :: the only accepted answer to continue 
    if %choice% == ACCEPT goto :MENU_MAIN
 
     :: hidden quick menu selections not displayed to the user
    if %choice% == adbshell GOTO:ADB_SHELL
    if %choice% == adbstartserver GOTO:ADB_RESTART
 
    :: we always want to use our close tool to exit the toolKIT
    :: so we remap commonly used commands for exiting
    if %choice% == e CALL "%CLOSE_TOOL%"
    if %choice% == q CALL "%CLOSE_TOOL%"
    if %choice% == exit CALL "%CLOSE_TOOL%"
    if %choice% == quit CALL "%CLOSE_TOOL%"
    echo.
    echo. invalid input please try again.
    echo.
    pause
GOTO:ACCEPT_LOOP

::menus to launch
:MENU_MAIN
    call "%mainMENU%"
CALL "%CLOSE_TOOL%"

:ADB_SHELL
    call "%BATCH_TOOL%adbshell.bat"
GOTO:MENU_MAIN

:ADB_RESTART:
    call "%ADB_RESTART%"
GOTO:ADB_SHELL