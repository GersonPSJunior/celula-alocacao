package br.com.duosdevelop.vb.igrejaalocacao.services.validation;

import br.com.duosdevelop.vb.igrejaalocacao.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class CPFExistValidator implements ConstraintValidator<CPFExist, String> {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return Optional.empty().equals(pessoaRepository.findByCpf(value));
    }
}
