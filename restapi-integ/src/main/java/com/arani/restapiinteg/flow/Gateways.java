package com.arani.restapiinteg.flow;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.integration.http.dsl.Http;
import org.springframework.integration.http.inbound.HttpRequestHandlingMessagingGateway;

@Configuration
public class Gateways {
    @Bean
    public HttpRequestHandlingMessagingGateway httpPostInboundGateway() {
        return Http.inboundGateway("/v1/request")
                .errorChannel("errorChannel")
                .requestChannel("httpInboundPostRequestChannel")
                .replyChannel("httpPostReplyChannel")
                .requestPayloadType(String.class)
                .requestMapping(m -> m.methods(HttpMethod.POST)
                        .consumes("application/json").produces("application/json"))
                .replyTimeout(5000L)
                .get();
    }
}
