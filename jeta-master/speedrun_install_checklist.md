
# Generic JETA Install checklist

*Basic steps:*

- Install Java and supporting tools
    - install Java JDK
    - install Maven
    - check Java and Maven work by running a sample test
- Install IntelliJ Community Edition and plugins
    - check IntelliJ works by running the sample test

---

# Windows Install checklist
 
## Install Java/Maven Pre-requisites
*  Check what you need to install by typing console commands "javac -version"
   *  If it fails install java jdk
* Check Maven "mvn -version"
   *  If it fails install maven
   
## Install JDK   
*  Download Java
    *  search for "download java sdk" (do not install the normal JRE from java.com) http://www.oracle.com/technetwork/java/javase/downloads/index.html
    *  For example, jdk1.8.0_121:  http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
*  Install Java JDK, e.g. on Windows C:\Program Files\Java\jdk1.8.0_121
*  Add the Java "\bin" folder to the path if it has not been added yet e.g. C:\Program Files\Java\jdk1.8.0_121\bin
*  create a JAVA_HOME environment variable while there for the root path e.g. JAVA_HOME=C:\Program Files\Java\jdk1.8.0_121
*  check Java JDK installed by typing javac -version in a new command line

## Install Maven
*  Download Maven http://maven.apache.org/download.html#Installation
*  Windows: Unzip maven to a directory (avoid one with spaces in it) e.g. C:\work\apache-maven-3.3.9
*  Add C:\work\apache-maven-3.3.9\bin to the PATH in System Variables
*  Check installed by typing mvn -version into a new command window

## Everything is installed, check the environment variables by displaying them on the console
*  Windows
    *  echo %JAVA_HOME%
    *  echo %PATH%

## Download JETA project 
This has a simple pom.xml and a basic test to run tagged with @first
*  Visit https://github.com/mariaklimenko/jeta/tree/master/jeta-master
*  Download the zip file and unzip somewhere
*  Run your first test from the command line
    *  Use the command line to "cd" to the project directory (where you unarchived the source code to)
    *  mvn test -Dcucumber.options="--tags @first"
**Note: By default your tests will be launched in Chrome browser. If you want to use another browser, you should specify it in cmd line paramtter "browser" (e.g -Dbrowser=firefox). **
    
If it runs successfully then you have Java and Maven Installed in Windows.


---

## Install and Configure IntelliJ
*  Install and run IntelliJ Community Edition, e.g. for Windows: https://www.jetbrains.com/idea/download/#section=windows
TBD

---
## IE11 settings
Instruction below is taken from https://github.com/SeleniumHQ/selenium/wiki/InternetExplorerDriver
* On IE 7 or higher on Windows Vista or Windows 7, you must set the Protected Mode settings for each zone to be the same value. The value can be on or off, as long as it is the same for every zone. To set the Protected Mode settings, choose "Internet Options..." from the Tools menu, and click on the Security tab. For each zone, there will be a check box at the bottom of the tab labeled "Enable Protected Mode".
* Additionally, "Enhanced Protected Mode" must be disabled for IE 10 and higher. This option is found in the Advanced tab of the Internet Options dialog.
* The browser zoom level must be set to 100% so that the native mouse events can be set to the correct coordinates.
* For IE 11 only, you will need to set a registry entry on the target computer so that the driver can maintain a connection to the instance of Internet Explorer it creates. For 32-bit Windows installations, the key you must examine in the registry editor is HKEY_LOCAL_MACHINE\SOFTWARE\Microsoft\Internet Explorer\Main\FeatureControl\FEATURE_BFCACHE. For 64-bit Windows installations, the key is HKEY_LOCAL_MACHINE\SOFTWARE\Wow6432Node\Microsoft\Internet Explorer\Main\FeatureControl\FEATURE_BFCACHE. Please note that the FEATURE_BFCACHE subkey may or may not be present, and should be created if it is not present. Important: Inside this key, create a DWORD value named iexplore.exe with the value of 0.
* Added domain to list of "Trusted Sites" for i.e. in "Internet Options"








