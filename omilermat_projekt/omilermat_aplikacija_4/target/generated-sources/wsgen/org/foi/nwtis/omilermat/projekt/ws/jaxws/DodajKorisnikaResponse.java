
package org.foi.nwtis.omilermat.projekt.ws.jaxws;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "dodajKorisnikaResponse", namespace = "http://ws.projekt.omilermat.nwtis.foi.org/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dodajKorisnikaResponse", namespace = "http://ws.projekt.omilermat.nwtis.foi.org/")
public class DodajKorisnikaResponse {

    @XmlElement(name = "return", namespace = "")
    private boolean _return;

    /**
     * 
     * @return
     *     returns boolean
     */
    public boolean isReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(boolean _return) {
        this._return = _return;
    }

}
