::You are free to tweak or modify this as you see fit,
::Just please be respectful is all I ask.

::Source: Generic Android ToolKit
::Author: Social-Design-Concepts

::Prepare the Command Processor
::SETLOCAL ENABLEEXTENSIONS
::SETLOCAL ENABLEDELAYEDEXPANSION

title=EXTRACTING DOWNLOADED KITS

:EXTRACT_DOWNLOADED
    ::print our default header
    call "%HEADER%"
    echo. EXTRACTING DOWNLOADED KITS
    echo. =======================================================================
    echo.
    set EXTRACTED=%DOWNLOADED-KITS%\EXTRACTED

    if exist "%DOWNLOADED-KITS%\*_PATCHKIT.7z" (
        for /f "usebackq tokens=*" %%f in (`dir/b %DOWNLOADED-KITS%\*_PATCHKIT.7z`) do (
        %seven-zip% -yo%TOOL_TOP% x %DOWNLOADED-KITS%\%%f
        move %DOWNLOADED-KITS%\%%f %EXTRACTED%
        )
    )

    if exist "%DOWNLOADED-KITS%\*_ASSETKIT.7z" (
        for /f "usebackq tokens=*" %%f in (`dir/b %DOWNLOADED-KITS%\*_ASSETKIT.7z`) do (
        %seven-zip% -yo%ASSET-KIT% x %DOWNLOADED-KITS%\%%f
        move %DOWNLOADED-KITS%\%%f %EXTRACTED%
        )
    )

    if exist "%DOWNLOADED-KITS%\**_ROOTKIT.7z" (
        for /f "usebackq tokens=*" %%f in (`dir/b %DOWNLOADED-KITS%\*_ROOTKIT.7z`) do (
        %seven-zip% -yo%ROOT-KIT% x %DOWNLOADED-KITS%\%%f
        move %DOWNLOADED-KITS%\%%f %EXTRACTED%
        )
    )

    if exist "%DOWNLOADED-KITS%\*_PKGKIT.7z" (
        for /f "usebackq tokens=*" %%f in (`dir/b %DOWNLOADED-KITS%\*_PKGKIT.7z`) do (
        %seven-zip% -yo%PACKAGE-KIT% x %DOWNLOADED-KITS%\%%f
        move %DOWNLOADED-KITS%\%%f %EXTRACTED%
        )
    )
