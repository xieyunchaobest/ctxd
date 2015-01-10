/**
 * IM4MOSServicesServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.cattsoft.im.service.webservice.client;

public class IM4MOSServicesServiceLocator extends org.apache.axis.client.Service implements com.cattsoft.im.service.webservice.client.IM4MOSServicesService {

    public IM4MOSServicesServiceLocator() {
    }


    public IM4MOSServicesServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public IM4MOSServicesServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for IM4MOSServices
    private java.lang.String IM4MOSServices_address = "http://localhost:7001/web/services/IM4MOSServices";

    public java.lang.String getIM4MOSServicesAddress() {
        return IM4MOSServices_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String IM4MOSServicesWSDDServiceName = "IM4MOSServices";

    public java.lang.String getIM4MOSServicesWSDDServiceName() {
        return IM4MOSServicesWSDDServiceName;
    }

    public void setIM4MOSServicesWSDDServiceName(java.lang.String name) {
        IM4MOSServicesWSDDServiceName = name;
    }

    public com.cattsoft.im.service.webservice.client.IM4MOSServices_PortType getIM4MOSServices() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(IM4MOSServices_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getIM4MOSServices(endpoint);
    }

    public com.cattsoft.im.service.webservice.client.IM4MOSServices_PortType getIM4MOSServices(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.cattsoft.im.service.webservice.client.IM4MOSServicesSoapBindingStub _stub = new com.cattsoft.im.service.webservice.client.IM4MOSServicesSoapBindingStub(portAddress, this);
            _stub.setPortName(getIM4MOSServicesWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setIM4MOSServicesEndpointAddress(java.lang.String address) {
        IM4MOSServices_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.cattsoft.im.service.webservice.client.IM4MOSServices_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.cattsoft.im.service.webservice.client.IM4MOSServicesSoapBindingStub _stub = new com.cattsoft.im.service.webservice.client.IM4MOSServicesSoapBindingStub(new java.net.URL(IM4MOSServices_address), this);
                _stub.setPortName(getIM4MOSServicesWSDDServiceName());
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
        if ("IM4MOSServices".equals(inputPortName)) {
            return getIM4MOSServices();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("urn:sps", "IM4MOSServicesService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("urn:sps", "IM4MOSServices"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("IM4MOSServices".equals(portName)) {
            setIM4MOSServicesEndpointAddress(address);
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
