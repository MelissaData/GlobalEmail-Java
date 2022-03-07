@echo off
mkdir target
javac -classpath "lib\gson-2.8.9.jar;" -d .\target .\src\main\java\com\melissadata\globalemail\*.java .\src\main\java\com\melissadata\globalemail\model\*.java .\src\main\java\com\melissadata\globalemail\view\*.java
xcopy src\main\resources\ target\ /s /q
cd target
java -classpath "../lib\gson-2.8.9.jar;" com.melissadata.globalemail.Main com.melissadata.globalemail.view.GlobalEmailController com.melissadata.globalemail.view.RootLayoutController com.melissadata.globalemail.model.GlobalEmailOptions com.melissadata.globalemail.model.GlobalEmailTransaction
cd ..
rd .\target /s /q