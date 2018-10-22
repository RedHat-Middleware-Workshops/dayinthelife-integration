package com.redhat;

import java.net.Socket;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLEngine;
import javax.net.ssl.X509ExtendedTrustManager;

public  class InsecureX509TrustManager extends X509ExtendedTrustManager {
    @Override
    public void checkClientTrusted(X509Certificate[] x509Certificates, String s, Socket socket) throws CertificateException {
        //Do nothing

    }

    @Override
    public void checkServerTrusted(X509Certificate[] x509Certificates, String s, Socket socket) throws CertificateException {
        //Do nothing

    }

    @Override
    public void checkClientTrusted(X509Certificate[] x509Certificates, String s, SSLEngine sslEngine) throws CertificateException {
        //Do nothing

    }

    @Override
    public void checkServerTrusted(X509Certificate[] x509Certificates, String s, SSLEngine sslEngine) throws CertificateException {
        //Do nothing

    }

    @Override
    public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
        //Do nothing

    }

    @Override
    public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
        //Do nothing

    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return new X509Certificate[0];
    }
}