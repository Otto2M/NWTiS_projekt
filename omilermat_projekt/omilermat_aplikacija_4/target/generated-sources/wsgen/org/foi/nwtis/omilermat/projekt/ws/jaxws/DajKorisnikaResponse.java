
package org.foi.nwtis.omilermat.projekt.ws.jaxws;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import org.foi.nwtis.podaci.Korisnik;

@XmlRootElement(name = "dajKorisnikaResponse", namespace = "http://ws.projekt.omilermat.nwtis.foi.org/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dajKorisnikaResponse", namespace = "http://ws.projekt.omilermat.nwtis.foi.org/")
public class DajKorisnikaResponse {

    @XmlElement(name = "return", namespace = "")
    private Korisnik _return;

    /**
     * 
     * @return
     *     returns Korisnik
     */
    public Korisnik getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(Korisnik _return) {
        this._return = _return;
    }

}
