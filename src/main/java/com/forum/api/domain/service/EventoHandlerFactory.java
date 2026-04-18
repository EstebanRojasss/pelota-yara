package com.forum.api.domain.service;

import com.forum.api.domain.evento.EventoHandler;
import com.forum.api.domain.exception.EventoNotFoundException;

import java.util.HashMap;
import java.util.Map;

public class EventoHandlerFactory {

    private final Map<String, EventoHandler> categorias = new HashMap<>();


    public EventoHandler getHandler(String type, String detail ){
        String key = generarKey(type, detail);

        EventoHandler handler = categorias.get(key);

        if(handler == null){
            throw new EventoNotFoundException("No handler para: " + key);
        }

        return handler;
    }


    private String generarKey(String type, String detail){
        return (type + ":" + detail).trim();
    }

    public void registrar(String type, String detail, EventoHandler handler){
        String key = generarKey(type, detail);
        categorias.put(key, handler);
    }
}
