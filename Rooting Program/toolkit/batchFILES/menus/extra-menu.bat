::You are free to tweak or modify this as you see fit,
::Just please be respectful is all I ask.

::Original Source: Kindle Fire Utility v0.9.6
::Original Author: Vashypooh

::Modified for Generic Android ToolKit by Social-Design-Concepts

@echo off

::the initial loading menu be for we start the actual toolKIT 
:EXTRA_LOOP
    call "%CHECK_DEVICE%"
    :: set window title specific to this section 
    title %PROGRAM_NAME% EXTRA TOOLS MENU
    cd "%MAIN_DIR%"
    set nxt=0
    set ric=0
    ::print our default header
    call "%HEADER%"
    echo. EXTRA TOOLS MENU
::  echo. *                                  *                                  * ::this line is used as a reference nothing prints here
    echo. =======================================================================
    echo. what would you like to do:
    echo.
    echo. E1 . install / update ADB driver
    echo.
    echo.
    echo.
    echo.
    echo.
    echo.
    echo.
    echo.  0 . previous menu
    echo.
    echo. =======================================================================
    call "%PRINT_DEVICE%"
    set choice=
    echo.&set /p choice=please make a selection or type quit or exit to close the tool kit: ||GOTO:EXTRA_LOOP

    :: the standard menu selection visible to the user
    if %choice% == E1 GOTO:INSTALL_DRIVER
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
    echo. invalid input please try again.
    echo.
    pause
GOTO:EXTRA_LOOP

::menu items
:INSTALL_DRIVER
    call "%BATCH_TOOL%install-driver.bat"
GOTO:EXTRA_LOOP

:ADB_SHELL
    call "%BATCH_TOOL%adbshell.bat"
GOTO:EXTRA_LOOP

:ADB_RESTART:
    call "%ADB_RESTART%"
GOTO:EXTRA_LOOP