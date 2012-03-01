package com.finishfirstsoftware;

/**
 *
 * @author Jefferson Carley
 */
public class Program {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    try {
      EBTServer server = new EBTServer();
      server.start();
    } catch(Exception e) {
      e.printStackTrace();
    }
  }
}
