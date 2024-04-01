package org.foi.nwtis.omilermat.projekt.zrna;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;

@Stateless
public class ProvjeraKorisnika {

  @Resource(lookup = "java:app/jdbc/nwtis_bp")
  javax.sql.DataSource ds;

  public Boolean provjeriKorisnika(String korisnik, String lozinka) {

    Boolean postoji = false;
    String upit =
        "SELECT KORISNICKO_IME, LOZINKA FROM KORISNIK WHERE KORISNICKO_IME = ? AND LOZINKA = ?";

    PreparedStatement stmt = null;
    try (var con = ds.getConnection()) {
      stmt = con.prepareStatement(upit.toString());
      stmt.setString(1, korisnik);
      stmt.setString(2, lozinka);

      ResultSet rs = stmt.executeQuery();
      if (rs.next()) {
        postoji = true;
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

    return postoji;
  }

}
