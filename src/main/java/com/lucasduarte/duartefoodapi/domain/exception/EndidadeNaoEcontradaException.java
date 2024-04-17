package com.lucasduarte.duartefoodapi.domain.exception;

public class EndidadeNaoEcontradaException extends RuntimeException{

    private static  final long serialVersionUID = 1L;

    public EndidadeNaoEcontradaException(String mensagem){
        super(mensagem);

    }

}
