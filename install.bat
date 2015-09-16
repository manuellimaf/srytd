nssm install R318 

IF EXIST %~dp0conf GOTO ALREADY_INSTALLED
mkdir %~dp0conf
copy logback.xml %~dp0conf
copy config.xml %~dp0conf
:ALREADY_INSTALLED
del logback.xml
del config.xml
