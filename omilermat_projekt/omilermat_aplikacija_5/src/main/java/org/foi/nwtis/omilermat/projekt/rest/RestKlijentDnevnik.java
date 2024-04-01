package org.foi.nwtis.omilermat.projekt.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.foi.nwtis.podaci.Dnevnik;
import com.google.gson.Gson;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;

public class RestKlijentDnevnik {

  public RestKlijentDnevnik() {}

  public void spremanjeZapisaAP5Filtera(Dnevnik zapis) {
    RestKKlijent rc = new RestKKlijent();
    rc.sremanjeZapisa(zapis);
    rc.close();
  }

  public List<Dnevnik> dohvatiPodatkeIzDnevnika(String vrsta, int odBroja, int broj) {
    RestKKlijent rc = new RestKKlijent();
    Dnevnik[] json_zapisDnevnik = rc.dohvatiPodatkeIzDnevnika(vrsta, odBroja, broj);
    List<Dnevnik> dnevnik;
    if (json_zapisDnevnik == null) {
      dnevnik = new ArrayList<>();
    } else {
      dnevnik = Arrays.asList(json_zapisDnevnik);
    }
    rc.close();
    return dnevnik;
  }

  static class RestKKlijent {

    private final WebTarget webTarget;
    private final Client client;
    private static final String BASE_URI = "http://200.20.0.4:8080/omilermat_aplikacija_2/api";

    public RestKKlijent() {
      client = ClientBuilder.newClient();
      webTarget = client.target(BASE_URI).path("dnevnik");
    }

    public void sremanjeZapisa(Dnevnik zapis) {
      WebTarget resource = webTarget;
      Invocation.Builder request = resource.request(MediaType.APPLICATION_JSON);
      Gson gson = new Gson();
      String zapisZaSpremnaje = gson.toJson(zapis);
      request.post(Entity.json(zapisZaSpremnaje));
    }

    public Dnevnik[] dohvatiPodatkeIzDnevnika(String vrsta, int odBroja, int broj) {
      WebTarget resource = webTarget.queryParam("odBroja", odBroja).queryParam("broj", broj)
          .queryParam("vrsta", vrsta);

      Invocation.Builder request = resource.request(MediaType.APPLICATION_JSON);
      if (request.get(String.class).isEmpty()) {
        return null;
      }
      Gson gson = new Gson();
      Dnevnik[] dnevnik = gson.fromJson(request.get(String.class), Dnevnik[].class);

      return dnevnik;
    }

    public void close() {
      client.close();
    }
  }
}
