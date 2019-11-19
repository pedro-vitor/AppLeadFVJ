::You are free to tweak or modify this as you see fit,
::Just please be respectful is all I ask.

::Source: Generic Android ToolKit
::Author: Social-Design-Concepts

:: defaults used through out the toolKIT

    ::set default parameters specific to whole toolKIT

echo off

    set default-color=color 0B

    set timestamp=%date:~-10,2%-%date:~-7,2%-%date:~-4,4%-%time:~-11,2%.%time:~-8,2%.%time:~-5,2%

::  when setting the program name its best to use this templete so that it always displays center in the header
::  echo. *                  Generic Android ToolKit ver.0.0.6                  *
::  echo. *                                  *                                  * ::this line is used as a reference nothing prints here

    set PROGRAM_NAME=Yet Another Generic Android ToolKit

    ::set the directories for the basic toolKIT
    set TOOL_KIT=%TOOL_TOP%toolkit\

    set DEVICE_PULLED=%TOOL_TOP%device-pulled

    set GOOGLE_TOOL=%TOOL_TOP%toolkit\google\

    set BATCH_MENU=%TOOL_TOP%toolkit\batchFILES\menus\

    set BATCH_TOOL=%TOOL_TOP%toolkit\batchFILES\tools\

    ::set misc stuff for the basic toolKIT
    set HEADER=%TOOL_KIT%batchFILES\header.bat
    set PRINT_DEVICE=%BATCH_TOOL%print-device.bat
    set CHECK_DEVICE=%BATCH_TOOL%check-device.bat
    set ADB_RESTART=%BATCH_TOOL%adbrestart.bat

    ::set starting device status.
    set status=UNKNOWN

    ::we use a built tool to ensure the toolKIT closes correctly
    set CLOSE_TOOL=%TOOL_KIT%batchFILES\close.bat

    ::busybox to use some basic tools
    set busybox=%TOOL_TOP%toolkit\other\busybox.exe

    ::busybox to use some basic tools
    set seven-zip=%TOOL_TOP%toolkit\other\7za.exe

    ::check if custom toolKIT provides adb drivers if not use the built in aosp during driver installation
    if exist "%TOOL_TOP%mykits\driverKIT\ADB\included" (
        set DRIVER=%TOOL_TOP%mykits\driverKIT\
    ) else (
        set DRIVER=%TOOL_TOP%toolkit\drivers\
    )

    ::check if myKIT device specific tool kit exist if not use the built mainMENU
    if exist "%TOOL_TOP%mykits\myKIT\myKIT-MENU.bat" (
        set mainMENU=%TOOL_TOP%mykits\myKIT\myKIT-MENU.bat
    ) else (
        set mainMENU=%TOOL_TOP%toolkit\batchFILES\menus\main-menu.bat
    )