package demo.webapp.server;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

public class EmbeddedTomcat {

    private static final String CONTEXT_NAME = "demo-webapp";

    private Tomcat tomcat;

    public EmbeddedTomcat() {
    }

    public void start() throws ServletException, LifecycleException {
        long begin = System.currentTimeMillis();

        // set system properties - catalina.base
        String catalinaBase = getBinDirectory();
        System.setProperty("catalina.base", catalinaBase);

        tomcat = new Tomcat();

        // Tomcat needs a directory for temp files. This should be the first method called.
        // By default, if this method is not called,
        // we use: - system properties - catalina.base, catalina.home - $HOME/tomcat.$PORT
        // tomcat.setBaseDir(catalinaBase);

        // The port that we should run on can be set into an environment variable
        // Look for that variable and default to 8080 if it isn't there.
        int port = 8080;
        tomcat.setPort(port);

        // add web app
        String contextPath = "/" + CONTEXT_NAME;
        String webappDir = getWebRootPath();
        tomcat.addWebapp(contextPath, webappDir);
        System.out.println("configuring app with basedir: " + webappDir);

        tomcat.start();
        System.out.println("Tomcat Server started, use " + (System.currentTimeMillis() - begin) + " ms");
        tomcat.getServer().await();
    }

    public void stop() throws LifecycleException {
        if (tomcat != null) {
            tomcat.stop();
        }
    }

    private String getWebRootPath() {
        try {
            return new File("./src/main/webapp").getCanonicalPath();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getBinDirectory() {
        try {
            return new File("./target").getCanonicalPath();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        try {
            EmbeddedTomcat server = new EmbeddedTomcat();
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
