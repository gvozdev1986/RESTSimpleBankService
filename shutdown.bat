call mvn tomcat7:shutdown
echo Exit Code = %ERRORLEVEL%
if not "%ERRORLEVEL%" == "0" exit /b