package org.foi.nwtis.omilermat.projekt.ws;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.foi.nwtis.Konfiguracija;
import org.foi.nwtis.podaci.Aerodrom;
import org.foi.nwtis.rest.klijenti.NwtisRestIznimka;
import org.foi.nwtis.rest.klijenti.OWMKlijent;
import org.foi.nwtis.rest.podaci.MeteoPodaci;
import com.google.gson.Gson;
import jakarta.annotation.Resource;
import jakarta.inject.Inject;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.servlet.ServletContext;

@WebService(serviceName = "meteo")
public class WsMeteo {

  @Inject
  private ServletContext kontekst;

  @Resource(lookup = "java:app/jdbc/nwtis_bp")
  javax.sql.DataSource ds;

  @WebMethod
  public MeteoPodaci dajMeteo(@WebParam String icao) {

    MeteoPodaci meteoPodaci = null;
    Konfiguracija konfig = (Konfiguracija) kontekst.getAttribute("konfig");

    org.foi.nwtis.podaci.Lokacija lokacija = dohvatiGPSKoordinate(icao);

    String geoSirina = lokacija.getLatitude();
    String geoDuzina = lokacija.getLongitude();

    String apiKljucOWM = konfig.dajPostavku("OpenWeatherMap.apikey");

    OWMKlijent owmk = new OWMKlijent(apiKljucOWM);

    try {
      meteoPodaci = owmk.getRealTimeWeather(geoDuzina, geoSirina);
    } catch (NwtisRestIznimka e) {
      e.printStackTrace();
    }

    return meteoPodaci;
  }

  private org.foi.nwtis.podaci.Lokacija dohvatiGPSKoordinate(String icao) {

    org.foi.nwtis.podaci.Lokacija lokacija = null;

    try {
      URL url = new URL("http://200.20.0.4:8080/omilermat_aplikacija_2/api/aerodromi/" + icao);
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("GET");

      int kodOdgovora = connection.getResponseCode();
      if (kodOdgovora == HttpURLConnection.HTTP_OK) {
        BufferedReader citac =
            new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder odgovor = new StringBuilder();
        String line;

        while ((line = citac.readLine()) != null) {
          odgovor.append(line);
        }
        citac.close();

        Gson gson = new Gson();
        Aerodrom aerodrom = gson.fromJson(odgovor.toString(), Aerodrom.class);
        lokacija = aerodrom.getLokacija();
        return lokacija;

      } else {
        System.out.println("Greška: " + kodOdgovora);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    return null;
  }


}


