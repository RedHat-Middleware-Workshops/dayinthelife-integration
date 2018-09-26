
package com.redhat;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.redhat package. 
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

    private final static QName _Phone_QNAME = new QName("http://redhat.com/", "phone");
    private final static QName _PhoneResponse_QNAME = new QName("http://redhat.com/", "phoneResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.redhat
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Phone }
     * 
     */
    public Phone createPhone() {
        return new Phone();
    }

    /**
     * Create an instance of {@link PhoneResponse }
     * 
     */
    public PhoneResponse createPhoneResponse() {
        return new PhoneResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Phone }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://redhat.com/", name = "phone")
    public JAXBElement<Phone> createPhone(Phone value) {
        return new JAXBElement<Phone>(_Phone_QNAME, Phone.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PhoneResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://redhat.com/", name = "phoneResponse")
    public JAXBElement<PhoneResponse> createPhoneResponse(PhoneResponse value) {
        return new JAXBElement<PhoneResponse>(_PhoneResponse_QNAME, PhoneResponse.class, null, value);
    }

}
