::You are free to tweak or modify this as you see fit,
::Just please be respectful is all I ask.

::Original Source: Kindle Fire Utility v0.9.6
::Original Author: Vashypooh

::Modified for Generic Android ToolKit by Social-Design-Concepts

@echo off

call "%HEADER%"
call "%ADB_RESTART%"
GOTO:MENU_LOOP

::the initial loading menu be for we start the actual toolKIT 
:MENU_LOOP
    call "%CHECK_DEVICE%"
    :: set window title specific to this section 
    title %PROGRAM_NAME% MAIN MENU
    set nxt=0
    set ric=0
    ::print our default header
    call "%HEADER%"
    echo. MAIN MENU
::  echo. *                                  *                                  * ::this line is used as a reference nothing prints here
    echo. =======================================================================
    echo. what would you like to do:
    echo.
    echo.  1 . backup / restore device apps and settings
    echo.
    echo.  2 . menu adb tools
    echo.
    echo.  3 . menu extra tools
    echo.
    echo.  4 . menu advanced
    echo.
    echo.  I . device info
    echo.
    echo. =======================================================================
    call "%PRINT_DEVICE%"
    set choice=
    echo.&set /p choice=please make a selection or type quit or exit to close the tool kit: ||GOTO:MENU_LOOP

    :: the standard menu selection visible to the user
    if %choice% == 1 GOTO:BACKUP_RESTORE
    if %choice% == 2 GOTO:MENU_ADB_TOOLS
    if %choice% == 3 GOTO:MENU_EXTRA
    if %choice% == 4 GOTO:MENU_ADVANCED
    if %choice% == I GOTO:ABOUT_DEVICE

    :: hidden quick menu selections not displayed to the user
    if %choice% == adbshell GOTO:ADB_SHELL
    if %choice% == adbstartserver GOTO:ADB_RESTART
    if %choice% == cmd GOTO:CMD_LINE

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
GOTO:MENU_LOOP

::menu items
:BACKUP_RESTORE
    call "%BATCH_TOOL%backup-restore.bat"
GOTO:MENU_LOOP

:MENU_ADB_TOOLS
    call "%BATCH_MENU%adbtools-menu.bat"
GOTO:MENU_LOOP

:MENU_EXTRA
    call "%BATCH_MENU%extra-menu.bat"
GOTO:MENU_LOOP

:MENU_ADVANCED
    call "%BATCH_MENU%advanced-menu.bat"
GOTO:MENU_LOOP

:ABOUT_DEVICE
    call "%BATCH_TOOL%about-device.bat"
GOTO:MENU_LOOP

:ADB_SHELL
    call "%BATCH_TOOL%adbshell.bat"
GOTO:MENU_LOOP

:ADB_RESTART:
    call "%ADB_RESTART%"
GOTO:MENU_LOOP

:CMD_LINE:
    cmd
GOTO:MENU_LOOP