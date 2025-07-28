@echo off
setlocal

REM Set paths
set SRC=src
set LIB=lib\mysql-connector-j-9.4.0.jar
set BIN=bin

REM Change to project directory
cd c:\Users\abhig\Documents\InsuranceJDBCProject\Insuracnce_policy_management

REM Create bin folder if it doesn't exist
if not exist %BIN% (
    mkdir %BIN%
)

REM Compile all Java files together
echo Compiling...
dir /s /b %SRC%\*.java > sources.txt
javac -cp "%LIB%" -d %BIN% @sources.txt
del sources.txt

REM Check if compilation succeeded
if errorlevel 1 (
    echo ❌ Compilation failed.
    pause
    exit /b
)

REM Run the Main class
echo Running Main...
java -cp "%LIB%;%BIN%" Main

endlocal
pause
