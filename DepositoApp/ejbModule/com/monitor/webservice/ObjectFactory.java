
package com.monitor.webservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.monitor.webservice package. 
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

    private final static QName _AgregarInforme_QNAME = new QName("http://webService.monitor.com/", "agregarInforme");
    private final static QName _AgregarInformeResponse_QNAME = new QName("http://webService.monitor.com/", "agregarInformeResponse");
    private final static QName _Log_QNAME = new QName("http://webService.monitor.com/", "log");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.monitor.webservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AgregarInforme }
     * 
     */
    public AgregarInforme createAgregarInforme() {
        return new AgregarInforme();
    }

    /**
     * Create an instance of {@link AgregarInformeResponse }
     * 
     */
    public AgregarInformeResponse createAgregarInformeResponse() {
        return new AgregarInformeResponse();
    }

    /**
     * Create an instance of {@link LogDTO }
     * 
     */
    public LogDTO createLogDTO() {
        return new LogDTO();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AgregarInforme }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webService.monitor.com/", name = "agregarInforme")
    public JAXBElement<AgregarInforme> createAgregarInforme(AgregarInforme value) {
        return new JAXBElement<AgregarInforme>(_AgregarInforme_QNAME, AgregarInforme.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AgregarInformeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webService.monitor.com/", name = "agregarInformeResponse")
    public JAXBElement<AgregarInformeResponse> createAgregarInformeResponse(AgregarInformeResponse value) {
        return new JAXBElement<AgregarInformeResponse>(_AgregarInformeResponse_QNAME, AgregarInformeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LogDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webService.monitor.com/", name = "log")
    public JAXBElement<LogDTO> createLog(LogDTO value) {
        return new JAXBElement<LogDTO>(_Log_QNAME, LogDTO.class, null, value);
    }

}
