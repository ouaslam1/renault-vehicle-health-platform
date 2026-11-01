package com.renault.vehicle_analysis_service.config;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WireMockConfig {
    @Bean(initMethod = "start", destroyMethod = "stop")
    public WireMockServer wireMockServer(){
        WireMockServer wireMockServer = new WireMockServer(8082);
        wireMockServer.stubFor(
                WireMock.get(
                        WireMock.urlMatching("/recall/.*"))
                .willReturn(
                        WireMock.aResponse()
                        .withStatus(200)
                                .withHeader("Content-Type", "application/json")
                                .withBody("""
                                        {
                                          "hasRecallCampaign": true
                                        }
                                        """)));


        return wireMockServer;
    }
}
