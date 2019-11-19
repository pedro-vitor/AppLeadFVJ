::You are free to tweak or modify this as you see fit,
::Just please be respectful is all I ask.

::Source: Generic Android ToolKit
::Author: Social-Design-Concepts

::Prepare the Command Processor
::SETLOCAL ENABLEEXTENSIONS
::SETLOCAL ENABLEDELAYEDEXPANSION

:CHECK
    ::check if package program exist 
    if NOT exist "%PACKAGE-KIT%\*_PKGKIT" (
        echo.
        color 0C
        echo Could not find any package programs &pause &goto :eof
        pause
    )
    GOTO:MENU_LOOP
GOTO:EOF

:MENU_LOOP
    call "%CHECK_DEVICE%"
    title=PACKAGE PROGRAM MENU
    ::print our default header
    call "%HEADER%"
    echo. Package Program Selection
    echo. =======================================================================
    echo. what would you like to do:
    set count=1
    for /f "usebackq tokens=*" %%f in (`dir/b %PACKAGE-KIT%\*_PKGKIT`) do (
        echo.
        echo   !count! . %%f
    set KIT!count!=""
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
    set runKIT=""
    set SELECTEDPGRM=""
    set runKIT=KIT%choice%
    call set SELECTEDPGRM=%%%runKIT%%%
    call "%PACKAGE-KIT%\%SELECTEDPGRM%\launcher.bat"
GOTO:MENU_LOOP

:RETURN
    echo. invalid input please try again.
pause
GOTO:MENU_LOOP