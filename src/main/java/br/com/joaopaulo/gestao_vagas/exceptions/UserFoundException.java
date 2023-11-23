package br.com.joaopaulo.gestao_vagas.exceptions;

public class UserFoundException extends RuntimeException {
     public UserFoundException() {
         super("Usuário já existe"); //chama o construtor da classe pai
     }
}
