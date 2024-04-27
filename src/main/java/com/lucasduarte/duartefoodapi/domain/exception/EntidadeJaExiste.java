package com.lucasduarte.duartefoodapi.domain.exception;

public class EntidadeJaExiste extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public EntidadeJaExiste(String mensagem) {
        super(mensagem);
    }
}
