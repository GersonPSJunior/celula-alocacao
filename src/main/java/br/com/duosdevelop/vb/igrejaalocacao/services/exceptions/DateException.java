package br.com.duosdevelop.vb.igrejaalocacao.services.exceptions;

public class DateException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DateException(String msg) {
        super(msg);
    }
    public DateException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
