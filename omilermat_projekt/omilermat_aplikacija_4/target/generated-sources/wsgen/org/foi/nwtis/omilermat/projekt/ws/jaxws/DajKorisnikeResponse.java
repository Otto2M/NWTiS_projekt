
package org.foi.nwtis.omilermat.projekt.ws.jaxws;

import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import org.foi.nwtis.podaci.Korisnik;

@XmlRootElement(name = "dajKorisnikeResponse", namespace = "http://ws.projekt.omilermat.nwtis.foi.org/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dajKorisnikeResponse", namespace = "http://ws.projekt.omilermat.nwtis.foi.org/")
public class DajKorisnikeResponse {

    @XmlElement(name = "return", namespace = "")
    private List<Korisnik> _return;

    /**
     * 
     * @return
     *     returns List<Korisnik>
     */
    public List<Korisnik> getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(List<Korisnik> _return) {
        this._return = _return;
    }

}
