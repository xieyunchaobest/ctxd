/**
 * RmsServiceForMosPortBindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.cattsoft.im.service.webservice.client;

public class RmsServiceForMosPortBindingSkeleton implements com.cattsoft.im.service.webservice.client.RmsServiceForMosDelegate, org.apache.axis.wsdl.Skeleton {
    private com.cattsoft.im.service.webservice.client.RmsServiceForMosDelegate impl;
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
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("woListInit", _params, new javax.xml.namespace.QName("", "return"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://mos.rms.cattsoft.com/", "woListInit"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("woListInit") == null) {
            _myOperations.put("woListInit", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("woListInit")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("resQuery", _params, new javax.xml.namespace.QName("", "return"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://mos.rms.cattsoft.com/", "resQuery"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("resQuery") == null) {
            _myOperations.put("resQuery", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("resQuery")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("resChange", _params, new javax.xml.namespace.QName("", "return"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://mos.rms.cattsoft.com/", "resChange"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("resChange") == null) {
            _myOperations.put("resChange", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("resChange")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("systemStatus", _params, new javax.xml.namespace.QName("", "return"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://mos.rms.cattsoft.com/", "systemStatus"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("systemStatus") == null) {
            _myOperations.put("systemStatus", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("systemStatus")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("portAndConnQuery", _params, new javax.xml.namespace.QName("", "return"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://mos.rms.cattsoft.com/", "portAndConnQuery"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("portAndConnQuery") == null) {
            _myOperations.put("portAndConnQuery", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("portAndConnQuery")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("rmsQuery", _params, new javax.xml.namespace.QName("", "return"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://mos.rms.cattsoft.com/", "rmsQuery"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("rmsQuery") == null) {
            _myOperations.put("rmsQuery", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("rmsQuery")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("workBook", _params, new javax.xml.namespace.QName("", "return"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://mos.rms.cattsoft.com/", "workBook"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("workBook") == null) {
            _myOperations.put("workBook", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("workBook")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("realPosition", _params, new javax.xml.namespace.QName("", "return"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://mos.rms.cattsoft.com/", "realPosition"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("realPosition") == null) {
            _myOperations.put("realPosition", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("realPosition")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("woOperate", _params, new javax.xml.namespace.QName("", "return"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://mos.rms.cattsoft.com/", "woOperate"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("woOperate") == null) {
            _myOperations.put("woOperate", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("woOperate")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("login", _params, new javax.xml.namespace.QName("", "return"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://mos.rms.cattsoft.com/", "login"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("login") == null) {
            _myOperations.put("login", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("login")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("deviceQuery", _params, new javax.xml.namespace.QName("", "return"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://mos.rms.cattsoft.com/", "deviceQuery"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("deviceQuery") == null) {
            _myOperations.put("deviceQuery", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("deviceQuery")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("failReasonQuery", _params, new javax.xml.namespace.QName("", "return"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://mos.rms.cattsoft.com/", "failReasonQuery"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("failReasonQuery") == null) {
            _myOperations.put("failReasonQuery", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("failReasonQuery")).add(_oper);
    }

    public RmsServiceForMosPortBindingSkeleton() {
        this.impl = new com.cattsoft.im.service.webservice.client.RmsServiceForMosPortBindingImpl();
    }

    public RmsServiceForMosPortBindingSkeleton(com.cattsoft.im.service.webservice.client.RmsServiceForMosDelegate impl) {
        this.impl = impl;
    }
    public java.lang.String woListInit(java.lang.String arg0) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.woListInit(arg0);
        return ret;
    }

    public java.lang.String resQuery(java.lang.String arg0) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.resQuery(arg0);
        return ret;
    }

    public java.lang.String resChange(java.lang.String arg0) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.resChange(arg0);
        return ret;
    }

    public java.lang.String systemStatus(java.lang.String arg0) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.systemStatus(arg0);
        return ret;
    }

    public java.lang.String portAndConnQuery(java.lang.String arg0) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.portAndConnQuery(arg0);
        return ret;
    }

    public java.lang.String rmsQuery(java.lang.String arg0) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.rmsQuery(arg0);
        return ret;
    }

    public java.lang.String workBook(java.lang.String arg0) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.workBook(arg0);
        return ret;
    }

    public java.lang.String realPosition(java.lang.String arg0) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.realPosition(arg0);
        return ret;
    }

    public java.lang.String woOperate(java.lang.String arg0) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.woOperate(arg0);
        return ret;
    }

    public java.lang.String login(java.lang.String arg0) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.login(arg0);
        return ret;
    }

    public java.lang.String deviceQuery(java.lang.String arg0) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.deviceQuery(arg0);
        return ret;
    }

    public java.lang.String failReasonQuery(java.lang.String arg0) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.failReasonQuery(arg0);
        return ret;
    }

}
