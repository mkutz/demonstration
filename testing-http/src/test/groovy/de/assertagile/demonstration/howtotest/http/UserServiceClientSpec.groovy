package de.assertagile.demonstration.howtotest.http

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import spock.lang.Specification

import static com.github.tomakehurst.wiremock.client.WireMock.*
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig

class UserServiceClientSpec extends Specification {

    WireMockServer wireMockServer = new WireMockServer(wireMockConfig().port(8888))
    WireMock wireMock = new WireMock("localhost", wireMockServer.port)

    UserServiceClient userServiceClient = new UserServiceClient("http://localhost:${wireMockServer.port}/user/")

    def setup() {
        wireMockServer.start()
    }

    def cleanup() {
        wireMockServer.stop()
    }

    def "Requesting a user name by ID"() {
        given:
        String userId = UUID.randomUUID().toString()
        String expectedUserName = "bob"

        and:
        wireMock.register(get(urlEqualTo("/user/${userId}"))
                .willReturn(aResponse()
                .withStatus(200)
                .withBody(expectedUserName)
        ))

        when:
        String userName = userServiceClient.getUserNameById(userId)

        then:
        userName == expectedUserName
    }
}