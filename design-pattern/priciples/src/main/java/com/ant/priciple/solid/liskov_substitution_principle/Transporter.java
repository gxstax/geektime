package com.ant.priciple.solid.liskov_substitution_principle;

import com.sun.tools.internal.ws.processor.model.Response;
import org.apache.http.client.HttpClient;

/**
 * <p>
 * 功能描述
 * </p>
 *
 * @author Ant
 * @since 2020/2/28 10:14 下午
 */

public class Transporter {
    private HttpClient httpClient;

    public Transporter(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public Response sendRequest(Request request) {
        // ...use httpClient to send request
        return null;
    }
}







