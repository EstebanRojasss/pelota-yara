package com.forum.api.infra.adapter.out.api;

import com.forum.api.application.in.DataApiProvider;
import com.forum.api.application.in.dto.FixtureData;
import com.forum.api.application.in.dto.TeamDataDto;
import com.forum.api.infra.adapter.out.dto.FixtureWrapper;
import com.forum.api.infra.adapter.out.dto.TeamWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

@Component
public class ApiFootballConsumer implements DataApiProvider {

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
                "https://v3.football.api-sports.io/fixtures?live=all",
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
                filter(
                        filtrarLiga(List.of(250L, 130L, 128L, 1032L, 2L, 3L, 73L, 13L, 11L, 836L, 194L))
                ).toList();
    }

    @Override
    public List<TeamDataDto> proveerDatosEquipos() {
        HttpHeaders headers = new HttpHeaders();

        headers.set("x-apisports-key", apiKey);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<ApiResponse<TeamWrapper>> response = restTemplate.exchange(
                "https://v3.football.api-sports.io/teams?league=252&season=2024",
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<ApiResponse<TeamWrapper>>() {
                }
        );

        if (response.getBody() == null) {
            throw new IllegalStateException("Respuesta vacía de API FOOTBALL");
        }

        return response.
                getBody().
                getResponse().
                stream().
                map(TeamWrapper::map)
                .toList();
    }

    private Predicate<FixtureData> filtrarLiga(Collection<Long> idsPermitidos) {
        Set<Long> permitidos = new HashSet<>(idsPermitidos);
        return f -> f != null && permitidos.contains(f.ligaId());
    }
}
