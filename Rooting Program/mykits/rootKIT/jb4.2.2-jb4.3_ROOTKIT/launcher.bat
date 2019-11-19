:ROOT_MENU
    cls
    :: set window title specific to this section
    title %PROGRAM_NAME%
    set nxt=0
    set ric=0
    ::print our default header
    call "%HEADER%"
    echo. JB ROOT TOOL Dell Venue 7 / 8 - Acer Iconia Tab A1-830
    echo. =======================================================================
    echo. what would you like to do:
    echo.
::  echo. *                                  *                                  * ::this line is used as a reference nothing prints here
    echo. R1 . root with koushSU Installation
    echo.
    echo. R2 . root with superSU Installation
    echo. 
    echo. R3 . unroot device
    echo.
    echo.
    echo.  0 . to return to the main menu
    echo.
    echo. =======================================================================
    call "%PRINT_DEVICE%"
    set choice=
    echo.&set /p choice=please make a selection or type quit or exit to close the tool kit: ||GOTO:ROOT_MENU
    set su=""
    :: the only accepted answer to continue 
    if %choice% == R1 set su=%ASSET-KIT%\koushSU &goto :ROOT_ACCEPT
    if %choice% == R2 set su=%ASSET-KIT%\superSU &goto :ROOT_ACCEPT
    if %choice% == R3 set su=%ASSET-KIT%\unroot &goto :ROOT_ACCEPT
    if %choice% == 0 call exit/B

    ::for debugging
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
GOTO:ROOT_MENU

:ROOT_ACCEPT
    cls
    :: set window title specific to this section 
    title %PROGRAM_NAME% disclaimer
    ::print our default header
    echo. READ ME:
::  echo. *                                  *                                  * ::this line is used as a centering reference nothing prints here
    echo. =======================================================================
    echo. standard disclaimer:
    echo.
    echo.              WITH GREAT POWER COMES GREAT RESPONSIBILITY.
    echo.
    echo.       by proceeding with the rooting your device you accept that
    echo.                  it is carried out at your own risk
    echo.             and you will not hold anyone else responsible
    echo.
    echo.              WITH GREAT POWER COMES GREAT RESPONSIBILITY.
    echo.
    echo.
    echo. . type " ACCEPT " without quotes to reboot and root device
    echo.
    echo. . type " 0 " to return to the main menu
    echo.
    echo. . type quit or exit to cancell and close the toolKIT
    echo.
    echo. =======================================================================
    set choice=
    echo.&set /p choice=: ||goto :ROOT_ACCEPT

    :: the only accepted answer to continue 
    if %choice% == ACCEPT goto :REBOOT4ROOT
    if %choice% == 0 call exit/B
 
    :: we always want to use our close tool to exit the toolKIT
    :: so we remap commonly used commands for exiting
    if %choice% == e CALL "%CLOSE_TOOL%"
    if %choice% == q CALL "%CLOSE_TOOL%"
    if %choice% == exit CALL "%CLOSE_TOOL%"
    if %choice% == quit CALL "%CLOSE_TOOL%"
GOTO:ROOT_ACCEPT

:REBOOT4ROOT
    call "%CHECK_DEVICE%"
    cls
    :: set window title specific to this section 
    title %PROGRAM_NAME% REBOOT4ROOT
    ::print our default header
    call "%HEADER%"
    echo. REBOOT4ROOT
    echo. =======================================================================
    echo.
    echo. DEVICE STATUS: %status%
    echo.
    echo. DEVICE INFORMATION: %deviceinfo%
    echo.
    echo. =======================================================================
    echo.
    if /i %status% == UNKNOWN ( GOTO:CONNECTION_FAILED)
    if /i %status% == ADB-ONLINE ( GOTO:REBOOT2FASTBOOT)
    if /i %status% == FASTBOOT-ONLINE ( GOTO:ROOT_TOOL)
GOTO:ROOT_MENU

:REBOOT2FASTBOOT
    "%GOOGLE_TOOL%adb" reboot-bootloader &"%GOOGLE_TOOL%fastboot" getvar all &GOTO:ROOT_TOOL
GOTO:ROOT_MENU

:ROOT_TOOL
cls
    call "%CHECK_DEVICE%"
    :: set window title specific to this section 
    title %PROGRAM_NAME% ROOT_TOOL 
    ::print our default header
    call "%HEADER%"
    echo. replace adbd binanary with rooted one
    echo. =======================================================================
    echo.
    echo. DEVICE STATUS: %status%
    echo.
    echo. DEVICE INFORMATION: %deviceinfo%
    echo.
    echo. =======================================================================
    echo.
    echo intels droidboot has a funny feature it allows you to use the
    echo fastboot flash command simular to the way you use adb push as long as
    echo the file location is mounted rw
    echo luckly for us ours is so we push the adbd binary from the dev-edition:
    echo.
    echo fastboot flash /sbin/adbd rooted-adbd-binary
    echo. 
    echo sending rooted adbd binary from ( Dell Venue 8 Dev-Edition boot.img ) to the device:
    "%GOOGLE_TOOL%fastboot" flash /sbin/adbd %ASSET-KIT%\adbd
    echo.
    echo.
    echo the next part is key our device has an oem command that allows us to
    echo stop the droidboot service and start adbd
    echo.
    echo fastboot oem startftm
    echo. 
    echo error msg is normal here this is happens when the 
    echo droidboot service is closed and the adbd service is started. . .
    echo.
    echo issue fastboot oem startftm command to start adb service:
    "%GOOGLE_TOOL%fastboot" oem startftm
    echo.
    ping -n 5 127.0.0.1 >nul
    cls
    "%GOOGLE_TOOL%adb" wait-for-device
    cls
    call "%CHECK_DEVICE%"
    :: set window title specific to this section 
    title %PROGRAM_NAME% ROOT_TOOL 
    ::print our default header
    call "%HEADER%"
    echo. ROOTING YOUR DEVICE
    echo. =======================================================================
    echo.
    echo. DEVICE STATUS: %status%
    echo.
    echo. DEVICE INFORMATION: %deviceinfo%
    echo.
    echo. =======================================================================
    echo.
    echo push our rooting assets to our device /tmp:
    "%GOOGLE_TOOL%adb" push %ASSET-KIT%\check-mount /tmp
    "%GOOGLE_TOOL%adb" push %su% /tmp
    echo.
    echo make executable - run su installation:
    "%GOOGLE_TOOL%adb" shell chmod 6755 /tmp/check-mount
    "%GOOGLE_TOOL%adb" shell chmod 6755 /tmp/install-su
    "%GOOGLE_TOOL%adb" shell "cd /tmp && ./check-mount"
    "%GOOGLE_TOOL%adb" shell "cd /tmp && ./install-su"
    ping -n 3 127.0.0.1 >nul
    echo.
    echo complete
    "%GOOGLE_TOOL%adb" reboot
    pause
    call exit/B
GOTO:ROOT_MENU

:CMD_LINE:
    cls
    call "%HEADER%"
    echo.
::  echo. *                                  *                                  * ::this line is used as a reference nothing prints here
    echo.     cli for debugging the toolKIT type " exit " to return to toolKIT
    echo.
    cmd
GOTO:ROOT_MENU

:CONNECTION_FAILED
    echo.
    echo.       ERROR ! ! ! ERROR PLEASE CHECK YOUR DEVICE ERROR ! ! ! ERROR
    echo.&set /p = FAILED TO COMUNICATE WITH DEVICE [ %status% ] HIT ANY KEY TO RETURN: 
GOTO:ROOT_MENU