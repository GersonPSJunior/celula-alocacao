package br.com.duosdevelop.vb.igrejaalocacao.event;

import org.springframework.context.ApplicationEvent;

import javax.servlet.http.HttpServletResponse;

public class CreateResourceEvent extends ApplicationEvent {

    private static final long serialVersionUID = 1L;

    private HttpServletResponse response;
    private Integer codigo;

    public CreateResourceEvent(Object source, HttpServletResponse response, Integer codigo) {
        super(source);
        this.response = response;
        this.codigo = codigo;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public Integer getCodigo() {
        return codigo;
    }
}
