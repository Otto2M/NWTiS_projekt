package org.foi.nwtis.omilermat.konfiguracije;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.foi.nwtis.Konfiguracija;
import org.foi.nwtis.KonfiguracijaApstraktna;
import org.foi.nwtis.NeispravnaKonfiguracija;

/**
 * 
 * Klasa konfiguracija za rad sa postavkama konfiguracije u .yaml formatu
 * 
 * @author Otto Miler Matulin
 *
 */
public class KonfiguracijaYaml extends KonfiguracijaApstraktna {

  /**
   * Konstanta TIP
   */
  public static final String TIP = "yaml";

  /**
   * Konstruktor za incijalizaciju - KonfiguracijaYaml
   * 
   * @param nazivDatoteke - Naziv datoteke
   */
  public KonfiguracijaYaml(String nazivDatoteke) {
    super(nazivDatoteke);
  }

  /**
   * Sprema konfiguraciju na disk u YAML formatu
   * 
   * @param datoteka - naziv datoteke
   * @throws NeispravnaKonfiguracija - iznimka kada nešto nije ispravno
   */
  @Override
  public void spremiKonfiguraciju(String datoteka) throws NeispravnaKonfiguracija {

  }

  /**
   * Učitaj konfiguraciju sa diska iz YAML datoteke
   * 
   * @throws NeispravnaKonfiguracija - ako nije ispravna
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
      this.postavke.load(Files.newInputStream(putanja));
    } catch (IOException e) {
      throw new NeispravnaKonfiguracija(
          "Datoteka '" + datoteka + "' nije moguće čitati. " + e.getMessage());
    }
  }
}
