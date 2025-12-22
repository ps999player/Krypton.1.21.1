@echo off
:: ============================================
:: Gradle wrapper startup script for Windows
:: Auto-detects Java 17+ for Minecraft 1.21.1
:: ============================================

setlocal

:: -----------------------------
:: Detect script directory
:: -----------------------------
set DIRNAME=%~dp0
if "%DIRNAME%"=="" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%

:: -----------------------------
:: Find Java
:: -----------------------------
if defined JAVA_HOME (
    set JAVA_EXE=%JAVA_HOME%\bin\java.exe
    if not exist "%JAVA_EXE%" (
        echo ERROR: JAVA_HOME is set but java.exe not found.
        goto fail
    )
) else (
    set JAVA_EXE=java.exe
    %JAVA_EXE% -version >NUL 2>&1
    if errorlevel 1 (
        echo ERROR: JAVA_HOME not set and java not in PATH.
        goto fail
    )
)

:: -----------------------------
:: Check Java version >= 17
:: -----------------------------
for /f "tokens=2 delims==" %%v in ('"%JAVA_EXE%" -XshowSettings:properties -version 2^>^&1 ^| findstr "java.version"') do set JAVAVER=%%v
for /f "tokens=1,2,3 delims=. " %%a in ("%JAVAVER%") do set JAVAMAJOR=%%a

if %JAVAMAJOR% LSS 17 (
    echo ERROR: Java 17 or higher is required. Detected version %JAVAVER%.
    goto fail
)

:: -----------------------------
:: Set JVM options
:: -----------------------------
set DEFAULT_JVM_OPTS=

:: -----------------------------
:: Set classpath
:: -----------------------------
set CLASSPATH=%APP_HOME%\gradle\wrapper\gradle-wrapper.jar

:: -----------------------------
:: Run Gradle
:: -----------------------------
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %GRADLE_OPTS% ^
  "-Dorg.gradle.appname=%APP_BASE_NAME%" -classpath "%CLASSPATH%" org.gradle.wrapper.GradleWrapperMain %*

:end
endlocal
exit /b %ERRORLEVEL%

:fail
endlocal
exit /b 1
