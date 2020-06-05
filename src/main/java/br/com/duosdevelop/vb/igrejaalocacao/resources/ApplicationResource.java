package br.com.duosdevelop.vb.igrejaalocacao.resources;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationResource {

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public ResponseEntity<String> home() {
        final String htmlContent = "<!DOCTYPE html><html><body>"
                + "<h1>Bem vindo a Celula Alocação REST Webservice.</h1>"
                + "<p style='font-size: large;'>Click aqui para acessar <a href='swagger-ui.html'>documentação da API REST</a> distribuído por <a href='https://swagger.io/'>Swagger</a></p>"
                + "</body></html>";
        final ResponseEntity<String> responseEntity = new ResponseEntity<>(htmlContent, HttpStatus.OK);
        return responseEntity;
    }
}
