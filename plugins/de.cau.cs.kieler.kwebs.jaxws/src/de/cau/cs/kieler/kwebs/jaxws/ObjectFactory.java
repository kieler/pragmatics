
package de.cau.cs.kieler.kwebs.jaxws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

import de.cau.cs.kieler.kwebs.GraphLayoutOption;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the de.cau.cs.kieler.kwebs.jaxws package. 
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

    private final static QName _ServiceFault_QNAME = new QName("http://rtsys.informatik.uni-kiel.de/layout", "ServiceFault");
    private final static QName _GetServiceData_QNAME = new QName("http://rtsys.informatik.uni-kiel.de/layout", "getServiceData");
    private final static QName _GetServiceDataResponse_QNAME = new QName("http://rtsys.informatik.uni-kiel.de/layout", "getServiceDataResponse");
    private final static QName _GetPreviewImage_QNAME = new QName("http://rtsys.informatik.uni-kiel.de/layout", "getPreviewImage");
    private final static QName _GraphLayoutResponse_QNAME = new QName("http://rtsys.informatik.uni-kiel.de/layout", "graphLayoutResponse");
    private final static QName _GraphLayout_QNAME = new QName("http://rtsys.informatik.uni-kiel.de/layout", "graphLayout");
    private final static QName _GetPreviewImageResponse_QNAME = new QName("http://rtsys.informatik.uni-kiel.de/layout", "getPreviewImageResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: de.cau.cs.kieler.kwebs.jaxws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetServiceDataResponse }
     * 
     */
    public GetServiceDataResponse createGetServiceDataResponse() {
        return new GetServiceDataResponse();
    }

    /**
     * Create an instance of {@link GetServiceData }
     * 
     */
    public GetServiceData createGetServiceData() {
        return new GetServiceData();
    }

    /**
     * Create an instance of {@link GetPreviewImage }
     * 
     */
    public GetPreviewImage createGetPreviewImage() {
        return new GetPreviewImage();
    }

    /**
     * Create an instance of {@link GraphLayoutOption }
     * 
     */
    public GraphLayoutOption createGraphLayoutOption() {
        return new GraphLayoutOption();
    }

    /**
     * Create an instance of {@link GetPreviewImageResponse }
     * 
     */
    public GetPreviewImageResponse createGetPreviewImageResponse() {
        return new GetPreviewImageResponse();
    }

    /**
     * Create an instance of {@link GraphLayoutResponse }
     * 
     */
    public GraphLayoutResponse createGraphLayoutResponse() {
        return new GraphLayoutResponse();
    }

    /**
     * Create an instance of {@link GraphLayout }
     * 
     */
    public GraphLayout createGraphLayout() {
        return new GraphLayout();
    }

    /**
     * Create an instance of {@link ServiceFault }
     * 
     */
    public ServiceFault createServiceFault() {
        return new ServiceFault();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ServiceFault }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rtsys.informatik.uni-kiel.de/layout", name = "ServiceFault")
    public JAXBElement<ServiceFault> createServiceFault(ServiceFault value) {
        return new JAXBElement<ServiceFault>(_ServiceFault_QNAME, ServiceFault.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetServiceData }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rtsys.informatik.uni-kiel.de/layout", name = "getServiceData")
    public JAXBElement<GetServiceData> createGetServiceData(GetServiceData value) {
        return new JAXBElement<GetServiceData>(_GetServiceData_QNAME, GetServiceData.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetServiceDataResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rtsys.informatik.uni-kiel.de/layout", name = "getServiceDataResponse")
    public JAXBElement<GetServiceDataResponse> createGetServiceDataResponse(GetServiceDataResponse value) {
        return new JAXBElement<GetServiceDataResponse>(_GetServiceDataResponse_QNAME, GetServiceDataResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPreviewImage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rtsys.informatik.uni-kiel.de/layout", name = "getPreviewImage")
    public JAXBElement<GetPreviewImage> createGetPreviewImage(GetPreviewImage value) {
        return new JAXBElement<GetPreviewImage>(_GetPreviewImage_QNAME, GetPreviewImage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GraphLayoutResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rtsys.informatik.uni-kiel.de/layout", name = "graphLayoutResponse")
    public JAXBElement<GraphLayoutResponse> createGraphLayoutResponse(GraphLayoutResponse value) {
        return new JAXBElement<GraphLayoutResponse>(_GraphLayoutResponse_QNAME, GraphLayoutResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GraphLayout }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rtsys.informatik.uni-kiel.de/layout", name = "graphLayout")
    public JAXBElement<GraphLayout> createGraphLayout(GraphLayout value) {
        return new JAXBElement<GraphLayout>(_GraphLayout_QNAME, GraphLayout.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPreviewImageResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rtsys.informatik.uni-kiel.de/layout", name = "getPreviewImageResponse")
    public JAXBElement<GetPreviewImageResponse> createGetPreviewImageResponse(GetPreviewImageResponse value) {
        return new JAXBElement<GetPreviewImageResponse>(_GetPreviewImageResponse_QNAME, GetPreviewImageResponse.class, null, value);
    }

}
