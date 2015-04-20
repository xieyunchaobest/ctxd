/**
 * IM4MOSServicesSoapBindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.cattsoft.im.service.webservice.client;

public class IM4MOSServicesSoapBindingSkeleton implements com.cattsoft.im.service.webservice.client.IM4MOSServices_PortType, org.apache.axis.wsdl.Skeleton {
    private com.cattsoft.im.service.webservice.client.IM4MOSServices_PortType impl;
    private static java.util.Map _myOperations = new java.util.Hashtable();
    private static java.util.Collection _myOperationsList = new java.util.ArrayList();

    /**
    * Returns List of OperationDesc objects with this name
    */
    public static java.util.List getOperationDescByName(java.lang.String methodName) {
        return (java.util.List)_myOperations.get(methodName);
    }

    /**
    * Returns Collection of OperationDescs
    */
    public static java.util.Collection getOperationDescs() {
        return _myOperationsList;
    }

    static {
        org.apache.axis.description.OperationDesc _oper;
        org.apache.axis.description.FaultDesc _fault;
        org.apache.axis.description.ParameterDesc [] _params;
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "funCode"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "head"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "body"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("svcCallIOMByMosNative", _params, new javax.xml.namespace.QName("", "svcCallIOMByMosNativeReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("urn:sps", "svcCallIOMByMosNative"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("svcCallIOMByMosNative") == null) {
            _myOperations.put("svcCallIOMByMosNative", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("svcCallIOMByMosNative")).add(_oper);
    }

    public IM4MOSServicesSoapBindingSkeleton() {
        this.impl = new com.cattsoft.im.service.webservice.client.IM4MOSServicesSoapBindingImpl();
    }

    public IM4MOSServicesSoapBindingSkeleton(com.cattsoft.im.service.webservice.client.IM4MOSServices_PortType impl) {
        this.impl = impl;
    }
    public java.lang.String svcCallIOMByMosNative(java.lang.String funCode, java.lang.String head, java.lang.String body) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.svcCallIOMByMosNative(funCode, head, body);
        return ret;
    }

}
