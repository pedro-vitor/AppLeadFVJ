::You are free to tweak or modify this as you see fit,
::Just please be respectful is all I ask.

::Source: Ultimate Backup tool v1.3.2
::Author: by Gigadroid

::Modified for myKIT_BATCH by Social-Design-Concepts

::called with: call "%BATCH_TOOL%adbUBT.bat"

@echo off

::set our backup file
if not defined backup_dir (
set backup_dir=%TOOL_TOP%backup\
)

set backupfile=%timestamp%-backup.ab

cls
:adbUBT_MENU
    if exist "msg.vbs" (
        del "msg.vbs"
    )

    if exist "search.find" (
        del "search.find"
    )

    call "%CHECK_DEVICE%"
    cls
    title Ultimate Backup Tool v1.3.2 rev. myKIT_BATCH
    echo. ***********************************************************************
    echo. *             Ultimate Backup tool v1.3.2 rev. myKIT_BATCH            *
    echo. *           Original Author:  Gigadroid  xda-developers.com           *
    echo. *                                                                     *
    echo. *                    modified for myKIT_BATCH s.d.c                   *
    echo. ***********************************************************************
    echo.
    echo. Make sure that USB Debugging is enabled!
    echo. Make sure that you have correctly installed drivers!
    echo.
    echo. The backup will be saved in:
    echo. %backup_dir%
    echo.
    echo. Notices:
    echo. I'M NOT RESPONSIBLE IF YOU DAMAGE YOUR DEVICE!
    echo. The backup function will not back up SMS messages!
    echo. =======================================================================
    echo. what would you like to do:
    echo.
    echo.  1 . Backup all without system apps
    echo.  2 . Backup all with system apps (unsafe)
    echo.  3 . Backup app and device data (not the APKs themselves)
    echo.  4 . Backup apps
    echo.  5 . Backup device shared storage / SD card contents
    echo.  6 . Backup a single app data (not the APK)
    echo.  7 . Restore
    echo.
    echo.  0 . previous menu
    echo.
    echo. =======================================================================
    call "%PRINT_DEVICE%"
    set choice=
    echo.&set /p choice=please make a selection or type quit or exit to close the tool kit: ||GOTO:adbUBT_MENU

    :: the standard menu selection visible to the user
    if %choice% == 1 goto all
    if %choice% == 2 goto system
    if %choice% == 3 goto appsdevice
    if %choice% == 4 goto apps
    if %choice% == 5 goto sd
    if %choice% == 6 goto single
    if %choice% == 7 goto restore
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
GOTO:adbUBT_MENU

:all
    ::we need to make sure status is ADB-ONLINE
    call "%CHECK_DEVICE%"
    if /i not %status% == ADB-ONLINE ( GOTO:CONNECTION_FAILED )
    cls
    echo Wait until you see a message saying 'Backup complete' or your phone returns to the home screen.
    call "%GOOGLE_TOOL%adb" backup -apk -shared -all -nosystem -f "%backup_dir%%timestamp%-full.nosystem.ab" > search.find 2>&1
    findstr "adb" search.find >nul
    IF %ERRORLEVEL% EQU 1 goto allcomplete
    IF %ERRORLEVEL% EQU 0 goto allfailed

:allfailed
    @echo off
    >"msg.vbs" (
    echo WScript.Quit _
    echo MsgBox("Backup failed!", _
    echo 5 Or 256 Or 16, _
    echo "Ultimate Backup Tool"^)
    )
    wscript "msg.vbs"
    IF %ERRORLEVEL% EQU 2 goto adbUBT_MENU
    IF %ERRORLEVEL% EQU 4 goto all

:allcomplete
    @echo off
    >"msg.vbs" (
    echo WScript.Quit _
    echo MsgBox("Backup complete!", _
    echo 0, _
    echo "Ultimate Backup Tool"^)
    )
    wscript "msg.vbs"
GOTO:adbUBT_MENU

:system
    ::we need to make sure status is ADB-ONLINE
    call "%CHECK_DEVICE%"
    if /i not %status% == ADB-ONLINE ( GOTO:CONNECTION_FAILED )
    cls
    echo Wait until you see a message saying 'Backup complete' or your phone returns to the home screen.
    call "%GOOGLE_TOOL%adb" backup -apk -shared -all -system -f "%backup_dir%%timestamp%-full.system.ab" > search.find 2>&1
    findstr "adb" search.find >nul
    IF %ERRORLEVEL% EQU 1 goto systemcomplete
    IF %ERRORLEVEL% EQU 0 goto systemfailed


:systemfailed
    @echo off
    >"msg.vbs" (
    echo WScript.Quit _
    echo MsgBox("Backup failed!", _
    echo 5 Or 256 Or 16, _
    echo "Ultimate Backup Tool"^)
    )
    wscript "msg.vbs"
    IF %ERRORLEVEL% EQU 2 goto adbUBT_MENU
    IF %ERRORLEVEL% EQU 4 goto system

:systemcomplete
    @echo off
    >"msg.vbs" (
    echo WScript.Quit _
    echo MsgBox("Backup complete!", _
    echo 0, _
    echo "Ultimate Backup Tool"^)
    )
    wscript "msg.vbs"
GOTO:adbUBT_MENU

:appsdevice
    ::we need to make sure status is ADB-ONLINE
    call "%CHECK_DEVICE%"
    if /i not %status% == ADB-ONLINE ( GOTO:CONNECTION_FAILED )
    cls
    echo Wait until you see a message saying 'Backup complete' or your phone returns to the home screen.
    call "%GOOGLE_TOOL%adb" backup -all -noapk -f "%backup_dir%%timestamp%-app-device.data.ab" > search.find 2>&1
    findstr "adb" search.find >nul
    IF %ERRORLEVEL% EQU 1 goto appsdevicecomplete
    IF %ERRORLEVEL% EQU 0 goto appsdevicefailed

:appsdevicefailed
    @echo off
    >"msg.vbs" (
    echo WScript.Quit _
    echo MsgBox("Backup failed!", _
    echo 5 Or 256 Or 16, _
    echo "Ultimate Backup Tool"^)
    )
    wscript "msg.vbs"
    IF %ERRORLEVEL% EQU 2 goto adbUBT_MENU
    IF %ERRORLEVEL% EQU 4 goto appsdevice

:appsdevicecomplete
    @echo off
    >"msg.vbs" (
    echo WScript.Quit _
    echo MsgBox("Backup complete!", _
    echo 0, _
    echo "Ultimate Backup Tool"^)
    )
    wscript "msg.vbs"
GOTO:adbUBT_MENU

:apps
    ::we need to make sure status is ADB-ONLINE
    call "%CHECK_DEVICE%"
    if /i not %status% == ADB-ONLINE ( GOTO:CONNECTION_FAILED )
    cls
    echo Wait until you see a message saying 'Backup complete' or your phone returns to the home screen.
    call "%GOOGLE_TOOL%adb" backup -all -apk -noshared -nosystem -f "%backup_dir%%timestamp%-apps.nosystem.ab" > search.find 2>&1
    findstr "adb" search.find >nul
    IF %ERRORLEVEL% EQU 1 goto appscomplete
    IF %ERRORLEVEL% EQU 0 goto appsfailed

:appsfailed
    @echo off
    >"msg.vbs" (
    echo WScript.Quit _
    echo MsgBox("Backup failed!", _
    echo 5 Or 256 Or 16, _
    echo "Ultimate Backup Tool"^)
    )
    wscript "msg.vbs"
    IF %ERRORLEVEL% EQU 2 goto adbUBT_MENU
    IF %ERRORLEVEL% EQU 4 goto apps

:appscomplete
    @echo off
    >"msg.vbs" (
    echo WScript.Quit _
    echo MsgBox("Backup complete!", _
    echo 0, _
    echo "Ultimate Backup Tool"^)
    )
    wscript "msg.vbs"
GOTO:adbUBT_MENU

:sd
    ::we need to make sure status is ADB-ONLINE
    call "%CHECK_DEVICE%"
    if /i not %status% == ADB-ONLINE ( GOTO:CONNECTION_FAILED )
    cls
    echo Wait until you see a message saying 'Backup complete' or your phone returns to the home screen.
    call "%GOOGLE_TOOL%adb" backup -noapk -shared -nosystem -f "%backup_dir%%timestamp%-internal.sdcard.ab" > search.find 2>&1
    findstr "adb" search.find >nul
    IF %ERRORLEVEL% EQU 1 goto sdcomplete
    IF %ERRORLEVEL% EQU 0 goto sdfailed

:sdfailed
    @echo off
    >"msg.vbs" (
    echo WScript.Quit _
    echo MsgBox("Backup failed!", _
    echo 5 Or 256 Or 16, _
    echo "Ultimate Backup Tool"^)
    )
    wscript "msg.vbs"
    IF %ERRORLEVEL% EQU 2 goto adbUBT_MENU
    IF %ERRORLEVEL% EQU 4 goto sd

:sdcomplete
    @echo off
    >"msg.vbs" (
    echo WScript.Quit _
    echo MsgBox("Backup complete!", _
    echo 0, _
    echo "Ultimate Backup Tool"^)
    )
    wscript "msg.vbs"
GOTO:adbUBT_MENU

:single 
    ::we need to make sure status is ADB-ONLINE
    call "%CHECK_DEVICE%"
    if /i not %status% == ADB-ONLINE ( GOTO:CONNECTION_FAILED )
    call "%BATCH_TOOL%singleappbackup.bat"
    echo.
    set App=""
    echo.Find the package name of the app you want to backup and type it below
    set /P App=:
    if /i %App% == "" ( set App="" &GOTO:singlefailed )
    echo Wait until you see a message saying 'Backup complete' or your phone returns to the home screen.
    call "%GOOGLE_TOOL%adb" backup "%App%" -apk -f "%backup_dir%%timestamp%-%App%.ab" > search.find 2>&1
    findstr "adb" search.find >nul
    IF %ERRORLEVEL% EQU 1 goto singlecomplete
    IF %ERRORLEVEL% EQU 0 goto singlefailed

:singlefailed
    @echo off
    >"msg.vbs" (
    echo WScript.Quit _
    echo MsgBox("Backup failed!", _
    echo 5 Or 256 Or 16, _
    echo "Ultimate Backup Tool"^)
    )
    wscript "msg.vbs"
    IF %ERRORLEVEL% EQU 2 goto adbUBT_MENU
    IF %ERRORLEVEL% EQU 4 goto single

:singlecomplete
    @echo off
    >"msg.vbs" (
    echo WScript.Quit _
    echo MsgBox("Backup complete!", _
    echo 0, _
    echo "Ultimate Backup Tool"^)
    )
    wscript "msg.vbs"
GOTO:adbUBT_MENU

:restore
    ::we need to make sure status is ADB-ONLINE
    call "%CHECK_DEVICE%"
    if /i not %status% == ADB-ONLINE ( GOTO:CONNECTION_FAILED )

    if NOT exist "%backup_dir%\*.ab" (
        echo.
        color 0C
        echo Could not find any adb backups &pause &goto :eof
        pause
    )
    cls
    title Ultimate Backup Tool v1.3.2 rev. myKIT_BATCH
    echo. ***********************************************************************
    echo. *             Ultimate Backup tool v1.3.2 rev. myKIT_BATCH            *
    echo. *           Original Author:  Gigadroid  xda-developers.com           *
    echo. *                                                                     *
    echo. *                    modified for myKIT_BATCH s.d.c                   *
    echo. ***********************************************************************
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
    echo.&set /p choice=please make a selection : ||GOTO:restore

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
    call "%GOOGLE_TOOL%adb" restore "%backup_dir%%backupname%" > search.find 2>&1
    echo Now unlock your device and confirm the restore operation.
    pause
    findstr "adb" search.find >nul
    IF %ERRORLEVEL% EQU 1 goto restorecomplete
    IF %ERRORLEVEL% EQU 0 goto restorefailed
GOTO:adbUBT_MENU

:RESTORE_RETURN
    echo. invalid input please try again.
pause
GOTO:restore

:restorefailed
    @echo off
    >"msg.vbs" (
    echo WScript.Quit _
    echo MsgBox("Restore failed!", _
    echo 5 Or 256 Or 16, _
    echo "Ultimate Backup Tool"^)
    )
    wscript "msg.vbs"
    IF %ERRORLEVEL% EQU 2 goto adbUBT_MENU
    IF %ERRORLEVEL% EQU 4 goto restore

:restorecomplete
    @echo off
    >"msg.vbs" (
    echo WScript.Quit _
    echo MsgBox("Restore complete!", _
    echo 0, _
    echo "Ultimate Backup Tool"^)
    )
    wscript "msg.vbs"
GOTO:adbUBT_MENU

:ADB_SHELL
    call "%BATCH_TOOL%adbshell.bat"
GOTO:adbUBT_MENU

:ADB_RESTART:
    call "%ADB_RESTART%"
GOTO:adbUBT_MENU

:CONNECTION_FAILED
    @echo off
    >"msg.vbs" (
    echo WScript.Quit _
    echo MsgBox("! ! ! ERROR PLEASE CHECK YOUR DEVICE ERROR ! ! ! [ %status% ]", _
    echo 16, _
    echo "Ultimate Backup Tool rev. myKIT_BATCH"^)
    )
    wscript "msg.vbs"
GOTO:adbUBT_MENU