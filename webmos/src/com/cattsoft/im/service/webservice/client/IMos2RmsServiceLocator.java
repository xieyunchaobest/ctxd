/**
 * IMos2RmsServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.cattsoft.im.service.webservice.client;

public class IMos2RmsServiceLocator extends org.apache.axis.client.Service implements com.cattsoft.im.service.webservice.client.IMos2RmsService {

    public IMos2RmsServiceLocator() {
    }


    public IMos2RmsServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public IMos2RmsServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for IMos2RmsPort
    private java.lang.String IMos2RmsPort_address = "http://192.168.100.39:9009/trms/services/Mos2RmsService";

    public java.lang.String getIMos2RmsPortAddress() {
        return IMos2RmsPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String IMos2RmsPortWSDDServiceName = "IMos2RmsPort";

    public java.lang.String getIMos2RmsPortWSDDServiceName() {
        return IMos2RmsPortWSDDServiceName;
    }

    public void setIMos2RmsPortWSDDServiceName(java.lang.String name) {
        IMos2RmsPortWSDDServiceName = name;
    }

    public com.cattsoft.im.service.webservice.client.IMos2Rms getIMos2RmsPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(IMos2RmsPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getIMos2RmsPort(endpoint);
    }

    public com.cattsoft.im.service.webservice.client.IMos2Rms getIMos2RmsPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.cattsoft.im.service.webservice.client.IMos2RmsServiceSoapBindingStub _stub = new com.cattsoft.im.service.webservice.client.IMos2RmsServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getIMos2RmsPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setIMos2RmsPortEndpointAddress(java.lang.String address) {
        IMos2RmsPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.cattsoft.im.service.webservice.client.IMos2Rms.class.isAssignableFrom(serviceEndpointInterface)) {
                com.cattsoft.im.service.webservice.client.IMos2RmsServiceSoapBindingStub _stub = new com.cattsoft.im.service.webservice.client.IMos2RmsServiceSoapBindingStub(new java.net.URL(IMos2RmsPort_address), this);
                _stub.setPortName(getIMos2RmsPortWSDDServiceName());
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
        if ("IMos2RmsPort".equals(inputPortName)) {
            return getIMos2RmsPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://mos.rms.cattsoft.com/", "IMos2RmsService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://mos.rms.cattsoft.com/", "IMos2RmsPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("IMos2RmsPort".equals(portName)) {
            setIMos2RmsPortEndpointAddress(address);
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
