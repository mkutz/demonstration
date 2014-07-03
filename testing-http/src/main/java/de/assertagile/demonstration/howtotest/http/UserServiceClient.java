package de.assertagile.demonstration.howtotest.http;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

import java.io.IOException;
import java.net.URI;
import java.net.URL;

/**
 * Created by mkutz on 02.07.14.
 */
public class UserServiceClient {

    private HttpClient httpClient = new HttpClient();
    private String serviceUrl;

    public UserServiceClient(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    public String getUserNameById(String id) throws ServiceUnavailableException {
        GetMethod get = new GetMethod(serviceUrl + id);

        try {
            int status = httpClient.executeMethod(get);
            if (status == 200) {
                return get.getResponseBodyAsString();
            } else {
                throw new ServiceUnavailableException("Getting user with ID " + id + " failed with status " + status + "!");
            }
        } catch (IOException e) {
            throw new ServiceUnavailableException("Service not available at " + serviceUrl + "!");
        }
    }

    private class ServiceUnavailableException extends Throwable {
        public ServiceUnavailableException(String message) {
            super(message);
        }
    }
}
