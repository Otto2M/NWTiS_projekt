
package org.foi.nwtis.omilermat.projekt.ws.WsAerodromi.endpoint;

import java.util.List;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.ws.Action;
import jakarta.xml.ws.FaultAction;
import jakarta.xml.ws.RequestWrapper;
import jakarta.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 3.0.0
 * Generated source version: 3.0
 * 
 */
@WebService(name = "WsAerodromi", targetNamespace = "http://ws.projekt.omilermat.nwtis.foi.org/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface WsAerodromi {


    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns java.util.List<org.foi.nwtis.omilermat.projekt.ws.WsAerodromi.endpoint.Aerodrom>
     * @throws PogresnaAutentikacija_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "dajAerodromeZaLetove", targetNamespace = "http://ws.projekt.omilermat.nwtis.foi.org/", className = "org.foi.nwtis.omilermat.projekt.ws.WsAerodromi.endpoint.DajAerodromeZaLetove")
    @ResponseWrapper(localName = "dajAerodromeZaLetoveResponse", targetNamespace = "http://ws.projekt.omilermat.nwtis.foi.org/", className = "org.foi.nwtis.omilermat.projekt.ws.WsAerodromi.endpoint.DajAerodromeZaLetoveResponse")
    @Action(input = "http://ws.projekt.omilermat.nwtis.foi.org/WsAerodromi/dajAerodromeZaLetoveRequest", output = "http://ws.projekt.omilermat.nwtis.foi.org/WsAerodromi/dajAerodromeZaLetoveResponse", fault = {
        @FaultAction(className = PogresnaAutentikacija_Exception.class, value = "http://ws.projekt.omilermat.nwtis.foi.org/WsAerodromi/dajAerodromeZaLetove/Fault/PogresnaAutentikacija")
    })
    public List<Aerodrom> dajAerodromeZaLetove(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1)
        throws PogresnaAutentikacija_Exception
    ;

    /**
     * 
     * @param arg2
     * @param arg1
     * @param arg0
     * @return
     *     returns boolean
     * @throws PogresnaAutentikacija_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "dodajAerodromZaLetove", targetNamespace = "http://ws.projekt.omilermat.nwtis.foi.org/", className = "org.foi.nwtis.omilermat.projekt.ws.WsAerodromi.endpoint.DodajAerodromZaLetove")
    @ResponseWrapper(localName = "dodajAerodromZaLetoveResponse", targetNamespace = "http://ws.projekt.omilermat.nwtis.foi.org/", className = "org.foi.nwtis.omilermat.projekt.ws.WsAerodromi.endpoint.DodajAerodromZaLetoveResponse")
    @Action(input = "http://ws.projekt.omilermat.nwtis.foi.org/WsAerodromi/dodajAerodromZaLetoveRequest", output = "http://ws.projekt.omilermat.nwtis.foi.org/WsAerodromi/dodajAerodromZaLetoveResponse", fault = {
        @FaultAction(className = PogresnaAutentikacija_Exception.class, value = "http://ws.projekt.omilermat.nwtis.foi.org/WsAerodromi/dodajAerodromZaLetove/Fault/PogresnaAutentikacija")
    })
    public boolean dodajAerodromZaLetove(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        String arg2)
        throws PogresnaAutentikacija_Exception
    ;

    /**
     * 
     * @param arg2
     * @param arg1
     * @param arg0
     * @return
     *     returns boolean
     * @throws PogresnaAutentikacija_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "pauzirajAerodromZaLetove", targetNamespace = "http://ws.projekt.omilermat.nwtis.foi.org/", className = "org.foi.nwtis.omilermat.projekt.ws.WsAerodromi.endpoint.PauzirajAerodromZaLetove")
    @ResponseWrapper(localName = "pauzirajAerodromZaLetoveResponse", targetNamespace = "http://ws.projekt.omilermat.nwtis.foi.org/", className = "org.foi.nwtis.omilermat.projekt.ws.WsAerodromi.endpoint.PauzirajAerodromZaLetoveResponse")
    @Action(input = "http://ws.projekt.omilermat.nwtis.foi.org/WsAerodromi/pauzirajAerodromZaLetoveRequest", output = "http://ws.projekt.omilermat.nwtis.foi.org/WsAerodromi/pauzirajAerodromZaLetoveResponse", fault = {
        @FaultAction(className = PogresnaAutentikacija_Exception.class, value = "http://ws.projekt.omilermat.nwtis.foi.org/WsAerodromi/pauzirajAerodromZaLetove/Fault/PogresnaAutentikacija")
    })
    public boolean pauzirajAerodromZaLetove(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        String arg2)
        throws PogresnaAutentikacija_Exception
    ;

    /**
     * 
     * @param arg2
     * @param arg1
     * @param arg0
     * @return
     *     returns boolean
     * @throws PogresnaAutentikacija_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "aktivirajAerodromZaLetove", targetNamespace = "http://ws.projekt.omilermat.nwtis.foi.org/", className = "org.foi.nwtis.omilermat.projekt.ws.WsAerodromi.endpoint.AktivirajAerodromZaLetove")
    @ResponseWrapper(localName = "aktivirajAerodromZaLetoveResponse", targetNamespace = "http://ws.projekt.omilermat.nwtis.foi.org/", className = "org.foi.nwtis.omilermat.projekt.ws.WsAerodromi.endpoint.AktivirajAerodromZaLetoveResponse")
    @Action(input = "http://ws.projekt.omilermat.nwtis.foi.org/WsAerodromi/aktivirajAerodromZaLetoveRequest", output = "http://ws.projekt.omilermat.nwtis.foi.org/WsAerodromi/aktivirajAerodromZaLetoveResponse", fault = {
        @FaultAction(className = PogresnaAutentikacija_Exception.class, value = "http://ws.projekt.omilermat.nwtis.foi.org/WsAerodromi/aktivirajAerodromZaLetove/Fault/PogresnaAutentikacija")
    })
    public boolean aktivirajAerodromZaLetove(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        String arg2)
        throws PogresnaAutentikacija_Exception
    ;

    /**
     * 
     * @return
     *     returns java.util.List<java.lang.String>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "dajAerodromeStatus", targetNamespace = "http://ws.projekt.omilermat.nwtis.foi.org/", className = "org.foi.nwtis.omilermat.projekt.ws.WsAerodromi.endpoint.DajAerodromeStatus")
    @ResponseWrapper(localName = "dajAerodromeStatusResponse", targetNamespace = "http://ws.projekt.omilermat.nwtis.foi.org/", className = "org.foi.nwtis.omilermat.projekt.ws.WsAerodromi.endpoint.DajAerodromeStatusResponse")
    @Action(input = "http://ws.projekt.omilermat.nwtis.foi.org/WsAerodromi/dajAerodromeStatusRequest", output = "http://ws.projekt.omilermat.nwtis.foi.org/WsAerodromi/dajAerodromeStatusResponse")
    public List<String> dajAerodromeStatus();

}