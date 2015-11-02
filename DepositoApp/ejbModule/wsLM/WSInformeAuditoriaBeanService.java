package wsLM;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.0.5
 * 2015-11-01T20:52:31.651-03:00
 * Generated source version: 3.0.5
 * 
 */
@WebServiceClient(name = "WSInformeAuditoriaBeanService", 
                  wsdlLocation = "file:/C:/Users/Franco/Desktop/IA/IA/workspace/DepositoApp/Varios/monitor.wsdl",
                  targetNamespace = "http://webService.monitor.com/") 
public class WSInformeAuditoriaBeanService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://webService.monitor.com/", "WSInformeAuditoriaBeanService");
    public final static QName WSInformeAuditoriaBeanPort = new QName("http://webService.monitor.com/", "WSInformeAuditoriaBeanPort");
    static {
        URL url = null;
        try {
            url = new URL("file:/C:/Users/Franco/Desktop/IA/IA/workspace/DepositoApp/Varios/monitor.wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(WSInformeAuditoriaBeanService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "file:/C:/Users/Franco/Desktop/IA/IA/workspace/DepositoApp/Varios/monitor.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public WSInformeAuditoriaBeanService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public WSInformeAuditoriaBeanService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public WSInformeAuditoriaBeanService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public WSInformeAuditoriaBeanService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public WSInformeAuditoriaBeanService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public WSInformeAuditoriaBeanService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }    

    /**
     *
     * @return
     *     returns WSInformeAuditoriaBean
     */
    @WebEndpoint(name = "WSInformeAuditoriaBeanPort")
    public WSInformeAuditoriaBean getWSInformeAuditoriaBeanPort() {
        return super.getPort(WSInformeAuditoriaBeanPort, WSInformeAuditoriaBean.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns WSInformeAuditoriaBean
     */
    @WebEndpoint(name = "WSInformeAuditoriaBeanPort")
    public WSInformeAuditoriaBean getWSInformeAuditoriaBeanPort(WebServiceFeature... features) {
        return super.getPort(WSInformeAuditoriaBeanPort, WSInformeAuditoriaBean.class, features);
    }

}
