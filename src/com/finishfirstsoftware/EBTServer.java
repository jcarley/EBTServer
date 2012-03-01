package com.finishfirstsoftware;

import com.finishfirstsoftware.configuration.ConfigManager;
import javax.servlet.ServletException;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

/**
 *
 * @author Jefferson Carley
 */
public class EBTServer {

  private Tomcat tomcat;

  public EBTServer() {
    this.tomcat = new Tomcat();
  }

  private void stop() throws LifecycleException {
    this.tomcat.stop();
  }

  public void start() throws LifecycleException, ServletException {

    ConfigManager manager = new ConfigManager();
    manager.configureServer(this.tomcat);

    this.tomcat.start();
    this.tomcat.getServer().await();
  }

}
