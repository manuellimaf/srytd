cd nssm-2.24\win64
nssm install R318 %~dp0\start.bat AppDirectory %~dp0 DisplayName R318 Start SERVICE_AUTO_START 
ObjectName LocalSystem Type SERVICE_WIN32_OWN_PROCESS AppStopMethodSkip 0 AppStopMethodConsole 
1500 AppStopMethodWindows 1500 AppStopMethodThreads 1500 AppThrottle 1500 AppExit Default 
Restart AppRestartDelay 0
cd %~dp0

copy logback.xml %HOMEPATH%
copy config.xml %HOMEPATH%
