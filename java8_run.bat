@echo off
cd target
java -classpath "lib\gson-2.8.9.jar;" com.melissadata.globalemail.Main com.melissadata.globalemail.view.GlobalEmailController com.melissadata.globalemail.view.RootLayoutController com.melissadata.globalemail.model.GlobalEmailOptions com.melissadata.globalemail.model.GlobalEmailTransaction
cd ..