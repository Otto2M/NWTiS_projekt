package org.foi.nwtis.omilermat.projekt;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.foi.nwtis.Konfiguracija;

public class GlavniPosluzitelj {

  protected Konfiguracija konf;

  public int ispis = 0;
  private int mreznaVrata;
  private int brojCekaca;
  private int brojacAktivnihDretvi = 0;
  public boolean status = false;
  public int brojacZahtjeva = 0;
  private ServerSocket ss;

  public boolean krajRada = false;

  public GlavniPosluzitelj(Konfiguracija konf) {
    this.konf = konf;
    this.mreznaVrata = Integer.parseInt(konf.dajPostavku("mreznaVrata"));
    this.brojCekaca = Integer.parseInt(konf.dajPostavku("brojCekaca"));
  }

  public void pokreniPosluzitelja() {
    if (!provjeriMreznaVrata()) {
      Logger.getGlobal().log(Level.SEVERE, "Mrežna vrata su zauzeta!");
      return;
    } else {
      otvoriMreznaVrata();
    }
  }

  public void otvoriMreznaVrata() {
    try {
      ss = new ServerSocket(this.mreznaVrata, this.brojCekaca);
      while (!this.krajRada) {
        Socket uticnica = ss.accept();
        brojacAktivnihDretvi++;
        MrezniRadnik dretvaMr = new MrezniRadnik(uticnica, konf, this);
        dretvaMr.setName("omilermat_" + Integer.toString(brojacAktivnihDretvi));
        dretvaMr.start();
      }

    } catch (IOException e) {
      Logger.getGlobal().log(Level.SEVERE, e.getMessage());
    }
  }

  public Boolean provjeriMreznaVrata() {
    try (ServerSocket posluziteljskaUticnica = new ServerSocket(mreznaVrata)) {
      return true;
    } catch (IOException e) {
      return false;
    }
  }

  public void zatvoriServer() {
    try {
      this.ss.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


}
