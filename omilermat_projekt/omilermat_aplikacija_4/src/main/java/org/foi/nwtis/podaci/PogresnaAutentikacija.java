package org.foi.nwtis.podaci;

public class PogresnaAutentikacija extends Exception {
  private static final long serialVersionUID = 8155604052215166021L;

  public PogresnaAutentikacija() {
    super("Autentikacija nije uspješna!");
  }
}

