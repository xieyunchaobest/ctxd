/**
 * IMos2RmsServiceSoapBindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.cattsoft.im.service.webservice.client;

public class IMos2RmsServiceSoapBindingSkeleton implements com.cattsoft.im.service.webservice.client.IMos2Rms, org.apache.axis.wsdl.Skeleton {
    private com.cattsoft.im.service.webservice.client.IMos2Rms impl;
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
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "funCode"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "xmlHeader"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "xmlString"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("mos2Rms", _params, new javax.xml.namespace.QName("", "return"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://mos.rms.cattsoft.com/", "mos2rms"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("mos2Rms") == null) {
            _myOperations.put("mos2Rms", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("mos2Rms")).add(_oper);
    }

    public IMos2RmsServiceSoapBindingSkeleton() {
        this.impl = new com.cattsoft.im.service.webservice.client.IMos2RmsServiceSoapBindingImpl();
    }

    public IMos2RmsServiceSoapBindingSkeleton(com.cattsoft.im.service.webservice.client.IMos2Rms impl) {
        this.impl = impl;
    }
    public java.lang.String mos2Rms(java.lang.String funCode, java.lang.String xmlHeader, java.lang.String xmlString) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.mos2Rms(funCode, xmlHeader, xmlString);
        return ret;
    }

}
