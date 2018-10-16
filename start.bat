call mvn tomcat7:run
echo Exit Code = %ERRORLEVEL%
if not "%ERRORLEVEL%" == "0" exit /b