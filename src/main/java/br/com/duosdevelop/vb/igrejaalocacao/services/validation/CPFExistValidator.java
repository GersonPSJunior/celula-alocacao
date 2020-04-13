package br.com.duosdevelop.vb.igrejaalocacao.services.validation;

import br.com.duosdevelop.vb.igrejaalocacao.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CPFExistValidator implements ConstraintValidator<CPFExist, String> {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Override
    public void initialize(CPFExist constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return pessoaRepository.findByCpf(value) == null;
    }
}
