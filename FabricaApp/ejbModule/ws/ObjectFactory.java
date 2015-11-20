
package ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ws package. 
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

    private final static QName _RecibirPedido_QNAME = new QName("http://ws/", "recibirPedido");
    private final static QName _RecibirPedidoResponse_QNAME = new QName("http://ws/", "recibirPedidoResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RecibirPedido }
     * 
     */
    public RecibirPedido createRecibirPedido() {
        return new RecibirPedido();
    }

    /**
     * Create an instance of {@link RecibirPedidoResponse }
     * 
     */
    public RecibirPedidoResponse createRecibirPedidoResponse() {
        return new RecibirPedidoResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RecibirPedido }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws/", name = "recibirPedido")
    public JAXBElement<RecibirPedido> createRecibirPedido(RecibirPedido value) {
        return new JAXBElement<RecibirPedido>(_RecibirPedido_QNAME, RecibirPedido.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RecibirPedidoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws/", name = "recibirPedidoResponse")
    public JAXBElement<RecibirPedidoResponse> createRecibirPedidoResponse(RecibirPedidoResponse value) {
        return new JAXBElement<RecibirPedidoResponse>(_RecibirPedidoResponse_QNAME, RecibirPedidoResponse.class, null, value);
    }

}
