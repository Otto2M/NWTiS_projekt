package org.foi.nwtis.omilermat.konfiguracije;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;
import org.foi.nwtis.Konfiguracija;
import org.foi.nwtis.KonfiguracijaApstraktna;
import org.foi.nwtis.NeispravnaKonfiguracija;

/**
 * 
 * Klasa konfiguracija za rad sa postavkama konfiguracije u .bin formatu
 * 
 * @author Otto Miler Matulin
 *
 */
public class KonfiguracijaBin extends KonfiguracijaApstraktna {

  /**
   * Konstanta TIP
   */
  public static final String TIP = "bin";

  /**
   * Konstruktor za incijalizaciju - KonfiguracijaBin
   * 
   * @param nazivDatoteke - Naziv datoteke
   */
  public KonfiguracijaBin(String nazivDatoteke) {
    super(nazivDatoteke);
  }

  /**
   * Sprema konfiguraciju na disk u BIN formatu
   * 
   * @param datoteka - naziv datoteke
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
      FileOutputStream fis = new FileOutputStream(datoteka);
      ObjectOutputStream ois = new ObjectOutputStream(fis);
      ois.writeObject(this.postavke);
      ois.close();
    } catch (IOException e) {
      throw new NeispravnaKonfiguracija(
          "Datoteka '" + datoteka + "' nije moguće upisivati." + e.getMessage());
    }

  }

  /**
   * Učitaj konfiguraciju sa diska iz BIN datoteke
   * 
   * @throws NeispravnaKonfiguracija - ako nije ispravna
   * 
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
          "Datoteka '" + datoteka + "' je direktorij ili nije moguće čitat.");
    }

    try {
      FileInputStream fis = new FileInputStream(datoteka);
      ObjectInputStream ois = new ObjectInputStream(fis);
      this.postavke = (Properties) ois.readObject();
      ois.close();
    } catch (IOException e) {
      throw new NeispravnaKonfiguracija(
          "Datoteka '" + datoteka + "' nije moguće čitati. " + e.getMessage());
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }

  }

}
