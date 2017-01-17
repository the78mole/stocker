# stocker

## Prerequisites

### Install Lombok in your IDE

Install lombok for your IDE by changing to a command line/console, step into the `WebContent/META-INF/lib` and executing `java -jar lombok`. If lombok does not find your IDE, select your IDE's installation directory (e.g. `~/eclipse-neon` as the folder and `eclipse.ini` as the file. Restart your IDE if running.

### Install Database Driver

If you want to use derby, simply use the Server `context.xml` and `server.xml`
provided along with this project and place the Derby libraries in Tomcat's library folder `${CATALINA_HOME}/lib`. IF you choose to use another DB, put the appropriate drivers into this library folder.