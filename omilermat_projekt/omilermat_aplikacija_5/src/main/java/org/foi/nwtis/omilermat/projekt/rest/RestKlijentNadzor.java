package org.foi.nwtis.omilermat.projekt.rest;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class RestKlijentNadzor {

  public RestKlijentNadzor() {}

  public String dajStatus() {
    RestKKlijent rc = new RestKKlijent();
    var status = rc.dajStatusPosluzitelja();
    String jsonOdgovor = status.readEntity(String.class);
    rc.close();

    JsonObject jsonObjekt = new Gson().fromJson(jsonOdgovor, JsonObject.class);

    String opis = jsonObjekt.get("opis").getAsString();

    return opis;
  }

  public String pokreniKomandu(String komanda) {
    RestKKlijent rc = new RestKKlijent();
    Response komandaPokrenuta = rc.pokreniKomandu(komanda);
    String jsonOdgovor = komandaPokrenuta.readEntity(String.class);
    rc.close();

    JsonObject jsonObjekt = new Gson().fromJson(jsonOdgovor, JsonObject.class);

    String opis = jsonObjekt.get("opis").getAsString();

    return opis;
  }

  public String dajInfo(String vrsta) {
    RestKKlijent rc = new RestKKlijent();
    Response info = rc.dajInfo(vrsta);
    String jsonOdgovor = info.readEntity(String.class);
    rc.close();

    JsonObject jsonObjekt = new Gson().fromJson(jsonOdgovor, JsonObject.class);

    String opis = jsonObjekt.get("opis").getAsString();

    return opis;
  }

  static class RestKKlijent {

    private final WebTarget webTarget;
    private final Client client;
    private static final String BASE_URI = "http://200.20.0.4:8080/omilermat_aplikacija_2/api";

    public RestKKlijent() {
      client = ClientBuilder.newClient();
      webTarget = client.target(BASE_URI).path("nadzor");
    }

    public Response dajStatusPosluzitelja() {
      WebTarget resource = webTarget;
      Invocation.Builder request = resource.request(MediaType.APPLICATION_JSON);
      return request.get();
    }

    public Response pokreniKomandu(String komanda) {
      WebTarget resource = webTarget;
      resource = resource.path(java.text.MessageFormat.format("{0}", new Object[] {komanda}));
      Invocation.Builder request = resource.request(MediaType.APPLICATION_JSON);
      return request.get();
    }

    public Response dajInfo(String vrsta) {
      WebTarget resource = webTarget;
      resource = resource.path(java.text.MessageFormat.format("INFO/{0}", new Object[] {vrsta}));
      Invocation.Builder request = resource.request(MediaType.APPLICATION_JSON);
      return request.get();
    }

    public void close() {
      client.close();
    }
  }
}
