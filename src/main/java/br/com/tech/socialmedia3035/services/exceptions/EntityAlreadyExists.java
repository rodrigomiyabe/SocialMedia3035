package br.com.tech.socialmedia3035.services.exceptions;

import java.io.Serial;

public class EntityAlreadyExists extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;
    public EntityAlreadyExists(String msg){
        super(msg);
    }
}
