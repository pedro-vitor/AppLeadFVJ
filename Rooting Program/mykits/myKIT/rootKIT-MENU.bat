::You are free to tweak or modify this as you see fit,
::Just please be respectful is all I ask.

::Source: Generic Android ToolKit
::Author: Social-Design-Concepts

::Prepare the Command Processor
::SETLOCAL ENABLEEXTENSIONS
::SETLOCAL ENABLEDELAYEDEXPANSION

:CHECK
    ::check if rootKIT exist 
    if NOT exist "%ROOT-KIT%\*_ROOTKIT" (
        echo.
        color 0C
        echo Could not find any rootKITs &pause &goto :eof
        pause
    )
    GOTO:MENU_LOOP
GOTO:EOF

:MENU_LOOP
    call "%CHECK_DEVICE%"
    title=ROOT KIT MENU
    ::print our default header
    call "%HEADER%"
    echo. rootKIT Selection
    echo. =======================================================================
    echo. what would you like to do:
    set count=1
    for /f "usebackq tokens=*" %%f in (`dir/b %ROOT-KIT%\*_ROOTKIT`) do (
        echo.
        echo   !count! . %%f
    set KIT!count!=%%f
    set /A count=!count!+1
    )
    set /A count=%count%-1
    echo.
    echo.  0 . previous menu
    echo.
    echo. =======================================================================
    call "%PRINT_DEVICE%"
    set choice=
    echo.&set /p choice=please make a selection or type quit or exit to close the tool kit: ||GOTO:MENU_LOOP

    :: we always want to use our close tool to exit the toolKIT
    :: so we remap commonly used commands for exiting
    if %choice% == e CALL "%CLOSE_TOOL%"
    if %choice% == q CALL "%CLOSE_TOOL%"
    if %choice% == exit CALL "%CLOSE_TOOL%"
    if %choice% == quit CALL "%CLOSE_TOOL%"

    if %choice% == 0 call exit/B

    if %choice% LEQ %count% goto:RUN

GOTO:RETURN

:RUN
    set runKIT=KIT%choice%
    call set launchKIT=%%%runKIT%%%\launcher.bat
    call "%ROOT-KIT%\%launchKIT%"
GOTO:MENU_LOOP

:RETURN
    echo. invalid input please try again.
pause
GOTO:MENU_LOOP