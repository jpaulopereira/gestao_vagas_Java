package br.com.joaopaulo.gestao_vagas.exceptions;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice // trata a mensagem de erro, antes de chegar ao cliente
public class ExeptionHandlerController {

     private MessageSource messageSource;

     public ExeptionHandlerController(MessageSource message) { // não inicializa o messageSource como null
          this.messageSource = message;
     }

     @ExceptionHandler(MethodArgumentNotValidException.class)
     public ResponseEntity<List<ErrorMessageDTO>> handleMethodArgumentNotValidException(
               MethodArgumentNotValidException e) {

          List<ErrorMessageDTO> dto = new ArrayList<>();

          e.getBindingResult().getFieldErrors().forEach(err -> {
               String message = messageSource.getMessage(err, LocaleContextHolder.getLocale());
               ErrorMessageDTO error = new ErrorMessageDTO(message, err.getField());
               dto.add(error);
          });

          return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
     }

}
