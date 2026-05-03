package com.forum.api.application.in;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface SSeRegistrarUseCase {
    SseEmitter registrar();
}
