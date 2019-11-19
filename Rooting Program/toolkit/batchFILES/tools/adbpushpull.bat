::You are free to tweak or modify this as you see fit,
::Just please be respectful is all I ask.

::Source: Generic Android ToolKit
::Author: Social-Design-Concepts

::called with: call "%BATCH_TOOLS%adbpushpull.bat"
@echo off

::the initial loading menu be for we start the actual toolKIT 
:MENU_LOOP
    call "%CHECK_DEVICE%"
    cls
    :: set window title specific to this section 
    title %PROGRAM_NAME% ADB PUSH / PULL
    cd "%MAIN_DIR%"
    set nxt=0
    set ric=0
    ::print our default header
    call "%HEADER%"
    echo. ADB PUSH / PULL
::  echo. *                                  *                                  * ::this line is used as a reference nothing prints here
    echo. =======================================================================
    echo. what would you like to do:
    echo.
    echo.  1 . push a file 
    echo.
    echo.  2 . pull a file
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
    echo.&set /p choice=please make a selection or type quit or exit to close the tool kit: ||GOTO:MENU_LOOP

    :: the standard menu selection visible to the user
    if %choice% == 1 GOTO:PUSH_ADB
    if %choice% == 2 GOTO:PULL_ADB
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
GOTO:MENU_LOOP

:PUSH_ADB
    ::we need to make sure status is ADB-ONLINE
    call "%CHECK_DEVICE%"
    if /i not %status% == ADB-ONLINE ( GOTO:CONNECTION_FAILED )
    cls
    :: set window title specific to this section 
    title %PROGRAM_NAME% PUSH A FILE
    cd "%MAIN_DIR%"
    ::print our default header
    call "%HEADER%"
    echo. PUSH A FILE
    echo. =======================================================================
    echo.
    echo.
::  echo. *                                  *                                  * ::this line is used as a reference nothing prints here
    echo.       [ type the path / location you wish to push the file to ]
    echo.
    echo.
    echo.                   (note: it CAN NOT contain spaces )
    echo.
    echo.
    echo.                    [ THEN PRESS ENTER TO CONTINUE ]
    echo.
    echo.
    echo.
    echo. =======================================================================
    echo.
    echo. to cancell leave blank and just press enter
    set PUSH-DIR=""
    echo.&set /P PUSH-DIR= :
    
    if /i %PUSH-DIR% == "" ( GOTO:MENU_LOOP )

    cls
    :: set window title specific to this section 
    title %PROGRAM_NAME% PUSH A FILE
    cd "%MAIN_DIR%"
    ::print our default header
    call "%HEADER%"
    echo. PUSH A FILE
    echo. =======================================================================
    echo.
    echo.
::  echo. *                                  *                                  * ::this line is used as a reference nothing prints here
    echo.             [ now drag the file you want to push here ]
    echo.
    echo.
    echo.
    echo.
    echo.
    echo.                    [ THEN PRESS ENTER TO CONTINUE ]
    echo.
    echo.
    echo.
    echo. =======================================================================
    echo.
    echo. to cancell leave blank and just press enter
    set PUSH-FILE=""
    echo.&set /P PUSH-FILE= :
    if /i %PUSH-FILE% == "" ( GOTO:MENU_LOOP )
    "%GOOGLE_TOOL%adb" push %PUSH-FILE% %PUSH-DIR% &set PUSH="" &set PUSH-DIR=""
    pause
GOTO:MENU_LOOP

:PULL_ADB
    ::we need to make sure status is ADB-ONLINE
    call "%CHECK_DEVICE%"
    if /i not %status% == ADB-ONLINE ( GOTO:CONNECTION_FAILED )
    cls
    :: set window title specific to this section 
    title %PROGRAM_NAME% PULL A FILE
    cd "%MAIN_DIR%"
    ::print our default header
    call "%HEADER%"
    echo. PULL FILE
    echo. =======================================================================
    echo.
    echo.
::  echo. *                                  *                                  * ::this line is used as a reference nothing prints here
    echo.           type the file path ( it can NOT contain spaces ) 
    echo.
    echo.
    echo.
    echo.
    echo.
    echo.
    echo.
    echo.
    echo.
    echo. =======================================================================
    echo.
    echo. to cancell leave blank and just press enter
    set PULL-FILE= ""
    echo.&set /P PULL-FILE= :
    if /i %PULL-FILE% == "" ( GOTO:MENU_LOOP )
    "%GOOGLE_TOOL%adb" pull %PULL-FILE% "%DEVICE_PULLED%" &set PULL-FILE= ""
    pause
GOTO:MENU_LOOP

:ADB_SHELL
    call "%BATCH_TOOL%adbshell.bat"
GOTO:MENU_LOOP

:ADB_RESTART:
    call "%ADB_RESTART%"
GOTO:MENU_LOOP

:CONNECTION_FAILED
    echo.
    echo.       ERROR ! ! ! ERROR PLEASE CHECK YOUR DEVICE ERROR ! ! ! ERROR
    echo.&set /p = FAILED TO COMUNICATE WITH DEVICE [ %status% ] HIT ANY KEY TO RETURN: 
GOTO:MENU_LOOP