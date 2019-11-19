::You are free to tweak or modify this as you see fit,
::Just please be respectful is all I ask.

::Original Source: Kindle Fire Utility v0.9.6
::Original Author: Vashypooh

::Modified for myKIT_BATCH by Social-Design-Concepts

::  echo. *                                  *                                  * ::this line is used as a reference nothing prints here
    @echo off
    call "%~dp0\myKIT-defaults.bat"
    call "%~dp0\myKIT-extract.bat"
    call %default-color%
    ::need to add something to skip this if the kit has been ran once already
:myKIT_MENU1
    cls
    :: set window title specific to this section
    title %PROGRAM_NAME%
    set nxt=0
    set ric=0
    ::print our default header
    call "%HEADER%"
    echo. install ADB driver or goto the toolKIT
::  echo. *                                  *                                  * ::this line is used as a reference nothing prints here
    echo. =======================================================================
    echo. what would you like to do:
    echo.
    echo.  1 . install / update the ADB driver
    echo.
    echo.  2 . %PROGRAM_NAME%
    echo.
    echo. =======================================================================
    echo.
    set choice=
    echo.&set /p choice=please make a selection or type quit or exit to close the tool kit: ||GOTO:myKIT_MENU1

    :: the standard menu selection visible to the user
    if %choice% == 1 GOTO:DRIVER_INSTALL
    if %choice% == 2 GOTO:myKIT_LOOP

    ::for debugging
    if %choice% == cmd GOTO:CMD_LINE

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
GOTO:myKIT_MENU1

::the main menu loop for device specific myKIT
:myKIT_LOOP
    call "%CHECK_DEVICE%"
    :: set window title specific to this section
    title %PROGRAM_NAME% MAIN MENU
    set nxt=0
    set ric=0
    ::print our default header
    call "%HEADER%"
    echo. MAIN MENU
    echo. =======================================================================
    echo. what would you like to do:
    echo.
    echo.  BASIC ADB TOOLS:
    echo.   1 . backup and restore tool           3 . push / pull
    echo.
    echo.   2 . install / sideload .apk           4 . adb shell
    echo.
    echo.                       5 . restart ADB service
    echo.
    echo.  EXTRAS:
    echo.  E1 . install / update adb driver      E2 . display device info
    echo.
    echo.  ADVANCED:
    echo.  A1 . ROOT KITS                        A2 . PACKAGE PROGRAMS
    echo.
    echo.                      A3 . CLI USE ADB / FASTBOOT
::  echo.  **                  **                **                             * ::this line is used as a reference nothing prints here
    echo.
    echo. =======================================================================
    call "%PRINT_DEVICE%"
    set choice=
    echo.&set /p choice=please make a selection or type quit or exit to close the tool kit: ||GOTO:myKIT_LOOP

    :: the basic ADB tools menu selection visible to the user
    if %choice% == 1 GOTO:ADB_BACKUP_RESTORE
    if %choice% == 2 GOTO:ADB_INSTALL_APK
    if %choice% == 3 GOTO:ADB_PUSH_PULL
    if %choice% == 4 GOTO:ADB_SHELL
    if %choice% == 5 GOTO:ADB_RESTART

    :: the extra tools menu selection visible to the user
    if %choice% == E1 GOTO:DRIVER_INSTALL
    if %choice% == E2 GOTO:ABOUT_DEVICE

    :: the advanced tools menu selection visible to the user
    if %choice% == A1 GOTO:ROOT_KIT
    if %choice% == A2 GOTO:PACKAGE_PROGRAM
    if %choice% == A3 cls &start cmd /K cd %GOOGLE_TOOL% &call "%CLOSE_TOOL%"

    :: we always want to use our close tool to exit the toolKIT
    :: so we remap commonly used commands for exiting
    if %choice% == e CALL "%CLOSE_TOOL%"
    if %choice% == q CALL "%CLOSE_TOOL%"
    if %choice% == exit CALL "%CLOSE_TOOL%"
    if %choice% == quit CALL "%CLOSE_TOOL%"

    :: hidden quick menu selections not displayed to the user
    if %choice% == cmd GOTO:CMD_LINE

    echo.
    echo. invalid input please try again.
    echo.
    pause
GOTO:myKIT_LOOP

::basic ADB tools options
:ADB_BACKUP_RESTORE
    call "%BATCH_TOOL%backup-restore.bat"
GOTO:myKIT_LOOP

:ADB_INSTALL_APK
    call "%BATCH_TOOL%adbinstall.bat"
GOTO:myKIT_LOOP

:ADB_PUSH_PULL
    call "%BATCH_TOOL%adbPushPull.bat"
GOTO:myKIT_LOOP

:ADB_SHELL
    call "%BATCH_TOOL%adbshell.bat"
GOTO:myKIT_LOOP

:ADB_RESTART
    call "%ADB_RESTART%"
GOTO:myKIT_LOOP

::extra menu selections / driver installations
:DRIVER_INSTALL
::what good is a tool kit if you dont have the adb driver
    call "%BATCH_TOOL%install-driver.bat"
GOTO:myKIT_LOOP

:ABOUT_DEVICE
    call "%BATCH_TOOL%about-device.bat"
GOTO:myKIT_LOOP

::advanced menu selections

:ROOT_KIT
    call "%myKIT_TOP%rootKIT-MENU"
GOTO:myKIT_LOOP

:PACKAGE_PROGRAM
    call "%myKIT_TOP%pkgKIT-MENU"
GOTO:myKIT_LOOP

::debug
:CMD_LINE:
    cls
    call "%HEADER%"
    echo.
::  echo. *                                  *                                  * ::this line is used as a reference nothing prints here
    echo.     cli for debugging the toolKIT type " exit " to return to toolKIT
    echo.
    cmd
GOTO:myKIT_LOOP

:CONNECTION_FAILED
    echo.
    echo.       ERROR ! ! ! ERROR PLEASE CHECK YOUR DEVICE ERROR ! ! ! ERROR
    echo.&set /p = FAILED TO COMUNICATE WITH DEVICE [ %status% ] HIT ANY KEY TO RETURN:
GOTO:myKIT_LOOP