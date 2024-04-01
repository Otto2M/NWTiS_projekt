
package org.foi.nwtis.omilermat.projekt.ws.WsAerodromi.endpoint;

import javax.xml.namespace.QName;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.foi.nwtis.omilermat.projekt.ws.WsAerodromi.endpoint package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _PogresnaAutentikacija_QNAME = new QName("http://ws.projekt.omilermat.nwtis.foi.org/", "PogresnaAutentikacija");
    private final static QName _AktivirajAerodromZaLetove_QNAME = new QName("http://ws.projekt.omilermat.nwtis.foi.org/", "aktivirajAerodromZaLetove");
    private final static QName _AktivirajAerodromZaLetoveResponse_QNAME = new QName("http://ws.projekt.omilermat.nwtis.foi.org/", "aktivirajAerodromZaLetoveResponse");
    private final static QName _DajAerodromeStatus_QNAME = new QName("http://ws.projekt.omilermat.nwtis.foi.org/", "dajAerodromeStatus");
    private final static QName _DajAerodromeStatusResponse_QNAME = new QName("http://ws.projekt.omilermat.nwtis.foi.org/", "dajAerodromeStatusResponse");
    private final static QName _DajAerodromeZaLetove_QNAME = new QName("http://ws.projekt.omilermat.nwtis.foi.org/", "dajAerodromeZaLetove");
    private final static QName _DajAerodromeZaLetoveResponse_QNAME = new QName("http://ws.projekt.omilermat.nwtis.foi.org/", "dajAerodromeZaLetoveResponse");
    private final static QName _DodajAerodromZaLetove_QNAME = new QName("http://ws.projekt.omilermat.nwtis.foi.org/", "dodajAerodromZaLetove");
    private final static QName _DodajAerodromZaLetoveResponse_QNAME = new QName("http://ws.projekt.omilermat.nwtis.foi.org/", "dodajAerodromZaLetoveResponse");
    private final static QName _PauzirajAerodromZaLetove_QNAME = new QName("http://ws.projekt.omilermat.nwtis.foi.org/", "pauzirajAerodromZaLetove");
    private final static QName _PauzirajAerodromZaLetoveResponse_QNAME = new QName("http://ws.projekt.omilermat.nwtis.foi.org/", "pauzirajAerodromZaLetoveResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.foi.nwtis.omilermat.projekt.ws.WsAerodromi.endpoint
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PogresnaAutentikacija }
     * 
     */
    public PogresnaAutentikacija createPogresnaAutentikacija() {
        return new PogresnaAutentikacija();
    }

    /**
     * Create an instance of {@link AktivirajAerodromZaLetove }
     * 
     */
    public AktivirajAerodromZaLetove createAktivirajAerodromZaLetove() {
        return new AktivirajAerodromZaLetove();
    }

    /**
     * Create an instance of {@link AktivirajAerodromZaLetoveResponse }
     * 
     */
    public AktivirajAerodromZaLetoveResponse createAktivirajAerodromZaLetoveResponse() {
        return new AktivirajAerodromZaLetoveResponse();
    }

    /**
     * Create an instance of {@link DajAerodromeStatus }
     * 
     */
    public DajAerodromeStatus createDajAerodromeStatus() {
        return new DajAerodromeStatus();
    }

    /**
     * Create an instance of {@link DajAerodromeStatusResponse }
     * 
     */
    public DajAerodromeStatusResponse createDajAerodromeStatusResponse() {
        return new DajAerodromeStatusResponse();
    }

    /**
     * Create an instance of {@link DajAerodromeZaLetove }
     * 
     */
    public DajAerodromeZaLetove createDajAerodromeZaLetove() {
        return new DajAerodromeZaLetove();
    }

    /**
     * Create an instance of {@link DajAerodromeZaLetoveResponse }
     * 
     */
    public DajAerodromeZaLetoveResponse createDajAerodromeZaLetoveResponse() {
        return new DajAerodromeZaLetoveResponse();
    }

    /**
     * Create an instance of {@link DodajAerodromZaLetove }
     * 
     */
    public DodajAerodromZaLetove createDodajAerodromZaLetove() {
        return new DodajAerodromZaLetove();
    }

    /**
     * Create an instance of {@link DodajAerodromZaLetoveResponse }
     * 
     */
    public DodajAerodromZaLetoveResponse createDodajAerodromZaLetoveResponse() {
        return new DodajAerodromZaLetoveResponse();
    }

    /**
     * Create an instance of {@link PauzirajAerodromZaLetove }
     * 
     */
    public PauzirajAerodromZaLetove createPauzirajAerodromZaLetove() {
        return new PauzirajAerodromZaLetove();
    }

    /**
     * Create an instance of {@link PauzirajAerodromZaLetoveResponse }
     * 
     */
    public PauzirajAerodromZaLetoveResponse createPauzirajAerodromZaLetoveResponse() {
        return new PauzirajAerodromZaLetoveResponse();
    }

    /**
     * Create an instance of {@link Aerodrom }
     * 
     */
    public Aerodrom createAerodrom() {
        return new Aerodrom();
    }

    /**
     * Create an instance of {@link Lokacija }
     * 
     */
    public Lokacija createLokacija() {
        return new Lokacija();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PogresnaAutentikacija }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link PogresnaAutentikacija }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.projekt.omilermat.nwtis.foi.org/", name = "PogresnaAutentikacija")
    public JAXBElement<PogresnaAutentikacija> createPogresnaAutentikacija(PogresnaAutentikacija value) {
        return new JAXBElement<PogresnaAutentikacija>(_PogresnaAutentikacija_QNAME, PogresnaAutentikacija.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AktivirajAerodromZaLetove }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AktivirajAerodromZaLetove }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.projekt.omilermat.nwtis.foi.org/", name = "aktivirajAerodromZaLetove")
    public JAXBElement<AktivirajAerodromZaLetove> createAktivirajAerodromZaLetove(AktivirajAerodromZaLetove value) {
        return new JAXBElement<AktivirajAerodromZaLetove>(_AktivirajAerodromZaLetove_QNAME, AktivirajAerodromZaLetove.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AktivirajAerodromZaLetoveResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AktivirajAerodromZaLetoveResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.projekt.omilermat.nwtis.foi.org/", name = "aktivirajAerodromZaLetoveResponse")
    public JAXBElement<AktivirajAerodromZaLetoveResponse> createAktivirajAerodromZaLetoveResponse(AktivirajAerodromZaLetoveResponse value) {
        return new JAXBElement<AktivirajAerodromZaLetoveResponse>(_AktivirajAerodromZaLetoveResponse_QNAME, AktivirajAerodromZaLetoveResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DajAerodromeStatus }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DajAerodromeStatus }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.projekt.omilermat.nwtis.foi.org/", name = "dajAerodromeStatus")
    public JAXBElement<DajAerodromeStatus> createDajAerodromeStatus(DajAerodromeStatus value) {
        return new JAXBElement<DajAerodromeStatus>(_DajAerodromeStatus_QNAME, DajAerodromeStatus.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DajAerodromeStatusResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DajAerodromeStatusResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.projekt.omilermat.nwtis.foi.org/", name = "dajAerodromeStatusResponse")
    public JAXBElement<DajAerodromeStatusResponse> createDajAerodromeStatusResponse(DajAerodromeStatusResponse value) {
        return new JAXBElement<DajAerodromeStatusResponse>(_DajAerodromeStatusResponse_QNAME, DajAerodromeStatusResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DajAerodromeZaLetove }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DajAerodromeZaLetove }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.projekt.omilermat.nwtis.foi.org/", name = "dajAerodromeZaLetove")
    public JAXBElement<DajAerodromeZaLetove> createDajAerodromeZaLetove(DajAerodromeZaLetove value) {
        return new JAXBElement<DajAerodromeZaLetove>(_DajAerodromeZaLetove_QNAME, DajAerodromeZaLetove.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DajAerodromeZaLetoveResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DajAerodromeZaLetoveResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.projekt.omilermat.nwtis.foi.org/", name = "dajAerodromeZaLetoveResponse")
    public JAXBElement<DajAerodromeZaLetoveResponse> createDajAerodromeZaLetoveResponse(DajAerodromeZaLetoveResponse value) {
        return new JAXBElement<DajAerodromeZaLetoveResponse>(_DajAerodromeZaLetoveResponse_QNAME, DajAerodromeZaLetoveResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DodajAerodromZaLetove }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DodajAerodromZaLetove }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.projekt.omilermat.nwtis.foi.org/", name = "dodajAerodromZaLetove")
    public JAXBElement<DodajAerodromZaLetove> createDodajAerodromZaLetove(DodajAerodromZaLetove value) {
        return new JAXBElement<DodajAerodromZaLetove>(_DodajAerodromZaLetove_QNAME, DodajAerodromZaLetove.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DodajAerodromZaLetoveResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DodajAerodromZaLetoveResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.projekt.omilermat.nwtis.foi.org/", name = "dodajAerodromZaLetoveResponse")
    public JAXBElement<DodajAerodromZaLetoveResponse> createDodajAerodromZaLetoveResponse(DodajAerodromZaLetoveResponse value) {
        return new JAXBElement<DodajAerodromZaLetoveResponse>(_DodajAerodromZaLetoveResponse_QNAME, DodajAerodromZaLetoveResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PauzirajAerodromZaLetove }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link PauzirajAerodromZaLetove }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.projekt.omilermat.nwtis.foi.org/", name = "pauzirajAerodromZaLetove")
    public JAXBElement<PauzirajAerodromZaLetove> createPauzirajAerodromZaLetove(PauzirajAerodromZaLetove value) {
        return new JAXBElement<PauzirajAerodromZaLetove>(_PauzirajAerodromZaLetove_QNAME, PauzirajAerodromZaLetove.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PauzirajAerodromZaLetoveResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link PauzirajAerodromZaLetoveResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.projekt.omilermat.nwtis.foi.org/", name = "pauzirajAerodromZaLetoveResponse")
    public JAXBElement<PauzirajAerodromZaLetoveResponse> createPauzirajAerodromZaLetoveResponse(PauzirajAerodromZaLetoveResponse value) {
        return new JAXBElement<PauzirajAerodromZaLetoveResponse>(_PauzirajAerodromZaLetoveResponse_QNAME, PauzirajAerodromZaLetoveResponse.class, null, value);
    }

}
