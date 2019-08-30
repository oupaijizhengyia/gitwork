/**
 * GetWeatherByCityServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package mypackage;

public class GetWeatherByCityServiceLocator extends org.apache.axis.client.Service implements mypackage.GetWeatherByCityService {

    public GetWeatherByCityServiceLocator() {
    }


    public GetWeatherByCityServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public GetWeatherByCityServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for GetWeatherByCityPort
    private java.lang.String GetWeatherByCityPort_address = "http://localhost:9000/getWeatherByCity";

    public java.lang.String getGetWeatherByCityPortAddress() {
        return GetWeatherByCityPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String GetWeatherByCityPortWSDDServiceName = "GetWeatherByCityPort";

    public java.lang.String getGetWeatherByCityPortWSDDServiceName() {
        return GetWeatherByCityPortWSDDServiceName;
    }

    public void setGetWeatherByCityPortWSDDServiceName(java.lang.String name) {
        GetWeatherByCityPortWSDDServiceName = name;
    }

    public mypackage.GetWeatherByCity getGetWeatherByCityPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(GetWeatherByCityPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getGetWeatherByCityPort(endpoint);
    }

    public mypackage.GetWeatherByCity getGetWeatherByCityPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            mypackage.GetWeatherByCityPortBindingStub _stub = new mypackage.GetWeatherByCityPortBindingStub(portAddress, this);
            _stub.setPortName(getGetWeatherByCityPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setGetWeatherByCityPortEndpointAddress(java.lang.String address) {
        GetWeatherByCityPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (mypackage.GetWeatherByCity.class.isAssignableFrom(serviceEndpointInterface)) {
                mypackage.GetWeatherByCityPortBindingStub _stub = new mypackage.GetWeatherByCityPortBindingStub(new java.net.URL(GetWeatherByCityPort_address), this);
                _stub.setPortName(getGetWeatherByCityPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("GetWeatherByCityPort".equals(inputPortName)) {
            return getGetWeatherByCityPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://example/", "GetWeatherByCityService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://example/", "GetWeatherByCityPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("GetWeatherByCityPort".equals(portName)) {
            setGetWeatherByCityPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
