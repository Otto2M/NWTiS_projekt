package org.foi.nwtis.omilermat.projekt.rest;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.foi.nwtis.podaci.Dnevnik;
import com.google.gson.Gson;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.servlet.ServletContext;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("dnevnik")
@RequestScoped
public class RestDnevnik {

  @Inject
  private ServletContext kontekst;

  @Resource(lookup = "java:app/jdbc/nwtis_bp")
  javax.sql.DataSource ds;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response dajPodatkeZaDnevnikVrsta(@QueryParam("vrsta") String vrsta,
      @DefaultValue("1") @QueryParam("odBroja") int odBroja,
      @DefaultValue("20") @QueryParam("broj") int broj) {

    String brojString = String.valueOf(broj);
    int kreniOd = (odBroja * broj) - broj;
    String kreniOdString = String.valueOf(kreniOd);

    List<Dnevnik> dnevnikVrsta = new ArrayList<Dnevnik>();

    StringBuilder upit = new StringBuilder();
    upit.append("SELECT RADNJA, METODA, DATUM_VRIJEME, VRSTA FROM DNEVNIK");

    if (vrsta != null && !vrsta.isEmpty()) {
      upit.append(" WHERE VRSTA = '").append(vrsta).append("' LIMIT ").append(brojString)
          .append(" OFFSET ").append(kreniOdString);

    } else {
      upit.append(" LIMIT ").append(brojString).append(" OFFSET ").append(kreniOdString);
    }

    PreparedStatement stmt = null;
    try (var con = ds.getConnection()) {
      stmt = con.prepareStatement(upit.toString());

      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        String radnja = rs.getString("RADNJA");
        String tipMetode = rs.getString("METODA");
        String datumVrijeme = rs.getString("DATUM_VRIJEME");
        String vrstaZapis = rs.getString("VRSTA");
        var dnevnikZapis = new Dnevnik(radnja, tipMetode, datumVrijeme, vrstaZapis);
        dnevnikVrsta.add(dnevnikZapis);
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
    var gson = new Gson();
    var jsonDnevikZapisi = gson.toJson(dnevnikVrsta);
    var odgovor = Response.ok().entity(jsonDnevikZapisi).build();

    return odgovor;
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public String spremiZapisUDnevnik(Dnevnik dnevnikZapis) {

    String radnja = dnevnikZapis.getRadnja();
    String tipMetode = dnevnikZapis.getTipMetode();
    String vrsta = dnevnikZapis.getVrsta();

    PreparedStatement stmt = null;
    try (var con = ds.getConnection()) {
      String query = "INSERT INTO PUBLIC.PUBLIC.DNEVNIK (RADNJA, METODA, DATUM_VRIJEME, VRSTA)"
          + "VALUES('" + radnja + "', '" + tipMetode + "', NOW(), '" + vrsta + "')";

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

    return null;
  }

}
