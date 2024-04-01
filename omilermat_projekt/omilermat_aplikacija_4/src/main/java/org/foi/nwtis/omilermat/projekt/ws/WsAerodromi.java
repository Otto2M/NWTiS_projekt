package org.foi.nwtis.omilermat.projekt.ws;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.foi.nwtis.omilermat.projekt.zrna.ProvjeraKorisnika;
import org.foi.nwtis.podaci.Aerodrom;
import org.foi.nwtis.podaci.Lokacija;
import org.foi.nwtis.podaci.PogresnaAutentikacija;
import jakarta.annotation.Resource;
import jakarta.ejb.EJB;
import jakarta.inject.Inject;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.servlet.ServletContext;

@WebService(serviceName = "aerodromi")
public class WsAerodromi {

  @Inject
  private ServletContext konfig;

  @EJB
  ProvjeraKorisnika provjeraKorisnika;

  @Resource(lookup = "java:app/jdbc/nwtis_bp")
  javax.sql.DataSource ds;

  @WebMethod
  public List<Aerodrom> dajAerodromeZaLetove(@WebParam String korisnik, @WebParam String lozinka)
      throws PogresnaAutentikacija {

    if (!provjeraKorisnika.provjeriKorisnika(korisnik, lozinka)) {
      throw new PogresnaAutentikacija();
    }

    List<Aerodrom> aerodromi = new ArrayList<Aerodrom>();

    String query = "SELECT ICAO, NAME, ISO_COUNTRY, COORDINATES FROM AIRPORTS "
        + "INNER JOIN AERODROMI_LETOVI ON AIRPORTS.ICAO=AERODROMI_LETOVI.ICAO";

    PreparedStatement stmt = null;
    try (var con = ds.getConnection()) {
      stmt = con.prepareStatement(query);

      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        String icao = rs.getString("ICAO");
        String naziv = rs.getString("NAME");
        String drzava = rs.getString("ISO_COUNTRY");
        String lokacijaString = rs.getString("COORDINATES");
        String[] koordinate = lokacijaString.split(", ");
        Lokacija lokacija = new Lokacija(koordinate[0], koordinate[1]);
        var ad = new Aerodrom(icao, naziv, drzava, lokacija);
        aerodromi.add(ad);
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

    return aerodromi;
  }

  @WebMethod
  public boolean dodajAerodromZaLetove(@WebParam String korisnik, @WebParam String lozinka,
      @WebParam String icao) throws PogresnaAutentikacija {

    if (!provjeraKorisnika.provjeriKorisnika(korisnik, lozinka)) {
      throw new PogresnaAutentikacija();
    }

    Boolean jeDodan = false;
    PreparedStatement stmt = null;
    try (var con = ds.getConnection()) {
      String upit = "INSERT INTO AERODROMI_LETOVI (ICAO, AKTIVAN) VALUES('" + icao + "', true)";

      stmt = con.prepareStatement(upit);
      try {
        if (icao.isEmpty()) {
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

  @WebMethod
  public boolean pauzirajAerodromZaLetove(String korisnik, String lozinka, String icao)
      throws PogresnaAutentikacija {

    if (!provjeraKorisnika.provjeriKorisnika(korisnik, lozinka)) {
      throw new PogresnaAutentikacija();
    }

    Boolean jeAzuriran = false;
    PreparedStatement stmt = null;
    try (var con = ds.getConnection()) {
      String upit = "UPDATE AERODROMI_LETOVI SET AKTIVAN = FALSE WHERE ICAO = '" + icao + "'";

      stmt = con.prepareStatement(upit);
      try {
        if (icao.isEmpty()) {
          jeAzuriran = false;
        } else {
          stmt.execute();
          jeAzuriran = true;
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

    return jeAzuriran;
  }

  @WebMethod
  public boolean aktivirajAerodromZaLetove(String korisnik, String lozinka, String icao)
      throws PogresnaAutentikacija {

    if (!provjeraKorisnika.provjeriKorisnika(korisnik, lozinka)) {
      throw new PogresnaAutentikacija();
    }

    Boolean jeAzuriran = false;
    PreparedStatement stmt = null;
    try (var con = ds.getConnection()) {
      String upit = "UPDATE AERODROMI_LETOVI SET AKTIVAN = TRUE WHERE ICAO = '" + icao + "'";

      stmt = con.prepareStatement(upit);
      try {
        if (icao.isEmpty()) {
          jeAzuriran = false;
        } else {
          stmt.execute();
          jeAzuriran = true;
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

    return jeAzuriran;
  }

  @WebMethod
  public List<String> dajAerodromeStatus() {
    List<String> aerodromi = new ArrayList<String>();
    String query =
        "SELECT AKTIVAN FROM AIRPORTS INNER JOIN AERODROMI_LETOVI ON AIRPORTS.ICAO=AERODROMI_LETOVI.ICAO;";

    PreparedStatement stmt = null;
    try (var con = ds.getConnection()) {
      stmt = con.prepareStatement(query);
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        String aktivan = rs.getString("AKTIVAN");
        aerodromi.add(aktivan);
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

    return aerodromi;
  }

}
