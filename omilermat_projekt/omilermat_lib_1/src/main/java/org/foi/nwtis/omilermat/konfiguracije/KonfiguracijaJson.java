package org.foi.nwtis.omilermat.konfiguracije;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;
import org.foi.nwtis.Konfiguracija;
import org.foi.nwtis.KonfiguracijaApstraktna;
import org.foi.nwtis.NeispravnaKonfiguracija;
import com.google.gson.Gson;

/**
 * 
 * Klasa konfiguracija za rad sa postavkama konfiguracije u .json formatu
 * 
 * @author Otto Miler Matulin
 *
 */
public class KonfiguracijaJson extends KonfiguracijaApstraktna {
  /**
   * Konstanta TIP
   */
  public static final String TIP = "json";
  private Gson gson = new Gson();

  /**
   * Konstruktor za incijalizaciju KonfiguracijaJson
   * 
   * @param nazivDatoteke - Naziv datoteke
   */
  public KonfiguracijaJson(String nazivDatoteke) {
    super(nazivDatoteke);
  }

  /**
   * Sprema konfiguraciju na disk u JSON formatu
   * 
   * @param datoteka - naziv Datoteke
   * @throws NeispravnaKonfiguracija - iznimka kada nešto nije ispravno
   */
  @Override
  public void spremiKonfiguraciju(String datoteka) throws NeispravnaKonfiguracija {
    var putanja = Path.of(datoteka);
    var tip = Konfiguracija.dajTipKonfiguracije(datoteka);

    if (tip == null || tip.compareTo(TIP) != 0) {
      throw new NeispravnaKonfiguracija("Datoteka '" + datoteka + "' nije tip " + TIP);
    } else if (Files.exists(putanja)
        && (Files.isDirectory(putanja) || !Files.isWritable(putanja))) {
      throw new NeispravnaKonfiguracija(
          "Datoteka '" + datoteka + "' je direktorij ili nije moguće pisati.");
    }

    try {
      BufferedWriter pisac = Files.newBufferedWriter(putanja);
      Gson gson = new Gson();
      String json = gson.toJson(this.postavke);
      pisac.write(json);
      pisac.close();
    } catch (IOException e) {
      throw new NeispravnaKonfiguracija(
          "Datoteka '" + datoteka + "' nije moguće upisivati. " + e.getMessage());
    }
  }

  /**
   * Učitaj konfiguraciju sa diska iz JSON datoteke
   * 
   * @throws NeispravnaKonfiguracija - ispisuje se kada nešto nije ispravno
   */
  @Override
  public void ucitajKonfiguraciju() throws NeispravnaKonfiguracija {
    var datoteka = this.nazivDatoteke;
    var putanja = Path.of(datoteka);
    var tip = Konfiguracija.dajTipKonfiguracije(datoteka);

    if (tip == null || tip.compareTo(TIP) != 0) {
      throw new NeispravnaKonfiguracija("Datoteka '" + datoteka + "' nije tip " + TIP);
    } else if (Files.exists(putanja)
        && (Files.isDirectory(putanja) || !Files.isReadable(putanja))) {
      throw new NeispravnaKonfiguracija(
          "Datoteka '" + datoteka + "' je direktorij ili nije moguće pisati.");
    }

    try {
      Gson gson = new Gson();
      FileReader citac = new FileReader(datoteka);
      BufferedReader buffread = new BufferedReader(citac);
      this.postavke = gson.fromJson(buffread, Properties.class);
      buffread.close();
      citac.close();
    } catch (IOException e) {
      throw new NeispravnaKonfiguracija(
          "Datoteka '" + datoteka + "' nije moguće čitati. " + e.getMessage());
    }
  }

}
