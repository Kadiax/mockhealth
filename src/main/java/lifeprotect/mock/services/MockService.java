package lifeprotect.mock.services;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class MockService {

    private WebClient webClient;
    private static final String API_MIME_TYPE = "application/json";
    private static final String API_BASE_URL = "http://localhost:8080/api";
    private static final String USER_AGENT = "Mock WebClient";

    public MockService() {
        this.webClient = WebClient.builder()
                .baseUrl(API_BASE_URL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, API_MIME_TYPE)
                .defaultHeader(HttpHeaders.USER_AGENT, USER_AGENT)
                .build();
    }



    public String sendMessage(String message) {
        return webClient.get()
                .uri("/mockHealth/message/"+message)
                .exchange()
                .flatMap(clientResponse -> clientResponse.bodyToMono(String.class)).block();
    }



}
