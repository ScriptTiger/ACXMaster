@echo off
setlocal ENABLEDELAYEDEXPANSION

rem BUILD REQUIREMENTS
rem Go must be installed and in your path, as it is used to build the app image binary launchers
rem The JDK jmods directory needs to include windows, linux, and mac subdirectories containing the jmods for those respective operating systems
rem The following environmental variables need to be set either system-wide, for your user, or you can set them here
rem set JAVA_HOME=C:\path-to-JDK
rem set PATH=%JAVA_HOME%\bin;%PATH%

echo Set build information...
set PROJECT=ACX Master
set PACKAGE=acxmaster
set ENTRY=Main
set DEPS=java.desktop

echo Clean up old release files...
if exist Release rd /s /q Release

echo Set Java version to build...
set JV=8

echo Compile package source files...
javac.exe -d Release\%JV% src\%PACKAGE%\*.java --release %JV%

echo Archive package to jar file...
cd Release\%JV%
jar.exe cfe "%PROJECT%.jar" %PACKAGE%.%ENTRY% %PACKAGE%\*.class
cd ..\..

echo Set Java version to build...
set JV=21

echo Compile module source files...
javac.exe --add-modules %DEPS% -d Release\%JV% src\%PACKAGE%\*.java --release %JV%

echo Archive module to jar file...
cd Release\%JV%
jar.exe cfe "%PROJECT%.jar" %PACKAGE%.%ENTRY% %PACKAGE%\*.class
cd ..\..

echo Compile module-info source file...
javac.exe -p "Release\%JV%\%PROJECT%.jar" --add-modules %DEPS%,%PACKAGE% -d Release\%JV% src\module-info.java

echo Update jar file with compiled module-info...
cd  Release\%JV%
jar.exe uf "%PROJECT%.jar" module-info.class
cd ..\..

echo Build app image for Windows...
jlink.exe -p "Release\%JV%\%PROJECT%.jar;%JAVA_HOME%\jmods\windows" --add-modules %DEPS%,%PACKAGE% --compress=zip-9 --no-header-files --no-man-pages --strip-debug --output "Release\%JV%\%PROJECT%-Windows"
(
	copy README.md "Release\%JV%\%PROJECT%-Windows"
	copy LICENSE "Release\%JV%\%PROJECT%-Windows"
	copy bin\* "Release\%JV%\%PROJECT%-Windows\bin"
	xcopy docs\* /s "Release\%JV%\%PROJECT%-Windows\legal"
) > nul

echo Build launcher for Windows...
set GOOS=windows
go build -ldflags="-s -w -H=windowsgui" -o "Release\%JV%\%PROJECT%-Windows\%PROJECT%.exe" src\%PACKAGE%.go src\include_windows.go

echo Build app image for Linux...
jlink.exe -p "Release\%JV%\%PROJECT%.jar;%JAVA_HOME%\jmods\linux" --add-modules %DEPS%,%PACKAGE% --compress=zip-9 --no-header-files --no-man-pages --strip-debug --output "Release\%JV%\%PROJECT%-Linux"
(
	copy README.md "Release\%JV%\%PROJECT%-Linux"
	copy LICENSE "Release\%JV%\%PROJECT%-Linux"
	copy bin\std.rnnn "Release\%JV%\%PROJECT%-Linux\bin"
	xcopy docs\rnnoise /s "Release\%JV%\%PROJECT%-Linux\legal\rennoise\"
) > nul

echo Build launcher for Linux...
set GOOS=linux
go build -ldflags="-s -w" -o "Release\%JV%\%PROJECT%-Linux\%PROJECT%" src\%PACKAGE%.go src\include_other.go

echo Build app image for Mac...
jlink.exe -p "Release\%JV%\%PROJECT%.jar;%JAVA_HOME%\jmods\mac" --add-modules %DEPS%,%PACKAGE% --compress=zip-9 --no-header-files --no-man-pages --strip-debug --output "Release\%JV%\%PROJECT%-Mac"
(
	copy README.md "Release\%JV%\%PROJECT%-Mac"
	copy LICENSE "Release\%JV%\%PROJECT%-Mac"
	copy bin\std.rnnn "Release\%JV%\%PROJECT%-Mac\bin"
	xcopy docs\rnnoise /s "Release\%JV%\%PROJECT%-Mac\legal\rennoise\"
) > nul

echo Build launcher for Mac...
set GOOS=darwin
go build -ldflags="-s -w" -o "Release\%JV%\%PROJECT%-Mac\%PROJECT%.app" src\%PACKAGE%.go src\include_other.go

echo Build complete
pause