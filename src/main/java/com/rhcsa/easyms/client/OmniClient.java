package com.rhcsa.easyms.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import com.rhcsa.easyms.model.Tutorial;

@Component
public class OmniClient {

    @Value("${app.server.backend.host}")
    private String host;

    private WebClient client = WebClient.create();

    public Mono<Tutorial> getOmniData(String id){
       return client.get()
                .uri(this.host + "/api/tutorials/{id}", id)
                .retrieve()
                .bodyToMono(Tutorial.class).log(" Data fetched ");
    }

    public Mono<Tutorial> createOmniData(Tutorial data){
        Mono<Tutorial> userMono = Mono.just(data);
        return client.post().uri(this.host + "/api/tutorials").contentType(MediaType.APPLICATION_JSON)
                .body(userMono,Tutorial.class).retrieve().bodyToMono(Tutorial.class).log("Data created  : ");

    }


}
