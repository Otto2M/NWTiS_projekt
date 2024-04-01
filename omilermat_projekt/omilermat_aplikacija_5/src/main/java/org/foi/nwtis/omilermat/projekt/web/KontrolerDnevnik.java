package org.foi.nwtis.omilermat.projekt.web;

import org.foi.nwtis.Konfiguracija;
import org.foi.nwtis.omilermat.projekt.rest.RestKlijentDnevnik;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.Models;
import jakarta.mvc.View;
import jakarta.servlet.ServletContext;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;

@Controller
@Path("dnevnik")
@RequestScoped
public class KontrolerDnevnik {

  @Inject
  private Models model;
  @Inject
  private ServletContext kontekst;

  @GET
  @Path("pocetak")
  @View("pogled_5_7.jsp")
  public void pocetak() {}

  @GET
  @Path("")
  @View("pogled_5_7.jsp")
  public void getDnevnik(@QueryParam("vrsta") String vrsta,
      @QueryParam("odBroja") @DefaultValue("1") int odBroja,
      @QueryParam("broj") @DefaultValue("20") int broj) {
    try {
      Konfiguracija konfiguracija = (Konfiguracija) kontekst.getAttribute("konfig");
      RestKlijentDnevnik rcl = new RestKlijentDnevnik();
      var dnevnik = rcl.dohvatiPodatkeIzDnevnika(vrsta, odBroja, broj);
      model.put("dnevnik", dnevnik);
      model.put("autorIme", konfiguracija.dajPostavku("autor.ime"));
      model.put("autorPrezime", konfiguracija.dajPostavku("autor.prezime"));
      model.put("predmetNaziv", konfiguracija.dajPostavku("autor.predmet"));
      model.put("godina", konfiguracija.dajPostavku("aplikacija.godina"));
      model.put("verzijaAplikacije", konfiguracija.dajPostavku("aplikacija.verzija"));
      model.put("vrsta", vrsta);
      model.put("odBroja", odBroja);
      model.put("broj", broj);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
