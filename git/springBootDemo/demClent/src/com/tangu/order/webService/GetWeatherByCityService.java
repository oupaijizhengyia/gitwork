/**
 * GetWeatherByCityService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.tangu.order.webService;

public interface GetWeatherByCityService extends javax.xml.rpc.Service {
    public java.lang.String getweatherPortAddress();

    public com.tangu.order.webService.Weather getweatherPort() throws javax.xml.rpc.ServiceException;

    public com.tangu.order.webService.Weather getweatherPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
