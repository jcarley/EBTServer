package com.finishfirstsoftware.configuration;

/**
 *
 * @author Jefferson Carley
 */
public class ServerConfigItem {

  private String appBase = "webapps";
  private int port = 8080;
  private String[] contexts;

  public String getAppBase() {
    return appBase;
  }

  public void setAppBase(String appBase) {
    this.appBase = appBase;
  }

  public int getPort() {
    return port;
  }

  public void setPort(int port) {
    this.port = port;
  }

  public String[] getContexts() {
    return contexts;
  }

  public void setContexts(String[] contexts) {
    this.contexts = contexts;
  }

}
