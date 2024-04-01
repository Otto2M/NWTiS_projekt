package org.foi.nwtis.omilermat.projekt;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.foi.nwtis.Konfiguracija;
import org.foi.nwtis.KonfiguracijaApstraktna;
import org.foi.nwtis.NeispravnaKonfiguracija;

public class PokretacPosluzitelja {

  public static void main(String[] args) {
    PokretacPosluzitelja pokretac = new PokretacPosluzitelja();
    if (!pokretac.provjeriArgumente(args)) {
      Logger.getLogger(PokretacPosluzitelja.class.getName()).log(Level.SEVERE,
          "Datoteka ne postoji ili su upisani argumenti neispravni!");
      return;
    }

    try {
      Konfiguracija konf = pokretac.ucitajPostavke(args[0]);
      GlavniPosluzitelj glavniPosluzitelj = new GlavniPosluzitelj(konf);
      glavniPosluzitelj.pokreniPosluzitelja();
    } catch (NeispravnaKonfiguracija e) {
      Logger.getLogger(PokretacPosluzitelja.class.getName()).log(Level.SEVERE,
          "Pogreška kod učitavanja postavki iz datoteke!" + e.getMessage());
    }
  }

  private boolean provjeriArgumente(String[] args) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < args.length; i++) {
      sb.append(args[i]).append(" ");
    }
    String argmunet = sb.toString().trim();

    if (imaJedanArgument(args)) {
      String regexKonfDatoteka = "[\\w]+.(txt|bin|yaml|json|xml)";
      Pattern pt = Pattern.compile(regexKonfDatoteka);
      Matcher mt = pt.matcher(argmunet);
      boolean status = mt.matches();
      return status;
    } else {
      return false;
    }
  }

  private static boolean imaJedanArgument(String[] args) {
    if (args.length > 1) {
      return false;
    } else
      return true;
  }

  Konfiguracija ucitajPostavke(String nazivDatoteke) throws NeispravnaKonfiguracija {
    return KonfiguracijaApstraktna.preuzmiKonfiguraciju(nazivDatoteke);
  }

}
