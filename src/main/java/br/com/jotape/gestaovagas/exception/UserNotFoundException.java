package br.com.jotape.gestaovagas.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super("Usuário não existe");
    }
}
