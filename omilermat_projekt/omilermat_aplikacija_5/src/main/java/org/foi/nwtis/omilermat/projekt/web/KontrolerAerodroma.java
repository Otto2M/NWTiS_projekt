package org.foi.nwtis.omilermat.projekt.web;

import org.foi.nwtis.Konfiguracija;
import org.foi.nwtis.omilermat.projekt.rest.RestKlijentAerodrom;
import org.foi.nwtis.omilermat.projekt.ws.WsAerodromi.endpoint.Aerodromi;
import org.foi.nwtis.omilermat.projekt.ws.WsMeteo.endpoint.Meteo;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.Models;
import jakarta.mvc.View;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.xml.ws.WebServiceRef;

@Controller
@Path("aerodromi")
@RequestScoped
public class KontrolerAerodroma {

  @WebServiceRef(wsdlLocation = "http://localhost:8080/omilermat_aplikacija_4/aerodromi?wsdl")
  private Aerodromi service;

  @WebServiceRef(wsdlLocation = "http://localhost:8080/omilermat_aplikacija_4/meteo?wsdl")
  private Meteo serviceMeteo;

  @Inject
  private Models model;
  @Inject
  private ServletContext kontekst;
  @Inject
  private HttpSession sesija;

  @GET
  @Path("pocetak")
  @View("index.jsp")
  public void pocetak() {
    String korIme = (String) sesija.getAttribute("korIme");
    String lozinka = (String) sesija.getAttribute("lozinka");
    model.put("korIme", korIme);
    model.put("lozinka", lozinka);
  }

  @GET
  @Path("aktivnosti")
  @View("pogled_5_5.jsp")
  public void aktivnost() {}

  @GET
  @Path("svi")
  @View("pogled_5_5_1.jsp")
  public void dajSveAerodrome(@QueryParam("traziNaziv") String traziNaziv,
      @QueryParam("traziDrzavu") String traziDrzavu,
      @DefaultValue("1") @QueryParam("odBroja") int odBroja,
      @DefaultValue("20") @QueryParam("broj") int broj) {
    try {
      Konfiguracija konfiguracija = (Konfiguracija) kontekst.getAttribute("konfig");
      RestKlijentAerodrom rka = new RestKlijentAerodrom();
      var sviAerodromi = rka.dohvatiSveAerodrome(traziNaziv, traziDrzavu, odBroja, broj);
      model.put("sviAerodromi", sviAerodromi);
      model.put("autorIme", konfiguracija.dajPostavku("autor.ime"));
      model.put("autorPrezime", konfiguracija.dajPostavku("autor.prezime"));
      model.put("predmetNaziv", konfiguracija.dajPostavku("autor.predmet"));
      model.put("godina", konfiguracija.dajPostavku("aplikacija.godina"));
      model.put("verzijaAplikacije", konfiguracija.dajPostavku("aplikacija.verzija"));
      model.put("odBroja", odBroja);
      model.put("broj", broj);
      model.put("traziNaziv", traziNaziv);
      model.put("traziDrzavu", traziDrzavu);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @GET
  @Path("dodajLetZaPreuzimanje")
  @View("pogled_5_5_3.jsp")
  public void dodajAerodrom(@QueryParam("icao") String icao) {
    try {
      String korIme = (String) sesija.getAttribute("korIme");
      String lozinka = (String) sesija.getAttribute("lozinka");
      Konfiguracija konfiguracija = (Konfiguracija) kontekst.getAttribute("konfig");
      var port = service.getWsAerodromiPort();
      var aerodromDodan = port.dodajAerodromZaLetove(korIme, lozinka, icao);
      model.put("aerodromDodan", aerodromDodan);
      var aerodromi = port.dajAerodromeZaLetove(korIme, lozinka);
      model.put("aerodromi", aerodromi);
      var statusPreuzimanja = port.dajAerodromeStatus();
      model.put("statusPreuzimanja", statusPreuzimanja);
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
  @Path("icaoPodaci")
  @View("pogled_5_5_2.jsp")
  public void dajOdabraniAerodrom(@QueryParam("icao") String icao) {
    try {
      Konfiguracija konfiguracija = (Konfiguracija) kontekst.getAttribute("konfig");
      RestKlijentAerodrom rka = new RestKlijentAerodrom();
      var aerodrom = rka.dohvatiPodatkeOAerodromu(icao);
      model.put("aerodrom", aerodrom);
      var port = serviceMeteo.getWsMeteoPort();
      var meteoPodaci = port.dajMeteo(icao);
      model.put("meteoPodaci", meteoPodaci);
      model.put("autorIme", konfiguracija.dajPostavku("autor.ime"));
      model.put("autorPrezime", konfiguracija.dajPostavku("autor.prezime"));
      model.put("predmetNaziv", konfiguracija.dajPostavku("autor.predmet"));
      model.put("godina", konfiguracija.dajPostavku("aplikacija.godina"));
      model.put("verzijaAplikacije", konfiguracija.dajPostavku("aplikacija.verzija"));
      model.put("icao", icao);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @GET
  @Path("polasci")
  @View("pogled_5_5_3.jsp")
  public void dajAerodromePreuzimanje() {
    try {
      String korIme = (String) sesija.getAttribute("korIme");
      String lozinka = (String) sesija.getAttribute("lozinka");
      Konfiguracija konfiguracija = (Konfiguracija) kontekst.getAttribute("konfig");
      var port = service.getWsAerodromiPort();
      var aerodromi = port.dajAerodromeZaLetove(korIme, lozinka);
      model.put("aerodromi", aerodromi);
      var statusPreuzimanja = port.dajAerodromeStatus();
      model.put("statusPreuzimanja", statusPreuzimanja);
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
  @Path("aktiviraj")
  @View("pogled_5_5_3.jsp")
  public void promjeniStatusAerodromaAktiviraj(@QueryParam("icao") String icao) {
    try {
      String korIme = (String) sesija.getAttribute("korIme");
      String lozinka = (String) sesija.getAttribute("lozinka");
      Konfiguracija konfiguracija = (Konfiguracija) kontekst.getAttribute("konfig");
      var port = service.getWsAerodromiPort();
      var status = port.aktivirajAerodromZaLetove(korIme, lozinka, icao);
      model.put("status", status);
      var aerodromi = port.dajAerodromeZaLetove(korIme, lozinka);
      model.put("aerodromi", aerodromi);
      var statusPreuzimanja = port.dajAerodromeStatus();
      model.put("statusPreuzimanja", statusPreuzimanja);
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
  @Path("pauziraj")
  @View("pogled_5_5_3.jsp")
  public void promjeniStatusAerodromaPauziraj(@QueryParam("icao") String icao) {
    try {
      String korIme = (String) sesija.getAttribute("korIme");
      String lozinka = (String) sesija.getAttribute("lozinka");
      Konfiguracija konfiguracija = (Konfiguracija) kontekst.getAttribute("konfig");
      var port = service.getWsAerodromiPort();
      var status = port.pauzirajAerodromZaLetove(korIme, lozinka, icao);
      model.put("status", status);
      var aerodromi = port.dajAerodromeZaLetove(korIme, lozinka);
      model.put("aerodromi", aerodromi);
      var statusPreuzimanja = port.dajAerodromeStatus();
      model.put("statusPreuzimanja", statusPreuzimanja);
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
  @Path("traziUdaljenostDvaAerodromaDrzava")
  @View("pogled_5_5_4_trazi.jsp")
  public void traziUdaljenostOdOdabranihAerodroma() {}

  @GET
  @Path("icaoOdDoUdaljenost")
  @View("pogled_5_5_4.jsp")
  public void dajUdaljenostOdOdabranihAerodroma(@QueryParam("icaoOd") String icaoOd,
      @QueryParam("icaoDo") String icaoDo) {
    try {
      Konfiguracija konfiguracija = (Konfiguracija) kontekst.getAttribute("konfig");
      RestKlijentAerodrom rka = new RestKlijentAerodrom();
      var udaljenostOdabranih = rka.dohvatiUdaljenostiOdabranihAerodroma(icaoOd, icaoDo);
      model.put("udaljenostOdabranih", udaljenostOdabranih);
      model.put("autorIme", konfiguracija.dajPostavku("autor.ime"));
      model.put("autorPrezime", konfiguracija.dajPostavku("autor.prezime"));
      model.put("predmetNaziv", konfiguracija.dajPostavku("autor.predmet"));
      model.put("godina", konfiguracija.dajPostavku("aplikacija.godina"));
      model.put("verzijaAplikacije", konfiguracija.dajPostavku("aplikacija.verzija"));
      model.put("icaoOd", icaoOd);
      model.put("icaoDo", icaoDo);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @GET
  @Path("traziUdaljenostDvaAerodroma")
  @View("pogled_5_5_5_trazi.jsp")
  public void traziUdaljenostDvaAerodroma() {}

  @GET
  @Path("udaljenostDvaAerodroma")
  @View("pogled_5_5_5.jsp")
  public void dajUdaljenostDvaAerodroma(@QueryParam("icaoOd") String icaoOd,
      @QueryParam("icaoDo") String icaoDo) {
    try {
      Konfiguracija konfiguracija = (Konfiguracija) kontekst.getAttribute("konfig");
      RestKlijentAerodrom rka = new RestKlijentAerodrom();
      String udaljenostDvaAerodroma = rka.dohvatiUdaljenostDvaAerodroma(icaoOd, icaoDo);
      double udaljenostBroj = Double.parseDouble(udaljenostDvaAerodroma.substring(3));
      model.put("udaljenostBroj", udaljenostBroj);
      model.put("autorIme", konfiguracija.dajPostavku("autor.ime"));
      model.put("autorPrezime", konfiguracija.dajPostavku("autor.prezime"));
      model.put("predmetNaziv", konfiguracija.dajPostavku("autor.predmet"));
      model.put("godina", konfiguracija.dajPostavku("aplikacija.godina"));
      model.put("verzijaAplikacije", konfiguracija.dajPostavku("aplikacija.verzija"));
      model.put("icaoOd", icaoOd);
      model.put("icaoDo", icaoDo);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @GET
  @Path("traziIcaoOdDo")
  @View("pogled_5_5_6_trazi.jsp")
  public void traziicaoOdicaoDo() {}

  @GET
  @Path("udaljenost1")
  @View("pogled_5_5_6.jsp")
  public void dajUdaljenostIcaoOdDo(@QueryParam("icaoOd") String icaoOd,
      @QueryParam("icaoDo") String icaoDo) {
    try {
      Konfiguracija konfiguracija = (Konfiguracija) kontekst.getAttribute("konfig");
      RestKlijentAerodrom rka = new RestKlijentAerodrom();
      var udaljenost1 = rka.dohvatiUdaljenost1IcaoOdDo(icaoOd, icaoDo);
      model.put("udaljenost1", udaljenost1);
      model.put("autorIme", konfiguracija.dajPostavku("autor.ime"));
      model.put("autorPrezime", konfiguracija.dajPostavku("autor.prezime"));
      model.put("predmetNaziv", konfiguracija.dajPostavku("autor.predmet"));
      model.put("godina", konfiguracija.dajPostavku("aplikacija.godina"));
      model.put("verzijaAplikacije", konfiguracija.dajPostavku("aplikacija.verzija"));
      model.put("icaoOd", icaoOd);
      model.put("icaoDo", icaoDo);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @GET
  @Path("traziDrzavaKm")
  @View("pogled_5_5_7_trazi.jsp")
  public void traziDrzavaKm() {}

  @GET
  @Path("udaljenost2")
  @View("pogled_5_5_7.jsp")
  public void dajUdaljenost2(@QueryParam("icaoOd") String icaoOd,
      @QueryParam("drzava") String drzava, @QueryParam("km") double km) {
    try {
      Konfiguracija konfiguracija = (Konfiguracija) kontekst.getAttribute("konfig");
      RestKlijentAerodrom rka = new RestKlijentAerodrom();
      var udaljenost2 = rka.dohvatiUdaljenost2(icaoOd, drzava, km);
      model.put("udaljenost2", udaljenost2);
      model.put("autorIme", konfiguracija.dajPostavku("autor.ime"));
      model.put("autorPrezime", konfiguracija.dajPostavku("autor.prezime"));
      model.put("predmetNaziv", konfiguracija.dajPostavku("autor.predmet"));
      model.put("godina", konfiguracija.dajPostavku("aplikacija.godina"));
      model.put("verzijaAplikacije", konfiguracija.dajPostavku("aplikacija.verzija"));
      model.put("km", km);
      model.put("drzava", drzava);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
