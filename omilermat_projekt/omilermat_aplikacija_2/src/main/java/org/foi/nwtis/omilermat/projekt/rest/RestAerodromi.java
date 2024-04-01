package org.foi.nwtis.omilermat.projekt.rest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.Charset;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.foi.nwtis.Konfiguracija;
import org.foi.nwtis.podaci.Aerodrom;
import org.foi.nwtis.podaci.Lokacija;
import org.foi.nwtis.podaci.Udaljenost;
import org.foi.nwtis.podaci.UdaljenostAerodrom;
import com.google.gson.Gson;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.servlet.ServletContext;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("aerodromi")
@RequestScoped
public class RestAerodromi {

  @Inject
  private ServletContext kontekst;

  @Resource(lookup = "java:app/jdbc/nwtis_bp")
  javax.sql.DataSource ds;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response dohvatiAerodrome(@QueryParam("traziNaziv") String traziNaziv,
      @QueryParam("traziDrzavu") String traziDrzavu,
      @DefaultValue("1") @QueryParam("odBroja") int odBroja,
      @DefaultValue("20") @QueryParam("broj") int broj) {

    boolean jeFiltriran = false;

    List<Aerodrom> aerodromi = new ArrayList<Aerodrom>();

    StringBuilder upit = new StringBuilder();
    upit.append("SELECT ICAO, NAME, ISO_COUNTRY, COORDINATES FROM AIRPORTS");

    if (traziNaziv != null && !traziNaziv.isEmpty()) {
      upit.append(" WHERE NAME LIKE '%").append(traziNaziv).append("%'");
      jeFiltriran = true;
    }

    if (traziDrzavu != null && !traziDrzavu.isEmpty()) {
      if (jeFiltriran) {
        upit.append(" AND ISO_COUNTRY = '").append(traziDrzavu).append("'");
      } else {
        upit.append(" WHERE ISO_COUNTRY = '").append(traziDrzavu).append("'");
        jeFiltriran = true;
      }
    }

    upit.append(" LIMIT ? OFFSET ?");

    String brojString = String.valueOf(broj);
    int kreniOd = (odBroja * broj) - broj;
    String kreniOdString = String.valueOf(kreniOd);

    PreparedStatement stmt = null;
    try (var con = ds.getConnection()) {
      stmt = con.prepareStatement(upit.toString());
      stmt.setString(1, brojString);
      stmt.setString(2, kreniOdString);

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

    var gson = new Gson();
    var jsonAerodromi = gson.toJson(aerodromi);
    var odgovor = Response.ok().entity(jsonAerodromi).build();

    return odgovor;
  }


  @GET
  @Path("{icao}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response dajAerodrom(@PathParam("icao") String icao) {
    List<Aerodrom> aerodromi = new ArrayList<>();

    String query = "SELECT ICAO, NAME, ISO_COUNTRY, COORDINATES FROM AIRPORTS WHERE ICAO = ?";

    PreparedStatement stmt = null;
    try (var con = ds.getConnection()) {
      stmt = con.prepareStatement(query);
      stmt.setString(1, icao);

      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
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

    var gson = new Gson();
    var jsonAerodromi = gson.toJson(aerodromi.get(0));
    var odgovor = Response.ok().entity(jsonAerodromi).build();

    return odgovor;
  }

  @GET
  @Path("{icaoOd}/{icaoDo}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response dajUdaljenostiAerodoma(@PathParam("icaoOd") String icaoOd,
      @PathParam("icaoDo") String icaoDo) {

    List<Udaljenost> udaljenosti = new ArrayList<Udaljenost>();

    String query = "SELECT ICAO_FROM, ICAO_TO, COUNTRY, DIST_CTRY FROM AIRPORTS_DISTANCE_MATRIX "
        + "WHERE ICAO_FROM = ? AND ICAO_TO = ?";

    PreparedStatement stmt = null;
    try (var con = ds.getConnection()) {
      stmt = con.prepareStatement(query);
      stmt.setString(1, icaoOd);
      stmt.setString(2, icaoDo);

      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        String drzava = rs.getString("COUNTRY");
        float udaljenost = rs.getFloat("DIST_CTRY");
        var u = new Udaljenost(drzava, udaljenost);
        udaljenosti.add(u);
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
    var jsonAerodromi = gson.toJson(udaljenosti);
    var odgovor = Response.ok().entity(jsonAerodromi).build();

    return odgovor;
  }

  @GET
  @Path("{icao}/udaljenosti")
  @Produces(MediaType.APPLICATION_JSON)
  public Response dajUdaljenostiOdOdabranogAerodroma(@PathParam("icao") String icao,
      @DefaultValue("1") @QueryParam("odBroja") int odBroja,
      @DefaultValue("20") @QueryParam("broj") int broj) {

    List<UdaljenostAerodrom> udaljenosti = new ArrayList<UdaljenostAerodrom>();

    String query = "SELECT DISTINCT ICAO_TO, DIST_TOT FROM AIRPORTS_DISTANCE_MATRIX "
        + "WHERE ICAO_FROM = ? ORDER BY DIST_TOT ASC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

    PreparedStatement stmt = null;
    try (var con = ds.getConnection()) {
      stmt = con.prepareStatement(query);
      stmt.setString(1, icao);
      int kreniOd = (odBroja * broj) - broj;
      stmt.setInt(2, kreniOd);
      stmt.setInt(3, broj);

      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        String icaoTo = rs.getString("ICAO_TO");
        float udaljenost = rs.getFloat("DIST_TOT");
        var u = new UdaljenostAerodrom(icaoTo, udaljenost);
        udaljenosti.add(u);
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
    var jsonAerodromi = gson.toJson(udaljenosti);
    var odgovor = Response.ok().entity(jsonAerodromi).build();

    return odgovor;
  }

  @GET
  @Path("{icaoOd}/izracunaj/{icaoDo}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response udaljenostAerodromaIzracunaj(@PathParam("icaoOd") String icaoOd,
      @PathParam("icaoDo") String icaoDo) {

    StringBuilder poruka = null;

    Konfiguracija konfig = (Konfiguracija) kontekst.getAttribute("konfig");
    String adresa = konfig.dajPostavku("adresa");
    Integer mreznaVrata = Integer.parseInt(konfig.dajPostavku("mreznaVrata"));

    try {
      Socket mreznaUticnica = new Socket(adresa, mreznaVrata);
      var citac = new BufferedReader(
          new InputStreamReader(mreznaUticnica.getInputStream(), Charset.forName("UTF-8")));
      var pisac = new BufferedWriter(
          new OutputStreamWriter(mreznaUticnica.getOutputStream(), Charset.forName("UTF-8")));

      Lokacija koordinateOd = dohvatiKoordinateIcao(icaoOd);
      Lokacija koordinateDo = dohvatiKoordinateIcao(icaoDo);

      String zahtjev =
          "UDALJENOST " + koordinateOd.getLatitude() + " " + koordinateOd.getLongitude() + " "
              + koordinateDo.getLatitude() + " " + koordinateDo.getLongitude();

      pisac.write(zahtjev);
      pisac.flush();
      mreznaUticnica.shutdownOutput();

      poruka = new StringBuilder();
      while (true) {
        var red = citac.readLine();
        if (red == null)
          break;

        poruka.append(red);
      }
      Logger.getGlobal().log(Level.INFO, "Odgovor: " + poruka);
      mreznaUticnica.shutdownInput();
      mreznaUticnica.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    Gson gson = new Gson();
    String jsonOdgovor = gson.toJson(poruka);
    var odgovor = Response.ok().entity(jsonOdgovor).build();

    return odgovor;
  }

  @GET
  @Path("{icaoOd}/udaljenost1/{icaoDo}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response udaljenostiAerodroma(@PathParam("icaoOd") String icaoOd,
      @PathParam("icaoDo") String icaoDo) {

    StringBuilder poruka = null;
    Lokacija koordinateOd = null;
    Lokacija koordinateDo = null;

    Konfiguracija konfig = (Konfiguracija) kontekst.getAttribute("konfig");
    String adresa = konfig.dajPostavku("adresa");
    Integer mreznaVrata = Integer.parseInt(konfig.dajPostavku("mreznaVrata"));

    try {
      Socket mreznaUticnica = new Socket(adresa, mreznaVrata);
      var citac = new BufferedReader(
          new InputStreamReader(mreznaUticnica.getInputStream(), Charset.forName("UTF-8")));
      var pisac = new BufferedWriter(
          new OutputStreamWriter(mreznaUticnica.getOutputStream(), Charset.forName("UTF-8")));

      koordinateOd = dohvatiKoordinateIcao(icaoOd);
      koordinateDo = dohvatiKoordinateIcao(icaoDo);

      String zahtjev =
          "UDALJENOST " + koordinateOd.getLatitude() + " " + koordinateOd.getLongitude() + " "
              + koordinateDo.getLatitude() + " " + koordinateDo.getLongitude();

      pisac.write(zahtjev);
      pisac.flush();
      mreznaUticnica.shutdownOutput();

      poruka = new StringBuilder();
      while (true) {
        var red = citac.readLine();
        if (red == null)
          break;

        poruka.append(red);
      }
      Logger.getGlobal().log(Level.INFO, "Odgovor: " + poruka);
      mreznaUticnica.shutdownInput();
      mreznaUticnica.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    String drzava = dajDrzavu(icaoDo);
    String[] udaljenostPocetna = poruka.toString().split(" ");
    double udaljenostPocetnaDouble = Double.parseDouble(udaljenostPocetna[1]);

    List<Aerodrom> sviAerodromiDrzava = dohvatiSveAerodromeIzDrzave(drzava);
    List<UdaljenostAerodrom> odabraniAerodromiUdaljenost = new ArrayList<UdaljenostAerodrom>();

    for (Aerodrom aerodrom : sviAerodromiDrzava) {
      Lokacija lokacija = aerodrom.getLokacija();

      double novaUdaljenost = izracunajUdaljenost(koordinateOd, lokacija);

      if (udaljenostPocetnaDouble > novaUdaljenost) {
        odabraniAerodromiUdaljenost.add(new UdaljenostAerodrom(aerodrom.getIcao(), novaUdaljenost));
      }
    }

    Gson gson = new Gson();
    String jsonOdgovor = gson.toJson(odabraniAerodromiUdaljenost);
    var odgovor = Response.ok().entity(jsonOdgovor).build();

    return odgovor;
  }

  @GET
  @Path("{icaoOd}/udaljenost2")
  @Produces(MediaType.APPLICATION_JSON)
  public Response udaljenostiAerodroma(@PathParam("icaoOd") String icaoOd,
      @QueryParam("drzava") String drzava, @QueryParam("km") double km) {

    Lokacija koordinateOd = dohvatiKoordinateIcao(icaoOd);

    List<Aerodrom> sviAerodromiDrzava = dohvatiSveAerodromeIzDrzave(drzava);
    List<UdaljenostAerodrom> odabraniAerodromiUdaljenost = new ArrayList<UdaljenostAerodrom>();

    for (Aerodrom aerodrom : sviAerodromiDrzava) {
      Lokacija lokacija = aerodrom.getLokacija();

      double novaUdaljenost = izracunajUdaljenost(koordinateOd, lokacija);

      if (km > novaUdaljenost) {
        odabraniAerodromiUdaljenost.add(new UdaljenostAerodrom(aerodrom.getIcao(), novaUdaljenost));
      }
    }

    Gson gson = new Gson();
    String jsonOdgovor = gson.toJson(odabraniAerodromiUdaljenost);
    var odgovor = Response.ok().entity(jsonOdgovor).build();

    return odgovor;
  }

  private Lokacija dohvatiKoordinateIcao(String icaoOdDo) {

    Lokacija lokacija = null;
    String query = "SELECT COORDINATES FROM AIRPORTS WHERE ICAO = ?";

    PreparedStatement stmt = null;
    try (var con = ds.getConnection()) {
      stmt = con.prepareStatement(query);
      stmt.setString(1, icaoOdDo);

      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        String lokacijaString = rs.getString("COORDINATES");
        String[] koordinate = lokacijaString.split(", ");
        lokacija = new Lokacija(koordinate[0], koordinate[1]);
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
    return lokacija;
  }

  private String dajDrzavu(String icaoDo) {

    String drzava = null;
    String query = "SELECT ISO_COUNTRY FROM AIRPORTS WHERE ICAO = ?";

    PreparedStatement stmt = null;
    try (var con = ds.getConnection()) {
      stmt = con.prepareStatement(query);
      stmt.setString(1, icaoDo);

      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        drzava = rs.getString("ISO_COUNTRY");
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
    return drzava;
  }

  private List<Aerodrom> dohvatiSveAerodromeIzDrzave(String drzava) {

    List<Aerodrom> aerodrom = new ArrayList<Aerodrom>();

    String query =
        "SELECT ICAO, NAME, ISO_COUNTRY, COORDINATES FROM AIRPORTS WHERE ISO_COUNTRY = ?";

    PreparedStatement stmt = null;
    try (var con = ds.getConnection()) {
      stmt = con.prepareStatement(query);
      stmt.setString(1, drzava);

      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        String icao = rs.getString("ICAO");
        String naziv = rs.getString("NAME");
        String lokacijaString = rs.getString("COORDINATES");
        String[] koordinate = lokacijaString.split(", ");
        Lokacija lokacija = new Lokacija(koordinate[0], koordinate[1]);
        var ad = new Aerodrom(icao, naziv, drzava, lokacija);
        aerodrom.add(ad);
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
    return aerodrom;
  }

  private double izracunajUdaljenost(Lokacija lokacija1, Lokacija lokacija2) {

    StringBuilder poruka = null;

    Konfiguracija konfig = (Konfiguracija) kontekst.getAttribute("konfig");
    String adresa = konfig.dajPostavku("adresa");
    Integer mreznaVrata = Integer.parseInt(konfig.dajPostavku("mreznaVrata"));

    try {
      Socket mreznaUticnica = new Socket(adresa, mreznaVrata);
      var citac = new BufferedReader(
          new InputStreamReader(mreznaUticnica.getInputStream(), Charset.forName("UTF-8")));
      var pisac = new BufferedWriter(
          new OutputStreamWriter(mreznaUticnica.getOutputStream(), Charset.forName("UTF-8")));

      String zahtjev = "UDALJENOST " + lokacija1.getLatitude() + " " + lokacija1.getLongitude()
          + " " + lokacija2.getLatitude() + " " + lokacija2.getLongitude();


      pisac.write(zahtjev);
      pisac.flush();
      mreznaUticnica.shutdownOutput();

      poruka = new StringBuilder();
      while (true) {
        var red = citac.readLine();
        if (red == null)
          break;

        poruka.append(red);
      }
      Logger.getGlobal().log(Level.INFO, "Odgovor: " + poruka);
      mreznaUticnica.shutdownInput();
      mreznaUticnica.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return Double.parseDouble(poruka.toString().split(" ")[1]);
  }
}
