package com.arani.restapiinteg.flow;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.security.channel.SecuredChannel;
import org.springframework.integration.transformer.ObjectToStringTransformer;
import org.springframework.messaging.MessageChannel;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class RestApis {

    @Bean
    public IntegrationFlow buildFlowForHttpPostRequest() {
        return IntegrationFlows.from("httpInboundPostRequestChannel")
                .wireTap(f -> f.handle(message -> log.debug("Http Post message received : " + message)))
                .transform(new ObjectToStringTransformer())
                .transform(message -> "Processed " + message)
                .channel("httpPostReplyChannel")
                .get();
    }

    @Bean
    @SecuredChannel(
            interceptor = "channelSecurityInterceptor",
            sendAccess = {"ROLE_USER"})
    public MessageChannel httpInboundPostRequestChannel()
    {
        //return MessageChannels.executor(Executors.newCachedThreadPool())
        //        .get();
        //return MessageChannels.direct().get();
    }

    @Bean
    public MessageChannel httpPostReplyChannel()
    {
        return MessageChannels.direct()
                .get();
    }
}
