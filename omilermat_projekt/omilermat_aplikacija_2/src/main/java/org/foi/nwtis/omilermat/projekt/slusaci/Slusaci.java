package org.foi.nwtis.omilermat.projekt.slusaci;

import java.io.IOException;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Properties;
import org.foi.nwtis.Konfiguracija;
import org.foi.nwtis.KonfiguracijaApstraktna;
import org.foi.nwtis.NeispravnaKonfiguracija;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class Slusaci implements ServletContextListener {
  private ServletContext kontekst;

  @Override
  public void contextInitialized(ServletContextEvent event) {
    kontekst = event.getServletContext();
    ucitajKonfiguraciju();
    provjeriStatusOdPosluzitelja();
  }

  private void ucitajKonfiguraciju() {
    String path = kontekst.getRealPath("/WEB-INF") + java.io.File.separator;
    String datoteka = kontekst.getInitParameter("konfiguracija");

    try {
      Konfiguracija konfiguracija = KonfiguracijaApstraktna.preuzmiKonfiguraciju(path + datoteka);
      konfiguracija.ucitajKonfiguraciju();

      Properties configData = konfiguracija.dajSvePostavke();
      Enumeration<?> keys = configData.propertyNames();
      while (keys.hasMoreElements()) {
        String key = (String) keys.nextElement();
        konfiguracija.spremiPostavku(key, configData.getProperty(key));
      }

      kontekst.setAttribute("konfig", konfiguracija);
      System.out.println("Učitana konfiguracija!");

    } catch (NeispravnaKonfiguracija e) {
      System.out.println("Problem s konfiguracijom!");
    }
  }

  @Override
  public void contextDestroyed(ServletContextEvent event) {
    kontekst = event.getServletContext();
    System.out.println("Obrisan kontekst: " + kontekst.getContextPath());
  }

  private void provjeriStatusOdPosluzitelja() {
    Konfiguracija konfig = (Konfiguracija) kontekst.getAttribute("konfig");
    String adresa = konfig.dajPostavku("adresa");
    int mreznaVrata = Integer.parseInt(konfig.dajPostavku("mreznaVrata"));

    try {
      Socket mreznaUticnica = new Socket(adresa, mreznaVrata);
      mreznaUticnica.close();
    } catch (IOException e) {
      throw new RuntimeException();
    }
    System.out.println("Poslužitelj na mrežnoj utičnici je aktivan. Nastavljam rad programa.");
  }

}
