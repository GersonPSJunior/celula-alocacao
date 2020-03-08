package br.com.duosdevelop.vb.igrejaalocacao.exceptionhandler;

import br.com.duosdevelop.vb.igrejaalocacao.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> parseData(Exception e, HttpServletRequest request){
        Error error = new Error(e.getMessage(), e.getCause().toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
        StandardError error = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String messageUser = messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
        String messageDev = ex.getCause().toString();
        return handleExceptionInternal(ex, new Error(messageUser, messageDev), headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        return handleExceptionInternal(ex, createErrors(ex.getBindingResult()), headers, status, request);
    }

    private List<Error> createErrors(BindingResult bindingResult){
        return bindingResult.getFieldErrors().stream().map(fieldError ->
            new Error(messageSource.getMessage(fieldError, LocaleContextHolder.getLocale()),
                    fieldError.toString())).collect(Collectors.toList());
    }

    public static class Error{
        private String messageUser;
        private String messageDev;

        public Error(String messageUser, String messageDev) {
            this.messageUser = messageUser;
            this.messageDev = messageDev;
        }

        public String getMessageUsuario() {
            return messageUser;
        }

        public String getMessageDesenvolvedor() {
            return messageDev;
        }
    }
}
