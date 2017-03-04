
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





    








