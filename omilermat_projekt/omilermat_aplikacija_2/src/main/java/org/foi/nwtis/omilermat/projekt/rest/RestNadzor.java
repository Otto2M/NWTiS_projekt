package org.foi.nwtis.omilermat.projekt.rest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.foi.nwtis.Konfiguracija;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.servlet.ServletContext;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("nadzor")
@RequestScoped
public class RestNadzor {

  @Inject
  private ServletContext kontekst;

  @Resource(lookup = "java:app/jdbc/nwtis_bp")
  javax.sql.DataSource ds;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response dajStatus() {

    Konfiguracija konfig = (Konfiguracija) kontekst.getAttribute("konfig");
    String adresa = konfig.dajPostavku("adresa");
    Integer mreznaVrata = Integer.parseInt(konfig.dajPostavku("mreznaVrata"));
    StringBuilder poruka = null;
    String status;
    String opis;

    try {
      Socket mreznaUticnica = new Socket(adresa, mreznaVrata);
      var citac = new BufferedReader(
          new InputStreamReader(mreznaUticnica.getInputStream(), Charset.forName("UTF-8")));
      var pisac = new BufferedWriter(
          new OutputStreamWriter(mreznaUticnica.getOutputStream(), Charset.forName("UTF-8")));

      String zahtjev = "STATUS";
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

    if (poruka.toString().startsWith("ERROR")) {
      status = "400";
      opis = poruka.toString();
    } else {
      status = "200";
      opis = "Komanda izvršena - " + poruka;
    }

    JsonObject jsonResponse = new JsonObject();
    jsonResponse.addProperty("status", status);
    jsonResponse.addProperty("opis", opis);

    Gson gson = new Gson();
    String jsonOdgovor = gson.toJson(jsonResponse);
    var odgovor = Response.status(Integer.parseInt(status)).entity(jsonOdgovor).build();

    return odgovor;

  }

  @Path("{komanda}")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response pokreniKomandu(@PathParam("komanda") String komanda) {

    Konfiguracija konfig = (Konfiguracija) kontekst.getAttribute("konfig");
    String adresa = konfig.dajPostavku("adresa");
    Integer mreznaVrata = Integer.parseInt(konfig.dajPostavku("mreznaVrata"));
    StringBuilder poruka = null;
    String status;
    String opis;

    try {
      Socket mreznaUticnica = new Socket(adresa, mreznaVrata);
      var citac = new BufferedReader(
          new InputStreamReader(mreznaUticnica.getInputStream(), Charset.forName("UTF-8")));
      var pisac = new BufferedWriter(
          new OutputStreamWriter(mreznaUticnica.getOutputStream(), Charset.forName("UTF-8")));

      String zahtjev = komanda;
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
    }

    if (poruka.toString().startsWith("ERROR")) {
      status = "400";
      opis = poruka.toString();
    } else {
      status = "200";
      opis = "Komanda izvršena - " + poruka;
    }

    JsonObject jsonResponse = new JsonObject();
    jsonResponse.addProperty("status", status);
    jsonResponse.addProperty("opis", opis);

    Gson gson = new Gson();
    String jsonOdgovor = gson.toJson(jsonResponse);
    var odgovor = Response.status(Integer.parseInt(status)).entity(jsonOdgovor).build();

    return odgovor;
  }

  @Path("INFO/{vrsta}")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response dajInfo(@PathParam("vrsta") String vrsta) {

    Konfiguracija konfig = (Konfiguracija) kontekst.getAttribute("konfig");
    String adresa = konfig.dajPostavku("adresa");
    Integer mreznaVrata = Integer.parseInt(konfig.dajPostavku("mreznaVrata"));
    StringBuilder poruka = null;
    String status;
    String opis;

    try {
      Socket mreznaUticnica = new Socket(adresa, mreznaVrata);
      var citac = new BufferedReader(
          new InputStreamReader(mreznaUticnica.getInputStream(), Charset.forName("UTF-8")));
      var pisac = new BufferedWriter(
          new OutputStreamWriter(mreznaUticnica.getOutputStream(), Charset.forName("UTF-8")));

      String zahtjev = "INFO " + vrsta;
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

    if (poruka.toString().startsWith("ERROR")) {
      status = "400";
      opis = poruka.toString();
    } else {
      status = "200";
      opis = "Komanda izvršena - " + poruka;
    }

    JsonObject jsonResponse = new JsonObject();
    jsonResponse.addProperty("status", status);
    jsonResponse.addProperty("opis", opis);

    Gson gson = new Gson();
    String jsonOdgovor = gson.toJson(jsonResponse);
    var odgovor = Response.status(Integer.parseInt(status)).entity(jsonOdgovor).build();

    return odgovor;
  }
}
