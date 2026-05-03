package com.forum.api.infra.adapter.stream;

import com.forum.api.application.in.SSeBroadcastUseCase;
import com.forum.api.application.in.SSeRegistrarUseCase;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class SSeService implements SSeRegistrarUseCase, SSeBroadcastUseCase {

    private final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    @Override
    public SseEmitter registrar() {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        emitters.add(emitter);

        emitter.onCompletion(() -> emitters.remove(emitter));
        emitter.onTimeout(() -> emitters.remove(emitter));
        emitter.onError(e -> emitters.remove(emitter));

        return emitter;
    }

    @Override
    public void broadcast(Object data) {
        List<SseEmitter> muertos = new ArrayList<>();
        for (SseEmitter emitter : emitters) {
            try {
                emitter.send(SseEmitter.event()
                        .name("partidos")
                        .data(data, MediaType.APPLICATION_JSON));
            } catch (Exception e) {
                muertos.add(emitter);
            }
        }
        emitters.removeAll(muertos);
    }

}
