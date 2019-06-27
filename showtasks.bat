call runcrud.bat
if "%ERRORLEVEL%" == "0" goto runchrome
echo.
echo There is a problem
goto fail

:runchrome
start "Chrome" "C:\Program Files (x86)\Google\Chrome\Application"
start "webpage name" "http://localhost:8080/crud/v1/task/getTasks"

:fail
echo There is a problem