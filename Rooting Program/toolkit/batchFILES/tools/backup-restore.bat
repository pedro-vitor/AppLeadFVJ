::You are free to tweak or modify this as you see fit,
::Just please be respectful is all I ask.

::Source: Generic Android ToolKit
::Author: Social-Design-Concepts

::called with: call "%BATCH_TOOL%backup-restore.bat"
@echo off

::set our backup file
if not defined backup_dir (
set backup_dir=%TOOL_TOP%device-backup\
)

set backupfile=%timestamp%-backup.ab

:BACKUP_RESTORE
    call "%CHECK_DEVICE%"
    cls
    title %PROGRAM_NAME% BACKUP / RESTORE
    cd "%MAIN_DIR%"
    ::print our default header
    call "%HEADER%"
    echo. BACKUP / RESTORE
::  echo. *                                  *                                  * ::this line is used as a reference nothing prints here
    echo. =======================================================================
    echo. what would you like to do:
    echo.
    echo.  B . Quick Backup Device APPS and Settings
    echo.
    echo.  R . Quick Restore Device APPS and Settings
    echo.
    echo.  U . ADB Ultimate Backup tool v1.3.2 rev. myKIT_BATCH ( ADVANCED )
    echo.
    echo.
    echo.
    echo.  0 . previous menu
    echo.
    echo. =======================================================================
    call "%PRINT_DEVICE%"
    set choice=
    echo.&set /p choice=please make a selection or type quit or exit to close the tool kit: ||GOTO:BACKUP_RESTORE

    :: the standard menu selection visible to the user
    if %choice% == B GOTO:DEVICE_BACKUP
    if %choice% == R GOTO:DEVICE_RESTORE
    if %choice% == U GOTO:adbUBT
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
GOTO:BACKUP_RESTORE

:DEVICE_BACKUP
    ::we need to make sure status is ADB-ONLINE
    call "%CHECK_DEVICE%"
    if /i not %status% == ADB-ONLINE ( GOTO:CONNECTION_FAILED )
::apps and settings backup
    call "%GOOGLE_TOOL%adb" backup -apk -shared -all -nosystem -f "%backup_dir%%timestamp%-full.nosystem.ab"
GOTO:BACKUP_RESTORE

:DEVICE_RESTORE
    ::we need to make sure status is ADB-ONLINE
    call "%CHECK_DEVICE%"
    if /i not %status% == ADB-ONLINE ( GOTO:CONNECTION_FAILED )

    if NOT exist "%backup_dir%\*.ab" (
        echo.
        color 0C
        echo Could not find any adb backups &pause &goto :eof
        pause
    )
    ::print our default header
    call "%HEADER%"
    echo. Backup Restore Selection
    echo. =======================================================================
    echo. select a backup to restore :
    set count=1
    set backupname=""
    for /f "usebackq tokens=*" %%f in (`dir/b %backup_dir%\*.ab`) do (
        echo.
        echo   !count! . %%f
    set backupname!count!=%%f
    set /A count=!count!+1
    )
    set /A count=%count%-1
    echo.
    echo.  0 . previous menu
    echo.
    echo. =======================================================================
    call "%PRINT_DEVICE%"
    set choice=
    echo.&set /p choice=please make a selection : ||GOTO:DEVICE_RESTORE

    :: we always want to use our close tool to exit the toolKIT
    :: so we remap commonly used commands for exiting
    if %choice% == e CALL "%CLOSE_TOOL%"
    if %choice% == q CALL "%CLOSE_TOOL%"
    if %choice% == exit CALL "%CLOSE_TOOL%"
    if %choice% == quit CALL "%CLOSE_TOOL%"

    if %choice% == 0 call exit/B

    if %choice% LEQ %count% goto:RUN_RESTORE

GOTO:RESTORE_RETURN

:RUN_RESTORE
    set backupname=backupname%choice%
    call set backupname=%%%backupname%%%
    call "%GOOGLE_TOOL%adb" restore "%backup_dir%%backupname%"
    pause
GOTO:BACKUP_RESTORE

:RESTORE_RETURN
    echo. invalid input please try again.
pause
GOTO:DEVICE_RESTORE

:adbUBT
    call "%BATCH_TOOL%adbUBT.bat"
GOTO:BACKUP_RESTORE

:ADB_SHELL
    call "%BATCH_TOOL%adbshell.bat"
GOTO:BACKUP_RESTORE

:ADB_RESTART:
    call "%ADB_RESTART%"
GOTO:BACKUP_RESTORE

:CONNECTION_FAILED
    echo.
    echo.       ERROR ! ! ! ERROR PLEASE CHECK YOUR DEVICE ERROR ! ! ! ERROR
    echo.&set /p = FAILED TO COMUNICATE WITH DEVICE [ %status% ] HIT ANY KEY TO RETURN: 
GOTO:BACKUP_RESTORE