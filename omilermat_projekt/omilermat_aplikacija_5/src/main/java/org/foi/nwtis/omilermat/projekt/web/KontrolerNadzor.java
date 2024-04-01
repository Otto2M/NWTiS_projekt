package org.foi.nwtis.omilermat.projekt.web;

import org.foi.nwtis.Konfiguracija;
import org.foi.nwtis.omilermat.projekt.rest.RestKlijentNadzor;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.Models;
import jakarta.mvc.View;
import jakarta.servlet.ServletContext;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;

@Controller
@Path("nadzor")
@RequestScoped
public class KontrolerNadzor {

  @Inject
  private Models model;
  @Inject
  private ServletContext kontekst;

  @GET
  @Path("")
  @View("pogled_5_3.jsp")
  public void getStatus() {
    try {
      Konfiguracija konfiguracija = (Konfiguracija) kontekst.getAttribute("konfig");
      RestKlijentNadzor rcn = new RestKlijentNadzor();
      var status = rcn.dajStatus();
      model.put("status", status);
      model.put("autorIme", konfiguracija.dajPostavku("autor.ime"));
      model.put("autorPrezime", konfiguracija.dajPostavku("autor.prezime"));
      model.put("predmetNaziv", konfiguracija.dajPostavku("autor.predmet"));
      model.put("godina", konfiguracija.dajPostavku("aplikacija.godina"));
      model.put("verzijaAplikacije", konfiguracija.dajPostavku("aplikacija.verzija"));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @GET
  @Path("komanda")
  @View("pogled_5_3.jsp")
  public void pokreniKomandu(@QueryParam("komanda") String komanda) {

    try {
      Konfiguracija konfiguracija = (Konfiguracija) kontekst.getAttribute("konfig");
      RestKlijentNadzor rcn = new RestKlijentNadzor();
      var status = rcn.pokreniKomandu(komanda);
      model.put("status", status);
      model.put("autorIme", konfiguracija.dajPostavku("autor.ime"));
      model.put("autorPrezime", konfiguracija.dajPostavku("autor.prezime"));
      model.put("predmetNaziv", konfiguracija.dajPostavku("autor.predmet"));
      model.put("godina", konfiguracija.dajPostavku("aplikacija.godina"));
      model.put("verzijaAplikacije", konfiguracija.dajPostavku("aplikacija.verzija"));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @GET
  @Path("info")
  @View("pogled_5_3.jsp")
  public void getInfo(@QueryParam("vrsta") String vrsta) {

    try {
      Konfiguracija konfiguracija = (Konfiguracija) kontekst.getAttribute("konfig");
      RestKlijentNadzor rcn = new RestKlijentNadzor();
      var status = rcn.dajInfo(vrsta);
      model.put("status", status);
      model.put("autorIme", konfiguracija.dajPostavku("autor.ime"));
      model.put("autorPrezime", konfiguracija.dajPostavku("autor.prezime"));
      model.put("predmetNaziv", konfiguracija.dajPostavku("autor.predmet"));
      model.put("godina", konfiguracija.dajPostavku("aplikacija.godina"));
      model.put("verzijaAplikacije", konfiguracija.dajPostavku("aplikacija.verzija"));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
