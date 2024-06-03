package br.com.jotape.gestaovagas.exception;

public class JobNotFoundException extends RuntimeException {

    public JobNotFoundException() {
        super("Usuário não existe");
    }
}
