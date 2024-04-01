package org.foi.nwtis.omilermat.projekt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.foi.nwtis.Konfiguracija;

public class MrezniRadnik extends Thread {

  protected Socket mreznaUticnica;
  protected Konfiguracija konfig;
  protected GlavniPosluzitelj glavniPosluzitelj;
  protected boolean zastavica = false;
  private boolean kraj = false;

  public MrezniRadnik(Socket mreznaUticnica, Konfiguracija konfig,
      GlavniPosluzitelj glavniPosluzitelj) {
    super();
    this.mreznaUticnica = mreznaUticnica;
    this.konfig = konfig;
    this.glavniPosluzitelj = glavniPosluzitelj;
  }

  @Override
  public synchronized void start() {
    super.start();
  }

  @Override
  public void run() {
    try {
      BufferedReader citac = new BufferedReader(
          new InputStreamReader(this.mreznaUticnica.getInputStream(), Charset.forName("UTF-8")));
      BufferedWriter pisac = new BufferedWriter(
          new OutputStreamWriter(this.mreznaUticnica.getOutputStream(), Charset.forName("UTF-8")));

      StringBuilder poruka = new StringBuilder();
      while (true) {
        String red = citac.readLine();
        if (red == null)
          break;
        if (glavniPosluzitelj.ispis == 1)
          Logger.getGlobal().log(Level.INFO, red);
        poruka.append(red);
      }
      this.mreznaUticnica.shutdownInput();
      String odgovor = "";

      odgovor = this.obradiZahtjev(poruka.toString());

      pisac.write(odgovor);
      pisac.flush();
      this.mreznaUticnica.shutdownOutput();
      this.mreznaUticnica.close();
    } catch (IOException e) {
    }
    if (kraj == true)
      this.glavniPosluzitelj.zatvoriServer();
  }

  private String obradiZahtjev(String string) {
    String odgovor = "";

    switch (string) {
      case "STATUS":
        odgovor = provjeriStatusPosluzitelja();
        break;

      case "PAUZA":
        if (glavniPosluzitelj.status == true) {
          odgovor = pauzirajPosluzitelja();
        } else {
          odgovor =
              "ERROR 01 | Poslužitelj se nalazi u pauziranom stanju! Izvršavanje komande nije moguće.";
        }
        break;

      case "INIT":
        odgovor = postaviPosluziteljaIStatus();
        break;

      case "KRAJ":
        odgovor = "OK";
        kraj = true;
        break;

      case "INFO DA":
        if (glavniPosluzitelj.status == true) {
          odgovor = dopustiIspisPrimljeneKomande();
        } else {
          odgovor =
              "ERROR 01 | Poslužitelj se nalazi u pauziranom stanju! Izvršavanje komande nije moguće.";
        }
        break;

      case "INFO NE":
        if (glavniPosluzitelj.status == true) {
          odgovor = prekiniIspisPrimljeneKomande();
        } else {
          odgovor =
              "ERROR 01 | Poslužitelj se nalazi u pauziranom stanju! Izvršavanje komande nije moguće.";
        }
        break;

      default:
        if (glavniPosluzitelj.status == true) {
          String[] dijeloviNaredbe = string.toString().split(" ");
          String gpsSirina1 = dijeloviNaredbe[1];
          String gosDuzina1 = dijeloviNaredbe[2];
          String gpsSirina2 = dijeloviNaredbe[3];
          String gosDuzina2 = dijeloviNaredbe[4];

          if (dijeloviNaredbe[0].equals("UDALJENOST")
              && jesuLiKoordinateIspravne(gpsSirina1, gosDuzina1)
              && jesuLiKoordinateIspravne(gpsSirina2, gosDuzina2)) {
            odgovor = izracunajUdaljenostLokacija(gpsSirina1, gosDuzina1, gpsSirina2, gosDuzina2);
          } else {
            odgovor = "ERROR 05 | Format komande nije ispravan!";
          }

          if (zastavica == true)
            glavniPosluzitelj.brojacZahtjeva++;
        } else {
          odgovor =
              "ERROR 01 | Poslužitelj se nalazi u pauziranom stanju! Izvršavanje komande nije moguće.";
        }

    }
    return odgovor;
  }

  private String provjeriStatusPosluzitelja() {
    int statusInt = 0;
    if (glavniPosluzitelj.status == true) {
      statusInt = 1;
    }
    return "OK " + statusInt;
  }

  private String postaviPosluziteljaIStatus() {
    String odgovor = "";
    if (glavniPosluzitelj.status == false) {
      glavniPosluzitelj.status = true;
      glavniPosluzitelj.brojacZahtjeva = 0;
      odgovor = "OK";
    } else {
      odgovor = "ERROR 02 | Poslužitelj je već aktivan! Upisana komanda nije moguća.";
    }
    return odgovor;
  }

  private String pauzirajPosluzitelja() {
    String odgovor = "";
    if (glavniPosluzitelj.status == true) {
      glavniPosluzitelj.status = false;
      odgovor = "OK " + glavniPosluzitelj.brojacZahtjeva;
    } else {
      odgovor =
          "ERROR 01 | Poslužitelj se već nalazi u pauziranom stanju! Izvršavanje komande nije moguće.";
    }
    return odgovor;
  }

  private String dopustiIspisPrimljeneKomande() {
    String odgovor = "";
    if (glavniPosluzitelj.ispis == 1) {
      odgovor =
          "ERROR 03 | Komanda nije moguća jer poslužitelj već ispisuje podatke o svom radu na standardni izlaz!";
    } else {
      glavniPosluzitelj.ispis = 1;
      odgovor = "OK";
    }
    return odgovor;
  }

  private String prekiniIspisPrimljeneKomande() {
    String odgovor = "";
    if (glavniPosluzitelj.ispis == 0) {
      odgovor =
          "ERROR 04 | Komanda nije moguća jer poslužitelj trenutno ne ispisuje podatke o svom radu na standardni izlaz!";
    } else {
      glavniPosluzitelj.ispis = 0;
      odgovor = "OK";
    }
    return odgovor;
  }

  public static boolean jesuLiKoordinateIspravne(String geoSirina, String geoDuzina) {
    try {

      double lat = Double.parseDouble(geoSirina);
      if (lat < -90 || lat > 90) {
        return false;
      }

      double lon = Double.parseDouble(geoDuzina);
      if (lon < -180 || lon > 180) {
        return false;
      }

      if (geoSirina.indexOf('.') != geoSirina.lastIndexOf('.')
          || geoDuzina.indexOf('.') != geoDuzina.lastIndexOf('.')) {
        return false;
      }

      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }


  // https://www.w3resource.com/java-exercises/basic/java-basic-exercise-36.php
  private String izracunajUdaljenostLokacija(String gpsSirina1, String gosDuzina1,
      String gpsSirina2, String gosDuzina2) {

    String odgovor = "";

    double gpsSirina1Float = Double.parseDouble(gpsSirina1);
    double gosDuzina1Float = Double.parseDouble(gosDuzina1);
    double gpsSirina2Float = Double.parseDouble(gpsSirina2);
    double gosDuzina2Float = Double.parseDouble(gosDuzina2);

    double lat1 = Math.toRadians(gpsSirina1Float);
    double lon1 = Math.toRadians(gosDuzina1Float);
    double lat2 = Math.toRadians(gpsSirina2Float);
    double lon2 = Math.toRadians(gosDuzina2Float);

    double EARTH_RADIUS = 6371.01;

    double udaljenost = EARTH_RADIUS * Math.acos(
        Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon1 - lon2));

    DecimalFormat format = new DecimalFormat("#####.##");

    odgovor = "OK " + format.format(udaljenost);
    zastavica = true;

    return odgovor;
  }

  @Override
  public void interrupt() {
    super.interrupt();
  }

}
