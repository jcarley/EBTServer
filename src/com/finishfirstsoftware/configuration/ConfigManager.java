package com.finishfirstsoftware.configuration;

import java.io.File;
import java.io.FileFilter;
import javax.servlet.ServletException;
import org.apache.catalina.Host;
import org.apache.catalina.core.JasperListener;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.core.StandardServer;
import org.apache.catalina.startup.Tomcat;

/**
 *
 * @author Jefferson Carley
 */
public class ConfigManager {

  private ServerConfigItem configItem;

  public ConfigManager() {
  }

  public void configureServer(Tomcat tomcat) throws ServletException {
    loadConfigItem();
    configureInstance(tomcat);
    deployWebApps(tomcat);
  }

  private void loadConfigItem() {
    this.configItem = new ServerConfigItem();
  }

  private void configureInstance(Tomcat tomcat) {
    // the directory that Tomcat will create its working directory in
    tomcat.setBaseDir(".");

    // the port to run server on
    tomcat.setPort(this.configItem.getPort());

    // the name of the directory to look for war files
    String appBase = this.configItem.getAppBase();

    StandardHost host = (StandardHost) tomcat.getHost();
    host.setAppBase(appBase);
    host.setUnpackWARs(false);
    host.setAutoDeploy(true);
    host.setDeployOnStartup(true);

    // I really don't think this line is necessary
    tomcat.setHost(host);

    // Add JasperListener
    StandardServer server = (StandardServer)tomcat.getServer();
    JasperListener jasper = new JasperListener();
    server.addLifecycleListener(jasper);
  }

  /*
   * Deploys WAR files located in the appBase directory.  There is a
   * convention in use here.  The context name will be the same name
   * as the WAR file with out the extension.  This makes it very easy
   * to add new applictions without having to specify any context name
   * or WAR file config in the server configuration file.
   */
  private void deployWebApps(Tomcat tomcat) throws ServletException {
    Host host = tomcat.getHost();

    File appBase = new File(host.getAppBase());
    if(appBase.isDirectory()) {
      File[] warFiles = appBase.listFiles(new FileFilter() {
        @Override
        public boolean accept(File file) {
          return file.getName().endsWith(".war");
        }
      });

      for(File warFile : warFiles) {
        String contextName = getFilenameWithoutExtension(warFile);
        String warFileName = warFile.getName();

        tomcat.addWebapp("/" + contextName, warFileName);
      }
    }

  }

  private String getFilenameWithoutExtension(File file) {
    int index = file.getName().lastIndexOf('.');
    if (index > 0 && index <= file.getName().length() - 2 ) {
      return file.getName().substring(0, index);
    }
    return file.getName();
  }

}
