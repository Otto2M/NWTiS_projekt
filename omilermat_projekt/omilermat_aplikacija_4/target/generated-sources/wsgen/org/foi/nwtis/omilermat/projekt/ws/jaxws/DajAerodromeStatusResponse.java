
package org.foi.nwtis.omilermat.projekt.ws.jaxws;

import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "dajAerodromeStatusResponse", namespace = "http://ws.projekt.omilermat.nwtis.foi.org/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dajAerodromeStatusResponse", namespace = "http://ws.projekt.omilermat.nwtis.foi.org/")
public class DajAerodromeStatusResponse {

    @XmlElement(name = "return", namespace = "")
    private List<String> _return;

    /**
     * 
     * @return
     *     returns List<String>
     */
    public List<String> getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(List<String> _return) {
        this._return = _return;
    }

}
