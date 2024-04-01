package org.foi.nwtis.omilermat.projekt.filter;

import java.io.IOException;
import org.foi.nwtis.omilermat.projekt.rest.RestKlijentDnevnik;
import org.foi.nwtis.podaci.Dnevnik;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

@WebFilter("/*")
public class AP5Filter implements Filter {

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    chain.doFilter(request, response);

    HttpServletRequest httpZahtjev = (HttpServletRequest) request;
    StringBuffer radnja = httpZahtjev.getRequestURL();

    Dnevnik zapis = new Dnevnik();
    zapis.setRadnja(radnja.toString());
    zapis.setTipMetode(httpZahtjev.getMethod());
    zapis.setDatumVrijeme(null);
    zapis.setVrsta("AP5");

    RestKlijentDnevnik rkd = new RestKlijentDnevnik();

    rkd.spremanjeZapisaAP5Filtera(zapis);

  }

}
