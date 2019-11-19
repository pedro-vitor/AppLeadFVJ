::You are free to tweak or modify this as you see fit,
::Just please be respectful is all I ask.

::Source: Generic Android ToolKit
::Author: Social-Design-Concepts

@echo off

@echo off
call "%CHECK_DEVICE%"
::we need to make sure status is ADB-ONLINE
if /i not %status% == ADB-ONLINE ( GOTO:CONNECTION_FAILED )

:CLEAR_POPULATE_SYSTEM_INFO
::call adb shell uname to populate kernel information
set uname-m=N/A &set uname-r=N/A &set uname-v=N/A

::call adb shell getprop to populate system information
set ro.product.manufacturer=N/A
set ro.product.model=N/A
set ro.product.name=N/A

set ro.hardware=N/A
set ro.product.board=N/A
set ro.product.device=N/A

set ro.build.display.id=N/A

set ro.build.version.release=N/A
set ro.build.version.sdk=N/A

set ro.build.description=N/A

set ro.build.date=N/A

set ro.product.cpu.abi=N/A
set ro.product.cpu.abi2=N/A

set ro.com.google.gmsversion=N/A
:GOTO:POPULATE_SYSTEM_INFO

:POPULATE_SYSTEM_INFO
::call adb shell uname to populate kernel information
    for /f "tokens=1-13" %%a in ( '"%GOOGLE_TOOL%adb" shell uname -mnrsv ^2^> nul' ) do (set uname-m=%%m &set uname-r=%%c &set uname-v=%%d %%e %%f %%g %%i %%j %%k %%l)

::call adb shell getprop to populate system information
    for /f "tokens=1-2" %%a in ( '"%GOOGLE_TOOL%adb" shell getprop ro.product.manufacturer ^2^> nul' ) do (set ro.product.manufacturer=%%a %%b)
    for /f "tokens=1-3" %%a in ( '"%GOOGLE_TOOL%adb" shell getprop ro.product.model ^2^> nul' ) do (set ro.product.model=%%a %%b %%c)
    for /f "tokens=1" %%a in ( '"%GOOGLE_TOOL%adb" shell getprop ro.product.name ^2^> nul' ) do (set ro.product.name=%%a)

    for /f "tokens=1" %%a in ( '"%GOOGLE_TOOL%adb" shell getprop ro.hardware ^2^> nul' ) do (set ro.hardware=%%a)
    for /f "tokens=1" %%a in ( '"%GOOGLE_TOOL%adb" shell getprop ro.product.board ^2^> nul' ) do (set ro.product.board=%%a)
    for /f "tokens=1" %%a in ( '"%GOOGLE_TOOL%adb" shell getprop ro.product.device ^2^> nul' ) do (set ro.product.device=%%a)

    for /f "tokens=1" %%a in ( '"%GOOGLE_TOOL%adb" shell getprop ro.build.display.id ^2^> nul' ) do (set ro.build.display.id=%%a)

    for /f "tokens=1" %%a in ( '"%GOOGLE_TOOL%adb" shell getprop ro.build.version.release ^2^> nul' ) do (set ro.build.version.release=%%a)
    for /f "tokens=1" %%a in ( '"%GOOGLE_TOOL%adb" shell getprop ro.build.version.sdk ^2^> nul' ) do (set ro.build.version.sdk=%%a)

    for /f "tokens=1-6" %%a in ( '"%GOOGLE_TOOL%adb" shell getprop ro.build.description ^2^> nul' ) do (set ro.build.description=%%a %%b %%c %%d %%e %%f)

    for /f "tokens=1-6" %%a in ( '"%GOOGLE_TOOL%adb" shell getprop ro.build.date ^2^> nul' ) do (set ro.build.date=%%a %%b %%c %%d %%e %%f)

    for /f "tokens=1" %%a in ( '"%GOOGLE_TOOL%adb" shell getprop ro.product.cpu.abi ^2^> nul' ) do (set ro.product.cpu.abi=%%a)
    for /f "tokens=1" %%a in ( '"%GOOGLE_TOOL%adb" shell getprop ro.product.cpu.abi2 ^2^> nul' ) do (set ro.product.cpu.abi2=%%a)

    for /f "tokens=1" %%a in ( '"%GOOGLE_TOOL%adb" shell getprop ro.com.google.gmsversion ^2^> nul' ) do (set ro.com.google.gmsversion=android-%%a)
GOTO:ABOUT_DEVICE

:ABOUT_DEVICE
    :: set window title specific to this section 
    title %PROGRAM_NAME% ABOUT DEVICE
    ::print our default header
    call "%HEADER%"
    echo. ABOUT DEVICE
::  echo. *                                  *                                  * ::this line is used as a reference nothing prints here
    echo. =======================================================================
    echo.
    ::call adb shell getprop to populate system information
    echo. Manufacturer: %ro.product.manufacturer%
    echo.
    echo. Model: %ro.product.model% (%ro.product.name%)
    echo.
    echo. Device: %ro.product.device%     Hardware: %ro.hardware%
    echo.
    echo. Board: %ro.product.board%
    echo.
    echo. Display ID: %ro.build.display.id%
    echo.
    echo. Android Version: %ro.build.version.release% [%ro.com.google.gmsversion%]    API: %ro.build.version.sdk%
    echo.
    echo. Build Date:
    echo. %ro.build.date%
    echo.
    echo. Build Description: 
    echo. %ro.build.description%
    echo.
    echo. ABI1: %ro.product.cpu.abi%
    echo. ABI2: %ro.product.cpu.abi2%
    echo.
    ::call adb shell uname to populate kernel information
    echo. Kernel Version:
    echo. %uname-r% %uname-v%
    echo.
    echo. Kernel Architecture: %uname-m%
    echo.
    echo. =======================================================================
    echo.&set /p =press any key to return to the previous menu: ||GOTO:EOF
:EOF

:CONNECTION_FAILED
    echo.
    echo.       ERROR ! ! ! ERROR PLEASE CHECK YOUR DEVICE ERROR ! ! ! ERROR
    echo.&set /p = FAILED TO COMUNICATE WITH DEVICE [ %status% ] HIT ANY KEY TO RETURN: 
GOTO:EOF