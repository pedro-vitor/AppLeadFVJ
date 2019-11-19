::You are free to tweak or modify this as you see fit,
::Just please be respectful is all I ask.

::Source: Generic Android ToolKit
::Author: Social-Design-Concepts

::called with: call "%BATCH_TOOLS%"backup-restore.bat
@echo off
:ADBTOOLS_LOOP
    call "%CHECK_DEVICE%"
    title %PROGRAM_NAME% ADB TOOLS
    cd "%MAIN_DIR%"
    ::print our default header
    call "%HEADER%"
    echo. ADB TOOLS
::  echo. *                                  *                                  * ::this line is used as a reference nothing prints here
    echo. =======================================================================
    echo. what would you like to do:
    echo.
    echo.  1 . backup / restore device apps and settings
    echo.
    echo.  2 . sideload / install .apk
    echo.
    echo.  3 . push / pull file
    echo.
    echo.  4 . launch an adb shell
    echo.
    echo.  0 . previous menu
    echo.
    echo. =======================================================================
    call "%PRINT_DEVICE%"
    set choice=
    echo.&set /p choice=please make a selection or type quit or exit to close the tool kit: ||GOTO:ADBTOOLS_LOOP

    :: the standard menu selection visible to the user
    if %choice% == 1 GOTO:ADB_BACKUP_RESTORE
    if %choice% == 2 GOTO:ADB_INSTALL_APK
    if %choice% == 3 GOTO:ADB_PUSH_PULL
    if %choice% == 4 GOTO:ADB_SHELL
    if %choice% == 0 call exit/B

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
    echo. invalid selection please try again.
    echo.
    pause
GOTO:ADBTOOLS_LOOP

:ADB_BACKUP_RESTORE
    call "%BATCH_TOOL%backup-restore.bat"
GOTO:ADBTOOLS_LOOP

:ADB_INSTALL_APK
    call "%BATCH_TOOL%adbinstall.bat"
GOTO:ADBTOOLS_LOOP

:ADB_PUSH_PULL
    call "%BATCH_TOOL%adbPushPull.bat"
GOTO:ADBTOOLS_LOOP

:ADB_SHELL
    call "%BATCH_TOOL%adbshell.bat"
GOTO:ADBTOOLS_LOOP

:ADB_RESTART:
    call "%ADB_RESTART%"
GOTO:ADBTOOLS_LOOP

:DEVICE_NOT_CONNECTED_SETUP
    echo.
    color 0C
    echo. ERROR DEVICE NOT FOUND....
    echo.
    pause &cls &color 0B
GOTO:ADBTOOLS_LOOP