package br.com.jotape.gestaovagas.exception;

import br.com.jotape.gestaovagas.dto.ErroMessageDTO;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionsHandler {

    private MessageSource messageSource;

    public ExceptionsHandler(MessageSource message) {
        this.messageSource = message;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErroMessageDTO>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<ErroMessageDTO> dto = new ArrayList<>();

        e.getBindingResult().getFieldErrors().forEach(error -> {
            String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());
            ErroMessageDTO errorDTO = new ErroMessageDTO(message, error.getField());
            dto.add(errorDTO);
        });
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }
}
