package org.foi.nwtis.omilermat.projekt.filter;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.annotation.Resource;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

@WebFilter("/*")
public class AP2Filter implements Filter {

  @Resource(lookup = "java:app/jdbc/nwtis_bp")
  javax.sql.DataSource ds;

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    chain.doFilter(request, response);

    HttpServletRequest httpZahtjev = (HttpServletRequest) request;
    StringBuffer radnja = httpZahtjev.getRequestURL();
    String opis = "Radnja obvaljena na adresi: ";
    String radnjaString = radnja.toString();
    String tipMetode = httpZahtjev.getMethod();

    PreparedStatement stmt = null;
    try (var con = ds.getConnection()) {
      String query = "INSERT INTO DNEVNIK (RADNJA, METODA, DATUM_VRIJEME, VRSTA)" + "VALUES('"
          + opis + radnjaString + "', '" + tipMetode + "', NOW(), 'AP2')";

      stmt = con.prepareStatement(query);
      stmt.execute();

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

  }

}
