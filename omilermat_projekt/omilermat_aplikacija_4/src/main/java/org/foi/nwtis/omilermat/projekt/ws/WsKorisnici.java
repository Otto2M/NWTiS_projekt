package org.foi.nwtis.omilermat.projekt.ws;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.foi.nwtis.omilermat.projekt.zrna.ProvjeraKorisnika;
import org.foi.nwtis.podaci.Korisnik;
import org.foi.nwtis.podaci.PogresnaAutentikacija;
import jakarta.annotation.Resource;
import jakarta.ejb.EJB;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

@WebService(serviceName = "korisnici")
public class WsKorisnici {

  @Resource(lookup = "java:app/jdbc/nwtis_bp")
  javax.sql.DataSource ds;

  @EJB
  ProvjeraKorisnika provjeraKorisnika;

  @WebMethod
  public List<Korisnik> dajKorisnike(@WebParam String korisnik, @WebParam String lozinka,
      @WebParam String traziImeKorisnika, @WebParam String traziPrezimeKorisnika)
      throws PogresnaAutentikacija {

    if (!provjeraKorisnika.provjeriKorisnika(korisnik, lozinka)) {
      throw new PogresnaAutentikacija();
    }

    boolean jeFiltriran = false;

    List<Korisnik> korisnici = new ArrayList<Korisnik>();

    StringBuilder upit = new StringBuilder();
    upit.append("SELECT IME, PREZIME, KORISNICKO_IME, LOZINKA, EMAIL FROM KORISNIK");

    if (traziImeKorisnika != null && !traziImeKorisnika.isEmpty()) {
      upit.append(" WHERE IME LIKE '%").append(traziImeKorisnika).append("%'");
      jeFiltriran = true;
    }

    if (traziPrezimeKorisnika != null && !traziPrezimeKorisnika.isEmpty()) {
      if (jeFiltriran) {
        upit.append(" AND PREZIME LIKE '%").append(traziPrezimeKorisnika).append("%'");
      } else {
        upit.append(" WHERE PREZIME LIKE '%").append(traziPrezimeKorisnika).append("%'");
        jeFiltriran = true;
      }
    }

    PreparedStatement stmt = null;
    try (var con = ds.getConnection()) {
      stmt = con.prepareStatement(upit.toString());

      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        String ime = rs.getString("IME");
        String prezime = rs.getString("PREZIME");
        String korIme = rs.getString("KORISNICKO_IME");
        String lozinkaUpit = rs.getString("LOZINKA");
        String email = rs.getString("EMAIL");

        var noviKorisnik = new Korisnik(korIme, ime, prezime, lozinkaUpit, email);
        korisnici.add(noviKorisnik);
      }
      rs.close();
    } catch (SQLException e) {
      e.printStackTrace();
      Logger.getGlobal().log(Level.SEVERE, e.getMessage());
    } finally {
      try {
        if (stmt != null && !stmt.isClosed())
          stmt.close();
      } catch (SQLException e) {
        Logger.getGlobal().log(Level.SEVERE, e.getMessage());
      }
    }

    return korisnici;
  }

  @WebMethod
  public Korisnik dajKorisnika(@WebParam String korisnik, @WebParam String lozinka,
      @WebParam String traziKorisnika) throws PogresnaAutentikacija {

    if (!provjeraKorisnika.provjeriKorisnika(korisnik, lozinka)) {
      throw new PogresnaAutentikacija();
    }

    Korisnik trazeni = null;

    String upit =
        "SELECT IME, PREZIME, KORISNICKO_IME, LOZINKA, EMAIL FROM KORISNIK WHERE KORISNICKO_IME = ?";

    PreparedStatement stmt = null;
    try (var con = ds.getConnection()) {
      stmt = con.prepareStatement(upit.toString());
      stmt.setString(1, traziKorisnika);

      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        String ime = rs.getString("IME");
        String prezime = rs.getString("PREZIME");
        String korIme = rs.getString("KORISNICKO_IME");
        String lozinkaUpit = rs.getString("LOZINKA");
        String email = rs.getString("EMAIL");

        trazeni = new Korisnik(korIme, ime, prezime, lozinkaUpit, email);

      }
      rs.close();
    } catch (SQLException e) {
      e.printStackTrace();
      Logger.getGlobal().log(Level.SEVERE, e.getMessage());
    } finally {
      try {
        if (stmt != null && !stmt.isClosed())
          stmt.close();
      } catch (SQLException e) {
        Logger.getGlobal().log(Level.SEVERE, e.getMessage());
      }
    }

    return trazeni;
  }

  @WebMethod
  public boolean dodajKorisnika(@WebParam Korisnik korisnik) {

    Boolean jeDodan = false;
    String ime = korisnik.getIme();
    String prezime = korisnik.getPrezime();
    String korIme = korisnik.getKorIme();
    String lozinka = korisnik.getLozinka();
    String email = korisnik.getEmail();

    PreparedStatement stmt = null;
    try (var con = ds.getConnection()) {
      String upit = "INSERT INTO KORISNIK (IME, PREZIME, KORISNICKO_IME, LOZINKA, EMAIL) VALUES('"
          + ime + "', '" + prezime + "', '" + korIme + "', '" + lozinka + "', '" + email + "')";

      stmt = con.prepareStatement(upit);
      try {
        if (ime.isEmpty() || prezime.isEmpty() || korIme.isEmpty() || lozinka.isEmpty()) {
          jeDodan = false;
        } else {
          stmt.execute();
          jeDodan = true;
        }
      } catch (SQLException e) {
      }

    } catch (SQLException e) {
      e.printStackTrace();
      Logger.getGlobal().log(Level.SEVERE, e.getMessage());
    } finally {
      try {
        if (stmt != null && !stmt.isClosed())
          stmt.close();
      } catch (SQLException e) {
        Logger.getGlobal().log(Level.SEVERE, e.getMessage());
      }
    }

    return jeDodan;
  }
}
