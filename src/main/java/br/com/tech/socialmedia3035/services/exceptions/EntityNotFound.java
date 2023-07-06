package br.com.tech.socialmedia3035.services.exceptions;

import java.io.Serial;

public class EntityNotFound extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public EntityNotFound(String msg){
        super(msg);
    }
}
