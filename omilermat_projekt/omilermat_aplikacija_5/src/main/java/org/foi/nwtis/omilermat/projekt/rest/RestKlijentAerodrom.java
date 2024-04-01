package org.foi.nwtis.omilermat.projekt.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.foi.nwtis.omilermat.projekt.ws.WsAerodromi.endpoint.Aerodrom;
import org.foi.nwtis.podaci.Udaljenost;
import org.foi.nwtis.podaci.UdaljenostAerodrom;
import com.google.gson.Gson;
import jakarta.ws.rs.ClientErrorException;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class RestKlijentAerodrom {

  public RestKlijentAerodrom() {}

  public List<Aerodrom> dohvatiSveAerodrome(String traziNaziv, String traziDrzavu, int odBroja,
      int broj) {
    RestKKlijent rc = new RestKKlijent();
    Aerodrom[] json_Aerodromi = rc.dohvatiSveAerodrome(traziNaziv, traziDrzavu, odBroja, broj);
    List<Aerodrom> aerodromi;
    if (json_Aerodromi == null) {
      aerodromi = new ArrayList<>();
    } else {
      aerodromi = Arrays.asList(json_Aerodromi);
    }
    rc.close();
    return aerodromi;
  }

  public Aerodrom dohvatiPodatkeOAerodromu(String icao) {
    RestKKlijent rc = new RestKKlijent();
    Aerodrom aerodrom = rc.dohvatiPodatkeOAerodromu(icao);
    rc.close();
    return aerodrom;
  }

  public List<Udaljenost> dohvatiUdaljenostiOdabranihAerodroma(String icaoOd, String icaoDo) {
    RestKKlijent rc = new RestKKlijent();
    Udaljenost[] json_Udaljenosti = rc.dohvatiUdaljenostiOdabranihAerodroma(icaoOd, icaoDo);
    List<Udaljenost> udaljenost;
    if (json_Udaljenosti == null) {
      udaljenost = new ArrayList<>();
    } else {
      udaljenost = Arrays.asList(json_Udaljenosti);
    }
    rc.close();
    return udaljenost;
  }

  public String dohvatiUdaljenostDvaAerodroma(String icaoOd, String icaoDo) {
    RestKKlijent rc = new RestKKlijent();
    Response jsonZapis = rc.dohvatiUdaljenostDvaAerodroma(icaoOd, icaoDo);
    String jsonOdgovor = jsonZapis.readEntity(String.class);
    rc.close();

    String jsonObjekt = new Gson().fromJson(jsonOdgovor, String.class);

    return jsonObjekt;
  }

  public List<UdaljenostAerodrom> dohvatiUdaljenost1IcaoOdDo(String icaoOd, String icaoDo) {
    RestKKlijent rc = new RestKKlijent();
    UdaljenostAerodrom[] jsonZapis = rc.dohvatiUdaljenost1icaoOdDo(icaoOd, icaoDo);
    List<UdaljenostAerodrom> udaljenosti;
    if (jsonZapis == null) {
      udaljenosti = new ArrayList<>();
    } else {
      udaljenosti = Arrays.asList(jsonZapis);
    }
    rc.close();
    return udaljenosti;
  }

  public List<UdaljenostAerodrom> dohvatiUdaljenost2(String icaoOd, String drzava, double km) {
    RestKKlijent rc = new RestKKlijent();
    UdaljenostAerodrom[] jsonZapis = rc.dohvatiUdaljenost2(icaoOd, drzava, km);
    List<UdaljenostAerodrom> udaljenosti;
    if (jsonZapis == null) {
      udaljenosti = new ArrayList<>();
    } else {
      udaljenosti = Arrays.asList(jsonZapis);
    }
    rc.close();
    return udaljenosti;
  }


  static class RestKKlijent {

    private final WebTarget webTarget;
    private final Client client;
    private static final String BASE_URI = "http://200.20.0.4:8080/omilermat_aplikacija_2/api";

    public RestKKlijent() {
      client = ClientBuilder.newClient();
      webTarget = client.target(BASE_URI).path("aerodromi");
    }

    public Aerodrom[] dohvatiSveAerodrome(String traziNaziv, String traziDrzavu, int odBroja,
        int broj) {
      WebTarget resource =
          webTarget.queryParam("traziNaziv", traziNaziv).queryParam("traziDrzavu", traziDrzavu)
              .queryParam("odBroja", odBroja).queryParam("broj", broj);

      Invocation.Builder request = resource.request(MediaType.APPLICATION_JSON);
      if (request.get(String.class).isEmpty()) {
        return null;
      }
      Gson gson = new Gson();
      Aerodrom[] aerodromi = gson.fromJson(request.get(String.class), Aerodrom[].class);

      return aerodromi;
    }

    public Aerodrom dohvatiPodatkeOAerodromu(String icao) {
      WebTarget resource = webTarget;
      resource = resource.path(java.text.MessageFormat.format("{0}", new Object[] {icao}));
      Invocation.Builder request = resource.request(MediaType.APPLICATION_JSON);
      if (request.get(String.class).isEmpty()) {
        return null;
      }
      Gson gson = new Gson();
      Aerodrom aerodrom = gson.fromJson(request.get(String.class), Aerodrom.class);

      return aerodrom;
    }

    public Udaljenost[] dohvatiUdaljenostiOdabranihAerodroma(String icaoOd, String icaoDo)
        throws ClientErrorException {
      WebTarget resource = webTarget;
      resource =
          resource.path(java.text.MessageFormat.format("{0}/{1}", new Object[] {icaoOd, icaoDo}));

      Invocation.Builder request = resource.request(MediaType.APPLICATION_JSON);
      if (request.get(String.class).isEmpty()) {
        return null;
      }
      Gson gson = new Gson();
      Udaljenost[] udaljenostAerodroma =
          gson.fromJson(request.get(String.class), Udaljenost[].class);

      return udaljenostAerodroma;
    }

    public Response dohvatiUdaljenostDvaAerodroma(String icaoOd, String icaoDo) {
      WebTarget resource = webTarget;
      resource = resource
          .path(java.text.MessageFormat.format("{0}/izracunaj/{1}", new Object[] {icaoOd, icaoDo}));

      Invocation.Builder request = resource.request(MediaType.APPLICATION_JSON);
      if (request.get(String.class).isEmpty()) {
        return null;
      }
      return request.get();
    }

    public UdaljenostAerodrom[] dohvatiUdaljenost1icaoOdDo(String icaoOd, String icaoDo) {
      WebTarget resource = webTarget;
      resource = resource.path(
          java.text.MessageFormat.format("{0}/udaljenost1/{1}", new Object[] {icaoOd, icaoDo}));

      Invocation.Builder request = resource.request(MediaType.APPLICATION_JSON);
      if (request.get(String.class).isEmpty()) {
        return null;
      }
      Gson gson = new Gson();
      UdaljenostAerodrom[] udaljenostAerodroma =
          gson.fromJson(request.get(String.class), UdaljenostAerodrom[].class);

      return udaljenostAerodroma;
    }

    public UdaljenostAerodrom[] dohvatiUdaljenost2(String icaoOd, String drzava, double km) {
      WebTarget resource = webTarget.queryParam("drzava", drzava).queryParam("km", km);

      resource =
          resource.path(java.text.MessageFormat.format("{0}/udaljenost2", new Object[] {icaoOd}));

      System.out.println(resource);

      Invocation.Builder request = resource.request(MediaType.APPLICATION_JSON);
      if (request.get(String.class).isEmpty()) {
        return null;
      }
      Gson gson = new Gson();
      UdaljenostAerodrom[] udaljenosti =
          gson.fromJson(request.get(String.class), UdaljenostAerodrom[].class);

      return udaljenosti;
    }

    public void close() {
      client.close();
    }
  }
}
