/**
 * RmsServiceForMosLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.cattsoft.im.service.webservice.client;

public class RmsServiceForMosLocator extends org.apache.axis.client.Service implements com.cattsoft.im.service.webservice.client.RmsServiceForMos {

    public RmsServiceForMosLocator() {
    }


    public RmsServiceForMosLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public RmsServiceForMosLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for RmsServiceForMosPort
    private java.lang.String RmsServiceForMosPort_address = "http://192.168.100.233:3991/trms/services/RmsServiceForMosPort";

    public java.lang.String getRmsServiceForMosPortAddress() {
        return RmsServiceForMosPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String RmsServiceForMosPortWSDDServiceName = "RmsServiceForMosPort";

    public java.lang.String getRmsServiceForMosPortWSDDServiceName() {
        return RmsServiceForMosPortWSDDServiceName;
    }

    public void setRmsServiceForMosPortWSDDServiceName(java.lang.String name) {
        RmsServiceForMosPortWSDDServiceName = name;
    }

    public com.cattsoft.im.service.webservice.client.RmsServiceForMosDelegate getRmsServiceForMosPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(RmsServiceForMosPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getRmsServiceForMosPort(endpoint);
    }

    public com.cattsoft.im.service.webservice.client.RmsServiceForMosDelegate getRmsServiceForMosPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.cattsoft.im.service.webservice.client.RmsServiceForMosPortBindingStub _stub = new com.cattsoft.im.service.webservice.client.RmsServiceForMosPortBindingStub(portAddress, this);
            _stub.setPortName(getRmsServiceForMosPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setRmsServiceForMosPortEndpointAddress(java.lang.String address) {
        RmsServiceForMosPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.cattsoft.im.service.webservice.client.RmsServiceForMosDelegate.class.isAssignableFrom(serviceEndpointInterface)) {
                com.cattsoft.im.service.webservice.client.RmsServiceForMosPortBindingStub _stub = new com.cattsoft.im.service.webservice.client.RmsServiceForMosPortBindingStub(new java.net.URL(RmsServiceForMosPort_address), this);
                _stub.setPortName(getRmsServiceForMosPortWSDDServiceName());
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
        if ("RmsServiceForMosPort".equals(inputPortName)) {
            return getRmsServiceForMosPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://mos.rms.cattsoft.com/", "RmsServiceForMos");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://mos.rms.cattsoft.com/", "RmsServiceForMosPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("RmsServiceForMosPort".equals(portName)) {
            setRmsServiceForMosPortEndpointAddress(address);
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
