package br.com.jotape.gestaovagas.exception;

public class UserFoundException extends RuntimeException {

    public UserFoundException() {
        super("Usuário já existe");
    }
}
