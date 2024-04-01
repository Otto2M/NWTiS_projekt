package org.foi.nwtis.podaci;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor()
public class Dnevnik {

  @Getter
  @Setter
  private String radnja;
  @Getter
  @Setter
  private String tipMetode;
  @Getter
  @Setter
  private String datumVrijeme;
  @Getter
  @Setter
  private String vrsta;

  public Dnevnik() {}
}
