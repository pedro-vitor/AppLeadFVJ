::Batch file created by Vashypooh
::KF Utility Driver Installer V.3
::You are free to tweak or modify this as you see fit,
::Just please be respectful is all I ask.

::Example from Microsoft's website modified to install drivers using DPINST from WDK.

@echo off
color 0B
title "%PROGRAM_NAME%" adb driver installation

:notifaction_one
    @echo on
    ::print our default header
    call "%HEADER%"
    echo. READ ME:
::  echo. *                                  *                                  * ::this line is used as a reference nothing prints here
    echo. =======================================================================
    echo. driver installation:
    echo.
::  echo. *                                  *                                  * ::this line is used as a reference nothing prints here
    echo.       you are about to start the adb driver installation
    echo.
    echo.       please disconnect your device from your computer
    echo.
    echo.       after the driver installation wizard is completed
    echo.
    echo.       please return this window for further information
    echo.
    echo. note:
    echo. if you ar using Windows 8 or 8.1 please see instrutions on how to:
    echo. enable installation of unsigned drivers then restart the toolKIT
    echo.
    echo. =======================================================================
    echo.
    pause

::Determine what OS we are running.
Set RegQry=HKLM\Hardware\Description\System\CentralProcessor\0
REG.exe Query %RegQry% > checkOS.txt
Find /i "x86" < CheckOS.txt > StringCheck.txt
 
If %ERRORLEVEL% == 0 (
	::We are 32bit, lets run 32bit installer.
    call "%DRIVER%ADB\dpinst32.exe"
) ELSE (
	::We are 64bit, lets run 32bit installer.
    call "%DRIVER%ADB\dpinst64.exe"
)

del CheckOS.txt
del StringCheck.txt
:notifaction_two
    ::print our default header
    call "%HEADER%"
    echo. READ ME:
::  echo. *                                  *                                  * ::this line is used as a reference nothing prints here
    echo. =======================================================================
    echo. enable developer options:
    echo.
::  echo. *                                  *                                  * ::this line is used as a reference nothing prints here
    echo. if you have not allready done so please enable Developer options:
    echo.
    echo. goto: Settings / About /
    echo.           then click Build number 7 time rapidly to enable:
    echo.                 Developer options Menu under Settings
    echo.
    echo. next: goto Settings / Developer options /
    echo.           and click the checkbox next to USB debugging
    echo.
    echo. finally: plug you device into your computer 
    echo.     wait for windows to finish installing the device driver then:
    echo.
    echo. =======================================================================
    echo.
    pause

::We should kill our ADB server if it is running just to make sure it catches our new device.
"%GOOGLE_TOOL%adb" kill-server