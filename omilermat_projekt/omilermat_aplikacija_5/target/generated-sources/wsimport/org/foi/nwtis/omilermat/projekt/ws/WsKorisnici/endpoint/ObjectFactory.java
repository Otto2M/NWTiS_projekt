
package org.foi.nwtis.omilermat.projekt.ws.WsKorisnici.endpoint;

import javax.xml.namespace.QName;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.foi.nwtis.omilermat.projekt.ws.WsKorisnici.endpoint package. 
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
    private final static QName _DajKorisnika_QNAME = new QName("http://ws.projekt.omilermat.nwtis.foi.org/", "dajKorisnika");
    private final static QName _DajKorisnikaResponse_QNAME = new QName("http://ws.projekt.omilermat.nwtis.foi.org/", "dajKorisnikaResponse");
    private final static QName _DajKorisnike_QNAME = new QName("http://ws.projekt.omilermat.nwtis.foi.org/", "dajKorisnike");
    private final static QName _DajKorisnikeResponse_QNAME = new QName("http://ws.projekt.omilermat.nwtis.foi.org/", "dajKorisnikeResponse");
    private final static QName _DodajKorisnika_QNAME = new QName("http://ws.projekt.omilermat.nwtis.foi.org/", "dodajKorisnika");
    private final static QName _DodajKorisnikaResponse_QNAME = new QName("http://ws.projekt.omilermat.nwtis.foi.org/", "dodajKorisnikaResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.foi.nwtis.omilermat.projekt.ws.WsKorisnici.endpoint
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
     * Create an instance of {@link DajKorisnika }
     * 
     */
    public DajKorisnika createDajKorisnika() {
        return new DajKorisnika();
    }

    /**
     * Create an instance of {@link DajKorisnikaResponse }
     * 
     */
    public DajKorisnikaResponse createDajKorisnikaResponse() {
        return new DajKorisnikaResponse();
    }

    /**
     * Create an instance of {@link DajKorisnike }
     * 
     */
    public DajKorisnike createDajKorisnike() {
        return new DajKorisnike();
    }

    /**
     * Create an instance of {@link DajKorisnikeResponse }
     * 
     */
    public DajKorisnikeResponse createDajKorisnikeResponse() {
        return new DajKorisnikeResponse();
    }

    /**
     * Create an instance of {@link DodajKorisnika }
     * 
     */
    public DodajKorisnika createDodajKorisnika() {
        return new DodajKorisnika();
    }

    /**
     * Create an instance of {@link DodajKorisnikaResponse }
     * 
     */
    public DodajKorisnikaResponse createDodajKorisnikaResponse() {
        return new DodajKorisnikaResponse();
    }

    /**
     * Create an instance of {@link Korisnik }
     * 
     */
    public Korisnik createKorisnik() {
        return new Korisnik();
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
     * Create an instance of {@link JAXBElement }{@code <}{@link DajKorisnika }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DajKorisnika }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.projekt.omilermat.nwtis.foi.org/", name = "dajKorisnika")
    public JAXBElement<DajKorisnika> createDajKorisnika(DajKorisnika value) {
        return new JAXBElement<DajKorisnika>(_DajKorisnika_QNAME, DajKorisnika.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DajKorisnikaResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DajKorisnikaResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.projekt.omilermat.nwtis.foi.org/", name = "dajKorisnikaResponse")
    public JAXBElement<DajKorisnikaResponse> createDajKorisnikaResponse(DajKorisnikaResponse value) {
        return new JAXBElement<DajKorisnikaResponse>(_DajKorisnikaResponse_QNAME, DajKorisnikaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DajKorisnike }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DajKorisnike }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.projekt.omilermat.nwtis.foi.org/", name = "dajKorisnike")
    public JAXBElement<DajKorisnike> createDajKorisnike(DajKorisnike value) {
        return new JAXBElement<DajKorisnike>(_DajKorisnike_QNAME, DajKorisnike.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DajKorisnikeResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DajKorisnikeResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.projekt.omilermat.nwtis.foi.org/", name = "dajKorisnikeResponse")
    public JAXBElement<DajKorisnikeResponse> createDajKorisnikeResponse(DajKorisnikeResponse value) {
        return new JAXBElement<DajKorisnikeResponse>(_DajKorisnikeResponse_QNAME, DajKorisnikeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DodajKorisnika }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DodajKorisnika }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.projekt.omilermat.nwtis.foi.org/", name = "dodajKorisnika")
    public JAXBElement<DodajKorisnika> createDodajKorisnika(DodajKorisnika value) {
        return new JAXBElement<DodajKorisnika>(_DodajKorisnika_QNAME, DodajKorisnika.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DodajKorisnikaResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DodajKorisnikaResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.projekt.omilermat.nwtis.foi.org/", name = "dodajKorisnikaResponse")
    public JAXBElement<DodajKorisnikaResponse> createDodajKorisnikaResponse(DodajKorisnikaResponse value) {
        return new JAXBElement<DodajKorisnikaResponse>(_DodajKorisnikaResponse_QNAME, DodajKorisnikaResponse.class, null, value);
    }

}
