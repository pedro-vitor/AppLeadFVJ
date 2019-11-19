::You are free to tweak or modify this as you see fit,
::Just please be respectful is all I ask.

::Source: Generic Android ToolKit
::Author: Social-Design-Concepts

@echo off
    ::set myKIT specific name
    set PROGRAM_NAME=myKIT_BATCH v1.1.2d

    ::set the default color for myKIT
    set default-color=color 0A

    set myKIT_TOP=%~dp0
    ::use short name for TOOL_TOP
    for %%f in ("%myKIT_TOP%") do set myKIT_TOP=%%~sf

    set DOWNLOADED-KITS=%TOOL_TOP%mykit-downloads

    set ASSET-KIT=%TOOL_TOP%mykits\assetKIT

    set ROOT-KIT=%TOOL_TOP%mykits\rootKIT

    set PACKAGE-KIT=%TOOL_TOP%mykits\pkgKIT
