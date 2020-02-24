package lifeprotect.mock.services;

import lifeprotect.mock.model.Person;
import org.reactivestreams.Subscriber;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
        /*webClient = WebClient.create("http://localhost:8080/api");
        result = webClient.get()
                .uri("/person/findAll")
                .accept(MediaType.APPLICATION_JSON)
                .exchange();*/
    }

    /*public String getResult() {
        return ">> result = " +
                result.flatMap(res -> res.bodyToMono(String.class)).block();
    }*/

    public Flux<Person> getAllPersons() {
        return webClient.get()
                .uri("/person/findAll")
                .exchange()
                .flatMapMany(clientResponse -> clientResponse.bodyToFlux(Person.class));
    }

    public Mono<ClientResponse> sendMessage() {
         return webClient.post()
                .uri("mockHealth/message")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue("HELLO WORLD"))
                .exchange();
    }


}
