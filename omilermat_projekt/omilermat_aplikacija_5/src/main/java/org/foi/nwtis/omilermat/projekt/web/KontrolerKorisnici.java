package org.foi.nwtis.omilermat.projekt.web;

import org.foi.nwtis.Konfiguracija;
import org.foi.nwtis.omilermat.projekt.ws.WsKorisnici.endpoint.Korisnici;
import org.foi.nwtis.omilermat.projekt.ws.WsKorisnici.endpoint.Korisnik;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.Models;
import jakarta.mvc.View;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.xml.ws.WebServiceRef;

@Controller
@Path("korisnici")
@RequestScoped
public class KontrolerKorisnici {

  @WebServiceRef(wsdlLocation = "http://localhost:8080/omilermat_aplikacija_4/korisnici?wsdl")
  private Korisnici service;

  @Inject
  private Models model;
  @Inject
  private ServletContext kontekst;
  @Inject
  private HttpSession sesija;

  @GET
  @Path("aktivnosti")
  @View("pogled_5_2.jsp")
  public void pocetak() {
    String korIme = (String) sesija.getAttribute("korIme");
    String lozinka = (String) sesija.getAttribute("lozinka");
    model.put("korIme", korIme);
    model.put("lozinka", lozinka);
  }

  @GET
  @Path("odjava")
  @View("pogled_5_2.jsp")
  public void odjava() {
    sesija.invalidate();
  }

  @GET
  @Path("registracija")
  @View("pogled_5_2_1.jsp")
  public void registrirajKorisnika() {
    Konfiguracija konfiguracija = (Konfiguracija) kontekst.getAttribute("konfig");
    model.put("autorIme", konfiguracija.dajPostavku("autor.ime"));
    model.put("autorPrezime", konfiguracija.dajPostavku("autor.prezime"));
    model.put("predmetNaziv", konfiguracija.dajPostavku("autor.predmet"));
    model.put("godina", konfiguracija.dajPostavku("aplikacija.godina"));
    model.put("verzijaAplikacije", konfiguracija.dajPostavku("aplikacija.verzija"));
  }

  @POST
  @Path("dodajKorisnika")
  @View("pogled_5_2_2.jsp")
  public void putKorisnik(@FormParam("korIme") String korIme, @FormParam("lozinka") String lozinka,
      @FormParam("ime") String ime, @FormParam("prezime") String prezime,
      @FormParam("email") String email) {
    try {
      Korisnik korisnik = new Korisnik();
      korisnik.setIme(ime);
      korisnik.setPrezime(prezime);
      korisnik.setKorIme(korIme);
      korisnik.setEmail(email);
      korisnik.setLozinka(lozinka);
      Konfiguracija konfiguracija = (Konfiguracija) kontekst.getAttribute("konfig");
      var port = service.getWsKorisniciPort();
      var korisnikUnos = port.dodajKorisnika(korisnik);
      model.put("korisnikUnos", korisnikUnos);
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
  @Path("prijava")
  @View("pogled_5_2_2.jsp")
  public void prijaviKorisnika() {
    Konfiguracija konfiguracija = (Konfiguracija) kontekst.getAttribute("konfig");
    model.put("autorIme", konfiguracija.dajPostavku("autor.ime"));
    model.put("autorPrezime", konfiguracija.dajPostavku("autor.prezime"));
    model.put("predmetNaziv", konfiguracija.dajPostavku("autor.predmet"));
    model.put("godina", konfiguracija.dajPostavku("aplikacija.godina"));
    model.put("verzijaAplikacije", konfiguracija.dajPostavku("aplikacija.verzija"));
  }

  @GET
  @Path("prijavljen")
  @View("index.jsp")
  public void getKorisnik(@QueryParam("korIme") String korIme,
      @QueryParam("lozinka") String lozinka) {
    try {
      Konfiguracija konfiguracija = (Konfiguracija) kontekst.getAttribute("konfig");
      var port = service.getWsKorisniciPort();
      var korisnikUnos = port.dajKorisnika(korIme, lozinka, korIme);
      model.put("korisnikUnos", korisnikUnos);
      model.put("autorIme", konfiguracija.dajPostavku("autor.ime"));
      model.put("autorPrezime", konfiguracija.dajPostavku("autor.prezime"));
      model.put("predmetNaziv", konfiguracija.dajPostavku("autor.predmet"));
      model.put("godina", konfiguracija.dajPostavku("aplikacija.godina"));
      model.put("verzijaAplikacije", konfiguracija.dajPostavku("aplikacija.verzija"));
      sesija.setAttribute("korIme", korIme);
      sesija.setAttribute("lozinka", lozinka);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @GET
  @Path("svi")
  @View("pogled_5_2_3.jsp")
  public void getKorisnici(@QueryParam("traziImeKorisnika") String traziImeKorisnika,
      @QueryParam("traziPrezimeKorisnika") String traziPrezimeKorisnika) {
    try {
      Konfiguracija konfiguracija = (Konfiguracija) kontekst.getAttribute("konfig");
      var port = service.getWsKorisniciPort();
      var korisnikDohvaceni = port.dajKorisnike(sesija.getAttribute("korIme").toString(),
          sesija.getAttribute("lozinka").toString(), traziImeKorisnika, traziPrezimeKorisnika);
      model.put("korisnikDohvaceni", korisnikDohvaceni);
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
