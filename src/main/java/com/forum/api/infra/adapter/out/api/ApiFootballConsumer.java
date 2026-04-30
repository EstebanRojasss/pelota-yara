package com.forum.api.infra.adapter.out.api;

import com.forum.api.application.in.DataFixtureProvider;
import com.forum.api.application.in.dto.FixtureData;
import com.forum.api.infra.adapter.out.dto.FixtureWrapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ApiFootballConsumer implements DataFixtureProvider {

    private final RestTemplate restTemplate;

    @Value("${API_FOOTBALL_KEY:}")
    private String apiKey;

    public ApiFootballConsumer(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<FixtureData> proveerDatosFixture() {


        HttpHeaders headers = new HttpHeaders();
        headers.set("x-apisports-key", apiKey);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<ApiResponse<FixtureWrapper>> response = restTemplate.exchange(
                "https://v3.football.api-sports.io/fixtures?live=all&league=13",
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<ApiResponse<FixtureWrapper>>() {
                }
        );

        ApiResponse<FixtureWrapper> body = response.getBody();

        if (body == null) {
            throw new IllegalStateException("Respuesta vacía de API_FOOTBALL");
        }
        return body.getResponse().
                stream().
                map(FixtureWrapper::map).
                collect(Collectors.toList());
    }
}
